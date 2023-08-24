package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfku {
    private static zzfku zza;
    private float zzb = 0.0f;
    private final zzfkn zzc;
    private final zzfkl zzd;
    private zzfkm zze;
    private zzfko zzf;

    public zzfku(zzfkn zzfknVar, zzfkl zzfklVar) {
        this.zzc = zzfknVar;
        this.zzd = zzfklVar;
    }

    public static zzfku zzb() {
        if (zza == null) {
            zza = new zzfku(new zzfkn(), new zzfkl());
        }
        return zza;
    }

    public final float zza() {
        return this.zzb;
    }

    public final void zzc(Context context) {
        this.zze = new zzfkm(new Handler(), context, new zzfkk(), this, null);
    }

    public final void zzd(float f) {
        this.zzb = f;
        if (this.zzf == null) {
            this.zzf = zzfko.zza();
        }
        for (zzfkd zzfkdVar : this.zzf.zzb()) {
            zzfkdVar.zzg().zzh(f);
        }
    }

    public final void zze() {
        zzfkp.zza().zzd(this);
        zzfkp.zza().zzb();
        zzflq.zzd().zzi();
        this.zze.zza();
    }

    public final void zzf() {
        zzflq.zzd().zzj();
        zzfkp.zza().zzc();
        this.zze.zzb();
    }
}
