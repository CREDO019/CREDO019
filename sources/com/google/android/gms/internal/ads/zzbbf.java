package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbbf implements Runnable {
    final /* synthetic */ zzaum zza;
    final /* synthetic */ zzbbg zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbbf(zzbbg zzbbgVar, zzaum zzaumVar) {
        this.zzb = zzbbgVar;
        this.zza = zzaumVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza();
    }
}
