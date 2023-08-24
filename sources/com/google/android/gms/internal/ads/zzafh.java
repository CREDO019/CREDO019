package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzafh {
    public final zzaam zza;
    public zzafv zzd;
    public zzafd zze;
    public int zzf;
    public int zzg;
    public int zzh;
    public int zzi;
    private boolean zzl;
    public final zzafu zzb = new zzafu();
    public final zzed zzc = new zzed();
    private final zzed zzj = new zzed(1);
    private final zzed zzk = new zzed();

    public zzafh(zzaam zzaamVar, zzafv zzafvVar, zzafd zzafdVar) {
        this.zza = zzaamVar;
        this.zzd = zzafvVar;
        this.zze = zzafdVar;
        zzh(zzafvVar, zzafdVar);
    }

    public final int zza() {
        int r0;
        if (this.zzl) {
            r0 = this.zzb.zzj[this.zzf] ? 1 : 0;
        } else {
            r0 = this.zzd.zzg[this.zzf];
        }
        return zzf() != null ? r0 | 1073741824 : r0;
    }

    public final int zzb() {
        return !this.zzl ? this.zzd.zzd[this.zzf] : this.zzb.zzh[this.zzf];
    }

    public final int zzc(int r10, int r11) {
        zzed zzedVar;
        zzaft zzf = zzf();
        if (zzf == null) {
            return 0;
        }
        int r2 = zzf.zzd;
        if (r2 != 0) {
            zzedVar = this.zzb.zzn;
        } else {
            byte[] bArr = (byte[]) zzel.zzH(zzf.zze);
            zzed zzedVar2 = this.zzk;
            int length = bArr.length;
            zzedVar2.zzD(bArr, length);
            zzedVar = this.zzk;
            r2 = length;
        }
        boolean zzb = this.zzb.zzb(this.zzf);
        boolean z = zzb || r11 != 0;
        zzed zzedVar3 = this.zzj;
        zzedVar3.zzH()[0] = (byte) ((true != z ? 0 : 128) | r2);
        zzedVar3.zzF(0);
        this.zza.zzr(this.zzj, 1, 1);
        this.zza.zzr(zzedVar, r2, 1);
        if (z) {
            if (!zzb) {
                this.zzc.zzC(8);
                zzed zzedVar4 = this.zzc;
                byte[] zzH = zzedVar4.zzH();
                zzH[0] = 0;
                zzH[1] = 1;
                zzH[2] = 0;
                zzH[3] = (byte) r11;
                zzH[4] = (byte) ((r10 >> 24) & 255);
                zzH[5] = (byte) ((r10 >> 16) & 255);
                zzH[6] = (byte) ((r10 >> 8) & 255);
                zzH[7] = (byte) (r10 & 255);
                this.zza.zzr(zzedVar4, 8, 1);
                return r2 + 9;
            }
            zzed zzedVar5 = this.zzb.zzn;
            int zzo = zzedVar5.zzo();
            zzedVar5.zzG(-2);
            int r3 = (zzo * 6) + 2;
            if (r11 != 0) {
                this.zzc.zzC(r3);
                byte[] zzH2 = this.zzc.zzH();
                zzedVar5.zzB(zzH2, 0, r3);
                int r102 = (((zzH2[2] & 255) << 8) | (zzH2[3] & 255)) + r11;
                zzH2[2] = (byte) ((r102 >> 8) & 255);
                zzH2[3] = (byte) (r102 & 255);
                zzedVar5 = this.zzc;
            }
            this.zza.zzr(zzedVar5, r3, 1);
            return r2 + 1 + r3;
        }
        return r2 + 1;
    }

    public final long zzd() {
        return !this.zzl ? this.zzd.zzc[this.zzf] : this.zzb.zzf[this.zzh];
    }

    public final long zze() {
        if (this.zzl) {
            zzafu zzafuVar = this.zzb;
            return zzafuVar.zzi[this.zzf];
        }
        return this.zzd.zzf[this.zzf];
    }

    public final zzaft zzf() {
        if (this.zzl) {
            zzafd zzafdVar = this.zzb.zza;
            int r2 = zzel.zza;
            int r0 = zzafdVar.zza;
            zzaft zzaftVar = this.zzb.zzm;
            if (zzaftVar == null) {
                zzaftVar = this.zzd.zza.zza(r0);
            }
            if (zzaftVar == null || !zzaftVar.zza) {
                return null;
            }
            return zzaftVar;
        }
        return null;
    }

    public final void zzh(zzafv zzafvVar, zzafd zzafdVar) {
        this.zzd = zzafvVar;
        this.zze = zzafdVar;
        this.zza.zzk(zzafvVar.zza.zzf);
        zzi();
    }

    public final void zzi() {
        zzafu zzafuVar = this.zzb;
        zzafuVar.zzd = 0;
        zzafuVar.zzp = 0L;
        zzafuVar.zzq = false;
        zzafuVar.zzk = false;
        zzafuVar.zzo = false;
        zzafuVar.zzm = null;
        this.zzf = 0;
        this.zzh = 0;
        this.zzg = 0;
        this.zzi = 0;
        this.zzl = false;
    }

    public final boolean zzk() {
        this.zzf++;
        if (this.zzl) {
            int r0 = this.zzg + 1;
            this.zzg = r0;
            int[] r3 = this.zzb.zzg;
            int r4 = this.zzh;
            if (r0 == r3[r4]) {
                this.zzh = r4 + 1;
                this.zzg = 0;
                return false;
            }
            return true;
        }
        return false;
    }
}
