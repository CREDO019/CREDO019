package com.google.android.gms.common;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes2.dex */
final class zzd {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int r5) {
        int[] r1 = {1, 2, 3};
        for (int r2 = 0; r2 < 3; r2++) {
            int r3 = r1[r2];
            int r4 = r3 - 1;
            if (r3 == 0) {
                throw null;
            }
            if (r4 == r5) {
                return r3;
            }
        }
        return 1;
    }
}
