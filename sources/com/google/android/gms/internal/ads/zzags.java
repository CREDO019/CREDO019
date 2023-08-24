package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.primitives.SignedBytes;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzags implements zzagz {
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

    public zzags() {
        this(null);
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzed zzedVar) {
        zzdd.zzb(this.zze);
        while (zzedVar.zza() > 0) {
            int r0 = this.zzf;
            if (r0 == 0) {
                while (zzedVar.zza() > 0) {
                    if (!this.zzh) {
                        this.zzh = zzedVar.zzk() == 172;
                    } else {
                        int zzk = zzedVar.zzk();
                        this.zzh = zzk == 172;
                        byte b = SignedBytes.MAX_POWER_OF_TWO;
                        if (zzk != 64) {
                            if (zzk == 65) {
                                zzk = 65;
                            }
                        }
                        this.zzf = 1;
                        zzed zzedVar2 = this.zzb;
                        zzedVar2.zzH()[0] = -84;
                        byte[] zzH = zzedVar2.zzH();
                        if (zzk == 65) {
                            b = 65;
                        }
                        zzH[1] = b;
                        this.zzg = 2;
                    }
                }
            } else if (r0 == 1) {
                byte[] zzH2 = this.zzb.zzH();
                int min = Math.min(zzedVar.zza(), 16 - this.zzg);
                zzedVar.zzB(zzH2, this.zzg, min);
                int r02 = this.zzg + min;
                this.zzg = r02;
                if (r02 == 16) {
                    this.zza.zzh(0);
                    zzyi zza = zzyj.zza(this.zza);
                    zzaf zzafVar = this.zzj;
                    if (zzafVar == null || zzafVar.zzz != 2 || zza.zza != zzafVar.zzA || !MimeTypes.AUDIO_AC4.equals(zzafVar.zzm)) {
                        zzad zzadVar = new zzad();
                        zzadVar.zzH(this.zzd);
                        zzadVar.zzS(MimeTypes.AUDIO_AC4);
                        zzadVar.zzw(2);
                        zzadVar.zzT(zza.zza);
                        zzadVar.zzK(this.zzc);
                        zzaf zzY = zzadVar.zzY();
                        this.zzj = zzY;
                        this.zze.zzk(zzY);
                    }
                    this.zzk = zza.zzb;
                    this.zzi = (zza.zzc * 1000000) / this.zzj.zzA;
                    this.zzb.zzF(0);
                    this.zze.zzq(this.zzb, 16);
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

    public zzags(String str) {
        zzec zzecVar = new zzec(new byte[16], 16);
        this.zza = zzecVar;
        this.zzb = new zzed(zzecVar.zza);
        this.zzf = 0;
        this.zzg = 0;
        this.zzh = false;
        this.zzl = C1856C.TIME_UNSET;
        this.zzc = str;
    }
}
