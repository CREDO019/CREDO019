package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzchz implements Runnable {
    final /* synthetic */ zzcia zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzchz(zzcia zzciaVar) {
        this.zza = zzciaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcib zzcibVar;
        zzcib zzcibVar2;
        zzcia zzciaVar = this.zza;
        zzcibVar = zzciaVar.zzq;
        if (zzcibVar != null) {
            zzcibVar2 = zzciaVar.zzq;
            zzcibVar2.zzd();
        }
    }
}
