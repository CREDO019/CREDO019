package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgip extends zzgon implements zzgpy {
    private static final zzgip zzb;
    private int zze;
    private int zzf;

    static {
        zzgip zzgipVar = new zzgip();
        zzb = zzgipVar;
        zzgon.zzaP(zzgip.class, zzgipVar);
    }

    private zzgip() {
    }

    public static zzgio zzc() {
        return (zzgio) zzb.zzay();
    }

    public static zzgip zze() {
        return zzb;
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
                    return new zzgio(null);
                }
                return new zzgip();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\f\u0002\u000b", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }

    public final int zzg() {
        int zzb2 = zzgig.zzb(this.zze);
        if (zzb2 == 0) {
            return 1;
        }
        return zzb2;
    }
}
