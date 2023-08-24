package com.google.android.gms.internal.ads;

import android.os.Binder;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeaw {
    private final zzfyy zza;
    private final zzfyy zzb;
    private final zzecd zzc;
    private final zzgul zzd;

    public zzeaw(zzfyy zzfyyVar, zzfyy zzfyyVar2, zzecd zzecdVar, zzgul zzgulVar) {
        this.zza = zzfyyVar;
        this.zzb = zzfyyVar2;
        this.zzc = zzecdVar;
        this.zzd = zzgulVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zza(zzcba zzcbaVar, int r2, zzecu zzecuVar) throws Exception {
        return ((zzedv) this.zzd.zzb()).zzc(zzcbaVar, r2);
    }

    public final zzfyx zzb(final zzcba zzcbaVar) {
        zzfyx zzg;
        String str = zzcbaVar.zzd;
        com.google.android.gms.ads.internal.zzt.zzq();
        if (com.google.android.gms.ads.internal.util.zzs.zzy(str)) {
            zzg = zzfyo.zzh(new zzecu(1));
        } else {
            zzg = zzfyo.zzg(this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzeat
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return zzeaw.this.zzc(zzcbaVar);
                }
            }), ExecutionException.class, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzeau
                @Override // com.google.android.gms.internal.ads.zzfxv
                public final zzfyx zza(Object obj) {
                    return zzfyo.zzh(((ExecutionException) obj).getCause());
                }
            }, this.zzb);
        }
        final int callingUid = Binder.getCallingUid();
        return zzfyo.zzg(zzg, zzecu.class, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzeav
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzeaw.this.zza(zzcbaVar, callingUid, (zzecu) obj);
            }
        }, this.zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ InputStream zzc(zzcba zzcbaVar) throws Exception {
        zzchf zzchfVar;
        final zzecd zzecdVar = this.zzc;
        synchronized (zzecdVar.zzb) {
            if (zzecdVar.zzc) {
                zzchfVar = zzecdVar.zza;
            } else {
                zzecdVar.zzc = true;
                zzecdVar.zze = zzcbaVar;
                zzecdVar.zzf.checkAvailabilityAndConnect();
                zzecdVar.zza.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzecc
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzecd.this.zza();
                    }
                }, zzcha.zzf);
                zzchfVar = zzecdVar.zza;
            }
        }
        return (InputStream) zzchfVar.get(((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeB)).intValue(), TimeUnit.SECONDS);
    }
}
