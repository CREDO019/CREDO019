package com.google.android.gms.ads.internal.overlay;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzf extends Exception {
    public zzf(String str) {
        super(str);
    }

    public zzf(String str, Throwable th) {
        super("Could not obtain webview for the overlay.", th);
    }
}
