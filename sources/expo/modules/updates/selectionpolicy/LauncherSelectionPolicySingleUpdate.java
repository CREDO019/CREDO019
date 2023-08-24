package expo.modules.updates.selectionpolicy;

import expo.modules.updates.p021db.entity.UpdateEntity;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: LauncherSelectionPolicySingleUpdate.kt */
@Metadata(m184d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\"\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m183d2 = {"Lexpo/modules/updates/selectionpolicy/LauncherSelectionPolicySingleUpdate;", "Lexpo/modules/updates/selectionpolicy/LauncherSelectionPolicy;", "updateID", "Ljava/util/UUID;", "(Ljava/util/UUID;)V", "selectUpdateToLaunch", "Lexpo/modules/updates/db/entity/UpdateEntity;", "updates", "", "filters", "Lorg/json/JSONObject;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class LauncherSelectionPolicySingleUpdate implements LauncherSelectionPolicy {
    private final UUID updateID;

    public LauncherSelectionPolicySingleUpdate(UUID updateID) {
        Intrinsics.checkNotNullParameter(updateID, "updateID");
        this.updateID = updateID;
    }

    @Override // expo.modules.updates.selectionpolicy.LauncherSelectionPolicy
    public UpdateEntity selectUpdateToLaunch(List<UpdateEntity> updates, JSONObject jSONObject) {
        Object obj;
        Intrinsics.checkNotNullParameter(updates, "updates");
        Iterator<T> it = updates.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((UpdateEntity) obj).getId(), this.updateID)) {
                break;
            }
        }
        return (UpdateEntity) obj;
    }
}
