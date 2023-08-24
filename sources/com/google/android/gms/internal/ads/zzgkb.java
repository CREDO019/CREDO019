package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgkb extends zzgon implements zzgpy {
    private static final zzgkb zzb;
    private int zze;
    private zzgke zzf;

    static {
        zzgkb zzgkbVar = new zzgkb();
        zzb = zzgkbVar;
        zzgon.zzaP(zzgkb.class, zzgkbVar);
    }

    private zzgkb() {
    }

    public static zzgka zzc() {
        return (zzgka) zzb.zzay();
    }

    public static zzgkb zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgkb) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zzgkb zzgkbVar, zzgke zzgkeVar) {
        zzgkeVar.getClass();
        zzgkbVar.zzf = zzgkeVar;
    }

    public final int zza() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
                    return new zzgka(null);
                }
                return new zzgkb();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }

    public final zzgke zzf() {
        zzgke zzgkeVar = this.zzf;
        return zzgkeVar == null ? zzgke.zzc() : zzgkeVar;
    }
}
