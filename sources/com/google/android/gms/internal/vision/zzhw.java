package com.google.android.gms.internal.vision;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzhw<K, V> extends LinkedHashMap<K, V> {
    private static final zzhw zzyp;
    private boolean zzrj;

    private zzhw() {
        this.zzrj = true;
    }

    private zzhw(Map<K, V> map) {
        super(map);
        this.zzrj = true;
    }

    public static <K, V> zzhw<K, V> zzhc() {
        return zzyp;
    }

    public final void zza(zzhw<K, V> zzhwVar) {
        zzhe();
        if (zzhwVar.isEmpty()) {
            return;
        }
        putAll(zzhwVar);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzhe();
        super.clear();
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        zzhe();
        zzgt.checkNotNull(k);
        zzgt.checkNotNull(v);
        return (V) super.put(k, v);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends K, ? extends V> map) {
        zzhe();
        for (K k : map.keySet()) {
            zzgt.checkNotNull(k);
            zzgt.checkNotNull(map.get(k));
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        zzhe();
        return (V) super.remove(obj);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005c A[RETURN] */
    @Override // java.util.AbstractMap, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof java.util.Map
            r1 = 0
            if (r0 == 0) goto L5d
            java.util.Map r7 = (java.util.Map) r7
            r0 = 1
            if (r6 == r7) goto L59
            int r2 = r6.size()
            int r3 = r7.size()
            if (r2 == r3) goto L16
        L14:
            r7 = 0
            goto L5a
        L16:
            java.util.Set r2 = r6.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L1e:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L59
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getKey()
            boolean r4 = r7.containsKey(r4)
            if (r4 != 0) goto L35
            goto L14
        L35:
            java.lang.Object r4 = r3.getValue()
            java.lang.Object r3 = r3.getKey()
            java.lang.Object r3 = r7.get(r3)
            boolean r5 = r4 instanceof byte[]
            if (r5 == 0) goto L52
            boolean r5 = r3 instanceof byte[]
            if (r5 == 0) goto L52
            byte[] r4 = (byte[]) r4
            byte[] r3 = (byte[]) r3
            boolean r3 = java.util.Arrays.equals(r4, r3)
            goto L56
        L52:
            boolean r3 = r4.equals(r3)
        L56:
            if (r3 != 0) goto L1e
            goto L14
        L59:
            r7 = 1
        L5a:
            if (r7 == 0) goto L5d
            return r0
        L5d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.vision.zzhw.equals(java.lang.Object):boolean");
    }

    private static int zzq(Object obj) {
        if (obj instanceof byte[]) {
            return zzgt.hashCode((byte[]) obj);
        }
        if (obj instanceof zzgw) {
            throw new UnsupportedOperationException();
        }
        return obj.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int r1 = 0;
        for (Map.Entry<K, V> entry : entrySet()) {
            r1 += zzq(entry.getValue()) ^ zzq(entry.getKey());
        }
        return r1;
    }

    public final zzhw<K, V> zzhd() {
        return isEmpty() ? new zzhw<>() : new zzhw<>(this);
    }

    public final void zzdp() {
        this.zzrj = false;
    }

    public final boolean isMutable() {
        return this.zzrj;
    }

    private final void zzhe() {
        if (!this.zzrj) {
            throw new UnsupportedOperationException();
        }
    }

    static {
        zzhw zzhwVar = new zzhw();
        zzyp = zzhwVar;
        zzhwVar.zzrj = false;
    }
}
