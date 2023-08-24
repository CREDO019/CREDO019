package com.google.android.gms.internal.clearcut;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* loaded from: classes2.dex */
final class zzeq<K, V> implements Iterator<Map.Entry<K, V>> {
    private int pos;
    private Iterator<Map.Entry<K, V>> zzor;
    private final /* synthetic */ zzei zzos;
    private boolean zzow;

    private zzeq(zzei zzeiVar) {
        this.zzos = zzeiVar;
        this.pos = -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzeq(zzei zzeiVar, zzej zzejVar) {
        this(zzeiVar);
    }

    private final Iterator<Map.Entry<K, V>> zzdw() {
        Map map;
        if (this.zzor == null) {
            map = this.zzos.zzon;
            this.zzor = map.entrySet().iterator();
        }
        return this.zzor;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        Map map;
        int r0 = this.pos + 1;
        list = this.zzos.zzom;
        if (r0 >= list.size()) {
            map = this.zzos.zzon;
            if (map.isEmpty() || !zzdw().hasNext()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Iterator
    public final /* synthetic */ Object next() {
        List list;
        Map.Entry<K, V> next;
        List list2;
        this.zzow = true;
        int r1 = this.pos + 1;
        this.pos = r1;
        list = this.zzos.zzom;
        if (r1 < list.size()) {
            list2 = this.zzos.zzom;
            next = (Map.Entry<K, V>) list2.get(this.pos);
        } else {
            next = zzdw().next();
        }
        return next;
    }

    @Override // java.util.Iterator
    public final void remove() {
        List list;
        if (!this.zzow) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzow = false;
        this.zzos.zzdu();
        int r0 = this.pos;
        list = this.zzos.zzom;
        if (r0 >= list.size()) {
            zzdw().remove();
            return;
        }
        zzei zzeiVar = this.zzos;
        int r1 = this.pos;
        this.pos = r1 - 1;
        zzeiVar.zzal(r1);
    }
}
