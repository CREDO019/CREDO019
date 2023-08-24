package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfqh extends zzfqt {
    final /* synthetic */ zzfqi zza;
    private final zzfqn zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfqh(zzfqi zzfqiVar, zzfqn zzfqnVar) {
        this.zza = zzfqiVar;
        this.zzb = zzfqnVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfqu
    public final void zzb(Bundle bundle) {
        int r0 = bundle.getInt("statusCode", 8150);
        String string = bundle.getString("sessionToken");
        zzfql zzc = zzfqm.zzc();
        zzc.zzb(r0);
        if (string != null) {
            zzc.zza(string);
        }
        this.zzb.zza(zzc.zzc());
        if (r0 == 8157) {
            this.zza.zzc();
        }
    }
}
