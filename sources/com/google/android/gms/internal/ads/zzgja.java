package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgja extends zzgon implements zzgpy {
    private static final zzgja zzb;
    private int zze;
    private zzgjd zzf;
    private zzgnf zzg = zzgnf.zzb;

    static {
        zzgja zzgjaVar = new zzgja();
        zzb = zzgjaVar;
        zzgon.zzaP(zzgja.class, zzgjaVar);
    }

    private zzgja() {
    }

    public static zzgiz zzc() {
        return (zzgiz) zzb.zzay();
    }

    public static zzgja zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgja) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzgja zzgjaVar, zzgjd zzgjdVar) {
        zzgjdVar.getClass();
        zzgjaVar.zzf = zzgjdVar;
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
                    return new zzgiz(null);
                }
                return new zzgja();
            }
            return zzaO(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final zzgjd zzf() {
        zzgjd zzgjdVar = this.zzf;
        return zzgjdVar == null ? zzgjd.zzf() : zzgjdVar;
    }

    public final zzgnf zzg() {
        return this.zzg;
    }

    public final boolean zzk() {
        return this.zzf != null;
    }
}
