package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqr extends zzarm {
    private final long zzi;

    public zzaqr(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, long j, int r14, int r15) {
        super(zzaqbVar, "xrMIVw+CZrXn1IekizTiUScI6wNAILuxj1b/rhxDE2pwil0Ht31LBwVl9aHUCekb", "3Va0stlf242U1Ue7o9TlJnm/36RIQrt1QqEOlEimTMw=", zzamhVar, r14, 25);
        this.zzi = j;
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zzf.invoke(null, new Object[0])).longValue();
        synchronized (this.zze) {
            this.zze.zzs(longValue);
            long j = this.zzi;
            if (j != 0) {
                this.zze.zzS(longValue - j);
                this.zze.zzT(this.zzi);
            }
        }
    }
}
