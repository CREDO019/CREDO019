package expo.modules.updates;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactApplicationContext;
import expo.modules.core.ExportedModule;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.Package;
import expo.modules.core.interfaces.ReactNativeHostHandler;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatesPackage.kt */
@Metadata(m184d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\r"}, m183d2 = {"Lexpo/modules/updates/UpdatesPackage;", "Lexpo/modules/core/interfaces/Package;", "()V", "createExportedModules", "", "Lexpo/modules/core/ExportedModule;", "context", "Landroid/content/Context;", "createInternalModules", "Lexpo/modules/core/interfaces/InternalModule;", "createReactNativeHostHandlers", "Lexpo/modules/core/interfaces/ReactNativeHostHandler;", "Companion", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class UpdatesPackage implements Package {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "UpdatesPackage";

    @Override // expo.modules.core.interfaces.Package
    public /* synthetic */ List createApplicationLifecycleListeners(Context context) {
        List emptyList;
        emptyList = Collections.emptyList();
        return emptyList;
    }

    @Override // expo.modules.core.interfaces.Package
    public /* synthetic */ List createReactActivityHandlers(Context context) {
        List emptyList;
        emptyList = Collections.emptyList();
        return emptyList;
    }

    @Override // expo.modules.core.interfaces.Package
    public /* synthetic */ List createReactActivityLifecycleListeners(Context context) {
        List emptyList;
        emptyList = Collections.emptyList();
        return emptyList;
    }

    @Override // expo.modules.core.interfaces.Package
    public /* synthetic */ List createSingletonModules(Context context) {
        List emptyList;
        emptyList = Collections.emptyList();
        return emptyList;
    }

    @Override // expo.modules.core.interfaces.Package
    public /* synthetic */ List createViewManagers(Context context) {
        List emptyList;
        emptyList = Collections.emptyList();
        return emptyList;
    }

    @Override // expo.modules.core.interfaces.Package
    public List<InternalModule> createInternalModules(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return CollectionsKt.listOf(new UpdatesService(context));
    }

    @Override // expo.modules.core.interfaces.Package
    public List<ExportedModule> createExportedModules(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return CollectionsKt.listOf(new UpdatesModule(context, null, 2, null));
    }

    @Override // expo.modules.core.interfaces.Package
    public List<ReactNativeHostHandler> createReactNativeHostHandlers(final Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return CollectionsKt.listOf(new ReactNativeHostHandler() { // from class: expo.modules.updates.UpdatesPackage$createReactNativeHostHandlers$handler$1
            private Boolean mShouldAutoSetup;

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public /* synthetic */ ReactInstanceManager createReactInstanceManager(boolean z) {
                return ReactNativeHostHandler.CC.$default$createReactInstanceManager(this, z);
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public /* synthetic */ Object getDevSupportManagerFactory() {
                return ReactNativeHostHandler.CC.$default$getDevSupportManagerFactory(this);
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public /* synthetic */ JavaScriptExecutorFactory getJavaScriptExecutorFactory() {
                return ReactNativeHostHandler.CC.$default$getJavaScriptExecutorFactory(this);
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public /* synthetic */ Boolean getUseDeveloperSupport() {
                return ReactNativeHostHandler.CC.$default$getUseDeveloperSupport(this);
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public /* synthetic */ void onRegisterJSIModules(ReactApplicationContext reactApplicationContext, JavaScriptContextHolder javaScriptContextHolder, boolean z) {
                ReactNativeHostHandler.CC.$default$onRegisterJSIModules(this, reactApplicationContext, javaScriptContextHolder, z);
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public String getJSBundleFile(boolean z) {
                if (!shouldAutoSetup(context) || (!r2 && z)) {
                    return null;
                }
                return UpdatesController.Companion.getInstance().getLaunchAssetFile();
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public String getBundleAssetName(boolean z) {
                if (!shouldAutoSetup(context) || (!r2 && z)) {
                    return null;
                }
                return UpdatesController.Companion.getInstance().getBundleAssetName();
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public void onWillCreateReactInstanceManager(boolean z) {
                if (shouldAutoSetup(context)) {
                    if (r2 || !z) {
                        UpdatesController.Companion.initialize(context);
                    }
                }
            }

            @Override // expo.modules.core.interfaces.ReactNativeHostHandler
            public void onDidCreateReactInstanceManager(ReactInstanceManager reactInstanceManager, boolean z) {
                Intrinsics.checkNotNullParameter(reactInstanceManager, "reactInstanceManager");
                if (shouldAutoSetup(context)) {
                    if (r2 || !z) {
                        UpdatesController.Companion.getInstance().onDidCreateReactInstanceManager(reactInstanceManager);
                    }
                }
            }

            private final boolean shouldAutoSetup(Context context2) {
                String str;
                boolean z;
                if (this.mShouldAutoSetup == null) {
                    try {
                        ApplicationInfo applicationInfo = context2.getPackageManager().getApplicationInfo(context2.getPackageName(), 128);
                        Intrinsics.checkNotNullExpressionValue(applicationInfo, "pm.getApplicationInfo(co…ageManager.GET_META_DATA)");
                        z = Boolean.valueOf(applicationInfo.metaData.getBoolean("expo.modules.updates.AUTO_SETUP", true));
                    } catch (Exception e) {
                        str = UpdatesPackage.TAG;
                        Log.e(str, "Could not read expo-updates configuration data in AndroidManifest", e);
                        z = true;
                    }
                    this.mShouldAutoSetup = z;
                }
                Boolean bool = this.mShouldAutoSetup;
                Intrinsics.checkNotNull(bool);
                return bool.booleanValue();
            }
        });
    }

    /* compiled from: UpdatesPackage.kt */
    @Metadata(m184d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, m183d2 = {"Lexpo/modules/updates/UpdatesPackage$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "expo-updates_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
