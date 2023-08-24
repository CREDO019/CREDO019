package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcwj extends zzczc {
    private final View zzc;
    private final zzcmn zzd;
    private final zzfct zze;
    private final int zzf;
    private final boolean zzg;
    private final boolean zzh;
    private final zzcwb zzi;
    private zzbdj zzj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcwj(zzczb zzczbVar, View view, zzcmn zzcmnVar, zzfct zzfctVar, int r5, boolean z, boolean z2, zzcwb zzcwbVar) {
        super(zzczbVar);
        this.zzc = view;
        this.zzd = zzcmnVar;
        this.zze = zzfctVar;
        this.zzf = r5;
        this.zzg = z;
        this.zzh = z2;
        this.zzi = zzcwbVar;
    }

    public final int zza() {
        return this.zzf;
    }

    public final View zzb() {
        return this.zzc;
    }

    public final zzfct zzc() {
        return zzfdr.zzb(this.zzb.zzs, this.zze);
    }

    public final void zzd(zzbcz zzbczVar) {
        this.zzd.zzaj(zzbczVar);
    }

    public final boolean zze() {
        return this.zzg;
    }

    public final boolean zzf() {
        return this.zzh;
    }

    public final boolean zzg() {
        return this.zzd.zzay();
    }

    public final boolean zzh() {
        return this.zzd.zzP() != null && this.zzd.zzP().zzJ();
    }

    public final void zzi(long j, int r4) {
        this.zzi.zza(j, r4);
    }

    public final zzbdj zzj() {
        return this.zzj;
    }

    public final void zzk(zzbdj zzbdjVar) {
        this.zzj = zzbdjVar;
    }
}
