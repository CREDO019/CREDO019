package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgag implements zzgai {
    final /* synthetic */ zzgem zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgag(zzgem zzgemVar) {
        this.zza = zzgemVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgai
    public final zzgac zza(Class cls) throws GeneralSecurityException {
        try {
            return new zzgae(this.zza, cls);
        } catch (IllegalArgumentException e) {
            throw new GeneralSecurityException("Primitive type not supported", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgai
    public final zzgac zzb() {
        zzgem zzgemVar = this.zza;
        return new zzgae(zzgemVar, zzgemVar.zzi());
    }

    @Override // com.google.android.gms.internal.ads.zzgai
    public final Class zzc() {
        return this.zza.getClass();
    }

    @Override // com.google.android.gms.internal.ads.zzgai
    public final Class zzd() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzgai
    public final Set zze() {
        return this.zza.zzl();
    }
}
