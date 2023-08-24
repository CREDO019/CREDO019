package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzhq extends zzex<Long> implements zzgz<Long>, zzio, RandomAccess {
    private static final zzhq zzyj;
    private int size;
    private long[] zzyk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhq() {
        this(new long[10], 0);
    }

    private zzhq(long[] jArr, int r2) {
        this.zzyk = jArr;
        this.size = r2;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int r3, int r4) {
        zzdq();
        if (r4 < r3) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        long[] jArr = this.zzyk;
        System.arraycopy(jArr, r4, jArr, r3, this.size - r4);
        this.size -= r4 - r3;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzhq)) {
            return super.equals(obj);
        }
        zzhq zzhqVar = (zzhq) obj;
        if (this.size != zzhqVar.size) {
            return false;
        }
        long[] jArr = zzhqVar.zzyk;
        for (int r1 = 0; r1 < this.size; r1++) {
            if (this.zzyk[r1] != jArr[r1]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.size; r1++) {
            r0 = (r0 * 31) + zzgt.zzab(this.zzyk[r1]);
        }
        return r0;
    }

    public final long getLong(int r4) {
        zzae(r4);
        return this.zzyk[r4];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzac(long j) {
        zzdq();
        int r0 = this.size;
        long[] jArr = this.zzyk;
        if (r0 == jArr.length) {
            long[] jArr2 = new long[((r0 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, r0);
            this.zzyk = jArr2;
        }
        long[] jArr3 = this.zzyk;
        int r1 = this.size;
        this.size = r1 + 1;
        jArr3[r1] = j;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Long> collection) {
        zzdq();
        zzgt.checkNotNull(collection);
        if (!(collection instanceof zzhq)) {
            return super.addAll(collection);
        }
        zzhq zzhqVar = (zzhq) collection;
        int r0 = zzhqVar.size;
        if (r0 == 0) {
            return false;
        }
        int r3 = this.size;
        if (Integer.MAX_VALUE - r3 < r0) {
            throw new OutOfMemoryError();
        }
        int r32 = r3 + r0;
        long[] jArr = this.zzyk;
        if (r32 > jArr.length) {
            this.zzyk = Arrays.copyOf(jArr, r32);
        }
        System.arraycopy(zzhqVar.zzyk, 0, this.zzyk, this.size, zzhqVar.size);
        this.size = r32;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzdq();
        for (int r1 = 0; r1 < this.size; r1++) {
            if (obj.equals(Long.valueOf(this.zzyk[r1]))) {
                long[] jArr = this.zzyk;
                System.arraycopy(jArr, r1 + 1, jArr, r1, (this.size - r1) - 1);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzae(int r2) {
        if (r2 < 0 || r2 >= this.size) {
            throw new IndexOutOfBoundsException(zzaf(r2));
        }
    }

    private final String zzaf(int r4) {
        int r0 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(r4);
        sb.append(", Size:");
        sb.append(r0);
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int r5, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzdq();
        zzae(r5);
        long[] jArr = this.zzyk;
        long j = jArr[r5];
        jArr[r5] = longValue;
        return Long.valueOf(j);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r6) {
        int r3;
        zzdq();
        zzae(r6);
        long[] jArr = this.zzyk;
        long j = jArr[r6];
        if (r6 < this.size - 1) {
            System.arraycopy(jArr, r6 + 1, jArr, r6, (r3 - r6) - 1);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r6, Object obj) {
        int r7;
        long longValue = ((Long) obj).longValue();
        zzdq();
        if (r6 < 0 || r6 > (r7 = this.size)) {
            throw new IndexOutOfBoundsException(zzaf(r6));
        }
        long[] jArr = this.zzyk;
        if (r7 < jArr.length) {
            System.arraycopy(jArr, r6, jArr, r6 + 1, r7 - r6);
        } else {
            long[] jArr2 = new long[((r7 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, r6);
            System.arraycopy(this.zzyk, r6, jArr2, r6 + 1, this.size - r6);
            this.zzyk = jArr2;
        }
        this.zzyk[r6] = longValue;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        zzac(((Long) obj).longValue());
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzgz
    public final /* synthetic */ zzgz<Long> zzag(int r3) {
        if (r3 < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzhq(Arrays.copyOf(this.zzyk, r3), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r3) {
        return Long.valueOf(getLong(r3));
    }

    static {
        zzhq zzhqVar = new zzhq(new long[0], 0);
        zzyj = zzhqVar;
        zzhqVar.zzdp();
    }
}
