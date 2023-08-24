package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgdb extends zzgel {
    final /* synthetic */ zzgdc zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgdb(zzgdc zzgdcVar, Class cls) {
        super(cls);
        this.zza = zzgdcVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ zzgpx zza(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzggy zzc = zzggz.zzc();
        zzc.zza(zzgnf.zzv(zzgmg.zza(((zzghc) zzgpxVar).zza())));
        zzc.zzb(0);
        return (zzggz) zzc.zzal();
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzghc.zze(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        zzghb zzc = zzghc.zzc();
        zzc.zza(64);
        hashMap.put("AES256_SIV", new zzgek((zzghc) zzc.zzal(), 1));
        zzghb zzc2 = zzghc.zzc();
        zzc2.zza(64);
        hashMap.put("AES256_SIV_RAW", new zzgek((zzghc) zzc2.zzal(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzghc zzghcVar = (zzghc) zzgpxVar;
        if (zzghcVar.zza() == 64) {
            return;
        }
        int zza = zzghcVar.zza();
        throw new InvalidAlgorithmParameterException("invalid key size: " + zza + ". Valid keys must have 64 bytes.");
    }
}
