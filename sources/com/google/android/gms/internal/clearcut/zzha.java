package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzge;
import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class zzha extends zzfu<zzha> implements Cloneable {
    public long zzbjf = 0;
    public long zzbjg = 0;
    private long zzbjh = 0;
    private String tag = "";
    public int zzbji = 0;
    private String zzbjj = "";
    private int zzbjk = 0;
    private boolean zzbjl = false;
    private zzhb[] zzbjm = zzhb.zzge();
    private byte[] zzbjn = zzgb.zzse;
    private zzge.zzd zzbjo = null;
    public byte[] zzbjp = zzgb.zzse;
    private String zzbjq = "";
    private String zzbjr = "";
    private zzgy zzbjs = null;
    private String zzbjt = "";
    public long zzbju = 180000;
    private zzgz zzbjv = null;
    public byte[] zzbjw = zzgb.zzse;
    private String zzbjx = "";
    private int zzbjy = 0;
    private int[] zzbjz = zzgb.zzrx;
    private long zzbka = 0;
    private zzge.zzs zzbkb = null;
    public boolean zzbkc = false;

    public zzha() {
        this.zzrj = null;
        this.zzrs = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    /* renamed from: zzgd */
    public final zzha clone() {
        try {
            zzha zzhaVar = (zzha) super.clone();
            zzhb[] zzhbVarArr = this.zzbjm;
            if (zzhbVarArr != null && zzhbVarArr.length > 0) {
                zzhaVar.zzbjm = new zzhb[zzhbVarArr.length];
                int r1 = 0;
                while (true) {
                    zzhb[] zzhbVarArr2 = this.zzbjm;
                    if (r1 >= zzhbVarArr2.length) {
                        break;
                    }
                    if (zzhbVarArr2[r1] != null) {
                        zzhaVar.zzbjm[r1] = (zzhb) zzhbVarArr2[r1].clone();
                    }
                    r1++;
                }
            }
            zzge.zzd zzdVar = this.zzbjo;
            if (zzdVar != null) {
                zzhaVar.zzbjo = zzdVar;
            }
            zzgy zzgyVar = this.zzbjs;
            if (zzgyVar != null) {
                zzhaVar.zzbjs = (zzgy) zzgyVar.clone();
            }
            zzgz zzgzVar = this.zzbjv;
            if (zzgzVar != null) {
                zzhaVar.zzbjv = (zzgz) zzgzVar.clone();
            }
            int[] r12 = this.zzbjz;
            if (r12 != null && r12.length > 0) {
                zzhaVar.zzbjz = (int[]) r12.clone();
            }
            zzge.zzs zzsVar = this.zzbkb;
            if (zzsVar != null) {
                zzhaVar.zzbkb = zzsVar;
            }
            return zzhaVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzha) {
            zzha zzhaVar = (zzha) obj;
            if (this.zzbjf == zzhaVar.zzbjf && this.zzbjg == zzhaVar.zzbjg) {
                String str = this.tag;
                if (str == null) {
                    if (zzhaVar.tag != null) {
                        return false;
                    }
                } else if (!str.equals(zzhaVar.tag)) {
                    return false;
                }
                if (this.zzbji != zzhaVar.zzbji) {
                    return false;
                }
                String str2 = this.zzbjj;
                if (str2 == null) {
                    if (zzhaVar.zzbjj != null) {
                        return false;
                    }
                } else if (!str2.equals(zzhaVar.zzbjj)) {
                    return false;
                }
                if (zzfy.equals(this.zzbjm, zzhaVar.zzbjm) && Arrays.equals(this.zzbjn, zzhaVar.zzbjn)) {
                    zzge.zzd zzdVar = this.zzbjo;
                    if (zzdVar == null) {
                        if (zzhaVar.zzbjo != null) {
                            return false;
                        }
                    } else if (!zzdVar.equals(zzhaVar.zzbjo)) {
                        return false;
                    }
                    if (Arrays.equals(this.zzbjp, zzhaVar.zzbjp)) {
                        String str3 = this.zzbjq;
                        if (str3 == null) {
                            if (zzhaVar.zzbjq != null) {
                                return false;
                            }
                        } else if (!str3.equals(zzhaVar.zzbjq)) {
                            return false;
                        }
                        String str4 = this.zzbjr;
                        if (str4 == null) {
                            if (zzhaVar.zzbjr != null) {
                                return false;
                            }
                        } else if (!str4.equals(zzhaVar.zzbjr)) {
                            return false;
                        }
                        zzgy zzgyVar = this.zzbjs;
                        if (zzgyVar == null) {
                            if (zzhaVar.zzbjs != null) {
                                return false;
                            }
                        } else if (!zzgyVar.equals(zzhaVar.zzbjs)) {
                            return false;
                        }
                        String str5 = this.zzbjt;
                        if (str5 == null) {
                            if (zzhaVar.zzbjt != null) {
                                return false;
                            }
                        } else if (!str5.equals(zzhaVar.zzbjt)) {
                            return false;
                        }
                        if (this.zzbju != zzhaVar.zzbju) {
                            return false;
                        }
                        zzgz zzgzVar = this.zzbjv;
                        if (zzgzVar == null) {
                            if (zzhaVar.zzbjv != null) {
                                return false;
                            }
                        } else if (!zzgzVar.equals(zzhaVar.zzbjv)) {
                            return false;
                        }
                        if (Arrays.equals(this.zzbjw, zzhaVar.zzbjw)) {
                            String str6 = this.zzbjx;
                            if (str6 == null) {
                                if (zzhaVar.zzbjx != null) {
                                    return false;
                                }
                            } else if (!str6.equals(zzhaVar.zzbjx)) {
                                return false;
                            }
                            if (zzfy.equals(this.zzbjz, zzhaVar.zzbjz)) {
                                zzge.zzs zzsVar = this.zzbkb;
                                if (zzsVar == null) {
                                    if (zzhaVar.zzbkb != null) {
                                        return false;
                                    }
                                } else if (!zzsVar.equals(zzhaVar.zzbkb)) {
                                    return false;
                                }
                                if (this.zzbkc != zzhaVar.zzbkc) {
                                    return false;
                                }
                                return (this.zzrj == null || this.zzrj.isEmpty()) ? zzhaVar.zzrj == null || zzhaVar.zzrj.isEmpty() : this.zzrj.equals(zzhaVar.zzrj);
                            }
                            return false;
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        long j = this.zzbjf;
        long j2 = this.zzbjg;
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31 * 31;
        String str = this.tag;
        int r2 = 0;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.zzbji) * 31;
        String str2 = this.zzbjj;
        int hashCode3 = ((((((hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31 * 31) + 1237) * 31) + zzfy.hashCode(this.zzbjm)) * 31) + Arrays.hashCode(this.zzbjn);
        zzge.zzd zzdVar = this.zzbjo;
        int hashCode4 = ((((hashCode3 * 31) + (zzdVar == null ? 0 : zzdVar.hashCode())) * 31) + Arrays.hashCode(this.zzbjp)) * 31;
        String str3 = this.zzbjq;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.zzbjr;
        int hashCode6 = hashCode5 + (str4 == null ? 0 : str4.hashCode());
        zzgy zzgyVar = this.zzbjs;
        int hashCode7 = ((hashCode6 * 31) + (zzgyVar == null ? 0 : zzgyVar.hashCode())) * 31;
        String str5 = this.zzbjt;
        int hashCode8 = str5 == null ? 0 : str5.hashCode();
        long j3 = this.zzbju;
        zzgz zzgzVar = this.zzbjv;
        int hashCode9 = (((((((hashCode7 + hashCode8) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31) + (zzgzVar == null ? 0 : zzgzVar.hashCode())) * 31) + Arrays.hashCode(this.zzbjw)) * 31;
        String str6 = this.zzbjx;
        int hashCode10 = str6 == null ? 0 : str6.hashCode();
        zzge.zzs zzsVar = this.zzbkb;
        int hashCode11 = (((((((hashCode9 + hashCode10) * 31 * 31) + zzfy.hashCode(this.zzbjz)) * 31 * 31) + (zzsVar == null ? 0 : zzsVar.hashCode())) * 31) + (this.zzbkc ? 1231 : 1237)) * 31;
        if (this.zzrj != null && !this.zzrj.isEmpty()) {
            r2 = this.zzrj.hashCode();
        }
        return hashCode11 + r2;
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final void zza(zzfs zzfsVar) throws IOException {
        long j = this.zzbjf;
        if (j != 0) {
            zzfsVar.zzi(1, j);
        }
        String str = this.tag;
        if (str != null && !str.equals("")) {
            zzfsVar.zza(2, this.tag);
        }
        zzhb[] zzhbVarArr = this.zzbjm;
        int r4 = 0;
        if (zzhbVarArr != null && zzhbVarArr.length > 0) {
            int r0 = 0;
            while (true) {
                zzhb[] zzhbVarArr2 = this.zzbjm;
                if (r0 >= zzhbVarArr2.length) {
                    break;
                }
                zzhb zzhbVar = zzhbVarArr2[r0];
                if (zzhbVar != null) {
                    zzfsVar.zza(3, zzhbVar);
                }
                r0++;
            }
        }
        if (!Arrays.equals(this.zzbjn, zzgb.zzse)) {
            zzfsVar.zza(4, this.zzbjn);
        }
        if (!Arrays.equals(this.zzbjp, zzgb.zzse)) {
            zzfsVar.zza(6, this.zzbjp);
        }
        zzgy zzgyVar = this.zzbjs;
        if (zzgyVar != null) {
            zzfsVar.zza(7, zzgyVar);
        }
        String str2 = this.zzbjq;
        if (str2 != null && !str2.equals("")) {
            zzfsVar.zza(8, this.zzbjq);
        }
        zzge.zzd zzdVar = this.zzbjo;
        if (zzdVar != null) {
            zzfsVar.zze(9, zzdVar);
        }
        int r02 = this.zzbji;
        if (r02 != 0) {
            zzfsVar.zzc(11, r02);
        }
        String str3 = this.zzbjr;
        if (str3 != null && !str3.equals("")) {
            zzfsVar.zza(13, this.zzbjr);
        }
        String str4 = this.zzbjt;
        if (str4 != null && !str4.equals("")) {
            zzfsVar.zza(14, this.zzbjt);
        }
        long j2 = this.zzbju;
        if (j2 != 180000) {
            zzfsVar.zzb(15, 0);
            zzfsVar.zzn(zzfs.zzj(j2));
        }
        zzgz zzgzVar = this.zzbjv;
        if (zzgzVar != null) {
            zzfsVar.zza(16, zzgzVar);
        }
        long j3 = this.zzbjg;
        if (j3 != 0) {
            zzfsVar.zzi(17, j3);
        }
        if (!Arrays.equals(this.zzbjw, zzgb.zzse)) {
            zzfsVar.zza(18, this.zzbjw);
        }
        int[] r03 = this.zzbjz;
        if (r03 != null && r03.length > 0) {
            while (true) {
                int[] r04 = this.zzbjz;
                if (r4 >= r04.length) {
                    break;
                }
                zzfsVar.zzc(20, r04[r4]);
                r4++;
            }
        }
        zzge.zzs zzsVar = this.zzbkb;
        if (zzsVar != null) {
            zzfsVar.zze(23, zzsVar);
        }
        String str5 = this.zzbjx;
        if (str5 != null && !str5.equals("")) {
            zzfsVar.zza(24, this.zzbjx);
        }
        boolean z = this.zzbkc;
        if (z) {
            zzfsVar.zzb(25, z);
        }
        String str6 = this.zzbjj;
        if (str6 != null && !str6.equals("")) {
            zzfsVar.zza(26, this.zzbjj);
        }
        super.zza(zzfsVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final int zzen() {
        int[] r3;
        int zzen = super.zzen();
        long j = this.zzbjf;
        if (j != 0) {
            zzen += zzfs.zzd(1, j);
        }
        String str = this.tag;
        if (str != null && !str.equals("")) {
            zzen += zzfs.zzb(2, this.tag);
        }
        zzhb[] zzhbVarArr = this.zzbjm;
        int r7 = 0;
        if (zzhbVarArr != null && zzhbVarArr.length > 0) {
            int r1 = 0;
            while (true) {
                zzhb[] zzhbVarArr2 = this.zzbjm;
                if (r1 >= zzhbVarArr2.length) {
                    break;
                }
                zzhb zzhbVar = zzhbVarArr2[r1];
                if (zzhbVar != null) {
                    zzen += zzfs.zzb(3, zzhbVar);
                }
                r1++;
            }
        }
        if (!Arrays.equals(this.zzbjn, zzgb.zzse)) {
            zzen += zzfs.zzb(4, this.zzbjn);
        }
        if (!Arrays.equals(this.zzbjp, zzgb.zzse)) {
            zzen += zzfs.zzb(6, this.zzbjp);
        }
        zzgy zzgyVar = this.zzbjs;
        if (zzgyVar != null) {
            zzen += zzfs.zzb(7, zzgyVar);
        }
        String str2 = this.zzbjq;
        if (str2 != null && !str2.equals("")) {
            zzen += zzfs.zzb(8, this.zzbjq);
        }
        zzge.zzd zzdVar = this.zzbjo;
        if (zzdVar != null) {
            zzen += zzbn.zzc(9, zzdVar);
        }
        int r12 = this.zzbji;
        if (r12 != 0) {
            zzen += zzfs.zzr(11) + zzfs.zzs(r12);
        }
        String str3 = this.zzbjr;
        if (str3 != null && !str3.equals("")) {
            zzen += zzfs.zzb(13, this.zzbjr);
        }
        String str4 = this.zzbjt;
        if (str4 != null && !str4.equals("")) {
            zzen += zzfs.zzb(14, this.zzbjt);
        }
        long j2 = this.zzbju;
        if (j2 != 180000) {
            zzen += zzfs.zzr(15) + zzfs.zzo(zzfs.zzj(j2));
        }
        zzgz zzgzVar = this.zzbjv;
        if (zzgzVar != null) {
            zzen += zzfs.zzb(16, zzgzVar);
        }
        long j3 = this.zzbjg;
        if (j3 != 0) {
            zzen += zzfs.zzd(17, j3);
        }
        if (!Arrays.equals(this.zzbjw, zzgb.zzse)) {
            zzen += zzfs.zzb(18, this.zzbjw);
        }
        int[] r13 = this.zzbjz;
        if (r13 != null && r13.length > 0) {
            int r14 = 0;
            while (true) {
                r3 = this.zzbjz;
                if (r7 >= r3.length) {
                    break;
                }
                r14 += zzfs.zzs(r3[r7]);
                r7++;
            }
            zzen = zzen + r14 + (r3.length * 2);
        }
        zzge.zzs zzsVar = this.zzbkb;
        if (zzsVar != null) {
            zzen += zzbn.zzc(23, zzsVar);
        }
        String str5 = this.zzbjx;
        if (str5 != null && !str5.equals("")) {
            zzen += zzfs.zzb(24, this.zzbjx);
        }
        if (this.zzbkc) {
            zzen += zzfs.zzr(25) + 1;
        }
        String str6 = this.zzbjj;
        return (str6 == null || str6.equals("")) ? zzen : zzen + zzfs.zzb(26, this.zzbjj);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu
    public final /* synthetic */ zzha zzeo() throws CloneNotSupportedException {
        return (zzha) clone();
    }

    @Override // com.google.android.gms.internal.clearcut.zzfu, com.google.android.gms.internal.clearcut.zzfz
    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzha) clone();
    }
}
