package com.google.android.gms.internal.ads;

import android.util.Log;
import android.util.Pair;
import com.facebook.imagepipeline.common.RotationOptions;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.CharUtils;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaee {
    public byte[] zzM;
    public zzaan zzS;
    public boolean zzT;
    public zzaam zzV;
    public int zzW;
    private int zzX;
    public String zza;
    public String zzb;
    public int zzc;
    public int zzd;
    public int zze;
    public int zzf;
    public boolean zzg;
    public byte[] zzh;
    public zzaal zzi;
    public byte[] zzj;
    public zzx zzk;
    public int zzl = -1;
    public int zzm = -1;
    public int zzn = -1;
    public int zzo = -1;
    public int zzp = 0;
    public int zzq = -1;
    public float zzr = 0.0f;
    public float zzs = 0.0f;
    public float zzt = 0.0f;
    public byte[] zzu = null;
    public int zzv = -1;
    public boolean zzw = false;
    public int zzx = -1;
    public int zzy = -1;
    public int zzz = -1;
    public int zzA = 1000;
    public int zzB = 200;
    public float zzC = -1.0f;
    public float zzD = -1.0f;
    public float zzE = -1.0f;
    public float zzF = -1.0f;
    public float zzG = -1.0f;
    public float zzH = -1.0f;
    public float zzI = -1.0f;
    public float zzJ = -1.0f;
    public float zzK = -1.0f;
    public float zzL = -1.0f;
    public int zzN = 1;
    public int zzO = -1;
    public int zzP = 8000;
    public long zzQ = 0;
    public long zzR = 0;
    public boolean zzU = true;
    private String zzY = "eng";

    private static Pair zzf(zzed zzedVar) throws zzbu {
        try {
            zzedVar.zzG(16);
            long zzq = zzedVar.zzq();
            if (zzq == 1482049860) {
                return new Pair(MimeTypes.VIDEO_DIVX, null);
            }
            if (zzq == 859189832) {
                return new Pair(MimeTypes.VIDEO_H263, null);
            }
            if (zzq != 826496599) {
                Log.w("MatroskaExtractor", "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair(MimeTypes.VIDEO_UNKNOWN, null);
            }
            int zzc = zzedVar.zzc() + 20;
            byte[] zzH = zzedVar.zzH();
            while (true) {
                int length = zzH.length;
                if (zzc < length - 4) {
                    if (zzH[zzc] == 0 && zzH[zzc + 1] == 0 && zzH[zzc + 2] == 1 && zzH[zzc + 3] == 15) {
                        return new Pair(MimeTypes.VIDEO_VC1, Collections.singletonList(Arrays.copyOfRange(zzH, zzc, length)));
                    }
                    zzc++;
                } else {
                    throw zzbu.zza("Failed to find FourCC VC1 initialization data", null);
                }
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw zzbu.zza("Error parsing FourCC private data", null);
        }
    }

    private static List zzg(byte[] bArr) throws zzbu {
        int r7;
        int r9;
        try {
            if (bArr[0] == 2) {
                int r5 = 1;
                int r6 = 0;
                while (true) {
                    r7 = bArr[r5] & 255;
                    if (r7 != 255) {
                        break;
                    }
                    r6 += 255;
                    r5++;
                }
                int r52 = r5 + 1;
                int r62 = r6 + r7;
                int r72 = 0;
                while (true) {
                    r9 = bArr[r52] & 255;
                    if (r9 != 255) {
                        break;
                    }
                    r72 += 255;
                    r52++;
                }
                int r53 = r52 + 1;
                int r73 = r72 + r9;
                if (bArr[r53] != 1) {
                    throw zzbu.zza("Error parsing vorbis codec private", null);
                }
                byte[] bArr2 = new byte[r62];
                System.arraycopy(bArr, r53, bArr2, 0, r62);
                int r54 = r53 + r62;
                if (bArr[r54] != 3) {
                    throw zzbu.zza("Error parsing vorbis codec private", null);
                }
                int r55 = r54 + r73;
                if (bArr[r55] == 5) {
                    int length = bArr.length - r55;
                    byte[] bArr3 = new byte[length];
                    System.arraycopy(bArr, r55, bArr3, 0, length);
                    ArrayList arrayList = new ArrayList(2);
                    arrayList.add(bArr2);
                    arrayList.add(bArr3);
                    return arrayList;
                }
                throw zzbu.zza("Error parsing vorbis codec private", null);
            }
            throw zzbu.zza("Error parsing vorbis codec private", null);
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw zzbu.zza("Error parsing vorbis codec private", null);
        }
    }

    private static boolean zzh(zzed zzedVar) throws zzbu {
        UUID r0;
        UUID r8;
        try {
            int zzi = zzedVar.zzi();
            if (zzi == 1) {
                return true;
            }
            if (zzi == 65534) {
                zzedVar.zzF(24);
                long zzr = zzedVar.zzr();
                r0 = zzaef.zzf;
                if (zzr == r0.getMostSignificantBits()) {
                    long zzr2 = zzedVar.zzr();
                    r8 = zzaef.zzf;
                    if (zzr2 == r8.getLeastSignificantBits()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw zzbu.zza("Error parsing MS/ACM codec private", null);
        }
    }

    @EnsuresNonNull({"codecPrivate"})
    private final byte[] zzi(String str) throws zzbu {
        byte[] bArr = this.zzj;
        if (bArr != null) {
            return bArr;
        }
        throw zzbu.zza("Missing CodecPrivate for codec ".concat(String.valueOf(str)), null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @EnsuresNonNull({"this.output"})
    @RequiresNonNull({"codecId"})
    public final void zze(zzzi zzziVar, int r22) throws zzbu {
        char c;
        List singletonList;
        List list;
        String str;
        int r3;
        String str2;
        byte[] bArr;
        int r7;
        int r6;
        Map map;
        Map map2;
        int r11;
        Map map3;
        zzzb zza;
        String str3 = this.zzb;
        int r62 = 1;
        int r9 = 4;
        int r10 = 0;
        switch (str3.hashCode()) {
            case -2095576542:
                if (str3.equals("V_MPEG4/ISO/AP")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -2095575984:
                if (str3.equals("V_MPEG4/ISO/SP")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1985379776:
                if (str3.equals("A_MS/ACM")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -1784763192:
                if (str3.equals("A_TRUEHD")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -1730367663:
                if (str3.equals("A_VORBIS")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -1482641358:
                if (str3.equals("A_MPEG/L2")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -1482641357:
                if (str3.equals("A_MPEG/L3")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -1373388978:
                if (str3.equals("V_MS/VFW/FOURCC")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -933872740:
                if (str3.equals("S_DVBSUB")) {
                    c = ' ';
                    break;
                }
                c = 65535;
                break;
            case -538363189:
                if (str3.equals("V_MPEG4/ISO/ASP")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -538363109:
                if (str3.equals("V_MPEG4/ISO/AVC")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -425012669:
                if (str3.equals("S_VOBSUB")) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case -356037306:
                if (str3.equals("A_DTS/LOSSLESS")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 62923557:
                if (str3.equals("A_AAC")) {
                    c = CharUtils.f1567CR;
                    break;
                }
                c = 65535;
                break;
            case 62923603:
                if (str3.equals("A_AC3")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 62927045:
                if (str3.equals("A_DTS")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 82318131:
                if (str3.equals("V_AV1")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 82338133:
                if (str3.equals("V_VP8")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 82338134:
                if (str3.equals("V_VP9")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 99146302:
                if (str3.equals("S_HDMV/PGS")) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case 444813526:
                if (str3.equals("V_THEORA")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 542569478:
                if (str3.equals("A_DTS/EXPRESS")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 635596514:
                if (str3.equals("A_PCM/FLOAT/IEEE")) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case 725948237:
                if (str3.equals("A_PCM/INT/BIG")) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case 725957860:
                if (str3.equals("A_PCM/INT/LIT")) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 738597099:
                if (str3.equals("S_TEXT/ASS")) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case 855502857:
                if (str3.equals("V_MPEGH/ISO/HEVC")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1045209816:
                if (str3.equals("S_TEXT/WEBVTT")) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case 1422270023:
                if (str3.equals("S_TEXT/UTF8")) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case 1809237540:
                if (str3.equals("V_MPEG2")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1950749482:
                if (str3.equals("A_EAC3")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 1950789798:
                if (str3.equals("A_FLAC")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case 1951062397:
                if (str3.equals("A_OPUS")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        String str4 = MimeTypes.AUDIO_RAW;
        zzq zzqVar = null;
        r12 = null;
        r12 = null;
        r12 = null;
        r12 = null;
        r12 = null;
        r12 = null;
        r12 = null;
        r12 = null;
        r12 = null;
        byte[] bArr2 = null;
        switch (c) {
            case 0:
                str4 = MimeTypes.VIDEO_VP8;
                singletonList = null;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 1:
                str4 = MimeTypes.VIDEO_VP9;
                singletonList = null;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 2:
                str4 = MimeTypes.VIDEO_AV1;
                singletonList = null;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 3:
                str4 = MimeTypes.VIDEO_MPEG2;
                singletonList = null;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 4:
            case 5:
            case 6:
                byte[] bArr3 = this.zzj;
                singletonList = bArr3 == null ? null : Collections.singletonList(bArr3);
                str4 = MimeTypes.VIDEO_MP4V;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 7:
                zzyk zza2 = zzyk.zza(new zzed(zzi(str3)));
                list = zza2.zza;
                this.zzW = zza2.zzb;
                str = zza2.zzf;
                str4 = MimeTypes.VIDEO_H264;
                r3 = -1;
                r9 = -1;
                List list2 = list;
                str2 = str;
                singletonList = list2;
                break;
            case '\b':
                zzzv zza3 = zzzv.zza(new zzed(zzi(str3)));
                list = zza3.zza;
                this.zzW = zza3.zzb;
                str = zza3.zzd;
                str4 = MimeTypes.VIDEO_H265;
                r3 = -1;
                r9 = -1;
                List list22 = list;
                str2 = str;
                singletonList = list22;
                break;
            case '\t':
                Pair zzf = zzf(new zzed(zzi(str3)));
                str4 = (String) zzf.first;
                singletonList = (List) zzf.second;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case '\n':
                str4 = MimeTypes.VIDEO_UNKNOWN;
                singletonList = null;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 11:
                singletonList = zzg(zzi(str3));
                str4 = MimeTypes.AUDIO_VORBIS;
                str2 = null;
                r3 = 8192;
                r9 = -1;
                break;
            case '\f':
                singletonList = new ArrayList(3);
                singletonList.add(zzi(this.zzb));
                singletonList.add(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.zzQ).array());
                singletonList.add(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.zzR).array());
                str4 = MimeTypes.AUDIO_OPUS;
                str2 = null;
                r3 = 5760;
                r9 = -1;
                break;
            case '\r':
                singletonList = Collections.singletonList(zzi(str3));
                zzyc zza4 = zzyd.zza(this.zzj);
                this.zzP = zza4.zza;
                this.zzN = zza4.zzb;
                str2 = zza4.zzc;
                str4 = MimeTypes.AUDIO_AAC;
                r3 = -1;
                r9 = -1;
                break;
            case 14:
                str4 = MimeTypes.AUDIO_MPEG_L2;
                singletonList = null;
                str2 = null;
                r3 = 4096;
                r9 = -1;
                break;
            case 15:
                str4 = MimeTypes.AUDIO_MPEG;
                singletonList = null;
                str2 = null;
                r3 = 4096;
                r9 = -1;
                break;
            case 16:
                str4 = MimeTypes.AUDIO_AC3;
                singletonList = null;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 17:
                str4 = MimeTypes.AUDIO_E_AC3;
                singletonList = null;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 18:
                this.zzS = new zzaan();
                str4 = MimeTypes.AUDIO_TRUEHD;
                singletonList = null;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 19:
            case 20:
                str4 = MimeTypes.AUDIO_DTS;
                singletonList = null;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 21:
                str4 = MimeTypes.AUDIO_DTS_HD;
                singletonList = null;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 22:
                singletonList = Collections.singletonList(zzi(str3));
                str4 = MimeTypes.AUDIO_FLAC;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 23:
                if (zzh(new zzed(zzi(str3)))) {
                    r9 = zzel.zzn(this.zzO);
                    if (r9 == 0) {
                        Log.w("MatroskaExtractor", "Unsupported PCM bit depth: " + this.zzO + ". Setting mimeType to audio/x-unknown");
                    }
                    singletonList = null;
                    str2 = null;
                    r3 = -1;
                    break;
                } else {
                    Log.w("MatroskaExtractor", "Non-PCM MS/ACM is unsupported. Setting mimeType to audio/x-unknown");
                }
                singletonList = null;
                str2 = null;
                str4 = MimeTypes.AUDIO_UNKNOWN;
                r3 = -1;
                r9 = -1;
                break;
            case 24:
                r9 = zzel.zzn(this.zzO);
                if (r9 == 0) {
                    Log.w("MatroskaExtractor", "Unsupported little endian PCM bit depth: " + this.zzO + ". Setting mimeType to audio/x-unknown");
                    singletonList = null;
                    str2 = null;
                    str4 = MimeTypes.AUDIO_UNKNOWN;
                    r3 = -1;
                    r9 = -1;
                    break;
                }
                singletonList = null;
                str2 = null;
                r3 = -1;
                break;
            case 25:
                int r1 = this.zzO;
                if (r1 != 8) {
                    if (r1 != 16) {
                        Log.w("MatroskaExtractor", "Unsupported big endian PCM bit depth: " + r1 + ". Setting mimeType to audio/x-unknown");
                        singletonList = null;
                        str2 = null;
                        str4 = MimeTypes.AUDIO_UNKNOWN;
                        r3 = -1;
                        r9 = -1;
                        break;
                    } else {
                        r9 = 268435456;
                        singletonList = null;
                        str2 = null;
                        r3 = -1;
                        break;
                    }
                } else {
                    singletonList = null;
                    str2 = null;
                    r3 = -1;
                    r9 = 3;
                    break;
                }
            case 26:
                int r12 = this.zzO;
                if (r12 != 32) {
                    Log.w("MatroskaExtractor", "Unsupported floating point PCM bit depth: " + r12 + ". Setting mimeType to audio/x-unknown");
                    singletonList = null;
                    str2 = null;
                    str4 = MimeTypes.AUDIO_UNKNOWN;
                    r3 = -1;
                    r9 = -1;
                    break;
                }
                singletonList = null;
                str2 = null;
                r3 = -1;
                break;
            case 27:
                str4 = MimeTypes.APPLICATION_SUBRIP;
                singletonList = null;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 28:
                bArr = zzaef.zzc;
                singletonList = zzfuv.zzq(bArr, zzi(this.zzb));
                str2 = null;
                str4 = MimeTypes.TEXT_SSA;
                r3 = -1;
                r9 = -1;
                break;
            case 29:
                singletonList = null;
                str2 = null;
                str4 = MimeTypes.TEXT_VTT;
                r3 = -1;
                r9 = -1;
                break;
            case 30:
                singletonList = zzfuv.zzp(zzi(str3));
                str4 = MimeTypes.APPLICATION_VOBSUB;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case 31:
                str4 = MimeTypes.APPLICATION_PGS;
                singletonList = null;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            case ' ':
                byte[] bArr4 = new byte[4];
                System.arraycopy(zzi(str3), 0, bArr4, 0, 4);
                singletonList = zzfuv.zzp(bArr4);
                str4 = MimeTypes.APPLICATION_DVBSUBS;
                str2 = null;
                r3 = -1;
                r9 = -1;
                break;
            default:
                throw zzbu.zza("Unrecognized codec identifier.", null);
        }
        byte[] bArr5 = this.zzM;
        if (bArr5 != null && (zza = zzzb.zza(new zzed(bArr5))) != null) {
            str2 = zza.zza;
            str4 = MimeTypes.VIDEO_DOLBY_VISION;
        }
        String str5 = str4;
        int r5 = (this.zzU ? 1 : 0) | (true != this.zzT ? 0 : 2);
        zzad zzadVar = new zzad();
        if (zzbt.zzg(str5)) {
            zzadVar.zzw(this.zzN);
            zzadVar.zzT(this.zzP);
            zzadVar.zzN(r9);
        } else if (zzbt.zzh(str5)) {
            if (this.zzp == 0) {
                int r63 = this.zzn;
                r7 = -1;
                if (r63 == -1) {
                    r63 = this.zzl;
                }
                this.zzn = r63;
                int r64 = this.zzo;
                if (r64 == -1) {
                    r64 = this.zzm;
                }
                this.zzo = r64;
            } else {
                r7 = -1;
            }
            float f = (this.zzn == r7 || (r11 = this.zzo) == r7) ? -1.0f : (this.zzm * r6) / (this.zzl * r11);
            if (this.zzw) {
                if (this.zzC != -1.0f && this.zzD != -1.0f && this.zzE != -1.0f && this.zzF != -1.0f && this.zzG != -1.0f && this.zzH != -1.0f && this.zzI != -1.0f && this.zzJ != -1.0f && this.zzK != -1.0f && this.zzL != -1.0f) {
                    bArr2 = new byte[25];
                    ByteBuffer order = ByteBuffer.wrap(bArr2).order(ByteOrder.LITTLE_ENDIAN);
                    order.put((byte) 0);
                    order.putShort((short) ((this.zzC * 50000.0f) + 0.5f));
                    order.putShort((short) ((this.zzD * 50000.0f) + 0.5f));
                    order.putShort((short) ((this.zzE * 50000.0f) + 0.5f));
                    order.putShort((short) ((this.zzF * 50000.0f) + 0.5f));
                    order.putShort((short) ((this.zzG * 50000.0f) + 0.5f));
                    order.putShort((short) ((this.zzH * 50000.0f) + 0.5f));
                    order.putShort((short) ((this.zzI * 50000.0f) + 0.5f));
                    order.putShort((short) ((this.zzJ * 50000.0f) + 0.5f));
                    order.putShort((short) (this.zzK + 0.5f));
                    order.putShort((short) (this.zzL + 0.5f));
                    order.putShort((short) this.zzA);
                    order.putShort((short) this.zzB);
                }
                zzqVar = new zzq(this.zzx, this.zzz, this.zzy, bArr2);
            }
            if (this.zza != null) {
                map = zzaef.zzg;
                if (map.containsKey(this.zza)) {
                    map2 = zzaef.zzg;
                    r7 = ((Integer) map2.get(this.zza)).intValue();
                }
            }
            if (this.zzq == 0 && Float.compare(this.zzr, 0.0f) == 0 && Float.compare(this.zzs, 0.0f) == 0) {
                if (Float.compare(this.zzt, 0.0f) != 0) {
                    if (Float.compare(this.zzs, 90.0f) == 0) {
                        r10 = 90;
                    } else if (Float.compare(this.zzs, -180.0f) == 0 || Float.compare(this.zzs, 180.0f) == 0) {
                        r10 = RotationOptions.ROTATE_180;
                    } else if (Float.compare(this.zzs, -90.0f) == 0) {
                        r10 = 270;
                    }
                }
                zzadVar.zzX(this.zzl);
                zzadVar.zzF(this.zzm);
                zzadVar.zzP(f);
                zzadVar.zzR(r10);
                zzadVar.zzQ(this.zzu);
                zzadVar.zzV(this.zzv);
                zzadVar.zzy(zzqVar);
                r62 = 2;
            }
            r10 = r7;
            zzadVar.zzX(this.zzl);
            zzadVar.zzF(this.zzm);
            zzadVar.zzP(f);
            zzadVar.zzR(r10);
            zzadVar.zzQ(this.zzu);
            zzadVar.zzV(this.zzv);
            zzadVar.zzy(zzqVar);
            r62 = 2;
        } else if (!MimeTypes.APPLICATION_SUBRIP.equals(str5) && !MimeTypes.TEXT_SSA.equals(str5) && !MimeTypes.TEXT_VTT.equals(str5) && !MimeTypes.APPLICATION_VOBSUB.equals(str5) && !MimeTypes.APPLICATION_PGS.equals(str5) && !MimeTypes.APPLICATION_DVBSUBS.equals(str5)) {
            throw zzbu.zza("Unexpected MIME type.", null);
        } else {
            r62 = 3;
        }
        if (this.zza != null) {
            map3 = zzaef.zzg;
            if (!map3.containsKey(this.zza)) {
                zzadVar.zzJ(this.zza);
            }
        }
        zzadVar.zzG(r22);
        zzadVar.zzS(str5);
        zzadVar.zzL(r3);
        zzadVar.zzK(this.zzY);
        zzadVar.zzU(r5);
        zzadVar.zzI(singletonList);
        zzadVar.zzx(str2);
        zzadVar.zzB(this.zzk);
        zzaf zzY = zzadVar.zzY();
        zzaam zzv = zzziVar.zzv(this.zzc, r62);
        this.zzV = zzv;
        zzv.zzk(zzY);
    }
}
