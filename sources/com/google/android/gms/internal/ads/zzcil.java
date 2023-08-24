package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcil {
    private final Context zza;
    private final zzciw zzb;
    private final ViewGroup zzc;
    private zzcik zzd;

    public zzcil(Context context, ViewGroup viewGroup, zzcmn zzcmnVar) {
        this.zza = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzc = viewGroup;
        this.zzb = zzcmnVar;
        this.zzd = null;
    }

    public final zzcik zza() {
        return this.zzd;
    }

    public final void zzb(int r2, int r3, int r4, int r5) {
        Preconditions.checkMainThread("The underlay may only be modified from the UI thread.");
        zzcik zzcikVar = this.zzd;
        if (zzcikVar != null) {
            zzcikVar.zzE(r2, r3, r4, r5);
        }
    }

    public final void zzc(int r12, int r13, int r14, int r15, int r16, boolean z, zzciv zzcivVar) {
        if (this.zzd != null) {
            return;
        }
        zzbjf.zza(this.zzb.zzo().zza(), this.zzb.zzn(), "vpr2");
        Context context = this.zza;
        zzciw zzciwVar = this.zzb;
        zzcik zzcikVar = new zzcik(context, zzciwVar, r16, z, zzciwVar.zzo().zza(), zzcivVar);
        this.zzd = zzcikVar;
        this.zzc.addView(zzcikVar, 0, new ViewGroup.LayoutParams(-1, -1));
        this.zzd.zzE(r12, r13, r14, r15);
        this.zzb.zzB(false);
    }

    public final void zzd() {
        Preconditions.checkMainThread("onDestroy must be called from the UI thread.");
        zzcik zzcikVar = this.zzd;
        if (zzcikVar != null) {
            zzcikVar.zzn();
            this.zzc.removeView(this.zzd);
            this.zzd = null;
        }
    }

    public final void zze() {
        Preconditions.checkMainThread("onPause must be called from the UI thread.");
        zzcik zzcikVar = this.zzd;
        if (zzcikVar != null) {
            zzcikVar.zzt();
        }
    }

    public final void zzf(int r2) {
        zzcik zzcikVar = this.zzd;
        if (zzcikVar != null) {
            zzcikVar.zzB(r2);
        }
    }
}
