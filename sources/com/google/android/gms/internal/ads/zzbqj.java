package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.p013h5.OnH5AdsEventListener;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbqj extends zzbql {
    private final OnH5AdsEventListener zza;

    public zzbqj(OnH5AdsEventListener onH5AdsEventListener) {
        this.zza = onH5AdsEventListener;
    }

    @Override // com.google.android.gms.internal.ads.zzbqm
    public final void zzb(String str) {
        this.zza.onH5AdsEvent(str);
    }
}
