package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.internal.ads.zzblo;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public class MediationNativeAdConfiguration extends MediationAdConfiguration {
    private final zzblo zza;

    public MediationNativeAdConfiguration(Context context, String str, Bundle bundle, Bundle bundle2, boolean z, Location location, int r7, int r8, String str2, String str3, zzblo zzbloVar) {
        super(context, str, bundle, bundle2, z, location, r7, r8, str2, str3);
        this.zza = zzbloVar;
    }

    public NativeAdOptions getNativeAdOptions() {
        return zzblo.zza(this.zza);
    }
}
