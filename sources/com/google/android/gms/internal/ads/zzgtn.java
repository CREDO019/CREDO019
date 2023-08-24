package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgtn extends zzgon implements zzgpy {
    private static final zzgtn zzb;
    private zzgtm zzA;
    private zzgso zzC;
    private zzgsg zzE;
    private int zze;
    private int zzf;
    private int zzg;
    private zzgsk zzk;
    private zzgtb zzo;
    private boolean zzp;
    private boolean zzs;
    private boolean zzt;
    private zzgtj zzv;
    private boolean zzw;
    private byte zzF = 2;
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private zzgow zzl = zzaJ();
    private zzgow zzm = zzaJ();
    private String zzn = "";
    private zzgow zzq = zzgon.zzaJ();
    private String zzr = "";
    private zzgnf zzu = zzgnf.zzb;
    private String zzx = "";
    private zzgow zzy = zzgon.zzaJ();
    private zzgow zzz = zzgon.zzaJ();
    private zzgow zzB = zzaJ();
    private String zzD = "";

    static {
        zzgtn zzgtnVar = new zzgtn();
        zzb = zzgtnVar;
        zzgon.zzaP(zzgtn.class, zzgtnVar);
    }

    private zzgtn() {
    }

    public static zzgsi zza() {
        return (zzgsi) zzb.zzay();
    }

    public static /* synthetic */ void zzg(zzgtn zzgtnVar, String str) {
        str.getClass();
        zzgtnVar.zze |= 4;
        zzgtnVar.zzh = str;
    }

    public static /* synthetic */ void zzh(zzgtn zzgtnVar, String str) {
        str.getClass();
        zzgtnVar.zze |= 8;
        zzgtnVar.zzi = str;
    }

    public static /* synthetic */ void zzi(zzgtn zzgtnVar, zzgsk zzgskVar) {
        zzgskVar.getClass();
        zzgtnVar.zzk = zzgskVar;
        zzgtnVar.zze |= 32;
    }

    public static /* synthetic */ void zzj(zzgtn zzgtnVar, zzgth zzgthVar) {
        zzgthVar.getClass();
        zzgow zzgowVar = zzgtnVar.zzl;
        if (!zzgowVar.zzc()) {
            zzgtnVar.zzl = zzgon.zzaK(zzgowVar);
        }
        zzgtnVar.zzl.add(zzgthVar);
    }

    public static /* synthetic */ void zzk(zzgtn zzgtnVar, String str) {
        zzgtnVar.zze |= 64;
        zzgtnVar.zzn = str;
    }

    public static /* synthetic */ void zzl(zzgtn zzgtnVar) {
        zzgtnVar.zze &= -65;
        zzgtnVar.zzn = zzb.zzn;
    }

    public static /* synthetic */ void zzm(zzgtn zzgtnVar, zzgtb zzgtbVar) {
        zzgtbVar.getClass();
        zzgtnVar.zzo = zzgtbVar;
        zzgtnVar.zze |= 128;
    }

    public static /* synthetic */ void zzn(zzgtn zzgtnVar, zzgtj zzgtjVar) {
        zzgtjVar.getClass();
        zzgtnVar.zzv = zzgtjVar;
        zzgtnVar.zze |= 8192;
    }

    public static /* synthetic */ void zzo(zzgtn zzgtnVar, Iterable iterable) {
        zzgow zzgowVar = zzgtnVar.zzy;
        if (!zzgowVar.zzc()) {
            zzgtnVar.zzy = zzgon.zzaK(zzgowVar);
        }
        zzgmo.zzat(iterable, zzgtnVar.zzy);
    }

    public static /* synthetic */ void zzp(zzgtn zzgtnVar, Iterable iterable) {
        zzgow zzgowVar = zzgtnVar.zzz;
        if (!zzgowVar.zzc()) {
            zzgtnVar.zzz = zzgon.zzaK(zzgowVar);
        }
        zzgmo.zzat(iterable, zzgtnVar.zzz);
    }

    public static /* synthetic */ void zzq(zzgtn zzgtnVar, int r1) {
        zzgtnVar.zzf = r1 - 1;
        zzgtnVar.zze |= 1;
    }

    public final String zzd() {
        return this.zzn;
    }

    public final String zze() {
        return this.zzh;
    }

    public final List zzf() {
        return this.zzl;
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
                        this.zzF = obj == null ? (byte) 0 : (byte) 1;
                        return null;
                    }
                    return new zzgsi(null);
                }
                return new zzgtn();
            }
            return zzaO(zzb, "\u0001\u001a\u0000\u0001\u0001\u001a\u001a\u0000\u0006\u0001\u0001ဈ\u0002\u0002ဈ\u0003\u0003ဈ\u0004\u0004Л\u0005ဇ\b\u0006\u001a\u0007ဈ\t\bဇ\n\tဇ\u000b\nဌ\u0000\u000bဌ\u0001\fဉ\u0005\rဈ\u0006\u000eဉ\u0007\u000fည\f\u0010\u001b\u0011ဉ\r\u0012ဇ\u000e\u0013ဈ\u000f\u0014\u001a\u0015\u001a\u0016ဉ\u0010\u0017\u001b\u0018ဉ\u0011\u0019ဈ\u0012\u001aဉ\u0013", new Object[]{"zze", "zzh", "zzi", "zzj", "zzl", zzgth.class, "zzp", "zzq", "zzr", "zzs", "zzt", "zzf", zzgtc.zza, "zzg", zzgsh.zza, "zzk", "zzn", "zzo", "zzu", "zzm", zzgtr.class, "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzB", zzgtx.class, "zzC", "zzD", "zzE"});
        }
        return Byte.valueOf(this.zzF);
    }
}
