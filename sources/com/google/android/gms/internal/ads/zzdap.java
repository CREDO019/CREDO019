package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdap implements zzdem, com.google.android.gms.ads.internal.client.zza, zzdft, zzdds, zzdcy, zzdie {
    private final Clock zza;
    private final zzcfp zzb;

    public zzdap(Clock clock, zzcfp zzcfpVar) {
        this.zza = clock;
        this.zzb = zzcfpVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zza
    public final void onAdClicked() {
        this.zzb.zzd();
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzb(zzfde zzfdeVar) {
        this.zzb.zzk(this.zza.elapsedRealtime());
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzbE(zzcba zzcbaVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzbv() {
    }

    public final String zzc() {
        return this.zzb.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzdie
    public final void zzd() {
    }

    @Override // com.google.android.gms.internal.ads.zzdie
    public final void zze(zzbfg zzbfgVar) {
        this.zzb.zzi();
    }

    @Override // com.google.android.gms.internal.ads.zzdie
    public final void zzf(zzbfg zzbfgVar) {
    }

    public final void zzg(com.google.android.gms.ads.internal.client.zzl zzlVar) {
        this.zzb.zzj(zzlVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdie
    public final void zzh(boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzdie
    public final void zzi(zzbfg zzbfgVar) {
        this.zzb.zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzj() {
        this.zzb.zze();
    }

    @Override // com.google.android.gms.internal.ads.zzdie
    public final void zzk(boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzdds
    public final void zzl() {
        this.zzb.zzf();
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzm() {
    }

    @Override // com.google.android.gms.internal.ads.zzdem
    public final void zzn() {
        this.zzb.zzh(true);
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzo() {
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzp(zzcbq zzcbqVar, String str, String str2) {
    }

    @Override // com.google.android.gms.internal.ads.zzdcy
    public final void zzr() {
    }
}
