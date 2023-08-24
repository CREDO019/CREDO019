package expo.modules.updates.selectionpolicy;

import expo.modules.updates.p021db.entity.UpdateEntity;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: ReaperSelectionPolicyFilterAware.kt */
@Metadata(m184d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, m183d2 = {"Lexpo/modules/updates/selectionpolicy/ReaperSelectionPolicyFilterAware;", "Lexpo/modules/updates/selectionpolicy/ReaperSelectionPolicy;", "()V", "selectUpdatesToDelete", "", "Lexpo/modules/updates/db/entity/UpdateEntity;", "updates", "launchedUpdate", "filters", "Lorg/json/JSONObject;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class ReaperSelectionPolicyFilterAware implements ReaperSelectionPolicy {
    @Override // expo.modules.updates.selectionpolicy.ReaperSelectionPolicy
    public List<UpdateEntity> selectUpdatesToDelete(List<UpdateEntity> updates, UpdateEntity updateEntity, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(updates, "updates");
        if (updateEntity == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        UpdateEntity updateEntity2 = null;
        UpdateEntity updateEntity3 = null;
        for (UpdateEntity updateEntity4 : updates) {
            if (Intrinsics.areEqual(updateEntity4.getScopeKey(), updateEntity.getScopeKey()) && updateEntity4.getCommitTime().before(updateEntity.getCommitTime())) {
                arrayList.add(updateEntity4);
                if (updateEntity3 == null || updateEntity3.getCommitTime().before(updateEntity4.getCommitTime())) {
                    updateEntity3 = updateEntity4;
                }
                if (SelectionPolicies.INSTANCE.matchesFilters(updateEntity4, jSONObject) && (updateEntity2 == null || updateEntity2.getCommitTime().before(updateEntity4.getCommitTime()))) {
                    updateEntity2 = updateEntity4;
                }
            }
        }
        if (updateEntity2 != null) {
            arrayList.remove(updateEntity2);
        } else if (updateEntity3 != null) {
            arrayList.remove(updateEntity3);
        }
        return arrayList;
    }
}
