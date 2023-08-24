package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaue extends zzawq implements zzbac {
    private final zzato zzb;
    private final zzaua zzc;
    private boolean zzd;
    private int zze;
    private int zzf;
    private long zzg;
    private boolean zzh;

    public zzaue(zzaws zzawsVar, zzaus zzausVar, boolean z, Handler handler, zzatp zzatpVar) {
        super(1, zzawsVar, null, true);
        this.zzc = new zzaua(null, new zzath[0], new zzaud(this, null));
        this.zzb = new zzato(handler, zzatpVar);
    }

    @Override // com.google.android.gms.internal.ads.zzawq, com.google.android.gms.internal.ads.zzasx
    public final boolean zzE() {
        return super.zzE() && this.zzc.zzo();
    }

    @Override // com.google.android.gms.internal.ads.zzawq, com.google.android.gms.internal.ads.zzasx
    public final boolean zzF() {
        return this.zzc.zzn() || super.zzF();
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final int zzH(zzaws zzawsVar, zzass zzassVar) throws zzawv {
        int r1;
        int r7;
        String str = zzassVar.zzf;
        if (zzbad.zza(str)) {
            int r0 = zzban.zza >= 21 ? 16 : 0;
            zzawo zzc = zzaxa.zzc(str, false);
            if (zzc == null) {
                return 1;
            }
            int r3 = 2;
            if (zzban.zza < 21 || (((r1 = zzassVar.zzs) == -1 || zzc.zzd(r1)) && ((r7 = zzassVar.zzr) == -1 || zzc.zzc(r7)))) {
                r3 = 3;
            }
            return r0 | 4 | r3;
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzbac
    public final long zzI() {
        long zza = this.zzc.zza(zzE());
        if (zza != Long.MIN_VALUE) {
            if (!this.zzh) {
                zza = Math.max(this.zzg, zza);
            }
            this.zzg = zza;
            this.zzh = false;
        }
        return this.zzg;
    }

    @Override // com.google.android.gms.internal.ads.zzbac
    public final zzasw zzJ() {
        return this.zzc.zzc();
    }

    @Override // com.google.android.gms.internal.ads.zzbac
    public final zzasw zzK(zzasw zzaswVar) {
        return this.zzc.zzd(zzaswVar);
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final zzawo zzM(zzaws zzawsVar, zzass zzassVar, boolean z) throws zzawv {
        return super.zzM(zzawsVar, zzassVar, false);
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final void zzO(zzawo zzawoVar, MediaCodec mediaCodec, zzass zzassVar, MediaCrypto mediaCrypto) {
        String str = zzawoVar.zza;
        boolean z = true;
        if (zzban.zza >= 24 || !"OMX.SEC.aac.dec".equals(str) || !"samsung".equals(zzban.zzc) || (!zzban.zzb.startsWith("zeroflte") && !zzban.zzb.startsWith("herolte") && !zzban.zzb.startsWith("heroqlte"))) {
            z = false;
        }
        this.zzd = z;
        mediaCodec.configure(zzassVar.zzb(), (Surface) null, (MediaCrypto) null, 0);
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final void zzP(String str, long j, long j2) {
        this.zzb.zzd(str, j, j2);
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final void zzQ(zzass zzassVar) throws zzase {
        super.zzQ(zzassVar);
        this.zzb.zzg(zzassVar);
        this.zze = MimeTypes.AUDIO_RAW.equals(zzassVar.zzf) ? zzassVar.zzt : 2;
        this.zzf = zzassVar.zzr;
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final void zzR(MediaCodec mediaCodec, MediaFormat mediaFormat) throws zzase {
        int r3;
        int[] r7;
        int integer = mediaFormat.getInteger("channel-count");
        int integer2 = mediaFormat.getInteger("sample-rate");
        if (this.zzd && integer == 6) {
            int r9 = this.zzf;
            if (r9 < 6) {
                int[] r92 = new int[r9];
                for (int r10 = 0; r10 < this.zzf; r10++) {
                    r92[r10] = r10;
                }
                r7 = r92;
            } else {
                r7 = null;
            }
            r3 = 6;
        } else {
            r3 = integer;
            r7 = null;
        }
        try {
            this.zzc.zze(MimeTypes.AUDIO_RAW, r3, integer2, this.zze, 0, r7);
        } catch (zzatu e) {
            throw zzase.zza(e, zza());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final boolean zzT(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int r7, int r8, long j3, boolean z) throws zzase {
        if (z) {
            mediaCodec.releaseOutputBuffer(r7, false);
            this.zza.zze++;
            this.zzc.zzf();
            return true;
        }
        try {
            if (this.zzc.zzm(byteBuffer, j3)) {
                mediaCodec.releaseOutputBuffer(r7, false);
                this.zza.zzd++;
                return true;
            }
            return false;
        } catch (zzatv | zzatz e) {
            throw zzase.zza(e, zza());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzasc, com.google.android.gms.internal.ads.zzasx
    public final zzbac zzi() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzasc, com.google.android.gms.internal.ads.zzasg
    public final void zzl(int r2, Object obj) throws zzase {
        if (r2 != 2) {
            return;
        }
        this.zzc.zzl(((Float) obj).floatValue());
    }

    @Override // com.google.android.gms.internal.ads.zzawq, com.google.android.gms.internal.ads.zzasc
    protected final void zzn() {
        try {
            this.zzc.zzj();
            try {
                super.zzn();
            } finally {
            }
        } catch (Throwable th) {
            try {
                super.zzn();
                throw th;
            } finally {
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzawq, com.google.android.gms.internal.ads.zzasc
    protected final void zzo(boolean z) throws zzase {
        super.zzo(z);
        this.zzb.zzf(this.zza);
        int r2 = zzg().zzb;
    }

    @Override // com.google.android.gms.internal.ads.zzawq, com.google.android.gms.internal.ads.zzasc
    protected final void zzp(long j, boolean z) throws zzase {
        super.zzp(j, z);
        this.zzc.zzk();
        this.zzg = j;
        this.zzh = true;
    }

    @Override // com.google.android.gms.internal.ads.zzasc
    protected final void zzq() {
        this.zzc.zzh();
    }

    @Override // com.google.android.gms.internal.ads.zzasc
    protected final void zzr() {
        this.zzc.zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final void zzS() throws zzase {
        try {
            this.zzc.zzi();
        } catch (zzatz e) {
            throw zzase.zza(e, zza());
        }
    }
}
