package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgbg {
    public static final String zza;
    public static final String zzb;
    @Deprecated
    public static final zzgkp zzc;
    @Deprecated
    public static final zzgkp zzd;
    @Deprecated
    public static final zzgkp zze;

    static {
        new zzgbm();
        zza = "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
        new zzgbv();
        zzb = "type.googleapis.com/google.crypto.tink.AesGcmKey";
        new zzgby();
        new zzgbs();
        new zzgce();
        new zzgci();
        new zzgcb();
        new zzgcl();
        zzgkp zzc2 = zzgkp.zzc();
        zzc = zzc2;
        zzd = zzc2;
        zze = zzc2;
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzgbe.zzo(new zzgbj());
        zzgew.zza();
        zzgbe.zzn(new zzgbm(), true);
        zzgbe.zzn(new zzgbv(), true);
        if (zzgcz.zzb()) {
            return;
        }
        zzgbe.zzn(new zzgbs(), true);
        zzgby.zzg(true);
        zzgbe.zzn(new zzgcb(), true);
        zzgbe.zzn(new zzgce(), true);
        zzgbe.zzn(new zzgci(), true);
        zzgbe.zzn(new zzgcl(), true);
    }
}
