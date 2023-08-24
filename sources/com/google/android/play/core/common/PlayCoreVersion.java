package com.google.android.play.core.common;

import android.os.Bundle;
import com.google.android.play.core.internal.C2494af;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class PlayCoreVersion {

    /* renamed from: a */
    private static final Set<String> f789a = new HashSet(Arrays.asList("review"));

    /* renamed from: b */
    private static final Set<String> f790b = new HashSet(Arrays.asList("native", "unity"));

    /* renamed from: c */
    private static final Map<String, Map<String, Integer>> f791c = new HashMap();

    /* renamed from: d */
    private static final C2494af f792d = new C2494af("PlayCoreVersion");

    private PlayCoreVersion() {
    }

    /* renamed from: a */
    public static Bundle m823a() {
        Bundle bundle = new Bundle();
        Map<String, Integer> m822a = m822a("review");
        bundle.putInt("playcore_version_code", m822a.get("java").intValue());
        if (m822a.containsKey("native")) {
            bundle.putInt("playcore_native_version", m822a.get("native").intValue());
        }
        if (m822a.containsKey("unity")) {
            bundle.putInt("playcore_unity_version", m822a.get("unity").intValue());
        }
        return bundle;
    }

    /* renamed from: a */
    public static synchronized Map<String, Integer> m822a(String str) {
        Map<String, Integer> map;
        synchronized (PlayCoreVersion.class) {
            Map<String, Map<String, Integer>> map2 = f791c;
            if (!map2.containsKey(str)) {
                HashMap hashMap = new HashMap();
                hashMap.put("java", 10800);
                map2.put(str, hashMap);
            }
            map = map2.get(str);
        }
        return map;
    }

    public static synchronized void addVersion(String str, String str2, int r6) {
        synchronized (PlayCoreVersion.class) {
            if (!f789a.contains(str)) {
                f792d.m804d("Illegal module name: %s", str);
            } else if (f790b.contains(str2)) {
                m822a(str).put(str2, Integer.valueOf(r6));
            } else {
                f792d.m804d("Illegal platform name: %s", str2);
            }
        }
    }
}
