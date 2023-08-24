package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgdl {
    @Deprecated
    public static final zzgkp zza;
    @Deprecated
    public static final zzgkp zzb;
    @Deprecated
    public static final zzgkp zzc;

    static {
        new zzgdk();
        new zzgdi();
        zza = zzgkp.zzc();
        zzb = zzgkp.zzc();
        zzc = zzgkp.zzc();
        try {
            zzgbe.zzo(new zzgdn());
            zzgbe.zzo(new zzgdq());
            zzgbg.zza();
            if (zzgcz.zzb()) {
                return;
            }
            zzgbe.zzl(new zzgdi(), new zzgdk(), true);
            zzgbe.zzl(new zzgee(), new zzgeg(), true);
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
