package com.airbnb.android.react.maps;

import android.content.Context;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.UrlTileProvider;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes.dex */
public class AirMapWMSTile extends AirMapUrlTile {
    private static final double FULL = 4.007501669578488E7d;
    private static final double[] mapBound = {-2.003750834789244E7d, 2.003750834789244E7d};

    /* loaded from: classes.dex */
    class AIRMapGSUrlTileProvider extends AirMapTileProvider {

        /* loaded from: classes.dex */
        class AIRMapWMSTileProvider extends UrlTileProvider {
            private final int tileSize;
            private String urlTemplate;

            public AIRMapWMSTileProvider(int r2, int r3, String str) {
                super(r2, r3);
                this.urlTemplate = str;
                this.tileSize = r2;
            }

            @Override // com.google.android.gms.maps.model.UrlTileProvider
            public URL getTileUrl(int r4, int r5, int r6) {
                if (AirMapWMSTile.this.maximumZ <= 0.0f || r6 <= AIRMapGSUrlTileProvider.this.maximumZ) {
                    if (AirMapWMSTile.this.minimumZ <= 0.0f || r6 >= AIRMapGSUrlTileProvider.this.minimumZ) {
                        double[] boundingBox = getBoundingBox(r4, r5, r6);
                        try {
                            return new URL(this.urlTemplate.replace("{minX}", Double.toString(boundingBox[0])).replace("{minY}", Double.toString(boundingBox[1])).replace("{maxX}", Double.toString(boundingBox[2])).replace("{maxY}", Double.toString(boundingBox[3])).replace("{width}", Integer.toString(this.tileSize)).replace("{height}", Integer.toString(this.tileSize)));
                        } catch (MalformedURLException e) {
                            throw new AssertionError(e);
                        }
                    }
                    return null;
                }
                return null;
            }

            private double[] getBoundingBox(int r10, int r11, int r12) {
                double pow = AirMapWMSTile.FULL / Math.pow(2.0d, r12);
                return new double[]{AirMapWMSTile.mapBound[0] + (r10 * pow), AirMapWMSTile.mapBound[1] - ((r11 + 1) * pow), AirMapWMSTile.mapBound[0] + ((r10 + 1) * pow), AirMapWMSTile.mapBound[1] - (r11 * pow)};
            }

            public void setUrlTemplate(String str) {
                this.urlTemplate = str;
            }
        }

        public AIRMapGSUrlTileProvider(int r16, String str, int r18, int r19, int r20, String str2, int r22, boolean z, Context context, boolean z2) {
            super(r16, false, str, r18, r19, r20, false, str2, r22, z, context, z2);
            this.tileProvider = new AIRMapWMSTileProvider(r16, r16, str);
        }
    }

    public AirMapWMSTile(Context context) {
        super(context);
    }

    @Override // com.airbnb.android.react.maps.AirMapUrlTile
    protected TileOverlayOptions createTileOverlayOptions() {
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        tileOverlayOptions.zIndex(this.zIndex);
        tileOverlayOptions.transparency(1.0f - this.opacity);
        tileOverlayOptions.tileProvider(new AIRMapGSUrlTileProvider((int) this.tileSize, this.urlTemplate, (int) this.maximumZ, (int) this.maximumNativeZ, (int) this.minimumZ, this.tileCachePath, (int) this.tileCacheMaxAge, this.offlineMode, this.context, this.customTileProviderNeeded));
        return tileOverlayOptions;
    }
}
