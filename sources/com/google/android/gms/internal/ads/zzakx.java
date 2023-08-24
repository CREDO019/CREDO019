package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.common.net.HttpHeaders;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.apache.commons.lang3.time.TimeZones;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzakx {
    public static long zza(String str) {
        try {
            return zzd("EEE, dd MMM yyyy HH:mm:ss zzz").parse(str).getTime();
        } catch (ParseException e) {
            if (SessionDescription.SUPPORTED_SDP_VERSION.equals(str) || "-1".equals(str)) {
                zzakm.zzd("Unable to parse dateStr: %s, falling back to 0", str);
                return 0L;
            }
            zzakm.zzc(e, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0L;
        }
    }

    public static zzajj zzb(zzajw zzajwVar) {
        boolean z;
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long currentTimeMillis = System.currentTimeMillis();
        Map map = zzajwVar.zzc;
        if (map == null) {
            return null;
        }
        String str = (String) map.get("Date");
        long zza = str != null ? zza(str) : 0L;
        String str2 = (String) map.get("Cache-Control");
        int r11 = 0;
        if (str2 != null) {
            String[] split = str2.split(",", 0);
            int r12 = 0;
            j = 0;
            j2 = 0;
            while (r11 < split.length) {
                String trim = split[r11].trim();
                if (trim.equals("no-cache") || trim.equals("no-store")) {
                    return null;
                }
                if (trim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(trim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (trim.startsWith("stale-while-revalidate=")) {
                    j2 = Long.parseLong(trim.substring(23));
                } else if (trim.equals("must-revalidate") || trim.equals("proxy-revalidate")) {
                    r12 = 1;
                }
                r11++;
            }
            r11 = r12;
            z = true;
        } else {
            z = false;
            j = 0;
            j2 = 0;
        }
        String str3 = (String) map.get("Expires");
        long zza2 = str3 != null ? zza(str3) : 0L;
        String str4 = (String) map.get(HttpHeaders.LAST_MODIFIED);
        long zza3 = str4 != null ? zza(str4) : 0L;
        String str5 = (String) map.get(HttpHeaders.ETAG);
        if (z) {
            j4 = currentTimeMillis + (j * 1000);
            if (r11 != 0) {
                j5 = j4;
            } else {
                Long.signum(j2);
                j5 = (j2 * 1000) + j4;
            }
            j3 = j5;
        } else {
            j3 = 0;
            if (zza <= 0 || zza2 < zza) {
                j4 = 0;
            } else {
                j4 = currentTimeMillis + (zza2 - zza);
                j3 = j4;
            }
        }
        zzajj zzajjVar = new zzajj();
        zzajjVar.zza = zzajwVar.zzb;
        zzajjVar.zzb = str5;
        zzajjVar.zzf = j4;
        zzajjVar.zze = j3;
        zzajjVar.zzc = zza;
        zzajjVar.zzd = zza3;
        zzajjVar.zzg = map;
        zzajjVar.zzh = zzajwVar.zzd;
        return zzajjVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzc(long j) {
        return zzd("EEE, dd MMM yyyy HH:mm:ss 'GMT'").format(new Date(j));
    }

    private static SimpleDateFormat zzd(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(TimeZones.GMT_ID));
        return simpleDateFormat;
    }
}
