package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaor implements zzfni {
    final /* synthetic */ zzfmf zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaor(zzfmf zzfmfVar) {
        this.zza = zzfmfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfni
    public final void zza(int r4, long j) {
        this.zza.zzd(r4, System.currentTimeMillis() - j);
    }

    @Override // com.google.android.gms.internal.ads.zzfni
    public final void zzb(int r4, long j, String str) {
        this.zza.zze(r4, System.currentTimeMillis() - j, str);
    }
}
