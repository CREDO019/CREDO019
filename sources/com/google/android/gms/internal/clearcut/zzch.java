package com.google.android.gms.internal.clearcut;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzch extends zzav<Integer> implements zzcn<Integer>, RandomAccess {
    private static final zzch zzkr;
    private int size;
    private int[] zzks;

    static {
        zzch zzchVar = new zzch();
        zzkr = zzchVar;
        zzchVar.zzv();
    }

    zzch() {
        this(new int[10], 0);
    }

    private zzch(int[] r1, int r2) {
        this.zzks = r1;
        this.size = r2;
    }

    public static zzch zzbk() {
        return zzkr;
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

    private final void zzo(int r5, int r6) {
        int r0;
        zzw();
        if (r5 < 0 || r5 > (r0 = this.size)) {
            throw new IndexOutOfBoundsException(zzh(r5));
        }
        int[] r1 = this.zzks;
        if (r0 < r1.length) {
            System.arraycopy(r1, r5, r1, r5 + 1, r0 - r5);
        } else {
            int[] r02 = new int[((r0 * 3) / 2) + 1];
            System.arraycopy(r1, 0, r02, 0, r5);
            System.arraycopy(this.zzks, r5, r02, r5 + 1, this.size - r5);
            this.zzks = r02;
        }
        this.zzks[r5] = r6;
        this.size++;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ void add(int r1, Object obj) {
        zzo(r1, ((Integer) obj).intValue());
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection<? extends Integer> collection) {
        zzw();
        zzci.checkNotNull(collection);
        if (collection instanceof zzch) {
            zzch zzchVar = (zzch) collection;
            int r0 = zzchVar.size;
            if (r0 == 0) {
                return false;
            }
            int r3 = this.size;
            if (Integer.MAX_VALUE - r3 >= r0) {
                int r32 = r3 + r0;
                int[] r02 = this.zzks;
                if (r32 > r02.length) {
                    this.zzks = Arrays.copyOf(r02, r32);
                }
                System.arraycopy(zzchVar.zzks, 0, this.zzks, this.size, zzchVar.size);
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
        if (obj instanceof zzch) {
            zzch zzchVar = (zzch) obj;
            if (this.size != zzchVar.size) {
                return false;
            }
            int[] r6 = zzchVar.zzks;
            for (int r1 = 0; r1 < this.size; r1++) {
                if (this.zzks[r1] != r6[r1]) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int r1) {
        return Integer.valueOf(getInt(r1));
    }

    public final int getInt(int r2) {
        zzg(r2);
        return this.zzks[r2];
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int r0 = 1;
        for (int r1 = 0; r1 < this.size; r1++) {
            r0 = (r0 * 31) + this.zzks[r1];
        }
        return r0;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object remove(int r5) {
        zzw();
        zzg(r5);
        int[] r0 = this.zzks;
        int r1 = r0[r5];
        int r2 = this.size;
        if (r5 < r2 - 1) {
            System.arraycopy(r0, r5 + 1, r0, r5, r2 - r5);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(r1);
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        zzw();
        for (int r1 = 0; r1 < this.size; r1++) {
            if (obj.equals(Integer.valueOf(this.zzks[r1]))) {
                int[] r4 = this.zzks;
                System.arraycopy(r4, r1 + 1, r4, r1, this.size - r1);
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
        int[] r0 = this.zzks;
        System.arraycopy(r0, r4, r0, r3, this.size - r4);
        this.size -= r4 - r3;
        this.modCount++;
    }

    @Override // com.google.android.gms.internal.clearcut.zzav, java.util.AbstractList, java.util.List
    public final /* synthetic */ Object set(int r3, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzw();
        zzg(r3);
        int[] r0 = this.zzks;
        int r1 = r0[r3];
        r0[r3] = intValue;
        return Integer.valueOf(r1);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    public final void zzac(int r2) {
        zzo(this.size, r2);
    }

    @Override // com.google.android.gms.internal.clearcut.zzcn
    public final /* synthetic */ zzcn<Integer> zzi(int r3) {
        if (r3 >= this.size) {
            return new zzch(Arrays.copyOf(this.zzks, r3), this.size);
        }
        throw new IllegalArgumentException();
    }
}
