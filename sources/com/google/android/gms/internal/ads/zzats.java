package com.google.android.gms.internal.ads;

import android.media.AudioTrack;
import android.os.SystemClock;
import com.google.android.exoplayer2.C1856C;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
class zzats {
    protected AudioTrack zza;
    private boolean zzb;
    private int zzc;
    private long zzd;
    private long zze;
    private long zzf;
    private long zzg;
    private long zzh;
    private long zzi;

    private zzats() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzats(zzatr zzatrVar) {
    }

    public final long zza() {
        if (this.zzg != C1856C.TIME_UNSET) {
            return Math.min(this.zzi, this.zzh + ((((SystemClock.elapsedRealtime() * 1000) - this.zzg) * this.zzc) / 1000000));
        }
        int playState = this.zza.getPlayState();
        if (playState == 1) {
            return 0L;
        }
        long playbackHeadPosition = this.zza.getPlaybackHeadPosition() & BodyPartID.bodyIdMax;
        if (this.zzb) {
            if (playState == 2 && playbackHeadPosition == 0) {
                this.zzf = this.zzd;
            }
            playbackHeadPosition += this.zzf;
        }
        if (this.zzd > playbackHeadPosition) {
            this.zze++;
        }
        this.zzd = playbackHeadPosition;
        return playbackHeadPosition + (this.zze << 32);
    }

    public final long zzb() {
        return (zza() * 1000000) / this.zzc;
    }

    public long zzc() {
        throw new UnsupportedOperationException();
    }

    public long zzd() {
        throw new UnsupportedOperationException();
    }

    public final void zze(long j) {
        this.zzh = zza();
        this.zzg = SystemClock.elapsedRealtime() * 1000;
        this.zzi = j;
        this.zza.stop();
    }

    public final void zzf() {
        if (this.zzg != C1856C.TIME_UNSET) {
            return;
        }
        this.zza.pause();
    }

    public void zzg(AudioTrack audioTrack, boolean z) {
        this.zza = audioTrack;
        this.zzb = z;
        this.zzg = C1856C.TIME_UNSET;
        this.zzd = 0L;
        this.zze = 0L;
        this.zzf = 0L;
        if (audioTrack != null) {
            this.zzc = audioTrack.getSampleRate();
        }
    }

    public boolean zzh() {
        return false;
    }
}
