package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgdh extends zzgel {
    final /* synthetic */ zzgdi zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgdh(zzgdi zzgdiVar, Class cls) {
        super(cls);
        this.zza = zzgdiVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ zzgpx zza(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzghq zzghqVar = (zzghq) zzgpxVar;
        KeyPair zzb = zzgln.zzb(zzgln.zze(zzgdr.zzc(zzghqVar.zze().zzf().zzg())));
        ECPoint w = ((ECPublicKey) zzb.getPublic()).getW();
        zzghy zzd = zzghz.zzd();
        zzd.zzb(0);
        zzd.zza(zzghqVar.zze());
        zzd.zzc(zzgnf.zzv(w.getAffineX().toByteArray()));
        zzd.zzd(zzgnf.zzv(w.getAffineY().toByteArray()));
        zzghv zzc = zzghw.zzc();
        zzc.zzc(0);
        zzc.zzb((zzghz) zzd.zzal());
        zzc.zza(zzgnf.zzv(((ECPrivateKey) zzb.getPrivate()).getS().toByteArray()));
        return (zzghw) zzc.zzal();
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzghq.zzd(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final Map zzc() throws GeneralSecurityException {
        byte[] bArr;
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArr4;
        byte[] bArr5;
        byte[] bArr6;
        byte[] bArr7;
        byte[] bArr8;
        byte[] bArr9;
        HashMap hashMap = new HashMap();
        zzgak zza = zzgal.zza("AES128_GCM");
        bArr = zzgdi.zza;
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM", zzgdi.zzh(4, 5, 3, zza, bArr, 1));
        zzgak zza2 = zzgal.zza("AES128_GCM");
        bArr2 = zzgdi.zza;
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_RAW", zzgdi.zzh(4, 5, 3, zza2, bArr2, 3));
        zzgak zza3 = zzgal.zza("AES128_GCM");
        bArr3 = zzgdi.zza;
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM", zzgdi.zzh(4, 5, 4, zza3, bArr3, 1));
        zzgak zza4 = zzgal.zza("AES128_GCM");
        bArr4 = zzgdi.zza;
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_GCM_RAW", zzgdi.zzh(4, 5, 4, zza4, bArr4, 3));
        zzgak zza5 = zzgal.zza("AES128_GCM");
        bArr5 = zzgdi.zza;
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_GCM_COMPRESSED_WITHOUT_PREFIX", zzgdi.zzh(4, 5, 4, zza5, bArr5, 3));
        zzgak zza6 = zzgal.zza("AES128_CTR_HMAC_SHA256");
        bArr6 = zzgdi.zza;
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zzgdi.zzh(4, 5, 3, zza6, bArr6, 1));
        zzgak zza7 = zzgal.zza("AES128_CTR_HMAC_SHA256");
        bArr7 = zzgdi.zza;
        hashMap.put("ECIES_P256_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzgdi.zzh(4, 5, 3, zza7, bArr7, 3));
        zzgak zza8 = zzgal.zza("AES128_CTR_HMAC_SHA256");
        bArr8 = zzgdi.zza;
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256", zzgdi.zzh(4, 5, 4, zza8, bArr8, 1));
        zzgak zza9 = zzgal.zza("AES128_CTR_HMAC_SHA256");
        bArr9 = zzgdi.zza;
        hashMap.put("ECIES_P256_COMPRESSED_HKDF_HMAC_SHA256_AES128_CTR_HMAC_SHA256_RAW", zzgdi.zzh(4, 5, 4, zza9, bArr9, 3));
        return Collections.unmodifiableMap(hashMap);
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgdr.zza(((zzghq) zzgpxVar).zze());
    }
}
