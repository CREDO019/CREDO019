package expo.modules.adapters.react.services;

import com.facebook.react.modules.systeminfo.ReactNativeVersion;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.InternalModule;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.core.interfaces.RuntimeEnvironmentInterface;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class RuntimeEnvironmentModule implements InternalModule, RuntimeEnvironmentInterface {
    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onCreate(ModuleRegistry moduleRegistry) {
        RegistryLifecycleListener.CC.$default$onCreate(this, moduleRegistry);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    @Override // expo.modules.core.interfaces.RuntimeEnvironmentInterface
    public String platformName() {
        return "React Native";
    }

    @Override // expo.modules.core.interfaces.InternalModule
    public List<? extends Class> getExportedInterfaces() {
        return Collections.singletonList(RuntimeEnvironmentInterface.class);
    }

    @Override // expo.modules.core.interfaces.RuntimeEnvironmentInterface
    public RuntimeEnvironmentInterface.PlatformVersion platformVersion() {
        final Map<String, Object> map = ReactNativeVersion.VERSION;
        return new RuntimeEnvironmentInterface.PlatformVersion() { // from class: expo.modules.adapters.react.services.RuntimeEnvironmentModule.1
            @Override // expo.modules.core.interfaces.RuntimeEnvironmentInterface.PlatformVersion
            public int major() {
                return ((Integer) map.get("major")).intValue();
            }

            @Override // expo.modules.core.interfaces.RuntimeEnvironmentInterface.PlatformVersion
            public int minor() {
                return ((Integer) map.get("minor")).intValue();
            }

            @Override // expo.modules.core.interfaces.RuntimeEnvironmentInterface.PlatformVersion
            public int patch() {
                return ((Integer) map.get("patch")).intValue();
            }

            @Override // expo.modules.core.interfaces.RuntimeEnvironmentInterface.PlatformVersion
            public String prerelease() {
                return (String) map.get("prerelease");
            }
        };
    }
}
