package com.google.android.play.core.common;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.google.android.play.core.common.a */
/* loaded from: classes3.dex */
public final class C2483a {

    /* renamed from: a */
    private final Map<String, Object> f793a = new HashMap();

    /* renamed from: a */
    public final synchronized void m820a(Bundle bundle) {
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null && this.f793a.get(str) == null) {
                this.f793a.put(str, obj);
            }
        }
    }

    /* renamed from: a */
    public final synchronized boolean m821a() {
        Object obj = this.f793a.get("usingExtractorStream");
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }
}
