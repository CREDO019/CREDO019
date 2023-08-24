package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.AdSize;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public class MediationBannerAdConfiguration extends MediationAdConfiguration {
    private final AdSize zza;

    public MediationBannerAdConfiguration(Context context, String str, Bundle bundle, Bundle bundle2, boolean z, Location location, int r18, int r19, String str2, AdSize adSize, String str3) {
        super(context, str, bundle, bundle2, z, location, r18, r19, str2, str3);
        this.zza = adSize;
    }

    public AdSize getAdSize() {
        return this.zza;
    }
}
