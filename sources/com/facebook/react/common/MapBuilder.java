package com.facebook.react.common;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class MapBuilder {
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1262of() {
        return newHashMap();
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1261of(K k, V v) {
        Map<K, V> m1262of = m1262of();
        m1262of.put(k, v);
        return m1262of;
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1260of(K k, V v, K k2, V v2) {
        Map<K, V> m1262of = m1262of();
        m1262of.put(k, v);
        m1262of.put(k2, v2);
        return m1262of;
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1259of(K k, V v, K k2, V v2, K k3, V v3) {
        Map<K, V> m1262of = m1262of();
        m1262of.put(k, v);
        m1262of.put(k2, v2);
        m1262of.put(k3, v3);
        return m1262of;
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1258of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map<K, V> m1262of = m1262of();
        m1262of.put(k, v);
        m1262of.put(k2, v2);
        m1262of.put(k3, v3);
        m1262of.put(k4, v4);
        return m1262of;
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1257of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Map<K, V> m1262of = m1262of();
        m1262of.put(k, v);
        m1262of.put(k2, v2);
        m1262of.put(k3, v3);
        m1262of.put(k4, v4);
        m1262of.put(k5, v5);
        return m1262of;
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1256of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) {
        Map<K, V> m1262of = m1262of();
        m1262of.put(k, v);
        m1262of.put(k2, v2);
        m1262of.put(k3, v3);
        m1262of.put(k4, v4);
        m1262of.put(k5, v5);
        m1262of.put(k6, v6);
        return m1262of;
    }

    /* renamed from: of */
    public static <K, V> Map<K, V> m1255of(K k, V v, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) {
        Map<K, V> m1262of = m1262of();
        m1262of.put(k, v);
        m1262of.put(k2, v2);
        m1262of.put(k3, v3);
        m1262of.put(k4, v4);
        m1262of.put(k5, v5);
        m1262of.put(k6, v6);
        m1262of.put(k7, v7);
        return m1262of;
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    /* loaded from: classes.dex */
    public static final class Builder<K, V> {
        private Map mMap;
        private boolean mUnderConstruction;

        private Builder() {
            this.mMap = MapBuilder.newHashMap();
            this.mUnderConstruction = true;
        }

        public Builder<K, V> put(K k, V v) {
            if (!this.mUnderConstruction) {
                throw new IllegalStateException("Underlying map has already been built");
            }
            this.mMap.put(k, v);
            return this;
        }

        public Map<K, V> build() {
            if (!this.mUnderConstruction) {
                throw new IllegalStateException("Underlying map has already been built");
            }
            this.mUnderConstruction = false;
            return this.mMap;
        }
    }
}
