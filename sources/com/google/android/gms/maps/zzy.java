package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
/* loaded from: classes3.dex */
public final class zzy extends com.google.android.gms.maps.internal.zzak {
    final /* synthetic */ GoogleMap.OnMapClickListener zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(GoogleMap googleMap, GoogleMap.OnMapClickListener onMapClickListener) {
        this.zza = onMapClickListener;
    }

    @Override // com.google.android.gms.maps.internal.zzal
    public final void zzb(LatLng latLng) {
        this.zza.onMapClick(latLng);
    }
}