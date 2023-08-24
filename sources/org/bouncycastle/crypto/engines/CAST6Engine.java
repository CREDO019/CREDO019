package org.bouncycastle.crypto.engines;

/* loaded from: classes5.dex */
public final class CAST6Engine extends CAST5Engine {
    protected static final int BLOCK_SIZE = 16;
    protected static final int ROUNDS = 12;
    protected int[] _Kr = new int[48];
    protected int[] _Km = new int[48];
    protected int[] _Tr = new int[192];
    protected int[] _Tm = new int[192];
    private int[] _workingKey = new int[8];

    protected final void CAST_Decipher(int r8, int r9, int r10, int r11, int[] r12) {
        int r2;
        int r1 = 0;
        while (true) {
            if (r1 >= 6) {
                break;
            }
            int r22 = (11 - r1) * 4;
            r10 ^= m66F1(r11, this._Km[r22], this._Kr[r22]);
            int r5 = r22 + 1;
            r9 ^= m65F2(r10, this._Km[r5], this._Kr[r5]);
            int r52 = r22 + 2;
            r8 ^= m64F3(r9, this._Km[r52], this._Kr[r52]);
            int r23 = r22 + 3;
            r11 ^= m66F1(r8, this._Km[r23], this._Kr[r23]);
            r1++;
        }
        for (r2 = 6; r2 < 12; r2++) {
            int r13 = (11 - r2) * 4;
            int r53 = r13 + 3;
            r11 ^= m66F1(r8, this._Km[r53], this._Kr[r53]);
            int r54 = r13 + 2;
            r8 ^= m64F3(r9, this._Km[r54], this._Kr[r54]);
            int r55 = r13 + 1;
            r9 ^= m65F2(r10, this._Km[r55], this._Kr[r55]);
            r10 ^= m66F1(r11, this._Km[r13], this._Kr[r13]);
        }
        r12[0] = r8;
        r12[1] = r9;
        r12[2] = r10;
        r12[3] = r11;
    }

    protected final void CAST_Encipher(int r8, int r9, int r10, int r11, int[] r12) {
        int r2;
        int r1 = 0;
        while (true) {
            if (r1 >= 6) {
                break;
            }
            int r22 = r1 * 4;
            r10 ^= m66F1(r11, this._Km[r22], this._Kr[r22]);
            int r5 = r22 + 1;
            r9 ^= m65F2(r10, this._Km[r5], this._Kr[r5]);
            int r52 = r22 + 2;
            r8 ^= m64F3(r9, this._Km[r52], this._Kr[r52]);
            int r23 = r22 + 3;
            r11 ^= m66F1(r8, this._Km[r23], this._Kr[r23]);
            r1++;
        }
        for (r2 = 6; r2 < 12; r2++) {
            int r13 = r2 * 4;
            int r53 = r13 + 3;
            r11 ^= m66F1(r8, this._Km[r53], this._Kr[r53]);
            int r54 = r13 + 2;
            r8 ^= m64F3(r9, this._Km[r54], this._Kr[r54]);
            int r55 = r13 + 1;
            r9 ^= m65F2(r10, this._Km[r55], this._Kr[r55]);
            r10 ^= m66F1(r11, this._Km[r13], this._Kr[r13]);
        }
        r12[0] = r8;
        r12[1] = r9;
        r12[2] = r10;
        r12[3] = r11;
    }

    @Override // org.bouncycastle.crypto.engines.CAST5Engine
    protected int decryptBlock(byte[] bArr, int r9, byte[] bArr2, int r11) {
        int[] r0 = new int[4];
        CAST_Decipher(BytesTo32bits(bArr, r9), BytesTo32bits(bArr, r9 + 4), BytesTo32bits(bArr, r9 + 8), BytesTo32bits(bArr, r9 + 12), r0);
        Bits32ToBytes(r0[0], bArr2, r11);
        Bits32ToBytes(r0[1], bArr2, r11 + 4);
        Bits32ToBytes(r0[2], bArr2, r11 + 8);
        Bits32ToBytes(r0[3], bArr2, r11 + 12);
        return 16;
    }

    @Override // org.bouncycastle.crypto.engines.CAST5Engine
    protected int encryptBlock(byte[] bArr, int r9, byte[] bArr2, int r11) {
        int[] r0 = new int[4];
        CAST_Encipher(BytesTo32bits(bArr, r9), BytesTo32bits(bArr, r9 + 4), BytesTo32bits(bArr, r9 + 8), BytesTo32bits(bArr, r9 + 12), r0);
        Bits32ToBytes(r0[0], bArr2, r11);
        Bits32ToBytes(r0[1], bArr2, r11 + 4);
        Bits32ToBytes(r0[2], bArr2, r11 + 8);
        Bits32ToBytes(r0[3], bArr2, r11 + 12);
        return 16;
    }

