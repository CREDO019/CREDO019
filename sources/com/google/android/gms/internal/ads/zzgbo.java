package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgbo extends zzgel {
    final /* synthetic */ zzgbp zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzgbo(zzgbp zzgbpVar, Class cls) {
        super(cls);
        this.zza = zzgbpVar;
    }

    public static final zzgfv zzf(zzgfy zzgfyVar) throws GeneralSecurityException {
        zzgfu zzc = zzgfv.zzc();
        zzc.zzb(zzgfyVar.zzg());
        zzc.zza(zzgnf.zzv(zzgmg.zza(zzgfyVar.zza())));
        zzc.zzc(0);
        return (zzgfv) zzc.zzal();
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* bridge */ /* synthetic */ zzgpx zza(zzgpx zzgpxVar) throws GeneralSecurityException {
        return zzf((zzgfy) zzgpxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    public final /* synthetic */ zzgpx zzb(zzgnf zzgnfVar) throws zzgoz {
        return zzgfy.zzf(zzgnfVar, zzgnz.zza());
    }

    @Override // com.google.android.gms.internal.ads.zzgel
    /* renamed from: zze */
    public final void zzd(zzgfy zzgfyVar) throws GeneralSecurityException {
        zzgmi.zza(zzgfyVar.zza());
        zzgbp zzgbpVar = this.zza;
        zzgbp.zzm(zzgfyVar.zzg());
    }
}
