package expo.modules.updates.p021db;

import com.facebook.common.util.UriUtil;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import expo.modules.updates.p021db.enums.UpdateStatus;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DatabaseIntegrityCheck.kt */
@Metadata(m184d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000¢\u0006\u0002\b\tJ\"\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¨\u0006\u0010"}, m183d2 = {"Lexpo/modules/updates/db/DatabaseIntegrityCheck;", "", "()V", "assetExists", "", UriUtil.LOCAL_ASSET_SCHEME, "Lexpo/modules/updates/db/entity/AssetEntity;", "updatesDirectory", "Ljava/io/File;", "assetExists$expo_updates_release", "run", "", "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "embeddedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.updates.db.DatabaseIntegrityCheck */
/* loaded from: classes5.dex */
public class DatabaseIntegrityCheck {
    public final void run(UpdatesDatabase database, File file, UpdateEntity updateEntity) {
        Intrinsics.checkNotNullParameter(database, "database");
        List<AssetEntity> loadAllAssets = database.assetDao().loadAllAssets();
        ArrayList arrayList = new ArrayList();
        for (AssetEntity assetEntity : loadAllAssets) {
            if (assetEntity.getRelativePath() == null || !assetExists$expo_updates_release(assetEntity, file)) {
                arrayList.add(assetEntity);
            }
        }
        if (arrayList.size() > 0) {
            database.updateDao().markUpdatesWithMissingAssets(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        for (UpdateEntity updateEntity2 : database.updateDao().loadAllUpdatesWithStatus(UpdateStatus.EMBEDDED)) {
            if (updateEntity == null || !Intrinsics.areEqual(updateEntity2.getId(), updateEntity.getId())) {
                arrayList2.add(updateEntity2);
            }
        }
        if (arrayList2.size() > 0) {
            database.updateDao().deleteUpdates(arrayList2);
        }
    }

    public final boolean assetExists$expo_updates_release(AssetEntity asset, File file) {
        Intrinsics.checkNotNullParameter(asset, "asset");
        return new File(file, asset.getRelativePath()).exists();
    }
}
