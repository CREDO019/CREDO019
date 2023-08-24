package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgbi implements zzfzs {
    private final zzgba zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgbi(zzgba zzgbaVar, zzgbh zzgbhVar) {
        this.zza = zzgbaVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfzs
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Logger logger;
        int length = bArr.length;
        if (length > 5) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 5);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 5, length);
            for (zzgau zzgauVar : this.zza.zzc(copyOfRange)) {
                try {
                    return ((zzfzs) zzgauVar.zza()).zza(copyOfRange2, bArr2);
                } catch (GeneralSecurityException e) {
                    logger = zzgbj.zza;
                    logger.logp(Level.INFO, "com.google.crypto.tink.aead.AeadWrapper$WrappedAead", "decrypt", "ciphertext prefix matches a key, but cannot decrypt: ".concat(String.valueOf(e.toString())));
                }
            }
        }
        for (zzgau zzgauVar2 : this.zza.zzc(zzfzx.zza)) {
            try {
                return ((zzfzs) zzgauVar2.zza()).zza(bArr, bArr2);
            } catch (GeneralSecurityException unused) {
            }
        }
        throw new GeneralSecurityException("decryption failed");
    }

    @Override // com.google.android.gms.internal.ads.zzfzs
    public final byte[] zzb(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return zzgle.zzc(this.zza.zza().zzb(), ((zzfzs) this.zza.zza().zza()).zzb(bArr, bArr2));
    }
}
