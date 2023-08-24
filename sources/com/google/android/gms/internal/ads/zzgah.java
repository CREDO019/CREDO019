package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgah implements zzgai {
    final /* synthetic */ zzgeo zza;
    final /* synthetic */ zzgem zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgah(zzgeo zzgeoVar, zzgem zzgemVar) {
        this.zza = zzgeoVar;
        this.zzb = zzgemVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgai
    public final zzgac zza(Class cls) throws GeneralSecurityException {
        try {
            return new zzgbc(this.zza, this.zzb, cls);
        } catch (IllegalArgumentException e) {
            throw new GeneralSecurityException("Primitive type not supported", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgai
    public final zzgac zzb() {
        zzgeo zzgeoVar = this.zza;
        return new zzgbc(zzgeoVar, this.zzb, zzgeoVar.zzi());
    }

    @Override // com.google.android.gms.internal.ads.zzgai
    public final Class zzc() {
        return this.zza.getClass();
    }

    @Override // com.google.android.gms.internal.ads.zzgai
    public final Class zzd() {
        return this.zzb.getClass();
    }

    @Override // com.google.android.gms.internal.ads.zzgai
    public final Set zze() {
        return this.zza.zzl();
    }
}
