package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzamx extends zzgon implements zzgpy {
    private static final zzamx zzb;
    private long zzA;
    private long zzB;
    private long zzC;
    private long zzG;
    private long zzH;
    private long zzI;
    private long zzK;
    private zzamz zzN;
    private zzams zzaD;
    private long zzaJ;
    private long zzaM;
    private boolean zzaP;
    private long zzaR;
    private zzang zzaS;
    private long zzaT;
    private zzamu zzaf;
    private zzamw zzah;
    private int zzas;
    private int zzat;
    private int zzau;
    private zzani zzav;
    private int zze;
    private int zzf;
    private int zzg;
    private long zzj;
    private long zzk;
    private long zzl;
    private long zzm;
    private long zzn;
    private long zzo;
    private long zzp;
    private long zzq;
    private long zzr;
    private long zzs;
    private long zzu;
    private long zzv;
    private long zzw;
    private long zzx;
    private long zzy;
    private long zzz;
    private String zzh = "";
    private String zzi = "";
    private String zzt = "";
    private String zzD = "";
    private String zzE = "D";
    private String zzF = "";
    private String zzJ = "";
    private long zzL = -1;
    private long zzM = -1;
    private long zzO = -1;
    private long zzP = -1;
    private long zzQ = -1;
    private long zzR = -1;
    private long zzS = -1;
    private long zzT = -1;
    private String zzU = "D";
    private String zzV = "D";
    private long zzW = -1;
    private int zzX = 1000;
    private int zzY = 1000;
    private long zzZ = -1;
    private long zzaa = -1;
    private long zzab = -1;
    private long zzac = -1;
    private long zzad = -1;
    private int zzae = 1000;
    private zzgow zzag = zzaJ();
    private long zzai = -1;
    private long zzaj = -1;
    private long zzak = -1;
    private long zzal = -1;
    private long zzam = -1;
    private long zzan = -1;
    private long zzao = -1;
    private long zzap = -1;
    private String zzaq = "D";
    private long zzar = -1;
    private long zzaw = -1;
    private int zzax = 1000;
    private int zzay = 1000;
    private String zzaz = "D";
    private zzgow zzaA = zzaJ();
    private int zzaB = 1000;
    private zzgow zzaC = zzaJ();
    private String zzaE = "";
    private long zzaF = -1;
    private long zzaG = -1;
    private long zzaH = -1;
    private long zzaI = -1;
    private long zzaK = -1;
    private String zzaL = "";
    private String zzaN = "";
    private int zzaO = 2;
    private String zzaQ = "";
    private String zzaU = "";

    static {
        zzamx zzamxVar = new zzamx();
        zzb = zzamxVar;
        zzgon.zzaP(zzamx.class, zzamxVar);
    }

    private zzamx() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzA(zzamx zzamxVar, long j) {
        zzamxVar.zze |= C1856C.BUFFER_FLAG_FIRST_SAMPLE;
        zzamxVar.zzI = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzB(zzamx zzamxVar, String str) {
        str.getClass();
        zzamxVar.zze |= 268435456;
        zzamxVar.zzJ = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzC(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 536870912;
        zzamxVar.zzK = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzD(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 1073741824;
        zzamxVar.zzL = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzE(zzamx zzamxVar, long j) {
        zzamxVar.zze |= Integer.MIN_VALUE;
        zzamxVar.zzM = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzF(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= 2;
        zzamxVar.zzO = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzG(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= 4;
        zzamxVar.zzP = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzH(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= 8;
        zzamxVar.zzQ = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzI(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= 16;
        zzamxVar.zzR = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzJ(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= 32;
        zzamxVar.zzS = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzK(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= 64;
        zzamxVar.zzT = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzL(zzamx zzamxVar, String str) {
        str.getClass();
        zzamxVar.zzf |= 128;
        zzamxVar.zzU = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzM(zzamx zzamxVar, String str) {
        str.getClass();
        zzamxVar.zzf |= 256;
        zzamxVar.zzV = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzN(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= 4096;
        zzamxVar.zzZ = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzO(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= 8192;
        zzamxVar.zzaa = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzP(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= 16384;
        zzamxVar.zzab = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzQ(zzamx zzamxVar, zzamu zzamuVar) {
        zzamuVar.getClass();
        zzamxVar.zzaf = zzamuVar;
        zzamxVar.zzf |= 262144;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzR(zzamx zzamxVar, zzamu zzamuVar) {
        zzamuVar.getClass();
        zzgow zzgowVar = zzamxVar.zzag;
        if (!zzgowVar.zzc()) {
            zzamxVar.zzag = zzgon.zzaK(zzgowVar);
        }
        zzamxVar.zzag.add(zzamuVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzT(zzamx zzamxVar, zzamw zzamwVar) {
        zzamwVar.getClass();
        zzamxVar.zzah = zzamwVar;
        zzamxVar.zzf |= 524288;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzU(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= 2097152;
        zzamxVar.zzaj = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzV(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= 4194304;
        zzamxVar.zzak = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzW(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= 8388608;
        zzamxVar.zzal = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzX(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= 67108864;
        zzamxVar.zzao = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzY(zzamx zzamxVar, long j) {
        zzamxVar.zzf |= C1856C.BUFFER_FLAG_FIRST_SAMPLE;
        zzamxVar.zzap = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzZ(zzamx zzamxVar, String str) {
        str.getClass();
        zzamxVar.zzf |= 268435456;
        zzamxVar.zzaq = str;
    }

    public static zzamh zza() {
        return (zzamh) zzb.zzay();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzaa(zzamx zzamxVar, long j) {
        zzamxVar.zzg |= 512;
        zzamxVar.zzaF = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzab(zzamx zzamxVar, long j) {
        zzamxVar.zzg |= 1024;
        zzamxVar.zzaG = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzac(zzamx zzamxVar, long j) {
        zzamxVar.zzg |= 2048;
        zzamxVar.zzaH = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzad(zzamx zzamxVar, long j) {
        zzamxVar.zzg |= 4096;
        zzamxVar.zzaI = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzae(zzamx zzamxVar, String str) {
        str.getClass();
        zzamxVar.zzg |= 131072;
        zzamxVar.zzaN = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzaf(zzamx zzamxVar, boolean z) {
        zzamxVar.zzg |= 524288;
        zzamxVar.zzaP = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzag(zzamx zzamxVar, long j) {
        zzamxVar.zzg |= 2097152;
        zzamxVar.zzaR = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzal(zzamx zzamxVar, int r1) {
        zzamxVar.zzX = r1 - 1;
        zzamxVar.zzf |= 1024;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzam(zzamx zzamxVar, int r1) {
        zzamxVar.zzY = r1 - 1;
        zzamxVar.zzf |= 2048;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzan(zzamx zzamxVar, int r2) {
        zzamxVar.zzae = r2 - 1;
        zzamxVar.zzf |= 131072;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzao(zzamx zzamxVar, int r1) {
        zzamxVar.zzax = r1 - 1;
        zzamxVar.zzg |= 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzap(zzamx zzamxVar, int r1) {
        zzamxVar.zzay = r1 - 1;
        zzamxVar.zzg |= 16;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzaq(zzamx zzamxVar, int r2) {
        zzamxVar.zzaO = 5;
        zzamxVar.zzg |= 262144;
    }

    public static zzamx zzd() {
        return zzb;
    }

    public static zzamx zze(byte[] bArr, zzgnz zzgnzVar) throws zzgoz {
        return (zzamx) zzgon.zzaF(zzb, bArr, zzgnzVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzi(zzamx zzamxVar, String str) {
        str.getClass();
        zzamxVar.zze |= 1;
        zzamxVar.zzh = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzj(zzamx zzamxVar, String str) {
        str.getClass();
        zzamxVar.zze |= 2;
        zzamxVar.zzi = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzk(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 4;
        zzamxVar.zzj = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzl(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 16;
        zzamxVar.zzl = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzm(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 32;
        zzamxVar.zzm = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzn(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 1024;
        zzamxVar.zzr = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzo(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 2048;
        zzamxVar.zzs = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzp(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 8192;
        zzamxVar.zzu = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzq(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 16384;
        zzamxVar.zzv = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzr(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 32768;
        zzamxVar.zzw = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzs(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 65536;
        zzamxVar.zzx = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzt(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 524288;
        zzamxVar.zzA = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzu(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 1048576;
        zzamxVar.zzB = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzv(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 2097152;
        zzamxVar.zzC = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzw(zzamx zzamxVar, String str) {
        str.getClass();
        zzamxVar.zze |= 4194304;
        zzamxVar.zzD = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzx(zzamx zzamxVar, String str) {
        str.getClass();
        zzamxVar.zze |= 16777216;
        zzamxVar.zzF = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzy(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 33554432;
        zzamxVar.zzG = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzz(zzamx zzamxVar, long j) {
        zzamxVar.zze |= 67108864;
        zzamxVar.zzH = j;
    }

    public final boolean zzah() {
        return this.zzaP;
    }

    public final boolean zzai() {
        return (this.zze & 4194304) != 0;
    }

    public final boolean zzaj() {
        return (this.zzg & 4194304) != 0;
    }

    public final int zzak() {
        int zza = zzamn.zza(this.zzaO);
        if (zza == 0) {
            return 3;
        }
        return zza;
    }

    @Override // com.google.android.gms.internal.ads.zzgon
    protected final Object zzb(int r6, Object obj, Object obj2) {
        int r62 = r6 - 1;
        if (r62 != 0) {
            if (r62 == 2) {
                zzgor zzgorVar = zzand.zza;
                return zzaO(zzb, "\u0001\\\u0000\u0003\u0001Į\\\u0000\u0003\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဂ\u0007\tဂ\b\nဂ\t\u000bဂ\n\fဂ\u000b\rဈ\f\u000eဂ\r\u000fဂ\u000e\u0010ဂ\u000f\u0011ဂ\u0010\u0012ဂ\u0011\u0013ဂ\u0012\u0014ဂ\u0013\u0015ဂP\u0016ဂ\u0014\u0017ဂ\u0015\u0018ဈQ\u0019ဂU\u001aဌR\u001bဈ\u0016\u001cဇS\u001dဈ\u0018\u001eဈT\u001fဂ\u0019 ဂ\u001a!ဂ\u001b\"ဈ\u001c#ဂ\u001d$ဂ\u001e%ဂ\u001f&ဉ 'ဂ!(ဂ\")ဂ#*ဂ$+\u001b,ဂ%-ဂ&.ဈ'/ဈ(0ဌ*1ဌ+2ဉ23ဂ,4ဂ-5ဂ.6ဂ/7ဂ08ဌ19ဉ3:ဂ4;ဂ5<ဂ6=ဂ7>ဂ:?ဂ;@ဂ=Aဌ>Bဌ?Cဈ<Dဌ@EဉAFဂBGဂ8Hဂ9IဌCJဂ)Kဈ\u0017LဌDMဈEN\u001bOဌFP\u001bQဉGRဈHSဂITဂJUဂKVဂLWဂMXဂNYဈOÉဉVĭဂWĮဈX", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzo", "zzp", "zzq", "zzr", "zzs", "zzt", "zzu", "zzv", "zzw", "zzx", "zzy", "zzz", "zzA", "zzaM", "zzB", "zzC", "zzaN", "zzaR", "zzaO", zzamm.zza, "zzD", "zzaP", "zzF", "zzaQ", "zzG", "zzH", "zzI", "zzJ", "zzK", "zzL", "zzM", "zzN", "zzO", "zzP", "zzQ", "zzR", "zzag", zzamu.class, "zzS", "zzT", "zzU", "zzV", "zzX", zzgorVar, "zzY", zzgorVar, "zzaf", "zzZ", "zzaa", "zzab", "zzac", "zzad", "zzae", zzgorVar, "zzah", "zzai", "zzaj", "zzak", "zzal", "zzao", "zzap", "zzar", "zzas", zzanc.zza, "zzat", zzane.zza, "zzaq", "zzau", zzami.zza, "zzav", "zzaw", "zzam", "zzan", "zzax", zzgorVar, "zzW", "zzE", "zzay", zzgorVar, "zzaz", "zzaA", zzamq.class, "zzaB", zzgorVar, "zzaC", zzamk.class, "zzaD", "zzaE", "zzaF", "zzaG", "zzaH", "zzaI", "zzaJ", "zzaK", "zzaL", "zzaS", "zzaT", "zzaU"});
            } else if (r62 != 3) {
                if (r62 != 4) {
                    if (r62 != 5) {
                        return null;
                    }
                    return zzb;
                }
                return new zzamh(null);
            } else {
                return new zzamx();
            }
        }
        return (byte) 1;
    }

    public final zzang zzf() {
        zzang zzangVar = this.zzaS;
        return zzangVar == null ? zzang.zzd() : zzangVar;
    }

    public final String zzg() {
        return this.zzaN;
    }

    public final String zzh() {
        return this.zzD;
    }
}
