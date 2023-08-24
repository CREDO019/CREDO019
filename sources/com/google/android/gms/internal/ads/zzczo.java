package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzczo implements zzfyk {
    final /* synthetic */ zzfyk zza;
    final /* synthetic */ zzczq zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzczo(zzczq zzczqVar, zzfyk zzfykVar) {
        this.zzb = zzczqVar;
        this.zza = zzfykVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        this.zza.zza(th);
        zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzczn
            @Override // java.lang.Runnable
            public final void run() {
                zzczq.this.zzd();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzczq.zzb(this.zzb, ((zzczj) obj).zza, this.zza);
    }
}
