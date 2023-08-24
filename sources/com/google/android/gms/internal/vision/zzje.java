package com.google.android.gms.internal.vision;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzje<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private final /* synthetic */ zziw zzaab;
    private Iterator<Map.Entry<K, V>> zzaac;
    private boolean zzaag;

    private zzje(zziw zziwVar) {
        this.zzaab = zziwVar;
        this.pos = -1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        Map map;
        int r0 = this.pos + 1;
        list = this.zzaab.zzzw;
        if (r0 >= list.size()) {
            map = this.zzaab.zzzx;
            if (map.isEmpty() || !zzic().hasNext()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Iterator
    public final void remove() {
        List list;
        if (!this.zzaag) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzaag = false;
        this.zzaab.zzia();
        int r0 = this.pos;
        list = this.zzaab.zzzw;
        if (r0 < list.size()) {
            zziw zziwVar = this.zzaab;
            int r1 = this.pos;
            this.pos = r1 - 1;
            zziwVar.zzbv(r1);
            return;
        }
        zzic().remove();
    }

    private final Iterator<Map.Entry<K, V>> zzic() {
        Map map;
        if (this.zzaac == null) {
            map = this.zzaab.zzzx;
            this.zzaac = map.entrySet().iterator();
        }
        return this.zzaac;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        List list;
        List list2;
        this.zzaag = true;
        int r1 = this.pos + 1;
        this.pos = r1;
        list = this.zzaab.zzzw;
        if (r1 >= list.size()) {
            return zzic().next();
        }
        list2 = this.zzaab.zzzw;
        return (Map.Entry) list2.get(this.pos);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzje(zziw zziwVar, zziv zzivVar) {
        this(zziwVar);
    }
}
