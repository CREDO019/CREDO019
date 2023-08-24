package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgey implements zzgaq {
    private final zzgba zza;
    private final byte[] zzb = {0};

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgey(zzgba zzgbaVar, zzgex zzgexVar) {
        this.zza = zzgbaVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgaq
    public final void zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        Logger logger;
        int length = bArr.length;
        if (length <= 5) {
            throw new GeneralSecurityException("tag too short");
        }
        byte[] copyOf = Arrays.copyOf(bArr, 5);
        byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, length);
        for (zzgau zzgauVar : this.zza.zzc(copyOf)) {
            try {
                if (zzgauVar.zzc() == 4) {
                    ((zzgaq) zzgauVar.zza()).zza(copyOfRange, zzgle.zzc(bArr2, this.zzb));
                    return;
                } else {
                    ((zzgaq) zzgauVar.zza()).zza(copyOfRange, bArr2);
                    return;
                }
            } catch (GeneralSecurityException e) {
                logger = zzgez.zza;
                logger.logp(Level.INFO, "com.google.crypto.tink.mac.MacWrapper$WrappedMac", "verifyMac", "tag prefix matches a key, but cannot verify: ".concat(e.toString()));
            }
        }
        for (zzgau zzgauVar2 : this.zza.zzc(zzfzx.zza)) {
            try {
                ((zzgaq) zzgauVar2.zza()).zza(bArr, bArr2);
                return;
            } catch (GeneralSecurityException unused) {
            }
        }
        throw new GeneralSecurityException("invalid MAC");
    }

    @Override // com.google.android.gms.internal.ads.zzgaq
    public final byte[] zzb(byte[] bArr) throws GeneralSecurityException {
        if (this.zza.zza().zzc() == 4) {
            return zzgle.zzc(this.zza.zza().zzb(), ((zzgaq) this.zza.zza().zza()).zzb(zzgle.zzc(bArr, this.zzb)));
        }
        return zzgle.zzc(this.zza.zza().zzb(), ((zzgaq) this.zza.zza().zza()).zzb(bArr));
    }
}
