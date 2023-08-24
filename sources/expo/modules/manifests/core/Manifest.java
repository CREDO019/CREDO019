package expo.modules.manifests.core;

import com.amplitude.api.Constants;
import com.amplitude.api.DeviceInfo;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.onesignal.OneSignalDbContract;
import java.util.Objects;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Manifest.kt */
@Metadata(m184d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 >2\u00020\u0001:\u0001>B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u0004\u0018\u00010\u0006J\b\u0010\u000e\u001a\u0004\u0018\u00010\u0006J\b\u0010\u000f\u001a\u0004\u0018\u00010\u0006J\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0011\u001a\u0004\u0018\u00010\u0006J\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003J\b\u0010\u0014\u001a\u0004\u0018\u00010\u0006J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0006H&J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0017H&J\b\u0010\u0018\u001a\u00020\u0006H&J\u0006\u0010\u0019\u001a\u00020\u0006J\n\u0010\u001a\u001a\u0004\u0018\u00010\u0006H&J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u001c\u001a\u0004\u0018\u00010\u0003H&J\u0006\u0010\u001d\u001a\u00020\u0006J\u0006\u0010\u001e\u001a\u00020\u0006J\u0006\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u0004\u0018\u00010\u0006J\b\u0010\"\u001a\u0004\u0018\u00010\u0006J\b\u0010#\u001a\u00020\u0006H\u0007J\b\u0010$\u001a\u0004\u0018\u00010\u0006J\u0006\u0010%\u001a\u00020\u0006J\b\u0010&\u001a\u0004\u0018\u00010\u0003J\b\u0010'\u001a\u0004\u0018\u00010\u0006J\b\u0010(\u001a\u0004\u0018\u00010\u0003J\b\u0010)\u001a\u0004\u0018\u00010\u0006J\b\u0010*\u001a\u0004\u0018\u00010\u0006J\b\u0010+\u001a\u00020\u0003H\u0007J\u0006\u0010,\u001a\u00020\u0006J\b\u0010-\u001a\u0004\u0018\u00010\u0003J\n\u0010.\u001a\u0004\u0018\u00010\u0006H&J\b\u0010/\u001a\u00020\u0006H&J\n\u00100\u001a\u0004\u0018\u00010\u0006H&J\n\u00101\u001a\u0004\u0018\u00010\u0006H'J\b\u00102\u001a\u0004\u0018\u00010\u0003J\b\u00103\u001a\u0004\u0018\u00010\u0006J\u0006\u00104\u001a\u00020 J\u0006\u00105\u001a\u00020 J\u0006\u00106\u001a\u00020 J\u0006\u00107\u001a\u00020 J\u0010\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0007J\u0006\u0010<\u001a\u00020 J\b\u0010=\u001a\u00020\u0006H\u0017R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006?"}, m183d2 = {"Lexpo/modules/manifests/core/Manifest;", "", "json", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)V", "jsEngine", "", "getJsEngine", "()Ljava/lang/String;", "jsEngine$delegate", "Lkotlin/Lazy;", "getJson", "()Lorg/json/JSONObject;", "getAndroidBackgroundColor", "getAndroidGoogleServicesFile", "getAndroidKeyboardLayoutMode", "getAndroidNavigationBarOptions", "getAndroidPackageName", "getAndroidSplashInfo", "getAndroidStatusBarOptions", "getAndroidUserInterfaceStyle", "getAppKey", "getAssets", "Lorg/json/JSONArray;", "getBundleURL", "getDebuggerHost", "getEASProjectID", "getExpoClientConfigRootObject", "getExpoGoConfigRootObject", "getFacebookAppId", "getFacebookApplicationName", "getFacebookAutoInitEnabled", "", "getHostUri", "getIconUrl", "getLegacyID", "getLogUrl", "getMainModuleName", "getMetadata", "getName", "getNotificationPreferences", "getOrientation", "getPrimaryColor", "getRawJson", "getRevisionId", "getRootSplashInfo", "getSDKVersion", "getScopeKey", "getSlug", "getStableLegacyID", "getUpdatesInfo", "getVersion", "isDevelopmentMode", "isDevelopmentSilentLaunch", "isUsingDeveloperTool", "isVerified", "mutateInternalJSONInPlace", "", "internalJSONMutator", "Lexpo/modules/manifests/core/InternalJSONMutator;", "shouldUseNextNotificationsApi", "toString", "Companion", "expo-manifests_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public abstract class Manifest {
    public static final Companion Companion = new Companion(null);
    private final Lazy jsEngine$delegate;
    private final JSONObject json;

    @JvmStatic
    public static final Manifest fromManifestJson(JSONObject jSONObject) {
        return Companion.fromManifestJson(jSONObject);
    }

    public abstract String getAppKey();

    public abstract JSONArray getAssets();

    public abstract String getBundleURL() throws JSONException;

    public abstract String getEASProjectID();

    public abstract JSONObject getExpoClientConfigRootObject();

    public abstract JSONObject getExpoGoConfigRootObject();

    public abstract String getSDKVersion();

    public abstract String getScopeKey() throws JSONException;

    public abstract String getSlug();

    @Deprecated(message = "Prefer scopeKey or projectId depending on use case")
    public abstract String getStableLegacyID();

    public Manifest(JSONObject json) {
        Intrinsics.checkNotNullParameter(json, "json");
        this.json = json;
        this.jsEngine$delegate = LazyKt.lazy(new Functions<String>() { // from class: expo.modules.manifests.core.Manifest$jsEngine$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Removed duplicated region for block: B:59:0x0187  */
            /* JADX WARN: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
            @Override // kotlin.jvm.functions.Functions
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.String invoke() {
                /*
                    Method dump skipped, instructions count: 586
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: expo.modules.manifests.core.Manifest$jsEngine$2.invoke():java.lang.String");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JSONObject getJson() {
        return this.json;
    }

    @Deprecated(message = "Strive for manifests to be immutable")
    public final void mutateInternalJSONInPlace(InternalJSONMutator internalJSONMutator) throws JSONException {
        Intrinsics.checkNotNullParameter(internalJSONMutator, "internalJSONMutator");
        internalJSONMutator.updateJSON(this.json);
    }

    @Deprecated(message = "Prefer to use specific field getters")
    public final JSONObject getRawJson() {
        return this.json;
    }

    @Deprecated(message = "Prefer to use specific field getters")
    public String toString() {
        String jSONObject = getRawJson().toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "getRawJson().toString()");
        return jSONObject;
    }

    @Deprecated(message = "Prefer scopeKey or projectId depending on use case")
    public final String getLegacyID() throws JSONException {
        JSONObject jSONObject = this.json;
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = jSONObject.getString("id");
            Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
            return string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(jSONObject.getDouble("id"));
        } else {
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(jSONObject.getInt("id"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(jSONObject.getLong("id"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(jSONObject.getBoolean("id"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = jSONObject.getJSONArray("id");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("id");
                Objects.requireNonNull(jSONObject2, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONObject2;
            } else {
                Object obj = jSONObject.get("id");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                return (String) obj;
            }
        }
    }

    public final String getRevisionId() throws JSONException {
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        Intrinsics.checkNotNull(expoClientConfigRootObject);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoClientConfigRootObject.getString("revisionId");
            Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
            return string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(expoClientConfigRootObject.getDouble("revisionId"));
        } else {
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(expoClientConfigRootObject.getInt("revisionId"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(expoClientConfigRootObject.getLong("revisionId"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("revisionId"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("revisionId");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("revisionId");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONObject;
            } else {
                Object obj = expoClientConfigRootObject.get("revisionId");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                return (String) obj;
            }
        }
    }

    public final JSONObject getMetadata() {
        JSONObject jSONObject = this.json;
        if (jSONObject.has(TtmlNode.TAG_METADATA)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = jSONObject.getString(TtmlNode.TAG_METADATA);
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                return (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (JSONObject) Double.valueOf(jSONObject.getDouble(TtmlNode.TAG_METADATA));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (JSONObject) Integer.valueOf(jSONObject.getInt(TtmlNode.TAG_METADATA));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (JSONObject) Long.valueOf(jSONObject.getLong(TtmlNode.TAG_METADATA));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (JSONObject) Boolean.valueOf(jSONObject.getBoolean(TtmlNode.TAG_METADATA));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray = jSONObject.getJSONArray(TtmlNode.TAG_METADATA);
                    Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                    return (JSONObject) jSONArray;
                } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(TtmlNode.TAG_METADATA);
                    Objects.requireNonNull(jSONObject2, "null cannot be cast to non-null type org.json.JSONObject");
                    return jSONObject2;
                } else {
                    Object obj = jSONObject.get(TtmlNode.TAG_METADATA);
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                    return (JSONObject) obj;
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean isDevelopmentMode() {
        /*
            Method dump skipped, instructions count: 450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.manifests.core.Manifest.isDevelopmentMode():boolean");
    }

    public final boolean isDevelopmentSilentLaunch() {
        JSONObject jSONObject;
        Boolean bool;
        JSONObject expoGoConfigRootObject = getExpoGoConfigRootObject();
        if (expoGoConfigRootObject == null) {
            return false;
        }
        Boolean bool2 = null;
        if (expoGoConfigRootObject.has("developmentClient")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoGoConfigRootObject.getString("developmentClient");
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoGoConfigRootObject.getDouble("developmentClient"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoGoConfigRootObject.getInt("developmentClient"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoGoConfigRootObject.getLong("developmentClient"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoGoConfigRootObject.getBoolean("developmentClient"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoGoConfigRootObject.getJSONArray("developmentClient");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoGoConfigRootObject.getJSONObject("developmentClient");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = expoGoConfigRootObject.get("developmentClient");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
        } else {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("silentLaunch")) {
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                String string2 = jSONObject.getString("silentLaunch");
                Objects.requireNonNull(string2, "null cannot be cast to non-null type kotlin.Boolean");
                bool = (Boolean) string2;
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                bool = (Boolean) Double.valueOf(jSONObject.getDouble("silentLaunch"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                bool = (Boolean) Integer.valueOf(jSONObject.getInt("silentLaunch"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                bool = (Boolean) Long.valueOf(jSONObject.getLong("silentLaunch"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                bool = Boolean.valueOf(jSONObject.getBoolean("silentLaunch"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("silentLaunch");
                Objects.requireNonNull(jSONArray2, "null cannot be cast to non-null type kotlin.Boolean");
                bool = (Boolean) jSONArray2;
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("silentLaunch");
                Objects.requireNonNull(jSONObject2, "null cannot be cast to non-null type kotlin.Boolean");
                bool = (Boolean) jSONObject2;
            } else {
                Object obj2 = jSONObject.get("silentLaunch");
                Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.Boolean");
                bool = (Boolean) obj2;
            }
            bool2 = bool;
        }
        if (bool2 == null) {
            return false;
        }
        return bool2.booleanValue();
    }

    public final boolean isUsingDeveloperTool() {
        JSONObject jSONObject;
        JSONObject expoGoConfigRootObject = getExpoGoConfigRootObject();
        if (expoGoConfigRootObject == null) {
            return false;
        }
        if (expoGoConfigRootObject.has("developer")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoGoConfigRootObject.getString("developer");
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoGoConfigRootObject.getDouble("developer"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoGoConfigRootObject.getInt("developer"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoGoConfigRootObject.getLong("developer"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoGoConfigRootObject.getBoolean("developer"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoGoConfigRootObject.getJSONArray("developer");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoGoConfigRootObject.getJSONObject("developer");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = expoGoConfigRootObject.get("developer");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
        } else {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return false;
        }
        return jSONObject.has(SessionDescription.ATTR_TOOL);
    }

    public final String getDebuggerHost() {
        JSONObject expoGoConfigRootObject = getExpoGoConfigRootObject();
        Intrinsics.checkNotNull(expoGoConfigRootObject);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoGoConfigRootObject.getString("debuggerHost");
            Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
            return string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(expoGoConfigRootObject.getDouble("debuggerHost"));
        } else {
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(expoGoConfigRootObject.getInt("debuggerHost"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(expoGoConfigRootObject.getLong("debuggerHost"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(expoGoConfigRootObject.getBoolean("debuggerHost"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoGoConfigRootObject.getJSONArray("debuggerHost");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoGoConfigRootObject.getJSONObject("debuggerHost");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONObject;
            } else {
                Object obj = expoGoConfigRootObject.get("debuggerHost");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                return (String) obj;
            }
        }
    }

    public final String getMainModuleName() {
        JSONObject expoGoConfigRootObject = getExpoGoConfigRootObject();
        Intrinsics.checkNotNull(expoGoConfigRootObject);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoGoConfigRootObject.getString("mainModuleName");
            Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
            return string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(expoGoConfigRootObject.getDouble("mainModuleName"));
        } else {
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(expoGoConfigRootObject.getInt("mainModuleName"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(expoGoConfigRootObject.getLong("mainModuleName"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(expoGoConfigRootObject.getBoolean("mainModuleName"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoGoConfigRootObject.getJSONArray("mainModuleName");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoGoConfigRootObject.getJSONObject("mainModuleName");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONObject;
            } else {
                Object obj = expoGoConfigRootObject.get("mainModuleName");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                return (String) obj;
            }
        }
    }

    public final String getLogUrl() {
        String str;
        JSONObject expoGoConfigRootObject = getExpoGoConfigRootObject();
        if (expoGoConfigRootObject != null && expoGoConfigRootObject.has("logUrl")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                str = expoGoConfigRootObject.getString("logUrl");
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(expoGoConfigRootObject.getDouble("logUrl"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(expoGoConfigRootObject.getInt("logUrl"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(expoGoConfigRootObject.getLong("logUrl"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(expoGoConfigRootObject.getBoolean("logUrl"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoGoConfigRootObject.getJSONArray("logUrl");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoGoConfigRootObject.getJSONObject("logUrl");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject;
            } else {
                Object obj = expoGoConfigRootObject.get("logUrl");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj;
            }
            return str;
        }
        return null;
    }

    public final String getHostUri() {
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject != null && expoClientConfigRootObject.has("hostUri")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                str = expoClientConfigRootObject.getString("hostUri");
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("hostUri"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("hostUri"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(expoClientConfigRootObject.getLong("hostUri"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("hostUri"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("hostUri");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("hostUri");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject;
            } else {
                Object obj = expoClientConfigRootObject.get("hostUri");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj;
            }
            return str;
        }
        return null;
    }

    public final boolean isVerified() {
        Boolean bool;
        JSONObject jSONObject = this.json;
        if (jSONObject.has("isVerified")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = jSONObject.getString("isVerified");
                Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.Boolean");
                bool = (Boolean) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                bool = (Boolean) Double.valueOf(jSONObject.getDouble("isVerified"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                bool = (Boolean) Integer.valueOf(jSONObject.getInt("isVerified"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                bool = (Boolean) Long.valueOf(jSONObject.getLong("isVerified"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                bool = Boolean.valueOf(jSONObject.getBoolean("isVerified"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = jSONObject.getJSONArray("isVerified");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.Boolean");
                bool = (Boolean) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("isVerified");
                Objects.requireNonNull(jSONObject2, "null cannot be cast to non-null type kotlin.Boolean");
                bool = (Boolean) jSONObject2;
            } else {
                Object obj = jSONObject.get("isVerified");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
                bool = (Boolean) obj;
            }
        } else {
            bool = null;
        }
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public final String getName() {
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject != null && expoClientConfigRootObject.has("name")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                str = expoClientConfigRootObject.getString("name");
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("name"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("name"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(expoClientConfigRootObject.getLong("name"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("name"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("name");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("name");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject;
            } else {
                Object obj = expoClientConfigRootObject.get("name");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj;
            }
            return str;
        }
        return null;
    }

    public final String getVersion() {
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject != null && expoClientConfigRootObject.has(Constants.AMP_PLAN_VERSION)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                str = expoClientConfigRootObject.getString(Constants.AMP_PLAN_VERSION);
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(expoClientConfigRootObject.getDouble(Constants.AMP_PLAN_VERSION));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(expoClientConfigRootObject.getInt(Constants.AMP_PLAN_VERSION));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(expoClientConfigRootObject.getLong(Constants.AMP_PLAN_VERSION));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean(Constants.AMP_PLAN_VERSION));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(Constants.AMP_PLAN_VERSION);
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoClientConfigRootObject.getJSONObject(Constants.AMP_PLAN_VERSION);
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject;
            } else {
                Object obj = expoClientConfigRootObject.get(Constants.AMP_PLAN_VERSION);
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj;
            }
            return str;
        }
        return null;
    }

    public final JSONObject getUpdatesInfo() {
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject != null && expoClientConfigRootObject.has("updates")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString("updates");
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble("updates"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt("updates"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong("updates"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean("updates"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("updates");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject("updates");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = expoClientConfigRootObject.get("updates");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
            return jSONObject;
        }
        return null;
    }

    public final String getPrimaryColor() {
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject != null && expoClientConfigRootObject.has("primaryColor")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                str = expoClientConfigRootObject.getString("primaryColor");
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("primaryColor"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("primaryColor"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(expoClientConfigRootObject.getLong("primaryColor"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("primaryColor"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("primaryColor");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("primaryColor");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject;
            } else {
                Object obj = expoClientConfigRootObject.get("primaryColor");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj;
            }
            return str;
        }
        return null;
    }

    public final String getOrientation() {
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject != null && expoClientConfigRootObject.has("orientation")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                str = expoClientConfigRootObject.getString("orientation");
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("orientation"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("orientation"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(expoClientConfigRootObject.getLong("orientation"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("orientation"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("orientation");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("orientation");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject;
            } else {
                Object obj = expoClientConfigRootObject.get("orientation");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj;
            }
            return str;
        }
        return null;
    }

    public final String getAndroidKeyboardLayoutMode() {
        JSONObject jSONObject;
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null) {
            return null;
        }
        if (expoClientConfigRootObject.has(DeviceInfo.OS_NAME)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
        } else {
            jSONObject = null;
        }
        if (jSONObject != null && jSONObject.has("softwareKeyboardLayoutMode")) {
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                str = jSONObject.getString("softwareKeyboardLayoutMode");
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(jSONObject.getDouble("softwareKeyboardLayoutMode"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(jSONObject.getInt("softwareKeyboardLayoutMode"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(jSONObject.getLong("softwareKeyboardLayoutMode"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(jSONObject.getBoolean("softwareKeyboardLayoutMode"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("softwareKeyboardLayoutMode");
                Objects.requireNonNull(jSONArray2, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray2;
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("softwareKeyboardLayoutMode");
                Objects.requireNonNull(jSONObject2, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject2;
            } else {
                Object obj2 = jSONObject.get("softwareKeyboardLayoutMode");
                Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj2;
            }
            return str;
        }
        return null;
    }

    public final String getAndroidUserInterfaceStyle() {
        String str;
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        String str2 = null;
        if (expoClientConfigRootObject == null) {
            return null;
        }
        try {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                if (string == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                if (jSONArray == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
                jSONObject = (JSONObject) obj;
            }
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                String string2 = jSONObject.getString("userInterfaceStyle");
                if (string2 != null) {
                    return string2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (String) Double.valueOf(jSONObject.getDouble("userInterfaceStyle"));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (String) Integer.valueOf(jSONObject.getInt("userInterfaceStyle"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (String) Long.valueOf(jSONObject.getLong("userInterfaceStyle"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (String) Boolean.valueOf(jSONObject.getBoolean("userInterfaceStyle"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray2 = jSONObject.getJSONArray("userInterfaceStyle");
                    if (jSONArray2 != null) {
                        return (String) jSONArray2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("userInterfaceStyle");
                    if (jSONObject2 != null) {
                        return (String) jSONObject2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                } else {
                    Object obj2 = jSONObject.get("userInterfaceStyle");
                    if (obj2 != null) {
                        return (String) obj2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
            }
        } catch (JSONException unused) {
            if (expoClientConfigRootObject.has("userInterfaceStyle")) {
                KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(String.class))) {
                    str = expoClientConfigRootObject.getString("userInterfaceStyle");
                    Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("userInterfaceStyle"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("userInterfaceStyle"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    str = (String) Long.valueOf(expoClientConfigRootObject.getLong("userInterfaceStyle"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("userInterfaceStyle"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray3 = expoClientConfigRootObject.getJSONArray("userInterfaceStyle");
                    Objects.requireNonNull(jSONArray3, "null cannot be cast to non-null type kotlin.String");
                    str = (String) jSONArray3;
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject3 = expoClientConfigRootObject.getJSONObject("userInterfaceStyle");
                    Objects.requireNonNull(jSONObject3, "null cannot be cast to non-null type kotlin.String");
                    str = (String) jSONObject3;
                } else {
                    Object obj3 = expoClientConfigRootObject.get("userInterfaceStyle");
                    Objects.requireNonNull(obj3, "null cannot be cast to non-null type kotlin.String");
                    str = (String) obj3;
                }
                str2 = str;
            }
            return str2;
        }
    }

    public final JSONObject getAndroidStatusBarOptions() {
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject != null && expoClientConfigRootObject.has("androidStatusBar")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString("androidStatusBar");
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble("androidStatusBar"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt("androidStatusBar"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong("androidStatusBar"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean("androidStatusBar"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("androidStatusBar");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject("androidStatusBar");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = expoClientConfigRootObject.get("androidStatusBar");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
            return jSONObject;
        }
        return null;
    }

    public final String getAndroidBackgroundColor() {
        String str;
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        String str2 = null;
        if (expoClientConfigRootObject == null) {
            return null;
        }
        try {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                if (string == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                if (jSONArray == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                if (jSONObject == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                if (obj == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.json.JSONObject");
                }
                jSONObject = (JSONObject) obj;
            }
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                String string2 = jSONObject.getString("backgroundColor");
                if (string2 != null) {
                    return string2;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                return (String) Double.valueOf(jSONObject.getDouble("backgroundColor"));
            } else {
                if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    return (String) Integer.valueOf(jSONObject.getInt("backgroundColor"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    return (String) Long.valueOf(jSONObject.getLong("backgroundColor"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    return (String) Boolean.valueOf(jSONObject.getBoolean("backgroundColor"));
                }
                if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray2 = jSONObject.getJSONArray("backgroundColor");
                    if (jSONArray2 != null) {
                        return (String) jSONArray2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("backgroundColor");
                    if (jSONObject2 != null) {
                        return (String) jSONObject2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                } else {
                    Object obj2 = jSONObject.get("backgroundColor");
                    if (obj2 != null) {
                        return (String) obj2;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
            }
        } catch (JSONException unused) {
            if (expoClientConfigRootObject.has("backgroundColor")) {
                KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(String.class))) {
                    str = expoClientConfigRootObject.getString("backgroundColor");
                    Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("backgroundColor"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("backgroundColor"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    str = (String) Long.valueOf(expoClientConfigRootObject.getLong("backgroundColor"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("backgroundColor"));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray3 = expoClientConfigRootObject.getJSONArray("backgroundColor");
                    Objects.requireNonNull(jSONArray3, "null cannot be cast to non-null type kotlin.String");
                    str = (String) jSONArray3;
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject3 = expoClientConfigRootObject.getJSONObject("backgroundColor");
                    Objects.requireNonNull(jSONObject3, "null cannot be cast to non-null type kotlin.String");
                    str = (String) jSONObject3;
                } else {
                    Object obj3 = expoClientConfigRootObject.get("backgroundColor");
                    Objects.requireNonNull(obj3, "null cannot be cast to non-null type kotlin.String");
                    str = (String) obj3;
                }
                str2 = str;
            }
            return str2;
        }
    }

    public final JSONObject getAndroidNavigationBarOptions() {
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject != null && expoClientConfigRootObject.has("androidNavigationBar")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString("androidNavigationBar");
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble("androidNavigationBar"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt("androidNavigationBar"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong("androidNavigationBar"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean("androidNavigationBar"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("androidNavigationBar");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject("androidNavigationBar");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = expoClientConfigRootObject.get("androidNavigationBar");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
            return jSONObject;
        }
        return null;
    }

    public final String getJsEngine() {
        return (String) this.jsEngine$delegate.getValue();
    }

    public final String getIconUrl() {
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject != null && expoClientConfigRootObject.has("iconUrl")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                str = expoClientConfigRootObject.getString("iconUrl");
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(expoClientConfigRootObject.getDouble("iconUrl"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(expoClientConfigRootObject.getInt("iconUrl"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(expoClientConfigRootObject.getLong("iconUrl"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("iconUrl"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("iconUrl");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("iconUrl");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject;
            } else {
                Object obj = expoClientConfigRootObject.get("iconUrl");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj;
            }
            return str;
        }
        return null;
    }

    public final JSONObject getNotificationPreferences() {
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject != null && expoClientConfigRootObject.has(OneSignalDbContract.NotificationTable.TABLE_NAME)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(OneSignalDbContract.NotificationTable.TABLE_NAME);
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(OneSignalDbContract.NotificationTable.TABLE_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(OneSignalDbContract.NotificationTable.TABLE_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(OneSignalDbContract.NotificationTable.TABLE_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(OneSignalDbContract.NotificationTable.TABLE_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(OneSignalDbContract.NotificationTable.TABLE_NAME);
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(OneSignalDbContract.NotificationTable.TABLE_NAME);
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = expoClientConfigRootObject.get(OneSignalDbContract.NotificationTable.TABLE_NAME);
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
            return jSONObject;
        }
        return null;
    }

    public final JSONObject getAndroidSplashInfo() {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null) {
            return null;
        }
        if (expoClientConfigRootObject.has(DeviceInfo.OS_NAME)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
        } else {
            jSONObject = null;
        }
        if (jSONObject != null && jSONObject.has("splash")) {
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                String string2 = jSONObject.getString("splash");
                Objects.requireNonNull(string2, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject2 = (JSONObject) string2;
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject2 = (JSONObject) Double.valueOf(jSONObject.getDouble("splash"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject2 = (JSONObject) Integer.valueOf(jSONObject.getInt("splash"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject2 = (JSONObject) Long.valueOf(jSONObject.getLong("splash"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject2 = (JSONObject) Boolean.valueOf(jSONObject.getBoolean("splash"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("splash");
                Objects.requireNonNull(jSONArray2, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject2 = (JSONObject) jSONArray2;
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject2 = jSONObject.getJSONObject("splash");
                Objects.requireNonNull(jSONObject2, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj2 = jSONObject.get("splash");
                Objects.requireNonNull(obj2, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject2 = (JSONObject) obj2;
            }
            return jSONObject2;
        }
        return null;
    }

    public final JSONObject getRootSplashInfo() {
        JSONObject jSONObject;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject != null && expoClientConfigRootObject.has("splash")) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString("splash");
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble("splash"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt("splash"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong("splash"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean("splash"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("splash");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject("splash");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = expoClientConfigRootObject.get("splash");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
            return jSONObject;
        }
        return null;
    }

    public final String getAndroidGoogleServicesFile() {
        JSONObject jSONObject;
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null) {
            return null;
        }
        if (expoClientConfigRootObject.has(DeviceInfo.OS_NAME)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
        } else {
            jSONObject = null;
        }
        if (jSONObject != null && jSONObject.has("googleServicesFile")) {
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                str = jSONObject.getString("googleServicesFile");
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(jSONObject.getDouble("googleServicesFile"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(jSONObject.getInt("googleServicesFile"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(jSONObject.getLong("googleServicesFile"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(jSONObject.getBoolean("googleServicesFile"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("googleServicesFile");
                Objects.requireNonNull(jSONArray2, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray2;
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("googleServicesFile");
                Objects.requireNonNull(jSONObject2, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject2;
            } else {
                Object obj2 = jSONObject.get("googleServicesFile");
                Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj2;
            }
            return str;
        }
        return null;
    }

    public final String getAndroidPackageName() {
        JSONObject jSONObject;
        String str;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null) {
            return null;
        }
        if (expoClientConfigRootObject.has(DeviceInfo.OS_NAME)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
        } else {
            jSONObject = null;
        }
        if (jSONObject != null && jSONObject.has("packageName")) {
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                str = jSONObject.getString("packageName");
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(jSONObject.getDouble("packageName"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(jSONObject.getInt("packageName"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(jSONObject.getLong("packageName"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(jSONObject.getBoolean("packageName"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("packageName");
                Objects.requireNonNull(jSONArray2, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray2;
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("packageName");
                Objects.requireNonNull(jSONObject2, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject2;
            } else {
                Object obj2 = jSONObject.get("packageName");
                Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj2;
            }
            return str;
        }
        return null;
    }

    public final boolean shouldUseNextNotificationsApi() {
        JSONObject jSONObject;
        Boolean bool;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        if (expoClientConfigRootObject == null) {
            return false;
        }
        Boolean bool2 = null;
        if (expoClientConfigRootObject.has(DeviceInfo.OS_NAME)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(JSONObject.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                String string = expoClientConfigRootObject.getString(DeviceInfo.OS_NAME);
                Objects.requireNonNull(string, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) string;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                jSONObject = (JSONObject) Double.valueOf(expoClientConfigRootObject.getDouble(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                jSONObject = (JSONObject) Integer.valueOf(expoClientConfigRootObject.getInt(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                jSONObject = (JSONObject) Long.valueOf(expoClientConfigRootObject.getLong(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                jSONObject = (JSONObject) Boolean.valueOf(expoClientConfigRootObject.getBoolean(DeviceInfo.OS_NAME));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray(DeviceInfo.OS_NAME);
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                jSONObject = expoClientConfigRootObject.getJSONObject(DeviceInfo.OS_NAME);
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type org.json.JSONObject");
            } else {
                Object obj = expoClientConfigRootObject.get(DeviceInfo.OS_NAME);
                Objects.requireNonNull(obj, "null cannot be cast to non-null type org.json.JSONObject");
                jSONObject = (JSONObject) obj;
            }
        } else {
            jSONObject = null;
        }
        if (jSONObject == null) {
            return false;
        }
        if (jSONObject.has("useNextNotificationsApi")) {
            KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(Boolean.class);
            if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                String string2 = jSONObject.getString("useNextNotificationsApi");
                Objects.requireNonNull(string2, "null cannot be cast to non-null type kotlin.Boolean");
                bool = (Boolean) string2;
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                bool = (Boolean) Double.valueOf(jSONObject.getDouble("useNextNotificationsApi"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                bool = (Boolean) Integer.valueOf(jSONObject.getInt("useNextNotificationsApi"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                bool = (Boolean) Long.valueOf(jSONObject.getLong("useNextNotificationsApi"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                bool = Boolean.valueOf(jSONObject.getBoolean("useNextNotificationsApi"));
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray2 = jSONObject.getJSONArray("useNextNotificationsApi");
                Objects.requireNonNull(jSONArray2, "null cannot be cast to non-null type kotlin.Boolean");
                bool = (Boolean) jSONArray2;
            } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("useNextNotificationsApi");
                Objects.requireNonNull(jSONObject2, "null cannot be cast to non-null type kotlin.Boolean");
                bool = (Boolean) jSONObject2;
            } else {
                Object obj2 = jSONObject.get("useNextNotificationsApi");
                Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.Boolean");
                bool = (Boolean) obj2;
            }
            bool2 = bool;
        }
        if (bool2 == null) {
            return false;
        }
        return bool2.booleanValue();
    }

    public final String getFacebookAppId() throws JSONException {
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        Intrinsics.checkNotNull(expoClientConfigRootObject);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoClientConfigRootObject.getString("facebookAppId");
            Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
            return string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(expoClientConfigRootObject.getDouble("facebookAppId"));
        } else {
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(expoClientConfigRootObject.getInt("facebookAppId"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(expoClientConfigRootObject.getLong("facebookAppId"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("facebookAppId"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("facebookAppId");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("facebookAppId");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONObject;
            } else {
                Object obj = expoClientConfigRootObject.get("facebookAppId");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                return (String) obj;
            }
        }
    }

    public final String getFacebookApplicationName() throws JSONException {
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        Intrinsics.checkNotNull(expoClientConfigRootObject);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoClientConfigRootObject.getString("facebookDisplayName");
            Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.String");
            return string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            return (String) Double.valueOf(expoClientConfigRootObject.getDouble("facebookDisplayName"));
        } else {
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                return (String) Integer.valueOf(expoClientConfigRootObject.getInt("facebookDisplayName"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                return (String) Long.valueOf(expoClientConfigRootObject.getLong("facebookDisplayName"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                return (String) Boolean.valueOf(expoClientConfigRootObject.getBoolean("facebookDisplayName"));
            }
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("facebookDisplayName");
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("facebookDisplayName");
                Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.String");
                return (String) jSONObject;
            } else {
                Object obj = expoClientConfigRootObject.get("facebookDisplayName");
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                return (String) obj;
            }
        }
    }

    public final boolean getFacebookAutoInitEnabled() throws JSONException {
        Boolean bool;
        JSONObject expoClientConfigRootObject = getExpoClientConfigRootObject();
        Intrinsics.checkNotNull(expoClientConfigRootObject);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Boolean.class);
        if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
            String string = expoClientConfigRootObject.getString("facebookAutoInitEnabled");
            Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.Boolean");
            bool = (Boolean) string;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
            bool = (Boolean) Double.valueOf(expoClientConfigRootObject.getDouble("facebookAutoInitEnabled"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            bool = (Boolean) Integer.valueOf(expoClientConfigRootObject.getInt("facebookAutoInitEnabled"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            bool = (Boolean) Long.valueOf(expoClientConfigRootObject.getLong("facebookAutoInitEnabled"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            bool = Boolean.valueOf(expoClientConfigRootObject.getBoolean("facebookAutoInitEnabled"));
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
            JSONArray jSONArray = expoClientConfigRootObject.getJSONArray("facebookAutoInitEnabled");
            Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.Boolean");
            bool = (Boolean) jSONArray;
        } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
            JSONObject jSONObject = expoClientConfigRootObject.getJSONObject("facebookAutoInitEnabled");
            Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.Boolean");
            bool = (Boolean) jSONObject;
        } else {
            Object obj = expoClientConfigRootObject.get("facebookAutoInitEnabled");
            Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Boolean");
            bool = (Boolean) obj;
        }
        return bool.booleanValue();
    }

    /* compiled from: Manifest.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/manifests/core/Manifest$Companion;", "", "()V", "fromManifestJson", "Lexpo/modules/manifests/core/Manifest;", "manifestJson", "Lorg/json/JSONObject;", "expo-manifests_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Manifest fromManifestJson(JSONObject manifestJson) {
            Intrinsics.checkNotNullParameter(manifestJson, "manifestJson");
            if (manifestJson.has("releaseId")) {
                return new LegacyManifest(manifestJson);
            }
            if (manifestJson.has(TtmlNode.TAG_METADATA)) {
                return new NewManifest(manifestJson);
            }
            return new BareManifest(manifestJson);
        }
    }
}
