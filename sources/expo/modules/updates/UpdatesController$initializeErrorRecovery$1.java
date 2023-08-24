package expo.modules.updates;

import android.content.Context;
import android.os.Handler;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.errorrecovery.ErrorRecoveryDelegate;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.Loader;
import expo.modules.updates.loader.LoaderTask;
import expo.modules.updates.loader.RemoteLoader;
import expo.modules.updates.logging.UpdatesErrorCode;
import expo.modules.updates.logging.UpdatesLogger;
import expo.modules.updates.manifest.UpdateManifest;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesController.kt */
@Metadata(m184d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0014\u0010\u000f\u001a\u00020\t2\n\u0010\u0010\u001a\u00060\u0011j\u0002`\u0012H\u0016¨\u0006\u0013"}, m183d2 = {"expo/modules/updates/UpdatesController$initializeErrorRecovery$1", "Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate;", "getCheckAutomaticallyConfiguration", "Lexpo/modules/updates/UpdatesConfiguration$CheckAutomaticallyConfiguration;", "getLaunchedUpdateSuccessfulLaunchCount", "", "getRemoteLoadStatus", "Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate$RemoteLoadStatus;", "loadRemoteUpdate", "", "markFailedLaunchForLaunchedUpdate", "markSuccessfulLaunchForLaunchedUpdate", "relaunch", "callback", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "throwException", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class UpdatesController$initializeErrorRecovery$1 implements ErrorRecoveryDelegate {
    final /* synthetic */ Context $context;
    final /* synthetic */ UpdatesController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UpdatesController$initializeErrorRecovery$1(UpdatesController updatesController, Context context) {
        this.this$0 = updatesController;
        this.$context = context;
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public void loadRemoteUpdate() {
        LoaderTask loaderTask;
        loaderTask = this.this$0.loaderTask;
        boolean z = false;
        if (loaderTask != null && loaderTask.isRunning()) {
            z = true;
        }
        if (z) {
            return;
        }
        this.this$0.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADING;
        RemoteLoader remoteLoader = new RemoteLoader(this.$context, this.this$0.getUpdatesConfiguration(), this.this$0.getDatabase(), this.this$0.getFileDownloader(), this.this$0.getUpdatesDirectory(), this.this$0.getLaunchedUpdate());
        final UpdatesController updatesController = this.this$0;
        remoteLoader.start(new Loader.LoaderCallback() { // from class: expo.modules.updates.UpdatesController$initializeErrorRecovery$1$loadRemoteUpdate$1
            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onAssetLoaded(AssetEntity asset, int r2, int r3, int r4) {
                Intrinsics.checkNotNullParameter(asset, "asset");
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onFailure(Exception e) {
                UpdatesLogger updatesLogger;
                Intrinsics.checkNotNullParameter(e, "e");
                updatesLogger = UpdatesController.this.logger;
                String str = "UpdatesController loadRemoteUpdate onFailure: " + e.getLocalizedMessage();
                UpdatesErrorCode updatesErrorCode = UpdatesErrorCode.UpdateFailedToLoad;
                UpdateEntity launchedUpdate = UpdatesController.this.getLaunchedUpdate();
                UpdatesLogger.error$default(updatesLogger, str, updatesErrorCode, launchedUpdate == null ? null : launchedUpdate.getLoggingId(), null, null, 16, null);
                UpdatesController.this.setRemoteLoadStatus(ErrorRecoveryDelegate.RemoteLoadStatus.IDLE);
                UpdatesController.this.releaseDatabase();
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onSuccess(UpdateEntity updateEntity) {
                ErrorRecoveryDelegate.RemoteLoadStatus remoteLoadStatus;
                UpdatesController updatesController2 = UpdatesController.this;
                if (updateEntity != null) {
                    remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADED;
                } else {
                    remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.IDLE;
                }
                updatesController2.setRemoteLoadStatus(remoteLoadStatus);
                UpdatesController.this.releaseDatabase();
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public boolean onUpdateManifestLoaded(UpdateManifest updateManifest) {
                Intrinsics.checkNotNullParameter(updateManifest, "updateManifest");
                return UpdatesController.this.getSelectionPolicy().shouldLoadNewUpdate(updateManifest.getUpdateEntity(), UpdatesController.this.getLaunchedUpdate(), updateManifest.getManifestFilters());
            }
        });
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public void relaunch(Launcher.LauncherCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.this$0.relaunchReactApplication(this.$context, false, callback);
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public void throwException(Exception exception) {
        Intrinsics.checkNotNullParameter(exception, "exception");
        throw exception;
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public void markFailedLaunchForLaunchedUpdate() {
        Handler handler;
        if (this.this$0.isEmergencyLaunch()) {
            return;
        }
        handler = this.this$0.databaseHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseHandler");
            handler = null;
        }
        final UpdatesController updatesController = this.this$0;
        handler.post(new Runnable() { // from class: expo.modules.updates.UpdatesController$initializeErrorRecovery$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                UpdatesController$initializeErrorRecovery$1.m1713markFailedLaunchForLaunchedUpdate$lambda0(UpdatesController.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: markFailedLaunchForLaunchedUpdate$lambda-0  reason: not valid java name */
    public static final void m1713markFailedLaunchForLaunchedUpdate$lambda0(UpdatesController this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UpdateEntity launchedUpdate = this$0.getLaunchedUpdate();
        if (launchedUpdate == null) {
            return;
        }
        this$0.getDatabase().updateDao().incrementFailedLaunchCount(launchedUpdate);
        this$0.releaseDatabase();
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public void markSuccessfulLaunchForLaunchedUpdate() {
        Handler handler;
        if (this.this$0.isEmergencyLaunch()) {
            return;
        }
        handler = this.this$0.databaseHandler;
        if (handler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("databaseHandler");
            handler = null;
        }
        final UpdatesController updatesController = this.this$0;
        handler.post(new Runnable() { // from class: expo.modules.updates.UpdatesController$initializeErrorRecovery$1$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                UpdatesController$initializeErrorRecovery$1.m1714markSuccessfulLaunchForLaunchedUpdate$lambda1(UpdatesController.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: markSuccessfulLaunchForLaunchedUpdate$lambda-1  reason: not valid java name */
    public static final void m1714markSuccessfulLaunchForLaunchedUpdate$lambda1(UpdatesController this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UpdateEntity launchedUpdate = this$0.getLaunchedUpdate();
        if (launchedUpdate == null) {
            return;
        }
        this$0.getDatabase().updateDao().incrementSuccessfulLaunchCount(launchedUpdate);
        this$0.releaseDatabase();
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public ErrorRecoveryDelegate.RemoteLoadStatus getRemoteLoadStatus() {
        ErrorRecoveryDelegate.RemoteLoadStatus remoteLoadStatus;
        remoteLoadStatus = this.this$0.remoteLoadStatus;
        return remoteLoadStatus;
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public UpdatesConfiguration.CheckAutomaticallyConfiguration getCheckAutomaticallyConfiguration() {
        return this.this$0.getUpdatesConfiguration().getCheckOnLaunch();
    }

    @Override // expo.modules.updates.errorrecovery.ErrorRecoveryDelegate
    public int getLaunchedUpdateSuccessfulLaunchCount() {
        UpdateEntity launchedUpdate = this.this$0.getLaunchedUpdate();
        if (launchedUpdate == null) {
            return 0;
        }
        return launchedUpdate.getSuccessfulLaunchCount();
    }
}
