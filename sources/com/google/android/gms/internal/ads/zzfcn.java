package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfcn implements zzeod {
    final /* synthetic */ zzfcp zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfcn(zzfcp zzfcpVar) {
        this.zza = zzfcpVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeod
    public final void zza() {
        synchronized (this.zza) {
            this.zza.zzd = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzeod
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzdua zzduaVar;
        zzfdf zzfdfVar;
        zzdua zzduaVar2 = (zzdua) obj;
        synchronized (this.zza) {
            this.zza.zzd = zzduaVar2;
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcG)).booleanValue()) {
                zzfdg zzd = zzduaVar2.zzd();
                zzfdfVar = this.zza.zzc;
                zzd.zza = zzfdfVar;
            }
            zzduaVar = this.zza.zzd;
            zzduaVar.zzW();
        }
    }
}
