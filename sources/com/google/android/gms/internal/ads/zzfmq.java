package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Looper;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfmq {
    private final Context zza;
    private final Looper zzb;

    public zzfmq(Context context, Looper looper) {
        this.zza = context;
        this.zzb = looper;
    }

    public final void zza(String str) {
        zzfne zza = zzfng.zza();
        zza.zza(this.zza.getPackageName());
        zza.zzc(2);
        zzfnb zza2 = zzfnc.zza();
        zza2.zza(str);
        zza2.zzb(2);
        zza.zzb(zza2);
        new zzfmr(this.zza, this.zzb, (zzfng) zza.zzal()).zza();
    }
}
