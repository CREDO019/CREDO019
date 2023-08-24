package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgem {
    private final Class zza;
    private final Map zzb;
    private final Class zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    @SafeVarargs
    public zzgem(Class cls, zzgen... zzgenVarArr) {
        this.zza = cls;
        HashMap hashMap = new HashMap();
        for (int r1 = 0; r1 <= 0; r1++) {
            zzgen zzgenVar = zzgenVarArr[r1];
            if (hashMap.containsKey(zzgenVar.zzb())) {
                throw new IllegalArgumentException("KeyTypeManager constructed with duplicate factories for primitive ".concat(String.valueOf(zzgenVar.zzb().getCanonicalName())));
            }
            hashMap.put(zzgenVar.zzb(), zzgenVar);
        }
        this.zzc = zzgenVarArr[0].zzb();
        this.zzb = Collections.unmodifiableMap(hashMap);
    }

    public zzgel zza() {
        throw new UnsupportedOperationException("Creating keys is not supported.");
    }

    public abstract zzgpx zzb(zzgnf zzgnfVar) throws zzgoz;

    public abstract String zzc();

    public abstract void zzd(zzgpx zzgpxVar) throws GeneralSecurityException;

    public int zze() {
        return 1;
    }

    public abstract int zzf();

    public final Class zzi() {
        return this.zzc;
    }

    public final Class zzj() {
        return this.zza;
    }

    public final Object zzk(zzgpx zzgpxVar, Class cls) throws GeneralSecurityException {
        zzgen zzgenVar = (zzgen) this.zzb.get(cls);
        if (zzgenVar == null) {
            String canonicalName = cls.getCanonicalName();
            throw new IllegalArgumentException("Requested primitive class " + canonicalName + " not supported.");
        }
        return zzgenVar.zza(zzgpxVar);
    }

    public final Set zzl() {
        return this.zzb.keySet();
    }
}
