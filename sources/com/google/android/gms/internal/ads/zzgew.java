package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgew {
    @Deprecated
    public static final zzgkp zza;
    @Deprecated
    public static final zzgkp zzb;
    @Deprecated
    public static final zzgkp zzc;

    static {
        new zzgev();
        zzgkp zzc2 = zzgkp.zzc();
        zza = zzc2;
        zzb = zzc2;
        zzc = zzc2;
        try {
            zza();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void zza() throws GeneralSecurityException {
        zzgbe.zzo(new zzgez());
        zzgbe.zzn(new zzgev(), true);
        if (zzgcz.zzb()) {
            return;
        }
        zzgbe.zzn(new zzges(), true);
    }
}
