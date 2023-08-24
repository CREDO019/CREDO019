package io.invertase.googlemobileads.common;

import android.content.SharedPreferences;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class ReactNativePreferences {
    private static final String PREFERENCES_FILE = "io.invertase.googlemobileads";
    private static ReactNativePreferences sharedInstance = new ReactNativePreferences();
    private SharedPreferences preferences;

    public static ReactNativePreferences getSharedInstance() {
        return sharedInstance;
    }

    public boolean contains(String str) {
        return getPreferences().contains(str);
    }

    public void setBooleanValue(String str, boolean z) {
        getPreferences().edit().putBoolean(str, z).apply();
    }

    public boolean getBooleanValue(String str, boolean z) {
        return getPreferences().getBoolean(str, z);
    }

    public void setLongValue(String str, long j) {
        getPreferences().edit().putLong(str, j).apply();
    }

    public long getLongValue(String str, long j) {
        return getPreferences().getLong(str, j);
    }

    public void setStringValue(String str, String str2) {
        getPreferences().edit().putString(str, str2).apply();
    }

    public String getStringValue(String str, String str2) {
        return getPreferences().getString(str, str2);
    }

    public WritableMap getAll() {
        WritableMap createMap = Arguments.createMap();
        for (Map.Entry<String, ?> entry : getPreferences().getAll().entrySet()) {
            SharedUtils.mapPutValue(entry.getKey(), entry.getValue(), createMap);
        }
        return createMap;
    }

    public void clearAll() {
        getPreferences().edit().clear().apply();
    }

    private SharedPreferences getPreferences() {
        if (this.preferences == null) {
            this.preferences = ReactNativeApp.getApplicationContext().getSharedPreferences("io.invertase.googlemobileads", 0);
        }
        return this.preferences;
    }
}
