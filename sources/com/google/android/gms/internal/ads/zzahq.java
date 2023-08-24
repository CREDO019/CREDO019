package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzahq implements zzaim {
    private final zzagz zza;
    private final zzec zzb = new zzec(new byte[10], 10);
    private int zzc = 0;
    private int zzd;
    private zzej zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;
    private int zzi;
    private int zzj;
    private boolean zzk;

    public zzahq(zzagz zzagzVar) {
        this.zza = zzagzVar;
    }

    private final void zzd(int r1) {
        this.zzc = r1;
        this.zzd = 0;
    }

    private final boolean zze(zzed zzedVar, byte[] bArr, int r6) {
        int min = Math.min(zzedVar.zza(), r6 - this.zzd);
        if (min <= 0) {
            return true;
        }
        if (bArr == null) {
            zzedVar.zzG(min);
        } else {
            zzedVar.zzB(bArr, this.zzd, min);
        }
        int r4 = this.zzd + min;
        this.zzd = r4;
        return r4 == r6;
    }

    @Override // com.google.android.gms.internal.ads.zzaim
    public final void zza(zzed zzedVar, int r17) throws zzbu {
        long j;
        zzdd.zzb(this.zze);
        int r4 = -1;
        int r5 = 2;
        if ((r17 & 1) != 0) {
            int r2 = this.zzc;
            if (r2 != 0 && r2 != 1) {
                if (r2 != 2) {
                    int r22 = this.zzj;
                    if (r22 != -1) {
                        Log.w("PesReader", "Unexpected start indicator: expected " + r22 + " more bytes");
                    }
                    this.zza.zzc();
                } else {
                    Log.w("PesReader", "Unexpected start indicator reading extended header");
                }
            }
            zzd(1);
        }
        int r23 = r17;
        while (zzedVar.zza() > 0) {
            int r7 = this.zzc;
            if (r7 != 0) {
                if (r7 != 1) {
                    if (r7 != r5) {
                        int zza = zzedVar.zza();
                        int r9 = this.zzj;
                        int r8 = r9 != r4 ? zza - r9 : 0;
                        if (r8 > 0) {
                            zza -= r8;
                            zzedVar.zzE(zzedVar.zzc() + zza);
                        }
                        this.zza.zza(zzedVar);
                        int r82 = this.zzj;
                        if (r82 != r4) {
                            int r83 = r82 - zza;
                            this.zzj = r83;
                            if (r83 == 0) {
                                this.zza.zzc();
                                zzd(1);
                            }
                        }
                    } else {
                        if (zze(zzedVar, this.zzb.zza, Math.min(10, this.zzi)) && zze(zzedVar, null, this.zzi)) {
                            this.zzb.zzh(0);
                            if (this.zzf) {
                                this.zzb.zzj(4);
                                int zzc = this.zzb.zzc(3);
                                this.zzb.zzj(1);
                                int zzc2 = this.zzb.zzc(15);
                                this.zzb.zzj(1);
                                long zzc3 = (zzc2 << 15) | (zzc << 30) | this.zzb.zzc(15);
                                this.zzb.zzj(1);
                                if (!this.zzh && this.zzg) {
                                    this.zzb.zzj(4);
                                    int zzc4 = this.zzb.zzc(3);
                                    this.zzb.zzj(1);
                                    int zzc5 = this.zzb.zzc(15);
                                    this.zzb.zzj(1);
                                    int zzc6 = this.zzb.zzc(15);
                                    this.zzb.zzj(1);
                                    this.zze.zzb((zzc4 << 30) | (zzc5 << 15) | zzc6);
                                    this.zzh = true;
                                }
                                j = this.zze.zzb(zzc3);
                            } else {
                                j = C1856C.TIME_UNSET;
                            }
                            r23 |= true != this.zzk ? 0 : 4;
                            this.zza.zzd(j, r23);
                            zzd(3);
                            r4 = -1;
                        }
                    }
                } else if (zze(zzedVar, this.zzb.zza, 9)) {
                    int r52 = 0;
                    this.zzb.zzh(0);
                    int zzc7 = this.zzb.zzc(24);
                    if (zzc7 != 1) {
                        Log.w("PesReader", "Unexpected start code prefix: " + zzc7);
                        r4 = -1;
                        this.zzj = -1;
                    } else {
                        this.zzb.zzj(8);
                        int zzc8 = this.zzb.zzc(16);
                        this.zzb.zzj(5);
                        this.zzk = this.zzb.zzl();
                        this.zzb.zzj(2);
                        this.zzf = this.zzb.zzl();
                        this.zzg = this.zzb.zzl();
                        this.zzb.zzj(6);
                        int zzc9 = this.zzb.zzc(8);
                        this.zzi = zzc9;
                        if (zzc8 == 0) {
                            this.zzj = -1;
                        } else {
                            int r42 = (zzc8 - 3) - zzc9;
                            this.zzj = r42;
                            if (r42 < 0) {
                                Log.w("PesReader", "Found negative packet payload size: " + r42);
                                r4 = -1;
                                this.zzj = -1;
                                r52 = 2;
                            }
                        }
                        r4 = -1;
                        r52 = 2;
                    }
                    zzd(r52);
                } else {
                    r4 = -1;
                }
            } else {
                zzedVar.zzG(zzedVar.zza());
            }
            r5 = 2;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaim
    public final void zzb(zzej zzejVar, zzzi zzziVar, zzail zzailVar) {
        this.zze = zzejVar;
        this.zza.zzb(zzziVar, zzailVar);
    }

    @Override // com.google.android.gms.internal.ads.zzaim
    public final void zzc() {
        this.zzc = 0;
        this.zzd = 0;
        this.zzh = false;
        this.zza.zze();
    }
}
