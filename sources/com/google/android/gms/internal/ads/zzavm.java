package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.CharUtils;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzavm {
    public boolean zzL;
    public zzave zzN;
    public int zzO;
    public String zza;
    public int zzb;
    public int zzc;
    public int zzd;
    public boolean zze;
    public byte[] zzf;
    public zzavd zzg;
    public byte[] zzh;
    public zzaur zzi;
    public int zzj = -1;
    public int zzk = -1;
    public int zzl = -1;
    public int zzm = -1;
    public int zzn = 0;
    public byte[] zzo = null;
    public int zzp = -1;
    public boolean zzq = false;
    public int zzr = -1;
    public int zzs = -1;
    public int zzt = -1;
    public int zzu = 1000;
    public int zzv = 200;
    public float zzw = -1.0f;
    public float zzx = -1.0f;
    public float zzy = -1.0f;
    public float zzz = -1.0f;
    public float zzA = -1.0f;
    public float zzB = -1.0f;
    public float zzC = -1.0f;
    public float zzD = -1.0f;
    public float zzE = -1.0f;
    public float zzF = -1.0f;
    public int zzG = 1;
    public int zzH = -1;
    public int zzI = 8000;
    public long zzJ = 0;
    public long zzK = 0;
    public boolean zzM = true;
    private String zzP = "eng";

    private zzavm() {
    }

    public /* synthetic */ zzavm(zzavl zzavlVar) {
    }

    private static List zzc(zzbag zzbagVar) throws zzasv {
        try {
            zzbagVar.zzw(16);
            if (zzbagVar.zzk() != 826496599) {
                return null;
            }
            int zzc = zzbagVar.zzc() + 20;
            byte[] bArr = zzbagVar.zza;
            while (true) {
                int length = bArr.length;
                if (zzc < length - 4) {
                    if (bArr[zzc] == 0 && bArr[zzc + 1] == 0 && bArr[zzc + 2] == 1 && bArr[zzc + 3] == 15) {
                        return Collections.singletonList(Arrays.copyOfRange(bArr, zzc, length));
                    }
                    zzc++;
                } else {
                    throw new zzasv("Failed to find FourCC VC1 initialization data");
                }
            }
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new zzasv("Error parsing FourCC VC1 codec private");
        }
    }

    private static List zzd(byte[] bArr) throws zzasv {
        int r6;
        int r8;
        try {
            if (bArr[0] == 2) {
                int r4 = 1;
                int r5 = 0;
                while (true) {
                    r6 = bArr[r4];
                    if (r6 != -1) {
                        break;
                    }
                    r5 += 255;
                    r4++;
                }
                int r42 = r4 + 1;
                int r52 = r5 + r6;
                int r62 = 0;
                while (true) {
                    r8 = bArr[r42];
                    if (r8 != -1) {
                        break;
                    }
                    r62 += 255;
                    r42++;
                }
                int r43 = r42 + 1;
                int r63 = r62 + r8;
                if (bArr[r43] != 1) {
                    throw new zzasv("Error parsing vorbis codec private");
                }
                byte[] bArr2 = new byte[r52];
                System.arraycopy(bArr, r43, bArr2, 0, r52);
                int r44 = r43 + r52;
                if (bArr[r44] != 3) {
                    throw new zzasv("Error parsing vorbis codec private");
                }
                int r45 = r44 + r63;
                if (bArr[r45] != 5) {
                    throw new zzasv("Error parsing vorbis codec private");
                }
                int length = bArr.length - r45;
                byte[] bArr3 = new byte[length];
                System.arraycopy(bArr, r45, bArr3, 0, length);
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(bArr2);
                arrayList.add(bArr3);
                return arrayList;
            }
            throw new zzasv("Error parsing vorbis codec private");
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new zzasv("Error parsing vorbis codec private");
        }
    }

    private static boolean zze(zzbag zzbagVar) throws zzasv {
        UUID r0;
        UUID r8;
        try {
            int zzf = zzbagVar.zzf();
            if (zzf == 1) {
                return true;
            }
            if (zzf == 65534) {
                zzbagVar.zzv(24);
                long zzl = zzbagVar.zzl();
                r0 = zzavn.zzd;
                if (zzl == r0.getMostSignificantBits()) {
                    long zzl2 = zzbagVar.zzl();
                    r8 = zzavn.zzd;
                    if (zzl2 == r8.getLeastSignificantBits()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new zzasv("Error parsing MS/ACM codec private");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final void zzb(zzauw zzauwVar, int r45) throws zzasv {
        char c;
        List singletonList;
        List list;
        int r27;
        int r32;
        int zzh;
        zzass zzi;
        int r3;
        int r2;
        zzbaq zzbaqVar;
        byte[] bArr;
        int r5;
        String str = this.zza;
        int r6 = 2;
        switch (str.hashCode()) {
            case -2095576542:
                if (str.equals("V_MPEG4/ISO/AP")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -2095575984:
                if (str.equals("V_MPEG4/ISO/SP")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1985379776:
                if (str.equals("A_MS/ACM")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            case -1784763192:
                if (str.equals("A_TRUEHD")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -1730367663:
                if (str.equals("A_VORBIS")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1482641358:
                if (str.equals("A_MPEG/L2")) {
                    c = CharUtils.f1567CR;
                    break;
                }
                c = 65535;
                break;
            case -1482641357:
                if (str.equals("A_MPEG/L3")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -1373388978:
                if (str.equals("V_MS/VFW/FOURCC")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -933872740:
                if (str.equals("S_DVBSUB")) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case -538363189:
                if (str.equals("V_MPEG4/ISO/ASP")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -538363109:
                if (str.equals("V_MPEG4/ISO/AVC")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -425012669:
                if (str.equals("S_VOBSUB")) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -356037306:
                if (str.equals("A_DTS/LOSSLESS")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case 62923557:
                if (str.equals("A_AAC")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 62923603:
                if (str.equals("A_AC3")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 62927045:
                if (str.equals("A_DTS")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 82338133:
                if (str.equals("V_VP8")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 82338134:
                if (str.equals("V_VP9")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 99146302:
                if (str.equals("S_HDMV/PGS")) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case 444813526:
                if (str.equals("V_THEORA")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 542569478:
                if (str.equals("A_DTS/EXPRESS")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 725957860:
                if (str.equals("A_PCM/INT/LIT")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case 855502857:
                if (str.equals("V_MPEGH/ISO/HEVC")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1422270023:
                if (str.equals("S_TEXT/UTF8")) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case 1809237540:
                if (str.equals("V_MPEG2")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1950749482:
                if (str.equals("A_EAC3")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 1950789798:
                if (str.equals("A_FLAC")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case 1951062397:
                if (str.equals("A_OPUS")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        String str2 = MimeTypes.AUDIO_RAW;
        switch (c) {
            case 0:
                str2 = MimeTypes.VIDEO_VP8;
                singletonList = null;
                r27 = -1;
                r32 = -1;
                break;
            case 1:
                str2 = MimeTypes.VIDEO_VP9;
                singletonList = null;
                r27 = -1;
                r32 = -1;
                break;
            case 2:
                str2 = MimeTypes.VIDEO_MPEG2;
                singletonList = null;
                r27 = -1;
                r32 = -1;
                break;
            case 3:
            case 4:
            case 5:
                byte[] bArr2 = this.zzh;
                singletonList = bArr2 == null ? null : Collections.singletonList(bArr2);
                str2 = MimeTypes.VIDEO_MP4V;
                r27 = -1;
                r32 = -1;
                break;
            case 6:
                zzbao zza = zzbao.zza(new zzbag(this.zzh));
                list = zza.zza;
                this.zzO = zza.zzb;
                str2 = MimeTypes.VIDEO_H264;
                singletonList = list;
                r27 = -1;
                r32 = -1;
                break;
            case 7:
                zzbau zza2 = zzbau.zza(new zzbag(this.zzh));
                list = zza2.zza;
                this.zzO = zza2.zzb;
                str2 = MimeTypes.VIDEO_H265;
                singletonList = list;
                r27 = -1;
                r32 = -1;
                break;
            case '\b':
                singletonList = zzc(new zzbag(this.zzh));
                if (singletonList != null) {
                    str2 = MimeTypes.VIDEO_VC1;
                } else {
                    Log.w("MatroskaExtractor", "Unsupported FourCC. Setting mimeType to video/x-unknown");
                    str2 = MimeTypes.VIDEO_UNKNOWN;
                }
                r27 = -1;
                r32 = -1;
                break;
            case '\t':
                str2 = MimeTypes.VIDEO_UNKNOWN;
                singletonList = null;
                r27 = -1;
                r32 = -1;
                break;
            case '\n':
                singletonList = zzd(this.zzh);
                str2 = MimeTypes.AUDIO_VORBIS;
                r27 = -1;
                r32 = 8192;
                break;
            case 11:
                singletonList = new ArrayList(3);
                singletonList.add(this.zzh);
                singletonList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.zzJ).array());
                singletonList.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.zzK).array());
                str2 = MimeTypes.AUDIO_OPUS;
                r27 = -1;
                r32 = 5760;
                break;
            case '\f':
                singletonList = Collections.singletonList(this.zzh);
                str2 = MimeTypes.AUDIO_AAC;
                r27 = -1;
                r32 = -1;
                break;
            case '\r':
                str2 = MimeTypes.AUDIO_MPEG_L2;
                singletonList = null;
                r27 = -1;
                r32 = 4096;
                break;
            case 14:
                str2 = MimeTypes.AUDIO_MPEG;
                singletonList = null;
                r27 = -1;
                r32 = 4096;
                break;
            case 15:
                str2 = MimeTypes.AUDIO_AC3;
                singletonList = null;
                r27 = -1;
                r32 = -1;
                break;
            case 16:
                str2 = MimeTypes.AUDIO_E_AC3;
                singletonList = null;
                r27 = -1;
                r32 = -1;
                break;
            case 17:
                str2 = MimeTypes.AUDIO_TRUEHD;
                singletonList = null;
                r27 = -1;
                r32 = -1;
                break;
            case 18:
            case 19:
                str2 = MimeTypes.AUDIO_DTS;
                singletonList = null;
                r27 = -1;
                r32 = -1;
                break;
            case 20:
                str2 = MimeTypes.AUDIO_DTS_HD;
                singletonList = null;
                r27 = -1;
                r32 = -1;
                break;
            case 21:
                singletonList = Collections.singletonList(this.zzh);
                str2 = "audio/x-flac";
                r27 = -1;
                r32 = -1;
                break;
            case 22:
                if (zze(new zzbag(this.zzh))) {
                    zzh = zzban.zzh(this.zzH);
                    if (zzh == 0) {
                        Log.w("MatroskaExtractor", "Unsupported PCM bit depth: " + this.zzH + ". Setting mimeType to audio/x-unknown");
                    }
                    r27 = zzh;
                    singletonList = null;
                    r32 = -1;
                    break;
                } else {
                    Log.w("MatroskaExtractor", "Non-PCM MS/ACM is unsupported. Setting mimeType to audio/x-unknown");
                }
                str2 = MimeTypes.AUDIO_UNKNOWN;
                singletonList = null;
                r27 = -1;
                r32 = -1;
            case 23:
                zzh = zzban.zzh(this.zzH);
                if (zzh == 0) {
                    Log.w("MatroskaExtractor", "Unsupported PCM bit depth: " + this.zzH + ". Setting mimeType to audio/x-unknown");
                    str2 = MimeTypes.AUDIO_UNKNOWN;
                    singletonList = null;
                    r27 = -1;
                    r32 = -1;
                    break;
                }
                r27 = zzh;
                singletonList = null;
                r32 = -1;
            case 24:
                str2 = MimeTypes.APPLICATION_SUBRIP;
                singletonList = null;
                r27 = -1;
                r32 = -1;
                break;
            case 25:
                singletonList = Collections.singletonList(this.zzh);
                str2 = MimeTypes.APPLICATION_VOBSUB;
                r27 = -1;
                r32 = -1;
                break;
            case 26:
                str2 = MimeTypes.APPLICATION_PGS;
                singletonList = null;
                r27 = -1;
                r32 = -1;
                break;
            case 27:
                byte[] bArr3 = this.zzh;
                singletonList = Collections.singletonList(new byte[]{bArr3[0], bArr3[1], bArr3[2], bArr3[3]});
                str2 = MimeTypes.APPLICATION_DVBSUBS;
                r27 = -1;
                r32 = -1;
                break;
            default:
                throw new zzasv("Unrecognized codec identifier.");
        }
        int r22 = (this.zzM ? 1 : 0) | (true != this.zzL ? 0 : 2);
        if (zzbad.zza(str2)) {
            zzi = zzass.zzh(Integer.toString(r45), str2, null, -1, r32, this.zzG, this.zzI, r27, -1, -1, singletonList, this.zzi, r22, this.zzP, null);
            r6 = 1;
        } else if (zzbad.zzb(str2)) {
            if (this.zzn == 0) {
                int r23 = this.zzl;
                r3 = -1;
                if (r23 == -1) {
                    r23 = this.zzj;
                }
                this.zzl = r23;
                int r24 = this.zzm;
                if (r24 == -1) {
                    r24 = this.zzk;
                }
                this.zzm = r24;
            } else {
                r3 = -1;
            }
            float f = (this.zzl == r3 || (r5 = this.zzm) == r3) ? -1.0f : (this.zzk * r2) / (this.zzj * r5);
            if (this.zzq) {
                if (this.zzw == -1.0f || this.zzx == -1.0f || this.zzy == -1.0f || this.zzz == -1.0f || this.zzA == -1.0f || this.zzB == -1.0f || this.zzC == -1.0f || this.zzD == -1.0f || this.zzE == -1.0f || this.zzF == -1.0f) {
                    bArr = null;
                } else {
                    bArr = new byte[25];
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    wrap.put((byte) 0);
                    wrap.putShort((short) ((this.zzw * 50000.0f) + 0.5f));
                    wrap.putShort((short) ((this.zzx * 50000.0f) + 0.5f));
                    wrap.putShort((short) ((this.zzy * 50000.0f) + 0.5f));
                    wrap.putShort((short) ((this.zzz * 50000.0f) + 0.5f));
                    wrap.putShort((short) ((this.zzA * 50000.0f) + 0.5f));
                    wrap.putShort((short) ((this.zzB * 50000.0f) + 0.5f));
                    wrap.putShort((short) ((this.zzC * 50000.0f) + 0.5f));
                    wrap.putShort((short) ((this.zzD * 50000.0f) + 0.5f));
                    wrap.putShort((short) (this.zzE + 0.5f));
                    wrap.putShort((short) (this.zzF + 0.5f));
                    wrap.putShort((short) this.zzu);
                    wrap.putShort((short) this.zzv);
                }
                zzbaqVar = new zzbaq(this.zzr, this.zzt, this.zzs, bArr);
            } else {
                zzbaqVar = null;
            }
            zzi = zzass.zzl(Integer.toString(r45), str2, null, -1, r32, this.zzj, this.zzk, -1.0f, singletonList, -1, f, this.zzo, this.zzp, zzbaqVar, this.zzi);
        } else {
            if (MimeTypes.APPLICATION_SUBRIP.equals(str2)) {
                zzi = zzass.zzk(Integer.toString(r45), str2, null, -1, r22, this.zzP, -1, this.zzi, Long.MAX_VALUE, Collections.emptyList());
            } else if (MimeTypes.APPLICATION_VOBSUB.equals(str2) || MimeTypes.APPLICATION_PGS.equals(str2) || MimeTypes.APPLICATION_DVBSUBS.equals(str2)) {
                zzi = zzass.zzi(Integer.toString(r45), str2, null, -1, singletonList, this.zzP, this.zzi);
            } else {
                throw new zzasv("Unexpected MIME type.");
            }
            r6 = 3;
        }
        zzave zzbi = zzauwVar.zzbi(this.zzb, r6);
        this.zzN = zzbi;
        zzbi.zza(zzi);
    }
}
