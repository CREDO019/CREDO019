package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfci implements zzeod {
    final /* synthetic */ zzfcj zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfci(zzfcj zzfcjVar) {
        this.zza = zzfcjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeod
    public final void zza() {
        synchronized (this.zza) {
            this.zza.zzg = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzeod
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzdua zzduaVar;
        zzfdf zzfdfVar;
        zzdua zzduaVar2 = (zzdua) obj;
        synchronized (this.zza) {
            this.zza.zzg = zzduaVar2;
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcG)).booleanValue()) {
                zzfdg zzd = zzduaVar2.zzd();
                zzfdfVar = this.zza.zzd;
                zzd.zza = zzfdfVar;
            }
            zzduaVar = this.zza.zzg;
            zzduaVar.zzW();
        }
    }
}
