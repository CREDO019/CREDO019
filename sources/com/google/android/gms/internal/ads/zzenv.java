package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzenv implements zzeod {
    final /* synthetic */ zzenw zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzenv(zzenw zzenwVar) {
        this.zza = zzenwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeod
    public final void zza() {
        synchronized (this.zza) {
            this.zza.zzh = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzeod
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzdle zzdleVar;
        zzdle zzdleVar2 = (zzdle) obj;
        synchronized (this.zza) {
            this.zza.zzh = zzdleVar2;
            zzdleVar = this.zza.zzh;
            zzdleVar.zzW();
        }
    }
}
