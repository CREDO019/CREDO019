package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcle {
    private final ArrayList zza = new ArrayList();
    private long zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long zza() {
        Iterator it = this.zza.iterator();
        while (it.hasNext()) {
            Map zze = ((zzazm) it.next()).zze();
            if (zze != null) {
                for (Map.Entry entry : zze.entrySet()) {
                    try {
                        if ("content-length".equalsIgnoreCase((String) entry.getKey())) {
                            this.zzb = Math.max(this.zzb, Long.parseLong((String) ((List) entry.getValue()).get(0)));
                        }
                    } catch (RuntimeException unused) {
                    }
                }
                it.remove();
            }
        }
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(zzazm zzazmVar) {
        this.zza.add(zzazmVar);
    }
}
