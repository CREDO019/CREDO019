package com.google.android.gms.internal.consent_sdk;

import android.app.Application;
import android.content.Context;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.0.0 */
/* loaded from: classes.dex */
public abstract class zzd {
    private static zzd zza;

    public static zzd zza(Context context) {
        zzd zzdVar;
        synchronized (zzd.class) {
            if (zza == null) {
                zzag zzagVar = new zzag(null);
                zzagVar.zzb((Application) context.getApplicationContext());
                zza = zzagVar.zza();
            }
            zzdVar = zza;
        }
        return zzdVar;
    }

    public abstract zzk zzb();

    public abstract zzba zzc();
}
