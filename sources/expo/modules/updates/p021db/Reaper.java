package expo.modules.updates.p021db;

import android.net.Uri;
import android.util.Log;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.manifest.ManifestMetadata;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Reaper.kt */
@Metadata(m184d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m183d2 = {"Lexpo/modules/updates/db/Reaper;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "reapUnusedUpdates", "", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "updatesDirectory", "Ljava/io/File;", "launchedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "selectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.updates.db.Reaper */
/* loaded from: classes5.dex */
public final class Reaper {
    public static final Reaper INSTANCE = new Reaper();
    private static final String TAG = "Reaper";

    private Reaper() {
    }

    @JvmStatic
    public static final void reapUnusedUpdates(UpdatesConfiguration configuration, UpdatesDatabase database, File file, UpdateEntity updateEntity, SelectionPolicy selectionPolicy) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(selectionPolicy, "selectionPolicy");
        if (updateEntity == null) {
            Log.d(TAG, "Tried to reap while no update was launched; aborting");
            return;
        }
        database.updateDao().deleteUpdates(selectionPolicy.selectUpdatesToDelete(database.updateDao().loadAllUpdates(), updateEntity, ManifestMetadata.INSTANCE.getManifestFilters(database, configuration)));
        List<AssetEntity> deleteUnusedAssets = database.assetDao().deleteUnusedAssets();
        ArrayList<AssetEntity> arrayList = new ArrayList();
        for (AssetEntity assetEntity : deleteUnusedAssets) {
            if (!assetEntity.getMarkedForDeletion()) {
                String str = TAG;
                Uri url = assetEntity.getUrl();
                Log.e(str, "Tried to delete asset with URL " + url + " but it was not marked for deletion");
            } else {
                File file2 = new File(file, assetEntity.getRelativePath());
                try {
                    if (file2.exists() && !file2.delete()) {
                        String str2 = TAG;
                        Uri url2 = assetEntity.getUrl();
                        Log.e(str2, "Failed to delete asset with URL " + url2 + " at path " + file2);
                        arrayList.add(assetEntity);
                    }
                } catch (Exception e) {
                    String str3 = TAG;
                    Uri url3 = assetEntity.getUrl();
                    Log.e(str3, "Failed to delete asset with URL " + url3 + " at path " + file2, e);
                    arrayList.add(assetEntity);
                }
            }
        }
        for (AssetEntity assetEntity2 : arrayList) {
            File file3 = new File(file, assetEntity2.getRelativePath());
            try {
            } catch (Exception e2) {
                String str4 = TAG;
                Uri url4 = assetEntity2.getUrl();
                Log.e(str4, "Retried and failed again deleting asset with URL " + url4 + " at path " + file3, e2);
                arrayList.add(assetEntity2);
            }
            if (file3.exists() && !file3.delete()) {
                String str5 = TAG;
                Uri url5 = assetEntity2.getUrl();
                Log.e(str5, "Retried and failed again deleting asset with URL " + url5 + " at path " + file3);
            }
            arrayList.remove(assetEntity2);
        }
    }
}
