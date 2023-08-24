package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzvs {
    private final String[] zza;
    private final int[] zzb;
    private final zzue[] zzc;
    private final int[] zzd;
    private final int[][][] zze;
    private final zzue zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzvs(String[] strArr, int[] r2, zzue[] zzueVarArr, int[] r4, int[][][] r5, zzue zzueVar) {
        this.zza = strArr;
        this.zzb = r2;
        this.zzc = zzueVarArr;
        this.zze = r5;
        this.zzd = r4;
        this.zzf = zzueVar;
    }

    public final int zza(int r9, int r10, boolean z) {
        int r11 = this.zzc[r9].zzb(r10).zzb;
        int[] r0 = new int[1];
        int r1 = 0;
        int r3 = 0;
        for (int r2 = 0; r2 <= 0; r2++) {
            if ((this.zze[r9][r10][r2] & 7) == 4) {
                r0[r3] = r2;
                r3++;
            }
        }
        int[] copyOf = Arrays.copyOf(r0, r3);
        String str = null;
        boolean z2 = false;
        int r32 = 0;
        int r4 = 16;
        while (r1 < copyOf.length) {
            String str2 = this.zzc[r9].zzb(r10).zzb(copyOf[r1]).zzm;
            int r7 = r32 + 1;
            if (r32 != 0) {
                z2 |= !zzel.zzT(str, str2);
            } else {
                str = str2;
            }
            r4 = Math.min(r4, this.zze[r9][r10][r1] & 24);
            r1++;
            r32 = r7;
        }
        return z2 ? Math.min(r4, this.zzd[r9]) : r4;
    }

    public final int zzb(int r2, int r3, int r4) {
        return this.zze[r2][r3][r4];
    }

    public final int zzc(int r2) {
        return this.zzb[r2];
    }

    public final zzue zzd(int r2) {
        return this.zzc[r2];
    }

    public final zzue zze() {
        return this.zzf;
    }
}