    @Override // org.bouncycastle.crypto.engines.CAST5Engine, org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "CAST6";
    }

    @Override // org.bouncycastle.crypto.engines.CAST5Engine, org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.engines.CAST5Engine, org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }

    @Override // org.bouncycastle.crypto.engines.CAST5Engine
    protected void setKey(byte[] bArr) {
        int r3 = 1518500249;
        int r4 = 19;
        for (int r5 = 0; r5 < 24; r5++) {
            for (int r6 = 0; r6 < 8; r6++) {
                int r9 = (r5 * 8) + r6;
                this._Tm[r9] = r3;
                r3 += 1859775393;
                this._Tr[r9] = r4;
                r4 = (r4 + 17) & 31;
            }
        }
        byte[] bArr2 = new byte[64];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        for (int r1 = 0; r1 < 8; r1++) {
            this._workingKey[r1] = BytesTo32bits(bArr2, r1 * 4);
        }
        for (int r12 = 0; r12 < 12; r12++) {
            int r32 = r12 * 2;
            int r42 = r32 * 8;
            int[] r52 = this._workingKey;
            r52[6] = r52[6] ^ m66F1(r52[7], this._Tm[r42], this._Tr[r42]);
            int[] r53 = this._workingKey;
            int r13 = r42 + 1;
            r53[5] = r53[5] ^ m65F2(r53[6], this._Tm[r13], this._Tr[r13]);
            int[] r54 = this._workingKey;
            int r14 = r42 + 2;
            r54[4] = r54[4] ^ m64F3(r54[5], this._Tm[r14], this._Tr[r14]);
            int[] r55 = this._workingKey;
            int r15 = r42 + 3;
            r55[3] = m66F1(r55[4], this._Tm[r15], this._Tr[r15]) ^ r55[3];
            int[] r56 = this._workingKey;
            int r152 = r42 + 4;
            r56[2] = m65F2(r56[3], this._Tm[r152], this._Tr[r152]) ^ r56[2];
            int[] r57 = this._workingKey;
            int r153 = r42 + 5;
            r57[1] = m64F3(r57[2], this._Tm[r153], this._Tr[r153]) ^ r57[1];
            int[] r58 = this._workingKey;
            int r142 = r42 + 6;
            r58[0] = r58[0] ^ m66F1(r58[1], this._Tm[r142], this._Tr[r142]);
            int[] r59 = this._workingKey;
            int r43 = r42 + 7;
            r59[7] = m65F2(r59[0], this._Tm[r43], this._Tr[r43]) ^ r59[7];
            int r33 = (r32 + 1) * 8;
            int[] r44 = this._workingKey;
            r44[6] = r44[6] ^ m66F1(r44[7], this._Tm[r33], this._Tr[r33]);
            int[] r45 = this._workingKey;
            int r132 = r33 + 1;
            r45[5] = r45[5] ^ m65F2(r45[6], this._Tm[r132], this._Tr[r132]);
            int[] r46 = this._workingKey;
            int r143 = r33 + 2;
            r46[4] = r46[4] ^ m64F3(r46[5], this._Tm[r143], this._Tr[r143]);
            int[] r47 = this._workingKey;
            int r144 = r33 + 3;
            r47[3] = m66F1(r47[4], this._Tm[r144], this._Tr[r144]) ^ r47[3];
            int[] r48 = this._workingKey;
            int r145 = r33 + 4;
            r48[2] = m65F2(r48[3], this._Tm[r145], this._Tr[r145]) ^ r48[2];
            int[] r49 = this._workingKey;
            int r133 = r33 + 5;
            r49[1] = m64F3(r49[2], this._Tm[r133], this._Tr[r133]) ^ r49[1];
            int[] r410 = this._workingKey;
            int r134 = r33 + 6;
            r410[0] = r410[0] ^ m66F1(r410[1], this._Tm[r134], this._Tr[r134]);
            int[] r411 = this._workingKey;
            int r34 = r33 + 7;
            r411[7] = m65F2(r411[0], this._Tm[r34], this._Tr[r34]) ^ r411[7];
            int[] r35 = this._Kr;
            int r412 = r12 * 4;
            int[] r510 = this._workingKey;
            r35[r412] = r510[0] & 31;
            int r10 = r412 + 1;
            r35[r10] = r510[2] & 31;
            int r122 = r412 + 2;
            r35[r122] = r510[4] & 31;
            int r135 = r412 + 3;
            r35[r135] = r510[6] & 31;
            int[] r36 = this._Km;
            r36[r412] = r510[7];
            r36[r10] = r510[5];
            r36[r122] = r510[3];
            r36[r135] = r510[1];
        }
    }
}
