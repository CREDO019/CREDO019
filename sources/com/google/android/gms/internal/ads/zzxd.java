package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import android.util.Pair;
import android.view.Surface;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.common.Scopes;
import com.onesignal.OneSignalRemoteParams;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzxd extends zzqo {
    private static final int[] zzb = {1920, 1600, OneSignalRemoteParams.DEFAULT_INDIRECT_ATTRIBUTION_WINDOW, 1280, 960, 854, 640, 540, 480};
    private static boolean zzc;
    private static boolean zzd;
    private long zzA;
    private int zzB;
    private int zzC;
    private int zzD;
    private int zzE;
    private float zzF;
    private zzda zzG;
    private int zzH;
    private zzxh zzI;
    private final Context zze;
    private final zzxo zzf;
    private final zzxz zzg;
    private final boolean zzh;
    private zzxc zzi;
    private boolean zzj;
    private boolean zzk;
    private Surface zzl;
    private zzxg zzm;
    private boolean zzn;
    private int zzo;
    private boolean zzp;
    private boolean zzq;
    private boolean zzr;
    private long zzs;
    private long zzt;
    private long zzu;
    private int zzv;
    private int zzw;
    private int zzx;
    private long zzy;
    private long zzz;

    public zzxd(Context context, zzqi zzqiVar, zzqq zzqqVar, long j, boolean z, Handler handler, zzya zzyaVar, int r16, float f) {
        super(2, zzqiVar, zzqqVar, false, 30.0f);
        Context applicationContext = context.getApplicationContext();
        this.zze = applicationContext;
        this.zzf = new zzxo(applicationContext);
        this.zzg = new zzxz(handler, zzyaVar);
        this.zzh = "NVIDIA".equals(zzel.zzc);
        this.zzt = C1856C.TIME_UNSET;
        this.zzC = -1;
        this.zzD = -1;
        this.zzF = -1.0f;
        this.zzo = 1;
        this.zzH = 0;
        this.zzG = null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0060, code lost:
        if (r3.equals(com.google.android.exoplayer2.util.MimeTypes.VIDEO_MP4V) != false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int zzT(com.google.android.gms.internal.ads.zzql r10, com.google.android.gms.internal.ads.zzaf r11) {
        /*
            int r0 = r11.zzr
            int r1 = r11.zzs
            r2 = -1
            if (r0 == r2) goto Lcb
            if (r1 != r2) goto Lb
            goto Lcb
        Lb:
            java.lang.String r3 = r11.zzm
            java.lang.String r4 = "video/dolby-vision"
            boolean r4 = r4.equals(r3)
            r5 = 1
            java.lang.String r6 = "video/avc"
            java.lang.String r7 = "video/hevc"
            r8 = 2
            if (r4 == 0) goto L34
            android.util.Pair r11 = com.google.android.gms.internal.ads.zzrd.zzb(r11)
            if (r11 == 0) goto L33
            java.lang.Object r11 = r11.first
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            r3 = 512(0x200, float:7.175E-43)
            if (r11 == r3) goto L31
            if (r11 == r5) goto L31
            if (r11 != r8) goto L33
        L31:
            r3 = r6
            goto L34
        L33:
            r3 = r7
        L34:
            int r11 = r3.hashCode()
            r4 = 4
            r9 = 3
            switch(r11) {
                case -1664118616: goto L75;
                case -1662735862: goto L6b;
                case -1662541442: goto L63;
                case 1187890754: goto L5a;
                case 1331836730: goto L52;
                case 1599127256: goto L48;
                case 1599127257: goto L3e;
                default: goto L3d;
            }
        L3d:
            goto L7f
        L3e:
            java.lang.String r11 = "video/x-vnd.on2.vp9"
            boolean r11 = r3.equals(r11)
            if (r11 == 0) goto L7f
            r5 = 6
            goto L80
        L48:
            java.lang.String r11 = "video/x-vnd.on2.vp8"
            boolean r11 = r3.equals(r11)
            if (r11 == 0) goto L7f
            r5 = 4
            goto L80
        L52:
            boolean r11 = r3.equals(r6)
            if (r11 == 0) goto L7f
            r5 = 2
            goto L80
        L5a:
            java.lang.String r11 = "video/mp4v-es"
            boolean r11 = r3.equals(r11)
            if (r11 == 0) goto L7f
            goto L80
        L63:
            boolean r11 = r3.equals(r7)
            if (r11 == 0) goto L7f
            r5 = 5
            goto L80
        L6b:
            java.lang.String r11 = "video/av01"
            boolean r11 = r3.equals(r11)
            if (r11 == 0) goto L7f
            r5 = 3
            goto L80
        L75:
            java.lang.String r11 = "video/3gpp"
            boolean r11 = r3.equals(r11)
            if (r11 == 0) goto L7f
            r5 = 0
            goto L80
        L7f:
            r5 = -1
        L80:
            switch(r5) {
                case 0: goto Lc4;
                case 1: goto Lc4;
                case 2: goto L88;
                case 3: goto Lc4;
                case 4: goto Lc4;
                case 5: goto L84;
                case 6: goto L84;
                default: goto L83;
            }
        L83:
            return r2
        L84:
            int r0 = r0 * r1
            r8 = 4
            goto Lc6
        L88:
            java.lang.String r11 = com.google.android.gms.internal.ads.zzel.zzd
            java.lang.String r3 = "BRAVIA 4K 2015"
            boolean r11 = r3.equals(r11)
            if (r11 != 0) goto Lc3
            java.lang.String r11 = com.google.android.gms.internal.ads.zzel.zzc
            java.lang.String r3 = "Amazon"
            boolean r11 = r3.equals(r11)
            if (r11 == 0) goto Lb4
            java.lang.String r11 = com.google.android.gms.internal.ads.zzel.zzd
            java.lang.String r3 = "KFSOWI"
            boolean r11 = r3.equals(r11)
            if (r11 != 0) goto Lc3
            java.lang.String r11 = com.google.android.gms.internal.ads.zzel.zzd
            java.lang.String r3 = "AFTS"
            boolean r11 = r3.equals(r11)
            if (r11 == 0) goto Lb4
            boolean r10 = r10.zzf
            if (r10 != 0) goto Lc3
        Lb4:
            r10 = 16
            int r11 = com.google.android.gms.internal.ads.zzel.zze(r0, r10)
            int r10 = com.google.android.gms.internal.ads.zzel.zze(r1, r10)
            int r11 = r11 * r10
            int r0 = r11 * 256
            goto Lc6
        Lc3:
            return r2
        Lc4:
            int r0 = r0 * r1
        Lc6:
            int r0 = r0 * 3
            int r8 = r8 + r8
            int r0 = r0 / r8
            return r0
        Lcb:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxd.zzT(com.google.android.gms.internal.ads.zzql, com.google.android.gms.internal.ads.zzaf):int");
    }

    protected static int zzU(zzql zzqlVar, zzaf zzafVar) {
        if (zzafVar.zzn != -1) {
            int size = zzafVar.zzo.size();
            int r1 = 0;
            for (int r0 = 0; r0 < size; r0++) {
                r1 += ((byte[]) zzafVar.zzo.get(r0)).length;
            }
            return zzafVar.zzn + r1;
        }
        return zzT(zzqlVar, zzafVar);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:395:0x05fa, code lost:
        if (r1.equals("A10-70F") != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:493:0x075c, code lost:
        if (r8 != 2) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007e A[FALL_THROUGH] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected static final boolean zzaB(java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 2798
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxd.zzaB(java.lang.String):boolean");
    }

    private static List zzaC(zzqq zzqqVar, zzaf zzafVar, boolean z, boolean z2) throws zzqx {
        String str = zzafVar.zzm;
        if (str == null) {
            return zzfuv.zzo();
        }
        List zzf = zzrd.zzf(str, z, z2);
        String zze = zzrd.zze(zzafVar);
        if (zze == null) {
            return zzfuv.zzm(zzf);
        }
        List zzf2 = zzrd.zzf(zze, z, z2);
        zzfus zzi = zzfuv.zzi();
        zzi.zzf(zzf);
        zzi.zzf(zzf2);
        return zzi.zzg();
    }

    private final void zzaD() {
        int r0 = this.zzC;
        if (r0 == -1) {
            if (this.zzD == -1) {
                return;
            }
            r0 = -1;
        }
        zzda zzdaVar = this.zzG;
        if (zzdaVar != null && zzdaVar.zzc == r0 && zzdaVar.zzd == this.zzD && zzdaVar.zze == this.zzE && zzdaVar.zzf == this.zzF) {
            return;
        }
        zzda zzdaVar2 = new zzda(r0, this.zzD, this.zzE, this.zzF);
        this.zzG = zzdaVar2;
        this.zzg.zzt(zzdaVar2);
    }

    private final void zzaE() {
        zzda zzdaVar = this.zzG;
        if (zzdaVar != null) {
            this.zzg.zzt(zzdaVar);
        }
    }

    private final void zzaF() {
        Surface surface = this.zzl;
        zzxg zzxgVar = this.zzm;
        if (surface == zzxgVar) {
            this.zzl = null;
        }
        zzxgVar.release();
        this.zzm = null;
    }

    private static boolean zzaG(long j) {
        return j < -30000;
    }

    private final boolean zzaH(zzql zzqlVar) {
        return zzel.zza >= 23 && !zzaB(zzqlVar.zza) && (!zzqlVar.zzf || zzxg.zzb(this.zze));
    }

    @Override // com.google.android.gms.internal.ads.zzqo, com.google.android.gms.internal.ads.zzgp, com.google.android.gms.internal.ads.zzjy
    public final void zzD(float f, float f2) throws zzgy {
        super.zzD(f, f2);
        this.zzf.zze(f);
    }

    @Override // com.google.android.gms.internal.ads.zzjy, com.google.android.gms.internal.ads.zzjz
    public final String zzK() {
        return "MediaCodecVideoRenderer";
    }

    @Override // com.google.android.gms.internal.ads.zzqo, com.google.android.gms.internal.ads.zzjy
    public final boolean zzN() {
        zzxg zzxgVar;
        if (!super.zzN() || (!this.zzp && (((zzxgVar = this.zzm) == null || this.zzl != zzxgVar) && zzaj() != null))) {
            if (this.zzt == C1856C.TIME_UNSET) {
                return false;
            }
            if (SystemClock.elapsedRealtime() < this.zzt) {
                return true;
            }
            this.zzt = C1856C.TIME_UNSET;
            return false;
        }
        this.zzt = C1856C.TIME_UNSET;
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final float zzP(float f, zzaf zzafVar, zzaf[] zzafVarArr) {
        float f2 = -1.0f;
        for (zzaf zzafVar2 : zzafVarArr) {
            float f3 = zzafVar2.zzt;
            if (f3 != -1.0f) {
                f2 = Math.max(f2, f3);
            }
        }
        if (f2 == -1.0f) {
            return -1.0f;
        }
        return f2 * f;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final int zzQ(zzqq zzqqVar, zzaf zzafVar) throws zzqx {
        boolean z;
        if (zzbt.zzh(zzafVar.zzm)) {
            int r3 = 0;
            boolean z2 = zzafVar.zzp != null;
            List zzaC = zzaC(zzqqVar, zzafVar, z2, false);
            if (z2 && zzaC.isEmpty()) {
                zzaC = zzaC(zzqqVar, zzafVar, false, false);
            }
            if (zzaC.isEmpty()) {
                return TsExtractor.TS_STREAM_TYPE_AC3;
            }
            if (zzav(zzafVar)) {
                zzql zzqlVar = (zzql) zzaC.get(0);
                boolean zzd2 = zzqlVar.zzd(zzafVar);
                if (!zzd2) {
                    for (int r7 = 1; r7 < zzaC.size(); r7++) {
                        zzql zzqlVar2 = (zzql) zzaC.get(r7);
                        if (zzqlVar2.zzd(zzafVar)) {
                            zzqlVar = zzqlVar2;
                            z = false;
                            zzd2 = true;
                            break;
                        }
                    }
                }
                z = true;
                int r72 = true != zzd2 ? 3 : 4;
                int r8 = true != zzqlVar.zze(zzafVar) ? 8 : 16;
                int r5 = true != zzqlVar.zzg ? 0 : 64;
                int r1 = true != z ? 0 : 128;
                if (zzd2) {
                    List zzaC2 = zzaC(zzqqVar, zzafVar, z2, true);
                    if (!zzaC2.isEmpty()) {
                        zzql zzqlVar3 = (zzql) zzrd.zzg(zzaC2, zzafVar).get(0);
                        if (zzqlVar3.zzd(zzafVar) && zzqlVar3.zze(zzafVar)) {
                            r3 = 32;
                        }
                    }
                }
                return r72 | r8 | r3 | r5 | r1;
            }
            return TsExtractor.TS_STREAM_TYPE_HDMV_DTS;
        }
        return 128;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final zzgr zzR(zzql zzqlVar, zzaf zzafVar, zzaf zzafVar2) {
        int r6;
        int r7;
        zzgr zzb2 = zzqlVar.zzb(zzafVar, zzafVar2);
        int r1 = zzb2.zze;
        int r2 = zzafVar2.zzr;
        zzxc zzxcVar = this.zzi;
        if (r2 > zzxcVar.zza || zzafVar2.zzs > zzxcVar.zzb) {
            r1 |= 256;
        }
        if (zzU(zzqlVar, zzafVar2) > this.zzi.zzc) {
            r1 |= 64;
        }
        String str = zzqlVar.zza;
        if (r1 != 0) {
            r7 = r1;
            r6 = 0;
        } else {
            r6 = zzb2.zzd;
            r7 = 0;
        }
        return new zzgr(str, zzafVar, zzafVar2, r6, r7);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzqo
    public final zzgr zzS(zzje zzjeVar) throws zzgy {
        zzgr zzS = super.zzS(zzjeVar);
        this.zzg.zzf(zzjeVar.zza, zzS);
        return zzS;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final zzqh zzV(zzql zzqlVar, zzaf zzafVar, MediaCrypto mediaCrypto, float f) {
        String str;
        zzxc zzxcVar;
        String str2;
        String str3;
        Point point;
        Pair zzb2;
        int zzT;
        zzxg zzxgVar = this.zzm;
        if (zzxgVar != null && zzxgVar.zza != zzqlVar.zzf) {
            zzaF();
        }
        String str4 = zzqlVar.zzc;
        zzaf[] zzJ = zzJ();
        int r6 = zzafVar.zzr;
        int r7 = zzafVar.zzs;
        int zzU = zzU(zzqlVar, zzafVar);
        int length = zzJ.length;
        if (length == 1) {
            if (zzU != -1 && (zzT = zzT(zzqlVar, zzafVar)) != -1) {
                zzU = Math.min((int) (zzU * 1.5f), zzT);
            }
            zzxcVar = new zzxc(r6, r7, zzU);
            str = str4;
        } else {
            boolean z = false;
            for (int r14 = 0; r14 < length; r14++) {
                zzaf zzafVar2 = zzJ[r14];
                if (zzafVar.zzy != null && zzafVar2.zzy == null) {
                    zzad zzb3 = zzafVar2.zzb();
                    zzb3.zzy(zzafVar.zzy);
                    zzafVar2 = zzb3.zzY();
                }
                if (zzqlVar.zzb(zzafVar, zzafVar2).zzd != 0) {
                    int r13 = zzafVar2.zzr;
                    z |= r13 == -1 || zzafVar2.zzs == -1;
                    r6 = Math.max(r6, r13);
                    r7 = Math.max(r7, zzafVar2.zzs);
                    zzU = Math.max(zzU, zzU(zzqlVar, zzafVar2));
                }
            }
            if (z) {
                StringBuilder sb = new StringBuilder();
                sb.append("Resolutions unknown. Codec max resolution: ");
                sb.append(r6);
                String str5 = "x";
                sb.append("x");
                sb.append(r7);
                String str6 = "MediaCodecVideoRenderer";
                Log.w("MediaCodecVideoRenderer", sb.toString());
                int r5 = zzafVar.zzs;
                int r11 = zzafVar.zzr;
                int r12 = r5 > r11 ? r5 : r11;
                int r132 = r5 <= r11 ? r5 : r11;
                float f2 = r132 / r12;
                int[] r15 = zzb;
                str = str4;
                int r3 = 0;
                while (r3 < 9) {
                    int r4 = r15[r3];
                    int[] r17 = r15;
                    int r152 = (int) (r4 * f2);
                    if (r4 <= r12 || r152 <= r132) {
                        break;
                    }
                    int r18 = r12;
                    int r19 = r132;
                    if (zzel.zza >= 21) {
                        int r122 = r5 <= r11 ? r4 : r152;
                        if (r5 <= r11) {
                            r4 = r152;
                        }
                        point = zzqlVar.zza(r122, r4);
                        str2 = str5;
                        str3 = str6;
                        if (zzqlVar.zzf(point.x, point.y, zzafVar.zzt)) {
                            break;
                        }
                        r3++;
                        r15 = r17;
                        r12 = r18;
                        r132 = r19;
                        str5 = str2;
                        str6 = str3;
                    } else {
                        str2 = str5;
                        str3 = str6;
                        try {
                            int zze = zzel.zze(r4, 16) * 16;
                            int zze2 = zzel.zze(r152, 16) * 16;
                            if (zze * zze2 <= zzrd.zza()) {
                                int r9 = r5 <= r11 ? zze : zze2;
                                if (r5 <= r11) {
                                    zze = zze2;
                                }
                                point = new Point(r9, zze);
                            } else {
                                r3++;
                                r15 = r17;
                                r12 = r18;
                                r132 = r19;
                                str5 = str2;
                                str6 = str3;
                            }
                        } catch (zzqx unused) {
                        }
                    }
                }
                str2 = str5;
                str3 = str6;
                point = null;
                if (point != null) {
                    r6 = Math.max(r6, point.x);
                    r7 = Math.max(r7, point.y);
                    zzad zzb4 = zzafVar.zzb();
                    zzb4.zzX(r6);
                    zzb4.zzF(r7);
                    zzU = Math.max(zzU, zzT(zzqlVar, zzb4.zzY()));
                    Log.w(str3, "Codec max resolution adjusted to: " + r6 + str2 + r7);
                }
            } else {
                str = str4;
            }
            zzxcVar = new zzxc(r6, r7, zzU);
        }
        this.zzi = zzxcVar;
        boolean z2 = this.zzh;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str);
        mediaFormat.setInteger("width", zzafVar.zzr);
        mediaFormat.setInteger("height", zzafVar.zzs);
        zzdw.zzb(mediaFormat, zzafVar.zzo);
        float f3 = zzafVar.zzt;
        if (f3 != -1.0f) {
            mediaFormat.setFloat("frame-rate", f3);
        }
        zzdw.zza(mediaFormat, "rotation-degrees", zzafVar.zzu);
        zzq zzqVar = zzafVar.zzy;
        if (zzqVar != null) {
            zzdw.zza(mediaFormat, "color-transfer", zzqVar.zzd);
            zzdw.zza(mediaFormat, "color-standard", zzqVar.zzb);
            zzdw.zza(mediaFormat, "color-range", zzqVar.zzc);
            byte[] bArr = zzqVar.zze;
            if (bArr != null) {
                mediaFormat.setByteBuffer("hdr-static-info", ByteBuffer.wrap(bArr));
            }
        }
        if (MimeTypes.VIDEO_DOLBY_VISION.equals(zzafVar.zzm) && (zzb2 = zzrd.zzb(zzafVar)) != null) {
            zzdw.zza(mediaFormat, Scopes.PROFILE, ((Integer) zzb2.first).intValue());
        }
        mediaFormat.setInteger("max-width", zzxcVar.zza);
        mediaFormat.setInteger("max-height", zzxcVar.zzb);
        zzdw.zza(mediaFormat, "max-input-size", zzxcVar.zzc);
        if (zzel.zza >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f != -1.0f) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (z2) {
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (this.zzl == null) {
            if (zzaH(zzqlVar)) {
                if (this.zzm == null) {
                    this.zzm = zzxg.zza(this.zze, zzqlVar.zzf);
                }
                this.zzl = this.zzm;
            } else {
                throw new IllegalStateException();
            }
        }
        return zzqh.zzb(zzqlVar, mediaFormat, zzafVar, this.zzl, null);
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final List zzW(zzqq zzqqVar, zzaf zzafVar, boolean z) throws zzqx {
        return zzrd.zzg(zzaC(zzqqVar, zzafVar, false, false), zzafVar);
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzX(Exception exc) {
        zzdu.zza("MediaCodecVideoRenderer", "Video codec error", exc);
        this.zzg.zzs(exc);
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzY(String str, zzqh zzqhVar, long j, long j2) {
        this.zzg.zza(str, j, j2);
        this.zzj = zzaB(str);
        zzql zzal = zzal();
        Objects.requireNonNull(zzal);
        boolean z = false;
        if (zzel.zza >= 29 && MimeTypes.VIDEO_VP9.equals(zzal.zzb)) {
            MediaCodecInfo.CodecProfileLevel[] zzg = zzal.zzg();
            int length = zzg.length;
            int r9 = 0;
            while (true) {
                if (r9 >= length) {
                    break;
                } else if (zzg[r9].profile == 16384) {
                    z = true;
                    break;
                } else {
                    r9++;
                }
            }
        }
        this.zzk = z;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzZ(String str) {
        this.zzg.zzb(str);
    }

    protected final void zzaA(long j) {
        zzgq zzgqVar = this.zza;
        zzgqVar.zzk += j;
        zzgqVar.zzl++;
        this.zzA += j;
        this.zzB++;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzaa(zzaf zzafVar, MediaFormat mediaFormat) {
        int integer;
        int integer2;
        zzqj zzaj = zzaj();
        if (zzaj != null) {
            zzaj.zzq(this.zzo);
        }
        Objects.requireNonNull(mediaFormat);
        boolean z = false;
        if (mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top")) {
            z = true;
        }
        if (z) {
            integer = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            integer = mediaFormat.getInteger("width");
        }
        this.zzC = integer;
        if (z) {
            integer2 = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            integer2 = mediaFormat.getInteger("height");
        }
        this.zzD = integer2;
        this.zzF = zzafVar.zzv;
        if (zzel.zza >= 21) {
            int r9 = zzafVar.zzu;
            if (r9 == 90 || r9 == 270) {
                int r92 = this.zzC;
                this.zzC = this.zzD;
                this.zzD = r92;
                this.zzF = 1.0f / this.zzF;
            }
        } else {
            this.zzE = zzafVar.zzu;
        }
        this.zzf.zzc(zzafVar.zzt);
    }

    final void zzab() {
        this.zzr = true;
        if (this.zzp) {
            return;
        }
        this.zzp = true;
        this.zzg.zzq(this.zzl);
        this.zzn = true;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzac() {
        this.zzp = false;
        int r0 = zzel.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzad(zzgg zzggVar) throws zzgy {
        this.zzx++;
        int r1 = zzel.zza;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final zzqk zzak(Throwable th, zzql zzqlVar) {
        return new zzxb(th, zzqlVar, this.zzl);
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final void zzam(zzgg zzggVar) throws zzgy {
        if (this.zzk) {
            ByteBuffer byteBuffer = zzggVar.zze;
            Objects.requireNonNull(byteBuffer);
            if (byteBuffer.remaining() >= 7) {
                byte b = byteBuffer.get();
                short s = byteBuffer.getShort();
                short s2 = byteBuffer.getShort();
                byte b2 = byteBuffer.get();
                byte b3 = byteBuffer.get();
                byteBuffer.position(0);
                if (b == -75 && s == 60 && s2 == 1 && b2 == 4 && b3 == 0) {
                    byte[] bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                    byteBuffer.position(0);
                    zzqj zzaj = zzaj();
                    Bundle bundle = new Bundle();
                    bundle.putByteArray("hdr10-plus-info", bArr);
                    zzaj.zzp(bundle);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzqo
    public final void zzao(long j) {
        super.zzao(j);
        this.zzx--;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzqo
    public final void zzaq() {
        super.zzaq();
        this.zzx = 0;
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final boolean zzau(zzql zzqlVar) {
        return this.zzl != null || zzaH(zzqlVar);
    }

    protected final void zzaw(zzqj zzqjVar, int r4, long j) {
        zzaD();
        int r5 = zzel.zza;
        Trace.beginSection("releaseOutputBuffer");
        zzqjVar.zzn(r4, true);
        Trace.endSection();
        this.zzz = SystemClock.elapsedRealtime() * 1000;
        this.zza.zze++;
        this.zzw = 0;
        zzab();
    }

    protected final void zzax(zzqj zzqjVar, int r2, long j, long j2) {
        zzaD();
        int r3 = zzel.zza;
        Trace.beginSection("releaseOutputBuffer");
        zzqjVar.zzm(r2, j2);
        Trace.endSection();
        this.zzz = SystemClock.elapsedRealtime() * 1000;
        this.zza.zze++;
        this.zzw = 0;
        zzab();
    }

    protected final void zzay(zzqj zzqjVar, int r2, long j) {
        int r3 = zzel.zza;
        Trace.beginSection("skipVideoBuffer");
        zzqjVar.zzn(r2, false);
        Trace.endSection();
        this.zza.zzf++;
    }

    protected final void zzaz(int r3, int r4) {
        zzgq zzgqVar = this.zza;
        zzgqVar.zzh += r3;
        int r32 = r3 + r4;
        zzgqVar.zzg += r32;
        this.zzv += r32;
        int r42 = this.zzw + r32;
        this.zzw = r42;
        zzgqVar.zzi = Math.max(r42, zzgqVar.zzi);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzqo, com.google.android.gms.internal.ads.zzgp
    public final void zzs() {
        this.zzG = null;
        this.zzp = false;
        int r1 = zzel.zza;
        this.zzn = false;
        try {
            super.zzs();
        } finally {
            this.zzg.zzc(this.zza);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzqo, com.google.android.gms.internal.ads.zzgp
    public final void zzt(boolean z, boolean z2) throws zzgy {
        super.zzt(z, z2);
        zzk();
        this.zzg.zze(this.zza);
        this.zzq = z2;
        this.zzr = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzqo, com.google.android.gms.internal.ads.zzgp
    public final void zzu(long j, boolean z) throws zzgy {
        super.zzu(j, z);
        this.zzp = false;
        int r4 = zzel.zza;
        this.zzf.zzf();
        this.zzy = C1856C.TIME_UNSET;
        this.zzs = C1856C.TIME_UNSET;
        this.zzw = 0;
        this.zzt = C1856C.TIME_UNSET;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.ads.zzqo, com.google.android.gms.internal.ads.zzgp
    public final void zzv() {
        try {
            super.zzv();
            if (this.zzm != null) {
                zzaF();
            }
        } catch (Throwable th) {
            if (this.zzm != null) {
                zzaF();
            }
            throw th;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgp
    protected final void zzw() {
        this.zzv = 0;
        this.zzu = SystemClock.elapsedRealtime();
        this.zzz = SystemClock.elapsedRealtime() * 1000;
        this.zzA = 0L;
        this.zzB = 0;
        this.zzf.zzg();
    }

    @Override // com.google.android.gms.internal.ads.zzgp
    protected final void zzx() {
        this.zzt = C1856C.TIME_UNSET;
        if (this.zzv > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.zzg.zzd(this.zzv, elapsedRealtime - this.zzu);
            this.zzv = 0;
            this.zzu = elapsedRealtime;
        }
        int r0 = this.zzB;
        if (r0 != 0) {
            this.zzg.zzr(this.zzA, r0);
            this.zzA = 0L;
            this.zzB = 0;
        }
        this.zzf.zzh();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v8, types: [android.view.Surface] */
    @Override // com.google.android.gms.internal.ads.zzgp, com.google.android.gms.internal.ads.zzju
    public final void zzp(int r6, Object obj) throws zzgy {
        if (r6 != 1) {
            if (r6 == 7) {
                this.zzI = (zzxh) obj;
                return;
            } else if (r6 == 10) {
                int intValue = ((Integer) obj).intValue();
                if (this.zzH != intValue) {
                    this.zzH = intValue;
                    return;
                }
                return;
            } else if (r6 != 4) {
                if (r6 != 5) {
                    return;
                }
                this.zzf.zzj(((Integer) obj).intValue());
                return;
            } else {
                int intValue2 = ((Integer) obj).intValue();
                this.zzo = intValue2;
                zzqj zzaj = zzaj();
                if (zzaj != null) {
                    zzaj.zzq(intValue2);
                    return;
                }
                return;
            }
        }
        zzxg zzxgVar = obj instanceof Surface ? (Surface) obj : null;
        if (zzxgVar == null) {
            zzxg zzxgVar2 = this.zzm;
            if (zzxgVar2 != null) {
                zzxgVar = zzxgVar2;
            } else {
                zzql zzal = zzal();
                if (zzal != null && zzaH(zzal)) {
                    zzxgVar = zzxg.zza(this.zze, zzal.zzf);
                    this.zzm = zzxgVar;
                }
            }
        }
        if (this.zzl != zzxgVar) {
            this.zzl = zzxgVar;
            this.zzf.zzi(zzxgVar);
            this.zzn = false;
            int zzbe = zzbe();
            zzqj zzaj2 = zzaj();
            if (zzaj2 != null) {
                if (zzel.zza < 23 || zzxgVar == null || this.zzj) {
                    zzap();
                    zzan();
                } else {
                    zzaj2.zzo(zzxgVar);
                }
            }
            if (zzxgVar == null || zzxgVar == this.zzm) {
                this.zzG = null;
                this.zzp = false;
                int r62 = zzel.zza;
                return;
            }
            zzaE();
            this.zzp = false;
            int r63 = zzel.zza;
            if (zzbe == 2) {
                this.zzt = C1856C.TIME_UNSET;
            }
        } else if (zzxgVar == null || zzxgVar == this.zzm) {
        } else {
            zzaE();
            if (this.zzn) {
                this.zzg.zzq(this.zzl);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzqo
    protected final boolean zzaf(long j, long j2, zzqj zzqjVar, ByteBuffer byteBuffer, int r30, int r31, int r32, long j3, boolean z, boolean z2, zzaf zzafVar) throws zzgy {
        boolean z3;
        int zzd2;
        Objects.requireNonNull(zzqjVar);
        if (this.zzs == C1856C.TIME_UNSET) {
            this.zzs = j;
        }
        if (j3 != this.zzy) {
            this.zzf.zzd(j3);
            this.zzy = j3;
        }
        long zzai = zzai();
        long j4 = j3 - zzai;
        if (!z || z2) {
            float zzah = zzah();
            int zzbe = zzbe();
            long elapsedRealtime = SystemClock.elapsedRealtime() * 1000;
            long j5 = (long) ((j3 - j) / zzah);
            if (zzbe == 2) {
                j5 -= elapsedRealtime - j2;
            }
            if (this.zzl == this.zzm) {
                if (zzaG(j5)) {
                    zzay(zzqjVar, r30, j4);
                    zzaA(j5);
                    return true;
                }
                return false;
            }
            long j6 = elapsedRealtime - this.zzz;
            boolean z4 = this.zzr ? !this.zzp : zzbe == 2 || this.zzq;
            if (this.zzt == C1856C.TIME_UNSET && j >= zzai && (z4 || (zzbe == 2 && zzaG(j5) && j6 > 100000))) {
                long nanoTime = System.nanoTime();
                if (zzel.zza >= 21) {
                    zzax(zzqjVar, r30, j4, nanoTime);
                } else {
                    zzaw(zzqjVar, r30, j4);
                }
                zzaA(j5);
                return true;
            } else if (zzbe != 2 || j == this.zzs) {
                return false;
            } else {
                long nanoTime2 = System.nanoTime();
                long zza = this.zzf.zza((j5 * 1000) + nanoTime2);
                long j7 = (zza - nanoTime2) / 1000;
                long j8 = this.zzt;
                if (j7 >= -500000 || z2 || (zzd2 = zzd(j)) == 0) {
                    if (!zzaG(j7) || z2) {
                        if (zzel.zza >= 21) {
                            if (j7 < 50000) {
                                zzax(zzqjVar, r30, j4, zza);
                                zzaA(j7);
                                return true;
                            }
                            return false;
                        } else if (j7 < 30000) {
                            if (j7 > 11000) {
                                try {
                                    Thread.sleep(((-10000) + j7) / 1000);
                                } catch (InterruptedException unused) {
                                    Thread.currentThread().interrupt();
                                    return false;
                                }
                            }
                            zzaw(zzqjVar, r30, j4);
                            zzaA(j7);
                            return true;
                        } else {
                            return false;
                        }
                    }
                    if (j8 == C1856C.TIME_UNSET) {
                        int r2 = zzel.zza;
                        Trace.beginSection("dropVideoBuffer");
                        zzqjVar.zzn(r30, false);
                        Trace.endSection();
                        z3 = true;
                        zzaz(0, 1);
                    } else {
                        zzay(zzqjVar, r30, j4);
                        z3 = true;
                    }
                    zzaA(j7);
                    return z3;
                }
                if (j8 != C1856C.TIME_UNSET) {
                    zzgq zzgqVar = this.zza;
                    zzgqVar.zzd += zzd2;
                    zzgqVar.zzf += this.zzx;
                } else {
                    this.zza.zzj++;
                    zzaz(zzd2, this.zzx);
                }
                zzas();
                return false;
            }
        }
        zzay(zzqjVar, r30, j4);
        return true;
    }
}
