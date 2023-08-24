package com.google.android.gms.internal.ads;

import android.support.p001v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.C1856C;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzawi implements zzauv, zzavc {
    public static final zzaux zza = new zzawg();
    private static final int zzb = zzban.zzg("qt  ");
    private int zzg;
    private int zzh;
    private long zzi;
    private int zzj;
    private zzbag zzk;
    private int zzl;
    private int zzm;
    private zzauw zzn;
    private zzawh[] zzo;
    private long zzp;
    private boolean zzq;
    private final zzbag zze = new zzbag(16);
    private final Stack zzf = new Stack();
    private final zzbag zzc = new zzbag(zzbae.zza);
    private final zzbag zzd = new zzbag(4);

    private final void zzh() {
        this.zzg = 0;
        this.zzj = 0;
    }

    private final void zzi(long j) throws zzasv {
        zzaxd zzaxdVar;
        zzauz zzauzVar;
        zzaxd zzaxdVar2;
        zzawk zza2;
        while (!this.zzf.isEmpty() && ((zzavq) this.zzf.peek()).zza == j) {
            zzavq zzavqVar = (zzavq) this.zzf.pop();
            if (zzavqVar.zzaR == zzavs.zzE) {
                ArrayList arrayList = new ArrayList();
                zzauz zzauzVar2 = new zzauz();
                zzavr zzb2 = zzavqVar.zzb(zzavs.zzaC);
                if (zzb2 != null) {
                    zzaxdVar = zzavz.zzc(zzb2, this.zzq);
                    if (zzaxdVar != null) {
                        zzauzVar2.zzb(zzaxdVar);
                    }
                } else {
                    zzaxdVar = null;
                }
                long j2 = C1856C.TIME_UNSET;
                long j3 = Long.MAX_VALUE;
                int r11 = 0;
                while (r11 < zzavqVar.zzc.size()) {
                    zzavq zzavqVar2 = (zzavq) zzavqVar.zzc.get(r11);
                    if (zzavqVar2.zzaR == zzavs.zzG && (zza2 = zzavz.zza(zzavqVar2, zzavqVar.zzb(zzavs.zzF), C1856C.TIME_UNSET, null, this.zzq)) != null) {
                        zzawn zzb3 = zzavz.zzb(zza2, zzavqVar2.zza(zzavs.zzH).zza(zzavs.zzI).zza(zzavs.zzJ), zzauzVar2);
                        if (zzb3.zza != 0) {
                            zzawh zzawhVar = new zzawh(zza2, zzb3, this.zzn.zzbi(r11, zza2.zzb));
                            zzass zze = zza2.zzf.zze(zzb3.zzd + 30);
                            if (zza2.zzb == 1) {
                                if (zzauzVar2.zza()) {
                                    zze = zze.zzd(zzauzVar2.zzb, zzauzVar2.zzc);
                                }
                                if (zzaxdVar != null) {
                                    zze = zze.zzf(zzaxdVar);
                                }
                            }
                            zzawhVar.zzc.zza(zze);
                            zzauzVar = zzauzVar2;
                            zzaxdVar2 = zzaxdVar;
                            long max = Math.max(j2, zza2.zze);
                            arrayList.add(zzawhVar);
                            long j4 = zzb3.zzb[0];
                            if (j4 < j3) {
                                j2 = max;
                                j3 = j4;
                            } else {
                                j2 = max;
                            }
                            r11++;
                            zzauzVar2 = zzauzVar;
                            zzaxdVar = zzaxdVar2;
                        }
                    }
                    zzauzVar = zzauzVar2;
                    zzaxdVar2 = zzaxdVar;
                    r11++;
                    zzauzVar2 = zzauzVar;
                    zzaxdVar = zzaxdVar2;
                }
                this.zzp = j2;
                this.zzo = (zzawh[]) arrayList.toArray(new zzawh[arrayList.size()]);
                this.zzn.zzb();
                this.zzn.zzc(this);
                this.zzf.clear();
                this.zzg = 2;
            } else if (!this.zzf.isEmpty()) {
                ((zzavq) this.zzf.peek()).zzc(zzavqVar);
            }
        }
        if (this.zzg != 2) {
            zzh();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzavc
    public final long zza() {
        return this.zzp;
    }

    @Override // com.google.android.gms.internal.ads.zzavc
    public final long zzb(long j) {
        long j2 = Long.MAX_VALUE;
        for (zzawh zzawhVar : this.zzo) {
            zzawn zzawnVar = zzawhVar.zzb;
            int zza2 = zzawnVar.zza(j);
            if (zza2 == -1) {
                zza2 = zzawnVar.zzb(j);
            }
            long j3 = zzawnVar.zzb[zza2];
            if (j3 < j2) {
                j2 = j3;
            }
        }
        return j2;
    }

    @Override // com.google.android.gms.internal.ads.zzavc
    public final boolean zzc() {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzauv
    public final void zzd(zzauw zzauwVar) {
        this.zzn = zzauwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzauv
    public final void zze(long j, long j2) {
        this.zzf.clear();
        this.zzj = 0;
        this.zzl = 0;
        this.zzm = 0;
        if (j == 0) {
            zzh();
            return;
        }
        zzawh[] zzawhVarArr = this.zzo;
        if (zzawhVarArr != null) {
            for (zzawh zzawhVar : zzawhVarArr) {
                zzawn zzawnVar = zzawhVar.zzb;
                int zza2 = zzawnVar.zza(j2);
                if (zza2 == -1) {
                    zza2 = zzawnVar.zzb(j2);
                }
                zzawhVar.zzd = zza2;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzauv
    public final boolean zzg(zzauu zzauuVar) throws IOException, InterruptedException {
        return zzawj.zzb(zzauuVar);
    }

    @Override // com.google.android.gms.internal.ads.zzauv
    public final int zzf(zzauu zzauuVar, zzava zzavaVar) throws IOException, InterruptedException {
        zzawh[] zzawhVarArr;
        boolean z;
        boolean z2;
        while (true) {
            int r3 = this.zzg;
            if (r3 == 0) {
                if (this.zzj == 0) {
                    if (!zzauuVar.zzh(this.zze.zza, 0, 8, true)) {
                        return -1;
                    }
                    this.zzj = 8;
                    this.zze.zzv(0);
                    this.zzi = this.zze.zzm();
                    this.zzh = this.zze.zze();
                }
                if (this.zzi == 1) {
                    zzauuVar.zzh(this.zze.zza, 8, 8, false);
                    this.zzj += 8;
                    this.zzi = this.zze.zzn();
                }
                int r32 = this.zzh;
                if (r32 == zzavs.zzE || r32 == zzavs.zzG || r32 == zzavs.zzH || r32 == zzavs.zzI || r32 == zzavs.zzJ || r32 == zzavs.zzS) {
                    long zzd = (zzauuVar.zzd() + this.zzi) - this.zzj;
                    this.zzf.add(new zzavq(this.zzh, zzd));
                    if (this.zzi == this.zzj) {
                        zzi(zzd);
                    } else {
                        zzh();
                    }
                } else {
                    int r33 = this.zzh;
                    if (r33 == zzavs.zzU || r33 == zzavs.zzF || r33 == zzavs.zzV || r33 == zzavs.zzW || r33 == zzavs.zzao || r33 == zzavs.zzap || r33 == zzavs.zzaq || r33 == zzavs.zzT || r33 == zzavs.zzar || r33 == zzavs.zzas || r33 == zzavs.zzat || r33 == zzavs.zzau || r33 == zzavs.zzav || r33 == zzavs.zzR || r33 == zzavs.zzd || r33 == zzavs.zzaC) {
                        zzazy.zze(this.zzj == 8);
                        zzazy.zze(this.zzi <= 2147483647L);
                        this.zzk = new zzbag((int) this.zzi);
                        System.arraycopy(this.zze.zza, 0, this.zzk.zza, 0, 8);
                    } else {
                        this.zzk = null;
                    }
                    this.zzg = 1;
                }
            } else if (r3 != 1) {
                long j = Long.MAX_VALUE;
                int r34 = 0;
                int r5 = -1;
                while (true) {
                    zzawhVarArr = this.zzo;
                    if (r34 >= zzawhVarArr.length) {
                        break;
                    }
                    zzawh zzawhVar = zzawhVarArr[r34];
                    int r15 = zzawhVar.zzd;
                    zzawn zzawnVar = zzawhVar.zzb;
                    if (r15 != zzawnVar.zza) {
                        long j2 = zzawnVar.zzb[r15];
                        if (j2 < j) {
                            r5 = r34;
                            j = j2;
                        }
                    }
                    r34++;
                }
                if (r5 == -1) {
                    return -1;
                }
                zzawh zzawhVar2 = zzawhVarArr[r5];
                zzave zzaveVar = zzawhVar2.zzc;
                int r52 = zzawhVar2.zzd;
                zzawn zzawnVar2 = zzawhVar2.zzb;
                long j3 = zzawnVar2.zzb[r52];
                int r11 = zzawnVar2.zzc[r52];
                if (zzawhVar2.zza.zzg == 1) {
                    j3 += 8;
                    r11 -= 8;
                }
                long zzd2 = (j3 - zzauuVar.zzd()) + this.zzl;
                if (zzd2 < 0 || zzd2 >= PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
                    zzavaVar.zza = j3;
                    return 1;
                }
                int r2 = (int) zzd2;
                boolean z3 = false;
                zzauuVar.zzi(r2, false);
                int r22 = zzawhVar2.zza.zzk;
                if (r22 == 0) {
                    while (true) {
                        int r23 = this.zzl;
                        if (r23 >= r11) {
                            break;
                        }
                        int zzd3 = zzaveVar.zzd(zzauuVar, r11 - r23, false);
                        this.zzl += zzd3;
                        this.zzm -= zzd3;
                    }
                } else {
                    byte[] bArr = this.zzd.zza;
                    bArr[0] = 0;
                    bArr[1] = 0;
                    bArr[2] = 0;
                    int r7 = 4 - r22;
                    while (this.zzl < r11) {
                        int r8 = this.zzm;
                        if (r8 == 0) {
                            zzauuVar.zzh(this.zzd.zza, r7, r22, z3);
                            this.zzd.zzv(z3 ? 1 : 0);
                            this.zzm = this.zzd.zzi();
                            this.zzc.zzv(z3 ? 1 : 0);
                            zzaveVar.zzb(this.zzc, 4);
                            this.zzl += 4;
                            r11 += r7;
                        } else {
                            int zzd4 = zzaveVar.zzd(zzauuVar, r8, z3);
                            this.zzl += zzd4;
                            this.zzm -= zzd4;
                            z3 = false;
                        }
                    }
                }
                int r20 = r11;
                zzawn zzawnVar3 = zzawhVar2.zzb;
                zzaveVar.zzc(zzawnVar3.zze[r52], zzawnVar3.zzf[r52], r20, 0, null);
                zzawhVar2.zzd++;
                this.zzl = 0;
                this.zzm = 0;
                return 0;
            } else {
                long j4 = this.zzi;
                int r6 = this.zzj;
                long j5 = j4 - r6;
                long zzd5 = zzauuVar.zzd() + j5;
                zzbag zzbagVar = this.zzk;
                if (zzbagVar != null) {
                    zzauuVar.zzh(zzbagVar.zza, r6, (int) j5, false);
                    if (this.zzh == zzavs.zzd) {
                        zzbag zzbagVar2 = this.zzk;
                        zzbagVar2.zzv(8);
                        if (zzbagVar2.zze() == zzb) {
                            z2 = true;
                            break;
                        }
                        zzbagVar2.zzw(4);
                        while (zzbagVar2.zza() > 0) {
                            if (zzbagVar2.zze() == zzb) {
                                z2 = true;
                                break;
                            }
                        }
                        z2 = false;
                        this.zzq = z2;
                    } else if (!this.zzf.isEmpty()) {
                        ((zzavq) this.zzf.peek()).zzd(new zzavr(this.zzh, this.zzk));
                    }
                } else if (j5 < PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
                    zzauuVar.zzi((int) j5, false);
                } else {
                    zzavaVar.zza = zzauuVar.zzd() + j5;
                    z = true;
                    zzi(zzd5);
                    if (z && this.zzg != 2) {
                        return 1;
                    }
                }
                z = false;
                zzi(zzd5);
                if (z) {
                    return 1;
                }
                continue;
            }
        }
    }
}
