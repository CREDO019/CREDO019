package com.google.android.gms.internal.ads;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfwe extends zzfuv {
    static final zzfuv zza = new zzfwe(new Object[0], 0);
    final transient Object[] zzb;
    private final transient int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfwe(Object[] objArr, int r2) {
        this.zzb = objArr;
        this.zzc = r2;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzfsf.zza(r3, this.zzc, "index");
        Object obj = this.zzb[r3];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzfuv, com.google.android.gms.internal.ads.zzfuq
    final int zza(Object[] objArr, int r5) {
        System.arraycopy(this.zzb, 0, objArr, r5, this.zzc);
        return r5 + this.zzc;
    }

    @Override // com.google.android.gms.internal.ads.zzfuq
    final int zzb() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzfuq
    public final int zzc() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzfuq
    public final boolean zzf() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzfuq
    public final Object[] zzg() {
        return this.zzb;
    }
}
