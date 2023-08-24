package expo.modules.updates.manifest;

import android.util.Log;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.p021db.UpdatesDatabase;
import expo.modules.updates.p021db.dao.JSONDataDao;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: ManifestMetadata.kt */
@Metadata(m184d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u000f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u001a\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u001e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m183d2 = {"Lexpo/modules/updates/manifest/ManifestMetadata;", "", "()V", "MANIFEST_FILTERS_KEY", "", "MANIFEST_SERVER_DEFINED_HEADERS_KEY", "TAG", "kotlin.jvm.PlatformType", "getJSONObject", "Lorg/json/JSONObject;", "key", "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "getManifestFilters", "getServerDefinedHeaders", "saveMetadata", "", "updateManifest", "Lexpo/modules/updates/manifest/UpdateManifest;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class ManifestMetadata {
    private static final String MANIFEST_FILTERS_KEY = "manifestFilters";
    private static final String MANIFEST_SERVER_DEFINED_HEADERS_KEY = "serverDefinedHeaders";
    public static final ManifestMetadata INSTANCE = new ManifestMetadata();
    private static final String TAG = "ManifestMetadata";

    private ManifestMetadata() {
    }

    private final JSONObject getJSONObject(String str, UpdatesDatabase updatesDatabase, UpdatesConfiguration updatesConfiguration) {
        try {
            JSONDataDao jsonDataDao = updatesDatabase.jsonDataDao();
            Intrinsics.checkNotNull(jsonDataDao);
            String scopeKey = updatesConfiguration.getScopeKey();
            Intrinsics.checkNotNull(scopeKey);
            String loadJSONStringForKey = jsonDataDao.loadJSONStringForKey(str, scopeKey);
            if (loadJSONStringForKey != null) {
                return new JSONObject(loadJSONStringForKey);
            }
            return null;
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "Error retrieving " + str + " from database", e);
            return null;
        }
    }

    @JvmStatic
    public static final JSONObject getServerDefinedHeaders(UpdatesDatabase database, UpdatesConfiguration configuration) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return INSTANCE.getJSONObject(MANIFEST_SERVER_DEFINED_HEADERS_KEY, database, configuration);
    }

    public final JSONObject getManifestFilters(UpdatesDatabase database, UpdatesConfiguration configuration) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return getJSONObject(MANIFEST_FILTERS_KEY, database, configuration);
    }

    public final void saveMetadata(UpdateManifest updateManifest, UpdatesDatabase database, UpdatesConfiguration configuration) {
        Intrinsics.checkNotNullParameter(updateManifest, "updateManifest");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (updateManifest.getServerDefinedHeaders() != null) {
            linkedHashMap.put(MANIFEST_SERVER_DEFINED_HEADERS_KEY, String.valueOf(updateManifest.getServerDefinedHeaders()));
        }
        if (updateManifest.getManifestFilters() != null) {
            linkedHashMap.put(MANIFEST_FILTERS_KEY, String.valueOf(updateManifest.getManifestFilters()));
        }
        if (!linkedHashMap.isEmpty()) {
            JSONDataDao jsonDataDao = database.jsonDataDao();
            Intrinsics.checkNotNull(jsonDataDao);
            String scopeKey = configuration.getScopeKey();
            Intrinsics.checkNotNull(scopeKey);
            jsonDataDao.setMultipleFields(linkedHashMap, scopeKey);
        }
    }
}
