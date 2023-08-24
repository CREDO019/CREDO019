package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;
import android.view.Surface;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public interface zzqj {
    int zza();

    int zzb(MediaCodec.BufferInfo bufferInfo);

    MediaFormat zzc();

    ByteBuffer zzf(int r1);

    ByteBuffer zzg(int r1);

    void zzi();

    void zzj(int r1, int r2, int r3, long j, int r6);

    void zzk(int r1, int r2, zzgd zzgdVar, long j, int r6);

    void zzl();

    void zzm(int r1, long j);

    void zzn(int r1, boolean z);

    void zzo(Surface surface);

    void zzp(Bundle bundle);

    void zzq(int r1);

    boolean zzr();
}
