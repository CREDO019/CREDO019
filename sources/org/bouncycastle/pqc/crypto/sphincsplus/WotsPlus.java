package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* loaded from: classes3.dex */
class WotsPlus {
    private final SPHINCSPlusEngine engine;

    /* renamed from: w */
    private final int f2512w;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WotsPlus(SPHINCSPlusEngine sPHINCSPlusEngine) {
        this.engine = sPHINCSPlusEngine;
        this.f2512w = sPHINCSPlusEngine.WOTS_W;
    }

    int[] base_w(byte[] bArr, int r10, int r11) {
        int[] r0 = new int[r11];
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        int r5 = 0;
        for (int r1 = 0; r1 < r11; r1++) {
            if (r2 == 0) {
                r5 = bArr[r3];
                r3++;
                r2 += 8;
            }
            r2 -= this.engine.WOTS_LOGW;
            r0[r4] = (r5 >>> r2) & (r10 - 1);
            r4++;
        }
        return r0;
    }

    byte[] chain(byte[] bArr, int r9, int r10, byte[] bArr2, ADRS adrs) {
        if (r10 == 0) {
            return Arrays.clone(bArr);
        }
        int r0 = r9 + r10;
        if (r0 > this.f2512w - 1) {
            return null;
        }
        byte[] chain = chain(bArr, r9, r10 - 1, bArr2, adrs);
        adrs.setHashAddress(r0 - 1);
        return this.engine.mo8F(bArr2, adrs, chain);
    }

    public byte[] pkFromSig(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        int[] base_w = base_w(bArr2, this.f2512w, this.engine.WOTS_LEN1);
        int r2 = 0;
        for (int r1 = 0; r1 < this.engine.WOTS_LEN1; r1++) {
            r2 += (this.f2512w - 1) - base_w[r1];
        }
        int[] concatenate = Arrays.concatenate(base_w, base_w(Arrays.copyOfRange(Pack.intToBigEndian(r2 << (8 - ((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) % 8))), 4 - (((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) + 7) / 8), 4), this.f2512w, this.engine.WOTS_LEN2));
        byte[] bArr4 = new byte[this.engine.f2507N];
        byte[][] bArr5 = new byte[this.engine.WOTS_LEN];
        for (int r14 = 0; r14 < this.engine.WOTS_LEN; r14++) {
            adrs.setChainAddress(r14);
            System.arraycopy(bArr, this.engine.f2507N * r14, bArr4, 0, this.engine.f2507N);
            bArr5[r14] = chain(bArr4, concatenate[r14], (this.f2512w - 1) - concatenate[r14], bArr3, adrs);
        }
        adrs2.setType(1);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr3, adrs2, Arrays.concatenate(bArr5));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] pkGen(byte[] bArr, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        byte[][] bArr3 = new byte[this.engine.WOTS_LEN];
        for (int r3 = 0; r3 < this.engine.WOTS_LEN; r3++) {
            ADRS adrs3 = new ADRS(adrs);
            adrs3.setChainAddress(r3);
            adrs3.setHashAddress(0);
            bArr3[r3] = chain(this.engine.PRF(bArr, adrs3), 0, this.f2512w - 1, bArr2, adrs3);
        }
        adrs2.setType(1);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr2, adrs2, Arrays.concatenate(bArr3));
    }

    public byte[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        int[] base_w = base_w(bArr, this.f2512w, this.engine.WOTS_LEN1);
        int r1 = 0;
        for (int r0 = 0; r0 < this.engine.WOTS_LEN1; r0++) {
            r1 += (this.f2512w - 1) - base_w[r0];
        }
        if (this.engine.WOTS_LOGW % 8 != 0) {
            r1 <<= 8 - ((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) % 8);
        }
        byte[] intToBigEndian = Pack.intToBigEndian(r1);
        int[] concatenate = Arrays.concatenate(base_w, base_w(Arrays.copyOfRange(intToBigEndian, ((this.engine.WOTS_LEN2 * this.engine.WOTS_LOGW) + 7) / 8, intToBigEndian.length), this.f2512w, this.engine.WOTS_LEN2));
        byte[][] bArr4 = new byte[this.engine.WOTS_LEN];
        for (int r8 = 0; r8 < this.engine.WOTS_LEN; r8++) {
            adrs2.setChainAddress(r8);
            adrs2.setHashAddress(0);
            bArr4[r8] = chain(this.engine.PRF(bArr2, adrs2), 0, concatenate[r8], bArr3, adrs2);
        }
        return Arrays.concatenate(bArr4);
    }
}
