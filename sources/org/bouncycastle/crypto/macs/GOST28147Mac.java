package org.bouncycastle.crypto.macs;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithSBox;

/* loaded from: classes5.dex */
public class GOST28147Mac implements Mac {
    private int blockSize = 8;
    private int macSize = 4;
    private boolean firstStep = true;
    private int[] workingKey = null;
    private byte[] macIV = null;

    /* renamed from: S */
    private byte[] f2006S = {9, 6, 3, 2, 8, Ascii.f1132VT, 1, 7, 10, 4, Ascii.f1129SO, Ascii.f1128SI, Ascii.f1121FF, 0, 13, 5, 3, 7, Ascii.f1129SO, 9, 8, 10, Ascii.f1128SI, 0, 5, 2, 6, Ascii.f1121FF, Ascii.f1132VT, 4, 13, 1, Ascii.f1129SO, 4, 6, 2, Ascii.f1132VT, 3, 13, 8, Ascii.f1121FF, Ascii.f1128SI, 5, 10, 0, 7, 1, 9, Ascii.f1129SO, 7, 10, Ascii.f1121FF, 13, 1, 3, 9, 0, 2, Ascii.f1132VT, 4, Ascii.f1128SI, 8, 5, 6, Ascii.f1132VT, 5, 1, 9, 8, 13, Ascii.f1128SI, 0, Ascii.f1129SO, 4, 2, 3, Ascii.f1121FF, 7, 10, 6, 3, 10, 13, Ascii.f1121FF, 1, 2, 0, Ascii.f1132VT, 7, 5, 9, 4, 8, Ascii.f1128SI, Ascii.f1129SO, 6, 1, 13, 2, 9, 7, 10, 6, 0, 8, Ascii.f1121FF, 4, 5, Ascii.f1128SI, 3, Ascii.f1132VT, Ascii.f1129SO, Ascii.f1132VT, 10, Ascii.f1128SI, 5, 0, Ascii.f1121FF, Ascii.f1129SO, 8, 6, 2, 3, 9, 1, 7, 13, 4};
    private byte[] mac = new byte[8];
    private byte[] buf = new byte[8];
    private int bufOff = 0;

