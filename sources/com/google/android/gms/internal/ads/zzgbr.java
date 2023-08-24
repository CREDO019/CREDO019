package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgbr extends zzgel {
    final /* synthetic */ zzgbs zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgbr(zzgbs zzgbsVar, Class cls) {
        super(cls);
        this.zza = zzgbsVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ zzgpx zza(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzggh zzgghVar = (zzggh) zzgpxVar;
        zzggd zzc = zzgge.zzc();
        zzc.zza(zzgnf.zzv(zzgmg.zza(zzgghVar.zza())));
        zzc.zzb(zzgghVar.zzf());
        zzc.zzc(0);
        return (zzgge) zzc.zzal();
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzggh.zze(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        hashMap.put("AES128_EAX", zzgbs.zzg(16, 16, 1));
        hashMap.put("AES128_EAX_RAW", zzgbs.zzg(16, 16, 3));
        hashMap.put("AES256_EAX", zzgbs.zzg(32, 16, 1));
        hashMap.put("AES256_EAX_RAW", zzgbs.zzg(32, 16, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzggh zzgghVar = (zzggh) zzgpxVar;
        zzgmi.zza(zzgghVar.zza());
        if (zzgghVar.zzf().zza() != 12 && zzgghVar.zzf().zza() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }
}
