package org.bouncycastle.pqc.crypto.sphincsplus;

import java.util.LinkedList;
import org.bouncycastle.util.Arrays;

/* renamed from: org.bouncycastle.pqc.crypto.sphincsplus.HT */
/* loaded from: classes3.dex */
class C5393HT {
    SPHINCSPlusEngine engine;
    final byte[] htPubKey;
    private final byte[] pkSeed;
    private final byte[] skSeed;
    WotsPlus wots;

    public C5393HT(SPHINCSPlusEngine sPHINCSPlusEngine, byte[] bArr, byte[] bArr2) {
        this.skSeed = bArr;
        this.pkSeed = bArr2;
        this.engine = sPHINCSPlusEngine;
        this.wots = new WotsPlus(sPHINCSPlusEngine);
        ADRS adrs = new ADRS();
        adrs.setLayerAddress(sPHINCSPlusEngine.f2504D - 1);
        adrs.setTreeAddress(0L);
        if (bArr != null) {
            this.htPubKey = xmss_PKgen(bArr, bArr2, adrs);
        } else {
            this.htPubKey = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] sign(byte[] bArr, long j, int r21) {
        long j2 = j;
        ADRS adrs = new ADRS();
        adrs.setLayerAddress(0);
        adrs.setTreeAddress(j2);
        SIG_XMSS xmss_sign = xmss_sign(bArr, this.skSeed, r21, this.pkSeed, adrs);
        int r11 = this.engine.f2504D;
        SIG_XMSS[] r12 = new SIG_XMSS[r11];
        r12[0] = xmss_sign;
        adrs.setLayerAddress(0);
        adrs.setTreeAddress(j2);
        byte[] xmss_pkFromSig = xmss_pkFromSig(r21, xmss_sign, bArr, this.pkSeed, adrs);
        for (int r15 = 1; r15 < this.engine.f2504D; r15++) {
            int r5 = (int) (((1 << this.engine.H_PRIME) - 1) & j2);
            j2 >>>= this.engine.H_PRIME;
            adrs.setLayerAddress(r15);
            adrs.setTreeAddress(j2);
            SIG_XMSS xmss_sign2 = xmss_sign(xmss_pkFromSig, this.skSeed, r5, this.pkSeed, adrs);
            r12[r15] = xmss_sign2;
            if (r15 < this.engine.f2504D - 1) {
                xmss_pkFromSig = xmss_pkFromSig(r5, xmss_sign2, xmss_pkFromSig, this.pkSeed, adrs);
            }
        }
        byte[][] bArr2 = new byte[r11];
        for (int r10 = 0; r10 != r11; r10++) {
            bArr2[r10] = Arrays.concatenate(r12[r10].sig, Arrays.concatenate(r12[r10].auth));
        }
        return Arrays.concatenate(bArr2);
    }

    byte[] treehash(byte[] bArr, int r10, int r11, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        LinkedList linkedList = new LinkedList();
        int r112 = 1 << r11;
        if (r10 % r112 != 0) {
            return null;
        }
        for (int r3 = 0; r3 < r112; r3++) {
            adrs2.setType(0);
            int r4 = r10 + r3;
            adrs2.setKeyPairAddress(r4);
            byte[] pkGen = this.wots.pkGen(bArr, bArr2, adrs2);
            adrs2.setType(2);
            adrs2.setTreeHeight(1);
            adrs2.setTreeIndex(r4);
            while (!linkedList.isEmpty() && ((NodeEntry) linkedList.get(0)).nodeHeight == adrs2.getTreeHeight()) {
                adrs2.setTreeIndex((adrs2.getTreeIndex() - 1) / 2);
                pkGen = this.engine.mo7H(bArr2, adrs2, ((NodeEntry) linkedList.remove(0)).nodeValue, pkGen);
                adrs2.setTreeHeight(adrs2.getTreeHeight() + 1);
            }
            linkedList.add(0, new NodeEntry(pkGen, adrs2.getTreeHeight()));
        }
        return ((NodeEntry) linkedList.get(0)).nodeValue;
    }

    public boolean verify(byte[] bArr, SIG_XMSS[] r9, byte[] bArr2, long j, int r13, byte[] bArr3) {
        ADRS adrs = new ADRS();
        SIG_XMSS r2 = r9[0];
        adrs.setLayerAddress(0);
        adrs.setTreeAddress(j);
        byte[] xmss_pkFromSig = xmss_pkFromSig(r13, r2, bArr, bArr2, adrs);
        for (int r8 = 1; r8 < this.engine.f2504D; r8++) {
            int r1 = (int) (((1 << this.engine.H_PRIME) - 1) & j);
            j >>>= this.engine.H_PRIME;
            SIG_XMSS r22 = r9[r8];
            adrs.setLayerAddress(r8);
            adrs.setTreeAddress(j);
            xmss_pkFromSig = xmss_pkFromSig(r1, r22, xmss_pkFromSig, bArr2, adrs);
        }
        return Arrays.areEqual(bArr3, xmss_pkFromSig);
    }

    byte[] xmss_PKgen(byte[] bArr, byte[] bArr2, ADRS adrs) {
        return treehash(bArr, 0, this.engine.H_PRIME, bArr2, adrs);
    }

    byte[] xmss_pkFromSig(int r6, SIG_XMSS r7, byte[] bArr, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        int r10 = 0;
        adrs2.setType(0);
        adrs2.setKeyPairAddress(r6);
        byte[] wOTSSig = r7.getWOTSSig();
        byte[][] xmssauth = r7.getXMSSAUTH();
        byte[] pkFromSig = this.wots.pkFromSig(wOTSSig, bArr, bArr2, adrs2);
        adrs2.setType(2);
        adrs2.setTreeIndex(r6);
        while (r10 < this.engine.H_PRIME) {
            int r2 = r10 + 1;
            adrs2.setTreeHeight(r2);
            if ((r6 / (1 << r10)) % 2 == 0) {
                adrs2.setTreeIndex(adrs2.getTreeIndex() / 2);
                pkFromSig = this.engine.mo7H(bArr2, adrs2, pkFromSig, xmssauth[r10]);
            } else {
                adrs2.setTreeIndex((adrs2.getTreeIndex() - 1) / 2);
                pkFromSig = this.engine.mo7H(bArr2, adrs2, xmssauth[r10], pkFromSig);
            }
            r10 = r2;
        }
        return pkFromSig;
    }

    SIG_XMSS xmss_sign(byte[] bArr, byte[] bArr2, int r12, byte[] bArr3, ADRS adrs) {
        byte[][] bArr4 = new byte[this.engine.H_PRIME];
        for (int r8 = 0; r8 < this.engine.H_PRIME; r8++) {
            int r3 = 1 << r8;
            bArr4[r8] = treehash(bArr2, (1 ^ (r12 / r3)) * r3, r8, bArr3, adrs);
        }
        ADRS adrs2 = new ADRS(adrs);
        adrs2.setType(0);
        adrs2.setKeyPairAddress(r12);
        return new SIG_XMSS(this.wots.sign(bArr, bArr2, bArr3, adrs2), bArr4);
    }
}
