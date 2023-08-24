package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgdz implements zzgaa {
    private static final byte[] zza = new byte[0];
    private final zzgjd zzb;
    private final zzgdw zzc;
    private final zzgei zzd;
    private final zzgdv zze;

    private zzgdz(zzgjd zzgjdVar, zzgei zzgeiVar, zzgdv zzgdvVar, zzgdw zzgdwVar, byte[] bArr) {
        this.zzb = zzgjdVar;
        this.zzd = zzgeiVar;
        this.zze = zzgdvVar;
        this.zzc = zzgdwVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgdz zzb(zzgjd zzgjdVar) throws GeneralSecurityException {
        if (zzgjdVar.zzh().zzD()) {
            throw new IllegalArgumentException("HpkePublicKey.public_key is empty.");
        }
        zzgix zzc = zzgjdVar.zzc();
        return new zzgdz(zzgjdVar, zzgeb.zzc(zzc), zzgeb.zzb(zzc), zzgeb.zza(zzc), null);
    }

    @Override // com.google.android.gms.internal.ads.zzgaa
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        byte[] bArr3 = bArr2;
        zzgjd zzgjdVar = this.zzb;
        zzgei zzgeiVar = this.zzd;
        zzgdv zzgdvVar = this.zze;
        zzgdw zzgdwVar = this.zzc;
        zzgea zza2 = zzgeiVar.zza(zzgjdVar.zzh().zzE(), zzgmj.zzb());
        zzgdx zzc = zzgdx.zzc(zza2.zza(), zza2.zzb(), zzgeiVar, zzgdvVar, zzgdwVar, bArr3);
        return zzgle.zzc(zzc.zza(), zzc.zzb(bArr, zza));
    }
}
