package com.google.android.gms.internal.vision;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class zzal {
    private static HashMap<String, String> zzex;
    private static Object zzfc;
    private static boolean zzfd;
    public static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    private static final Uri zzet = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern zzeu = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern zzev = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    private static final AtomicBoolean zzew = new AtomicBoolean();
    private static final HashMap<String, Boolean> zzey = new HashMap<>();
    private static final HashMap<String, Integer> zzez = new HashMap<>();
    private static final HashMap<String, Long> zzfa = new HashMap<>();
    private static final HashMap<String, Float> zzfb = new HashMap<>();
    private static String[] zzfe = new String[0];

    public static String zza(ContentResolver contentResolver, String str, String str2) {
        synchronized (zzal.class) {
            if (zzex == null) {
                zzew.set(false);
                zzex = new HashMap<>();
                zzfc = new Object();
                zzfd = false;
                contentResolver.registerContentObserver(CONTENT_URI, true, new zzao(null));
            } else if (zzew.getAndSet(false)) {
                zzex.clear();
                zzey.clear();
                zzez.clear();
                zzfa.clear();
                zzfb.clear();
                zzfc = new Object();
                zzfd = false;
            }
            Object obj = zzfc;
            if (zzex.containsKey(str)) {
                String str3 = zzex.get(str);
                return str3 != null ? str3 : null;
            }
            for (String str4 : zzfe) {
                if (str.startsWith(str4)) {
                    if (!zzfd || zzex.isEmpty()) {
                        zzex.putAll(zza(contentResolver, zzfe));
                        zzfd = true;
                        if (zzex.containsKey(str)) {
                            String str5 = zzex.get(str);
                            return str5 != null ? str5 : null;
                        }
                    }
                    return null;
                }
            }
            Cursor query = contentResolver.query(CONTENT_URI, null, null, new String[]{str}, null);
            if (query == null) {
                return null;
            }
            try {
                if (!query.moveToFirst()) {
                    zza(obj, str, (String) null);
                    if (query != null) {
                        query.close();
                    }
                    return null;
                }
                String string = query.getString(1);
                if (string != null && string.equals(null)) {
                    string = null;
                }
                zza(obj, str, string);
                String str6 = string != null ? string : null;
                if (query != null) {
                    query.close();
                }
                return str6;
            } finally {
                if (query != null) {
                    query.close();
                }
            }
        }
    }

    private static void zza(Object obj, String str, String str2) {
        synchronized (zzal.class) {
            if (obj == zzfc) {
                zzex.put(str, str2);
            }
        }
    }

    private static Map<String, String> zza(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(zzet, null, null, strArr, null);
        TreeMap treeMap = new TreeMap();
        if (query == null) {
            return treeMap;
        }
        while (query.moveToNext()) {
            try {
                treeMap.put(query.getString(0), query.getString(1));
            } finally {
                query.close();
            }
        }
        return treeMap;
    }
}
