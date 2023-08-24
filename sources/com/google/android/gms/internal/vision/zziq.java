package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zziq<E> extends zzex<E> implements RandomAccess {
    private static final zziq<Object> zzzq;
    private int size;
    private E[] zzlu;

    public static <E> zziq<E> zzhr() {
        return (zziq<E>) zzzq;
    }

    zziq() {
        this(new Object[10], 0);
    }

    private zziq(E[] eArr, int r2) {
        this.zzlu = eArr;
        this.size = r2;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(E e) {
        zzdq();
        int r0 = this.size;
        E[] eArr = this.zzlu;
        if (r0 == eArr.length) {
            this.zzlu = (E[]) Arrays.copyOf(eArr, ((r0 * 3) / 2) + 1);
        }
        E[] eArr2 = this.zzlu;
        int r1 = this.size;
        this.size = r1 + 1;
        eArr2[r1] = e;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final void add(int r5, E e) {
        int r0;
        zzdq();
        if (r5 < 0 || r5 > (r0 = this.size)) {
            throw new IndexOutOfBoundsException(zzaf(r5));
        }
        E[] eArr = this.zzlu;
        if (r0 < eArr.length) {
            System.arraycopy(eArr, r5, eArr, r5 + 1, r0 - r5);
        } else {
            E[] eArr2 = (E[]) new Object[((r0 * 3) / 2) + 1];
            System.arraycopy(eArr, 0, eArr2, 0, r5);
            System.arraycopy(this.zzlu, r5, eArr2, r5 + 1, this.size - r5);
            this.zzlu = eArr2;
        }
        this.zzlu[r5] = e;
        this.size++;
        this.modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final E get(int r2) {
        zzae(r2);
        return this.zzlu[r2];
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final E remove(int r5) {
        int r2;
        zzdq();
        zzae(r5);
        E[] eArr = this.zzlu;
        E e = eArr[r5];
        if (r5 < this.size - 1) {
            System.arraycopy(eArr, r5 + 1, eArr, r5, (r2 - r5) - 1);
        }
        this.size--;
        this.modCount++;
        return e;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final E set(int r3, E e) {
        zzdq();
        zzae(r3);
        E[] eArr = this.zzlu;
        E e2 = eArr[r3];
        eArr[r3] = e;
        this.modCount++;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    private final void zzae(int r2) {
        if (r2 < 0 || r2 >= this.size) {
            throw new IndexOutOfBoundsException(zzaf(r2));
        }
    }

    private final String zzaf(int r4) {
        int r0 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(r4);
        sb.append(", Size:");
        sb.append(r0);
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.vision.zzgz
    public final /* synthetic */ zzgz zzag(int r3) {
        if (r3 < this.size) {
            throw new IllegalArgumentException();
        }
        return new zziq(Arrays.copyOf(this.zzlu, r3), this.size);
    }

    static {
        zziq<Object> zziqVar = new zziq<>(new Object[0], 0);
        zzzq = zziqVar;
        zziqVar.zzdp();
    }
}
