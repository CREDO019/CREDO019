package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzagy implements zzagz {
    private final List zza;
    private final zzaam[] zzb;
    private boolean zzc;
    private int zzd;
    private int zze;
    private long zzf = C1856C.TIME_UNSET;

    public zzagy(List list) {
        this.zza = list;
        this.zzb = new zzaam[list.size()];
    }

    private final boolean zzf(zzed zzedVar, int r4) {
        if (zzedVar.zza() == 0) {
            return false;
        }
        if (zzedVar.zzk() != r4) {
            this.zzc = false;
        }
        this.zzd--;
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzed zzedVar) {
        zzaam[] zzaamVarArr;
        if (this.zzc) {
            if (this.zzd != 2 || zzf(zzedVar, 32)) {
                if (this.zzd != 1 || zzf(zzedVar, 0)) {
                    int zzc = zzedVar.zzc();
                    int zza = zzedVar.zza();
                    for (zzaam zzaamVar : this.zzb) {
                        zzedVar.zzF(zzc);
                        zzaamVar.zzq(zzedVar, zza);
                    }
                    this.zze += zza;
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzb(zzzi zzziVar, zzail zzailVar) {
        for (int r0 = 0; r0 < this.zzb.length; r0++) {
            zzaii zzaiiVar = (zzaii) this.zza.get(r0);
            zzailVar.zzc();
            zzaam zzv = zzziVar.zzv(zzailVar.zza(), 3);
            zzad zzadVar = new zzad();
            zzadVar.zzH(zzailVar.zzb());
            zzadVar.zzS(MimeTypes.APPLICATION_DVBSUBS);
            zzadVar.zzI(Collections.singletonList(zzaiiVar.zzb));
            zzadVar.zzK(zzaiiVar.zza);
            zzv.zzk(zzadVar.zzY());
            this.zzb[r0] = zzv;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzc() {
        if (this.zzc) {
            if (this.zzf != C1856C.TIME_UNSET) {
                for (zzaam zzaamVar : this.zzb) {
                    zzaamVar.zzs(this.zzf, 1, this.zze, 0, null);
                }
            }
            this.zzc = false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzd(long j, int r5) {
        if ((r5 & 4) == 0) {
            return;
        }
        this.zzc = true;
        if (j != C1856C.TIME_UNSET) {
            this.zzf = j;
        }
        this.zze = 0;
        this.zzd = 2;
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zze() {
        this.zzc = false;
        this.zzf = C1856C.TIME_UNSET;
    }
}
