package com.google.android.gms.maps.model;

/* compiled from: com.google.android.gms:play-services-maps@@18.0.0 */
/* loaded from: classes3.dex */
public interface TileProvider {
    public static final Tile NO_TILE = new Tile(-1, -1, null);

    Tile getTile(int r1, int r2, int r3);
}
