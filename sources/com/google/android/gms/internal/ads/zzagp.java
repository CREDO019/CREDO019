package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import com.google.common.base.Ascii;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzagp implements zzagz {
    private final zzec zza;
    private final zzed zzb;
    private final String zzc;
    private String zzd;
    private zzaam zze;
    private int zzf;
    private int zzg;
    private boolean zzh;
    private long zzi;
    private zzaf zzj;
    private int zzk;
    private long zzl;

    public zzagp() {
        this(null);
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzed zzedVar) {
        zzdd.zzb(this.zze);
        while (zzedVar.zza() > 0) {
            int r0 = this.zzf;
            if (r0 == 0) {
                while (true) {
                    if (zzedVar.zza() <= 0) {
                        break;
                    } else if (!this.zzh) {
                        this.zzh = zzedVar.zzk() == 11;
                    } else {
                        int zzk = zzedVar.zzk();
                        if (zzk == 119) {
                            this.zzh = false;
                            this.zzf = 1;
                            zzed zzedVar2 = this.zzb;
                            zzedVar2.zzH()[0] = Ascii.f1132VT;
                            zzedVar2.zzH()[1] = 119;
                            this.zzg = 2;
                            break;
                        }
                        this.zzh = zzk == 11;
                    }
                }
            } else if (r0 == 1) {
                byte[] zzH = this.zzb.zzH();
                int min = Math.min(zzedVar.zza(), 128 - this.zzg);
                zzedVar.zzB(zzH, this.zzg, min);
                int r02 = this.zzg + min;
                this.zzg = r02;
                if (r02 == 128) {
                    this.zza.zzh(0);
                    zzyf zze = zzyg.zze(this.zza);
                    zzaf zzafVar = this.zzj;
                    if (zzafVar == null || zze.zzc != zzafVar.zzz || zze.zzb != zzafVar.zzA || !zzel.zzT(zze.zza, zzafVar.zzm)) {
                        zzad zzadVar = new zzad();
                        zzadVar.zzH(this.zzd);
                        zzadVar.zzS(zze.zza);
                        zzadVar.zzw(zze.zzc);
                        zzadVar.zzT(zze.zzb);
                        zzadVar.zzK(this.zzc);
                        zzaf zzY = zzadVar.zzY();
                        this.zzj = zzY;
                        this.zze.zzk(zzY);
                    }
                    this.zzk = zze.zzd;
                    this.zzi = (zze.zze * 1000000) / this.zzj.zzA;
                    this.zzb.zzF(0);
                    this.zze.zzq(this.zzb, 128);
                    this.zzf = 2;
                }
            } else {
                int min2 = Math.min(zzedVar.zza(), this.zzk - this.zzg);
                this.zze.zzq(zzedVar, min2);
                int r1 = this.zzg + min2;
                this.zzg = r1;
                int r8 = this.zzk;
                if (r1 == r8) {
                    long j = this.zzl;
                    if (j != C1856C.TIME_UNSET) {
                        this.zze.zzs(j, 1, r8, 0, null);
                        this.zzl += this.zzi;
                    }
                    this.zzf = 0;
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzb(zzzi zzziVar, zzail zzailVar) {
        zzailVar.zzc();
        this.zzd = zzailVar.zzb();
        this.zze = zzziVar.zzv(zzailVar.zza(), 1);
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzc() {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzd(long j, int r5) {
        if (j != C1856C.TIME_UNSET) {
            this.zzl = j;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zze() {
        this.zzf = 0;
        this.zzg = 0;
        this.zzh = false;
        this.zzl = C1856C.TIME_UNSET;
    }

    public zzagp(String str) {
        zzec zzecVar = new zzec(new byte[128], 128);
        this.zza = zzecVar;
        this.zzb = new zzed(zzecVar.zza);
        this.zzf = 0;
        this.zzl = C1856C.TIME_UNSET;
        this.zzc = str;
    }
}
