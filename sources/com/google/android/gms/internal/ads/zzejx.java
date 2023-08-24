package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzejx {
    private final Clock zza;
    private final zzejy zzb;
    private final zzfju zzc;
    private final List zzd = Collections.synchronizedList(new ArrayList());
    private final boolean zze = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfS)).booleanValue();
    private final zzegp zzf;

    public zzejx(Clock clock, zzejy zzejyVar, zzegp zzegpVar, zzfju zzfjuVar) {
        this.zza = clock;
        this.zzb = zzejyVar;
        this.zzf = zzegpVar;
        this.zzc = zzfjuVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzg(zzejx zzejxVar, String str, int r3, long j, String str2) {
        String str3 = str + "." + r3 + "." + j;
        if (!TextUtils.isEmpty(str2)) {
            str3 = str3 + "." + str2;
        }
        zzejxVar.zzd.add(str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzfyx zze(zzfde zzfdeVar, zzfcs zzfcsVar, zzfyx zzfyxVar, zzfjq zzfjqVar) {
        zzfcv zzfcvVar = zzfdeVar.zzb.zzb;
        long elapsedRealtime = this.zza.elapsedRealtime();
        String str = zzfcsVar.zzx;
        if (str != null) {
            zzfyo.zzr(zzfyxVar, new zzejw(this, elapsedRealtime, str, zzfcsVar, zzfcvVar, zzfjqVar, zzfdeVar), zzcha.zzf);
        }
        return zzfyxVar;
    }

    public final String zzf() {
        return TextUtils.join("_", this.zzd);
    }
}
