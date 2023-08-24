package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgth extends zzgon implements zzgpy {
    private static final zzgth zzb;
    private int zze;
    private int zzf;
    private zzgsu zzh;
    private zzgsy zzi;
    private int zzj;
    private int zzm;
    private byte zzo = 2;
    private String zzg = "";
    private zzgos zzk = zzaG();
    private String zzl = "";
    private zzgow zzn = zzgon.zzaJ();

    static {
        zzgth zzgthVar = new zzgth();
        zzb = zzgthVar;
        zzgon.zzaP(zzgth.class, zzgthVar);
    }

    private zzgth() {
    }

    public static zzgtg zzc() {
        return (zzgtg) zzb.zzay();
    }

    public static /* synthetic */ void zzf(zzgth zzgthVar, int r2) {
        zzgthVar.zze |= 1;
        zzgthVar.zzf = r2;
    }

    public static /* synthetic */ void zzg(zzgth zzgthVar, String str) {
        str.getClass();
        zzgthVar.zze |= 2;
        zzgthVar.zzg = str;
    }

    public static /* synthetic */ void zzh(zzgth zzgthVar, zzgsu zzgsuVar) {
        zzgsuVar.getClass();
        zzgthVar.zzh = zzgsuVar;
        zzgthVar.zze |= 4;
    }

    public static /* synthetic */ void zzi(zzgth zzgthVar, String str) {
        str.getClass();
        zzgow zzgowVar = zzgthVar.zzn;
        if (!zzgowVar.zzc()) {
            zzgthVar.zzn = zzgon.zzaK(zzgowVar);
        }
        zzgthVar.zzn.add(str);
    }

    public static /* synthetic */ void zzj(zzgth zzgthVar, int r1) {
        zzgthVar.zzm = r1 - 1;
        zzgthVar.zze |= 64;
    }

    public final int zza() {
        return this.zzn.size();
    }

    public final String zze() {
        return this.zzg;
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
                        this.zzo = obj == null ? (byte) 0 : (byte) 1;
                        return null;
                    }
                    return new zzgtg(null);
                }
                return new zzgth();
            }
            return zzaO(zzb, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0002\u0003\u0001ᔄ\u0000\u0002ဈ\u0001\u0003ᐉ\u0002\u0004ᐉ\u0003\u0005င\u0004\u0006\u0016\u0007ဈ\u0005\bဌ\u0006\t\u001a", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", zzgte.zza, "zzn"});
        }
        return Byte.valueOf(this.zzo);
    }
}
