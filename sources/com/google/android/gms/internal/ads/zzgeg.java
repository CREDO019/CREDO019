package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgeg extends zzgem {
    public zzgeg() {
        super(zzgjd.class, new zzgef(zzgaa.class));
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzgjd.zzg(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final String zzc() {
        return "type.googleapis.com/google.crypto.tink.HpkePublicKey";
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgjd zzgjdVar = (zzgjd) zzgpxVar;
        zzgmi.zzb(zzgjdVar.zza(), 0);
        if (!zzgjdVar.zzl()) {
            throw new GeneralSecurityException("Missing HPKE key params.");
        }
        zzgeh.zza(zzgjdVar.zzc());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final int zzf() {
        return 5;
    }
}
