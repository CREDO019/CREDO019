package com.airbnb.android.react.maps;

import android.view.View;
import com.facebook.hermes.intl.Constants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.onesignal.OneSignalDbContract;
import expo.modules.securestore.SecureStoreModule;
import java.util.Map;

/* loaded from: classes.dex */
public class AirMapManager extends ViewGroupManager<AirMapView> {
    private static final String REACT_CLASS = "AIRMap";
    private final ReactApplicationContext appContext;
    private AirMapMarkerManager markerManager;
    private final Map<String, Integer> MAP_TYPES = MapBuilder.m1257of(Constants.COLLATION_STANDARD, 1, "satellite", 2, SecureStoreModule.HybridAESEncrypter.NAME, 4, "terrain", 3, "none", 0);
    private final Map<String, Integer> MY_LOCATION_PRIORITY = MapBuilder.m1258of("balanced", 102, "high", 100, "low", 104, "passive", 105);
    protected GoogleMapOptions googleMapOptions = new GoogleMapOptions();

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public AirMapManager(ReactApplicationContext reactApplicationContext) {
        this.appContext = reactApplicationContext;
    }

    public AirMapMarkerManager getMarkerManager() {
        return this.markerManager;
    }

    public void setMarkerManager(AirMapMarkerManager airMapMarkerManager) {
        this.markerManager = airMapMarkerManager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public AirMapView createViewInstance(ThemedReactContext themedReactContext) {
        return new AirMapView(themedReactContext, this.appContext, this, this.googleMapOptions);
    }

    private void emitMapError(ThemedReactContext themedReactContext, String str, String str2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, str);
        createMap.putString(SessionDescription.ATTR_TYPE, str2);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) themedReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onError", createMap);
    }

    @ReactProp(name = "region")
    public void setRegion(AirMapView airMapView, ReadableMap readableMap) {
        airMapView.setRegion(readableMap);
    }

    @ReactProp(name = "initialRegion")
    public void setInitialRegion(AirMapView airMapView, ReadableMap readableMap) {
        airMapView.setInitialRegion(readableMap);
    }

    @ReactProp(name = "camera")
    public void setCamera(AirMapView airMapView, ReadableMap readableMap) {
        airMapView.setCamera(readableMap);
    }

    @ReactProp(name = "initialCamera")
    public void setInitialCamera(AirMapView airMapView, ReadableMap readableMap) {
        airMapView.setInitialCamera(readableMap);
    }

    @ReactProp(name = "mapType")
    public void setMapType(AirMapView airMapView, String str) {
        airMapView.map.setMapType(this.MAP_TYPES.get(str).intValue());
    }

    @ReactProp(name = "customMapStyleString")
    public void setMapStyle(AirMapView airMapView, String str) {
        airMapView.setMapStyle(str);
    }

    @ReactProp(name = "mapPadding")
    public void setMapPadding(AirMapView airMapView, ReadableMap readableMap) {
        int r10;
        int r4;
        int r5;
        double d = airMapView.getResources().getDisplayMetrics().density;
        int r2 = 0;
        if (readableMap != null) {
            int r3 = readableMap.hasKey("left") ? (int) (readableMap.getDouble("left") * d) : 0;
            r4 = readableMap.hasKey(ViewProps.TOP) ? (int) (readableMap.getDouble(ViewProps.TOP) * d) : 0;
            r5 = readableMap.hasKey("right") ? (int) (readableMap.getDouble("right") * d) : 0;
            if (readableMap.hasKey(ViewProps.BOTTOM)) {
                r10 = (int) (readableMap.getDouble(ViewProps.BOTTOM) * d);
                r2 = r3;
            } else {
                r2 = r3;
                r10 = 0;
            }
        } else {
            r10 = 0;
            r4 = 0;
            r5 = 0;
        }
        airMapView.applyBaseMapPadding(r2, r4, r5, r10);
        airMapView.map.setPadding(r2, r4, r5, r10);
    }

    @ReactProp(defaultBoolean = false, name = "showsUserLocation")
    public void setShowsUserLocation(AirMapView airMapView, boolean z) {
        airMapView.setShowsUserLocation(z);
    }

    @ReactProp(name = "userLocationPriority")
    public void setUserLocationPriority(AirMapView airMapView, String str) {
        airMapView.setUserLocationPriority(this.MY_LOCATION_PRIORITY.get(str).intValue());
    }

    @ReactProp(defaultInt = 5000, name = "userLocationUpdateInterval")
    public void setUserLocationUpdateInterval(AirMapView airMapView, int r2) {
        airMapView.setUserLocationUpdateInterval(r2);
    }

    @ReactProp(defaultInt = 5000, name = "userLocationFastestInterval")
    public void setUserLocationFastestInterval(AirMapView airMapView, int r2) {
        airMapView.setUserLocationFastestInterval(r2);
    }

    @ReactProp(defaultBoolean = true, name = "showsMyLocationButton")
    public void setShowsMyLocationButton(AirMapView airMapView, boolean z) {
        airMapView.setShowsMyLocationButton(z);
    }

    @ReactProp(defaultBoolean = true, name = "toolbarEnabled")
    public void setToolbarEnabled(AirMapView airMapView, boolean z) {
        airMapView.setToolbarEnabled(z);
    }

    @ReactProp(defaultBoolean = false, name = "handlePanDrag")
    public void setHandlePanDrag(AirMapView airMapView, boolean z) {
        airMapView.setHandlePanDrag(z);
    }

    @ReactProp(defaultBoolean = false, name = "showsTraffic")
    public void setShowTraffic(AirMapView airMapView, boolean z) {
        airMapView.map.setTrafficEnabled(z);
    }

    @ReactProp(defaultBoolean = false, name = "showsBuildings")
    public void setShowBuildings(AirMapView airMapView, boolean z) {
        airMapView.map.setBuildingsEnabled(z);
    }

    @ReactProp(defaultBoolean = false, name = "showsIndoors")
    public void setShowIndoors(AirMapView airMapView, boolean z) {
        airMapView.map.setIndoorEnabled(z);
    }

    @ReactProp(defaultBoolean = false, name = "showsIndoorLevelPicker")
    public void setShowsIndoorLevelPicker(AirMapView airMapView, boolean z) {
        airMapView.map.getUiSettings().setIndoorLevelPickerEnabled(z);
    }

    @ReactProp(defaultBoolean = false, name = "showsCompass")
    public void setShowsCompass(AirMapView airMapView, boolean z) {
        airMapView.map.getUiSettings().setCompassEnabled(z);
    }

    @ReactProp(defaultBoolean = false, name = "scrollEnabled")
    public void setScrollEnabled(AirMapView airMapView, boolean z) {
        airMapView.map.getUiSettings().setScrollGesturesEnabled(z);
    }

    @ReactProp(defaultBoolean = false, name = "zoomEnabled")
    public void setZoomEnabled(AirMapView airMapView, boolean z) {
        airMapView.map.getUiSettings().setZoomGesturesEnabled(z);
    }

    @ReactProp(defaultBoolean = true, name = "zoomControlEnabled")
    public void setZoomControlEnabled(AirMapView airMapView, boolean z) {
        airMapView.map.getUiSettings().setZoomControlsEnabled(z);
    }

    @ReactProp(defaultBoolean = false, name = "rotateEnabled")
    public void setRotateEnabled(AirMapView airMapView, boolean z) {
        airMapView.map.getUiSettings().setRotateGesturesEnabled(z);
    }

    @ReactProp(defaultBoolean = true, name = "scrollDuringRotateOrZoomEnabled")
    public void setScrollDuringRotateOrZoomEnabled(AirMapView airMapView, boolean z) {
        airMapView.map.getUiSettings().setScrollGesturesEnabledDuringRotateOrZoom(z);
    }

    @ReactProp(defaultBoolean = false, name = "cacheEnabled")
    public void setCacheEnabled(AirMapView airMapView, boolean z) {
        airMapView.setCacheEnabled(z);
    }

    @ReactProp(defaultBoolean = false, name = "loadingEnabled")
    public void setLoadingEnabled(AirMapView airMapView, boolean z) {
        airMapView.enableMapLoading(z);
    }

    @ReactProp(defaultBoolean = true, name = "moveOnMarkerPress")
    public void setMoveOnMarkerPress(AirMapView airMapView, boolean z) {
        airMapView.setMoveOnMarkerPress(z);
    }

    @ReactProp(customType = "Color", name = "loadingBackgroundColor")
    public void setLoadingBackgroundColor(AirMapView airMapView, Integer num) {
        airMapView.setLoadingBackgroundColor(num);
    }

    @ReactProp(customType = "Color", name = "loadingIndicatorColor")
    public void setLoadingIndicatorColor(AirMapView airMapView, Integer num) {
        airMapView.setLoadingIndicatorColor(num);
    }

    @ReactProp(defaultBoolean = false, name = "pitchEnabled")
    public void setPitchEnabled(AirMapView airMapView, boolean z) {
        airMapView.map.getUiSettings().setTiltGesturesEnabled(z);
    }

    @ReactProp(name = "minZoomLevel")
    public void setMinZoomLevel(AirMapView airMapView, float f) {
        airMapView.map.setMinZoomPreference(f);
    }

    @ReactProp(name = "maxZoomLevel")
    public void setMaxZoomLevel(AirMapView airMapView, float f) {
        airMapView.map.setMaxZoomPreference(f);
    }

    @ReactProp(name = "kmlSrc")
    public void setKmlSrc(AirMapView airMapView, String str) {
        if (str != null) {
            airMapView.setKmlSrc(str);
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(AirMapView airMapView, String str, ReadableArray readableArray) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1144095793:
                if (str.equals("fitToSuppliedMarkers")) {
                    c = 0;
                    break;
                }
                break;
            case -3054903:
                if (str.equals("setIndoorActiveLevelIndex")) {
                    c = 1;
                    break;
                }
                break;
            case 113646119:
                if (str.equals("setCamera")) {
                    c = 2;
                    break;
                }
                break;
            case 369162114:
                if (str.equals("setMapBoundaries")) {
                    c = 3;
                    break;
                }
                break;
            case 483577731:
                if (str.equals("fitToElements")) {
                    c = 4;
                    break;
                }
                break;
            case 1505195366:
                if (str.equals("animateCamera")) {
                    c = 5;
                    break;
                }
                break;
            case 1713586000:
                if (str.equals("animateToRegion")) {
                    c = 6;
                    break;
                }
                break;
            case 2141065199:
                if (str.equals("fitToCoordinates")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (readableArray == null) {
                    return;
                }
                airMapView.fitToSuppliedMarkers(readableArray.getArray(0), readableArray.getMap(1), readableArray.getBoolean(2));
                return;
            case 1:
                if (readableArray == null) {
                    return;
                }
                airMapView.setIndoorActiveLevelIndex(readableArray.getInt(0));
                return;
            case 2:
                if (readableArray == null) {
                    return;
                }
                airMapView.animateToCamera(readableArray.getMap(0), 0);
                return;
            case 3:
                if (readableArray == null) {
                    return;
                }
                airMapView.setMapBoundaries(readableArray.getMap(0), readableArray.getMap(1));
                return;
            case 4:
                if (readableArray == null) {
                    return;
                }
                airMapView.fitToElements(readableArray.getMap(0), readableArray.getBoolean(1));
                return;
            case 5:
                if (readableArray == null) {
                    return;
                }
                airMapView.animateToCamera(readableArray.getMap(0), readableArray.getInt(1));
                return;
            case 6:
                if (readableArray == null) {
                    return;
                }
                ReadableMap map = readableArray.getMap(0);
                int r2 = readableArray.getInt(1);
                double d = map.getDouble("longitude");
                double d2 = map.getDouble("latitude");
                double d3 = map.getDouble("longitudeDelta");
                double d4 = map.getDouble("latitudeDelta") / 2.0d;
                double d5 = d3 / 2.0d;
                airMapView.animateToRegion(new LatLngBounds(new LatLng(d2 - d4, d - d5), new LatLng(d2 + d4, d + d5)), r2);
                return;
            case 7:
                if (readableArray == null) {
                    return;
                }
                airMapView.fitToCoordinates(readableArray.getArray(0), readableArray.getMap(1), readableArray.getBoolean(2));
                return;
            default:
                return;
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        Map m1255of = MapBuilder.m1255of("onMapReady", MapBuilder.m1261of("registrationName", "onMapReady"), "onPress", MapBuilder.m1261of("registrationName", "onPress"), "onLongPress", MapBuilder.m1261of("registrationName", "onLongPress"), "onMarkerPress", MapBuilder.m1261of("registrationName", "onMarkerPress"), "onMarkerSelect", MapBuilder.m1261of("registrationName", "onMarkerSelect"), "onMarkerDeselect", MapBuilder.m1261of("registrationName", "onMarkerDeselect"), "onCalloutPress", MapBuilder.m1261of("registrationName", "onCalloutPress"));
        m1255of.putAll(MapBuilder.m1255of("onUserLocationChange", MapBuilder.m1261of("registrationName", "onUserLocationChange"), "onMarkerDragStart", MapBuilder.m1261of("registrationName", "onMarkerDragStart"), "onMarkerDrag", MapBuilder.m1261of("registrationName", "onMarkerDrag"), "onMarkerDragEnd", MapBuilder.m1261of("registrationName", "onMarkerDragEnd"), "onPanDrag", MapBuilder.m1261of("registrationName", "onPanDrag"), "onKmlReady", MapBuilder.m1261of("registrationName", "onKmlReady"), "onPoiClick", MapBuilder.m1261of("registrationName", "onPoiClick")));
        m1255of.putAll(MapBuilder.m1258of("onIndoorLevelActivated", MapBuilder.m1261of("registrationName", "onIndoorLevelActivated"), "onIndoorBuildingFocused", MapBuilder.m1261of("registrationName", "onIndoorBuildingFocused"), "onDoublePress", MapBuilder.m1261of("registrationName", "onDoublePress"), "onMapLoaded", MapBuilder.m1261of("registrationName", "onMapLoaded")));
        return m1255of;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public LayoutShadowNode createShadowNodeInstance() {
        return new SizeReportingShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(AirMapView airMapView, View view, int r3) {
        airMapView.addFeature(view, r3);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public int getChildCount(AirMapView airMapView) {
        return airMapView.getFeatureCount();
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public View getChildAt(AirMapView airMapView, int r2) {
        return airMapView.getFeatureAt(r2);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(AirMapView airMapView, int r2) {
        airMapView.removeFeatureAt(r2);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void updateExtraData(AirMapView airMapView, Object obj) {
        airMapView.updateExtraData(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pushEvent(ThemedReactContext themedReactContext, View view, String str, WritableMap writableMap) {
        ((RCTEventEmitter) themedReactContext.getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), str, writableMap);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(AirMapView airMapView) {
        airMapView.doDestroy();
        super.onDropViewInstance((AirMapManager) airMapView);
    }
}
