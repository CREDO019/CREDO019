package com.google.android.gms.internal.ads;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.audio.WavUtil;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaiw {
    public static Pair zza(zzzg zzzgVar) throws IOException {
        zzzgVar.zzj();
        zzaiv zzd = zzd(1684108385, zzzgVar, new zzed(8));
        ((zzyv) zzzgVar).zzo(8, false);
        return Pair.create(Long.valueOf(zzzgVar.zzf()), Long.valueOf(zzd.zzb));
    }

    public static zzaiu zzb(zzzg zzzgVar) throws IOException {
        byte[] bArr;
        zzed zzedVar = new zzed(16);
        zzaiv zzd = zzd(WavUtil.FMT_FOURCC, zzzgVar, zzedVar);
        zzdd.zzf(zzd.zzb >= 16);
        zzyv zzyvVar = (zzyv) zzzgVar;
        zzyvVar.zzm(zzedVar.zzH(), 0, 16, false);
        zzedVar.zzF(0);
        int zzi = zzedVar.zzi();
        int zzi2 = zzedVar.zzi();
        int zzh = zzedVar.zzh();
        int zzh2 = zzedVar.zzh();
        int zzi3 = zzedVar.zzi();
        int zzi4 = zzedVar.zzi();
        int r1 = ((int) zzd.zzb) - 16;
        if (r1 > 0) {
            bArr = new byte[r1];
            zzyvVar.zzm(bArr, 0, r1, false);
        } else {
            bArr = zzel.zzf;
        }
        byte[] bArr2 = bArr;
        zzyvVar.zzo((int) (zzzgVar.zze() - zzzgVar.zzf()), false);
        return new zzaiu(zzi, zzi2, zzh, zzh2, zzi3, zzi4, bArr2);
    }

    public static boolean zzc(zzzg zzzgVar) throws IOException {
        zzed zzedVar = new zzed(8);
        int r1 = zzaiv.zza(zzzgVar, zzedVar).zza;
        if (r1 == 1380533830 || r1 == 1380333108) {
            ((zzyv) zzzgVar).zzm(zzedVar.zzH(), 0, 4, false);
            zzedVar.zzF(0);
            int zze = zzedVar.zze();
            if (zze != 1463899717) {
                Log.e("WavHeaderReader", "Unsupported form type: " + zze);
                return false;
            }
            return true;
        }
        return false;
    }

    private static zzaiv zzd(int r6, zzzg zzzgVar, zzed zzedVar) throws IOException {
        zzaiv zza = zzaiv.zza(zzzgVar, zzedVar);
        while (true) {
            int r1 = zza.zza;
            if (r1 == r6) {
                return zza;
            }
            Log.w("WavHeaderReader", "Ignoring unknown WAV chunk: " + r1);
            long j = zza.zzb + 8;
            if (j > 2147483647L) {
                int r62 = zza.zza;
                throw zzbu.zzc("Chunk is too large (~2GB+) to skip; id: " + r62);
            }
            ((zzyv) zzzgVar).zzo((int) j, false);
            zza = zzaiv.zza(zzzgVar, zzedVar);
        }
    }
}
