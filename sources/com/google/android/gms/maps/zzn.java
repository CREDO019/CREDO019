package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Circle;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
/* loaded from: classes3.dex */
final class zzn extends com.google.android.gms.maps.internal.zzw {
    final /* synthetic */ GoogleMap.OnCircleClickListener zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzn(GoogleMap googleMap, GoogleMap.OnCircleClickListener onCircleClickListener) {
        this.zza = onCircleClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzx
    public final void zzb(com.google.android.gms.internal.maps.zzl zzlVar) {
        this.zza.onCircleClick(new Circle(zzlVar));
    }
}
