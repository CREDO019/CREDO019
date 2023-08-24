package com.google.android.gms.internal.clearcut;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzeb<E> extends zzav<E> {
    private static final zzeb<Object> zznf;
    private final List<E> zzls;

    static {
        zzeb<Object> zzebVar = new zzeb<>();
        zznf = zzebVar;
        zzebVar.zzv();
    }

    zzeb() {
        this(new ArrayList(10));
    }

    private zzeb(List<E> list) {
        this.zzls = list;
    }

    public static <E> zzeb<E> zzcn() {
        return (zzeb<E>) zznf;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final void add(int r2, E e) {
        zzw();
        this.zzls.add(r2, e);
        this.modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int r2) {
        return this.zzls.get(r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final E remove(int r2) {
        zzw();
        E remove = this.zzls.remove(r2);
        this.modCount++;
        return remove;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final E set(int r2, E e) {
        zzw();
        E e2 = this.zzls.set(r2, e);
        this.modCount++;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzls.size();
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn zzi(int r2) {
        if (r2 >= size()) {
            ArrayList arrayList = new ArrayList(r2);
            arrayList.addAll(this.zzls);
            return new zzeb(arrayList);
        }
        throw new IllegalArgumentException();
    }
}
