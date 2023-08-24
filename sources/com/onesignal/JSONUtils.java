package com.onesignal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
class JSONUtils {
    JSONUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject generateJsonDiff(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, Set<String> set) {
        if (jSONObject == null) {
            return null;
        }
        if (jSONObject2 == null) {
            return jSONObject3;
        }
        Iterator<String> keys = jSONObject2.keys();
        JSONObject jSONObject4 = jSONObject3 != null ? jSONObject3 : new JSONObject();
        while (keys.hasNext()) {
            try {
                String next = keys.next();
                Object obj = jSONObject2.get(next);
                if (jSONObject.has(next)) {
                    if (obj instanceof JSONObject) {
                        String jSONObject5 = generateJsonDiff(jSONObject.getJSONObject(next), (JSONObject) obj, (jSONObject3 == null || !jSONObject3.has(next)) ? null : jSONObject3.getJSONObject(next), set).toString();
                        if (!jSONObject5.equals("{}")) {
                            jSONObject4.put(next, new JSONObject(jSONObject5));
                        }
                    } else if (obj instanceof JSONArray) {
                        handleJsonArray(next, (JSONArray) obj, jSONObject.getJSONArray(next), jSONObject4);
                    } else if (set != null && set.contains(next)) {
                        jSONObject4.put(next, obj);
                    } else {
                        Object obj2 = jSONObject.get(next);
                        if (!obj.equals(obj2)) {
                            if ((obj2 instanceof Number) && (obj instanceof Number)) {
                                if (((Number) obj2).doubleValue() != ((Number) obj).doubleValue()) {
                                    jSONObject4.put(next, obj);
                                }
                            } else {
                                jSONObject4.put(next, obj);
                            }
                        }
                    }
                } else if (obj instanceof JSONObject) {
                    jSONObject4.put(next, new JSONObject(obj.toString()));
                } else if (obj instanceof JSONArray) {
                    handleJsonArray(next, (JSONArray) obj, null, jSONObject4);
                } else {
                    jSONObject4.put(next, obj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject4;
    }

    private static void handleJsonArray(String str, JSONArray jSONArray, JSONArray jSONArray2, JSONObject jSONObject) throws JSONException {
        if (str.endsWith("_a") || str.endsWith("_d")) {
            jSONObject.put(str, jSONArray);
            return;
        }
        String stringNE = toStringNE(jSONArray);
        JSONArray jSONArray3 = new JSONArray();
        JSONArray jSONArray4 = new JSONArray();
        String stringNE2 = jSONArray2 == null ? null : toStringNE(jSONArray2);
        for (int r7 = 0; r7 < jSONArray.length(); r7++) {
            String str2 = (String) jSONArray.get(r7);
            if (jSONArray2 == null || !stringNE2.contains(str2)) {
                jSONArray3.put(str2);
            }
        }
        if (jSONArray2 != null) {
            for (int r6 = 0; r6 < jSONArray2.length(); r6++) {
                String string = jSONArray2.getString(r6);
                if (!stringNE.contains(string)) {
                    jSONArray4.put(string);
                }
            }
        }
        if (!jSONArray3.toString().equals(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI)) {
            jSONObject.put(str + "_a", jSONArray3);
        }
        if (jSONArray4.toString().equals(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI)) {
            return;
        }
        jSONObject.put(str + "_d", jSONArray4);
    }

    static String toStringNE(JSONArray jSONArray) {
        String str = "[";
        for (int r1 = 0; r1 < jSONArray.length(); r1++) {
            try {
                str = str + "\"" + jSONArray.getString(r1) + "\"";
            } catch (JSONException unused) {
            }
        }
        return str + "]";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toUnescapedEUIDString(JSONObject jSONObject) {
        String group;
        String jSONObject2 = jSONObject.toString();
        if (jSONObject.has("external_user_id")) {
            Matcher matcher = Pattern.compile("(?<=\"external_user_id\":\").*?(?=\")").matcher(jSONObject2);
            return (!matcher.find() || (group = matcher.group(0)) == null) ? jSONObject2 : matcher.replaceAll(Matcher.quoteReplacement(group.replace("\\/", "/")));
        }
        return jSONObject2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static JSONObject getJSONObjectWithoutBlankValues(ImmutableJSONObject immutableJSONObject, String str) {
        if (immutableJSONObject.has(str)) {
            JSONObject jSONObject = new JSONObject();
            JSONObject optJSONObject = immutableJSONObject.optJSONObject(str);
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    Object obj = optJSONObject.get(next);
                    if (!"".equals(obj)) {
                        jSONObject.put(next, obj);
                    }
                } catch (JSONException unused) {
                }
            }
            return jSONObject;
        }
        return null;
    }

    static Map<String, Object> jsonObjectToMap(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null || jSONObject == JSONObject.NULL) {
            return null;
        }
        return jsonObjectToMapNonNull(jSONObject);
    }

    private static Map<String, Object> jsonObjectToMapNonNull(JSONObject jSONObject) throws JSONException {
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, convertNestedJSONType(jSONObject.get(next)));
        }
        return hashMap;
    }

    static List<Object> jsonArrayToList(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        return jsonArrayToListNonNull(jSONArray);
    }

    private static List<Object> jsonArrayToListNonNull(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int r1 = 0; r1 < jSONArray.length(); r1++) {
            arrayList.add(convertNestedJSONType(jSONArray.get(r1)));
        }
        return arrayList;
    }

