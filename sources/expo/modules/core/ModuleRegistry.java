package expo.modules.core;

import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.core.interfaces.SingletonModule;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class ModuleRegistry {
    private final Map<Class, InternalModule> mInternalModulesMap = new HashMap();
    private final Map<String, ViewManager> mViewManagersMap = new HashMap();
    private final Map<String, ExportedModule> mExportedModulesMap = new HashMap();
    private final Map<Class, ExportedModule> mExportedModulesByClassMap = new HashMap();
    private final Map<String, SingletonModule> mSingletonModulesMap = new HashMap();
    private final List<WeakReference<RegistryLifecycleListener>> mExtraRegistryLifecycleListeners = new ArrayList();
    private volatile boolean mIsInitialized = false;

    public ModuleRegistry(Collection<InternalModule> collection, Collection<ExportedModule> collection2, Collection<ViewManager> collection3, Collection<SingletonModule> collection4) {
        for (InternalModule internalModule : collection) {
            registerInternalModule(internalModule);
        }
        for (ExportedModule exportedModule : collection2) {
            registerExportedModule(exportedModule);
        }
        for (ViewManager viewManager : collection3) {
            registerViewManager(viewManager);
        }
        for (SingletonModule singletonModule : collection4) {
            registerSingletonModule(singletonModule);
        }
    }

    public <T> T getModule(Class<T> cls) {
        return (T) this.mInternalModulesMap.get(cls);
    }

    public ExportedModule getExportedModule(String str) {
        return this.mExportedModulesMap.get(str);
    }

    public ExportedModule getExportedModuleOfClass(Class cls) {
        return this.mExportedModulesByClassMap.get(cls);
    }

    public Collection<ViewManager> getAllViewManagers() {
        return this.mViewManagersMap.values();
    }

    public Collection<ExportedModule> getAllExportedModules() {
        return this.mExportedModulesMap.values();
    }

    public <T> T getSingletonModule(String str, Class<T> cls) {
        return (T) this.mSingletonModulesMap.get(str);
    }

    public void registerInternalModule(InternalModule internalModule) {
        for (Class cls : internalModule.getExportedInterfaces()) {
            this.mInternalModulesMap.put(cls, internalModule);
        }
    }

    public InternalModule unregisterInternalModule(Class cls) {
        return this.mInternalModulesMap.remove(cls);
    }

    public void registerExportedModule(ExportedModule exportedModule) {
        this.mExportedModulesMap.put(exportedModule.getName(), exportedModule);
        this.mExportedModulesByClassMap.put(exportedModule.getClass(), exportedModule);
    }

    public void registerViewManager(ViewManager viewManager) {
        this.mViewManagersMap.put(viewManager.getName(), viewManager);
    }

    public void registerSingletonModule(SingletonModule singletonModule) {
        this.mSingletonModulesMap.put(singletonModule.getName(), singletonModule);
    }

    public void registerExtraListener(RegistryLifecycleListener registryLifecycleListener) {
        this.mExtraRegistryLifecycleListeners.add(new WeakReference<>(registryLifecycleListener));
    }

    public synchronized void ensureIsInitialized() {
        if (!this.mIsInitialized) {
            initialize();
            this.mIsInitialized = true;
        }
    }

    public void initialize() {
        ArrayList<RegistryLifecycleListener> arrayList = new ArrayList();
        arrayList.addAll(this.mExportedModulesMap.values());
        arrayList.addAll(this.mInternalModulesMap.values());
        arrayList.addAll(this.mViewManagersMap.values());
        for (WeakReference<RegistryLifecycleListener> weakReference : this.mExtraRegistryLifecycleListeners) {
            if (weakReference.get() != null) {
                arrayList.add(weakReference.get());
            }
        }
        for (RegistryLifecycleListener registryLifecycleListener : arrayList) {
            registryLifecycleListener.onCreate(this);
        }
    }

    public void onDestroy() {
        ArrayList<RegistryLifecycleListener> arrayList = new ArrayList();
        arrayList.addAll(this.mExportedModulesMap.values());
        arrayList.addAll(this.mInternalModulesMap.values());
        arrayList.addAll(this.mViewManagersMap.values());
        for (WeakReference<RegistryLifecycleListener> weakReference : this.mExtraRegistryLifecycleListeners) {
            if (weakReference.get() != null) {
                arrayList.add(weakReference.get());
            }
        }
        for (RegistryLifecycleListener registryLifecycleListener : arrayList) {
            registryLifecycleListener.onDestroy();
        }
    }
}
