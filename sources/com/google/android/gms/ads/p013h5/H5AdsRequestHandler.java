package com.google.android.gms.ads.p013h5;

import android.content.Context;
import com.google.android.gms.internal.ads.zzbqt;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* renamed from: com.google.android.gms.ads.h5.H5AdsRequestHandler */
/* loaded from: classes2.dex */
public final class H5AdsRequestHandler {
    private final zzbqt zza;

    public H5AdsRequestHandler(Context context, OnH5AdsEventListener onH5AdsEventListener) {
        this.zza = new zzbqt(context, onH5AdsEventListener);
    }

    public void clearAdObjects() {
        this.zza.zza();
    }

    public boolean handleH5AdsRequest(String str) {
        return this.zza.zzb(str);
    }

    public boolean shouldInterceptRequest(String str) {
        return zzbqt.zzc(str);
    }
}
