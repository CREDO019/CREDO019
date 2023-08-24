package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzdih {
    protected final Map zza = new HashMap();

    /* JADX INFO: Access modifiers changed from: protected */
    public zzdih(Set set) {
        zzm(set);
    }

    public final synchronized void zzg(zzdke zzdkeVar) {
        zzj(zzdkeVar.zza, zzdkeVar.zzb);
    }

    public final synchronized void zzj(Object obj, Executor executor) {
        this.zza.put(obj, executor);
    }

    public final synchronized void zzm(Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            zzg((zzdke) it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final synchronized void zzo(final zzdig zzdigVar) {
        for (Map.Entry entry : this.zza.entrySet()) {
            final Object key = entry.getKey();
            ((Executor) entry.getValue()).execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzdif
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        zzdig.this.zza(key);
                    } catch (Throwable th) {
                        com.google.android.gms.ads.internal.zzt.zzp().zzs(th, "EventEmitter.notify");
                        com.google.android.gms.ads.internal.util.zze.zzb("Event emitter exception.", th);
                    }
                }
            });
        }
    }
}
