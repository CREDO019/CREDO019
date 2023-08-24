package com.google.android.play.core.assetpacks;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.google.android.play.core.assetpacks.bz */
/* loaded from: classes3.dex */
public final class C2406bz {

    /* renamed from: a */
    private final Map<String, Double> f580a = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized double m948a(String str, C2425cr c2425cr) {
        double d;
        d = (((C2399bs) c2425cr).f539e + 1.0d) / ((C2399bs) c2425cr).f540f;
        this.f580a.put(str, Double.valueOf(d));
        return d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void m949a(String str) {
        this.f580a.put(str, Double.valueOf(0.0d));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final synchronized double m947b(String str) {
        Double d = this.f580a.get(str);
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }
}
