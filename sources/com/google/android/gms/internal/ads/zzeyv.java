package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzeyv implements zzeod {
    final /* synthetic */ zzeyw zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeyv(zzeyw zzeywVar) {
        this.zza = zzeywVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeod
    public final void zza() {
        synchronized (this.zza) {
            this.zza.zza = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzeod
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzeyo zzeyoVar;
        zzeyo zzeyoVar2;
        zzcwt zzcwtVar = (zzcwt) obj;
        synchronized (this.zza) {
            zzcwt zzcwtVar2 = this.zza.zza;
            if (zzcwtVar2 != null) {
                zzcwtVar2.zzV();
            }
            zzeyw zzeywVar = this.zza;
            zzeywVar.zza = zzcwtVar;
            zzcwtVar.zzc(zzeywVar);
            zzeyw zzeywVar2 = this.zza;
            zzeyoVar = zzeywVar2.zzg;
            zzeyoVar2 = zzeywVar2.zzg;
            zzeyoVar.zzl(new zzcwu(zzcwtVar, zzeywVar2, zzeyoVar2));
            zzcwtVar.zzW();
        }
    }
}
