package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.SkippingStreamCipher;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes5.dex */
public class SICBlockCipher extends StreamBlockCipher implements SkippingStreamCipher {

    /* renamed from: IV */
    private byte[] f2076IV;
    private final int blockSize;
    private int byteCount;
    private final BlockCipher cipher;
    private byte[] counter;
    private byte[] counterOut;

    public SICBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.blockSize = blockSize;
        this.f2076IV = new byte[blockSize];
        this.counter = new byte[blockSize];
        this.counterOut = new byte[blockSize];
        this.byteCount = 0;
    }

    private void adjustCounter(long j) {
        int r15;
        int r1 = 5;
        if (j >= 0) {
            long j2 = (this.byteCount + j) / this.blockSize;
            long j3 = j2;
            if (j2 > 255) {
                while (r1 >= 1) {
                    long j4 = 1 << (r1 * 8);
                    while (j3 >= j4) {
                        incrementCounterAt(r1);
                        j3 -= j4;
                    }
                    r1--;
                }
            }
            incrementCounter((int) j3);
            r15 = (int) ((j + this.byteCount) - (this.blockSize * j2));
        } else {
            long j5 = ((-j) - this.byteCount) / this.blockSize;
            long j6 = j5;
            if (j5 > 255) {
                while (r1 >= 1) {
                    long j7 = 1 << (r1 * 8);
                    while (j6 > j7) {
                        decrementCounterAt(r1);
                        j6 -= j7;
                    }
                    r1--;
                }
            }
            for (long j8 = 0; j8 != j6; j8++) {
                decrementCounterAt(0);
            }
            int r14 = (int) (this.byteCount + j + (this.blockSize * j5));
            if (r14 >= 0) {
                this.byteCount = 0;
                return;
            } else {
                decrementCounterAt(0);
                r15 = this.blockSize + r14;
            }
        }
        this.byteCount = r15;
    }

    private void checkCounter() {
        if (this.f2076IV.length >= this.blockSize) {
            return;
        }
        int r0 = 0;
        while (true) {
            byte[] bArr = this.f2076IV;
            if (r0 == bArr.length) {
                return;
            }
            if (this.counter[r0] != bArr[r0]) {
                throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
            }
            r0++;
        }
    }

    private void decrementCounterAt(int r4) {
        byte[] bArr;
        byte b;
        int length = this.counter.length - r4;
        do {
            length--;
            if (length < 0) {
                return;
            }
            b = (byte) (bArr[length] - 1);
            this.counter[length] = b;
        } while (b == -1);
    }

    private void incrementCounter(int r6) {
        byte[] bArr = this.counter;
        byte b = bArr[bArr.length - 1];
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] + r6);
        if (b == 0 || bArr[bArr.length - 1] >= b) {
            return;
        }
        incrementCounterAt(1);
    }

    private void incrementCounterAt(int r3) {
        byte b;
        int length = this.counter.length - r3;
        do {
            length--;
            if (length < 0) {
                return;
            }
            byte[] bArr = this.counter;
            b = (byte) (bArr[length] + 1);
            bArr[length] = b;
        } while (b == 0);
    }

    private void incrementCounterChecked() {
        byte b;
        int length = this.counter.length;
        do {
            length--;
            if (length < 0) {
                break;
            }
            byte[] bArr = this.counter;
            b = (byte) (bArr[length] + 1);
            bArr[length] = b;
        } while (b == 0);
        byte[] bArr2 = this.f2076IV;
        if (length < bArr2.length && bArr2.length < this.blockSize) {
            throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
        }
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    protected byte calculateByte(byte b) throws DataLengthException, IllegalStateException {
        int r0 = this.byteCount;
        if (r0 == 0) {
            this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
            byte[] bArr = this.counterOut;
            int r1 = this.byteCount;
            this.byteCount = r1 + 1;
            return (byte) (b ^ bArr[r1]);
        }
        byte[] bArr2 = this.counterOut;
        int r3 = r0 + 1;
        this.byteCount = r3;
        byte b2 = (byte) (b ^ bArr2[r0]);
        if (r3 == this.counter.length) {
            this.byteCount = 0;
            incrementCounterChecked();
        }
        return b2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/SIC";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long getPosition() {
        byte[] bArr = this.counter;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        int r0 = length - 1;
        while (r0 >= 1) {
            byte[] bArr3 = this.f2076IV;
            int r5 = r0 < bArr3.length ? (bArr2[r0] & 255) - (bArr3[r0] & 255) : bArr2[r0] & 255;
            if (r5 < 0) {
                int r4 = r0 - 1;
                bArr2[r4] = (byte) (bArr2[r4] - 1);
                r5 += 256;
            }
            bArr2[r0] = (byte) r5;
            r0--;
        }
        return (Pack.bigEndianToLong(bArr2, length - 8) * this.blockSize) + this.byteCount;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("CTR/SIC mode requires ParametersWithIV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] clone = Arrays.clone(parametersWithIV.getIV());
        this.f2076IV = clone;
        int r0 = this.blockSize;
        if (r0 < clone.length) {
            throw new IllegalArgumentException("CTR/SIC mode requires IV no greater than: " + this.blockSize + " bytes.");
        }
        int r3 = 8 > r0 / 2 ? r0 / 2 : 8;
        if (r0 - clone.length <= r3) {
            if (parametersWithIV.getParameters() != null) {
                this.cipher.init(true, parametersWithIV.getParameters());
            }
            reset();
            return;
        }
        throw new IllegalArgumentException("CTR/SIC mode requires IV of at least: " + (this.blockSize - r3) + " bytes.");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r9, byte[] bArr2, int r11) throws DataLengthException, IllegalStateException {
        if (this.byteCount != 0) {
            processBytes(bArr, r9, this.blockSize, bArr2, r11);
        } else {
            int r0 = this.blockSize;
            if (r9 + r0 > bArr.length) {
                throw new DataLengthException("input buffer too small");
            }
            if (r0 + r11 > bArr2.length) {
                throw new OutputLengthException("output buffer too short");
            }
            this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
            for (int r3 = 0; r3 < this.blockSize; r3++) {
                bArr2[r11 + r3] = (byte) (bArr[r9 + r3] ^ this.counterOut[r3]);
            }
            incrementCounterChecked();
        }
        return this.blockSize;
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher, org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int r8, int r9, byte[] bArr2, int r11) throws DataLengthException {
        byte b;
        if (r8 + r9 <= bArr.length) {
            if (r11 + r9 <= bArr2.length) {
                for (int r1 = 0; r1 < r9; r1++) {
                    int r2 = this.byteCount;
                    if (r2 == 0) {
                        this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
                        byte b2 = bArr[r8 + r1];
                        byte[] bArr3 = this.counterOut;
                        int r4 = this.byteCount;
                        this.byteCount = r4 + 1;
                        b = (byte) (b2 ^ bArr3[r4]);
                    } else {
                        byte b3 = bArr[r8 + r1];
                        byte[] bArr4 = this.counterOut;
                        int r5 = r2 + 1;
                        this.byteCount = r5;
                        b = (byte) (bArr4[r2] ^ b3);
                        if (r5 == this.counter.length) {
                            this.byteCount = 0;
                            incrementCounterChecked();
                        }
                    }
                    bArr2[r11 + r1] = b;
                }
                return r9;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too small");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.counter, (byte) 0);
        byte[] bArr = this.f2076IV;
        System.arraycopy(bArr, 0, this.counter, 0, bArr.length);
        this.cipher.reset();
        this.byteCount = 0;
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long seekTo(long j) {
        reset();
        return skip(j);
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long skip(long j) {
        adjustCounter(j);
        checkCounter();
        this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
        return j;
    }
}
