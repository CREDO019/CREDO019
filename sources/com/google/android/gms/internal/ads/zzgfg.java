package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgfg extends zzgon implements zzgpy {
    private static final zzgfg zzb;
    private int zze;
    private zzgnf zzf = zzgnf.zzb;
    private zzgfm zzg;

    static {
        zzgfg zzgfgVar = new zzgfg();
        zzb = zzgfgVar;
        zzgon.zzaP(zzgfg.class, zzgfgVar);
    }

    private zzgfg() {
    }

    public static zzgff zzc() {
        return (zzgff) zzb.zzay();
    }

    public static zzgfg zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgfg) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzj(zzgfg zzgfgVar, zzgfm zzgfmVar) {
        zzgfmVar.getClass();
        zzgfgVar.zzg = zzgfmVar;
    }

    public final int zza() {
        return this.zze;
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
                    return new zzgff(null);
                }
                return new zzgfg();
            }
            return zzaO(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n\u0003\t", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final zzgfm zzf() {
        zzgfm zzgfmVar = this.zzg;
        return zzgfmVar == null ? zzgfm.zze() : zzgfmVar;
    }

    public final zzgnf zzg() {
        return this.zzf;
    }
}
