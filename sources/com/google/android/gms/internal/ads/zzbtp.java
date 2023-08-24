package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbtp implements zzchj {
    final /* synthetic */ zzbtq zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbtp(zzbtq zzbtqVar) {
        this.zza = zzbtqVar;
    }

    @Override // com.google.android.gms.internal.ads.zzchj
    public final /* bridge */ /* synthetic */ void zza(Object obj) {
        final zzbsm zzbsmVar = (zzbsm) obj;
        zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbto
            @Override // java.lang.Runnable
            public final void run() {
                zzbsm zzbsmVar2 = zzbsmVar;
                zzbsmVar2.zzr("/result", zzbpp.zzo);
                zzbsmVar2.zzc();
            }
        });
    }
}
