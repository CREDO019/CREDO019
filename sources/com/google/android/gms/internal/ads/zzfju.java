package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfju {
    private final Context zza;
    private final Executor zzb;
    private final zzcgs zzc;
    private final zzfje zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfju(Context context, Executor executor, zzcgs zzcgsVar, zzfje zzfjeVar) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = zzcgsVar;
        this.zzd = zzfjeVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(String str) {
        this.zzc.zza(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(String str, zzfjc zzfjcVar) {
        zzfir zza = zzfiq.zza(this.zza, 14);
        zza.zzf();
        zza.zze(this.zzc.zza(str));
        if (zzfjcVar == null) {
            this.zzd.zzb(zza.zzj());
            return;
        }
        zzfjcVar.zza(zza);
        zzfjcVar.zzg();
    }

    public final void zzc(final String str, final zzfjc zzfjcVar) {
        if (!zzfje.zza() || !((Boolean) zzbkh.zzd.zze()).booleanValue()) {
            this.zzb.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfjs
                @Override // java.lang.Runnable
                public final void run() {
                    zzfju.this.zza(str);
                }
            });
        } else {
            this.zzb.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzfjt
                @Override // java.lang.Runnable
                public final void run() {
                    zzfju.this.zzb(str, zzfjcVar);
                }
            });
        }
    }

    public final void zzd(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzc((String) it.next(), null);
        }
    }
}
