package com.google.android.gms.internal.vision;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public class zziw<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private volatile zzix zzaaa;
    private boolean zztc;
    private final int zzzv;
    private List<zzjb> zzzw;
    private Map<K, V> zzzx;
    private volatile zzjd zzzy;
    private Map<K, V> zzzz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <FieldDescriptorType extends zzgk<FieldDescriptorType>> zziw<FieldDescriptorType, Object> zzbt(int r1) {
        return new zziv(r1);
    }

    private zziw(int r1) {
        this.zzzv = r1;
        this.zzzw = Collections.emptyList();
        this.zzzx = Collections.emptyMap();
        this.zzzz = Collections.emptyMap();
    }

    public void zzdp() {
        Map<K, V> unmodifiableMap;
        Map<K, V> unmodifiableMap2;
        if (this.zztc) {
            return;
        }
        if (this.zzzx.isEmpty()) {
            unmodifiableMap = Collections.emptyMap();
        } else {
            unmodifiableMap = Collections.unmodifiableMap(this.zzzx);
        }
        this.zzzx = unmodifiableMap;
        if (this.zzzz.isEmpty()) {
            unmodifiableMap2 = Collections.emptyMap();
        } else {
            unmodifiableMap2 = Collections.unmodifiableMap(this.zzzz);
        }
        this.zzzz = unmodifiableMap2;
        this.zztc = true;
    }

    public final boolean isImmutable() {
        return this.zztc;
    }

    public final int zzhx() {
        return this.zzzw.size();
    }

    public final Map.Entry<K, V> zzbu(int r2) {
        return this.zzzw.get(r2);
    }

    public final Iterable<Map.Entry<K, V>> zzhy() {
        if (this.zzzx.isEmpty()) {
            return zzja.zzid();
        }
        return this.zzzx.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.zzzw.size() + this.zzzx.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza((zziw<K, V>) comparable) >= 0 || this.zzzx.containsKey(comparable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza((zziw<K, V>) comparable);
        if (zza >= 0) {
            return (V) this.zzzw.get(zza).getValue();
        }
        return this.zzzx.get(comparable);
    }

    public final V zza(K k, V v) {
        zzia();
        int zza = zza((zziw<K, V>) k);
        if (zza >= 0) {
            return (V) this.zzzw.get(zza).setValue(v);
        }
        zzia();
        if (this.zzzw.isEmpty() && !(this.zzzw instanceof ArrayList)) {
            this.zzzw = new ArrayList(this.zzzv);
        }
        int r0 = -(zza + 1);
        if (r0 >= this.zzzv) {
            return zzib().put(k, v);
        }
        int size = this.zzzw.size();
        int r2 = this.zzzv;
        if (size == r2) {
            zzjb remove = this.zzzw.remove(r2 - 1);
            zzib().put((K) remove.getKey(), (V) remove.getValue());
        }
        this.zzzw.add(r0, new zzjb(this, k, v));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        zzia();
        if (!this.zzzw.isEmpty()) {
            this.zzzw.clear();
        }
        if (this.zzzx.isEmpty()) {
            return;
        }
        this.zzzx.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        zzia();
        Comparable comparable = (Comparable) obj;
        int zza = zza((zziw<K, V>) comparable);
        if (zza >= 0) {
            return (V) zzbv(zza);
        }
        if (this.zzzx.isEmpty()) {
            return null;
        }
        return this.zzzx.remove(comparable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V zzbv(int r5) {
        zzia();
        V v = (V) this.zzzw.remove(r5).getValue();
        if (!this.zzzx.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = zzib().entrySet().iterator();
            this.zzzw.add(new zzjb(this, it.next()));
            it.remove();
        }
        return v;
    }

    private final int zza(K k) {
        int size = this.zzzw.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) this.zzzw.get(size).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int r1 = 0;
        while (r1 <= size) {
            int r2 = (r1 + size) / 2;
            int compareTo2 = k.compareTo((Comparable) this.zzzw.get(r2).getKey());
            if (compareTo2 < 0) {
                size = r2 - 1;
            } else if (compareTo2 <= 0) {
                return r2;
            } else {
                r1 = r2 + 1;
            }
        }
        return -(r1 + 1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zzzy == null) {
            this.zzzy = new zzjd(this, null);
        }
        return this.zzzy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set<Map.Entry<K, V>> zzhz() {
        if (this.zzaaa == null) {
            this.zzaaa = new zzix(this, null);
        }
        return this.zzaaa;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzia() {
        if (this.zztc) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzib() {
        zzia();
        if (this.zzzx.isEmpty() && !(this.zzzx instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzzx = treeMap;
            this.zzzz = treeMap.descendingMap();
        }
        return (SortedMap) this.zzzx;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zziw)) {
            return super.equals(obj);
        }
        zziw zziwVar = (zziw) obj;
        int size = size();
        if (size != zziwVar.size()) {
            return false;
        }
        int zzhx = zzhx();
        if (zzhx != zziwVar.zzhx()) {
            return entrySet().equals(zziwVar.entrySet());
        }
        for (int r4 = 0; r4 < zzhx; r4++) {
            if (!zzbu(r4).equals(zziwVar.zzbu(r4))) {
                return false;
            }
        }
        if (zzhx != size) {
            return this.zzzx.equals(zziwVar.zzzx);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int zzhx = zzhx();
        int r2 = 0;
        for (int r1 = 0; r1 < zzhx; r1++) {
            r2 += this.zzzw.get(r1).hashCode();
        }
        return this.zzzx.size() > 0 ? r2 + this.zzzx.hashCode() : r2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    public /* synthetic */ Object put(Object obj, Object obj2) {
        return zza((zziw<K, V>) ((Comparable) obj), (Comparable) obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zziw(int r1, zziv zzivVar) {
        this(r1);
    }
}
