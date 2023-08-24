package com.google.android.gms.ads.internal.util;

import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbd {
    private final List zza = new ArrayList();
    private final List zzb = new ArrayList();
    private final List zzc = new ArrayList();

    public final zzbd zza(String str, double d, double d2) {
        int r0 = 0;
        while (r0 < this.zza.size()) {
            double doubleValue = ((Double) this.zzc.get(r0)).doubleValue();
            double doubleValue2 = ((Double) this.zzb.get(r0)).doubleValue();
            if (d < doubleValue || (doubleValue == d && d2 < doubleValue2)) {
                break;
            }
            r0++;
        }
        this.zza.add(r0, str);
        this.zzc.add(r0, Double.valueOf(d));
        this.zzb.add(r0, Double.valueOf(d2));
        return this;
    }

    public final zzbf zzb() {
        return new zzbf(this, null);
    }
}
