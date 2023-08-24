package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import android.os.HandlerThread;
import android.os.Trace;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzpu implements zzqi {
    private final zzfsv zzb;
    private final zzfsv zzc;

    public zzpu(int r2, boolean z) {
        zzps zzpsVar = new zzps(r2);
        zzpt zzptVar = new zzpt(r2);
        this.zzb = zzpsVar;
        this.zzc = zzptVar;
    }

    public static /* synthetic */ HandlerThread zza(int r1) {
        String zzs;
        zzs = zzpw.zzs(r1, "ExoPlayer:MediaCodecAsyncAdapter:");
        return new HandlerThread(zzs);
    }

    public static /* synthetic */ HandlerThread zzb(int r1) {
        String zzs;
        zzs = zzpw.zzs(r1, "ExoPlayer:MediaCodecQueueingThread:");
        return new HandlerThread(zzs);
    }

    public final zzpw zzc(zzqh zzqhVar) throws IOException {
        MediaCodec mediaCodec;
        String str = zzqhVar.zza.zza;
        zzpw zzpwVar = null;
        try {
            int r3 = zzel.zza;
            Trace.beginSection("createCodec:" + str);
            mediaCodec = MediaCodec.createByCodecName(str);
            try {
                zzpw zzpwVar2 = new zzpw(mediaCodec, zza(((zzps) this.zzb).zza), zzb(((zzpt) this.zzc).zza), false, null);
                try {
                    Trace.endSection();
                    zzpw.zzh(zzpwVar2, zzqhVar.zzb, zzqhVar.zzd, null, 0);
                    return zzpwVar2;
                } catch (Exception e) {
                    e = e;
                    zzpwVar = zzpwVar2;
                    if (zzpwVar != null) {
                        zzpwVar.zzl();
                    } else if (mediaCodec != null) {
                        mediaCodec.release();
                    }
                    throw e;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            mediaCodec = null;
        }
    }
}
