package com.google.android.gms.internal.ads;

import android.content.Context;
import java.io.File;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzalg implements zzakv {
    final /* synthetic */ Context zza;
    private File zzb = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzalg(Context context) {
        this.zza = context;
    }

    @Override // com.google.android.gms.internal.ads.zzakv
    public final File zza() {
        if (this.zzb == null) {
            this.zzb = new File(this.zza.getCacheDir(), "volley");
        }
        return this.zzb;
    }
}
