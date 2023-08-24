package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzchw implements Runnable {
    final /* synthetic */ int zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ zzcia zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzchw(zzcia zzciaVar, int r2, int r3) {
        this.zzc = zzciaVar;
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcib zzcibVar;
        zzcib zzcibVar2;
        zzcia zzciaVar = this.zzc;
        zzcibVar = zzciaVar.zzq;
        if (zzcibVar != null) {
            zzcibVar2 = zzciaVar.zzq;
            zzcibVar2.zzj(this.zza, this.zzb);
        }
    }
}
