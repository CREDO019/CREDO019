package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgbp extends zzgem {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgbp() {
        super(zzgfv.class, new zzgbn(zzgma.class));
    }

    public static final void zzh(zzgfv zzgfvVar) throws GeneralSecurityException {
        zzgmi.zzb(zzgfvVar.zza(), 0);
        zzgmi.zza(zzgfvVar.zzh().zzd());
        zzm(zzgfvVar.zzg());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void zzm(zzggb zzggbVar) throws GeneralSecurityException {
        if (zzggbVar.zza() < 12 || zzggbVar.zza() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final zzgel zza() {
        return new zzgbo(this, zzgfy.class);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzgfv.zzf(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final String zzc() {
        return "type.googleapis.com/google.crypto.tink.AesCtrKey";
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzh((zzgfv) zzgpxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final int zzf() {
        return 3;
    }
}
