package com.google.android.gms.ads.internal.util;

import android.content.Context;
import com.google.android.gms.internal.ads.zzcgs;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzby extends zzb {
    private final zzcgs zza;
    private final String zzb;

    public zzby(Context context, String str, String str2) {
        this.zza = new zzcgs(com.google.android.gms.ads.internal.zzt.zzq().zzc(context, str));
        this.zzb = str2;
    }

    @Override // com.google.android.gms.ads.internal.util.zzb
    public final void zza() {
        this.zza.zza(this.zzb);
    }
}
