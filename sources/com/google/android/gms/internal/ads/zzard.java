package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzard extends zzarm {
    private final boolean zzi;

    public zzard(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13) {
        super(zzaqbVar, "+MexfEnBZA7q7iZMuUPE2bpWWq7dZXL2urW+z97dpchqWh4hWOgUnbCk4z+Hbza8", "LyfKIPN68aZRbmvxI0qKlkb7QqDxcBxbJuROIg+7WFc=", zzamhVar, r12, 61);
        this.zzi = zzaqbVar.zzs();
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.zzf.invoke(null, this.zzb.zzb(), Boolean.valueOf(this.zzi))).longValue();
        synchronized (this.zze) {
            this.zze.zzD(longValue);
        }
    }
}
