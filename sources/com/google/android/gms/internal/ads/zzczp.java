package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzczp implements zzfyk {
    final /* synthetic */ zzfyk zza;
    final /* synthetic */ zzczq zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzczp(zzczq zzczqVar, zzfyk zzfykVar) {
        this.zzb = zzczqVar;
        this.zza = zzfykVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzczn
            @Override // java.lang.Runnable
            public final void run() {
                zzczq.this.zzd();
            }
        });
        this.zza.zza(th);
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzczn
            @Override // java.lang.Runnable
            public final void run() {
                zzczq.this.zzd();
            }
        });
        this.zza.zzb((zzczc) obj);
    }
}
