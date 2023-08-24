package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfwi extends zzfuv {
    private final transient Object[] zza;
    private final transient int zzb;
    private final transient int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfwi(Object[] objArr, int r2, int r3) {
        this.zza = objArr;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.util.List
    public final Object get(int r3) {
        zzfsf.zza(r3, this.zzc, "index");
        Object obj = this.zza[r3 + r3 + this.zzb];
        obj.getClass();
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzfuq
    public final boolean zzf() {
        return true;
    }
}
