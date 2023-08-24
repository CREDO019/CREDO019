package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgsu extends zzgon implements zzgpy {
    private static final zzgsu zzb;
    private int zze;
    private zzgst zzf;
    private int zzj;
    private byte zzk = 2;
    private zzgow zzg = zzaJ();
    private zzgnf zzh = zzgnf.zzb;
    private zzgnf zzi = zzgnf.zzb;

    static {
        zzgsu zzgsuVar = new zzgsu();
        zzb = zzgsuVar;
        zzgon.zzaP(zzgsu.class, zzgsuVar);
    }

    private zzgsu() {
    }

    public static zzgsr zza() {
        return (zzgsr) zzb.zzay();
    }

    public static /* synthetic */ void zzd(zzgsu zzgsuVar, zzgsq zzgsqVar) {
        zzgsqVar.getClass();
        zzgow zzgowVar = zzgsuVar.zzg;
        if (!zzgowVar.zzc()) {
            zzgsuVar.zzg = zzgon.zzaK(zzgowVar);
        }
        zzgsuVar.zzg.add(zzgsqVar);
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
                        this.zzk = obj == null ? (byte) 0 : (byte) 1;
                        return null;
                    }
                    return new zzgsr(null);
                }
                return new zzgsu();
            }
            return zzaO(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0001\u0001ဉ\u0000\u0002Л\u0003ည\u0001\u0004ည\u0002\u0005င\u0003", new Object[]{"zze", "zzf", "zzg", zzgsq.class, "zzh", "zzi", "zzj"});
        }
        return Byte.valueOf(this.zzk);
    }
}
