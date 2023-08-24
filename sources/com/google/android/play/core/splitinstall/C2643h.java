package com.google.android.play.core.splitinstall;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.android.play.core.splitinstall.h */
/* loaded from: classes3.dex */
public final class C2643h {

    /* renamed from: a */
    private final Map<String, Map<String, String>> f997a;

    /* renamed from: a */
    public final Map<String, Set<String>> m527a(Collection<String> collection) {
        Set unmodifiableSet;
        HashMap hashMap = new HashMap();
        for (String str : this.f997a.keySet()) {
            if (this.f997a.containsKey(str)) {
                HashSet hashSet = new HashSet();
                for (Map.Entry<String, String> entry : this.f997a.get(str).entrySet()) {
                    if (collection.contains(entry.getKey())) {
                        hashSet.add(entry.getValue());
                    }
                }
                unmodifiableSet = Collections.unmodifiableSet(hashSet);
            } else {
                unmodifiableSet = Collections.emptySet();
            }
            hashMap.put(str, unmodifiableSet);
        }
        return hashMap;
    }
}
