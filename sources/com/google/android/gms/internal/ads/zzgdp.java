package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgdp implements zzgaa {
    final zzgba zza;

    public zzgdp(zzgba zzgbaVar) {
        this.zza = zzgbaVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgaa
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzgba zzgbaVar = this.zza;
        if (zzgbaVar.zza() == null) {
            throw new GeneralSecurityException("keyset without primary key");
        }
        return zzgle.zzc(zzgbaVar.zza().zzb(), ((zzgaa) this.zza.zza().zza()).zza(bArr, bArr2));
    }
}
