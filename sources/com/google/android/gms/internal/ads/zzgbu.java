package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgbu extends zzgel {
    final /* synthetic */ zzgbv zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgbu(zzgbv zzgbvVar, Class cls) {
        super(cls);
        this.zza = zzgbvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ zzgpx zza(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzggm zzc = zzggn.zzc();
        zzc.zza(zzgnf.zzv(zzgmg.zza(((zzggq) zzgpxVar).zza())));
        zzc.zzb(0);
        return (zzggn) zzc.zzal();
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzggq.zze(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_GCM", zzgbv.zzg(16, 1));
        hashMap.put("AES128_GCM_RAW", zzgbv.zzg(16, 3));
        hashMap.put("AES256_GCM", zzgbv.zzg(32, 1));
        hashMap.put("AES256_GCM_RAW", zzgbv.zzg(32, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgmi.zza(((zzggq) zzgpxVar).zza());
    }
}
