package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzhk extends zzex<String> implements zzhj, RandomAccess {
    private static final zzhk zzyb;
    private static final zzhj zzyc;
    private final List<Object> zzyd;

    public zzhk() {
        this(10);
    }

    public zzhk(int r2) {
        this(new ArrayList(r2));
    }

    private zzhk(ArrayList<Object> arrayList) {
        this.zzyd = arrayList;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzyd.size();
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final boolean addAll(int r2, Collection<? extends String> collection) {
        zzdq();
        if (collection instanceof zzhj) {
            collection = ((zzhj) collection).zzgx();
        }
        boolean addAll = this.zzyd.addAll(r2, collection);
        this.modCount++;
        return addAll;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzdq();
        this.zzyd.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final void zzc(zzfh zzfhVar) {
        zzdq();
        this.zzyd.add(zzfhVar);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final Object getRaw(int r2) {
        return this.zzyd.get(r2);
    }

    private static String zzj(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzfh) {
            return ((zzfh) obj).zzer();
        }
        return zzgt.zzh((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final List<?> zzgx() {
        return Collections.unmodifiableList(this.zzyd);
    }

    @Override // com.google.android.gms.internal.vision.zzhj
    public final zzhj zzgy() {
        return zzdo() ? new zzjo(this) : this;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int r2, Object obj) {
        zzdq();
        return zzj(this.zzyd.set(r2, (String) obj));
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r2) {
        zzdq();
        Object remove = this.zzyd.remove(r2);
        this.modCount++;
        return zzj(remove);
    }

    @Override // com.google.android.gms.internal.vision.zzex, com.google.android.gms.internal.vision.zzgz
    public final /* bridge */ /* synthetic */ boolean zzdo() {
        return super.zzdo();
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r2, Object obj) {
        zzdq();
        this.zzyd.add(r2, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        return super.add(obj);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.android.gms.internal.vision.zzgz
    public final /* synthetic */ zzgz zzag(int r2) {
        if (r2 < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(r2);
        arrayList.addAll(this.zzyd);
        return new zzhk(arrayList);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r3) {
        Object obj = this.zzyd.get(r3);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzfh) {
            zzfh zzfhVar = (zzfh) obj;
            String zzer = zzfhVar.zzer();
            if (zzfhVar.zzes()) {
                this.zzyd.set(r3, zzer);
            }
            return zzer;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzgt.zzh(bArr);
        if (zzgt.zzg(bArr)) {
            this.zzyd.set(r3, zzh);
        }
        return zzh;
    }

    static {
        zzhk zzhkVar = new zzhk();
        zzyb = zzhkVar;
        zzhkVar.zzdp();
        zzyc = zzhkVar;
    }
}
