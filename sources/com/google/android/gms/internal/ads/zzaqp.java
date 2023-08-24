package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqp extends zzarm {
    public zzaqp(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13) {
        super(zzaqbVar, "6HNxGbhhczkgMwux7c1JnxuvU0wUTaRoejYXAQva8ckSWURuEsC3usSfONdhI0SW", "REWdnkKpTlNFb2GN/u4uBWWkAz1/VOT6KFxfIGHPouc=", zzamhVar, r12, 5);
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        this.zze.zzl(-1L);
        this.zze.zzk(-1L);
        int[] r0 = (int[]) this.zzf.invoke(null, this.zzb.zzb());
        synchronized (this.zze) {
            this.zze.zzl(r0[0]);
            this.zze.zzk(r0[1]);
            int r02 = r0[2];
            if (r02 != Integer.MIN_VALUE) {
                this.zze.zzj(r02);
            }
        }
    }
}
