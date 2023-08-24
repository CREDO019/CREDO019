package com.google.maps.android.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.collection.LruCache;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.C3346R;
import com.google.maps.android.data.geojson.BiMultiMap;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonGeometryCollection;
import com.google.maps.android.data.geojson.GeoJsonLineString;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonMultiLineString;
import com.google.maps.android.data.geojson.GeoJsonMultiPoint;
import com.google.maps.android.data.geojson.GeoJsonMultiPolygon;
import com.google.maps.android.data.geojson.GeoJsonPoint;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygon;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlGroundOverlay;
import com.google.maps.android.data.kml.KmlMultiGeometry;
import com.google.maps.android.data.kml.KmlPlacemark;
import com.google.maps.android.data.kml.KmlPolygon;
import com.google.maps.android.data.kml.KmlStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public class Renderer {
    private static final Object FEATURE_NOT_ON_MAP = null;
    private static final int LRU_CACHE_SIZE = 50;
    private BiMultiMap<Feature> mContainerFeatures;
    private ArrayList<KmlContainer> mContainers;
    private Context mContext;
    private final GeoJsonLineStringStyle mDefaultLineStringStyle;
    private final GeoJsonPointStyle mDefaultPointStyle;
    private final GeoJsonPolygonStyle mDefaultPolygonStyle;
    private final BiMultiMap<Feature> mFeatures;
    private HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlays;
    private final LruCache<String, Bitmap> mImagesCache;
    private boolean mLayerOnMap;
    private GoogleMap mMap;
    private final ArrayList<String> mMarkerIconUrls;
    private HashMap<String, String> mStyleMaps;
    private HashMap<String, KmlStyle> mStyles;
    private HashMap<String, KmlStyle> mStylesRenderer;

    public Renderer(GoogleMap googleMap, Context context) {
        this.mFeatures = new BiMultiMap<>();
        this.mMap = googleMap;
        this.mContext = context;
        this.mLayerOnMap = false;
        this.mImagesCache = new LruCache<>(50);
        this.mMarkerIconUrls = new ArrayList<>();
        this.mStylesRenderer = new HashMap<>();
        this.mDefaultPointStyle = null;
        this.mDefaultLineStringStyle = null;
        this.mDefaultPolygonStyle = null;
        this.mContainerFeatures = new BiMultiMap<>();
    }

    public Renderer(GoogleMap googleMap, HashMap<? extends Feature, Object> hashMap) {
        BiMultiMap<Feature> biMultiMap = new BiMultiMap<>();
        this.mFeatures = biMultiMap;
        this.mMap = googleMap;
        biMultiMap.putAll(hashMap);
        this.mLayerOnMap = false;
        this.mMarkerIconUrls = null;
        this.mDefaultPointStyle = new GeoJsonPointStyle();
        this.mDefaultLineStringStyle = new GeoJsonLineStringStyle();
        this.mDefaultPolygonStyle = new GeoJsonPolygonStyle();
        this.mImagesCache = null;
        this.mContainerFeatures = null;
    }

    public boolean isLayerOnMap() {
        return this.mLayerOnMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setLayerVisibility(boolean z) {
        this.mLayerOnMap = z;
    }

    public GoogleMap getMap() {
        return this.mMap;
    }

    public void setMap(GoogleMap googleMap) {
        this.mMap = googleMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void putContainerFeature(Object obj, Feature feature) {
        this.mContainerFeatures.put((BiMultiMap<Feature>) feature, obj);
    }

    public Set<Feature> getFeatures() {
        return this.mFeatures.keySet();
    }

    public Feature getFeature(Object obj) {
        return this.mFeatures.getKey(obj);
    }

    public Feature getContainerFeature(Object obj) {
        BiMultiMap<Feature> biMultiMap = this.mContainerFeatures;
        if (biMultiMap != null) {
            return biMultiMap.getKey(obj);
        }
        return null;
    }

    public Collection<Object> getValues() {
        return this.mFeatures.values();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HashMap<? extends Feature, Object> getAllFeatures() {
        return this.mFeatures;
    }

    public ArrayList<String> getMarkerIconUrls() {
        return this.mMarkerIconUrls;
    }

    public HashMap<String, KmlStyle> getStylesRenderer() {
        return this.mStylesRenderer;
    }

    public HashMap<String, String> getStyleMaps() {
        return this.mStyleMaps;
    }

    public LruCache<String, Bitmap> getImagesCache() {
        return this.mImagesCache;
    }

    public HashMap<KmlGroundOverlay, GroundOverlay> getGroundOverlayMap() {
        return this.mGroundOverlays;
    }

    public ArrayList<KmlContainer> getContainerList() {
        return this.mContainers;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public KmlStyle getPlacemarkStyle(String str) {
        return this.mStylesRenderer.get(str) != null ? this.mStylesRenderer.get(str) : this.mStylesRenderer.get(null);
    }

    public GeoJsonPointStyle getDefaultPointStyle() {
        return this.mDefaultPointStyle;
    }

    public GeoJsonLineStringStyle getDefaultLineStringStyle() {
        return this.mDefaultLineStringStyle;
    }

    public GeoJsonPolygonStyle getDefaultPolygonStyle() {
        return this.mDefaultPolygonStyle;
    }

    public void putFeatures(Feature feature, Object obj) {
        this.mFeatures.put((BiMultiMap<Feature>) feature, obj);
    }

    public void putStyles() {
        this.mStylesRenderer.putAll(this.mStyles);
    }

    public void putStyles(HashMap<String, KmlStyle> hashMap) {
        this.mStylesRenderer.putAll(hashMap);
    }

    public void putImagesCache(String str, Bitmap bitmap) {
        this.mImagesCache.put(str, bitmap);
    }

    public boolean hasFeatures() {
        return this.mFeatures.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void removeFeatures(HashMap<Feature, Object> hashMap) {
        for (Object obj : hashMap.values()) {
            if (obj instanceof Marker) {
                ((Marker) obj).remove();
            } else if (obj instanceof Polyline) {
                ((Polyline) obj).remove();
            } else if (obj instanceof Polygon) {
                ((Polygon) obj).remove();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeFeature(Feature feature) {
        if (this.mFeatures.containsKey(feature)) {
            removeFromMap(this.mFeatures.remove(feature));
        }
    }

    private void setFeatureDefaultStyles(GeoJsonFeature geoJsonFeature) {
        if (geoJsonFeature.getPointStyle() == null) {
            geoJsonFeature.setPointStyle(this.mDefaultPointStyle);
        }
        if (geoJsonFeature.getLineStringStyle() == null) {
            geoJsonFeature.setLineStringStyle(this.mDefaultLineStringStyle);
        }
        if (geoJsonFeature.getPolygonStyle() == null) {
            geoJsonFeature.setPolygonStyle(this.mDefaultPolygonStyle);
        }
    }

    public void clearStylesRenderer() {
        this.mStylesRenderer.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void storeData(HashMap<String, KmlStyle> hashMap, HashMap<String, String> hashMap2, HashMap<KmlPlacemark, Object> hashMap3, ArrayList<KmlContainer> arrayList, HashMap<KmlGroundOverlay, GroundOverlay> hashMap4) {
        this.mStyles = hashMap;
        this.mStyleMaps = hashMap2;
        this.mFeatures.putAll(hashMap3);
        this.mContainers = arrayList;
        this.mGroundOverlays = hashMap4;
    }

    public void addFeature(Feature feature) {
        Object obj = FEATURE_NOT_ON_MAP;
        if (feature instanceof GeoJsonFeature) {
            setFeatureDefaultStyles((GeoJsonFeature) feature);
        }
        if (this.mLayerOnMap) {
            if (this.mFeatures.containsKey(feature)) {
                removeFromMap(this.mFeatures.get(feature));
            }
            if (feature.hasGeometry()) {
                if (feature instanceof KmlPlacemark) {
                    KmlPlacemark kmlPlacemark = (KmlPlacemark) feature;
                    obj = addKmlPlacemarkToMap(kmlPlacemark, feature.getGeometry(), getPlacemarkStyle(feature.getId()), kmlPlacemark.getInlineStyle(), getPlacemarkVisibility(feature));
                } else {
                    obj = addGeoJsonFeatureToMap(feature, feature.getGeometry());
                }
            }
        }
        this.mFeatures.put((BiMultiMap<Feature>) feature, obj);
    }

    public static void removeFromMap(Object obj) {
        if (obj instanceof Marker) {
            ((Marker) obj).remove();
        } else if (obj instanceof Polyline) {
            ((Polyline) obj).remove();
        } else if (obj instanceof Polygon) {
            ((Polygon) obj).remove();
        } else if (obj instanceof ArrayList) {
            Iterator it = ((ArrayList) obj).iterator();
            while (it.hasNext()) {
                removeFromMap(it.next());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object addGeoJsonFeatureToMap(Feature feature, Geometry geometry) {
        String geometryType = geometry.getGeometryType();
        geometryType.hashCode();
        char c = 65535;
        switch (geometryType.hashCode()) {
            case -2116761119:
                if (geometryType.equals("MultiPolygon")) {
                    c = 0;
                    break;
                }
                break;
            case -1065891849:
                if (geometryType.equals("MultiPoint")) {
                    c = 1;
                    break;
                }
                break;
            case -627102946:
                if (geometryType.equals("MultiLineString")) {
                    c = 2;
                    break;
                }
                break;
            case 77292912:
                if (geometryType.equals("Point")) {
                    c = 3;
                    break;
                }
                break;
            case 1267133722:
                if (geometryType.equals(KmlPolygon.GEOMETRY_TYPE)) {
                    c = 4;
                    break;
                }
                break;
            case 1806700869:
                if (geometryType.equals("LineString")) {
                    c = 5;
                    break;
                }
                break;
            case 1950410960:
                if (geometryType.equals("GeometryCollection")) {
                    c = 6;
                    break;
                }
                break;
        }
        MarkerOptions markerOptions = null;
        PolylineOptions polylineOptions = null;
        PolygonOptions polygonOptions = null;
        switch (c) {
            case 0:
                return addMultiPolygonToMap(((GeoJsonFeature) feature).getPolygonStyle(), (GeoJsonMultiPolygon) geometry);
            case 1:
                return addMultiPointToMap(((GeoJsonFeature) feature).getPointStyle(), (GeoJsonMultiPoint) geometry);
            case 2:
                return addMultiLineStringToMap(((GeoJsonFeature) feature).getLineStringStyle(), (GeoJsonMultiLineString) geometry);
            case 3:
                if (feature instanceof GeoJsonFeature) {
                    markerOptions = ((GeoJsonFeature) feature).getMarkerOptions();
                } else if (feature instanceof KmlPlacemark) {
                    markerOptions = ((KmlPlacemark) feature).getMarkerOptions();
                }
                return addPointToMap(markerOptions, (GeoJsonPoint) geometry);
            case 4:
                if (feature instanceof GeoJsonFeature) {
                    polygonOptions = ((GeoJsonFeature) feature).getPolygonOptions();
                } else if (feature instanceof KmlPlacemark) {
                    polygonOptions = ((KmlPlacemark) feature).getPolygonOptions();
                }
                return addPolygonToMap(polygonOptions, (DataPolygon) geometry);
            case 5:
                if (feature instanceof GeoJsonFeature) {
                    polylineOptions = ((GeoJsonFeature) feature).getPolylineOptions();
                } else if (feature instanceof KmlPlacemark) {
                    polylineOptions = ((KmlPlacemark) feature).getPolylineOptions();
                }
                return addLineStringToMap(polylineOptions, (GeoJsonLineString) geometry);
            case 6:
                return addGeometryCollectionToMap((GeoJsonFeature) feature, ((GeoJsonGeometryCollection) geometry).getGeometries());
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004c, code lost:
        if (r0.equals("Point") == false) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object addKmlPlacemarkToMap(com.google.maps.android.data.kml.KmlPlacemark r10, com.google.maps.android.data.Geometry r11, com.google.maps.android.data.kml.KmlStyle r12, com.google.maps.android.data.kml.KmlStyle r13, boolean r14) {
        /*
            r9 = this;
            java.lang.String r0 = r11.getGeometryType()
            java.lang.String r2 = "drawOrder"
            boolean r3 = r10.hasProperty(r2)
            r6 = 0
            r7 = 0
            if (r3 == 0) goto L18
            java.lang.String r2 = r10.getProperty(r2)     // Catch: java.lang.NumberFormatException -> L17
            float r7 = java.lang.Float.parseFloat(r2)     // Catch: java.lang.NumberFormatException -> L17
            goto L18
        L17:
            r3 = 0
        L18:
            r0.hashCode()
            r2 = -1
            int r8 = r0.hashCode()
            switch(r8) {
                case 77292912: goto L46;
                case 89139371: goto L3b;
                case 1267133722: goto L30;
                case 1806700869: goto L25;
                default: goto L23;
            }
        L23:
            r6 = -1
            goto L4f
        L25:
            java.lang.String r6 = "LineString"
            boolean r0 = r0.equals(r6)
            if (r0 != 0) goto L2e
            goto L23
        L2e:
            r6 = 3
            goto L4f
        L30:
            java.lang.String r6 = "Polygon"
            boolean r0 = r0.equals(r6)
            if (r0 != 0) goto L39
            goto L23
        L39:
            r6 = 2
            goto L4f
        L3b:
            java.lang.String r6 = "MultiGeometry"
            boolean r0 = r0.equals(r6)
            if (r0 != 0) goto L44
            goto L23
        L44:
            r6 = 1
            goto L4f
        L46:
            java.lang.String r8 = "Point"
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L4f
            goto L23
        L4f:
            switch(r6) {
                case 0: goto Lb7;
                case 1: goto Laa;
                case 2: goto L7f;
                case 3: goto L54;
                default: goto L52;
            }
        L52:
            r0 = 0
            return r0
        L54:
            com.google.android.gms.maps.model.PolylineOptions r0 = r12.getPolylineOptions()
            if (r13 == 0) goto L5e
            r9.setInlineLineStringStyle(r0, r13)
            goto L6f
        L5e:
            boolean r1 = r12.isLineRandomColorMode()
            if (r1 == 0) goto L6f
            int r1 = r0.getColor()
            int r1 = com.google.maps.android.data.kml.KmlStyle.computeRandomColor(r1)
            r0.color(r1)
        L6f:
            r1 = r11
            com.google.maps.android.data.LineString r1 = (com.google.maps.android.data.LineString) r1
            com.google.android.gms.maps.model.Polyline r0 = r9.addLineStringToMap(r0, r1)
            r0.setVisible(r14)
            if (r3 == 0) goto L7e
            r0.setZIndex(r7)
        L7e:
            return r0
        L7f:
            com.google.android.gms.maps.model.PolygonOptions r0 = r12.getPolygonOptions()
            if (r13 == 0) goto L89
            r9.setInlinePolygonStyle(r0, r13)
            goto L9a
        L89:
            boolean r1 = r12.isPolyRandomColorMode()
            if (r1 == 0) goto L9a
            int r1 = r0.getFillColor()
            int r1 = com.google.maps.android.data.kml.KmlStyle.computeRandomColor(r1)
            r0.fillColor(r1)
        L9a:
            r1 = r11
            com.google.maps.android.data.DataPolygon r1 = (com.google.maps.android.data.DataPolygon) r1
            com.google.android.gms.maps.model.Polygon r0 = r9.addPolygonToMap(r0, r1)
            r0.setVisible(r14)
            if (r3 == 0) goto La9
            r0.setZIndex(r7)
        La9:
            return r0
        Laa:
            r2 = r11
            com.google.maps.android.data.kml.KmlMultiGeometry r2 = (com.google.maps.android.data.kml.KmlMultiGeometry) r2
            r0 = r9
            r1 = r10
            r3 = r12
            r4 = r13
            r5 = r14
            java.util.ArrayList r0 = r0.addMultiGeometryToMap(r1, r2, r3, r4, r5)
            return r0
        Lb7:
            com.google.android.gms.maps.model.MarkerOptions r0 = r12.getMarkerOptions()
            if (r13 == 0) goto Lc5
            java.lang.String r2 = r12.getIconUrl()
            r9.setInlinePointStyle(r0, r13, r2)
            goto Ld2
        Lc5:
            java.lang.String r2 = r12.getIconUrl()
            if (r2 == 0) goto Ld2
            java.lang.String r2 = r12.getIconUrl()
            r9.addMarkerIcons(r2, r0)
        Ld2:
            r2 = r11
            com.google.maps.android.data.kml.KmlPoint r2 = (com.google.maps.android.data.kml.KmlPoint) r2
            com.google.android.gms.maps.model.Marker r0 = r9.addPointToMap(r0, r2)
            r0.setVisible(r14)
            r9.setMarkerInfoWindow(r12, r0, r10)
            if (r3 == 0) goto Le4
            r0.setZIndex(r7)
        Le4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.maps.android.data.Renderer.addKmlPlacemarkToMap(com.google.maps.android.data.kml.KmlPlacemark, com.google.maps.android.data.Geometry, com.google.maps.android.data.kml.KmlStyle, com.google.maps.android.data.kml.KmlStyle, boolean):java.lang.Object");
    }

    protected Marker addPointToMap(MarkerOptions markerOptions, Point point) {
        markerOptions.position(point.getGeometryObject());
        return this.mMap.addMarker(markerOptions);
    }

    private void setInlinePointStyle(MarkerOptions markerOptions, KmlStyle kmlStyle, String str) {
        MarkerOptions markerOptions2 = kmlStyle.getMarkerOptions();
        if (kmlStyle.isStyleSet("heading")) {
            markerOptions.rotation(markerOptions2.getRotation());
        }
        if (kmlStyle.isStyleSet("hotSpot")) {
            markerOptions.anchor(markerOptions2.getAnchorU(), markerOptions2.getAnchorV());
        }
        if (kmlStyle.isStyleSet("markerColor")) {
            markerOptions.icon(markerOptions2.getIcon());
        }
        if (kmlStyle.isStyleSet("iconUrl")) {
            addMarkerIcons(kmlStyle.getIconUrl(), markerOptions);
        } else if (str != null) {
            addMarkerIcons(str, markerOptions);
        }
    }

    protected Polyline addLineStringToMap(PolylineOptions polylineOptions, LineString lineString) {
        polylineOptions.addAll(lineString.getGeometryObject());
        Polyline addPolyline = this.mMap.addPolyline(polylineOptions);
        addPolyline.setClickable(true);
        return addPolyline;
    }

    private void setInlineLineStringStyle(PolylineOptions polylineOptions, KmlStyle kmlStyle) {
        PolylineOptions polylineOptions2 = kmlStyle.getPolylineOptions();
        if (kmlStyle.isStyleSet("outlineColor")) {
            polylineOptions.color(polylineOptions2.getColor());
        }
        if (kmlStyle.isStyleSet("width")) {
            polylineOptions.width(polylineOptions2.getWidth());
        }
        if (kmlStyle.isLineRandomColorMode()) {
            polylineOptions.color(KmlStyle.computeRandomColor(polylineOptions2.getColor()));
        }
    }

    protected Polygon addPolygonToMap(PolygonOptions polygonOptions, DataPolygon dataPolygon) {
        polygonOptions.addAll(dataPolygon.getOuterBoundaryCoordinates());
        for (List<LatLng> list : dataPolygon.getInnerBoundaryCoordinates()) {
            polygonOptions.addHole(list);
        }
        Polygon addPolygon = this.mMap.addPolygon(polygonOptions);
        addPolygon.setClickable(true);
        return addPolygon;
    }

    private void setInlinePolygonStyle(PolygonOptions polygonOptions, KmlStyle kmlStyle) {
        PolygonOptions polygonOptions2 = kmlStyle.getPolygonOptions();
        if (kmlStyle.hasFill() && kmlStyle.isStyleSet("fillColor")) {
            polygonOptions.fillColor(polygonOptions2.getFillColor());
        }
        if (kmlStyle.hasOutline()) {
            if (kmlStyle.isStyleSet("outlineColor")) {
                polygonOptions.strokeColor(polygonOptions2.getStrokeColor());
            }
            if (kmlStyle.isStyleSet("width")) {
                polygonOptions.strokeWidth(polygonOptions2.getStrokeWidth());
            }
        }
        if (kmlStyle.isPolyRandomColorMode()) {
            polygonOptions.fillColor(KmlStyle.computeRandomColor(polygonOptions2.getFillColor()));
        }
    }

    private ArrayList<Object> addGeometryCollectionToMap(GeoJsonFeature geoJsonFeature, List<Geometry> list) {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (Geometry geometry : list) {
            arrayList.add(addGeoJsonFeatureToMap(geoJsonFeature, geometry));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean getPlacemarkVisibility(Feature feature) {
        return (feature.hasProperty("visibility") && Integer.parseInt(feature.getProperty("visibility")) == 0) ? false : true;
    }

    public void assignStyleMap(HashMap<String, String> hashMap, HashMap<String, KmlStyle> hashMap2) {
        for (String str : hashMap.keySet()) {
            String str2 = hashMap.get(str);
            if (hashMap2.containsKey(str2)) {
                hashMap2.put(str, hashMap2.get(str2));
            }
        }
    }

    private ArrayList<Object> addMultiGeometryToMap(KmlPlacemark kmlPlacemark, KmlMultiGeometry kmlMultiGeometry, KmlStyle kmlStyle, KmlStyle kmlStyle2, boolean z) {
        ArrayList<Object> arrayList = new ArrayList<>();
        Iterator<Geometry> it = kmlMultiGeometry.getGeometryObject().iterator();
        while (it.hasNext()) {
            arrayList.add(addKmlPlacemarkToMap(kmlPlacemark, it.next(), kmlStyle, kmlStyle2, z));
        }
        return arrayList;
    }

    private ArrayList<Marker> addMultiPointToMap(GeoJsonPointStyle geoJsonPointStyle, GeoJsonMultiPoint geoJsonMultiPoint) {
        ArrayList<Marker> arrayList = new ArrayList<>();
        for (GeoJsonPoint geoJsonPoint : geoJsonMultiPoint.getPoints()) {
            arrayList.add(addPointToMap(geoJsonPointStyle.toMarkerOptions(), geoJsonPoint));
        }
        return arrayList;
    }

    private ArrayList<Polyline> addMultiLineStringToMap(GeoJsonLineStringStyle geoJsonLineStringStyle, GeoJsonMultiLineString geoJsonMultiLineString) {
        ArrayList<Polyline> arrayList = new ArrayList<>();
        for (GeoJsonLineString geoJsonLineString : geoJsonMultiLineString.getLineStrings()) {
            arrayList.add(addLineStringToMap(geoJsonLineStringStyle.toPolylineOptions(), geoJsonLineString));
        }
        return arrayList;
    }

    private ArrayList<Polygon> addMultiPolygonToMap(GeoJsonPolygonStyle geoJsonPolygonStyle, GeoJsonMultiPolygon geoJsonMultiPolygon) {
        ArrayList<Polygon> arrayList = new ArrayList<>();
        for (GeoJsonPolygon geoJsonPolygon : geoJsonMultiPolygon.getPolygons()) {
            arrayList.add(addPolygonToMap(geoJsonPolygonStyle.toPolygonOptions(), geoJsonPolygon));
        }
        return arrayList;
    }

    private void addMarkerIcons(String str, MarkerOptions markerOptions) {
        if (this.mImagesCache.get(str) != null) {
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(this.mImagesCache.get(str)));
        } else if (this.mMarkerIconUrls.contains(str)) {
        } else {
            this.mMarkerIconUrls.add(str);
        }
    }

    public GroundOverlay attachGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        return this.mMap.addGroundOverlay(groundOverlayOptions);
    }

    private void setMarkerInfoWindow(KmlStyle kmlStyle, Marker marker, KmlPlacemark kmlPlacemark) {
        boolean hasProperty = kmlPlacemark.hasProperty("name");
        boolean hasProperty2 = kmlPlacemark.hasProperty("description");
        boolean hasBalloonStyle = kmlStyle.hasBalloonStyle();
        boolean containsKey = kmlStyle.getBalloonOptions().containsKey("text");
        if (hasBalloonStyle && containsKey) {
            marker.setTitle(kmlStyle.getBalloonOptions().get("text"));
            createInfoWindow();
        } else if (hasBalloonStyle && hasProperty) {
            marker.setTitle(kmlPlacemark.getProperty("name"));
            createInfoWindow();
        } else if (hasProperty && hasProperty2) {
            marker.setTitle(kmlPlacemark.getProperty("name"));
            marker.setSnippet(kmlPlacemark.getProperty("description"));
            createInfoWindow();
        } else if (hasProperty2) {
            marker.setTitle(kmlPlacemark.getProperty("description"));
            createInfoWindow();
        } else if (hasProperty) {
            marker.setTitle(kmlPlacemark.getProperty("name"));
            createInfoWindow();
        }
    }

    private void createInfoWindow() {
        this.mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() { // from class: com.google.maps.android.data.Renderer.1
            @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
            public View getInfoContents(Marker marker) {
                View inflate = LayoutInflater.from(Renderer.this.mContext).inflate(C3346R.layout.amu_info_window, (ViewGroup) null);
                TextView textView = (TextView) inflate.findViewById(C3346R.C3349id.window);
                if (marker.getSnippet() != null) {
                    textView.setText(Html.fromHtml(marker.getTitle() + "<br>" + marker.getSnippet()));
                } else {
                    textView.setText(Html.fromHtml(marker.getTitle()));
                }
                return inflate;
            }
        });
    }
}
