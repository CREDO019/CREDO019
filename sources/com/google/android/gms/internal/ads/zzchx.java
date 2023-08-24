package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzchx implements Runnable {
    final /* synthetic */ zzcia zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzchx(zzcia zzciaVar) {
        this.zza = zzciaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcib zzcibVar;
        zzcib zzcibVar2;
        zzcib zzcibVar3;
        zzcia zzciaVar = this.zza;
        zzcibVar = zzciaVar.zzq;
        if (zzcibVar != null) {
            zzcibVar2 = zzciaVar.zzq;
            zzcibVar2.zzd();
            zzcibVar3 = this.zza.zzq;
            zzcibVar3.zzi();
        }
    }
}
