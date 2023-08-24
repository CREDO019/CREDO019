package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdyy extends com.google.android.gms.ads.internal.client.zzbe {
    final /* synthetic */ zzdys zza;
    final /* synthetic */ zzdyz zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdyy(zzdyz zzdyzVar, zzdys zzdysVar) {
        this.zzb = zzdyzVar;
        this.zza = zzdysVar;
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzc() throws RemoteException {
        long j;
        zzdys zzdysVar = this.zza;
        j = this.zzb.zza;
        zzdysVar.zzb(j);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzd() throws RemoteException {
        long j;
        zzdys zzdysVar = this.zza;
        j = this.zzb.zza;
        zzdysVar.zzc(j);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zze(int r4) throws RemoteException {
        long j;
        zzdys zzdysVar = this.zza;
        j = this.zzb.zza;
        zzdysVar.zzd(j, r4);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzf(com.google.android.gms.ads.internal.client.zze zzeVar) throws RemoteException {
        long j;
        zzdys zzdysVar = this.zza;
        j = this.zzb.zza;
        zzdysVar.zzd(j, zzeVar.zza);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzg() {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzh() {
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzi() throws RemoteException {
        long j;
        zzdys zzdysVar = this.zza;
        j = this.zzb.zza;
        zzdysVar.zze(j);
    }

    @Override // com.google.android.gms.ads.internal.client.zzbf
    public final void zzj() throws RemoteException {
        long j;
        zzdys zzdysVar = this.zza;
        j = this.zzb.zza;
        zzdysVar.zzg(j);
    }
}
