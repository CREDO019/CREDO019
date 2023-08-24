package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzchy implements Runnable {
    final /* synthetic */ zzcia zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzchy(zzcia zzciaVar) {
        this.zza = zzciaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcib zzcibVar;
        boolean z;
        zzcib zzcibVar2;
        zzcib zzcibVar3;
        zzcia zzciaVar = this.zza;
        zzcibVar = zzciaVar.zzq;
        if (zzcibVar != null) {
            z = zzciaVar.zzr;
            if (!z) {
                zzcibVar3 = zzciaVar.zzq;
                zzcibVar3.zzg();
                this.zza.zzr = true;
            }
            zzcibVar2 = this.zza.zzq;
            zzcibVar2.zze();
        }
    }
}
