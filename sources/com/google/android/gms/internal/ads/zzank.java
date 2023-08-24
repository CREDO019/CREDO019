package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzank extends zzgon implements zzgpy {
    private static final zzank zzb;
    private int zze;
    private zzgow zzf = zzaJ();
    private zzgnf zzg = zzgnf.zzb;
    private int zzh = 1;
    private int zzi = 1;

    static {
        zzank zzankVar = new zzank();
        zzb = zzankVar;
        zzgon.zzaP(zzank.class, zzankVar);
    }

    private zzank() {
    }

    public static zzanj zza() {
        return (zzanj) zzb.zzay();
    }

    public static /* synthetic */ void zzd(zzank zzankVar, zzgnf zzgnfVar) {
        zzgow zzgowVar = zzankVar.zzf;
        if (!zzgowVar.zzc()) {
            zzankVar.zzf = zzgon.zzaK(zzgowVar);
        }
        zzankVar.zzf.add(zzgnfVar);
    }

    public static /* synthetic */ void zze(zzank zzankVar, zzgnf zzgnfVar) {
        zzankVar.zze |= 1;
        zzankVar.zzg = zzgnfVar;
    }

    public static /* synthetic */ void zzf(zzank zzankVar, int r1) {
        zzankVar.zzi = r1 - 1;
        zzankVar.zze |= 4;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r6, Object obj, Object obj2) {
        int r62 = r6 - 1;
        if (r62 != 0) {
            if (r62 != 2) {
                if (r62 != 3) {
                    if (r62 != 4) {
                        if (r62 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzanj(null);
                }
                return new zzank();
            }
            return zzaO(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0000\u0001\u001c\u0002ည\u0000\u0003ဌ\u0001\u0004ဌ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh", zzane.zza, "zzi", zzanc.zza});
        }
        return (byte) 1;
    }
}
