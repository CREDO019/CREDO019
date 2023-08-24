package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgpm extends zzgmp implements RandomAccess, zzgov, zzgqe {
    private static final zzgpm zza;
    private long[] zzb;
    private int zzc;

    static {
        zzgpm zzgpmVar = new zzgpm(new long[0], 0);
        zza = zzgpmVar;
        zzgpmVar.zzb();
    }

    zzgpm() {
        this(new long[10], 0);
    }

    public static zzgpm zzf() {
        return zza;
    }

    private final String zzh(int r4) {
        int r0 = this.zzc;
        return "Index:" + r4 + ", Size:" + r0;
    }

    private final void zzi(int r2) {
        if (r2 < 0 || r2 >= this.zzc) {
            throw new IndexOutOfBoundsException(zzh(r2));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r6, Object obj) {
        int r7;
        long longValue = ((Long) obj).longValue();
        zzbM();
        if (r6 < 0 || r6 > (r7 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzh(r6));
        }
        long[] jArr = this.zzb;
        if (r7 < jArr.length) {
            System.arraycopy(jArr, r6, jArr, r6 + 1, r7 - r6);
        } else {
            long[] jArr2 = new long[((r7 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, r6);
            System.arraycopy(this.zzb, r6, jArr2, r6 + 1, this.zzc - r6);
            this.zzb = jArr2;
        }
        this.zzb[r6] = longValue;
        this.zzc++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zzbM();
        zzgox.zze(collection);
        if (!(collection instanceof zzgpm)) {
            return super.addAll(collection);
        }
        zzgpm zzgpmVar = (zzgpm) collection;
        int r0 = zzgpmVar.zzc;
        if (r0 == 0) {
            return false;
        }
        int r2 = this.zzc;
        if (Integer.MAX_VALUE - r2 >= r0) {
            int r22 = r2 + r0;
            long[] jArr = this.zzb;
            if (r22 > jArr.length) {
                this.zzb = Arrays.copyOf(jArr, r22);
            }
            System.arraycopy(zzgpmVar.zzb, 0, this.zzb, this.zzc, zzgpmVar.zzc);
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
        if (!(obj instanceof zzgpm)) {
            return super.equals(obj);
        }
        zzgpm zzgpmVar = (zzgpm) obj;
        if (this.zzc != zzgpmVar.zzc) {
            return false;
        }
        long[] jArr = zzgpmVar.zzb;
        for (int r1 = 0; r1 < this.zzc; r1++) {
            if (this.zzb[r1] != jArr[r1]) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r4) {
        zzi(r4);
        return Long.valueOf(this.zzb[r4]);
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.zzc; r1++) {
            r0 = (r0 * 31) + zzgox.zzc(this.zzb[r1]);
        }
        return r0;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        if (obj instanceof Long) {
            long longValue = ((Long) obj).longValue();
            int r8 = this.zzc;
            for (int r0 = 0; r0 < r8; r0++) {
                if (this.zzb[r0] == longValue) {
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
        zzi(r6);
        long[] jArr = this.zzb;
        long j = jArr[r6];
        if (r6 < this.zzc - 1) {
            System.arraycopy(jArr, r6 + 1, jArr, r6, (r3 - r6) - 1);
        }
        this.zzc--;
        this.modCount++;
        return Long.valueOf(j);
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int r3, int r4) {
        zzbM();
        if (r4 >= r3) {
            long[] jArr = this.zzb;
            System.arraycopy(jArr, r4, jArr, r3, this.zzc - r4);
            this.zzc -= r4 - r3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int r5, Object obj) {
        long longValue = ((Long) obj).longValue();
        zzbM();
        zzi(r5);
        long[] jArr = this.zzb;
        long j = jArr[r5];
        jArr[r5] = longValue;
        return Long.valueOf(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzgow
    /* renamed from: zza */
    public final zzgov zzd(int r3) {
        if (r3 < this.zzc) {
            throw new IllegalArgumentException();
        }
        return new zzgpm(Arrays.copyOf(this.zzb, r3), this.zzc);
    }

    public final long zze(int r4) {
        zzi(r4);
        return this.zzb[r4];
    }

    public final void zzg(long j) {
        zzbM();
        int r0 = this.zzc;
        long[] jArr = this.zzb;
        if (r0 == jArr.length) {
            long[] jArr2 = new long[((r0 * 3) / 2) + 1];
            System.arraycopy(jArr, 0, jArr2, 0, r0);
            this.zzb = jArr2;
        }
        long[] jArr3 = this.zzb;
        int r1 = this.zzc;
        this.zzc = r1 + 1;
        jArr3[r1] = j;
    }

    private zzgpm(long[] jArr, int r2) {
        this.zzb = jArr;
        this.zzc = r2;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzg(((Long) obj).longValue());
        return true;
    }
}
