package io.invertase.googlemobileads;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import io.invertase.googlemobileads.interfaces.NativeEvent;

/* loaded from: classes5.dex */
public class ReactNativeGoogleMobileAdsEvent implements NativeEvent {
    public static final String GOOGLE_MOBILE_ADS_EVENT_APP_EVENT = "app_event";
    public static final String GOOGLE_MOBILE_ADS_EVENT_APP_OPEN = "google_mobile_ads_app_open_event";
    public static final String GOOGLE_MOBILE_ADS_EVENT_CLICKED = "clicked";
    public static final String GOOGLE_MOBILE_ADS_EVENT_CLOSED = "closed";
    public static final String GOOGLE_MOBILE_ADS_EVENT_ERROR = "error";
    public static final String GOOGLE_MOBILE_ADS_EVENT_INTERSTITIAL = "google_mobile_ads_interstitial_event";
    public static final String GOOGLE_MOBILE_ADS_EVENT_LOADED = "loaded";
    public static final String GOOGLE_MOBILE_ADS_EVENT_OPENED = "opened";
    public static final String GOOGLE_MOBILE_ADS_EVENT_REWARDED = "google_mobile_ads_rewarded_event";
    public static final String GOOGLE_MOBILE_ADS_EVENT_REWARDED_EARNED_REWARD = "rewarded_earned_reward";
    public static final String GOOGLE_MOBILE_ADS_EVENT_REWARDED_INTERSTITIAL = "google_mobile_ads_rewarded_interstitial_event";
    public static final String GOOGLE_MOBILE_ADS_EVENT_REWARDED_LOADED = "rewarded_loaded";
    private static final String KEY_BODY = "body";
    private static final String KEY_EVENT_NAME = "eventName";
    private static final String KEY_GOOGLE_MOBILE_ADS_EVENT_UNIT_ID = "adUnitId";
    private static final String KEY_REQUEST_ID = "requestId";
    private String adUnitId;
    private WritableMap eventBody;
    private String eventName;
    private int requestId;

    public ReactNativeGoogleMobileAdsEvent(String str, int r2, String str2, WritableMap writableMap) {
        this.eventName = str;
        this.requestId = r2;
        this.adUnitId = str2;
        this.eventBody = writableMap;
    }

    @Override // io.invertase.googlemobileads.interfaces.NativeEvent
    public String getEventName() {
        return this.eventName;
    }

    @Override // io.invertase.googlemobileads.interfaces.NativeEvent
    public WritableMap getEventBody() {
        WritableMap createMap = Arguments.createMap();
        createMap.putMap("body", this.eventBody);
        createMap.putInt(KEY_REQUEST_ID, this.requestId);
        createMap.putString(KEY_GOOGLE_MOBILE_ADS_EVENT_UNIT_ID, this.adUnitId);
        createMap.putString(KEY_EVENT_NAME, this.eventName);
        return createMap;
    }
}
