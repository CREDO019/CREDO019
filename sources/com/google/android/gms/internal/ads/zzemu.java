package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzemu implements zzeod {
    final /* synthetic */ zzemv zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzemu(zzemv zzemvVar) {
        this.zza = zzemvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeod
    public final void zza() {
        synchronized (this.zza) {
            this.zza.zzh = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzeod
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzcxa zzcxaVar;
        zzcxa zzcxaVar2;
        zzcxa zzcxaVar3;
        zzcxa zzcxaVar4 = (zzcxa) obj;
        synchronized (this.zza) {
            zzemv zzemvVar = this.zza;
            zzcxaVar = zzemvVar.zzh;
            if (zzcxaVar != null) {
                zzcxaVar3 = zzemvVar.zzh;
                zzcxaVar3.zzV();
            }
            this.zza.zzh = zzcxaVar4;
            zzcxaVar2 = this.zza.zzh;
            zzcxaVar2.zzW();
        }
    }
}
