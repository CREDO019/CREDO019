package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.AdError;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzelm extends zzbvk {
    private final zzdcw zza;
    private final zzdkj zzb;
    private final zzddq zzc;
    private final zzdef zzd;
    private final zzdek zze;
    private final zzdhr zzf;
    private final zzdfe zzg;
    private final zzdlb zzh;
    private final zzdhn zzi;
    private final zzddl zzj;

    public zzelm(zzdcw zzdcwVar, zzdkj zzdkjVar, zzddq zzddqVar, zzdef zzdefVar, zzdek zzdekVar, zzdhr zzdhrVar, zzdfe zzdfeVar, zzdlb zzdlbVar, zzdhn zzdhnVar, zzddl zzddlVar) {
        this.zza = zzdcwVar;
        this.zzb = zzdkjVar;
        this.zzc = zzddqVar;
        this.zzd = zzdefVar;
        this.zze = zzdekVar;
        this.zzf = zzdhrVar;
        this.zzg = zzdfeVar;
        this.zzh = zzdlbVar;
        this.zzi = zzdhnVar;
        this.zzj = zzddlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zze() {
        this.zza.onAdClicked();
        this.zzb.zzq();
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzf() {
        this.zzg.zzf(4);
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzg(int r1) {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzh(com.google.android.gms.ads.internal.client.zze zzeVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzi(int r1, String str) {
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    @Deprecated
    public final void zzj(int r8) throws RemoteException {
        zzk(new com.google.android.gms.ads.internal.client.zze(r8, "", AdError.UNDEFINED_DOMAIN, null, null));
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzk(com.google.android.gms.ads.internal.client.zze zzeVar) {
        this.zzj.zza(zzfem.zzc(8, zzeVar));
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzl(String str) {
        zzk(new com.google.android.gms.ads.internal.client.zze(0, str, AdError.UNDEFINED_DOMAIN, null, null));
    }

    public void zzm() {
        this.zzc.zza();
        this.zzi.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzn() {
        this.zzd.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzo() {
        this.zze.zzn();
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzp() {
        this.zzg.zzb();
        this.zzi.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzq(String str, String str2) {
        this.zzf.zzbD(str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzr(zzbmu zzbmuVar, String str) {
    }

    public void zzs(zzccc zzcccVar) {
    }

    public void zzt(zzccg zzccgVar) throws RemoteException {
    }

    public void zzu() throws RemoteException {
    }

    public void zzv() {
        this.zzh.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzw() {
        this.zzh.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzbvl
    public final void zzx() throws RemoteException {
        this.zzh.zzc();
    }

    public void zzy() {
        this.zzh.zzd();
    }
}
