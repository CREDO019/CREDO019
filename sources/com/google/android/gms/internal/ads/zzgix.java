package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgix extends zzgon implements zzgpy {
    private static final zzgix zzb;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzgix zzgixVar = new zzgix();
        zzb = zzgixVar;
        zzgon.zzaP(zzgix.class, zzgixVar);
    }

    private zzgix() {
    }

    public static zzgiw zza() {
        return (zzgiw) zzb.zzay();
    }

    public static zzgix zzd() {
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
                    return new zzgiw(null);
                }
                return new zzgix();
            }
            return zzaO(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u0003\f", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final int zze() {
        int r0 = this.zzg;
        int r1 = 3;
        if (r0 == 0) {
            r1 = 2;
        } else if (r0 != 1) {
            r1 = r0 != 2 ? r0 != 3 ? 0 : 5 : 4;
        }
        if (r1 == 0) {
            return 1;
        }
        return r1;
    }

    public final int zzf() {
        int r0 = this.zzf;
        int r02 = r0 != 0 ? r0 != 1 ? 0 : 3 : 2;
        if (r02 == 0) {
            return 1;
        }
        return r02;
    }

    public final int zzg() {
        int r0 = this.zze;
        int r02 = r0 != 0 ? r0 != 1 ? 0 : 3 : 2;
        if (r02 == 0) {
            return 1;
        }
        return r02;
    }
}