    private byte[] CM5func(byte[] bArr, int r5, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length - r5];
        System.arraycopy(bArr, r5, bArr3, 0, bArr2.length);
        for (int r2 = 0; r2 != bArr2.length; r2++) {
            bArr3[r2] = (byte) (bArr3[r2] ^ bArr2[r2]);
        }
        return bArr3;
    }

    private int bytesToint(byte[] bArr, int r5) {
        return ((bArr[r5 + 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) + ((bArr[r5 + 2] << 16) & 16711680) + ((bArr[r5 + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[r5] & 255);
    }

    private int[] generateWorkingKey(byte[] bArr) {
        if (bArr.length == 32) {
            int[] r1 = new int[8];
            for (int r2 = 0; r2 != 8; r2++) {
                r1[r2] = bytesToint(bArr, r2 * 4);
            }
            return r1;
        }
        throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
    }

    private void gost28147MacFunc(int[] r6, byte[] bArr, int r8, byte[] bArr2, int r10) {
        int bytesToint = bytesToint(bArr, r8);
        int bytesToint2 = bytesToint(bArr, r8 + 4);
        for (int r1 = 0; r1 < 2; r1++) {
            int r2 = 0;
            while (r2 < 8) {
                r2++;
                int r4 = bytesToint;
                bytesToint = bytesToint2 ^ gost28147_mainStep(bytesToint, r6[r2]);
                bytesToint2 = r4;
            }
        }
        intTobytes(bytesToint, bArr2, r10);
        intTobytes(bytesToint2, bArr2, r10 + 4);
    }

    private int gost28147_mainStep(int r3, int r4) {
        int r42 = r4 + r3;
        byte[] bArr = this.f2006S;
        int r0 = (bArr[((r42 >> 0) & 15) + 0] << 0) + (bArr[((r42 >> 4) & 15) + 16] << 4) + (bArr[((r42 >> 8) & 15) + 32] << 8) + (bArr[((r42 >> 12) & 15) + 48] << Ascii.f1121FF) + (bArr[((r42 >> 16) & 15) + 64] << 16) + (bArr[((r42 >> 20) & 15) + 80] << Ascii.DC4) + (bArr[((r42 >> 24) & 15) + 96] << Ascii.CAN) + (bArr[((r42 >> 28) & 15) + 112] << Ascii.f1122FS);
        return (r0 << 11) | (r0 >>> 21);
    }

    private void intTobytes(int r3, byte[] bArr, int r5) {
        bArr[r5 + 3] = (byte) (r3 >>> 24);
        bArr[r5 + 2] = (byte) (r3 >>> 16);
        bArr[r5 + 1] = (byte) (r3 >>> 8);
        bArr[r5] = (byte) r3;
    }

    private void recursiveInit(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters == null) {
            return;
        }
        CipherParameters cipherParameters2 = null;
        if (cipherParameters instanceof ParametersWithSBox) {
            ParametersWithSBox parametersWithSBox = (ParametersWithSBox) cipherParameters;
            System.arraycopy(parametersWithSBox.getSBox(), 0, this.f2006S, 0, parametersWithSBox.getSBox().length);
            cipherParameters2 = parametersWithSBox.getParameters();
        } else if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey());
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameter passed to GOST28147 init - " + cipherParameters.getClass().getName());
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] bArr = parametersWithIV.getIV();
            byte[] bArr2 = this.mac;
            System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
            this.macIV = parametersWithIV.getIV();
            cipherParameters2 = parametersWithIV.getParameters();
        }
        recursiveInit(cipherParameters2);
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int r10) throws DataLengthException, IllegalStateException {
        while (true) {
            int r0 = this.bufOff;
            if (r0 >= this.blockSize) {
                break;
            }
            this.buf[r0] = 0;
            this.bufOff = r0 + 1;
        }
        byte[] bArr2 = this.buf;
        byte[] bArr3 = new byte[bArr2.length];
        System.arraycopy(bArr2, 0, bArr3, 0, this.mac.length);
        if (this.firstStep) {
            this.firstStep = false;
        } else {
            bArr3 = CM5func(this.buf, 0, this.mac);
        }
        gost28147MacFunc(this.workingKey, bArr3, 0, this.mac, 0);
        byte[] bArr4 = this.mac;
        int r2 = this.macSize;
        System.arraycopy(bArr4, (bArr4.length / 2) - r2, bArr, r10, r2);
        reset();
        return this.macSize;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "GOST28147Mac";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.macSize;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        reset();
        this.buf = new byte[this.blockSize];
        this.macIV = null;
        recursiveInit(cipherParameters);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        int r1 = 0;
        while (true) {
            byte[] bArr = this.buf;
            if (r1 >= bArr.length) {
                this.bufOff = 0;
                this.firstStep = true;
                return;
            }
            bArr[r1] = 0;
            r1++;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        int r0 = this.bufOff;
        byte[] bArr = this.buf;
        if (r0 == bArr.length) {
            byte[] bArr2 = new byte[bArr.length];
            System.arraycopy(bArr, 0, bArr2, 0, this.mac.length);
            if (this.firstStep) {
                this.firstStep = false;
                byte[] bArr3 = this.macIV;
                if (bArr3 != null) {
                    bArr2 = CM5func(this.buf, 0, bArr3);
                }
            } else {
                bArr2 = CM5func(this.buf, 0, this.mac);
            }
            gost28147MacFunc(this.workingKey, bArr2, 0, this.mac, 0);
            this.bufOff = 0;
        }
        byte[] bArr4 = this.buf;
        int r1 = this.bufOff;
        this.bufOff = r1 + 1;
        bArr4[r1] = b;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int r13, int r14) throws DataLengthException, IllegalStateException {
        if (r14 < 0) {
            throw new IllegalArgumentException("Can't have a negative input length!");
        }
        int r0 = this.blockSize;
        int r1 = this.bufOff;
        int r02 = r0 - r1;
        if (r14 > r02) {
            System.arraycopy(bArr, r13, this.buf, r1, r02);
            byte[] bArr2 = this.buf;
            byte[] bArr3 = new byte[bArr2.length];
            System.arraycopy(bArr2, 0, bArr3, 0, this.mac.length);
            if (this.firstStep) {
                this.firstStep = false;
                byte[] bArr4 = this.macIV;
                if (bArr4 != null) {
                    bArr3 = CM5func(this.buf, 0, bArr4);
                }
            } else {
                bArr3 = CM5func(this.buf, 0, this.mac);
            }
            gost28147MacFunc(this.workingKey, bArr3, 0, this.mac, 0);
            this.bufOff = 0;
            while (true) {
                r14 -= r02;
                r13 += r02;
                if (r14 <= this.blockSize) {
                    break;
                }
                gost28147MacFunc(this.workingKey, CM5func(bArr, r13, this.mac), 0, this.mac, 0);
                r02 = this.blockSize;
            }
        }
        System.arraycopy(bArr, r13, this.buf, this.bufOff, r14);
        this.bufOff += r14;
    }
}
