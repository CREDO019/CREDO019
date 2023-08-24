package org.bouncycastle.crypto.modes;

import java.io.ByteArrayOutputStream;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.modes.kgcm.KGCMMultiplier;
import org.bouncycastle.crypto.modes.kgcm.Tables16kKGCMMultiplier_512;
import org.bouncycastle.crypto.modes.kgcm.Tables4kKGCMMultiplier_128;
import org.bouncycastle.crypto.modes.kgcm.Tables8kKGCMMultiplier_256;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class KGCMBlockCipher implements AEADBlockCipher {
    private static final int MIN_MAC_BITS = 64;

    /* renamed from: b */
    private long[] f2068b;
    private final int blockSize;
    private BufferedBlockCipher ctrEngine;
    private BlockCipher engine;
    private boolean forEncryption;
    private byte[] initialAssociatedText;

    /* renamed from: iv */
    private byte[] f2069iv;
    private byte[] macBlock;
    private KGCMMultiplier multiplier;
    private ExposedByteArrayOutputStream associatedText = new ExposedByteArrayOutputStream();
    private ExposedByteArrayOutputStream data = new ExposedByteArrayOutputStream();
    private int macSize = -1;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class ExposedByteArrayOutputStream extends ByteArrayOutputStream {
        public ExposedByteArrayOutputStream() {
        }

        public byte[] getBuffer() {
            return this.buf;
        }
    }

    public KGCMBlockCipher(BlockCipher blockCipher) {
        this.engine = blockCipher;
        this.ctrEngine = new BufferedBlockCipher(new KCTRBlockCipher(blockCipher));
        int blockSize = this.engine.getBlockSize();
        this.blockSize = blockSize;
        this.initialAssociatedText = new byte[blockSize];
        this.f2069iv = new byte[blockSize];
        this.multiplier = createDefaultMultiplier(blockSize);
        this.f2068b = new long[blockSize >>> 3];
        this.macBlock = null;
    }

    private void calculateMac(byte[] bArr, int r6, int r7, int r8) {
        int r0 = r6 + r7;
        while (r6 < r0) {
            xorWithInput(this.f2068b, bArr, r6);
            this.multiplier.multiplyH(this.f2068b);
            r6 += this.blockSize;
        }
        long j = (BodyPartID.bodyIdMax & r7) << 3;
        long[] jArr = this.f2068b;
        jArr[0] = ((r8 & BodyPartID.bodyIdMax) << 3) ^ jArr[0];
        int r5 = this.blockSize >>> 4;
        jArr[r5] = jArr[r5] ^ j;
        byte[] longToLittleEndian = Pack.longToLittleEndian(jArr);
        this.macBlock = longToLittleEndian;
        this.engine.processBlock(longToLittleEndian, 0, longToLittleEndian, 0);
    }

    private static KGCMMultiplier createDefaultMultiplier(int r1) {
        if (r1 != 16) {
            if (r1 != 32) {
                if (r1 == 64) {
                    return new Tables16kKGCMMultiplier_512();
                }
                throw new IllegalArgumentException("Only 128, 256, and 512 -bit block sizes supported");
            }
            return new Tables8kKGCMMultiplier_256();
        }
        return new Tables4kKGCMMultiplier_128();
    }

    private void processAAD(byte[] bArr, int r4, int r5) {
        int r52 = r5 + r4;
        while (r4 < r52) {
            xorWithInput(this.f2068b, bArr, r4);
            this.multiplier.multiplyH(this.f2068b);
            r4 += this.blockSize;
        }
    }

    private static void xorWithInput(long[] jArr, byte[] bArr, int r7) {
        for (int r0 = 0; r0 < jArr.length; r0++) {
            jArr[r0] = jArr[r0] ^ Pack.littleEndianToLong(bArr, r7);
            r7 += 8;
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int r11) throws IllegalStateException, InvalidCipherTextException {
        int doFinal;
        int size = this.data.size();
        if (this.forEncryption || size >= this.macSize) {
            byte[] bArr2 = new byte[this.blockSize];
            this.engine.processBlock(bArr2, 0, bArr2, 0);
            long[] jArr = new long[this.blockSize >>> 3];
            Pack.littleEndianToLong(bArr2, 0, jArr);
            this.multiplier.init(jArr);
            Arrays.fill(bArr2, (byte) 0);
            Arrays.fill(jArr, 0L);
            int size2 = this.associatedText.size();
            if (size2 > 0) {
                processAAD(this.associatedText.getBuffer(), 0, size2);
            }
            if (!this.forEncryption) {
                int r3 = size - this.macSize;
                if (bArr.length - r11 < r3) {
                    throw new OutputLengthException("Output buffer too short");
                }
                calculateMac(this.data.getBuffer(), 0, r3, size2);
                int processBytes = this.ctrEngine.processBytes(this.data.getBuffer(), 0, r3, bArr, r11);
                doFinal = processBytes + this.ctrEngine.doFinal(bArr, r11 + processBytes);
            } else if ((bArr.length - r11) - this.macSize < size) {
                throw new OutputLengthException("Output buffer too short");
            } else {
                int processBytes2 = this.ctrEngine.processBytes(this.data.getBuffer(), 0, size, bArr, r11);
                doFinal = processBytes2 + this.ctrEngine.doFinal(bArr, r11 + processBytes2);
                calculateMac(bArr, r11, size, size2);
            }
            byte[] bArr3 = this.macBlock;
            if (bArr3 != null) {
                if (this.forEncryption) {
                    System.arraycopy(bArr3, 0, bArr, r11 + doFinal, this.macSize);
                    reset();
                    return doFinal + this.macSize;
                }
                byte[] bArr4 = new byte[this.macSize];
                byte[] buffer = this.data.getBuffer();
                int r32 = this.macSize;
                System.arraycopy(buffer, size - r32, bArr4, 0, r32);
                int r2 = this.macSize;
                byte[] bArr5 = new byte[r2];
                System.arraycopy(this.macBlock, 0, bArr5, 0, r2);
                if (Arrays.constantTimeAreEqual(bArr4, bArr5)) {
                    reset();
                    return doFinal;
                }
                throw new InvalidCipherTextException("mac verification failed");
            }
            throw new IllegalStateException("mac is not calculated");
        }
        throw new InvalidCipherTextException("data too short");
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return this.engine.getAlgorithmName() + "/KGCM";
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
        return this.engine;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int r1) {
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        KeyParameter keyParameter;
        this.forEncryption = z;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            byte[] nonce = aEADParameters.getNonce();
            byte[] bArr = this.f2069iv;
            Arrays.fill(bArr, (byte) 0);
            System.arraycopy(nonce, 0, this.f2069iv, bArr.length - nonce.length, nonce.length);
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            int macSize = aEADParameters.getMacSize();
            if (macSize < 64 || macSize > (this.blockSize << 3) || (macSize & 7) != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
            this.macSize = macSize >>> 3;
            keyParameter = aEADParameters.getKey();
            byte[] bArr2 = this.initialAssociatedText;
            if (bArr2 != null) {
                processAADBytes(bArr2, 0, bArr2.length);
            }
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Invalid parameter passed");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] bArr3 = parametersWithIV.getIV();
            byte[] bArr4 = this.f2069iv;
            Arrays.fill(bArr4, (byte) 0);
            System.arraycopy(bArr3, 0, this.f2069iv, bArr4.length - bArr3.length, bArr3.length);
            this.initialAssociatedText = null;
            this.macSize = this.blockSize;
            keyParameter = (KeyParameter) parametersWithIV.getParameters();
        }
        this.macBlock = new byte[this.blockSize];
        this.ctrEngine.init(true, new ParametersWithIV(keyParameter, this.f2069iv));
        this.engine.init(true, keyParameter);
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
        throw new DataLengthException("input buffer too short");
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        Arrays.fill(this.f2068b, 0L);
        this.engine.reset();
        this.data.reset();
        this.associatedText.reset();
        byte[] bArr = this.initialAssociatedText;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }
}
