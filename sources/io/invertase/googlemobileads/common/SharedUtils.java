package io.invertase.googlemobileads.common;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class SharedUtils {
    private static final String EXPO_CORE_PACKAGE = "expo.core";
    private static final String EXPO_REGISTRY_CLASS = "ModuleRegistry";
    private static final String FLUTTER_CORE_PACKAGE = "io.flutter.plugin.common";
    private static final String FLUTTER_REGISTRY_CLASS = "PluginRegistry";
    private static final String REACT_NATIVE_CORE_PACKAGE = "com.facebook.react.bridge";
    private static final String REACT_NATIVE_REGISTRY_CLASS = "NativeModuleRegistry";
    private static final String RN_DEVSUPPORT_CLASS = "DevSupportManagerImpl";
    private static final String RN_DEVSUPPORT_PACKAGE = "com.facebook.react.devsupport";
    private static final String TAG = "Utils";

    public static int[] rectToIntArray(@Nullable Rect rect) {
        return (rect == null || rect.isEmpty()) ? new int[0] : new int[]{rect.left, rect.top, rect.right, rect.bottom};
    }

    public static int[] pointToIntArray(@Nullable Point point) {
        return point == null ? new int[0] : new int[]{point.x, point.y};
    }

    public static List<int[]> pointsToIntsList(@Nullable Point[] pointArr) {
        if (pointArr == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(pointArr.length);
        for (Point point : pointArr) {
            arrayList.add(pointToIntArray(point));
        }
        return arrayList;
    }

    public static Uri getUri(String str) {
        Uri parse = Uri.parse(str);
        return (parse.getScheme() == null || parse.getScheme().isEmpty()) ? Uri.fromFile(new File(str)) : parse;
    }

    public static String timestampToUTC(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat.format(Long.valueOf(j * 1000));
    }

    public static void sendEvent(ReactContext reactContext, String str, Object obj) {
        if (reactContext != null) {
            ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, obj);
        } else {
            Log.d(TAG, "Missing context - cannot send event!");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isAppInForeground(android.content.Context r8) {
        /*
            java.lang.String r0 = "activity"
            java.lang.Object r0 = r8.getSystemService(r0)
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            r1 = 0
            if (r0 != 0) goto Lc
            return r1
        Lc:
            java.util.List r2 = r0.getRunningAppProcesses()
            if (r2 != 0) goto L13
            return r1
        L13:
            io.invertase.googlemobileads.common.ReactNativeJSON r3 = io.invertase.googlemobileads.common.ReactNativeJSON.getSharedInstance()
            java.lang.String r4 = "android_background_activity_names"
            boolean r5 = r3.contains(r4)
            r6 = 1
            if (r5 == 0) goto L8e
            java.util.ArrayList r3 = r3.getArrayValue(r4)
            int r4 = r3.size()
            if (r4 == 0) goto L8e
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 21
            java.lang.String r7 = ""
            if (r4 < r5) goto L69
            java.util.List r0 = r0.getAppTasks()
            int r4 = r0.size()
            if (r4 <= 0) goto L80
            java.lang.Object r0 = r0.get(r1)
            android.app.ActivityManager$AppTask r0 = (android.app.ActivityManager.AppTask) r0
            android.app.ActivityManager$RecentTaskInfo r0 = r0.getTaskInfo()
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 23
            if (r4 < r5) goto L53
            android.content.ComponentName r0 = r0.baseActivity
            java.lang.String r0 = r0.getShortClassName()
            goto L81
        L53:
            android.content.ComponentName r4 = r0.origActivity
            if (r4 == 0) goto L5e
            android.content.ComponentName r0 = r0.origActivity
            java.lang.String r0 = r0.getShortClassName()
            goto L81
        L5e:
            android.content.Intent r0 = r0.baseIntent
            android.content.ComponentName r0 = r0.getComponent()
            java.lang.String r0 = r0.getShortClassName()
            goto L81
        L69:
            java.util.List r0 = r0.getRunningTasks(r6)
            int r4 = r0.size()
            if (r4 <= 0) goto L80
            java.lang.Object r0 = r0.get(r1)
            android.app.ActivityManager$RunningTaskInfo r0 = (android.app.ActivityManager.RunningTaskInfo) r0
            android.content.ComponentName r0 = r0.topActivity
            java.lang.String r0 = r0.getShortClassName()
            goto L81
        L80:
            r0 = r7
        L81:
            boolean r4 = r7.equals(r0)
            if (r4 != 0) goto L8e
            boolean r0 = r3.contains(r0)
            if (r0 == 0) goto L8e
            return r1
        L8e:
            java.lang.String r0 = r8.getPackageName()
            java.util.Iterator r2 = r2.iterator()
        L96:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto Lbd
            java.lang.Object r3 = r2.next()
            android.app.ActivityManager$RunningAppProcessInfo r3 = (android.app.ActivityManager.RunningAppProcessInfo) r3
            int r4 = r3.importance
            r5 = 100
            if (r4 != r5) goto L96
            java.lang.String r3 = r3.processName
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L96
            com.facebook.react.bridge.ReactContext r8 = (com.facebook.react.bridge.ReactContext) r8     // Catch: java.lang.ClassCastException -> Lbc
            com.facebook.react.common.LifecycleState r8 = r8.getLifecycleState()
            com.facebook.react.common.LifecycleState r0 = com.facebook.react.common.LifecycleState.RESUMED
            if (r8 != r0) goto Lbb
            r1 = 1
        Lbb:
            return r1
        Lbc:
            return r6
        Lbd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.invertase.googlemobileads.common.SharedUtils.isAppInForeground(android.content.Context):boolean");
    }

    public static int getResId(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, "string", context.getPackageName());
        if (identifier == 0) {
            Log.e(TAG, "resource " + str + " could not be found");
        }
        return identifier;
    }

    public static Boolean reactNativeHasDevSupport() {
        return hasPackageClass(RN_DEVSUPPORT_PACKAGE, RN_DEVSUPPORT_CLASS);
    }

    public static Boolean isExpo() {
        return hasPackageClass("expo.core", EXPO_REGISTRY_CLASS);
    }

    public static Boolean isFlutter() {
        return hasPackageClass(FLUTTER_CORE_PACKAGE, FLUTTER_REGISTRY_CLASS);
    }

    public static Boolean isReactNative() {
        return Boolean.valueOf(!isExpo().booleanValue() && hasPackageClass(REACT_NATIVE_CORE_PACKAGE, REACT_NATIVE_REGISTRY_CLASS).booleanValue());
    }

    public static Boolean hasPackageClass(String str, String str2) {
        try {
            Class.forName(str + "." + str2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static WritableMap jsonObjectToWritableMap(JSONObject jSONObject) throws JSONException {
        Iterator<String> keys = jSONObject.keys();
        WritableMap createMap = Arguments.createMap();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if ((obj instanceof Float) || (obj instanceof Double)) {
                createMap.putDouble(next, jSONObject.getDouble(next));
            } else if (obj instanceof Number) {
                createMap.putInt(next, jSONObject.getInt(next));
            } else if (obj instanceof String) {
                createMap.putString(next, jSONObject.getString(next));
            } else if (obj instanceof JSONObject) {
                createMap.putMap(next, jsonObjectToWritableMap(jSONObject.getJSONObject(next)));
            } else if (obj instanceof JSONArray) {
                createMap.putArray(next, jsonArrayToWritableArray(jSONObject.getJSONArray(next)));
            } else if (obj == JSONObject.NULL) {
                createMap.putNull(next);
            }
        }
        return createMap;
    }

    public static WritableArray jsonArrayToWritableArray(JSONArray jSONArray) throws JSONException {
        WritableArray createArray = Arguments.createArray();
        for (int r1 = 0; r1 < jSONArray.length(); r1++) {
            Object obj = jSONArray.get(r1);
            if ((obj instanceof Float) || (obj instanceof Double)) {
                createArray.pushDouble(jSONArray.getDouble(r1));
            } else if (obj instanceof Number) {
                createArray.pushInt(jSONArray.getInt(r1));
            } else if (obj instanceof String) {
                createArray.pushString(jSONArray.getString(r1));
            } else if (obj instanceof JSONObject) {
                createArray.pushMap(jsonObjectToWritableMap(jSONArray.getJSONObject(r1)));
            } else if (obj instanceof JSONArray) {
                createArray.pushArray(jsonArrayToWritableArray(jSONArray.getJSONArray(r1)));
            } else if (obj == JSONObject.NULL) {
                createArray.pushNull();
            }
        }
        return createArray;
    }

    public static WritableMap mapToWritableMap(Map<String, Object> map) {
        WritableMap createMap = Arguments.createMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            mapPutValue(entry.getKey(), entry.getValue(), createMap);
        }
        return createMap;
    }

    private static WritableArray listToWritableArray(List<Object> list) {
        WritableArray createArray = Arguments.createArray();
        for (Object obj : list) {
            arrayPushValue(obj, createArray);
        }
        return createArray;
    }

    public static void arrayPushValue(@Nullable Object obj, WritableArray writableArray) {
        if (obj == null || obj == JSONObject.NULL) {
            writableArray.pushNull();
            return;
        }
        String name = obj.getClass().getName();
        name.hashCode();
        char c = 65535;
        switch (name.hashCode()) {
            case -2056817302:
                if (name.equals("java.lang.Integer")) {
                    c = 0;
                    break;
                }
                break;
            case -527879800:
                if (name.equals("java.lang.Float")) {
                    c = 1;
                    break;
                }
                break;
            case 146015330:
                if (name.equals("org.json.JSONArray$1")) {
                    c = 2;
                    break;
                }
                break;
            case 344809556:
                if (name.equals("java.lang.Boolean")) {
                    c = 3;
                    break;
                }
                break;
            case 398795216:
                if (name.equals("java.lang.Long")) {
                    c = 4;
                    break;
                }
                break;
            case 761287205:
                if (name.equals("java.lang.Double")) {
                    c = 5;
                    break;
                }
                break;
            case 1195259493:
                if (name.equals("java.lang.String")) {
                    c = 6;
                    break;
                }
                break;
            case 1614941136:
                if (name.equals("org.json.JSONObject$1")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                writableArray.pushInt(((Integer) obj).intValue());
                return;
            case 1:
                writableArray.pushDouble(((Float) obj).floatValue());
                return;
            case 2:
                try {
                    writableArray.pushArray(jsonArrayToWritableArray((JSONArray) obj));
                    return;
                } catch (JSONException unused) {
                    writableArray.pushNull();
                    return;
                }
            case 3:
                writableArray.pushBoolean(((Boolean) obj).booleanValue());
                return;
            case 4:
                writableArray.pushDouble(((Long) obj).longValue());
                return;
            case 5:
                writableArray.pushDouble(((Double) obj).doubleValue());
                return;
            case 6:
                writableArray.pushString((String) obj);
                return;
            case 7:
                try {
                    writableArray.pushMap(jsonObjectToWritableMap((JSONObject) obj));
                    return;
                } catch (JSONException unused2) {
                    writableArray.pushNull();
                    return;
                }
            default:
                if (List.class.isAssignableFrom(obj.getClass())) {
                    writableArray.pushArray(listToWritableArray((List) obj));
                    return;
                } else if (Map.class.isAssignableFrom(obj.getClass())) {
                    writableArray.pushMap(mapToWritableMap((Map) obj));
                    return;
                } else {
                    Log.d(TAG, "utils:arrayPushValue:unknownType:" + name);
                    writableArray.pushNull();
                    return;
                }
        }
    }

    public static void mapPutValue(String str, @Nullable Object obj, WritableMap writableMap) {
        if (obj == null || obj == JSONObject.NULL) {
            writableMap.putNull(str);
            return;
        }
        String name = obj.getClass().getName();
        name.hashCode();
        char c = 65535;
        switch (name.hashCode()) {
            case -2056817302:
                if (name.equals("java.lang.Integer")) {
                    c = 0;
                    break;
                }
                break;
            case -527879800:
                if (name.equals("java.lang.Float")) {
                    c = 1;
                    break;
                }
                break;
            case 146015330:
                if (name.equals("org.json.JSONArray$1")) {
                    c = 2;
                    break;
                }
                break;
            case 344809556:
                if (name.equals("java.lang.Boolean")) {
                    c = 3;
                    break;
                }
                break;
            case 398795216:
                if (name.equals("java.lang.Long")) {
                    c = 4;
                    break;
                }
                break;
            case 761287205:
                if (name.equals("java.lang.Double")) {
                    c = 5;
                    break;
                }
                break;
            case 1195259493:
                if (name.equals("java.lang.String")) {
                    c = 6;
                    break;
                }
                break;
            case 1614941136:
                if (name.equals("org.json.JSONObject$1")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                writableMap.putInt(str, ((Integer) obj).intValue());
                return;
            case 1:
                writableMap.putDouble(str, ((Float) obj).floatValue());
                return;
            case 2:
                try {
                    writableMap.putArray(str, jsonArrayToWritableArray((JSONArray) obj));
                    return;
                } catch (JSONException unused) {
                    writableMap.putNull(str);
                    return;
                }
            case 3:
                writableMap.putBoolean(str, ((Boolean) obj).booleanValue());
                return;
            case 4:
                writableMap.putDouble(str, ((Long) obj).longValue());
                return;
            case 5:
                writableMap.putDouble(str, ((Double) obj).doubleValue());
                return;
            case 6:
                writableMap.putString(str, (String) obj);
                return;
            case 7:
                try {
                    writableMap.putMap(str, jsonObjectToWritableMap((JSONObject) obj));
                    return;
                } catch (JSONException unused2) {
                    writableMap.putNull(str);
                    return;
                }
            default:
                if (List.class.isAssignableFrom(obj.getClass())) {
                    writableMap.putArray(str, listToWritableArray((List) obj));
                    return;
                } else if (Map.class.isAssignableFrom(obj.getClass())) {
                    writableMap.putMap(str, mapToWritableMap((Map) obj));
                    return;
                } else {
                    Log.d(TAG, "utils:mapPutValue:unknownType:" + name);
                    writableMap.putNull(str);
                    return;
                }
        }
    }

    public static WritableMap readableMapToWritableMap(ReadableMap readableMap) {
        WritableMap createMap = Arguments.createMap();
        createMap.merge(readableMap);
        return createMap;
    }
}
