package com.google.android.gms.internal.vision;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzcx {
    public static <T> zzcu<T> zza(zzcu<T> zzcuVar) {
        if ((zzcuVar instanceof zzcz) || (zzcuVar instanceof zzcw)) {
            return zzcuVar;
        }
        if (zzcuVar instanceof Serializable) {
            return new zzcw(zzcuVar);
        }
        return new zzcz(zzcuVar);
    }

    public static <T> zzcu<T> zzd(@NullableDecl T t) {
        return new zzcy(t);
    }
}
