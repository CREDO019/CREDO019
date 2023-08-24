package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbbb implements Runnable {
    final /* synthetic */ zzass zza;
    final /* synthetic */ zzbbg zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbbb(zzbbg zzbbgVar, zzass zzassVar) {
        this.zzb = zzbbgVar;
        this.zza = zzassVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbbh zzbbhVar;
        zzbbhVar = this.zzb.zzb;
        zzbbhVar.zzn(this.zza);
    }
}
