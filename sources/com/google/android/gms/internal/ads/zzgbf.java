package com.google.android.gms.internal.ads;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgbf {
    public static final Charset zza = Charset.forName("UTF-8");

    public static zzgjy zza(zzgjt zzgjtVar) {
        zzgjv zza2 = zzgjy.zza();
        zza2.zzb(zzgjtVar.zzc());
        for (zzgjs zzgjsVar : zzgjtVar.zzh()) {
            zzgjw zza3 = zzgjx.zza();
            zza3.zzb(zzgjsVar.zzc().zzf());
            zza3.zzd(zzgjsVar.zzi());
            zza3.zzc(zzgjsVar.zzj());
            zza3.zza(zzgjsVar.zza());
            zza2.zza((zzgjx) zza3.zzal());
        }
        return (zzgjy) zza2.zzal();
    }

    public static void zzb(zzgjt zzgjtVar) throws GeneralSecurityException {
        int zzc = zzgjtVar.zzc();
        int r3 = 0;
        boolean z = false;
        boolean z2 = true;
        for (zzgjs zzgjsVar : zzgjtVar.zzh()) {
            if (zzgjsVar.zzi() == 3) {
                if (zzgjsVar.zzh()) {
                    if (zzgjsVar.zzj() != 2) {
                        if (zzgjsVar.zzi() != 2) {
                            if (zzgjsVar.zza() == zzc) {
                                if (z) {
                                    throw new GeneralSecurityException("keyset contains multiple primary keys");
                                }
                                z = true;
                            }
                            z2 &= zzgjsVar.zzc().zzi() == 5;
                            r3++;
                        } else {
                            throw new GeneralSecurityException(String.format("key %d has unknown status", Integer.valueOf(zzgjsVar.zza())));
                        }
                    } else {
                        throw new GeneralSecurityException(String.format("key %d has unknown prefix", Integer.valueOf(zzgjsVar.zza())));
                    }
                } else {
                    throw new GeneralSecurityException(String.format("key %d has no key data", Integer.valueOf(zzgjsVar.zza())));
                }
            }
        }
        if (r3 == 0) {
            throw new GeneralSecurityException("keyset must contain at least one ENABLED key");
        }
        if (!z && !z2) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
