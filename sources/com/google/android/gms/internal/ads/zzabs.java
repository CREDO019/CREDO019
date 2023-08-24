package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzabs extends zzabr {
    private final zzed zzb;
    private final zzed zzc;
    private int zzd;
    private boolean zze;
    private boolean zzf;
    private int zzg;

    public zzabs(zzaam zzaamVar) {
        super(zzaamVar);
        this.zzb = new zzed(zzaac.zza);
        this.zzc = new zzed(4);
    }

    @Override // com.google.android.gms.internal.ads.zzabr
    protected final boolean zza(zzed zzedVar) throws zzabq {
        int zzk = zzedVar.zzk();
        int r0 = zzk >> 4;
        int r4 = zzk & 15;
        if (r4 == 7) {
            this.zzg = r0;
            return r0 != 5;
        }
        throw new zzabq("Video format not supported: " + r4);
    }

    @Override // com.google.android.gms.internal.ads.zzabr
    protected final boolean zzb(zzed zzedVar, long j) throws zzbu {
        int zzk = zzedVar.zzk();
        long zzf = j + (zzedVar.zzf() * 1000);
        if (zzk == 0) {
            if (!this.zze) {
                zzed zzedVar2 = new zzed(new byte[zzedVar.zza()]);
                zzedVar.zzB(zzedVar2.zzH(), 0, zzedVar.zza());
                zzyk zza = zzyk.zza(zzedVar2);
                this.zzd = zza.zzb;
                zzad zzadVar = new zzad();
                zzadVar.zzS(MimeTypes.VIDEO_H264);
                zzadVar.zzx(zza.zzf);
                zzadVar.zzX(zza.zzc);
                zzadVar.zzF(zza.zzd);
                zzadVar.zzP(zza.zze);
                zzadVar.zzI(zza.zza);
                this.zza.zzk(zzadVar.zzY());
                this.zze = true;
                return false;
            }
        } else if (zzk == 1 && this.zze) {
            int r6 = this.zzg == 1 ? 1 : 0;
            if (this.zzf || r6 != 0) {
                byte[] zzH = this.zzc.zzH();
                zzH[0] = 0;
                zzH[1] = 0;
                zzH[2] = 0;
                int r0 = 4 - this.zzd;
                int r7 = 0;
                while (zzedVar.zza() > 0) {
                    zzedVar.zzB(this.zzc.zzH(), r0, this.zzd);
                    this.zzc.zzF(0);
                    int zzn = this.zzc.zzn();
                    this.zzb.zzF(0);
                    this.zza.zzq(this.zzb, 4);
                    this.zza.zzq(zzedVar, zzn);
                    r7 = r7 + 4 + zzn;
                }
                this.zza.zzs(zzf, r6, r7, 0, null);
                this.zzf = true;
                return true;
            }
            return false;
        }
        return false;
    }
}
