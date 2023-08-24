package expo.modules.updates.selectionpolicy;

import android.util.Log;
import expo.modules.manifests.core.Manifest;
import expo.modules.updates.p021db.entity.UpdateEntity;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: SelectionPolicies.kt */
@Metadata(m184d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rR\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, m183d2 = {"Lexpo/modules/updates/selectionpolicy/SelectionPolicies;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "matchesFilters", "", "update", "Lexpo/modules/updates/db/entity/UpdateEntity;", "manifestFilters", "Lorg/json/JSONObject;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class SelectionPolicies {
    public static final SelectionPolicies INSTANCE = new SelectionPolicies();
    private static final String TAG = "SelectionPolicies";

    private SelectionPolicies() {
    }

    public final String getTAG() {
        return TAG;
    }

    public final boolean matchesFilters(UpdateEntity update, JSONObject jSONObject) {
        JSONObject metadata;
        Intrinsics.checkNotNullParameter(update, "update");
        JSONObject manifest = update.getManifest();
        if (jSONObject == null || manifest == null || (metadata = Manifest.Companion.fromManifestJson(manifest).getMetadata()) == null) {
            return true;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            Iterator<String> keys = metadata.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Intrinsics.checkNotNullExpressionValue(key, "key");
                String lowerCase = key.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                jSONObject2.put(lowerCase, metadata.get(key));
            }
            Iterator<String> keys2 = jSONObject.keys();
            while (keys2.hasNext()) {
                String next = keys2.next();
                if (jSONObject2.has(next) && !Intrinsics.areEqual(jSONObject.get(next), jSONObject2.get(next))) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error filtering manifest using server data", e);
            return true;
        }
    }
}
