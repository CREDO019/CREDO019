package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgam {
    private final zzgjt zza;

    private zzgam(zzgjt zzgjtVar) {
        this.zza = zzgjtVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final zzgam zza(zzgjt zzgjtVar) throws GeneralSecurityException {
        if (zzgjtVar == null || zzgjtVar.zza() <= 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        return new zzgam(zzgjtVar);
    }

    public static final zzgam zzb(zzgak zzgakVar) throws GeneralSecurityException {
        zzgan zzd = zzgan.zzd();
        zzd.zzc(zzgakVar.zza());
        return zzd.zzb();
    }

    public final String toString() {
        return zzgbf.zza(this.zza).toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzgjt zzc() {
        return this.zza;
    }

    public final Object zzd(Class cls) throws GeneralSecurityException {
        Class zze = zzgbe.zze(cls);
        if (zze == null) {
            throw new GeneralSecurityException("No wrapper found for ".concat(String.valueOf(cls.getName())));
        }
        zzgbf.zzb(this.zza);
        zzgat zzgatVar = new zzgat(zze, null);
        for (zzgjs zzgjsVar : this.zza.zzh()) {
            if (zzgjsVar.zzi() == 3) {
                Object zzf = zzgbe.zzf(zzgjsVar.zzc(), zze);
                if (zzgjsVar.zza() == this.zza.zzc()) {
                    zzgatVar.zza(zzf, zzgjsVar);
                } else {
                    zzgatVar.zzb(zzf, zzgjsVar);
                }
            }
        }
        return zzgbe.zzj(zzgatVar.zzc(), cls);
    }
}
