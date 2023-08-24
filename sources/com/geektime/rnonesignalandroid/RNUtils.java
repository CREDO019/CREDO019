package com.geektime.rnonesignalandroid;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class RNUtils {
    @Nullable
    public static JSONObject readableMapToJson(ReadableMap readableMap) {
        JSONObject jSONObject = new JSONObject();
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        if (keySetIterator.hasNextKey()) {
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                switch (C18121.$SwitchMap$com$facebook$react$bridge$ReadableType[readableMap.getType(nextKey).ordinal()]) {
                    case 1:
                        jSONObject.put(nextKey, (Object) null);
                        continue;
                    case 2:
                        jSONObject.put(nextKey, readableMap.getBoolean(nextKey));
                        continue;
                    case 3:
                        jSONObject.put(nextKey, readableMap.getInt(nextKey));
                        continue;
                    case 4:
                        jSONObject.put(nextKey, readableMap.getString(nextKey));
                        continue;
                    case 5:
                        jSONObject.put(nextKey, readableMapToJson(readableMap.getMap(nextKey)));
                        continue;
                    case 6:
                        jSONObject.put(nextKey, readableMap.getArray(nextKey));
                        continue;
                    default:
                        continue;
                }
            }
            return jSONObject;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.geektime.rnonesignalandroid.RNUtils$1 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C18121 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType;

        static {
            int[] r0 = new int[ReadableType.values().length];
            $SwitchMap$com$facebook$react$bridge$ReadableType = r0;
            try {
                r0[ReadableType.Null.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Map.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Array.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    @Nullable
    public static WritableMap jsonToWritableMap(JSONObject jSONObject) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (jSONObject == null) {
            return null;
        }
        Iterator<String> keys = jSONObject.keys();
        if (keys.hasNext()) {
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    Object obj = jSONObject.get(next);
                    if (obj == null) {
                        writableNativeMap.putNull(next);
                    } else if (obj instanceof Boolean) {
                        writableNativeMap.putBoolean(next, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof Integer) {
                        writableNativeMap.putInt(next, ((Integer) obj).intValue());
                    } else {
                        if (!(obj instanceof Double) && !(obj instanceof Long) && !(obj instanceof Float)) {
                            if (obj instanceof String) {
                                writableNativeMap.putString(next, obj.toString());
                            } else if (obj instanceof JSONObject) {
                                writableNativeMap.putMap(next, jsonToWritableMap((JSONObject) obj));
                            } else if (obj instanceof JSONArray) {
                                writableNativeMap.putArray(next, jsonArrayToWritableArray((JSONArray) obj));
                            } else if (obj.getClass().isEnum()) {
                                writableNativeMap.putString(next, obj.toString());
                            }
                        }
                        writableNativeMap.putDouble(next, Double.parseDouble(String.valueOf(obj)));
                    }
                } catch (JSONException unused) {
                }
            }
            return writableNativeMap;
        }
        return null;
    }

    @Nullable
    public static WritableArray jsonArrayToWritableArray(JSONArray jSONArray) {
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int r1 = 0; r1 < jSONArray.length(); r1++) {
                try {
                    Object obj = jSONArray.get(r1);
                    if (obj == null) {
                        writableNativeArray.pushNull();
                    } else if (obj instanceof Boolean) {
                        writableNativeArray.pushBoolean(((Boolean) obj).booleanValue());
                    } else if (obj instanceof Integer) {
                        writableNativeArray.pushInt(((Integer) obj).intValue());
                    } else {
                        if (!(obj instanceof Double) && !(obj instanceof Long) && !(obj instanceof Float)) {
                            if (obj instanceof String) {
                                writableNativeArray.pushString(obj.toString());
                            } else if (obj instanceof JSONObject) {
                                writableNativeArray.pushMap(jsonToWritableMap((JSONObject) obj));
                            } else if (obj instanceof JSONArray) {
                                writableNativeArray.pushArray(jsonArrayToWritableArray((JSONArray) obj));
                            } else if (obj.getClass().isEnum()) {
                                writableNativeArray.pushString(obj.toString());
                            }
                        }
                        writableNativeArray.pushDouble(Double.parseDouble(String.valueOf(obj)));
                    }
                } catch (JSONException unused) {
                }
            }
            return writableNativeArray;
        }
        return null;
    }

    public static Collection<String> convertReableArrayIntoStringCollection(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        Iterator<Object> it = readableArray.toArrayList().iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof String) {
                arrayList.add((String) next);
            }
        }
        return arrayList;
    }
}
