package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfjm extends zzgon implements zzgpy {
    private static final zzfjm zzb;
    private zzgow zze = zzaJ();

    static {
        zzfjm zzfjmVar = new zzfjm();
        zzb = zzfjmVar;
        zzgon.zzaP(zzfjm.class, zzfjmVar);
    }

    private zzfjm() {
    }

    public static zzfjj zzc() {
        return (zzfjj) zzb.zzay();
    }

    public static /* synthetic */ zzfjm zzd() {
        return zzb;
    }

    public static /* synthetic */ void zze(zzfjm zzfjmVar) {
        zzfjmVar.zze = zzaJ();
    }

    public static /* synthetic */ void zzf(zzfjm zzfjmVar, zzfjl zzfjlVar) {
        zzfjlVar.getClass();
        zzgow zzgowVar = zzfjmVar.zze;
        if (!zzgowVar.zzc()) {
            zzfjmVar.zze = zzgon.zzaK(zzgowVar);
        }
        zzfjmVar.zze.add(zzfjlVar);
    }

    public final int zza() {
        return this.zze.size();
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
                    return new zzfjj(null);
                }
                return new zzfjm();
            }
            return zzaO(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzfjl.class});
        }
        return (byte) 1;
    }
}
