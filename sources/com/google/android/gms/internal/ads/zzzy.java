package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzzy {
    public int zza;
    public String zzb;
    public int zzc;
    public int zzd;
    public int zze;
    public int zzf;
    public int zzg;

    public final boolean zza(int r10) {
        boolean zzm;
        int r0;
        int r4;
        int r5;
        int r6;
        String[] strArr;
        int[] r1;
        int zzl;
        int[] r02;
        int[] r03;
        int r04;
        int[] r05;
        int[] r06;
        int r07;
        int[] r08;
        zzm = zzzz.zzm(r10);
        if (!zzm || (r0 = (r10 >>> 19) & 3) == 1 || (r4 = (r10 >>> 17) & 3) == 0 || (r5 = (r10 >>> 12) & 15) == 0 || r5 == 15 || (r6 = (r10 >>> 10) & 3) == 3) {
            return false;
        }
        this.zza = r0;
        strArr = zzzz.zza;
        this.zzb = strArr[3 - r4];
        r1 = zzzz.zzb;
        int r12 = r1[r6];
        this.zzd = r12;
        if (r0 == 2) {
            r12 /= 2;
            this.zzd = r12;
        } else if (r0 == 0) {
            r12 /= 4;
            this.zzd = r12;
        }
        int r7 = (r10 >>> 9) & 1;
        zzl = zzzz.zzl(r0, r4);
        this.zzg = zzl;
        if (r4 == 3) {
            if (r0 == 3) {
                r08 = zzzz.zzc;
                r07 = r08[r5 - 1];
            } else {
                r06 = zzzz.zzd;
                r07 = r06[r5 - 1];
            }
            this.zzf = r07;
            this.zzc = (((r07 * 12) / r12) + r7) * 4;
        } else {
            if (r0 == 3) {
                if (r4 == 2) {
                    r05 = zzzz.zze;
                    r04 = r05[r5 - 1];
                } else {
                    r03 = zzzz.zzf;
                    r04 = r03[r5 - 1];
                }
                this.zzf = r04;
                this.zzc = ((r04 * 144) / r12) + r7;
            } else {
                r02 = zzzz.zzg;
                int r09 = r02[r5 - 1];
                this.zzf = r09;
                this.zzc = (((r4 == 1 ? 72 : 144) * r09) / r12) + r7;
            }
        }
        this.zze = ((r10 >> 6) & 3) == 3 ? 1 : 2;
        return true;
    }
}
