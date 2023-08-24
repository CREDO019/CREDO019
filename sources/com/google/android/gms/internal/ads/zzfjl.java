package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfjl extends zzgon implements zzgpy {
    private static final zzfjl zzb;
    private zzfjh zze;

    static {
        zzfjl zzfjlVar = new zzfjl();
        zzb = zzfjlVar;
        zzgon.zzaP(zzfjl.class, zzfjlVar);
    }

    private zzfjl() {
    }

    public static zzfjk zza() {
        return (zzfjk) zzb.zzay();
    }

    public static /* synthetic */ zzfjl zzc() {
        return zzb;
    }

    public static /* synthetic */ void zzd(zzfjl zzfjlVar, zzfjh zzfjhVar) {
        zzfjhVar.getClass();
        zzfjlVar.zze = zzfjhVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r1, Object obj, Object obj2) {
        int r12 = r1 - 1;
        if (r12 != 0) {
            if (r12 != 2) {
                if (r12 != 3) {
                    if (r12 != 4) {
                        if (r12 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzfjk(null);
                }
                return new zzfjl();
            }
            return zzaO(zzb, "\u0000\u0001\u0000\u0000\u0006\u0006\u0001\u0000\u0000\u0000\u0006\t", new Object[]{"zze"});
        }
        return (byte) 1;
    }
}
