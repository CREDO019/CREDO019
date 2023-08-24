package expo.modules.updates.launcher;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.EmbeddedLoader;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.loader.LoaderFiles;
import expo.modules.updates.manifest.EmbeddedManifest;
import expo.modules.updates.manifest.ManifestMetadata;
import expo.modules.updates.manifest.UpdateManifest;
import expo.modules.updates.p021db.UpdatesDatabase;
import expo.modules.updates.p021db.dao.AssetDao;
import expo.modules.updates.p021db.dao.UpdateDao;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import expo.modules.updates.p021db.enums.UpdateStatus;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DatabaseLauncher.kt */
@Metadata(m184d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 72\u00020\u0001:\u00017B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ'\u0010(\u001a\u0004\u0018\u00010\u00052\u0006\u0010)\u001a\u00020$2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0000¢\u0006\u0002\b.J\u0018\u0010/\u001a\u0004\u0018\u00010\u001d2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-J\u0014\u00100\u001a\b\u0012\u0004\u0012\u000202012\u0006\u0010*\u001a\u00020+J \u00103\u001a\u0002042\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\u001a\u00105\u001a\u0002042\u0006\u0010)\u001a\u00020$2\b\u00106\u001a\u0004\u0018\u00010\u0005H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0017R\u0016\u0010\u0018\u001a\n\u0018\u00010\u0019j\u0004\u0018\u0001`\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u001b\u001a\u0004\u0018\u00010\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R\"\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\b\u0010\u000e\u001a\u0004\u0018\u00010\u001d@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R:\u0010%\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u000f\u0018\u00010#2\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u000f\u0018\u00010#@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, m183d2 = {"Lexpo/modules/updates/launcher/DatabaseLauncher;", "Lexpo/modules/updates/launcher/Launcher;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "updatesDirectory", "Ljava/io/File;", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "selectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "(Lexpo/modules/updates/UpdatesConfiguration;Ljava/io/File;Lexpo/modules/updates/loader/FileDownloader;Lexpo/modules/updates/selectionpolicy/SelectionPolicy;)V", "assetsToDownload", "", "assetsToDownloadFinished", "<set-?>", "", "bundleAssetName", "getBundleAssetName", "()Ljava/lang/String;", "callback", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "isUsingEmbeddedAssets", "", "()Z", "launchAssetException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "launchAssetFile", "getLaunchAssetFile", "Lexpo/modules/updates/db/entity/UpdateEntity;", "launchedUpdate", "getLaunchedUpdate", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "loaderFiles", "Lexpo/modules/updates/loader/LoaderFiles;", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "localAssetFiles", "getLocalAssetFiles", "()Ljava/util/Map;", "ensureAssetExists", UriUtil.LOCAL_ASSET_SCHEME, "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "context", "Landroid/content/Context;", "ensureAssetExists$expo_updates_release", "getLaunchableUpdate", "getReadyUpdateIds", "", "Ljava/util/UUID;", "launch", "", "maybeFinish", "assetFile", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class DatabaseLauncher implements Launcher {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "DatabaseLauncher";
    private int assetsToDownload;
    private int assetsToDownloadFinished;
    private String bundleAssetName;
    private Launcher.LauncherCallback callback;
    private final UpdatesConfiguration configuration;
    private final FileDownloader fileDownloader;
    private Exception launchAssetException;
    private String launchAssetFile;
    private UpdateEntity launchedUpdate;
    private final LoaderFiles loaderFiles;
    private Map<AssetEntity, String> localAssetFiles;
    private final SelectionPolicy selectionPolicy;
    private final File updatesDirectory;

    public DatabaseLauncher(UpdatesConfiguration configuration, File file, FileDownloader fileDownloader, SelectionPolicy selectionPolicy) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(fileDownloader, "fileDownloader");
        Intrinsics.checkNotNullParameter(selectionPolicy, "selectionPolicy");
        this.configuration = configuration;
        this.updatesDirectory = file;
        this.fileDownloader = fileDownloader;
        this.selectionPolicy = selectionPolicy;
        this.loaderFiles = new LoaderFiles();
    }

    @Override // expo.modules.updates.launcher.Launcher
    public UpdateEntity getLaunchedUpdate() {
        return this.launchedUpdate;
    }

    @Override // expo.modules.updates.launcher.Launcher
    public String getLaunchAssetFile() {
        return this.launchAssetFile;
    }

    @Override // expo.modules.updates.launcher.Launcher
    public String getBundleAssetName() {
        return this.bundleAssetName;
    }

    @Override // expo.modules.updates.launcher.Launcher
    public Map<AssetEntity, String> getLocalAssetFiles() {
        return this.localAssetFiles;
    }

    @Override // expo.modules.updates.launcher.Launcher
    public boolean isUsingEmbeddedAssets() {
        return getLocalAssetFiles() == null;
    }

    public final synchronized void launch(UpdatesDatabase database, Context context, Launcher.LauncherCallback launcherCallback) {
        File ensureAssetExists$expo_updates_release;
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.callback != null) {
            throw new AssertionError("DatabaseLauncher has already started. Create a new instance in order to launch a new version.");
        }
        this.callback = launcherCallback;
        this.launchedUpdate = getLaunchableUpdate(database, context);
        if (getLaunchedUpdate() == null) {
            Launcher.LauncherCallback launcherCallback2 = this.callback;
            Intrinsics.checkNotNull(launcherCallback2);
            launcherCallback2.onFailure(new Exception("No launchable update was found. If this is a bare workflow app, make sure you have configured expo-updates correctly in android/app/build.gradle."));
            return;
        }
        UpdateDao updateDao = database.updateDao();
        UpdateEntity launchedUpdate = getLaunchedUpdate();
        Intrinsics.checkNotNull(launchedUpdate);
        updateDao.markUpdateAccessed(launchedUpdate);
        UpdateEntity launchedUpdate2 = getLaunchedUpdate();
        Intrinsics.checkNotNull(launchedUpdate2);
        if (launchedUpdate2.getStatus() == UpdateStatus.EMBEDDED) {
            this.bundleAssetName = EmbeddedLoader.BARE_BUNDLE_FILENAME;
            if (getLocalAssetFiles() != null) {
                throw new AssertionError("mLocalAssetFiles should be null for embedded updates");
            }
            Launcher.LauncherCallback launcherCallback3 = this.callback;
            Intrinsics.checkNotNull(launcherCallback3);
            launcherCallback3.onSuccess();
            return;
        }
        UpdateEntity launchedUpdate3 = getLaunchedUpdate();
        Intrinsics.checkNotNull(launchedUpdate3);
        if (launchedUpdate3.getStatus() == UpdateStatus.DEVELOPMENT) {
            Launcher.LauncherCallback launcherCallback4 = this.callback;
            Intrinsics.checkNotNull(launcherCallback4);
            launcherCallback4.onSuccess();
            return;
        }
        UpdateDao updateDao2 = database.updateDao();
        UpdateEntity launchedUpdate4 = getLaunchedUpdate();
        Intrinsics.checkNotNull(launchedUpdate4);
        AssetEntity loadLaunchAsset = updateDao2.loadLaunchAsset(launchedUpdate4.getId());
        if (loadLaunchAsset.getRelativePath() == null) {
            throw new AssertionError("Launch Asset relativePath should not be null");
        }
        File ensureAssetExists$expo_updates_release2 = ensureAssetExists$expo_updates_release(loadLaunchAsset, database, context);
        if (ensureAssetExists$expo_updates_release2 != null) {
            this.launchAssetFile = ensureAssetExists$expo_updates_release2.toString();
        }
        AssetDao assetDao = database.assetDao();
        UpdateEntity launchedUpdate5 = getLaunchedUpdate();
        Intrinsics.checkNotNull(launchedUpdate5);
        List<AssetEntity> loadAssetsForUpdate = assetDao.loadAssetsForUpdate(launchedUpdate5.getId());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (AssetEntity assetEntity : loadAssetsForUpdate) {
            if (assetEntity.getId() != loadLaunchAsset.getId() && assetEntity.getRelativePath() != null && (ensureAssetExists$expo_updates_release = ensureAssetExists$expo_updates_release(assetEntity, database, context)) != null) {
                String uri = Uri.fromFile(ensureAssetExists$expo_updates_release).toString();
                Intrinsics.checkNotNullExpressionValue(uri, "fromFile(assetFile).toString()");
                linkedHashMap.put(assetEntity, uri);
            }
        }
        this.localAssetFiles = linkedHashMap;
        if (this.assetsToDownload == 0) {
            if (getLaunchAssetFile() == null) {
                Launcher.LauncherCallback launcherCallback5 = this.callback;
                Intrinsics.checkNotNull(launcherCallback5);
                launcherCallback5.onFailure(new Exception("mLaunchAssetFile was immediately null; this should never happen"));
            } else {
                Launcher.LauncherCallback launcherCallback6 = this.callback;
                Intrinsics.checkNotNull(launcherCallback6);
                launcherCallback6.onSuccess();
            }
        }
    }

    public final UpdateEntity getLaunchableUpdate(UpdatesDatabase database, Context context) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(context, "context");
        List<UpdateEntity> loadLaunchableUpdatesForScope = database.updateDao().loadLaunchableUpdatesForScope(this.configuration.getScopeKey());
        UpdateManifest updateManifest = EmbeddedManifest.INSTANCE.get(context, this.configuration);
        ArrayList arrayList = new ArrayList();
        for (UpdateEntity updateEntity : loadLaunchableUpdatesForScope) {
            if (updateEntity.getStatus() == UpdateStatus.EMBEDDED && updateManifest != null) {
                UpdateEntity updateEntity2 = updateManifest.getUpdateEntity();
                Intrinsics.checkNotNull(updateEntity2);
                if (!Intrinsics.areEqual(updateEntity2.getId(), updateEntity.getId())) {
                }
            }
            arrayList.add(updateEntity);
        }
        return this.selectionPolicy.selectUpdateToLaunch(arrayList, ManifestMetadata.INSTANCE.getManifestFilters(database, this.configuration));
    }

    public final List<UUID> getReadyUpdateIds(UpdatesDatabase database) {
        Intrinsics.checkNotNullParameter(database, "database");
        return database.updateDao().loadAllUpdateIdsWithStatus(UpdateStatus.READY);
    }

    public final File ensureAssetExists$expo_updates_release(AssetEntity asset, final UpdatesDatabase database, Context context) {
        UpdateManifest updateManifest;
        AssetEntity assetEntity;
        Intrinsics.checkNotNullParameter(asset, "asset");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(context, "context");
        File file = new File(this.updatesDirectory, asset.getRelativePath());
        boolean exists = file.exists();
        if (!exists && (updateManifest = EmbeddedManifest.INSTANCE.get(context, this.configuration)) != null) {
            Iterator<AssetEntity> it = updateManifest.getAssetEntityList().iterator();
            while (true) {
                if (!it.hasNext()) {
                    assetEntity = null;
                    break;
                }
                assetEntity = it.next();
                if (assetEntity.getKey() != null && Intrinsics.areEqual(assetEntity.getKey(), asset.getKey())) {
                    break;
                }
            }
            if (assetEntity != null) {
                try {
                    if (Arrays.equals(this.loaderFiles.copyAssetAndGetHash(assetEntity, file, context), asset.getHash())) {
                        exists = true;
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Failed to copy matching embedded asset", e);
                }
            }
        }
        if (exists) {
            return file;
        }
        this.assetsToDownload++;
        this.fileDownloader.downloadAsset(asset, this.updatesDirectory, this.configuration, context, new FileDownloader.AssetDownloadCallback() { // from class: expo.modules.updates.launcher.DatabaseLauncher$ensureAssetExists$1
            @Override // expo.modules.updates.loader.FileDownloader.AssetDownloadCallback
            public void onFailure(Exception e2, AssetEntity assetEntity2) {
                String str;
                Intrinsics.checkNotNullParameter(e2, "e");
                Intrinsics.checkNotNullParameter(assetEntity2, "assetEntity");
                str = DatabaseLauncher.TAG;
                Log.e(str, "Failed to load asset from disk or network", e2);
                if (assetEntity2.isLaunchAsset()) {
                    DatabaseLauncher.this.launchAssetException = e2;
                }
                DatabaseLauncher.this.maybeFinish(assetEntity2, null);
            }

            @Override // expo.modules.updates.loader.FileDownloader.AssetDownloadCallback
            public void onSuccess(AssetEntity assetEntity2, boolean z) {
                File file2;
                Intrinsics.checkNotNullParameter(assetEntity2, "assetEntity");
                database.assetDao().updateAsset(assetEntity2);
                file2 = DatabaseLauncher.this.updatesDirectory;
                File file3 = new File(file2, assetEntity2.getRelativePath());
                DatabaseLauncher databaseLauncher = DatabaseLauncher.this;
                if (!file3.exists()) {
                    file3 = null;
                }
                databaseLauncher.maybeFinish(assetEntity2, file3);
            }
        });
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void maybeFinish(AssetEntity assetEntity, File file) {
        String file2;
        this.assetsToDownloadFinished++;
        if (assetEntity.isLaunchAsset()) {
            if (file == null) {
                Log.e(TAG, "Could not launch; failed to load update from disk or network");
                file2 = null;
                String str = null;
            } else {
                file2 = file.toString();
            }
            this.launchAssetFile = file2;
        } else if (file != null) {
            Map<AssetEntity, String> localAssetFiles = getLocalAssetFiles();
            Intrinsics.checkNotNull(localAssetFiles);
            String file3 = file.toString();
            Intrinsics.checkNotNullExpressionValue(file3, "assetFile.toString()");
            localAssetFiles.put(assetEntity, file3);
        }
        if (this.assetsToDownloadFinished == this.assetsToDownload) {
            if (getLaunchAssetFile() == null) {
                if (this.launchAssetException == null) {
                    this.launchAssetException = new Exception("Launcher mLaunchAssetFile is unexpectedly null");
                }
                Launcher.LauncherCallback launcherCallback = this.callback;
                Intrinsics.checkNotNull(launcherCallback);
                Exception exc = this.launchAssetException;
                Intrinsics.checkNotNull(exc);
                launcherCallback.onFailure(exc);
            } else {
                Launcher.LauncherCallback launcherCallback2 = this.callback;
                Intrinsics.checkNotNull(launcherCallback2);
                launcherCallback2.onSuccess();
            }
        }
    }

    /* compiled from: DatabaseLauncher.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/updates/launcher/DatabaseLauncher$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
