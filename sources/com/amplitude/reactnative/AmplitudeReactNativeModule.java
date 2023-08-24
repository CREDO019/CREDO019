package com.amplitude.reactnative;

import com.amplitude.api.Amplitude;
import com.amplitude.api.AmplitudeClient;
import com.amplitude.api.AmplitudeLogCallback;
import com.amplitude.api.AmplitudeServerZone;
import com.amplitude.api.Constants;
import com.amplitude.api.IngestionMetadata;
import com.amplitude.api.Plan;
import com.amplitude.api.Revenue;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.onesignal.OneSignalDbContract;
import org.json.JSONException;
import org.json.JSONObject;

@ReactModule(name = AmplitudeReactNativeModule.NAME)
/* loaded from: classes.dex */
public class AmplitudeReactNativeModule extends ReactContextBaseJavaModule {
    public static final String NAME = "AmplitudeReactNative";
    private final ReactApplicationContext reactContext;

    @ReactMethod
    public void addListener(String str) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public AmplitudeReactNativeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    @ReactMethod
    public void initialize(String str, String str2, Promise promise) {
        Amplitude.getInstance(str).initialize(this.reactContext, str2);
        promise.resolve(true);
    }

    @ReactMethod
    public void logEvent(String str, String str2, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.logEvent(str2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void logEventWithProperties(String str, String str2, ReadableMap readableMap, Promise promise) throws JSONException {
        JSONObject convertMapToJson = ReactNativeHelper.convertMapToJson(readableMap);
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.logEvent(str2, convertMapToJson);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void enableCoppaControl(String str, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.enableCoppaControl();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void disableCoppaControl(String str, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.disableCoppaControl();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void regenerateDeviceId(String str, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.regenerateDeviceId();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setDeviceId(String str, String str2, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setDeviceId(str2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void getDeviceId(String str, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            promise.resolve(amplitude.getDeviceId());
        }
    }

    @ReactMethod
    public void setAdvertisingIdForDeviceId(String str, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.useAdvertisingIdForDeviceId();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setAppSetIdForDeviceId(String str, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.useAppSetIdForDeviceId();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setOptOut(String str, boolean z, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setOptOut(z);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setLibraryName(String str, String str2, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setLibraryName(str2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setLibraryVersion(String str, String str2, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setLibraryVersion(str2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void trackingSessionEvents(String str, boolean z, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.trackSessionEvents(z);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setUseDynamicConfig(String str, boolean z, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setUseDynamicConfig(z);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setUserId(String str, String str2, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setUserId(str2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setServerUrl(String str, String str2, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setServerUrl(str2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void logRevenueV2(String str, ReadableMap readableMap, Promise promise) throws JSONException {
        JSONObject convertMapToJson = ReactNativeHelper.convertMapToJson(readableMap);
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.logRevenueV2(createRevenue(convertMapToJson));
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void identify(String str, ReadableMap readableMap, Promise promise) throws JSONException {
        JSONObject convertMapToJson = ReactNativeHelper.convertMapToJson(readableMap);
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.identify(createIdentify(convertMapToJson));
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void groupIdentify(String str, String str2, String str3, ReadableMap readableMap, Promise promise) throws JSONException {
        JSONObject convertMapToJson = ReactNativeHelper.convertMapToJson(readableMap);
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.groupIdentify(str2, str3, createIdentify(convertMapToJson));
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setUserProperties(String str, ReadableMap readableMap, Promise promise) throws JSONException {
        JSONObject convertMapToJson = ReactNativeHelper.convertMapToJson(readableMap);
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setUserProperties(convertMapToJson);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void clearUserProperties(String str, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.clearUserProperties();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setGroup(String str, String str2, String str3, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setGroup(str2, str3);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void uploadEvents(String str, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.uploadEvents();
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void getSessionId(String str, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            promise.resolve(Double.valueOf(amplitude.getSessionId()));
        }
    }

    @ReactMethod
    public void setMinTimeBetweenSessionsMillis(String str, double d, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setMinTimeBetweenSessionsMillis((long) d);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setServerZone(String str, String str2, boolean z, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setServerZone(str2.equals("EU") ? AmplitudeServerZone.EU : AmplitudeServerZone.US, z);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setEventUploadMaxBatchSize(String str, int r2, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setEventUploadMaxBatchSize(r2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setEventUploadPeriodMillis(String str, int r2, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setEventUploadPeriodMillis(r2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setEventUploadThreshold(String str, int r2, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setEventUploadThreshold(r2);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setPlan(String str, ReadableMap readableMap, Promise promise) {
        Plan plan = new Plan();
        if (readableMap.hasKey(Constants.AMP_PLAN_BRANCH)) {
            plan.setBranch(readableMap.getString(Constants.AMP_PLAN_BRANCH));
        }
        if (readableMap.hasKey("source")) {
            plan.setSource(readableMap.getString("source"));
        }
        if (readableMap.hasKey(Constants.AMP_PLAN_VERSION)) {
            plan.setVersion(readableMap.getString(Constants.AMP_PLAN_VERSION));
        }
        if (readableMap.hasKey(Constants.AMP_PLAN_VERSION_ID)) {
            plan.setVersionId(readableMap.getString(Constants.AMP_PLAN_VERSION_ID));
        }
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setPlan(plan);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setIngestionMetadata(String str, ReadableMap readableMap, Promise promise) {
        IngestionMetadata ingestionMetadata = new IngestionMetadata();
        if (readableMap.hasKey("sourceName")) {
            ingestionMetadata.setSourceName(readableMap.getString("sourceName"));
        }
        if (readableMap.hasKey("sourceVersion")) {
            ingestionMetadata.setSourceVersion(readableMap.getString("sourceVersion"));
        }
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setIngestionMetadata(ingestionMetadata);
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void enableLogging(String str, Boolean bool, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.enableLogging(bool.booleanValue());
            if (bool.booleanValue()) {
                amplitude.setLogCallback(new AmplitudeLogCallback() { // from class: com.amplitude.reactnative.AmplitudeReactNativeModule.1
                    @Override // com.amplitude.api.AmplitudeLogCallback
                    public void onError(String str2, String str3) {
                        WritableNativeMap writableNativeMap = new WritableNativeMap();
                        writableNativeMap.putString("tag", str2);
                        writableNativeMap.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, str3);
                        ((DeviceEventManagerModule.RCTDeviceEventEmitter) AmplitudeReactNativeModule.this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("AmplitudeLogError", writableNativeMap);
                    }
                });
            } else {
                amplitude.setLogCallback(null);
            }
            promise.resolve(true);
        }
    }

    @ReactMethod
    public void setLogLevel(String str, Integer num, Promise promise) {
        AmplitudeClient amplitude = Amplitude.getInstance(str);
        synchronized (amplitude) {
            amplitude.setLogLevel(num.intValue());
            promise.resolve(true);
        }
    }

    private Revenue createRevenue(JSONObject jSONObject) {
        Revenue revenue = new Revenue();
        try {
            if (jSONObject.has("productId")) {
                revenue.setProductId(jSONObject.getString("productId"));
            }
            if (jSONObject.has("price")) {
                revenue.setPrice(jSONObject.getDouble("price"));
            }
            if (jSONObject.has("quantity")) {
                revenue.setQuantity(jSONObject.getInt("quantity"));
            } else {
                revenue.setQuantity(1);
            }
            if (jSONObject.has("revenueType")) {
                revenue.setRevenueType(jSONObject.getString("revenueType"));
            }
            if (jSONObject.has("receipt") && jSONObject.has("receiptSignature")) {
                revenue.setReceipt(jSONObject.getString("receipt"), jSONObject.getString("receiptSignature"));
            }
            if (jSONObject.has("eventProperties")) {
                revenue.setEventProperties(jSONObject.getJSONObject("eventProperties"));
            }
        } catch (JSONException unused) {
        }
        return revenue;
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x01f9 A[Catch: JSONException -> 0x0313, TryCatch #0 {JSONException -> 0x0313, blocks: (B:6:0x0015, B:7:0x001d, B:9:0x0023, B:10:0x002e, B:36:0x0081, B:38:0x0085, B:40:0x008d, B:41:0x0095, B:43:0x009d, B:44:0x00a5, B:46:0x00ad, B:47:0x00b5, B:49:0x00bd, B:50:0x00c5, B:52:0x00cd, B:53:0x00d4, B:55:0x00dc, B:56:0x00e4, B:58:0x00ec, B:59:0x00f4, B:61:0x00fc, B:62:0x0104, B:64:0x010c, B:65:0x0114, B:67:0x011c, B:68:0x0124, B:70:0x012c, B:71:0x0133, B:73:0x013b, B:74:0x0143, B:76:0x014b, B:77:0x0153, B:79:0x015b, B:80:0x0163, B:82:0x016b, B:83:0x0173, B:85:0x017b, B:86:0x0183, B:88:0x018b, B:89:0x0192, B:91:0x019a, B:92:0x01a2, B:94:0x01aa, B:95:0x01b2, B:97:0x01ba, B:98:0x01c2, B:100:0x01ca, B:101:0x01d2, B:103:0x01da, B:104:0x01e2, B:106:0x01ea, B:107:0x01f1, B:109:0x01f9, B:110:0x0201, B:112:0x0209, B:113:0x0211, B:115:0x0219, B:116:0x0221, B:118:0x0229, B:119:0x0231, B:121:0x0239, B:122:0x0241, B:124:0x0249, B:125:0x0250, B:127:0x0258, B:128:0x0260, B:130:0x0268, B:131:0x0270, B:133:0x0278, B:134:0x0280, B:136:0x0288, B:137:0x0290, B:139:0x0298, B:140:0x02a0, B:142:0x02a8, B:143:0x02af, B:145:0x02b7, B:146:0x02bf, B:148:0x02c7, B:149:0x02cf, B:151:0x02d7, B:152:0x02df, B:154:0x02e7, B:155:0x02ef, B:157:0x02f7, B:158:0x02ff, B:160:0x0307, B:161:0x030e, B:12:0x0032, B:15:0x003c, B:18:0x0046, B:21:0x0050, B:24:0x005a, B:27:0x0064, B:30:0x006e, B:33:0x0078), top: B:165:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0201 A[Catch: JSONException -> 0x0313, TryCatch #0 {JSONException -> 0x0313, blocks: (B:6:0x0015, B:7:0x001d, B:9:0x0023, B:10:0x002e, B:36:0x0081, B:38:0x0085, B:40:0x008d, B:41:0x0095, B:43:0x009d, B:44:0x00a5, B:46:0x00ad, B:47:0x00b5, B:49:0x00bd, B:50:0x00c5, B:52:0x00cd, B:53:0x00d4, B:55:0x00dc, B:56:0x00e4, B:58:0x00ec, B:59:0x00f4, B:61:0x00fc, B:62:0x0104, B:64:0x010c, B:65:0x0114, B:67:0x011c, B:68:0x0124, B:70:0x012c, B:71:0x0133, B:73:0x013b, B:74:0x0143, B:76:0x014b, B:77:0x0153, B:79:0x015b, B:80:0x0163, B:82:0x016b, B:83:0x0173, B:85:0x017b, B:86:0x0183, B:88:0x018b, B:89:0x0192, B:91:0x019a, B:92:0x01a2, B:94:0x01aa, B:95:0x01b2, B:97:0x01ba, B:98:0x01c2, B:100:0x01ca, B:101:0x01d2, B:103:0x01da, B:104:0x01e2, B:106:0x01ea, B:107:0x01f1, B:109:0x01f9, B:110:0x0201, B:112:0x0209, B:113:0x0211, B:115:0x0219, B:116:0x0221, B:118:0x0229, B:119:0x0231, B:121:0x0239, B:122:0x0241, B:124:0x0249, B:125:0x0250, B:127:0x0258, B:128:0x0260, B:130:0x0268, B:131:0x0270, B:133:0x0278, B:134:0x0280, B:136:0x0288, B:137:0x0290, B:139:0x0298, B:140:0x02a0, B:142:0x02a8, B:143:0x02af, B:145:0x02b7, B:146:0x02bf, B:148:0x02c7, B:149:0x02cf, B:151:0x02d7, B:152:0x02df, B:154:0x02e7, B:155:0x02ef, B:157:0x02f7, B:158:0x02ff, B:160:0x0307, B:161:0x030e, B:12:0x0032, B:15:0x003c, B:18:0x0046, B:21:0x0050, B:24:0x005a, B:27:0x0064, B:30:0x006e, B:33:0x0078), top: B:165:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0258 A[Catch: JSONException -> 0x0313, TryCatch #0 {JSONException -> 0x0313, blocks: (B:6:0x0015, B:7:0x001d, B:9:0x0023, B:10:0x002e, B:36:0x0081, B:38:0x0085, B:40:0x008d, B:41:0x0095, B:43:0x009d, B:44:0x00a5, B:46:0x00ad, B:47:0x00b5, B:49:0x00bd, B:50:0x00c5, B:52:0x00cd, B:53:0x00d4, B:55:0x00dc, B:56:0x00e4, B:58:0x00ec, B:59:0x00f4, B:61:0x00fc, B:62:0x0104, B:64:0x010c, B:65:0x0114, B:67:0x011c, B:68:0x0124, B:70:0x012c, B:71:0x0133, B:73:0x013b, B:74:0x0143, B:76:0x014b, B:77:0x0153, B:79:0x015b, B:80:0x0163, B:82:0x016b, B:83:0x0173, B:85:0x017b, B:86:0x0183, B:88:0x018b, B:89:0x0192, B:91:0x019a, B:92:0x01a2, B:94:0x01aa, B:95:0x01b2, B:97:0x01ba, B:98:0x01c2, B:100:0x01ca, B:101:0x01d2, B:103:0x01da, B:104:0x01e2, B:106:0x01ea, B:107:0x01f1, B:109:0x01f9, B:110:0x0201, B:112:0x0209, B:113:0x0211, B:115:0x0219, B:116:0x0221, B:118:0x0229, B:119:0x0231, B:121:0x0239, B:122:0x0241, B:124:0x0249, B:125:0x0250, B:127:0x0258, B:128:0x0260, B:130:0x0268, B:131:0x0270, B:133:0x0278, B:134:0x0280, B:136:0x0288, B:137:0x0290, B:139:0x0298, B:140:0x02a0, B:142:0x02a8, B:143:0x02af, B:145:0x02b7, B:146:0x02bf, B:148:0x02c7, B:149:0x02cf, B:151:0x02d7, B:152:0x02df, B:154:0x02e7, B:155:0x02ef, B:157:0x02f7, B:158:0x02ff, B:160:0x0307, B:161:0x030e, B:12:0x0032, B:15:0x003c, B:18:0x0046, B:21:0x0050, B:24:0x005a, B:27:0x0064, B:30:0x006e, B:33:0x0078), top: B:165:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0260 A[Catch: JSONException -> 0x0313, TryCatch #0 {JSONException -> 0x0313, blocks: (B:6:0x0015, B:7:0x001d, B:9:0x0023, B:10:0x002e, B:36:0x0081, B:38:0x0085, B:40:0x008d, B:41:0x0095, B:43:0x009d, B:44:0x00a5, B:46:0x00ad, B:47:0x00b5, B:49:0x00bd, B:50:0x00c5, B:52:0x00cd, B:53:0x00d4, B:55:0x00dc, B:56:0x00e4, B:58:0x00ec, B:59:0x00f4, B:61:0x00fc, B:62:0x0104, B:64:0x010c, B:65:0x0114, B:67:0x011c, B:68:0x0124, B:70:0x012c, B:71:0x0133, B:73:0x013b, B:74:0x0143, B:76:0x014b, B:77:0x0153, B:79:0x015b, B:80:0x0163, B:82:0x016b, B:83:0x0173, B:85:0x017b, B:86:0x0183, B:88:0x018b, B:89:0x0192, B:91:0x019a, B:92:0x01a2, B:94:0x01aa, B:95:0x01b2, B:97:0x01ba, B:98:0x01c2, B:100:0x01ca, B:101:0x01d2, B:103:0x01da, B:104:0x01e2, B:106:0x01ea, B:107:0x01f1, B:109:0x01f9, B:110:0x0201, B:112:0x0209, B:113:0x0211, B:115:0x0219, B:116:0x0221, B:118:0x0229, B:119:0x0231, B:121:0x0239, B:122:0x0241, B:124:0x0249, B:125:0x0250, B:127:0x0258, B:128:0x0260, B:130:0x0268, B:131:0x0270, B:133:0x0278, B:134:0x0280, B:136:0x0288, B:137:0x0290, B:139:0x0298, B:140:0x02a0, B:142:0x02a8, B:143:0x02af, B:145:0x02b7, B:146:0x02bf, B:148:0x02c7, B:149:0x02cf, B:151:0x02d7, B:152:0x02df, B:154:0x02e7, B:155:0x02ef, B:157:0x02f7, B:158:0x02ff, B:160:0x0307, B:161:0x030e, B:12:0x0032, B:15:0x003c, B:18:0x0046, B:21:0x0050, B:24:0x005a, B:27:0x0064, B:30:0x006e, B:33:0x0078), top: B:165:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x02b7 A[Catch: JSONException -> 0x0313, TryCatch #0 {JSONException -> 0x0313, blocks: (B:6:0x0015, B:7:0x001d, B:9:0x0023, B:10:0x002e, B:36:0x0081, B:38:0x0085, B:40:0x008d, B:41:0x0095, B:43:0x009d, B:44:0x00a5, B:46:0x00ad, B:47:0x00b5, B:49:0x00bd, B:50:0x00c5, B:52:0x00cd, B:53:0x00d4, B:55:0x00dc, B:56:0x00e4, B:58:0x00ec, B:59:0x00f4, B:61:0x00fc, B:62:0x0104, B:64:0x010c, B:65:0x0114, B:67:0x011c, B:68:0x0124, B:70:0x012c, B:71:0x0133, B:73:0x013b, B:74:0x0143, B:76:0x014b, B:77:0x0153, B:79:0x015b, B:80:0x0163, B:82:0x016b, B:83:0x0173, B:85:0x017b, B:86:0x0183, B:88:0x018b, B:89:0x0192, B:91:0x019a, B:92:0x01a2, B:94:0x01aa, B:95:0x01b2, B:97:0x01ba, B:98:0x01c2, B:100:0x01ca, B:101:0x01d2, B:103:0x01da, B:104:0x01e2, B:106:0x01ea, B:107:0x01f1, B:109:0x01f9, B:110:0x0201, B:112:0x0209, B:113:0x0211, B:115:0x0219, B:116:0x0221, B:118:0x0229, B:119:0x0231, B:121:0x0239, B:122:0x0241, B:124:0x0249, B:125:0x0250, B:127:0x0258, B:128:0x0260, B:130:0x0268, B:131:0x0270, B:133:0x0278, B:134:0x0280, B:136:0x0288, B:137:0x0290, B:139:0x0298, B:140:0x02a0, B:142:0x02a8, B:143:0x02af, B:145:0x02b7, B:146:0x02bf, B:148:0x02c7, B:149:0x02cf, B:151:0x02d7, B:152:0x02df, B:154:0x02e7, B:155:0x02ef, B:157:0x02f7, B:158:0x02ff, B:160:0x0307, B:161:0x030e, B:12:0x0032, B:15:0x003c, B:18:0x0046, B:21:0x0050, B:24:0x005a, B:27:0x0064, B:30:0x006e, B:33:0x0078), top: B:165:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02bf A[Catch: JSONException -> 0x0313, TryCatch #0 {JSONException -> 0x0313, blocks: (B:6:0x0015, B:7:0x001d, B:9:0x0023, B:10:0x002e, B:36:0x0081, B:38:0x0085, B:40:0x008d, B:41:0x0095, B:43:0x009d, B:44:0x00a5, B:46:0x00ad, B:47:0x00b5, B:49:0x00bd, B:50:0x00c5, B:52:0x00cd, B:53:0x00d4, B:55:0x00dc, B:56:0x00e4, B:58:0x00ec, B:59:0x00f4, B:61:0x00fc, B:62:0x0104, B:64:0x010c, B:65:0x0114, B:67:0x011c, B:68:0x0124, B:70:0x012c, B:71:0x0133, B:73:0x013b, B:74:0x0143, B:76:0x014b, B:77:0x0153, B:79:0x015b, B:80:0x0163, B:82:0x016b, B:83:0x0173, B:85:0x017b, B:86:0x0183, B:88:0x018b, B:89:0x0192, B:91:0x019a, B:92:0x01a2, B:94:0x01aa, B:95:0x01b2, B:97:0x01ba, B:98:0x01c2, B:100:0x01ca, B:101:0x01d2, B:103:0x01da, B:104:0x01e2, B:106:0x01ea, B:107:0x01f1, B:109:0x01f9, B:110:0x0201, B:112:0x0209, B:113:0x0211, B:115:0x0219, B:116:0x0221, B:118:0x0229, B:119:0x0231, B:121:0x0239, B:122:0x0241, B:124:0x0249, B:125:0x0250, B:127:0x0258, B:128:0x0260, B:130:0x0268, B:131:0x0270, B:133:0x0278, B:134:0x0280, B:136:0x0288, B:137:0x0290, B:139:0x0298, B:140:0x02a0, B:142:0x02a8, B:143:0x02af, B:145:0x02b7, B:146:0x02bf, B:148:0x02c7, B:149:0x02cf, B:151:0x02d7, B:152:0x02df, B:154:0x02e7, B:155:0x02ef, B:157:0x02f7, B:158:0x02ff, B:160:0x0307, B:161:0x030e, B:12:0x0032, B:15:0x003c, B:18:0x0046, B:21:0x0050, B:24:0x005a, B:27:0x0064, B:30:0x006e, B:33:0x0078), top: B:165:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00dc A[Catch: JSONException -> 0x0313, TryCatch #0 {JSONException -> 0x0313, blocks: (B:6:0x0015, B:7:0x001d, B:9:0x0023, B:10:0x002e, B:36:0x0081, B:38:0x0085, B:40:0x008d, B:41:0x0095, B:43:0x009d, B:44:0x00a5, B:46:0x00ad, B:47:0x00b5, B:49:0x00bd, B:50:0x00c5, B:52:0x00cd, B:53:0x00d4, B:55:0x00dc, B:56:0x00e4, B:58:0x00ec, B:59:0x00f4, B:61:0x00fc, B:62:0x0104, B:64:0x010c, B:65:0x0114, B:67:0x011c, B:68:0x0124, B:70:0x012c, B:71:0x0133, B:73:0x013b, B:74:0x0143, B:76:0x014b, B:77:0x0153, B:79:0x015b, B:80:0x0163, B:82:0x016b, B:83:0x0173, B:85:0x017b, B:86:0x0183, B:88:0x018b, B:89:0x0192, B:91:0x019a, B:92:0x01a2, B:94:0x01aa, B:95:0x01b2, B:97:0x01ba, B:98:0x01c2, B:100:0x01ca, B:101:0x01d2, B:103:0x01da, B:104:0x01e2, B:106:0x01ea, B:107:0x01f1, B:109:0x01f9, B:110:0x0201, B:112:0x0209, B:113:0x0211, B:115:0x0219, B:116:0x0221, B:118:0x0229, B:119:0x0231, B:121:0x0239, B:122:0x0241, B:124:0x0249, B:125:0x0250, B:127:0x0258, B:128:0x0260, B:130:0x0268, B:131:0x0270, B:133:0x0278, B:134:0x0280, B:136:0x0288, B:137:0x0290, B:139:0x0298, B:140:0x02a0, B:142:0x02a8, B:143:0x02af, B:145:0x02b7, B:146:0x02bf, B:148:0x02c7, B:149:0x02cf, B:151:0x02d7, B:152:0x02df, B:154:0x02e7, B:155:0x02ef, B:157:0x02f7, B:158:0x02ff, B:160:0x0307, B:161:0x030e, B:12:0x0032, B:15:0x003c, B:18:0x0046, B:21:0x0050, B:24:0x005a, B:27:0x0064, B:30:0x006e, B:33:0x0078), top: B:165:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00e4 A[Catch: JSONException -> 0x0313, TryCatch #0 {JSONException -> 0x0313, blocks: (B:6:0x0015, B:7:0x001d, B:9:0x0023, B:10:0x002e, B:36:0x0081, B:38:0x0085, B:40:0x008d, B:41:0x0095, B:43:0x009d, B:44:0x00a5, B:46:0x00ad, B:47:0x00b5, B:49:0x00bd, B:50:0x00c5, B:52:0x00cd, B:53:0x00d4, B:55:0x00dc, B:56:0x00e4, B:58:0x00ec, B:59:0x00f4, B:61:0x00fc, B:62:0x0104, B:64:0x010c, B:65:0x0114, B:67:0x011c, B:68:0x0124, B:70:0x012c, B:71:0x0133, B:73:0x013b, B:74:0x0143, B:76:0x014b, B:77:0x0153, B:79:0x015b, B:80:0x0163, B:82:0x016b, B:83:0x0173, B:85:0x017b, B:86:0x0183, B:88:0x018b, B:89:0x0192, B:91:0x019a, B:92:0x01a2, B:94:0x01aa, B:95:0x01b2, B:97:0x01ba, B:98:0x01c2, B:100:0x01ca, B:101:0x01d2, B:103:0x01da, B:104:0x01e2, B:106:0x01ea, B:107:0x01f1, B:109:0x01f9, B:110:0x0201, B:112:0x0209, B:113:0x0211, B:115:0x0219, B:116:0x0221, B:118:0x0229, B:119:0x0231, B:121:0x0239, B:122:0x0241, B:124:0x0249, B:125:0x0250, B:127:0x0258, B:128:0x0260, B:130:0x0268, B:131:0x0270, B:133:0x0278, B:134:0x0280, B:136:0x0288, B:137:0x0290, B:139:0x0298, B:140:0x02a0, B:142:0x02a8, B:143:0x02af, B:145:0x02b7, B:146:0x02bf, B:148:0x02c7, B:149:0x02cf, B:151:0x02d7, B:152:0x02df, B:154:0x02e7, B:155:0x02ef, B:157:0x02f7, B:158:0x02ff, B:160:0x0307, B:161:0x030e, B:12:0x0032, B:15:0x003c, B:18:0x0046, B:21:0x0050, B:24:0x005a, B:27:0x0064, B:30:0x006e, B:33:0x0078), top: B:165:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x013b A[Catch: JSONException -> 0x0313, TryCatch #0 {JSONException -> 0x0313, blocks: (B:6:0x0015, B:7:0x001d, B:9:0x0023, B:10:0x002e, B:36:0x0081, B:38:0x0085, B:40:0x008d, B:41:0x0095, B:43:0x009d, B:44:0x00a5, B:46:0x00ad, B:47:0x00b5, B:49:0x00bd, B:50:0x00c5, B:52:0x00cd, B:53:0x00d4, B:55:0x00dc, B:56:0x00e4, B:58:0x00ec, B:59:0x00f4, B:61:0x00fc, B:62:0x0104, B:64:0x010c, B:65:0x0114, B:67:0x011c, B:68:0x0124, B:70:0x012c, B:71:0x0133, B:73:0x013b, B:74:0x0143, B:76:0x014b, B:77:0x0153, B:79:0x015b, B:80:0x0163, B:82:0x016b, B:83:0x0173, B:85:0x017b, B:86:0x0183, B:88:0x018b, B:89:0x0192, B:91:0x019a, B:92:0x01a2, B:94:0x01aa, B:95:0x01b2, B:97:0x01ba, B:98:0x01c2, B:100:0x01ca, B:101:0x01d2, B:103:0x01da, B:104:0x01e2, B:106:0x01ea, B:107:0x01f1, B:109:0x01f9, B:110:0x0201, B:112:0x0209, B:113:0x0211, B:115:0x0219, B:116:0x0221, B:118:0x0229, B:119:0x0231, B:121:0x0239, B:122:0x0241, B:124:0x0249, B:125:0x0250, B:127:0x0258, B:128:0x0260, B:130:0x0268, B:131:0x0270, B:133:0x0278, B:134:0x0280, B:136:0x0288, B:137:0x0290, B:139:0x0298, B:140:0x02a0, B:142:0x02a8, B:143:0x02af, B:145:0x02b7, B:146:0x02bf, B:148:0x02c7, B:149:0x02cf, B:151:0x02d7, B:152:0x02df, B:154:0x02e7, B:155:0x02ef, B:157:0x02f7, B:158:0x02ff, B:160:0x0307, B:161:0x030e, B:12:0x0032, B:15:0x003c, B:18:0x0046, B:21:0x0050, B:24:0x005a, B:27:0x0064, B:30:0x006e, B:33:0x0078), top: B:165:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0143 A[Catch: JSONException -> 0x0313, TryCatch #0 {JSONException -> 0x0313, blocks: (B:6:0x0015, B:7:0x001d, B:9:0x0023, B:10:0x002e, B:36:0x0081, B:38:0x0085, B:40:0x008d, B:41:0x0095, B:43:0x009d, B:44:0x00a5, B:46:0x00ad, B:47:0x00b5, B:49:0x00bd, B:50:0x00c5, B:52:0x00cd, B:53:0x00d4, B:55:0x00dc, B:56:0x00e4, B:58:0x00ec, B:59:0x00f4, B:61:0x00fc, B:62:0x0104, B:64:0x010c, B:65:0x0114, B:67:0x011c, B:68:0x0124, B:70:0x012c, B:71:0x0133, B:73:0x013b, B:74:0x0143, B:76:0x014b, B:77:0x0153, B:79:0x015b, B:80:0x0163, B:82:0x016b, B:83:0x0173, B:85:0x017b, B:86:0x0183, B:88:0x018b, B:89:0x0192, B:91:0x019a, B:92:0x01a2, B:94:0x01aa, B:95:0x01b2, B:97:0x01ba, B:98:0x01c2, B:100:0x01ca, B:101:0x01d2, B:103:0x01da, B:104:0x01e2, B:106:0x01ea, B:107:0x01f1, B:109:0x01f9, B:110:0x0201, B:112:0x0209, B:113:0x0211, B:115:0x0219, B:116:0x0221, B:118:0x0229, B:119:0x0231, B:121:0x0239, B:122:0x0241, B:124:0x0249, B:125:0x0250, B:127:0x0258, B:128:0x0260, B:130:0x0268, B:131:0x0270, B:133:0x0278, B:134:0x0280, B:136:0x0288, B:137:0x0290, B:139:0x0298, B:140:0x02a0, B:142:0x02a8, B:143:0x02af, B:145:0x02b7, B:146:0x02bf, B:148:0x02c7, B:149:0x02cf, B:151:0x02d7, B:152:0x02df, B:154:0x02e7, B:155:0x02ef, B:157:0x02f7, B:158:0x02ff, B:160:0x0307, B:161:0x030e, B:12:0x0032, B:15:0x003c, B:18:0x0046, B:21:0x0050, B:24:0x005a, B:27:0x0064, B:30:0x006e, B:33:0x0078), top: B:165:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x019a A[Catch: JSONException -> 0x0313, TryCatch #0 {JSONException -> 0x0313, blocks: (B:6:0x0015, B:7:0x001d, B:9:0x0023, B:10:0x002e, B:36:0x0081, B:38:0x0085, B:40:0x008d, B:41:0x0095, B:43:0x009d, B:44:0x00a5, B:46:0x00ad, B:47:0x00b5, B:49:0x00bd, B:50:0x00c5, B:52:0x00cd, B:53:0x00d4, B:55:0x00dc, B:56:0x00e4, B:58:0x00ec, B:59:0x00f4, B:61:0x00fc, B:62:0x0104, B:64:0x010c, B:65:0x0114, B:67:0x011c, B:68:0x0124, B:70:0x012c, B:71:0x0133, B:73:0x013b, B:74:0x0143, B:76:0x014b, B:77:0x0153, B:79:0x015b, B:80:0x0163, B:82:0x016b, B:83:0x0173, B:85:0x017b, B:86:0x0183, B:88:0x018b, B:89:0x0192, B:91:0x019a, B:92:0x01a2, B:94:0x01aa, B:95:0x01b2, B:97:0x01ba, B:98:0x01c2, B:100:0x01ca, B:101:0x01d2, B:103:0x01da, B:104:0x01e2, B:106:0x01ea, B:107:0x01f1, B:109:0x01f9, B:110:0x0201, B:112:0x0209, B:113:0x0211, B:115:0x0219, B:116:0x0221, B:118:0x0229, B:119:0x0231, B:121:0x0239, B:122:0x0241, B:124:0x0249, B:125:0x0250, B:127:0x0258, B:128:0x0260, B:130:0x0268, B:131:0x0270, B:133:0x0278, B:134:0x0280, B:136:0x0288, B:137:0x0290, B:139:0x0298, B:140:0x02a0, B:142:0x02a8, B:143:0x02af, B:145:0x02b7, B:146:0x02bf, B:148:0x02c7, B:149:0x02cf, B:151:0x02d7, B:152:0x02df, B:154:0x02e7, B:155:0x02ef, B:157:0x02f7, B:158:0x02ff, B:160:0x0307, B:161:0x030e, B:12:0x0032, B:15:0x003c, B:18:0x0046, B:21:0x0050, B:24:0x005a, B:27:0x0064, B:30:0x006e, B:33:0x0078), top: B:165:0x0015 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01a2 A[Catch: JSONException -> 0x0313, TryCatch #0 {JSONException -> 0x0313, blocks: (B:6:0x0015, B:7:0x001d, B:9:0x0023, B:10:0x002e, B:36:0x0081, B:38:0x0085, B:40:0x008d, B:41:0x0095, B:43:0x009d, B:44:0x00a5, B:46:0x00ad, B:47:0x00b5, B:49:0x00bd, B:50:0x00c5, B:52:0x00cd, B:53:0x00d4, B:55:0x00dc, B:56:0x00e4, B:58:0x00ec, B:59:0x00f4, B:61:0x00fc, B:62:0x0104, B:64:0x010c, B:65:0x0114, B:67:0x011c, B:68:0x0124, B:70:0x012c, B:71:0x0133, B:73:0x013b, B:74:0x0143, B:76:0x014b, B:77:0x0153, B:79:0x015b, B:80:0x0163, B:82:0x016b, B:83:0x0173, B:85:0x017b, B:86:0x0183, B:88:0x018b, B:89:0x0192, B:91:0x019a, B:92:0x01a2, B:94:0x01aa, B:95:0x01b2, B:97:0x01ba, B:98:0x01c2, B:100:0x01ca, B:101:0x01d2, B:103:0x01da, B:104:0x01e2, B:106:0x01ea, B:107:0x01f1, B:109:0x01f9, B:110:0x0201, B:112:0x0209, B:113:0x0211, B:115:0x0219, B:116:0x0221, B:118:0x0229, B:119:0x0231, B:121:0x0239, B:122:0x0241, B:124:0x0249, B:125:0x0250, B:127:0x0258, B:128:0x0260, B:130:0x0268, B:131:0x0270, B:133:0x0278, B:134:0x0280, B:136:0x0288, B:137:0x0290, B:139:0x0298, B:140:0x02a0, B:142:0x02a8, B:143:0x02af, B:145:0x02b7, B:146:0x02bf, B:148:0x02c7, B:149:0x02cf, B:151:0x02d7, B:152:0x02df, B:154:0x02e7, B:155:0x02ef, B:157:0x02f7, B:158:0x02ff, B:160:0x0307, B:161:0x030e, B:12:0x0032, B:15:0x003c, B:18:0x0046, B:21:0x0050, B:24:0x005a, B:27:0x0064, B:30:0x006e, B:33:0x0078), top: B:165:0x0015 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amplitude.api.Identify createIdentify(org.json.JSONObject r9) {
        /*
            Method dump skipped, instructions count: 846
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.reactnative.AmplitudeReactNativeModule.createIdentify(org.json.JSONObject):com.amplitude.api.Identify");
    }
}
