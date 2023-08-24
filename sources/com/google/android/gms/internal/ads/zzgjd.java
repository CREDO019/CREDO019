package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgjd extends zzgon implements zzgpy {
    private static final zzgjd zzb;
    private int zze;
    private zzgix zzf;
    private zzgnf zzg = zzgnf.zzb;

    static {
        zzgjd zzgjdVar = new zzgjd();
        zzb = zzgjdVar;
        zzgon.zzaP(zzgjd.class, zzgjdVar);
    }

    private zzgjd() {
    }

    public static zzgjc zzd() {
        return (zzgjc) zzb.zzay();
    }

    public static zzgjd zzf() {
        return zzb;
    }

    public static zzgjd zzg(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgjd) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzj(zzgjd zzgjdVar, zzgix zzgixVar) {
        zzgixVar.getClass();
        zzgjdVar.zzf = zzgixVar;
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
                    return new zzgjc(null);
                }
                return new zzgjd();
            }
            return zzaO(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final zzgix zzc() {
        zzgix zzgixVar = this.zzf;
        return zzgixVar == null ? zzgix.zzd() : zzgixVar;
    }

    public final zzgnf zzh() {
        return this.zzg;
    }

    public final boolean zzl() {
        return this.zzf != null;
    }
}
