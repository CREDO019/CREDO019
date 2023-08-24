package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzce extends zzav<Float> implements zzcn<Float>, RandomAccess {
    private static final zzce zzjm;
    private int size;
    private float[] zzjn;

    static {
        zzce zzceVar = new zzce();
        zzjm = zzceVar;
        zzceVar.zzv();
    }

    zzce() {
        this(new float[10], 0);
    }

    private zzce(float[] fArr, int r2) {
        this.zzjn = fArr;
        this.size = r2;
    }

    private final void zzc(int r5, float f) {
        int r0;
        zzw();
        if (r5 < 0 || r5 > (r0 = this.size)) {
            throw new IndexOutOfBoundsException(zzh(r5));
        }
        float[] fArr = this.zzjn;
        if (r0 < fArr.length) {
            System.arraycopy(fArr, r5, fArr, r5 + 1, r0 - r5);
        } else {
            float[] fArr2 = new float[((r0 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, r5);
            System.arraycopy(this.zzjn, r5, fArr2, r5 + 1, this.size - r5);
            this.zzjn = fArr2;
        }
        this.zzjn[r5] = f;
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
    public final /* synthetic */ void add(int r1, Object obj) {
        zzc(r1, ((Float) obj).floatValue());
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Float> collection) {
        zzw();
        zzci.checkNotNull(collection);
        if (collection instanceof zzce) {
            zzce zzceVar = (zzce) collection;
            int r0 = zzceVar.size;
            if (r0 == 0) {
                return false;
            }
            int r3 = this.size;
            if (Integer.MAX_VALUE - r3 >= r0) {
                int r32 = r3 + r0;
                float[] fArr = this.zzjn;
                if (r32 > fArr.length) {
                    this.zzjn = Arrays.copyOf(fArr, r32);
                }
                System.arraycopy(zzceVar.zzjn, 0, this.zzjn, this.size, zzceVar.size);
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
        if (obj instanceof zzce) {
            zzce zzceVar = (zzce) obj;
            if (this.size != zzceVar.size) {
                return false;
            }
            float[] fArr = zzceVar.zzjn;
            for (int r1 = 0; r1 < this.size; r1++) {
                if (this.zzjn[r1] != fArr[r1]) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r2) {
        zzg(r2);
        return Float.valueOf(this.zzjn[r2]);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.size; r1++) {
            r0 = (r0 * 31) + Float.floatToIntBits(this.zzjn[r1]);
        }
        return r0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r5) {
        zzw();
        zzg(r5);
        float[] fArr = this.zzjn;
        float f = fArr[r5];
        int r2 = this.size;
        if (r5 < r2 - 1) {
            System.arraycopy(fArr, r5 + 1, fArr, r5, r2 - r5);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzw();
        for (int r1 = 0; r1 < this.size; r1++) {
            if (obj.equals(Float.valueOf(this.zzjn[r1]))) {
                float[] fArr = this.zzjn;
                System.arraycopy(fArr, r1 + 1, fArr, r1, this.size - r1);
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
        float[] fArr = this.zzjn;
        System.arraycopy(fArr, r4, fArr, r3, this.size - r4);
        this.size -= r4 - r3;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int r3, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zzw();
        zzg(r3);
        float[] fArr = this.zzjn;
        float f = fArr[r3];
        fArr[r3] = floatValue;
        return Float.valueOf(f);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzc(float f) {
        zzc(this.size, f);
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn<Float> zzi(int r3) {
        if (r3 >= this.size) {
            return new zzce(Arrays.copyOf(this.zzjn, r3), this.size);
        }
        throw new IllegalArgumentException();
    }
}
