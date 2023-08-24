package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgde implements zzfzy {
    private final zzgba zza;

    public zzgde(zzgba zzgbaVar) {
        this.zza = zzgbaVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfzy
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return zzgle.zzc(this.zza.zza().zzb(), ((zzfzy) this.zza.zza().zza()).zza(bArr, bArr2));
    }
}
