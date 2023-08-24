package io.invertase.googlemobileads;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.ads.AdInspectorError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnAdInspectorClosedListener;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import io.invertase.googlemobileads.common.ReactNativeModule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes5.dex */
public class ReactNativeGoogleMobileAdsModule extends ReactNativeModule {
    private static final String SERVICE = "RNGoogleMobileAdsModule";

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReactNativeGoogleMobileAdsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext, SERVICE);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private RequestConfiguration buildRequestConfiguration(ReadableMap readableMap) {
        char c;
        RequestConfiguration.Builder builder = new RequestConfiguration.Builder();
        if (readableMap.hasKey("testDeviceIdentifiers")) {
            ReadableArray array = readableMap.getArray("testDeviceIdentifiers");
            Objects.requireNonNull(array);
            ArrayList<Object> arrayList = array.toArrayList();
            ArrayList arrayList2 = new ArrayList();
            Iterator<Object> it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str.equals("EMULATOR")) {
                    arrayList2.add("B3EEABB8EE11C2BE770B684D95219ECB");
                } else {
                    arrayList2.add(str);
                }
            }
            builder.setTestDeviceIds(arrayList2);
        }
        if (readableMap.hasKey("maxAdContentRating")) {
            String string = readableMap.getString("maxAdContentRating");
            Objects.requireNonNull(string);
            string.hashCode();
            switch (string.hashCode()) {
                case 71:
                    if (string.equals(RequestConfiguration.MAX_AD_CONTENT_RATING_G)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 84:
                    if (string.equals("T")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 2452:
                    if (string.equals(RequestConfiguration.MAX_AD_CONTENT_RATING_MA)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 2551:
                    if (string.equals(RequestConfiguration.MAX_AD_CONTENT_RATING_PG)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    builder.setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_G);
                    break;
                case 1:
                    builder.setMaxAdContentRating("T");
                    break;
                case 2:
                    builder.setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_MA);
                    break;
                case 3:
                    builder.setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_PG);
                    break;
            }
        }
        if (readableMap.hasKey("tagForChildDirectedTreatment")) {
            if (readableMap.getBoolean("tagForChildDirectedTreatment")) {
                builder.setTagForChildDirectedTreatment(1);
            } else {
                builder.setTagForChildDirectedTreatment(0);
            }
        } else {
            builder.setTagForChildDirectedTreatment(-1);
        }
        if (readableMap.hasKey("tagForUnderAgeOfConsent")) {
            if (readableMap.getBoolean("tagForUnderAgeOfConsent")) {
                builder.setTagForUnderAgeOfConsent(1);
            } else {
                builder.setTagForUnderAgeOfConsent(0);
            }
        } else {
            builder.setTagForUnderAgeOfConsent(-1);
        }
        return builder.build();
    }

    @ReactMethod
    public void initialize(final Promise promise) {
        MobileAds.initialize(getApplicationContext(), new OnInitializationCompleteListener() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsModule.1
            @Override // com.google.android.gms.ads.initialization.OnInitializationCompleteListener
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                WritableArray createArray = Arguments.createArray();
                for (Map.Entry<String, AdapterStatus> entry : initializationStatus.getAdapterStatusMap().entrySet()) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("name", entry.getKey());
                    createMap.putInt("state", entry.getValue().getInitializationState().ordinal());
                    createMap.putString("description", entry.getValue().getDescription());
                    createArray.pushMap(createMap);
                }
                promise.resolve(createArray);
            }
        });
    }

    @ReactMethod
    public void setRequestConfiguration(ReadableMap readableMap, Promise promise) {
        MobileAds.setRequestConfiguration(buildRequestConfiguration(readableMap));
        promise.resolve(null);
    }

    @ReactMethod
    public void openAdInspector(final Promise promise) {
        if (getCurrentActivity() == null) {
            rejectPromiseWithCodeAndMessage(promise, "null-activity", "Ad Inspector attempted to open but the current Activity was null.");
        } else {
            getCurrentActivity().runOnUiThread(new Runnable() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsModule$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ReactNativeGoogleMobileAdsModule.this.m200x3297af0f(promise);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$openAdInspector$0$io-invertase-googlemobileads-ReactNativeGoogleMobileAdsModule */
    public /* synthetic */ void m200x3297af0f(final Promise promise) {
        MobileAds.openAdInspector(getApplicationContext(), new OnAdInspectorClosedListener() { // from class: io.invertase.googlemobileads.ReactNativeGoogleMobileAdsModule.2
            @Override // com.google.android.gms.ads.OnAdInspectorClosedListener
            public void onAdInspectorClosed(AdInspectorError adInspectorError) {
                if (adInspectorError != null) {
                    int code = adInspectorError.getCode();
                    ReactNativeModule.rejectPromiseWithCodeAndMessage(promise, code != 0 ? code != 1 ? code != 2 ? code != 3 ? "" : "ALREADY_OPEN" : "NOT_IN_TEST_MODE" : "FAILED_TO_LOAD" : "INTERNAL_ERROR", adInspectorError.getMessage());
                    return;
                }
                promise.resolve(null);
            }
        });
    }
}
