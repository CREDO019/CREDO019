package com.google.android.gms.internal.play_billing;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.android.billingclient:billing@@4.0.0 */
/* loaded from: classes3.dex */
public final class zzq<E> extends zzp<E> {
    static final zzp<Object> zza = new zzq(new Object[0], 0);
    final transient Object[] zzb;

    zzq(Object[] objArr, int r2) {
        this.zzb = objArr;
    }

    @Override // java.util.List
    public final E get(int r3) {
        zzj.zza(r3, 0, "index");
        return (E) this.zzb[r3];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzp, com.google.android.gms.internal.play_billing.zzm
    final int zza(Object[] objArr, int r3) {
        System.arraycopy(this.zzb, 0, objArr, 0, 0);
        return 0;
    }

    @Override // com.google.android.gms.internal.play_billing.zzm
    final int zzb() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzm
    public final int zzc() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.play_billing.zzm
    public final Object[] zze() {
        return this.zzb;
    }
}
