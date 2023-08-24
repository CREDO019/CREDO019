package com.amplitude.unity.plugins;

import android.app.Application;
import android.content.Context;
import com.amplitude.api.Amplitude;
import com.amplitude.api.AmplitudeServerZone;
import com.amplitude.api.C1060Utils;
import com.amplitude.api.Identify;
import com.amplitude.api.Revenue;
import com.amplitude.api.TrackingOptions;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AmplitudePlugin {
    @Deprecated
    public static void endSession() {
    }

    @Deprecated
    public static void startSession() {
    }

    public static JSONObject ToJSONObject(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void init(String str, Context context, String str2) {
        Amplitude.getInstance(str).initialize(context, str2);
    }

    public static void init(String str, Context context, String str2, String str3) {
        Amplitude.getInstance(str).initialize(context, str2, str3);
    }

    public static void setTrackingOptions(String str, String str2) {
        JSONObject ToJSONObject = ToJSONObject(str2);
        TrackingOptions trackingOptions = new TrackingOptions();
        if (ToJSONObject.optBoolean("disableADID", false)) {
            trackingOptions.disableAdid();
        }
        if (ToJSONObject.optBoolean("disableAppSetId", false)) {
            trackingOptions.disableAppSetId();
        }
        if (ToJSONObject.optBoolean("disableCarrier", false)) {
            trackingOptions.disableCarrier();
        }
        if (ToJSONObject.optBoolean("disableCity", false)) {
            trackingOptions.disableCity();
        }
        if (ToJSONObject.optBoolean("disableCountry", false)) {
            trackingOptions.disableCountry();
        }
        if (ToJSONObject.optBoolean("disableDeviceBrand", false)) {
            trackingOptions.disableDeviceBrand();
        }
        if (ToJSONObject.optBoolean("disableDeviceManufacturer", false)) {
            trackingOptions.disableDeviceManufacturer();
        }
        if (ToJSONObject.optBoolean("disableDeviceModel", false)) {
            trackingOptions.disableDeviceModel();
        }
        if (ToJSONObject.optBoolean("disableDMA", false)) {
            trackingOptions.disableDma();
        }
        if (ToJSONObject.optBoolean("disableIPAddress", false)) {
            trackingOptions.disableIpAddress();
        }
        if (ToJSONObject.optBoolean("disableLanguage", false)) {
            trackingOptions.disableLanguage();
        }
        if (ToJSONObject.optBoolean("disableLatLng", false)) {
            trackingOptions.disableLatLng();
        }
        if (ToJSONObject.optBoolean("disableOSName", false)) {
            trackingOptions.disableOsName();
        }
        if (ToJSONObject.optBoolean("disableOSVersion", false)) {
            trackingOptions.disableOsVersion();
        }
        if (ToJSONObject.optBoolean("disableApiLevel", false)) {
            trackingOptions.disableApiLevel();
        }
        if (ToJSONObject.optBoolean("disablePlatform", false)) {
            trackingOptions.disablePlatform();
        }
        if (ToJSONObject.optBoolean("disableRegion", false)) {
            trackingOptions.disableRegion();
        }
        if (ToJSONObject.optBoolean("disableVersionName", false)) {
            trackingOptions.disableVersionName();
        }
        Amplitude.getInstance(str).setTrackingOptions(trackingOptions);
    }

    public static void enableForegroundTracking(String str, Application application) {
        Amplitude.getInstance(str).enableForegroundTracking(application);
    }

    public static void enableCoppaControl(String str) {
        Amplitude.getInstance(str).enableCoppaControl();
    }

    public static void disableCoppaControl(String str) {
        Amplitude.getInstance(str).disableCoppaControl();
    }

    public static void setLibraryName(String str, String str2) {
        Amplitude.getInstance(str).setLibraryName(str2);
    }

    public static void setLibraryVersion(String str, String str2) {
        Amplitude.getInstance(str).setLibraryVersion(str2);
    }

    public static void setServerUrl(String str, String str2) {
        Amplitude.getInstance(str).setServerUrl(str2);
    }

    public static void setServerZone(String str, String str2, boolean z) {
        Amplitude.getInstance(str).setServerZone(AmplitudeServerZone.getServerZone(str2), z);
    }

    public static void setUseDynamicConfig(String str, boolean z) {
        Amplitude.getInstance(str).setUseDynamicConfig(z);
    }

    public static void logEvent(String str, String str2) {
        Amplitude.getInstance(str).logEvent(str2);
    }

    public static void logEvent(String str, String str2, String str3) {
        Amplitude.getInstance(str).logEvent(str2, ToJSONObject(str3));
    }

    public static void logEvent(String str, String str2, String str3, boolean z) {
        Amplitude.getInstance(str).logEvent(str2, ToJSONObject(str3), z);
    }

    public static void uploadEvents(String str) {
        Amplitude.getInstance(str).uploadEvents();
    }

    public static void useAdvertisingIdForDeviceId(String str) {
        Amplitude.getInstance(str).useAdvertisingIdForDeviceId();
    }

    public static void useAppSetIdForDeviceId(String str) {
        Amplitude.getInstance(str).useAppSetIdForDeviceId();
    }

    public static void setOffline(String str, boolean z) {
        Amplitude.getInstance(str).setOffline(z);
    }

    public static void setUserId(String str, String str2) {
        Amplitude.getInstance(str).setUserId(str2);
    }

    public static void setOptOut(String str, boolean z) {
        Amplitude.getInstance(str).setOptOut(z);
    }

    public static void setMinTimeBetweenSessionsMillis(String str, long j) {
        Amplitude.getInstance(str).setMinTimeBetweenSessionsMillis(j);
    }

    public static void setEventUploadPeriodMillis(String str, int r1) {
        Amplitude.getInstance(str).setEventUploadPeriodMillis(r1);
    }

    public static void setUserProperties(String str, String str2) {
        Amplitude.getInstance(str).setUserProperties(ToJSONObject(str2));
    }

    public static void logRevenue(String str, double d) {
        Amplitude.getInstance(str).logRevenue(d);
    }

    public static void logRevenue(String str, String str2, int r2, double d) {
        Amplitude.getInstance(str).logRevenue(str2, r2, d);
    }

    public static void logRevenue(String str, String str2, int r9, double d, String str3, String str4) {
        Amplitude.getInstance(str).logRevenue(str2, r9, d, str3, str4);
    }

    public static void logRevenue(String str, String str2, int r3, double d, String str3, String str4, String str5, String str6) {
        Revenue price = new Revenue().setQuantity(r3).setPrice(d);
        if (!C1060Utils.isEmptyString(str2)) {
            price.setProductId(str2);
        }
        if (!C1060Utils.isEmptyString(str3) && !C1060Utils.isEmptyString(str4)) {
            price.setReceipt(str3, str4);
        }
        if (!C1060Utils.isEmptyString(str5)) {
            price.setRevenueType(str5);
        }
        if (!C1060Utils.isEmptyString(str6)) {
            price.setEventProperties(ToJSONObject(str6));
        }
        Amplitude.getInstance(str).logRevenueV2(price);
    }

    public static String getDeviceId(String str) {
        return Amplitude.getInstance(str).getDeviceId();
    }

    public static void setDeviceId(String str, String str2) {
        Amplitude.getInstance(str).setDeviceId(str2);
    }

    public static void regenerateDeviceId(String str) {
        Amplitude.getInstance(str).regenerateDeviceId();
    }

    public static void trackSessionEvents(String str, boolean z) {
        Amplitude.getInstance(str).trackSessionEvents(z);
    }

    public static long getSessionId(String str) {
        return Amplitude.getInstance(str).getSessionId();
    }

    public static void clearUserProperties(String str) {
        Amplitude.getInstance(str).clearUserProperties();
    }

    public static void unsetUserProperty(String str, String str2) {
        Amplitude.getInstance(str).identify(new Identify().unset(str2));
    }

    public static void setOnceUserProperty(String str, String str2, boolean z) {
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, z));
    }

    public static void setOnceUserProperty(String str, String str2, double d) {
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, d));
    }

    public static void setOnceUserProperty(String str, String str2, float f) {
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, f));
    }

    public static void setOnceUserProperty(String str, String str2, int r3) {
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, r3));
    }

    public static void setOnceUserProperty(String str, String str2, long j) {
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, j));
    }

    public static void setOnceUserProperty(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, str3));
    }

    public static void setOnceUserPropertyDict(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, ToJSONObject(str3)));
    }

    public static void setOnceUserPropertyList(String str, String str2, String str3) {
        JSONObject ToJSONObject = ToJSONObject(str3);
        if (ToJSONObject == null) {
            return;
        }
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, ToJSONObject.optJSONArray("list")));
    }

    public static void setOnceUserProperty(String str, String str2, boolean[] zArr) {
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, zArr));
    }

    public static void setOnceUserProperty(String str, String str2, double[] dArr) {
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, dArr));
    }

    public static void setOnceUserProperty(String str, String str2, float[] fArr) {
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, fArr));
    }

    public static void setOnceUserProperty(String str, String str2, int[] r3) {
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, r3));
    }

    public static void setOnceUserProperty(String str, String str2, long[] jArr) {
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, jArr));
    }

    public static void setOnceUserProperty(String str, String str2, String[] strArr) {
        Amplitude.getInstance(str).identify(new Identify().setOnce(str2, strArr));
    }

    public static void setUserProperty(String str, String str2, boolean z) {
        Amplitude.getInstance(str).identify(new Identify().set(str2, z));
    }

    public static void setUserProperty(String str, String str2, double d) {
        Amplitude.getInstance(str).identify(new Identify().set(str2, d));
    }

    public static void setUserProperty(String str, String str2, float f) {
        Amplitude.getInstance(str).identify(new Identify().set(str2, f));
    }

    public static void setUserProperty(String str, String str2, int r3) {
        Amplitude.getInstance(str).identify(new Identify().set(str2, r3));
    }

    public static void setUserProperty(String str, String str2, long j) {
        Amplitude.getInstance(str).identify(new Identify().set(str2, j));
    }

    public static void setUserProperty(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().set(str2, str3));
    }

    public static void setUserPropertyDict(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().set(str2, ToJSONObject(str3)));
    }

    public static void setUserPropertyList(String str, String str2, String str3) {
        JSONObject ToJSONObject = ToJSONObject(str3);
        if (ToJSONObject == null) {
            return;
        }
        Amplitude.getInstance(str).identify(new Identify().set(str2, ToJSONObject.optJSONArray("list")));
    }

    public static void setUserProperty(String str, String str2, boolean[] zArr) {
        Amplitude.getInstance(str).identify(new Identify().set(str2, zArr));
    }

    public static void setUserProperty(String str, String str2, double[] dArr) {
        Amplitude.getInstance(str).identify(new Identify().set(str2, dArr));
    }

    public static void setUserProperty(String str, String str2, float[] fArr) {
        Amplitude.getInstance(str).identify(new Identify().set(str2, fArr));
    }

    public static void setUserProperty(String str, String str2, int[] r3) {
        Amplitude.getInstance(str).identify(new Identify().set(str2, r3));
    }

    public static void setUserProperty(String str, String str2, long[] jArr) {
        Amplitude.getInstance(str).identify(new Identify().set(str2, jArr));
    }

    public static void setUserProperty(String str, String str2, String[] strArr) {
        Amplitude.getInstance(str).identify(new Identify().set(str2, strArr));
    }

    public static void addUserProperty(String str, String str2, double d) {
        Amplitude.getInstance(str).identify(new Identify().add(str2, d));
    }

    public static void addUserProperty(String str, String str2, float f) {
        Amplitude.getInstance(str).identify(new Identify().add(str2, f));
    }

    public static void addUserProperty(String str, String str2, int r3) {
        Amplitude.getInstance(str).identify(new Identify().add(str2, r3));
    }

    public static void addUserProperty(String str, String str2, long j) {
        Amplitude.getInstance(str).identify(new Identify().add(str2, j));
    }

    public static void addUserProperty(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().add(str2, str3));
    }

    public static void addUserPropertyDict(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().add(str2, ToJSONObject(str3)));
    }

    public static void appendUserProperty(String str, String str2, boolean z) {
        Amplitude.getInstance(str).identify(new Identify().append(str2, z));
    }

    public static void appendUserProperty(String str, String str2, double d) {
        Amplitude.getInstance(str).identify(new Identify().append(str2, d));
    }

    public static void appendUserProperty(String str, String str2, float f) {
        Amplitude.getInstance(str).identify(new Identify().append(str2, f));
    }

    public static void appendUserProperty(String str, String str2, int r3) {
        Amplitude.getInstance(str).identify(new Identify().append(str2, r3));
    }

    public static void appendUserProperty(String str, String str2, long j) {
        Amplitude.getInstance(str).identify(new Identify().append(str2, j));
    }

    public static void appendUserProperty(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().append(str2, str3));
    }

    public static void appendUserPropertyDict(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().append(str2, ToJSONObject(str3)));
    }

    public static void appendUserPropertyList(String str, String str2, String str3) {
        JSONObject ToJSONObject = ToJSONObject(str3);
        if (ToJSONObject == null) {
            return;
        }
        Amplitude.getInstance(str).identify(new Identify().append(str2, ToJSONObject.optJSONArray("list")));
    }

    public static void appendUserProperty(String str, String str2, boolean[] zArr) {
        Amplitude.getInstance(str).identify(new Identify().append(str2, zArr));
    }

    public static void appendUserProperty(String str, String str2, double[] dArr) {
        Amplitude.getInstance(str).identify(new Identify().append(str2, dArr));
    }

    public static void appendUserProperty(String str, String str2, float[] fArr) {
        Amplitude.getInstance(str).identify(new Identify().append(str2, fArr));
    }

    public static void appendUserProperty(String str, String str2, int[] r3) {
        Amplitude.getInstance(str).identify(new Identify().append(str2, r3));
    }

    public static void appendUserProperty(String str, String str2, long[] jArr) {
        Amplitude.getInstance(str).identify(new Identify().append(str2, jArr));
    }

    public static void appendUserProperty(String str, String str2, String[] strArr) {
        Amplitude.getInstance(str).identify(new Identify().append(str2, strArr));
    }

    public static void prependUserProperty(String str, String str2, boolean z) {
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, z));
    }

    public static void prependUserProperty(String str, String str2, double d) {
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, d));
    }

    public static void prependUserProperty(String str, String str2, float f) {
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, f));
    }

    public static void prependUserProperty(String str, String str2, int r3) {
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, r3));
    }

    public static void prependUserProperty(String str, String str2, long j) {
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, j));
    }

    public static void prependUserProperty(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, str3));
    }

    public static void prependUserPropertyDict(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, ToJSONObject(str3)));
    }

    public static void prependUserPropertyList(String str, String str2, String str3) {
        JSONObject ToJSONObject = ToJSONObject(str3);
        if (ToJSONObject == null) {
            return;
        }
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, ToJSONObject.optJSONArray("list")));
    }

    public static void prependUserProperty(String str, String str2, boolean[] zArr) {
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, zArr));
    }

    public static void prependUserProperty(String str, String str2, double[] dArr) {
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, dArr));
    }

    public static void prependUserProperty(String str, String str2, float[] fArr) {
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, fArr));
    }

    public static void prependUserProperty(String str, String str2, int[] r3) {
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, r3));
    }

    public static void prependUserProperty(String str, String str2, long[] jArr) {
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, jArr));
    }

    public static void prependUserProperty(String str, String str2, String[] strArr) {
        Amplitude.getInstance(str).identify(new Identify().prepend(str2, strArr));
    }

    public static void preInsertUserProperty(String str, String str2, boolean z) {
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, z));
    }

    public static void preInsertUserProperty(String str, String str2, double d) {
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, d));
    }

    public static void preInsertUserProperty(String str, String str2, float f) {
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, f));
    }

    public static void preInsertUserProperty(String str, String str2, int r3) {
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, r3));
    }

    public static void preInsertUserProperty(String str, String str2, long j) {
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, j));
    }

    public static void preInsertUserProperty(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, str3));
    }

    public static void preInsertUserPropertyDict(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, ToJSONObject(str3)));
    }

    public static void preInsertUserPropertyList(String str, String str2, String str3) {
        JSONObject ToJSONObject = ToJSONObject(str3);
        if (ToJSONObject == null) {
            return;
        }
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, ToJSONObject.optJSONArray("list")));
    }

    public static void preInsertUserProperty(String str, String str2, boolean[] zArr) {
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, zArr));
    }

    public static void preInsertUserProperty(String str, String str2, double[] dArr) {
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, dArr));
    }

    public static void preInsertUserProperty(String str, String str2, float[] fArr) {
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, fArr));
    }

    public static void preInsertUserProperty(String str, String str2, int[] r3) {
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, r3));
    }

    public static void preInsertUserProperty(String str, String str2, long[] jArr) {
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, jArr));
    }

    public static void preInsertUserProperty(String str, String str2, String[] strArr) {
        Amplitude.getInstance(str).identify(new Identify().preInsert(str2, strArr));
    }

    public static void postInsertUserProperty(String str, String str2, boolean z) {
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, z));
    }

    public static void postInsertUserProperty(String str, String str2, double d) {
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, d));
    }

    public static void postInsertUserProperty(String str, String str2, float f) {
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, f));
    }

    public static void postInsertUserProperty(String str, String str2, int r3) {
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, r3));
    }

    public static void postInsertUserProperty(String str, String str2, long j) {
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, j));
    }

    public static void postInsertUserProperty(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, str3));
    }

    public static void postInsertUserPropertyDict(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, ToJSONObject(str3)));
    }

    public static void postInsertUserPropertyList(String str, String str2, String str3) {
        JSONObject ToJSONObject = ToJSONObject(str3);
        if (ToJSONObject == null) {
            return;
        }
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, ToJSONObject.optJSONArray("list")));
    }

    public static void postInsertUserProperty(String str, String str2, boolean[] zArr) {
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, zArr));
    }

    public static void postInsertUserProperty(String str, String str2, double[] dArr) {
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, dArr));
    }

    public static void postInsertUserProperty(String str, String str2, float[] fArr) {
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, fArr));
    }

    public static void postInsertUserProperty(String str, String str2, int[] r3) {
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, r3));
    }

    public static void postInsertUserProperty(String str, String str2, long[] jArr) {
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, jArr));
    }

    public static void postInsertUserProperty(String str, String str2, String[] strArr) {
        Amplitude.getInstance(str).identify(new Identify().postInsert(str2, strArr));
    }

    public static void removeUserProperty(String str, String str2, boolean z) {
        Amplitude.getInstance(str).identify(new Identify().remove(str2, z));
    }

    public static void removeUserProperty(String str, String str2, double d) {
        Amplitude.getInstance(str).identify(new Identify().remove(str2, d));
    }

    public static void removeUserProperty(String str, String str2, float f) {
        Amplitude.getInstance(str).identify(new Identify().remove(str2, f));
    }

    public static void removeUserProperty(String str, String str2, int r3) {
        Amplitude.getInstance(str).identify(new Identify().remove(str2, r3));
    }

    public static void removeUserProperty(String str, String str2, long j) {
        Amplitude.getInstance(str).identify(new Identify().remove(str2, j));
    }

    public static void removeUserProperty(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().remove(str2, str3));
    }

    public static void removeUserPropertyDict(String str, String str2, String str3) {
        Amplitude.getInstance(str).identify(new Identify().remove(str2, ToJSONObject(str3)));
    }

    public static void removeUserPropertyList(String str, String str2, String str3) {
        JSONObject ToJSONObject = ToJSONObject(str3);
        if (ToJSONObject == null) {
            return;
        }
        Amplitude.getInstance(str).identify(new Identify().remove(str2, ToJSONObject.optJSONArray("list")));
    }

    public static void removeUserProperty(String str, String str2, boolean[] zArr) {
        Amplitude.getInstance(str).identify(new Identify().remove(str2, zArr));
    }

    public static void removeUserProperty(String str, String str2, double[] dArr) {
        Amplitude.getInstance(str).identify(new Identify().remove(str2, dArr));
    }

    public static void removeUserProperty(String str, String str2, float[] fArr) {
        Amplitude.getInstance(str).identify(new Identify().remove(str2, fArr));
    }

    public static void removeUserProperty(String str, String str2, int[] r3) {
        Amplitude.getInstance(str).identify(new Identify().remove(str2, r3));
    }

    public static void removeUserProperty(String str, String str2, long[] jArr) {
        Amplitude.getInstance(str).identify(new Identify().remove(str2, jArr));
    }

    public static void removeUserProperty(String str, String str2, String[] strArr) {
        Amplitude.getInstance(str).identify(new Identify().remove(str2, strArr));
    }
}
