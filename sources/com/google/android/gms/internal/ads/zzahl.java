package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzahl implements zzagz {
    private zzaam zzb;
    private boolean zzc;
    private int zze;
    private int zzf;
    private final zzed zza = new zzed(10);
    private long zzd = C1856C.TIME_UNSET;

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zza(zzed zzedVar) {
        zzdd.zzb(this.zzb);
        if (this.zzc) {
            int zza = zzedVar.zza();
            int r1 = this.zzf;
            if (r1 < 10) {
                int min = Math.min(zza, 10 - r1);
                System.arraycopy(zzedVar.zzH(), zzedVar.zzc(), this.zza.zzH(), this.zzf, min);
                if (this.zzf + min == 10) {
                    this.zza.zzF(0);
                    if (this.zza.zzk() != 73 || this.zza.zzk() != 68 || this.zza.zzk() != 51) {
                        Log.w("Id3Reader", "Discarding invalid ID3 tag");
                        this.zzc = false;
                        return;
                    }
                    this.zza.zzG(3);
                    this.zze = this.zza.zzj() + 10;
                }
            }
            int min2 = Math.min(zza, this.zze - this.zzf);
            this.zzb.zzq(zzedVar, min2);
            this.zzf += min2;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzb(zzzi zzziVar, zzail zzailVar) {
        zzailVar.zzc();
        zzaam zzv = zzziVar.zzv(zzailVar.zza(), 5);
        this.zzb = zzv;
        zzad zzadVar = new zzad();
        zzadVar.zzH(zzailVar.zzb());
        zzadVar.zzS(MimeTypes.APPLICATION_ID3);
        zzv.zzk(zzadVar.zzY());
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zzc() {
        int r5;
        zzdd.zzb(this.zzb);
        if (this.zzc && (r5 = this.zze) != 0 && this.zzf == r5) {
            long j = this.zzd;
            if (j != C1856C.TIME_UNSET) {
                this.zzb.zzs(j, 1, r5, 0, null);
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
            this.zzd = j;
        }
        this.zze = 0;
        this.zzf = 0;
    }

    @Override // com.google.android.gms.internal.ads.zzagz
    public final void zze() {
        this.zzc = false;
        this.zzd = C1856C.TIME_UNSET;
    }
}
