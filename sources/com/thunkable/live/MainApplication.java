package com.thunkable.live;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.soloader.SoLoader;
import com.swmansion.reanimated.ReanimatedJSIModulePackage;
import expo.modules.ApplicationLifecycleDispatcher;
import expo.modules.ReactNativeHostWrapper;
import java.util.List;

/* loaded from: classes4.dex */
public class MainApplication extends Application implements ReactApplication {
    private final ReactNativeHost mReactNativeHost = new ReactNativeHostWrapper(this, new ReactNativeHost(this) { // from class: com.thunkable.live.MainApplication.1
        @Override // com.facebook.react.ReactNativeHost
        protected String getJSMainModuleName() {
            return "index";
        }

        @Override // com.facebook.react.ReactNativeHost
        public boolean getUseDeveloperSupport() {
            return false;
        }

        @Override // com.facebook.react.ReactNativeHost
        protected List<ReactPackage> getPackages() {
            return new PackageList(this).getPackages();
        }

        @Override // com.facebook.react.ReactNativeHost
        protected JSIModulePackage getJSIModulePackage() {
            return new ReanimatedJSIModulePackage();
        }
    });

    private static void initializeFlipper(Context context, ReactInstanceManager reactInstanceManager) {
    }

    @Override // com.facebook.react.ReactApplication
    public ReactNativeHost getReactNativeHost() {
        return this.mReactNativeHost;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        ReactFeatureFlags.useTurboModules = false;
        SoLoader.init((Context) this, false);
        ApplicationLifecycleDispatcher.onApplicationCreate(this);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ApplicationLifecycleDispatcher.onConfigurationChanged(this, configuration);
    }
}
