package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgjl extends zzgon implements zzgpy {
    private static final zzgjl zzb;
    private String zze = "";
    private zzgnf zzf = zzgnf.zzb;
    private int zzg;

    static {
        zzgjl zzgjlVar = new zzgjl();
        zzb = zzgjlVar;
        zzgon.zzaP(zzgjl.class, zzgjlVar);
    }

    private zzgjl() {
    }

    public static zzgjk zza() {
        return (zzgjk) zzb.zzay();
    }

    public static /* synthetic */ zzgjl zzc() {
        return zzb;
    }

    public static zzgjl zzd() {
        return zzb;
    }

    public static /* synthetic */ void zzg(zzgjl zzgjlVar, String str) {
        str.getClass();
        zzgjlVar.zze = str;
    }

    public static /* synthetic */ void zzh(zzgjl zzgjlVar, zzgnf zzgnfVar) {
        zzgjlVar.zzf = zzgnfVar;
    }

    public static /* synthetic */ void zzj(zzgjl zzgjlVar, int r1) {
        zzgjlVar.zzg = zzgkm.zza(r1);
    }

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
                    return new zzgjk(null);
                }
                return new zzgjl();
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
        int zzb2 = zzgkm.zzb(this.zzg);
        if (zzb2 == 0) {
            return 1;
        }
        return zzb2;
    }
}
