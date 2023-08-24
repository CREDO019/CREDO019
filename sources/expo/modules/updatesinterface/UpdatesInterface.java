package expo.modules.updatesinterface;

import android.content.Context;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public interface UpdatesInterface {

    /* loaded from: classes5.dex */
    public interface QueryCallback {
        void onFailure(Exception exc);

        void onSuccess(List<UUID> list);
    }

    /* loaded from: classes5.dex */
    public interface Update {
        String getLaunchAssetPath();

        JSONObject getManifest();
    }

    /* loaded from: classes5.dex */
    public interface UpdateCallback {
        void onFailure(Exception exc);

        boolean onManifestLoaded(JSONObject jSONObject);

        void onProgress(int r1, int r2, int r3);

        void onSuccess(Update update);
    }

    void fetchUpdateWithConfiguration(HashMap<String, Object> hashMap, Context context, UpdateCallback updateCallback);

    void reset();

    void storedUpdateIdsWithConfiguration(HashMap<String, Object> hashMap, Context context, QueryCallback queryCallback);
}
