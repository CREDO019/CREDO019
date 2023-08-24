package org.bouncycastle.pqc.crypto.sphincsplus;

import java.util.LinkedList;
import org.bouncycastle.util.Arrays;

/* loaded from: classes3.dex */
class Fors {
    SPHINCSPlusEngine engine;
    private final WotsPlus wots;

    public Fors(SPHINCSPlusEngine sPHINCSPlusEngine) {
        this.engine = sPHINCSPlusEngine;
        this.wots = new WotsPlus(sPHINCSPlusEngine);
    }

    static int[] message_to_idxs(byte[] bArr, int r9, int r10) {
        int[] r0 = new int[r9];
        int r3 = 0;
        for (int r2 = 0; r2 < r9; r2++) {
            r0[r2] = 0;
            for (int r4 = 0; r4 < r10; r4++) {
                r0[r2] = r0[r2] ^ (((bArr[r3 >> 3] >> (r3 & 7)) & 1) << r4);
                r3++;
            }
        }
        return r0;
    }

    public byte[] pkFromSig(SIG_FORS[] r17, byte[] bArr, byte[] bArr2, ADRS adrs) {
        int r3 = 2;
        byte[][] bArr3 = new byte[2];
        byte[][] bArr4 = new byte[this.engine.f2506K];
        int r6 = this.engine.f2508T;
        int[] message_to_idxs = message_to_idxs(bArr, this.engine.f2506K, this.engine.f2503A);
        int r9 = 0;
        while (r9 < this.engine.f2506K) {
            int r10 = message_to_idxs[r9];
            byte[] sk = r17[r9].getSK();
            adrs.setTreeHeight(0);
            int r12 = (r9 * r6) + r10;
            adrs.setTreeIndex(r12);
            bArr3[0] = this.engine.mo8F(bArr2, adrs, sk);
            byte[][] authPath = r17[r9].getAuthPath();
            adrs.setTreeIndex(r12);
            int r122 = 0;
            while (r122 < this.engine.f2503A) {
                int r13 = r122 + 1;
                adrs.setTreeHeight(r13);
                if ((r10 / (1 << r122)) % r3 == 0) {
                    adrs.setTreeIndex(adrs.getTreeIndex() / r3);
                    bArr3[1] = this.engine.mo7H(bArr2, adrs, bArr3[0], authPath[r122]);
                } else {
                    adrs.setTreeIndex((adrs.getTreeIndex() - 1) / 2);
                    bArr3[1] = this.engine.mo7H(bArr2, adrs, authPath[r122], bArr3[0]);
                }
                bArr3[0] = bArr3[1];
                r122 = r13;
                r3 = 2;
            }
            bArr4[r9] = bArr3[0];
            r9++;
            r3 = 2;
        }
        ADRS adrs2 = new ADRS(adrs);
        adrs2.setType(4);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr2, adrs2, Arrays.concatenate(bArr4));
    }

    byte[] pkGen(byte[] bArr, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        byte[][] bArr3 = new byte[this.engine.f2506K];
        for (int r2 = 0; r2 < this.engine.f2506K; r2++) {
            bArr3[r2] = treehash(bArr, r2 * this.engine.f2508T, this.engine.f2503A, bArr2, adrs);
        }
        adrs2.setType(4);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr2, adrs2, Arrays.concatenate(bArr3));
    }

    public SIG_FORS[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        Fors fors = this;
        int[] message_to_idxs = message_to_idxs(bArr, fors.engine.f2506K, fors.engine.f2503A);
        SIG_FORS[] r9 = new SIG_FORS[fors.engine.f2506K];
        int r10 = fors.engine.f2508T;
        int r11 = 0;
        int r12 = 0;
        while (r12 < fors.engine.f2506K) {
            int r13 = message_to_idxs[r12];
            adrs.setTreeHeight(r11);
            int r14 = r12 * r10;
            adrs.setTreeIndex(r14 + r13);
            byte[] PRF = fors.engine.PRF(bArr2, adrs);
            byte[][] bArr4 = new byte[fors.engine.f2503A];
            int r3 = 0;
            while (r3 < fors.engine.f2503A) {
                int r1 = 1 << r3;
                int r16 = r3;
                byte[][] bArr5 = bArr4;
                bArr5[r16] = treehash(bArr2, r14 + ((1 ^ (r13 / r1)) * r1), r3, bArr3, adrs);
                r3 = r16 + 1;
                PRF = PRF;
                bArr4 = bArr5;
                fors = this;
            }
            r9[r12] = new SIG_FORS(PRF, bArr4);
            r12++;
            r11 = 0;
            fors = this;
        }
        return r9;
    }

    byte[] treehash(byte[] bArr, int r9, int r10, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        LinkedList linkedList = new LinkedList();
        int r102 = 1 << r10;
        if (r9 % r102 != 0) {
            return null;
        }
        for (int r3 = 0; r3 < r102; r3++) {
            adrs2.setTreeHeight(0);
            int r4 = r9 + r3;
            adrs2.setTreeIndex(r4);
            byte[] mo8F = this.engine.mo8F(bArr2, adrs2, this.engine.PRF(bArr, adrs2));
            adrs2.setTreeHeight(1);
            adrs2.setTreeIndex(r4);
            while (!linkedList.isEmpty() && ((NodeEntry) linkedList.get(0)).nodeHeight == adrs2.getTreeHeight()) {
                adrs2.setTreeIndex((adrs2.getTreeIndex() - 1) / 2);
                mo8F = this.engine.mo7H(bArr2, adrs2, ((NodeEntry) linkedList.remove(0)).nodeValue, mo8F);
                adrs2.setTreeHeight(adrs2.getTreeHeight() + 1);
            }
            linkedList.add(0, new NodeEntry(mo8F, adrs2.getTreeHeight()));
        }
        return ((NodeEntry) linkedList.get(0)).nodeValue;
    }
}
