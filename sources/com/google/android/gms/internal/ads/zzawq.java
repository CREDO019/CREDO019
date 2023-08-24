package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.SystemClock;
import com.google.android.exoplayer2.C1856C;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzawq extends zzasc {
    private static final byte[] zzb = zzban.zzp("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    private boolean zzA;
    private int zzB;
    private int zzC;
    private boolean zzD;
    private boolean zzE;
    private boolean zzF;
    private boolean zzG;
    private boolean zzH;
    protected zzaum zza;
    private final zzaws zzc;
    private final zzaun zzd;
    private final zzaun zze;
    private final zzast zzf;
    private final List zzg;
    private final MediaCodec.BufferInfo zzh;
    private zzass zzi;
    private MediaCodec zzj;
    private zzawo zzk;
    private boolean zzl;
    private boolean zzm;
    private boolean zzn;
    private boolean zzo;
    private boolean zzp;
    private boolean zzq;
    private boolean zzr;
    private boolean zzs;
    private boolean zzt;
    private ByteBuffer[] zzu;
    private ByteBuffer[] zzv;
    private long zzw;
    private int zzx;
    private int zzy;
    private boolean zzz;

    public zzawq(int r1, zzaws zzawsVar, zzaus zzausVar, boolean z) {
        super(r1);
        zzazy.zze(zzban.zza >= 16);
        this.zzc = zzawsVar;
        this.zzd = new zzaun(0);
        this.zze = new zzaun(0);
        this.zzf = new zzast();
        this.zzg = new ArrayList();
        this.zzh = new MediaCodec.BufferInfo();
        this.zzB = 0;
        this.zzC = 0;
    }

    private final void zzI() throws zzase {
        if (this.zzC == 2) {
            zzY();
            zzW();
            return;
        }
        this.zzG = true;
        zzS();
    }

    private final boolean zzJ() throws zzase {
        MediaCodec mediaCodec = this.zzj;
        if (mediaCodec == null || this.zzC == 2 || this.zzF) {
            return false;
        }
        if (this.zzx < 0) {
            int dequeueInputBuffer = mediaCodec.dequeueInputBuffer(0L);
            this.zzx = dequeueInputBuffer;
            if (dequeueInputBuffer < 0) {
                return false;
            }
            zzaun zzaunVar = this.zzd;
            zzaunVar.zzb = this.zzu[dequeueInputBuffer];
            zzaunVar.zzb();
        }
        if (this.zzC == 1) {
            if (!this.zzo) {
                this.zzE = true;
                this.zzj.queueInputBuffer(this.zzx, 0, 0, 0L, 4);
                this.zzx = -1;
            }
            this.zzC = 2;
            return false;
        } else if (this.zzs) {
            this.zzs = false;
            ByteBuffer byteBuffer = this.zzd.zzb;
            byte[] bArr = zzb;
            byteBuffer.put(bArr);
            MediaCodec mediaCodec2 = this.zzj;
            int r6 = this.zzx;
            int length = bArr.length;
            mediaCodec2.queueInputBuffer(r6, 0, 38, 0L, 0);
            this.zzx = -1;
            this.zzD = true;
            return true;
        } else {
            if (this.zzB == 1) {
                for (int r0 = 0; r0 < this.zzi.zzh.size(); r0++) {
                    this.zzd.zzb.put((byte[]) this.zzi.zzh.get(r0));
                }
                this.zzB = 2;
            }
            int position = this.zzd.zzb.position();
            int zzd = zzd(this.zzf, this.zzd, false);
            if (zzd == -3) {
                return false;
            }
            if (zzd == -5) {
                if (this.zzB == 2) {
                    this.zzd.zzb();
                    this.zzB = 1;
                }
                zzQ(this.zzf.zza);
                return true;
            }
            zzaun zzaunVar2 = this.zzd;
            if (!zzaunVar2.zzf()) {
                if (!this.zzH || zzaunVar2.zzg()) {
                    this.zzH = false;
                    boolean zzi = zzaunVar2.zzi();
                    if (this.zzl && !zzi) {
                        ByteBuffer byteBuffer2 = zzaunVar2.zzb;
                        byte[] bArr2 = zzbae.zza;
                        int position2 = byteBuffer2.position();
                        int r8 = 0;
                        int r9 = 0;
                        while (true) {
                            int r10 = r8 + 1;
                            if (r10 < position2) {
                                int r11 = byteBuffer2.get(r8) & 255;
                                if (r9 == 3) {
                                    if (r11 == 1) {
                                        if ((byteBuffer2.get(r10) & Ascii.f1131US) == 7) {
                                            ByteBuffer duplicate = byteBuffer2.duplicate();
                                            duplicate.position(r8 - 3);
                                            duplicate.limit(position2);
                                            byteBuffer2.position(0);
                                            byteBuffer2.put(duplicate);
                                            break;
                                        }
                                        r11 = 1;
                                    }
                                } else if (r11 == 0) {
                                    r9++;
                                }
                                if (r11 != 0) {
                                    r9 = 0;
                                }
                                r8 = r10;
                            } else {
                                byteBuffer2.clear();
                                break;
                            }
                        }
                        if (this.zzd.zzb.position() == 0) {
                            return true;
                        }
                        this.zzl = false;
                    }
                    try {
                        zzaun zzaunVar3 = this.zzd;
                        long j = zzaunVar3.zzc;
                        if (zzaunVar3.zze()) {
                            this.zzg.add(Long.valueOf(j));
                        }
                        this.zzd.zzb.flip();
                        zzX(this.zzd);
                        if (zzi) {
                            MediaCodec.CryptoInfo zza = this.zzd.zza.zza();
                            if (position != 0) {
                                if (zza.numBytesOfClearData == null) {
                                    zza.numBytesOfClearData = new int[1];
                                }
                                int[] r3 = zza.numBytesOfClearData;
                                r3[0] = r3[0] + position;
                            }
                            this.zzj.queueSecureInputBuffer(this.zzx, 0, zza, j, 0);
                        } else {
                            this.zzj.queueInputBuffer(this.zzx, 0, this.zzd.zzb.limit(), j, 0);
                        }
                        this.zzx = -1;
                        this.zzD = true;
                        this.zzB = 0;
                        this.zza.zzc++;
                        return true;
                    } catch (MediaCodec.CryptoException e) {
                        throw zzase.zza(e, zza());
                    }
                } else {
                    zzaunVar2.zzb();
                    if (this.zzB == 2) {
                        this.zzB = 1;
                    }
                    return true;
                }
            }
            if (this.zzB == 2) {
                zzaunVar2.zzb();
                this.zzB = 1;
            }
            this.zzF = true;
            if (!this.zzD) {
                zzI();
                return false;
            }
            try {
                if (!this.zzo) {
                    this.zzE = true;
                    this.zzj.queueInputBuffer(this.zzx, 0, 0, 0L, 4);
                    this.zzx = -1;
                }
                return false;
            } catch (MediaCodec.CryptoException e2) {
                throw zzase.zza(e2, zza());
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public final void zzD(long j, long j2) throws zzase {
        boolean zzT;
        int dequeueOutputBuffer;
        boolean z;
        if (this.zzG) {
            zzS();
            return;
        }
        if (this.zzi == null) {
            this.zze.zzb();
            int zzd = zzd(this.zzf, this.zze, true);
            if (zzd != -5) {
                if (zzd == -4) {
                    zzazy.zze(this.zze.zzf());
                    this.zzF = true;
                    zzI();
                    return;
                }
                return;
            }
            zzQ(this.zzf.zza);
        }
        zzW();
        if (this.zzj != null) {
            zzbal.zza("drainAndFeed");
            while (true) {
                if (this.zzy < 0) {
                    if (!this.zzq || !this.zzE) {
                        dequeueOutputBuffer = this.zzj.dequeueOutputBuffer(this.zzh, 0L);
                        this.zzy = dequeueOutputBuffer;
                    } else {
                        try {
                            dequeueOutputBuffer = this.zzj.dequeueOutputBuffer(this.zzh, 0L);
                            this.zzy = dequeueOutputBuffer;
                        } catch (IllegalStateException unused) {
                            zzI();
                            if (this.zzG) {
                                zzY();
                            }
                        }
                    }
                    if (dequeueOutputBuffer >= 0) {
                        if (this.zzt) {
                            this.zzt = false;
                            this.zzj.releaseOutputBuffer(dequeueOutputBuffer, false);
                            this.zzy = -1;
                        } else if ((this.zzh.flags & 4) == 0) {
                            ByteBuffer byteBuffer = this.zzv[this.zzy];
                            if (byteBuffer != null) {
                                byteBuffer.position(this.zzh.offset);
                                byteBuffer.limit(this.zzh.offset + this.zzh.size);
                            }
                            long j3 = this.zzh.presentationTimeUs;
                            int size = this.zzg.size();
                            int r3 = 0;
                            while (true) {
                                if (r3 >= size) {
                                    z = false;
                                    break;
                                } else if (((Long) this.zzg.get(r3)).longValue() == j3) {
                                    this.zzg.remove(r3);
                                    z = true;
                                    break;
                                } else {
                                    r3++;
                                }
                            }
                            this.zzz = z;
                        } else {
                            zzI();
                            this.zzy = -1;
                            break;
                        }
                    } else if (dequeueOutputBuffer == -2) {
                        MediaFormat outputFormat = this.zzj.getOutputFormat();
                        if (this.zzn && outputFormat.getInteger("width") == 32 && outputFormat.getInteger("height") == 32) {
                            this.zzt = true;
                        } else {
                            if (this.zzr) {
                                outputFormat.setInteger("channel-count", 1);
                            }
                            zzR(this.zzj, outputFormat);
                        }
                    } else if (dequeueOutputBuffer == -3) {
                        this.zzv = this.zzj.getOutputBuffers();
                    } else if (this.zzo && (this.zzF || this.zzC == 2)) {
                        zzI();
                    }
                }
                if (this.zzq && this.zzE) {
                    try {
                        MediaCodec mediaCodec = this.zzj;
                        ByteBuffer[] byteBufferArr = this.zzv;
                        int r7 = this.zzy;
                        zzT = zzT(j, j2, mediaCodec, byteBufferArr[r7], r7, this.zzh.flags, this.zzh.presentationTimeUs, this.zzz);
                    } catch (IllegalStateException unused2) {
                        zzI();
                        if (this.zzG) {
                            zzY();
                        }
                    }
                } else {
                    MediaCodec mediaCodec2 = this.zzj;
                    ByteBuffer[] byteBufferArr2 = this.zzv;
                    int r72 = this.zzy;
                    zzT = zzT(j, j2, mediaCodec2, byteBufferArr2[r72], r72, this.zzh.flags, this.zzh.presentationTimeUs, this.zzz);
                }
                if (!zzT) {
                    break;
                }
                long j4 = this.zzh.presentationTimeUs;
                this.zzy = -1;
            }
            do {
            } while (zzJ());
            zzbal.zzb();
        } else {
            zzx(j);
            this.zze.zzb();
            int zzd2 = zzd(this.zzf, this.zze, false);
            if (zzd2 == -5) {
                zzQ(this.zzf.zza);
            } else if (zzd2 == -4) {
                zzazy.zze(this.zze.zzf());
                this.zzF = true;
                zzI();
            }
        }
        this.zza.zza();
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public boolean zzE() {
        return this.zzG;
    }

    @Override // com.google.android.gms.internal.ads.zzasx
    public boolean zzF() {
        if (this.zzi != null) {
            if (zzC() || this.zzy >= 0) {
                return true;
            }
            if (this.zzw != C1856C.TIME_UNSET && SystemClock.elapsedRealtime() < this.zzw) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzasy
    public final int zzG(zzass zzassVar) throws zzase {
        try {
            return zzH(this.zzc, zzassVar);
        } catch (zzawv e) {
            throw zzase.zza(e, zza());
        }
    }

    protected abstract int zzH(zzaws zzawsVar, zzass zzassVar) throws zzawv;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzawo zzM(zzaws zzawsVar, zzass zzassVar, boolean z) throws zzawv {
        return zzaxa.zzc(zzassVar.zzf, false);
    }

    protected abstract void zzO(zzawo zzawoVar, MediaCodec mediaCodec, zzass zzassVar, MediaCrypto mediaCrypto) throws zzawv;

    protected void zzP(String str, long j, long j2) {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004f, code lost:
        if (r6.zzk == r0.zzk) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void zzQ(com.google.android.gms.internal.ads.zzass r6) throws com.google.android.gms.internal.ads.zzase {
        /*
            r5 = this;
            com.google.android.gms.internal.ads.zzass r0 = r5.zzi
            r5.zzi = r6
            com.google.android.gms.internal.ads.zzaur r6 = r6.zzi
            if (r0 != 0) goto La
            r1 = 0
            goto Lc
        La:
            com.google.android.gms.internal.ads.zzaur r1 = r0.zzi
        Lc:
            boolean r6 = com.google.android.gms.internal.ads.zzban.zzo(r6, r1)
            if (r6 != 0) goto L29
            com.google.android.gms.internal.ads.zzass r6 = r5.zzi
            com.google.android.gms.internal.ads.zzaur r6 = r6.zzi
            if (r6 != 0) goto L19
            goto L29
        L19:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "Media requires a DrmSessionManager"
            r6.<init>(r0)
            int r0 = r5.zza()
            com.google.android.gms.internal.ads.zzase r6 = com.google.android.gms.internal.ads.zzase.zza(r6, r0)
            throw r6
        L29:
            android.media.MediaCodec r6 = r5.zzj
            r1 = 1
            if (r6 == 0) goto L56
            com.google.android.gms.internal.ads.zzawo r2 = r5.zzk
            boolean r2 = r2.zzb
            com.google.android.gms.internal.ads.zzass r3 = r5.zzi
            boolean r6 = r5.zzZ(r6, r2, r0, r3)
            if (r6 == 0) goto L56
            r5.zzA = r1
            r5.zzB = r1
            boolean r6 = r5.zzn
            r2 = 0
            if (r6 == 0) goto L52
            com.google.android.gms.internal.ads.zzass r6 = r5.zzi
            int r3 = r6.zzj
            int r4 = r0.zzj
            if (r3 != r4) goto L52
            int r6 = r6.zzk
            int r0 = r0.zzk
            if (r6 != r0) goto L52
            goto L53
        L52:
            r1 = 0
        L53:
            r5.zzs = r1
            return
        L56:
            boolean r6 = r5.zzD
            if (r6 == 0) goto L5d
            r5.zzC = r1
            return
        L5d:
            r5.zzY()
            r5.zzW()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzawq.zzQ(com.google.android.gms.internal.ads.zzass):void");
    }

    protected void zzR(MediaCodec mediaCodec, MediaFormat mediaFormat) throws zzase {
        throw null;
    }

    protected void zzS() throws zzase {
    }

    protected abstract boolean zzT(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int r7, int r8, long j3, boolean z) throws zzase;

    /* JADX INFO: Access modifiers changed from: protected */
    public final MediaCodec zzU() {
        return this.zzj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzawo zzV() {
        return this.zzk;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzW() throws zzase {
        zzass zzassVar;
        if (this.zzj != null || (zzassVar = this.zzi) == null) {
            return;
        }
        zzawo zzawoVar = this.zzk;
        if (zzawoVar == null) {
            try {
                zzawoVar = zzM(this.zzc, zzassVar, false);
                this.zzk = zzawoVar;
                if (zzawoVar == null) {
                    throw zzase.zza(new zzawp(this.zzi, (Throwable) null, false, -49999), zza());
                }
            } catch (zzawv e) {
                throw zzase.zza(new zzawp(this.zzi, (Throwable) e, false, -49998), zza());
            }
        }
        if (zzaa(zzawoVar)) {
            String str = this.zzk.zza;
            this.zzl = zzban.zza < 21 && this.zzi.zzh.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str);
            this.zzm = zzban.zza < 18 || (zzban.zza == 18 && ("OMX.SEC.avc.dec".equals(str) || "OMX.SEC.avc.dec.secure".equals(str))) || (zzban.zza == 19 && zzban.zzd.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(str) || "OMX.Exynos.avc.dec.secure".equals(str)));
            this.zzn = zzban.zza < 24 && ("OMX.Nvidia.h264.decode".equals(str) || "OMX.Nvidia.h264.decode.secure".equals(str)) && ("flounder".equals(zzban.zzb) || "flounder_lte".equals(zzban.zzb) || "grouper".equals(zzban.zzb) || "tilapia".equals(zzban.zzb));
            this.zzo = zzban.zza <= 17 && ("OMX.rk.video_decoder.avc".equals(str) || "OMX.allwinner.video.decoder.avc".equals(str));
            this.zzp = (zzban.zza <= 23 && "OMX.google.vorbis.decoder".equals(str)) || (zzban.zza <= 19 && "hb2000".equals(zzban.zzb) && ("OMX.amlogic.avc.decoder.awesome".equals(str) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str)));
            this.zzq = zzban.zza == 21 && "OMX.google.aac.decoder".equals(str);
            this.zzr = zzban.zza <= 18 && this.zzi.zzr == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(str);
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                zzbal.zza("createCodec:" + str);
                this.zzj = MediaCodec.createByCodecName(str);
                zzbal.zzb();
                zzbal.zza("configureCodec");
                zzO(this.zzk, this.zzj, this.zzi, null);
                zzbal.zzb();
                zzbal.zza("startCodec");
                this.zzj.start();
                zzbal.zzb();
                long elapsedRealtime2 = SystemClock.elapsedRealtime();
                zzP(str, elapsedRealtime2, elapsedRealtime2 - elapsedRealtime);
                this.zzu = this.zzj.getInputBuffers();
                this.zzv = this.zzj.getOutputBuffers();
                this.zzw = zzb() == 2 ? SystemClock.elapsedRealtime() + 1000 : C1856C.TIME_UNSET;
                this.zzx = -1;
                this.zzy = -1;
                this.zzH = true;
                this.zza.zza++;
            } catch (Exception e2) {
                throw zzase.zza(new zzawp(this.zzi, (Throwable) e2, false, str), zza());
            }
        }
    }

    protected void zzX(zzaun zzaunVar) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zzY() {
        this.zzw = C1856C.TIME_UNSET;
        this.zzx = -1;
        this.zzy = -1;
        this.zzz = false;
        this.zzg.clear();
        this.zzu = null;
        this.zzv = null;
        this.zzk = null;
        this.zzA = false;
        this.zzD = false;
        this.zzl = false;
        this.zzm = false;
        this.zzn = false;
        this.zzo = false;
        this.zzp = false;
        this.zzr = false;
        this.zzs = false;
        this.zzt = false;
        this.zzE = false;
        this.zzB = 0;
        this.zzC = 0;
        this.zzd.zzb = null;
        MediaCodec mediaCodec = this.zzj;
        if (mediaCodec != null) {
            this.zza.zzb++;
            try {
                mediaCodec.stop();
                try {
                    this.zzj.release();
                } finally {
                }
            } catch (Throwable th) {
                try {
                    this.zzj.release();
                    throw th;
                } finally {
                }
            }
        }
    }

    protected boolean zzZ(MediaCodec mediaCodec, boolean z, zzass zzassVar, zzass zzassVar2) {
        return false;
    }

    protected boolean zzaa(zzawo zzawoVar) {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzasc, com.google.android.gms.internal.ads.zzasy
    public final int zze() {
        return 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzasc
    public void zzo(boolean z) throws zzase {
        this.zza = new zzaum();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzasc
    public void zzp(long j, boolean z) throws zzase {
        this.zzF = false;
        this.zzG = false;
        if (this.zzj != null) {
            this.zzw = C1856C.TIME_UNSET;
            this.zzx = -1;
            this.zzy = -1;
            this.zzH = true;
            this.zzz = false;
            this.zzg.clear();
            this.zzs = false;
            this.zzt = false;
            if (this.zzm || (this.zzp && this.zzE)) {
                zzY();
                zzW();
            } else if (this.zzC != 0) {
                zzY();
                zzW();
            } else {
                this.zzj.flush();
                this.zzD = false;
            }
            if (!this.zzA || this.zzi == null) {
                return;
            }
            this.zzB = 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzasc
    public void zzn() {
        this.zzi = null;
        zzY();
    }
}
