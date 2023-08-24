package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqg implements Runnable {
    final /* synthetic */ zzaqi zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaqg(zzaqi zzaqiVar) {
        this.zza = zzaqiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzf();
    }
}
