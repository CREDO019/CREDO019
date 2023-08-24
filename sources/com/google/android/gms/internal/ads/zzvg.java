package com.google.android.gms.internal.ads;

import android.media.Spatializer;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzvg implements Spatializer.OnSpatializerStateChangedListener {
    final /* synthetic */ zzvo zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzvg(zzvh zzvhVar, zzvo zzvoVar) {
        this.zza = zzvoVar;
    }

    @Override // android.media.Spatializer.OnSpatializerStateChangedListener
    public final void onSpatializerAvailableChanged(Spatializer spatializer, boolean z) {
        zzvo.zzg(this.zza);
    }

    @Override // android.media.Spatializer.OnSpatializerStateChangedListener
    public final void onSpatializerEnabledChanged(Spatializer spatializer, boolean z) {
        zzvo.zzg(this.zza);
    }
}
