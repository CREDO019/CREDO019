package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Collections;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzabm extends zzabr {
    private static final int[] zzb = {5512, 11025, 22050, 44100};
    private boolean zzc;
    private boolean zzd;
    private int zze;

    public zzabm(zzaam zzaamVar) {
        super(zzaamVar);
    }

    @Override // com.google.android.gms.internal.ads.zzabr
    protected final boolean zza(zzed zzedVar) throws zzabq {
        if (!this.zzc) {
            int zzk = zzedVar.zzk();
            int r0 = zzk >> 4;
            this.zze = r0;
            if (r0 == 2) {
                int r4 = zzb[(zzk >> 2) & 3];
                zzad zzadVar = new zzad();
                zzadVar.zzS(MimeTypes.AUDIO_MPEG);
                zzadVar.zzw(1);
                zzadVar.zzT(r4);
                this.zza.zzk(zzadVar.zzY());
                this.zzd = true;
            } else if (r0 == 7 || r0 == 8) {
                String str = r0 == 7 ? MimeTypes.AUDIO_ALAW : MimeTypes.AUDIO_MLAW;
                zzad zzadVar2 = new zzad();
                zzadVar2.zzS(str);
                zzadVar2.zzw(1);
                zzadVar2.zzT(8000);
                this.zza.zzk(zzadVar2.zzY());
                this.zzd = true;
            } else if (r0 != 10) {
                throw new zzabq("Audio format not supported: " + r0);
            }
            this.zzc = true;
        } else {
            zzedVar.zzG(1);
        }
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzabr
    protected final boolean zzb(zzed zzedVar, long j) throws zzbu {
        if (this.zze == 2) {
            int zza = zzedVar.zza();
            this.zza.zzq(zzedVar, zza);
            this.zza.zzs(j, 1, zza, 0, null);
            return true;
        }
        int zzk = zzedVar.zzk();
        if (zzk != 0 || this.zzd) {
            if (this.zze != 10 || zzk == 1) {
                int zza2 = zzedVar.zza();
                this.zza.zzq(zzedVar, zza2);
                this.zza.zzs(j, 1, zza2, 0, null);
                return true;
            }
            return false;
        }
        int zza3 = zzedVar.zza();
        byte[] bArr = new byte[zza3];
        zzedVar.zzB(bArr, 0, zza3);
        zzyc zza4 = zzyd.zza(bArr);
        zzad zzadVar = new zzad();
        zzadVar.zzS(MimeTypes.AUDIO_AAC);
        zzadVar.zzx(zza4.zzc);
        zzadVar.zzw(zza4.zzb);
        zzadVar.zzT(zza4.zza);
        zzadVar.zzI(Collections.singletonList(bArr));
        this.zza.zzk(zzadVar.zzY());
        this.zzd = true;
        return false;
    }
}
