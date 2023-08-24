package com.google.android.gms.internal.ads;

import android.media.MediaCodecInfo;
import android.util.Log;
import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaxa {
    private static final SparseIntArray zzd;
    private static final SparseIntArray zze;
    private static final Map zzf;
    private static final zzawo zza = zzawo.zzb("OMX.google.raw.decoder");
    private static final Pattern zzb = Pattern.compile("^\\D?(\\d+)$");
    private static final HashMap zzc = new HashMap();
    private static int zzg = -1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        zzd = sparseIntArray;
        sparseIntArray.put(66, 1);
        sparseIntArray.put(77, 2);
        sparseIntArray.put(88, 4);
        sparseIntArray.put(100, 8);
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        zze = sparseIntArray2;
        sparseIntArray2.put(10, 1);
        sparseIntArray2.put(11, 4);
        sparseIntArray2.put(12, 8);
        sparseIntArray2.put(13, 16);
        sparseIntArray2.put(20, 32);
        sparseIntArray2.put(21, 64);
        sparseIntArray2.put(22, 128);
        sparseIntArray2.put(30, 256);
        sparseIntArray2.put(31, 512);
        sparseIntArray2.put(32, 1024);
        sparseIntArray2.put(40, 2048);
        sparseIntArray2.put(41, 4096);
        sparseIntArray2.put(42, 8192);
        sparseIntArray2.put(50, 16384);
        sparseIntArray2.put(51, 32768);
        sparseIntArray2.put(52, 65536);
        HashMap hashMap = new HashMap();
        zzf = hashMap;
        hashMap.put("L30", 1);
        hashMap.put("L60", 4);
        hashMap.put("L63", 16);
        hashMap.put("L90", 64);
        hashMap.put("L93", 256);
        hashMap.put("L120", 1024);
        hashMap.put("L123", 4096);
        hashMap.put("L150", 16384);
        hashMap.put("L153", 65536);
        hashMap.put("L156", 262144);
        hashMap.put("L180", 1048576);
        hashMap.put("L183", 4194304);
        hashMap.put("L186", 16777216);
        hashMap.put("H30", 2);
        hashMap.put("H60", 8);
        hashMap.put("H63", 32);
        hashMap.put("H90", 128);
        hashMap.put("H93", 512);
        hashMap.put("H120", 2048);
        hashMap.put("H123", 8192);
        hashMap.put("H150", 32768);
        hashMap.put("H153", 131072);
        hashMap.put("H156", 524288);
        hashMap.put("H180", 2097152);
        hashMap.put("H183", 8388608);
        hashMap.put("H186", 33554432);
    }

    public static int zza() throws zzawv {
        int r0 = zzg;
        if (r0 == -1) {
            zzawo zzc2 = zzc(MimeTypes.VIDEO_H264, false);
            if (zzc2 != null) {
                int r4 = 0;
                for (MediaCodecInfo.CodecProfileLevel codecProfileLevel : zzc2.zzg()) {
                    int r5 = codecProfileLevel.level;
                    int r6 = 9437184;
                    if (r5 == 1 || r5 == 2) {
                        r6 = 25344;
                    } else {
                        switch (r5) {
                            case 8:
                            case 16:
                            case 32:
                                r6 = 101376;
                                continue;
                            case 64:
                                r6 = 202752;
                                continue;
                            case 128:
                            case 256:
                                r6 = 414720;
                                continue;
                            case 512:
                                r6 = 921600;
                                continue;
                            case 1024:
                                r6 = 1310720;
                                continue;
                            case 2048:
                            case 4096:
                                r6 = 2097152;
                                continue;
                            case 8192:
                                r6 = 2228224;
                                continue;
                            case 16384:
                                r6 = 5652480;
                                continue;
                            case 32768:
                            case 65536:
                                break;
                            default:
                                r6 = -1;
                                continue;
                        }
                    }
                    r4 = Math.max(r6, r4);
                }
                r0 = Math.max(r4, zzban.zza >= 21 ? 345600 : 172800);
            } else {
                r0 = 0;
            }
            zzg = r0;
        }
        return r0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static Pair zzb(String str) {
        char c;
        Integer valueOf;
        Integer valueOf2;
        String[] split = str.split("\\.");
        String str2 = split[0];
        int r5 = 2;
        switch (str2.hashCode()) {
            case 3006243:
                if (str2.equals("avc1")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3006244:
                if (str2.equals("avc2")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3199032:
                if (str2.equals("hev1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3214780:
                if (str2.equals("hvc1")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0 || c == 1) {
            if (split.length < 4) {
                Log.w("MediaCodecUtil", "Ignoring malformed HEVC codec string: ".concat(str));
                return null;
            }
            Matcher matcher = zzb.matcher(split[1]);
            if (!matcher.matches()) {
                Log.w("MediaCodecUtil", "Ignoring malformed HEVC codec string: ".concat(str));
                return null;
            }
            String group = matcher.group(1);
            if (IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(group)) {
                r5 = 1;
            } else if (!"2".equals(group)) {
                Log.w("MediaCodecUtil", "Unknown HEVC profile string: ".concat(String.valueOf(group)));
                return null;
            }
            Integer num = (Integer) zzf.get(split[3]);
            if (num == null) {
                Log.w("MediaCodecUtil", "Unknown HEVC level string: ".concat(String.valueOf(matcher.group(1))));
                return null;
            }
            return new Pair(Integer.valueOf(r5), num);
        } else if (c == 2 || c == 3) {
            int length = split.length;
            if (length < 2) {
                Log.w("MediaCodecUtil", "Ignoring malformed AVC codec string: ".concat(str));
                return null;
            }
            try {
                if (split[1].length() == 6) {
                    valueOf = Integer.valueOf(Integer.parseInt(split[1].substring(0, 2), 16));
                    valueOf2 = Integer.valueOf(Integer.parseInt(split[1].substring(4), 16));
                } else if (length >= 3) {
                    valueOf = Integer.valueOf(Integer.parseInt(split[1]));
                    valueOf2 = Integer.valueOf(Integer.parseInt(split[2]));
                } else {
                    Log.w("MediaCodecUtil", "Ignoring malformed AVC codec string: " + str);
                    return null;
                }
                return new Pair(Integer.valueOf(zzd.get(valueOf.intValue())), Integer.valueOf(zze.get(valueOf2.intValue())));
            } catch (NumberFormatException unused) {
                Log.w("MediaCodecUtil", "Ignoring malformed AVC codec string: ".concat(str));
                return null;
            }
        } else {
            return null;
        }
    }

    public static zzawo zzc(String str, boolean z) throws zzawv {
        List zzd2 = zzd(str, z);
        if (zzd2.isEmpty()) {
            return null;
        }
        return (zzawo) zzd2.get(0);
    }

    public static synchronized List zzd(String str, boolean z) throws zzawv {
        zzaww zzawyVar;
        int r7;
        synchronized (zzaxa.class) {
            zzawt zzawtVar = new zzawt(str, z);
            HashMap hashMap = zzc;
            List list = (List) hashMap.get(zzawtVar);
            if (list != null) {
                return list;
            }
            if (zzban.zza >= 21) {
                zzawyVar = new zzawz(z);
            } else {
                zzawyVar = new zzawy(null);
            }
            List zze2 = zze(zzawtVar, zzawyVar);
            if (z && zze2.isEmpty() && (r7 = zzban.zza) >= 21 && r7 <= 23) {
                zze2 = zze(zzawtVar, new zzawy(null));
                if (!zze2.isEmpty()) {
                    String str2 = ((zzawo) zze2.get(0)).zza;
                    Log.w("MediaCodecUtil", "MediaCodecList API didn't list secure decoder for: " + str + ". Assuming: " + str2);
                }
            }
            List unmodifiableList = Collections.unmodifiableList(zze2);
            hashMap.put(zzawtVar, unmodifiableList);
            return unmodifiableList;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:119:0x01f0 A[Catch: Exception -> 0x01eb, TryCatch #1 {Exception -> 0x01eb, blocks: (B:106:0x01c5, B:108:0x01cf, B:110:0x01d9, B:112:0x01e1, B:119:0x01f0, B:124:0x01f9), top: B:155:0x01c5 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.List zze(com.google.android.gms.internal.ads.zzawt r18, com.google.android.gms.internal.ads.zzaww r19) throws com.google.android.gms.internal.ads.zzawv {
        /*
            Method dump skipped, instructions count: 664
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaxa.zze(com.google.android.gms.internal.ads.zzawt, com.google.android.gms.internal.ads.zzaww):java.util.List");
    }
}
