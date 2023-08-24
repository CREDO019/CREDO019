package org.bouncycastle.math.raw;

/* loaded from: classes5.dex */
public abstract class Mont256 {

    /* renamed from: M */
    private static final long f2380M = 4294967295L;

    public static int inverse32(int r2) {
        int r0 = (2 - (r2 * r2)) * r2;
        int r02 = r0 * (2 - (r2 * r0));
        int r03 = r02 * (2 - (r2 * r02));
        return r03 * (2 - (r2 * r03));
    }

    public static void multAdd(int[] r27, int[] r28, int[] r29, int[] r30, int r31) {
        char c = 0;
        long j = r28[0] & 4294967295L;
        int r7 = 0;
        int r8 = 0;
        while (r7 < 8) {
            long j2 = r27[r7] & 4294967295L;
            long j3 = j2 * j;
            long j4 = (j3 & 4294967295L) + (r29[c] & 4294967295L);
            long j5 = j;
            long j6 = (((int) j4) * r31) & 4294967295L;
            int r19 = r7;
            int r20 = r8;
            long j7 = (r30[c] & 4294967295L) * j6;
            long j8 = ((j4 + (j7 & 4294967295L)) >>> 32) + (j3 >>> 32) + (j7 >>> 32);
            int r72 = 1;
            for (int r82 = 8; r72 < r82; r82 = 8) {
                long j9 = (r28[r72] & 4294967295L) * j2;
                long j10 = (r30[r72] & 4294967295L) * j6;
                long j11 = j8 + (j9 & 4294967295L) + (j10 & 4294967295L) + (r29[r72] & 4294967295L);
                r29[r72 - 1] = (int) j11;
                j8 = (j11 >>> 32) + (j9 >>> 32) + (j10 >>> 32);
                r72++;
                j6 = j6;
            }
            long j12 = j8 + (r20 & 4294967295L);
            r29[7] = (int) j12;
            r8 = (int) (j12 >>> 32);
            r7 = r19 + 1;
            j = j5;
            c = 0;
        }
        if (r8 != 0 || Nat256.gte(r29, r30)) {
            Nat256.sub(r29, r30, r29);
        }
    }

    public static void multAddXF(int[] r26, int[] r27, int[] r28, int[] r29) {
        char c = 0;
        long j = r27[0] & 4294967295L;
        int r7 = 0;
        int r8 = 0;
        while (true) {
            if (r7 >= 8) {
                break;
            }
            long j2 = r26[r7] & 4294967295L;
            long j3 = (j2 * j) + (r28[c] & 4294967295L);
            long j4 = j3 & 4294967295L;
            long j5 = (j3 >>> 32) + j4;
            int r2 = 1;
            for (int r9 = 8; r2 < r9; r9 = 8) {
                long j6 = j;
                long j7 = (r27[r2] & 4294967295L) * j2;
                long j8 = (r29[r2] & 4294967295L) * j4;
                long j9 = j5 + (j7 & 4294967295L) + (j8 & 4294967295L) + (r28[r2] & 4294967295L);
                r28[r2 - 1] = (int) j9;
                j5 = (j9 >>> 32) + (j7 >>> 32) + (j8 >>> 32);
                r2++;
                j = j6;
                j2 = j2;
                j4 = j4;
            }
            long j10 = j5 + (r8 & 4294967295L);
            r28[7] = (int) j10;
            r8 = (int) (j10 >>> 32);
            r7++;
            j = j;
            c = 0;
        }
        if (r8 != 0 || Nat256.gte(r28, r29)) {
            Nat256.sub(r28, r29, r28);
        }
    }

    public static void reduce(int[] r17, int[] r18, int r19) {
        char c = 0;
        int r3 = 0;
        while (r3 < 8) {
            int r5 = r17[c];
            long j = (r5 * r19) & 4294967295L;
            long j2 = (((r18[c] & 4294967295L) * j) + (r5 & 4294967295L)) >>> 32;
            int r12 = 1;
            while (r12 < 8) {
                long j3 = j2 + ((r18[r12] & 4294967295L) * j) + (r17[r12] & 4294967295L);
                r17[r12 - 1] = (int) j3;
                j2 = j3 >>> 32;
                r12++;
                r3 = r3;
            }
            r17[7] = (int) j2;
            r3++;
            c = 0;
        }
        if (Nat256.gte(r17, r18)) {
            Nat256.sub(r17, r18, r17);
        }
    }

    public static void reduceXF(int[] r14, int[] r15) {
        for (int r1 = 0; r1 < 8; r1++) {
            long j = r14[0] & 4294967295L;
            long j2 = j;
            for (int r7 = 1; r7 < 8; r7++) {
                long j3 = j2 + ((r15[r7] & 4294967295L) * j) + (r14[r7] & 4294967295L);
                r14[r7 - 1] = (int) j3;
                j2 = j3 >>> 32;
            }
            r14[7] = (int) j2;
        }
        if (Nat256.gte(r14, r15)) {
            Nat256.sub(r14, r15, r14);
        }
    }
}
