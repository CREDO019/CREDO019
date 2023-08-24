package com.google.android.gms.internal.ads;

import android.view.View;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcmr implements View.OnAttachStateChangeListener {
    final /* synthetic */ zzcdo zza;
    final /* synthetic */ zzcmu zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcmr(zzcmu zzcmuVar, zzcdo zzcdoVar) {
        this.zzb = zzcmuVar;
        this.zza = zzcdoVar;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.zzb.zzQ(view, this.zza, 10);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
    }
}
