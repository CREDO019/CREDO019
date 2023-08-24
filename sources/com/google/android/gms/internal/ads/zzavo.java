package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzavo {
    private final zzbag zza = new zzbag(8);
    private int zzb;

    private final long zzb(zzauu zzauuVar) throws IOException, InterruptedException {
        int r1 = 0;
        zzauuVar.zzg(this.zza.zza, 0, 1, false);
        int r0 = this.zza.zza[0] & 255;
        if (r0 != 0) {
            int r3 = 128;
            int r4 = 0;
            while ((r0 & r3) == 0) {
                r3 >>= 1;
                r4++;
            }
            int r02 = r0 & (~r3);
            zzauuVar.zzg(this.zza.zza, 1, r4, false);
            while (r1 < r4) {
                r1++;
                r02 = (this.zza.zza[r1] & 255) + (r02 << 8);
            }
            this.zzb += r4 + 1;
            return r02;
        }
        return Long.MIN_VALUE;
    }

    public final boolean zza(zzauu zzauuVar) throws IOException, InterruptedException {
        long zzb;
        int r3;
        long zzc = zzauuVar.zzc();
        long j = 1024;
        int r6 = (zzc > (-1L) ? 1 : (zzc == (-1L) ? 0 : -1));
        if (r6 != 0 && zzc <= 1024) {
            j = zzc;
        }
        int r32 = (int) j;
        zzauuVar.zzg(this.zza.zza, 0, 4, false);
        long zzm = this.zza.zzm();
        this.zzb = 4;
        while (zzm != 440786851) {
            int r5 = this.zzb + 1;
            this.zzb = r5;
            if (r5 == r32) {
                return false;
            }
            zzauuVar.zzg(this.zza.zza, 0, 1, false);
            zzm = ((zzm << 8) & (-256)) | (this.zza.zza[0] & 255);
        }
        long zzb2 = zzb(zzauuVar);
        long j2 = this.zzb;
        if (zzb2 != Long.MIN_VALUE && (r6 == 0 || j2 + zzb2 < zzc)) {
            while (true) {
                int r33 = (this.zzb > (j2 + zzb2) ? 1 : (this.zzb == (j2 + zzb2) ? 0 : -1));
                if (r33 < 0) {
                    if (zzb(zzauuVar) == Long.MIN_VALUE || (zzb = zzb(zzauuVar)) < 0) {
                        return false;
                    }
                    if (r3 != 0) {
                        zzauuVar.zzf((int) zzb, false);
                        this.zzb = (int) (this.zzb + zzb);
                    }
                } else if (r33 == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
