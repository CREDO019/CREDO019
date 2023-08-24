package expo.modules.updates.p021db;

import android.net.Uri;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.p021db.dao.JSONDataDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: BuildData.kt */
@Metadata(m184d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0004J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\rJ\u0016\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, m183d2 = {"Lexpo/modules/updates/db/BuildData;", "", "()V", "staticBuildDataKey", "", "clearAllUpdatesFromDatabase", "", "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "ensureBuildDataIsConsistent", "updatesConfiguration", "Lexpo/modules/updates/UpdatesConfiguration;", "getBuildDataFromConfig", "Lorg/json/JSONObject;", "getBuildDataFromDatabase", UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY, "isBuildDataConsistent", "", "databaseBuildData", "setBuildDataInDatabase", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.updates.db.BuildData */
/* loaded from: classes5.dex */
public final class BuildData {
    public static final BuildData INSTANCE = new BuildData();
    private static String staticBuildDataKey = "staticBuildData";

    private BuildData() {
    }

    public final void ensureBuildDataIsConsistent(UpdatesConfiguration updatesConfiguration, UpdatesDatabase database) {
        Intrinsics.checkNotNullParameter(updatesConfiguration, "updatesConfiguration");
        Intrinsics.checkNotNullParameter(database, "database");
        String scopeKey = updatesConfiguration.getScopeKey();
        if (scopeKey == null) {
            throw new AssertionError("expo-updates is enabled, but no valid URL is configured in AndroidManifest.xml. If you are making a release build for the first time, make sure you have run `expo publish` at least once.");
        }
        JSONObject buildDataFromDatabase = getBuildDataFromDatabase(database, scopeKey);
        if (buildDataFromDatabase == null) {
            setBuildDataInDatabase(database, updatesConfiguration);
        } else if (isBuildDataConsistent(updatesConfiguration, buildDataFromDatabase)) {
        } else {
            clearAllUpdatesFromDatabase(database);
            setBuildDataInDatabase(database, updatesConfiguration);
        }
    }

    public final void clearAllUpdatesFromDatabase(UpdatesDatabase database) {
        Intrinsics.checkNotNullParameter(database, "database");
        database.updateDao().deleteUpdates(database.updateDao().loadAllUpdates());
    }

    public final boolean isBuildDataConsistent(UpdatesConfiguration updatesConfiguration, JSONObject databaseBuildData) {
        String str;
        String str2;
        JSONObject jSONObject;
        String str3;
        JSONObject jSONObject2;
        Intrinsics.checkNotNullParameter(updatesConfiguration, "updatesConfiguration");
        Intrinsics.checkNotNullParameter(databaseBuildData, "databaseBuildData");
        JSONObject buildDataFromConfig = getBuildDataFromConfig(updatesConfiguration);
        ArrayList arrayList = new ArrayList();
        if (databaseBuildData.has(UpdatesConfiguration.UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY)) {
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
            if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(String.class))) {
                str = databaseBuildData.getString(UpdatesConfiguration.UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY);
                Objects.requireNonNull(str, "null cannot be cast to non-null type kotlin.String");
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                str = (String) Double.valueOf(databaseBuildData.getDouble(UpdatesConfiguration.UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                str = (String) Integer.valueOf(databaseBuildData.getInt(UpdatesConfiguration.UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                str = (String) Long.valueOf(databaseBuildData.getLong(UpdatesConfiguration.UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                str = (String) Boolean.valueOf(databaseBuildData.getBoolean(UpdatesConfiguration.UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY));
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                JSONArray jSONArray = databaseBuildData.getJSONArray(UpdatesConfiguration.UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY);
                Objects.requireNonNull(jSONArray, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONArray;
            } else if (Intrinsics.areEqual(orCreateKotlinClass, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                JSONObject jSONObject3 = databaseBuildData.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY);
                Objects.requireNonNull(jSONObject3, "null cannot be cast to non-null type kotlin.String");
                str = (String) jSONObject3;
            } else {
                Object obj = databaseBuildData.get(UpdatesConfiguration.UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY);
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                str = (String) obj;
            }
        } else {
            str = null;
        }
        arrayList.add(Boolean.valueOf(Intrinsics.areEqual(str, buildDataFromConfig.get(UpdatesConfiguration.UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY))));
        arrayList.add(Boolean.valueOf(Intrinsics.areEqual(Uri.parse(databaseBuildData.get(UpdatesConfiguration.UPDATES_CONFIGURATION_UPDATE_URL_KEY).toString()), buildDataFromConfig.get(UpdatesConfiguration.UPDATES_CONFIGURATION_UPDATE_URL_KEY))));
        Iterator<String> keys = buildDataFromConfig.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY).keys();
        Intrinsics.checkNotNullExpressionValue(keys, "configBuildData.getJSONO…requestHeadersKey).keys()");
        while (keys.hasNext()) {
            String key = keys.next();
            JSONObject jSONObject4 = databaseBuildData.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY);
            Intrinsics.checkNotNullExpressionValue(jSONObject4, "databaseBuildData.getJSONObject(requestHeadersKey)");
            Intrinsics.checkNotNullExpressionValue(key, "key");
            if (jSONObject4.has(key)) {
                KClass orCreateKotlinClass2 = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(String.class))) {
                    str3 = jSONObject4.getString(key);
                    Objects.requireNonNull(str3, "null cannot be cast to non-null type kotlin.String");
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    str3 = (String) Double.valueOf(jSONObject4.getDouble(key));
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    str3 = (String) Integer.valueOf(jSONObject4.getInt(key));
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    str3 = (String) Long.valueOf(jSONObject4.getLong(key));
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    str3 = (String) Boolean.valueOf(jSONObject4.getBoolean(key));
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray2 = jSONObject4.getJSONArray(key);
                    Objects.requireNonNull(jSONArray2, "null cannot be cast to non-null type kotlin.String");
                    str3 = (String) jSONArray2;
                } else if (Intrinsics.areEqual(orCreateKotlinClass2, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject5 = jSONObject4.getJSONObject(key);
                    Objects.requireNonNull(jSONObject5, "null cannot be cast to non-null type kotlin.String");
                    str3 = (String) jSONObject5;
                } else {
                    Object obj2 = jSONObject4.get(key);
                    Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.String");
                    str3 = (String) obj2;
                }
            } else {
                str3 = null;
            }
            JSONObject jSONObject6 = buildDataFromConfig.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY);
            Intrinsics.checkNotNullExpressionValue(jSONObject6, "configBuildData.getJSONObject(requestHeadersKey)");
            if (jSONObject6.has(key)) {
                KClass orCreateKotlinClass3 = Reflection.getOrCreateKotlinClass(Object.class);
                if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(String.class))) {
                    String string = jSONObject6.getString(key);
                    Objects.requireNonNull(string, "null cannot be cast to non-null type kotlin.Any");
                    jSONObject2 = string;
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    jSONObject2 = Double.valueOf(jSONObject6.getDouble(key));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    jSONObject2 = Integer.valueOf(jSONObject6.getInt(key));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    jSONObject2 = Long.valueOf(jSONObject6.getLong(key));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    jSONObject2 = Boolean.valueOf(jSONObject6.getBoolean(key));
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray3 = jSONObject6.getJSONArray(key);
                    Objects.requireNonNull(jSONArray3, "null cannot be cast to non-null type kotlin.Any");
                    jSONObject2 = jSONArray3;
                } else if (Intrinsics.areEqual(orCreateKotlinClass3, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject7 = jSONObject6.getJSONObject(key);
                    Objects.requireNonNull(jSONObject7, "null cannot be cast to non-null type kotlin.Any");
                    jSONObject2 = jSONObject7;
                } else {
                    jSONObject2 = jSONObject6.get(key);
                    Objects.requireNonNull(jSONObject2, "null cannot be cast to non-null type kotlin.Any");
                }
            } else {
                jSONObject2 = null;
            }
            arrayList.add(Boolean.valueOf(Intrinsics.areEqual(str3, jSONObject2)));
        }
        Iterator<String> keys2 = databaseBuildData.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY).keys();
        Intrinsics.checkNotNullExpressionValue(keys2, "databaseBuildData.getJSO…requestHeadersKey).keys()");
        while (keys2.hasNext()) {
            String key2 = keys2.next();
            JSONObject jSONObject8 = databaseBuildData.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY);
            Intrinsics.checkNotNullExpressionValue(jSONObject8, "databaseBuildData.getJSONObject(requestHeadersKey)");
            Intrinsics.checkNotNullExpressionValue(key2, "key");
            if (jSONObject8.has(key2)) {
                KClass orCreateKotlinClass4 = Reflection.getOrCreateKotlinClass(String.class);
                if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(String.class))) {
                    str2 = jSONObject8.getString(key2);
                    Objects.requireNonNull(str2, "null cannot be cast to non-null type kotlin.String");
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    str2 = (String) Double.valueOf(jSONObject8.getDouble(key2));
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    str2 = (String) Integer.valueOf(jSONObject8.getInt(key2));
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    str2 = (String) Long.valueOf(jSONObject8.getLong(key2));
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    str2 = (String) Boolean.valueOf(jSONObject8.getBoolean(key2));
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray4 = jSONObject8.getJSONArray(key2);
                    Objects.requireNonNull(jSONArray4, "null cannot be cast to non-null type kotlin.String");
                    str2 = (String) jSONArray4;
                } else if (Intrinsics.areEqual(orCreateKotlinClass4, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject9 = jSONObject8.getJSONObject(key2);
                    Objects.requireNonNull(jSONObject9, "null cannot be cast to non-null type kotlin.String");
                    str2 = (String) jSONObject9;
                } else {
                    Object obj3 = jSONObject8.get(key2);
                    Objects.requireNonNull(obj3, "null cannot be cast to non-null type kotlin.String");
                    str2 = (String) obj3;
                }
            } else {
                str2 = null;
            }
            JSONObject jSONObject10 = buildDataFromConfig.getJSONObject(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY);
            Intrinsics.checkNotNullExpressionValue(jSONObject10, "configBuildData.getJSONObject(requestHeadersKey)");
            if (jSONObject10.has(key2)) {
                KClass orCreateKotlinClass5 = Reflection.getOrCreateKotlinClass(Object.class);
                if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(String.class))) {
                    String string2 = jSONObject10.getString(key2);
                    Objects.requireNonNull(string2, "null cannot be cast to non-null type kotlin.Any");
                    jSONObject = string2;
                } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Double.TYPE))) {
                    jSONObject = Double.valueOf(jSONObject10.getDouble(key2));
                } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
                    jSONObject = Integer.valueOf(jSONObject10.getInt(key2));
                } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Long.TYPE))) {
                    jSONObject = Long.valueOf(jSONObject10.getLong(key2));
                } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
                    jSONObject = Boolean.valueOf(jSONObject10.getBoolean(key2));
                } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(JSONArray.class))) {
                    JSONArray jSONArray5 = jSONObject10.getJSONArray(key2);
                    Objects.requireNonNull(jSONArray5, "null cannot be cast to non-null type kotlin.Any");
                    jSONObject = jSONArray5;
                } else if (Intrinsics.areEqual(orCreateKotlinClass5, Reflection.getOrCreateKotlinClass(JSONObject.class))) {
                    JSONObject jSONObject11 = jSONObject10.getJSONObject(key2);
                    Objects.requireNonNull(jSONObject11, "null cannot be cast to non-null type kotlin.Any");
                    jSONObject = jSONObject11;
                } else {
                    jSONObject = jSONObject10.get(key2);
                    Objects.requireNonNull(jSONObject, "null cannot be cast to non-null type kotlin.Any");
                }
            } else {
                jSONObject = null;
            }
            arrayList.add(Boolean.valueOf(Intrinsics.areEqual(str2, jSONObject)));
        }
        ArrayList<Boolean> arrayList2 = arrayList;
        if ((arrayList2 instanceof Collection) && arrayList2.isEmpty()) {
            return true;
        }
        for (Boolean bool : arrayList2) {
            if (!bool.booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public final void setBuildDataInDatabase(UpdatesDatabase database, UpdatesConfiguration updatesConfiguration) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(updatesConfiguration, "updatesConfiguration");
        JSONObject buildDataFromConfig = getBuildDataFromConfig(updatesConfiguration);
        JSONDataDao jsonDataDao = database.jsonDataDao();
        if (jsonDataDao == null) {
            return;
        }
        String str = staticBuildDataKey;
        String jSONObject = buildDataFromConfig.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "buildDataJSON.toString()");
        String scopeKey = updatesConfiguration.getScopeKey();
        Objects.requireNonNull(scopeKey, "null cannot be cast to non-null type kotlin.String");
        jsonDataDao.setJSONStringForKey(str, jSONObject, scopeKey);
    }

    public final JSONObject getBuildDataFromDatabase(UpdatesDatabase database, String scopeKey) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(scopeKey, "scopeKey");
        JSONDataDao jsonDataDao = database.jsonDataDao();
        String loadJSONStringForKey = jsonDataDao == null ? null : jsonDataDao.loadJSONStringForKey(staticBuildDataKey, scopeKey);
        if (loadJSONStringForKey == null) {
            return null;
        }
        return new JSONObject(loadJSONStringForKey);
    }

    private final JSONObject getBuildDataFromConfig(UpdatesConfiguration updatesConfiguration) {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, String> entry : updatesConfiguration.getRequestHeaders().entrySet()) {
            jSONObject.put(entry.getKey(), entry.getValue());
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(UpdatesConfiguration.UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY, updatesConfiguration.getReleaseChannel());
        jSONObject2.put(UpdatesConfiguration.UPDATES_CONFIGURATION_UPDATE_URL_KEY, updatesConfiguration.getUpdateUrl());
        jSONObject2.put(UpdatesConfiguration.UPDATES_CONFIGURATION_REQUEST_HEADERS_KEY, jSONObject);
        return jSONObject2;
    }
}
