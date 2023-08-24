package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgdy implements zzfzz {
    private final zzgja zza;
    private final zzgdw zzb;
    private final zzgei zzc;
    private final zzgdv zzd;

    private zzgdy(zzgja zzgjaVar, zzgei zzgeiVar, zzgdv zzgdvVar, zzgdw zzgdwVar, int r5, byte[] bArr) {
        this.zza = zzgjaVar;
        this.zzc = zzgeiVar;
        this.zzd = zzgdvVar;
        this.zzb = zzgdwVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgdy zza(zzgja zzgjaVar) throws GeneralSecurityException {
        if (!zzgjaVar.zzk()) {
            throw new IllegalArgumentException("HpkePrivateKey is missing public_key field.");
        }
        if (!zzgjaVar.zzf().zzl()) {
            throw new IllegalArgumentException("HpkePrivateKey.public_key is missing params field.");
        }
        if (zzgjaVar.zzg().zzD()) {
            throw new IllegalArgumentException("HpkePrivateKey.private_key is empty.");
        }
        zzgix zzc = zzgjaVar.zzf().zzc();
        zzgei zzc2 = zzgeb.zzc(zzc);
        zzgdv zzb = zzgeb.zzb(zzc);
        zzgdw zza = zzgeb.zza(zzc);
        int zzg = zzc.zzg();
        if (zzg - 2 == 1) {
            return new zzgdy(zzgjaVar, zzc2, zzb, zza, 32, null);
        }
        throw new IllegalArgumentException("Unable to determine KEM-encoding length for ".concat(zzgir.zza(zzg)));
    }
}
