package expo.modules.sensors.services;

import android.content.Context;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.interfaces.LifecycleEventListener;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import expo.modules.core.interfaces.services.UIManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseService.kt */
@Metadata(m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0010H&J\b\u0010\u0014\u001a\u00020\u0010H&J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\b\u0010\u0017\u001a\u00020\u0010H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, m183d2 = {"Lexpo/modules/sensors/services/BaseService;", "Lexpo/modules/core/interfaces/LifecycleEventListener;", "Lexpo/modules/core/interfaces/RegistryLifecycleListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "<set-?>", "", "experienceIsForegrounded", "getExperienceIsForegrounded", "()Z", "mModuleRegistry", "Lexpo/modules/core/ModuleRegistry;", "onCreate", "", "moduleRegistry", "onDestroy", "onExperienceBackgrounded", "onExperienceForegrounded", "onHostDestroy", "onHostPause", "onHostResume", "expo-sensors_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public abstract class BaseService implements LifecycleEventListener, RegistryLifecycleListener {
    private final Context context;
    private boolean experienceIsForegrounded;
    private ModuleRegistry mModuleRegistry;

    public abstract void onExperienceBackgrounded();

    public abstract void onExperienceForegrounded();

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostDestroy() {
    }

    public BaseService(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Context getContext() {
        return this.context;
    }

    public final boolean getExperienceIsForegrounded() {
        return this.experienceIsForegrounded;
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        this.mModuleRegistry = moduleRegistry;
        if (moduleRegistry == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mModuleRegistry");
            moduleRegistry = null;
        }
        UIManager uIManager = (UIManager) moduleRegistry.getModule(UIManager.class);
        if (uIManager == null) {
            return;
        }
        uIManager.registerLifecycleEventListener(this);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public void onDestroy() {
        ModuleRegistry moduleRegistry = this.mModuleRegistry;
        if (moduleRegistry != null) {
            if (moduleRegistry == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mModuleRegistry");
                moduleRegistry = null;
            }
            UIManager uIManager = (UIManager) moduleRegistry.getModule(UIManager.class);
            if (uIManager == null) {
                return;
            }
            uIManager.unregisterLifecycleEventListener(this);
        }
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostResume() {
        this.experienceIsForegrounded = true;
        onExperienceForegrounded();
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostPause() {
        this.experienceIsForegrounded = false;
        onExperienceBackgrounded();
    }
}
