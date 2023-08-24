package com.google.android.gms.internal.vision;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzgo extends zzex<Float> implements zzgz<Float>, zzio, RandomAccess {
    private static final zzgo zzvq;
    private int size;
    private float[] zzvr;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgo() {
        this(new float[10], 0);
    }

    private zzgo(float[] fArr, int r2) {
        this.zzvr = fArr;
        this.size = r2;
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int r3, int r4) {
        zzdq();
        if (r4 < r3) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        float[] fArr = this.zzvr;
        System.arraycopy(fArr, r4, fArr, r3, this.size - r4);
        this.size -= r4 - r3;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgo)) {
            return super.equals(obj);
        }
        zzgo zzgoVar = (zzgo) obj;
        if (this.size != zzgoVar.size) {
            return false;
        }
        float[] fArr = zzgoVar.zzvr;
        for (int r1 = 0; r1 < this.size; r1++) {
            if (Float.floatToIntBits(this.zzvr[r1]) != Float.floatToIntBits(fArr[r1])) {
                return false;
            }
        }
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.size; r1++) {
            r0 = (r0 * 31) + Float.floatToIntBits(this.zzvr[r1]);
        }
        return r0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzu(float f) {
        zzdq();
        int r0 = this.size;
        float[] fArr = this.zzvr;
        if (r0 == fArr.length) {
            float[] fArr2 = new float[((r0 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, r0);
            this.zzvr = fArr2;
        }
        float[] fArr3 = this.zzvr;
        int r1 = this.size;
        this.size = r1 + 1;
        fArr3[r1] = f;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Float> collection) {
        zzdq();
        zzgt.checkNotNull(collection);
        if (!(collection instanceof zzgo)) {
            return super.addAll(collection);
        }
        zzgo zzgoVar = (zzgo) collection;
        int r0 = zzgoVar.size;
        if (r0 == 0) {
            return false;
        }
        int r3 = this.size;
        if (Integer.MAX_VALUE - r3 < r0) {
            throw new OutOfMemoryError();
        }
        int r32 = r3 + r0;
        float[] fArr = this.zzvr;
        if (r32 > fArr.length) {
            this.zzvr = Arrays.copyOf(fArr, r32);
        }
        System.arraycopy(zzgoVar.zzvr, 0, this.zzvr, this.size, zzgoVar.size);
        this.size = r32;
        this.modCount++;
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzdq();
        for (int r1 = 0; r1 < this.size; r1++) {
            if (obj.equals(Float.valueOf(this.zzvr[r1]))) {
                float[] fArr = this.zzvr;
                System.arraycopy(fArr, r1 + 1, fArr, r1, (this.size - r1) - 1);
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
        float floatValue = ((Float) obj).floatValue();
        zzdq();
        zzae(r3);
        float[] fArr = this.zzvr;
        float f = fArr[r3];
        fArr[r3] = floatValue;
        return Float.valueOf(f);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r5) {
        int r2;
        zzdq();
        zzae(r5);
        float[] fArr = this.zzvr;
        float f = fArr[r5];
        if (r5 < this.size - 1) {
            System.arraycopy(fArr, r5 + 1, fArr, r5, (r2 - r5) - 1);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f);
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r5, Object obj) {
        int r0;
        float floatValue = ((Float) obj).floatValue();
        zzdq();
        if (r5 < 0 || r5 > (r0 = this.size)) {
            throw new IndexOutOfBoundsException(zzaf(r5));
        }
        float[] fArr = this.zzvr;
        if (r0 < fArr.length) {
            System.arraycopy(fArr, r5, fArr, r5 + 1, r0 - r5);
        } else {
            float[] fArr2 = new float[((r0 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, r5);
            System.arraycopy(this.zzvr, r5, fArr2, r5 + 1, this.size - r5);
            this.zzvr = fArr2;
        }
        this.zzvr[r5] = floatValue;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.vision.zzex, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* synthetic */ boolean add(Object obj) {
        zzu(((Float) obj).floatValue());
        return true;
    }

    @Override // com.google.android.gms.internal.vision.zzgz
    public final /* synthetic */ zzgz<Float> zzag(int r3) {
        if (r3 < this.size) {
            throw new IllegalArgumentException();
        }
        return new zzgo(Arrays.copyOf(this.zzvr, r3), this.size);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r2) {
        zzae(r2);
        return Float.valueOf(this.zzvr[r2]);
    }

    static {
        zzgo zzgoVar = new zzgo(new float[0], 0);
        zzvq = zzgoVar;
        zzgoVar.zzdp();
    }
}
