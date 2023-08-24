package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgrm extends AbstractList implements RandomAccess, zzgpf {
    private final zzgpf zza;

    public zzgrm(zzgpf zzgpfVar) {
        this.zza = zzgpfVar;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object get(int r2) {
        return ((zzgpe) this.zza).get(r2);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new zzgrl(this);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int r2) {
        return new zzgrk(this, r2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.ads.zzgpf
    public final zzgpf zze() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzgpf
    public final Object zzf(int r2) {
        return this.zza.zzf(r2);
    }

    @Override // com.google.android.gms.internal.ads.zzgpf
    public final List zzh() {
        return this.zza.zzh();
    }

    @Override // com.google.android.gms.internal.ads.zzgpf
    public final void zzi(zzgnf zzgnfVar) {
        throw new UnsupportedOperationException();
    }
}
