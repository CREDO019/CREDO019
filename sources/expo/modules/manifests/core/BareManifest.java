package expo.modules.manifests.core;

import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BareManifest.kt */
@Metadata(m184d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, m183d2 = {"Lexpo/modules/manifests/core/BareManifest;", "Lexpo/modules/manifests/core/BaseLegacyManifest;", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getCommitTimeLong", "", "getID", "", "expo-manifests_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class BareManifest extends BaseLegacyManifest {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BareManifest(JSONObject json) {
        super(json);
        Intrinsics.checkNotNullParameter(json, "json");
    }

    public final String getID() throws JSONException {
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("id");
            Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
            return string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("id"));
        } else {
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(json.getInt("id"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(json.getLong("id"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(json.getBoolean("id"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("id");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("id");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONObject;
            } else {
                Object obj = json.get("id");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                return (String) obj;
            }
        }
    }

    public final long getCommitTimeLong() throws JSONException {
        Long l;
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Long.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("commitTime");
            Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.Long");
            l = (Long) string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            l = (Long) Double.valueOf(json.getDouble("commitTime"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            l = (Long) Integer.valueOf(json.getInt("commitTime"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            l = Long.valueOf(json.getLong("commitTime"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            l = (Long) Boolean.valueOf(json.getBoolean("commitTime"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = json.getJSONArray("commitTime");
            Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.Long");
            l = (Long) jSONArray;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject = json.getJSONObject("commitTime");
            Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.Long");
            l = (Long) jSONObject;
        } else {
            Object obj = json.get("commitTime");
            Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Long");
            l = (Long) obj;
        }
        return l.longValue();
    }
}
