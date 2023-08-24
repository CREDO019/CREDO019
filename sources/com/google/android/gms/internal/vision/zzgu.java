package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzgu extends zzex<Integer> implements zzgz<Integer>, zzio, RandomAccess {
    private static final zzgu zzxf;
    private int size;
    private int[] zzxg;

    public static zzgu zzgl() {
        return zzxf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgu() {
        this(new int[10], 0);
    }

    private zzgu(int[] r1, int r2) {
        this.zzxg = r1;
        this.size = r2;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int r3, int r4) {
        zzdq();
        if (r4 < r3) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        int[] r0 = this.zzxg;
        System.arraycopy(r0, r4, r0, r3, this.size - r4);
        this.size -= r4 - r3;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgu)) {
            return super.equals(obj);
        }
        zzgu zzguVar = (zzgu) obj;
        if (this.size != zzguVar.size) {
            return false;
        }
        int[] r6 = zzguVar.zzxg;
        for (int r1 = 0; r1 < this.size; r1++) {
            if (this.zzxg[r1] != r6[r1]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.size; r1++) {
            r0 = (r0 * 31) + this.zzxg[r1];
        }
        return r0;
    }

    public final int getInt(int r2) {
        zzae(r2);
        return this.zzxg[r2];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzbl(int r5) {
        zzdq();
        int r0 = this.size;
        int[] r1 = this.zzxg;
        if (r0 == r1.length) {
            int[] r2 = new int[((r0 * 3) / 2) + 1];
            System.arraycopy(r1, 0, r2, 0, r0);
            this.zzxg = r2;
        }
        int[] r02 = this.zzxg;
        int r12 = this.size;
        this.size = r12 + 1;
        r02[r12] = r5;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Integer> collection) {
        zzdq();
        zzgt.checkNotNull(collection);
        if (!(collection instanceof zzgu)) {
            return super.addAll(collection);
        }
        zzgu zzguVar = (zzgu) collection;
        int r0 = zzguVar.size;
        if (r0 == 0) {
            return false;
        }
        int r3 = this.size;
        if (Integer.MAX_VALUE - r3 < r0) {
            throw new OutOfMemoryError();
        }
        int r32 = r3 + r0;
        int[] r02 = this.zzxg;
        if (r32 > r02.length) {
            this.zzxg = Arrays.copyOf(r02, r32);
        }
        System.arraycopy(zzguVar.zzxg, 0, this.zzxg, this.size, zzguVar.size);
        this.size = r32;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzdq();
        for (int r1 = 0; r1 < this.size; r1++) {
            if (obj.equals(Integer.valueOf(this.zzxg[r1]))) {
                int[] r5 = this.zzxg;
                System.arraycopy(r5, r1 + 1, r5, r1, (this.size - r1) - 1);
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
        int intValue = ((Integer) obj).intValue();
        zzdq();
        zzae(r3);
        int[] r0 = this.zzxg;
        int r1 = r0[r3];
        r0[r3] = intValue;
        return Integer.valueOf(r1);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r5) {
        int r2;
        zzdq();
        zzae(r5);
        int[] r0 = this.zzxg;
        int r1 = r0[r5];
        if (r5 < this.size - 1) {
            System.arraycopy(r0, r5 + 1, r0, r5, (r2 - r5) - 1);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(r1);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r5, Object obj) {
        int r0;
        int intValue = ((Integer) obj).intValue();
        zzdq();
        if (r5 < 0 || r5 > (r0 = this.size)) {
            throw new IndexOutOfBoundsException(zzaf(r5));
        }
        int[] r1 = this.zzxg;
        if (r0 < r1.length) {
            System.arraycopy(r1, r5, r1, r5 + 1, r0 - r5);
        } else {
            int[] r02 = new int[((r0 * 3) / 2) + 1];
            System.arraycopy(r1, 0, r02, 0, r5);
            System.arraycopy(this.zzxg, r5, r02, r5 + 1, this.size - r5);
            this.zzxg = r02;
        }
        this.zzxg[r5] = intValue;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        zzbl(((Integer) obj).intValue());
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzgz
    public final /* synthetic */ zzgz<Integer> zzag(int r3) {
        if (r3 < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzgu(Arrays.copyOf(this.zzxg, r3), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r1) {
        return Integer.valueOf(getInt(r1));
    }

    static {
        zzgu zzguVar = new zzgu(new int[0], 0);
        zzxf = zzguVar;
        zzguVar.zzdp();
    }
}
