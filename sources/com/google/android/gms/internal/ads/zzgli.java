package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgli implements zzfzz {
    private final ECPrivateKey zza;
    private final zzglk zzb;
    private final String zzc;
    private final byte[] zzd;
    private final zzglh zze;

    public zzgli(ECPrivateKey eCPrivateKey, byte[] bArr, String str, int r4, zzglh zzglhVar) throws GeneralSecurityException {
        this.zza = eCPrivateKey;
        this.zzb = new zzglk(eCPrivateKey);
        this.zzd = bArr;
        this.zzc = str;
        this.zze = zzglhVar;
    }
}
