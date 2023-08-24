package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgbs extends zzgem {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgbs() {
        super(zzgge.class, new zzgbq(zzfzs.class));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzgek zzg(int r1, int r2, int r3) {
        zzggg zzc = zzggh.zzc();
        zzc.zza(r1);
        zzggj zzc2 = zzggk.zzc();
        zzc2.zza(16);
        zzc.zzb((zzggk) zzc2.zzal());
        return new zzgek((zzggh) zzc.zzal(), r3);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final zzgel zza() {
        return new zzgbr(this, zzggh.class);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzgge.zze(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final String zzc() {
        return "type.googleapis.com/google.crypto.tink.AesEaxKey";
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgge zzggeVar = (zzgge) zzgpxVar;
        zzgmi.zzb(zzggeVar.zza(), 0);
        zzgmi.zza(zzggeVar.zzg().zzd());
        if (zzggeVar.zzf().zza() != 12 && zzggeVar.zzf().zza() != 16) {
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final int zzf() {
        return 3;
    }
}
