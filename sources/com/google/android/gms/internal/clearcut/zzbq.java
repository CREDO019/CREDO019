package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzbq extends zzav<Double> implements zzcn<Double>, RandomAccess {
    private static final zzbq zzgj;
    private int size;
    private double[] zzgk;

    static {
        zzbq zzbqVar = new zzbq();
        zzgj = zzbqVar;
        zzbqVar.zzv();
    }

    zzbq() {
        this(new double[10], 0);
    }

    private zzbq(double[] dArr, int r2) {
        this.zzgk = dArr;
        this.size = r2;
    }

    private final void zzc(int r5, double d) {
        int r0;
        zzw();
        if (r5 < 0 || r5 > (r0 = this.size)) {
            throw new IndexOutOfBoundsException(zzh(r5));
        }
        double[] dArr = this.zzgk;
        if (r0 < dArr.length) {
            System.arraycopy(dArr, r5, dArr, r5 + 1, r0 - r5);
        } else {
            double[] dArr2 = new double[((r0 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, r5);
            System.arraycopy(this.zzgk, r5, dArr2, r5 + 1, this.size - r5);
            this.zzgk = dArr2;
        }
        this.zzgk[r5] = d;
        this.size++;
        this.modCount++;
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

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r3, Object obj) {
        zzc(r3, ((Double) obj).doubleValue());
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Double> collection) {
        zzw();
        zzci.checkNotNull(collection);
        if (collection instanceof zzbq) {
            zzbq zzbqVar = (zzbq) collection;
            int r0 = zzbqVar.size;
            if (r0 == 0) {
                return false;
            }
            int r3 = this.size;
            if (Integer.MAX_VALUE - r3 >= r0) {
                int r32 = r3 + r0;
                double[] dArr = this.zzgk;
                if (r32 > dArr.length) {
                    this.zzgk = Arrays.copyOf(dArr, r32);
                }
                System.arraycopy(zzbqVar.zzgk, 0, this.zzgk, this.size, zzbqVar.size);
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
        if (obj instanceof zzbq) {
            zzbq zzbqVar = (zzbq) obj;
            if (this.size != zzbqVar.size) {
                return false;
            }
            double[] dArr = zzbqVar.zzgk;
            for (int r1 = 0; r1 < this.size; r1++) {
                if (this.zzgk[r1] != dArr[r1]) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r4) {
        zzg(r4);
        return Double.valueOf(this.zzgk[r4]);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.size; r1++) {
            r0 = (r0 * 31) + zzci.zzl(Double.doubleToLongBits(this.zzgk[r1]));
        }
        return r0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r6) {
        zzw();
        zzg(r6);
        double[] dArr = this.zzgk;
        double d = dArr[r6];
        int r3 = this.size;
        if (r6 < r3 - 1) {
            System.arraycopy(dArr, r6 + 1, dArr, r6, r3 - r6);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzw();
        for (int r1 = 0; r1 < this.size; r1++) {
            if (obj.equals(Double.valueOf(this.zzgk[r1]))) {
                double[] dArr = this.zzgk;
                System.arraycopy(dArr, r1 + 1, dArr, r1, this.size - r1);
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
        double[] dArr = this.zzgk;
        System.arraycopy(dArr, r4, dArr, r3, this.size - r4);
        this.size -= r4 - r3;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int r5, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzw();
        zzg(r5);
        double[] dArr = this.zzgk;
        double d = dArr[r5];
        dArr[r5] = doubleValue;
        return Double.valueOf(d);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzc(double d) {
        zzc(this.size, d);
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn<Double> zzi(int r3) {
        if (r3 >= this.size) {
            return new zzbq(Arrays.copyOf(this.zzgk, r3), this.size);
        }
        throw new IllegalArgumentException();
    }
}
