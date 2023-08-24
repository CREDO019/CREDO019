package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzfuv extends zzfuq implements List, RandomAccess {
    private static final zzfwv zza = new zzfut(zzfwe.zza, 0);

    public static zzfus zzi() {
        return new zzfus(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfuv zzj(Object[] objArr) {
        return zzk(objArr, objArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfuv zzk(Object[] objArr, int r2) {
        if (r2 == 0) {
            return zzfwe.zza;
        }
        return new zzfwe(objArr, r2);
    }

    public static zzfuv zzm(Collection collection) {
        if (collection instanceof zzfuq) {
            zzfuv zzd = ((zzfuq) collection).zzd();
            if (zzd.zzf()) {
                Object[] array = zzd.toArray();
                return zzk(array, array.length);
            }
            return zzd;
        }
        Object[] array2 = collection.toArray();
        int length = array2.length;
        zzfwc.zzb(array2, length);
        return zzk(array2, length);
    }

    public static zzfuv zzn(Object[] objArr) {
        if (objArr.length == 0) {
            return zzfwe.zza;
        }
        Object[] objArr2 = (Object[]) objArr.clone();
        int length = objArr2.length;
        zzfwc.zzb(objArr2, length);
        return zzk(objArr2, length);
    }

    public static zzfuv zzo() {
        return zzfwe.zza;
    }

    public static zzfuv zzp(Object obj) {
        Object[] objArr = {obj};
        zzfwc.zzb(objArr, 1);
        return zzk(objArr, 1);
    }

    public static zzfuv zzq(Object obj, Object obj2) {
        Object[] objArr = {obj, obj2};
        zzfwc.zzb(objArr, 2);
        return zzk(objArr, 2);
    }

    public static zzfuv zzr(Object obj, Object obj2, Object obj3) {
        Object[] objArr = {"2011", "1009", "3010"};
        zzfwc.zzb(objArr, 3);
        return zzk(objArr, 3);
    }

    public static zzfuv zzs(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        Object[] objArr = {obj, obj2, obj3, obj4, obj5};
        zzfwc.zzb(objArr, 5);
        return zzk(objArr, 5);
    }

    public static zzfuv zzt(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Object[] objArr = {"3010", "3008", "1005", "1009", "2011", "2007"};
        zzfwc.zzb(objArr, 6);
        return zzk(objArr, 6);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int r1, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int r1, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.ads.zzfuq, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(@CheckForNull Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof List) {
            List list = (List) obj;
            int size = size();
            if (size == list.size()) {
                if (list instanceof RandomAccess) {
                    for (int r3 = 0; r3 < size; r3++) {
                        if (zzfsa.zza(get(r3), list.get(r3))) {
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it2.hasNext()) {
                            if (!zzfsa.zza(it.next(), it2.next())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else if (!it2.hasNext()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int r1 = 1;
        for (int r2 = 0; r2 < size; r2++) {
            r1 = (r1 * 31) + get(r2).hashCode();
        }
        return r1;
    }

    @Override // java.util.List
    public final int indexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int r2 = 0; r2 < size; r2++) {
            if (obj.equals(get(r2))) {
                return r2;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzfuq, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public final int lastIndexOf(@CheckForNull Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    public final /* synthetic */ ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    @Deprecated
    public final Object remove(int r1) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final Object set(int r1, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.android.gms.internal.ads.zzfuq
    int zza(Object[] objArr, int r6) {
        int size = size();
        for (int r1 = 0; r1 < size; r1++) {
            objArr[r6 + r1] = get(r1);
        }
        return r6 + size;
    }

    @Override // com.google.android.gms.internal.ads.zzfuq
    @Deprecated
    public final zzfuv zzd() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzfuq
    public final zzfwu zze() {
        return listIterator(0);
    }

    @Override // java.util.List
    /* renamed from: zzh */
    public zzfuv subList(int r2, int r3) {
        zzfsf.zzg(r2, r3, size());
        int r32 = r3 - r2;
        if (r32 == size()) {
            return this;
        }
        if (r32 != 0) {
            return new zzfuu(this, r2, r32);
        }
        return zzfwe.zza;
    }

    @Override // java.util.List
    /* renamed from: zzu */
    public final zzfwv listIterator(int r3) {
        zzfsf.zzb(r3, size(), "index");
        return isEmpty() ? zza : new zzfut(this, r3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static zzfuv zzl(Iterable iterable) {
        Objects.requireNonNull(iterable);
        return zzm(iterable);
    }
}
