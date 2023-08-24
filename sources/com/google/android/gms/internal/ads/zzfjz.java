package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfjz {
    public abstract void zzb(View view, zzfkf zzfkfVar, String str);

    public abstract void zzc();

    public abstract void zzd(View view);

    public abstract void zze();

    public static zzfjz zza(zzfka zzfkaVar, zzfkb zzfkbVar) {
        if (zzfjx.zzb()) {
            return new zzfkd(zzfkaVar, zzfkbVar);
        }
        throw new IllegalStateException("Method called before OM SDK activation");
    }
}
