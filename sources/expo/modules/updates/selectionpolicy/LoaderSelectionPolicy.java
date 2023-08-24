package expo.modules.updates.selectionpolicy;

import expo.modules.updates.p021db.entity.UpdateEntity;
import kotlin.Metadata;
import org.json.JSONObject;

/* compiled from: LoaderSelectionPolicy.kt */
@Metadata(m184d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¨\u0006\t"}, m183d2 = {"Lexpo/modules/updates/selectionpolicy/LoaderSelectionPolicy;", "", "shouldLoadNewUpdate", "", "newUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "launchedUpdate", "filters", "Lorg/json/JSONObject;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public interface LoaderSelectionPolicy {
    boolean shouldLoadNewUpdate(UpdateEntity updateEntity, UpdateEntity updateEntity2, JSONObject jSONObject);
}
