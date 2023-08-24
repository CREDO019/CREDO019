package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzgb extends zzex<Double> implements zzgz<Double>, zzio, RandomAccess {
    private static final zzgb zzst;
    private int size;
    private double[] zzsu;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgb() {
        this(new double[10], 0);
    }

    private zzgb(double[] dArr, int r2) {
        this.zzsu = dArr;
        this.size = r2;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int r3, int r4) {
        zzdq();
        if (r4 < r3) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        double[] dArr = this.zzsu;
        System.arraycopy(dArr, r4, dArr, r3, this.size - r4);
        this.size -= r4 - r3;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgb)) {
            return super.equals(obj);
        }
        zzgb zzgbVar = (zzgb) obj;
        if (this.size != zzgbVar.size) {
            return false;
        }
        double[] dArr = zzgbVar.zzsu;
        for (int r1 = 0; r1 < this.size; r1++) {
            if (Double.doubleToLongBits(this.zzsu[r1]) != Double.doubleToLongBits(dArr[r1])) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.size; r1++) {
            r0 = (r0 * 31) + zzgt.zzab(Double.doubleToLongBits(this.zzsu[r1]));
        }
        return r0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzc(double d) {
        zzdq();
        int r0 = this.size;
        double[] dArr = this.zzsu;
        if (r0 == dArr.length) {
            double[] dArr2 = new double[((r0 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, r0);
            this.zzsu = dArr2;
        }
        double[] dArr3 = this.zzsu;
        int r1 = this.size;
        this.size = r1 + 1;
        dArr3[r1] = d;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Double> collection) {
        zzdq();
        zzgt.checkNotNull(collection);
        if (!(collection instanceof zzgb)) {
            return super.addAll(collection);
        }
        zzgb zzgbVar = (zzgb) collection;
        int r0 = zzgbVar.size;
        if (r0 == 0) {
            return false;
        }
        int r3 = this.size;
        if (Integer.MAX_VALUE - r3 < r0) {
            throw new OutOfMemoryError();
        }
        int r32 = r3 + r0;
        double[] dArr = this.zzsu;
        if (r32 > dArr.length) {
            this.zzsu = Arrays.copyOf(dArr, r32);
        }
        System.arraycopy(zzgbVar.zzsu, 0, this.zzsu, this.size, zzgbVar.size);
        this.size = r32;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzdq();
        for (int r1 = 0; r1 < this.size; r1++) {
            if (obj.equals(Double.valueOf(this.zzsu[r1]))) {
                double[] dArr = this.zzsu;
                System.arraycopy(dArr, r1 + 1, dArr, r1, (this.size - r1) - 1);
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
        double doubleValue = ((Double) obj).doubleValue();
        zzdq();
        zzae(r5);
        double[] dArr = this.zzsu;
        double d = dArr[r5];
        dArr[r5] = doubleValue;
        return Double.valueOf(d);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r6) {
        int r3;
        zzdq();
        zzae(r6);
        double[] dArr = this.zzsu;
        double d = dArr[r6];
        if (r6 < this.size - 1) {
            System.arraycopy(dArr, r6 + 1, dArr, r6, (r3 - r6) - 1);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r6, Object obj) {
        int r7;
        double doubleValue = ((Double) obj).doubleValue();
        zzdq();
        if (r6 < 0 || r6 > (r7 = this.size)) {
            throw new IndexOutOfBoundsException(zzaf(r6));
        }
        double[] dArr = this.zzsu;
        if (r7 < dArr.length) {
            System.arraycopy(dArr, r6, dArr, r6 + 1, r7 - r6);
        } else {
            double[] dArr2 = new double[((r7 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, r6);
            System.arraycopy(this.zzsu, r6, dArr2, r6 + 1, this.size - r6);
            this.zzsu = dArr2;
        }
        this.zzsu[r6] = doubleValue;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        zzc(((Double) obj).doubleValue());
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzgz
    public final /* synthetic */ zzgz<Double> zzag(int r3) {
        if (r3 < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzgb(Arrays.copyOf(this.zzsu, r3), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r4) {
        zzae(r4);
        return Double.valueOf(this.zzsu[r4]);
    }

    static {
        zzgb zzgbVar = new zzgb(new double[0], 0);
        zzst = zzgbVar;
        zzgbVar.zzdp();
    }
}
