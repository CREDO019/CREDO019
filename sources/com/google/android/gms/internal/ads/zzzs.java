package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.audio.DtsUtil;
import com.google.android.exoplayer2.audio.OpusUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzzs {
    public final int zza;
    public final int zzb;
    public final int zzc;
    public final int zzd;
    public final int zze;
    public final int zzf;
    public final int zzg;
    public final int zzh;
    public final int zzi;
    public final long zzj;
    public final zzzr zzk;
    private final zzbq zzl;

    private zzzs(int r1, int r2, int r3, int r4, int r5, int r6, int r7, long j, zzzr zzzrVar, zzbq zzbqVar) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = zzi(r5);
        this.zzg = r6;
        this.zzh = r7;
        this.zzi = zzh(r7);
        this.zzj = j;
        this.zzk = zzzrVar;
        this.zzl = zzbqVar;
    }

    public zzzs(byte[] bArr, int r4) {
        zzec zzecVar = new zzec(bArr, bArr.length);
        zzecVar.zzh(r4 * 8);
        this.zza = zzecVar.zzc(16);
        this.zzb = zzecVar.zzc(16);
        this.zzc = zzecVar.zzc(24);
        this.zzd = zzecVar.zzc(24);
        int zzc = zzecVar.zzc(20);
        this.zze = zzc;
        this.zzf = zzi(zzc);
        this.zzg = zzecVar.zzc(3) + 1;
        int zzc2 = zzecVar.zzc(5) + 1;
        this.zzh = zzc2;
        this.zzi = zzh(zzc2);
        this.zzj = zzel.zzy(zzecVar.zzc(4), zzecVar.zzc(32));
        this.zzk = null;
        this.zzl = null;
    }

    private static int zzh(int r1) {
        if (r1 != 8) {
            if (r1 != 12) {
                if (r1 != 16) {
                    if (r1 != 20) {
                        return r1 != 24 ? -1 : 6;
                    }
                    return 5;
                }
                return 4;
            }
            return 2;
        }
        return 1;
    }

    private static int zzi(int r0) {
        switch (r0) {
            case 8000:
                return 4;
            case AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND /* 16000 */:
                return 5;
            case 22050:
                return 6;
            case 24000:
                return 7;
            case 32000:
                return 8;
            case 44100:
                return 9;
            case OpusUtil.SAMPLE_RATE /* 48000 */:
                return 10;
            case 88200:
                return 1;
            case 96000:
                return 11;
            case 176400:
                return 2;
            case DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND /* 192000 */:
                return 3;
            default:
                return -1;
        }
    }

    public final long zza() {
        long j = this.zzj;
        return j == 0 ? C1856C.TIME_UNSET : (j * 1000000) / this.zze;
    }

    public final long zzb(long j) {
        return zzel.zzr((j * this.zze) / 1000000, 0L, this.zzj - 1);
    }

    public final zzaf zzc(byte[] bArr, zzbq zzbqVar) {
        bArr[4] = Byte.MIN_VALUE;
        int r0 = this.zzd;
        if (r0 <= 0) {
            r0 = -1;
        }
        zzbq zzd = zzd(zzbqVar);
        zzad zzadVar = new zzad();
        zzadVar.zzS(MimeTypes.AUDIO_FLAC);
        zzadVar.zzL(r0);
        zzadVar.zzw(this.zzg);
        zzadVar.zzT(this.zze);
        zzadVar.zzI(Collections.singletonList(bArr));
        zzadVar.zzM(zzd);
        return zzadVar.zzY();
    }

    public final zzbq zzd(zzbq zzbqVar) {
        zzbq zzbqVar2 = this.zzl;
        return zzbqVar2 == null ? zzbqVar : zzbqVar2.zzd(zzbqVar);
    }

    public final zzzs zze(List list) {
        return new zzzs(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzg, this.zzh, this.zzj, this.zzk, zzd(new zzbq(list)));
    }

    public final zzzs zzf(zzzr zzzrVar) {
        return new zzzs(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzg, this.zzh, this.zzj, zzzrVar, this.zzl);
    }

    public final zzzs zzg(List list) {
        return new zzzs(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzg, this.zzh, this.zzj, this.zzk, zzd(zzaas.zzb(list)));
    }
}
