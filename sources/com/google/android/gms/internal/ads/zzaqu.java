package com.google.android.gms.internal.ads;

import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqu extends zzarm {
    private final Map zzi;
    private final View zzj;

    public zzaqu(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13, Map map, View view) {
        super(zzaqbVar, "nPgtvuonjnkWdghv0SijHg6/i9Y34D7EpYJU2u9rcQe20YVAO3kNDyZXdnFoabyE", "8k8YzM5wR7o+JxVSif6W3gUExnUXRa+1SaNfeXoc/KI=", zzamhVar, r12, 85);
        this.zzi = map;
        this.zzj = view;
    }

    private final long zzc(int r3) {
        Map map = this.zzi;
        Integer valueOf = Integer.valueOf(r3);
        if (map.containsKey(valueOf)) {
            return ((Long) this.zzi.get(valueOf)).longValue();
        }
        return Long.MIN_VALUE;
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        long[] jArr = (long[]) this.zzf.invoke(null, new long[]{zzc(1), zzc(2)}, this.zzb.zzb(), this.zzj);
        long j = jArr[0];
        this.zzi.put(1, Long.valueOf(jArr[1]));
        long j2 = jArr[2];
        this.zzi.put(2, Long.valueOf(jArr[3]));
        synchronized (this.zze) {
            this.zze.zzu(j);
            this.zze.zzt(j2);
        }
    }
}
