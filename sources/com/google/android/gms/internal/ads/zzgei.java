package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgei {
    private final zzgdv zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgei(zzgdv zzgdvVar) {
        this.zza = zzgdvVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzgea zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] zza = zzgmj.zza(bArr2, bArr);
        byte[] zzc = zzgmj.zzc(bArr2);
        byte[] zzc2 = zzgle.zzc(zzc, bArr);
        byte[] zzd = zzgeh.zzd(zzgeh.zzb);
        zzgdv zzgdvVar = this.zza;
        return new zzgea(zzgdvVar.zzb(null, zza, "eae_prk", zzc2, "shared_secret", zzd, zzgdvVar.zza()), zzc);
    }
}
