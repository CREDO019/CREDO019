package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzecn implements zzdft {
    private final Context zza;
    private final zzces zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzecn(Context context, zzces zzcesVar) {
        this.zza = context;
        this.zzb = zzcesVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzb(zzfde zzfdeVar) {
        if (TextUtils.isEmpty(zzfdeVar.zzb.zzb.zzd)) {
            return;
        }
        this.zzb.zzp(this.zza, zzfdeVar.zza.zza.zzd);
        this.zzb.zzl(this.zza, zzfdeVar.zzb.zzb.zzd);
    }

    @Override // com.google.android.gms.internal.ads.zzdft
    public final void zzbE(zzcba zzcbaVar) {
    }
}
