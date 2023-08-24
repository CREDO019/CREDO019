package expo.modules.updates.selectionpolicy;

import expo.modules.updates.p021db.entity.UpdateEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: ReaperSelectionPolicyDevelopmentClient.kt */
@Metadata(m184d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J0\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, m183d2 = {"Lexpo/modules/updates/selectionpolicy/ReaperSelectionPolicyDevelopmentClient;", "Lexpo/modules/updates/selectionpolicy/ReaperSelectionPolicy;", "maxUpdatesToKeep", "", "(I)V", "selectUpdatesToDelete", "", "Lexpo/modules/updates/db/entity/UpdateEntity;", "updates", "launchedUpdate", "filters", "Lorg/json/JSONObject;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class ReaperSelectionPolicyDevelopmentClient implements ReaperSelectionPolicy {
    private final int maxUpdatesToKeep;

    public ReaperSelectionPolicyDevelopmentClient() {
        this(0, 1, null);
    }

    public ReaperSelectionPolicyDevelopmentClient(int r2) {
        this.maxUpdatesToKeep = r2;
        if (r2 <= 0) {
            throw new AssertionError("Cannot initialize ReaperSelectionPolicyDevelopmentClient with maxUpdatesToKeep <= 0");
        }
    }

    public /* synthetic */ ReaperSelectionPolicyDevelopmentClient(int r1, int r2, DefaultConstructorMarker defaultConstructorMarker) {
        this((r2 & 1) != 0 ? 10 : r1);
    }

    @Override // expo.modules.updates.selectionpolicy.ReaperSelectionPolicy
    public List<UpdateEntity> selectUpdatesToDelete(List<UpdateEntity> updates, UpdateEntity updateEntity, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(updates, "updates");
        if (updateEntity == null || updates.size() <= this.maxUpdatesToKeep) {
            return new ArrayList();
        }
        List mutableList = CollectionsKt.toMutableList((Collection) updates);
        CollectionsKt.sortWith(mutableList, new Comparator() { // from class: expo.modules.updates.selectionpolicy.ReaperSelectionPolicyDevelopmentClient$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int m1739selectUpdatesToDelete$lambda0;
                m1739selectUpdatesToDelete$lambda0 = ReaperSelectionPolicyDevelopmentClient.m1739selectUpdatesToDelete$lambda0((UpdateEntity) obj, (UpdateEntity) obj2);
                return m1739selectUpdatesToDelete$lambda0;
            }
        });
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (mutableList.size() > this.maxUpdatesToKeep) {
            UpdateEntity updateEntity2 = (UpdateEntity) mutableList.remove(0);
            if (!Intrinsics.areEqual(updateEntity2.getId(), updateEntity.getId())) {
                arrayList.add(updateEntity2);
            } else if (z) {
                throw new AssertionError("Multiple updates with the same ID were passed into ReaperSelectionPolicyDevelopmentClient");
            } else {
                mutableList.add(updateEntity2);
                z = true;
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: selectUpdatesToDelete$lambda-0  reason: not valid java name */
    public static final int m1739selectUpdatesToDelete$lambda0(UpdateEntity u1, UpdateEntity u2) {
        Intrinsics.checkNotNullParameter(u1, "u1");
        Intrinsics.checkNotNullParameter(u2, "u2");
        int compareTo = u1.getLastAccessed().compareTo(u2.getLastAccessed());
        return compareTo == 0 ? u1.getCommitTime().compareTo(u2.getCommitTime()) : compareTo;
    }
}
