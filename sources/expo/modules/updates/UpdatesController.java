package expo.modules.updates;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.onesignal.OneSignalDbContract;
import expo.modules.updates.errorrecovery.ErrorRecovery;
import expo.modules.updates.errorrecovery.ErrorRecoveryDelegate;
import expo.modules.updates.launcher.DatabaseLauncher;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.launcher.NoDatabaseLauncher;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.loader.LoaderTask;
import expo.modules.updates.logging.UpdatesErrorCode;
import expo.modules.updates.logging.UpdatesLogReader;
import expo.modules.updates.logging.UpdatesLogger;
import expo.modules.updates.manifest.UpdateManifest;
import expo.modules.updates.p021db.BuildData;
import expo.modules.updates.p021db.DatabaseHolder;
import expo.modules.updates.p021db.Reaper;
import expo.modules.updates.p021db.UpdatesDatabase;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import expo.modules.updates.selectionpolicy.SelectionPolicyFactory;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesController.kt */
@Metadata(m184d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 i2\u00020\u0001:\u0001iB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010Q\u001a\u00020RJ\b\u0010S\u001a\u00020TH\u0002J\u000e\u0010U\u001a\u00020T2\u0006\u0010\u0002\u001a\u00020\u0003J\b\u0010V\u001a\u00020TH\u0002J\u000e\u0010W\u001a\u00020T2\u0006\u0010X\u001a\u00020YJ\u0010\u0010Z\u001a\u00020T2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0016\u0010[\u001a\u00020T2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\\\u001a\u00020]J \u0010[\u001a\u00020T2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010^\u001a\u00020\u001c2\u0006\u0010\\\u001a\u00020]H\u0002J\u0006\u0010_\u001a\u00020TJ\u0006\u0010`\u001a\u00020TJ\u0006\u0010a\u001a\u00020TJ\u000e\u0010b\u001a\u00020T2\u0006\u00109\u001a\u00020\u0014J\u0010\u0010c\u001a\u00020T2\b\u0010(\u001a\u0004\u0018\u00010)J\u0010\u0010d\u001a\u00020T2\b\u00109\u001a\u0004\u0018\u00010\u0014J\u000e\u0010e\u001a\u00020T2\u0006\u00104\u001a\u000206J\u0010\u0010f\u001a\u00020T2\u0006\u0010g\u001a\u000208H\u0002J\u000e\u0010h\u001a\u00020T2\u0006\u0010\u0002\u001a\u00020\u0003R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001c@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010!\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b!\u0010\u001eR\u0013\u0010\"\u001a\u0004\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b#\u0010\nR\u0013\u0010$\u001a\u0004\u0018\u00010%8F¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u001f\u0010,\u001a\u0010\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\b\u0018\u00010-8F¢\u0006\u0006\u001a\u0004\b/\u00100R\u000e\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00104\u001a\n\u0012\u0004\u0012\u000206\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u000208X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u00109\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0013\u0010<\u001a\u0004\u0018\u00010=8F¢\u0006\u0006\u001a\u0004\b>\u0010?R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001c\u0010D\u001a\u0004\u0018\u00010EX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\"\u0010J\u001a\n\u0018\u00010Kj\u0004\u0018\u0001`LX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010P¨\u0006j"}, m183d2 = {"Lexpo/modules/updates/UpdatesController;", "", "context", "Landroid/content/Context;", "updatesConfiguration", "Lexpo/modules/updates/UpdatesConfiguration;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;)V", "bundleAssetName", "", "getBundleAssetName", "()Ljava/lang/String;", "databaseHandler", "Landroid/os/Handler;", "databaseHandlerThread", "Landroid/os/HandlerThread;", "databaseHolder", "Lexpo/modules/updates/db/DatabaseHolder;", "getDatabaseHolder", "()Lexpo/modules/updates/db/DatabaseHolder;", "defaultSelectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "errorRecovery", "Lexpo/modules/updates/errorrecovery/ErrorRecovery;", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "getFileDownloader", "()Lexpo/modules/updates/loader/FileDownloader;", "<set-?>", "", "isEmergencyLaunch", "()Z", "isLoaderTaskFinished", "isStarted", "isUsingEmbeddedAssets", "launchAssetFile", "getLaunchAssetFile", "launchedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "getLaunchedUpdate", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "launcher", "Lexpo/modules/updates/launcher/Launcher;", "loaderTask", "Lexpo/modules/updates/loader/LoaderTask;", "localAssetFiles", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "getLocalAssetFiles", "()Ljava/util/Map;", "logger", "Lexpo/modules/updates/logging/UpdatesLogger;", "mSelectionPolicy", "reactNativeHost", "Ljava/lang/ref/WeakReference;", "Lcom/facebook/react/ReactNativeHost;", "remoteLoadStatus", "Lexpo/modules/updates/errorrecovery/ErrorRecoveryDelegate$RemoteLoadStatus;", "selectionPolicy", "getSelectionPolicy", "()Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", UpdatesConfiguration.UPDATES_CONFIGURATION_UPDATE_URL_KEY, "Landroid/net/Uri;", "getUpdateUrl", "()Landroid/net/Uri;", "getUpdatesConfiguration", "()Lexpo/modules/updates/UpdatesConfiguration;", "setUpdatesConfiguration", "(Lexpo/modules/updates/UpdatesConfiguration;)V", "updatesDirectory", "Ljava/io/File;", "getUpdatesDirectory", "()Ljava/io/File;", "setUpdatesDirectory", "(Ljava/io/File;)V", "updatesDirectoryException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "getUpdatesDirectoryException", "()Ljava/lang/Exception;", "setUpdatesDirectoryException", "(Ljava/lang/Exception;)V", "getDatabase", "Lexpo/modules/updates/db/UpdatesDatabase;", "initializeDatabaseHandler", "", "initializeErrorRecovery", "notifyController", "onDidCreateReactInstanceManager", "reactInstanceManager", "Lcom/facebook/react/ReactInstanceManager;", "purgeUpdatesLogsOlderThanOneDay", "relaunchReactApplication", "callback", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "shouldRunReaper", "releaseDatabase", "resetSelectionPolicyToDefault", "runReaper", "setDefaultSelectionPolicy", "setLauncher", "setNextSelectionPolicy", "setReactNativeHost", "setRemoteLoadStatus", "status", "start", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class UpdatesController {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "UpdatesController";
    private static final String UPDATE_AVAILABLE_EVENT = "updateAvailable";
    private static final String UPDATE_ERROR_EVENT = "error";
    private static final String UPDATE_NO_UPDATE_AVAILABLE_EVENT = "noUpdateAvailable";
    private static UpdatesController singletonInstance;
    private Handler databaseHandler;
    private final HandlerThread databaseHandlerThread;
    private final DatabaseHolder databaseHolder;
    private SelectionPolicy defaultSelectionPolicy;
    private final ErrorRecovery errorRecovery;
    private final FileDownloader fileDownloader;
    private boolean isEmergencyLaunch;
    private boolean isLoaderTaskFinished;
    private boolean isStarted;
    private Launcher launcher;
    private LoaderTask loaderTask;
    private final UpdatesLogger logger;
    private SelectionPolicy mSelectionPolicy;
    private WeakReference<ReactNativeHost> reactNativeHost;
    private ErrorRecoveryDelegate.RemoteLoadStatus remoteLoadStatus;
    private UpdatesConfiguration updatesConfiguration;
    private File updatesDirectory;
    private Exception updatesDirectoryException;

    public /* synthetic */ UpdatesController(Context context, UpdatesConfiguration updatesConfiguration, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, updatesConfiguration);
    }

    public static final UpdatesController getInstance() {
        return Companion.getInstance();
    }

    @JvmStatic
    public static final void initialize(Context context) {
        Companion.initialize(context);
    }

    @JvmStatic
    public static final void initialize(Context context, Map<String, ? extends Object> map) {
        Companion.initialize(context, map);
    }

    @JvmStatic
    public static final void initializeWithoutStarting(Context context) {
        Companion.initializeWithoutStarting(context);
    }

    private UpdatesController(Context context, UpdatesConfiguration updatesConfiguration) {
        this.updatesConfiguration = updatesConfiguration;
        this.reactNativeHost = context instanceof ReactApplication ? new WeakReference<>(((ReactApplication) context).getReactNativeHost()) : null;
        this.databaseHolder = new DatabaseHolder(UpdatesDatabase.Companion.getInstance(context));
        this.databaseHandlerThread = new HandlerThread("expo-updates-database");
        this.logger = new UpdatesLogger(context);
        this.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.IDLE;
        this.defaultSelectionPolicy = SelectionPolicyFactory.createFilterAwarePolicy(UpdatesUtils.INSTANCE.getRuntimeVersion(this.updatesConfiguration));
        this.fileDownloader = new FileDownloader(context);
        this.errorRecovery = new ErrorRecovery(context);
        try {
            this.updatesDirectory = UpdatesUtils.INSTANCE.getOrCreateUpdatesDirectory(context);
        } catch (Exception e) {
            this.updatesDirectoryException = e;
            this.updatesDirectory = null;
        }
    }

    public final UpdatesConfiguration getUpdatesConfiguration() {
        return this.updatesConfiguration;
    }

    public final void setUpdatesConfiguration(UpdatesConfiguration updatesConfiguration) {
        Intrinsics.checkNotNullParameter(updatesConfiguration, "<set-?>");
        this.updatesConfiguration = updatesConfiguration;
    }

    public final File getUpdatesDirectory() {
        return this.updatesDirectory;
    }

    public final void setUpdatesDirectory(File file) {
        this.updatesDirectory = file;
    }

    public final Exception getUpdatesDirectoryException() {
        return this.updatesDirectoryException;
    }

    public final void setUpdatesDirectoryException(Exception exc) {
        this.updatesDirectoryException = exc;
    }

    public final DatabaseHolder getDatabaseHolder() {
        return this.databaseHolder;
    }

    private final void initializeDatabaseHandler() {
        if (this.databaseHandler == null) {
            this.databaseHandlerThread.start();
            this.databaseHandler = new Handler(this.databaseHandlerThread.getLooper());
        }
    }

    private final void purgeUpdatesLogsOlderThanOneDay(Context context) {
        UpdatesLogReader.purgeLogEntries$default(new UpdatesLogReader(context), null, new Function1<Error, Unit>() { // from class: expo.modules.updates.UpdatesController$purgeUpdatesLogsOlderThanOneDay$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Error error) {
                invoke2(error);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Error error) {
                String str;
                if (error != null) {
                    str = UpdatesController.TAG;
                    Log.e(str, "UpdatesLogReader: error in purgeLogEntries", error);
                }
            }
        }, 1, null);
    }

    public final FileDownloader getFileDownloader() {
        return this.fileDownloader;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRemoteLoadStatus(ErrorRecoveryDelegate.RemoteLoadStatus remoteLoadStatus) {
        this.remoteLoadStatus = remoteLoadStatus;
        this.errorRecovery.notifyNewRemoteLoadStatus(remoteLoadStatus);
    }

    public final boolean isEmergencyLaunch() {
        return this.isEmergencyLaunch;
    }

    public final void onDidCreateReactInstanceManager(ReactInstanceManager reactInstanceManager) {
        Intrinsics.checkNotNullParameter(reactInstanceManager, "reactInstanceManager");
        if (this.isEmergencyLaunch || !this.updatesConfiguration.isEnabled()) {
            return;
        }
        this.errorRecovery.startMonitoring(reactInstanceManager);
    }

    public final void setReactNativeHost(ReactNativeHost reactNativeHost) {
        Intrinsics.checkNotNullParameter(reactNativeHost, "reactNativeHost");
        this.reactNativeHost = new WeakReference<>(reactNativeHost);
    }

    public final synchronized String getLaunchAssetFile() {
        Launcher launcher;
        while (!this.isLoaderTaskFinished) {
            try {
                wait();
            } catch (InterruptedException e) {
                Log.e(TAG, "Interrupted while waiting for launch asset file", e);
            }
        }
        launcher = this.launcher;
        return launcher == null ? null : launcher.getLaunchAssetFile();
    }

    public final String getBundleAssetName() {
        Launcher launcher = this.launcher;
        if (launcher == null) {
            return null;
        }
        return launcher.getBundleAssetName();
    }

    public final Map<AssetEntity, String> getLocalAssetFiles() {
        Launcher launcher = this.launcher;
        if (launcher == null) {
            return null;
        }
        return launcher.getLocalAssetFiles();
    }

    public final boolean isUsingEmbeddedAssets() {
        Launcher launcher = this.launcher;
        if (launcher == null) {
            return false;
        }
        return launcher.isUsingEmbeddedAssets();
    }

    public final UpdatesDatabase getDatabase() {
        return this.databaseHolder.getDatabase();
    }

    public final void releaseDatabase() {
        this.databaseHolder.releaseDatabase();
    }

    public final Uri getUpdateUrl() {
        return this.updatesConfiguration.getUpdateUrl();
    }

    public final UpdateEntity getLaunchedUpdate() {
        Launcher launcher = this.launcher;
        if (launcher == null) {
            return null;
        }
        return launcher.getLaunchedUpdate();
    }

    public final SelectionPolicy getSelectionPolicy() {
        SelectionPolicy selectionPolicy = this.mSelectionPolicy;
        return selectionPolicy == null ? this.defaultSelectionPolicy : selectionPolicy;
    }

    public final void setNextSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.mSelectionPolicy = selectionPolicy;
    }

    public final void resetSelectionPolicyToDefault() {
        this.mSelectionPolicy = null;
    }

    public final void setDefaultSelectionPolicy(SelectionPolicy selectionPolicy) {
        Intrinsics.checkNotNullParameter(selectionPolicy, "selectionPolicy");
        this.defaultSelectionPolicy = selectionPolicy;
    }

    public final void setLauncher(Launcher launcher) {
        this.launcher = launcher;
    }

    public final synchronized void start(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.isStarted) {
            return;
        }
        this.isStarted = true;
        if (!this.updatesConfiguration.isEnabled()) {
            this.launcher = new NoDatabaseLauncher(context, this.updatesConfiguration, null, 4, null);
            notifyController();
        } else if (this.updatesConfiguration.getUpdateUrl() == null || this.updatesConfiguration.getScopeKey() == null) {
            throw new AssertionError("expo-updates is enabled, but no valid URL is configured in AndroidManifest.xml. If you are making a release build for the first time, make sure you have run `expo publish` at least once.");
        } else {
            if (this.updatesDirectory == null) {
                this.launcher = new NoDatabaseLauncher(context, this.updatesConfiguration, this.updatesDirectoryException);
                this.isEmergencyLaunch = true;
                notifyController();
                return;
            }
            purgeUpdatesLogsOlderThanOneDay(context);
            initializeDatabaseHandler();
            initializeErrorRecovery(context);
            BuildData.INSTANCE.ensureBuildDataIsConsistent(this.updatesConfiguration, getDatabase());
            releaseDatabase();
            LoaderTask loaderTask = new LoaderTask(this.updatesConfiguration, this.databaseHolder, this.updatesDirectory, this.fileDownloader, getSelectionPolicy(), new LoaderTask.LoaderTaskCallback() { // from class: expo.modules.updates.UpdatesController$start$1

                /* compiled from: UpdatesController.kt */
                @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
                /* loaded from: classes5.dex */
                public /* synthetic */ class WhenMappings {
                    public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                    static {
                        int[] r0 = new int[LoaderTask.BackgroundUpdateStatus.values().length];
                        r0[LoaderTask.BackgroundUpdateStatus.ERROR.ordinal()] = 1;
                        r0[LoaderTask.BackgroundUpdateStatus.UPDATE_AVAILABLE.ordinal()] = 2;
                        r0[LoaderTask.BackgroundUpdateStatus.NO_UPDATE_AVAILABLE.ordinal()] = 3;
                        $EnumSwitchMapping$0 = r0;
                    }
                }

                @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
                public boolean onCachedUpdateLoaded(UpdateEntity update) {
                    Intrinsics.checkNotNullParameter(update, "update");
                    return true;
                }

                @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
                public void onFailure(Exception e) {
                    UpdatesLogger updatesLogger;
                    Intrinsics.checkNotNullParameter(e, "e");
                    updatesLogger = UpdatesController.this.logger;
                    String localizedMessage = e.getLocalizedMessage();
                    UpdatesLogger.error$default(updatesLogger, "UpdatesController loaderTask onFailure: " + localizedMessage, UpdatesErrorCode.None, null, 4, null);
                    UpdatesController.this.launcher = new NoDatabaseLauncher(context, UpdatesController.this.getUpdatesConfiguration(), e);
                    UpdatesController.this.isEmergencyLaunch = true;
                    UpdatesController.this.notifyController();
                }

                @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
                public void onRemoteUpdateManifestLoaded(UpdateManifest updateManifest) {
                    Intrinsics.checkNotNullParameter(updateManifest, "updateManifest");
                    UpdatesController.this.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADING;
                }

                @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
                public void onSuccess(Launcher launcher, boolean z) {
                    ErrorRecoveryDelegate.RemoteLoadStatus remoteLoadStatus;
                    Intrinsics.checkNotNullParameter(launcher, "launcher");
                    remoteLoadStatus = UpdatesController.this.remoteLoadStatus;
                    if (remoteLoadStatus == ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADING && z) {
                        UpdatesController.this.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.IDLE;
                    }
                    UpdatesController.this.launcher = launcher;
                    UpdatesController.this.notifyController();
                }

                @Override // expo.modules.updates.loader.LoaderTask.LoaderTaskCallback
                public void onBackgroundUpdateFinished(LoaderTask.BackgroundUpdateStatus status, UpdateEntity updateEntity, Exception exc) {
                    UpdatesLogger updatesLogger;
                    WeakReference<ReactNativeHost> weakReference;
                    ErrorRecovery errorRecovery;
                    ErrorRecoveryDelegate.RemoteLoadStatus remoteLoadStatus;
                    UpdatesLogger updatesLogger2;
                    WeakReference<ReactNativeHost> weakReference2;
                    UpdatesLogger updatesLogger3;
                    WeakReference<ReactNativeHost> weakReference3;
                    Intrinsics.checkNotNullParameter(status, "status");
                    int r7 = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
                    if (r7 != 1) {
                        if (r7 != 2) {
                            if (r7 == 3) {
                                UpdatesController.this.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.IDLE;
                                updatesLogger3 = UpdatesController.this.logger;
                                UpdatesLogger.error$default(updatesLogger3, "UpdatesController onBackgroundUpdateFinished: No update available", UpdatesErrorCode.NoUpdatesAvailable, null, 4, null);
                                UpdatesUtils updatesUtils = UpdatesUtils.INSTANCE;
                                weakReference3 = UpdatesController.this.reactNativeHost;
                                updatesUtils.sendEventToReactNative(weakReference3, "noUpdateAvailable", null);
                            }
                        } else if (updateEntity == null) {
                            throw new AssertionError("Background update with error status must have a nonnull update object");
                        } else {
                            UpdatesController.this.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.NEW_UPDATE_LOADED;
                            updatesLogger2 = UpdatesController.this.logger;
                            updatesLogger2.info("UpdatesController onBackgroundUpdateFinished: Update available", UpdatesErrorCode.None);
                            WritableMap createMap = Arguments.createMap();
                            createMap.putString("manifestString", String.valueOf(updateEntity.getManifest()));
                            UpdatesUtils updatesUtils2 = UpdatesUtils.INSTANCE;
                            weakReference2 = UpdatesController.this.reactNativeHost;
                            updatesUtils2.sendEventToReactNative(weakReference2, "updateAvailable", createMap);
                        }
                    } else if (exc != null) {
                        updatesLogger = UpdatesController.this.logger;
                        String localizedMessage = exc.getLocalizedMessage();
                        updatesLogger.error("UpdatesController onBackgroundUpdateFinished: Error: " + localizedMessage, UpdatesErrorCode.Unknown, exc);
                        UpdatesController.this.remoteLoadStatus = ErrorRecoveryDelegate.RemoteLoadStatus.IDLE;
                        WritableMap createMap2 = Arguments.createMap();
                        createMap2.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, exc.getMessage());
                        UpdatesUtils updatesUtils3 = UpdatesUtils.INSTANCE;
                        weakReference = UpdatesController.this.reactNativeHost;
                        updatesUtils3.sendEventToReactNative(weakReference, "error", createMap2);
                    } else {
                        throw new AssertionError("Background update with error status must have a nonnull exception object");
                    }
                    errorRecovery = UpdatesController.this.errorRecovery;
                    remoteLoadStatus = UpdatesController.this.remoteLoadStatus;
                    errorRecovery.notifyNewRemoteLoadStatus(remoteLoadStatus);
                }
            });
            this.loaderTask = loaderTask;
            Intrinsics.checkNotNull(loaderTask);
            loaderTask.start(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void notifyController() {
        if (this.launcher == null) {
            throw new AssertionError("UpdatesController.notifyController was called with a null launcher, which is an error. This method should only be called when an update is ready to launch.");
        }
        this.isLoaderTaskFinished = true;
        notify();
    }

    public final void initializeErrorRecovery(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.errorRecovery.initialize(new UpdatesController$initializeErrorRecovery$1(this, context));
    }

    public final void runReaper() {
        AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.UpdatesController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                UpdatesController.m1711runReaper$lambda0(UpdatesController.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: runReaper$lambda-0  reason: not valid java name */
    public static final void m1711runReaper$lambda0(UpdatesController this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Reaper.reapUnusedUpdates(this$0.updatesConfiguration, this$0.getDatabase(), this$0.updatesDirectory, this$0.getLaunchedUpdate(), this$0.getSelectionPolicy());
        this$0.releaseDatabase();
    }

    public final void relaunchReactApplication(Context context, Launcher.LauncherCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(callback, "callback");
        relaunchReactApplication(context, true, callback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void relaunchReactApplication(Context context, boolean z, Launcher.LauncherCallback launcherCallback) {
        WeakReference<ReactNativeHost> weakReference = this.reactNativeHost;
        ReactNativeHost reactNativeHost = weakReference == null ? null : weakReference.get();
        if (reactNativeHost == null) {
            launcherCallback.onFailure(new Exception("Could not reload application. Ensure you have passed the correct instance of ReactApplication into UpdatesController.initialize()."));
            return;
        }
        Launcher launcher = this.launcher;
        Intrinsics.checkNotNull(launcher);
        String launchAssetFile = launcher.getLaunchAssetFile();
        UpdatesDatabase database = getDatabase();
        UpdatesConfiguration updatesConfiguration = this.updatesConfiguration;
        File file = this.updatesDirectory;
        Intrinsics.checkNotNull(file);
        DatabaseLauncher databaseLauncher = new DatabaseLauncher(updatesConfiguration, file, this.fileDownloader, getSelectionPolicy());
        databaseLauncher.launch(database, context, new UpdatesController$relaunchReactApplication$1(launcherCallback, this, databaseLauncher, reactNativeHost, launchAssetFile, z));
    }

    /* compiled from: UpdatesController.kt */
    @Metadata(m184d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007J$\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0014H\u0007J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\n8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m183d2 = {"Lexpo/modules/updates/UpdatesController$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "UPDATE_AVAILABLE_EVENT", "UPDATE_ERROR_EVENT", "UPDATE_NO_UPDATE_AVAILABLE_EVENT", "instance", "Lexpo/modules/updates/UpdatesController;", "getInstance$annotations", "getInstance", "()Lexpo/modules/updates/UpdatesController;", "singletonInstance", "initialize", "", "context", "Landroid/content/Context;", "configuration", "", "initializeWithoutStarting", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        private Companion() {
        }

        public final UpdatesController getInstance() {
            UpdatesController updatesController = UpdatesController.singletonInstance;
            if (updatesController != null) {
                return updatesController;
            }
            throw new IllegalStateException("UpdatesController.instance was called before the module was initialized".toString());
        }

        @JvmStatic
        public final void initializeWithoutStarting(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (UpdatesController.singletonInstance == null) {
                UpdatesController.singletonInstance = new UpdatesController(context, new UpdatesConfiguration(context, null), null);
            }
        }

        @JvmStatic
        public final void initialize(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (UpdatesController.singletonInstance == null) {
                initializeWithoutStarting(context);
                UpdatesController updatesController = UpdatesController.singletonInstance;
                Intrinsics.checkNotNull(updatesController);
                updatesController.start(context);
            }
        }

        @JvmStatic
        public final void initialize(Context context, Map<String, ? extends Object> configuration) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(configuration, "configuration");
            if (UpdatesController.singletonInstance == null) {
                UpdatesController.singletonInstance = new UpdatesController(context, new UpdatesConfiguration(context, configuration), null);
                UpdatesController updatesController = UpdatesController.singletonInstance;
                Intrinsics.checkNotNull(updatesController);
                updatesController.start(context);
            }
        }
    }
}
