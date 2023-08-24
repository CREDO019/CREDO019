package org.bouncycastle.pqc.crypto.sphincs;

import com.google.common.base.Ascii;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class Wots {
    static final int WOTS_L = 67;
    static final int WOTS_L1 = 64;
    static final int WOTS_LOGW = 4;
    static final int WOTS_LOG_L = 7;
    static final int WOTS_SIGBYTES = 2144;
    static final int WOTS_W = 16;

    private static void clear(byte[] bArr, int r4, int r5) {
        for (int r1 = 0; r1 != r5; r1++) {
            bArr[r1 + r4] = 0;
        }
    }

    static void expand_seed(byte[] bArr, int r8, byte[] bArr2, int r10) {
        clear(bArr, r8, WOTS_SIGBYTES);
        Seed.prg(bArr, r8, 2144L, bArr2, r10);
    }

    static void gen_chain(HashFunctions hashFunctions, byte[] bArr, int r10, byte[] bArr2, int r12, byte[] bArr3, int r14, int r15) {
        for (int r1 = 0; r1 < 32; r1++) {
            bArr[r1 + r10] = bArr2[r1 + r12];
        }
        for (int r0 = 0; r0 < r15 && r0 < 16; r0++) {
            hashFunctions.hash_n_n_mask(bArr, r10, bArr, r10, bArr3, r14 + (r0 * 32));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void wots_pkgen(HashFunctions hashFunctions, byte[] bArr, int r11, byte[] bArr2, int r13, byte[] bArr3, int r15) {
        expand_seed(bArr, r11, bArr2, r13);
        for (int r12 = 0; r12 < 67; r12++) {
            int r4 = r11 + (r12 * 32);
            gen_chain(hashFunctions, bArr, r4, bArr, r4, bArr3, r15, 15);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void wots_sign(HashFunctions hashFunctions, byte[] bArr, int r17, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int[] r2 = new int[67];
        int r4 = 0;
        int r5 = 0;
        while (r4 < 64) {
            int r6 = r4 / 2;
            r2[r4] = bArr2[r6] & Ascii.f1128SI;
            int r7 = r4 + 1;
            r2[r7] = (bArr2[r6] & 255) >>> 4;
            r5 = r5 + (15 - r2[r4]) + (15 - r2[r7]);
            r4 += 2;
        }
        while (r4 < 67) {
            r2[r4] = r5 & 15;
            r5 >>>= 4;
            r4++;
        }
        expand_seed(bArr, r17, bArr3, 0);
        for (int r3 = 0; r3 < 67; r3++) {
            int r10 = r17 + (r3 * 32);
            gen_chain(hashFunctions, bArr, r10, bArr, r10, bArr4, 0, r2[r3]);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void wots_verify(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, int r15, byte[] bArr3, byte[] bArr4) {
        int[] r1 = new int[67];
        int r3 = 0;
        int r4 = 0;
        while (r3 < 64) {
            int r5 = r3 / 2;
            r1[r3] = bArr3[r5] & Ascii.f1128SI;
            int r6 = r3 + 1;
            r1[r6] = (bArr3[r5] & 255) >>> 4;
            r4 = r4 + (15 - r1[r3]) + (15 - r1[r6]);
            r3 += 2;
        }
        while (r3 < 67) {
            r1[r3] = r4 & 15;
            r4 >>>= 4;
            r3++;
        }
        for (int r2 = 0; r2 < 67; r2++) {
            int r52 = r2 * 32;
            gen_chain(hashFunctions, bArr, r52, bArr2, r15 + r52, bArr4, r1[r2] * 32, 15 - r1[r2]);
        }
    }
}
