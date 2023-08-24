package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbch implements Runnable {
    final /* synthetic */ View zza;
    final /* synthetic */ zzbcl zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbch(zzbcl zzbclVar, View view) {
        this.zzb = zzbclVar;
        this.zza = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzc(this.zza);
    }
}
