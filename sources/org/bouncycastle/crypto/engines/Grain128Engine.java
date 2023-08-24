package org.bouncycastle.crypto.engines;

import androidx.core.view.MotionEventCompat;
import com.google.common.base.Ascii;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* loaded from: classes5.dex */
public class Grain128Engine implements StreamCipher {
    private static final int STATE_SIZE = 4;
    private int index = 4;
    private boolean initialised = false;
    private int[] lfsr;
    private int[] nfsr;
    private byte[] out;
    private int output;
    private byte[] workingIV;
    private byte[] workingKey;

    private byte getKeyStream() {
        if (this.index > 3) {
            oneRound();
            this.index = 0;
        }
        byte[] bArr = this.out;
        int r1 = this.index;
        this.index = r1 + 1;
        return bArr[r1];
    }

    private int getOutput() {
        int[] r1 = this.nfsr;
        int r3 = (r1[0] >>> 2) | (r1[1] << 30);
        int r6 = (r1[0] >>> 12) | (r1[1] << 20);
        int r7 = (r1[0] >>> 15) | (r1[1] << 17);
        int r8 = (r1[1] >>> 4) | (r1[2] << 28);
        int r9 = (r1[1] >>> 13) | (r1[2] << 19);
        int r10 = r1[2];
        int r11 = (r1[2] >>> 9) | (r1[3] << 23);
        int r13 = (r1[2] >>> 25) | (r1[3] << 7);
        int r12 = (r1[3] << 1) | (r1[2] >>> 31);
        int[] r14 = this.lfsr;
        int r15 = (r14[0] >>> 8) | (r14[1] << 24);
        int r16 = (r14[0] >>> 13) | (r14[1] << 19);
        int r2 = (r14[0] >>> 20) | (r14[1] << 12);
        int r17 = (r14[1] >>> 10) | (r14[2] << 22);
        int r18 = (r14[1] >>> 28) | (r14[2] << 4);
        int r19 = (r14[2] >>> 15) | (r14[3] << 17);
        int r110 = r12 & r6 & ((r14[2] >>> 31) | (r14[3] << 1));
        return ((((((((r110 ^ ((((r16 & r2) ^ (r6 & r15)) ^ (r12 & r17)) ^ (r18 & r19))) ^ ((r14[2] >>> 29) | (r14[3] << 3))) ^ r3) ^ r7) ^ r8) ^ r9) ^ r10) ^ r11) ^ r13;
    }

    private int getOutputLFSR() {
        int[] r0 = this.lfsr;
        int r2 = r0[0];
        int r1 = (r0[0] >>> 7) | (r0[1] << 25);
        int r3 = (r0[1] >>> 6) | (r0[2] << 26);
        return r0[3] ^ ((((r1 ^ r2) ^ r3) ^ ((r0[2] >>> 6) | (r0[3] << 26))) ^ ((r0[2] >>> 17) | (r0[3] << 15)));
    }

    private int getOutputNFSR() {
        int[] r1 = this.nfsr;
        int r3 = r1[0];
        int r4 = (r1[0] >>> 3) | (r1[1] << 29);
        int r7 = (r1[0] >>> 11) | (r1[1] << 21);
        int r8 = (r1[0] >>> 13) | (r1[1] << 19);
        int r9 = (r1[0] >>> 17) | (r1[1] << 15);
        int r10 = (r1[0] >>> 18) | (r1[1] << 14);
        int r11 = (r1[0] >>> 26) | (r1[1] << 6);
        int r2 = (r1[0] >>> 27) | (r1[1] << 5);
        int r12 = (r1[1] >>> 8) | (r1[2] << 24);
        int r14 = (r1[1] >>> 16) | (r1[2] << 16);
        int r15 = (r1[1] >>> 24) | (r1[2] << 8);
        int r16 = (r1[1] >>> 27) | (r1[2] << 5);
        int r17 = (r1[1] >>> 29) | (r1[2] << 3);
        int r6 = (r1[2] >>> 1) | (r1[3] << 31);
        return (((((((r1[3] ^ (((r3 ^ r11) ^ r15) ^ ((r1[2] >>> 27) | (r1[3] << 5)))) ^ (r4 & ((r1[2] >>> 3) | (r1[3] << 29)))) ^ (r7 & r8)) ^ (r9 & r10)) ^ (r2 & r16)) ^ (r12 & r14)) ^ (r17 & r6)) ^ (((r1[2] >>> 4) | (r1[3] << 28)) & ((r1[2] >>> 20) | (r1[3] << 12)));
    }

