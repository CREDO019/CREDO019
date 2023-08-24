package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import com.google.android.exoplayer2.C1856C;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzahh {
    private final zzaam zza;
    private final zzaae zzd;
    private final byte[] zze;
    private int zzf;
    private long zzg;
    private long zzh;
    private long zzl;
    private long zzm;
    private boolean zzn;
    private final SparseArray zzb = new SparseArray();
    private final SparseArray zzc = new SparseArray();
    private final zzahg zzi = new zzahg(null);
    private final zzahg zzj = new zzahg(null);
    private boolean zzk = false;

    public zzahh(zzaam zzaamVar, boolean z, boolean z2) {
        this.zza = zzaamVar;
        byte[] bArr = new byte[128];
        this.zze = bArr;
        this.zzd = new zzaae(bArr, 0, 0);
    }

    public final void zza(zzaaa zzaaaVar) {
        this.zzc.append(zzaaaVar.zza, zzaaaVar);
    }

    public final void zzb(zzaab zzaabVar) {
        this.zzb.append(zzaabVar.zzd, zzaabVar);
    }

    public final void zzc() {
        this.zzk = false;
    }

    public final void zzd(long j, int r3, long j2) {
        this.zzf = r3;
        this.zzh = j2;
        this.zzg = j;
    }

    public final boolean zze(long j, int r17, boolean z, boolean z2) {
        boolean z3 = false;
        if (this.zzf == 9) {
            if (z && this.zzk) {
                long j2 = this.zzg;
                int r11 = r17 + ((int) (j - j2));
                long j3 = this.zzm;
                if (j3 != C1856C.TIME_UNSET) {
                    boolean z4 = this.zzn;
                    long j4 = this.zzl;
                    this.zza.zzs(j3, z4 ? 1 : 0, (int) (j2 - j4), r11, null);
                }
            }
            this.zzl = this.zzg;
            this.zzm = this.zzh;
            this.zzn = false;
            this.zzk = true;
        }
        boolean z5 = this.zzn;
        int r4 = this.zzf;
        if (r4 == 5 || (z2 && r4 == 1)) {
            z3 = true;
        }
        boolean z6 = z5 | z3;
        this.zzn = z6;
        return z6;
    }
}
