package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzarf extends zzarm {
    public zzarf(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13) {
        super(zzaqbVar, "G1zSQHxSHVPUwoFnXHh/RUDU4HrWkkXkBz0C0L8MT/vMOwG2Z70Zze/sd76ItTPz", "Q7CPoNnCWDIIOccltii1S+O+2a/ZVRW78C0n4S9Y84k=", zzamhVar, r12, 51);
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.zze) {
            zzapw zzapwVar = new zzapw((String) this.zzf.invoke(null, new Object[0]));
            this.zze.zzo(zzapwVar.zza.longValue());
            this.zze.zzp(zzapwVar.zzb.longValue());
        }
    }
}
