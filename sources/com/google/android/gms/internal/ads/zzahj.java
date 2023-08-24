package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzahj {
    private final zzaam zza;
    private long zzb;
    private boolean zzc;
    private int zzd;
    private long zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;
    private long zzk;
    private long zzl;
    private boolean zzm;

    public zzahj(zzaam zzaamVar) {
        this.zza = zzaamVar;
    }

    private final void zze(int r9) {
        long j = this.zzl;
        if (j == C1856C.TIME_UNSET) {
            return;
        }
        boolean z = this.zzm;
        long j2 = this.zzb;
        long j3 = this.zzk;
        this.zza.zzs(j, z ? 1 : 0, (int) (j2 - j3), r9, null);
    }

    public final void zza(long j, int r5, boolean z) {
        if (this.zzj && this.zzg) {
            this.zzm = this.zzc;
            this.zzj = false;
        } else if (this.zzh || this.zzg) {
            if (z && this.zzi) {
                zze(r5 + ((int) (j - this.zzb)));
            }
            this.zzk = this.zzb;
            this.zzl = this.zze;
            this.zzm = this.zzc;
            this.zzi = true;
        }
    }

    public final void zzb(byte[] bArr, int r4, int r5) {
        if (this.zzf) {
            int r0 = this.zzd;
            int r1 = (r4 + 2) - r0;
            if (r1 >= r5) {
                this.zzd = r0 + (r5 - r4);
                return;
            }
            this.zzg = (bArr[r1] & 128) != 0;
            this.zzf = false;
        }
    }

    public final void zzc() {
        this.zzf = false;
        this.zzg = false;
        this.zzh = false;
        this.zzi = false;
        this.zzj = false;
    }

    public final void zzd(long j, int r4, int r5, long j2, boolean z) {
        boolean z2 = false;
        this.zzg = false;
        this.zzh = false;
        this.zze = j2;
        this.zzd = 0;
        this.zzb = j;
        if (r5 >= 32 && r5 != 40) {
            if (this.zzi && !this.zzj) {
                if (z) {
                    zze(r4);
                }
                this.zzi = false;
            }
            if (r5 <= 35 || r5 == 39) {
                this.zzh = !this.zzj;
                this.zzj = true;
            }
        }
        boolean z3 = r5 >= 16 && r5 <= 21;
        this.zzc = z3;
        this.zzf = (z3 || r5 <= 9) ? true : true;
    }
}
