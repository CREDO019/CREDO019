package com.google.android.datatransport.runtime.dagger.internal;

import java.util.Collections;
import java.util.Map;

/* loaded from: classes2.dex */
public final class MapBuilder<K, V> {
    private final Map<K, V> contributions;

    private MapBuilder(int r1) {
        this.contributions = DaggerCollections.newLinkedHashMapWithExpectedSize(r1);
    }

    public static <K, V> MapBuilder<K, V> newMapBuilder(int r1) {
        return new MapBuilder<>(r1);
    }

    public MapBuilder<K, V> put(K k, V v) {
        this.contributions.put(k, v);
        return this;
    }

    public MapBuilder<K, V> putAll(Map<K, V> map) {
        this.contributions.putAll(map);
        return this;
    }

    public Map<K, V> build() {
        if (this.contributions.size() == 0) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(this.contributions);
    }
}
