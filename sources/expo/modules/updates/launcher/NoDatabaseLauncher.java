package expo.modules.updates.launcher;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import expo.modules.updates.UpdatesConfiguration;
import expo.modules.updates.loader.EmbeddedLoader;
import expo.modules.updates.manifest.BareUpdateManifest;
import expo.modules.updates.manifest.EmbeddedManifest;
import expo.modules.updates.manifest.UpdateManifest;
import expo.modules.updates.p021db.entity.AssetEntity;
import expo.modules.updates.p021db.entity.UpdateEntity;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.p028io.FileUtils;

/* compiled from: NoDatabaseLauncher.kt */
@Metadata(m184d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b¢\u0006\u0002\u0010\tJ\u001c\u0010\u001f\u001a\u00020 2\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH\u0002R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012R\u0016\u0010\u0013\u001a\u0004\u0018\u00010\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\rR\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R:\u0010\u001c\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001a2\u0014\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001a@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006\""}, m183d2 = {"Lexpo/modules/updates/launcher/NoDatabaseLauncher;", "Lexpo/modules/updates/launcher/Launcher;", "context", "Landroid/content/Context;", "configuration", "Lexpo/modules/updates/UpdatesConfiguration;", "fatalException", "Ljava/lang/Exception;", "Lkotlin/Exception;", "(Landroid/content/Context;Lexpo/modules/updates/UpdatesConfiguration;Ljava/lang/Exception;)V", "bundleAssetName", "", "getBundleAssetName", "()Ljava/lang/String;", "setBundleAssetName", "(Ljava/lang/String;)V", "isUsingEmbeddedAssets", "", "()Z", "launchAssetFile", "getLaunchAssetFile", "launchedUpdate", "Lexpo/modules/updates/db/entity/UpdateEntity;", "getLaunchedUpdate", "()Lexpo/modules/updates/db/entity/UpdateEntity;", "<set-?>", "", "Lexpo/modules/updates/db/entity/AssetEntity;", "localAssetFiles", "getLocalAssetFiles", "()Ljava/util/Map;", "writeErrorToLog", "", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class NoDatabaseLauncher implements Launcher {
    private static final String ERROR_LOG_FILENAME = "expo-error.log";
    private String bundleAssetName;
    private Map<AssetEntity, String> localAssetFiles;
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "NoDatabaseLauncher";

    /* JADX WARN: 'thıs' call moved to the top of the method (can break code semantics) */
    public NoDatabaseLauncher(Context context, UpdatesConfiguration configuration) {
        this(context, configuration, null, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
    }

    @Override // expo.modules.updates.launcher.Launcher
    public String getLaunchAssetFile() {
        return null;
    }

    @Override // expo.modules.updates.launcher.Launcher
    public UpdateEntity getLaunchedUpdate() {
        return null;
    }

    public NoDatabaseLauncher(final Context context, UpdatesConfiguration configuration, final Exception exc) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        UpdateManifest updateManifest = EmbeddedManifest.INSTANCE.get(context, configuration);
        if (updateManifest == null) {
            throw new RuntimeException("Failed to launch with embedded update because the embedded manifest was null");
        }
        if (updateManifest instanceof BareUpdateManifest) {
            setBundleAssetName(EmbeddedLoader.BARE_BUNDLE_FILENAME);
            this.localAssetFiles = null;
        } else {
            setBundleAssetName(EmbeddedLoader.BUNDLE_FILENAME);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (AssetEntity assetEntity : updateManifest.getAssetEntityList()) {
                String embeddedAssetFilename = assetEntity.getEmbeddedAssetFilename();
                linkedHashMap.put(assetEntity, "asset:///" + embeddedAssetFilename);
            }
            this.localAssetFiles = linkedHashMap;
        }
        if (exc != null) {
            AsyncTask.execute(new Runnable() { // from class: expo.modules.updates.launcher.NoDatabaseLauncher$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    NoDatabaseLauncher.m1733_init_$lambda1(NoDatabaseLauncher.this, context, exc);
                }
            });
        }
    }

    public /* synthetic */ NoDatabaseLauncher(Context context, UpdatesConfiguration updatesConfiguration, Exception exc, int r4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, updatesConfiguration, (r4 & 4) != 0 ? null : exc);
    }

    @Override // expo.modules.updates.launcher.Launcher
    public String getBundleAssetName() {
        return this.bundleAssetName;
    }

    public void setBundleAssetName(String str) {
        this.bundleAssetName = str;
    }

    @Override // expo.modules.updates.launcher.Launcher
    public Map<AssetEntity, String> getLocalAssetFiles() {
        return this.localAssetFiles;
    }

    @Override // expo.modules.updates.launcher.Launcher
    public boolean isUsingEmbeddedAssets() {
        return getLocalAssetFiles() == null;
    }

    private final void writeErrorToLog(Context context, Exception exc) {
        try {
            FileUtils.writeStringToFile(new File(context.getFilesDir(), ERROR_LOG_FILENAME), exc.toString(), "UTF-8", true);
        } catch (Exception e) {
            Log.e(TAG, "Failed to write fatal error to log", e);
        }
    }

    /* compiled from: NoDatabaseLauncher.kt */
    @Metadata(m184d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m183d2 = {"Lexpo/modules/updates/launcher/NoDatabaseLauncher$Companion;", "", "()V", "ERROR_LOG_FILENAME", "", "TAG", "kotlin.jvm.PlatformType", "consumeErrorLog", "context", "Landroid/content/Context;", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String consumeErrorLog(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                File file = new File(context.getFilesDir(), NoDatabaseLauncher.ERROR_LOG_FILENAME);
                if (file.exists()) {
                    String readFileToString = FileUtils.readFileToString(file, "UTF-8");
                    file.delete();
                    return readFileToString;
                }
                return null;
            } catch (Exception e) {
                Log.e(NoDatabaseLauncher.TAG, "Failed to read error log", e);
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: _init_$lambda-1  reason: not valid java name */
    public static final void m1733_init_$lambda1(NoDatabaseLauncher this$0, Context context, Exception exc) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(context, "$context");
        this$0.writeErrorToLog(context, exc);
    }
}
