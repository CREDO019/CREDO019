package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgjx extends zzgon implements zzgpy {
    private static final zzgjx zzb;
    private String zze = "";
    private int zzf;
    private int zzg;
    private int zzh;

    static {
        zzgjx zzgjxVar = new zzgjx();
        zzb = zzgjxVar;
        zzgon.zzaP(zzgjx.class, zzgjxVar);
    }

    private zzgjx() {
    }

    public static zzgjw zza() {
        return (zzgjw) zzb.zzay();
    }

    public static /* synthetic */ void zzd(zzgjx zzgjxVar, String str) {
        str.getClass();
        zzgjxVar.zze = str;
    }

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
                    return new zzgjw(null);
                }
                return new zzgjx();
            }
            return zzaO(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", new Object[]{"zze", "zzf", "zzg", "zzh"});
        }
        return (byte) 1;
    }
}
