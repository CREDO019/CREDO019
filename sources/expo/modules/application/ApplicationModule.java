package expo.modules.application;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ActivityProvider;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApplicationModule.kt */
@Metadata(m184d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00192\u00020\u00012\u00020\u0002:\u0001\u0019B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0016\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\rH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\b\u0010\u0016\u001a\u00020\u000eH\u0016J\u0010\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u000bH\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, m183d2 = {"Lexpo/modules/application/ApplicationModule;", "Lexpo/modules/core/ExportedModule;", "Lexpo/modules/core/interfaces/RegistryLifecycleListener;", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mActivity", "Landroid/app/Activity;", "mActivityProvider", "Lexpo/modules/core/interfaces/ActivityProvider;", "mModuleRegistry", "Lexpo/modules/core/ModuleRegistry;", "getConstants", "", "", "", "getInstallReferrerAsync", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "getInstallationTimeAsync", "getLastUpdateTimeAsync", "getName", "onCreate", "moduleRegistry", "Companion", "expo-application_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ApplicationModule extends ExportedModule implements RegistryLifecycleListener {
    public static final Companion Companion = new Companion(null);
    private Activity mActivity;
    private ActivityProvider mActivityProvider;
    private final Context mContext;
    private ModuleRegistry mModuleRegistry;

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return "ExpoApplication";
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ApplicationModule(Context mContext) {
        super(mContext);
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        this.mContext = mContext;
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.mModuleRegistry = moduleRegistry;
        ActivityProvider activityProvider = (ActivityProvider) moduleRegistry.getModule(ActivityProvider.class);
        this.mActivityProvider = activityProvider;
        this.mActivity = activityProvider == null ? null : activityProvider.getCurrentActivity();
    }

    @Override // expo.modules.core.ExportedModule
    public Map<String, Object> getConstants() {
        String str;
        HashMap hashMap = new HashMap();
        String obj = this.mContext.getApplicationInfo().loadLabel(this.mContext.getPackageManager()).toString();
        String packageName = this.mContext.getPackageName();
        HashMap hashMap2 = hashMap;
        hashMap2.put("applicationName", obj);
        hashMap2.put("applicationId", packageName);
        try {
            PackageInfo pInfo = this.mContext.getPackageManager().getPackageInfo(packageName, 0);
            hashMap.put("nativeApplicationVersion", pInfo.versionName);
            Companion companion = Companion;
            Intrinsics.checkNotNullExpressionValue(pInfo, "pInfo");
            hashMap.put("nativeBuildVersion", String.valueOf((int) companion.getLongVersionCode(pInfo)));
        } catch (PackageManager.NameNotFoundException e) {
            str = ApplicationModuleKt.TAG;
            Log.e(str, "Exception: ", e);
        }
        hashMap2.put("androidId", Settings.Secure.getString(this.mContext.getContentResolver(), "android_id"));
        return hashMap2;
    }

    @ExpoMethod
    public final void getInstallationTimeAsync(Promise promise) {
        String str;
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            promise.resolve(Double.valueOf(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).firstInstallTime));
        } catch (PackageManager.NameNotFoundException e) {
            str = ApplicationModuleKt.TAG;
            PackageManager.NameNotFoundException nameNotFoundException = e;
            Log.e(str, "Exception: ", nameNotFoundException);
            promise.reject("ERR_APPLICATION_PACKAGE_NAME_NOT_FOUND", "Unable to get install time of this application. Could not get package info or package name.", nameNotFoundException);
        }
    }

    @ExpoMethod
    public final void getLastUpdateTimeAsync(Promise promise) {
        String str;
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            promise.resolve(Double.valueOf(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).lastUpdateTime));
        } catch (PackageManager.NameNotFoundException e) {
            str = ApplicationModuleKt.TAG;
            PackageManager.NameNotFoundException nameNotFoundException = e;
            Log.e(str, "Exception: ", nameNotFoundException);
            promise.reject("ERR_APPLICATION_PACKAGE_NAME_NOT_FOUND", "Unable to get last update time of this application. Could not get package info or package name.", nameNotFoundException);
        }
    }

    @ExpoMethod
    public final void getInstallReferrerAsync(final Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        final StringBuilder sb = new StringBuilder();
        final InstallReferrerClient build = InstallReferrerClient.newBuilder(this.mContext).build();
        build.startConnection(new InstallReferrerStateListener() { // from class: expo.modules.application.ApplicationModule$getInstallReferrerAsync$1
            @Override // com.android.installreferrer.api.InstallReferrerStateListener
            public void onInstallReferrerSetupFinished(int r4) {
                String str;
                if (r4 == 0) {
                    try {
                        sb.append(InstallReferrerClient.this.getInstallReferrer().getInstallReferrer());
                    } catch (RemoteException e) {
                        str = ApplicationModuleKt.TAG;
                        RemoteException remoteException = e;
                        Log.e(str, "Exception: ", remoteException);
                        promise.reject("ERR_APPLICATION_INSTALL_REFERRER_REMOTE_EXCEPTION", "RemoteException getting install referrer information. This may happen if the process hosting the remote object is no longer available.", remoteException);
                    }
                    promise.resolve(sb.toString());
                } else if (r4 == 1) {
                    promise.reject("ERR_APPLICATION_INSTALL_REFERRER_CONNECTION", "Could not establish a connection to Google Play");
                } else if (r4 == 2) {
                    promise.reject("ERR_APPLICATION_INSTALL_REFERRER_UNAVAILABLE", "The current Play Store app doesn't provide the installation referrer API, or the Play Store may not be installed.");
                } else {
                    Promise promise2 = promise;
                    promise2.reject("ERR_APPLICATION_INSTALL_REFERRER", "General error retrieving the install referrer: response code " + r4);
                }
                InstallReferrerClient.this.endConnection();
            }

            @Override // com.android.installreferrer.api.InstallReferrerStateListener
            public void onInstallReferrerServiceDisconnected() {
                promise.reject("ERR_APPLICATION_INSTALL_REFERRER_SERVICE_DISCONNECTED", "Connection to install referrer service was lost.");
            }
        });
    }

    /* compiled from: ApplicationModule.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u0007"}, m183d2 = {"Lexpo/modules/application/ApplicationModule$Companion;", "", "()V", "getLongVersionCode", "", "info", "Landroid/content/pm/PackageInfo;", "expo-application_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long getLongVersionCode(PackageInfo packageInfo) {
            if (Build.VERSION.SDK_INT >= 28) {
                return packageInfo.getLongVersionCode();
            }
            return packageInfo.versionCode;
        }
    }
}
