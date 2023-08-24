package expo.modules.kotlin.views;

import android.content.Context;
import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import expo.modules.core.logging.Logger;
import expo.modules.kotlin.CoreLogger;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.events.KModuleEventEmitterWrapperKt;
import expo.modules.kotlin.viewevent.ViewEventDelegate;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMapping;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import kotlin.reflect.KProperty1;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.KCallablesJvm;

/* compiled from: ViewManagerWrapperDelegate.kt */
@Metadata(m184d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u000e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u001aJ\u0014\u0010\u001b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001cJ\u0015\u0010\u001d\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017¢\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\u0004R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00118@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\""}, m183d2 = {"Lexpo/modules/kotlin/views/ViewManagerWrapperDelegate;", "", "moduleHolder", "Lexpo/modules/kotlin/ModuleHolder;", "(Lexpo/modules/kotlin/ModuleHolder;)V", "definition", "Lexpo/modules/kotlin/views/ViewManagerDefinition;", "getDefinition", "()Lexpo/modules/kotlin/views/ViewManagerDefinition;", "getModuleHolder$expo_modules_core_release", "()Lexpo/modules/kotlin/ModuleHolder;", "setModuleHolder$expo_modules_core_release", "name", "", "getName", "()Ljava/lang/String;", "viewGroupDefinition", "Lexpo/modules/kotlin/views/ViewGroupDefinition;", "getViewGroupDefinition$expo_modules_core_release", "()Lexpo/modules/kotlin/views/ViewGroupDefinition;", "configureView", "", "view", "Landroid/view/View;", "createView", "context", "Landroid/content/Context;", "getExportedCustomDirectEventTypeConstants", "", "onDestroy", "(Landroid/view/View;)Lkotlin/Unit;", "setProxiedProperties", "proxiedProperties", "Lcom/facebook/react/bridge/ReadableMap;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ViewManagerWrapperDelegate {
    private ModuleHolder moduleHolder;

    public ViewManagerWrapperDelegate(ModuleHolder moduleHolder) {
        Intrinsics.checkNotNullParameter(moduleHolder, "moduleHolder");
        this.moduleHolder = moduleHolder;
    }

    public final ModuleHolder getModuleHolder$expo_modules_core_release() {
        return this.moduleHolder;
    }

    public final void setModuleHolder$expo_modules_core_release(ModuleHolder moduleHolder) {
        Intrinsics.checkNotNullParameter(moduleHolder, "<set-?>");
        this.moduleHolder = moduleHolder;
    }

    private final ViewManagerDefinition getDefinition() {
        ViewManagerDefinition viewManagerDefinition = this.moduleHolder.getDefinition().getViewManagerDefinition();
        if (viewManagerDefinition != null) {
            return viewManagerDefinition;
        }
        throw new IllegalArgumentException("Required value was null.".toString());
    }

    public final ViewGroupDefinition getViewGroupDefinition$expo_modules_core_release() {
        return getDefinition().getViewGroupDefinition();
    }

    public final String getName() {
        return this.moduleHolder.getName();
    }

    public final View createView(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        View createView = getDefinition().createView(context, this.moduleHolder.getModule().getAppContext());
        configureView(createView);
        return createView;
    }

    public final void setProxiedProperties(View view, ReadableMap proxiedProperties) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(proxiedProperties, "proxiedProperties");
        getDefinition().setProps(proxiedProperties, view);
        Function1<View, Unit> onViewDidUpdateProps = getDefinition().getOnViewDidUpdateProps();
        if (onViewDidUpdateProps == null) {
            return;
        }
        onViewDidUpdateProps.invoke(view);
    }

    public final Unit onDestroy(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Function1<View, Unit> onViewDestroys = getDefinition().getOnViewDestroys();
        if (onViewDestroys == null) {
            return null;
        }
        onViewDestroys.invoke(view);
        return Unit.INSTANCE;
    }

    public final Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        String[] names;
        MapBuilder.Builder builder = MapBuilder.builder();
        CallbacksDefinition callbacksDefinition = getDefinition().getCallbacksDefinition();
        if (callbacksDefinition != null && (names = callbacksDefinition.getNames()) != null) {
            int r2 = 0;
            int length = names.length;
            while (r2 < length) {
                String str = names[r2];
                r2++;
                builder.put(KModuleEventEmitterWrapperKt.normalizeEventName(str), MapBuilder.m1261of("registrationName", str));
            }
        }
        return builder.build();
    }

    private final void configureView(View view) {
        CallbacksDefinition callbacksDefinition = getDefinition().getCallbacksDefinition();
        String[] names = callbacksDefinition == null ? null : callbacksDefinition.getNames();
        if (names == null) {
            return;
        }
        KClass kotlinClass = JvmClassMapping.getKotlinClass(view.getClass());
        Collection declaredMemberProperties = KClasses.getDeclaredMemberProperties(kotlinClass);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(declaredMemberProperties, 10)), 16));
        for (Object obj : declaredMemberProperties) {
            linkedHashMap.put(((KProperty1) obj).getName(), obj);
        }
        int r3 = 0;
        int length = names.length;
        while (r3 < length) {
            String str = names[r3];
            r3++;
            Object obj2 = linkedHashMap.get(str);
            if (obj2 != null) {
                KProperty1 kProperty1 = (KProperty1) obj2;
                KCallablesJvm.setAccessible(kProperty1, true);
                Object delegate = kProperty1.getDelegate(view);
                if (delegate == null) {
                    Logger logger = CoreLogger.getLogger();
                    String simpleName = kotlinClass.getSimpleName();
                    Logger.warn$default(logger, "⚠️ Property delegate for `" + str + "` in " + simpleName + " does not exist", null, 2, null);
                } else {
                    ViewEventDelegate viewEventDelegate = delegate instanceof ViewEventDelegate ? (ViewEventDelegate) delegate : null;
                    if (viewEventDelegate == null) {
                        Logger logger2 = CoreLogger.getLogger();
                        Logger.warn$default(logger2, "⚠️ Property delegate for `" + str + "` cannot be cased to `ViewCallbackDelegate`", null, 2, null);
                    } else {
                        viewEventDelegate.setValidated$expo_modules_core_release(true);
                    }
                }
            } else {
                Logger logger3 = CoreLogger.getLogger();
                String simpleName2 = kotlinClass.getSimpleName();
                Logger.warn$default(logger3, "⚠️ Property `" + str + "` does not exist in " + simpleName2, null, 2, null);
            }
        }
    }
}
