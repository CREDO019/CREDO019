package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaiq implements zzair {
    private static final int[] zza = {-1, -1, -1, -1, 2, 4, 6, 8, -1, -1, -1, -1, 2, 4, 6, 8};
    private static final int[] zzb = {7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 19, 21, 23, 25, 28, 31, 34, 37, 41, 45, 50, 55, 60, 66, 73, 80, 88, 97, 107, 118, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 143, 157, 173, 190, 209, 230, 253, 279, 307, 337, 371, 408, 449, 494, 544, 598, 658, 724, 796, 876, 963, 1060, 1166, 1282, 1411, 1552, 1707, 1878, 2066, 2272, 2499, 2749, 3024, 3327, 3660, 4026, 4428, 4871, 5358, 5894, 6484, 7132, 7845, 8630, 9493, 10442, 11487, 12635, 13899, 15289, 16818, 18500, 20350, 22385, 24623, 27086, 29794, 32767};
    private final zzzi zzc;
    private final zzaam zzd;
    private final zzaiu zze;
    private final int zzf;
    private final byte[] zzg;
    private final zzed zzh;
    private final int zzi;
    private final zzaf zzj;
    private int zzk;
    private long zzl;
    private int zzm;
    private long zzn;

    public zzaiq(zzzi zzziVar, zzaam zzaamVar, zzaiu zzaiuVar) throws zzbu {
        this.zzc = zzziVar;
        this.zzd = zzaamVar;
        this.zze = zzaiuVar;
        int max = Math.max(1, zzaiuVar.zzc / 10);
        this.zzi = max;
        zzed zzedVar = new zzed(zzaiuVar.zzf);
        zzedVar.zzi();
        int zzi = zzedVar.zzi();
        this.zzf = zzi;
        int r1 = zzaiuVar.zzb;
        int r2 = (((zzaiuVar.zzd - (r1 * 4)) * 8) / (zzaiuVar.zze * r1)) + 1;
        if (zzi != r2) {
            throw zzbu.zza("Expected frames per block: " + r2 + "; got: " + zzi, null);
        }
        int zze = zzel.zze(max, zzi);
        this.zzg = new byte[zzaiuVar.zzd * zze];
        this.zzh = new zzed(zze * (zzi + zzi) * r1);
        int r6 = ((zzaiuVar.zzc * zzaiuVar.zzd) * 8) / zzi;
        zzad zzadVar = new zzad();
        zzadVar.zzS(MimeTypes.AUDIO_RAW);
        zzadVar.zzv(r6);
        zzadVar.zzO(r6);
        zzadVar.zzL((max + max) * r1);
        zzadVar.zzw(zzaiuVar.zzb);
        zzadVar.zzT(zzaiuVar.zzc);
        zzadVar.zzN(2);
        this.zzj = zzadVar.zzY();
    }

    private final int zzd(int r2) {
        int r0 = this.zze.zzb;
        return r2 / (r0 + r0);
    }

    private final int zze(int r2) {
        return (r2 + r2) * this.zze.zzb;
    }

    private final void zzf(int r13) {
        long j = this.zzl;
        long zzw = zzel.zzw(this.zzn, 1000000L, this.zze.zzc);
        int zze = zze(r13);
        this.zzd.zzs(j + zzw, 1, zze, this.zzm - zze, null);
        this.zzn += r13;
        this.zzm -= zze;
    }

    @Override // com.google.android.gms.internal.ads.zzair
    public final void zza(int r10, long j) {
        this.zzc.zzL(new zzaix(this.zze, this.zzf, r10, j));
        this.zzd.zzk(this.zzj);
    }

    @Override // com.google.android.gms.internal.ads.zzair
    public final void zzb(long j) {
        this.zzk = 0;
        this.zzl = j;
        this.zzm = 0;
        this.zzn = 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x003b -> B:4:0x001f). Please submit an issue!!! */
    @Override // com.google.android.gms.internal.ads.zzair
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzc(com.google.android.gms.internal.ads.zzzg r20, long r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 342
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaiq.zzc(com.google.android.gms.internal.ads.zzzg, long):boolean");
    }
}
