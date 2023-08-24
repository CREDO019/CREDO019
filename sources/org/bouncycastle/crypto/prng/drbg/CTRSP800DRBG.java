package org.bouncycastle.crypto.prng.drbg;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.prng.EntropySource;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes5.dex */
public class CTRSP800DRBG implements SP80090DRBG {
    private static final int AES_MAX_BITS_REQUEST = 262144;
    private static final long AES_RESEED_MAX = 140737488355328L;
    private static final byte[] K_BITS = Hex.decodeStrict("000102030405060708090A0B0C0D0E0F101112131415161718191A1B1C1D1E1F");
    private static final int TDEA_MAX_BITS_REQUEST = 4096;
    private static final long TDEA_RESEED_MAX = 2147483648L;
    private byte[] _Key;

    /* renamed from: _V */
    private byte[] f2164_V;
    private BlockCipher _engine;
    private EntropySource _entropySource;
    private boolean _isTDEA;
    private int _keySizeInBits;
    private long _reseedCounter = 0;
    private int _securityStrength;
    private int _seedLength;

    public CTRSP800DRBG(BlockCipher blockCipher, int r4, int r5, EntropySource entropySource, byte[] bArr, byte[] bArr2) {
        this._isTDEA = false;
        this._entropySource = entropySource;
        this._engine = blockCipher;
        this._keySizeInBits = r4;
        this._securityStrength = r5;
        this._seedLength = (blockCipher.getBlockSize() * 8) + r4;
        this._isTDEA = isTDEA(blockCipher);
        if (r5 > 256) {
            throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
        }
        if (getMaxSecurityStrength(blockCipher, r4) < r5) {
            throw new IllegalArgumentException("Requested security strength is not supported by block cipher and key size");
        }
        if (entropySource.entropySize() < r5) {
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        }
        CTR_DRBG_Instantiate_algorithm(getEntropy(), bArr2, bArr);
    }

    private void BCC(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int blockSize = this._engine.getBlockSize();
        byte[] bArr5 = new byte[blockSize];
        int length = bArr4.length / blockSize;
        byte[] bArr6 = new byte[blockSize];
        this._engine.init(true, new KeyParameter(expandKey(bArr2)));
        this._engine.processBlock(bArr3, 0, bArr5, 0);
        for (int r8 = 0; r8 < length; r8++) {
            XOR(bArr6, bArr5, bArr4, r8 * blockSize);
            this._engine.processBlock(bArr6, 0, bArr5, 0);
        }
        System.arraycopy(bArr5, 0, bArr, 0, bArr.length);
    }

    private byte[] Block_Cipher_df(byte[] bArr, int r19) {
        int blockSize = this._engine.getBlockSize();
        int length = bArr.length;
        int r5 = r19 / 8;
        int r6 = length + 8;
        byte[] bArr2 = new byte[((((r6 + 1) + blockSize) - 1) / blockSize) * blockSize];
        copyIntToByteArray(bArr2, length, 0);
        copyIntToByteArray(bArr2, r5, 4);
        System.arraycopy(bArr, 0, bArr2, 8, length);
        bArr2[r6] = Byte.MIN_VALUE;
        int r1 = this._keySizeInBits;
        int r3 = (r1 / 8) + blockSize;
        byte[] bArr3 = new byte[r3];
        byte[] bArr4 = new byte[blockSize];
        byte[] bArr5 = new byte[blockSize];
        int r12 = r1 / 8;
        byte[] bArr6 = new byte[r12];
        System.arraycopy(K_BITS, 0, bArr6, 0, r12);
        int r122 = 0;
        while (true) {
            int r13 = r122 * blockSize;
            if (r13 * 8 >= this._keySizeInBits + (blockSize * 8)) {
                break;
            }
            copyIntToByteArray(bArr5, r122, 0);
            BCC(bArr4, bArr6, bArr5, bArr2);
            int r14 = r3 - r13;
            if (r14 > blockSize) {
                r14 = blockSize;
            }
            System.arraycopy(bArr4, 0, bArr3, r13, r14);
            r122++;
        }
        byte[] bArr7 = new byte[blockSize];
        System.arraycopy(bArr3, 0, bArr6, 0, r12);
        System.arraycopy(bArr3, r12, bArr7, 0, blockSize);
        byte[] bArr8 = new byte[r5];
        this._engine.init(true, new KeyParameter(expandKey(bArr6)));
        int r4 = 0;
        while (true) {
            int r62 = r4 * blockSize;
            if (r62 >= r5) {
                return bArr8;
            }
            this._engine.processBlock(bArr7, 0, bArr7, 0);
            int r7 = r5 - r62;
            if (r7 > blockSize) {
                r7 = blockSize;
            }
            System.arraycopy(bArr7, 0, bArr8, r62, r7);
            r4++;
        }
    }

