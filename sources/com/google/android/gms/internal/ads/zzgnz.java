package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgnz {
    static final zzgnz zza = new zzgnz(true);
    private static volatile boolean zzb = false;
    private static volatile zzgnz zzc;
    private static volatile zzgnz zzd;
    private final Map zze;

    zzgnz() {
        this.zze = new HashMap();
    }

    public static zzgnz zza() {
        zzgnz zzgnzVar = zzc;
        if (zzgnzVar == null) {
            synchronized (zzgnz.class) {
                zzgnzVar = zzc;
                if (zzgnzVar == null) {
                    zzgnzVar = zza;
                    zzc = zzgnzVar;
                }
            }
        }
        return zzgnzVar;
    }

    public final zzgol zzc(zzgpx zzgpxVar, int r4) {
        return (zzgol) this.zze.get(new zzgny(zzgpxVar, r4));
    }

    zzgnz(boolean z) {
        this.zze = Collections.emptyMap();
    }

    public static zzgnz zzb() {
        zzgnz zzgnzVar = zzd;
        if (zzgnzVar != null) {
            return zzgnzVar;
        }
        synchronized (zzgnz.class) {
            zzgnz zzgnzVar2 = zzd;
            if (zzgnzVar2 != null) {
                return zzgnzVar2;
            }
            zzgnz zzb2 = zzgoh.zzb(zzgnz.class);
            zzd = zzb2;
            return zzb2;
        }
    }
}
