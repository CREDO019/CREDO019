package com.google.android.gms.internal.clearcut;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
abstract class zzav<E> extends AbstractList<E> implements zzcn<E> {
    private boolean zzfa = true;

    @Override // java.util.AbstractList, java.util.List
    public void add(int r1, E e) {
        zzw();
        super.add(r1, e);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        zzw();
        return super.add(e);
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int r1, Collection<? extends E> collection) {
        zzw();
        return super.addAll(r1, collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> collection) {
        zzw();
        return super.addAll(collection);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        zzw();
        super.clear();
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            if (obj instanceof RandomAccess) {
                List list = (List) obj;
                int size = size();
                if (size != list.size()) {
                    return false;
                }
                for (int r3 = 0; r3 < size; r3++) {
                    if (!get(r3).equals(list.get(r3))) {
                        return false;
                    }
                }
                return true;
            }
            return super.equals(obj);
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int size = size();
        int r1 = 1;
        for (int r2 = 0; r2 < size; r2++) {
            r1 = (r1 * 31) + get(r2).hashCode();
        }
        return r1;
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int r1) {
        zzw();
        return (E) super.remove(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        zzw();
        return super.remove(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<?> collection) {
        zzw();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<?> collection) {
        zzw();
        return super.retainAll(collection);
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int r1, E e) {
        zzw();
        return (E) super.set(r1, e);
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public boolean zzu() {
        return this.zzfa;
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final void zzv() {
        this.zzfa = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzw() {
        if (!this.zzfa) {
            throw new UnsupportedOperationException();
        }
    }
}
