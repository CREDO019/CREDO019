package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Handler;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgk {
    private final Context zza;
    private final zzgi zzb;

    public zzgk(Context context, Handler handler, zzgj zzgjVar) {
        this.zza = context.getApplicationContext();
        this.zzb = new zzgi(this, handler, zzgjVar);
    }
}
