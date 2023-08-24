package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.core.content.ContextCompat;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeom implements zzeun {
    private final Context zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeom(Context context) {
        this.zza = context;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 2;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcr)).booleanValue()) {
            return zzfyo.zzi(new zzeon(ContextCompat.checkSelfPermission(this.zza, "com.google.android.gms.permission.AD_ID") == 0));
        }
        return zzfyo.zzi(null);
    }
}