    private static Object convertNestedJSONType(Object obj) throws JSONException {
        if (obj instanceof JSONObject) {
            return jsonObjectToMapNonNull((JSONObject) obj);
        }
        return obj instanceof JSONArray ? jsonArrayToListNonNull((JSONArray) obj) : obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003b, code lost:
        r2 = r2 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean compareJSONArrays(org.json.JSONArray r6, org.json.JSONArray r7) {
        /*
            r0 = 1
            if (r6 != 0) goto L6
            if (r7 != 0) goto L6
            return r0
        L6:
            r1 = 0
            if (r6 == 0) goto L47
            if (r7 != 0) goto Lc
            goto L47
        Lc:
            int r2 = r6.length()
            int r3 = r7.length()
            if (r2 == r3) goto L17
            return r1
        L17:
            r2 = 0
        L18:
            int r3 = r6.length()     // Catch: org.json.JSONException -> L43
            if (r2 >= r3) goto L42
            r3 = 0
        L1f:
            int r4 = r7.length()     // Catch: org.json.JSONException -> L43
            if (r3 >= r4) goto L41
            java.lang.Object r4 = r6.get(r2)     // Catch: org.json.JSONException -> L43
            java.lang.Object r4 = normalizeType(r4)     // Catch: org.json.JSONException -> L43
            java.lang.Object r5 = r7.get(r3)     // Catch: org.json.JSONException -> L43
            java.lang.Object r5 = normalizeType(r5)     // Catch: org.json.JSONException -> L43
            boolean r4 = r4.equals(r5)     // Catch: org.json.JSONException -> L43
            if (r4 == 0) goto L3e
            int r2 = r2 + 1
            goto L18
        L3e:
            int r3 = r3 + 1
            goto L1f
        L41:
            return r1
        L42:
            return r0
        L43:
            r6 = move-exception
            r6.printStackTrace()
        L47:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onesignal.JSONUtils.compareJSONArrays(org.json.JSONArray, org.json.JSONArray):boolean");
    }

    public static Object normalizeType(Object obj) {
        Class<?> cls = obj.getClass();
        if (cls.equals(Integer.class)) {
            return Long.valueOf(((Integer) obj).intValue());
        }
        return cls.equals(Float.class) ? Double.valueOf(((Float) obj).floatValue()) : obj;
    }
}
