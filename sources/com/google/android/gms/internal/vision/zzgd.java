package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class zzgd {
    private static volatile boolean zzsv = false;
    private static boolean zzsw = true;
    private static volatile zzgd zzsx;
    private static volatile zzgd zzsy;
    private static final zzgd zzsz = new zzgd(true);
    private final Map<zza, zzgs.zzg<?, ?>> zzta;

    public static zzgd zzfk() {
        return new zzgd();
    }

    public static zzgd zzfl() {
        zzgd zzgdVar = zzsx;
        if (zzgdVar == null) {
            synchronized (zzgd.class) {
                zzgdVar = zzsx;
                if (zzgdVar == null) {
                    zzgdVar = zzsz;
                    zzsx = zzgdVar;
                }
            }
        }
        return zzgdVar;
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    static final class zza {
        private final int number;
        private final Object object;

        zza(Object obj, int r2) {
            this.object = obj;
            this.number = r2;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * 65535) + this.number;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof zza) {
                zza zzaVar = (zza) obj;
                return this.object == zzaVar.object && this.number == zzaVar.number;
            }
            return false;
        }
    }

    public static zzgd zzfm() {
        zzgd zzgdVar = zzsy;
        if (zzgdVar == null) {
            synchronized (zzgd.class) {
                zzgdVar = zzsy;
                if (zzgdVar == null) {
                    zzgdVar = zzgr.zzc(zzgd.class);
                    zzsy = zzgdVar;
                }
            }
        }
        return zzgdVar;
    }

    public final <ContainingType extends zzic> zzgs.zzg<ContainingType, ?> zza(ContainingType containingtype, int r4) {
        return (zzgs.zzg<ContainingType, ?>) this.zzta.get(new zza(containingtype, r4));
    }

    public final void zza(zzgs.zzg<?, ?> zzgVar) {
        this.zzta.put(new zza(zzgVar.zzwz, zzgVar.zzxb.number), zzgVar);
    }

    zzgd() {
        this.zzta = new HashMap();
    }

    private zzgd(boolean z) {
        this.zzta = Collections.emptyMap();
    }
}
