package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzget extends zzgen {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzget(Class cls) {
        super(cls);
    }

    @Override // com.google.android.gms.internal.ads.zzgen
    public final /* bridge */ /* synthetic */ Object zza(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgij zzgijVar = (zzgij) zzgpxVar;
        int zzg = zzgijVar.zzg().zzg();
        SecretKeySpec secretKeySpec = new SecretKeySpec(zzgijVar.zzh().zzE(), "HMAC");
        int zza = zzgijVar.zzg().zza();
        int r0 = zzg - 2;
        if (r0 != 1) {
            if (r0 != 2) {
                if (r0 != 3) {
                    if (r0 != 4) {
                        if (r0 == 5) {
                            return new zzgme(new zzgmd("HMACSHA224", secretKeySpec), zza);
                        }
                        throw new GeneralSecurityException("unknown hash");
                    }
                    return new zzgme(new zzgmd("HMACSHA512", secretKeySpec), zza);
                }
                return new zzgme(new zzgmd("HMACSHA256", secretKeySpec), zza);
            }
            return new zzgme(new zzgmd("HMACSHA384", secretKeySpec), zza);
        }
        return new zzgme(new zzgmd("HMACSHA1", secretKeySpec), zza);
    }
}
