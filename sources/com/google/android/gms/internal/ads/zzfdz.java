package com.google.android.gms.internal.ads;

import android.util.Base64;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfdz {
    public zzfdz() {
        try {
            zzgbg.zza();
        } catch (GeneralSecurityException e) {
            com.google.android.gms.ads.internal.util.zze.zza("Failed to Configure Aead. ".concat(e.toString()));
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "CryptoUtils.registerAead");
        }
    }

    public static final String zza() {
        zzgnc zzt = zzgnf.zzt();
        try {
            zzfzw.zzb(zzgam.zzb(zzgal.zza("AES128_GCM")), zzfzu.zzb(zzt));
        } catch (IOException | GeneralSecurityException e) {
            com.google.android.gms.ads.internal.util.zze.zza("Failed to generate key".concat(e.toString()));
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "CryptoUtils.generateKey");
        }
        String encodeToString = Base64.encodeToString(zzt.zzb().zzE(), 11);
        zzt.zzc();
        return encodeToString;
    }

    @Nullable
    public static final String zzb(byte[] bArr, byte[] bArr2, String str, zzdxj zzdxjVar) {
        zzgam zzc = zzc(str);
        if (zzc == null) {
            return null;
        }
        try {
            byte[] zza = ((zzfzs) zzc.zzd(zzfzs.class)).zza(bArr, bArr2);
            zzdxjVar.zza().put("ds", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            return new String(zza, "UTF-8");
        } catch (UnsupportedEncodingException | GeneralSecurityException e) {
            com.google.android.gms.ads.internal.util.zze.zza("Failed to decrypt ".concat(e.toString()));
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "CryptoUtils.decrypt");
            zzdxjVar.zza().put("df", e.toString());
            return null;
        }
    }

    @Nullable
    private static final zzgam zzc(String str) {
        try {
            return zzfzw.zza(zzfzt.zzb(Base64.decode(str, 11)));
        } catch (IOException | GeneralSecurityException e) {
            com.google.android.gms.ads.internal.util.zze.zza("Failed to get keysethandle".concat(e.toString()));
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "CryptoUtils.getHandle");
            return null;
        }
    }
}
