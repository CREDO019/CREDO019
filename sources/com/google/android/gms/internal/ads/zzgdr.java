package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgdr {
    public static void zza(zzght zzghtVar) throws GeneralSecurityException {
        zzgln.zze(zzc(zzghtVar.zzf().zzg()));
        zzb(zzghtVar.zzf().zzh());
        if (zzghtVar.zzi() == 2) {
            throw new GeneralSecurityException("unknown EC point format");
        }
        zzgbe.zzc(zzghtVar.zza().zze());
    }

    public static String zzb(int r2) throws NoSuchAlgorithmException {
        int r0 = r2 - 2;
        if (r0 != 1) {
            if (r0 != 2) {
                if (r0 != 3) {
                    if (r0 != 4) {
                        if (r0 == 5) {
                            return "HmacSha224";
                        }
                        throw new NoSuchAlgorithmException("hash unsupported for HMAC: ".concat(Integer.toString(zzgig.zza(r2))));
                    }
                    return "HmacSha512";
                }
                return "HmacSha256";
            }
            return "HmacSha384";
        }
        return "HmacSha1";
    }

    public static int zzc(int r3) throws GeneralSecurityException {
        int r0 = r3 - 2;
        if (r0 != 2) {
            if (r0 != 3) {
                if (r0 == 4) {
                    return 3;
                }
                throw new GeneralSecurityException("unknown curve type: ".concat(Integer.toString(zzgie.zza(r3))));
            }
            return 2;
        }
        return 1;
    }

    public static int zzd(int r2) throws GeneralSecurityException {
        int r0 = r2 - 2;
        int r1 = 1;
        if (r0 != 1) {
            r1 = 2;
            if (r0 != 2) {
                if (r0 == 3) {
                    return 3;
                }
                throw new GeneralSecurityException("unknown point format: ".concat(Integer.toString(zzghk.zza(r2))));
            }
        }
        return r1;
    }
}
