package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgnw extends zzgmp implements RandomAccess, zzgow, zzgqe {
    private static final zzgnw zza;
    private double[] zzb;
    private int zzc;

    static {
        zzgnw zzgnwVar = new zzgnw(new double[0], 0);
        zza = zzgnwVar;
        zzgnwVar.zzb();
    }

    zzgnw() {
        this(new double[10], 0);
    }

    private final String zzf(int r4) {
        int r0 = this.zzc;
        return "Index:" + r4 + ", Size:" + r0;
    }

    private final void zzg(int r2) {
        if (r2 < 0 || r2 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzf(r2));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r6, Object obj) {
        int r7;
        double doubleValue = ((Double) obj).doubleValue();
        zzbM();
        if (r6 < 0 || r6 > (r7 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(r6));
        }
        double[] dArr = this.zzb;
        if (r7 < dArr.length) {
            System.arraycopy(dArr, r6, dArr, r6 + 1, r7 - r6);
        } else {
            double[] dArr2 = new double[((r7 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, r6);
            System.arraycopy(this.zzb, r6, dArr2, r6 + 1, this.zzc - r6);
            this.zzb = dArr2;
        }
        this.zzb[r6] = doubleValue;
        this.zzc++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zzbM();
        zzgox.zze(collection);
        if (!(collection instanceof zzgnw)) {
            return super.addAll(collection);
        }
        zzgnw zzgnwVar = (zzgnw) collection;
        int r0 = zzgnwVar.zzc;
        if (r0 == 0) {
            return false;
        }
        int r2 = this.zzc;
        if (Integer.MAX_VALUE - r2 >= r0) {
            int r22 = r2 + r0;
            double[] dArr = this.zzb;
            if (r22 > dArr.length) {
                this.zzb = Arrays.copyOf(dArr, r22);
            }
            System.arraycopy(zzgnwVar.zzb, 0, this.zzb, this.zzc, zzgnwVar.zzc);
            this.zzc = r22;
            this.modCount++;
            return true;
        }
        throw new OutOfMemoryError();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgnw)) {
            return super.equals(obj);
        }
        zzgnw zzgnwVar = (zzgnw) obj;
        if (this.zzc != zzgnwVar.zzc) {
            return false;
        }
        double[] dArr = zzgnwVar.zzb;
        for (int r1 = 0; r1 < this.zzc; r1++) {
            if (Double.doubleToLongBits(this.zzb[r1]) != Double.doubleToLongBits(dArr[r1])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r4) {
        zzg(r4);
        return Double.valueOf(this.zzb[r4]);
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.zzc; r1++) {
            r0 = (r0 * 31) + zzgox.zzc(Double.doubleToLongBits(this.zzb[r1]));
        }
        return r0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Double) {
            double doubleValue = ((Double) obj).doubleValue();
            int r8 = this.zzc;
            for (int r0 = 0; r0 < r8; r0++) {
                if (this.zzb[r0] == doubleValue) {
                    return r0;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int r6) {
        int r3;
        zzbM();
        zzg(r6);
        double[] dArr = this.zzb;
        double d = dArr[r6];
        if (r6 < this.zzc - 1) {
            System.arraycopy(dArr, r6 + 1, dArr, r6, (r3 - r6) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Double.valueOf(d);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int r3, int r4) {
        zzbM();
        if (r4 >= r3) {
            double[] dArr = this.zzb;
            System.arraycopy(dArr, r4, dArr, r3, this.zzc - r4);
            this.zzc -= r4 - r3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int r5, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzbM();
        zzg(r5);
        double[] dArr = this.zzb;
        double d = dArr[r5];
        dArr[r5] = doubleValue;
        return Double.valueOf(d);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzgow
    public final /* bridge */ /* synthetic */ zzgow zzd(int r3) {
        if (r3 < this.zzc) {
            throw new IllegalArgumentException();
        }
        return new zzgnw(Arrays.copyOf(this.zzb, r3), this.zzc);
    }

    public final void zze(double d) {
        zzbM();
        int r0 = this.zzc;
        double[] dArr = this.zzb;
        if (r0 == dArr.length) {
            double[] dArr2 = new double[((r0 * 3) / 2) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, r0);
            this.zzb = dArr2;
        }
        double[] dArr3 = this.zzb;
        int r1 = this.zzc;
        this.zzc = r1 + 1;
        dArr3[r1] = d;
    }

    private zzgnw(double[] dArr, int r2) {
        this.zzb = dArr;
        this.zzc = r2;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zze(((Double) obj).doubleValue());
        return true;
    }
}
