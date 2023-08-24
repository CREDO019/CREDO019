package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Random;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zztz {
    private final Random zza;
    private final int[] zzb;
    private final int[] zzc;

    public zztz(int r2) {
        this(0, new Random());
    }

    public final int zza() {
        int[] r0 = this.zzb;
        if (r0.length > 0) {
            return r0[0];
        }
        return -1;
    }

    public final int zzb() {
        int[] r0 = this.zzb;
        int length = r0.length;
        if (length > 0) {
            return r0[length - 1];
        }
        return -1;
    }

    public final int zzc() {
        return this.zzb.length;
    }

    public final int zzd(int r3) {
        int r32 = this.zzc[r3] + 1;
        int[] r0 = this.zzb;
        if (r32 < r0.length) {
            return r0[r32];
        }
        return -1;
    }

    public final int zze(int r2) {
        int r22 = this.zzc[r2] - 1;
        if (r22 >= 0) {
            return this.zzb[r22];
        }
        return -1;
    }

    public final zztz zzf() {
        return new zztz(0, new Random(this.zza.nextLong()));
    }

    public final zztz zzg(int r8, int r9) {
        int[] r82 = new int[r9];
        int[] r0 = new int[r9];
        int r1 = 0;
        int r2 = 0;
        while (r2 < r9) {
            r82[r2] = this.zza.nextInt(this.zzb.length + 1);
            int r3 = r2 + 1;
            int nextInt = this.zza.nextInt(r3);
            r0[r2] = r0[nextInt];
            r0[nextInt] = r2;
            r2 = r3;
        }
        Arrays.sort(r82);
        int[] r22 = new int[this.zzb.length + r9];
        int r32 = 0;
        int r4 = 0;
        while (true) {
            int[] r5 = this.zzb;
            if (r1 < r5.length + r9) {
                if (r32 >= r9 || r4 != r82[r32]) {
                    int r6 = r4 + 1;
                    int r42 = r5[r4];
                    r22[r1] = r42;
                    if (r42 >= 0) {
                        r22[r1] = r42 + r9;
                    }
                    r4 = r6;
                } else {
                    r22[r1] = r0[r32];
                    r32++;
                }
                r1++;
            } else {
                return new zztz(r22, new Random(this.zza.nextLong()));
            }
        }
    }

    public final zztz zzh(int r5, int r6) {
        int[] r52 = new int[this.zzb.length - r6];
        int r0 = 0;
        int r1 = 0;
        while (true) {
            int[] r2 = this.zzb;
            if (r0 < r2.length) {
                int r22 = r2[r0];
                if (r22 < 0 || r22 >= r6) {
                    int r3 = r0 - r1;
                    if (r22 >= 0) {
                        r22 -= r6;
                    }
                    r52[r3] = r22;
                } else {
                    r1++;
                }
                r0++;
            } else {
                return new zztz(r52, new Random(this.zza.nextLong()));
            }
        }
    }

    private zztz(int r1, Random random) {
        this(new int[0], random);
    }

    private zztz(int[] r3, Random random) {
        this.zzb = r3;
        this.zza = random;
        this.zzc = new int[r3.length];
        for (int r4 = 0; r4 < r3.length; r4++) {
            this.zzc[r3[r4]] = r4;
        }
    }
}
