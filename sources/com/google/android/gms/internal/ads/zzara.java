package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzara extends zzarm {
    public zzara(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13) {
        super(zzaqbVar, "dqHO3Wlmb7oighB5DEjImrvo4du4e9JtW0sm8RACV5Fa9z8v+l2/I/8OmoY2y3HK", "t8m4GxfDNdbzfvzmJ7ERdURqh0DgV49Pg4cGI5qmXbo=", zzamhVar, r12, 3);
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        zzapi zzapiVar = new zzapi((String) this.zzf.invoke(null, this.zzb.zzb(), Boolean.valueOf(((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzci)).booleanValue())));
        synchronized (this.zze) {
            this.zze.zzi(zzapiVar.zza);
            this.zze.zzB(zzapiVar.zzb);
        }
    }
}
