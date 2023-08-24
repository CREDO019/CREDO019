package expo.modules.adapters.react;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import expo.modules.adapters.react.views.SimpleViewManagerAdapter;
import expo.modules.adapters.react.views.ViewGroupManagerAdapter;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ViewManager;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.Package;
import expo.modules.kotlin.CoreLogger;
import expo.modules.kotlin.KotlinInteropModuleRegistry;
import expo.modules.kotlin.ModulesProvider;
import expo.modules.kotlin.views.ViewWrapperDelegateHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
public class ModuleRegistryAdapter implements ReactPackage {
    protected ReactModuleRegistryProvider mModuleRegistryProvider;
    protected ModulesProvider mModulesProvider;
    private NativeModulesProxy mModulesProxy;
    protected ReactAdapterPackage mReactAdapterPackage = new ReactAdapterPackage();
    private List<ViewWrapperDelegateHolder> mWrapperDelegateHolders = null;
    private FabricComponentsRegistry mFabricComponentsRegistry = null;

    public ModuleRegistryAdapter(List<Package> list) {
        this.mModuleRegistryProvider = new ReactModuleRegistryProvider(list, null);
    }

    public ModuleRegistryAdapter(ReactModuleRegistryProvider reactModuleRegistryProvider) {
        this.mModuleRegistryProvider = reactModuleRegistryProvider;
    }

    public ModuleRegistryAdapter(ReactModuleRegistryProvider reactModuleRegistryProvider, ModulesProvider modulesProvider) {
        this.mModuleRegistryProvider = reactModuleRegistryProvider;
        this.mModulesProvider = modulesProvider;
    }

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        NativeModulesProxy orCreateNativeModulesProxy = getOrCreateNativeModulesProxy(reactApplicationContext, null);
        ModuleRegistry moduleRegistry = orCreateNativeModulesProxy.getModuleRegistry();
        for (InternalModule internalModule : this.mReactAdapterPackage.createInternalModules(reactApplicationContext)) {
            moduleRegistry.registerInternalModule(internalModule);
        }
        List<NativeModule> nativeModulesFromModuleRegistry = getNativeModulesFromModuleRegistry(reactApplicationContext, moduleRegistry);
        if (this.mWrapperDelegateHolders != null) {
            orCreateNativeModulesProxy.getKotlinInteropModuleRegistry().updateModuleHoldersInViewManagers(this.mWrapperDelegateHolders);
        }
        return nativeModulesFromModuleRegistry;
    }

    protected List<NativeModule> getNativeModulesFromModuleRegistry(ReactApplicationContext reactApplicationContext, ModuleRegistry moduleRegistry) {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(getOrCreateNativeModulesProxy(reactApplicationContext, moduleRegistry));
        arrayList.add(new ModuleRegistryReadyNotifier(moduleRegistry));
        for (ReactPackage reactPackage : ((ReactPackagesProvider) moduleRegistry.getModule(ReactPackagesProvider.class)).getReactPackages()) {
            arrayList.addAll(reactPackage.createNativeModules(reactApplicationContext));
        }
        return arrayList;
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList(this.mModuleRegistryProvider.getReactViewManagers(reactApplicationContext));
        for (expo.modules.core.ViewManager viewManager : this.mModuleRegistryProvider.getViewManagers(reactApplicationContext)) {
            int r3 = C42731.$SwitchMap$expo$modules$core$ViewManager$ViewManagerType[viewManager.getViewManagerType().ordinal()];
            if (r3 == 1) {
                arrayList.add(new ViewGroupManagerAdapter(viewManager));
            } else if (r3 == 2) {
                arrayList.add(new SimpleViewManagerAdapter(viewManager));
            }
        }
        NativeModulesProxy orCreateNativeModulesProxy = getOrCreateNativeModulesProxy(reactApplicationContext, null);
        Objects.requireNonNull(orCreateNativeModulesProxy);
        KotlinInteropModuleRegistry kotlinInteropModuleRegistry = orCreateNativeModulesProxy.getKotlinInteropModuleRegistry();
        List<ViewManager<?, ?>> exportViewManagers = kotlinInteropModuleRegistry.exportViewManagers();
        this.mWrapperDelegateHolders = kotlinInteropModuleRegistry.extractViewManagersDelegateHolders(exportViewManagers);
        arrayList.addAll(exportViewManagers);
        return arrayList;
    }

    /* renamed from: expo.modules.adapters.react.ModuleRegistryAdapter$1 */
    /* loaded from: classes4.dex */
    static /* synthetic */ class C42731 {
        static final /* synthetic */ int[] $SwitchMap$expo$modules$core$ViewManager$ViewManagerType;

        static {
            int[] r0 = new int[ViewManager.ViewManagerType.values().length];
            $SwitchMap$expo$modules$core$ViewManager$ViewManagerType = r0;
            try {
                r0[ViewManager.ViewManagerType.GROUP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$expo$modules$core$ViewManager$ViewManagerType[ViewManager.ViewManagerType.SIMPLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private synchronized NativeModulesProxy getOrCreateNativeModulesProxy(ReactApplicationContext reactApplicationContext, ModuleRegistry moduleRegistry) {
        NativeModulesProxy nativeModulesProxy = this.mModulesProxy;
        if (nativeModulesProxy != null && nativeModulesProxy.getReactContext() != reactApplicationContext) {
            this.mModulesProxy = null;
        }
        if (this.mModulesProxy == null) {
            ModuleRegistry moduleRegistry2 = moduleRegistry != null ? moduleRegistry : this.mModuleRegistryProvider.get(reactApplicationContext);
            if (this.mModulesProvider != null) {
                this.mModulesProxy = new NativeModulesProxy(reactApplicationContext, moduleRegistry2, this.mModulesProvider);
            } else {
                this.mModulesProxy = new NativeModulesProxy(reactApplicationContext, moduleRegistry2);
            }
            this.mModulesProxy.getKotlinInteropModuleRegistry().setLegacyModulesProxy(this.mModulesProxy);
        }
        if (moduleRegistry != null && moduleRegistry != this.mModulesProxy.getModuleRegistry()) {
            CoreLogger.getLogger().error("❌ NativeModuleProxy was configured with a different instance of the modules registry.", null);
        }
        return this.mModulesProxy;
    }
}
