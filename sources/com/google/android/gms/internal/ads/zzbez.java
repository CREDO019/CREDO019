package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbez extends zzgon implements zzgpy {
    private static final zzbez zzb;
    private int zze;
    private int zzf;
    private zzbfd zzg;
    private zzbff zzh;

    static {
        zzbez zzbezVar = new zzbez();
        zzb = zzbezVar;
        zzgon.zzaP(zzbez.class, zzbezVar);
    }

    private zzbez() {
    }

    public static zzbey zza() {
        return (zzbey) zzb.zzay();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzd(zzbez zzbezVar, zzbfd zzbfdVar) {
        zzbfdVar.getClass();
        zzbezVar.zzg = zzbfdVar;
        zzbezVar.zze |= 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zze(zzbez zzbezVar, zzbff zzbffVar) {
        zzbffVar.getClass();
        zzbezVar.zzh = zzbffVar;
        zzbezVar.zze |= 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzf(zzbez zzbezVar, int r2) {
        zzbezVar.zzf = 1;
        zzbezVar.zze = 1 | zzbezVar.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    protected final Object zzb(int r5, Object obj, Object obj2) {
        int r52 = r5 - 1;
        if (r52 != 0) {
            if (r52 != 2) {
                if (r52 != 3) {
                    if (r52 != 4) {
                        if (r52 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzbey(null);
                }
                return new zzbez();
            }
            return zzaO(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"zze", "zzf", zzbfb.zza, "zzg", "zzh"});
        }
        return (byte) 1;
    }
}
