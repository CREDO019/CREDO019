package com.google.android.gms.internal.vision;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzjo extends AbstractList<String> implements zzhj, RandomAccess {
    private final zzhj zzaao;

    public zzjo(zzhj zzhjVar) {
        this.zzaao = zzhjVar;
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final zzhj zzgy() {
        return this;
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final Object getRaw(int r2) {
        return this.zzaao.getRaw(r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzaao.size();
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final void zzc(zzfh zzfhVar) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int r2) {
        return new zzjn(this, r2);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new zzjq(this);
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final List<?> zzgx() {
        return this.zzaao.zzgx();
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r2) {
        return (String) this.zzaao.get(r2);
    }
}
