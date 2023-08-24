package com.google.common.collect;

import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
final class Hashing {

    /* renamed from: C1 */
    private static final long f1156C1 = -862048943;

    /* renamed from: C2 */
    private static final long f1157C2 = 461845907;
    private static final int MAX_TABLE_SIZE = 1073741824;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean needsResizing(int r4, int r5, double d) {
        return ((double) r4) > d * ((double) r5) && r5 < 1073741824;
    }

    private Hashing() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int smear(int r4) {
        return (int) (Integer.rotateLeft((int) (r4 * f1156C1), 15) * f1157C2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int smearedHash(@CheckForNull Object obj) {
        return smear(obj == null ? 0 : obj.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int closedTableSize(int r3, double d) {
        int max = Math.max(r3, 2);
        int highestOneBit = Integer.highestOneBit(max);
        if (max > ((int) (d * highestOneBit))) {
            int r32 = highestOneBit << 1;
            if (r32 > 0) {
                return r32;
            }
            return 1073741824;
        }
        return highestOneBit;
    }
}
