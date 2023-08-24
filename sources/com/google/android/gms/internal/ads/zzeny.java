package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeny implements zzeod {
    final /* synthetic */ zzenz zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeny(zzenz zzenzVar) {
        this.zza = zzenzVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeod
    public final void zza() {
        synchronized (this.zza) {
        }
    }

    @Override // com.google.android.gms.internal.ads.zzeod
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        synchronized (this.zza) {
            this.zza.zzc = ((zzczc) obj).zzl();
            ((zzczc) obj).zzW();
        }
    }
}
