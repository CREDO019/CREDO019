package com.google.android.gms.internal.ads;

import java.io.Serializable;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfsb implements Serializable {
    public static zzfsb zzc() {
        return zzfrk.zza;
    }

    public static zzfsb zzd(@CheckForNull Object obj) {
        return obj == null ? zzfrk.zza : new zzfsk(obj);
    }

    public abstract zzfsb zza(zzfru zzfruVar);

    public abstract Object zzb(Object obj);
}
