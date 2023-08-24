package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzqa {
    private static final ArrayDeque zza = new ArrayDeque();
    private static final Object zzb = new Object();
    private final MediaCodec zzc;
    private final HandlerThread zzd;
    private Handler zze;
    private final AtomicReference zzf;
    private final zzdg zzg;
    private boolean zzh;

    public zzqa(MediaCodec mediaCodec, HandlerThread handlerThread) {
        zzdg zzdgVar = new zzdg(zzde.zza);
        this.zzc = mediaCodec;
        this.zzd = handlerThread;
        this.zzg = zzdgVar;
        this.zzf = new AtomicReference();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006e A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* bridge */ /* synthetic */ void zza(com.google.android.gms.internal.ads.zzqa r9, android.os.Message r10) {
        /*
            int r0 = r10.what
            r1 = 0
            if (r0 == 0) goto L45
            r2 = 1
            if (r0 == r2) goto L22
            r2 = 2
            if (r0 == r2) goto L1c
            java.util.concurrent.atomic.AtomicReference r9 = r9.zzf
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            int r10 = r10.what
            java.lang.String r10 = java.lang.String.valueOf(r10)
            r0.<init>(r10)
            com.google.android.gms.internal.ads.zzpx.zza(r9, r1, r0)
            goto L61
        L1c:
            com.google.android.gms.internal.ads.zzdg r9 = r9.zzg
            r9.zze()
            goto L61
        L22:
            java.lang.Object r10 = r10.obj
            com.google.android.gms.internal.ads.zzpz r10 = (com.google.android.gms.internal.ads.zzpz) r10
            int r3 = r10.zza
            int r0 = r10.zzb
            android.media.MediaCodec$CryptoInfo r5 = r10.zzd
            long r6 = r10.zze
            int r8 = r10.zzf
            java.lang.Object r0 = com.google.android.gms.internal.ads.zzqa.zzb     // Catch: java.lang.RuntimeException -> L3e
            monitor-enter(r0)     // Catch: java.lang.RuntimeException -> L3e
            android.media.MediaCodec r2 = r9.zzc     // Catch: java.lang.Throwable -> L3b
            r4 = 0
            r2.queueSecureInputBuffer(r3, r4, r5, r6, r8)     // Catch: java.lang.Throwable -> L3b
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3b
            goto L60
        L3b:
            r2 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3b
            throw r2     // Catch: java.lang.RuntimeException -> L3e
        L3e:
            r0 = move-exception
            java.util.concurrent.atomic.AtomicReference r9 = r9.zzf
            com.google.android.gms.internal.ads.zzpx.zza(r9, r1, r0)
            goto L60
        L45:
            java.lang.Object r10 = r10.obj
            com.google.android.gms.internal.ads.zzpz r10 = (com.google.android.gms.internal.ads.zzpz) r10
            int r3 = r10.zza
            int r0 = r10.zzb
            int r5 = r10.zzc
            long r6 = r10.zze
            int r8 = r10.zzf
            android.media.MediaCodec r2 = r9.zzc     // Catch: java.lang.RuntimeException -> L5a
            r4 = 0
            r2.queueInputBuffer(r3, r4, r5, r6, r8)     // Catch: java.lang.RuntimeException -> L5a
            goto L60
        L5a:
            r0 = move-exception
            java.util.concurrent.atomic.AtomicReference r9 = r9.zzf
            com.google.android.gms.internal.ads.zzpx.zza(r9, r1, r0)
        L60:
            r1 = r10
        L61:
            if (r1 == 0) goto L6e
            java.util.ArrayDeque r9 = com.google.android.gms.internal.ads.zzqa.zza
            monitor-enter(r9)
            r9.add(r1)     // Catch: java.lang.Throwable -> L6b
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L6b
            return
        L6b:
            r10 = move-exception
            monitor-exit(r9)     // Catch: java.lang.Throwable -> L6b
            throw r10
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqa.zza(com.google.android.gms.internal.ads.zzqa, android.os.Message):void");
    }

    private static zzpz zzg() {
        ArrayDeque arrayDeque = zza;
        synchronized (arrayDeque) {
            if (arrayDeque.isEmpty()) {
                return new zzpz();
            }
            return (zzpz) arrayDeque.removeFirst();
        }
    }

    private final void zzh() {
        RuntimeException runtimeException = (RuntimeException) this.zzf.getAndSet(null);
        if (runtimeException != null) {
            throw runtimeException;
        }
    }

    public final void zzb() {
        if (this.zzh) {
            try {
                Handler handler = this.zze;
                Objects.requireNonNull(handler);
                handler.removeCallbacksAndMessages(null);
                this.zzg.zzc();
                Handler handler2 = this.zze;
                Objects.requireNonNull(handler2);
                handler2.obtainMessage(2).sendToTarget();
                this.zzg.zza();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(e);
            }
        }
    }

    public final void zzc(int r8, int r9, int r10, long j, int r13) {
        zzh();
        zzpz zzg = zzg();
        zzg.zza(r8, 0, r10, j, r13);
        Handler handler = this.zze;
        int r102 = zzel.zza;
        handler.obtainMessage(0, zzg).sendToTarget();
    }

    public final void zzd(int r8, int r9, zzgd zzgdVar, long j, int r13) {
        zzh();
        zzpz zzg = zzg();
        zzg.zza(r8, 0, 0, j, 0);
        MediaCodec.CryptoInfo cryptoInfo = zzg.zzd;
        cryptoInfo.numSubSamples = zzgdVar.zzf;
        cryptoInfo.numBytesOfClearData = zzj(zzgdVar.zzd, cryptoInfo.numBytesOfClearData);
        cryptoInfo.numBytesOfEncryptedData = zzj(zzgdVar.zze, cryptoInfo.numBytesOfEncryptedData);
        byte[] zzi = zzi(zzgdVar.zzb, cryptoInfo.key);
        Objects.requireNonNull(zzi);
        cryptoInfo.key = zzi;
        byte[] zzi2 = zzi(zzgdVar.zza, cryptoInfo.iv);
        Objects.requireNonNull(zzi2);
        cryptoInfo.iv = zzi2;
        cryptoInfo.mode = zzgdVar.zzc;
        if (zzel.zza >= 24) {
            cryptoInfo.setPattern(new MediaCodec.CryptoInfo.Pattern(zzgdVar.zzg, zzgdVar.zzh));
        }
        this.zze.obtainMessage(1, zzg).sendToTarget();
    }

    public final void zze() {
        if (this.zzh) {
            zzb();
            this.zzd.quit();
        }
        this.zzh = false;
    }

    public final void zzf() {
        if (this.zzh) {
            return;
        }
        this.zzd.start();
        this.zze = new zzpy(this, this.zzd.getLooper());
        this.zzh = true;
    }

    private static byte[] zzi(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return bArr2;
        }
        if (bArr2 != null) {
            int length = bArr2.length;
            int length2 = bArr.length;
            if (length >= length2) {
                System.arraycopy(bArr, 0, bArr2, 0, length2);
                return bArr2;
            }
        }
        return Arrays.copyOf(bArr, bArr.length);
    }

    private static int[] zzj(int[] r2, int[] r3) {
        if (r2 == null) {
            return r3;
        }
        if (r3 != null) {
            int length = r3.length;
            int length2 = r2.length;
            if (length >= length2) {
                System.arraycopy(r2, 0, r3, 0, length2);
                return r3;
            }
        }
        return Arrays.copyOf(r2, r2.length);
    }
}
