package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.BaseImplementation;

/* compiled from: com.google.android.gms:play-services-location@@20.0.0 */
/* loaded from: classes.dex */
final class zzx extends zzah {
    private final BaseImplementation.ResultHolder zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzx(BaseImplementation.ResultHolder resultHolder) {
        this.zza = resultHolder;
    }

    @Override // com.google.android.gms.internal.location.zzai
    public final void zzb(zzaa zzaaVar) {
        this.zza.setResult(zzaaVar.getStatus());
    }

    @Override // com.google.android.gms.internal.location.zzai
    public final void zzc() {
    }
}
