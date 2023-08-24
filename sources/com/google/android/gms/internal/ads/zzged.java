package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzged extends zzgel {
    final /* synthetic */ zzgee zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzged(zzgee zzgeeVar, Class cls) {
        super(cls);
        this.zza = zzgeeVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ zzgpx zza(zzgpx zzgpxVar) throws GeneralSecurityException {
        byte[] zzb = zzgmj.zzb();
        byte[] zzc = zzgmj.zzc(zzb);
        zzgjc zzd = zzgjd.zzd();
        zzd.zzc(0);
        zzd.zza(((zzgiu) zzgpxVar).zze());
        zzd.zzb(zzgnf.zzv(zzc));
        zzgiz zzc2 = zzgja.zzc();
        zzc2.zzc(0);
        zzc2.zzb((zzgjd) zzd.zzal());
        zzc2.zza(zzgnf.zzv(zzb));
        return (zzgja) zzc2.zzal();
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzgiu.zzd(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final Map zzc() {
        HashMap hashMap = new HashMap();
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM", zzgee.zzg(3, 3, 3, 1));
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_128_GCM_RAW", zzgee.zzg(3, 3, 3, 3));
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM", zzgee.zzg(3, 3, 4, 1));
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_AES_256_GCM_RAW", zzgee.zzg(3, 3, 4, 3));
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305", zzgee.zzg(3, 3, 5, 1));
        hashMap.put("DHKEM_X25519_HKDF_SHA256_HKDF_SHA256_CHACHA20_POLY1305_RAW", zzgee.zzg(3, 3, 5, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgeh.zza(((zzgiu) zzgpxVar).zze());
    }
}
