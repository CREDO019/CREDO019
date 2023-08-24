package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzchu implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzcia zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzchu(zzcia zzciaVar, String str, String str2) {
        this.zzc = zzciaVar;
        this.zza = str;
        this.zzb = str2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcib zzcibVar;
        zzcib zzcibVar2;
        zzcia zzciaVar = this.zzc;
        zzcibVar = zzciaVar.zzq;
        if (zzcibVar != null) {
            zzcibVar2 = zzciaVar.zzq;
            zzcibVar2.zzb(this.zza, this.zzb);
        }
    }
}
