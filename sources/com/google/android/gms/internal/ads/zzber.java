package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzber {
    zzasa zza;
    boolean zzb;
    private final ExecutorService zzc;

    public zzber() {
        this.zzc = zzcgc.zzb;
    }

    public static /* bridge */ /* synthetic */ ExecutorService zza(zzber zzberVar) {
        return zzberVar.zzc;
    }

    /* renamed from: zzc */
    public final void zzb(Context context) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdX)).booleanValue()) {
            try {
                this.zza = (zzasa) zzcgr.zzb(context, "com.google.android.gms.ads.clearcut.DynamiteClearcutLogger", new zzcgp() { // from class: com.google.android.gms.internal.ads.zzben
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.google.android.gms.internal.ads.zzcgp
                    public final Object zza(Object obj) {
                        return zzarz.zzb(obj);
                    }
                });
                this.zza.zze(ObjectWrapper.wrap(context), "GMA_SDK");
                this.zzb = true;
            } catch (RemoteException | zzcgq | NullPointerException unused) {
                zzcgn.zze("Cannot dynamite load clearcut");
            }
        }
    }

    public zzber(final Context context) {
        ExecutorService executorService = zzcgc.zzb;
        this.zzc = executorService;
        zzbiy.zzc(context);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziA)).booleanValue()) {
            executorService.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbem
                @Override // java.lang.Runnable
                public final void run() {
                    zzber.this.zzb(context);
                }
            });
        } else {
            zzb(context);
        }
    }
}
