package com.google.android.gms.internal.ads;

import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzbtw {
    private final Object zza = new Object();
    private final Object zzb = new Object();
    private zzbuf zzc;
    private zzbuf zzd;

    private static final Context zzc(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }

    public final zzbuf zza(Context context, zzcgt zzcgtVar, zzfje zzfjeVar) {
        zzbuf zzbufVar;
        synchronized (this.zza) {
            if (this.zzc == null) {
                this.zzc = new zzbuf(zzc(context), zzcgtVar, (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zza), zzfjeVar);
            }
            zzbufVar = this.zzc;
        }
        return zzbufVar;
    }

    public final zzbuf zzb(Context context, zzcgt zzcgtVar, zzfje zzfjeVar) {
        zzbuf zzbufVar;
        synchronized (this.zzb) {
            if (this.zzd == null) {
                this.zzd = new zzbuf(zzc(context), zzcgtVar, (String) zzbkx.zzb.zze(), zzfjeVar);
            }
            zzbufVar = this.zzd;
        }
        return zzbufVar;
    }
}
