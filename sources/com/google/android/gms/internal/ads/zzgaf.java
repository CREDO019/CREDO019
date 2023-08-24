package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgaf implements zzgai {
    final /* synthetic */ zzgac zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgaf(zzgac zzgacVar) {
        this.zza = zzgacVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgai
    public final zzgac zza(Class cls) throws GeneralSecurityException {
        if (this.zza.zzc().equals(cls)) {
            return this.zza;
        }
        throw new InternalError("This should never be called, as we always first check supportedPrimitives.");
    }

    @Override // com.google.android.gms.internal.ads.zzgai
    public final zzgac zzb() {
        return this.zza;
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
        return Collections.singleton(this.zza.zzc());
    }
}
