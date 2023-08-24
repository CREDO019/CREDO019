package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzekl extends zzelm {
    private final zzdku zza;

    public zzekl(zzdcw zzdcwVar, zzdkj zzdkjVar, zzddq zzddqVar, zzdef zzdefVar, zzdek zzdekVar, zzddl zzddlVar, zzdhr zzdhrVar, zzdlb zzdlbVar, zzdfe zzdfeVar, zzdku zzdkuVar, zzdhn zzdhnVar) {
        super(zzdcwVar, zzdkjVar, zzddqVar, zzdefVar, zzdekVar, zzdhrVar, zzdfeVar, zzdlbVar, zzdhnVar, zzddlVar);
        this.zza = zzdkuVar;
    }

    @Override // com.google.android.gms.internal.ads.zzelm, com.google.android.gms.internal.ads.zzbvl
    public final void zzs(zzccc zzcccVar) {
        this.zza.zza(zzcccVar);
    }

    @Override // com.google.android.gms.internal.ads.zzelm, com.google.android.gms.internal.ads.zzbvl
    public final void zzt(zzccg zzccgVar) throws RemoteException {
        this.zza.zza(new zzccc(zzccgVar.zzf(), zzccgVar.zze()));
    }

    @Override // com.google.android.gms.internal.ads.zzelm, com.google.android.gms.internal.ads.zzbvl
    public final void zzu() throws RemoteException {
        this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzelm, com.google.android.gms.internal.ads.zzbvl
    public final void zzv() {
        this.zza.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzelm, com.google.android.gms.internal.ads.zzbvl
    public final void zzy() {
        this.zza.zzc();
    }
}
