package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
final class zzkd extends zzim implements RandomAccess, zzkh, zzlq {
    private static final zzkd zza;
    private int[] zzb;
    private int zzc;

    static {
        zzkd zzkdVar = new zzkd(new int[0], 0);
        zza = zzkdVar;
        zzkdVar.zzb();
    }

    zzkd() {
        this(new int[10], 0);
    }

    public static zzkd zzf() {
        return zza;
    }

    private final String zzi(int r4) {
        int r0 = this.zzc;
        return "Index:" + r4 + ", Size:" + r0;
    }

    private final void zzj(int r2) {
        if (r2 < 0 || r2 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzi(r2));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r5, Object obj) {
        int r0;
        int intValue = ((Integer) obj).intValue();
        zzbS();
        if (r5 < 0 || r5 > (r0 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzi(r5));
        }
        int[] r1 = this.zzb;
        if (r0 < r1.length) {
            System.arraycopy(r1, r5, r1, r5 + 1, r0 - r5);
        } else {
            int[] r02 = new int[((r0 * 3) / 2) + 1];
            System.arraycopy(r1, 0, r02, 0, r5);
            System.arraycopy(this.zzb, r5, r02, r5 + 1, this.zzc - r5);
            this.zzb = r02;
        }
        this.zzb[r5] = intValue;
        this.zzc++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zzbS();
        zzkk.zze(collection);
        if (!(collection instanceof zzkd)) {
            return super.addAll(collection);
        }
        zzkd zzkdVar = (zzkd) collection;
        int r0 = zzkdVar.zzc;
        if (r0 == 0) {
            return false;
        }
        int r2 = this.zzc;
        if (Integer.MAX_VALUE - r2 >= r0) {
            int r22 = r2 + r0;
            int[] r02 = this.zzb;
            if (r22 > r02.length) {
                this.zzb = Arrays.copyOf(r02, r22);
            }
            System.arraycopy(zzkdVar.zzb, 0, this.zzb, this.zzc, zzkdVar.zzc);
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

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzkd)) {
            return super.equals(obj);
        }
        zzkd zzkdVar = (zzkd) obj;
        if (this.zzc != zzkdVar.zzc) {
            return false;
        }
        int[] r6 = zzkdVar.zzb;
        for (int r1 = 0; r1 < this.zzc; r1++) {
            if (this.zzb[r1] != r6[r1]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r2) {
        zzj(r2);
        return Integer.valueOf(this.zzb[r2]);
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.zzc; r1++) {
            r0 = (r0 * 31) + this.zzb[r1];
        }
        return r0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            int r0 = this.zzc;
            for (int r2 = 0; r2 < r0; r2++) {
                if (this.zzb[r2] == intValue) {
                    return r2;
                }
            }
            return -1;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int r5) {
        int r2;
        zzbS();
        zzj(r5);
        int[] r0 = this.zzb;
        int r1 = r0[r5];
        if (r5 < this.zzc - 1) {
            System.arraycopy(r0, r5 + 1, r0, r5, (r2 - r5) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Integer.valueOf(r1);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int r3, int r4) {
        zzbS();
        if (r4 >= r3) {
            int[] r0 = this.zzb;
            System.arraycopy(r0, r4, r0, r3, this.zzc - r4);
            this.zzc -= r4 - r3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int r3, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzbS();
        zzj(r3);
        int[] r0 = this.zzb;
        int r1 = r0[r3];
        r0[r3] = intValue;
        return Integer.valueOf(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    public final int zze(int r2) {
        zzj(r2);
        return this.zzb[r2];
    }

    @Override // com.google.android.gms.internal.measurement.zzkj
    /* renamed from: zzg */
    public final zzkh zzd(int r3) {
        if (r3 < this.zzc) {
            throw new IllegalArgumentException();
        }
        return new zzkd(Arrays.copyOf(this.zzb, r3), this.zzc);
    }

    public final void zzh(int r5) {
        zzbS();
        int r0 = this.zzc;
        int[] r1 = this.zzb;
        if (r0 == r1.length) {
            int[] r2 = new int[((r0 * 3) / 2) + 1];
            System.arraycopy(r1, 0, r2, 0, r0);
            this.zzb = r2;
        }
        int[] r02 = this.zzb;
        int r12 = this.zzc;
        this.zzc = r12 + 1;
        r02[r12] = r5;
    }

    private zzkd(int[] r1, int r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    @Override // com.google.android.gms.internal.measurement.zzim, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzh(((Integer) obj).intValue());
        return true;
    }
}
