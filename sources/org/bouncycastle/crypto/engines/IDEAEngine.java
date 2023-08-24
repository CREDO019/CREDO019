package org.bouncycastle.crypto.engines;

import androidx.core.view.MotionEventCompat;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes5.dex */
public class IDEAEngine implements BlockCipher {
    private static final int BASE = 65537;
    protected static final int BLOCK_SIZE = 8;
    private static final int MASK = 65535;
    private int[] workingKey = null;

    private int bytesToWord(byte[] bArr, int r4) {
        return ((bArr[r4] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[r4 + 1] & 255);
    }

    private int[] expandKey(byte[] bArr) {
        int r2;
        int[] r1 = new int[52];
        int r3 = 0;
        if (bArr.length < 16) {
            byte[] bArr2 = new byte[16];
            System.arraycopy(bArr, 0, bArr2, 16 - bArr.length, bArr.length);
            bArr = bArr2;
        }
        while (true) {
            if (r3 >= 8) {
                break;
            }
            r1[r3] = bytesToWord(bArr, r3 * 2);
            r3++;
        }
        for (r2 = 8; r2 < 52; r2++) {
            int r7 = r2 & 7;
            if (r7 < 6) {
                r1[r2] = (((r1[r2 - 7] & 127) << 9) | (r1[r2 - 6] >> 7)) & 65535;
            } else if (r7 == 6) {
                r1[r2] = (((r1[r2 - 7] & 127) << 9) | (r1[r2 - 14] >> 7)) & 65535;
            } else {
                r1[r2] = (((r1[r2 - 15] & 127) << 9) | (r1[r2 - 14] >> 7)) & 65535;
            }
        }
        return r1;
    }

    private int[] generateWorkingKey(boolean z, byte[] bArr) {
        return z ? expandKey(bArr) : invertKey(expandKey(bArr));
    }

    private void ideaFunc(int[] r9, byte[] bArr, int r11, byte[] bArr2, int r13) {
        int bytesToWord = bytesToWord(bArr, r11);
        int bytesToWord2 = bytesToWord(bArr, r11 + 2);
        int bytesToWord3 = bytesToWord(bArr, r11 + 4);
        int bytesToWord4 = bytesToWord(bArr, r11 + 6);
        int r112 = 0;
        int r3 = bytesToWord3;
        int r2 = bytesToWord2;
        int r1 = bytesToWord;
        int r0 = 0;
        while (r112 < 8) {
            int r4 = r0 + 1;
            int mul = mul(r1, r9[r0]);
            int r12 = r4 + 1;
            int r22 = (r2 + r9[r4]) & 65535;
            int r5 = r12 + 1;
            int r14 = (r3 + r9[r12]) & 65535;
            int r32 = r5 + 1;
            int mul2 = mul(bytesToWord4, r9[r5]);
            int r7 = r32 + 1;
            int mul3 = mul(r14 ^ mul, r9[r32]);
            int mul4 = mul(((r22 ^ mul2) + mul3) & 65535, r9[r7]);
            int r33 = (mul3 + mul4) & 65535;
            bytesToWord4 = mul2 ^ r33;
            r3 = r33 ^ r22;
            r112++;
            r2 = r14 ^ mul4;
            r1 = mul ^ mul4;
            r0 = r7 + 1;
        }
        int r113 = r0 + 1;
        wordToBytes(mul(r1, r9[r0]), bArr2, r13);
        int r02 = r113 + 1;
        wordToBytes(r3 + r9[r113], bArr2, r13 + 2);
        wordToBytes(r2 + r9[r02], bArr2, r13 + 4);
        wordToBytes(mul(bytesToWord4, r9[r02 + 1]), bArr2, r13 + 6);
    }

    private int[] invertKey(int[] r9) {
        int[] r0 = new int[52];
        int mulInv = mulInv(r9[0]);
        int r2 = 1;
        int addInv = addInv(r9[1]);
        int addInv2 = addInv(r9[2]);
        r0[51] = mulInv(r9[3]);
        r0[50] = addInv2;
        r0[49] = addInv;
        int r3 = 48;
        r0[48] = mulInv;
        int r1 = 4;
        while (r2 < 8) {
            int r4 = r1 + 1;
            int r12 = r9[r1];
            int r5 = r4 + 1;
            int r32 = r3 - 1;
            r0[r32] = r9[r4];
            int r33 = r32 - 1;
            r0[r33] = r12;
            int r13 = r5 + 1;
            int mulInv2 = mulInv(r9[r5]);
            int r52 = r13 + 1;
            int addInv3 = addInv(r9[r13]);
            int r6 = r52 + 1;
            int addInv4 = addInv(r9[r52]);
            int r34 = r33 - 1;
            r0[r34] = mulInv(r9[r6]);
            int r35 = r34 - 1;
            r0[r35] = addInv3;
            int r36 = r35 - 1;
            r0[r36] = addInv4;
            r3 = r36 - 1;
            r0[r3] = mulInv2;
            r2++;
            r1 = r6 + 1;
        }
        int r22 = r1 + 1;
        int r14 = r9[r1];
        int r42 = r22 + 1;
        int r37 = r3 - 1;
        r0[r37] = r9[r22];
        int r38 = r37 - 1;
        r0[r38] = r14;
        int r15 = r42 + 1;
        int mulInv3 = mulInv(r9[r42]);
        int r43 = r15 + 1;
        int addInv5 = addInv(r9[r15]);
        int r53 = r43 + 1;
        int addInv6 = addInv(r9[r43]);
        int r39 = r38 - 1;
        r0[r39] = mulInv(r9[r53]);
        int r310 = r39 - 1;
        r0[r310] = addInv6;
        int r311 = r310 - 1;
        r0[r311] = addInv5;
        r0[r311 - 1] = mulInv3;
        return r0;
    }

    private int mul(int r3, int r4) {
        int r1;
        if (r3 == 0) {
            r1 = BASE - r4;
        } else if (r4 == 0) {
            r1 = BASE - r3;
        } else {
            int r32 = r3 * r4;
            int r42 = r32 & 65535;
            int r33 = r32 >>> 16;
            r1 = (r42 - r33) + (r42 < r33 ? 1 : 0);
        }
        return r1 & 65535;
    }

    private int mulInv(int r7) {
        if (r7 < 2) {
            return r7;
        }
        int r1 = BASE / r7;
        int r0 = BASE % r7;
        int r3 = 1;
        while (r0 != 1) {
            int r5 = r7 / r0;
            r7 %= r0;
            r3 = (r3 + (r5 * r1)) & 65535;
            if (r7 == 1) {
                return r3;
            }
            int r52 = r0 / r7;
            r0 %= r7;
            r1 = (r1 + (r52 * r3)) & 65535;
        }
        return (1 - r1) & 65535;
    }

    private void wordToBytes(int r2, byte[] bArr, int r4) {
        bArr[r4] = (byte) (r2 >>> 8);
        bArr[r4 + 1] = (byte) r2;
    }

    int addInv(int r2) {
        return (0 - r2) & 65535;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "IDEA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(z, ((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to IDEA init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int r8, byte[] bArr2, int r10) {
        int[] r1 = this.workingKey;
        if (r1 != null) {
            if (r8 + 8 <= bArr.length) {
                if (r10 + 8 <= bArr2.length) {
                    ideaFunc(r1, bArr, r8, bArr2, r10);
                    return 8;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("IDEA engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
