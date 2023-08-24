package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgdi extends zzgeo {
    private static final byte[] zza = new byte[0];

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgdi() {
        super(zzghw.class, zzghz.class, new zzgdg(zzfzz.class));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ zzgek zzh(int r4, int r5, int r6, zzgak zzgakVar, byte[] bArr, int r9) {
        zzghp zza2 = zzghq.zza();
        zzgib zza3 = zzgic.zza();
        int r1 = 4;
        zza3.zzb(4);
        zza3.zzc(5);
        zza3.zza(zzgnf.zzv(bArr));
        zzgic zzgicVar = (zzgic) zza3.zzal();
        zzgjk zza4 = zzgjl.zza();
        zza4.zza(zzgakVar.zzb());
        zza4.zzb(zzgnf.zzv(zzgakVar.zzc()));
        int zzd = zzgakVar.zzd() - 1;
        if (zzd == 0) {
            r1 = 3;
        } else if (zzd != 1) {
            r1 = zzd != 2 ? 6 : 5;
        }
        zza4.zzc(r1);
        zzghm zza5 = zzghn.zza();
        zza5.zza((zzgjl) zza4.zzal());
        zzghs zzc = zzght.zzc();
        zzc.zzb(zzgicVar);
        zzc.zza((zzghn) zza5.zzal());
        zzc.zzc(r6);
        zza2.zza((zzght) zzc.zzal());
        return new zzgek((zzghq) zza2.zzal(), r9);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final zzgel zza() {
        return new zzgdh(this, zzghq.class);
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzghw.zze(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final String zzc() {
        return "type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey";
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzghw zzghwVar = (zzghw) zzgpxVar;
        if (zzghwVar.zzg().zzD()) {
            throw new GeneralSecurityException("invalid ECIES private key");
        }
        zzgmi.zzb(zzghwVar.zza(), 0);
        zzgdr.zza(zzghwVar.zzf().zzc());
    }

    @Override // com.google.android.gms.internal.ads.zzgem
    public final int zzf() {
        return 4;
    }
}
