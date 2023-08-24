package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzdk<K, V> extends zzdj<Map.Entry<K, V>> {
    private final transient int size;
    private final transient zzdg<K, V> zzlw;
    private final transient Object[] zzlx;
    private final transient int zzly = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdk(zzdg<K, V> zzdgVar, Object[] objArr, int r3, int r4) {
        this.zzlw = zzdgVar;
        this.zzlx = objArr;
        this.size = r4;
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final zzdr<Map.Entry<K, V>> zzby() {
        return (zzdr) zzcc().iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzdc
    public final int zza(Object[] objArr, int r3) {
        return zzcc().zza(objArr, r3);
    }

    @Override // com.google.android.gms.internal.vision.zzdj
    final zzdf<Map.Entry<K, V>> zzch() {
        return new zzdn(this);
    }

    @Override // com.google.android.gms.internal.vision.zzdc, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value != null && value.equals(this.zzlw.get(key))) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.vision.zzdj, com.google.android.gms.internal.vision.zzdc, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
