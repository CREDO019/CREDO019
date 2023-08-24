package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzckq implements Runnable {
    final /* synthetic */ zzckr zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzckq(zzckr zzckrVar) {
        this.zza = zzckrVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.google.android.gms.ads.internal.zzt.zzz().zzc(this.zza);
    }
}
