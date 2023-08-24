package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgev extends zzgem {
    public zzgev() {
        super(zzgij.class, new zzget(zzgaq.class));
    }

    public static final void zzh(zzgij zzgijVar) throws GeneralSecurityException {
        zzgmi.zzb(zzgijVar.zza(), 0);
        if (zzgijVar.zzh().zzd() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        zzn(zzgijVar.zzg());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzgek zzm(int r3, int r4, int r5, int r6) {
        zzgil zzc = zzgim.zzc();
        zzgio zzc2 = zzgip.zzc();
        zzc2.zzb(r5);
        zzc2.zza(r4);
        zzc.zzb((zzgip) zzc2.zzal());
        zzc.zza(r3);
        return new zzgek((zzgim) zzc.zzal(), r6);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzn(zzgip zzgipVar) throws GeneralSecurityException {
        if (zzgipVar.zza() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        int zzg = zzgipVar.zzg() - 2;
        if (zzg == 1) {
            if (zzgipVar.zza() > 20) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (zzg == 2) {
            if (zzgipVar.zza() > 48) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (zzg == 3) {
            if (zzgipVar.zza() > 32) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (zzg == 4) {
            if (zzgipVar.zza() > 64) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else if (zzg == 5) {
            if (zzgipVar.zza() > 28) {
                throw new GeneralSecurityException("tag size too big");
            }
        } else {
            throw new GeneralSecurityException("unknown hash type");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final zzgel zza() {
        return new zzgeu(this, zzgim.class);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzgij.zzf(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final String zzc() {
        return "type.googleapis.com/google.crypto.tink.HmacKey";
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzh((zzgij) zzgpxVar);
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
