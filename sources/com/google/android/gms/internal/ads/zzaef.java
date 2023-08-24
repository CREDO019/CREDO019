package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.util.SparseArray;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import com.google.firebase.FirebaseError;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.fileupload.MultipartStream;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaef implements zzzf {
    public static final zzzm zza = new zzzm() { // from class: com.google.android.gms.internal.ads.zzaeb
        @Override // com.google.android.gms.internal.ads.zzzm
        public final zzzf[] zza() {
            zzzm zzzmVar = zzaef.zza;
            return new zzzf[]{new zzaef(0)};
        }

        @Override // com.google.android.gms.internal.ads.zzzm
        public final /* synthetic */ zzzf[] zzb(Uri uri, Map map) {
            return zzzl.zza(this, uri, map);
        }
    };
    private static final byte[] zzb = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, MultipartStream.DASH, MultipartStream.DASH, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final byte[] zzc = zzel.zzaa("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] zzd = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final byte[] zze = {87, 69, 66, 86, 84, 84, 10, 10, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 32, MultipartStream.DASH, MultipartStream.DASH, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 10};
    private static final UUID zzf = new UUID(72057594037932032L, -9223371306706625679L);
    private static final Map zzg;
    private long zzA;
    private zzaee zzB;
    private boolean zzC;
    private int zzD;
    private long zzE;
    private boolean zzF;
    private long zzG;
    private long zzH;
    private long zzI;
    private zzdv zzJ;
    private zzdv zzK;
    private boolean zzL;
    private boolean zzM;
    private int zzN;
    private long zzO;
    private long zzP;
    private int zzQ;
    private int zzR;
    private int[] zzS;
    private int zzT;
    private int zzU;
    private int zzV;
    private int zzW;
    private boolean zzX;
    private long zzY;
    private int zzZ;
    private int zzaa;
    private int zzab;
    private boolean zzac;
    private boolean zzad;
    private boolean zzae;
    private int zzaf;
    private byte zzag;
    private boolean zzah;
    private zzzi zzai;
    private final zzaea zzh;
    private final zzaeh zzi;
    private final SparseArray zzj;
    private final boolean zzk;
    private final zzed zzl;
    private final zzed zzm;
    private final zzed zzn;
    private final zzed zzo;
    private final zzed zzp;
    private final zzed zzq;
    private final zzed zzr;
    private final zzed zzs;
    private final zzed zzt;
    private final zzed zzu;
    private ByteBuffer zzv;
    private long zzw;
    private long zzx;
    private long zzy;
    private long zzz;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("htc_video_rotA-000", 0);
        hashMap.put("htc_video_rotA-090", 90);
        hashMap.put("htc_video_rotA-180", Integer.valueOf((int) RotationOptions.ROTATE_180));
        hashMap.put("htc_video_rotA-270", 270);
        zzg = Collections.unmodifiableMap(hashMap);
    }

    public zzaef() {
        this(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final int zzn(int r0) {
        switch (r0) {
            case 131:
            case 136:
            case 155:
            case 159:
            case 176:
            case 179:
            case 186:
            case JfifUtil.MARKER_RST7 /* 215 */:
            case 231:
            case 238:
            case 241:
            case 251:
            case 16871:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21930:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 21998:
            case 22186:
            case 22203:
            case 25188:
            case 30114:
            case 30321:
            case 2352003:
            case 2807729:
                return 2;
            case TsExtractor.TS_STREAM_TYPE_SPLICE_INFO /* 134 */:
            case FirebaseError.ERROR_WEAK_PASSWORD /* 17026 */:
            case 21358:
            case 2274716:
                return 3;
            case 160:
            case 166:
            case 174:
            case 183:
            case 187:
            case 224:
            case JfifUtil.MARKER_APP1 /* 225 */:
            case 16868:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30113:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case 161:
            case 163:
            case 165:
            case 16877:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
            case 30323:
            case 30324:
            case 30325:
                return 5;
            default:
                return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final boolean zzo(int r1) {
        return r1 == 357149030 || r1 == 524531317 || r1 == 475249515 || r1 == 374648427;
    }

    @RequiresNonNull({"#2.output"})
    private final int zzp(zzzg zzzgVar, zzaee zzaeeVar, int r13, boolean z) throws IOException {
        int r8;
        if ("S_TEXT/UTF8".equals(zzaeeVar.zzb)) {
            zzx(zzzgVar, zzb, r13);
            int r11 = this.zzaa;
            zzw();
            return r11;
        } else if ("S_TEXT/ASS".equals(zzaeeVar.zzb)) {
            zzx(zzzgVar, zzd, r13);
            int r112 = this.zzaa;
            zzw();
            return r112;
        } else if ("S_TEXT/WEBVTT".equals(zzaeeVar.zzb)) {
            zzx(zzzgVar, zze, r13);
            int r113 = this.zzaa;
            zzw();
            return r113;
        } else {
            zzaam zzaamVar = zzaeeVar.zzV;
            if (!this.zzac) {
                if (zzaeeVar.zzg) {
                    this.zzV &= -1073741825;
                    if (!this.zzad) {
                        ((zzyv) zzzgVar).zzn(this.zzn.zzH(), 0, 1, false);
                        this.zzZ++;
                        if ((this.zzn.zzH()[0] & 128) == 128) {
                            throw zzbu.zza("Extension bit is set in signal byte", null);
                        }
                        this.zzag = this.zzn.zzH()[0];
                        this.zzad = true;
                    }
                    byte b = this.zzag;
                    if ((b & 1) == 1) {
                        int r1 = b & 2;
                        this.zzV |= 1073741824;
                        if (!this.zzah) {
                            ((zzyv) zzzgVar).zzn(this.zzs.zzH(), 0, 8, false);
                            this.zzZ += 8;
                            this.zzah = true;
                            this.zzn.zzH()[0] = (byte) ((r1 != 2 ? 0 : 128) | 8);
                            this.zzn.zzF(0);
                            zzaamVar.zzr(this.zzn, 1, 1);
                            this.zzaa++;
                            this.zzs.zzF(0);
                            zzaamVar.zzr(this.zzs, 8, 1);
                            this.zzaa += 8;
                        }
                        if (r1 == 2) {
                            if (!this.zzae) {
                                ((zzyv) zzzgVar).zzn(this.zzn.zzH(), 0, 1, false);
                                this.zzZ++;
                                this.zzn.zzF(0);
                                this.zzaf = this.zzn.zzk();
                                this.zzae = true;
                            }
                            int r12 = this.zzaf * 4;
                            this.zzn.zzC(r12);
                            ((zzyv) zzzgVar).zzn(this.zzn.zzH(), 0, r12, false);
                            this.zzZ += r12;
                            int r14 = (this.zzaf >> 1) + 1;
                            int r6 = (r14 * 6) + 2;
                            ByteBuffer byteBuffer = this.zzv;
                            if (byteBuffer == null || byteBuffer.capacity() < r6) {
                                this.zzv = ByteBuffer.allocate(r6);
                            }
                            this.zzv.position(0);
                            this.zzv.putShort((short) r14);
                            int r15 = 0;
                            int r7 = 0;
                            while (true) {
                                r8 = this.zzaf;
                                if (r15 >= r8) {
                                    break;
                                }
                                int zzn = this.zzn.zzn();
                                if (r15 % 2 == 0) {
                                    this.zzv.putShort((short) (zzn - r7));
                                } else {
                                    this.zzv.putInt(zzn - r7);
                                }
                                r15++;
                                r7 = zzn;
                            }
                            int r16 = (r13 - this.zzZ) - r7;
                            if ((r8 & 1) == 1) {
                                this.zzv.putInt(r16);
                            } else {
                                this.zzv.putShort((short) r16);
                                this.zzv.putInt(0);
                            }
                            this.zzt.zzD(this.zzv.array(), r6);
                            zzaamVar.zzr(this.zzt, r6, 1);
                            this.zzaa += r6;
                        }
                    }
                } else {
                    byte[] bArr = zzaeeVar.zzh;
                    if (bArr != null) {
                        this.zzq.zzD(bArr, bArr.length);
                    }
                }
                if (!"A_OPUS".equals(zzaeeVar.zzb) ? zzaeeVar.zzf > 0 : z) {
                    this.zzV |= 268435456;
                    this.zzu.zzC(0);
                    int zzd2 = (this.zzq.zzd() + r13) - this.zzZ;
                    this.zzn.zzC(4);
                    this.zzn.zzH()[0] = (byte) ((zzd2 >> 24) & 255);
                    this.zzn.zzH()[1] = (byte) ((zzd2 >> 16) & 255);
                    this.zzn.zzH()[2] = (byte) ((zzd2 >> 8) & 255);
                    this.zzn.zzH()[3] = (byte) (zzd2 & 255);
                    zzaamVar.zzr(this.zzn, 4, 2);
                    this.zzaa += 4;
                }
                this.zzac = true;
            }
            int zzd3 = r13 + this.zzq.zzd();
            if ("V_MPEG4/ISO/AVC".equals(zzaeeVar.zzb) || "V_MPEGH/ISO/HEVC".equals(zzaeeVar.zzb)) {
                byte[] zzH = this.zzm.zzH();
                zzH[0] = 0;
                zzH[1] = 0;
                zzH[2] = 0;
                int r17 = zzaeeVar.zzW;
                int r2 = 4 - r17;
                while (this.zzZ < zzd3) {
                    int r4 = this.zzab;
                    if (r4 == 0) {
                        int min = Math.min(r17, this.zzq.zza());
                        ((zzyv) zzzgVar).zzn(zzH, r2 + min, r17 - min, false);
                        if (min > 0) {
                            this.zzq.zzB(zzH, r2, min);
                        }
                        this.zzZ += r17;
                        this.zzm.zzF(0);
                        this.zzab = this.zzm.zzn();
                        this.zzl.zzF(0);
                        zzaak.zzb(zzaamVar, this.zzl, 4);
                        this.zzaa += 4;
                    } else {
                        int zzq = zzq(zzzgVar, zzaamVar, r4);
                        this.zzZ += zzq;
                        this.zzaa += zzq;
                        this.zzab -= zzq;
                    }
                }
            } else {
                if (zzaeeVar.zzS != null) {
                    zzdd.zzf(this.zzq.zzd() == 0);
                    zzaeeVar.zzS.zzd(zzzgVar);
                }
                while (true) {
                    int r142 = this.zzZ;
                    if (r142 >= zzd3) {
                        break;
                    }
                    int zzq2 = zzq(zzzgVar, zzaamVar, zzd3 - r142);
                    this.zzZ += zzq2;
                    this.zzaa += zzq2;
                }
            }
            if ("A_VORBIS".equals(zzaeeVar.zzb)) {
                this.zzo.zzF(0);
                zzaak.zzb(zzaamVar, this.zzo, 4);
                this.zzaa += 4;
            }
            int r114 = this.zzaa;
            zzw();
            return r114;
        }
    }

    private final int zzq(zzzg zzzgVar, zzaam zzaamVar, int r4) throws IOException {
        int zza2 = this.zzq.zza();
        if (zza2 <= 0) {
            return zzaak.zza(zzaamVar, zzzgVar, r4, false);
        }
        int min = Math.min(r4, zza2);
        zzaak.zzb(zzaamVar, this.zzq, min);
        return min;
    }

    private final long zzr(long j) throws zzbu {
        long j2 = this.zzy;
        if (j2 == C1856C.TIME_UNSET) {
            throw zzbu.zza("Can't scale timecode prior to timecodeScale being set.", null);
        }
        return zzel.zzw(j, j2, 1000L);
    }

    @EnsuresNonNull({"cueTimesUs", "cueClusterPositions"})
    private final void zzs(int r3) throws zzbu {
        if (this.zzJ == null || this.zzK == null) {
            throw zzbu.zza("Element " + r3 + " must be in a Cues", null);
        }
    }

    @EnsuresNonNull({"currentTrack"})
    private final void zzt(int r3) throws zzbu {
        if (this.zzB != null) {
            return;
        }
        throw zzbu.zza("Element " + r3 + " must be in a TrackEntry", null);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00e0 A[EDGE_INSN: B:62:0x00e0->B:51:0x00e0 ?: BREAK  , SYNTHETIC] */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"#1.output"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzu(com.google.android.gms.internal.ads.zzaee r18, long r19, int r21, int r22, int r23) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaef.zzu(com.google.android.gms.internal.ads.zzaee, long, int, int, int):void");
    }

    private final void zzv(zzzg zzzgVar, int r6) throws IOException {
        if (this.zzn.zzd() >= r6) {
            return;
        }
        if (this.zzn.zzb() < r6) {
            zzed zzedVar = this.zzn;
            int zzb2 = zzedVar.zzb();
            zzedVar.zzz(Math.max(zzb2 + zzb2, r6));
        }
        ((zzyv) zzzgVar).zzn(this.zzn.zzH(), this.zzn.zzd(), r6 - this.zzn.zzd(), false);
        this.zzn.zzE(r6);
    }

    private final void zzw() {
        this.zzZ = 0;
        this.zzaa = 0;
        this.zzab = 0;
        this.zzac = false;
        this.zzad = false;
        this.zzae = false;
        this.zzaf = 0;
        this.zzag = (byte) 0;
        this.zzah = false;
        this.zzq.zzC(0);
    }

    private final void zzx(zzzg zzzgVar, byte[] bArr, int r8) throws IOException {
        int length = bArr.length;
        int r1 = length + r8;
        if (this.zzr.zzb() < r1) {
            zzed zzedVar = this.zzr;
            byte[] copyOf = Arrays.copyOf(bArr, r1 + r8);
            zzedVar.zzD(copyOf, copyOf.length);
        } else {
            System.arraycopy(bArr, 0, this.zzr.zzH(), 0, length);
        }
        ((zzyv) zzzgVar).zzn(this.zzr.zzH(), length, r8, false);
        this.zzr.zzF(0);
        this.zzr.zzE(r1);
    }

    private static byte[] zzy(long j, String str, long j2) {
        zzdd.zzd(j != C1856C.TIME_UNSET);
        int r5 = (int) (j / 3600000000L);
        long j3 = j - (r5 * 3600000000L);
        int r4 = (int) (j3 / 60000000);
        long j4 = j3 - (r4 * 60000000);
        int r7 = (int) (j4 / 1000000);
        return zzel.zzaa(String.format(Locale.US, str, Integer.valueOf(r5), Integer.valueOf(r4), Integer.valueOf(r7), Integer.valueOf((int) ((j4 - (r7 * 1000000)) / j2))));
    }

    private static int[] zzz(int[] r1, int r2) {
        if (r1 == null) {
            return new int[r2];
        }
        int length = r1.length;
        return length >= r2 ? r1 : new int[Math.max(length + length, r2)];
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final int zza(zzzg zzzgVar, zzaaf zzaafVar) throws IOException {
        this.zzM = false;
        while (!this.zzM) {
            if (!this.zzh.zzc(zzzgVar)) {
                for (int r0 = 0; r0 < this.zzj.size(); r0++) {
                    zzaee zzaeeVar = (zzaee) this.zzj.valueAt(r0);
                    zzaee.zzd(zzaeeVar);
                    zzaan zzaanVar = zzaeeVar.zzS;
                    if (zzaanVar != null) {
                        zzaanVar.zza(zzaeeVar.zzV, zzaeeVar.zzi);
                    }
                }
                return -1;
            }
            long zzf2 = zzzgVar.zzf();
            if (this.zzF) {
                this.zzH = zzf2;
                zzaafVar.zza = this.zzG;
                this.zzF = false;
                return 1;
            } else if (this.zzC) {
                long j = this.zzH;
                if (j != -1) {
                    zzaafVar.zza = j;
                    this.zzH = -1L;
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzb(zzzi zzziVar) {
        this.zzai = zzziVar;
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final void zzc(long j, long j2) {
        this.zzI = C1856C.TIME_UNSET;
        this.zzN = 0;
        this.zzh.zzb();
        this.zzi.zze();
        zzw();
        for (int r1 = 0; r1 < this.zzj.size(); r1++) {
            zzaan zzaanVar = ((zzaee) this.zzj.valueAt(r1)).zzS;
            if (zzaanVar != null) {
                zzaanVar.zzb();
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzzf
    public final boolean zzd(zzzg zzzgVar) throws IOException {
        return new zzaeg().zza(zzzgVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x01e8, code lost:
        if (r5.equals("V_MPEGH/ISO/HEVC") != false) goto L95;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzh(int r22) throws com.google.android.gms.internal.ads.zzbu {
        /*
            Method dump skipped, instructions count: 1150
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaef.zzh(int):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzk(int r7, long j, long j2) throws zzbu {
        zzdd.zzb(this.zzai);
        if (r7 == 160) {
            this.zzX = false;
            this.zzY = 0L;
        } else if (r7 == 174) {
            this.zzB = new zzaee();
        } else if (r7 == 187) {
            this.zzL = false;
        } else if (r7 == 19899) {
            this.zzD = -1;
            this.zzE = -1L;
        } else if (r7 == 20533) {
            zzt(r7);
            this.zzB.zzg = true;
        } else if (r7 == 21968) {
            zzt(r7);
            this.zzB.zzw = true;
        } else if (r7 == 408125543) {
            long j3 = this.zzx;
            if (j3 != -1 && j3 != j) {
                throw zzbu.zza("Multiple Segment elements not supported", null);
            }
            this.zzx = j;
            this.zzw = j2;
        } else if (r7 == 475249515) {
            this.zzJ = new zzdv(32);
            this.zzK = new zzdv(32);
        } else if (r7 == 524531317 && !this.zzC) {
            if (this.zzk && this.zzG != -1) {
                this.zzF = true;
                return;
            }
            this.zzai.zzL(new zzaah(this.zzA, 0L));
            this.zzC = true;
        }
    }

    public zzaef(int r5) {
        zzady zzadyVar = new zzady();
        this.zzx = -1L;
        this.zzy = C1856C.TIME_UNSET;
        this.zzz = C1856C.TIME_UNSET;
        this.zzA = C1856C.TIME_UNSET;
        this.zzG = -1L;
        this.zzH = -1L;
        this.zzI = C1856C.TIME_UNSET;
        this.zzh = zzadyVar;
        zzadyVar.zza(new zzaed(this, null));
        this.zzk = true;
        this.zzi = new zzaeh();
        this.zzj = new SparseArray();
        this.zzn = new zzed(4);
        this.zzo = new zzed(ByteBuffer.allocate(4).putInt(-1).array());
        this.zzp = new zzed(4);
        this.zzl = new zzed(zzaac.zza);
        this.zzm = new zzed(4);
        this.zzq = new zzed();
        this.zzr = new zzed();
        this.zzs = new zzed(8);
        this.zzt = new zzed();
        this.zzu = new zzed();
        this.zzS = new int[1];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzl(int r2, String str) throws zzbu {
        if (r2 == 134) {
            zzt(r2);
            this.zzB.zzb = str;
        } else if (r2 == 17026) {
            if ("webm".equals(str) || "matroska".equals(str)) {
                return;
            }
            throw zzbu.zza("DocType " + str + " not supported", null);
        } else if (r2 == 21358) {
            zzt(r2);
            this.zzB.zza = str;
        } else if (r2 != 2274716) {
        } else {
            zzt(r2);
            zzaee.zzc(this.zzB, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0287, code lost:
        throw com.google.android.gms.internal.ads.zzbu.zza("EBML lacing sample size out of range.", null);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzg(int r19, int r20, com.google.android.gms.internal.ads.zzzg r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 819
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaef.zzg(int, int, com.google.android.gms.internal.ads.zzzg):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzj(int r9, long j) throws zzbu {
        if (r9 == 20529) {
            if (j == 0) {
                return;
            }
            throw zzbu.zza("ContentEncodingOrder " + j + " not supported", null);
        } else if (r9 == 20530) {
            if (j == 1) {
                return;
            }
            throw zzbu.zza("ContentEncodingScope " + j + " not supported", null);
        } else {
            switch (r9) {
                case 131:
                    zzt(r9);
                    this.zzB.zzd = (int) j;
                    return;
                case 136:
                    zzt(r9);
                    this.zzB.zzU = j == 1;
                    return;
                case 155:
                    this.zzP = zzr(j);
                    return;
                case 159:
                    zzt(r9);
                    this.zzB.zzN = (int) j;
                    return;
                case 176:
                    zzt(r9);
                    this.zzB.zzl = (int) j;
                    return;
                case 179:
                    zzs(r9);
                    this.zzJ.zzc(zzr(j));
                    return;
                case 186:
                    zzt(r9);
                    this.zzB.zzm = (int) j;
                    return;
                case JfifUtil.MARKER_RST7 /* 215 */:
                    zzt(r9);
                    this.zzB.zzc = (int) j;
                    return;
                case 231:
                    this.zzI = zzr(j);
                    return;
                case 238:
                    this.zzW = (int) j;
                    return;
                case 241:
                    if (this.zzL) {
                        return;
                    }
                    zzs(r9);
                    this.zzK.zzc(j);
                    this.zzL = true;
                    return;
                case 251:
                    this.zzX = true;
                    return;
                case 16871:
                    zzt(r9);
                    zzaee.zzb(this.zzB, (int) j);
                    return;
                case 16980:
                    if (j == 3) {
                        return;
                    }
                    throw zzbu.zza("ContentCompAlgo " + j + " not supported", null);
                case 17029:
                    if (j < 1 || j > 2) {
                        throw zzbu.zza("DocTypeReadVersion " + j + " not supported", null);
                    }
                    return;
                case 17143:
                    if (j == 1) {
                        return;
                    }
                    throw zzbu.zza("EBMLReadVersion " + j + " not supported", null);
                case 18401:
                    if (j == 5) {
                        return;
                    }
                    throw zzbu.zza("ContentEncAlgo " + j + " not supported", null);
                case 18408:
                    if (j == 1) {
                        return;
                    }
                    throw zzbu.zza("AESSettingsCipherMode " + j + " not supported", null);
                case 21420:
                    this.zzE = j + this.zzx;
                    return;
                case 21432:
                    zzt(r9);
                    int r92 = (int) j;
                    if (r92 == 0) {
                        this.zzB.zzv = 0;
                        return;
                    } else if (r92 == 1) {
                        this.zzB.zzv = 2;
                        return;
                    } else if (r92 == 3) {
                        this.zzB.zzv = 1;
                        return;
                    } else if (r92 != 15) {
                        return;
                    } else {
                        this.zzB.zzv = 3;
                        return;
                    }
                case 21680:
                    zzt(r9);
                    this.zzB.zzn = (int) j;
                    return;
                case 21682:
                    zzt(r9);
                    this.zzB.zzp = (int) j;
                    return;
                case 21690:
                    zzt(r9);
                    this.zzB.zzo = (int) j;
                    return;
                case 21930:
                    zzt(r9);
                    this.zzB.zzT = j == 1;
                    return;
                case 21998:
                    zzt(r9);
                    this.zzB.zzf = (int) j;
                    return;
                case 22186:
                    zzt(r9);
                    this.zzB.zzQ = j;
                    return;
                case 22203:
                    zzt(r9);
                    this.zzB.zzR = j;
                    return;
                case 25188:
                    zzt(r9);
                    this.zzB.zzO = (int) j;
                    return;
                case 30114:
                    this.zzY = j;
                    return;
                case 30321:
                    zzt(r9);
                    int r93 = (int) j;
                    if (r93 == 0) {
                        this.zzB.zzq = 0;
                        return;
                    } else if (r93 == 1) {
                        this.zzB.zzq = 1;
                        return;
                    } else if (r93 == 2) {
                        this.zzB.zzq = 2;
                        return;
                    } else if (r93 != 3) {
                        return;
                    } else {
                        this.zzB.zzq = 3;
                        return;
                    }
                case 2352003:
                    zzt(r9);
                    this.zzB.zze = (int) j;
                    return;
                case 2807729:
                    this.zzy = j;
                    return;
                default:
                    switch (r9) {
                        case 21945:
                            zzt(r9);
                            int r94 = (int) j;
                            if (r94 == 1) {
                                this.zzB.zzz = 2;
                                return;
                            } else if (r94 != 2) {
                                return;
                            } else {
                                this.zzB.zzz = 1;
                                return;
                            }
                        case 21946:
                            zzt(r9);
                            int zzb2 = zzq.zzb((int) j);
                            if (zzb2 != -1) {
                                this.zzB.zzy = zzb2;
                                return;
                            }
                            return;
                        case 21947:
                            zzt(r9);
                            zzaee zzaeeVar = this.zzB;
                            zzaeeVar.zzw = true;
                            int zza2 = zzq.zza((int) j);
                            if (zza2 != -1) {
                                zzaeeVar.zzx = zza2;
                                return;
                            }
                            return;
                        case 21948:
                            zzt(r9);
                            this.zzB.zzA = (int) j;
                            return;
                        case 21949:
                            zzt(r9);
                            this.zzB.zzB = (int) j;
                            return;
                        default:
                            return;
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzi(int r2, double d) throws zzbu {
        if (r2 == 181) {
            zzt(r2);
            this.zzB.zzP = (int) d;
        } else if (r2 != 17545) {
            switch (r2) {
                case 21969:
                    zzt(r2);
                    this.zzB.zzC = (float) d;
                    return;
                case 21970:
                    zzt(r2);
                    this.zzB.zzD = (float) d;
                    return;
                case 21971:
                    zzt(r2);
                    this.zzB.zzE = (float) d;
                    return;
                case 21972:
                    zzt(r2);
                    this.zzB.zzF = (float) d;
                    return;
                case 21973:
                    zzt(r2);
                    this.zzB.zzG = (float) d;
                    return;
                case 21974:
                    zzt(r2);
                    this.zzB.zzH = (float) d;
                    return;
                case 21975:
                    zzt(r2);
                    this.zzB.zzI = (float) d;
                    return;
                case 21976:
                    zzt(r2);
                    this.zzB.zzJ = (float) d;
                    return;
                case 21977:
                    zzt(r2);
                    this.zzB.zzK = (float) d;
                    return;
                case 21978:
                    zzt(r2);
                    this.zzB.zzL = (float) d;
                    return;
                default:
                    switch (r2) {
                        case 30323:
                            zzt(r2);
                            this.zzB.zzr = (float) d;
                            return;
                        case 30324:
                            zzt(r2);
                            this.zzB.zzs = (float) d;
                            return;
                        case 30325:
                            zzt(r2);
                            this.zzB.zzt = (float) d;
                            return;
                        default:
                            return;
                    }
            }
        } else {
            this.zzz = (long) d;
        }
    }
}
