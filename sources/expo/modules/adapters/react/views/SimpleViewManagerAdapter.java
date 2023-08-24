package expo.modules.adapters.react.views;

import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.ViewManager;
import expo.modules.core.interfaces.RegistryLifecycleListener;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public class SimpleViewManagerAdapter<M extends ViewManager<V>, V extends View> extends SimpleViewManager<V> implements RegistryLifecycleListener {
    private M mViewManager;

    @Override // expo.modules.core.interfaces.RegistryLifecycleListener
    public /* synthetic */ void onDestroy() {
        RegistryLifecycleListener.CC.$default$onDestroy(this);
    }

    public SimpleViewManagerAdapter(M m) {
        this.mViewManager = m;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    protected V createViewInstance(ThemedReactContext themedReactContext) {
        return (V) this.mViewManager.createViewInstance(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(V v) {
        this.mViewManager.onDropViewInstance(v);
        super.onDropViewInstance(v);
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
