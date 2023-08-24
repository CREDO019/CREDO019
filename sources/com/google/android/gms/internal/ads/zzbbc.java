package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbbc implements Runnable {
    final /* synthetic */ int zza;
    final /* synthetic */ long zzb;
    final /* synthetic */ zzbbg zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbbc(zzbbg zzbbgVar, int r2, long j) {
        this.zzc = zzbbgVar;
        this.zza = r2;
        this.zzb = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbbh zzbbhVar;
        zzbbhVar = this.zzc.zzb;
        zzbbhVar.zzl(this.zza, this.zzb);
    }
}
