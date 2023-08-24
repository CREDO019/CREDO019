package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgqg extends zzgmp implements RandomAccess {
    private static final zzgqg zza;
    private Object[] zzb;
    private int zzc;

    static {
        zzgqg zzgqgVar = new zzgqg(new Object[0], 0);
        zza = zzgqgVar;
        zzgqgVar.zzb();
    }

    zzgqg() {
        this(new Object[10], 0);
    }

    public static zzgqg zze() {
        return zza;
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
    public final void add(int r5, Object obj) {
        int r0;
        zzbM();
        if (r5 < 0 || r5 > (r0 = this.zzc)) {
            throw new IndexOutOfBoundsException(zzf(r5));
        }
        Object[] objArr = this.zzb;
        if (r0 < objArr.length) {
            System.arraycopy(objArr, r5, objArr, r5 + 1, r0 - r5);
        } else {
            Object[] objArr2 = new Object[((r0 * 3) / 2) + 1];
            System.arraycopy(objArr, 0, objArr2, 0, r5);
            System.arraycopy(this.zzb, r5, objArr2, r5 + 1, this.zzc - r5);
            this.zzb = objArr2;
        }
        this.zzb[r5] = obj;
        this.zzc++;
        this.modCount++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int r2) {
        zzg(r2);
        return this.zzb[r2];
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final Object remove(int r5) {
        int r2;
        zzbM();
        zzg(r5);
        Object[] objArr = this.zzb;
        Object obj = objArr[r5];
        if (r5 < this.zzc - 1) {
            System.arraycopy(objArr, r5 + 1, objArr, r5, (r2 - r5) - 1);
        }
        this.zzc--;
        this.modCount++;
        return obj;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.List
    public final Object set(int r3, Object obj) {
        zzbM();
        zzg(r3);
        Object[] objArr = this.zzb;
        Object obj2 = objArr[r3];
        objArr[r3] = obj;
        this.modCount++;
        return obj2;
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
        return new zzgqg(Arrays.copyOf(this.zzb, r3), this.zzc);
    }

    private zzgqg(Object[] objArr, int r2) {
        this.zzb = objArr;
        this.zzc = r2;
    }

    @Override // com.google.android.gms.internal.ads.zzgmp, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        zzbM();
        int r0 = this.zzc;
        Object[] objArr = this.zzb;
        if (r0 == objArr.length) {
            this.zzb = Arrays.copyOf(objArr, ((r0 * 3) / 2) + 1);
        }
        Object[] objArr2 = this.zzb;
        int r1 = this.zzc;
        this.zzc = r1 + 1;
        objArr2[r1] = obj;
        this.modCount++;
        return true;
    }
}
