package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgoo extends zzgmp implements RandomAccess, zzgos, zzgqe {
    private static final zzgoo zza;
    private int[] zzb;
    private int zzc;

    static {
        zzgoo zzgooVar = new zzgoo(new int[0], 0);
        zza = zzgooVar;
        zzgooVar.zzb();
    }

    zzgoo() {
        this(new int[10], 0);
    }

    public static zzgoo zzf() {
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

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r5, Object obj) {
        int r0;
        int intValue = ((Integer) obj).intValue();
        zzbM();
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

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        zzbM();
        zzgox.zze(collection);
        if (!(collection instanceof zzgoo)) {
            return super.addAll(collection);
        }
        zzgoo zzgooVar = (zzgoo) collection;
        int r0 = zzgooVar.zzc;
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
            System.arraycopy(zzgooVar.zzb, 0, this.zzb, this.zzc, zzgooVar.zzc);
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
        if (!(obj instanceof zzgoo)) {
            return super.equals(obj);
        }
        zzgoo zzgooVar = (zzgoo) obj;
        if (this.zzc != zzgooVar.zzc) {
            return false;
        }
        int[] r6 = zzgooVar.zzb;
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

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.Collection, java.util.List
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

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int r5) {
        int r2;
        zzbM();
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
        zzbM();
        if (r4 >= r3) {
            int[] r0 = this.zzb;
            System.arraycopy(r0, r4, r0, r3, this.zzc - r4);
            this.zzc -= r4 - r3;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException("toIndex < fromIndex");
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int r3, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzbM();
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

    @Override // com.google.android.gms.internal.ads.zzgow
    /* renamed from: zzg */
    public final zzgos zzd(int r3) {
        if (r3 < this.zzc) {
            throw new IllegalArgumentException();
        }
        return new zzgoo(Arrays.copyOf(this.zzb, r3), this.zzc);
    }

    @Override // com.google.android.gms.internal.ads.zzgos
    public final void zzh(int r5) {
        zzbM();
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

    private zzgoo(int[] r1, int r2) {
        this.zzb = r1;
        this.zzc = r2;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ /* synthetic */ boolean add(Object obj) {
        zzh(((Integer) obj).intValue());
        return true;
    }
}
