package com.google.android.play.core.splitinstall;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.play.core.splitinstall.g */
/* loaded from: classes3.dex */
public final class C2642g {

    /* renamed from: a */
    private final Map<String, Map<String, String>> f996a = new HashMap();

    /* renamed from: a */
    public final C2643h m529a() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, Map<String, String>> entry : this.f996a.entrySet()) {
            hashMap.put(entry.getKey(), Collections.unmodifiableMap(new HashMap(entry.getValue())));
        }
        return new C2643h(Collections.unmodifiableMap(hashMap));
    }

    /* renamed from: a */
    public final void m528a(String str, String str2, String str3) {
        if (!this.f996a.containsKey(str2)) {
            this.f996a.put(str2, new HashMap());
        }
        this.f996a.get(str2).put(str, str3);
    }
}
