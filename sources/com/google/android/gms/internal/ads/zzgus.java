package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgus implements zzgur, zzgul {
    private static final zzgus zza = new zzgus(null);
    private final Object zzb;

    private zzgus(Object obj) {
        this.zzb = obj;
    }

    public static zzgur zza(Object obj) {
        zzguz.zza(obj, "instance cannot be null");
        return new zzgus(obj);
    }

    public static zzgur zzc(Object obj) {
        return obj == null ? zza : new zzgus(obj);
    }

    @Override // com.google.android.gms.internal.ads.zzgve
    public final Object zzb() {
        return this.zzb;
    }
}
