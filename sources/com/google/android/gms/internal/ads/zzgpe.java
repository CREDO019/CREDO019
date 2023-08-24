package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgpe extends zzgmp implements RandomAccess, zzgpf {
    public static final zzgpf zza;
    private static final zzgpe zzb;
    private final List zzc;

    static {
        zzgpe zzgpeVar = new zzgpe(10);
        zzb = zzgpeVar;
        zzgpeVar.zzb();
        zza = zzgpeVar;
    }

    public zzgpe() {
        this(10);
    }

    private static String zzj(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzgnf) {
            return ((zzgnf) obj).zzA(zzgox.zzb);
        }
        return zzgox.zzh((byte[]) obj);
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int r2, Object obj) {
        zzbM();
        this.zzc.add(r2, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final boolean addAll(int r2, Collection collection) {
        zzbM();
        if (collection instanceof zzgpf) {
            collection = ((zzgpf) collection).zzh();
        }
        boolean addAll = this.zzc.addAll(r2, collection);
        this.modCount++;
        return addAll;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zzbM();
        this.zzc.clear();
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int r2) {
        zzbM();
        Object remove = this.zzc.remove(r2);
        this.modCount++;
        return zzj(remove);
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int r2, Object obj) {
        zzbM();
        return zzj(this.zzc.set(r2, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.gms.internal.ads.zzgow
    public final /* bridge */ /* synthetic */ zzgow zzd(int r2) {
        if (r2 < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(r2);
        arrayList.addAll(this.zzc);
        return new zzgpe(arrayList);
    }

    @Override // com.google.android.gms.internal.ads.zzgpf
    public final zzgpf zze() {
        return zzc() ? new zzgrm(this) : this;
    }

    @Override // com.google.android.gms.internal.ads.zzgpf
    public final Object zzf(int r2) {
        return this.zzc.get(r2);
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: zzg */
    public final String get(int r3) {
        Object obj = this.zzc.get(r3);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzgnf) {
            zzgnf zzgnfVar = (zzgnf) obj;
            String zzA = zzgnfVar.zzA(zzgox.zzb);
            if (zzgnfVar.zzp()) {
                this.zzc.set(r3, zzA);
            }
            return zzA;
        }
        byte[] bArr = (byte[]) obj;
        String zzh = zzgox.zzh(bArr);
        if (zzgox.zzi(bArr)) {
            this.zzc.set(r3, zzh);
        }
        return zzh;
    }

    @Override // com.google.android.gms.internal.ads.zzgpf
    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    @Override // com.google.android.gms.internal.ads.zzgpf
    public final void zzi(zzgnf zzgnfVar) {
        zzbM();
        this.zzc.add(zzgnfVar);
        this.modCount++;
    }

    public zzgpe(int r2) {
        this.zzc = new ArrayList(r2);
    }

    private zzgpe(ArrayList arrayList) {
        this.zzc = arrayList;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
