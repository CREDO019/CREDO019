package expo.modules.updates.selectionpolicy;

import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.p021db.entity.UpdateEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: LauncherSelectionPolicyFilterAware.kt */
@Metadata(m184d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0013\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\u0002\u0010\u0007J\"\u0010\b\u001a\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m183d2 = {"Lexpo/modules/updates/selectionpolicy/LauncherSelectionPolicyFilterAware;", "Lexpo/modules/updates/selectionpolicy/LauncherSelectionPolicy;", UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY, "", "(Ljava/lang/String;)V", "runtimeVersions", "", "(Ljava/util/List;)V", "selectUpdateToLaunch", "Lexpo/modules/updates/db/entity/UpdateEntity;", "updates", "filters", "Lorg/json/JSONObject;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class LauncherSelectionPolicyFilterAware implements LauncherSelectionPolicy {
    private final List<String> runtimeVersions;

    public LauncherSelectionPolicyFilterAware(List<String> runtimeVersions) {
        Intrinsics.checkNotNullParameter(runtimeVersions, "runtimeVersions");
        this.runtimeVersions = runtimeVersions;
    }

    /* JADX WARN: 'thıs' call moved to the top of the method (can break code semantics) */
    public LauncherSelectionPolicyFilterAware(String runtimeVersion) {
        this(CollectionsKt.listOf(runtimeVersion));
        Intrinsics.checkNotNullParameter(runtimeVersion, "runtimeVersion");
    }

    @Override // expo.modules.updates.selectionpolicy.LauncherSelectionPolicy
    public UpdateEntity selectUpdateToLaunch(List<UpdateEntity> updates, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(updates, "updates");
        UpdateEntity updateEntity = null;
        for (UpdateEntity updateEntity2 : updates) {
            if (this.runtimeVersions.contains(updateEntity2.getRuntimeVersion()) && SelectionPolicies.INSTANCE.matchesFilters(updateEntity2, jSONObject) && (updateEntity == null || updateEntity.getCommitTime().before(updateEntity2.getCommitTime()))) {
                updateEntity = updateEntity2;
            }
        }
        return updateEntity;
    }
}
