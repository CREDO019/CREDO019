package org.bouncycastle.crypto.encodings;

import com.google.common.base.Ascii;
import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;

/* loaded from: classes5.dex */
public class ISO9796d1Encoding implements AsymmetricBlockCipher {
    private int bitSize;
    private AsymmetricBlockCipher engine;
    private boolean forEncryption;
    private BigInteger modulus;
    private int padBits = 0;
    private static final BigInteger SIXTEEN = BigInteger.valueOf(16);
    private static final BigInteger SIX = BigInteger.valueOf(6);
    private static byte[] shadows = {Ascii.f1129SO, 3, 5, 8, 9, 4, 2, Ascii.f1128SI, 0, 13, Ascii.f1132VT, 6, 7, 10, Ascii.f1121FF, 1};
    private static byte[] inverse = {8, Ascii.f1128SI, 6, 1, 5, 2, Ascii.f1132VT, Ascii.f1121FF, 3, 4, 13, 10, Ascii.f1129SO, 9, 0, 7};

    public ISO9796d1Encoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.engine = asymmetricBlockCipher;
    }

    private static byte[] convertOutputDecryptOnly(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            int length = byteArray.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(byteArray, 1, bArr, 0, length);
            return bArr;
        }
        return byteArray;
    }

    private byte[] decodeBlock(byte[] bArr, int r10, int r11) throws InvalidCipherTextException {
        byte[] processBlock = this.engine.processBlock(bArr, r10, r11);
        int r102 = (this.bitSize + 13) / 16;
        BigInteger bigInteger = new BigInteger(1, processBlock);
        BigInteger bigInteger2 = SIXTEEN;
        BigInteger mod = bigInteger.mod(bigInteger2);
        BigInteger bigInteger3 = SIX;
        if (!mod.equals(bigInteger3)) {
            if (!this.modulus.subtract(bigInteger).mod(bigInteger2).equals(bigInteger3)) {
                throw new InvalidCipherTextException("resulting integer iS or (modulus - iS) is not congruent to 6 mod 16");
            }
            bigInteger = this.modulus.subtract(bigInteger);
        }
        byte[] convertOutputDecryptOnly = convertOutputDecryptOnly(bigInteger);
        if ((convertOutputDecryptOnly[convertOutputDecryptOnly.length - 1] & Ascii.f1128SI) == 6) {
            convertOutputDecryptOnly[convertOutputDecryptOnly.length - 1] = (byte) (((convertOutputDecryptOnly[convertOutputDecryptOnly.length - 1] & 255) >>> 4) | (inverse[(convertOutputDecryptOnly[convertOutputDecryptOnly.length - 2] & 255) >> 4] << 4));
            byte[] bArr2 = shadows;
            convertOutputDecryptOnly[0] = (byte) (bArr2[convertOutputDecryptOnly[1] & Ascii.f1128SI] | (bArr2[(convertOutputDecryptOnly[1] & 255) >>> 4] << 4));
            int r2 = 0;
            boolean z = false;
            int r4 = 1;
            for (int length = convertOutputDecryptOnly.length - 1; length >= convertOutputDecryptOnly.length - (r102 * 2); length -= 2) {
                byte[] bArr3 = shadows;
                int r5 = bArr3[convertOutputDecryptOnly[length] & Ascii.f1128SI] | (bArr3[(convertOutputDecryptOnly[length] & 255) >>> 4] << 4);
                int r6 = length - 1;
                if (((convertOutputDecryptOnly[r6] ^ r5) & 255) != 0) {
                    if (z) {
                        throw new InvalidCipherTextException("invalid tsums in block");
                    }
                    r4 = (convertOutputDecryptOnly[r6] ^ r5) & 255;
                    r2 = r6;
                    z = true;
                }
            }
            convertOutputDecryptOnly[r2] = 0;
            int length2 = (convertOutputDecryptOnly.length - r2) / 2;
            byte[] bArr4 = new byte[length2];
            for (int r1 = 0; r1 < length2; r1++) {
                bArr4[r1] = convertOutputDecryptOnly[(r1 * 2) + r2 + 1];
            }
            this.padBits = r4 - 1;
            return bArr4;
        }
        throw new InvalidCipherTextException("invalid forcing byte in block");
    }

    private byte[] encodeBlock(byte[] bArr, int r13, int r14) throws InvalidCipherTextException {
        int r0 = this.bitSize;
        int r1 = (r0 + 7) / 8;
        byte[] bArr2 = new byte[r1];
        int r5 = 1;
        int r4 = this.padBits + 1;
        int r02 = (r0 + 13) / 16;
        int r7 = 0;
        while (r7 < r02) {
            if (r7 > r02 - r14) {
                int r9 = r02 - r7;
                System.arraycopy(bArr, (r13 + r14) - r9, bArr2, r1 - r02, r9);
            } else {
                System.arraycopy(bArr, r13, bArr2, r1 - (r7 + r14), r14);
            }
            r7 += r14;
        }
        for (int r12 = r1 - (r02 * 2); r12 != r1; r12 += 2) {
            byte b = bArr2[(r1 - r02) + (r12 / 2)];
            byte[] bArr3 = shadows;
            bArr2[r12] = (byte) (bArr3[b & Ascii.f1128SI] | (bArr3[(b & 255) >>> 4] << 4));
            bArr2[r12 + 1] = b;
        }
        int r122 = r1 - (r14 * 2);
        bArr2[r122] = (byte) (bArr2[r122] ^ r4);
        int r123 = r1 - 1;
        bArr2[r123] = (byte) ((bArr2[r123] << 4) | 6);
        int r124 = 8 - ((this.bitSize - 1) % 8);
        if (r124 != 8) {
            bArr2[0] = (byte) (bArr2[0] & (255 >>> r124));
            bArr2[0] = (byte) ((128 >>> r124) | bArr2[0]);
            r5 = 0;
        } else {
            bArr2[0] = 0;
            bArr2[1] = (byte) (bArr2[1] | 128);
        }
        return this.engine.processBlock(bArr2, r5, r1 - r5);
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        int inputBlockSize = this.engine.getInputBlockSize();
        return this.forEncryption ? (inputBlockSize + 1) / 2 : inputBlockSize;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        int outputBlockSize = this.engine.getOutputBlockSize();
        return this.forEncryption ? outputBlockSize : (outputBlockSize + 1) / 2;
    }

    public int getPadBits() {
        return this.padBits;
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters = cipherParameters instanceof ParametersWithRandom ? (RSAKeyParameters) ((ParametersWithRandom) cipherParameters).getParameters() : (RSAKeyParameters) cipherParameters;
        this.engine.init(z, cipherParameters);
        BigInteger modulus = rSAKeyParameters.getModulus();
        this.modulus = modulus;
        this.bitSize = modulus.bitLength();
        this.forEncryption = z;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int r3, int r4) throws InvalidCipherTextException {
        return this.forEncryption ? encodeBlock(bArr, r3, r4) : decodeBlock(bArr, r3, r4);
    }

    public void setPadBits(int r2) {
        if (r2 > 7) {
            throw new IllegalArgumentException("padBits > 7");
        }
        this.padBits = r2;
    }
}
