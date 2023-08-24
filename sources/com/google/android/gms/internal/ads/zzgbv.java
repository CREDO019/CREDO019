package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgbv extends zzgem {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgbv() {
        super(zzggn.class, new zzgbt(zzfzs.class));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzgek zzg(int r1, int r2) {
        zzggp zzc = zzggq.zzc();
        zzc.zza(r1);
        return new zzgek((zzggq) zzc.zzal(), r2);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final zzgel zza() {
        return new zzgbu(this, zzggq.class);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzggn.zze(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final String zzc() {
        return "type.googleapis.com/google.crypto.tink.AesGcmKey";
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzggn zzggnVar = (zzggn) zzgpxVar;
        zzgmi.zzb(zzggnVar.zza(), 0);
        zzgmi.zza(zzggnVar.zzf().zzd());
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
