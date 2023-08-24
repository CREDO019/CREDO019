package com.google.android.gms.internal.ads;

import android.media.MediaCodecInfo;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzrd {
    public static final /* synthetic */ int zza = 0;
    private static final Pattern zzb = Pattern.compile("^\\D?(\\d+)$");
    private static final HashMap zzc = new HashMap();
    private static int zzd = -1;

    public static int zza() throws zzqx {
        int r5;
        int r0 = zzd;
        if (r0 == -1) {
            zzql zzc2 = zzc(MimeTypes.VIDEO_H264, false, false);
            if (zzc2 != null) {
                int r4 = 0;
                for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : zzc2.zzg()) {
                    int r52 = codecProfileLevel.level;
                    if (r52 == 1 || r52 == 2) {
                        r5 = 25344;
                    } else {
                        switch (r52) {
                            case 8:
                            case 16:
                            case 32:
                                r5 = 101376;
                                continue;
                            case 64:
                                r5 = 202752;
                                continue;
                            case 128:
                            case 256:
                                r5 = 414720;
                                continue;
                            case 512:
                                r5 = 921600;
                                continue;
                            case 1024:
                                r5 = 1310720;
                                continue;
                            case 2048:
                            case 4096:
                                r5 = 2097152;
                                continue;
                            case 8192:
                                r5 = 2228224;
                                continue;
                            case 16384:
                                r5 = 5652480;
                                continue;
                            case 32768:
                            case 65536:
                                r5 = 9437184;
                                continue;
                            case 131072:
                            case 262144:
                            case 524288:
                                r5 = 35651584;
                                continue;
                            default:
                                r5 = -1;
                                continue;
                        }
                    }
                    r4 = Math.max(r5, r4);
                }
                r0 = Math.max(r4, zzel.zza >= 21 ? 345600 : 172800);
            } else {
                r0 = 0;
            }
            zzd = r0;
        }
        return r0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02c5 A[Catch: NumberFormatException -> 0x02d5, TRY_LEAVE, TryCatch #1 {NumberFormatException -> 0x02d5, blocks: (B:148:0x026a, B:150:0x027c, B:161:0x0298, B:177:0x02c5), top: B:488:0x026a }] */
    /* JADX WARN: Removed duplicated region for block: B:362:0x0610  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x061f  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x011e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.util.Pair zzb(com.google.android.gms.internal.ads.zzaf r17) {
        /*
            Method dump skipped, instructions count: 2540
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzrd.zzb(com.google.android.gms.internal.ads.zzaf):android.util.Pair");
    }

    public static zzql zzc(String str, boolean z, boolean z2) throws zzqx {
        List zzf = zzf(str, false, false);
        if (zzf.isEmpty()) {
            return null;
        }
        return (zzql) zzf.get(0);
    }

    public static zzql zzd() throws zzqx {
        return zzc(MimeTypes.AUDIO_RAW, false, false);
    }

    public static String zze(zzaf zzafVar) {
        Pair zzb2;
        if (MimeTypes.AUDIO_E_AC3_JOC.equals(zzafVar.zzm)) {
            return MimeTypes.AUDIO_E_AC3;
        }
        if (!MimeTypes.VIDEO_DOLBY_VISION.equals(zzafVar.zzm) || (zzb2 = zzb(zzafVar)) == null) {
            return null;
        }
        int intValue = ((Integer) zzb2.first).intValue();
        if (intValue == 16 || intValue == 256) {
            return MimeTypes.VIDEO_H265;
        }
        if (intValue == 512) {
            return MimeTypes.VIDEO_H264;
        }
        return null;
    }

    public static synchronized List zzf(String str, boolean z, boolean z2) throws zzqx {
        zzqy zzraVar;
        int r1;
        synchronized (zzrd.class) {
            zzqv zzqvVar = new zzqv(str, z, z2);
            HashMap hashMap = zzc;
            List list = (List) hashMap.get(zzqvVar);
            if (list != null) {
                return list;
            }
            if (zzel.zza >= 21) {
                zzraVar = new zzrb(z, z2);
            } else {
                zzraVar = new zzra(null);
            }
            ArrayList zzh = zzh(zzqvVar, zzraVar);
            if (z && zzh.isEmpty() && (r1 = zzel.zza) >= 21 && r1 <= 23) {
                zzh = zzh(zzqvVar, new zzra(null));
                if (!zzh.isEmpty()) {
                    String str2 = ((zzql) zzh.get(0)).zza;
                    Log.w("MediaCodecUtil", "MediaCodecList API didn't list secure decoder for: " + str + ". Assuming: " + str2);
                }
            }
            if (MimeTypes.AUDIO_RAW.equals(str)) {
                if (zzel.zza < 26 && zzel.zzb.equals("R9") && zzh.size() == 1 && ((zzql) zzh.get(0)).zza.equals("OMX.MTK.AUDIO.DECODER.RAW")) {
                    zzh.add(zzql.zzc("OMX.google.raw.decoder", MimeTypes.AUDIO_RAW, MimeTypes.AUDIO_RAW, null, false, true, false, false, false));
                }
                zzi(zzh, new zzrc() { // from class: com.google.android.gms.internal.ads.zzqt
                    @Override // com.google.android.gms.internal.ads.zzrc
                    public final int zza(Object obj) {
                        int r0 = zzrd.zza;
                        String str3 = ((zzql) obj).zza;
                        if (str3.startsWith("OMX.google") || str3.startsWith("c2.android")) {
                            return 1;
                        }
                        return (zzel.zza >= 26 || !str3.equals("OMX.MTK.AUDIO.DECODER.RAW")) ? 0 : -1;
                    }
                });
            }
            if (zzel.zza < 21 && zzh.size() > 1) {
                String str3 = ((zzql) zzh.get(0)).zza;
                if ("OMX.SEC.mp3.dec".equals(str3) || "OMX.SEC.MP3.Decoder".equals(str3) || "OMX.brcm.audio.mp3.decoder".equals(str3)) {
                    zzi(zzh, new zzrc() { // from class: com.google.android.gms.internal.ads.zzqu
                        @Override // com.google.android.gms.internal.ads.zzrc
                        public final int zza(Object obj) {
                            int r0 = zzrd.zza;
                            return ((zzql) obj).zza.startsWith("OMX.google") ? 1 : 0;
                        }
                    });
                }
            }
            if (zzel.zza < 32 && zzh.size() > 1 && "OMX.qti.audio.decoder.flac".equals(((zzql) zzh.get(0)).zza)) {
                zzh.add((zzql) zzh.remove(0));
            }
            zzfuv zzm = zzfuv.zzm(zzh);
            hashMap.put(zzqvVar, zzm);
            return zzm;
        }
    }

    public static List zzg(List list, final zzaf zzafVar) {
        ArrayList arrayList = new ArrayList(list);
        zzi(arrayList, new zzrc() { // from class: com.google.android.gms.internal.ads.zzqr
            @Override // com.google.android.gms.internal.ads.zzrc
            public final int zza(Object obj) {
                zzaf zzafVar2 = zzaf.this;
                zzql zzqlVar = (zzql) obj;
                int r1 = zzrd.zza;
                try {
                    return !zzqlVar.zzd(zzafVar2) ? 0 : 1;
                } catch (zzqx unused) {
                    return -1;
                }
            }
        });
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:153:0x0254, code lost:
        if (r1.zzb == false) goto L112;
     */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0209 A[Catch: Exception -> 0x02b2, TryCatch #5 {Exception -> 0x02b2, blocks: (B:118:0x01d8, B:124:0x01ef, B:130:0x0203, B:132:0x0209, B:137:0x0218, B:139:0x0222, B:149:0x024c, B:140:0x0227, B:142:0x0237, B:144:0x023f, B:133:0x020f), top: B:198:0x01d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x020f A[Catch: Exception -> 0x02b2, TryCatch #5 {Exception -> 0x02b2, blocks: (B:118:0x01d8, B:124:0x01ef, B:130:0x0203, B:132:0x0209, B:137:0x0218, B:139:0x0222, B:149:0x024c, B:140:0x0227, B:142:0x0237, B:144:0x023f, B:133:0x020f), top: B:198:0x01d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0222 A[Catch: Exception -> 0x02b2, TryCatch #5 {Exception -> 0x02b2, blocks: (B:118:0x01d8, B:124:0x01ef, B:130:0x0203, B:132:0x0209, B:137:0x0218, B:139:0x0222, B:149:0x024c, B:140:0x0227, B:142:0x0237, B:144:0x023f, B:133:0x020f), top: B:198:0x01d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0227 A[Catch: Exception -> 0x02b2, TryCatch #5 {Exception -> 0x02b2, blocks: (B:118:0x01d8, B:124:0x01ef, B:130:0x0203, B:132:0x0209, B:137:0x0218, B:139:0x0222, B:149:0x024c, B:140:0x0227, B:142:0x0237, B:144:0x023f, B:133:0x020f), top: B:198:0x01d8 }] */
    /* JADX WARN: Removed duplicated region for block: B:178:0x02c1 A[Catch: Exception -> 0x030f, TRY_ENTER, TryCatch #1 {Exception -> 0x030f, blocks: (B:3:0x0008, B:5:0x001c, B:7:0x0026, B:10:0x0033, B:14:0x0041, B:16:0x0047, B:18:0x004d, B:20:0x0055, B:22:0x005d, B:24:0x0065, B:26:0x006d, B:28:0x0075, B:30:0x007d, B:33:0x0085, B:35:0x008d, B:37:0x0095, B:39:0x009d, B:41:0x00a7, B:43:0x00b1, B:45:0x00bb, B:47:0x00c5, B:49:0x00cf, B:51:0x00d9, B:53:0x00e3, B:55:0x00ed, B:58:0x00f5, B:60:0x00fd, B:62:0x0105, B:64:0x010f, B:66:0x0119, B:68:0x0121, B:70:0x012b, B:72:0x0135, B:74:0x0139, B:76:0x0141, B:78:0x0149, B:80:0x014f, B:82:0x0157, B:84:0x015f, B:86:0x0167, B:175:0x02b9, B:178:0x02c1, B:180:0x02c7, B:181:0x02e1, B:182:0x0302, B:89:0x0171, B:90:0x0176, B:92:0x017e, B:95:0x0189, B:97:0x0191, B:100:0x019c, B:102:0x01a4, B:105:0x01af, B:107:0x01b7, B:110:0x01c2, B:112:0x01ca), top: B:190:0x0008 }] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0252 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02e1 A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.ArrayList zzh(com.google.android.gms.internal.ads.zzqv r23, com.google.android.gms.internal.ads.zzqy r24) throws com.google.android.gms.internal.ads.zzqx {
        /*
            Method dump skipped, instructions count: 791
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzrd.zzh(com.google.android.gms.internal.ads.zzqv, com.google.android.gms.internal.ads.zzqy):java.util.ArrayList");
    }

    private static void zzi(List list, final zzrc zzrcVar) {
        Collections.sort(list, new Comparator() { // from class: com.google.android.gms.internal.ads.zzqs
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                zzrc zzrcVar2 = zzrc.this;
                int r1 = zzrd.zza;
                return zzrcVar2.zza(obj2) - zzrcVar2.zza(obj);
            }
        });
    }

    private static boolean zzj(MediaCodecInfo mediaCodecInfo, String str) {
        if (zzel.zza >= 29) {
            return mediaCodecInfo.isSoftwareOnly();
        }
        if (zzbt.zzg(str)) {
            return true;
        }
        String zza2 = zzfrm.zza(mediaCodecInfo.getName());
        if (zza2.startsWith("arc.")) {
            return false;
        }
        if (zza2.startsWith("omx.google.") || zza2.startsWith("omx.ffmpeg.")) {
            return true;
        }
        if ((zza2.startsWith("omx.sec.") && zza2.contains(".sw.")) || zza2.equals("omx.qcom.video.decoder.hevcswvdec") || zza2.startsWith("c2.android.") || zza2.startsWith("c2.google.")) {
            return true;
        }
        return (zza2.startsWith("omx.") || zza2.startsWith("c2.")) ? false : true;
    }
}
