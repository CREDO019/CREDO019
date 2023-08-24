package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbig extends zzgon implements zzgpy {
    private static final zzbig zzb;
    private int zze;
    private boolean zzf;
    private int zzg;

    static {
        zzbig zzbigVar = new zzbig();
        zzb = zzbigVar;
        zzgon.zzaP(zzbig.class, zzbigVar);
    }

    private zzbig() {
    }

    public static zzbif zza() {
        return (zzbif) zzb.zzay();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzd(zzbig zzbigVar, boolean z) {
        zzbigVar.zze |= 1;
        zzbigVar.zzf = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zze(zzbig zzbigVar, int r2) {
        zzbigVar.zze |= 2;
        zzbigVar.zzg = r2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
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
                    return new zzbif(null);
                }
                return new zzbig();
            }
            return zzaO(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဇ\u0000\u0002င\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final boolean zzf() {
        return this.zzf;
    }
}
