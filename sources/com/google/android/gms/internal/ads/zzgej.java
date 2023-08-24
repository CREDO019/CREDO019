package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgej {
    private final zzfzs zza;
    private final zzfzy zzb;

    public zzgej(zzfzs zzfzsVar) {
        this.zza = zzfzsVar;
        this.zzb = null;
    }

    public zzgej(zzfzy zzfzyVar) {
        this.zza = null;
        this.zzb = zzfzyVar;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzfzs zzfzsVar = this.zza;
        return zzfzsVar != null ? zzfzsVar.zzb(bArr, bArr2) : this.zzb.zza(bArr, bArr2);
    }
}
