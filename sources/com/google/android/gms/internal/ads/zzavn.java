package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer2.C1856C;
import com.google.android.exoplayer2.extractor.p011ts.TsExtractor;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.firebase.FirebaseError;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;
import org.apache.commons.fileupload.MultipartStream;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzavn implements zzauv {
    public static final zzaux zza = new zzavi();
    private static final byte[] zzb = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, MultipartStream.DASH, MultipartStream.DASH, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final byte[] zzc = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final UUID zzd = new UUID(72057594037932032L, -9223371306706625679L);
    private boolean zzA;
    private long zzB;
    private long zzC;
    private long zzD;
    private zzbab zzE;
    private zzbab zzF;
    private boolean zzG;
    private int zzH;
    private long zzI;
    private long zzJ;
    private int zzK;
    private int zzL;
    private int[] zzM;
    private int zzN;
    private int zzO;
    private int zzP;
    private int zzQ;
    private boolean zzR;
    private boolean zzS;
    private boolean zzT;
    private boolean zzU;
    private byte zzV;
    private int zzW;
    private int zzX;
    private int zzY;
    private boolean zzZ;
    private boolean zzaa;
    private zzauw zzab;
    private final zzavh zzac;
    private final zzavp zze;
    private final SparseArray zzf;
    private final boolean zzg;
    private final zzbag zzh;
    private final zzbag zzi;
    private final zzbag zzj;
    private final zzbag zzk;
    private final zzbag zzl;
    private final zzbag zzm;
    private final zzbag zzn;
    private final zzbag zzo;
    private final zzbag zzp;
    private ByteBuffer zzq;
    private long zzr;
    private long zzs;
    private long zzt;
    private long zzu;
    private long zzv;
    private zzavm zzw;
    private boolean zzx;
    private int zzy;
    private long zzz;

    public zzavn() {
        this(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int zzl(int r0) {
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
            case 241:
            case 251:
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
            case 22186:
            case 22203:
            case 25188:
            case 2352003:
            case 2807729:
                return 2;
            case TsExtractor.TS_STREAM_TYPE_SPLICE_INFO /* 134 */:
            case FirebaseError.ERROR_WEAK_PASSWORD /* 17026 */:
            case 2274716:
                return 3;
            case 160:
            case 174:
            case 183:
            case 187:
            case 224:
            case JfifUtil.MARKER_APP1 /* 225 */:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
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
                return 5;
            default:
                return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final boolean zzm(int r1) {
        return r1 == 357149030 || r1 == 524531317 || r1 == 475249515 || r1 == 374648427;
    }

    private final long zzn(long j) throws zzasv {
        long j2 = this.zzt;
        if (j2 == C1856C.TIME_UNSET) {
            throw new zzasv("Can't scale timecode prior to timecodeScale being set.");
        }
        return zzban.zzj(j, j2, 1000L);
    }

    private final void zzo(zzavm zzavmVar, long j) {
        int r4;
        byte[] zzq;
        if ("S_TEXT/UTF8".equals(zzavmVar.zza)) {
            byte[] bArr = this.zzn.zza;
            long j2 = this.zzJ;
            if (j2 == C1856C.TIME_UNSET) {
                zzq = zzc;
            } else {
                int r8 = (int) (j2 / 3600000000L);
                long j3 = j2 - (r8 * 3600000000L);
                int r5 = (int) (j3 / 60000000);
                long j4 = j3 - (60000000 * r5);
                zzq = zzban.zzq(String.format(Locale.US, "%02d:%02d:%02d,%03d", Integer.valueOf(r8), Integer.valueOf(r5), Integer.valueOf((int) (j4 / 1000000)), Integer.valueOf((int) ((j4 - (r4 * 1000000)) / 1000))));
            }
            System.arraycopy(zzq, 0, bArr, 19, 12);
            zzave zzaveVar = zzavmVar.zzN;
            zzbag zzbagVar = this.zzn;
            zzaveVar.zzb(zzbagVar, zzbagVar.zzd());
            this.zzY += this.zzn.zzd();
        }
        zzavmVar.zzN.zzc(j, this.zzP, this.zzY, 0, zzavmVar.zzg);
        this.zzZ = true;
        zzp();
    }

    private final void zzp() {
        this.zzQ = 0;
        this.zzY = 0;
        this.zzX = 0;
        this.zzR = false;
        this.zzS = false;
        this.zzU = false;
        this.zzW = 0;
        this.zzV = (byte) 0;
        this.zzT = false;
        this.zzm.zzr();
    }

    private static int[] zzq(int[] r1, int r2) {
        if (r1 == null) {
            return new int[r2];
        }
        int length = r1.length;
        return length >= r2 ? r1 : new int[Math.max(length + length, r2)];
    }

    private final int zzr(zzauu zzauuVar, zzave zzaveVar, int r4) throws IOException, InterruptedException {
        int zzd2;
        int zza2 = this.zzm.zza();
        if (zza2 > 0) {
            zzd2 = Math.min(r4, zza2);
            zzaveVar.zzb(this.zzm, zzd2);
        } else {
            zzd2 = zzaveVar.zzd(zzauuVar, r4, false);
        }
        this.zzQ += zzd2;
        this.zzY += zzd2;
        return zzd2;
    }

    private final void zzs(zzauu zzauuVar, int r6) throws IOException, InterruptedException {
        if (this.zzj.zzd() >= r6) {
            return;
        }
        if (this.zzj.zzb() < r6) {
            zzbag zzbagVar = this.zzj;
            byte[] bArr = zzbagVar.zza;
            int length = bArr.length;
            zzbagVar.zzt(Arrays.copyOf(bArr, Math.max(length + length, r6)), this.zzj.zzd());
        }
        zzbag zzbagVar2 = this.zzj;
        zzauuVar.zzh(zzbagVar2.zza, zzbagVar2.zzd(), r6 - this.zzj.zzd(), false);
        this.zzj.zzu(r6);
    }

    private final void zzt(zzauu zzauuVar, zzavm zzavmVar, int r13) throws IOException, InterruptedException {
        int r8;
        if ("S_TEXT/UTF8".equals(zzavmVar.zza)) {
            int r12 = r13 + 32;
            if (this.zzn.zzb() < r12) {
                this.zzn.zza = Arrays.copyOf(zzb, r12 + r13);
            }
            zzauuVar.zzh(this.zzn.zza, 32, r13, false);
            this.zzn.zzv(0);
            this.zzn.zzu(r12);
            return;
        }
        zzave zzaveVar = zzavmVar.zzN;
        if (!this.zzR) {
            if (zzavmVar.zze) {
                this.zzP &= -1073741825;
                if (!this.zzS) {
                    zzauuVar.zzh(this.zzj.zza, 0, 1, false);
                    this.zzQ++;
                    byte b = this.zzj.zza[0];
                    if ((b & 128) != 128) {
                        this.zzV = b;
                        this.zzS = true;
                    } else {
                        throw new zzasv("Extension bit is set in signal byte");
                    }
                }
                byte b2 = this.zzV;
                if ((b2 & 1) == 1) {
                    int r2 = b2 & 2;
                    this.zzP |= 1073741824;
                    if (!this.zzT) {
                        zzauuVar.zzh(this.zzo.zza, 0, 8, false);
                        this.zzQ += 8;
                        this.zzT = true;
                        zzbag zzbagVar = this.zzj;
                        zzbagVar.zza[0] = (byte) ((r2 != 2 ? 0 : 128) | 8);
                        zzbagVar.zzv(0);
                        zzaveVar.zzb(this.zzj, 1);
                        this.zzY++;
                        this.zzo.zzv(0);
                        zzaveVar.zzb(this.zzo, 8);
                        this.zzY += 8;
                    }
                    if (r2 == 2) {
                        if (!this.zzU) {
                            zzauuVar.zzh(this.zzj.zza, 0, 1, false);
                            this.zzQ++;
                            this.zzj.zzv(0);
                            this.zzW = this.zzj.zzg();
                            this.zzU = true;
                        }
                        int r22 = this.zzW * 4;
                        this.zzj.zzs(r22);
                        zzauuVar.zzh(this.zzj.zza, 0, r22, false);
                        this.zzQ += r22;
                        int r23 = (this.zzW >> 1) + 1;
                        int r6 = (r23 * 6) + 2;
                        ByteBuffer byteBuffer = this.zzq;
                        if (byteBuffer == null || byteBuffer.capacity() < r6) {
                            this.zzq = ByteBuffer.allocate(r6);
                        }
                        this.zzq.position(0);
                        this.zzq.putShort((short) r23);
                        int r24 = 0;
                        int r7 = 0;
                        while (true) {
                            r8 = this.zzW;
                            if (r24 >= r8) {
                                break;
                            }
                            int zzi = this.zzj.zzi();
                            if (r24 % 2 == 0) {
                                this.zzq.putShort((short) (zzi - r7));
                            } else {
                                this.zzq.putInt(zzi - r7);
                            }
                            r24++;
                            r7 = zzi;
                        }
                        int r25 = (r13 - this.zzQ) - r7;
                        if ((r8 & 1) == 1) {
                            this.zzq.putInt(r25);
                        } else {
                            this.zzq.putShort((short) r25);
                            this.zzq.putInt(0);
                        }
                        this.zzp.zzt(this.zzq.array(), r6);
                        zzaveVar.zzb(this.zzp, r6);
                        this.zzY += r6;
                    }
                }
            } else {
                byte[] bArr = zzavmVar.zzf;
                if (bArr != null) {
                    this.zzm.zzt(bArr, bArr.length);
                }
            }
            this.zzR = true;
        }
        int zzd2 = r13 + this.zzm.zzd();
        if (!"V_MPEG4/ISO/AVC".equals(zzavmVar.zza) && !"V_MPEGH/ISO/HEVC".equals(zzavmVar.zza)) {
            while (true) {
                int r26 = this.zzQ;
                if (r26 >= zzd2) {
                    break;
                }
                zzr(zzauuVar, zzaveVar, zzd2 - r26);
            }
        } else {
            byte[] bArr2 = this.zzi.zza;
            bArr2[0] = 0;
            bArr2[1] = 0;
            bArr2[2] = 0;
            int r4 = zzavmVar.zzO;
            int r5 = 4 - r4;
            while (this.zzQ < zzd2) {
                int r62 = this.zzX;
                if (r62 == 0) {
                    int min = Math.min(r4, this.zzm.zza());
                    zzauuVar.zzh(bArr2, r5 + min, r4 - min, false);
                    if (min > 0) {
                        this.zzm.zzq(bArr2, r5, min);
                    }
                    this.zzQ += r4;
                    this.zzi.zzv(0);
                    this.zzX = this.zzi.zzi();
                    this.zzh.zzv(0);
                    zzaveVar.zzb(this.zzh, 4);
                    this.zzY += 4;
                } else {
                    this.zzX = r62 - zzr(zzauuVar, zzaveVar, r62);
                }
            }
        }
        if ("A_VORBIS".equals(zzavmVar.zza)) {
            this.zzk.zzv(0);
            zzaveVar.zzb(this.zzk, 4);
            this.zzY += 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc(int r2, double d) {
        if (r2 == 181) {
            this.zzw.zzI = (int) d;
        } else if (r2 == 17545) {
            this.zzu = (long) d;
        } else {
            switch (r2) {
                case 21969:
                    this.zzw.zzw = (float) d;
                    return;
                case 21970:
                    this.zzw.zzx = (float) d;
                    return;
                case 21971:
                    this.zzw.zzy = (float) d;
                    return;
                case 21972:
                    this.zzw.zzz = (float) d;
                    return;
                case 21973:
                    this.zzw.zzA = (float) d;
                    return;
                case 21974:
                    this.zzw.zzB = (float) d;
                    return;
                case 21975:
                    this.zzw.zzC = (float) d;
                    return;
                case 21976:
                    this.zzw.zzD = (float) d;
                    return;
                case 21977:
                    this.zzw.zzE = (float) d;
                    return;
                case 21978:
                    this.zzw.zzF = (float) d;
                    return;
                default:
                    return;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzauv
    public final void zzd(zzauw zzauwVar) {
        this.zzab = zzauwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzauv
    public final void zze(long j, long j2) {
        this.zzD = C1856C.TIME_UNSET;
        this.zzH = 0;
        this.zzac.zza();
        this.zze.zzd();
        zzp();
    }

    @Override // com.google.android.gms.internal.ads.zzauv
    public final int zzf(zzauu zzauuVar, zzava zzavaVar) throws IOException, InterruptedException {
        this.zzZ = false;
        while (!this.zzZ) {
            if (!this.zzac.zzc(zzauuVar)) {
                return -1;
            }
            long zzd2 = zzauuVar.zzd();
            if (this.zzA) {
                this.zzC = zzd2;
                zzavaVar.zza = this.zzB;
                this.zzA = false;
                return 1;
            } else if (this.zzx) {
                long j = this.zzC;
                if (j != -1) {
                    zzavaVar.zza = j;
                    this.zzC = -1L;
                    return 1;
                }
            }
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzauv
    public final boolean zzg(zzauu zzauuVar) throws IOException, InterruptedException {
        return new zzavo().zza(zzauuVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzi(int r6, long j, long j2) throws zzasv {
        if (r6 == 160) {
            this.zzaa = false;
        } else if (r6 == 174) {
            this.zzw = new zzavm(null);
        } else if (r6 == 187) {
            this.zzG = false;
        } else if (r6 == 19899) {
            this.zzy = -1;
            this.zzz = -1L;
        } else if (r6 == 20533) {
            this.zzw.zze = true;
        } else if (r6 == 21968) {
            this.zzw.zzq = true;
        } else if (r6 == 408125543) {
            long j3 = this.zzs;
            if (j3 != -1 && j3 != j) {
                throw new zzasv("Multiple Segment elements not supported");
            }
            this.zzs = j;
            this.zzr = j2;
        } else if (r6 == 475249515) {
            this.zzE = new zzbab(32);
            this.zzF = new zzbab(32);
        } else if (r6 == 524531317 && !this.zzx) {
            if (this.zzg && this.zzB != -1) {
                this.zzA = true;
                return;
            }
            this.zzab.zzc(new zzavb(this.zzv));
            this.zzx = true;
        }
    }

    public zzavn(int r5) {
        zzavh zzavhVar = new zzavh();
        this.zzs = -1L;
        this.zzt = C1856C.TIME_UNSET;
        this.zzu = C1856C.TIME_UNSET;
        this.zzv = C1856C.TIME_UNSET;
        this.zzB = -1L;
        this.zzC = -1L;
        this.zzD = C1856C.TIME_UNSET;
        this.zzac = zzavhVar;
        zzavhVar.zzb(new zzavk(this, null));
        this.zzg = true;
        this.zze = new zzavp();
        this.zzf = new SparseArray();
        this.zzj = new zzbag(4);
        this.zzk = new zzbag(ByteBuffer.allocate(4).putInt(-1).array());
        this.zzl = new zzbag(4);
        this.zzh = new zzbag(zzbae.zza);
        this.zzi = new zzbag(4);
        this.zzm = new zzbag();
        this.zzn = new zzbag();
        this.zzo = new zzbag(8);
        this.zzp = new zzbag();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzj(int r3, String str) throws zzasv {
        if (r3 == 134) {
            this.zzw.zza = str;
        } else if (r3 != 17026) {
            if (r3 != 2274716) {
                return;
            }
            zzavm.zza(this.zzw, str);
        } else if ("webm".equals(str) || "matroska".equals(str)) {
        } else {
            throw new zzasv("DocType " + str + " not supported");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzh(int r8, long j) throws zzasv {
        if (r8 == 20529) {
            if (j == 0) {
                return;
            }
            throw new zzasv("ContentEncodingOrder " + j + " not supported");
        } else if (r8 == 20530) {
            if (j == 1) {
                return;
            }
            throw new zzasv("ContentEncodingScope " + j + " not supported");
        } else {
            switch (r8) {
                case 131:
                    this.zzw.zzc = (int) j;
                    return;
                case 136:
                    this.zzw.zzL = j == 1;
                    return;
                case 155:
                    this.zzJ = zzn(j);
                    return;
                case 159:
                    this.zzw.zzG = (int) j;
                    return;
                case 176:
                    this.zzw.zzj = (int) j;
                    return;
                case 179:
                    this.zzE.zzc(zzn(j));
                    return;
                case 186:
                    this.zzw.zzk = (int) j;
                    return;
                case JfifUtil.MARKER_RST7 /* 215 */:
                    this.zzw.zzb = (int) j;
                    return;
                case 231:
                    this.zzD = zzn(j);
                    return;
                case 241:
                    if (this.zzG) {
                        return;
                    }
                    this.zzF.zzc(j);
                    this.zzG = true;
                    return;
                case 251:
                    this.zzaa = true;
                    return;
                case 16980:
                    if (j == 3) {
                        return;
                    }
                    throw new zzasv("ContentCompAlgo " + j + " not supported");
                case 17029:
                    if (j < 1 || j > 2) {
                        throw new zzasv("DocTypeReadVersion " + j + " not supported");
                    }
                    return;
                case 17143:
                    if (j == 1) {
                        return;
                    }
                    throw new zzasv("EBMLReadVersion " + j + " not supported");
                case 18401:
                    if (j == 5) {
                        return;
                    }
                    throw new zzasv("ContentEncAlgo " + j + " not supported");
                case 18408:
                    if (j == 1) {
                        return;
                    }
                    throw new zzasv("AESSettingsCipherMode " + j + " not supported");
                case 21420:
                    this.zzz = j + this.zzs;
                    return;
                case 21432:
                    int r82 = (int) j;
                    if (r82 == 0) {
                        this.zzw.zzp = 0;
                        return;
                    } else if (r82 == 1) {
                        this.zzw.zzp = 2;
                        return;
                    } else if (r82 == 3) {
                        this.zzw.zzp = 1;
                        return;
                    } else if (r82 != 15) {
                        return;
                    } else {
                        this.zzw.zzp = 3;
                        return;
                    }
                case 21680:
                    this.zzw.zzl = (int) j;
                    return;
                case 21682:
                    this.zzw.zzn = (int) j;
                    return;
                case 21690:
                    this.zzw.zzm = (int) j;
                    return;
                case 21930:
                    this.zzw.zzM = j == 1;
                    return;
                case 22186:
                    this.zzw.zzJ = j;
                    return;
                case 22203:
                    this.zzw.zzK = j;
                    return;
                case 25188:
                    this.zzw.zzH = (int) j;
                    return;
                case 2352003:
                    this.zzw.zzd = (int) j;
                    return;
                case 2807729:
                    this.zzt = j;
                    return;
                default:
                    switch (r8) {
                        case 21945:
                            int r83 = (int) j;
                            if (r83 == 1) {
                                this.zzw.zzt = 2;
                                return;
                            } else if (r83 != 2) {
                                return;
                            } else {
                                this.zzw.zzt = 1;
                                return;
                            }
                        case 21946:
                            int r84 = (int) j;
                            if (r84 != 1) {
                                if (r84 == 16) {
                                    this.zzw.zzs = 6;
                                    return;
                                } else if (r84 == 18) {
                                    this.zzw.zzs = 7;
                                    return;
                                } else if (r84 != 6 && r84 != 7) {
                                    return;
                                }
                            }
                            this.zzw.zzs = 3;
                            return;
                        case 21947:
                            zzavm zzavmVar = this.zzw;
                            zzavmVar.zzq = true;
                            int r10 = (int) j;
                            if (r10 == 1) {
                                zzavmVar.zzr = 1;
                                return;
                            } else if (r10 == 9) {
                                zzavmVar.zzr = 6;
                                return;
                            } else if (r10 == 4 || r10 == 5 || r10 == 6 || r10 == 7) {
                                zzavmVar.zzr = 2;
                                return;
                            } else {
                                return;
                            }
                        case 21948:
                            this.zzw.zzu = (int) j;
                            return;
                        case 21949:
                            this.zzw.zzv = (int) j;
                            return;
                        default:
                            return;
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01f1, code lost:
        throw new com.google.android.gms.internal.ads.zzasv("EBML lacing sample size out of range.");
     */
    /* JADX WARN: Removed duplicated region for block: B:96:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0242  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzk(int r19, int r20, com.google.android.gms.internal.ads.zzauu r21) throws java.io.IOException, java.lang.InterruptedException {
        /*
            Method dump skipped, instructions count: 654
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzavn.zzk(int, int, com.google.android.gms.internal.ads.zzauu):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(int r15) throws zzasv {
        zzavc zzavbVar;
        zzbab zzbabVar;
        zzbab zzbabVar2;
        int r9;
        int r2 = 0;
        if (r15 == 160) {
            if (this.zzH != 2) {
                return;
            }
            if (!this.zzaa) {
                this.zzP |= 1;
            }
            zzo((zzavm) this.zzf.get(this.zzN), this.zzI);
            this.zzH = 0;
        } else if (r15 == 174) {
            String str = this.zzw.zza;
            if ("V_VP8".equals(str) || "V_VP9".equals(str) || "V_MPEG2".equals(str) || "V_MPEG4/ISO/SP".equals(str) || "V_MPEG4/ISO/ASP".equals(str) || "V_MPEG4/ISO/AP".equals(str) || "V_MPEG4/ISO/AVC".equals(str) || "V_MPEGH/ISO/HEVC".equals(str) || "V_MS/VFW/FOURCC".equals(str) || "V_THEORA".equals(str) || "A_OPUS".equals(str) || "A_VORBIS".equals(str) || "A_AAC".equals(str) || "A_MPEG/L2".equals(str) || "A_MPEG/L3".equals(str) || "A_AC3".equals(str) || "A_EAC3".equals(str) || "A_TRUEHD".equals(str) || "A_DTS".equals(str) || "A_DTS/EXPRESS".equals(str) || "A_DTS/LOSSLESS".equals(str) || "A_FLAC".equals(str) || "A_MS/ACM".equals(str) || "A_PCM/INT/LIT".equals(str) || "S_TEXT/UTF8".equals(str) || "S_VOBSUB".equals(str) || "S_HDMV/PGS".equals(str) || "S_DVBSUB".equals(str)) {
                zzavm zzavmVar = this.zzw;
                zzavmVar.zzb(this.zzab, zzavmVar.zzb);
                SparseArray sparseArray = this.zzf;
                zzavm zzavmVar2 = this.zzw;
                sparseArray.put(zzavmVar2.zzb, zzavmVar2);
            }
            this.zzw = null;
        } else if (r15 == 19899) {
            int r152 = this.zzy;
            if (r152 != -1) {
                long j = this.zzz;
                if (j != -1) {
                    if (r152 == 475249515) {
                        this.zzB = j;
                        return;
                    }
                    return;
                }
            }
            throw new zzasv("Mandatory element SeekID or SeekPosition not found");
        } else if (r15 == 25152) {
            zzavm zzavmVar3 = this.zzw;
            if (zzavmVar3.zze) {
                if (zzavmVar3.zzg == null) {
                    throw new zzasv("Encrypted Track found but ContentEncKeyID was not found");
                }
                zzavmVar3.zzi = new zzaur(new zzauq(zzasd.zzb, MimeTypes.VIDEO_WEBM, this.zzw.zzg.zzb, false));
            }
        } else if (r15 == 28032) {
            zzavm zzavmVar4 = this.zzw;
            if (zzavmVar4.zze && zzavmVar4.zzf != null) {
                throw new zzasv("Combining encryption and compression is not supported");
            }
        } else if (r15 == 357149030) {
            if (this.zzt == C1856C.TIME_UNSET) {
                this.zzt = 1000000L;
            }
            long j2 = this.zzu;
            if (j2 != C1856C.TIME_UNSET) {
                this.zzv = zzn(j2);
            }
        } else if (r15 == 374648427) {
            if (this.zzf.size() == 0) {
                throw new zzasv("No valid tracks were found");
            }
            this.zzab.zzb();
        } else if (r15 == 475249515 && !this.zzx) {
            zzauw zzauwVar = this.zzab;
            if (this.zzs == -1 || this.zzv == C1856C.TIME_UNSET || (zzbabVar = this.zzE) == null || zzbabVar.zza() == 0 || (zzbabVar2 = this.zzF) == null || zzbabVar2.zza() != zzbabVar.zza()) {
                this.zzE = null;
                this.zzF = null;
                zzavbVar = new zzavb(this.zzv);
            } else {
                int zza2 = zzbabVar.zza();
                int[] r4 = new int[zza2];
                long[] jArr = new long[zza2];
                long[] jArr2 = new long[zza2];
                long[] jArr3 = new long[zza2];
                for (int r92 = 0; r92 < zza2; r92++) {
                    jArr3[r92] = this.zzE.zzb(r92);
                    jArr[r92] = this.zzs + this.zzF.zzb(r92);
                }
                while (true) {
                    r9 = zza2 - 1;
                    if (r2 >= r9) {
                        break;
                    }
                    int r93 = r2 + 1;
                    r4[r2] = (int) (jArr[r93] - jArr[r2]);
                    jArr2[r2] = jArr3[r93] - jArr3[r2];
                    r2 = r93;
                }
                r4[r9] = (int) ((this.zzs + this.zzr) - jArr[r9]);
                jArr2[r9] = this.zzv - jArr3[r9];
                this.zzE = null;
                this.zzF = null;
                zzavbVar = new zzaut(r4, jArr, jArr2, jArr3);
            }
            zzauwVar.zzc(zzavbVar);
            this.zzx = true;
        }
    }
}
