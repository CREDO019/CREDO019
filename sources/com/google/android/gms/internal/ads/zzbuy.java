package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbuy {
    private static zzbuy zza;
    private final AtomicBoolean zzb = new AtomicBoolean(false);

    zzbuy() {
    }

    public static zzbuy zza() {
        if (zza == null) {
            zza = new zzbuy();
        }
        return zza;
    }

    public final Thread zzb(final Context context, final String str) {
        if (this.zzb.compareAndSet(false, true)) {
            Thread thread = new Thread(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbux
                @Override // java.lang.Runnable
                public final void run() {
                    Context context2 = context;
                    String str2 = str;
                    zzbiy.zzc(context2);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("measurementEnabled", ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaf)).booleanValue());
                    if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzam)).booleanValue()) {
                        bundle.putString("ad_storage", "denied");
                        bundle.putString("analytics_storage", "denied");
                    }
                    try {
                        ((zzcoh) zzcgr.zzb(context2, "com.google.android.gms.ads.measurement.DynamiteMeasurementManager", new zzcgp() { // from class: com.google.android.gms.internal.ads.zzbuw
                            /* JADX WARN: Multi-variable type inference failed */
                            @Override // com.google.android.gms.internal.ads.zzcgp
                            public final Object zza(Object obj) {
                                return zzcog.zzb(obj);
                            }
                        })).zze(ObjectWrapper.wrap(context2), new zzbuv(AppMeasurementSdk.getInstance(context2, "FA-Ads", "am", str2, bundle)));
                    } catch (RemoteException | zzcgq | NullPointerException e) {
                        zzcgn.zzl("#007 Could not call remote method.", e);
                    }
                }
            });
            thread.start();
            return thread;
        }
        return null;
    }
}
