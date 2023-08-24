package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgdc extends zzgem {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgdc() {
        super(zzggz.class, new zzgda(zzfzy.class));
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final zzgel zza() {
        return new zzgdb(this, zzghc.class);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzggz.zze(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final String zzc() {
        return "type.googleapis.com/google.crypto.tink.AesSivKey";
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzggz zzggzVar = (zzggz) zzgpxVar;
        zzgmi.zzb(zzggzVar.zza(), 0);
        if (zzggzVar.zzf().zzd() == 64) {
            return;
        }
        int zzd = zzggzVar.zzf().zzd();
        throw new InvalidKeyException("invalid key size: " + zzd + ". Valid keys must have 64 bytes.");
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final int zzf() {
        return 3;
    }
}
