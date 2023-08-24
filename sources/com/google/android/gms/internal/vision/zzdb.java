package com.google.android.gms.internal.vision;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
abstract class zzdb<E> extends zzdq<E> {
    private int position;
    private final int size;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzdb(int r1, int r2) {
        zzct.zzd(r2, r1);
        this.size = r1;
        this.position = r2;
    }

    protected abstract E get(int r1);

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.position < this.size;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int r0 = this.position;
        this.position = r0 + 1;
        return get(r0);
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.position;
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.position > 0;
    }

    @Override // java.util.ListIterator
    public final E previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int r0 = this.position - 1;
        this.position = r0;
        return get(r0);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.position - 1;
    }
}
