package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgim extends zzgon implements zzgpy {
    private static final zzgim zzb;
    private zzgip zze;
    private int zzf;
    private int zzg;

    static {
        zzgim zzgimVar = new zzgim();
        zzb = zzgimVar;
        zzgon.zzaP(zzgim.class, zzgimVar);
    }

    private zzgim() {
    }

    public static zzgil zzc() {
        return (zzgil) zzb.zzay();
    }

    public static zzgim zze() {
        return zzb;
    }

    public static zzgim zzf(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgim) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    public static /* synthetic */ void zzh(zzgim zzgimVar, zzgip zzgipVar) {
        zzgipVar.getClass();
        zzgimVar.zze = zzgipVar;
    }

    public final int zza() {
        return this.zzf;
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
                    return new zzgil(null);
                }
                return new zzgim();
            }
            return zzaO(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b\u0003\u000b", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final zzgip zzg() {
        zzgip zzgipVar = this.zze;
        return zzgipVar == null ? zzgip.zze() : zzgipVar;
    }
}
