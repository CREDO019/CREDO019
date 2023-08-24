package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@Deprecated
/* loaded from: classes2.dex */
public final class zzgar {
    @Deprecated
    public static final zzgam zza(byte[] bArr) throws GeneralSecurityException {
        try {
            zzgjt zzg = zzgjt.zzg(bArr, zzgnz.zza());
            for (zzgjs zzgjsVar : zzg.zzh()) {
                if (zzgjsVar.zzc().zzi() == 2 || zzgjsVar.zzc().zzi() == 3 || zzgjsVar.zzc().zzi() == 4) {
                    throw new GeneralSecurityException("keyset contains secret key material");
                }
            }
            return zzgam.zza(zzg);
        } catch (zzgoz unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }
}
