package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgjs extends zzgon implements zzgpy {
    private static final zzgjs zzb;
    private zzgjg zze;
    private int zzf;
    private int zzg;
    private int zzh;

    static {
        zzgjs zzgjsVar = new zzgjs();
        zzb = zzgjsVar;
        zzgon.zzaP(zzgjs.class, zzgjsVar);
    }

    private zzgjs() {
    }

    public static zzgjr zzd() {
        return (zzgjr) zzb.zzay();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzf(zzgjs zzgjsVar, zzgjg zzgjgVar) {
        zzgjgVar.getClass();
        zzgjsVar.zze = zzgjgVar;
    }

    public final int zza() {
        return this.zzg;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r4, Object obj, Object obj2) {
        int r42 = r4 - 1;
        if (r42 != 0) {
            if (r42 != 2) {
                if (r42 != 3) {
                    if (r42 != 4) {
                        if (r42 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzgjr(null);
                }
                return new zzgjs();
            }
            return zzaO(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000b\u0004\f", new Object[]{"zze", "zzf", "zzg", "zzh"});
        }
        return (byte) 1;
    }

    public final zzgjg zzc() {
        zzgjg zzgjgVar = this.zze;
        return zzgjgVar == null ? zzgjg.zzd() : zzgjgVar;
    }

    public final boolean zzh() {
        return this.zze != null;
    }

    public final int zzi() {
        int r0 = this.zzf;
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

    public final int zzj() {
        int zzb2 = zzgkm.zzb(this.zzh);
        if (zzb2 == 0) {
            return 1;
        }
        return zzb2;
    }
}
