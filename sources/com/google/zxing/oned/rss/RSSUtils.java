package com.google.zxing.oned.rss;

/* loaded from: classes3.dex */
public final class RSSUtils {
    private RSSUtils() {
    }

    public static int getRSSvalue(int[] r17, int r18, boolean z) {
        int[] r0 = r17;
        int r5 = 0;
        for (int r6 : r0) {
            r5 += r6;
        }
        int length = r0.length;
        int r4 = 0;
        int r62 = 0;
        int r7 = 0;
        while (true) {
            int r8 = length - 1;
            if (r4 >= r8) {
                return r62;
            }
            int r10 = 1 << r4;
            r7 |= r10;
            int r11 = 1;
            while (r11 < r0[r4]) {
                int r12 = r5 - r11;
                int r14 = length - r4;
                int r15 = r14 - 2;
                int combins = combins(r12 - 1, r15);
                if (z && r7 == 0) {
                    int r3 = r14 - 1;
                    if (r12 - r3 >= r3) {
                        combins -= combins(r12 - r14, r15);
                    }
                }
                if (r14 - 1 > 1) {
                    int r152 = 0;
                    for (int r32 = r12 - r15; r32 > r18; r32--) {
                        r152 += combins((r12 - r32) - 1, r14 - 3);
                    }
                    combins -= r152 * (r8 - r4);
                } else if (r12 > r18) {
                    combins--;
                }
                r62 += combins;
                r11++;
                r7 &= ~r10;
                r0 = r17;
            }
            r5 -= r11;
            r4++;
            r0 = r17;
        }
    }

    private static int combins(int r4, int r5) {
        int r0 = r4 - r5;
        if (r0 > r5) {
            r0 = r5;
            r5 = r0;
        }
        int r1 = 1;
        int r2 = 1;
        while (r4 > r5) {
            r1 *= r4;
            if (r2 <= r0) {
                r1 /= r2;
                r2++;
            }
            r4--;
        }
        while (r2 <= r0) {
            r1 /= r2;
            r2++;
        }
        return r1;
    }
}
