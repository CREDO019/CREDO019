package expo.modules.core.interfaces;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.ReactApplicationContext;

/* loaded from: classes4.dex */
public interface ReactNativeHostHandler {

    /* renamed from: expo.modules.core.interfaces.ReactNativeHostHandler$-CC  reason: invalid class name */
    /* loaded from: classes4.dex */
    public final /* synthetic */ class CC {
        public static ReactInstanceManager $default$createReactInstanceManager(ReactNativeHostHandler _this, boolean z) {
            return null;
        }

        public static String $default$getBundleAssetName(ReactNativeHostHandler _this, boolean z) {
            return null;
        }

        public static Object $default$getDevSupportManagerFactory(ReactNativeHostHandler _this) {
            return null;
        }

        public static String $default$getJSBundleFile(ReactNativeHostHandler _this, boolean z) {
            return null;
        }

        public static JavaScriptExecutorFactory $default$getJavaScriptExecutorFactory(ReactNativeHostHandler _this) {
            return null;
        }

        public static Boolean $default$getUseDeveloperSupport(ReactNativeHostHandler _this) {
            return null;
        }

        public static void $default$onDidCreateReactInstanceManager(ReactNativeHostHandler _this, ReactInstanceManager reactInstanceManager, boolean z) {
        }

        public static void $default$onRegisterJSIModules(ReactNativeHostHandler _this, ReactApplicationContext reactApplicationContext, JavaScriptContextHolder javaScriptContextHolder, boolean z) {
        }

        public static void $default$onWillCreateReactInstanceManager(ReactNativeHostHandler _this, boolean z) {
        }
    }

    ReactInstanceManager createReactInstanceManager(boolean z);

    String getBundleAssetName(boolean z);

    Object getDevSupportManagerFactory();

    String getJSBundleFile(boolean z);

    JavaScriptExecutorFactory getJavaScriptExecutorFactory();

    Boolean getUseDeveloperSupport();

    void onDidCreateReactInstanceManager(ReactInstanceManager reactInstanceManager, boolean z);

    void onRegisterJSIModules(ReactApplicationContext reactApplicationContext, JavaScriptContextHolder javaScriptContextHolder, boolean z);

    void onWillCreateReactInstanceManager(boolean z);
}
