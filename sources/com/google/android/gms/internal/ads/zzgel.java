package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgel {
    private final Class zza;

    public zzgel(Class cls) {
        this.zza = cls;
    }

    public abstract zzgpx zza(zzgpx zzgpxVar) throws GeneralSecurityException;

    public abstract zzgpx zzb(zzgnf zzgnfVar) throws zzgoz;

    public Map zzc() throws GeneralSecurityException {
        return Collections.emptyMap();
    }

    public abstract void zzd(zzgpx zzgpxVar) throws GeneralSecurityException;

    public final Class zzg() {
        return this.zza;
    }
}
