package expo.modules.updates.loader;

import android.content.Context;
import android.util.Log;
import com.facebook.common.util.UriUtil;
import com.onesignal.OneSignalDbContract;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.UpdatesUtils;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.loader.Loader;
import expo.modules.updates.manifest.ManifestMetadata;
import expo.modules.updates.manifest.UpdateManifest;
import expo.modules.updates.p021db.UpdatesDatabase;
import expo.modules.updates.p021db.dao.AssetDao;
import expo.modules.updates.p021db.dao.UpdateDao;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import expo.modules.updates.p021db.enums.UpdateStatus;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Loader.kt */
@Metadata(m184d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b&\u0018\u0000 42\u00020\u0001:\u0003345B1\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0016\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\u001eH\u0002J\u001c\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!2\n\u0010\"\u001a\u00060#j\u0002`$H\u0002J\b\u0010%\u001a\u00020\u001cH\u0002J\u0018\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)H\u0002J2\u0010*\u001a\u00020\u001c2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u00132\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020+H$J(\u0010,\u001a\u00020\u001c2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020-H$J\u0010\u0010.\u001a\u00020\u001c2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010/\u001a\u00020\u001cH\u0002J\u0010\u00100\u001a\u0002012\u0006\u0010'\u001a\u00020\u0013H$J\u000e\u00102\u001a\u00020\u001c2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, m183d2 = {"Lexpo/modules/updates/loader/Loader;", "", "context", "Landroid/content/Context;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "database", "Lexpo/modules/updates/db/UpdatesDatabase;", "updatesDirectory", "Ljava/io/File;", "loaderFiles", "Lexpo/modules/updates/loader/LoaderFiles;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/UpdatesDatabase;Ljava/io/File;Lexpo/modules/updates/loader/LoaderFiles;)V", "assetTotal", "", "callback", "Lexpo/modules/updates/loader/Loader$LoaderCallback;", "erroredAssetList", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "existingAssetList", "finishedAssetList", "skippedAssetList", "updateEntity", "Lexpo/modules/updates/db/entity/UpdateEntity;", "updateManifest", "Lexpo/modules/updates/manifest/UpdateManifest;", "downloadAllAssets", "", "assetList", "", "finishWithError", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "finishWithSuccess", "handleAssetDownloadCompleted", "assetEntity", "result", "Lexpo/modules/updates/loader/Loader$AssetLoadResult;", "loadAsset", "Lexpo/modules/updates/loader/FileDownloader$AssetDownloadCallback;", "loadManifest", "Lexpo/modules/updates/loader/FileDownloader$ManifestDownloadCallback;", "processUpdateManifest", "reset", "shouldSkipAsset", "", "start", "AssetLoadResult", "Companion", "LoaderCallback", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public abstract class Loader {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "Loader";
    private int assetTotal;
    private LoaderCallback callback;
    private final UpdatesConfiguration configuration;
    private final Context context;
    private final UpdatesDatabase database;
    private List<AssetEntity> erroredAssetList;
    private List<AssetEntity> existingAssetList;
    private List<AssetEntity> finishedAssetList;
    private final LoaderFiles loaderFiles;
    private List<AssetEntity> skippedAssetList;
    private UpdateEntity updateEntity;
    private UpdateManifest updateManifest;
    private final File updatesDirectory;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Loader.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/updates/loader/Loader$AssetLoadResult;", "", "(Ljava/lang/String;I)V", "FINISHED", "ALREADY_EXISTS", "ERRORED", "SKIPPED", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public enum AssetLoadResult {
        FINISHED,
        ALREADY_EXISTS,
        ERRORED,
        SKIPPED
    }

    /* compiled from: Loader.kt */
    @Metadata(m184d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&J\u0014\u0010\n\u001a\u00020\u00032\n\u0010\u000b\u001a\u00060\fj\u0002`\rH&J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H&¨\u0006\u0015"}, m183d2 = {"Lexpo/modules/updates/loader/Loader$LoaderCallback;", "", "onAssetLoaded", "", UriUtil.LOCAL_ASSET_SCHEME, "Lexpo/modules/updates/db/entity/AssetEntity;", "successfulAssetCount", "", "failedAssetCount", "totalAssetCount", "onFailure", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "update", "Lexpo/modules/updates/db/entity/UpdateEntity;", "onUpdateManifestLoaded", "", "updateManifest", "Lexpo/modules/updates/manifest/UpdateManifest;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public interface LoaderCallback {
        void onAssetLoaded(AssetEntity assetEntity, int r2, int r3, int r4);

        void onFailure(Exception exc);

        void onSuccess(UpdateEntity updateEntity);

        boolean onUpdateManifestLoaded(UpdateManifest updateManifest);
    }

    /* compiled from: Loader.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[AssetLoadResult.values().length];
            r0[AssetLoadResult.FINISHED.ordinal()] = 1;
            r0[AssetLoadResult.ALREADY_EXISTS.ordinal()] = 2;
            r0[AssetLoadResult.ERRORED.ordinal()] = 3;
            r0[AssetLoadResult.SKIPPED.ordinal()] = 4;
            $EnumSwitchMapping$0 = r0;
        }
    }

    protected abstract void loadAsset(Context context, AssetEntity assetEntity, File file, UpdatesConfiguration updatesConfiguration, FileDownloader.AssetDownloadCallback assetDownloadCallback);

    protected abstract void loadManifest(Context context, UpdatesDatabase updatesDatabase, UpdatesConfiguration updatesConfiguration, FileDownloader.ManifestDownloadCallback manifestDownloadCallback);

    protected abstract boolean shouldSkipAsset(AssetEntity assetEntity);

    /* JADX INFO: Access modifiers changed from: protected */
    public Loader(Context context, UpdatesConfiguration configuration, UpdatesDatabase database, File file, LoaderFiles loaderFiles) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(loaderFiles, "loaderFiles");
        this.context = context;
        this.configuration = configuration;
        this.database = database;
        this.updatesDirectory = file;
        this.loaderFiles = loaderFiles;
        this.erroredAssetList = new ArrayList();
        this.skippedAssetList = new ArrayList();
        this.existingAssetList = new ArrayList();
        this.finishedAssetList = new ArrayList();
    }

    public final void start(LoaderCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (this.callback != null) {
            callback.onFailure(new Exception("RemoteLoader has already started. Create a new instance in order to load multiple URLs in parallel."));
            return;
        }
        this.callback = callback;
        loadManifest(this.context, this.database, this.configuration, new FileDownloader.ManifestDownloadCallback() { // from class: expo.modules.updates.loader.Loader$start$1
            @Override // expo.modules.updates.loader.FileDownloader.ManifestDownloadCallback
            public void onFailure(String message, Exception e) {
                Intrinsics.checkNotNullParameter(message, "message");
                Intrinsics.checkNotNullParameter(e, "e");
                Loader.this.finishWithError(message, e);
            }

            @Override // expo.modules.updates.loader.FileDownloader.ManifestDownloadCallback
            public void onSuccess(UpdateManifest updateManifest) {
                Loader.LoaderCallback loaderCallback;
                Intrinsics.checkNotNullParameter(updateManifest, "updateManifest");
                Loader.this.updateManifest = updateManifest;
                loaderCallback = Loader.this.callback;
                Intrinsics.checkNotNull(loaderCallback);
                if (loaderCallback.onUpdateManifestLoaded(updateManifest)) {
                    Loader.this.processUpdateManifest(updateManifest);
                    return;
                }
                Loader.this.updateEntity = null;
                Loader.this.finishWithSuccess();
            }
        });
    }

    private final void reset() {
        this.updateEntity = null;
        this.callback = null;
        this.assetTotal = 0;
        this.erroredAssetList = new ArrayList();
        this.skippedAssetList = new ArrayList();
        this.existingAssetList = new ArrayList();
        this.finishedAssetList = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishWithSuccess() {
        if (this.callback == null) {
            String str = TAG;
            String simpleName = getClass().getSimpleName();
            Log.e(str, simpleName + " tried to finish but it already finished or was never initialized.");
            return;
        }
        ManifestMetadata manifestMetadata = ManifestMetadata.INSTANCE;
        UpdateManifest updateManifest = this.updateManifest;
        Intrinsics.checkNotNull(updateManifest);
        manifestMetadata.saveMetadata(updateManifest, this.database, this.configuration);
        LoaderCallback loaderCallback = this.callback;
        Intrinsics.checkNotNull(loaderCallback);
        loaderCallback.onSuccess(this.updateEntity);
        reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void finishWithError(String str, Exception exc) {
        String str2 = TAG;
        Log.e(str2, str, exc);
        LoaderCallback loaderCallback = this.callback;
        if (loaderCallback == null) {
            String simpleName = getClass().getSimpleName();
            Log.e(str2, simpleName + " tried to finish but it already finished or was never initialized.");
            return;
        }
        Intrinsics.checkNotNull(loaderCallback);
        loaderCallback.onFailure(exc);
        reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void processUpdateManifest(UpdateManifest updateManifest) {
        if (updateManifest.isDevelopmentMode()) {
            UpdateEntity updateEntity = updateManifest.getUpdateEntity();
            UpdateDao updateDao = this.database.updateDao();
            Intrinsics.checkNotNull(updateEntity);
            updateDao.insertUpdate(updateEntity);
            this.database.updateDao().markUpdateFinished(updateEntity);
            finishWithSuccess();
            return;
        }
        UpdateEntity updateEntity2 = updateManifest.getUpdateEntity();
        UpdateDao updateDao2 = this.database.updateDao();
        Intrinsics.checkNotNull(updateEntity2);
        UpdateEntity loadUpdateWithId = updateDao2.loadUpdateWithId(updateEntity2.getId());
        if (loadUpdateWithId != null && !Intrinsics.areEqual(loadUpdateWithId.getScopeKey(), updateEntity2.getScopeKey())) {
            this.database.updateDao().setUpdateScopeKey(loadUpdateWithId, updateEntity2.getScopeKey());
            Log.e(TAG, "Loaded an update with the same ID but a different scopeKey than one we already have on disk. This is a server error. Overwriting the scopeKey and loading the existing update.");
        }
        if (loadUpdateWithId != null && loadUpdateWithId.getStatus() == UpdateStatus.READY) {
            this.updateEntity = loadUpdateWithId;
            finishWithSuccess();
            return;
        }
        if (loadUpdateWithId == null) {
            this.updateEntity = updateEntity2;
            UpdateDao updateDao3 = this.database.updateDao();
            UpdateEntity updateEntity3 = this.updateEntity;
            Intrinsics.checkNotNull(updateEntity3);
            updateDao3.insertUpdate(updateEntity3);
        } else {
            this.updateEntity = loadUpdateWithId;
        }
        downloadAllAssets(updateManifest.getAssetEntityList());
    }

    private final void downloadAllAssets(List<AssetEntity> list) {
        AssetEntity assetEntity;
        this.assetTotal = list.size();
        for (AssetEntity assetEntity2 : list) {
            if (shouldSkipAsset(assetEntity2)) {
                handleAssetDownloadCompleted(assetEntity2, AssetLoadResult.SKIPPED);
            } else {
                AssetEntity loadAssetWithKey = this.database.assetDao().loadAssetWithKey(assetEntity2.getKey());
                if (loadAssetWithKey != null) {
                    this.database.assetDao().mergeAndUpdateAsset(loadAssetWithKey, assetEntity2);
                    assetEntity = loadAssetWithKey;
                } else {
                    assetEntity = assetEntity2;
                }
                if (assetEntity.getRelativePath() != null && this.loaderFiles.fileExists(new File(this.updatesDirectory, assetEntity.getRelativePath()))) {
                    handleAssetDownloadCompleted(assetEntity, AssetLoadResult.ALREADY_EXISTS);
                } else {
                    loadAsset(this.context, assetEntity, this.updatesDirectory, this.configuration, new FileDownloader.AssetDownloadCallback() { // from class: expo.modules.updates.loader.Loader$downloadAllAssets$1
                        @Override // expo.modules.updates.loader.FileDownloader.AssetDownloadCallback
                        public void onFailure(Exception e, AssetEntity assetEntity3) {
                            String str;
                            String str2;
                            Intrinsics.checkNotNullParameter(e, "e");
                            Intrinsics.checkNotNullParameter(assetEntity3, "assetEntity");
                            if (assetEntity3.getHash() != null) {
                                UpdatesUtils updatesUtils = UpdatesUtils.INSTANCE;
                                byte[] hash = assetEntity3.getHash();
                                Intrinsics.checkNotNull(hash);
                                str = "hash " + updatesUtils.bytesToHex(hash);
                            } else {
                                str = "key " + assetEntity3.getKey();
                            }
                            str2 = Loader.TAG;
                            Log.e(str2, "Failed to download asset with " + str, e);
                            Loader.this.handleAssetDownloadCompleted(assetEntity3, Loader.AssetLoadResult.ERRORED);
                        }

                        @Override // expo.modules.updates.loader.FileDownloader.AssetDownloadCallback
                        public void onSuccess(AssetEntity assetEntity3, boolean z) {
                            Intrinsics.checkNotNullParameter(assetEntity3, "assetEntity");
                            Loader.this.handleAssetDownloadCompleted(assetEntity3, z ? Loader.AssetLoadResult.FINISHED : Loader.AssetLoadResult.ALREADY_EXISTS);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void handleAssetDownloadCompleted(AssetEntity assetEntity, AssetLoadResult assetLoadResult) {
        int r8 = WhenMappings.$EnumSwitchMapping$0[assetLoadResult.ordinal()];
        boolean z = true;
        if (r8 == 1) {
            this.finishedAssetList.add(assetEntity);
        } else if (r8 == 2) {
            this.existingAssetList.add(assetEntity);
        } else if (r8 == 3) {
            this.erroredAssetList.add(assetEntity);
        } else if (r8 == 4) {
            this.skippedAssetList.add(assetEntity);
        } else {
            throw new AssertionError("Missing implementation for AssetLoadResult value");
        }
        LoaderCallback loaderCallback = this.callback;
        Intrinsics.checkNotNull(loaderCallback);
        loaderCallback.onAssetLoaded(assetEntity, this.finishedAssetList.size() + this.existingAssetList.size(), this.erroredAssetList.size(), this.assetTotal);
        if (this.finishedAssetList.size() + this.erroredAssetList.size() + this.existingAssetList.size() + this.skippedAssetList.size() == this.assetTotal) {
            try {
                for (AssetEntity assetEntity2 : this.existingAssetList) {
                    AssetDao assetDao = this.database.assetDao();
                    UpdateEntity updateEntity = this.updateEntity;
                    Intrinsics.checkNotNull(updateEntity);
                    if (!assetDao.addExistingAssetToUpdate(updateEntity, assetEntity2, assetEntity2.isLaunchAsset())) {
                        byte[] bArr = null;
                        try {
                            bArr = UpdatesUtils.INSTANCE.sha256(new File(this.updatesDirectory, assetEntity2.getRelativePath()));
                        } catch (Exception unused) {
                        }
                        assetEntity2.setDownloadTime(new Date());
                        assetEntity2.setHash(bArr);
                        this.finishedAssetList.add(assetEntity2);
                    }
                }
                AssetDao assetDao2 = this.database.assetDao();
                List<AssetEntity> list = this.finishedAssetList;
                UpdateEntity updateEntity2 = this.updateEntity;
                Intrinsics.checkNotNull(updateEntity2);
                assetDao2.insertAssets(list, updateEntity2);
                if (this.erroredAssetList.size() == 0) {
                    UpdateDao updateDao = this.database.updateDao();
                    UpdateEntity updateEntity3 = this.updateEntity;
                    Intrinsics.checkNotNull(updateEntity3);
                    if (this.skippedAssetList.size() == 0) {
                        z = false;
                    }
                    updateDao.markUpdateFinished(updateEntity3, z);
                }
                if (this.erroredAssetList.size() > 0) {
                    finishWithError("Failed to load all assets", new Exception("Failed to load all assets"));
                } else {
                    finishWithSuccess();
                }
            } catch (Exception e) {
                finishWithError("Error while adding new update to database", e);
            }
        }
    }

    /* compiled from: Loader.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/updates/loader/Loader$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
