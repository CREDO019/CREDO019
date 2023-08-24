package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* loaded from: classes5.dex */
public class Grainv1Engine implements StreamCipher {
    private static final int STATE_SIZE = 5;
    private int index = 2;
    private boolean initialised = false;
    private int[] lfsr;
    private int[] nfsr;
    private byte[] out;
    private int output;
    private byte[] workingIV;
    private byte[] workingKey;

    private byte getKeyStream() {
        if (this.index > 1) {
            oneRound();
            this.index = 0;
        }
        byte[] bArr = this.out;
        int r1 = this.index;
        this.index = r1 + 1;
        return bArr[r1];
    }

    private int getOutput() {
        int[] r0 = this.nfsr;
        int r2 = (r0[0] >>> 1) | (r0[1] << 15);
        int r4 = (r0[0] >>> 2) | (r0[1] << 14);
        int r6 = (r0[0] >>> 4) | (r0[1] << 12);
        int r8 = (r0[0] >>> 10) | (r0[1] << 6);
        int r9 = (r0[1] >>> 15) | (r0[2] << 1);
        int r10 = (r0[2] >>> 11) | (r0[3] << 5);
        int r12 = (r0[3] >>> 8) | (r0[4] << 8);
        int r02 = (r0[4] << 1) | (r0[3] >>> 15);
        int[] r13 = this.lfsr;
        int r1 = (r13[0] >>> 3) | (r13[1] << 13);
        int r3 = (r13[1] >>> 9) | (r13[2] << 7);
        int r5 = (r13[3] << 2) | (r13[2] >>> 14);
        int r7 = r13[4];
        int r11 = (r3 ^ r02) ^ (r1 & r7);
        int r132 = r5 & r7;
        int r14 = r1 & r5;
        int r72 = r7 & r14;
        int r15 = r14 & r02;
        return (((((((((r02 & r132) ^ ((r15 ^ (r72 ^ (((r11 ^ r132) ^ (r7 & r02)) ^ ((r1 & r3) & r5)))) ^ ((r3 & r5) & r02))) ^ r2) ^ r4) ^ r6) ^ r8) ^ r9) ^ r10) ^ r12) & 65535;
    }

    private int getOutputLFSR() {
        int[] r0 = this.lfsr;
        int r2 = r0[0];
        int r3 = (r0[1] >>> 7) | (r0[2] << 9);
        int r6 = (r0[2] >>> 6) | (r0[3] << 10);
        return (((r0[4] << 2) | (r0[3] >>> 14)) ^ ((((((r0[0] >>> 13) | (r0[1] << 3)) ^ r2) ^ r3) ^ r6) ^ ((r0[3] >>> 3) | (r0[4] << 13)))) & 65535;
    }

    private int getOutputNFSR() {
        int[] r1 = this.nfsr;
        int r3 = r1[0];
        int r4 = (r1[0] >>> 9) | (r1[1] << 7);
        int r6 = (r1[0] >>> 14) | (r1[1] << 2);
        int r2 = (r1[0] >>> 15) | (r1[1] << 1);
        int r7 = (r1[1] >>> 5) | (r1[2] << 11);
        int r9 = (r1[1] >>> 12) | (r1[2] << 4);
        int r10 = (r1[2] >>> 1) | (r1[3] << 15);
        int r13 = (r1[2] >>> 5) | (r1[3] << 11);
        int r14 = (r1[2] >>> 13) | (r1[3] << 3);
        int r15 = (r1[3] >>> 4) | (r1[4] << 12);
        int r16 = (r1[3] >>> 12) | (r1[4] << 4);
        int r12 = (r1[4] << 1) | (r1[3] >>> 15);
        int r5 = r12 & r16;
        int r62 = r16 & r15;
        int r32 = ((((r3 ^ (((((((((((r1[3] >>> 14) | (r1[4] << 2)) ^ r16) ^ r15) ^ r14) ^ r13) ^ r10) ^ r9) ^ r7) ^ r6) ^ r4)) ^ r5) ^ (r13 & r10)) ^ (r2 & r4)) ^ (r62 & r14);
        int r8 = r10 & r9 & r7;
        return (((((((((r12 & r14) & r9) & r4) ^ (r32 ^ r8)) ^ ((r62 & r13) & r10)) ^ ((r5 & r7) & r2)) ^ (((r5 & r15) & r14) & r13)) ^ ((r2 & r8) & r4)) ^ (((((r15 & r14) & r13) & r10) & r9) & r7)) & 65535;
    }

    private void initGrain() {
        for (int r1 = 0; r1 < 10; r1++) {
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
        this.nfsr = shift(this.nfsr, getOutputNFSR() ^ this.lfsr[0]);
        this.lfsr = shift(this.lfsr, getOutputLFSR());
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        bArr2[8] = -1;
        bArr2[9] = -1;
        this.workingKey = bArr;
        this.workingIV = bArr2;
        int r6 = 0;
        int r7 = 0;
        while (true) {
            int[] r1 = this.nfsr;
            if (r6 >= r1.length) {
                return;
            }
            byte[] bArr3 = this.workingKey;
            int r3 = r7 + 1;
            r1[r6] = ((bArr3[r7] & 255) | (bArr3[r3] << 8)) & 65535;
            int[] r12 = this.lfsr;
            byte[] bArr4 = this.workingIV;
            r12[r6] = ((bArr4[r7] & 255) | (bArr4[r3] << 8)) & 65535;
            r7 += 2;
            r6++;
        }
    }

    private int[] shift(int[] r4, int r5) {
        r4[0] = r4[1];
        r4[1] = r4[2];
        r4[2] = r4[3];
        r4[3] = r4[4];
        r4[4] = r5;
        return r4;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "Grain v1";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Grain v1 Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] bArr = parametersWithIV.getIV();
        if (bArr == null || bArr.length != 8) {
            throw new IllegalArgumentException("Grain v1 requires exactly 8 bytes of IV");
        }
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("Grain v1 Init parameters must include a key");
        }
        KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
        this.workingIV = new byte[keyParameter.getKey().length];
        this.workingKey = new byte[keyParameter.getKey().length];
        this.lfsr = new int[5];
        this.nfsr = new int[5];
        this.out = new byte[2];
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
        this.index = 2;
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
