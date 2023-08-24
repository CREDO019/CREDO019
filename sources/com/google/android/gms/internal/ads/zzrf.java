package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;
import android.view.Surface;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzrf implements zzqj {
    private final MediaCodec zza;
    private ByteBuffer[] zzb;
    private ByteBuffer[] zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzrf(MediaCodec mediaCodec, zzre zzreVar) {
        this.zza = mediaCodec;
        if (zzel.zza < 21) {
            this.zzb = mediaCodec.getInputBuffers();
            this.zzc = mediaCodec.getOutputBuffers();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final int zza() {
        return this.zza.dequeueInputBuffer(0L);
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final int zzb(MediaCodec.BufferInfo bufferInfo) {
        int dequeueOutputBuffer;
        do {
            dequeueOutputBuffer = this.zza.dequeueOutputBuffer(bufferInfo, 0L);
            if (dequeueOutputBuffer == -3) {
                if (zzel.zza < 21) {
                    this.zzc = this.zza.getOutputBuffers();
                }
                dequeueOutputBuffer = -3;
                continue;
            }
        } while (dequeueOutputBuffer == -3);
        return dequeueOutputBuffer;
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final MediaFormat zzc() {
        return this.zza.getOutputFormat();
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final ByteBuffer zzf(int r3) {
        if (zzel.zza >= 21) {
            return this.zza.getInputBuffer(r3);
        }
        return ((ByteBuffer[]) zzel.zzH(this.zzb))[r3];
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final ByteBuffer zzg(int r3) {
        if (zzel.zza >= 21) {
            return this.zza.getOutputBuffer(r3);
        }
        return ((ByteBuffer[]) zzel.zzH(this.zzc))[r3];
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final void zzi() {
        this.zza.flush();
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final void zzj(int r8, int r9, int r10, long j, int r13) {
        this.zza.queueInputBuffer(r8, 0, r10, j, r13);
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final void zzk(int r8, int r9, zzgd zzgdVar, long j, int r13) {
        this.zza.queueSecureInputBuffer(r8, 0, zzgdVar.zza(), j, 0);
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final void zzl() {
        this.zzb = null;
        this.zzc = null;
        this.zza.release();
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final void zzm(int r2, long j) {
        this.zza.releaseOutputBuffer(r2, j);
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final void zzn(int r2, boolean z) {
        this.zza.releaseOutputBuffer(r2, z);
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final void zzo(Surface surface) {
        this.zza.setOutputSurface(surface);
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final void zzp(Bundle bundle) {
        this.zza.setParameters(bundle);
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final void zzq(int r2) {
        this.zza.setVideoScalingMode(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final boolean zzr() {
        return false;
    }
}
