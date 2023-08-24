package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.modes.gcm.BasicGCMExponentiator;
import org.bouncycastle.crypto.modes.gcm.GCMExponentiator;
import org.bouncycastle.crypto.modes.gcm.GCMMultiplier;
import org.bouncycastle.crypto.modes.gcm.GCMUtil;
import org.bouncycastle.crypto.modes.gcm.Tables4kGCMMultiplier;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class GCMBlockCipher implements AEADBlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: H */
    private byte[] f2057H;

    /* renamed from: J0 */
    private byte[] f2058J0;

    /* renamed from: S */
    private byte[] f2059S;
    private byte[] S_at;
    private byte[] S_atPre;
    private byte[] atBlock;
    private int atBlockPos;
    private long atLength;
    private long atLengthPre;
    private int blocksRemaining;
    private byte[] bufBlock;
    private int bufOff;
    private BlockCipher cipher;
    private byte[] counter;
    private GCMExponentiator exp;
    private boolean forEncryption;
    private byte[] initialAssociatedText;
    private boolean initialised;
    private byte[] lastKey;
    private byte[] macBlock;
    private int macSize;
    private GCMMultiplier multiplier;
    private byte[] nonce;
    private long totalLength;

    public GCMBlockCipher(BlockCipher blockCipher) {
        this(blockCipher, null);
    }

    public GCMBlockCipher(BlockCipher blockCipher, GCMMultiplier gCMMultiplier) {
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("cipher required with a block size of 16.");
        }
        gCMMultiplier = gCMMultiplier == null ? new Tables4kGCMMultiplier() : gCMMultiplier;
        this.cipher = blockCipher;
        this.multiplier = gCMMultiplier;
    }

    private void checkStatus() {
        if (this.initialised) {
            return;
        }
        if (!this.forEncryption) {
            throw new IllegalStateException("GCM cipher needs to be initialised");
        }
        throw new IllegalStateException("GCM cipher cannot be reused for encryption");
    }

    private void gHASH(byte[] bArr, byte[] bArr2, int r6) {
        for (int r0 = 0; r0 < r6; r0 += 16) {
            gHASHPartial(bArr, bArr2, r0, Math.min(r6 - r0, 16));
        }
    }

    private void gHASHBlock(byte[] bArr, byte[] bArr2) {
        GCMUtil.xor(bArr, bArr2);
        this.multiplier.multiplyH(bArr);
    }

    private void gHASHBlock(byte[] bArr, byte[] bArr2, int r3) {
        GCMUtil.xor(bArr, bArr2, r3);
        this.multiplier.multiplyH(bArr);
    }

    private void gHASHPartial(byte[] bArr, byte[] bArr2, int r3, int r4) {
        GCMUtil.xor(bArr, bArr2, r3, r4);
        this.multiplier.multiplyH(bArr);
    }

    private void getNextCTRBlock(byte[] bArr) {
        int r0 = this.blocksRemaining;
        if (r0 == 0) {
            throw new IllegalStateException("Attempt to process too many blocks");
        }
        this.blocksRemaining = r0 - 1;
        byte[] bArr2 = this.counter;
        int r2 = (bArr2[15] & 255) + 1;
        bArr2[15] = (byte) r2;
        int r1 = (r2 >>> 8) + (bArr2[14] & 255);
        bArr2[14] = (byte) r1;
        int r12 = (r1 >>> 8) + (bArr2[13] & 255);
        bArr2[13] = (byte) r12;
        bArr2[12] = (byte) ((r12 >>> 8) + (bArr2[12] & 255));
        this.cipher.processBlock(bArr2, 0, bArr, 0);
    }

    private void initCipher() {
        if (this.atLength > 0) {
            System.arraycopy(this.S_at, 0, this.S_atPre, 0, 16);
            this.atLengthPre = this.atLength;
        }
        int r0 = this.atBlockPos;
        if (r0 > 0) {
            gHASHPartial(this.S_atPre, this.atBlock, 0, r0);
            this.atLengthPre += this.atBlockPos;
        }
        if (this.atLengthPre > 0) {
            System.arraycopy(this.S_atPre, 0, this.f2059S, 0, 16);
        }
    }

    private void processBlock(byte[] bArr, int r10, byte[] bArr2, int r12) {
        if (bArr2.length - r12 < 16) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.totalLength == 0) {
            initCipher();
        }
        byte[] bArr3 = new byte[16];
        getNextCTRBlock(bArr3);
        if (this.forEncryption) {
            GCMUtil.xor(bArr3, bArr, r10);
            gHASHBlock(this.f2059S, bArr3);
            System.arraycopy(bArr3, 0, bArr2, r12, 16);
        } else {
            gHASHBlock(this.f2059S, bArr, r10);
            GCMUtil.xor(bArr3, 0, bArr, r10, bArr2, r12);
        }
        this.totalLength += 16;
    }

    private void processPartial(byte[] bArr, int r5, int r6, byte[] bArr2, int r8) {
        byte[] bArr3 = new byte[16];
        getNextCTRBlock(bArr3);
        if (this.forEncryption) {
            GCMUtil.xor(bArr, r5, bArr3, 0, r6);
            gHASHPartial(this.f2059S, bArr, r5, r6);
        } else {
            gHASHPartial(this.f2059S, bArr, r5, r6);
            GCMUtil.xor(bArr, r5, bArr3, 0, r6);
        }
        System.arraycopy(bArr, r5, bArr2, r8, r6);
        this.totalLength += r6;
    }

    private void reset(boolean z) {
        this.cipher.reset();
        this.f2059S = new byte[16];
        this.S_at = new byte[16];
        this.S_atPre = new byte[16];
        this.atBlock = new byte[16];
        this.atBlockPos = 0;
        this.atLength = 0L;
        this.atLengthPre = 0L;
        this.counter = Arrays.clone(this.f2058J0);
        this.blocksRemaining = -2;
        this.bufOff = 0;
        this.totalLength = 0L;
        byte[] bArr = this.bufBlock;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
        if (z) {
            this.macBlock = null;
        }
        if (this.forEncryption) {
            this.initialised = false;
            return;
        }
        byte[] bArr2 = this.initialAssociatedText;
        if (bArr2 != null) {
            processAADBytes(bArr2, 0, bArr2.length);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int r15) throws IllegalStateException, InvalidCipherTextException {
        checkStatus();
        if (this.totalLength == 0) {
            initCipher();
        }
        int r0 = this.bufOff;
        if (!this.forEncryption) {
            int r1 = this.macSize;
            if (r0 < r1) {
                throw new InvalidCipherTextException("data too short");
            }
            r0 -= r1;
            if (bArr.length - r15 < r0) {
                throw new OutputLengthException("Output buffer too short");
            }
        } else if (bArr.length - r15 < this.macSize + r0) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (r0 > 0) {
            processPartial(this.bufBlock, 0, r0, bArr, r15);
        }
        long j = this.atLength;
        int r12 = this.atBlockPos;
        long j2 = j + r12;
        this.atLength = j2;
        if (j2 > this.atLengthPre) {
            if (r12 > 0) {
                gHASHPartial(this.S_at, this.atBlock, 0, r12);
            }
            if (this.atLengthPre > 0) {
                GCMUtil.xor(this.S_at, this.S_atPre);
            }
            long j3 = ((this.totalLength * 8) + 127) >>> 7;
            byte[] bArr2 = new byte[16];
            if (this.exp == null) {
                BasicGCMExponentiator basicGCMExponentiator = new BasicGCMExponentiator();
                this.exp = basicGCMExponentiator;
                basicGCMExponentiator.init(this.f2057H);
            }
            this.exp.exponentiateX(j3, bArr2);
            GCMUtil.multiply(this.S_at, bArr2);
            GCMUtil.xor(this.f2059S, this.S_at);
        }
        byte[] bArr3 = new byte[16];
        Pack.longToBigEndian(this.atLength * 8, bArr3, 0);
        Pack.longToBigEndian(this.totalLength * 8, bArr3, 8);
        gHASHBlock(this.f2059S, bArr3);
        byte[] bArr4 = new byte[16];
        this.cipher.processBlock(this.f2058J0, 0, bArr4, 0);
        GCMUtil.xor(bArr4, this.f2059S);
        int r2 = this.macSize;
        byte[] bArr5 = new byte[r2];
        this.macBlock = bArr5;
        System.arraycopy(bArr4, 0, bArr5, 0, r2);
        if (this.forEncryption) {
            System.arraycopy(this.macBlock, 0, bArr, r15 + this.bufOff, this.macSize);
            r0 += this.macSize;
        } else {
            int r14 = this.macSize;
            byte[] bArr6 = new byte[r14];
            System.arraycopy(this.bufBlock, r0, bArr6, 0, r14);
            if (!Arrays.constantTimeAreEqual(this.macBlock, bArr6)) {
                throw new InvalidCipherTextException("mac check in GCM failed");
            }
        }
        reset(false);
        return r0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/GCM";
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public byte[] getMac() {
        byte[] bArr = this.macBlock;
        return bArr == null ? new byte[this.macSize] : Arrays.clone(bArr);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int r2) {
        int r22 = r2 + this.bufOff;
        if (this.forEncryption) {
            return r22 + this.macSize;
        }
        int r0 = this.macSize;
        if (r22 < r0) {
            return 0;
        }
        return r22 - r0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.cipher;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int r2) {
        int r22 = r2 + this.bufOff;
        if (!this.forEncryption) {
            int r0 = this.macSize;
            if (r22 < r0) {
                return 0;
            }
            r22 -= r0;
        }
        return r22 - (r22 % 16);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] bArr;
        KeyParameter keyParameter;
        byte[] bArr2;
        this.forEncryption = z;
        this.macBlock = null;
        this.initialised = true;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            bArr = aEADParameters.getNonce();
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            int macSize = aEADParameters.getMacSize();
            if (macSize < 32 || macSize > 128 || macSize % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
            this.macSize = macSize / 8;
            keyParameter = aEADParameters.getKey();
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameters passed to GCM");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            this.initialAssociatedText = null;
            this.macSize = 16;
            keyParameter = (KeyParameter) parametersWithIV.getParameters();
        }
        this.bufBlock = new byte[z ? 16 : this.macSize + 16];
        if (bArr == null || bArr.length < 1) {
            throw new IllegalArgumentException("IV must be at least 1 byte");
        }
        if (z && (bArr2 = this.nonce) != null && Arrays.areEqual(bArr2, bArr)) {
            if (keyParameter == null) {
                throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
            }
            byte[] bArr3 = this.lastKey;
            if (bArr3 != null && Arrays.areEqual(bArr3, keyParameter.getKey())) {
                throw new IllegalArgumentException("cannot reuse nonce for GCM encryption");
            }
        }
        this.nonce = bArr;
        if (keyParameter != null) {
            this.lastKey = keyParameter.getKey();
        }
        if (keyParameter != null) {
            this.cipher.init(true, keyParameter);
            byte[] bArr4 = new byte[16];
            this.f2057H = bArr4;
            this.cipher.processBlock(bArr4, 0, bArr4, 0);
            this.multiplier.init(this.f2057H);
            this.exp = null;
        } else if (this.f2057H == null) {
            throw new IllegalArgumentException("Key must be specified in initial init");
        }
        byte[] bArr5 = new byte[16];
        this.f2058J0 = bArr5;
        byte[] bArr6 = this.nonce;
        if (bArr6.length == 12) {
            System.arraycopy(bArr6, 0, bArr5, 0, bArr6.length);
            this.f2058J0[15] = 1;
        } else {
            gHASH(bArr5, bArr6, bArr6.length);
            byte[] bArr7 = new byte[16];
            Pack.longToBigEndian(this.nonce.length * 8, bArr7, 8);
            gHASHBlock(this.f2058J0, bArr7);
        }
        this.f2059S = new byte[16];
        this.S_at = new byte[16];
        this.S_atPre = new byte[16];
        this.atBlock = new byte[16];
        this.atBlockPos = 0;
        this.atLength = 0L;
        this.atLengthPre = 0L;
        this.counter = Arrays.clone(this.f2058J0);
        this.blocksRemaining = -2;
        this.bufOff = 0;
        this.totalLength = 0L;
        byte[] bArr8 = this.initialAssociatedText;
        if (bArr8 != null) {
            processAADBytes(bArr8, 0, bArr8.length);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b) {
        checkStatus();
        byte[] bArr = this.atBlock;
        int r1 = this.atBlockPos;
        bArr[r1] = b;
        int r12 = r1 + 1;
        this.atBlockPos = r12;
        if (r12 == 16) {
            gHASHBlock(this.S_at, bArr);
            this.atBlockPos = 0;
            this.atLength += 16;
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int r8, int r9) {
        checkStatus();
        for (int r1 = 0; r1 < r9; r1++) {
            byte[] bArr2 = this.atBlock;
            int r3 = this.atBlockPos;
            bArr2[r3] = bArr[r8 + r1];
            int r32 = r3 + 1;
            this.atBlockPos = r32;
            if (r32 == 16) {
                gHASHBlock(this.S_at, bArr2);
                this.atBlockPos = 0;
                this.atLength += 16;
            }
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processByte(byte b, byte[] bArr, int r6) throws DataLengthException {
        checkStatus();
        byte[] bArr2 = this.bufBlock;
        int r1 = this.bufOff;
        bArr2[r1] = b;
        int r12 = r1 + 1;
        this.bufOff = r12;
        if (r12 == bArr2.length) {
            processBlock(bArr2, 0, bArr, r6);
            if (this.forEncryption) {
                this.bufOff = 0;
            } else {
                byte[] bArr3 = this.bufBlock;
                System.arraycopy(bArr3, 16, bArr3, 0, this.macSize);
                this.bufOff = this.macSize;
            }
            return 16;
        }
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int r9, int r10, byte[] bArr2, int r12) throws DataLengthException {
        int r0;
        checkStatus();
        if (bArr.length - r9 >= r10) {
            if (this.forEncryption) {
                if (this.bufOff != 0) {
                    while (r10 > 0) {
                        r10--;
                        byte[] bArr3 = this.bufBlock;
                        int r3 = this.bufOff;
                        int r4 = r9 + 1;
                        bArr3[r3] = bArr[r9];
                        int r32 = r3 + 1;
                        this.bufOff = r32;
                        if (r32 == 16) {
                            processBlock(bArr3, 0, bArr2, r12);
                            this.bufOff = 0;
                            r9 = r4;
                            r0 = 16;
                            break;
                        }
                        r9 = r4;
                    }
                }
                r0 = 0;
                while (r10 >= 16) {
                    processBlock(bArr, r9, bArr2, r12 + r0);
                    r9 += 16;
                    r10 -= 16;
                    r0 += 16;
                }
                if (r10 > 0) {
                    System.arraycopy(bArr, r9, this.bufBlock, 0, r10);
                    this.bufOff = r10;
                }
            } else {
                r0 = 0;
                for (int r33 = 0; r33 < r10; r33++) {
                    byte[] bArr4 = this.bufBlock;
                    int r5 = this.bufOff;
                    bArr4[r5] = bArr[r9 + r33];
                    int r52 = r5 + 1;
                    this.bufOff = r52;
                    if (r52 == bArr4.length) {
                        processBlock(bArr4, 0, bArr2, r12 + r0);
                        byte[] bArr5 = this.bufBlock;
                        System.arraycopy(bArr5, 16, bArr5, 0, this.macSize);
                        this.bufOff = this.macSize;
                        r0 += 16;
                    }
                }
            }
            return r0;
        }
        throw new DataLengthException("Input buffer too short");
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        reset(true);
    }
}
