package com.google.android.gms.internal.ads;

import android.util.Log;
import androidx.core.view.MotionEventCompat;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzagv implements zzagz {
    private static final byte[] zza = {73, 68, 51};
    private final boolean zzb;
    private final zzec zzc = new zzec(new byte[7], 7);
    private final zzed zzd = new zzed(Arrays.copyOf(zza, 10));
    private final String zze;
    private String zzf;
    private zzaam zzg;
    private zzaam zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private boolean zzl;
    private boolean zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private boolean zzq;
    private long zzr;
    private int zzs;
    private long zzt;
    private zzaam zzu;
    private long zzv;

    public zzagv(boolean z, String str) {
        zzh();
        this.zzn = -1;
        this.zzo = -1;
        this.zzr = C1856C.TIME_UNSET;
        this.zzt = C1856C.TIME_UNSET;
        this.zzb = z;
        this.zze = str;
    }

    public static boolean zzf(int r1) {
        return (r1 & 65526) == 65520;
    }

    private final void zzg() {
        this.zzm = false;
        zzh();
    }

    private final void zzh() {
        this.zzi = 0;
        this.zzj = 0;
        this.zzk = 256;
    }

    private final void zzi() {
        this.zzi = 3;
        this.zzj = 0;
    }

    private final void zzj(zzaam zzaamVar, long j, int r5, int r6) {
        this.zzi = 4;
        this.zzj = r5;
        this.zzu = zzaamVar;
        this.zzv = j;
        this.zzs = r6;
    }

    private final boolean zzk(zzed zzedVar, byte[] bArr, int r5) {
        int min = Math.min(zzedVar.zza(), r5 - this.zzj);
        zzedVar.zzB(bArr, this.zzj, min);
        int r3 = this.zzj + min;
        this.zzj = r3;
        return r3 == r5;
    }

    private static final boolean zzl(byte b, byte b2) {
        return zzf((b2 & 255) | MotionEventCompat.ACTION_POINTER_INDEX_MASK);
    }

    private static final boolean zzm(zzed zzedVar, byte[] bArr, int r4) {
        if (zzedVar.zza() < r4) {
            return false;
        }
        zzedVar.zzB(bArr, 0, r4);
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzed zzedVar) throws zzbu {
        int r13;
        int r11;
        int r9;
        Objects.requireNonNull(this.zzg);
        int r0 = zzel.zza;
        while (zzedVar.zza() > 0) {
            int r02 = this.zzi;
            int r1 = 13;
            int r92 = 2;
            if (r02 == 0) {
                byte[] zzH = zzedVar.zzH();
                int zzc = zzedVar.zzc();
                int zzd = zzedVar.zzd();
                while (true) {
                    if (zzc < zzd) {
                        r13 = zzc + 1;
                        r11 = zzH[zzc] & 255;
                        if (this.zzk == 512 && zzl((byte) -1, (byte) r11)) {
                            if (!this.zzm) {
                                int r14 = r13 - 2;
                                zzedVar.zzF(r14 + 1);
                                if (zzm(zzedVar, this.zzc.zza, 1)) {
                                    this.zzc.zzh(4);
                                    int zzc2 = this.zzc.zzc(1);
                                    int r8 = this.zzn;
                                    if (r8 == -1 || zzc2 == r8) {
                                        if (this.zzo != -1) {
                                            if (!zzm(zzedVar, this.zzc.zza, 1)) {
                                                break;
                                            }
                                            this.zzc.zzh(r92);
                                            if (this.zzc.zzc(4) == this.zzo) {
                                                zzedVar.zzF(r14 + 2);
                                            }
                                        }
                                        if (!zzm(zzedVar, this.zzc.zza, 4)) {
                                            break;
                                        }
                                        this.zzc.zzh(14);
                                        int zzc3 = this.zzc.zzc(r1);
                                        if (zzc3 >= 7) {
                                            byte[] zzH2 = zzedVar.zzH();
                                            int zzd2 = zzedVar.zzd();
                                            int r142 = r14 + zzc3;
                                            if (r142 < zzd2) {
                                                byte b = zzH2[r142];
                                                if (b != -1) {
                                                    if (b == 73) {
                                                        int r82 = r142 + 1;
                                                        if (r82 == zzd2) {
                                                            break;
                                                        } else if (zzH2[r82] == 68) {
                                                            int r143 = r142 + 2;
                                                            if (r143 == zzd2) {
                                                                break;
                                                            } else if (zzH2[r143] == 51) {
                                                                break;
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    int r144 = r142 + 1;
                                                    if (r144 == zzd2) {
                                                        break;
                                                    }
                                                    byte b2 = zzH2[r144];
                                                    if (zzl((byte) -1, b2) && ((b2 & 8) >> 3) == zzc2) {
                                                        break;
                                                    }
                                                }
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else {
                                break;
                            }
                        }
                        int r12 = this.zzk;
                        int r83 = r12 | r11;
                        if (r83 == 329) {
                            r9 = 768;
                        } else if (r83 == 511) {
                            r9 = 512;
                        } else if (r83 == 836) {
                            r9 = 1024;
                        } else if (r83 == 1075) {
                            this.zzi = 2;
                            this.zzj = 3;
                            this.zzs = 0;
                            this.zzd.zzF(0);
                            zzedVar.zzF(r13);
                            break;
                        } else if (r12 != 256) {
                            this.zzk = 256;
                            zzc = r13 - 1;
                            r1 = 13;
                            r92 = 2;
                        } else {
                            zzc = r13;
                            r1 = 13;
                            r92 = 2;
                        }
                        this.zzk = r9;
                        zzc = r13;
                        r1 = 13;
                        r92 = 2;
                    } else {
                        zzedVar.zzF(zzc);
                        break;
                    }
                }
                this.zzp = (r11 & 8) >> 3;
                this.zzl = 1 == ((r11 & 1) ^ 1);
                if (this.zzm) {
                    zzi();
                } else {
                    this.zzi = 1;
                    this.zzj = 0;
                }
                zzedVar.zzF(r13);
            } else if (r02 != 1) {
                if (r02 != 2) {
                    if (r02 != 3) {
                        int min = Math.min(zzedVar.zza(), this.zzs - this.zzj);
                        this.zzu.zzq(zzedVar, min);
                        int r15 = this.zzj + min;
                        this.zzj = r15;
                        int r122 = this.zzs;
                        if (r15 == r122) {
                            long j = this.zzt;
                            if (j != C1856C.TIME_UNSET) {
                                this.zzu.zzs(j, 1, r122, 0, null);
                                this.zzt += this.zzv;
                            }
                            zzh();
                        }
                    } else {
                        if (zzk(zzedVar, this.zzc.zza, true != this.zzl ? 5 : 7)) {
                            this.zzc.zzh(0);
                            if (!this.zzq) {
                                int zzc4 = this.zzc.zzc(2) + 1;
                                if (zzc4 != 2) {
                                    Log.w("AdtsReader", "Detected audio object type: " + zzc4 + ", but assuming AAC LC.");
                                }
                                this.zzc.zzj(5);
                                int zzc5 = this.zzc.zzc(3);
                                int r5 = this.zzo;
                                int r112 = zzyd.zza;
                                byte[] bArr = {(byte) (((r5 >> 1) & 7) | 16), (byte) (((zzc5 << 3) & 120) | ((r5 << 7) & 128))};
                                zzyc zza2 = zzyd.zza(bArr);
                                zzad zzadVar = new zzad();
                                zzadVar.zzH(this.zzf);
                                zzadVar.zzS(MimeTypes.AUDIO_AAC);
                                zzadVar.zzx(zza2.zzc);
                                zzadVar.zzw(zza2.zzb);
                                zzadVar.zzT(zza2.zza);
                                zzadVar.zzI(Collections.singletonList(bArr));
                                zzadVar.zzK(this.zze);
                                zzaf zzY = zzadVar.zzY();
                                this.zzr = 1024000000 / zzY.zzA;
                                this.zzg.zzk(zzY);
                                this.zzq = true;
                            } else {
                                this.zzc.zzj(10);
                            }
                            this.zzc.zzj(4);
                            int zzc6 = this.zzc.zzc(13) - 7;
                            if (this.zzl) {
                                zzc6 -= 2;
                            }
                            zzj(this.zzg, this.zzr, 0, zzc6);
                        }
                    }
                } else if (zzk(zzedVar, this.zzd.zzH(), 10)) {
                    this.zzh.zzq(this.zzd, 10);
                    this.zzd.zzF(6);
                    zzj(this.zzh, 0L, 10, 10 + this.zzd.zzj());
                }
            } else if (zzedVar.zza() != 0) {
                zzec zzecVar = this.zzc;
                zzecVar.zza[0] = zzedVar.zzH()[zzedVar.zzc()];
                zzecVar.zzh(2);
                int zzc7 = this.zzc.zzc(4);
                int r16 = this.zzo;
                if (r16 == -1 || zzc7 == r16) {
                    if (!this.zzm) {
                        this.zzm = true;
                        this.zzn = this.zzp;
                        this.zzo = zzc7;
                    }
                    zzi();
                } else {
                    zzg();
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzb(zzzi zzziVar, zzail zzailVar) {
        zzailVar.zzc();
        this.zzf = zzailVar.zzb();
        zzaam zzv = zzziVar.zzv(zzailVar.zza(), 1);
        this.zzg = zzv;
        this.zzu = zzv;
        if (!this.zzb) {
            this.zzh = new zzze();
            return;
        }
        zzailVar.zzc();
        zzaam zzv2 = zzziVar.zzv(zzailVar.zza(), 5);
        this.zzh = zzv2;
        zzad zzadVar = new zzad();
        zzadVar.zzH(zzailVar.zzb());
        zzadVar.zzS(MimeTypes.APPLICATION_ID3);
        zzv2.zzk(zzadVar.zzY());
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzc() {
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzd(long j, int r5) {
        if (j != C1856C.TIME_UNSET) {
            this.zzt = j;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zze() {
        this.zzt = C1856C.TIME_UNSET;
        zzg();
    }
}
