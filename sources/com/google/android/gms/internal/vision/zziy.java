package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zziy<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zziw zzaab;
    private Iterator<Map.Entry<K, V>> zzaac;

    private zziy(zziw zziwVar) {
        List list;
        this.zzaab = zziwVar;
        list = zziwVar.zzzw;
        this.pos = list.size();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        int r0 = this.pos;
        if (r0 > 0) {
            list = this.zzaab.zzzw;
            if (r0 <= list.size()) {
                return true;
            }
        }
        return zzic().hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Map.Entry<K, V>> zzic() {
        Map map;
        if (this.zzaac == null) {
            map = this.zzaab.zzzz;
            this.zzaac = map.entrySet().iterator();
        }
        return this.zzaac;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        List list;
        if (zzic().hasNext()) {
            return zzic().next();
        }
        list = this.zzaab.zzzw;
        int r1 = this.pos - 1;
        this.pos = r1;
        return (Map.Entry) list.get(r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zziy(zziw zziwVar, zziv zzivVar) {
        this(zziwVar);
    }
}
