package com.amplitude.analytics.connector.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(m184d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0001H\u0002\u001a\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0001H\u0002\u001a\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u0005H\u0000\u001a\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\b\u001a\u0014\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0005*\u00020\u0004H\u0000\u001a\u001a\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b*\u00020\u0007H\u0000\u001a$\u0010\f\u001a\u001c\u0012\u0004\u0012\u00020\u000b\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b0\b*\u00020\u0007¨\u0006\r"}, m183d2 = {"fromJSON", "", "toJSON", "toJSONArray", "Lorg/json/JSONArray;", "", "toJSONObject", "Lorg/json/JSONObject;", "", "toList", "toMap", "", "toUpdateUserPropertiesMap", "analytics-connector_release"}, m182k = 2, m181mv = {1, 5, 1}, m179xi = 48)
/* renamed from: com.amplitude.analytics.connector.util.JSONUtil */
/* loaded from: classes.dex */
public final class JSON {
    public static final Map<String, Map<String, Object>> toUpdateUserPropertiesMap(JSONObject jSONObject) {
        Map<String, Object> emptyMap;
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<String> keys = jSONObject.keys();
        Intrinsics.checkNotNullExpressionValue(keys, "this.keys()");
        while (keys.hasNext()) {
            String key = keys.next();
            Intrinsics.checkNotNullExpressionValue(key, "key");
            Object obj = jSONObject.get(key);
            if (obj instanceof JSONObject) {
                emptyMap = toMap((JSONObject) obj);
            } else if (obj instanceof JSONArray) {
                emptyMap = MapsKt.emptyMap();
            } else {
                emptyMap = Intrinsics.areEqual(obj, JSONObject.NULL) ? MapsKt.emptyMap() : MapsKt.emptyMap();
            }
            linkedHashMap.put(key, emptyMap);
        }
        return linkedHashMap;
    }

    public static final JSONObject toJSONObject(Map<?, ?> map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object key = entry.getKey();
            String str = key instanceof String ? (String) key : null;
            if (str != null) {
                jSONObject.put(str, toJSON(entry.getValue()));
            }
        }
        return jSONObject;
    }

    public static final Map<String, Object> toMap(JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<String> keys = jSONObject.keys();
        Intrinsics.checkNotNullExpressionValue(keys, "this.keys()");
        while (keys.hasNext()) {
            String key = keys.next();
            Intrinsics.checkNotNullExpressionValue(key, "key");
            linkedHashMap.put(key, fromJSON(jSONObject.get(key)));
        }
        return linkedHashMap;
    }

    public static final List<Object> toList(JSONArray jSONArray) {
        Intrinsics.checkNotNullParameter(jSONArray, "<this>");
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        if (length > 0) {
            int r2 = 0;
            while (true) {
                int r3 = r2 + 1;
                arrayList.add(fromJSON(jSONArray.get(r2)));
                if (r3 >= length) {
                    break;
                }
                r2 = r3;
            }
        }
        return arrayList;
    }

    public static final JSONArray toJSONArray(List<?> list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(toJSON(it.next()));
        }
        return jSONArray;
    }

    private static final Object fromJSON(Object obj) {
        if (obj instanceof JSONObject) {
            return toMap((JSONObject) obj);
        }
        if (obj instanceof JSONArray) {
            return toList((JSONArray) obj);
        }
        if (obj instanceof BigDecimal) {
            return Double.valueOf(((BigDecimal) obj).doubleValue());
        }
        if (Intrinsics.areEqual(obj, JSONObject.NULL)) {
            return null;
        }
        return obj;
    }

    private static final Object toJSON(Object obj) {
        return obj instanceof Map ? toJSONObject((Map) obj) : obj instanceof List ? toJSONArray((List) obj) : obj;
    }
}