    private void CTR_DRBG_Instantiate_algorithm(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] Block_Cipher_df = Block_Cipher_df(Arrays.concatenate(bArr, bArr2, bArr3), this._seedLength);
        int blockSize = this._engine.getBlockSize();
        byte[] bArr4 = new byte[(this._keySizeInBits + 7) / 8];
        this._Key = bArr4;
        byte[] bArr5 = new byte[blockSize];
        this.f2164_V = bArr5;
        CTR_DRBG_Update(Block_Cipher_df, bArr4, bArr5);
        this._reseedCounter = 1L;
    }

    private void CTR_DRBG_Reseed_algorithm(byte[] bArr) {
        CTR_DRBG_Update(Block_Cipher_df(Arrays.concatenate(getEntropy(), bArr), this._seedLength), this._Key, this.f2164_V);
        this._reseedCounter = 1L;
    }

    private void CTR_DRBG_Update(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int length = bArr.length;
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[this._engine.getBlockSize()];
        int blockSize = this._engine.getBlockSize();
        this._engine.init(true, new KeyParameter(expandKey(bArr2)));
        int r5 = 0;
        while (true) {
            int r6 = r5 * blockSize;
            if (r6 >= bArr.length) {
                XOR(bArr4, bArr, bArr4, 0);
                System.arraycopy(bArr4, 0, bArr2, 0, bArr2.length);
                System.arraycopy(bArr4, bArr2.length, bArr3, 0, bArr3.length);
                return;
            }
            addOneTo(bArr3);
            this._engine.processBlock(bArr3, 0, bArr5, 0);
            int r7 = length - r6;
            if (r7 > blockSize) {
                r7 = blockSize;
            }
            System.arraycopy(bArr5, 0, bArr4, r6, r7);
            r5++;
        }
    }

    private void XOR(byte[] bArr, byte[] bArr2, byte[] bArr3, int r7) {
        for (int r0 = 0; r0 < bArr.length; r0++) {
            bArr[r0] = (byte) (bArr2[r0] ^ bArr3[r0 + r7]);
        }
    }

    private void addOneTo(byte[] bArr) {
        int r2 = 1;
        for (int r1 = 1; r1 <= bArr.length; r1++) {
            int r3 = (bArr[bArr.length - r1] & 255) + r2;
            r2 = r3 > 255 ? 1 : 0;
            bArr[bArr.length - r1] = (byte) r3;
        }
    }

    private void copyIntToByteArray(byte[] bArr, int r4, int r5) {
        bArr[r5 + 0] = (byte) (r4 >> 24);
        bArr[r5 + 1] = (byte) (r4 >> 16);
        bArr[r5 + 2] = (byte) (r4 >> 8);
        bArr[r5 + 3] = (byte) r4;
    }

    private byte[] getEntropy() {
        byte[] entropy = this._entropySource.getEntropy();
        if (entropy.length >= (this._securityStrength + 7) / 8) {
            return entropy;
        }
        throw new IllegalStateException("Insufficient entropy provided by entropy source");
    }

    private int getMaxSecurityStrength(BlockCipher blockCipher, int r3) {
        if (isTDEA(blockCipher) && r3 == 168) {
            return 112;
        }
        if (blockCipher.getAlgorithmName().equals("AES")) {
            return r3;
        }
        return -1;
    }

    private boolean isTDEA(BlockCipher blockCipher) {
        return blockCipher.getAlgorithmName().equals("DESede") || blockCipher.getAlgorithmName().equals("TDEA");
    }

    private void padKey(byte[] bArr, int r6, byte[] bArr2, int r8) {
        int r1 = r6 + 0;
        bArr2[r8 + 0] = (byte) (bArr[r1] & 254);
        int r2 = r6 + 1;
        bArr2[r8 + 1] = (byte) ((bArr[r1] << 7) | ((bArr[r2] & 252) >>> 1));
        int r22 = r6 + 2;
        bArr2[r8 + 2] = (byte) ((bArr[r2] << 6) | ((bArr[r22] & 248) >>> 2));
        int r23 = r6 + 3;
        bArr2[r8 + 3] = (byte) ((bArr[r22] << 5) | ((bArr[r23] & 240) >>> 3));
        int r24 = r6 + 4;
        bArr2[r8 + 4] = (byte) ((bArr[r23] << 4) | ((bArr[r24] & 224) >>> 4));
        int r25 = r6 + 5;
        bArr2[r8 + 5] = (byte) ((bArr[r24] << 3) | ((bArr[r25] & 192) >>> 5));
        int r62 = r6 + 6;
        bArr2[r8 + 6] = (byte) ((bArr[r25] << 2) | ((bArr[r62] & 128) >>> 6));
        int r0 = r8 + 7;
        bArr2[r0] = (byte) (bArr[r62] << 1);
        while (r8 <= r0) {
            byte b = bArr2[r8];
            bArr2[r8] = (byte) (((((b >> 7) ^ ((((((b >> 1) ^ (b >> 2)) ^ (b >> 3)) ^ (b >> 4)) ^ (b >> 5)) ^ (b >> 6))) ^ 1) & 1) | (b & 254));
            r8++;
        }
    }

    byte[] expandKey(byte[] bArr) {
        if (this._isTDEA) {
            byte[] bArr2 = new byte[24];
            padKey(bArr, 0, bArr2, 0);
            padKey(bArr, 7, bArr2, 8);
            padKey(bArr, 14, bArr2, 16);
            return bArr2;
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int generate(byte[] bArr, byte[] bArr2, boolean z) {
        byte[] bArr3;
        boolean z2 = this._isTDEA;
        long j = this._reseedCounter;
        if (z2) {
            if (j > TDEA_RESEED_MAX) {
                return -1;
            }
            if (C5246Utils.isTooLarge(bArr, 512)) {
                throw new IllegalArgumentException("Number of bits per request limited to 4096");
            }
        } else if (j > AES_RESEED_MAX) {
            return -1;
        } else {
            if (C5246Utils.isTooLarge(bArr, 32768)) {
                throw new IllegalArgumentException("Number of bits per request limited to 262144");
            }
        }
        if (z) {
            CTR_DRBG_Reseed_algorithm(bArr2);
            bArr2 = null;
        }
        if (bArr2 != null) {
            bArr3 = Block_Cipher_df(bArr2, this._seedLength);
            CTR_DRBG_Update(bArr3, this._Key, this.f2164_V);
        } else {
            bArr3 = new byte[this._seedLength / 8];
        }
        int length = this.f2164_V.length;
        byte[] bArr4 = new byte[length];
        this._engine.init(true, new KeyParameter(expandKey(this._Key)));
        for (int r2 = 0; r2 <= bArr.length / length; r2++) {
            int r4 = r2 * length;
            int length2 = bArr.length - r4 > length ? length : bArr.length - (this.f2164_V.length * r2);
            if (length2 != 0) {
                addOneTo(this.f2164_V);
                this._engine.processBlock(this.f2164_V, 0, bArr4, 0);
                System.arraycopy(bArr4, 0, bArr, r4, length2);
            }
        }
        CTR_DRBG_Update(bArr3, this._Key, this.f2164_V);
        this._reseedCounter++;
        return bArr.length * 8;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public int getBlockSize() {
        return this.f2164_V.length * 8;
    }

    @Override // org.bouncycastle.crypto.prng.drbg.SP80090DRBG
    public void reseed(byte[] bArr) {
        CTR_DRBG_Reseed_algorithm(bArr);
    }
}
