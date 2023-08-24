package expo.modules.updates.p021db.dao;

import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import expo.modules.updates.p021db.enums.UpdateStatus;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdateDao.kt */
@Metadata(m184d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0005\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H'J&\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\nH'J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u0018\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0006H'J\u001e\u0010\u0013\u001a\u00020\u00042\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\n2\u0006\u0010\u0012\u001a\u00020\u000fH'J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000bH'J\u0016\u0010\u0018\u001a\u00020\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH'J\u000e\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000bJ\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000bJ\u0010\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000bH'J\u0016\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u0006\u0010\u0012\u001a\u00020\u000fH'J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH'J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0012\u001a\u00020\u000fH'J\u000e\u0010 \u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0010\u0010\"\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010#\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000bJ\u000e\u0010$\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000bJ\u0018\u0010$\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020&H\u0017J\u0014\u0010'\u001a\u00020\u00042\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\b0\nJ\u0016\u0010)\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\r¨\u0006+"}, m183d2 = {"Lexpo/modules/updates/db/dao/UpdateDao;", "", "()V", "_keepUpdate", "", "id", "Ljava/util/UUID;", "_loadLaunchAsset", "Lexpo/modules/updates/db/entity/AssetEntity;", "_loadLaunchableUpdatesForProjectWithStatuses", "", "Lexpo/modules/updates/db/entity/UpdateEntity;", UpdatesConfiguration.UPDATES_CONFIGURATION_SCOPE_KEY_KEY, "", "statuses", "Lexpo/modules/updates/db/enums/UpdateStatus;", "_loadUpdatesWithId", "_markUpdateWithStatus", "status", "_markUpdatesWithMissingAssets", "missingAssetIds", "", "_updateUpdate", "update", "deleteUpdates", "updates", "incrementFailedLaunchCount", "incrementSuccessfulLaunchCount", "insertUpdate", "loadAllUpdateIdsWithStatus", "loadAllUpdates", "loadAllUpdatesWithStatus", "loadLaunchAsset", "loadLaunchableUpdatesForScope", "loadUpdateWithId", "markUpdateAccessed", "markUpdateFinished", "hasSkippedEmbeddedAssets", "", "markUpdatesWithMissingAssets", "missingAssets", "setUpdateScopeKey", "newScopeKey", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.updates.db.dao.UpdateDao */
/* loaded from: classes5.dex */
public abstract class UpdateDao {
    public abstract void _keepUpdate(UUID r1);

    public abstract AssetEntity _loadLaunchAsset(UUID r1);

    public abstract List<UpdateEntity> _loadLaunchableUpdatesForProjectWithStatuses(String str, List<? extends UpdateStatus> list);

    public abstract List<UpdateEntity> _loadUpdatesWithId(UUID r1);

    public abstract void _markUpdateWithStatus(UpdateStatus updateStatus, UUID r2);

    public abstract void _markUpdatesWithMissingAssets(List<Long> list, UpdateStatus updateStatus);

    public abstract void _updateUpdate(UpdateEntity updateEntity);

    public abstract void deleteUpdates(List<UpdateEntity> list);

    public abstract void insertUpdate(UpdateEntity updateEntity);

    public abstract List<UUID> loadAllUpdateIdsWithStatus(UpdateStatus updateStatus);

    public abstract List<UpdateEntity> loadAllUpdates();

    public abstract List<UpdateEntity> loadAllUpdatesWithStatus(UpdateStatus updateStatus);

    public final List<UpdateEntity> loadLaunchableUpdatesForScope(String str) {
        return _loadLaunchableUpdatesForProjectWithStatuses(str, CollectionsKt.listOf((Object[]) new UpdateStatus[]{UpdateStatus.READY, UpdateStatus.EMBEDDED, UpdateStatus.DEVELOPMENT}));
    }

    public final UpdateEntity loadUpdateWithId(UUID id) {
        Intrinsics.checkNotNullParameter(id, "id");
        List<UpdateEntity> _loadUpdatesWithId = _loadUpdatesWithId(id);
        if (!_loadUpdatesWithId.isEmpty()) {
            return _loadUpdatesWithId.get(0);
        }
        return null;
    }

    public final AssetEntity loadLaunchAsset(UUID id) {
        Intrinsics.checkNotNullParameter(id, "id");
        AssetEntity _loadLaunchAsset = _loadLaunchAsset(id);
        _loadLaunchAsset.setLaunchAsset(true);
        return _loadLaunchAsset;
    }

    public final void setUpdateScopeKey(UpdateEntity update, String newScopeKey) {
        Intrinsics.checkNotNullParameter(update, "update");
        Intrinsics.checkNotNullParameter(newScopeKey, "newScopeKey");
        update.setScopeKey(newScopeKey);
        _updateUpdate(update);
    }

    public void markUpdateFinished(UpdateEntity update, boolean z) {
        Intrinsics.checkNotNullParameter(update, "update");
        UpdateStatus updateStatus = UpdateStatus.READY;
        if (update.getStatus() == UpdateStatus.DEVELOPMENT) {
            updateStatus = UpdateStatus.DEVELOPMENT;
        } else if (z) {
            updateStatus = UpdateStatus.EMBEDDED;
        }
        _markUpdateWithStatus(updateStatus, update.getId());
        _keepUpdate(update.getId());
    }

    public final void markUpdateFinished(UpdateEntity update) {
        Intrinsics.checkNotNullParameter(update, "update");
        markUpdateFinished(update, false);
    }

    public final void markUpdateAccessed(UpdateEntity update) {
        Intrinsics.checkNotNullParameter(update, "update");
        update.setLastAccessed(new Date());
        _updateUpdate(update);
    }

    public final void incrementSuccessfulLaunchCount(UpdateEntity update) {
        Intrinsics.checkNotNullParameter(update, "update");
        update.setSuccessfulLaunchCount(update.getSuccessfulLaunchCount() + 1);
        _updateUpdate(update);
    }

    public final void incrementFailedLaunchCount(UpdateEntity update) {
        Intrinsics.checkNotNullParameter(update, "update");
        update.setFailedLaunchCount(update.getFailedLaunchCount() + 1);
        _updateUpdate(update);
    }

    public final void markUpdatesWithMissingAssets(List<AssetEntity> missingAssets) {
        Intrinsics.checkNotNullParameter(missingAssets, "missingAssets");
        ArrayList arrayList = new ArrayList();
        for (AssetEntity assetEntity : missingAssets) {
            arrayList.add(Long.valueOf(assetEntity.getId()));
        }
        _markUpdatesWithMissingAssets(arrayList, UpdateStatus.PENDING);
    }
}
