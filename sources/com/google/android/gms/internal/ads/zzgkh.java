package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgkh extends zzgon implements zzgpy {
    private static final zzgkh zzb;
    private int zze;
    private zzgkk zzf;

    static {
        zzgkh zzgkhVar = new zzgkh();
        zzb = zzgkhVar;
        zzgon.zzaP(zzgkh.class, zzgkhVar);
    }

    private zzgkh() {
    }

    public static zzgkg zzc() {
        return (zzgkg) zzb.zzay();
    }

    public static zzgkh zze(zzgnf zzgnfVar, zzgnz zzgnzVar) throws zzgoz {
        return (zzgkh) zzgon.zzaD(zzb, zzgnfVar, zzgnzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zzgkh zzgkhVar, zzgkk zzgkkVar) {
        zzgkkVar.getClass();
        zzgkhVar.zzf = zzgkkVar;
    }

    public final int zza() {
        return this.zze;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
                    return new zzgkg(null);
                }
                return new zzgkh();
            }
            return zzaO(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u000b\u0002\t", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }

    public final zzgkk zzf() {
        zzgkk zzgkkVar = this.zzf;
        return zzgkkVar == null ? zzgkk.zzd() : zzgkkVar;
    }
}
