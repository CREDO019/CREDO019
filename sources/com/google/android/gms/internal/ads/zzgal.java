package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgal {
    public static zzgak zza(String str) throws GeneralSecurityException {
        if (!zzgbe.zzk().containsKey(str)) {
            throw new GeneralSecurityException("cannot find key template: ".concat(str));
        }
        return (zzgak) zzgbe.zzk().get(str);
    }
}
