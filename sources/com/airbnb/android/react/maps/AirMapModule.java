package com.airbnb.android.react.maps;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.autofill.HintConstants;
import com.amplitude.api.Constants;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIBlock;
import com.facebook.react.uimanager.UIManagerModule;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapsSdkInitializedCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ReactModule(name = AirMapModule.NAME)
/* loaded from: classes.dex */
public class AirMapModule extends ReactContextBaseJavaModule {
    public static final String NAME = "AirMapModule";
    private static final String SNAPSHOT_FORMAT_JPG = "jpg";
    private static final String SNAPSHOT_FORMAT_PNG = "png";
    private static final String SNAPSHOT_RESULT_BASE64 = "base64";
    private static final String SNAPSHOT_RESULT_FILE = "file";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public AirMapModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("legalNotice", "This license information is displayed in Settings > Google > Open Source on any device running Google Play services.");
        return hashMap;
    }

    public Activity getActivity() {
        return getCurrentActivity();
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }

    @ReactMethod
    public void takeSnapshot(final int r16, ReadableMap readableMap, final Promise promise) {
        Bitmap.CompressFormat compressFormat;
        final ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        final String string = readableMap.hasKey("format") ? readableMap.getString("format") : SNAPSHOT_FORMAT_PNG;
        if (string.equals(SNAPSHOT_FORMAT_PNG)) {
            compressFormat = Bitmap.CompressFormat.PNG;
        } else {
            compressFormat = string.equals(SNAPSHOT_FORMAT_JPG) ? Bitmap.CompressFormat.JPEG : null;
        }
        final Bitmap.CompressFormat compressFormat2 = compressFormat;
        final double d = readableMap.hasKey("quality") ? readableMap.getDouble("quality") : 1.0d;
        DisplayMetrics displayMetrics = reactApplicationContext.getResources().getDisplayMetrics();
        final Integer valueOf = Integer.valueOf(readableMap.hasKey("width") ? (int) (displayMetrics.density * readableMap.getDouble("width")) : 0);
        final Integer valueOf2 = Integer.valueOf(readableMap.hasKey("height") ? (int) (displayMetrics.density * readableMap.getDouble("height")) : 0);
        final String string2 = readableMap.hasKey("result") ? readableMap.getString("result") : "file";
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.airbnb.android.react.maps.AirMapModule.1
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                AirMapView airMapView = (AirMapView) nativeViewHierarchyManager.resolveView(r16);
                if (airMapView == null) {
                    promise.reject("AirMapView not found");
                } else if (airMapView.map == null) {
                    promise.reject("AirMapView.map is not valid");
                } else {
                    airMapView.map.snapshot(new GoogleMap.SnapshotReadyCallback() { // from class: com.airbnb.android.react.maps.AirMapModule.1.1
                        @Override // com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback
                        public void onSnapshotReady(Bitmap bitmap) {
                            if (bitmap == null) {
                                promise.reject("Failed to generate bitmap, snapshot = null");
                                return;
                            }
                            if (valueOf.intValue() != 0 && valueOf2.intValue() != 0 && (valueOf.intValue() != bitmap.getWidth() || valueOf2.intValue() != bitmap.getHeight())) {
                                bitmap = Bitmap.createScaledBitmap(bitmap, valueOf.intValue(), valueOf2.intValue(), true);
                            }
                            if (string2.equals("file")) {
                                try {
                                    File createTempFile = File.createTempFile("AirMapSnapshot", "." + string, reactApplicationContext.getCacheDir());
                                    FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                                    bitmap.compress(compressFormat2, (int) (d * 100.0d), fileOutputStream);
                                    AirMapModule.closeQuietly(fileOutputStream);
                                    promise.resolve(Uri.fromFile(createTempFile).toString());
                                } catch (Exception e) {
                                    promise.reject(e);
                                }
                            } else if (string2.equals("base64")) {
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                bitmap.compress(compressFormat2, (int) (d * 100.0d), byteArrayOutputStream);
                                AirMapModule.closeQuietly(byteArrayOutputStream);
                                promise.resolve(Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2));
                            }
                        }
                    });
                }
            }
        });
    }

    @ReactMethod
    public void getCamera(final int r3, final Promise promise) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.airbnb.android.react.maps.AirMapModule.2
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                AirMapView airMapView = (AirMapView) nativeViewHierarchyManager.resolveView(r3);
                if (airMapView == null) {
                    promise.reject("AirMapView not found");
                } else if (airMapView.map == null) {
                    promise.reject("AirMapView.map is not valid");
                } else {
                    CameraPosition cameraPosition = airMapView.map.getCameraPosition();
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putDouble("latitude", cameraPosition.target.latitude);
                    writableNativeMap.putDouble("longitude", cameraPosition.target.longitude);
                    WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                    writableNativeMap2.putMap(TtmlNode.CENTER, writableNativeMap);
                    writableNativeMap2.putDouble("heading", cameraPosition.bearing);
                    writableNativeMap2.putDouble("zoom", cameraPosition.zoom);
                    writableNativeMap2.putDouble("pitch", cameraPosition.tilt);
                    promise.resolve(writableNativeMap2);
                }
            }
        });
    }

    @ReactMethod
    public void getAddressFromCoordinates(final int r9, final ReadableMap readableMap, final Promise promise) {
        final ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.airbnb.android.react.maps.AirMapModule.3
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                AirMapView airMapView = (AirMapView) nativeViewHierarchyManager.resolveView(r9);
                if (airMapView == null) {
                    promise.reject("AirMapView not found");
                } else if (airMapView.map == null) {
                    promise.reject("AirMapView.map is not valid");
                } else {
                    ReadableMap readableMap2 = readableMap;
                    if (readableMap2 == null || !readableMap2.hasKey("latitude") || !readableMap.hasKey("longitude")) {
                        promise.reject("Invalid coordinate format");
                        return;
                    }
                    try {
                        List<Address> fromLocation = new Geocoder(reactApplicationContext).getFromLocation(readableMap.getDouble("latitude"), readableMap.getDouble("longitude"), 1);
                        if (fromLocation.isEmpty()) {
                            promise.reject("Can not get address location");
                            return;
                        }
                        Address address = fromLocation.get(0);
                        WritableNativeMap writableNativeMap = new WritableNativeMap();
                        writableNativeMap.putString("name", address.getFeatureName());
                        writableNativeMap.putString("locality", address.getLocality());
                        writableNativeMap.putString("thoroughfare", address.getThoroughfare());
                        writableNativeMap.putString("subThoroughfare", address.getSubThoroughfare());
                        writableNativeMap.putString("subLocality", address.getSubLocality());
                        writableNativeMap.putString("administrativeArea", address.getAdminArea());
                        writableNativeMap.putString("subAdministrativeArea", address.getSubAdminArea());
                        writableNativeMap.putString(HintConstants.AUTOFILL_HINT_POSTAL_CODE, address.getPostalCode());
                        writableNativeMap.putString("countryCode", address.getCountryCode());
                        writableNativeMap.putString(Constants.AMP_TRACKING_OPTION_COUNTRY, address.getCountryName());
                        promise.resolve(writableNativeMap);
                    } catch (IOException unused) {
                        promise.reject("Can not get address location");
                    }
                }
            }
        });
    }

    @ReactMethod
    public void pointForCoordinate(final int r11, ReadableMap readableMap, final Promise promise) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        final double d = reactApplicationContext.getResources().getDisplayMetrics().density;
        final LatLng latLng = new LatLng(readableMap.hasKey("latitude") ? readableMap.getDouble("latitude") : 0.0d, readableMap.hasKey("longitude") ? readableMap.getDouble("longitude") : 0.0d);
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.airbnb.android.react.maps.AirMapModule.4
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                AirMapView airMapView = (AirMapView) nativeViewHierarchyManager.resolveView(r11);
                if (airMapView == null) {
                    promise.reject("AirMapView not found");
                } else if (airMapView.map == null) {
                    promise.reject("AirMapView.map is not valid");
                } else {
                    Point screenLocation = airMapView.map.getProjection().toScreenLocation(latLng);
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putDouble("x", screenLocation.x / d);
                    writableNativeMap.putDouble("y", screenLocation.y / d);
                    promise.resolve(writableNativeMap);
                }
            }
        });
    }

    @ReactMethod
    public void coordinateForPoint(final int r9, ReadableMap readableMap, final Promise promise) {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        double d = reactApplicationContext.getResources().getDisplayMetrics().density;
        final Point point = new Point(readableMap.hasKey("x") ? (int) (readableMap.getDouble("x") * d) : 0, readableMap.hasKey("y") ? (int) (readableMap.getDouble("y") * d) : 0);
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.airbnb.android.react.maps.AirMapModule.5
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                AirMapView airMapView = (AirMapView) nativeViewHierarchyManager.resolveView(r9);
                if (airMapView == null) {
                    promise.reject("AirMapView not found");
                } else if (airMapView.map == null) {
                    promise.reject("AirMapView.map is not valid");
                } else {
                    LatLng fromScreenLocation = airMapView.map.getProjection().fromScreenLocation(point);
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    writableNativeMap.putDouble("latitude", fromScreenLocation.latitude);
                    writableNativeMap.putDouble("longitude", fromScreenLocation.longitude);
                    promise.resolve(writableNativeMap);
                }
            }
        });
    }

    @ReactMethod
    public void getMapBoundaries(final int r3, final Promise promise) {
        ((UIManagerModule) getReactApplicationContext().getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.airbnb.android.react.maps.AirMapModule.6
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                AirMapView airMapView = (AirMapView) nativeViewHierarchyManager.resolveView(r3);
                if (airMapView == null) {
                    promise.reject("AirMapView not found");
                } else if (airMapView.map == null) {
                    promise.reject("AirMapView.map is not valid");
                } else {
                    double[][] mapBoundaries = airMapView.getMapBoundaries();
                    WritableNativeMap writableNativeMap = new WritableNativeMap();
                    WritableNativeMap writableNativeMap2 = new WritableNativeMap();
                    WritableNativeMap writableNativeMap3 = new WritableNativeMap();
                    writableNativeMap2.putDouble("longitude", mapBoundaries[0][0]);
                    writableNativeMap2.putDouble("latitude", mapBoundaries[0][1]);
                    writableNativeMap3.putDouble("longitude", mapBoundaries[1][0]);
                    writableNativeMap3.putDouble("latitude", mapBoundaries[1][1]);
                    writableNativeMap.putMap("northEast", writableNativeMap2);
                    writableNativeMap.putMap("southWest", writableNativeMap3);
                    promise.resolve(writableNativeMap);
                }
            }
        });
    }

    @ReactMethod
    public void enableLatestRenderer(final Promise promise) {
        final ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        ((UIManagerModule) reactApplicationContext.getNativeModule(UIManagerModule.class)).addUIBlock(new UIBlock() { // from class: com.airbnb.android.react.maps.AirMapModule.7
            @Override // com.facebook.react.uimanager.UIBlock
            public void execute(NativeViewHierarchyManager nativeViewHierarchyManager) {
                MapsInitializer.initialize(reactApplicationContext, MapsInitializer.Renderer.LATEST, new OnMapsSdkInitializedCallback() { // from class: com.airbnb.android.react.maps.AirMapModule.7.1
                    @Override // com.google.android.gms.maps.OnMapsSdkInitializedCallback
                    public void onMapsSdkInitialized(MapsInitializer.Renderer renderer) {
                        Log.d("AirMapRenderer", renderer.toString());
                        promise.resolve(renderer.toString());
                    }
                });
            }
        });
    }
}
