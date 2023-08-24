package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcnf implements Runnable {
    final /* synthetic */ zzcng zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcnf(zzcng zzcngVar) {
        this.zza = zzcngVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        super/*android.webkit.WebView*/.destroy();
    }
}
