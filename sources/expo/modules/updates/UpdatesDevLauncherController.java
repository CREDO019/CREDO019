package expo.modules.updates;

import android.content.Context;
import expo.modules.updates.launcher.DatabaseLauncher;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.Loader;
import expo.modules.updates.loader.RemoteLoader;
import expo.modules.updates.manifest.UpdateManifest;
import expo.modules.updates.p021db.DatabaseHolder;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import expo.modules.updates.selectionpolicy.LauncherSelectionPolicySingleUpdate;
import expo.modules.updates.selectionpolicy.ReaperSelectionPolicyDevelopmentClient;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import expo.modules.updatesinterface.UpdatesInterface;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: UpdatesDevLauncherController.kt */
@Metadata(m184d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J,\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J(\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u0012\u001a\u00020\u0006H\u0016J,\u0010\u0013\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0014H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m183d2 = {"Lexpo/modules/updates/UpdatesDevLauncherController;", "Lexpo/modules/updatesinterface/UpdatesInterface;", "()V", "mTempConfiguration", "Lexpo/modules/updates/UpdatesConfiguration;", "fetchUpdateWithConfiguration", "", "configuration", "Ljava/util/HashMap;", "", "", "context", "Landroid/content/Context;", "callback", "Lexpo/modules/updatesinterface/UpdatesInterface$UpdateCallback;", "launchUpdate", "update", "Lexpo/modules/updates/db/entity/UpdateEntity;", "reset", "storedUpdateIdsWithConfiguration", "Lexpo/modules/updatesinterface/UpdatesInterface$QueryCallback;", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class UpdatesDevLauncherController implements expo.modules.updatesinterface.UpdatesInterface {
    public static final Companion Companion = new Companion(null);
    private static UpdatesDevLauncherController singletonInstance;
    private UpdatesConfiguration mTempConfiguration;

    @JvmStatic
    public static final UpdatesDevLauncherController initialize(Context context) {
        return Companion.initialize(context);
    }

    @Override // expo.modules.updatesinterface.UpdatesInterface
    public void reset() {
        UpdatesController.Companion.getInstance().setLauncher(null);
    }

    @Override // expo.modules.updatesinterface.UpdatesInterface
    public void fetchUpdateWithConfiguration(HashMap<String, Object> configuration, final Context context, final UpdatesInterface.UpdateCallback callback) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        final UpdatesController companion = UpdatesController.Companion.getInstance();
        final UpdatesConfiguration updatesConfiguration = new UpdatesConfiguration(context, configuration);
        if (updatesConfiguration.getUpdateUrl() == null || updatesConfiguration.getScopeKey() == null) {
            callback.onFailure(new Exception("Failed to load update: UpdatesConfiguration object must include a valid update URL"));
        } else if (companion.getUpdatesDirectory() == null) {
            callback.onFailure(companion.getUpdatesDirectoryException());
        } else {
            this.mTempConfiguration = companion.getUpdatesConfiguration();
            Companion.setDevelopmentSelectionPolicy();
            companion.setUpdatesConfiguration(updatesConfiguration);
            final DatabaseHolder databaseHolder = companion.getDatabaseHolder();
            new RemoteLoader(context, updatesConfiguration, databaseHolder.getDatabase(), companion.getFileDownloader(), companion.getUpdatesDirectory(), null).start(new Loader.LoaderCallback() { // from class: expo.modules.updates.UpdatesDevLauncherController$fetchUpdateWithConfiguration$1
                @Override // expo.modules.updates.loader.Loader.LoaderCallback
                public void onFailure(Exception e) {
                    UpdatesConfiguration updatesConfiguration2;
                    Intrinsics.checkNotNullParameter(e, "e");
                    DatabaseHolder.this.releaseDatabase();
                    UpdatesController updatesController = companion;
                    updatesConfiguration2 = this.mTempConfiguration;
                    Intrinsics.checkNotNull(updatesConfiguration2);
                    updatesController.setUpdatesConfiguration(updatesConfiguration2);
                    callback.onFailure(e);
                }

                @Override // expo.modules.updates.loader.Loader.LoaderCallback
                public void onSuccess(UpdateEntity updateEntity) {
                    DatabaseHolder.this.releaseDatabase();
                    if (updateEntity != null) {
                        this.launchUpdate(updateEntity, updatesConfiguration, context, callback);
                    } else {
                        callback.onSuccess(null);
                    }
                }

                @Override // expo.modules.updates.loader.Loader.LoaderCallback
                public void onAssetLoaded(AssetEntity asset, int r3, int r4, int r5) {
                    Intrinsics.checkNotNullParameter(asset, "asset");
                    callback.onProgress(r3, r4, r5);
                }

                @Override // expo.modules.updates.loader.Loader.LoaderCallback
                public boolean onUpdateManifestLoaded(UpdateManifest updateManifest) {
                    Intrinsics.checkNotNullParameter(updateManifest, "updateManifest");
                    return callback.onManifestLoaded(updateManifest.getManifest().getRawJson());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchUpdate(UpdateEntity updateEntity, UpdatesConfiguration updatesConfiguration, Context context, final UpdatesInterface.UpdateCallback updateCallback) {
        final UpdatesController companion = UpdatesController.Companion.getInstance();
        SelectionPolicy selectionPolicy = companion.getSelectionPolicy();
        companion.setNextSelectionPolicy(new SelectionPolicy(new LauncherSelectionPolicySingleUpdate(updateEntity.getId()), selectionPolicy.getLoaderSelectionPolicy(), selectionPolicy.getReaperSelectionPolicy()));
        final DatabaseHolder databaseHolder = companion.getDatabaseHolder();
        File updatesDirectory = companion.getUpdatesDirectory();
        Intrinsics.checkNotNull(updatesDirectory);
        final DatabaseLauncher databaseLauncher = new DatabaseLauncher(updatesConfiguration, updatesDirectory, companion.getFileDownloader(), companion.getSelectionPolicy());
        databaseLauncher.launch(databaseHolder.getDatabase(), context, new Launcher.LauncherCallback() { // from class: expo.modules.updates.UpdatesDevLauncherController$launchUpdate$1
            @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
            public void onFailure(Exception e) {
                UpdatesConfiguration updatesConfiguration2;
                Intrinsics.checkNotNullParameter(e, "e");
                DatabaseHolder.this.releaseDatabase();
                UpdatesController updatesController = companion;
                updatesConfiguration2 = this.mTempConfiguration;
                Intrinsics.checkNotNull(updatesConfiguration2);
                updatesController.setUpdatesConfiguration(updatesConfiguration2);
                updateCallback.onFailure(e);
            }

            @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
            public void onSuccess() {
                DatabaseHolder.this.releaseDatabase();
                companion.setLauncher(databaseLauncher);
                UpdatesInterface.UpdateCallback updateCallback2 = updateCallback;
                final DatabaseLauncher databaseLauncher2 = databaseLauncher;
                updateCallback2.onSuccess(new UpdatesInterface.Update() { // from class: expo.modules.updates.UpdatesDevLauncherController$launchUpdate$1$onSuccess$1
                    @Override // expo.modules.updatesinterface.UpdatesInterface.Update
                    public JSONObject getManifest() {
                        UpdateEntity launchedUpdate = DatabaseLauncher.this.getLaunchedUpdate();
                        Intrinsics.checkNotNull(launchedUpdate);
                        JSONObject manifest = launchedUpdate.getManifest();
                        Intrinsics.checkNotNull(manifest);
                        return manifest;
                    }

                    @Override // expo.modules.updatesinterface.UpdatesInterface.Update
                    public String getLaunchAssetPath() {
                        String launchAssetFile = DatabaseLauncher.this.getLaunchAssetFile();
                        Intrinsics.checkNotNull(launchAssetFile);
                        return launchAssetFile;
                    }
                });
                companion.runReaper();
            }
        });
    }

    @Override // expo.modules.updatesinterface.UpdatesInterface
    public void storedUpdateIdsWithConfiguration(HashMap<String, Object> configuration, Context context, UpdatesInterface.QueryCallback callback) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        UpdatesController companion = UpdatesController.Companion.getInstance();
        UpdatesConfiguration updatesConfiguration = new UpdatesConfiguration(context, configuration);
        if (updatesConfiguration.getUpdateUrl() == null || updatesConfiguration.getScopeKey() == null) {
            callback.onFailure(new Exception("Failed to load update: UpdatesConfiguration object must include a valid update URL"));
            return;
        }
        File updatesDirectory = companion.getUpdatesDirectory();
        if (updatesDirectory == null) {
            callback.onFailure(companion.getUpdatesDirectoryException());
            return;
        }
        List<UUID> readyUpdateIds = new DatabaseLauncher(updatesConfiguration, updatesDirectory, companion.getFileDownloader(), companion.getSelectionPolicy()).getReadyUpdateIds(companion.getDatabaseHolder().getDatabase());
        companion.getDatabaseHolder().releaseDatabase();
        callback.onSuccess(readyUpdateIds);
    }

    /* compiled from: UpdatesDevLauncherController.kt */
    @Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0007J\b\u0010\u000b\u001a\u00020\fH\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, m183d2 = {"Lexpo/modules/updates/UpdatesDevLauncherController$Companion;", "", "()V", "instance", "Lexpo/modules/updates/UpdatesDevLauncherController;", "getInstance", "()Lexpo/modules/updates/UpdatesDevLauncherController;", "singletonInstance", "initialize", "context", "Landroid/content/Context;", "setDevelopmentSelectionPolicy", "", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UpdatesDevLauncherController getInstance() {
            UpdatesDevLauncherController updatesDevLauncherController = UpdatesDevLauncherController.singletonInstance;
            if (updatesDevLauncherController != null) {
                return updatesDevLauncherController;
            }
            throw new IllegalStateException("UpdatesDevLauncherController.instance was called before the module was initialized".toString());
        }

        @JvmStatic
        public final UpdatesDevLauncherController initialize(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (UpdatesDevLauncherController.singletonInstance == null) {
                UpdatesDevLauncherController.singletonInstance = new UpdatesDevLauncherController();
            }
            UpdatesController.Companion.initializeWithoutStarting(context);
            return getInstance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setDevelopmentSelectionPolicy() {
            UpdatesController companion = UpdatesController.Companion.getInstance();
            companion.resetSelectionPolicyToDefault();
            SelectionPolicy selectionPolicy = companion.getSelectionPolicy();
            companion.setDefaultSelectionPolicy(new SelectionPolicy(selectionPolicy.getLauncherSelectionPolicy(), selectionPolicy.getLoaderSelectionPolicy(), new ReaperSelectionPolicyDevelopmentClient(0, 1, null)));
            companion.resetSelectionPolicyToDefault();
        }
    }
}
