package com.google.android.gms.internal.clearcut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
public final class zzcw extends zzav<String> implements zzcx, RandomAccess {
    private static final zzcw zzlq;
    private static final zzcx zzlr;
    private final List<Object> zzls;

    static {
        zzcw zzcwVar = new zzcw();
        zzlq = zzcwVar;
        zzcwVar.zzv();
        zzlr = zzcwVar;
    }

    public zzcw() {
        this(10);
    }

    public zzcw(int r2) {
        this(new ArrayList(r2));
    }

    private zzcw(ArrayList<Object> arrayList) {
        this.zzls = arrayList;
    }

    private static String zze(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzbb ? ((zzbb) obj).zzz() : zzci.zzf((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r2, Object obj) {
        zzw();
        this.zzls.add(r2, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final boolean addAll(int r2, Collection<? extends String> collection) {
        zzw();
        if (collection instanceof zzcx) {
            collection = ((zzcx) collection).zzbt();
        }
        boolean addAll = this.zzls.addAll(r2, collection);
        this.modCount++;
        return addAll;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzw();
        this.zzls.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r3) {
        Object obj = this.zzls.get(r3);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzbb) {
            zzbb zzbbVar = (zzbb) obj;
            String zzz = zzbbVar.zzz();
            if (zzbbVar.zzaa()) {
                this.zzls.set(r3, zzz);
            }
            return zzz;
        }
        byte[] bArr = (byte[]) obj;
        String zzf = zzci.zzf(bArr);
        if (zzci.zze(bArr)) {
            this.zzls.set(r3, zzf);
        }
        return zzf;
    }

    @Override // com.google.android.gms.internal.clearcut.zzcx
    public final Object getRaw(int r2) {
        return this.zzls.get(r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r2) {
        zzw();
        Object remove = this.zzls.remove(r2);
        this.modCount++;
        return zze(remove);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int r2, Object obj) {
        zzw();
        return zze(this.zzls.set(r2, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzls.size();
    }

    @Override // com.google.android.gms.internal.clearcut.zzcx
    public final List<?> zzbt() {
        return Collections.unmodifiableList(this.zzls);
    }

    @Override // com.google.android.gms.internal.clearcut.zzcx
    public final zzcx zzbu() {
        return zzu() ? new zzfa(this) : this;
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn zzi(int r2) {
        if (r2 >= size()) {
            ArrayList arrayList = new ArrayList(r2);
            arrayList.addAll(this.zzls);
            return new zzcw(arrayList);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, com.google.android.gms.internal.clearcut.zzcn
    public final /* bridge */ /* synthetic */ boolean zzu() {
        return super.zzu();
    }
}
