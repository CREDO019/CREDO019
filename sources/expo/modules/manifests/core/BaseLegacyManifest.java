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

/* compiled from: BaseLegacyManifest.kt */
@Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u0004\u0018\u00010\u0006J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\u0003H\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u000f\u001a\u00020\u0006H\u0016J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u0006H\u0016¨\u0006\u0012"}, m183d2 = {"Lexpo/modules/manifests/core/BaseLegacyManifest;", "Lexpo/modules/manifests/core/Manifest;", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getAppKey", "", "getAssets", "Lorg/json/JSONArray;", "getBundleURL", "getCommitTime", "getEASProjectID", "getExpoClientConfigRootObject", "getExpoGoConfigRootObject", "getSDKVersion", "getScopeKey", "getSlug", "getStableLegacyID", "expo-manifests_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public abstract class BaseLegacyManifest extends Manifest {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseLegacyManifest(JSONObject json) {
        super(json);
        Intrinsics.checkNotNullParameter(json, "json");
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getStableLegacyID() {
        String str;
        JSONObject json = getJson();
        if (json.has("originalFullName")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                str = json.getString("originalFullName");
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(json.getDouble("originalFullName"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(json.getInt("originalFullName"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(json.getLong("originalFullName"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(json.getBoolean("originalFullName"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("originalFullName");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("originalFullName");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject;
            } else {
                Object obj = json.get("originalFullName");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj;
            }
        } else {
            str = null;
        }
        return str == null ? getLegacyID() : str;
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getScopeKey() {
        String str;
        JSONObject json = getJson();
        if (json.has(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                str = json.getString(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY);
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(json.getDouble(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(json.getInt(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(json.getLong(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(json.getBoolean(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY);
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY);
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject;
            } else {
                Object obj = json.get(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY);
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj;
            }
        } else {
            str = null;
        }
        return str == null ? getStableLegacyID() : str;
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getEASProjectID() {
        JSONObject json = getJson();
        if (json.has("projectId")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = json.getString("projectId");
                Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
                return string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (String) Double.valueOf(json.getDouble("projectId"));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (String) Integer.valueOf(json.getInt("projectId"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (String) Long.valueOf(json.getLong("projectId"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (String) Boolean.valueOf(json.getBoolean("projectId"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = json.getJSONArray("projectId");
                    Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject = json.getJSONObject("projectId");
                    Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONObject;
                } else {
                    Object obj = json.get("projectId");
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                    return (String) obj;
                }
            }
        }
        return null;
    }

    @Override // expo.modules.manifests.core.Manifest
    public JSONArray getAssets() {
        JSONObject json = getJson();
        if (json.has("assets")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONArray.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = json.getString("assets");
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONArray");
                return (JSONArray) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (JSONArray) Double.valueOf(json.getDouble("assets"));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (JSONArray) Integer.valueOf(json.getInt("assets"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (JSONArray) Long.valueOf(json.getLong("assets"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (JSONArray) Boolean.valueOf(json.getBoolean("assets"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = json.getJSONArray("assets");
                    Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONArray");
                    return jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject = json.getJSONObject("assets");
                    Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONArray");
                    return (JSONArray) jSONObject;
                } else {
                    Object obj = json.get("assets");
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONArray");
                    return (JSONArray) obj;
                }
            }
        }
        return null;
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getBundleURL() throws JSONException {
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("bundleUrl");
            Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
            return string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("bundleUrl"));
        } else {
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(json.getInt("bundleUrl"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(json.getLong("bundleUrl"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(json.getBoolean("bundleUrl"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("bundleUrl");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("bundleUrl");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONObject;
            } else {
                Object obj = json.get("bundleUrl");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                return (String) obj;
            }
        }
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getSDKVersion() {
        JSONObject json = getJson();
        if (json.has(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = json.getString(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY);
                Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
                return string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (String) Double.valueOf(json.getDouble(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (String) Integer.valueOf(json.getInt(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (String) Long.valueOf(json.getLong(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (String) Boolean.valueOf(json.getBoolean(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = json.getJSONArray(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY);
                    Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject = json.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY);
                    Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONObject;
                } else {
                    Object obj = json.get(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY);
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                    return (String) obj;
                }
            }
        }
        return null;
    }

    @Override // expo.modules.manifests.core.Manifest
    public JSONObject getExpoGoConfigRootObject() {
        return getJson();
    }

    @Override // expo.modules.manifests.core.Manifest
    public JSONObject getExpoClientConfigRootObject() {
        return getJson();
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getSlug() {
        JSONObject json = getJson();
        if (json.has("slug")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = json.getString("slug");
                Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
                return string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (String) Double.valueOf(json.getDouble("slug"));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (String) Integer.valueOf(json.getInt("slug"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (String) Long.valueOf(json.getLong("slug"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (String) Boolean.valueOf(json.getBoolean("slug"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = json.getJSONArray("slug");
                    Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject = json.getJSONObject("slug");
                    Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONObject;
                } else {
                    Object obj = json.get("slug");
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                    return (String) obj;
                }
            }
        }
        return null;
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getAppKey() {
        JSONObject json = getJson();
        if (json.has("appKey")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = json.getString("appKey");
                Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
                return string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (String) Double.valueOf(json.getDouble("appKey"));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (String) Integer.valueOf(json.getInt("appKey"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (String) Long.valueOf(json.getLong("appKey"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (String) Boolean.valueOf(json.getBoolean("appKey"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = json.getJSONArray("appKey");
                    Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject = json.getJSONObject("appKey");
                    Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONObject;
                } else {
                    Object obj = json.get("appKey");
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                    return (String) obj;
                }
            }
        }
        return null;
    }

    public final String getCommitTime() {
        JSONObject json = getJson();
        if (json.has("commitTime")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = json.getString("commitTime");
                Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
                return string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (String) Double.valueOf(json.getDouble("commitTime"));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (String) Integer.valueOf(json.getInt("commitTime"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (String) Long.valueOf(json.getLong("commitTime"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (String) Boolean.valueOf(json.getBoolean("commitTime"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = json.getJSONArray("commitTime");
                    Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject = json.getJSONObject("commitTime");
                    Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                    return (String) jSONObject;
                } else {
                    Object obj = json.get("commitTime");
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                    return (String) obj;
                }
            }
        }
        return null;
    }
}
