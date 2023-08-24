package com.google.android.gms.internal.vision;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzdi<E> extends zzdf<E> {
    static final zzdf<Object> zzlt = new zzdi(new Object[0], 0);
    private final transient int size;
    private final transient Object[] zzlu;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdi(Object[] objArr, int r2) {
        this.zzlu = objArr;
        this.size = r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzdc
    public final int zzca() {
        return 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.vision.zzdc
    public final Object[] zzbz() {
        return this.zzlu;
    }

    @Override // com.google.android.gms.internal.vision.zzdc
    final int zzcb() {
        return this.size;
    }

    @Override // com.google.android.gms.internal.vision.zzdf, com.google.android.gms.internal.vision.zzdc
    final int zza(Object[] objArr, int r5) {
        System.arraycopy(this.zzlu, 0, objArr, r5, this.size);
        return r5 + this.size;
    }

    @Override // java.util.List
    public final E get(int r2) {
        zzct.zzc(r2, this.size);
        return (E) this.zzlu[r2];
    }
}
