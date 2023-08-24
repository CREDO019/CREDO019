package expo.modules.kotlin.views;

import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SimpleViewManagerWrapper.kt */
@Metadata(m184d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0014J\u0016\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\rH\u0016J\b\u0010\u0010\u001a\u00020\u000eH\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u0016J\u0018\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0007R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0017"}, m183d2 = {"Lexpo/modules/kotlin/views/SimpleViewManagerWrapper;", "Lcom/facebook/react/uimanager/SimpleViewManager;", "Landroid/view/View;", "Lexpo/modules/kotlin/views/ViewWrapperDelegateHolder;", "viewWrapperDelegate", "Lexpo/modules/kotlin/views/ViewManagerWrapperDelegate;", "(Lexpo/modules/kotlin/views/ViewManagerWrapperDelegate;)V", "getViewWrapperDelegate", "()Lexpo/modules/kotlin/views/ViewManagerWrapperDelegate;", "createViewInstance", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getExportedCustomDirectEventTypeConstants", "", "", "", "getName", "onDropViewInstance", "", "view", "setProxiedProperties", "proxiedProperties", "Lcom/facebook/react/bridge/ReadableMap;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SimpleViewManagerWrapper extends SimpleViewManager<View> implements ViewWrapperDelegateHolder {
    private final ViewManagerWrapperDelegate viewWrapperDelegate;

    @Override // expo.modules.kotlin.views.ViewWrapperDelegateHolder
    public ViewManagerWrapperDelegate getViewWrapperDelegate() {
        return this.viewWrapperDelegate;
    }

    public SimpleViewManagerWrapper(ViewManagerWrapperDelegate viewWrapperDelegate) {
        Intrinsics.checkNotNullParameter(viewWrapperDelegate, "viewWrapperDelegate");
        this.viewWrapperDelegate = viewWrapperDelegate;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        String name = getViewWrapperDelegate().getName();
        return "ViewManagerAdapter_" + name;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    protected View createViewInstance(ThemedReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return getViewWrapperDelegate().createView(reactContext);
    }

    @ReactProp(name = "proxiedProperties")
    public final void setProxiedProperties(View view, ReadableMap proxiedProperties) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(proxiedProperties, "proxiedProperties");
        getViewWrapperDelegate().setProxiedProperties(view, proxiedProperties);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onDropViewInstance(view);
        getViewWrapperDelegate().onDestroy(view);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        Map<String, Object> exportedCustomDirectEventTypeConstants = getViewWrapperDelegate().getExportedCustomDirectEventTypeConstants();
        if (exportedCustomDirectEventTypeConstants != null) {
            Map<String, Object> exportedCustomDirectEventTypeConstants2 = super.getExportedCustomDirectEventTypeConstants();
            if (exportedCustomDirectEventTypeConstants2 == null) {
                exportedCustomDirectEventTypeConstants2 = MapsKt.emptyMap();
            }
            MapBuilder.Builder builder = MapBuilder.builder();
            for (Map.Entry<String, Object> entry : exportedCustomDirectEventTypeConstants2.entrySet()) {
                builder.put(entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, Object> entry2 : exportedCustomDirectEventTypeConstants.entrySet()) {
                builder.put(entry2.getKey(), entry2.getValue());
            }
            return builder.build();
        }
        return super.getExportedCustomDirectEventTypeConstants();
    }
}
