package expo.modules.core.interfaces;

import android.content.Context;
import expo.modules.core.ExportedModule;
import expo.modules.core.ViewManager;
import java.util.List;

/* loaded from: classes4.dex */
public interface Package {
    List<? extends ApplicationLifecycleListener> createApplicationLifecycleListeners(Context context);

    List<? extends ExportedModule> createExportedModules(Context context);

    List<? extends InternalModule> createInternalModules(Context context);

    List<? extends ReactActivityHandler> createReactActivityHandlers(Context context);

    List<? extends ReactActivityLifecycleListener> createReactActivityLifecycleListeners(Context context);

    List<? extends ReactNativeHostHandler> createReactNativeHostHandlers(Context context);

    List<? extends SingletonModule> createSingletonModules(Context context);

    List<? extends ViewManager> createViewManagers(Context context);

    /* renamed from: expo.modules.core.interfaces.Package$-CC  reason: invalid class name */
    /* loaded from: classes4.dex */
    public final /* synthetic */ class CC {
    }
}
