package expo.modules.adapters.react.views;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ViewManager;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public class ViewGroupManagerAdapter<M extends ViewManager<V>, V extends ViewGroup> extends ViewGroupManager<V> implements RegistryLifecycleListener {
    private M mViewManager;

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.uimanager.ViewManager
    public /* bridge */ /* synthetic */ void onDropViewInstance(View view) {
        onDropViewInstance((ViewGroupManagerAdapter<M, V>) ((ViewGroup) view));
    }

    public ViewGroupManagerAdapter(M m) {
        this.mViewManager = m;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public V createViewInstance(ThemedReactContext themedReactContext) {
        return (V) this.mViewManager.createViewInstance(themedReactContext);
    }

    public void onDropViewInstance(V v) {
        this.mViewManager.onDropViewInstance(v);
        super.onDropViewInstance((ViewGroupManagerAdapter<M, V>) v);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        return ViewManagerAdapterUtils.getConstants(this.mViewManager);
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return ViewManagerAdapterUtils.getViewManagerAdapterName(this.mViewManager);
    }

    @ReactProp(name = "proxiedProperties")
    public void setProxiedProperties(V v, ReadableMap readableMap) {
        ViewManagerAdapterUtils.setProxiedProperties(getName(), this.mViewManager, v, readableMap);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return ViewManagerAdapterUtils.getExportedCustomDirectEventTypeConstants(this.mViewManager);
    }

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        this.mViewManager.onCreate(moduleRegistry);
    }
}
