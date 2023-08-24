package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzbxt implements View.OnClickListener {
    final /* synthetic */ zzbxu zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbxt(zzbxu zzbxuVar) {
        this.zza = zzbxuVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zza.zza(true);
    }
}
