package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgmt extends zzgmp implements RandomAccess, zzgow, zzgqe {
    private static final zzgmt zza;
    private boolean[] zzb;
    private int zzc;

    static {
        zzgmt zzgmtVar = new zzgmt(new boolean[0], 0);
        zza = zzgmtVar;
        zzgmtVar.zzb();
    }

    zzgmt() {
        this(new boolean[10], 0);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzbM();
        if (r5 < 0 || r5 > (r0 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(r5));
        }
        boolean[] zArr = this.zzb;
        if (r0 < zArr.length) {
            System.arraycopy(zArr, r5, zArr, r5 + 1, r0 - r5);
        } else {
            boolean[] zArr2 = new boolean[((r0 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, r5);
            System.arraycopy(this.zzb, r5, zArr2, r5 + 1, this.zzc - r5);
            this.zzb = zArr2;
        }
        this.zzb[r5] = booleanValue;
        this.zzc++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zzbM();
        zzgox.zze(collection);
        if (!(collection instanceof zzgmt)) {
            return super.addAll(collection);
        }
        zzgmt zzgmtVar = (zzgmt) collection;
        int r0 = zzgmtVar.zzc;
        if (r0 == 0) {
            return false;
        }
        int r2 = this.zzc;
        if (Integer.MAX_VALUE - r2 >= r0) {
            int r22 = r2 + r0;
            boolean[] zArr = this.zzb;
            if (r22 > zArr.length) {
                this.zzb = Arrays.copyOf(zArr, r22);
            }
            System.arraycopy(zzgmtVar.zzb, 0, this.zzb, this.zzc, zzgmtVar.zzc);
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
        if (!(obj instanceof zzgmt)) {
            return super.equals(obj);
        }
        zzgmt zzgmtVar = (zzgmt) obj;
        if (this.zzc != zzgmtVar.zzc) {
            return false;
        }
        boolean[] zArr = zzgmtVar.zzb;
        for (int r1 = 0; r1 < this.zzc; r1++) {
            if (this.zzb[r1] != zArr[r1]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r2) {
        zzg(r2);
        return Boolean.valueOf(this.zzb[r2]);
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.zzc; r1++) {
            r0 = (r0 * 31) + zzgox.zza(this.zzb[r1]);
        }
        return r0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Boolean) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            int r0 = this.zzc;
            for (int r2 = 0; r2 < r0; r2++) {
                if (this.zzb[r2] == booleanValue) {
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
        boolean[] zArr = this.zzb;
        boolean z = zArr[r5];
        if (r5 < this.zzc - 1) {
            System.arraycopy(zArr, r5 + 1, zArr, r5, (r2 - r5) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int r3, int r4) {
        zzbM();
        if (r4 >= r3) {
            boolean[] zArr = this.zzb;
            System.arraycopy(zArr, r4, zArr, r3, this.zzc - r4);
            this.zzc -= r4 - r3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int r3, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzbM();
        zzg(r3);
        boolean[] zArr = this.zzb;
        boolean z = zArr[r3];
        zArr[r3] = booleanValue;
        return Boolean.valueOf(z);
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
        return new zzgmt(Arrays.copyOf(this.zzb, r3), this.zzc);
    }

    public final void zze(boolean z) {
        zzbM();
        int r0 = this.zzc;
        boolean[] zArr = this.zzb;
        if (r0 == zArr.length) {
            boolean[] zArr2 = new boolean[((r0 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, r0);
            this.zzb = zArr2;
        }
        boolean[] zArr3 = this.zzb;
        int r1 = this.zzc;
        this.zzc = r1 + 1;
        zArr3[r1] = z;
    }

    private zzgmt(boolean[] zArr, int r2) {
        this.zzb = zArr;
        this.zzc = r2;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zze(((Boolean) obj).booleanValue());
        return true;
    }
}
