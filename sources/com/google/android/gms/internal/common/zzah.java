package com.google.android.gms.internal.common;

import org.jspecify.nullness.NullMarked;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
@NullMarked
/* loaded from: classes.dex */
public final class zzah {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] zza(Object[] objArr, int r3) {
        for (int r0 = 0; r0 < r3; r0++) {
            if (objArr[r0] == null) {
                throw new NullPointerException("at index " + r0);
            }
        }
        return objArr;
    }
}
