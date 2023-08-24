package com.amplitude.api;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amplitude.api.Utils */
/* loaded from: classes.dex */
public class C1060Utils {
    private static final String TAG = "com.amplitude.api.Utils";
    private static AmplitudeLog logger = AmplitudeLog.getLogger();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject cloneJSONObject(JSONObject jSONObject) {
        JSONArray jSONArray;
        if (jSONObject == null) {
            return null;
        }
        if (jSONObject.length() == 0) {
            return new JSONObject();
        }
        try {
            jSONArray = jSONObject.names();
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.m1368e(TAG, e.toString());
            jSONArray = null;
        }
        int length = jSONArray != null ? jSONArray.length() : 0;
        String[] strArr = new String[length];
        for (int r2 = 0; r2 < length; r2++) {
            strArr[r2] = jSONArray.optString(r2);
        }
        try {
            return new JSONObject(jSONObject, strArr);
        } catch (JSONException e2) {
            logger.m1368e(TAG, e2.toString());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean compareJSONObjects(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == jSONObject2) {
            return true;
        }
        if ((jSONObject == null || jSONObject2 != null) && (jSONObject != null || jSONObject2 == null)) {
            try {
                if (jSONObject.length() != jSONObject2.length()) {
                    return false;
                }
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!jSONObject2.has(next)) {
                        return false;
                    }
                    Object obj = jSONObject.get(next);
                    Object obj2 = jSONObject2.get(next);
                    if (!obj.getClass().equals(obj2.getClass())) {
                        return false;
                    }
                    if (obj.getClass() == JSONObject.class) {
                        if (!compareJSONObjects((JSONObject) obj, (JSONObject) obj2)) {
                            return false;
                        }
                    } else if (!obj.equals(obj2)) {
                        return false;
                    }
                }
                return true;
            } catch (JSONException unused) {
                return false;
            }
        }
        return false;
    }

    public static boolean isEmptyString(String str) {
        return str == null || str.length() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String normalizeInstanceName(String str) {
        if (isEmptyString(str)) {
            str = Constants.DEFAULT_INSTANCE;
        }
        return str.toLowerCase();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean checkLocationPermissionAllowed(Context context) {
        return checkPermissionAllowed(context, "android.permission.ACCESS_COARSE_LOCATION") || checkPermissionAllowed(context, "android.permission.ACCESS_FINE_LOCATION");
    }

    static boolean checkPermissionAllowed(Context context, String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                return Integer.parseInt(Activity.class.getMethod("checkSelfPermission", String.class).invoke(context, str).toString()) == 0;
            } catch (Exception unused) {
                return false;
            }
        }
        return true;
    }
}
