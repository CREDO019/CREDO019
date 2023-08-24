package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgij extends zzgon implements zzgpy {
    private static final zzgij zzb;
    private int zze;
    private zzgip zzf;
    private zzgnf zzg = zzgnf.zzb;

    static {
        zzgij zzgijVar = new zzgij();
        zzb = zzgijVar;
        zzgon.zzaP(zzgij.class, zzgijVar);
    }

    private zzgij() {
    }

    public static zzgii zzc() {
        return (zzgii) zzb.zzay();
    }

    public static zzgij zze() {
        return zzb;
    }

    public static zzgij zzf(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgij) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzj(zzgij zzgijVar, zzgip zzgipVar) {
        zzgipVar.getClass();
        zzgijVar.zzf = zzgipVar;
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
                    return new zzgii(null);
                }
                return new zzgij();
            }
            return zzaO(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final zzgip zzg() {
        zzgip zzgipVar = this.zzf;
        return zzgipVar == null ? zzgip.zze() : zzgipVar;
    }

    public final zzgnf zzh() {
        return this.zzg;
    }
}
