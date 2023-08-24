package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgsk extends zzgon implements zzgpy {
    private static final zzgsk zzb;
    private int zze;
    private String zzf = "";

    static {
        zzgsk zzgskVar = new zzgsk();
        zzb = zzgskVar;
        zzgon.zzaP(zzgsk.class, zzgskVar);
    }

    private zzgsk() {
    }

    public static zzgsj zza() {
        return (zzgsj) zzb.zzay();
    }

    public static /* synthetic */ void zzd(zzgsk zzgskVar, String str) {
        zzgskVar.zze |= 1;
        zzgskVar.zzf = str;
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
                    return new zzgsj(null);
                }
                return new zzgsk();
            }
            return zzaO(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"zze", "zzf"});
        }
        return (byte) 1;
    }
}
