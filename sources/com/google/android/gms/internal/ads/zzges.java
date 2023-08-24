package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzges extends zzgem {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzges() {
        super(zzgfg.class, new zzgeq(zzgaq.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzm(zzgfm zzgfmVar) throws GeneralSecurityException {
        if (zzgfmVar.zza() < 10) {
            throw new GeneralSecurityException("tag size too short");
        }
        if (zzgfmVar.zza() > 16) {
            throw new GeneralSecurityException("tag size too long");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzn(int r1) throws GeneralSecurityException {
        if (r1 != 32) {
            throw new GeneralSecurityException("AesCmacKey size wrong, must be 32 bytes");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final zzgel zza() {
        return new zzger(this, zzgfj.class);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzgfg.zze(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final String zzc() {
        return "type.googleapis.com/google.crypto.tink.AesCmacKey";
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgfg zzgfgVar = (zzgfg) zzgpxVar;
        zzgmi.zzb(zzgfgVar.zza(), 0);
        zzn(zzgfgVar.zzg().zzd());
        zzm(zzgfgVar.zzf());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final int zzf() {
        return 3;
    }
}
