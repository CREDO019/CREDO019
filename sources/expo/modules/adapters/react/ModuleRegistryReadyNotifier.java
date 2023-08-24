package expo.modules.adapters.react;

import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.core.ModuleRegistry;

/* loaded from: classes4.dex */
public class ModuleRegistryReadyNotifier extends BaseJavaModule {
    private ModuleRegistry mModuleRegistry;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return null;
    }

    public ModuleRegistryReadyNotifier(ModuleRegistry moduleRegistry) {
        this.mModuleRegistry = moduleRegistry;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
        this.mModuleRegistry.ensureIsInitialized();
    }
}
