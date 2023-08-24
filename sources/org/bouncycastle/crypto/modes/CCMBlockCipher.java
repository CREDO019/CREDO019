package org.bouncycastle.crypto.modes;

import com.google.common.primitives.SignedBytes;
import java.io.ByteArrayOutputStream;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.macs.CBCBlockCipherMac;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* loaded from: classes5.dex */
public class CCMBlockCipher implements AEADBlockCipher {
    private int blockSize;
    private BlockCipher cipher;
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private CipherParameters keyParam;
    private byte[] macBlock;
    private int macSize;
    private byte[] nonce;
    private ExposedByteArrayOutputStream associatedText = new ExposedByteArrayOutputStream();
    private ExposedByteArrayOutputStream data = new ExposedByteArrayOutputStream();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        public ExposedByteArrayOutputStream() {
        }

        public byte[] getBuffer() {
            return this.buf;
        }
    }

    public CCMBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.macBlock = new byte[blockSize];
        if (blockSize != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
    }

    private int calculateMac(byte[] bArr, int r12, int r13, byte[] bArr2) {
        CBCBlockCipherMac cBCBlockCipherMac = new CBCBlockCipherMac(this.cipher, this.macSize * 8);
        cBCBlockCipherMac.init(this.keyParam);
        byte[] bArr3 = new byte[16];
        if (hasAssociatedText()) {
            bArr3[0] = (byte) (bArr3[0] | SignedBytes.MAX_POWER_OF_TWO);
        }
        int r6 = 2;
        bArr3[0] = (byte) (bArr3[0] | ((((cBCBlockCipherMac.getMacSize() - 2) / 2) & 7) << 3));
        byte b = bArr3[0];
        byte[] bArr4 = this.nonce;
        bArr3[0] = (byte) (b | (((15 - bArr4.length) - 1) & 7));
        System.arraycopy(bArr4, 0, bArr3, 1, bArr4.length);
        int r3 = r13;
        int r5 = 1;
        while (r3 > 0) {
            bArr3[16 - r5] = (byte) (r3 & 255);
            r3 >>>= 8;
            r5++;
        }
        cBCBlockCipherMac.update(bArr3, 0, 16);
        if (hasAssociatedText()) {
            int associatedTextLength = getAssociatedTextLength();
            if (associatedTextLength < 65280) {
                cBCBlockCipherMac.update((byte) (associatedTextLength >> 8));
                cBCBlockCipherMac.update((byte) associatedTextLength);
            } else {
                cBCBlockCipherMac.update((byte) -1);
                cBCBlockCipherMac.update((byte) -2);
                cBCBlockCipherMac.update((byte) (associatedTextLength >> 24));
                cBCBlockCipherMac.update((byte) (associatedTextLength >> 16));
                cBCBlockCipherMac.update((byte) (associatedTextLength >> 8));
                cBCBlockCipherMac.update((byte) associatedTextLength);
                r6 = 6;
            }
            byte[] bArr5 = this.initialAssociatedText;
            if (bArr5 != null) {
                cBCBlockCipherMac.update(bArr5, 0, bArr5.length);
            }
            if (this.associatedText.size() > 0) {
                cBCBlockCipherMac.update(this.associatedText.getBuffer(), 0, this.associatedText.size());
            }
            int r62 = (r6 + associatedTextLength) % 16;
            if (r62 != 0) {
                while (r62 != 16) {
                    cBCBlockCipherMac.update((byte) 0);
                    r62++;
                }
            }
        }
        cBCBlockCipherMac.update(bArr, r12, r13);
        return cBCBlockCipherMac.doFinal(bArr2, 0);
    }

    private int getAssociatedTextLength() {
        int size = this.associatedText.size();
        byte[] bArr = this.initialAssociatedText;
        return size + (bArr == null ? 0 : bArr.length);
    }

    private int getMacSize(boolean z, int r2) {
        if (!z || (r2 >= 32 && r2 <= 128 && (r2 & 15) == 0)) {
            return r2 >>> 3;
        }
        throw new IllegalArgumentException("tag length in octets must be one of {4,6,8,10,12,14,16}");
    }

    private boolean hasAssociatedText() {
        return getAssociatedTextLength() > 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int r9) throws IllegalStateException, InvalidCipherTextException {
        int processPacket = processPacket(this.data.getBuffer(), 0, this.data.size(), bArr, r9);
        reset();
        return processPacket;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/CCM";
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public byte[] getMac() {
        int r0 = this.macSize;
        byte[] bArr = new byte[r0];
        System.arraycopy(this.macBlock, 0, bArr, 0, r0);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int r2) {
        int size = r2 + this.data.size();
        if (this.forEncryption) {
            return size + this.macSize;
        }
        int r0 = this.macSize;
        if (size < r0) {
            return 0;
        }
        return size - r0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int r1) {
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        CipherParameters parameters;
        this.forEncryption = z;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            this.nonce = aEADParameters.getNonce();
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            this.macSize = getMacSize(z, aEADParameters.getMacSize());
            parameters = aEADParameters.getKey();
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameters passed to CCM: " + cipherParameters.getClass().getName());
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.nonce = parametersWithIV.getIV();
            this.initialAssociatedText = null;
            this.macSize = getMacSize(z, 64);
            parameters = parametersWithIV.getParameters();
        }
        if (parameters != null) {
            this.keyParam = parameters;
        }
        byte[] bArr = this.nonce;
        if (bArr == null || bArr.length < 7 || bArr.length > 13) {
            throw new IllegalArgumentException("nonce must have length from 7 to 13 octets");
        }
        reset();
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        this.associatedText.write(b);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int r3, int r4) {
        this.associatedText.write(bArr, r3, r4);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processByte(byte b, byte[] bArr, int r3) throws DataLengthException, IllegalStateException {
        this.data.write(b);
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int r2, int r3, byte[] bArr2, int r5) throws DataLengthException, IllegalStateException {
        if (bArr.length >= r2 + r3) {
            this.data.write(bArr, r2, r3);
            return 0;
        }
        throw new DataLengthException("Input buffer too short");
    }

    public int processPacket(byte[] bArr, int r11, int r12, byte[] bArr2, int r14) throws IllegalStateException, InvalidCipherTextException, DataLengthException {
        int r5;
        if (this.keyParam == null) {
            throw new IllegalStateException("CCM cipher unitialized.");
        }
        byte[] bArr3 = this.nonce;
        int length = 15 - bArr3.length;
        if (length < 4 && r12 >= (1 << (length * 8))) {
            throw new IllegalStateException("CCM packet too large for choice of q.");
        }
        byte[] bArr4 = new byte[this.blockSize];
        bArr4[0] = (byte) ((length - 1) & 7);
        System.arraycopy(bArr3, 0, bArr4, 1, bArr3.length);
        SICBlockCipher sICBlockCipher = new SICBlockCipher(this.cipher);
        sICBlockCipher.init(this.forEncryption, new ParametersWithIV(this.keyParam, bArr4));
        if (!this.forEncryption) {
            int r1 = this.macSize;
            if (r12 >= r1) {
                int r122 = r12 - r1;
                if (bArr2.length >= r122 + r14) {
                    int r2 = r11 + r122;
                    System.arraycopy(bArr, r2, this.macBlock, 0, r1);
                    byte[] bArr5 = this.macBlock;
                    sICBlockCipher.processBlock(bArr5, 0, bArr5, 0);
                    int r13 = this.macSize;
                    while (true) {
                        byte[] bArr6 = this.macBlock;
                        if (r13 == bArr6.length) {
                            break;
                        }
                        bArr6[r13] = 0;
                        r13++;
                    }
                    int r15 = r11;
                    int r3 = r14;
                    while (true) {
                        r5 = this.blockSize;
                        if (r15 >= r2 - r5) {
                            break;
                        }
                        sICBlockCipher.processBlock(bArr, r15, bArr2, r3);
                        int r52 = this.blockSize;
                        r3 += r52;
                        r15 += r52;
                    }
                    byte[] bArr7 = new byte[r5];
                    int r112 = r122 - (r15 - r11);
                    System.arraycopy(bArr, r15, bArr7, 0, r112);
                    sICBlockCipher.processBlock(bArr7, 0, bArr7, 0);
                    System.arraycopy(bArr7, 0, bArr2, r3, r112);
                    byte[] bArr8 = new byte[this.blockSize];
                    calculateMac(bArr2, r14, r122, bArr8);
                    if (Arrays.constantTimeAreEqual(this.macBlock, bArr8)) {
                        return r122;
                    }
                    throw new InvalidCipherTextException("mac check in CCM failed");
                }
                throw new OutputLengthException("Output buffer too short.");
            }
            throw new InvalidCipherTextException("data too short");
        }
        int r16 = this.macSize + r12;
        if (bArr2.length < r16 + r14) {
            throw new OutputLengthException("Output buffer too short.");
        }
        calculateMac(bArr, r11, r12, this.macBlock);
        byte[] bArr9 = new byte[this.blockSize];
        sICBlockCipher.processBlock(this.macBlock, 0, bArr9, 0);
        int r32 = r11;
        int r53 = r14;
        while (true) {
            int r6 = r11 + r12;
            int r7 = this.blockSize;
            if (r32 >= r6 - r7) {
                byte[] bArr10 = new byte[r7];
                int r62 = r6 - r32;
                System.arraycopy(bArr, r32, bArr10, 0, r62);
                sICBlockCipher.processBlock(bArr10, 0, bArr10, 0);
                System.arraycopy(bArr10, 0, bArr2, r53, r62);
                System.arraycopy(bArr9, 0, bArr2, r14 + r12, this.macSize);
                return r16;
            }
            sICBlockCipher.processBlock(bArr, r32, bArr2, r53);
            int r63 = this.blockSize;
            r53 += r63;
            r32 += r63;
        }
    }

    public byte[] processPacket(byte[] bArr, int r9, int r10) throws IllegalStateException, InvalidCipherTextException {
        int r0;
        if (this.forEncryption) {
            r0 = this.macSize + r10;
        } else {
            int r02 = this.macSize;
            if (r10 < r02) {
                throw new InvalidCipherTextException("data too short");
            }
            r0 = r10 - r02;
        }
        byte[] bArr2 = new byte[r0];
        processPacket(bArr, r9, r10, bArr2, 0);
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        this.cipher.reset();
        this.associatedText.reset();
        this.data.reset();
    }
}
