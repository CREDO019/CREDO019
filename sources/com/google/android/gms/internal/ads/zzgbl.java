package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgbl extends zzgel {
    final /* synthetic */ zzgbm zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgbl(zzgbm zzgbmVar, Class cls) {
        super(cls);
        this.zza = zzgbmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ zzgpx zza(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgfs zzgfsVar = (zzgfs) zzgpxVar;
        new zzgbp();
        zzgfv zzf = zzgbo.zzf(zzgfsVar.zze());
        zzgpx zza = new zzgev().zza().zza(zzgfsVar.zzf());
        zzgfo zzc = zzgfp.zzc();
        zzc.zza(zzf);
        zzc.zzb((zzgij) zza);
        zzc.zzc(0);
        return (zzgfp) zzc.zzal();
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzgfs.zzd(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_CTR_HMAC_SHA256", zzgbm.zzg(16, 16, 32, 16, 5, 1));
        hashMap.put("AES128_CTR_HMAC_SHA256_RAW", zzgbm.zzg(16, 16, 32, 16, 5, 3));
        hashMap.put("AES256_CTR_HMAC_SHA256", zzgbm.zzg(32, 16, 32, 32, 5, 1));
        hashMap.put("AES256_CTR_HMAC_SHA256_RAW", zzgbm.zzg(32, 16, 32, 32, 5, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgfs zzgfsVar = (zzgfs) zzgpxVar;
        ((zzgbo) new zzgbp().zza()).zzd(zzgfsVar.zze());
        new zzgev().zza().zzd(zzgfsVar.zzf());
        zzgmi.zza(zzgfsVar.zze().zza());
    }
}
