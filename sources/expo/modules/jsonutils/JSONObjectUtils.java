package expo.modules.jsonutils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(m184d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a(\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0086\b¢\u0006\u0002\u0010\u0006\u001a&\u0010\u0007\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0086\b¢\u0006\u0002\u0010\u0006¨\u0006\b"}, m183d2 = {"getNullable", "T", "", "Lorg/json/JSONObject;", "key", "", "(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/Object;", "require", "expo-json-utils_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.jsonutils.JSONObjectUtilsKt */
/* loaded from: classes4.dex */
public final class JSONObjectUtils {
    public static final /* synthetic */ <T> T require(JSONObject jSONObject, String key) throws JSONException {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.reifiedOperationMarker(4, "T");
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = jSONObject.getString(key);
            Intrinsics.reifiedOperationMarker(1, "T");
            return (T) string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            Double valueOf = Double.valueOf(jSONObject.getDouble(key));
            Intrinsics.reifiedOperationMarker(1, "T");
            return (T) valueOf;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            Integer valueOf2 = Integer.valueOf(jSONObject.getInt(key));
            Intrinsics.reifiedOperationMarker(1, "T");
            return (T) valueOf2;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            Long valueOf3 = Long.valueOf(jSONObject.getLong(key));
            Intrinsics.reifiedOperationMarker(1, "T");
            return (T) valueOf3;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            Boolean valueOf4 = Boolean.valueOf(jSONObject.getBoolean(key));
            Intrinsics.reifiedOperationMarker(1, "T");
            return (T) valueOf4;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = jSONObject.getJSONArray(key);
            Intrinsics.reifiedOperationMarker(1, "T");
            return (T) jSONArray;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(key);
            Intrinsics.reifiedOperationMarker(1, "T");
            return (T) jSONObject2;
        } else {
            T t = (T) jSONObject.get(key);
            Intrinsics.reifiedOperationMarker(1, "T");
            return t;
        }
    }

    public static final /* synthetic */ <T> T getNullable(JSONObject jSONObject, String key) {
        Intrinsics.checkNotNullParameter(jSONObject, "<this>");
        Intrinsics.checkNotNullParameter(key, "key");
        if (jSONObject.has(key)) {
            Intrinsics.reifiedOperationMarker(4, "T");
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = jSONObject.getString(key);
                Intrinsics.reifiedOperationMarker(1, "T");
                return (T) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                Double valueOf = Double.valueOf(jSONObject.getDouble(key));
                Intrinsics.reifiedOperationMarker(1, "T");
                return (T) valueOf;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                Integer valueOf2 = Integer.valueOf(jSONObject.getInt(key));
                Intrinsics.reifiedOperationMarker(1, "T");
                return (T) valueOf2;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                Long valueOf3 = Long.valueOf(jSONObject.getLong(key));
                Intrinsics.reifiedOperationMarker(1, "T");
                return (T) valueOf3;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                Boolean valueOf4 = Boolean.valueOf(jSONObject.getBoolean(key));
                Intrinsics.reifiedOperationMarker(1, "T");
                return (T) valueOf4;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = jSONObject.getJSONArray(key);
                Intrinsics.reifiedOperationMarker(1, "T");
                return (T) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(key);
                Intrinsics.reifiedOperationMarker(1, "T");
                return (T) jSONObject2;
            } else {
                T t = (T) jSONObject.get(key);
                Intrinsics.reifiedOperationMarker(1, "T");
                return t;
            }
        }
        return null;
    }
}
