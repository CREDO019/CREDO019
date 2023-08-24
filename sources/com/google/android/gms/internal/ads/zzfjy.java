package com.google.android.gms.internal.ads;

import android.app.Application;
import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfjy {
    private boolean zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(Context context) {
        zzflg.zzb(context, "Application Context cannot be null");
        if (this.zza) {
            return;
        }
        this.zza = true;
        zzfku.zzb().zzc(context);
        zzfkp zza = zzfkp.zza();
        if (context instanceof Application) {
            ((Application) context).registerActivityLifecycleCallbacks(zza);
        }
        zzfle.zzg(context);
        zzfkr.zzb().zzc(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzb() {
        return this.zza;
    }
}
