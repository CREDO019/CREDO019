package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgeb {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgdw zza(zzgix zzgixVar) throws GeneralSecurityException {
        if (zzgixVar.zze() == 3) {
            return new zzgdt(16);
        }
        if (zzgixVar.zze() == 4) {
            return new zzgdt(32);
        }
        if (zzgixVar.zze() == 5) {
            return new zzgdu();
        }
        throw new IllegalArgumentException("Unrecognized HPKE AEAD identifier");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgdv zzb(zzgix zzgixVar) {
        if (zzgixVar.zzf() == 3) {
            return new zzgdv("HmacSha256");
        }
        throw new IllegalArgumentException("Unrecognized HPKE KDF identifier");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgei zzc(zzgix zzgixVar) {
        if (zzgixVar.zzg() == 3) {
            return new zzgei(new zzgdv("HmacSha256"));
        }
        throw new IllegalArgumentException("Unrecognized HPKE KEM identifier");
    }
}
