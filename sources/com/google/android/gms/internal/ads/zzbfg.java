package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbfg extends zzgon implements zzgpy {
    private static final zzbfg zzb;
    private zzgow zze = zzaJ();

    static {
        zzbfg zzbfgVar = new zzbfg();
        zzb = zzbfgVar;
        zzgon.zzaP(zzbfg.class, zzbfgVar);
    }

    private zzbfg() {
    }

    public static zzbfa zza() {
        return (zzbfa) zzb.zzay();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzd(zzbfg zzbfgVar, zzbez zzbezVar) {
        zzbezVar.getClass();
        zzgow zzgowVar = zzbfgVar.zze;
        if (!zzgowVar.zzc()) {
            zzbfgVar.zze = zzgon.zzaK(zzgowVar);
        }
        zzbfgVar.zze.add(zzbezVar);
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
                    return new zzbfa(null);
                }
                return new zzbfg();
            }
            return zzaO(zzb, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzbez.class});
        }
        return (byte) 1;
    }
}
