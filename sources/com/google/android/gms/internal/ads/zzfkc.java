package com.google.android.gms.internal.ads;

import com.onesignal.OSInAppMessageContentKt;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public enum zzfkc {
    HTML(OSInAppMessageContentKt.HTML),
    NATIVE("native"),
    JAVASCRIPT("javascript");
    
    private final String zze;

    zzfkc(String str) {
        this.zze = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.zze;
    }
}
