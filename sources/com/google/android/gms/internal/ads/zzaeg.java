package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaeg {
    private final zzed zza = new zzed(8);
    private int zzb;

    private final long zzb(zzzg zzzgVar) throws IOException {
        zzyv zzyvVar = (zzyv) zzzgVar;
        int r1 = 0;
        zzyvVar.zzm(this.zza.zzH(), 0, 1, false);
        int r0 = this.zza.zzH()[0] & 255;
        if (r0 != 0) {
            int r3 = 128;
            int r4 = 0;
            while ((r0 & r3) == 0) {
                r3 >>= 1;
                r4++;
            }
            int r02 = r0 & (~r3);
            zzyvVar.zzm(this.zza.zzH(), 1, r4, false);
            while (r1 < r4) {
                r1++;
                r02 = (this.zza.zzH()[r1] & 255) + (r02 << 8);
            }
            this.zzb += r4 + 1;
            return r02;
        }
        return Long.MIN_VALUE;
    }

    public final boolean zza(zzzg zzzgVar) throws IOException {
        long zzb;
        int r4;
        long zzd = zzzgVar.zzd();
        long j = 1024;
        int r7 = (zzd > (-1L) ? 1 : (zzd == (-1L) ? 0 : -1));
        if (r7 != 0 && zzd <= 1024) {
            j = zzd;
        }
        int r42 = (int) j;
        zzyv zzyvVar = (zzyv) zzzgVar;
        zzyvVar.zzm(this.zza.zzH(), 0, 4, false);
        long zzs = this.zza.zzs();
        this.zzb = 4;
        while (zzs != 440786851) {
            int r8 = this.zzb + 1;
            this.zzb = r8;
            if (r8 == r42) {
                return false;
            }
            zzyvVar.zzm(this.zza.zzH(), 0, 1, false);
            zzs = ((zzs << 8) & (-256)) | (this.zza.zzH()[0] & 255);
        }
        long zzb2 = zzb(zzzgVar);
        long j2 = this.zzb;
        if (zzb2 != Long.MIN_VALUE && (r7 == 0 || j2 + zzb2 < zzd)) {
            while (true) {
                int r43 = (this.zzb > (j2 + zzb2) ? 1 : (this.zzb == (j2 + zzb2) ? 0 : -1));
                if (r43 < 0) {
                    if (zzb(zzzgVar) == Long.MIN_VALUE || (zzb = zzb(zzzgVar)) < 0) {
                        return false;
                    }
                    if (r4 != 0) {
                        int r2 = (int) zzb;
                        zzyvVar.zzl(r2, false);
                        this.zzb += r2;
                    }
                } else if (r43 == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
