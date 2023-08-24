package com.airbnb.android.react.maps;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.maps.android.heatmaps.WeightedLatLng;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class AirMapHeatmap extends AirMapFeature {
    private Gradient gradient;
    private TileOverlay heatmap;
    private TileOverlayOptions heatmapOptions;
    private HeatmapTileProvider heatmapTileProvider;
    private Double opacity;
    private List<WeightedLatLng> points;
    private Integer radius;

    public AirMapHeatmap(Context context) {
        super(context);
    }

    public void setPoints(WeightedLatLng[] weightedLatLngArr) {
        List<WeightedLatLng> asList = Arrays.asList(weightedLatLngArr);
        this.points = asList;
        HeatmapTileProvider heatmapTileProvider = this.heatmapTileProvider;
        if (heatmapTileProvider != null) {
            heatmapTileProvider.setWeightedData(asList);
        }
        TileOverlay tileOverlay = this.heatmap;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setGradient(Gradient gradient) {
        this.gradient = gradient;
        HeatmapTileProvider heatmapTileProvider = this.heatmapTileProvider;
        if (heatmapTileProvider != null) {
            heatmapTileProvider.setGradient(gradient);
        }
        TileOverlay tileOverlay = this.heatmap;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setOpacity(double d) {
        this.opacity = Double.valueOf(d);
        HeatmapTileProvider heatmapTileProvider = this.heatmapTileProvider;
        if (heatmapTileProvider != null) {
            heatmapTileProvider.setOpacity(d);
        }
        TileOverlay tileOverlay = this.heatmap;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public void setRadius(int r2) {
        this.radius = Integer.valueOf(r2);
        HeatmapTileProvider heatmapTileProvider = this.heatmapTileProvider;
        if (heatmapTileProvider != null) {
            heatmapTileProvider.setRadius(r2);
        }
        TileOverlay tileOverlay = this.heatmap;
        if (tileOverlay != null) {
            tileOverlay.clearTileCache();
        }
    }

    public TileOverlayOptions getHeatmapOptions() {
        if (this.heatmapOptions == null) {
            this.heatmapOptions = createHeatmapOptions();
        }
        return this.heatmapOptions;
    }

    private TileOverlayOptions createHeatmapOptions() {
        TileOverlayOptions tileOverlayOptions = new TileOverlayOptions();
        if (this.heatmapTileProvider == null) {
            HeatmapTileProvider.Builder weightedData = new HeatmapTileProvider.Builder().weightedData(this.points);
            Integer num = this.radius;
            if (num != null) {
                weightedData.radius(num.intValue());
            }
            Double d = this.opacity;
            if (d != null) {
                weightedData.opacity(d.doubleValue());
            }
            Gradient gradient = this.gradient;
            if (gradient != null) {
                weightedData.gradient(gradient);
            }
            this.heatmapTileProvider = weightedData.build();
        }
        tileOverlayOptions.tileProvider(this.heatmapTileProvider);
        return tileOverlayOptions;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public Object getFeature() {
        return this.heatmap;
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void addToMap(GoogleMap googleMap) {
        Log.d("AirMapHeatmap", "ADD TO MAP");
        this.heatmap = googleMap.addTileOverlay(getHeatmapOptions());
    }

    @Override // com.airbnb.android.react.maps.AirMapFeature
    public void removeFromMap(GoogleMap googleMap) {
        this.heatmap.remove();
    }
}
