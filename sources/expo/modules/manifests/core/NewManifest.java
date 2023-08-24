package expo.modules.manifests.core;

import com.google.android.gms.common.internal.ImagesContract;
import expo.modules.updates.UpdatesConfiguration;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NewManifest.kt */
@Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\u0006H\u0016J\u0006\u0010\n\u001a\u00020\u0006J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\u0003H\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\u0003H\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u0002J\u0006\u0010\u000f\u001a\u00020\u0006J\u0006\u0010\u0010\u001a\u00020\u0003J\u0006\u0010\u0011\u001a\u00020\u0006J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0013\u001a\u00020\u0006H\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0006H\u0016J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0016"}, m183d2 = {"Lexpo/modules/manifests/core/NewManifest;", "Lexpo/modules/manifests/core/Manifest;", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "getAppKey", "", "getAssets", "Lorg/json/JSONArray;", "getBundleURL", "getCreatedAt", "getEASProjectID", "getExpoClientConfigRootObject", "getExpoGoConfigRootObject", "getExtra", "getID", "getLaunchAsset", "getRuntimeVersion", "getSDKVersion", "getScopeKey", "getSlug", "getStableLegacyID", "expo-manifests_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class NewManifest extends Manifest {
    @Override // expo.modules.manifests.core.Manifest
    public String getAppKey() {
        return null;
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getSlug() {
        return null;
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getStableLegacyID() {
        return null;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NewManifest(JSONObject json) {
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

    @Override // expo.modules.manifests.core.Manifest
    public String getScopeKey() throws JSONException {
        JSONObject jSONObject;
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("extra");
            Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
            jSONObject = (JSONObject) string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            jSONObject = (JSONObject) Double.valueOf(json.getDouble("extra"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            jSONObject = (JSONObject) Integer.valueOf(json.getInt("extra"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            jSONObject = (JSONObject) Long.valueOf(json.getLong("extra"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            jSONObject = (JSONObject) Boolean.valueOf(json.getBoolean("extra"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = json.getJSONArray("extra");
            Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
            jSONObject = (JSONObject) jSONArray;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            jSONObject = json.getJSONObject("extra");
            Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
        } else {
            Object obj = json.get("extra");
            Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
            jSONObject = (JSONObject) obj;
        }
        KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
            String string2 = jSONObject.getString(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY);
            Objects.requireNonNull(string2, "null cannot be cast to non-null type kotlin.String");
            return string2;
        } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(jSONObject.getDouble(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY));
        } else {
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(jSONObject.getInt(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(jSONObject.getLong(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(jSONObject.getBoolean(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray2 = jSONObject.getJSONArray(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY);
                Objects.requireNonNull(jSONArray2, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONArray2;
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY);
                Objects.requireNonNull(jSONObject2, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONObject2;
            } else {
                Object obj2 = jSONObject.get(UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY);
                Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.String");
                return (String) obj2;
            }
        }
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getEASProjectID() {
        JSONObject jSONObject;
        String str;
        JSONObject extra = getExtra();
        if (extra != null && extra.has("eas")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = extra.getString("eas");
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(extra.getDouble("eas"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(extra.getInt("eas"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(extra.getLong("eas"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(extra.getBoolean("eas"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = extra.getJSONArray("eas");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = extra.getJSONObject("eas");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = extra.get("eas");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
        } else {
            jSONObject = null;
        }
        if (jSONObject != null && jSONObject.has("projectId")) {
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                str = jSONObject.getString("projectId");
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(jSONObject.getDouble("projectId"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(jSONObject.getInt("projectId"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(jSONObject.getLong("projectId"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(jSONObject.getBoolean("projectId"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("projectId");
                Objects.requireNonNull(jSONArray2, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray2;
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("projectId");
                Objects.requireNonNull(jSONObject2, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject2;
            } else {
                Object obj2 = jSONObject.get("projectId");
                Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj2;
            }
            return str;
        }
        return null;
    }

    public final String getRuntimeVersion() throws JSONException {
        JSONObject json = getJson();
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

    @Override // expo.modules.manifests.core.Manifest
    public String getBundleURL() throws JSONException {
        JSONObject launchAsset = getLaunchAsset();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = launchAsset.getString(ImagesContract.URL);
            Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
            return string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(launchAsset.getDouble(ImagesContract.URL));
        } else {
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(launchAsset.getInt(ImagesContract.URL));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(launchAsset.getLong(ImagesContract.URL));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(launchAsset.getBoolean(ImagesContract.URL));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = launchAsset.getJSONArray(ImagesContract.URL);
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = launchAsset.getJSONObject(ImagesContract.URL);
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONObject;
            } else {
                Object obj = launchAsset.get(ImagesContract.URL);
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                return (String) obj;
            }
        }
    }

    @Override // expo.modules.manifests.core.Manifest
    public String getSDKVersion() {
        String runtimeVersion = getRuntimeVersion();
        if (Intrinsics.areEqual(runtimeVersion, "exposdk:UNVERSIONED")) {
            return "UNVERSIONED";
        }
        Pattern compile = Pattern.compile("^exposdk:(\\d+\\.\\d+\\.\\d+)$");
        Intrinsics.checkNotNullExpressionValue(compile, "compile(\"^exposdk:(\\\\d+\\\\.\\\\d+\\\\.\\\\d+)$\")");
        Matcher matcher = compile.matcher(runtimeVersion);
        Intrinsics.checkNotNullExpressionValue(matcher, "expoSDKRuntimeVersionRegex.matcher(runtimeVersion)");
        if (matcher.find()) {
            String group = matcher.group(1);
            Intrinsics.checkNotNull(group);
            return group;
        }
        return null;
    }

    public final JSONObject getLaunchAsset() throws JSONException {
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("launchAsset");
            Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
            return (JSONObject) string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (JSONObject) Double.valueOf(json.getDouble("launchAsset"));
        } else {
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (JSONObject) Integer.valueOf(json.getInt("launchAsset"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (JSONObject) Long.valueOf(json.getLong("launchAsset"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (JSONObject) Boolean.valueOf(json.getBoolean("launchAsset"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("launchAsset");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                return (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("launchAsset");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
                return jSONObject;
            } else {
                Object obj = json.get("launchAsset");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                return (JSONObject) obj;
            }
        }
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

    public final String getCreatedAt() throws JSONException {
        JSONObject json = getJson();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = json.getString("createdAt");
            Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
            return string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(json.getDouble("createdAt"));
        } else {
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(json.getInt("createdAt"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(json.getLong("createdAt"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(json.getBoolean("createdAt"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = json.getJSONArray("createdAt");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = json.getJSONObject("createdAt");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONObject;
            } else {
                Object obj = json.get("createdAt");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                return (String) obj;
            }
        }
    }

    @Override // expo.modules.manifests.core.Manifest
    public JSONObject getExpoGoConfigRootObject() {
        JSONObject jSONObject;
        JSONObject extra = getExtra();
        if (extra != null && extra.has("expoGo")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = extra.getString("expoGo");
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(extra.getDouble("expoGo"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(extra.getInt("expoGo"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(extra.getLong("expoGo"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(extra.getBoolean("expoGo"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = extra.getJSONArray("expoGo");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = extra.getJSONObject("expoGo");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = extra.get("expoGo");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
            return jSONObject;
        }
        return null;
    }

    @Override // expo.modules.manifests.core.Manifest
    public JSONObject getExpoClientConfigRootObject() {
        JSONObject jSONObject;
        JSONObject extra = getExtra();
        if (extra != null && extra.has("expoClient")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = extra.getString("expoClient");
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(extra.getDouble("expoClient"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(extra.getInt("expoClient"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(extra.getLong("expoClient"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(extra.getBoolean("expoClient"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = extra.getJSONArray("expoClient");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = extra.getJSONObject("expoClient");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = extra.get("expoClient");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
            return jSONObject;
        }
        return null;
    }

    private final JSONObject getExtra() {
        JSONObject json = getJson();
        if (json.has("extra")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = json.getString("extra");
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                return (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (JSONObject) Double.valueOf(json.getDouble("extra"));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (JSONObject) Integer.valueOf(json.getInt("extra"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (JSONObject) Long.valueOf(json.getLong("extra"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (JSONObject) Boolean.valueOf(json.getBoolean("extra"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = json.getJSONArray("extra");
                    Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                    return (JSONObject) jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject = json.getJSONObject("extra");
                    Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
                    return jSONObject;
                } else {
                    Object obj = json.get("extra");
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                    return (JSONObject) obj;
                }
            }
        }
        return null;
    }
}
