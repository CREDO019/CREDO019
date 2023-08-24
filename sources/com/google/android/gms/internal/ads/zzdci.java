package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdci {
    private Context zza;
    private zzfdn zzb;
    private Bundle zzc;
    private zzfdf zzd;

    public final zzdci zzc(Context context) {
        this.zza = context;
        return this;
    }

    public final zzdci zzd(Bundle bundle) {
        this.zzc = bundle;
        return this;
    }

    public final zzdci zze(zzfdf zzfdfVar) {
        this.zzd = zzfdfVar;
        return this;
    }

    public final zzdci zzf(zzfdn zzfdnVar) {
        this.zzb = zzfdnVar;
        return this;
    }

    public final zzdck zzg() {
        return new zzdck(this, null);
    }
}
