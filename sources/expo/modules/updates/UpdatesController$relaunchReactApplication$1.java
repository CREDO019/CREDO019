package expo.modules.updates;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.JSBundleLoader;
import expo.modules.updates.launcher.DatabaseLauncher;
import expo.modules.updates.launcher.Launcher;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesController.kt */
@Metadata(m184d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\b"}, m183d2 = {"expo/modules/updates/UpdatesController$relaunchReactApplication$1", "Lexpo/modules/updates/launcher/Launcher$LauncherCallback;", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class UpdatesController$relaunchReactApplication$1 implements Launcher.LauncherCallback {
    final /* synthetic */ Launcher.LauncherCallback $callback;
    final /* synthetic */ ReactNativeHost $host;
    final /* synthetic */ DatabaseLauncher $newLauncher;
    final /* synthetic */ String $oldLaunchAssetFile;
    final /* synthetic */ boolean $shouldRunReaper;
    final /* synthetic */ UpdatesController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UpdatesController$relaunchReactApplication$1(Launcher.LauncherCallback launcherCallback, UpdatesController updatesController, DatabaseLauncher databaseLauncher, ReactNativeHost reactNativeHost, String str, boolean z) {
        this.$callback = launcherCallback;
        this.this$0 = updatesController;
        this.$newLauncher = databaseLauncher;
        this.$host = reactNativeHost;
        this.$oldLaunchAssetFile = str;
        this.$shouldRunReaper = z;
    }

    @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
    public void onFailure(Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        this.$callback.onFailure(e);
    }

    @Override // expo.modules.updates.launcher.Launcher.LauncherCallback
    public void onSuccess() {
        Launcher launcher;
        String str;
        this.this$0.launcher = this.$newLauncher;
        this.this$0.releaseDatabase();
        final ReactInstanceManager reactInstanceManager = this.$host.getReactInstanceManager();
        launcher = this.this$0.launcher;
        Intrinsics.checkNotNull(launcher);
        String launchAssetFile = launcher.getLaunchAssetFile();
        if (launchAssetFile != null && !Intrinsics.areEqual(launchAssetFile, this.$oldLaunchAssetFile)) {
            try {
                JSBundleLoader createFileLoader = JSBundleLoader.createFileLoader(launchAssetFile);
                Field declaredField = reactInstanceManager.getClass().getDeclaredField("mBundleLoader");
                declaredField.setAccessible(true);
                declaredField.set(reactInstanceManager, createFileLoader);
            } catch (Exception e) {
                str = UpdatesController.TAG;
                Log.e(str, "Could not reset JSBundleLoader in ReactInstanceManager", e);
            }
        }
        this.$callback.onSuccess();
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: expo.modules.updates.UpdatesController$relaunchReactApplication$1$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ReactInstanceManager.this.recreateReactContextInBackground();
            }
        });
        if (this.$shouldRunReaper) {
            this.this$0.runReaper();
        }
    }
}
