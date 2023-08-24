package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgsq extends zzgon implements zzgpy {
    private static final zzgsq zzb;
    private int zze;
    private byte zzh = 2;
    private zzgnf zzf = zzgnf.zzb;
    private zzgnf zzg = zzgnf.zzb;

    static {
        zzgsq zzgsqVar = new zzgsq();
        zzb = zzgsqVar;
        zzgon.zzaP(zzgsq.class, zzgsqVar);
    }

    private zzgsq() {
    }

    public static zzgsp zza() {
        return (zzgsp) zzb.zzay();
    }

    public static /* synthetic */ zzgsq zzc() {
        return zzb;
    }

    public static /* synthetic */ void zzd(zzgsq zzgsqVar, zzgnf zzgnfVar) {
        zzgsqVar.zze |= 1;
        zzgsqVar.zzf = zzgnfVar;
    }

    public static /* synthetic */ void zze(zzgsq zzgsqVar, zzgnf zzgnfVar) {
        zzgsqVar.zze |= 2;
        zzgsqVar.zzg = zzgnfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r4, Object obj, Object obj2) {
        int r42 = r4 - 1;
        if (r42 != 0) {
            if (r42 != 2) {
                if (r42 != 3) {
                    if (r42 != 4) {
                        if (r42 == 5) {
                            return zzb;
                        }
                        this.zzh = obj == null ? (byte) 0 : (byte) 1;
                        return null;
                    }
                    return new zzgsp(null);
                }
                return new zzgsq();
            }
            return zzaO(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001ᔊ\u0000\u0002ည\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        return Byte.valueOf(this.zzh);
    }
}
