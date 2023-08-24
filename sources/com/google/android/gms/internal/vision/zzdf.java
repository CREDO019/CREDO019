package com.google.android.gms.internal.vision;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public abstract class zzdf<E> extends zzdc<E> implements List<E>, RandomAccess {
    private static final zzdq<Object> zzln = new zzde(zzdi.zzlt, 0);

    public static <E> zzdf<E> zzcd() {
        return (zzdf<E>) zzdi.zzlt;
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final zzdf<E> zzcc() {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <E> zzdf<E> zza(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return (zzdf<E>) zzdi.zzlt;
        }
        return new zzdi(objArr, length);
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    public final zzdr<E> zzby() {
        return (zzdq) listIterator();
    }

    @Override // java.util.List
    public int indexOf(@NullableDecl Object obj) {
        if (obj == null) {
            return -1;
        }
        if (this instanceof RandomAccess) {
            int size = size();
            int r2 = 0;
            if (obj == null) {
                while (r2 < size) {
                    if (get(r2) == null) {
                        return r2;
                    }
                    r2++;
                }
            } else {
                while (r2 < size) {
                    if (obj.equals(get(r2))) {
                        return r2;
                    }
                    r2++;
                }
            }
            return -1;
        }
        ListIterator<E> listIterator = listIterator();
        while (listIterator.hasNext()) {
            if (zzco.equal(obj, listIterator.next())) {
                return listIterator.previousIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public int lastIndexOf(@NullableDecl Object obj) {
        if (obj == null) {
            return -1;
        }
        if (!(this instanceof RandomAccess)) {
            ListIterator<E> listIterator = listIterator(size());
            while (listIterator.hasPrevious()) {
                if (zzco.equal(obj, listIterator.previous())) {
                    return listIterator.nextIndex();
                }
            }
            return -1;
        }
        if (obj == null) {
            for (int size = size() - 1; size >= 0; size--) {
                if (get(size) == null) {
                    return size;
                }
            }
        } else {
            for (int size2 = size() - 1; size2 >= 0; size2--) {
                if (obj.equals(get(size2))) {
                    return size2;
                }
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.vision.zzdc, java.util.AbstractCollection, java.util.Collection
    public boolean contains(@NullableDecl Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.List
    /* renamed from: zze */
    public zzdf<E> subList(int r2, int r3) {
        zzct.zza(r2, r3, size());
        int r32 = r3 - r2;
        if (r32 == size()) {
            return this;
        }
        if (r32 == 0) {
            return (zzdf<E>) zzdi.zzlt;
        }
        return new zzdh(this, r2, r32);
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int r1, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final E set(int r1, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int r1, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final E remove(int r1) {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzdc
    public int zza(Object[] objArr, int r6) {
        int size = size();
        for (int r1 = 0; r1 < size; r1++) {
            objArr[r6 + r1] = get(r1);
        }
        return r6 + size;
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(@NullableDecl Object obj) {
        if (obj == zzct.checkNotNull(this)) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if ((this instanceof RandomAccess) && (list instanceof RandomAccess)) {
                    for (int r3 = 0; r3 < size; r3++) {
                        if (zzco.equal(get(r3), list.get(r3))) {
                        }
                    }
                    return true;
                }
                int size2 = size();
                Iterator<E> it = list.iterator();
                int r32 = 0;
                while (true) {
                    if (r32 < size2) {
                        if (!it.hasNext()) {
                            break;
                        }
                        E e = get(r32);
                        r32++;
                        if (!zzco.equal(e, it.next())) {
                            break;
                        }
                    } else if (!it.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        int size = size();
        int r1 = 1;
        for (int r2 = 0; r2 < size; r2++) {
            r1 = ~(~((r1 * 31) + get(r2).hashCode()));
        }
        return r1;
    }

    @Override // com.google.android.gms.internal.vision.zzdc, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }

    @Override // java.util.List
    public /* synthetic */ ListIterator listIterator(int r2) {
        zzct.zzd(r2, size());
        if (isEmpty()) {
            return zzln;
        }
        return new zzde(this, r2);
    }

    @Override // java.util.List
    public /* synthetic */ ListIterator listIterator() {
        return (zzdq) listIterator(0);
    }
}
