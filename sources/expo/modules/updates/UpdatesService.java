package expo.modules.updates;

import android.content.Context;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.updates.launcher.Launcher;
import expo.modules.updates.loader.FileDownloader;
import expo.modules.updates.manifest.EmbeddedManifest;
import expo.modules.updates.manifest.UpdateManifest;
import expo.modules.updates.p021db.DatabaseHolder;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import expo.modules.updates.selectionpolicy.SelectionPolicy;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesService.kt */
@Metadata(m184d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 72\u00020\u00012\u00020\u0002:\u00017B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010.\u001a\u00020\u001eH\u0016J\u0012\u0010/\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030100H\u0016J\u0010\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0016J\b\u00106\u001a\u000203H\u0016R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0003\u001a\u00020\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0005R\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u0014\u0010 \u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u001fR\u0014\u0010!\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u001fR\u0016\u0010\"\u001a\u0004\u0018\u00010\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0018R\"\u0010$\u001a\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'\u0018\u00010%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020+8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-¨\u00068"}, m183d2 = {"Lexpo/modules/updates/UpdatesService;", "Lexpo/modules/core/interfaces/InternalModule;", "Lexpo/modules/updates/UpdatesInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "getConfiguration", "()Lexpo/modules/updates/UpdatesConfiguration;", "getContext", "()Landroid/content/Context;", "setContext", "databaseHolder", "Lexpo/modules/updates/db/DatabaseHolder;", "getDatabaseHolder", "()Lexpo/modules/updates/db/DatabaseHolder;", "directory", "Ljava/io/File;", "getDirectory", "()Ljava/io/File;", "embeddedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "getEmbeddedUpdate", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "fileDownloader", "Lexpo/modules/updates/loader/FileDownloader;", "getFileDownloader", "()Lexpo/modules/updates/loader/FileDownloader;", "isEmbeddedLaunch", "", "()Z", "isEmergencyLaunch", "isUsingEmbeddedAssets", "launchedUpdate", "getLaunchedUpdate", "localAssetFiles", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "", "getLocalAssetFiles", "()Ljava/util/Map;", "selectionPolicy", "Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "getSelectionPolicy", "()Lexpo/modules/updates/selectionpolicy/SelectionPolicy;", "canRelaunch", "getExportedInterfaces", "", "Ljava/lang/Class;", "relaunchReactApplication", "", "callback", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "resetSelectionPolicy", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public class UpdatesService implements InternalModule, UpdatesInterface {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "UpdatesService";
    private Context context;

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onCreate(ModuleRegistry moduleRegistry) {
        RegistryLifecycleListener.CC.$default$onCreate(this, moduleRegistry);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    public UpdatesService(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    protected final Context getContext() {
        return this.context;
    }

    protected final void setContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<Class<?>> getExportedInterfaces() {
        return CollectionsKt.listOf(UpdatesInterface.class);
    }

    @Override // expo.modules.updates.UpdatesInterface
    public UpdatesConfiguration getConfiguration() {
        return UpdatesController.Companion.getInstance().getUpdatesConfiguration();
    }

    @Override // expo.modules.updates.UpdatesInterface
    public SelectionPolicy getSelectionPolicy() {
        return UpdatesController.Companion.getInstance().getSelectionPolicy();
    }

    @Override // expo.modules.updates.UpdatesInterface
    public File getDirectory() {
        return UpdatesController.Companion.getInstance().getUpdatesDirectory();
    }

    @Override // expo.modules.updates.UpdatesInterface
    public FileDownloader getFileDownloader() {
        return UpdatesController.Companion.getInstance().getFileDownloader();
    }

    @Override // expo.modules.updates.UpdatesInterface
    public DatabaseHolder getDatabaseHolder() {
        return UpdatesController.Companion.getInstance().getDatabaseHolder();
    }

    @Override // expo.modules.updates.UpdatesInterface
    public boolean isEmergencyLaunch() {
        return UpdatesController.Companion.getInstance().isEmergencyLaunch();
    }

    @Override // expo.modules.updates.UpdatesInterface
    public boolean isEmbeddedLaunch() {
        UUID id;
        UpdateEntity launchedUpdate = getLaunchedUpdate();
        if (launchedUpdate == null || (id = launchedUpdate.getId()) == null) {
            return false;
        }
        UpdateEntity embeddedUpdate = getEmbeddedUpdate();
        return id.equals(embeddedUpdate == null ? null : embeddedUpdate.getId());
    }

    @Override // expo.modules.updates.UpdatesInterface
    public boolean isUsingEmbeddedAssets() {
        return UpdatesController.Companion.getInstance().isUsingEmbeddedAssets();
    }

    @Override // expo.modules.updates.UpdatesInterface
    public boolean canRelaunch() {
        return getConfiguration().isEnabled() && getLaunchedUpdate() != null;
    }

    @Override // expo.modules.updates.UpdatesInterface
    public UpdateEntity getEmbeddedUpdate() {
        UpdateManifest updateManifest = EmbeddedManifest.INSTANCE.get(this.context, getConfiguration());
        if (updateManifest == null) {
            return null;
        }
        return updateManifest.getUpdateEntity();
    }

    @Override // expo.modules.updates.UpdatesInterface
    public UpdateEntity getLaunchedUpdate() {
        return UpdatesController.Companion.getInstance().getLaunchedUpdate();
    }

    @Override // expo.modules.updates.UpdatesInterface
    public Map<AssetEntity, String> getLocalAssetFiles() {
        return UpdatesController.Companion.getInstance().getLocalAssetFiles();
    }

    @Override // expo.modules.updates.UpdatesInterface
    public void relaunchReactApplication(Launcher.LauncherCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        UpdatesController.Companion.getInstance().relaunchReactApplication(this.context, callback);
    }

    @Override // expo.modules.updates.UpdatesInterface
    public void resetSelectionPolicy() {
        UpdatesController.Companion.getInstance().resetSelectionPolicyToDefault();
    }

    /* compiled from: UpdatesService.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/updates/UpdatesService$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
