package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import com.amplitude.api.Constants;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeuw implements zzeum {
    public final String zza;
    public final int zzb;
    public final int zzc;
    public final int zzd;
    public final boolean zze;
    public final int zzf;

    public zzeuw(String str, int r2, int r3, int r4, boolean z, int r6) {
        this.zza = str;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = z;
        this.zzf = r6;
    }

    @Override // com.google.android.gms.internal.ads.zzeum
    public final /* bridge */ /* synthetic */ void zzf(Object obj) {
        Bundle bundle = (Bundle) obj;
        String str = this.zza;
        zzfdy.zzg(bundle, Constants.AMP_TRACKING_OPTION_CARRIER, str, !TextUtils.isEmpty(str));
        zzfdy.zzf(bundle, "cnt", Integer.valueOf(this.zzb), this.zzb != -2);
        bundle.putInt("gnt", this.zzc);
        bundle.putInt("pt", this.zzd);
        Bundle zza = zzfdy.zza(bundle, "device");
        bundle.putBundle("device", zza);
        Bundle zza2 = zzfdy.zza(zza, "network");
        zza.putBundle("network", zza2);
        zza2.putInt("active_network_state", this.zzf);
        zza2.putBoolean("active_network_metered", this.zze);
    }
}
