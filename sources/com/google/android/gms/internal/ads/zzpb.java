package com.google.android.gms.internal.ads;

import java.nio.ShortBuffer;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzpb {
    private final int zza;
    private final int zzb;
    private final float zzc;
    private final float zzd;
    private final float zze;
    private final int zzf;
    private final int zzg;
    private final int zzh;
    private final short[] zzi;
    private short[] zzj;
    private int zzk;
    private short[] zzl;
    private int zzm;
    private short[] zzn;
    private int zzo;
    private int zzp;
    private int zzq;
    private int zzr;
    private int zzs;
    private int zzt;
    private int zzu;
    private int zzv;

    public zzpb(int r1, int r2, float f, float f2, int r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = f;
        this.zzd = f2;
        this.zze = r1 / r5;
        this.zzf = r1 / 400;
        int r12 = r1 / 65;
        this.zzg = r12;
        int r13 = r12 + r12;
        this.zzh = r13;
        this.zzi = new short[r13];
        int r14 = r13 * r2;
        this.zzj = new short[r14];
        this.zzl = new short[r14];
        this.zzn = new short[r14];
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
                r6 += Math.abs(sArr[r112 + r5] - sArr[(r112 + r12) + r5]);
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
        this.zzu = r0 / r3;
        this.zzv = r4 / r2;
        return r3;
    }

    private final void zzh(short[] sArr, int r5, int r6) {
        short[] zzl = zzl(this.zzl, this.zzm, r6);
        this.zzl = zzl;
        int r1 = this.zzb;
        System.arraycopy(sArr, r5 * r1, zzl, this.zzm * r1, r1 * r6);
        this.zzm += r6;
    }

    private final void zzi(short[] sArr, int r8, int r9) {
        int r0 = this.zzh / r9;
        int r1 = this.zzb;
        int r92 = r9 * r1;
        int r82 = r8 * r1;
        for (int r2 = 0; r2 < r0; r2++) {
            int r4 = 0;
            for (int r3 = 0; r3 < r92; r3++) {
                r4 += sArr[(r2 * r92) + r82 + r3];
            }
            this.zzi[r2] = (short) (r4 / r92);
        }
    }

    private static void zzj(int r8, int r9, short[] sArr, int r11, short[] sArr2, int r13, short[] sArr3, int r15) {
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

    private final short[] zzl(short[] sArr, int r4, int r5) {
        int length = sArr.length;
        int r1 = this.zzb;
        int r0 = length / r1;
        return r4 + r5 <= r0 ? sArr : Arrays.copyOf(sArr, (((r0 * 3) / 2) + r5) * r1);
    }

    public final int zza() {
        int r0 = this.zzm * this.zzb;
        return r0 + r0;
    }

    public final int zzb() {
        int r0 = this.zzk * this.zzb;
        return r0 + r0;
    }

    public final void zzc() {
        this.zzk = 0;
        this.zzm = 0;
        this.zzo = 0;
        this.zzp = 0;
        this.zzq = 0;
        this.zzr = 0;
        this.zzs = 0;
        this.zzt = 0;
        this.zzu = 0;
        this.zzv = 0;
    }

    public final void zzd(ShortBuffer shortBuffer) {
        int min = Math.min(shortBuffer.remaining() / this.zzb, this.zzm);
        shortBuffer.put(this.zzl, 0, this.zzb * min);
        int r5 = this.zzm - min;
        this.zzm = r5;
        short[] sArr = this.zzl;
        int r2 = this.zzb;
        System.arraycopy(sArr, min * r2, sArr, 0, r5 * r2);
    }

    public final void zze() {
        int r3;
        int r0 = this.zzk;
        float f = this.zzc;
        float f2 = this.zzd;
        int r4 = this.zzm + ((int) ((((r0 / (f / f2)) + this.zzo) / (this.zze * f2)) + 0.5f));
        short[] sArr = this.zzj;
        int r2 = this.zzh;
        this.zzj = zzl(sArr, r0, r2 + r2 + r0);
        int r22 = 0;
        while (true) {
            int r32 = this.zzh;
            r3 = r32 + r32;
            int r5 = this.zzb;
            if (r22 >= r3 * r5) {
                break;
            }
            this.zzj[(r5 * r0) + r22] = 0;
            r22++;
        }
        this.zzk += r3;
        zzk();
        if (this.zzm > r4) {
            this.zzm = r4;
        }
        this.zzk = 0;
        this.zzr = 0;
        this.zzo = 0;
    }

    public final void zzf(ShortBuffer shortBuffer) {
        int remaining = shortBuffer.remaining();
        int r1 = this.zzb;
        int r0 = remaining / r1;
        int r12 = r1 * r0;
        short[] zzl = zzl(this.zzj, this.zzk, r0);
        this.zzj = zzl;
        shortBuffer.get(zzl, this.zzk * this.zzb, (r12 + r12) / 2);
        this.zzk += r0;
        zzk();
    }

    private final void zzk() {
        int r11;
        int r19;
        int r13;
        int r14;
        int r4;
        int r5;
        int r42;
        int r6;
        int r1 = this.zzm;
        float f = this.zzc;
        float f2 = this.zzd;
        float f3 = f / f2;
        float f4 = this.zze * f2;
        double d = f3;
        float f5 = 1.0f;
        int r8 = 1;
        if (d > 1.00001d || d < 0.99999d) {
            int r9 = this.zzk;
            if (r9 >= this.zzh) {
                int r10 = 0;
                while (true) {
                    int r112 = this.zzr;
                    if (r112 > 0) {
                        int min = Math.min(this.zzh, r112);
                        zzh(this.zzj, r10, min);
                        this.zzr -= min;
                        r10 += min;
                    } else {
                        short[] sArr = this.zzj;
                        int r12 = this.zza;
                        int r122 = r12 > 4000 ? r12 / 4000 : 1;
                        if (this.zzb == r8 && r122 == r8) {
                            r11 = zzg(sArr, r10, this.zzf, this.zzg);
                        } else {
                            zzi(sArr, r10, r122);
                            int zzg = zzg(this.zzi, 0, this.zzf / r122, this.zzg / r122);
                            if (r122 != r8) {
                                int r132 = zzg * r122;
                                int r123 = r122 * 4;
                                int r142 = r132 - r123;
                                int r133 = r132 + r123;
                                int r124 = this.zzf;
                                if (r142 < r124) {
                                    r142 = r124;
                                }
                                int r125 = this.zzg;
                                if (r133 > r125) {
                                    r133 = r125;
                                }
                                if (this.zzb == r8) {
                                    r11 = zzg(sArr, r10, r142, r133);
                                } else {
                                    zzi(sArr, r10, r8);
                                    r11 = zzg(this.zzi, 0, r142, r133);
                                }
                            } else {
                                r11 = zzg;
                            }
                        }
                        int r126 = this.zzu;
                        int r15 = (r126 == 0 || (r14 = this.zzs) == 0 || this.zzv > r126 * 3 || r126 + r126 <= this.zzt * 3) ? r11 : r14;
                        this.zzt = r126;
                        this.zzs = r11;
                        if (d > 1.0d) {
                            short[] sArr2 = this.zzj;
                            if (f3 >= 2.0f) {
                                r13 = (int) (r15 / ((-1.0f) + f3));
                            } else {
                                this.zzr = (int) ((r15 * (2.0f - f3)) / ((-1.0f) + f3));
                                r13 = r15;
                            }
                            short[] zzl = zzl(this.zzl, this.zzm, r13);
                            this.zzl = zzl;
                            int r192 = r13;
                            zzj(r13, this.zzb, zzl, this.zzm, sArr2, r10, sArr2, r10 + r15);
                            this.zzm += r192;
                            r10 += r15 + r192;
                        } else {
                            int r82 = r15;
                            short[] sArr3 = this.zzj;
                            if (f3 < 0.5f) {
                                r19 = (int) ((r82 * f3) / (f5 - f3));
                            } else {
                                this.zzr = (int) ((r82 * ((f3 + f3) - 1.0f)) / (f5 - f3));
                                r19 = r82;
                            }
                            int r143 = r82 + r19;
                            short[] zzl2 = zzl(this.zzl, this.zzm, r143);
                            this.zzl = zzl2;
                            int r127 = this.zzb;
                            System.arraycopy(sArr3, r10 * r127, zzl2, this.zzm * r127, r127 * r82);
                            zzj(r19, this.zzb, this.zzl, this.zzm + r82, sArr3, r10 + r82, sArr3, r10);
                            this.zzm += r143;
                            r10 += r19;
                        }
                    }
                    if (this.zzh + r10 > r9) {
                        break;
                    }
                    f5 = 1.0f;
                    r8 = 1;
                }
                int r2 = this.zzk - r10;
                short[] sArr4 = this.zzj;
                int r52 = this.zzb;
                System.arraycopy(sArr4, r10 * r52, sArr4, 0, r52 * r2);
                this.zzk = r2;
                f5 = 1.0f;
            }
        } else {
            zzh(this.zzj, 0, this.zzk);
            this.zzk = 0;
        }
        if (f4 == f5 || this.zzm == r1) {
            return;
        }
        int r22 = this.zza;
        int r3 = (int) (r22 / f4);
        while (true) {
            if (r3 <= 16384 && r22 <= 16384) {
                break;
            }
            r3 /= 2;
            r22 /= 2;
        }
        int r43 = this.zzm - r1;
        short[] zzl3 = zzl(this.zzn, this.zzo, r43);
        this.zzn = zzl3;
        short[] sArr5 = this.zzl;
        int r83 = this.zzb;
        System.arraycopy(sArr5, r1 * r83, zzl3, this.zzo * r83, r83 * r43);
        this.zzm = r1;
        this.zzo += r43;
        int r16 = 0;
        while (true) {
            r4 = this.zzo;
            r5 = r4 - 1;
            if (r16 >= r5) {
                break;
            }
            while (true) {
                r42 = this.zzp + 1;
                r6 = this.zzq;
                if (r42 * r3 <= r6 * r22) {
                    break;
                }
                this.zzl = zzl(this.zzl, this.zzm, 1);
                int r44 = 0;
                while (true) {
                    int r53 = this.zzb;
                    if (r44 < r53) {
                        short[] sArr6 = this.zzl;
                        int r84 = this.zzm;
                        short[] sArr7 = this.zzn;
                        int r102 = (r16 * r53) + r44;
                        short s = sArr7[r102];
                        short s2 = sArr7[r102 + r53];
                        int r103 = this.zzq;
                        int r128 = this.zzp;
                        int r134 = (r128 + 1) * r3;
                        int r104 = r134 - (r103 * r22);
                        int r135 = r134 - (r128 * r3);
                        sArr6[(r84 * r53) + r44] = (short) (((s * r104) + ((r135 - r104) * s2)) / r135);
                        r44++;
                    }
                }
                this.zzq++;
                this.zzm++;
            }
            this.zzp = r42;
            if (r42 == r22) {
                this.zzp = 0;
                zzdd.zzf(r6 == r3);
                this.zzq = 0;
            }
            r16++;
        }
        if (r5 != 0) {
            short[] sArr8 = this.zzn;
            int r23 = this.zzb;
            System.arraycopy(sArr8, r5 * r23, sArr8, 0, (r4 - r5) * r23);
            this.zzo -= r5;
        }
    }
}
