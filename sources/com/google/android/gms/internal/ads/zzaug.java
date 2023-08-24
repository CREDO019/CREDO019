package com.google.android.gms.internal.ads;

import java.nio.ShortBuffer;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaug {
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private final int zze;
    private final short[] zzf;
    private int zzg;
    private short[] zzh;
    private int zzi;
    private short[] zzj;
    private int zzk;
    private short[] zzl;
    private int zzq;
    private int zzr;
    private int zzs;
    private int zzt;
    private int zzv;
    private int zzw;
    private int zzx;
    private int zzm = 0;
    private int zzn = 0;
    private int zzu = 0;
    private float zzo = 1.0f;
    private float zzp = 1.0f;

    public zzaug(int r2, int r3) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r2 / 400;
        int r22 = r2 / 65;
        this.zzd = r22;
        int r23 = r22 + r22;
        this.zze = r23;
        this.zzf = new short[r23];
        this.zzg = r23;
        int r32 = r3 * r23;
        this.zzh = new short[r32];
        this.zzi = r23;
        this.zzj = new short[r32];
        this.zzk = r23;
        this.zzl = new short[r32];
    }

    private final int zzg(short[] sArr, int r11, int r12, int r13) {
        int r112 = r11 * this.zzb;
        int r0 = 1;
        int r2 = 255;
        int r3 = 0;
        int r4 = 0;
        while (r12 <= r13) {
            int r6 = 0;
            for (int r5 = 0; r5 < r12; r5++) {
                short s = sArr[r112 + r5];
                short s2 = sArr[r112 + r12 + r5];
                r6 += s >= s2 ? s - s2 : s2 - s;
            }
            int r52 = r6 * r3;
            int r7 = r0 * r12;
            if (r52 < r7) {
                r0 = r6;
            }
            if (r52 < r7) {
                r3 = r12;
            }
            int r53 = r6 * r2;
            int r72 = r4 * r12;
            if (r53 > r72) {
                r4 = r6;
            }
            if (r53 > r72) {
                r2 = r12;
            }
            r12++;
        }
        this.zzw = r0 / r3;
        this.zzx = r4 / r2;
        return r3;
    }

    private final void zzh(short[] sArr, int r5, int r6) {
        zzk(r6);
        int r0 = this.zzb;
        System.arraycopy(sArr, r5 * r0, this.zzj, this.zzr * r0, r0 * r6);
        this.zzr += r6;
    }

    private final void zzi(short[] sArr, int r8, int r9) {
        int r0 = this.zze / r9;
        int r1 = this.zzb;
        int r92 = r9 * r1;
        int r82 = r8 * r1;
        for (int r2 = 0; r2 < r0; r2++) {
            int r4 = 0;
            for (int r3 = 0; r3 < r92; r3++) {
                r4 += sArr[(r2 * r92) + r82 + r3];
            }
            this.zzf[r2] = (short) (r4 / r92);
        }
    }

    private final void zzj(int r3) {
        int r0 = this.zzq;
        int r1 = this.zzg;
        if (r0 + r3 > r1) {
            int r12 = r1 + (r1 / 2) + r3;
            this.zzg = r12;
            this.zzh = Arrays.copyOf(this.zzh, r12 * this.zzb);
        }
    }

    private final void zzk(int r3) {
        int r0 = this.zzr;
        int r1 = this.zzi;
        if (r0 + r3 > r1) {
            int r12 = r1 + (r1 / 2) + r3;
            this.zzi = r12;
            this.zzj = Arrays.copyOf(this.zzj, r12 * this.zzb);
        }
    }

    private static void zzl(int r8, int r9, short[] sArr, int r11, short[] sArr2, int r13, short[] sArr3, int r15) {
        for (int r1 = 0; r1 < r9; r1++) {
            int r2 = (r11 * r9) + r1;
            int r3 = (r15 * r9) + r1;
            int r4 = (r13 * r9) + r1;
            for (int r5 = 0; r5 < r8; r5++) {
                sArr[r2] = (short) (((sArr2[r4] * (r8 - r5)) + (sArr3[r3] * r5)) / r8);
                r2 += r9;
                r4 += r9;
                r3 += r9;
            }
        }
    }

    public final int zza() {
        return this.zzr;
    }

    public final void zzb(ShortBuffer shortBuffer) {
        int min = Math.min(shortBuffer.remaining() / this.zzb, this.zzr);
        shortBuffer.put(this.zzj, 0, this.zzb * min);
        int r5 = this.zzr - min;
        this.zzr = r5;
        short[] sArr = this.zzj;
        int r2 = this.zzb;
        System.arraycopy(sArr, min * r2, sArr, 0, r5 * r2);
    }

    public final void zzc() {
        int r4;
        int r0 = this.zzq;
        float f = this.zzo;
        float f2 = this.zzp;
        int r3 = this.zzr + ((int) ((((r0 / (f / f2)) + this.zzs) / f2) + 0.5f));
        int r1 = this.zze;
        zzj(r1 + r1 + r0);
        int r2 = 0;
        while (true) {
            int r42 = this.zze;
            r4 = r42 + r42;
            int r5 = this.zzb;
            if (r2 >= r4 * r5) {
                break;
            }
            this.zzh[(r5 * r0) + r2] = 0;
            r2++;
        }
        this.zzq += r4;
        zzm();
        if (this.zzr > r3) {
            this.zzr = r3;
        }
        this.zzq = 0;
        this.zzt = 0;
        this.zzs = 0;
    }

    public final void zzd(ShortBuffer shortBuffer) {
        int remaining = shortBuffer.remaining();
        int r1 = this.zzb;
        int r0 = remaining / r1;
        int r12 = r1 * r0;
        zzj(r0);
        shortBuffer.get(this.zzh, this.zzq * this.zzb, (r12 + r12) / 2);
        this.zzq += r0;
        zzm();
    }

    public final void zze(float f) {
        this.zzp = f;
    }

    public final void zzf(float f) {
        this.zzo = f;
    }

    private final void zzm() {
        int r10;
        int r18;
        int r13;
        int r132;
        int r4;
        int r5;
        int r42;
        int r7;
        int r1 = this.zzr;
        float f = this.zzo / this.zzp;
        double d = f;
        int r72 = 1;
        if (d > 1.00001d || d < 0.99999d) {
            int r8 = this.zzq;
            if (r8 >= this.zze) {
                int r9 = 0;
                while (true) {
                    int r102 = this.zzt;
                    if (r102 > 0) {
                        int min = Math.min(this.zze, r102);
                        zzh(this.zzh, r9, min);
                        this.zzt -= min;
                        r9 += min;
                    } else {
                        short[] sArr = this.zzh;
                        int r11 = this.zza;
                        int r112 = r11 > 4000 ? r11 / 4000 : 1;
                        if (this.zzb == r72 && r112 == r72) {
                            r10 = zzg(sArr, r9, this.zzc, this.zzd);
                        } else {
                            zzi(sArr, r9, r112);
                            int zzg = zzg(this.zzf, 0, this.zzc / r112, this.zzd / r112);
                            if (r112 != r72) {
                                int r12 = zzg * r112;
                                int r113 = r112 * 4;
                                int r133 = r12 - r113;
                                int r122 = r12 + r113;
                                int r114 = this.zzc;
                                if (r133 < r114) {
                                    r133 = r114;
                                }
                                int r115 = this.zzd;
                                if (r122 > r115) {
                                    r122 = r115;
                                }
                                if (this.zzb == r72) {
                                    r10 = zzg(sArr, r9, r133, r122);
                                } else {
                                    zzi(sArr, r9, r72);
                                    r10 = zzg(this.zzf, 0, r133, r122);
                                }
                            } else {
                                r10 = zzg;
                            }
                        }
                        int r116 = this.zzw;
                        int r15 = (r116 == 0 || (r132 = this.zzu) == 0 || this.zzx > r116 * 3 || r116 + r116 <= this.zzv * 3) ? r10 : r132;
                        this.zzv = r116;
                        this.zzu = r10;
                        if (d > 1.0d) {
                            short[] sArr2 = this.zzh;
                            if (f >= 2.0f) {
                                r13 = (int) (r15 / ((-1.0f) + f));
                            } else {
                                this.zzt = (int) ((r15 * (2.0f - f)) / ((-1.0f) + f));
                                r13 = r15;
                            }
                            zzk(r13);
                            int r182 = r13;
                            zzl(r13, this.zzb, this.zzj, this.zzr, sArr2, r9, sArr2, r9 + r15);
                            this.zzr += r182;
                            r9 += r15 + r182;
                        } else {
                            int r73 = r15;
                            short[] sArr3 = this.zzh;
                            if (f < 0.5f) {
                                r18 = (int) ((r73 * f) / (1.0f - f));
                            } else {
                                this.zzt = (int) ((r73 * ((f + f) - 1.0f)) / (1.0f - f));
                                r18 = r73;
                            }
                            int r14 = r73 + r18;
                            zzk(r14);
                            int r103 = this.zzb;
                            System.arraycopy(sArr3, r9 * r103, this.zzj, this.zzr * r103, r103 * r73);
                            zzl(r18, this.zzb, this.zzj, this.zzr + r73, sArr3, r73 + r9, sArr3, r9);
                            this.zzr += r14;
                            r9 += r18;
                        }
                    }
                    if (this.zze + r9 > r8) {
                        break;
                    }
                    r72 = 1;
                }
                int r2 = this.zzq - r9;
                short[] sArr4 = this.zzh;
                int r43 = this.zzb;
                System.arraycopy(sArr4, r9 * r43, sArr4, 0, r43 * r2);
                this.zzq = r2;
            }
        } else {
            zzh(this.zzh, 0, this.zzq);
            this.zzq = 0;
        }
        float f2 = this.zzp;
        if (f2 == 1.0f || this.zzr == r1) {
            return;
        }
        int r3 = this.zza;
        int r22 = (int) (r3 / f2);
        while (true) {
            if (r22 <= 16384 && r3 <= 16384) {
                break;
            }
            r22 /= 2;
            r3 /= 2;
        }
        int r44 = this.zzr - r1;
        int r52 = this.zzs;
        int r74 = this.zzk;
        if (r52 + r44 > r74) {
            int r75 = r74 + (r74 / 2) + r44;
            this.zzk = r75;
            this.zzl = Arrays.copyOf(this.zzl, r75 * this.zzb);
        }
        short[] sArr5 = this.zzj;
        int r76 = this.zzb;
        System.arraycopy(sArr5, r1 * r76, this.zzl, this.zzs * r76, r76 * r44);
        this.zzr = r1;
        this.zzs += r44;
        int r16 = 0;
        while (true) {
            r4 = this.zzs;
            r5 = r4 - 1;
            if (r16 >= r5) {
                break;
            }
            while (true) {
                r42 = this.zzm + 1;
                r7 = this.zzn;
                if (r42 * r22 <= r7 * r3) {
                    break;
                }
                zzk(1);
                int r45 = 0;
                while (true) {
                    int r53 = this.zzb;
                    if (r45 < r53) {
                        short[] sArr6 = this.zzj;
                        int r82 = this.zzr;
                        short[] sArr7 = this.zzl;
                        int r104 = (r16 * r53) + r45;
                        short s = sArr7[r104];
                        short s2 = sArr7[r104 + r53];
                        int r105 = this.zzn;
                        int r123 = this.zzm;
                        int r134 = (r123 + 1) * r22;
                        int r106 = r134 - (r105 * r3);
                        int r135 = r134 - (r123 * r22);
                        sArr6[(r82 * r53) + r45] = (short) (((s * r106) + ((r135 - r106) * s2)) / r135);
                        r45++;
                    }
                }
                this.zzn++;
                this.zzr++;
            }
            this.zzm = r42;
            if (r42 == r3) {
                this.zzm = 0;
                zzazy.zze(r7 == r22);
                this.zzn = 0;
            }
            r16++;
        }
        if (r5 != 0) {
            short[] sArr8 = this.zzl;
            int r23 = this.zzb;
            System.arraycopy(sArr8, r5 * r23, sArr8, 0, (r4 - r5) * r23);
            this.zzs -= r5;
        }
    }
}
