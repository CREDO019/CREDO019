package com.google.android.gms.maps.model;

import com.google.android.gms.internal.maps.zzai;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
/* loaded from: classes3.dex */
final class zzu extends zzai {
    final /* synthetic */ TileProvider zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(TileOverlayOptions tileOverlayOptions, TileProvider tileProvider) {
        this.zza = tileProvider;
    }

    @Override // com.google.android.gms.internal.maps.zzaj
    public final Tile zzb(int r2, int r3, int r4) {
        return this.zza.getTile(r2, r3, r4);
    }
}
