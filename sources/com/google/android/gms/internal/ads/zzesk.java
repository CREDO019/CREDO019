package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzesk implements zzeum {
    public final zzfcr zza;

    public zzesk(zzfcr zzfcrVar) {
        this.zza = zzfcrVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeum
    public final /* bridge */ /* synthetic */ void zzf(Object obj) {
        Bundle bundle = (Bundle) obj;
        zzfcr zzfcrVar = this.zza;
        if (zzfcrVar != null) {
            bundle.putBoolean("render_in_browser", zzfcrVar.zzd());
            bundle.putBoolean("disable_ml", this.zza.zzc());
        }
    }
}
