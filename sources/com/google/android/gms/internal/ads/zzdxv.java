package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzdxv {
    protected final Executor zzc;
    protected final zzcgs zzd;
    private final zzfij zzf;
    protected final String zza = (String) zzbki.zzb.zze();
    protected final Map zzb = new HashMap();
    protected final boolean zze = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbJ)).booleanValue();
    private final boolean zzg = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbM)).booleanValue();
    private final boolean zzh = ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgb)).booleanValue();

    /* JADX INFO: Access modifiers changed from: protected */
    public zzdxv(Executor executor, zzcgs zzcgsVar, zzfij zzfijVar) {
        this.zzc = executor;
        this.zzd = zzcgsVar;
        this.zzf = zzfijVar;
    }

    private final void zza(Map map, boolean z) {
        if (!map.isEmpty()) {
            final String zza = this.zzf.zza(map);
            com.google.android.gms.ads.internal.util.zze.zza(zza);
            boolean parseBoolean = Boolean.parseBoolean((String) map.get("scar"));
            if (this.zze) {
                if (!z || this.zzg) {
                    if (!parseBoolean || this.zzh) {
                        this.zzc.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdxu
                            @Override // java.lang.Runnable
                            public final void run() {
                                zzdxv zzdxvVar = zzdxv.this;
                                zzdxvVar.zzd.zza(zza);
                            }
                        });
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        com.google.android.gms.ads.internal.util.zze.zze("Empty paramMap.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String zzb(Map map) {
        return this.zzf.zza(map);
    }

    public final ConcurrentHashMap zzc() {
        return new ConcurrentHashMap(this.zzb);
    }

    public final void zzd(Map map) {
        zza(map, true);
    }

    public final void zze(Map map) {
        zza(map, false);
    }
}
