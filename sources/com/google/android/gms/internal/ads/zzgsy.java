package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgsy extends zzgon implements zzgpy {
    private static final zzgsy zzb;
    private int zze;
    private zzgsx zzf;
    private zzgnf zzi;
    private int zzj;
    private zzgnf zzk;
    private byte zzl = 2;
    private zzgow zzg = zzaJ();
    private zzgnf zzh = zzgnf.zzb;

    static {
        zzgsy zzgsyVar = new zzgsy();
        zzb = zzgsyVar;
        zzgon.zzaP(zzgsy.class, zzgsyVar);
    }

    private zzgsy() {
        zzgnf zzgnfVar = zzgnf.zzb;
        this.zzi = zzgnfVar;
        this.zzk = zzgnfVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    public final Object zzb(int r6, Object obj, Object obj2) {
        int r62 = r6 - 1;
        if (r62 != 0) {
            if (r62 != 2) {
                if (r62 != 3) {
                    if (r62 != 4) {
                        if (r62 == 5) {
                            return zzb;
                        }
                        this.zzl = obj == null ? (byte) 0 : (byte) 1;
                        return null;
                    }
                    return new zzgsv(null);
                }
                return new zzgsy();
            }
            return zzaO(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0001\u0001ဉ\u0000\u0002Л\u0003ည\u0001\u0004ည\u0002\u0005င\u0003\u0006ည\u0004", new Object[]{"zze", "zzf", "zzg", zzgsq.class, "zzh", "zzi", "zzj", "zzk"});
        }
        return Byte.valueOf(this.zzl);
    }
}
