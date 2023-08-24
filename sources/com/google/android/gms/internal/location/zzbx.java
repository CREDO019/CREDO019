package com.google.android.gms.internal.location;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes.dex */
public abstract class zzbx extends zzbu implements List, RandomAccess {
    private static final zzca zza = new zzbv(zzby.zza, 0);

    static zzbx zzi(Object[] objArr, int r2) {
        if (r2 == 0) {
            return zzby.zza;
        }
        return new zzby(objArr, r2);
    }

    public static zzbx zzj(Collection collection) {
        if (collection instanceof zzbu) {
            zzbx zzd = ((zzbu) collection).zzd();
            if (zzd.zzf()) {
                Object[] array = zzd.toArray();
                return zzi(array, array.length);
            }
            return zzd;
        }
        Object[] array2 = collection.toArray();
        int length = array2.length;
        for (int r1 = 0; r1 < length; r1++) {
            if (array2[r1] == null) {
                StringBuilder sb = new StringBuilder(20);
                sb.append("at index ");
                sb.append(r1);
                throw new NullPointerException(sb.toString());
            }
        }
        return zzi(array2, length);
    }

    public static zzbx zzk() {
        return zzby.zza;
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

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
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
                        if (zzbq.zza(get(r3), list.get(r3))) {
                        }
                    }
                    return true;
                }
                Iterator it = iterator();
                Iterator it2 = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it2.hasNext()) {
                            if (!zzbq.zza(it.next(), it2.next())) {
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

    @Override // com.google.android.gms.internal.location.zzbu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
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

    @Override // com.google.android.gms.internal.location.zzbu
    int zza(Object[] objArr, int r4) {
        int size = size();
        for (int r0 = 0; r0 < size; r0++) {
            objArr[r0] = get(r0);
        }
        return size;
    }

    @Override // com.google.android.gms.internal.location.zzbu
    @Deprecated
    public final zzbx zzd() {
        return this;
    }

    @Override // com.google.android.gms.internal.location.zzbu
    public final zzbz zze() {
        return listIterator(0);
    }

    @Override // java.util.List
    /* renamed from: zzh */
    public zzbx subList(int r2, int r3) {
        zzbr.zzc(r2, r3, size());
        int r32 = r3 - r2;
        if (r32 == size()) {
            return this;
        }
        if (r32 != 0) {
            return new zzbw(this, r2, r32);
        }
        return zzby.zza;
    }

    @Override // java.util.List
    /* renamed from: zzl */
    public final zzca listIterator(int r3) {
        zzbr.zzb(r3, size(), "index");
        return isEmpty() ? zza : new zzbv(this, r3);
    }
}
