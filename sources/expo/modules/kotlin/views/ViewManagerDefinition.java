package expo.modules.kotlin.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import expo.modules.adapters.react.NativeModulesProxy;
import expo.modules.core.ViewManager;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.defaultmodules.ErrorManagerModule;
import expo.modules.kotlin.exception.CodedException;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewManagerDefinition.kt */
@Metadata(m184d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013\u0012\u0016\b\u0002\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\u0002\u0010\u0015J\u0016\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0005J\u0006\u0010$\u001a\u00020%J\u0016\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020)J\u0016\u0010*\u001a\u00020\u000f2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0006R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001f\u0010\u0014\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR \u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, m183d2 = {"Lexpo/modules/kotlin/views/ViewManagerDefinition;", "", "viewFactory", "Lkotlin/Function2;", "Landroid/content/Context;", "Lexpo/modules/kotlin/AppContext;", "Landroid/view/View;", "viewType", "Ljava/lang/Class;", "props", "", "", "Lexpo/modules/kotlin/views/AnyViewProp;", "onViewDestroys", "Lkotlin/Function1;", "", "callbacksDefinition", "Lexpo/modules/kotlin/views/CallbacksDefinition;", "viewGroupDefinition", "Lexpo/modules/kotlin/views/ViewGroupDefinition;", "onViewDidUpdateProps", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Class;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lexpo/modules/kotlin/views/CallbacksDefinition;Lexpo/modules/kotlin/views/ViewGroupDefinition;Lkotlin/jvm/functions/Function1;)V", "getCallbacksDefinition", "()Lexpo/modules/kotlin/views/CallbacksDefinition;", "getOnViewDestroys", "()Lkotlin/jvm/functions/Function1;", "getOnViewDidUpdateProps", "propsNames", "", "getPropsNames", "()Ljava/util/List;", "getViewGroupDefinition", "()Lexpo/modules/kotlin/views/ViewGroupDefinition;", "createView", "context", "appContext", "getViewManagerType", "Lexpo/modules/core/ViewManager$ViewManagerType;", "handleException", "view", "exception", "Lexpo/modules/kotlin/exception/CodedException;", "setProps", "propsToSet", "Lcom/facebook/react/bridge/ReadableMap;", "onView", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ViewManagerDefinition {
    private final CallbacksDefinition callbacksDefinition;
    private final Function1<View, Unit> onViewDestroys;
    private final Function1<View, Unit> onViewDidUpdateProps;
    private final Map<String, AnyViewProp> props;
    private final List<String> propsNames;
    private final Function2<Context, AppContext, View> viewFactory;
    private final ViewGroupDefinition viewGroupDefinition;
    private final Class<? extends View> viewType;

    /* JADX WARN: Multi-variable type inference failed */
    public ViewManagerDefinition(Function2<? super Context, ? super AppContext, ? extends View> viewFactory, Class<? extends View> viewType, Map<String, ? extends AnyViewProp> props, Function1<? super View, Unit> function1, CallbacksDefinition callbacksDefinition, ViewGroupDefinition viewGroupDefinition, Function1<? super View, Unit> function12) {
        Intrinsics.checkNotNullParameter(viewFactory, "viewFactory");
        Intrinsics.checkNotNullParameter(viewType, "viewType");
        Intrinsics.checkNotNullParameter(props, "props");
        this.viewFactory = viewFactory;
        this.viewType = viewType;
        this.props = props;
        this.onViewDestroys = function1;
        this.callbacksDefinition = callbacksDefinition;
        this.viewGroupDefinition = viewGroupDefinition;
        this.onViewDidUpdateProps = function12;
        this.propsNames = CollectionsKt.toList(props.keySet());
    }

    public /* synthetic */ ViewManagerDefinition(Function2 function2, Class cls, Map map, Function1 function1, CallbacksDefinition callbacksDefinition, ViewGroupDefinition viewGroupDefinition, Function1 function12, int r18, DefaultConstructorMarker defaultConstructorMarker) {
        this(function2, cls, map, (r18 & 8) != 0 ? null : function1, (r18 & 16) != 0 ? null : callbacksDefinition, (r18 & 32) != 0 ? null : viewGroupDefinition, (r18 & 64) != 0 ? null : function12);
    }

    public final Function1<View, Unit> getOnViewDestroys() {
        return this.onViewDestroys;
    }

    public final CallbacksDefinition getCallbacksDefinition() {
        return this.callbacksDefinition;
    }

    public final ViewGroupDefinition getViewGroupDefinition() {
        return this.viewGroupDefinition;
    }

    public final Function1<View, Unit> getOnViewDidUpdateProps() {
        return this.onViewDidUpdateProps;
    }

    public final View createView(Context context, AppContext appContext) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        return this.viewFactory.invoke(context, appContext);
    }

    public final List<String> getPropsNames() {
        return this.propsNames;
    }

    public final ViewManager.ViewManagerType getViewManagerType() {
        if (ViewGroup.class.isAssignableFrom(this.viewType)) {
            return ViewManager.ViewManagerType.GROUP;
        }
        return ViewManager.ViewManagerType.SIMPLE;
    }

    public final void setProps(ReadableMap propsToSet, View onView) {
        Intrinsics.checkNotNullParameter(propsToSet, "propsToSet");
        Intrinsics.checkNotNullParameter(onView, "onView");
        ReadableMapKeySetIterator keySetIterator = propsToSet.keySetIterator();
        Intrinsics.checkNotNullExpressionValue(keySetIterator, "propsToSet.keySetIterator()");
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            AnyViewProp anyViewProp = this.props.get(nextKey);
            if (anyViewProp != null) {
                Dynamic dynamic = propsToSet.getDynamic(nextKey);
                Intrinsics.checkNotNullExpressionValue(dynamic, "propsToSet.getDynamic(key)");
                try {
                    anyViewProp.set(dynamic, onView);
                    Unit unit = Unit.INSTANCE;
                    dynamic.recycle();
                }
            }
        }
    }

    public final void handleException(View view, CodedException exception) {
        ErrorManagerModule errorManager$expo_modules_core_release;
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(exception, "exception");
        Context context = view.getContext();
        ReactContext reactContext = context instanceof ReactContext ? (ReactContext) context : null;
        if (reactContext == null) {
            return;
        }
        CatalystInstance catalystInstance = reactContext.getCatalystInstance();
        NativeModule nativeModule = catalystInstance == null ? null : catalystInstance.getNativeModule("NativeUnimoduleProxy");
        NativeModulesProxy nativeModulesProxy = nativeModule instanceof NativeModulesProxy ? nativeModule : null;
        if (nativeModulesProxy == null || (errorManager$expo_modules_core_release = nativeModulesProxy.getKotlinInteropModuleRegistry().getAppContext$expo_modules_core_release().getErrorManager$expo_modules_core_release()) == null) {
            return;
        }
        errorManager$expo_modules_core_release.reportExceptionToLogBox(exception);
    }
}
