package com.google.android.gms.internal.ads;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import com.google.android.exoplayer2.audio.OpusUtil;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzna {
    private static final AudioAttributes zza = new AudioAttributes.Builder().setUsage(1).setContentType(3).setFlags(0).build();

    public static int zza(int r3, int r4) {
        for (int r0 = 8; r0 > 0; r0--) {
            if (AudioTrack.isDirectPlaybackSupported(new AudioFormat.Builder().setEncoding(r3).setSampleRate(r4).setChannelMask(zzel.zzj(r0)).build(), zza)) {
                return r0;
            }
        }
        return 0;
    }

    public static int[] zzb() {
        zzfuy zzfuyVar;
        zzfus zzi = zzfuv.zzi();
        zzfuyVar = zznb.zzc;
        zzfwu it = zzfuyVar.keySet().iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            if (AudioTrack.isDirectPlaybackSupported(new AudioFormat.Builder().setChannelMask(12).setEncoding(intValue).setSampleRate(OpusUtil.SAMPLE_RATE).build(), zza)) {
                zzi.zze(Integer.valueOf(intValue));
            }
        }
        zzi.zze((Object) 2);
        Object[] array = zzi.zzg().toArray();
        int length = array.length;
        int[] r2 = new int[length];
        for (int r3 = 0; r3 < length; r3++) {
            Object obj = array[r3];
            Objects.requireNonNull(obj);
            r2[r3] = ((Number) obj).intValue();
        }
        return r2;
    }
}
