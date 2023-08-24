package com.airbnb.android.react.maps;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes.dex */
public class AirMapUrlTile extends AirMapFeature {
    protected Context context;
    protected boolean customTileProviderNeeded;
    protected boolean doubleTileSize;
    protected boolean flipY;
    protected float maximumNativeZ;
    protected float maximumZ;
    protected float minimumZ;
    protected boolean offlineMode;
    protected float opacity;
    protected float tileCacheMaxAge;
    protected String tileCachePath;
    protected TileOverlay tileOverlay;
    protected TileOverlayOptions tileOverlayOptions;
    protected AirMapTileProvider tileProvider;
    protected float tileSize;
    protected String urlTemplate;
    protected float zIndex;

    public AirMapUrlTile(Context context) {
        super(context);
        this.maximumNativeZ = 100.0f;
        this.flipY = false;
        this.tileSize = 256.0f;
        this.doubleTileSize = false;
        this.offlineMode = false;
        this.opacity = 1.0f;
        this.customTileProviderNeeded = false;
        this.context = context;
    }

    public void setUrlTemplate(String str) {
        this.urlTemplate = str;
        AirMapTileProvider airMapTileProvider = this.tileProvider;
        if (airMapTileProvider != null) {
            airMapTileProvider.setUrlTemplate(str);
        }
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setZIndex(float f) {
        this.zIndex = f;
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.setZIndex(f);
        }
    }

    public void setMaximumZ(float f) {
        this.maximumZ = f;
        AirMapTileProvider airMapTileProvider = this.tileProvider;
        if (airMapTileProvider != null) {
            airMapTileProvider.setMaximumZ((int) f);
        }
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setMaximumNativeZ(float f) {
        this.maximumNativeZ = f;
        AirMapTileProvider airMapTileProvider = this.tileProvider;
        if (airMapTileProvider != null) {
            airMapTileProvider.setMaximumNativeZ((int) f);
        }
        setCustomTileProviderMode();
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setMinimumZ(float f) {
        this.minimumZ = f;
        AirMapTileProvider airMapTileProvider = this.tileProvider;
        if (airMapTileProvider != null) {
            airMapTileProvider.setMinimumZ((int) f);
        }
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setFlipY(boolean z) {
        this.flipY = z;
        AirMapTileProvider airMapTileProvider = this.tileProvider;
        if (airMapTileProvider != null) {
            airMapTileProvider.setFlipY(z);
        }
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setDoubleTileSize(boolean z) {
        this.doubleTileSize = z;
        AirMapTileProvider airMapTileProvider = this.tileProvider;
        if (airMapTileProvider != null) {
            airMapTileProvider.setDoubleTileSize(z);
        }
        setCustomTileProviderMode();
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setTileSize(float f) {
        this.tileSize = f;
        AirMapTileProvider airMapTileProvider = this.tileProvider;
        if (airMapTileProvider != null) {
            airMapTileProvider.setTileSize((int) f);
        }
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setTileCachePath(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        try {
            this.tileCachePath = new URL(str).getPath();
        } catch (MalformedURLException unused) {
            this.tileCachePath = str;
        } catch (Exception unused2) {
            return;
        }
        AirMapTileProvider airMapTileProvider = this.tileProvider;
        if (airMapTileProvider != null) {
            airMapTileProvider.setTileCachePath(str);
        }
        setCustomTileProviderMode();
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setTileCacheMaxAge(float f) {
        this.tileCacheMaxAge = f;
        AirMapTileProvider airMapTileProvider = this.tileProvider;
        if (airMapTileProvider != null) {
            airMapTileProvider.setTileCacheMaxAge((int) f);
        }
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setOfflineMode(boolean z) {
        this.offlineMode = z;
        AirMapTileProvider airMapTileProvider = this.tileProvider;
        if (airMapTileProvider != null) {
            airMapTileProvider.setOfflineMode(z);
        }
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setOpacity(float f) {
        this.opacity = f;
        TileOverlay tileOverlay = this.tileOverlay;
        if (tileOverlay != null) {
            tileOverlay.setTransparency(1.0f - f);
        }
    }

    public TileOverlayOptions getTileOverlayOptions() {
        if (this.tileOverlayOptions == null) {
            this.tileOverlayOptions = createTileOverlayOptions();
        }
        return this.tileOverlayOptions;
    }

    protected void setCustomTileProviderMode() {
        Log.d("urlTile ", "creating new mode TileProvider");
        this.customTileProviderNeeded = true;
        AirMapTileProvider airMapTileProvider = this.tileProvider;
        if (airMapTileProvider != null) {
            airMapTileProvider.setCustomMode();
        }
    }

    protected TileOverlayOptions createTileOverlayOptions() {
        Log.d("urlTile ", "creating TileProvider");
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        tileOverlayOptions.zIndex(this.zIndex);
        tileOverlayOptions.transparency(1.0f - this.opacity);
        AirMapTileProvider airMapTileProvider = new AirMapTileProvider((int) this.tileSize, this.doubleTileSize, this.urlTemplate, (int) this.maximumZ, (int) this.maximumNativeZ, (int) this.minimumZ, this.flipY, this.tileCachePath, (int) this.tileCacheMaxAge, this.offlineMode, this.context, this.customTileProviderNeeded);
        this.tileProvider = airMapTileProvider;
        tileOverlayOptions.tileProvider(airMapTileProvider);
        return tileOverlayOptions;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public Object getFeature() {
        return this.tileOverlay;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void addToMap(GoogleMap googleMap) {
        this.tileOverlay = googleMap.addTileOverlay(getTileOverlayOptions());
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void removeFromMap(GoogleMap googleMap) {
        this.tileOverlay.remove();
    }
}
