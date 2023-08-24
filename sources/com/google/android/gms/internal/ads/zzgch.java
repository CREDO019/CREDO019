package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgch extends zzgel {
    final /* synthetic */ zzgci zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgch(zzgci zzgciVar, Class cls) {
        super(cls);
        this.zza = zzgciVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ zzgpx zza(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgkg zzc = zzgkh.zzc();
        zzc.zza((zzgkk) zzgpxVar);
        zzc.zzb(0);
        return (zzgkh) zzc.zzal();
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzgkk.zze(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ void zzd(zzgpx zzgpxVar) throws GeneralSecurityException {
        zzgkk zzgkkVar = (zzgkk) zzgpxVar;
        if (zzgkkVar.zzf().isEmpty() || !zzgkkVar.zzg()) {
            throw new GeneralSecurityException("invalid key format: missing KEK URI or DEK template");
        }
    }
}
