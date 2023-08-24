package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzger extends zzgel {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzger(zzges zzgesVar, Class cls) {
        super(cls);
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ zzgpx zza(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgfj zzgfjVar = (zzgfj) zzgpxVar;
        zzgff zzc = zzgfg.zzc();
        zzc.zzc(0);
        zzc.zza(zzgnf.zzv(zzgmg.zza(zzgfjVar.zza())));
        zzc.zzb(zzgfjVar.zzf());
        return (zzgfg) zzc.zzal();
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzgfj.zze(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final Map zzc() throws GeneralSecurityException {
        HashMap hashMap = new HashMap();
        zzgfi zzc = zzgfj.zzc();
        zzc.zza(32);
        zzgfl zzc2 = zzgfm.zzc();
        zzc2.zza(16);
        zzc.zzb((zzgfm) zzc2.zzal());
        hashMap.put("AES_CMAC", new zzgek((zzgfj) zzc.zzal(), 1));
        zzgfi zzc3 = zzgfj.zzc();
        zzc3.zza(32);
        zzgfl zzc4 = zzgfm.zzc();
        zzc4.zza(16);
        zzc3.zzb((zzgfm) zzc4.zzal());
        hashMap.put("AES256_CMAC", new zzgek((zzgfj) zzc3.zzal(), 1));
        zzgfi zzc5 = zzgfj.zzc();
        zzc5.zza(32);
        zzgfl zzc6 = zzgfm.zzc();
        zzc6.zza(16);
        zzc5.zzb((zzgfm) zzc6.zzal());
        hashMap.put("AES256_CMAC_RAW", new zzgek((zzgfj) zzc5.zzal(), 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgfj zzgfjVar = (zzgfj) zzgpxVar;
        zzges.zzm(zzgfjVar.zzf());
        zzges.zzn(zzgfjVar.zza());
    }
}
