package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgee extends zzgeo {
    public zzgee() {
        super(zzgja.class, zzgjd.class, new zzgec(zzfzz.class));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzgek zzg(int r0, int r1, int r2, int r3) {
        zzgiw zza = zzgix.zza();
        zza.zzc(3);
        zza.zzb(3);
        zza.zza(r2);
        zzgit zza2 = zzgiu.zza();
        zza2.zza((zzgix) zza.zzal());
        return new zzgek((zzgiu) zza2.zzal(), r3);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final zzgel zza() {
        return new zzged(this, zzgiu.class);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzgja.zze(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final String zzc() {
        return "type.googleapis.com/google.crypto.tink.HpkePrivateKey";
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgja zzgjaVar = (zzgja) zzgpxVar;
        if (zzgjaVar.zzg().zzD()) {
            throw new GeneralSecurityException("Private key is empty.");
        }
        if (!zzgjaVar.zzk()) {
            throw new GeneralSecurityException("Missing public key.");
        }
        zzgmi.zzb(zzgjaVar.zza(), 0);
        zzgeh.zza(zzgjaVar.zzf().zzc());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final int zzf() {
        return 4;
    }
}
