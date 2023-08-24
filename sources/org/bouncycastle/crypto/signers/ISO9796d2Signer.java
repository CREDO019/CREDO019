package org.bouncycastle.crypto.signers;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.SignerWithRecovery;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class ISO9796d2Signer implements SignerWithRecovery {
    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_WHIRLPOOL = 14284;
    private byte[] block;
    private AsymmetricBlockCipher cipher;
    private Digest digest;
    private boolean fullMessage;
    private int keyBits;
    private byte[] mBuf;
    private int messageLength;
    private byte[] preBlock;
    private byte[] preSig;
    private byte[] recoveredMessage;
    private int trailer;

    public ISO9796d2Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest) {
        this(asymmetricBlockCipher, digest, false);
    }

    public ISO9796d2Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, boolean z) {
        int intValue;
        this.cipher = asymmetricBlockCipher;
        this.digest = digest;
        if (z) {
            intValue = 188;
        } else {
            Integer trailer = ISOTrailers.getTrailer(digest);
            if (trailer == null) {
                throw new IllegalArgumentException("no valid trailer for digest: " + digest.getAlgorithmName());
            }
            intValue = trailer.intValue();
        }
        this.trailer = intValue;
    }

    private void clearBlock(byte[] bArr) {
        for (int r1 = 0; r1 != bArr.length; r1++) {
            bArr[r1] = 0;
        }
    }

    private boolean isSameAs(byte[] bArr, byte[] bArr2) {
        boolean z;
        int r0 = this.messageLength;
        byte[] bArr3 = this.mBuf;
        if (r0 > bArr3.length) {
            z = bArr3.length <= bArr2.length;
            for (int r02 = 0; r02 != this.mBuf.length; r02++) {
                if (bArr[r02] != bArr2[r02]) {
                    z = false;
                }
            }
        } else {
            z = r0 == bArr2.length;
            for (int r03 = 0; r03 != bArr2.length; r03++) {
                if (bArr[r03] != bArr2[r03]) {
                    z = false;
                }
            }
        }
        return z;
    }

    private boolean returnFalse(byte[] bArr) {
        this.messageLength = 0;
        clearBlock(this.mBuf);
        clearBlock(bArr);
        return false;
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException {
        int r1;
        int r4;
        byte b;
        int r42;
        int digestSize = this.digest.getDigestSize();
        if (this.trailer == 188) {
            byte[] bArr = this.block;
            r4 = (bArr.length - digestSize) - 1;
            this.digest.doFinal(bArr, r4);
            byte[] bArr2 = this.block;
            bArr2[bArr2.length - 1] = PSSSigner.TRAILER_IMPLICIT;
            r1 = 8;
        } else {
            r1 = 16;
            byte[] bArr3 = this.block;
            int length = (bArr3.length - digestSize) - 2;
            this.digest.doFinal(bArr3, length);
            byte[] bArr4 = this.block;
            int r7 = this.trailer;
            bArr4[bArr4.length - 2] = (byte) (r7 >>> 8);
            bArr4[bArr4.length - 1] = (byte) r7;
            r4 = length;
        }
        int r5 = this.messageLength;
        int r0 = ((((digestSize + r5) * 8) + r1) + 4) - this.keyBits;
        if (r0 > 0) {
            int r52 = r5 - ((r0 + 7) / 8);
            b = 96;
            r42 = r4 - r52;
            System.arraycopy(this.mBuf, 0, this.block, r42, r52);
            this.recoveredMessage = new byte[r52];
        } else {
            b = SignedBytes.MAX_POWER_OF_TWO;
            r42 = r4 - r5;
            System.arraycopy(this.mBuf, 0, this.block, r42, r5);
            this.recoveredMessage = new byte[this.messageLength];
        }
        int r43 = r42 - 1;
        if (r43 > 0) {
            for (int r2 = r43; r2 != 0; r2--) {
                this.block[r2] = -69;
            }
            byte[] bArr5 = this.block;
            bArr5[r43] = (byte) (bArr5[r43] ^ 1);
            bArr5[0] = Ascii.f1132VT;
            bArr5[0] = (byte) (bArr5[0] | b);
        } else {
            byte[] bArr6 = this.block;
            bArr6[0] = 10;
            bArr6[0] = (byte) (bArr6[0] | b);
        }
        AsymmetricBlockCipher asymmetricBlockCipher = this.cipher;
        byte[] bArr7 = this.block;
        byte[] processBlock = asymmetricBlockCipher.processBlock(bArr7, 0, bArr7.length);
        this.fullMessage = (b & 32) == 0;
        byte[] bArr8 = this.mBuf;
        byte[] bArr9 = this.recoveredMessage;
        System.arraycopy(bArr8, 0, bArr9, 0, bArr9.length);
        this.messageLength = 0;
        clearBlock(this.mBuf);
        clearBlock(this.block);
        return processBlock;
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public byte[] getRecoveredMessage() {
        return this.recoveredMessage;
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public boolean hasFullMessage() {
        return this.fullMessage;
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters = (RSAKeyParameters) cipherParameters;
        this.cipher.init(z, rSAKeyParameters);
        int bitLength = rSAKeyParameters.getModulus().bitLength();
        this.keyBits = bitLength;
        byte[] bArr = new byte[(bitLength + 7) / 8];
        this.block = bArr;
        int r3 = this.trailer;
        int length = bArr.length;
        if (r3 == 188) {
            this.mBuf = new byte[(length - this.digest.getDigestSize()) - 2];
        } else {
            this.mBuf = new byte[(length - this.digest.getDigestSize()) - 3];
        }
        reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.digest.reset();
        this.messageLength = 0;
        clearBlock(this.mBuf);
        byte[] bArr = this.recoveredMessage;
        if (bArr != null) {
            clearBlock(bArr);
        }
        this.recoveredMessage = null;
        this.fullMessage = false;
        if (this.preSig != null) {
            this.preSig = null;
            clearBlock(this.preBlock);
            this.preBlock = null;
        }
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        this.digest.update(b);
        int r0 = this.messageLength;
        byte[] bArr = this.mBuf;
        if (r0 < bArr.length) {
            bArr[r0] = b;
        }
        this.messageLength = r0 + 1;
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int r4, int r5) {
        while (r5 > 0 && this.messageLength < this.mBuf.length) {
            update(bArr[r4]);
            r4++;
            r5--;
        }
        this.digest.update(bArr, r4, r5);
        this.messageLength += r5;
    }

    @Override // org.bouncycastle.crypto.SignerWithRecovery
    public void updateWithRecoveredMessage(byte[] bArr) throws InvalidCipherTextException {
        byte[] processBlock = this.cipher.processBlock(bArr, 0, bArr.length);
        if (((processBlock[0] & 192) ^ 64) != 0) {
            throw new InvalidCipherTextException("malformed signature");
        }
        if (((processBlock[processBlock.length - 1] & Ascii.f1128SI) ^ 12) != 0) {
            throw new InvalidCipherTextException("malformed signature");
        }
        int r3 = 2;
        if (((processBlock[processBlock.length - 1] & 255) ^ 188) == 0) {
            r3 = 1;
        } else {
            int r1 = ((processBlock[processBlock.length - 2] & 255) << 8) | (processBlock[processBlock.length - 1] & 255);
            Integer trailer = ISOTrailers.getTrailer(this.digest);
            if (trailer == null) {
                throw new IllegalArgumentException("unrecognised hash in signature");
            }
            int intValue = trailer.intValue();
            if (r1 != intValue && (intValue != 15052 || r1 != 16588)) {
                throw new IllegalStateException("signer initialised with wrong digest for trailer " + r1);
            }
        }
        int r12 = 0;
        while (r12 != processBlock.length && ((processBlock[r12] & Ascii.f1128SI) ^ 10) != 0) {
            r12++;
        }
        int r13 = r12 + 1;
        int length = ((processBlock.length - r3) - this.digest.getDigestSize()) - r13;
        if (length <= 0) {
            throw new InvalidCipherTextException("malformed block");
        }
        if ((processBlock[0] & 32) == 0) {
            this.fullMessage = true;
            byte[] bArr2 = new byte[length];
            this.recoveredMessage = bArr2;
            System.arraycopy(processBlock, r13, bArr2, 0, bArr2.length);
        } else {
            this.fullMessage = false;
            byte[] bArr3 = new byte[length];
            this.recoveredMessage = bArr3;
            System.arraycopy(processBlock, r13, bArr3, 0, bArr3.length);
        }
        this.preSig = bArr;
        this.preBlock = processBlock;
        Digest digest = this.digest;
        byte[] bArr4 = this.recoveredMessage;
        digest.update(bArr4, 0, bArr4.length);
        byte[] bArr5 = this.recoveredMessage;
        this.messageLength = bArr5.length;
        System.arraycopy(bArr5, 0, this.mBuf, 0, bArr5.length);
    }

    @Override // org.bouncycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        byte[] processBlock;
        byte[] bArr2 = this.preSig;
        if (bArr2 == null) {
            try {
                processBlock = this.cipher.processBlock(bArr, 0, bArr.length);
            } catch (Exception unused) {
                return false;
            }
        } else if (!Arrays.areEqual(bArr2, bArr)) {
            throw new IllegalStateException("updateWithRecoveredMessage called on different signature");
        } else {
            processBlock = this.preBlock;
            this.preSig = null;
            this.preBlock = null;
        }
        if (((processBlock[0] & 192) ^ 64) == 0 && ((processBlock[processBlock.length - 1] & Ascii.f1128SI) ^ 12) == 0) {
            int r3 = 2;
            if (((processBlock[processBlock.length - 1] & 255) ^ 188) == 0) {
                r3 = 1;
            } else {
                int r0 = ((processBlock[processBlock.length - 2] & 255) << 8) | (processBlock[processBlock.length - 1] & 255);
                Integer trailer = ISOTrailers.getTrailer(this.digest);
                if (trailer == null) {
                    throw new IllegalArgumentException("unrecognised hash in signature");
                }
                int intValue = trailer.intValue();
                if (r0 != intValue && (intValue != 15052 || r0 != 16588)) {
                    throw new IllegalStateException("signer initialised with wrong digest for trailer " + r0);
                }
            }
            int r02 = 0;
            while (r02 != processBlock.length && ((processBlock[r02] & Ascii.f1128SI) ^ 10) != 0) {
                r02++;
            }
            int r03 = r02 + 1;
            int digestSize = this.digest.getDigestSize();
            byte[] bArr3 = new byte[digestSize];
            int length = (processBlock.length - r3) - digestSize;
            int r32 = length - r03;
            if (r32 <= 0) {
                return returnFalse(processBlock);
            }
            if ((processBlock[0] & 32) == 0) {
                this.fullMessage = true;
                if (this.messageLength > r32) {
                    return returnFalse(processBlock);
                }
                this.digest.reset();
                this.digest.update(processBlock, r03, r32);
                this.digest.doFinal(bArr3, 0);
                boolean z = true;
                for (int r7 = 0; r7 != digestSize; r7++) {
                    int r9 = length + r7;
                    processBlock[r9] = (byte) (processBlock[r9] ^ bArr3[r7]);
                    if (processBlock[r9] != 0) {
                        z = false;
                    }
                }
                if (!z) {
                    return returnFalse(processBlock);
                }
                byte[] bArr4 = new byte[r32];
                this.recoveredMessage = bArr4;
                System.arraycopy(processBlock, r03, bArr4, 0, bArr4.length);
            } else {
                this.fullMessage = false;
                this.digest.doFinal(bArr3, 0);
                boolean z2 = true;
                for (int r72 = 0; r72 != digestSize; r72++) {
                    int r92 = length + r72;
                    processBlock[r92] = (byte) (processBlock[r92] ^ bArr3[r72]);
                    if (processBlock[r92] != 0) {
                        z2 = false;
                    }
                }
                if (!z2) {
                    return returnFalse(processBlock);
                }
                byte[] bArr5 = new byte[r32];
                this.recoveredMessage = bArr5;
                System.arraycopy(processBlock, r03, bArr5, 0, bArr5.length);
            }
            if (this.messageLength == 0 || isSameAs(this.mBuf, this.recoveredMessage)) {
                clearBlock(this.mBuf);
                clearBlock(processBlock);
                this.messageLength = 0;
                return true;
            }
            return returnFalse(processBlock);
        }
        return returnFalse(processBlock);
    }
}
