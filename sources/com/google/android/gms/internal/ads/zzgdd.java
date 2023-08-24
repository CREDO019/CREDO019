package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgdd {
    public static final String zza;
    @Deprecated
    public static final zzgkp zzb;
    @Deprecated
    public static final zzgkp zzc;

    static {
        new zzgdc();
        zza = "type.googleapis.com/google.crypto.tink.AesSivKey";
        zzb = zzgkp.zzc();
        zzc = zzgkp.zzc();
        try {
            zzgbe.zzo(new zzgdf());
            if (zzgcz.zzb()) {
                return;
            }
            zzgbe.zzn(new zzgdc(), true);
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
