package expo.modules.manifests.core;

import expo.modules.updates.UpdatesConfiguration;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: LegacyManifest.kt */
@Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0006\u0010\n\u001a\u00020\u0006J\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006¨\u0006\f"}, m183d2 = {"Lexpo/modules/manifests/core/LegacyManifest;", "Lexpo/modules/manifests/core/BaseLegacyManifest;", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getAssetUrlOverride", "", "getBundleKey", "getBundledAssets", "Lorg/json/JSONArray;", "getReleaseId", "getRuntimeVersion", "expo-manifests_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public class LegacyManifest extends BaseLegacyManifest {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LegacyManifest(JSONObject json) {
        super(json);
        Intrinsics.checkNotNullParameter(json, "json");
    }

    public final String getBundleKey() throws JSONException {
        JSONObject json = getJson();
        if (json.has("bundleKey")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = json.getString("bundleKey");
                Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
                return string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (String) Double.valueOf(json.getDouble("bundleKey"));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (String) Integer.valueOf(json.getInt("bundleKey"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (String) Long.valueOf(json.getLong("bundleKey"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (String) Boolean.valueOf(json.getBoolean("bundleKey"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = json.getJSONArray("bundleKey");
                    Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject = json.getJSONObject("bundleKey");
                    Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONObject;
                } else {
                    Object obj = json.get("bundleKey");
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                    return (String) obj;
                }
            }
        }
        return null;
    }

    public final String getReleaseId() throws JSONException {
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("releaseId");
            Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
            return string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("releaseId"));
        } else {
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(json.getInt("releaseId"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(json.getLong("releaseId"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(json.getBoolean("releaseId"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("releaseId");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("releaseId");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONObject;
            } else {
                Object obj = json.get("releaseId");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                return (String) obj;
            }
        }
    }

    public final String getRuntimeVersion() {
        JSONObject json = getJson();
        if (json.has(UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = json.getString(UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY);
                Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
                return string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (String) Double.valueOf(json.getDouble(UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (String) Integer.valueOf(json.getInt(UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (String) Long.valueOf(json.getLong(UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (String) Boolean.valueOf(json.getBoolean(UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = json.getJSONArray(UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY);
                    Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject = json.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY);
                    Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONObject;
                } else {
                    Object obj = json.get(UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY);
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                    return (String) obj;
                }
            }
        }
        return null;
    }

    public final JSONArray getBundledAssets() throws JSONException {
        JSONObject json = getJson();
        if (json.has("bundledAssets")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONArray.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = json.getString("bundledAssets");
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONArray");
                return (JSONArray) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (JSONArray) Double.valueOf(json.getDouble("bundledAssets"));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (JSONArray) Integer.valueOf(json.getInt("bundledAssets"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (JSONArray) Long.valueOf(json.getLong("bundledAssets"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (JSONArray) Boolean.valueOf(json.getBoolean("bundledAssets"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = json.getJSONArray("bundledAssets");
                    Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONArray");
                    return jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject = json.getJSONObject("bundledAssets");
                    Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONArray");
                    return (JSONArray) jSONObject;
                } else {
                    Object obj = json.get("bundledAssets");
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONArray");
                    return (JSONArray) obj;
                }
            }
        }
        return null;
    }

    public String getAssetUrlOverride() {
        JSONObject json = getJson();
        if (json.has("assetUrlOverride")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = json.getString("assetUrlOverride");
                Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
                return string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (String) Double.valueOf(json.getDouble("assetUrlOverride"));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (String) Integer.valueOf(json.getInt("assetUrlOverride"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (String) Long.valueOf(json.getLong("assetUrlOverride"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (String) Boolean.valueOf(json.getBoolean("assetUrlOverride"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = json.getJSONArray("assetUrlOverride");
                    Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject = json.getJSONObject("assetUrlOverride");
                    Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONObject;
                } else {
                    Object obj = json.get("assetUrlOverride");
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                    return (String) obj;
                }
            }
        }
        return null;
    }
}
