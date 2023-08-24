package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public final class zzgkp extends zzgon implements zzgpy {
    private static final zzgkp zzb;
    private String zze = "";
    private zzgow zzf = zzaJ();

    static {
        zzgkp zzgkpVar = new zzgkp();
        zzb = zzgkpVar;
        zzgon.zzaP(zzgkp.class, zzgkpVar);
    }

    private zzgkp() {
    }

    public static zzgkp zzc() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r3, Object obj, Object obj2) {
        int r32 = r3 - 1;
        if (r32 != 0) {
            if (r32 != 2) {
                if (r32 != 3) {
                    if (r32 != 4) {
                        if (r32 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzgko(null);
                }
                return new zzgkp();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002\u001b", new Object[]{"zze", "zzf", zzgjo.class});
        }
        return (byte) 1;
    }

    public final List zzd() {
        return this.zzf;
    }
}
