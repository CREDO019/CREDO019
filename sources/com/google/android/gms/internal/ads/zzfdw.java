package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfdw {
    private final zzfcs zza;
    private final zzfcv zzb;
    private final zzefz zzc;
    private final zzfju zzd;
    private final zzfjc zze;

    public zzfdw(zzefz zzefzVar, zzfju zzfjuVar, zzfcs zzfcsVar, zzfcv zzfcvVar, zzfjc zzfjcVar) {
        this.zza = zzfcsVar;
        this.zzb = zzfcvVar;
        this.zzc = zzefzVar;
        this.zzd = zzfjuVar;
        this.zze = zzfjcVar;
    }

    public final void zza(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzb((String) it.next(), 2);
        }
    }

    public final void zzb(String str, int r9) {
        if (!this.zza.zzak) {
            this.zzd.zzc(str, this.zze);
            return;
        }
        this.zzc.zzd(new zzegb(com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis(), this.zzb.zzb, str, r9));
    }

    public final void zzc(List list, int r3) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            zzb((String) it.next(), r3);
        }
    }
}
