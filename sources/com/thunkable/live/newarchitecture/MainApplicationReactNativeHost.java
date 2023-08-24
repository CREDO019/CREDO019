package com.thunkable.live.newarchitecture;

import android.app.Application;
import com.facebook.react.PackageList;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.JSIModulePackage;
import com.facebook.react.bridge.JSIModuleProvider;
import com.facebook.react.bridge.JSIModuleSpec;
import com.facebook.react.bridge.JSIModuleType;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.fabric.CoreComponentsRegistry;
import com.facebook.react.fabric.FabricJSIModuleProvider;
import com.facebook.react.fabric.ReactNativeConfig;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.thunkable.live.newarchitecture.components.MainComponentsRegistry;
import com.thunkable.live.newarchitecture.modules.MainApplicationTurboModuleManagerDelegate;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class MainApplicationReactNativeHost extends ReactNativeHost {
    @Override // com.facebook.react.ReactNativeHost
    protected String getJSMainModuleName() {
        return "index";
    }

    @Override // com.facebook.react.ReactNativeHost
    public boolean getUseDeveloperSupport() {
        return false;
    }

    public MainApplicationReactNativeHost(Application application) {
        super(application);
    }

    @Override // com.facebook.react.ReactNativeHost
    protected List<ReactPackage> getPackages() {
        return new PackageList(this).getPackages();
    }

    @Override // com.facebook.react.ReactNativeHost
    protected ReactPackageTurboModuleManagerDelegate.Builder getReactPackageTurboModuleManagerDelegateBuilder() {
        return new MainApplicationTurboModuleManagerDelegate.Builder();
    }

    @Override // com.facebook.react.ReactNativeHost
    protected JSIModulePackage getJSIModulePackage() {
        return new JSIModulePackage() { // from class: com.thunkable.live.newarchitecture.MainApplicationReactNativeHost.1
            @Override // com.facebook.react.bridge.JSIModulePackage
            public List<JSIModuleSpec> getJSIModules(final ReactApplicationContext reactApplicationContext, JavaScriptContextHolder javaScriptContextHolder) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new JSIModuleSpec() { // from class: com.thunkable.live.newarchitecture.MainApplicationReactNativeHost.1.1
                    @Override // com.facebook.react.bridge.JSIModuleSpec
                    public JSIModuleType getJSIModuleType() {
                        return JSIModuleType.UIManager;
                    }

                    @Override // com.facebook.react.bridge.JSIModuleSpec
                    public JSIModuleProvider<UIManager> getJSIModuleProvider() {
                        ComponentFactory componentFactory = new ComponentFactory();
                        CoreComponentsRegistry.register(componentFactory);
                        MainComponentsRegistry.register(componentFactory);
                        return new FabricJSIModuleProvider(reactApplicationContext, componentFactory, ReactNativeConfig.DEFAULT_CONFIG, new ViewManagerRegistry(MainApplicationReactNativeHost.this.getReactInstanceManager().getOrCreateViewManagers(reactApplicationContext)));
                    }
                });
                return arrayList;
            }
        };
    }
}
