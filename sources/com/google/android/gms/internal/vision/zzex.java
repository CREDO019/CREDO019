package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
abstract class zzex<E> extends AbstractList<E> implements zzgz<E> {
    private boolean zzrj = true;

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            if (!(obj instanceof RandomAccess)) {
                return super.equals(obj);
            }
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

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        zzdq();
        return super.add(e);
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int r1, E e) {
        zzdq();
        super.add(r1, e);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> collection) {
        zzdq();
        return super.addAll(collection);
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int r1, Collection<? extends E> collection) {
        zzdq();
        return super.addAll(r1, collection);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        zzdq();
        super.clear();
    }

    @Override // com.google.android.gms.internal.vision.zzgz
    public boolean zzdo() {
        return this.zzrj;
    }

    @Override // com.google.android.gms.internal.vision.zzgz
    public final void zzdp() {
        this.zzrj = false;
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int r1) {
        zzdq();
        return (E) super.remove(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        zzdq();
        return super.remove(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<?> collection) {
        zzdq();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<?> collection) {
        zzdq();
        return super.retainAll(collection);
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int r1, E e) {
        zzdq();
        return (E) super.set(r1, e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzdq() {
        if (!this.zzrj) {
            throw new UnsupportedOperationException();
        }
    }
}
