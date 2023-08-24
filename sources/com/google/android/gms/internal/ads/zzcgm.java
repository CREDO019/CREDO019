package com.google.android.gms.internal.ads;

import android.content.Context;
import android.provider.Settings;
import android.util.JsonWriter;
import androidx.browser.trusted.sharing.ShareTarget;
import androidx.core.app.NotificationCompat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.onesignal.outcomes.data.OutcomeEventsTable;
import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcgm {
    public static final /* synthetic */ int zza = 0;
    private static boolean zzc = false;
    private static boolean zzd = false;
    private final List zzg;
    private static final Object zzb = new Object();
    private static final Clock zze = DefaultClock.getInstance();
    private static final Set zzf = new HashSet(Arrays.asList(new String[0]));

    public zzcgm() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zza(String str, String str2, Map map, byte[] bArr, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name(OutcomeEventsTable.COLUMN_NAME_PARAMS).beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name("uri").value(str);
        jsonWriter.name("verb").value(str2);
        jsonWriter.endObject();
        zzs(jsonWriter, map);
        if (bArr != null) {
            jsonWriter.name(TtmlNode.TAG_BODY).value(Base64Utils.encode(bArr));
        }
        jsonWriter.endObject();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzb(int r3, Map map, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name(OutcomeEventsTable.COLUMN_NAME_PARAMS).beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name("code").value(r3);
        jsonWriter.endObject();
        zzs(jsonWriter, map);
        jsonWriter.endObject();
    }

    public static void zzi() {
        synchronized (zzb) {
            zzc = false;
            zzd = false;
            zzcgn.zzj("Ad debug logging enablement is out of date.");
        }
    }

    public static void zzj(boolean z) {
        synchronized (zzb) {
            zzc = true;
            zzd = z;
        }
    }

    public static boolean zzk(Context context) {
        if (((Boolean) zzbkk.zza.zze()).booleanValue()) {
            try {
                return Settings.Global.getInt(context.getContentResolver(), "development_settings_enabled", 0) != 0;
            } catch (Exception e) {
                zzcgn.zzk("Fail to determine debug setting.", e);
                return false;
            }
        }
        return false;
    }

    public static boolean zzl() {
        boolean z;
        synchronized (zzb) {
            z = false;
            if (zzc && zzd) {
                z = true;
            }
        }
        return z;
    }

    public static boolean zzm() {
        boolean z;
        synchronized (zzb) {
            z = zzc;
        }
        return z;
    }

    private static synchronized void zzn(String str) {
        synchronized (zzcgm.class) {
            zzcgn.zzi("GMA Debug BEGIN");
            int r1 = 0;
            while (r1 < str.length()) {
                int r2 = r1 + 4000;
                zzcgn.zzi("GMA Debug CONTENT ".concat(String.valueOf(str.substring(r1, Math.min(r2, str.length())))));
                r1 = r2;
            }
            zzcgn.zzi("GMA Debug FINISH");
        }
    }

    private final void zzo(String str, zzcgl zzcglVar) {
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        try {
            jsonWriter.beginObject();
            jsonWriter.name("timestamp").value(zze.currentTimeMillis());
            jsonWriter.name(NotificationCompat.CATEGORY_EVENT).value(str);
            jsonWriter.name("components").beginArray();
            for (String str2 : this.zzg) {
                jsonWriter.value(str2);
            }
            jsonWriter.endArray();
            zzcglVar.zza(jsonWriter);
            jsonWriter.endObject();
            jsonWriter.flush();
            jsonWriter.close();
        } catch (IOException e) {
            zzcgn.zzh("unable to log", e);
        }
        zzn(stringWriter.toString());
    }

    private final void zzp(final String str) {
        zzo("onNetworkRequestError", new zzcgl() { // from class: com.google.android.gms.internal.ads.zzcgi
            @Override // com.google.android.gms.internal.ads.zzcgl
            public final void zza(JsonWriter jsonWriter) {
                String str2 = str;
                int r1 = zzcgm.zza;
                jsonWriter.name(OutcomeEventsTable.COLUMN_NAME_PARAMS).beginObject();
                if (str2 != null) {
                    jsonWriter.name("error_description").value(str2);
                }
                jsonWriter.endObject();
            }
        });
    }

    private final void zzq(final String str, final String str2, final Map map, final byte[] bArr) {
        zzo("onNetworkRequest", new zzcgl() { // from class: com.google.android.gms.internal.ads.zzcgj
            @Override // com.google.android.gms.internal.ads.zzcgl
            public final void zza(JsonWriter jsonWriter) {
                zzcgm.zza(str, str2, map, bArr, jsonWriter);
            }
        });
    }

    private final void zzr(final Map map, final int r3) {
        zzo("onNetworkResponse", new zzcgl() { // from class: com.google.android.gms.internal.ads.zzcgh
            @Override // com.google.android.gms.internal.ads.zzcgl
            public final void zza(JsonWriter jsonWriter) {
                zzcgm.zzb(r3, map, jsonWriter);
            }
        });
    }

    private static void zzs(JsonWriter jsonWriter, Map map) throws IOException {
        if (map == null) {
            return;
        }
        jsonWriter.name("headers").beginArray();
        Iterator it = map.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            if (!zzf.contains(str)) {
                if (entry.getValue() instanceof List) {
                    for (String str2 : (List) entry.getValue()) {
                        jsonWriter.beginObject();
                        jsonWriter.name("name").value(str);
                        jsonWriter.name("value").value(str2);
                        jsonWriter.endObject();
                    }
                } else if (entry.getValue() instanceof String) {
                    jsonWriter.beginObject();
                    jsonWriter.name("name").value(str);
                    jsonWriter.name("value").value((String) entry.getValue());
                    jsonWriter.endObject();
                } else {
                    zzcgn.zzg("Connection headers should be either Map<String, String> or Map<String, List<String>>");
                    break;
                }
            }
        }
        jsonWriter.endArray();
    }

    public final void zzc(HttpURLConnection httpURLConnection, byte[] bArr) {
        if (zzl()) {
            zzq(new String(httpURLConnection.getURL().toString()), new String(httpURLConnection.getRequestMethod()), httpURLConnection.getRequestProperties() == null ? null : new HashMap(httpURLConnection.getRequestProperties()), bArr);
        }
    }

    public final void zzd(String str, String str2, Map map, byte[] bArr) {
        if (zzl()) {
            zzq(str, ShareTarget.METHOD_GET, map, bArr);
        }
    }

    public final void zze(HttpURLConnection httpURLConnection, int r5) {
        if (zzl()) {
            String str = null;
            zzr(httpURLConnection.getHeaderFields() == null ? null : new HashMap(httpURLConnection.getHeaderFields()), r5);
            if (r5 < 200 || r5 >= 300) {
                try {
                    str = httpURLConnection.getResponseMessage();
                } catch (IOException e) {
                    zzcgn.zzj("Can not get error message from error HttpURLConnection\n".concat(String.valueOf(e.getMessage())));
                }
                zzp(str);
            }
        }
    }

    public final void zzf(Map map, int r3) {
        if (zzl()) {
            zzr(map, r3);
            if (r3 < 200 || r3 >= 300) {
                zzp(null);
            }
        }
    }

    public final void zzg(String str) {
        if (zzl() && str != null) {
            zzh(str.getBytes());
        }
    }

    public final void zzh(final byte[] bArr) {
        zzo("onNetworkResponseBody", new zzcgl() { // from class: com.google.android.gms.internal.ads.zzcgk
            @Override // com.google.android.gms.internal.ads.zzcgl
            public final void zza(JsonWriter jsonWriter) {
                byte[] bArr2 = bArr;
                int r1 = zzcgm.zza;
                jsonWriter.name(OutcomeEventsTable.COLUMN_NAME_PARAMS).beginObject();
                int length = bArr2.length;
                String encode = Base64Utils.encode(bArr2);
                if (length < 10000) {
                    jsonWriter.name(TtmlNode.TAG_BODY).value(encode);
                } else {
                    String zze2 = zzcgg.zze(encode);
                    if (zze2 != null) {
                        jsonWriter.name("bodydigest").value(zze2);
                    }
                }
                jsonWriter.name("bodylength").value(length);
                jsonWriter.endObject();
            }
        });
    }

    public zzcgm(String str) {
        List asList;
        if (!zzl()) {
            asList = new ArrayList();
        } else {
            asList = Arrays.asList("network_request_".concat(String.valueOf(UUID.randomUUID().toString())));
        }
        this.zzg = asList;
    }
}
