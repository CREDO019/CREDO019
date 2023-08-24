package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbbd implements Runnable {
    final /* synthetic */ int zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ int zzc;
    final /* synthetic */ float zzd;
    final /* synthetic */ zzbbg zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbbd(zzbbg zzbbgVar, int r2, int r3, int r4, float f) {
        this.zze = zzbbgVar;
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = f;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbbh zzbbhVar;
        zzbbhVar = this.zze.zzb;
        zzbbhVar.zzo(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
