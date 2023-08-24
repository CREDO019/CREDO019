package com.google.android.gms.internal.vision;

import java.util.ListIterator;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzjn implements ListIterator<String> {
    private ListIterator<String> zzaal;
    private final /* synthetic */ int zzaam;
    private final /* synthetic */ zzjo zzaan;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjn(zzjo zzjoVar, int r2) {
        zzhj zzhjVar;
        this.zzaan = zzjoVar;
        this.zzaam = r2;
        zzhjVar = zzjoVar.zzaao;
        this.zzaal = zzhjVar.listIterator(r2);
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.zzaal.hasNext();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.zzaal.hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.zzaal.nextIndex();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.zzaal.previousIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void add(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ void set(String str) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public final /* synthetic */ String previous() {
        return this.zzaal.previous();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final /* synthetic */ Object next() {
        return this.zzaal.next();
    }
}
