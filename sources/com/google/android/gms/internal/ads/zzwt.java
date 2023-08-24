package com.google.android.gms.internal.ads;

import android.os.Looper;
import android.os.SystemClock;
import com.google.android.exoplayer2.C1856C;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzwt {
    public static final zzwn zza = new zzwn(0, C1856C.TIME_UNSET, null);
    public static final zzwn zzb = new zzwn(1, C1856C.TIME_UNSET, null);
    public static final zzwn zzc = new zzwn(2, C1856C.TIME_UNSET, null);
    public static final zzwn zzd = new zzwn(3, C1856C.TIME_UNSET, null);
    private final ExecutorService zze = zzel.zzQ("ExoPlayer:Loader:ProgressiveMediaPeriod");
    private zzwo zzf;
    private IOException zzg;

    public zzwt(String str) {
    }

    public static zzwn zzb(boolean z, long j) {
        return new zzwn(z ? 1 : 0, j, null);
    }

    public final long zza(zzwp zzwpVar, zzwl zzwlVar, int r14) {
        Looper myLooper = Looper.myLooper();
        zzdd.zzb(myLooper);
        this.zzg = null;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        new zzwo(this, myLooper, zzwpVar, zzwlVar, r14, elapsedRealtime).zzc(0L);
        return elapsedRealtime;
    }

    public final void zzg() {
        zzwo zzwoVar = this.zzf;
        zzdd.zzb(zzwoVar);
        zzwoVar.zza(false);
    }

    public final void zzh() {
        this.zzg = null;
    }

    public final void zzi(int r2) throws IOException {
        IOException iOException = this.zzg;
        if (iOException != null) {
            throw iOException;
        }
        zzwo zzwoVar = this.zzf;
        if (zzwoVar != null) {
            zzwoVar.zzb(r2);
        }
    }

    public final void zzj(zzwq zzwqVar) {
        zzwo zzwoVar = this.zzf;
        if (zzwoVar != null) {
            zzwoVar.zza(true);
        }
        this.zze.execute(new zzwr(zzwqVar));
        this.zze.shutdown();
    }

    public final boolean zzk() {
        return this.zzg != null;
    }

    public final boolean zzl() {
        return this.zzf != null;
    }
}
