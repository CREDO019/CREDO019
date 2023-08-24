package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgbx extends zzgel {
    final /* synthetic */ zzgby zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgbx(zzgby zzgbyVar, Class cls) {
        super(cls);
        this.zza = zzgbyVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ zzgpx zza(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzggs zzc = zzggt.zzc();
        zzc.zza(zzgnf.zzv(zzgmg.zza(((zzggw) zzgpxVar).zza())));
        zzc.zzb(0);
        return (zzggt) zzc.zzal();
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzggw.zze(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_GCM_SIV", zzgby.zzh(16, 1));
        hashMap.put("AES128_GCM_SIV_RAW", zzgby.zzh(16, 3));
        hashMap.put("AES256_GCM_SIV", zzgby.zzh(32, 1));
        hashMap.put("AES256_GCM_SIV_RAW", zzgby.zzh(32, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgmi.zza(((zzggw) zzgpxVar).zza());
    }
}
