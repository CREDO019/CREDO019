package expo.modules.updates.loader;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.polidea.rxandroidble.ClientComponent;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.UpdatesUtils;
import expo.modules.updates.launcher.DatabaseLauncher;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.Loader;
import expo.modules.updates.loader.LoaderTask;
import expo.modules.updates.manifest.EmbeddedManifest;
import expo.modules.updates.manifest.ManifestMetadata;
import expo.modules.updates.manifest.UpdateManifest;
import expo.modules.updates.p021db.DatabaseHolder;
import expo.modules.updates.p021db.Reaper;
import expo.modules.updates.p021db.UpdatesDatabase;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoaderTask.kt */
@Metadata(m184d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 /2\u00020\u0001:\u0004-./0B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0018\u0010\u001c\u001a\u00020\u001d2\u000e\u0010\u001e\u001a\n\u0018\u00010\u001fj\u0004\u0018\u0001` H\u0002J\u0018\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0018\u0010&\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\u0006\u0010'\u001a\u00020%H\u0002J\b\u0010(\u001a\u00020\u001dH\u0002J\b\u0010)\u001a\u00020\u001dH\u0002J\u000e\u0010*\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#J\b\u0010+\u001a\u00020\u001dH\u0002J\b\u0010,\u001a\u00020\u001dH\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, m183d2 = {"Lexpo/modules/updates/loader/LoaderTask;", "", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "databaseHolder", "Lexpo/modules/updates/db/DatabaseHolder;", "directory", "Ljava/io/File;", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "selectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "callback", "Lexpo/modules/updates/loader/LoaderTask$LoaderTaskCallback;", "(Lexpo/modules/updates/UpdatesConfiguration;Lexpo/modules/updates/db/DatabaseHolder;Ljava/io/File;Lexpo/modules/updates/loader/FileDownloader;Lexpo/modules/updates/selectionpolicy/SelectionPolicy;Lexpo/modules/updates/loader/LoaderTask$LoaderTaskCallback;)V", "candidateLauncher", "Lexpo/modules/updates/launcher/Launcher;", "finalizedLauncher", "handlerThread", "Landroid/os/HandlerThread;", "hasLaunched", "", "isReadyToLaunch", "<set-?>", "isRunning", "()Z", "isUpToDate", "timeoutFinished", "finish", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "launchFallbackUpdateFromDisk", "context", "Landroid/content/Context;", "diskUpdateCallback", "Lexpo/modules/updates/loader/LoaderTask$Callback;", "launchRemoteUpdateInBackground", "remoteUpdateCallback", "maybeFinish", "runReaper", "start", "stopTimer", ClientComponent.NamedSchedulers.TIMEOUT, "BackgroundUpdateStatus", "Callback", "Companion", "LoaderTaskCallback", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class LoaderTask {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "LoaderTask";
    private final LoaderTaskCallback callback;
    private Launcher candidateLauncher;
    private final UpdatesConfiguration configuration;
    private final DatabaseHolder databaseHolder;
    private final File directory;
    private final FileDownloader fileDownloader;
    private Launcher finalizedLauncher;
    private final HandlerThread handlerThread;
    private boolean hasLaunched;
    private boolean isReadyToLaunch;
    private boolean isRunning;
    private boolean isUpToDate;
    private final SelectionPolicy selectionPolicy;
    private boolean timeoutFinished;

    /* compiled from: LoaderTask.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/updates/loader/LoaderTask$BackgroundUpdateStatus;", "", "(Ljava/lang/String;I)V", "ERROR", "NO_UPDATE_AVAILABLE", "UPDATE_AVAILABLE", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public enum BackgroundUpdateStatus {
        ERROR,
        NO_UPDATE_AVAILABLE,
        UPDATE_AVAILABLE
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: LoaderTask.kt */
    @Metadata(m184d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bb\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&¨\u0006\b"}, m183d2 = {"Lexpo/modules/updates/loader/LoaderTask$Callback;", "", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public interface Callback {
        void onFailure(Exception exc);

        void onSuccess();
    }

    /* compiled from: LoaderTask.kt */
    @Metadata(m184d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\u0010\b\u001a\n\u0018\u00010\tj\u0004\u0018\u0001`\nH&J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0014\u0010\r\u001a\u00020\u00032\n\u0010\u000e\u001a\u00060\tj\u0002`\nH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0018\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\fH&¨\u0006\u0016"}, m183d2 = {"Lexpo/modules/updates/loader/LoaderTask$LoaderTaskCallback;", "", "onBackgroundUpdateFinished", "", "status", "Lexpo/modules/updates/loader/LoaderTask$BackgroundUpdateStatus;", "update", "Lexpo/modules/updates/db/entity/UpdateEntity;", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onCachedUpdateLoaded", "", "onFailure", "e", "onRemoteUpdateManifestLoaded", "updateManifest", "Lexpo/modules/updates/manifest/UpdateManifest;", "onSuccess", "launcher", "Lexpo/modules/updates/launcher/Launcher;", "isUpToDate", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public interface LoaderTaskCallback {
        void onBackgroundUpdateFinished(BackgroundUpdateStatus backgroundUpdateStatus, UpdateEntity updateEntity, Exception exc);

        boolean onCachedUpdateLoaded(UpdateEntity updateEntity);

        void onFailure(Exception exc);

        void onRemoteUpdateManifestLoaded(UpdateManifest updateManifest);

        void onSuccess(Launcher launcher, boolean z);
    }

    public LoaderTask(UpdatesConfiguration configuration, DatabaseHolder databaseHolder, File file, FileDownloader fileDownloader, SelectionPolicy selectionPolicy, LoaderTaskCallback callback) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(databaseHolder, "databaseHolder");
        Intrinsics.checkNotNullParameter(fileDownloader, "fileDownloader");
        Intrinsics.checkNotNullParameter(selectionPolicy, "selectionPolicy");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.configuration = configuration;
        this.databaseHolder = databaseHolder;
        this.directory = file;
        this.fileDownloader = fileDownloader;
        this.selectionPolicy = selectionPolicy;
        this.callback = callback;
        this.handlerThread = new HandlerThread("expo-updates-timer");
    }

    public final boolean isRunning() {
        return this.isRunning;
    }

    public final void start(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!this.configuration.isEnabled()) {
            this.callback.onFailure(new Exception("LoaderTask was passed a configuration object with updates disabled. You should load updates from an embedded source rather than calling LoaderTask, or enable updates in the configuration."));
        } else if (this.configuration.getUpdateUrl() == null) {
            this.callback.onFailure(new Exception("LoaderTask was passed a configuration object with a null URL. You must pass a nonnull URL in order to use LoaderTask to load updates."));
        } else if (this.directory == null) {
            throw new AssertionError("LoaderTask directory must be nonnull.");
        } else {
            this.isRunning = true;
            final boolean shouldCheckForUpdateOnLaunch = UpdatesUtils.INSTANCE.shouldCheckForUpdateOnLaunch(this.configuration, context);
            int launchWaitMs = this.configuration.getLaunchWaitMs();
            if (launchWaitMs > 0 && shouldCheckForUpdateOnLaunch) {
                this.handlerThread.start();
                new Handler(this.handlerThread.getLooper()).postDelayed(new Runnable() { // from class: expo.modules.updates.loader.LoaderTask$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        LoaderTask.m1738start$lambda0(LoaderTask.this);
                    }
                }, launchWaitMs);
            } else {
                this.timeoutFinished = true;
            }
            launchFallbackUpdateFromDisk(context, new Callback() { // from class: expo.modules.updates.loader.LoaderTask$start$2
                private final void launchRemoteUpdate() {
                    final LoaderTask loaderTask = LoaderTask.this;
                    loaderTask.launchRemoteUpdateInBackground(context, new LoaderTask.Callback() { // from class: expo.modules.updates.loader.LoaderTask$start$2$launchRemoteUpdate$1
                        @Override // expo.modules.updates.loader.LoaderTask.Callback
                        public void onFailure(Exception e) {
                            Intrinsics.checkNotNullParameter(e, "e");
                            LoaderTask.this.finish(e);
                            LoaderTask.this.isRunning = false;
                            LoaderTask.this.runReaper();
                        }

                        @Override // expo.modules.updates.loader.LoaderTask.Callback
                        public void onSuccess() {
                            LoaderTask loaderTask2 = LoaderTask.this;
                            synchronized (loaderTask2) {
                                loaderTask2.isReadyToLaunch = true;
                                Unit unit = Unit.INSTANCE;
                            }
                            LoaderTask.this.finish(null);
                            LoaderTask.this.isRunning = false;
                            LoaderTask.this.runReaper();
                        }
                    });
                }

                @Override // expo.modules.updates.loader.LoaderTask.Callback
                public void onFailure(Exception e) {
                    String str;
                    Intrinsics.checkNotNullParameter(e, "e");
                    if (!shouldCheckForUpdateOnLaunch) {
                        LoaderTask.this.finish(e);
                        LoaderTask.this.isRunning = false;
                    } else {
                        launchRemoteUpdate();
                    }
                    str = LoaderTask.TAG;
                    Log.e(str, "Failed to launch embedded or launchable update", e);
                }

                @Override // expo.modules.updates.loader.LoaderTask.Callback
                public void onSuccess() {
                    Launcher launcher;
                    LoaderTask.LoaderTaskCallback loaderTaskCallback;
                    Launcher launcher2;
                    launcher = LoaderTask.this.candidateLauncher;
                    Intrinsics.checkNotNull(launcher);
                    if (launcher.getLaunchedUpdate() != null) {
                        loaderTaskCallback = LoaderTask.this.callback;
                        launcher2 = LoaderTask.this.candidateLauncher;
                        Intrinsics.checkNotNull(launcher2);
                        UpdateEntity launchedUpdate = launcher2.getLaunchedUpdate();
                        Intrinsics.checkNotNull(launchedUpdate);
                        if (!loaderTaskCallback.onCachedUpdateLoaded(launchedUpdate)) {
                            LoaderTask.this.stopTimer();
                            LoaderTask.this.candidateLauncher = null;
                            launchRemoteUpdate();
                            return;
                        }
                    }
                    LoaderTask loaderTask = LoaderTask.this;
                    synchronized (loaderTask) {
                        loaderTask.isReadyToLaunch = true;
                        loaderTask.maybeFinish();
                        Unit unit = Unit.INSTANCE;
                    }
                    if (!shouldCheckForUpdateOnLaunch) {
                        LoaderTask.this.isRunning = false;
                        LoaderTask.this.runReaper();
                        return;
                    }
                    launchRemoteUpdate();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: start$lambda-0  reason: not valid java name */
    public static final void m1738start$lambda0(LoaderTask this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.timeout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x003f A[Catch: all -> 0x004f, TryCatch #0 {, blocks: (B:3:0x0001, B:8:0x0008, B:11:0x0014, B:14:0x001e, B:20:0x003b, B:22:0x003f, B:24:0x0044, B:15:0x002b, B:17:0x002f, B:19:0x0038), top: B:30:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0044 A[Catch: all -> 0x004f, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:8:0x0008, B:11:0x0014, B:14:0x001e, B:20:0x003b, B:22:0x003f, B:24:0x0044, B:15:0x002b, B:17:0x002f, B:19:0x0038), top: B:30:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized void finish(java.lang.Exception r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.hasLaunched     // Catch: java.lang.Throwable -> L4f
            if (r0 == 0) goto L7
            monitor-exit(r3)
            return
        L7:
            r0 = 1
            r3.hasLaunched = r0     // Catch: java.lang.Throwable -> L4f
            expo.modules.updates.launcher.Launcher r0 = r3.candidateLauncher     // Catch: java.lang.Throwable -> L4f
            r3.finalizedLauncher = r0     // Catch: java.lang.Throwable -> L4f
            boolean r1 = r3.isReadyToLaunch     // Catch: java.lang.Throwable -> L4f
            if (r1 == 0) goto L2b
            if (r0 == 0) goto L2b
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch: java.lang.Throwable -> L4f
            expo.modules.updates.db.entity.UpdateEntity r0 = r0.getLaunchedUpdate()     // Catch: java.lang.Throwable -> L4f
            if (r0 != 0) goto L1e
            goto L2b
        L1e:
            expo.modules.updates.loader.LoaderTask$LoaderTaskCallback r0 = r3.callback     // Catch: java.lang.Throwable -> L4f
            expo.modules.updates.launcher.Launcher r1 = r3.finalizedLauncher     // Catch: java.lang.Throwable -> L4f
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Throwable -> L4f
            boolean r2 = r3.isUpToDate     // Catch: java.lang.Throwable -> L4f
            r0.onSuccess(r1, r2)     // Catch: java.lang.Throwable -> L4f
            goto L3b
        L2b:
            expo.modules.updates.loader.LoaderTask$LoaderTaskCallback r0 = r3.callback     // Catch: java.lang.Throwable -> L4f
            if (r4 != 0) goto L37
            java.lang.Exception r1 = new java.lang.Exception     // Catch: java.lang.Throwable -> L4f
            java.lang.String r2 = "LoaderTask encountered an unexpected error and could not launch an update."
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L4f
            goto L38
        L37:
            r1 = r4
        L38:
            r0.onFailure(r1)     // Catch: java.lang.Throwable -> L4f
        L3b:
            boolean r0 = r3.timeoutFinished     // Catch: java.lang.Throwable -> L4f
            if (r0 != 0) goto L42
            r3.stopTimer()     // Catch: java.lang.Throwable -> L4f
        L42:
            if (r4 == 0) goto L4d
            java.lang.String r0 = expo.modules.updates.loader.LoaderTask.TAG     // Catch: java.lang.Throwable -> L4f
            java.lang.String r1 = "Unexpected error encountered while loading this app"
            java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch: java.lang.Throwable -> L4f
            android.util.Log.e(r0, r1, r4)     // Catch: java.lang.Throwable -> L4f
        L4d:
            monitor-exit(r3)
            return
        L4f:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.updates.loader.LoaderTask.finish(java.lang.Exception):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void maybeFinish() {
        if (this.isReadyToLaunch && this.timeoutFinished) {
            finish(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void stopTimer() {
        this.timeoutFinished = true;
        this.handlerThread.quitSafely();
    }

    private final synchronized void timeout() {
        if (!this.timeoutFinished) {
            this.timeoutFinished = true;
            maybeFinish();
        }
        stopTimer();
    }

    private final void launchFallbackUpdateFromDisk(final Context context, final Callback callback) {
        final UpdatesDatabase database = this.databaseHolder.getDatabase();
        final DatabaseLauncher databaseLauncher = new DatabaseLauncher(this.configuration, this.directory, this.fileDownloader, this.selectionPolicy);
        this.candidateLauncher = databaseLauncher;
        final Launcher.LauncherCallback launcherCallback = new Launcher.LauncherCallback() { // from class: expo.modules.updates.loader.LoaderTask$launchFallbackUpdateFromDisk$launcherCallback$1
            @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
            public void onFailure(Exception e) {
                DatabaseHolder databaseHolder;
                Intrinsics.checkNotNullParameter(e, "e");
                databaseHolder = LoaderTask.this.databaseHolder;
                databaseHolder.releaseDatabase();
                callback.onFailure(e);
            }

            @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
            public void onSuccess() {
                DatabaseHolder databaseHolder;
                databaseHolder = LoaderTask.this.databaseHolder;
                databaseHolder.releaseDatabase();
                callback.onSuccess();
            }
        };
        if (this.configuration.getHasEmbeddedUpdate()) {
            UpdateManifest updateManifest = EmbeddedManifest.INSTANCE.get(context, this.configuration);
            Intrinsics.checkNotNull(updateManifest);
            if (this.selectionPolicy.shouldLoadNewUpdate(updateManifest.getUpdateEntity(), databaseLauncher.getLaunchableUpdate(database, context), ManifestMetadata.INSTANCE.getManifestFilters(database, this.configuration))) {
                new EmbeddedLoader(context, this.configuration, database, this.directory).start(new Loader.LoaderCallback() { // from class: expo.modules.updates.loader.LoaderTask$launchFallbackUpdateFromDisk$1
                    @Override // expo.modules.updates.loader.Loader.LoaderCallback
                    public void onAssetLoaded(AssetEntity asset, int r2, int r3, int r4) {
                        Intrinsics.checkNotNullParameter(asset, "asset");
                    }

                    @Override // expo.modules.updates.loader.Loader.LoaderCallback
                    public boolean onUpdateManifestLoaded(UpdateManifest updateManifest2) {
                        Intrinsics.checkNotNullParameter(updateManifest2, "updateManifest");
                        return true;
                    }

                    @Override // expo.modules.updates.loader.Loader.LoaderCallback
                    public void onFailure(Exception e) {
                        String str;
                        Intrinsics.checkNotNullParameter(e, "e");
                        str = LoaderTask.TAG;
                        Log.e(str, "Unexpected error copying embedded update", e);
                        DatabaseLauncher.this.launch(database, context, launcherCallback);
                    }

                    @Override // expo.modules.updates.loader.Loader.LoaderCallback
                    public void onSuccess(UpdateEntity updateEntity) {
                        DatabaseLauncher.this.launch(database, context, launcherCallback);
                    }
                });
                return;
            } else {
                databaseLauncher.launch(database, context, launcherCallback);
                return;
            }
        }
        databaseLauncher.launch(database, context, launcherCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchRemoteUpdateInBackground(final Context context, final Callback callback) {
        AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.loader.LoaderTask$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                LoaderTask.m1736launchRemoteUpdateInBackground$lambda1(LoaderTask.this, context, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: launchRemoteUpdateInBackground$lambda-1  reason: not valid java name */
    public static final void m1736launchRemoteUpdateInBackground$lambda1(final LoaderTask this$0, final Context context, final Callback remoteUpdateCallback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(remoteUpdateCallback, "$remoteUpdateCallback");
        final UpdatesDatabase database = this$0.databaseHolder.getDatabase();
        UpdatesConfiguration updatesConfiguration = this$0.configuration;
        FileDownloader fileDownloader = this$0.fileDownloader;
        File file = this$0.directory;
        Launcher launcher = this$0.candidateLauncher;
        new RemoteLoader(context, updatesConfiguration, database, fileDownloader, file, launcher == null ? null : launcher.getLaunchedUpdate()).start(new Loader.LoaderCallback() { // from class: expo.modules.updates.loader.LoaderTask$launchRemoteUpdateInBackground$1$1
            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onAssetLoaded(AssetEntity asset, int r2, int r3, int r4) {
                Intrinsics.checkNotNullParameter(asset, "asset");
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onFailure(Exception e) {
                DatabaseHolder databaseHolder;
                LoaderTask.LoaderTaskCallback loaderTaskCallback;
                String str;
                Intrinsics.checkNotNullParameter(e, "e");
                databaseHolder = LoaderTask.this.databaseHolder;
                databaseHolder.releaseDatabase();
                remoteUpdateCallback.onFailure(e);
                loaderTaskCallback = LoaderTask.this.callback;
                loaderTaskCallback.onBackgroundUpdateFinished(LoaderTask.BackgroundUpdateStatus.ERROR, null, e);
                str = LoaderTask.TAG;
                Log.e(str, "Failed to download remote update", e);
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public boolean onUpdateManifestLoaded(UpdateManifest updateManifest) {
                SelectionPolicy selectionPolicy;
                Launcher launcher2;
                LoaderTask.LoaderTaskCallback loaderTaskCallback;
                Intrinsics.checkNotNullParameter(updateManifest, "updateManifest");
                selectionPolicy = LoaderTask.this.selectionPolicy;
                UpdateEntity updateEntity = updateManifest.getUpdateEntity();
                launcher2 = LoaderTask.this.candidateLauncher;
                if (selectionPolicy.shouldLoadNewUpdate(updateEntity, launcher2 == null ? null : launcher2.getLaunchedUpdate(), updateManifest.getManifestFilters())) {
                    LoaderTask.this.isUpToDate = false;
                    loaderTaskCallback = LoaderTask.this.callback;
                    loaderTaskCallback.onRemoteUpdateManifestLoaded(updateManifest);
                    return true;
                }
                LoaderTask.this.isUpToDate = true;
                return false;
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onSuccess(final UpdateEntity updateEntity) {
                UpdatesConfiguration updatesConfiguration2;
                File file2;
                FileDownloader fileDownloader2;
                SelectionPolicy selectionPolicy;
                updatesConfiguration2 = LoaderTask.this.configuration;
                file2 = LoaderTask.this.directory;
                fileDownloader2 = LoaderTask.this.fileDownloader;
                selectionPolicy = LoaderTask.this.selectionPolicy;
                final DatabaseLauncher databaseLauncher = new DatabaseLauncher(updatesConfiguration2, file2, fileDownloader2, selectionPolicy);
                UpdatesDatabase updatesDatabase = database;
                Context context2 = context;
                final LoaderTask loaderTask = LoaderTask.this;
                final LoaderTask.Callback callback = remoteUpdateCallback;
                databaseLauncher.launch(updatesDatabase, context2, new Launcher.LauncherCallback() { // from class: expo.modules.updates.loader.LoaderTask$launchRemoteUpdateInBackground$1$1$onSuccess$1
                    @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
                    public void onFailure(Exception e) {
                        DatabaseHolder databaseHolder;
                        String str;
                        Intrinsics.checkNotNullParameter(e, "e");
                        databaseHolder = LoaderTask.this.databaseHolder;
                        databaseHolder.releaseDatabase();
                        callback.onFailure(e);
                        str = LoaderTask.TAG;
                        Log.e(str, "Loaded new update but it failed to launch", e);
                    }

                    @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
                    public void onSuccess() {
                        DatabaseHolder databaseHolder;
                        boolean z;
                        boolean z2;
                        LoaderTask.LoaderTaskCallback loaderTaskCallback;
                        LoaderTask.LoaderTaskCallback loaderTaskCallback2;
                        databaseHolder = LoaderTask.this.databaseHolder;
                        databaseHolder.releaseDatabase();
                        LoaderTask loaderTask2 = LoaderTask.this;
                        DatabaseLauncher databaseLauncher2 = databaseLauncher;
                        synchronized (loaderTask2) {
                            z = loaderTask2.hasLaunched;
                            if (!z) {
                                loaderTask2.candidateLauncher = databaseLauncher2;
                                loaderTask2.isUpToDate = true;
                            }
                            z2 = loaderTask2.hasLaunched;
                        }
                        callback.onSuccess();
                        if (z2) {
                            if (updateEntity == null) {
                                loaderTaskCallback2 = LoaderTask.this.callback;
                                loaderTaskCallback2.onBackgroundUpdateFinished(LoaderTask.BackgroundUpdateStatus.NO_UPDATE_AVAILABLE, null, null);
                                return;
                            }
                            loaderTaskCallback = LoaderTask.this.callback;
                            loaderTaskCallback.onBackgroundUpdateFinished(LoaderTask.BackgroundUpdateStatus.UPDATE_AVAILABLE, updateEntity, null);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runReaper() {
        AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.loader.LoaderTask$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                LoaderTask.m1737runReaper$lambda3(LoaderTask.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: runReaper$lambda-3  reason: not valid java name */
    public static final void m1737runReaper$lambda3(LoaderTask this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        synchronized (this$0) {
            Launcher launcher = this$0.finalizedLauncher;
            if (launcher != null) {
                Intrinsics.checkNotNull(launcher);
                if (launcher.getLaunchedUpdate() != null) {
                    UpdatesDatabase database = this$0.databaseHolder.getDatabase();
                    UpdatesConfiguration updatesConfiguration = this$0.configuration;
                    File file = this$0.directory;
                    Launcher launcher2 = this$0.finalizedLauncher;
                    Intrinsics.checkNotNull(launcher2);
                    Reaper.reapUnusedUpdates(updatesConfiguration, database, file, launcher2.getLaunchedUpdate(), this$0.selectionPolicy);
                    this$0.databaseHolder.releaseDatabase();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* compiled from: LoaderTask.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/updates/loader/LoaderTask$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
