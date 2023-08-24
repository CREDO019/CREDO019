package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.PlaybackException;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzqo extends zzgp {
    private static final byte[] zzb = {0, 0, 1, 103, 66, -64, Ascii.f1132VT, -38, 37, -112, 0, 0, 1, 104, -50, Ascii.f1128SI, 19, 32, 0, 0, 1, 101, -120, -124, 13, -50, 113, Ascii.CAN, -96, 0, 47, -65, Ascii.f1122FS, 49, -61, 39, 93, 120};
    private float zzA;
    private ArrayDeque zzB;
    private zzqn zzC;
    private zzql zzD;
    private int zzE;
    private boolean zzF;
    private boolean zzG;
    private boolean zzH;
    private boolean zzI;
    private boolean zzJ;
    private boolean zzK;
    private boolean zzL;
    private boolean zzM;
    private boolean zzN;
    private zzqe zzO;
    private long zzP;
    private int zzQ;
    private int zzR;
    private ByteBuffer zzS;
    private boolean zzT;
    private boolean zzU;
    private boolean zzV;
    private boolean zzW;
    private boolean zzX;
    private boolean zzY;
    private int zzZ;
    protected zzgq zza;
    private int zzaa;
    private int zzab;
    private boolean zzac;
    private boolean zzad;
    private boolean zzae;
    private long zzaf;
    private long zzag;
    private boolean zzah;
    private boolean zzai;
    private boolean zzaj;
    private long zzak;
    private long zzal;
    private int zzam;
    private zzpp zzan;
    private zzpp zzao;
    private final zzqi zzc;
    private final zzqq zzd;
    private final float zze;
    private final zzgg zzf;
    private final zzgg zzg;
    private final zzgg zzh;
    private final zzqd zzi;
    private final zzei zzj;
    private final ArrayList zzk;
    private final MediaCodec.BufferInfo zzl;
    private final long[] zzm;
    private final long[] zzn;
    private final long[] zzo;
    private zzaf zzp;
    private zzaf zzq;
    private MediaCrypto zzr;
    private boolean zzs;
    private long zzt;
    private float zzu;
    private float zzv;
    private zzqj zzw;
    private zzaf zzx;
    private MediaFormat zzy;
    private boolean zzz;

    public zzqo(int r3, zzqi zzqiVar, zzqq zzqqVar, boolean z, float f) {
        super(r3);
        this.zzc = zzqiVar;
        Objects.requireNonNull(zzqqVar);
        this.zzd = zzqqVar;
        this.zze = f;
        this.zzf = new zzgg(0, 0);
        this.zzg = new zzgg(0, 0);
        this.zzh = new zzgg(2, 0);
        zzqd zzqdVar = new zzqd();
        this.zzi = zzqdVar;
        this.zzj = new zzei(10);
        this.zzk = new ArrayList();
        this.zzl = new MediaCodec.BufferInfo();
        this.zzu = 1.0f;
        this.zzv = 1.0f;
        this.zzt = C1856C.TIME_UNSET;
        this.zzm = new long[10];
        this.zzn = new long[10];
        this.zzo = new long[10];
        this.zzak = C1856C.TIME_UNSET;
        this.zzal = C1856C.TIME_UNSET;
        zzqdVar.zzi(0);
        zzqdVar.zzb.order(ByteOrder.nativeOrder());
        this.zzA = -1.0f;
        this.zzE = 0;
        this.zzZ = 0;
        this.zzQ = -1;
        this.zzR = -1;
        this.zzP = C1856C.TIME_UNSET;
        this.zzaf = C1856C.TIME_UNSET;
        this.zzag = C1856C.TIME_UNSET;
        this.zzaa = 0;
        this.zzab = 0;
    }

    private final void zzT() {
        this.zzX = false;
        this.zzi.zzb();
        this.zzh.zzb();
        this.zzW = false;
        this.zzV = false;
    }

    private final void zzU() throws zzgy {
        if (this.zzac) {
            this.zzaa = 1;
            this.zzab = 3;
            return;
        }
        zzap();
        zzan();
    }

    private final void zzaA() throws zzgy {
        try {
            throw null;
        } catch (MediaCryptoException e) {
            throw zzbg(e, this.zzp, false, PlaybackException.ERROR_CODE_DRM_SYSTEM_ERROR);
        }
    }

    private final boolean zzaB() throws zzgy {
        if (this.zzac) {
            this.zzaa = 1;
            if (this.zzG || this.zzI) {
                this.zzab = 3;
                return false;
            }
            this.zzab = 2;
        } else {
            zzaA();
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    private final boolean zzaC() throws zzgy {
        zzqj zzqjVar = this.zzw;
        boolean z = 0;
        if (zzqjVar == null || this.zzaa == 2 || this.zzah) {
            return false;
        }
        if (this.zzQ < 0) {
            int zza = zzqjVar.zza();
            this.zzQ = zza;
            if (zza < 0) {
                return false;
            }
            this.zzg.zzb = this.zzw.zzf(zza);
            this.zzg.zzb();
        }
        if (this.zzaa == 1) {
            if (!this.zzN) {
                this.zzad = true;
                this.zzw.zzj(this.zzQ, 0, 0, 0L, 4);
                zzay();
            }
            this.zzaa = 2;
            return false;
        } else if (this.zzL) {
            this.zzL = false;
            this.zzg.zzb.put(zzb);
            this.zzw.zzj(this.zzQ, 0, 38, 0L, 0);
            zzay();
            this.zzac = true;
            return true;
        } else {
            if (this.zzZ == 1) {
                for (int r0 = 0; r0 < this.zzx.zzo.size(); r0++) {
                    this.zzg.zzb.put((byte[]) this.zzx.zzo.get(r0));
                }
                this.zzZ = 2;
            }
            int position = this.zzg.zzb.position();
            zzje zzh = zzh();
            try {
                int zzbf = zzbf(zzh, this.zzg, 0);
                if (zzG()) {
                    this.zzag = this.zzaf;
                }
                if (zzbf == -3) {
                    return false;
                }
                if (zzbf == -5) {
                    if (this.zzZ == 2) {
                        this.zzg.zzb();
                        this.zzZ = 1;
                    }
                    zzS(zzh);
                    return true;
                }
                zzgg zzggVar = this.zzg;
                if (!zzggVar.zzg()) {
                    if (this.zzac || zzggVar.zzh()) {
                        boolean zzk = zzggVar.zzk();
                        if (zzk) {
                            zzggVar.zza.zzb(position);
                        }
                        if (this.zzF && !zzk) {
                            ByteBuffer byteBuffer = this.zzg.zzb;
                            byte[] bArr = zzaac.zza;
                            int position2 = byteBuffer.position();
                            int r5 = 0;
                            int r7 = 0;
                            while (true) {
                                int r8 = r5 + 1;
                                if (r8 < position2) {
                                    int r9 = byteBuffer.get(r5) & 255;
                                    if (r7 == 3) {
                                        if (r9 == 1) {
                                            if ((byteBuffer.get(r8) & Ascii.f1131US) == 7) {
                                                ByteBuffer duplicate = byteBuffer.duplicate();
                                                duplicate.position(r5 - 3);
                                                duplicate.limit(position2);
                                                byteBuffer.position(0);
                                                byteBuffer.put(duplicate);
                                                break;
                                            }
                                            r9 = 1;
                                        }
                                    } else if (r9 == 0) {
                                        r7++;
                                    }
                                    if (r9 != 0) {
                                        r7 = 0;
                                    }
                                    r5 = r8;
                                } else {
                                    byteBuffer.clear();
                                    break;
                                }
                            }
                            if (this.zzg.zzb.position() == 0) {
                                return true;
                            }
                            this.zzF = false;
                        }
                        zzgg zzggVar2 = this.zzg;
                        long j = zzggVar2.zzd;
                        zzqe zzqeVar = this.zzO;
                        if (zzqeVar != null) {
                            j = zzqeVar.zzb(this.zzp, zzggVar2);
                            this.zzaf = Math.max(this.zzaf, this.zzO.zza(this.zzp));
                        }
                        long j2 = j;
                        if (this.zzg.zzf()) {
                            this.zzk.add(Long.valueOf(j2));
                        }
                        if (this.zzaj) {
                            this.zzj.zzd(j2, this.zzp);
                            this.zzaj = false;
                        }
                        this.zzaf = Math.max(this.zzaf, j2);
                        this.zzg.zzj();
                        zzgg zzggVar3 = this.zzg;
                        if (zzggVar3.zze()) {
                            zzam(zzggVar3);
                        }
                        zzad(this.zzg);
                        try {
                            if (zzk) {
                                this.zzw.zzk(this.zzQ, 0, this.zzg.zza, j2, 0);
                            } else {
                                this.zzw.zzj(this.zzQ, 0, this.zzg.zzb.limit(), j2, 0);
                            }
                            zzay();
                            this.zzac = true;
                            this.zzZ = 0;
                            zzgq zzgqVar = this.zza;
                            z = zzgqVar.zzc + 1;
                            zzgqVar.zzc = z;
                            return true;
                        } catch (MediaCodec.CryptoException e) {
                            throw zzbg(e, this.zzp, z, zzel.zzl(e.getErrorCode()));
                        }
                    } else {
                        zzggVar.zzb();
                        if (this.zzZ == 2) {
                            this.zzZ = 1;
                        }
                        return true;
                    }
                }
                if (this.zzZ == 2) {
                    zzggVar.zzb();
                    this.zzZ = 1;
                }
                this.zzah = true;
                if (!this.zzac) {
                    zzax();
                    return false;
                }
                try {
                    if (!this.zzN) {
                        this.zzad = true;
                        this.zzw.zzj(this.zzQ, 0, 0, 0L, 4);
                        zzay();
                    }
                    return false;
                } catch (MediaCodec.CryptoException e2) {
                    throw zzbg(e2, this.zzp, false, zzel.zzl(e2.getErrorCode()));
                }
            } catch (zzgf e3) {
                zzX(e3);
                zzaE(0);
                zzab();
                return true;
            }
        }
    }

    private final boolean zzaD() {
        return this.zzR >= 0;
    }

    private final boolean zzaE(int r4) throws zzgy {
        zzje zzh = zzh();
        this.zzf.zzb();
        int zzbf = zzbf(zzh, this.zzf, r4 | 4);
        if (zzbf == -5) {
            zzS(zzh);
            return true;
        } else if (zzbf == -4 && this.zzf.zzg()) {
            this.zzah = true;
            zzax();
            return false;
        } else {
            return false;
        }
    }

    private final boolean zzaF(long j) {
        return this.zzt == C1856C.TIME_UNSET || SystemClock.elapsedRealtime() - j < this.zzt;
    }

    private final boolean zzaG(zzaf zzafVar) throws zzgy {
        if (zzel.zza >= 23 && this.zzw != null && this.zzab != 3 && zzbe() != 0) {
            float zzP = zzP(this.zzv, zzafVar, zzJ());
            float f = this.zzA;
            if (f == zzP) {
                return true;
            }
            if (zzP == -1.0f) {
                zzU();
                return false;
            } else if (f == -1.0f && zzP <= this.zze) {
                return true;
            } else {
                Bundle bundle = new Bundle();
                bundle.putFloat("operating-rate", zzP);
                this.zzw.zzp(bundle);
                this.zzA = zzP;
            }
        }
        return true;
    }

    private final void zzab() {
        try {
            this.zzw.zzi();
        } finally {
            zzaq();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean zzav(zzaf zzafVar) {
        return zzafVar.zzF == 0;
    }

    private final void zzaw(zzql zzqlVar, MediaCrypto mediaCrypto) throws Exception {
        zzpw zzrfVar;
        int r1;
        String str = zzqlVar.zza;
        float zzP = zzel.zza < 23 ? -1.0f : zzP(this.zzv, this.zzp, zzJ());
        float f = zzP > this.zze ? zzP : -1.0f;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        MediaCodec mediaCodec = null;
        zzqh zzV = zzV(zzqlVar, this.zzp, null, f);
        if (zzel.zza >= 31) {
            zzqm.zza(zzV, zzl());
        }
        try {
            Trace.beginSection("createCodec:" + str);
            if (zzel.zza >= 23 && zzel.zza >= 31) {
                int zzb2 = zzbt.zzb(zzV.zzc.zzm);
                Log.i("DMCodecAdapterFactory", "Creating an asynchronous MediaCodec adapter for track type ".concat(zzel.zzO(zzb2)));
                zzrfVar = new zzpu(zzb2, false).zzc(zzV);
            } else {
                try {
                    zzql zzqlVar2 = zzV.zza;
                    Objects.requireNonNull(zzqlVar2);
                    String str2 = zzqlVar2.zza;
                    Trace.beginSection("createCodec:".concat(String.valueOf(str2)));
                    MediaCodec createByCodecName = MediaCodec.createByCodecName(str2);
                    Trace.endSection();
                    try {
                        Trace.beginSection("configureCodec");
                        createByCodecName.configure(zzV.zzb, zzV.zzd, (MediaCrypto) null, 0);
                        Trace.endSection();
                        Trace.beginSection("startCodec");
                        createByCodecName.start();
                        Trace.endSection();
                        zzrfVar = new zzrf(createByCodecName, null);
                    } catch (IOException | RuntimeException e) {
                        e = e;
                        mediaCodec = createByCodecName;
                        if (mediaCodec != null) {
                            mediaCodec.release();
                        }
                        throw e;
                    }
                } catch (IOException e2) {
                    e = e2;
                } catch (RuntimeException e3) {
                    e = e3;
                }
            }
            this.zzw = zzrfVar;
            Trace.endSection();
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            this.zzD = zzqlVar;
            this.zzA = f;
            this.zzx = this.zzp;
            if (zzel.zza <= 25 && "OMX.Exynos.avc.dec.secure".equals(str) && (zzel.zzd.startsWith("SM-T585") || zzel.zzd.startsWith("SM-A510") || zzel.zzd.startsWith("SM-A520") || zzel.zzd.startsWith("SM-J700"))) {
                r1 = 2;
            } else {
                r1 = (zzel.zza >= 24 || !(("OMX.Nvidia.h264.decode".equals(str) || "OMX.Nvidia.h264.decode.secure".equals(str)) && ("flounder".equals(zzel.zzb) || "flounder_lte".equals(zzel.zzb) || "grouper".equals(zzel.zzb) || "tilapia".equals(zzel.zzb)))) ? 0 : 1;
            }
            this.zzE = r1;
            this.zzF = zzel.zza < 21 && this.zzx.zzo.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str);
            this.zzG = zzel.zza == 19 && zzel.zzd.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(str) || "OMX.Exynos.avc.dec.secure".equals(str));
            this.zzH = zzel.zza == 29 && "c2.android.aac.decoder".equals(str);
            this.zzI = (zzel.zza <= 23 && "OMX.google.vorbis.decoder".equals(str)) || (zzel.zza <= 19 && (("hb2000".equals(zzel.zzb) || "stvm8".equals(zzel.zzb)) && ("OMX.amlogic.avc.decoder.awesome".equals(str) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str))));
            this.zzJ = zzel.zza == 21 && "OMX.google.aac.decoder".equals(str);
            this.zzK = zzel.zza < 21 && "OMX.SEC.mp3.dec".equals(str) && "samsung".equals(zzel.zzc) && (zzel.zzb.startsWith("baffin") || zzel.zzb.startsWith("grand") || zzel.zzb.startsWith("fortuna") || zzel.zzb.startsWith("gprimelte") || zzel.zzb.startsWith("j2y18lte") || zzel.zzb.startsWith("ms01"));
            String str3 = zzqlVar.zza;
            this.zzN = (zzel.zza <= 25 && "OMX.rk.video_decoder.avc".equals(str3)) || (zzel.zza <= 29 && ("OMX.broadcom.video_decoder.tunnel".equals(str3) || "OMX.broadcom.video_decoder.tunnel.secure".equals(str3))) || ("Amazon".equals(zzel.zzc) && "AFTS".equals(zzel.zzd) && zzqlVar.zzf);
            this.zzw.zzr();
            if ("c2.android.mp3.decoder".equals(zzqlVar.zza)) {
                this.zzO = new zzqe();
            }
            if (zzbe() == 2) {
                this.zzP = SystemClock.elapsedRealtime() + 1000;
            }
            this.zza.zza++;
            zzY(str, zzV, elapsedRealtime2, elapsedRealtime2 - elapsedRealtime);
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    private final void zzay() {
        this.zzQ = -1;
        this.zzg.zzb = null;
    }

    private final void zzaz() {
        this.zzR = -1;
        this.zzS = null;
    }

    @Override // com.google.android.gms.internal.ads.zzgp, com.google.android.gms.internal.ads.zzjy
    public void zzD(float f, float f2) throws zzgy {
        this.zzu = f;
        this.zzv = f2;
        zzaG(this.zzx);
    }

    /* JADX WARN: Code restructure failed: missing block: B:145:0x026b, code lost:
        if (r15.zzq != null) goto L204;
     */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x031f A[LOOP:2: B:72:0x0139->B:180:0x031f, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:184:0x032f A[Catch: IllegalStateException -> 0x0360, TryCatch #3 {IllegalStateException -> 0x0360, blocks: (B:177:0x0318, B:182:0x0329, B:184:0x032f, B:186:0x0335, B:173:0x0300, B:175:0x0312, B:193:0x0346), top: B:233:0x012c }] */
    /* JADX WARN: Removed duplicated region for block: B:206:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x031e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0326 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r13v8 */
    @Override // com.google.android.gms.internal.ads.zzjy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzL(long r24, long r26) throws com.google.android.gms.internal.ads.zzgy {
        /*
            Method dump skipped, instructions count: 948
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqo.zzL(long, long):void");
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public boolean zzM() {
        return this.zzai;
    }

    @Override // com.google.android.gms.internal.ads.zzjy
    public boolean zzN() {
        if (this.zzp != null) {
            if (zzI() || zzaD()) {
                return true;
            }
            if (this.zzP != C1856C.TIME_UNSET && SystemClock.elapsedRealtime() < this.zzP) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzjz
    public final int zzO(zzaf zzafVar) throws zzgy {
        try {
            return zzQ(this.zzd, zzafVar);
        } catch (zzqx e) {
            throw zzbg(e, zzafVar, false, PlaybackException.ERROR_CODE_DECODER_QUERY_FAILED);
        }
    }

    protected float zzP(float f, zzaf zzafVar, zzaf[] zzafVarArr) {
        throw null;
    }

    protected abstract int zzQ(zzqq zzqqVar, zzaf zzafVar) throws zzqx;

    protected zzgr zzR(zzql zzqlVar, zzaf zzafVar, zzaf zzafVar2) {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005b, code lost:
        if (zzaB() == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0087, code lost:
        if (zzaB() == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x009b, code lost:
        if (zzaB() == false) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.google.android.gms.internal.ads.zzgr zzS(com.google.android.gms.internal.ads.zzje r13) throws com.google.android.gms.internal.ads.zzgy {
        /*
            Method dump skipped, instructions count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqo.zzS(com.google.android.gms.internal.ads.zzje):com.google.android.gms.internal.ads.zzgr");
    }

    protected abstract zzqh zzV(zzql zzqlVar, zzaf zzafVar, MediaCrypto mediaCrypto, float f);

    protected abstract List zzW(zzqq zzqqVar, zzaf zzafVar, boolean z) throws zzqx;

    protected void zzX(Exception exc) {
        throw null;
    }

    protected void zzY(String str, zzqh zzqhVar, long j, long j2) {
        throw null;
    }

    protected void zzZ(String str) {
        throw null;
    }

    protected void zzaa(zzaf zzafVar, MediaFormat mediaFormat) throws zzgy {
        throw null;
    }

    protected void zzac() {
    }

    protected void zzad(zzgg zzggVar) throws zzgy {
        throw null;
    }

    protected void zzae() throws zzgy {
    }

    protected abstract boolean zzaf(long j, long j2, zzqj zzqjVar, ByteBuffer byteBuffer, int r7, int r8, int r9, long j3, boolean z, boolean z2, zzaf zzafVar) throws zzgy;

    protected boolean zzag(zzaf zzafVar) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float zzah() {
        return this.zzu;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long zzai() {
        return this.zzal;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzqj zzaj() {
        return this.zzw;
    }

    protected zzqk zzak(Throwable th, zzql zzqlVar) {
        return new zzqk(th, zzqlVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzql zzal() {
        return this.zzD;
    }

    protected void zzam(zzgg zzggVar) throws zzgy {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0100 A[Catch: zzqn -> 0x0123, TryCatch #3 {zzqn -> 0x0123, blocks: (B:30:0x0069, B:33:0x006e, B:35:0x0086, B:36:0x0091, B:41:0x00a0, B:43:0x00a8, B:44:0x00b0, B:46:0x00b4, B:57:0x00dc, B:59:0x0100, B:61:0x0109, B:64:0x0112, B:65:0x0114, B:60:0x0103, B:66:0x0115, B:68:0x0118, B:69:0x0122, B:39:0x0095, B:40:0x009f, B:54:0x00cc, B:55:0x00da, B:49:0x00c3), top: B:80:0x0069, inners: #0, #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0103 A[Catch: zzqn -> 0x0123, TryCatch #3 {zzqn -> 0x0123, blocks: (B:30:0x0069, B:33:0x006e, B:35:0x0086, B:36:0x0091, B:41:0x00a0, B:43:0x00a8, B:44:0x00b0, B:46:0x00b4, B:57:0x00dc, B:59:0x0100, B:61:0x0109, B:64:0x0112, B:65:0x0114, B:60:0x0103, B:66:0x0115, B:68:0x0118, B:69:0x0122, B:39:0x0095, B:40:0x009f, B:54:0x00cc, B:55:0x00da, B:49:0x00c3), top: B:80:0x0069, inners: #0, #1, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0112 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzan() throws com.google.android.gms.internal.ads.zzgy {
        /*
            Method dump skipped, instructions count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzqo.zzan():void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zzao(long j) {
        while (true) {
            int r0 = this.zzam;
            if (r0 == 0 || j < this.zzo[0]) {
                return;
            }
            long[] jArr = this.zzm;
            this.zzak = jArr[0];
            this.zzal = this.zzn[0];
            int r02 = r0 - 1;
            this.zzam = r02;
            System.arraycopy(jArr, 1, jArr, 0, r02);
            long[] jArr2 = this.zzn;
            System.arraycopy(jArr2, 1, jArr2, 0, this.zzam);
            long[] jArr3 = this.zzo;
            System.arraycopy(jArr3, 1, jArr3, 0, this.zzam);
            zzac();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.android.gms.internal.ads.zzpp, com.google.android.gms.internal.ads.zzqj, android.media.MediaCrypto] */
    public final void zzap() {
        try {
            zzqj zzqjVar = this.zzw;
            if (zzqjVar != null) {
                zzqjVar.zzl();
                this.zza.zzb++;
                zzZ(this.zzD.zza);
            }
        } finally {
            this.zzw = null;
            this.zzr = null;
            this.zzan = null;
            zzar();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zzaq() {
        zzay();
        zzaz();
        this.zzP = C1856C.TIME_UNSET;
        this.zzad = false;
        this.zzac = false;
        this.zzL = false;
        this.zzM = false;
        this.zzT = false;
        this.zzU = false;
        this.zzk.clear();
        this.zzaf = C1856C.TIME_UNSET;
        this.zzag = C1856C.TIME_UNSET;
        zzqe zzqeVar = this.zzO;
        if (zzqeVar != null) {
            zzqeVar.zzc();
        }
        this.zzaa = 0;
        this.zzab = 0;
        this.zzZ = this.zzY ? 1 : 0;
    }

    protected final void zzar() {
        zzaq();
        this.zzO = null;
        this.zzB = null;
        this.zzD = null;
        this.zzx = null;
        this.zzy = null;
        this.zzz = false;
        this.zzae = false;
        this.zzA = -1.0f;
        this.zzE = 0;
        this.zzF = false;
        this.zzG = false;
        this.zzH = false;
        this.zzI = false;
        this.zzJ = false;
        this.zzK = false;
        this.zzN = false;
        this.zzY = false;
        this.zzZ = 0;
        this.zzs = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean zzas() throws zzgy {
        boolean zzat = zzat();
        if (zzat) {
            zzan();
        }
        return zzat;
    }

    protected final boolean zzat() {
        if (this.zzw == null) {
            return false;
        }
        int r0 = this.zzab;
        if (r0 != 3 && !this.zzG && ((!this.zzH || this.zzae) && (!this.zzI || !this.zzad))) {
            if (r0 == 2) {
                zzdd.zzf(zzel.zza >= 23);
                if (zzel.zza >= 23) {
                    try {
                        zzaA();
                    } catch (zzgy e) {
                        zzdu.zzb("MediaCodecRenderer", "Failed to update the DRM session, releasing the codec instead.", e);
                        zzap();
                        return true;
                    }
                }
            }
            zzab();
            return false;
        }
        zzap();
        return true;
    }

    protected boolean zzau(zzql zzqlVar) {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzgp, com.google.android.gms.internal.ads.zzjz
    public final int zze() {
        return 8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgp
    public void zzs() {
        this.zzp = null;
        this.zzak = C1856C.TIME_UNSET;
        this.zzal = C1856C.TIME_UNSET;
        this.zzam = 0;
        zzat();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgp
    public void zzt(boolean z, boolean z2) throws zzgy {
        this.zza = new zzgq();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgp
    public void zzu(long j, boolean z) throws zzgy {
        this.zzah = false;
        this.zzai = false;
        if (this.zzV) {
            this.zzi.zzb();
            this.zzh.zzb();
            this.zzW = false;
        } else {
            zzas();
        }
        zzei zzeiVar = this.zzj;
        if (zzeiVar.zza() > 0) {
            this.zzaj = true;
        }
        zzeiVar.zze();
        int r4 = this.zzam;
        if (r4 != 0) {
            int r42 = r4 - 1;
            this.zzal = this.zzn[r42];
            this.zzak = this.zzm[r42];
            this.zzam = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzgp
    public void zzv() {
        try {
            zzT();
            zzap();
        } finally {
            this.zzao = null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgp
    protected final void zzy(zzaf[] zzafVarArr, long j, long j2) throws zzgy {
        if (this.zzal == C1856C.TIME_UNSET) {
            zzdd.zzf(this.zzak == C1856C.TIME_UNSET);
            this.zzak = j;
            this.zzal = j2;
            return;
        }
        int r0 = this.zzam;
        if (r0 == 10) {
            Log.w("MediaCodecRenderer", "Too many stream changes, so dropping offset: " + this.zzn[9]);
        } else {
            this.zzam = r0 + 1;
        }
        long[] jArr = this.zzm;
        int r02 = this.zzam - 1;
        jArr[r02] = j;
        this.zzn[r02] = j2;
        this.zzo[r02] = this.zzaf;
    }

    private final void zzax() throws zzgy {
        int r0 = this.zzab;
        if (r0 == 1) {
            zzab();
        } else if (r0 == 2) {
            zzab();
            zzaA();
        } else if (r0 != 3) {
            this.zzai = true;
            zzae();
        } else {
            zzap();
            zzan();
        }
    }
}
