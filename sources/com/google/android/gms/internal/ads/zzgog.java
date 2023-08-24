package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgog extends zzgmp implements RandomAccess, zzgow, zzgqe {
    private static final zzgog zza;
    private float[] zzb;
    private int zzc;

    static {
        zzgog zzgogVar = new zzgog(new float[0], 0);
        zza = zzgogVar;
        zzgogVar.zzb();
    }

    zzgog() {
        this(new float[10], 0);
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
    public final /* synthetic */ void add(int r5, Object obj) {
        int r0;
        float floatValue = ((Float) obj).floatValue();
        zzbM();
        if (r5 < 0 || r5 > (r0 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(r5));
        }
        float[] fArr = this.zzb;
        if (r0 < fArr.length) {
            System.arraycopy(fArr, r5, fArr, r5 + 1, r0 - r5);
        } else {
            float[] fArr2 = new float[((r0 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, r5);
            System.arraycopy(this.zzb, r5, fArr2, r5 + 1, this.zzc - r5);
            this.zzb = fArr2;
        }
        this.zzb[r5] = floatValue;
        this.zzc++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zzbM();
        zzgox.zze(collection);
        if (!(collection instanceof zzgog)) {
            return super.addAll(collection);
        }
        zzgog zzgogVar = (zzgog) collection;
        int r0 = zzgogVar.zzc;
        if (r0 == 0) {
            return false;
        }
        int r2 = this.zzc;
        if (Integer.MAX_VALUE - r2 >= r0) {
            int r22 = r2 + r0;
            float[] fArr = this.zzb;
            if (r22 > fArr.length) {
                this.zzb = Arrays.copyOf(fArr, r22);
            }
            System.arraycopy(zzgogVar.zzb, 0, this.zzb, this.zzc, zzgogVar.zzc);
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
        if (!(obj instanceof zzgog)) {
            return super.equals(obj);
        }
        zzgog zzgogVar = (zzgog) obj;
        if (this.zzc != zzgogVar.zzc) {
            return false;
        }
        float[] fArr = zzgogVar.zzb;
        for (int r1 = 0; r1 < this.zzc; r1++) {
            if (Float.floatToIntBits(this.zzb[r1]) != Float.floatToIntBits(fArr[r1])) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r2) {
        zzg(r2);
        return Float.valueOf(this.zzb[r2]);
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.zzc; r1++) {
            r0 = (r0 * 31) + Float.floatToIntBits(this.zzb[r1]);
        }
        return r0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            int r0 = this.zzc;
            for (int r2 = 0; r2 < r0; r2++) {
                if (this.zzb[r2] == floatValue) {
                    return r2;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int r5) {
        int r2;
        zzbM();
        zzg(r5);
        float[] fArr = this.zzb;
        float f = fArr[r5];
        if (r5 < this.zzc - 1) {
            System.arraycopy(fArr, r5 + 1, fArr, r5, (r2 - r5) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Float.valueOf(f);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int r3, int r4) {
        zzbM();
        if (r4 >= r3) {
            float[] fArr = this.zzb;
            System.arraycopy(fArr, r4, fArr, r3, this.zzc - r4);
            this.zzc -= r4 - r3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int r3, Object obj) {
        float floatValue = ((Float) obj).floatValue();
        zzbM();
        zzg(r3);
        float[] fArr = this.zzb;
        float f = fArr[r3];
        fArr[r3] = floatValue;
        return Float.valueOf(f);
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
        return new zzgog(Arrays.copyOf(this.zzb, r3), this.zzc);
    }

    public final void zze(float f) {
        zzbM();
        int r0 = this.zzc;
        float[] fArr = this.zzb;
        if (r0 == fArr.length) {
            float[] fArr2 = new float[((r0 * 3) / 2) + 1];
            System.arraycopy(fArr, 0, fArr2, 0, r0);
            this.zzb = fArr2;
        }
        float[] fArr3 = this.zzb;
        int r1 = this.zzc;
        this.zzc = r1 + 1;
        fArr3[r1] = f;
    }

    private zzgog(float[] fArr, int r2) {
        this.zzb = fArr;
        this.zzc = r2;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zze(((Float) obj).floatValue());
        return true;
    }
}
