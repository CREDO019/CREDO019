package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgic extends zzgon implements zzgpy {
    private static final zzgic zzb;
    private int zze;
    private int zzf;
    private zzgnf zzg = zzgnf.zzb;

    static {
        zzgic zzgicVar = new zzgic();
        zzb = zzgicVar;
        zzgon.zzaP(zzgic.class, zzgicVar);
    }

    private zzgic() {
    }

    public static zzgib zza() {
        return (zzgib) zzb.zzay();
    }

    public static zzgic zzd() {
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
                    return new zzgib(null);
                }
                return new zzgic();
            }
            return zzaO(zzb, "\u0000\u0003\u0000\u0000\u0001\u000b\u0003\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final zzgnf zze() {
        return this.zzg;
    }

    public final int zzg() {
        int r0 = this.zze;
        int r1 = 5;
        if (r0 == 0) {
            r1 = 2;
        } else if (r0 == 2) {
            r1 = 4;
        } else if (r0 != 3) {
            r1 = r0 != 4 ? r0 != 5 ? 0 : 7 : 6;
        }
        if (r1 == 0) {
            return 1;
        }
        return r1;
    }

    public final int zzh() {
        int zzb2 = zzgig.zzb(this.zzf);
        if (zzb2 == 0) {
            return 1;
        }
        return zzb2;
    }
}
