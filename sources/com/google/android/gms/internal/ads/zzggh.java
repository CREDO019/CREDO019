package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzggh extends zzgon implements zzgpy {
    private static final zzggh zzb;
    private zzggk zze;
    private int zzf;

    static {
        zzggh zzgghVar = new zzggh();
        zzb = zzgghVar;
        zzgon.zzaP(zzggh.class, zzgghVar);
    }

    private zzggh() {
    }

    public static zzggg zzc() {
        return (zzggg) zzb.zzay();
    }

    public static zzggh zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzggh) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    public static /* synthetic */ void zzg(zzggh zzgghVar, zzggk zzggkVar) {
        zzggkVar.getClass();
        zzgghVar.zze = zzggkVar;
    }

    public final int zza() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r2, Object obj, Object obj2) {
        int r22 = r2 - 1;
        if (r22 != 0) {
            if (r22 != 2) {
                if (r22 != 3) {
                    if (r22 != 4) {
                        if (r22 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzggg(null);
                }
                return new zzggh();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\u000b", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }

    public final zzggk zzf() {
        zzggk zzggkVar = this.zze;
        return zzggkVar == null ? zzggk.zze() : zzggkVar;
    }
}
