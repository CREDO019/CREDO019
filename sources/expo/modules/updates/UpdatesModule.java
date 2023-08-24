package expo.modules.updates;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.facebook.react.bridge.BaseJavaModule;
import com.onesignal.OneSignalDbContract;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ModuleRegistryDelegate;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.loader.Loader;
import expo.modules.updates.loader.RemoteLoader;
import expo.modules.updates.logging.UpdatesErrorCode;
import expo.modules.updates.logging.UpdatesLogEntry;
import expo.modules.updates.logging.UpdatesLogReader;
import expo.modules.updates.logging.UpdatesLogger;
import expo.modules.updates.manifest.UpdateManifest;
import expo.modules.updates.p021db.DatabaseHolder;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* compiled from: UpdatesModule.kt */
@Metadata(m184d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0014\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014H\u0016J\b\u0010\u0017\u001a\u00020\u0015H\u0016J\u001f\u0010\u0018\u001a\u0010\u0012\f\u0012\n \u001b*\u0004\u0018\u0001H\u001aH\u001a0\u0019\"\u0006\b\u0000\u0010\u001a\u0018\u0001H\u0082\bJ\u0010\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010!\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u0004\u0018\u00010\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006#"}, m183d2 = {"Lexpo/modules/updates/UpdatesModule;", "Lexpo/modules/core/ExportedModule;", "context", "Landroid/content/Context;", "moduleRegistryDelegate", "Lexpo/modules/core/ModuleRegistryDelegate;", "(Landroid/content/Context;Lexpo/modules/core/ModuleRegistryDelegate;)V", "updatesService", "Lexpo/modules/updates/UpdatesInterface;", "getUpdatesService", "()Lexpo/modules/updates/UpdatesInterface;", "updatesService$delegate", "Lkotlin/Lazy;", "checkForUpdateAsync", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "clearLogEntriesAsync", "fetchUpdateAsync", "getConstants", "", "", "", "getName", "moduleRegistry", "Lkotlin/Lazy;", "T", "kotlin.jvm.PlatformType", "onCreate", "Lexpo/modules/core/ModuleRegistry;", "readLogEntriesAsync", "maxAge", "", "reload", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class UpdatesModule extends ExportedModule {
    private static final String NAME = "ExpoUpdates";
    private final ModuleRegistryDelegate moduleRegistryDelegate;
    private final Lazy updatesService$delegate;
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "UpdatesModule";

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return NAME;
    }

    public /* synthetic */ UpdatesModule(Context context, ModuleRegistryDelegate moduleRegistryDelegate, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (r3 & 2) != 0 ? new ModuleRegistryDelegate() : moduleRegistryDelegate);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdatesModule(Context context, final ModuleRegistryDelegate moduleRegistryDelegate) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(moduleRegistryDelegate, "moduleRegistryDelegate");
        this.moduleRegistryDelegate = moduleRegistryDelegate;
        this.updatesService$delegate = LazyKt.lazy(new Functions<UpdatesInterface>() { // from class: expo.modules.updates.UpdatesModule$special$$inlined$moduleRegistry$1
            {
                super(0);
            }

            /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, expo.modules.updates.UpdatesInterface] */
            @Override // kotlin.jvm.functions.Functions
            public final UpdatesInterface invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                return moduleRegistry.getModule(UpdatesInterface.class);
            }
        });
    }

    private final /* synthetic */ <T> Lazy<T> moduleRegistry() {
        final ModuleRegistryDelegate moduleRegistryDelegate = this.moduleRegistryDelegate;
        Intrinsics.needClassReification();
        return LazyKt.lazy(new Functions<T>() { // from class: expo.modules.updates.UpdatesModule$moduleRegistry$$inlined$getFromModuleRegistry$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final T invoke() {
                ModuleRegistry moduleRegistry = ModuleRegistryDelegate.this.getModuleRegistry();
                Intrinsics.checkNotNull(moduleRegistry);
                Intrinsics.reifiedOperationMarker(4, "T");
                return (T) moduleRegistry.getModule(Object.class);
            }
        });
    }

    private final UpdatesInterface getUpdatesService() {
        return (UpdatesInterface) this.updatesService$delegate.getValue();
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.moduleRegistryDelegate.onCreate(moduleRegistry);
    }

    @Override // expo.modules.core.ExportedModule
    public Map<String, Object> getConstants() {
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        new UpdatesLogger(context).info("UpdatesModule: getConstants called", UpdatesErrorCode.None);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        try {
            UpdatesInterface updatesService = getUpdatesService();
            if (updatesService != null) {
                linkedHashMap.put("isEmergencyLaunch", Boolean.valueOf(updatesService.isEmergencyLaunch()));
                linkedHashMap.put("isEmbeddedLaunch", Boolean.valueOf(updatesService.isEmbeddedLaunch()));
                linkedHashMap.put("isMissingRuntimeVersion", Boolean.valueOf(updatesService.getConfiguration().isMissingRuntimeVersion()));
                linkedHashMap.put("isEnabled", Boolean.valueOf(updatesService.getConfiguration().isEnabled()));
                linkedHashMap.put(UpdatesConfiguration.UPDATES_CONFIGURATION_RELEASE_CHANNEL_KEY, updatesService.getConfiguration().getReleaseChannel());
                linkedHashMap.put("isUsingEmbeddedAssets", Boolean.valueOf(updatesService.isUsingEmbeddedAssets()));
                String runtimeVersion = updatesService.getConfiguration().getRuntimeVersion();
                String str = "";
                if (runtimeVersion == null) {
                    runtimeVersion = "";
                }
                linkedHashMap.put(UpdatesConfiguration.UPDATES_CONFIGURATION_RUNTIME_VERSION_KEY, runtimeVersion);
                String str2 = updatesService.getConfiguration().getRequestHeaders().get("expo-channel-name");
                if (str2 != null) {
                    str = str2;
                }
                linkedHashMap.put("channel", str);
                UpdateEntity launchedUpdate = updatesService.getLaunchedUpdate();
                if (launchedUpdate != null) {
                    String str3 = launchedUpdate.getId().toString();
                    Intrinsics.checkNotNullExpressionValue(str3, "launchedUpdate.id.toString()");
                    linkedHashMap.put("updateId", str3);
                    linkedHashMap.put("commitTime", Long.valueOf(launchedUpdate.getCommitTime().getTime()));
                    linkedHashMap.put("manifestString", launchedUpdate.getManifest() != null ? String.valueOf(launchedUpdate.getManifest()) : "{}");
                }
                Map<AssetEntity, String> localAssetFiles = updatesService.getLocalAssetFiles();
                if (localAssetFiles != null) {
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                    for (AssetEntity assetEntity : localAssetFiles.keySet()) {
                        if (assetEntity.getKey() != null) {
                            String key = assetEntity.getKey();
                            Intrinsics.checkNotNull(key);
                            String str4 = localAssetFiles.get(assetEntity);
                            Intrinsics.checkNotNull(str4);
                            linkedHashMap2.put(key, str4);
                        }
                    }
                    linkedHashMap.put("localAssets", linkedHashMap2);
                }
            }
        } catch (Exception unused) {
            linkedHashMap.put("isEnabled", false);
            linkedHashMap.put("isMissingRuntimeVersion", Boolean.valueOf(new UpdatesConfiguration(getContext(), null).isMissingRuntimeVersion()));
        }
        return linkedHashMap;
    }

    @ExpoMethod
    public final void reload(final Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            UpdatesInterface updatesService = getUpdatesService();
            Intrinsics.checkNotNull(updatesService);
            if (!updatesService.canRelaunch()) {
                promise.reject("ERR_UPDATES_DISABLED", "You cannot reload when expo-updates is not enabled.");
            } else {
                updatesService.relaunchReactApplication(new Launcher.LauncherCallback() { // from class: expo.modules.updates.UpdatesModule$reload$1
                    @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
                    public void onFailure(Exception e) {
                        String str;
                        Intrinsics.checkNotNullParameter(e, "e");
                        str = UpdatesModule.TAG;
                        Exception exc = e;
                        Log.e(str, "Failed to relaunch application", exc);
                        Promise.this.reject("ERR_UPDATES_RELOAD", e.getMessage(), exc);
                    }

                    @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
                    public void onSuccess() {
                        Promise.this.resolve(null);
                    }
                });
            }
        } catch (IllegalStateException unused) {
            promise.reject("ERR_UPDATES_RELOAD", "The updates module controller has not been properly initialized. If you're using a development client, you cannot use `Updates.reloadAsync`. Otherwise, make sure you have called the native method UpdatesController.initialize().");
        }
    }

    @ExpoMethod
    public final void checkForUpdateAsync(final Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            final UpdatesInterface updatesService = getUpdatesService();
            Intrinsics.checkNotNull(updatesService);
            if (!updatesService.getConfiguration().isEnabled()) {
                promise.reject("ERR_UPDATES_DISABLED", "You cannot check for updates when expo-updates is not enabled.");
                return;
            }
            DatabaseHolder databaseHolder = updatesService.getDatabaseHolder();
            JSONObject extraHeaders = FileDownloader.Companion.getExtraHeaders(databaseHolder.getDatabase(), updatesService.getConfiguration(), updatesService.getLaunchedUpdate(), updatesService.getEmbeddedUpdate());
            databaseHolder.releaseDatabase();
            FileDownloader fileDownloader = updatesService.getFileDownloader();
            UpdatesConfiguration configuration = updatesService.getConfiguration();
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            fileDownloader.downloadManifest(configuration, extraHeaders, context, new FileDownloader.ManifestDownloadCallback() { // from class: expo.modules.updates.UpdatesModule$checkForUpdateAsync$1
                @Override // expo.modules.updates.loader.FileDownloader.ManifestDownloadCallback
                public void onFailure(String message, Exception e) {
                    String str;
                    Intrinsics.checkNotNullParameter(message, "message");
                    Intrinsics.checkNotNullParameter(e, "e");
                    Exception exc = e;
                    Promise.this.reject("ERR_UPDATES_CHECK", message, exc);
                    str = UpdatesModule.TAG;
                    Log.e(str, message, exc);
                }

                @Override // expo.modules.updates.loader.FileDownloader.ManifestDownloadCallback
                public void onSuccess(UpdateManifest updateManifest) {
                    Intrinsics.checkNotNullParameter(updateManifest, "updateManifest");
                    UpdateEntity launchedUpdate = updatesService.getLaunchedUpdate();
                    Bundle bundle = new Bundle();
                    if (launchedUpdate == null) {
                        bundle.putBoolean("isAvailable", true);
                        bundle.putString("manifestString", updateManifest.getManifest().toString());
                        Promise.this.resolve(bundle);
                    } else if (updatesService.getSelectionPolicy().shouldLoadNewUpdate(updateManifest.getUpdateEntity(), launchedUpdate, updateManifest.getManifestFilters())) {
                        bundle.putBoolean("isAvailable", true);
                        bundle.putString("manifestString", updateManifest.getManifest().toString());
                        Promise.this.resolve(bundle);
                    } else {
                        bundle.putBoolean("isAvailable", false);
                        Promise.this.resolve(bundle);
                    }
                }
            });
        } catch (IllegalStateException unused) {
            promise.reject("ERR_UPDATES_CHECK", "The updates module controller has not been properly initialized. If you're using a development client, you cannot check for updates. Otherwise, make sure you have called the native method UpdatesController.initialize().");
        }
    }

    @ExpoMethod
    public final void fetchUpdateAsync(final Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            final UpdatesInterface updatesService = getUpdatesService();
            Intrinsics.checkNotNull(updatesService);
            if (!updatesService.getConfiguration().isEnabled()) {
                promise.reject("ERR_UPDATES_DISABLED", "You cannot fetch updates when expo-updates is not enabled.");
            } else {
                AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.UpdatesModule$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        UpdatesModule.m1719fetchUpdateAsync$lambda0(UpdatesInterface.this, this, promise);
                    }
                });
            }
        } catch (IllegalStateException unused) {
            promise.reject("ERR_UPDATES_FETCH", "The updates module controller has not been properly initialized. If you're using a development client, you cannot fetch updates. Otherwise, make sure you have called the native method UpdatesController.initialize().");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: fetchUpdateAsync$lambda-0  reason: not valid java name */
    public static final void m1719fetchUpdateAsync$lambda0(final UpdatesInterface updatesInterface, UpdatesModule this$0, final Promise promise) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        final DatabaseHolder databaseHolder = updatesInterface.getDatabaseHolder();
        Context context = this$0.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        new RemoteLoader(context, updatesInterface.getConfiguration(), databaseHolder.getDatabase(), updatesInterface.getFileDownloader(), updatesInterface.getDirectory(), updatesInterface.getLaunchedUpdate()).start(new Loader.LoaderCallback() { // from class: expo.modules.updates.UpdatesModule$fetchUpdateAsync$1$1
            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onAssetLoaded(AssetEntity asset, int r2, int r3, int r4) {
                Intrinsics.checkNotNullParameter(asset, "asset");
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onFailure(Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                DatabaseHolder.this.releaseDatabase();
                promise.reject("ERR_UPDATES_FETCH", "Failed to download new update", e);
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public boolean onUpdateManifestLoaded(UpdateManifest updateManifest) {
                Intrinsics.checkNotNullParameter(updateManifest, "updateManifest");
                return updatesInterface.getSelectionPolicy().shouldLoadNewUpdate(updateManifest.getUpdateEntity(), updatesInterface.getLaunchedUpdate(), updateManifest.getManifestFilters());
            }

            @Override // expo.modules.updates.loader.Loader.LoaderCallback
            public void onSuccess(UpdateEntity updateEntity) {
                DatabaseHolder.this.releaseDatabase();
                Bundle bundle = new Bundle();
                if (updateEntity == null) {
                    bundle.putBoolean("isNew", false);
                } else {
                    updatesInterface.resetSelectionPolicy();
                    bundle.putBoolean("isNew", true);
                    bundle.putString("manifestString", String.valueOf(updateEntity.getManifest()));
                }
                promise.resolve(bundle);
            }
        });
    }

    @ExpoMethod
    public final void readLogEntriesAsync(final long j, final Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.UpdatesModule$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                UpdatesModule.m1720readLogEntriesAsync$lambda4(UpdatesModule.this, j, promise);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: readLogEntriesAsync$lambda-4  reason: not valid java name */
    public static final void m1720readLogEntriesAsync$lambda4(UpdatesModule this$0, long j, Promise promise) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Context context = this$0.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        ArrayList arrayList = new ArrayList();
        for (String str : new UpdatesLogReader(context).getLogEntries(new Date(new Date().getTime() - j))) {
            UpdatesLogEntry create = UpdatesLogEntry.Companion.create(str);
            if (create != null) {
                arrayList.add(create);
            }
        }
        ArrayList<UpdatesLogEntry> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (UpdatesLogEntry updatesLogEntry : arrayList2) {
            Bundle bundle = new Bundle();
            bundle.putLong("timestamp", updatesLogEntry.getTimestamp());
            bundle.putString(OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, updatesLogEntry.getMessage());
            bundle.putString("code", updatesLogEntry.getCode());
            bundle.putString("level", updatesLogEntry.getLevel());
            if (updatesLogEntry.getUpdateId() != null) {
                bundle.putString("updateId", updatesLogEntry.getUpdateId());
            }
            if (updatesLogEntry.getAssetId() != null) {
                bundle.putString("assetId", updatesLogEntry.getAssetId());
            }
            if (updatesLogEntry.getStacktrace() != null) {
                Object[] array = updatesLogEntry.getStacktrace().toArray(new String[0]);
                Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                bundle.putStringArray("stacktrace", (String[]) array);
            }
            arrayList3.add(bundle);
        }
        promise.resolve(arrayList3);
    }

    @ExpoMethod
    public final void clearLogEntriesAsync(final Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.UpdatesModule$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                UpdatesModule.m1718clearLogEntriesAsync$lambda5(UpdatesModule.this, promise);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: clearLogEntriesAsync$lambda-5  reason: not valid java name */
    public static final void m1718clearLogEntriesAsync$lambda5(UpdatesModule this$0, final Promise promise) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        Context context = this$0.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        new UpdatesLogReader(context).purgeLogEntries(new Date(), new Function1<Error, Unit>() { // from class: expo.modules.updates.UpdatesModule$clearLogEntriesAsync$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Error error) {
                invoke2(error);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Error error) {
                if (error != null) {
                    Promise.this.reject("ERR_UPDATES_READ_LOGS", "There was an error when clearing the expo-updates log file", error);
                } else {
                    Promise.this.resolve(null);
                }
            }
        });
    }

    /* compiled from: UpdatesModule.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/updates/UpdatesModule$Companion;", "", "()V", "NAME", "", "TAG", "kotlin.jvm.PlatformType", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
