package expo.modules.adapters.react;

import com.facebook.react.ReactPackage;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class ReactPackagesProvider implements InternalModule {
    private Collection<ReactPackage> mReactPackages = new ArrayList();

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onCreate(ModuleRegistry moduleRegistry) {
        RegistryLifecycleListener.CC.$default$onCreate(this, moduleRegistry);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<? extends Class> getExportedInterfaces() {
        return Collections.singletonList(ReactPackagesProvider.class);
    }

    public void addPackage(ReactPackage reactPackage) {
        this.mReactPackages.add(reactPackage);
    }

    public Collection<ReactPackage> getReactPackages() {
        return this.mReactPackages;
    }
}
