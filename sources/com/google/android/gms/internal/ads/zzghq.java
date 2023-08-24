package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzghq extends zzgon implements zzgpy {
    private static final zzghq zzb;
    private zzght zze;

    static {
        zzghq zzghqVar = new zzghq();
        zzb = zzghqVar;
        zzgon.zzaP(zzghq.class, zzghqVar);
    }

    private zzghq() {
    }

    public static zzghp zza() {
        return (zzghp) zzb.zzay();
    }

    public static zzghq zzd(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzghq) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    public static /* synthetic */ void zzf(zzghq zzghqVar, zzght zzghtVar) {
        zzghtVar.getClass();
        zzghqVar.zze = zzghtVar;
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
                    return new zzghp(null);
                }
                return new zzghq();
            }
            return zzaO(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"zze"});
        }
        return (byte) 1;
    }

    public final zzght zze() {
        zzght zzghtVar = this.zze;
        return zzghtVar == null ? zzght.zze() : zzghtVar;
    }
}
