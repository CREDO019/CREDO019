package expo.modules.updates.selectionpolicy;

import expo.modules.updates.p021db.entity.UpdateEntity;
import kotlin.Metadata;
import org.json.JSONObject;

/* compiled from: LoaderSelectionPolicyFilterAware.kt */
@Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, m183d2 = {"Lexpo/modules/updates/selectionpolicy/LoaderSelectionPolicyFilterAware;", "Lexpo/modules/updates/selectionpolicy/LoaderSelectionPolicy;", "()V", "shouldLoadNewUpdate", "", "newUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "launchedUpdate", "filters", "Lorg/json/JSONObject;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class LoaderSelectionPolicyFilterAware implements LoaderSelectionPolicy {
    @Override // expo.modules.updates.selectionpolicy.LoaderSelectionPolicy
    public boolean shouldLoadNewUpdate(UpdateEntity updateEntity, UpdateEntity updateEntity2, JSONObject jSONObject) {
        if (updateEntity != null && SelectionPolicies.INSTANCE.matchesFilters(updateEntity, jSONObject)) {
            if (updateEntity2 != null && SelectionPolicies.INSTANCE.matchesFilters(updateEntity2, jSONObject)) {
                return updateEntity.getCommitTime().after(updateEntity2.getCommitTime());
            }
            return true;
        }
        return false;
    }
}
