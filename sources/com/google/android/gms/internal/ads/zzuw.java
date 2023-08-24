package com.google.android.gms.internal.ads;

import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzuw extends zzvk implements Comparable {
    private final int zze;
    private final boolean zzf;
    private final String zzg;
    private final zzvc zzh;
    private final boolean zzi;
    private final int zzj;
    private final int zzk;
    private final int zzl;
    private final boolean zzm;
    private final int zzn;
    private final int zzo;
    private final boolean zzp;
    private final int zzq;
    private final int zzr;
    private final int zzs;
    private final int zzt;
    private final boolean zzu;
    private final boolean zzv;

    public zzuw(int r5, zzcp zzcpVar, int r7, zzvc zzvcVar, int r9, boolean z, zzfsg zzfsgVar) {
        super(r5, zzcpVar, r7);
        int r0;
        int r72;
        int r1;
        boolean z2;
        this.zzh = zzvcVar;
        this.zzg = zzvo.zzf(this.zzd.zzd);
        int r52 = 0;
        this.zzi = zzvo.zzm(r9, false);
        int r6 = 0;
        while (true) {
            r0 = Integer.MAX_VALUE;
            if (r6 >= zzvcVar.zzq.size()) {
                r6 = Integer.MAX_VALUE;
                r72 = 0;
                break;
            }
            r72 = zzvo.zza(this.zzd, (String) zzvcVar.zzq.get(r6), false);
            if (r72 > 0) {
                break;
            }
            r6++;
        }
        this.zzk = r6;
        this.zzj = r72;
        int r62 = this.zzd.zzf;
        int r63 = zzvcVar.zzr;
        this.zzl = Integer.bitCount(0);
        zzaf zzafVar = this.zzd;
        int r73 = zzafVar.zzf;
        this.zzm = true;
        this.zzp = 1 == (zzafVar.zze & 1);
        this.zzq = zzafVar.zzz;
        this.zzr = zzafVar.zzA;
        this.zzs = zzafVar.zzi;
        if (zzafVar.zzi != -1) {
            int r12 = zzvcVar.zzt;
        }
        if (zzafVar.zzz != -1) {
            int r13 = zzvcVar.zzs;
        }
        this.zzf = zzfsgVar.zza(zzafVar);
        String[] zzaf = zzel.zzaf();
        int r11 = 0;
        while (true) {
            if (r11 >= zzaf.length) {
                r11 = Integer.MAX_VALUE;
                r1 = 0;
                break;
            }
            r1 = zzvo.zza(this.zzd, zzaf[r11], false);
            if (r1 > 0) {
                break;
            }
            r11++;
        }
        this.zzn = r11;
        this.zzo = r1;
        int r64 = 0;
        while (true) {
            if (r64 < zzvcVar.zzu.size()) {
                String str = this.zzd.zzm;
                if (str != null && str.equals(zzvcVar.zzu.get(r64))) {
                    r0 = r64;
                    break;
                }
                r64++;
            } else {
                break;
            }
        }
        this.zzt = r0;
        this.zzu = (r9 & 128) == 128;
        this.zzv = (r9 & 64) == 64;
        zzvc zzvcVar2 = this.zzh;
        if (zzvo.zzm(r9, zzvcVar2.zzQ) && ((z2 = this.zzf) || zzvcVar2.zzK)) {
            if (zzvo.zzm(r9, false) && z2 && this.zzd.zzi != -1) {
                boolean z3 = zzvcVar2.zzA;
                boolean z4 = zzvcVar2.zzz;
                if (zzvcVar2.zzS || !z) {
                    r52 = 2;
                }
            }
            r52 = 1;
        }
        this.zze = r52;
    }

    @Override // com.google.android.gms.internal.ads.zzvk
    public final int zzb() {
        return this.zze;
    }

    @Override // com.google.android.gms.internal.ads.zzvk
    public final /* bridge */ /* synthetic */ boolean zzc(zzvk zzvkVar) {
        zzuw zzuwVar = (zzuw) zzvkVar;
        zzvc zzvcVar = this.zzh;
        boolean z = zzvcVar.zzN;
        zzaf zzafVar = this.zzd;
        int r2 = zzafVar.zzz;
        if (r2 != -1) {
            zzaf zzafVar2 = zzuwVar.zzd;
            if (r2 == zzafVar2.zzz) {
                boolean z2 = zzvcVar.zzL;
                String str = zzafVar.zzm;
                if (str == null || !TextUtils.equals(str, zzafVar2.zzm)) {
                    return false;
                }
                zzvc zzvcVar2 = this.zzh;
                boolean z3 = zzvcVar2.zzM;
                int r1 = this.zzd.zzA;
                if (r1 == -1 || r1 != zzuwVar.zzd.zzA) {
                    return false;
                }
                boolean z4 = zzvcVar2.zzO;
                return this.zzu == zzuwVar.zzu && this.zzv == zzuwVar.zzv;
            }
            return false;
        }
        return false;
    }

    @Override // java.lang.Comparable
    /* renamed from: zza */
    public final int compareTo(zzuw zzuwVar) {
        zzfwd zzfwdVar;
        zzfwd zza;
        zzfwd zzfwdVar2;
        if (this.zzf && this.zzi) {
            zza = zzvo.zzc;
        } else {
            zzfwdVar = zzvo.zzc;
            zza = zzfwdVar.zza();
        }
        zzfuk zzd = zzfuk.zzj().zzd(this.zzi, zzuwVar.zzi).zzc(Integer.valueOf(this.zzk), Integer.valueOf(zzuwVar.zzk), zzfwd.zzc().zza()).zzb(this.zzj, zzuwVar.zzj).zzb(this.zzl, zzuwVar.zzl).zzd(this.zzp, zzuwVar.zzp);
        boolean z = zzuwVar.zzm;
        zzfuk zzc = zzd.zzd(true, true).zzc(Integer.valueOf(this.zzn), Integer.valueOf(zzuwVar.zzn), zzfwd.zzc().zza()).zzb(this.zzo, zzuwVar.zzo).zzd(this.zzf, zzuwVar.zzf).zzc(Integer.valueOf(this.zzt), Integer.valueOf(zzuwVar.zzt), zzfwd.zzc().zza());
        Integer valueOf = Integer.valueOf(this.zzs);
        Integer valueOf2 = Integer.valueOf(zzuwVar.zzs);
        boolean z2 = this.zzh.zzz;
        zzfwdVar2 = zzvo.zzd;
        zzfuk zzc2 = zzc.zzc(valueOf, valueOf2, zzfwdVar2).zzd(this.zzu, zzuwVar.zzu).zzd(this.zzv, zzuwVar.zzv).zzc(Integer.valueOf(this.zzq), Integer.valueOf(zzuwVar.zzq), zza).zzc(Integer.valueOf(this.zzr), Integer.valueOf(zzuwVar.zzr), zza);
        Integer valueOf3 = Integer.valueOf(this.zzs);
        Integer valueOf4 = Integer.valueOf(zzuwVar.zzs);
        if (!zzel.zzT(this.zzg, zzuwVar.zzg)) {
            zza = zzvo.zzd;
        }
        return zzc2.zzc(valueOf3, valueOf4, zza).zza();
    }
}
