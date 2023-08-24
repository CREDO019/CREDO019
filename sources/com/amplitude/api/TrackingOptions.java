package com.amplitude.api;

import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class TrackingOptions {
    private static final String TAG = "com.amplitude.api.TrackingOptions";
    Set<String> disabledFields = new HashSet();
    private static String[] SERVER_SIDE_PROPERTIES = {Constants.AMP_TRACKING_OPTION_CITY, Constants.AMP_TRACKING_OPTION_COUNTRY, Constants.AMP_TRACKING_OPTION_DMA, Constants.AMP_TRACKING_OPTION_IP_ADDRESS, Constants.AMP_TRACKING_OPTION_LAT_LNG, "region"};
    private static String[] COPPA_CONTROL_PROPERTIES = {Constants.AMP_TRACKING_OPTION_ADID, Constants.AMP_TRACKING_OPTION_CITY, Constants.AMP_TRACKING_OPTION_IP_ADDRESS, Constants.AMP_TRACKING_OPTION_LAT_LNG};

    public TrackingOptions disableAdid() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_ADID);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackAdid() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_ADID);
    }

    public TrackingOptions disableAppSetId() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_APP_SET_ID);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackAppSetId() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_APP_SET_ID);
    }

    public TrackingOptions disableCarrier() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_CARRIER);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackCarrier() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_CARRIER);
    }

    public TrackingOptions disableCity() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_CITY);
        return this;
    }

    boolean shouldTrackCity() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_CITY);
    }

    public TrackingOptions disableCountry() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_COUNTRY);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackCountry() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_COUNTRY);
    }

    public TrackingOptions disableDeviceBrand() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_DEVICE_BRAND);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackDeviceBrand() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_DEVICE_BRAND);
    }

    public TrackingOptions disableDeviceManufacturer() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_DEVICE_MANUFACTURER);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackDeviceManufacturer() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_DEVICE_MANUFACTURER);
    }

    public TrackingOptions disableDeviceModel() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_DEVICE_MODEL);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackDeviceModel() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_DEVICE_MODEL);
    }

    public TrackingOptions disableDma() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_DMA);
        return this;
    }

    boolean shouldTrackDma() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_DMA);
    }

    public TrackingOptions disableIpAddress() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_IP_ADDRESS);
        return this;
    }

    boolean shouldTrackIpAddress() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_IP_ADDRESS);
    }

    public TrackingOptions disableLanguage() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_LANGUAGE);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackLanguage() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_LANGUAGE);
    }

    public TrackingOptions disableLatLng() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_LAT_LNG);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackLatLng() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_LAT_LNG);
    }

    public TrackingOptions disableOsName() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_OS_NAME);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackOsName() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_OS_NAME);
    }

    public TrackingOptions disableOsVersion() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_OS_VERSION);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackOsVersion() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_OS_VERSION);
    }

    public TrackingOptions disableApiLevel() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_API_LEVEL);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackApiLevel() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_API_LEVEL);
    }

    public TrackingOptions disablePlatform() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_PLATFORM);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackPlatform() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_PLATFORM);
    }

    public TrackingOptions disableRegion() {
        disableTrackingField("region");
        return this;
    }

    boolean shouldTrackRegion() {
        return shouldTrackField("region");
    }

    public TrackingOptions disableVersionName() {
        disableTrackingField(Constants.AMP_TRACKING_OPTION_VERSION_NAME);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldTrackVersionName() {
        return shouldTrackField(Constants.AMP_TRACKING_OPTION_VERSION_NAME);
    }

    private void disableTrackingField(String str) {
        this.disabledFields.add(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject getApiPropertiesTrackingOptions() {
        String[] strArr;
        JSONObject jSONObject = new JSONObject();
        if (this.disabledFields.isEmpty()) {
            return jSONObject;
        }
        for (String str : SERVER_SIDE_PROPERTIES) {
            if (this.disabledFields.contains(str)) {
                try {
                    jSONObject.put(str, false);
                } catch (JSONException e) {
                    AmplitudeLog.getLogger().m1368e(TAG, e.toString());
                }
            }
        }
        return jSONObject;
    }

    private boolean shouldTrackField(String str) {
        return !this.disabledFields.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrackingOptions mergeIn(TrackingOptions trackingOptions) {
        for (String str : trackingOptions.disabledFields) {
            disableTrackingField(str);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TrackingOptions copyOf(TrackingOptions trackingOptions) {
        TrackingOptions trackingOptions2 = new TrackingOptions();
        for (String str : trackingOptions.disabledFields) {
            trackingOptions2.disableTrackingField(str);
        }
        return trackingOptions2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static TrackingOptions forCoppaControl() {
        TrackingOptions trackingOptions = new TrackingOptions();
        for (String str : COPPA_CONTROL_PROPERTIES) {
            trackingOptions.disableTrackingField(str);
        }
        return trackingOptions;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return ((TrackingOptions) obj).disabledFields.equals(this.disabledFields);
        }
        return false;
    }
}
