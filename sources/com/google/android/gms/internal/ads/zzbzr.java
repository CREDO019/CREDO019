package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.ads.AdFormat;
import com.google.android.gms.ads.query.QueryInfoGenerationCallback;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbzr {
    private static zzcfe zza;
    private final Context zzb;
    private final AdFormat zzc;
    private final com.google.android.gms.ads.internal.client.zzdr zzd;

    public zzbzr(Context context, AdFormat adFormat, com.google.android.gms.ads.internal.client.zzdr zzdrVar) {
        this.zzb = context;
        this.zzc = adFormat;
        this.zzd = zzdrVar;
    }

    public static zzcfe zza(Context context) {
        zzcfe zzcfeVar;
        synchronized (zzbzr.class) {
            if (zza == null) {
                zza = com.google.android.gms.ads.internal.client.zzaw.zza().zzq(context, new zzbvc());
            }
            zzcfeVar = zza;
        }
        return zzcfeVar;
    }

    public final void zzb(QueryInfoGenerationCallback queryInfoGenerationCallback) {
        com.google.android.gms.ads.internal.client.zzl zza2;
        zzcfe zza3 = zza(this.zzb);
        if (zza3 == null) {
            queryInfoGenerationCallback.onFailure("Internal Error, query info generator is null.");
            return;
        }
        IObjectWrapper wrap = ObjectWrapper.wrap(this.zzb);
        com.google.android.gms.ads.internal.client.zzdr zzdrVar = this.zzd;
        if (zzdrVar != null) {
            zza2 = com.google.android.gms.ads.internal.client.zzp.zza.zza(this.zzb, zzdrVar);
        } else {
            zza2 = new com.google.android.gms.ads.internal.client.zzm().zza();
        }
        try {
            zza3.zze(wrap, new zzcfi(null, this.zzc.name(), null, zza2), new zzbzq(this, queryInfoGenerationCallback));
        } catch (RemoteException unused) {
            queryInfoGenerationCallback.onFailure("Internal Error.");
        }
    }
}
