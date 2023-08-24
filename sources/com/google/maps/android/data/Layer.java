package com.google.maps.android.data;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.geojson.GeoJsonRenderer;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlGroundOverlay;
import com.google.maps.android.data.kml.KmlRenderer;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public abstract class Layer {
    private Renderer mRenderer;

    /* loaded from: classes3.dex */
    public interface OnFeatureClickListener {
        void onFeatureClick(Feature feature);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addKMLToMap() throws IOException, XmlPullParserException {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof KmlRenderer) {
            ((KmlRenderer) renderer).addLayerToMap();
            return;
        }
        throw new UnsupportedOperationException("Stored renderer is not a KmlRenderer");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addGeoJsonToMap() {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof GeoJsonRenderer) {
            ((GeoJsonRenderer) renderer).addLayerToMap();
            return;
        }
        throw new UnsupportedOperationException("Stored renderer is not a GeoJsonRenderer");
    }

    public void removeLayerFromMap() {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof GeoJsonRenderer) {
            ((GeoJsonRenderer) renderer).removeLayerFromMap();
        } else if (renderer instanceof KmlRenderer) {
            ((KmlRenderer) renderer).removeLayerFromMap();
        }
    }

    public void setOnFeatureClickListener(final OnFeatureClickListener onFeatureClickListener) {
        GoogleMap map = getMap();
        map.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() { // from class: com.google.maps.android.data.Layer.1
            @Override // com.google.android.gms.maps.GoogleMap.OnPolygonClickListener
            public void onPolygonClick(Polygon polygon) {
                if (Layer.this.getFeature(polygon) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getFeature(polygon));
                } else if (Layer.this.getContainerFeature(polygon) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getContainerFeature(polygon));
                } else {
                    OnFeatureClickListener onFeatureClickListener2 = onFeatureClickListener;
                    Layer layer = Layer.this;
                    onFeatureClickListener2.onFeatureClick(layer.getFeature(layer.multiObjectHandler(polygon)));
                }
            }
        });
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() { // from class: com.google.maps.android.data.Layer.2
            @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
            public boolean onMarkerClick(Marker marker) {
                if (Layer.this.getFeature(marker) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getFeature(marker));
                    return false;
                } else if (Layer.this.getContainerFeature(marker) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getContainerFeature(marker));
                    return false;
                } else {
                    OnFeatureClickListener onFeatureClickListener2 = onFeatureClickListener;
                    Layer layer = Layer.this;
                    onFeatureClickListener2.onFeatureClick(layer.getFeature(layer.multiObjectHandler(marker)));
                    return false;
                }
            }
        });
        map.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() { // from class: com.google.maps.android.data.Layer.3
            @Override // com.google.android.gms.maps.GoogleMap.OnPolylineClickListener
            public void onPolylineClick(Polyline polyline) {
                if (Layer.this.getFeature(polyline) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getFeature(polyline));
                } else if (Layer.this.getContainerFeature(polyline) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getContainerFeature(polyline));
                } else {
                    OnFeatureClickListener onFeatureClickListener2 = onFeatureClickListener;
                    Layer layer = Layer.this;
                    onFeatureClickListener2.onFeatureClick(layer.getFeature(layer.multiObjectHandler(polyline)));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<?> multiObjectHandler(Object obj) {
        for (Object obj2 : this.mRenderer.getValues()) {
            if (obj2.getClass().getSimpleName().equals("ArrayList")) {
                ArrayList<?> arrayList = (ArrayList) obj2;
                if (arrayList.contains(obj)) {
                    return arrayList;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void storeRenderer(Renderer renderer) {
        this.mRenderer = renderer;
    }

    public Iterable<? extends Feature> getFeatures() {
        return this.mRenderer.getFeatures();
    }

    public Feature getFeature(Object obj) {
        return this.mRenderer.getFeature(obj);
    }

    public Feature getContainerFeature(Object obj) {
        return this.mRenderer.getContainerFeature(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasFeatures() {
        return this.mRenderer.hasFeatures();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasContainers() {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof KmlRenderer) {
            return ((KmlRenderer) renderer).hasNestedContainers();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Iterable<KmlContainer> getContainers() {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof KmlRenderer) {
            return ((KmlRenderer) renderer).getNestedContainers();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Iterable<KmlGroundOverlay> getGroundOverlays() {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof KmlRenderer) {
            return ((KmlRenderer) renderer).getGroundOverlays();
        }
        return null;
    }

    public GoogleMap getMap() {
        return this.mRenderer.getMap();
    }

    public void setMap(GoogleMap googleMap) {
        this.mRenderer.setMap(googleMap);
    }

    public boolean isLayerOnMap() {
        return this.mRenderer.isLayerOnMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addFeature(Feature feature) {
        this.mRenderer.addFeature(feature);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeFeature(Feature feature) {
        this.mRenderer.removeFeature(feature);
    }

    public GeoJsonPointStyle getDefaultPointStyle() {
        return this.mRenderer.getDefaultPointStyle();
    }

    public GeoJsonLineStringStyle getDefaultLineStringStyle() {
        return this.mRenderer.getDefaultLineStringStyle();
    }

    public GeoJsonPolygonStyle getDefaultPolygonStyle() {
        return this.mRenderer.getDefaultPolygonStyle();
    }
}
