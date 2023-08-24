package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgiu extends zzgon implements zzgpy {
    private static final zzgiu zzb;
    private zzgix zze;

    static {
        zzgiu zzgiuVar = new zzgiu();
        zzb = zzgiuVar;
        zzgon.zzaP(zzgiu.class, zzgiuVar);
    }

    private zzgiu() {
    }

    public static zzgit zza() {
        return (zzgit) zzb.zzay();
    }

    public static zzgiu zzd(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgiu) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    public static /* synthetic */ void zzf(zzgiu zzgiuVar, zzgix zzgixVar) {
        zzgixVar.getClass();
        zzgiuVar.zze = zzgixVar;
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
                    return new zzgit(null);
                }
                return new zzgiu();
            }
            return zzaO(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"zze"});
        }
        return (byte) 1;
    }

    public final zzgix zze() {
        zzgix zzgixVar = this.zze;
        return zzgixVar == null ? zzgix.zzd() : zzgixVar;
    }
}
