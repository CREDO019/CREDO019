package com.google.android.gms.internal.vision;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzhf<K> implements Map.Entry<K, Object> {
    private Map.Entry<K, zzhd> zzxw;

    private zzhf(Map.Entry<K, zzhd> entry) {
        this.zzxw = entry;
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        return this.zzxw.getKey();
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this.zzxw.getValue() == null) {
            return null;
        }
        return zzhd.zzgu();
    }

    public final zzhd zzgw() {
        return this.zzxw.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (!(obj instanceof zzic)) {
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        }
        return this.zzxw.getValue().zzi((zzic) obj);
    }
}
