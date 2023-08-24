package expo.modules.kotlin.views;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GroupViewManagerWrapper.kt */
@Metadata(m184d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\u0016\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0016H\u0016J\b\u0010\u0019\u001a\u00020\u0017H\u0016J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u0002H\u0016J\u0018\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\rH\u0016J\u0018\u0010\u001d\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 H\u0007R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006!"}, m183d2 = {"Lexpo/modules/kotlin/views/GroupViewManagerWrapper;", "Lcom/facebook/react/uimanager/ViewGroupManager;", "Landroid/view/ViewGroup;", "Lexpo/modules/kotlin/views/ViewWrapperDelegateHolder;", "viewWrapperDelegate", "Lexpo/modules/kotlin/views/ViewManagerWrapperDelegate;", "(Lexpo/modules/kotlin/views/ViewManagerWrapperDelegate;)V", "getViewWrapperDelegate", "()Lexpo/modules/kotlin/views/ViewManagerWrapperDelegate;", "addView", "", "parent", "child", "Landroid/view/View;", "index", "", "createViewInstance", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "getChildAt", "getChildCount", "getExportedCustomDirectEventTypeConstants", "", "", "", "getName", "onDropViewInstance", "view", "removeView", "removeViewAt", "setProxiedProperties", "proxiedProperties", "Lcom/facebook/react/bridge/ReadableMap;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class GroupViewManagerWrapper extends ViewGroupManager<ViewGroup> implements ViewWrapperDelegateHolder {
    private final ViewManagerWrapperDelegate viewWrapperDelegate;

    @Override // expo.modules.kotlin.views.ViewWrapperDelegateHolder
    public ViewManagerWrapperDelegate getViewWrapperDelegate() {
        return this.viewWrapperDelegate;
    }

    public GroupViewManagerWrapper(ViewManagerWrapperDelegate viewWrapperDelegate) {
        Intrinsics.checkNotNullParameter(viewWrapperDelegate, "viewWrapperDelegate");
        this.viewWrapperDelegate = viewWrapperDelegate;
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        String name = getViewWrapperDelegate().getName();
        return "ViewManagerAdapter_" + name;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ViewGroup createViewInstance(ThemedReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        return (ViewGroup) getViewWrapperDelegate().createView(reactContext);
    }

    @ReactProp(name = "proxiedProperties")
    public final void setProxiedProperties(View view, ReadableMap proxiedProperties) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(proxiedProperties, "proxiedProperties");
        getViewWrapperDelegate().setProxiedProperties(view, proxiedProperties);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(ViewGroup view) {
        Intrinsics.checkNotNullParameter(view, "view");
        ViewGroup viewGroup = view;
        super.onDropViewInstance((GroupViewManagerWrapper) viewGroup);
        getViewWrapperDelegate().onDestroy(viewGroup);
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

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(ViewGroup parent, View child, int r5) {
        Function3<ViewGroup, View, Integer, Unit> addViewAction;
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(child, "child");
        ViewGroupDefinition viewGroupDefinition$expo_modules_core_release = getViewWrapperDelegate().getViewGroupDefinition$expo_modules_core_release();
        Unit unit = null;
        if (viewGroupDefinition$expo_modules_core_release != null && (addViewAction = viewGroupDefinition$expo_modules_core_release.getAddViewAction()) != null) {
            addViewAction.invoke(parent, child, Integer.valueOf(r5));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            super.addView(parent, child, r5);
            Unit unit2 = Unit.INSTANCE;
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public int getChildCount(ViewGroup parent) {
        Function1<ViewGroup, Integer> getChildCountAction;
        Intrinsics.checkNotNullParameter(parent, "parent");
        ViewGroupDefinition viewGroupDefinition$expo_modules_core_release = getViewWrapperDelegate().getViewGroupDefinition$expo_modules_core_release();
        Integer num = null;
        if (viewGroupDefinition$expo_modules_core_release != null && (getChildCountAction = viewGroupDefinition$expo_modules_core_release.getGetChildCountAction()) != null) {
            num = getChildCountAction.invoke(parent);
        }
        if (num == null) {
            num = Integer.valueOf(super.getChildCount(parent));
        }
        return num.intValue();
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public View getChildAt(ViewGroup parent, int r3) {
        Function2<ViewGroup, Integer, View> getChildAtAction;
        Intrinsics.checkNotNullParameter(parent, "parent");
        ViewGroupDefinition viewGroupDefinition$expo_modules_core_release = getViewWrapperDelegate().getViewGroupDefinition$expo_modules_core_release();
        if (viewGroupDefinition$expo_modules_core_release != null && (getChildAtAction = viewGroupDefinition$expo_modules_core_release.getGetChildAtAction()) != null) {
            return getChildAtAction.invoke(parent, Integer.valueOf(r3));
        }
        return super.getChildAt(parent, r3);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(ViewGroup parent, int r4) {
        Function2<ViewGroup, Integer, Unit> removeViewAtAction;
        Intrinsics.checkNotNullParameter(parent, "parent");
        ViewGroupDefinition viewGroupDefinition$expo_modules_core_release = getViewWrapperDelegate().getViewGroupDefinition$expo_modules_core_release();
        Unit unit = null;
        if (viewGroupDefinition$expo_modules_core_release != null && (removeViewAtAction = viewGroupDefinition$expo_modules_core_release.getRemoveViewAtAction()) != null) {
            removeViewAtAction.invoke(parent, Integer.valueOf(r4));
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            super.removeViewAt(parent, r4);
            Unit unit2 = Unit.INSTANCE;
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeView(ViewGroup parent, View view) {
        Function2<ViewGroup, View, Unit> removeViewAction;
        Intrinsics.checkNotNullParameter(parent, "parent");
        Intrinsics.checkNotNullParameter(view, "view");
        ViewGroupDefinition viewGroupDefinition$expo_modules_core_release = getViewWrapperDelegate().getViewGroupDefinition$expo_modules_core_release();
        Unit unit = null;
        if (viewGroupDefinition$expo_modules_core_release != null && (removeViewAction = viewGroupDefinition$expo_modules_core_release.getRemoveViewAction()) != null) {
            removeViewAction.invoke(parent, view);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            super.removeView(parent, view);
            Unit unit2 = Unit.INSTANCE;
        }
    }
}
