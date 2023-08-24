package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzdc extends zzav<Long> implements zzcn<Long>, RandomAccess {
    private static final zzdc zzlw;
    private int size;
    private long[] zzlx;

    static {
        zzdc zzdcVar = new zzdc();
        zzlw = zzdcVar;
        zzdcVar.zzv();
    }

    zzdc() {
        this(new long[10], 0);
    }

    private zzdc(long[] jArr, int r2) {
        this.zzlx = jArr;
        this.size = r2;
    }

    public static zzdc zzbx() {
        return zzlw;
    }

    private final void zzg(int r2) {
        if (r2 < 0 || r2 >= this.size) {
            throw new IndexOutOfBoundsException(zzh(r2));
        }
    }

    private final String zzh(int r4) {
        int r0 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(r4);
        sb.append(", Size:");
        sb.append(r0);
        return sb.toString();
    }

    private final void zzk(int r5, long j) {
        int r0;
        zzw();
        if (r5 < 0 || r5 > (r0 = this.size)) {
            throw new IndexOutOfBoundsException(zzh(r5));
        }
        long[] jArr = this.zzlx;
        if (r0 < jArr.length) {
            System.arraycopy(jArr, r5, jArr, r5 + 1, r0 - r5);
        } else {
            long[] jArr2 = new long[((r0 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, r5);
            System.arraycopy(this.zzlx, r5, jArr2, r5 + 1, this.size - r5);
            this.zzlx = jArr2;
        }
        this.zzlx[r5] = j;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r3, Object obj) {
        zzk(r3, ((Long) obj).longValue());
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Long> collection) {
        zzw();
        zzci.checkNotNull(collection);
        if (collection instanceof zzdc) {
            zzdc zzdcVar = (zzdc) collection;
            int r0 = zzdcVar.size;
            if (r0 == 0) {
                return false;
            }
            int r3 = this.size;
            if (Integer.MAX_VALUE - r3 >= r0) {
                int r32 = r3 + r0;
                long[] jArr = this.zzlx;
                if (r32 > jArr.length) {
                    this.zzlx = Arrays.copyOf(jArr, r32);
                }
                System.arraycopy(zzdcVar.zzlx, 0, this.zzlx, this.size, zzdcVar.size);
                this.size = r32;
                this.modCount++;
                return true;
            }
            throw new OutOfMemoryError();
        }
        return super.addAll(collection);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzdc) {
            zzdc zzdcVar = (zzdc) obj;
            if (this.size != zzdcVar.size) {
                return false;
            }
            long[] jArr = zzdcVar.zzlx;
            for (int r1 = 0; r1 < this.size; r1++) {
                if (this.zzlx[r1] != jArr[r1]) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r3) {
        return Long.valueOf(getLong(r3));
    }

    public final long getLong(int r4) {
        zzg(r4);
        return this.zzlx[r4];
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.size; r1++) {
            r0 = (r0 * 31) + zzci.zzl(this.zzlx[r1]);
        }
        return r0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r6) {
        zzw();
        zzg(r6);
        long[] jArr = this.zzlx;
        long j = jArr[r6];
        int r3 = this.size;
        if (r6 < r3 - 1) {
            System.arraycopy(jArr, r6 + 1, jArr, r6, r3 - r6);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzw();
        for (int r1 = 0; r1 < this.size; r1++) {
            if (obj.equals(Long.valueOf(this.zzlx[r1]))) {
                long[] jArr = this.zzlx;
                System.arraycopy(jArr, r1 + 1, jArr, r1, this.size - r1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int r3, int r4) {
        zzw();
        if (r4 < r3) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        long[] jArr = this.zzlx;
        System.arraycopy(jArr, r4, jArr, r3, this.size - r4);
        this.size -= r4 - r3;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int r5, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzw();
        zzg(r5);
        long[] jArr = this.zzlx;
        long j = jArr[r5];
        jArr[r5] = longValue;
        return Long.valueOf(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn<Long> zzi(int r3) {
        if (r3 >= this.size) {
            return new zzdc(Arrays.copyOf(this.zzlx, r3), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final void zzm(long j) {
        zzk(this.size, j);
    }
}