    private void initGrain() {
        for (int r1 = 0; r1 < 8; r1++) {
            this.output = getOutput();
            this.nfsr = shift(this.nfsr, (getOutputNFSR() ^ this.lfsr[0]) ^ this.output);
            this.lfsr = shift(this.lfsr, getOutputLFSR() ^ this.output);
        }
        this.initialised = true;
    }

    private void oneRound() {
        int output = getOutput();
        this.output = output;
        byte[] bArr = this.out;
        bArr[0] = (byte) output;
        bArr[1] = (byte) (output >> 8);
        bArr[2] = (byte) (output >> 16);
        bArr[3] = (byte) (output >> 24);
        this.nfsr = shift(this.nfsr, getOutputNFSR() ^ this.lfsr[0]);
        this.lfsr = shift(this.lfsr, getOutputLFSR());
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        bArr2[12] = -1;
        bArr2[13] = -1;
        bArr2[14] = -1;
        bArr2[15] = -1;
        this.workingKey = bArr;
        this.workingIV = bArr2;
        int r10 = 0;
        int r11 = 0;
        while (true) {
            int[] r0 = this.nfsr;
            if (r10 >= r0.length) {
                return;
            }
            byte[] bArr3 = this.workingKey;
            int r2 = r11 + 3;
            int r4 = r11 + 2;
            int r3 = (bArr3[r2] << Ascii.CAN) | ((bArr3[r4] << 16) & 16711680);
            int r5 = r11 + 1;
            r0[r10] = (bArr3[r11] & 255) | r3 | ((bArr3[r5] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
            int[] r02 = this.lfsr;
            byte[] bArr4 = this.workingIV;
            r02[r10] = (bArr4[r11] & 255) | (bArr4[r2] << Ascii.CAN) | ((bArr4[r4] << 16) & 16711680) | ((bArr4[r5] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
            r11 += 4;
            r10++;
        }
    }

    private int[] shift(int[] r4, int r5) {
        r4[0] = r4[1];
        r4[1] = r4[2];
        r4[2] = r4[3];
        r4[3] = r5;
        return r4;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "Grain-128";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Grain-128 Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] bArr = parametersWithIV.getIV();
        if (bArr == null || bArr.length != 12) {
            throw new IllegalArgumentException("Grain-128  requires exactly 12 bytes of IV");
        }
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("Grain-128 Init parameters must include a key");
        }
        KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
        this.workingIV = new byte[keyParameter.getKey().length];
        this.workingKey = new byte[keyParameter.getKey().length];
        this.lfsr = new int[4];
        this.nfsr = new int[4];
        this.out = new byte[4];
        System.arraycopy(bArr, 0, this.workingIV, 0, bArr.length);
        System.arraycopy(keyParameter.getKey(), 0, this.workingKey, 0, keyParameter.getKey().length);
        reset();
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int r6, int r7, byte[] bArr2, int r9) throws DataLengthException {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (r6 + r7 <= bArr.length) {
            if (r9 + r7 <= bArr2.length) {
                for (int r0 = 0; r0 < r7; r0++) {
                    bArr2[r9 + r0] = (byte) (bArr[r6 + r0] ^ getKeyStream());
                }
                return r7;
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        this.index = 4;
        setKey(this.workingKey, this.workingIV);
        initGrain();
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b) {
        if (this.initialised) {
            return (byte) (b ^ getKeyStream());
        }
        throw new IllegalStateException(getAlgorithmName() + " not initialised");
    }
}
