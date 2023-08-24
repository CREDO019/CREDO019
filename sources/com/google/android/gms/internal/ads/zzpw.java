package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Trace;
import android.view.Surface;
import java.nio.ByteBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzpw implements zzqj {
    private final MediaCodec zza;
    private final zzqc zzb;
    private final zzqa zzc;
    private boolean zzd;
    private int zze = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzpw(MediaCodec mediaCodec, HandlerThread handlerThread, HandlerThread handlerThread2, boolean z, zzpv zzpvVar) {
        this.zza = mediaCodec;
        this.zzb = new zzqc(handlerThread);
        this.zzc = new zzqa(mediaCodec, handlerThread2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String zzd(int r1) {
        return zzs(r1, "ExoPlayer:MediaCodecAsyncAdapter:");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String zze(int r1) {
        return zzs(r1, "ExoPlayer:MediaCodecQueueingThread:");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzh(zzpw zzpwVar, MediaFormat mediaFormat, Surface surface, MediaCrypto mediaCrypto, int r5) {
        zzpwVar.zzb.zzf(zzpwVar.zza);
        int r4 = zzel.zza;
        Trace.beginSection("configureCodec");
        zzpwVar.zza.configure(mediaFormat, surface, (MediaCrypto) null, 0);
        Trace.endSection();
        zzpwVar.zzc.zzf();
        Trace.beginSection("startCodec");
        zzpwVar.zza.start();
        Trace.endSection();
        zzpwVar.zze = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String zzs(int r1, String str) {
        StringBuilder sb = new StringBuilder(str);
        if (r1 == 1) {
            sb.append("Audio");
        } else if (r1 == 2) {
            sb.append("Video");
        } else {
            sb.append("Unknown(");
            sb.append(r1);
            sb.append(")");
        }
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final int zza() {
        return this.zzb.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final int zzb(MediaCodec.BufferInfo bufferInfo) {
        return this.zzb.zzb(bufferInfo);
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final MediaFormat zzc() {
        return this.zzb.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final ByteBuffer zzf(int r2) {
        return this.zza.getInputBuffer(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final ByteBuffer zzg(int r2) {
        return this.zza.getOutputBuffer(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final void zzi() {
        this.zzc.zzb();
        this.zza.flush();
        this.zzb.zze();
        this.zza.start();
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final void zzj(int r8, int r9, int r10, long j, int r13) {
        this.zzc.zzc(r8, 0, r10, j, r13);
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final void zzk(int r8, int r9, zzgd zzgdVar, long j, int r13) {
        this.zzc.zzd(r8, 0, zzgdVar, j, 0);
    }

    @Override // com.google.android.gms.internal.ads.zzqj
    public final void zzl() {
        try {
            if (this.zze == 1) {
                this.zzc.zze();
                this.zzb.zzg();
            }
            this.zze = 2;
            if (this.zzd) {
                return;
            }
            this.zza.release();
            this.zzd = true;
        } catch (Throwable th) {
            if (!this.zzd) {
                this.zza.release();
                this.zzd = true;
            }
            throw th;
        }
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
