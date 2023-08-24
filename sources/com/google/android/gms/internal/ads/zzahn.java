package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzahn implements zzagz {
    private final zzed zza;
    private final zzzy zzb;
    private final String zzc;
    private zzaam zzd;
    private String zze;
    private int zzf;
    private int zzg;
    private boolean zzh;
    private boolean zzi;
    private long zzj;
    private int zzk;
    private long zzl;

    public zzahn() {
        this(null);
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzed zzedVar) {
        zzzy zzzyVar;
        zzdd.zzb(this.zzd);
        while (zzedVar.zza() > 0) {
            int r0 = this.zzf;
            if (r0 == 0) {
                byte[] zzH = zzedVar.zzH();
                int zzc = zzedVar.zzc();
                int zzd = zzedVar.zzd();
                while (true) {
                    if (zzc < zzd) {
                        byte b = zzH[zzc];
                        boolean z = (b & 255) == 255;
                        boolean z2 = this.zzi && (b & 224) == 224;
                        this.zzi = z;
                        if (z2) {
                            zzedVar.zzF(zzc + 1);
                            this.zzi = false;
                            this.zza.zzH()[1] = zzH[zzc];
                            this.zzg = 2;
                            this.zzf = 1;
                            break;
                        }
                        zzc++;
                    } else {
                        zzedVar.zzF(zzd);
                        break;
                    }
                }
            } else if (r0 == 1) {
                int min = Math.min(zzedVar.zza(), 4 - this.zzg);
                zzedVar.zzB(this.zza.zzH(), this.zzg, min);
                int r4 = this.zzg + min;
                this.zzg = r4;
                if (r4 >= 4) {
                    this.zza.zzF(0);
                    if (this.zzb.zza(this.zza.zze())) {
                        this.zzk = this.zzb.zzc;
                        if (!this.zzh) {
                            this.zzj = (zzzyVar.zzg * 1000000) / zzzyVar.zzd;
                            zzad zzadVar = new zzad();
                            zzadVar.zzH(this.zze);
                            zzadVar.zzS(this.zzb.zzb);
                            zzadVar.zzL(4096);
                            zzadVar.zzw(this.zzb.zze);
                            zzadVar.zzT(this.zzb.zzd);
                            zzadVar.zzK(this.zzc);
                            this.zzd.zzk(zzadVar.zzY());
                            this.zzh = true;
                        }
                        this.zza.zzF(0);
                        this.zzd.zzq(this.zza, 4);
                        this.zzf = 2;
                    } else {
                        this.zzg = 0;
                        this.zzf = 1;
                    }
                }
            } else {
                int min2 = Math.min(zzedVar.zza(), this.zzk - this.zzg);
                this.zzd.zzq(zzedVar, min2);
                int r1 = this.zzg + min2;
                this.zzg = r1;
                int r7 = this.zzk;
                if (r1 >= r7) {
                    long j = this.zzl;
                    if (j != C1856C.TIME_UNSET) {
                        this.zzd.zzs(j, 1, r7, 0, null);
                        this.zzl += this.zzj;
                    }
                    this.zzg = 0;
                    this.zzf = 0;
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzb(zzzi zzziVar, zzail zzailVar) {
        zzailVar.zzc();
        this.zze = zzailVar.zzb();
        this.zzd = zzziVar.zzv(zzailVar.zza(), 1);
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
        this.zzi = false;
        this.zzl = C1856C.TIME_UNSET;
    }

    public zzahn(String str) {
        this.zzf = 0;
        zzed zzedVar = new zzed(4);
        this.zza = zzedVar;
        zzedVar.zzH()[0] = -1;
        this.zzb = new zzzy();
        this.zzl = C1856C.TIME_UNSET;
        this.zzc = str;
    }
}
