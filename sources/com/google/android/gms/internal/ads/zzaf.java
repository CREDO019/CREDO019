package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaf {
    private static final zzaf zzG = new zzaf(new zzad());
    public static final zzn zza = new zzn() { // from class: com.google.android.gms.internal.ads.zzab
    };
    public final int zzA;
    public final int zzB;
    public final int zzC;
    public final int zzD;
    public final int zzE;
    public final int zzF;
    private int zzH;
    public final String zzb;
    public final String zzc;
    public final String zzd;
    public final int zze;
    public final int zzf;
    public final int zzg;
    public final int zzh;
    public final int zzi;
    public final String zzj;
    public final zzbq zzk;
    public final String zzl;
    public final String zzm;
    public final int zzn;
    public final List zzo;
    public final zzx zzp;
    public final long zzq;
    public final int zzr;
    public final int zzs;
    public final float zzt;
    public final int zzu;
    public final float zzv;
    public final byte[] zzw;
    public final int zzx;
    public final zzq zzy;
    public final int zzz;

    private zzaf(zzad zzadVar) {
        this.zzb = zzad.zzac(zzadVar);
        this.zzc = zzad.zzad(zzadVar);
        this.zzd = zzel.zzP(zzad.zzae(zzadVar));
        this.zze = zzad.zzo(zzadVar);
        this.zzf = 0;
        int zzd = zzad.zzd(zzadVar);
        this.zzg = zzd;
        int zzl = zzad.zzl(zzadVar);
        this.zzh = zzl;
        this.zzi = zzl != -1 ? zzl : zzd;
        this.zzj = zzad.zzaa(zzadVar);
        this.zzk = zzad.zzZ(zzadVar);
        this.zzl = zzad.zzab(zzadVar);
        this.zzm = zzad.zzaf(zzadVar);
        this.zzn = zzad.zzj(zzadVar);
        this.zzo = zzad.zzag(zzadVar) == null ? Collections.emptyList() : zzad.zzag(zzadVar);
        zzx zzt = zzad.zzt(zzadVar);
        this.zzp = zzt;
        this.zzq = zzad.zzr(zzadVar);
        this.zzr = zzad.zzq(zzadVar);
        this.zzs = zzad.zzi(zzadVar);
        this.zzt = zzad.zza(zzadVar);
        this.zzu = zzad.zzm(zzadVar) == -1 ? 0 : zzad.zzm(zzadVar);
        this.zzv = zzad.zzb(zzadVar) == -1.0f ? 1.0f : zzad.zzb(zzadVar);
        this.zzw = zzad.zzah(zzadVar);
        this.zzx = zzad.zzp(zzadVar);
        this.zzy = zzad.zzs(zzadVar);
        this.zzz = zzad.zze(zzadVar);
        this.zzA = zzad.zzn(zzadVar);
        this.zzB = zzad.zzk(zzadVar);
        this.zzC = zzad.zzg(zzadVar) == -1 ? 0 : zzad.zzg(zzadVar);
        this.zzD = zzad.zzh(zzadVar) != -1 ? zzad.zzh(zzadVar) : 0;
        this.zzE = zzad.zzc(zzadVar);
        this.zzF = (zzad.zzf(zzadVar) != 0 || zzt == null) ? zzad.zzf(zzadVar) : 1;
    }

    public final boolean equals(Object obj) {
        int r3;
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzaf zzafVar = (zzaf) obj;
            int r2 = this.zzH;
            if ((r2 == 0 || (r3 = zzafVar.zzH) == 0 || r2 == r3) && this.zze == zzafVar.zze && this.zzg == zzafVar.zzg && this.zzh == zzafVar.zzh && this.zzn == zzafVar.zzn && this.zzq == zzafVar.zzq && this.zzr == zzafVar.zzr && this.zzs == zzafVar.zzs && this.zzu == zzafVar.zzu && this.zzx == zzafVar.zzx && this.zzz == zzafVar.zzz && this.zzA == zzafVar.zzA && this.zzB == zzafVar.zzB && this.zzC == zzafVar.zzC && this.zzD == zzafVar.zzD && this.zzE == zzafVar.zzE && this.zzF == zzafVar.zzF && Float.compare(this.zzt, zzafVar.zzt) == 0 && Float.compare(this.zzv, zzafVar.zzv) == 0 && zzel.zzT(this.zzb, zzafVar.zzb) && zzel.zzT(this.zzc, zzafVar.zzc) && zzel.zzT(this.zzj, zzafVar.zzj) && zzel.zzT(this.zzl, zzafVar.zzl) && zzel.zzT(this.zzm, zzafVar.zzm) && zzel.zzT(this.zzd, zzafVar.zzd) && Arrays.equals(this.zzw, zzafVar.zzw) && zzel.zzT(this.zzk, zzafVar.zzk) && zzel.zzT(this.zzy, zzafVar.zzy) && zzel.zzT(this.zzp, zzafVar.zzp) && zzd(zzafVar)) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        String str = this.zzb;
        String str2 = this.zzc;
        String str3 = this.zzl;
        String str4 = this.zzm;
        String str5 = this.zzj;
        int r5 = this.zzi;
        String str6 = this.zzd;
        int r7 = this.zzr;
        int r8 = this.zzs;
        float f = this.zzt;
        int r10 = this.zzz;
        int r11 = this.zzA;
        return "Format(" + str + ", " + str2 + ", " + str3 + ", " + str4 + ", " + str5 + ", " + r5 + ", " + str6 + ", [" + r7 + ", " + r8 + ", " + f + "], [" + r10 + ", " + r11 + "])";
    }

    public final int zza() {
        int r2;
        int r0 = this.zzr;
        if (r0 == -1 || (r2 = this.zzs) == -1) {
            return -1;
        }
        return r0 * r2;
    }

    public final zzad zzb() {
        return new zzad(this, null);
    }

    public final zzaf zzc(int r3) {
        zzad zzadVar = new zzad(this, null);
        zzadVar.zzA(r3);
        return new zzaf(zzadVar);
    }

    public final boolean zzd(zzaf zzafVar) {
        if (this.zzo.size() == zzafVar.zzo.size()) {
            for (int r0 = 0; r0 < this.zzo.size(); r0++) {
                if (!Arrays.equals((byte[]) this.zzo.get(r0), (byte[]) zzafVar.zzo.get(r0))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int r0 = this.zzH;
        if (r0 == 0) {
            String str = this.zzb;
            int hashCode = ((str == null ? 0 : str.hashCode()) + 527) * 31;
            String str2 = this.zzc;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.zzd;
            int hashCode3 = (((((((hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.zze) * 961) + this.zzg) * 31) + this.zzh) * 31;
            String str4 = this.zzj;
            int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
            zzbq zzbqVar = this.zzk;
            int hashCode5 = (hashCode4 + (zzbqVar == null ? 0 : zzbqVar.hashCode())) * 31;
            String str5 = this.zzl;
            int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.zzm;
            int hashCode7 = ((((((((((((((((((((((((((((((hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31) + this.zzn) * 31) + ((int) this.zzq)) * 31) + this.zzr) * 31) + this.zzs) * 31) + Float.floatToIntBits(this.zzt)) * 31) + this.zzu) * 31) + Float.floatToIntBits(this.zzv)) * 31) + this.zzx) * 31) + this.zzz) * 31) + this.zzA) * 31) + this.zzB) * 31) + this.zzC) * 31) + this.zzD) * 31) + this.zzE) * 31) + this.zzF;
            this.zzH = hashCode7;
            return hashCode7;
        }
        return r0;
    }
}
