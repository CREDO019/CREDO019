package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzang extends zzgon implements zzgpy {
    private static final zzang zzb;
    private int zze;
    private long zzf;
    private String zzg = "";
    private zzgnf zzh = zzgnf.zzb;

    static {
        zzang zzangVar = new zzang();
        zzb = zzangVar;
        zzgon.zzaP(zzang.class, zzangVar);
    }

    private zzang() {
    }

    public static zzang zzd() {
        return zzb;
    }

    public final long zza() {
        return this.zzf;
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
                    return new zzanf(null);
                }
                return new zzang();
            }
            return zzaO(zzb, "\u0001\u0003\u0000\u0001\u0001\u0004\u0003\u0000\u0000\u0000\u0001ဂ\u0000\u0003ဈ\u0001\u0004ည\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
        }
        return (byte) 1;
    }

    public final boolean zze() {
        return (this.zze & 1) != 0;
    }
}
