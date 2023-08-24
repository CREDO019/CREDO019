package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgbm extends zzgem {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgbm() {
        super(zzgfp.class, new zzgbk(zzfzs.class));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzgek zzg(int r1, int r2, int r3, int r4, int r5, int r6) {
        zzgfx zzc = zzgfy.zzc();
        zzgga zzc2 = zzggb.zzc();
        zzc2.zza(16);
        zzc.zzb((zzggb) zzc2.zzal());
        zzc.zza(r1);
        zzgil zzc3 = zzgim.zzc();
        zzgio zzc4 = zzgip.zzc();
        zzc4.zzb(5);
        zzc4.zza(r4);
        zzc3.zzb((zzgip) zzc4.zzal());
        zzc3.zza(32);
        zzgfr zza = zzgfs.zza();
        zza.zza((zzgfy) zzc.zzal());
        zza.zzb((zzgim) zzc3.zzal());
        return new zzgek((zzgfs) zza.zzal(), r6);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final zzgel zza() {
        return new zzgbl(this, zzgfs.class);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzgfp.zze(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final String zzc() {
        return "type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey";
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgfp zzgfpVar = (zzgfp) zzgpxVar;
        zzgmi.zzb(zzgfpVar.zza(), 0);
        new zzgbp();
        zzgbp.zzh(zzgfpVar.zzf());
        new zzgev();
        zzgev.zzh(zzgfpVar.zzg());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final int zze() {
        return 2;
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final int zzf() {
        return 3;
    }
}
