package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgjg extends zzgon implements zzgpy {
    private static final zzgjg zzb;
    private String zze = "";
    private zzgnf zzf = zzgnf.zzb;
    private int zzg;

    static {
        zzgjg zzgjgVar = new zzgjg();
        zzb = zzgjgVar;
        zzgon.zzaP(zzgjg.class, zzgjgVar);
    }

    private zzgjg() {
    }

    public static zzgjf zza() {
        return (zzgjf) zzb.zzay();
    }

    public static zzgjg zzd() {
        return zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    protected final Object zzb(int r3, Object obj, Object obj2) {
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
                    return new zzgjf(null);
                }
                return new zzgjg();
            }
            return zzaO(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final zzgnf zze() {
        return this.zzf;
    }

    public final String zzf() {
        return this.zze;
    }

    public final int zzi() {
        int r0 = this.zzg;
        int r1 = 4;
        if (r0 == 0) {
            r1 = 2;
        } else if (r0 == 1) {
            r1 = 3;
        } else if (r0 != 2) {
            r1 = r0 != 3 ? r0 != 4 ? 0 : 6 : 5;
        }
        if (r1 == 0) {
            return 1;
        }
        return r1;
    }
}
