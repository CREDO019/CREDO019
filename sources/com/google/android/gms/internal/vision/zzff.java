package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzff extends zzex<Boolean> implements zzgz<Boolean>, zzio, RandomAccess {
    private static final zzff zzrs;
    private int size;
    private boolean[] zzrt;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzff() {
        this(new boolean[10], 0);
    }

    private zzff(boolean[] zArr, int r2) {
        this.zzrt = zArr;
        this.size = r2;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int r3, int r4) {
        zzdq();
        if (r4 < r3) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        boolean[] zArr = this.zzrt;
        System.arraycopy(zArr, r4, zArr, r3, this.size - r4);
        this.size -= r4 - r3;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzff)) {
            return super.equals(obj);
        }
        zzff zzffVar = (zzff) obj;
        if (this.size != zzffVar.size) {
            return false;
        }
        boolean[] zArr = zzffVar.zzrt;
        for (int r1 = 0; r1 < this.size; r1++) {
            if (this.zzrt[r1] != zArr[r1]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.size; r1++) {
            r0 = (r0 * 31) + zzgt.zzm(this.zzrt[r1]);
        }
        return r0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void addBoolean(boolean z) {
        zzdq();
        int r0 = this.size;
        boolean[] zArr = this.zzrt;
        if (r0 == zArr.length) {
            boolean[] zArr2 = new boolean[((r0 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, r0);
            this.zzrt = zArr2;
        }
        boolean[] zArr3 = this.zzrt;
        int r1 = this.size;
        this.size = r1 + 1;
        zArr3[r1] = z;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzdq();
        zzgt.checkNotNull(collection);
        if (!(collection instanceof zzff)) {
            return super.addAll(collection);
        }
        zzff zzffVar = (zzff) collection;
        int r0 = zzffVar.size;
        if (r0 == 0) {
            return false;
        }
        int r3 = this.size;
        if (Integer.MAX_VALUE - r3 < r0) {
            throw new OutOfMemoryError();
        }
        int r32 = r3 + r0;
        boolean[] zArr = this.zzrt;
        if (r32 > zArr.length) {
            this.zzrt = Arrays.copyOf(zArr, r32);
        }
        System.arraycopy(zzffVar.zzrt, 0, this.zzrt, this.size, zzffVar.size);
        this.size = r32;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzdq();
        for (int r1 = 0; r1 < this.size; r1++) {
            if (obj.equals(Boolean.valueOf(this.zzrt[r1]))) {
                boolean[] zArr = this.zzrt;
                System.arraycopy(zArr, r1 + 1, zArr, r1, (this.size - r1) - 1);
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
    public final /* synthetic */ Object set(int r3, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzdq();
        zzae(r3);
        boolean[] zArr = this.zzrt;
        boolean z = zArr[r3];
        zArr[r3] = booleanValue;
        return Boolean.valueOf(z);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r5) {
        int r2;
        zzdq();
        zzae(r5);
        boolean[] zArr = this.zzrt;
        boolean z = zArr[r5];
        if (r5 < this.size - 1) {
            System.arraycopy(zArr, r5 + 1, zArr, r5, (r2 - r5) - 1);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r5, Object obj) {
        int r0;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzdq();
        if (r5 < 0 || r5 > (r0 = this.size)) {
            throw new IndexOutOfBoundsException(zzaf(r5));
        }
        boolean[] zArr = this.zzrt;
        if (r0 < zArr.length) {
            System.arraycopy(zArr, r5, zArr, r5 + 1, r0 - r5);
        } else {
            boolean[] zArr2 = new boolean[((r0 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, r5);
            System.arraycopy(this.zzrt, r5, zArr2, r5 + 1, this.size - r5);
            this.zzrt = zArr2;
        }
        this.zzrt[r5] = booleanValue;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        addBoolean(((Boolean) obj).booleanValue());
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzgz
    public final /* synthetic */ zzgz<Boolean> zzag(int r3) {
        if (r3 < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzff(Arrays.copyOf(this.zzrt, r3), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r2) {
        zzae(r2);
        return Boolean.valueOf(this.zzrt[r2]);
    }

    static {
        zzff zzffVar = new zzff(new boolean[0], 0);
        zzrs = zzffVar;
        zzffVar.zzdp();
    }
}
