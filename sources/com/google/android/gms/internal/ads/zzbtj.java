package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbtj implements zzchj {
    final /* synthetic */ zzbtl zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbtj(zzbtl zzbtlVar) {
        this.zza = zzbtlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzchj
    public final /* bridge */ /* synthetic */ void zza(Object obj) {
        zzbtq zzbtqVar;
        zzbts zzbtsVar = (zzbts) obj;
        com.google.android.gms.ads.internal.util.zze.zza("Releasing engine reference.");
        zzbtqVar = this.zza.zzb;
        zzbtqVar.zzd();
    }
}
