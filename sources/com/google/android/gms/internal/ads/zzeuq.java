package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeuq {
    private final Context zza;
    private final Set zzb;
    private final Executor zzc;
    private final zzfjc zzd;
    private final zzdxo zze;

    public zzeuq(Context context, Executor executor, Set set, zzfjc zzfjcVar, zzdxo zzdxoVar) {
        this.zza = context;
        this.zzc = executor;
        this.zzb = set;
        this.zzd = zzfjcVar;
        this.zze = zzdxoVar;
    }

    public final zzfyx zza(final Object obj) {
        zzfir zza = zzfiq.zza(this.zza, 8);
        zza.zzf();
        final ArrayList arrayList = new ArrayList(this.zzb.size());
        for (final zzeun zzeunVar : this.zzb) {
            zzfyx zzb = zzeunVar.zzb();
            zzb.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzeuo
                @Override // java.lang.Runnable
                public final void run() {
                    zzeuq.this.zzb(zzeunVar);
                }
            }, zzcha.zzf);
            arrayList.add(zzb);
        }
        zzfyx zza2 = zzfyo.zzc(arrayList).zza(new Callable() { // from class: com.google.android.gms.internal.ads.zzeup
            @Override // java.util.concurrent.Callable
            public final Object call() {
                List<zzfyx> list = arrayList;
                Object obj2 = obj;
                for (zzfyx zzfyxVar : list) {
                    zzeum zzeumVar = (zzeum) zzfyxVar.get();
                    if (zzeumVar != null) {
                        zzeumVar.zzf(obj2);
                    }
                }
                return obj2;
            }
        }, this.zzc);
        if (zzfje.zza()) {
            zzfjb.zza(zza2, this.zzd, zza);
        }
        return zza2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(zzeun zzeunVar) {
        long elapsedRealtime = com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime() - com.google.android.gms.ads.internal.zzt.zzB().elapsedRealtime();
        if (((Boolean) zzbkr.zza.zze()).booleanValue()) {
            String zzc = zzfsu.zzc(zzeunVar.getClass().getCanonicalName());
            com.google.android.gms.ads.internal.util.zze.zza("Signal runtime (ms) : " + zzc + " = " + elapsedRealtime);
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbM)).booleanValue()) {
            zzdxn zza = this.zze.zza();
            zza.zzb("action", "lat_ms");
            zza.zzb("lat_grp", "sig_lat_grp");
            zza.zzb("lat_id", String.valueOf(zzeunVar.zza()));
            zza.zzb("clat_ms", String.valueOf(elapsedRealtime));
            zza.zzh();
        }
    }
}
