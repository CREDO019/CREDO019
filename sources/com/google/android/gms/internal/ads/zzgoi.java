package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgoi implements zzgpv {
    private static final zzgoi zza = new zzgoi();

    private zzgoi() {
    }

    public static zzgoi zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.ads.zzgpv
    public final zzgpu zzb(Class cls) {
        if (!zzgon.class.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Unsupported message type: ".concat(String.valueOf(cls.getName())));
        }
        try {
            return (zzgpu) zzgon.zzaA(cls.asSubclass(zzgon.class)).zzb(3, null, null);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get message info for ".concat(String.valueOf(cls.getName())), e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgpv
    public final boolean zzc(Class cls) {
        return zzgon.class.isAssignableFrom(cls);
    }
}
