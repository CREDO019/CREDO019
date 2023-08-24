package com.google.android.gms.internal.ads;

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
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzgrd extends AbstractMap {
    private final int zza;
    private boolean zzd;
    private volatile zzgrb zze;
    private List zzb = Collections.emptyList();
    private Map zzc = Collections.emptyMap();
    private Map zzf = Collections.emptyMap();

    private final int zzk(Comparable comparable) {
        int size = this.zzb.size() - 1;
        int r1 = 0;
        if (size >= 0) {
            int compareTo = comparable.compareTo(((zzgqx) this.zzb.get(size)).zza());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        while (r1 <= size) {
            int r2 = (r1 + size) / 2;
            int compareTo2 = comparable.compareTo(((zzgqx) this.zzb.get(r2)).zza());
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

    /* JADX INFO: Access modifiers changed from: private */
    public final Object zzl(int r6) {
        zzn();
        Object value = ((zzgqx) this.zzb.remove(r6)).getValue();
        if (!this.zzc.isEmpty()) {
            Iterator it = zzm().entrySet().iterator();
            List list = this.zzb;
            Map.Entry entry = (Map.Entry) it.next();
            list.add(new zzgqx(this, (Comparable) entry.getKey(), entry.getValue()));
            it.remove();
        }
        return value;
    }

    private final SortedMap zzm() {
        zzn();
        if (this.zzc.isEmpty() && !(this.zzc instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.zzc = treeMap;
            this.zzf = treeMap.descendingMap();
        }
        return (SortedMap) this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzn() {
        if (this.zzd) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        zzn();
        if (!this.zzb.isEmpty()) {
            this.zzb.clear();
        }
        if (this.zzc.isEmpty()) {
            return;
        }
        this.zzc.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zzk(comparable) >= 0 || this.zzc.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.zze == null) {
            this.zze = new zzgrb(this, null);
        }
        return this.zze;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgrd)) {
            return super.equals(obj);
        }
        zzgrd zzgrdVar = (zzgrd) obj;
        int size = size();
        if (size != zzgrdVar.size()) {
            return false;
        }
        int zzb = zzb();
        if (zzb == zzgrdVar.zzb()) {
            for (int r4 = 0; r4 < zzb; r4++) {
                if (!zzg(r4).equals(zzgrdVar.zzg(r4))) {
                    return false;
                }
            }
            if (zzb != size) {
                return this.zzc.equals(zzgrdVar.zzc);
            }
            return true;
        }
        return entrySet().equals(zzgrdVar.entrySet());
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zzk = zzk(comparable);
        if (zzk >= 0) {
            return ((zzgqx) this.zzb.get(zzk)).getValue();
        }
        return this.zzc.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int zzb = zzb();
        int r2 = 0;
        for (int r1 = 0; r1 < zzb; r1++) {
            r2 += ((zzgqx) this.zzb.get(r1)).hashCode();
        }
        return this.zzc.size() > 0 ? r2 + this.zzc.hashCode() : r2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        zzn();
        Comparable comparable = (Comparable) obj;
        int zzk = zzk(comparable);
        if (zzk >= 0) {
            return zzl(zzk);
        }
        if (this.zzc.isEmpty()) {
            return null;
        }
        return this.zzc.remove(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.zzb.size() + this.zzc.size();
    }

    public void zza() {
        Map unmodifiableMap;
        Map unmodifiableMap2;
        if (this.zzd) {
            return;
        }
        if (this.zzc.isEmpty()) {
            unmodifiableMap = Collections.emptyMap();
        } else {
            unmodifiableMap = Collections.unmodifiableMap(this.zzc);
        }
        this.zzc = unmodifiableMap;
        if (this.zzf.isEmpty()) {
            unmodifiableMap2 = Collections.emptyMap();
        } else {
            unmodifiableMap2 = Collections.unmodifiableMap(this.zzf);
        }
        this.zzf = unmodifiableMap2;
        this.zzd = true;
    }

    public final int zzb() {
        return this.zzb.size();
    }

    public final Iterable zzc() {
        return this.zzc.isEmpty() ? zzgqw.zza() : this.zzc.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* renamed from: zze */
    public final Object put(Comparable comparable, Object obj) {
        zzn();
        int zzk = zzk(comparable);
        if (zzk >= 0) {
            return ((zzgqx) this.zzb.get(zzk)).setValue(obj);
        }
        zzn();
        if (this.zzb.isEmpty() && !(this.zzb instanceof ArrayList)) {
            this.zzb = new ArrayList(this.zza);
        }
        int r0 = -(zzk + 1);
        if (r0 >= this.zza) {
            return zzm().put(comparable, obj);
        }
        int size = this.zzb.size();
        int r2 = this.zza;
        if (size == r2) {
            zzgqx zzgqxVar = (zzgqx) this.zzb.remove(r2 - 1);
            zzm().put(zzgqxVar.zza(), zzgqxVar.getValue());
        }
        this.zzb.add(r0, new zzgqx(this, comparable, obj));
        return null;
    }

    public final Map.Entry zzg(int r2) {
        return (Map.Entry) this.zzb.get(r2);
    }

    public final boolean zzj() {
        return this.zzd;
    }
}
