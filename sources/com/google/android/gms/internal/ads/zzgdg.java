package com.google.android.gms.internal.ads;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECPrivateKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgdg extends zzgen {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgdg(Class cls) {
        super(cls);
    }

    @Override // com.google.android.gms.internal.ads.zzgen
    public final /* bridge */ /* synthetic */ Object zza(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzghw zzghwVar = (zzghw) zzgpxVar;
        zzght zzc = zzghwVar.zzf().zzc();
        zzgic zzf = zzc.zzf();
        int zzc2 = zzgdr.zzc(zzf.zzg());
        byte[] zzE = zzghwVar.zzg().zzE();
        return new zzgli((ECPrivateKey) ((KeyFactory) zzglp.zzg.zza("EC")).generatePrivate(new ECPrivateKeySpec(new BigInteger(1, zzE), zzgln.zze(zzc2))), zzf.zze().zzE(), zzgdr.zzb(zzf.zzh()), zzgdr.zzd(zzc.zzi()), new zzgds(zzc.zza().zze()));
    }
}
