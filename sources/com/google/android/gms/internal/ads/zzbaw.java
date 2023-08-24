package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbaw extends zzawq {
    private int zzA;
    private final Context zzb;
    private final zzbay zzc;
    private final zzbbg zzd;
    private final boolean zze;
    private final long[] zzf;
    private zzass[] zzg;
    private zzbav zzh;
    private Surface zzi;
    private Surface zzj;
    private boolean zzk;
    private long zzl;
    private long zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private float zzq;
    private int zzr;
    private int zzs;
    private int zzt;
    private float zzu;
    private int zzv;
    private int zzw;
    private int zzx;
    private float zzy;
    private long zzz;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbaw(Context context, zzaws zzawsVar, long j, Handler handler, zzbbh zzbbhVar, int r7) {
        super(2, zzawsVar, null, false);
        boolean z = false;
        this.zzb = context.getApplicationContext();
        this.zzc = new zzbay(context);
        this.zzd = new zzbbg(handler, zzbbhVar);
        if (zzban.zza <= 22 && "foster".equals(zzban.zzb) && "NVIDIA".equals(zzban.zzc)) {
            z = true;
        }
        this.zze = z;
        this.zzf = new long[10];
        this.zzz = C1856C.TIME_UNSET;
        this.zzl = C1856C.TIME_UNSET;
        this.zzr = -1;
        this.zzs = -1;
        this.zzu = -1.0f;
        this.zzq = -1.0f;
        zzab();
    }

    private static int zzN(zzass zzassVar) {
        int r1 = zzassVar.zzm;
        if (r1 == -1) {
            return 0;
        }
        return r1;
    }

    private final void zzab() {
        this.zzv = -1;
        this.zzw = -1;
        this.zzy = -1.0f;
        this.zzx = -1;
    }

    private final void zzac() {
        if (this.zzn > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.zzd.zzd(this.zzn, elapsedRealtime - this.zzm);
            this.zzn = 0;
            this.zzm = elapsedRealtime;
        }
    }

    private final void zzad() {
        int r0 = this.zzv;
        int r1 = this.zzr;
        if (r0 == r1 && this.zzw == this.zzs && this.zzx == this.zzt && this.zzy == this.zzu) {
            return;
        }
        this.zzd.zzh(r1, this.zzs, this.zzt, this.zzu);
        this.zzv = this.zzr;
        this.zzw = this.zzs;
        this.zzx = this.zzt;
        this.zzy = this.zzu;
    }

    private final void zzae() {
        if (this.zzv == -1 && this.zzw == -1) {
            return;
        }
        this.zzd.zzh(this.zzr, this.zzs, this.zzt, this.zzu);
    }

    private static boolean zzaf(long j) {
        return j < -30000;
    }

    private final boolean zzag(boolean z) {
        return zzban.zza >= 23 && (!z || zzbat.zzb(this.zzb));
    }

    @Override // com.google.android.gms.internal.ads.zzawq, com.google.android.gms.internal.ads.zzasx
    public final boolean zzF() {
        Surface surface;
        if (!super.zzF() || (!this.zzk && (((surface = this.zzj) == null || this.zzi != surface) && zzU() != null))) {
            if (this.zzl == C1856C.TIME_UNSET) {
                return false;
            }
            if (SystemClock.elapsedRealtime() < this.zzl) {
                return true;
            }
            this.zzl = C1856C.TIME_UNSET;
            return false;
        }
        this.zzl = C1856C.TIME_UNSET;
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final int zzH(zzaws zzawsVar, zzass zzassVar) throws zzawv {
        boolean z;
        String str = zzassVar.zzf;
        if (zzbad.zzb(str)) {
            zzaur zzaurVar = zzassVar.zzi;
            if (zzaurVar != null) {
                z = false;
                for (int r2 = 0; r2 < zzaurVar.zza; r2++) {
                    z |= zzaurVar.zza(r2).zzc;
                }
            } else {
                z = false;
            }
            zzawo zzc = zzaxa.zzc(str, z);
            if (zzc == null) {
                return 1;
            }
            boolean zze = zzc.zze(zzassVar.zzc);
            if (zze && zzassVar.zzj > 0 && zzassVar.zzk > 0) {
                if (zzban.zza >= 21) {
                    zze = zzc.zzf(zzassVar.zzj, zzassVar.zzk, zzassVar.zzl);
                } else {
                    zze = zzassVar.zzj * zzassVar.zzk <= zzaxa.zza();
                    if (!zze) {
                        int r3 = zzassVar.zzj;
                        int r9 = zzassVar.zzk;
                        String str2 = zzban.zze;
                        Log.d("MediaCodecVideoRenderer", "FalseCheck [legacyFrameSize, " + r3 + "x" + r9 + "] [" + str2 + "]");
                    }
                }
            }
            return (true != zze ? 2 : 3) | (true != zzc.zzb ? 4 : 8) | (true == zzc.zzc ? 16 : 0);
        }
        return 0;
    }

    final void zzI() {
        if (this.zzk) {
            return;
        }
        this.zzk = true;
        this.zzd.zzg(this.zzi);
    }

    protected final void zzJ(MediaCodec mediaCodec, int r2, long j) {
        zzad();
        zzbal.zza("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(r2, true);
        zzbal.zzb();
        this.zza.zzd++;
        this.zzo = 0;
        zzI();
    }

    protected final void zzK(MediaCodec mediaCodec, int r2, long j, long j2) {
        zzad();
        zzbal.zza("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(r2, j2);
        zzbal.zzb();
        this.zza.zzd++;
        this.zzo = 0;
        zzI();
    }

    protected final void zzL(MediaCodec mediaCodec, int r2, long j) {
        zzbal.zza("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(r2, false);
        zzbal.zzb();
        this.zza.zze++;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.gms.internal.ads.zzawq
    protected final void zzO(zzawo zzawoVar, MediaCodec mediaCodec, zzass zzassVar, MediaCrypto mediaCrypto) throws zzawv {
        char c;
        int r2;
        zzass[] zzassVarArr = this.zzg;
        int r0 = zzassVar.zzj;
        int r1 = zzassVar.zzk;
        int r22 = zzassVar.zzg;
        if (r22 == -1) {
            String str = zzassVar.zzf;
            if (r0 != -1 && r1 != -1) {
                int r8 = 4;
                switch (str.hashCode()) {
                    case -1664118616:
                        if (str.equals(MimeTypes.VIDEO_H263)) {
                            c = 0;
                            break;
                        }
                        c = 65535;
                        break;
                    case -1662541442:
                        if (str.equals(MimeTypes.VIDEO_H265)) {
                            c = 4;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1187890754:
                        if (str.equals(MimeTypes.VIDEO_MP4V)) {
                            c = 1;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1331836730:
                        if (str.equals(MimeTypes.VIDEO_H264)) {
                            c = 2;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1599127256:
                        if (str.equals(MimeTypes.VIDEO_VP8)) {
                            c = 3;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1599127257:
                        if (str.equals(MimeTypes.VIDEO_VP9)) {
                            c = 5;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        c = 65535;
                        break;
                }
                if (c != 0 && c != 1) {
                    if (c != 2) {
                        if (c != 3) {
                            if (c == 4 || c == 5) {
                                r2 = r0 * r1;
                                r22 = (r2 * 3) / (r8 + r8);
                            }
                        }
                    } else if (!"BRAVIA 4K 2015".equals(zzban.zzd)) {
                        r2 = zzban.zzd(r0, 16) * zzban.zzd(r1, 16) * 256;
                        r8 = 2;
                        r22 = (r2 * 3) / (r8 + r8);
                    }
                }
                r2 = r0 * r1;
                r8 = 2;
                r22 = (r2 * 3) / (r8 + r8);
            }
            r22 = -1;
        }
        int length = zzassVarArr.length;
        zzbav zzbavVar = new zzbav(r0, r1, r22);
        this.zzh = zzbavVar;
        boolean z = this.zze;
        MediaFormat zzb = zzassVar.zzb();
        zzb.setInteger("max-width", zzbavVar.zza);
        zzb.setInteger("max-height", zzbavVar.zzb);
        int r15 = zzbavVar.zzc;
        if (r15 != -1) {
            zzb.setInteger("max-input-size", r15);
        }
        if (z) {
            zzb.setInteger("auto-frc", 0);
        }
        if (this.zzi == null) {
            zzazy.zze(zzag(zzawoVar.zzd));
            if (this.zzj == null) {
                this.zzj = zzbat.zza(this.zzb, zzawoVar.zzd);
            }
            this.zzi = this.zzj;
        }
        mediaCodec.configure(zzb, this.zzi, (MediaCrypto) null, 0);
        int r12 = zzban.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final void zzP(String str, long j, long j2) {
        this.zzd.zzb(str, j, j2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzawq
    public final void zzQ(zzass zzassVar) throws zzase {
        super.zzQ(zzassVar);
        this.zzd.zzf(zzassVar);
        float f = zzassVar.zzn;
        if (f == -1.0f) {
            f = 1.0f;
        }
        this.zzq = f;
        this.zzp = zzN(zzassVar);
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final void zzR(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int integer;
        int integer2;
        boolean z = false;
        if (mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top")) {
            z = true;
        }
        if (z) {
            integer = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            integer = mediaFormat.getInteger("width");
        }
        this.zzr = integer;
        if (z) {
            integer2 = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            integer2 = mediaFormat.getInteger("height");
        }
        this.zzs = integer2;
        this.zzu = this.zzq;
        if (zzban.zza >= 21) {
            int r9 = this.zzp;
            if (r9 == 90 || r9 == 270) {
                int r92 = this.zzr;
                this.zzr = this.zzs;
                this.zzs = r92;
                this.zzu = 1.0f / this.zzu;
            }
        } else {
            this.zzt = this.zzp;
        }
        mediaCodec.setVideoScalingMode(1);
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final boolean zzT(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int r26, int r27, long j3, boolean z) {
        while (true) {
            int r0 = this.zzA;
            if (r0 == 0) {
                break;
            }
            long[] jArr = this.zzf;
            long j4 = jArr[0];
            if (j3 < j4) {
                break;
            }
            this.zzz = j4;
            int r02 = r0 - 1;
            this.zzA = r02;
            System.arraycopy(jArr, 1, jArr, 0, r02);
        }
        long j5 = j3 - this.zzz;
        if (z) {
            zzL(mediaCodec, r26, j5);
            return true;
        }
        long j6 = j3 - j;
        if (this.zzi == this.zzj) {
            if (zzaf(j6)) {
                zzL(mediaCodec, r26, j5);
                return true;
            }
            return false;
        } else if (!this.zzk) {
            if (zzban.zza >= 21) {
                zzK(mediaCodec, r26, j5, System.nanoTime());
            } else {
                zzJ(mediaCodec, r26, j5);
            }
            return true;
        } else if (zzb() != 2) {
            return false;
        } else {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long nanoTime = System.nanoTime();
            long zza = this.zzc.zza(j3, ((j6 - ((elapsedRealtime * 1000) - j2)) * 1000) + nanoTime);
            long j7 = (zza - nanoTime) / 1000;
            if (zzaf(j7)) {
                zzbal.zza("dropVideoBuffer");
                mediaCodec.releaseOutputBuffer(r26, false);
                zzbal.zzb();
                zzaum zzaumVar = this.zza;
                zzaumVar.zzf++;
                this.zzn++;
                int r1 = this.zzo + 1;
                this.zzo = r1;
                zzaumVar.zzg = Math.max(r1, zzaumVar.zzg);
                if (this.zzn == -1) {
                    zzac();
                }
                return true;
            }
            if (zzban.zza >= 21) {
                if (j7 < 50000) {
                    zzK(mediaCodec, r26, j5, zza);
                    return true;
                }
            } else if (j7 < 30000) {
                if (j7 > 11000) {
                    try {
                        Thread.sleep((j7 - 10000) / 1000);
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                    }
                }
                zzJ(mediaCodec, r26, j5);
                return true;
            }
            return false;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final void zzX(zzaun zzaunVar) {
        int r1 = zzban.zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzawq
    public final void zzY() {
        try {
            super.zzY();
        } finally {
            Surface surface = this.zzj;
            if (surface != null) {
                if (this.zzi == surface) {
                    this.zzi = null;
                }
                surface.release();
                this.zzj = null;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final boolean zzZ(MediaCodec mediaCodec, boolean z, zzass zzassVar, zzass zzassVar2) {
        if (zzassVar.zzf.equals(zzassVar2.zzf) && zzN(zzassVar) == zzN(zzassVar2)) {
            if (z || (zzassVar.zzj == zzassVar2.zzj && zzassVar.zzk == zzassVar2.zzk)) {
                int r2 = zzassVar2.zzj;
                zzbav zzbavVar = this.zzh;
                return r2 <= zzbavVar.zza && zzassVar2.zzk <= zzbavVar.zzb && zzassVar2.zzg <= zzbavVar.zzc;
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzawq
    protected final boolean zzaa(zzawo zzawoVar) {
        return this.zzi != null || zzag(zzawoVar.zzd);
    }

    @Override // com.google.android.gms.internal.ads.zzasc, com.google.android.gms.internal.ads.zzasg
    public final void zzl(int r5, Object obj) throws zzase {
        if (r5 == 1) {
            Surface surface = (Surface) obj;
            if (surface == null) {
                Surface surface2 = this.zzj;
                if (surface2 != null) {
                    surface = surface2;
                } else {
                    zzawo zzV = zzV();
                    if (zzV != null && zzag(zzV.zzd)) {
                        surface = zzbat.zza(this.zzb, zzV.zzd);
                        this.zzj = surface;
                    }
                }
            }
            if (this.zzi == surface) {
                if (surface == null || surface == this.zzj) {
                    return;
                }
                zzae();
                if (this.zzk) {
                    this.zzd.zzg(this.zzi);
                    return;
                }
                return;
            }
            this.zzi = surface;
            int zzb = zzb();
            if (zzb == 1 || zzb == 2) {
                MediaCodec zzU = zzU();
                if (zzban.zza < 23 || zzU == null || surface == null) {
                    zzY();
                    zzW();
                } else {
                    zzU.setOutputSurface(surface);
                }
            }
            if (surface == null || surface == this.zzj) {
                zzab();
                this.zzk = false;
                int r52 = zzban.zza;
                return;
            }
            zzae();
            this.zzk = false;
            int r6 = zzban.zza;
            if (zzb == 2) {
                this.zzl = C1856C.TIME_UNSET;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzawq, com.google.android.gms.internal.ads.zzasc
    public final void zzn() {
        this.zzr = -1;
        this.zzs = -1;
        this.zzu = -1.0f;
        this.zzq = -1.0f;
        this.zzz = C1856C.TIME_UNSET;
        this.zzA = 0;
        zzab();
        this.zzk = false;
        int r0 = zzban.zza;
        this.zzc.zzb();
        try {
            super.zzn();
        } finally {
            this.zza.zza();
            this.zzd.zzc(this.zza);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzawq, com.google.android.gms.internal.ads.zzasc
    public final void zzo(boolean z) throws zzase {
        super.zzo(z);
        int r2 = zzg().zzb;
        this.zzd.zze(this.zza);
        this.zzc.zzc();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzawq, com.google.android.gms.internal.ads.zzasc
    public final void zzp(long j, boolean z) throws zzase {
        super.zzp(j, z);
        this.zzk = false;
        int r5 = zzban.zza;
        this.zzo = 0;
        int r52 = this.zzA;
        if (r52 != 0) {
            this.zzz = this.zzf[r52 - 1];
            this.zzA = 0;
        }
        this.zzl = C1856C.TIME_UNSET;
    }

    @Override // com.google.android.gms.internal.ads.zzasc
    protected final void zzq() {
        this.zzn = 0;
        this.zzm = SystemClock.elapsedRealtime();
        this.zzl = C1856C.TIME_UNSET;
    }

    @Override // com.google.android.gms.internal.ads.zzasc
    protected final void zzr() {
        zzac();
    }

    @Override // com.google.android.gms.internal.ads.zzasc
    protected final void zzs(zzass[] zzassVarArr, long j) throws zzase {
        this.zzg = zzassVarArr;
        if (this.zzz == C1856C.TIME_UNSET) {
            this.zzz = j;
            return;
        }
        int r5 = this.zzA;
        if (r5 == 10) {
            long j2 = this.zzf[9];
            Log.w("MediaCodecVideoRenderer", "Too many stream changes, so dropping offset: " + j2);
        } else {
            this.zzA = r5 + 1;
        }
        this.zzf[this.zzA - 1] = j;
    }
}
