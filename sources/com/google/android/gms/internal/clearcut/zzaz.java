package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzaz extends zzav<Boolean> implements zzcn<Boolean>, RandomAccess {
    private static final zzaz zzfg;
    private int size;
    private boolean[] zzfh;

    static {
        zzaz zzazVar = new zzaz();
        zzfg = zzazVar;
        zzazVar.zzv();
    }

    zzaz() {
        this(new boolean[10], 0);
    }

    private zzaz(boolean[] zArr, int r2) {
        this.zzfh = zArr;
        this.size = r2;
    }

    private final void zza(int r5, boolean z) {
        int r0;
        zzw();
        if (r5 < 0 || r5 > (r0 = this.size)) {
            throw new IndexOutOfBoundsException(zzh(r5));
        }
        boolean[] zArr = this.zzfh;
        if (r0 < zArr.length) {
            System.arraycopy(zArr, r5, zArr, r5 + 1, r0 - r5);
        } else {
            boolean[] zArr2 = new boolean[((r0 * 3) / 2) + 1];
            System.arraycopy(zArr, 0, zArr2, 0, r5);
            System.arraycopy(this.zzfh, r5, zArr2, r5 + 1, this.size - r5);
            this.zzfh = zArr2;
        }
        this.zzfh[r5] = z;
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
        zza(r1, ((Boolean) obj).booleanValue());
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzw();
        zzci.checkNotNull(collection);
        if (collection instanceof zzaz) {
            zzaz zzazVar = (zzaz) collection;
            int r0 = zzazVar.size;
            if (r0 == 0) {
                return false;
            }
            int r3 = this.size;
            if (Integer.MAX_VALUE - r3 >= r0) {
                int r32 = r3 + r0;
                boolean[] zArr = this.zzfh;
                if (r32 > zArr.length) {
                    this.zzfh = Arrays.copyOf(zArr, r32);
                }
                System.arraycopy(zzazVar.zzfh, 0, this.zzfh, this.size, zzazVar.size);
                this.size = r32;
                this.modCount++;
                return true;
            }
            throw new OutOfMemoryError();
        }
        return super.addAll(collection);
    }

    public final void addBoolean(boolean z) {
        zza(this.size, z);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzaz) {
            zzaz zzazVar = (zzaz) obj;
            if (this.size != zzazVar.size) {
                return false;
            }
            boolean[] zArr = zzazVar.zzfh;
            for (int r1 = 0; r1 < this.size; r1++) {
                if (this.zzfh[r1] != zArr[r1]) {
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
        return Boolean.valueOf(this.zzfh[r2]);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.size; r1++) {
            r0 = (r0 * 31) + zzci.zzc(this.zzfh[r1]);
        }
        return r0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r5) {
        zzw();
        zzg(r5);
        boolean[] zArr = this.zzfh;
        boolean z = zArr[r5];
        int r2 = this.size;
        if (r5 < r2 - 1) {
            System.arraycopy(zArr, r5 + 1, zArr, r5, r2 - r5);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzw();
        for (int r1 = 0; r1 < this.size; r1++) {
            if (obj.equals(Boolean.valueOf(this.zzfh[r1]))) {
                boolean[] zArr = this.zzfh;
                System.arraycopy(zArr, r1 + 1, zArr, r1, this.size - r1);
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
        boolean[] zArr = this.zzfh;
        System.arraycopy(zArr, r4, zArr, r3, this.size - r4);
        this.size -= r4 - r3;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int r3, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzw();
        zzg(r3);
        boolean[] zArr = this.zzfh;
        boolean z = zArr[r3];
        zArr[r3] = booleanValue;
        return Boolean.valueOf(z);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn<Boolean> zzi(int r3) {
        if (r3 >= this.size) {
            return new zzaz(Arrays.copyOf(this.zzfh, r3), this.size);
        }
        throw new IllegalArgumentException();
    }
}
