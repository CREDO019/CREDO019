package expo.modules.kotlin;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.adapters.react.NativeModulesProxy;
import expo.modules.core.ViewManager;
import expo.modules.kotlin.defaultmodules.NativeModulesProxyModuleKt;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent;
import expo.modules.kotlin.views.GroupViewManagerWrapper;
import expo.modules.kotlin.views.SimpleViewManagerWrapper;
import expo.modules.kotlin.views.ViewManagerDefinition;
import expo.modules.kotlin.views.ViewManagerWrapperDelegate;
import expo.modules.kotlin.views.ViewWrapperDelegateHolder;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Tuples;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: KotlinInteropModuleRegistry.kt */
@Metadata(m184d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ&\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJb\u0010\u001b\u001a*\u0012\b\u0012\u00060\u0015j\u0002`\u001d\u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001cj\u0002`\u001f0\u001e0\u001c22\b\u0002\u0010 \u001a,\u0012\u0004\u0012\u00020\u0015\u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001cj\u0002`\u001f0\u001e\u0012\u0004\u0012\u00020\u00130!J\u0014\u0010\"\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030#0\u001eJ(\u0010$\u001a$\u0012\b\u0012\u00060\u0015j\u0002`\u001d\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u001cj\u0002`%0\u001cJ\"\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u001e2\u0014\u0010(\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030#0\u001eJ\u000e\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0015J\u0006\u0010,\u001a\u00020\u0013J\u0006\u0010-\u001a\u00020\u0013J\u000e\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u000200J\u0014\u00101\u001a\u00020\u00132\f\u00102\u001a\b\u0012\u0004\u0012\u00020'0\u001eJ\u001e\u00103\u001a\u001a\u0012\u0004\u0012\u00020\u0015\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00010\u001c0\u001cR\u0014\u0010\n\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u00064"}, m183d2 = {"Lexpo/modules/kotlin/KotlinInteropModuleRegistry;", "", "modulesProvider", "Lexpo/modules/kotlin/ModulesProvider;", "legacyModuleRegistry", "Lexpo/modules/core/ModuleRegistry;", "reactContext", "Ljava/lang/ref/WeakReference;", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lexpo/modules/kotlin/ModulesProvider;Lexpo/modules/core/ModuleRegistry;Ljava/lang/ref/WeakReference;)V", "appContext", "Lexpo/modules/kotlin/AppContext;", "getAppContext$expo_modules_core_release", "()Lexpo/modules/kotlin/AppContext;", "registry", "Lexpo/modules/kotlin/ModuleRegistry;", "getRegistry", "()Lexpo/modules/kotlin/ModuleRegistry;", "callMethod", "", "moduleName", "", "method", "arguments", "Lcom/facebook/react/bridge/ReadableArray;", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "exportMethods", "", "Lexpo/modules/kotlin/ModuleName;", "", "Lexpo/modules/kotlin/ModuleMethodInfo;", "exportKey", "Lkotlin/Function2;", "exportViewManagers", "Lcom/facebook/react/uimanager/ViewManager;", "exportedModulesConstants", "Lexpo/modules/kotlin/ModuleConstants;", "extractViewManagersDelegateHolders", "Lexpo/modules/kotlin/views/ViewWrapperDelegateHolder;", "viewManagers", "hasModule", "", "name", "installJSIInterop", "onDestroy", "setLegacyModulesProxy", "proxyModule", "Lexpo/modules/adapters/react/NativeModulesProxy;", "updateModuleHoldersInViewManagers", "viewWrapperHolders", "viewManagersMetadata", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class KotlinInteropModuleRegistry {
    private final AppContext appContext;

    /* compiled from: KotlinInteropModuleRegistry.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[ViewManager.ViewManagerType.values().length];
            r0[ViewManager.ViewManagerType.SIMPLE.ordinal()] = 1;
            r0[ViewManager.ViewManagerType.GROUP.ordinal()] = 2;
            $EnumSwitchMapping$0 = r0;
        }
    }

    public KotlinInteropModuleRegistry(ModulesProvider modulesProvider, expo.modules.core.ModuleRegistry legacyModuleRegistry, WeakReference<ReactApplicationContext> reactContext) {
        Intrinsics.checkNotNullParameter(modulesProvider, "modulesProvider");
        Intrinsics.checkNotNullParameter(legacyModuleRegistry, "legacyModuleRegistry");
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.appContext = new AppContext(modulesProvider, legacyModuleRegistry, reactContext);
    }

    public final AppContext getAppContext$expo_modules_core_release() {
        return this.appContext;
    }

    private final ModuleRegistry getRegistry() {
        return this.appContext.getRegistry();
    }

    public final boolean hasModule(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return getRegistry().hasModule(name);
    }

    public final void callMethod(String moduleName, String method, ReadableArray arguments, Promise promise) {
        Intrinsics.checkNotNullParameter(moduleName, "moduleName");
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(arguments, "arguments");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            ModuleHolder moduleHolder = getRegistry().getModuleHolder(moduleName);
            if (moduleHolder == null) {
                throw new IllegalArgumentException(("Trying to call '" + method + "' on the non-existing module '" + moduleName + "'").toString());
            }
            moduleHolder.call(method, arguments, promise);
        } catch (CodedException e) {
            promise.reject(e);
        } catch (Throwable th) {
            promise.reject(new UnexpectedException(th));
        }
    }

    public final Map<String, Map<String, Object>> exportedModulesConstants() {
        ArrayList arrayList = new ArrayList();
        for (ModuleHolder moduleHolder : getRegistry()) {
            if (!Intrinsics.areEqual(moduleHolder.getName(), NativeModulesProxyModuleKt.NativeModulesProxyModuleName)) {
                arrayList.add(moduleHolder);
            }
        }
        ArrayList<ModuleHolder> arrayList2 = arrayList;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList2, 10)), 16));
        for (ModuleHolder moduleHolder2 : arrayList2) {
            Tuples m176to = TuplesKt.m176to(moduleHolder2.getName(), moduleHolder2.getDefinition().getConstantsProvider().invoke());
            linkedHashMap.put(m176to.getFirst(), m176to.getSecond());
        }
        return linkedHashMap;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Map exportMethods$default(KotlinInteropModuleRegistry kotlinInteropModuleRegistry, Function2 function2, int r2, Object obj) {
        if ((r2 & 1) != 0) {
            function2 = new Function2<String, List<? extends Map<String, ? extends Object>>, Unit>() { // from class: expo.modules.kotlin.KotlinInteropModuleRegistry$exportMethods$1
                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(String noName_0, List<? extends Map<String, ? extends Object>> noName_1) {
                    Intrinsics.checkNotNullParameter(noName_0, "$noName_0");
                    Intrinsics.checkNotNullParameter(noName_1, "$noName_1");
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(String str, List<? extends Map<String, ? extends Object>> list) {
                    invoke2(str, list);
                    return Unit.INSTANCE;
                }
            };
        }
        return kotlinInteropModuleRegistry.exportMethods(function2);
    }

    public final Map<String, List<Map<String, Object>>> exportMethods(Function2<? super String, ? super List<? extends Map<String, ? extends Object>>, Unit> exportKey) {
        Intrinsics.checkNotNullParameter(exportKey, "exportKey");
        ModuleRegistry registry = getRegistry();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(registry, 10)), 16));
        for (ModuleHolder moduleHolder : registry) {
            Map<String, BaseAsyncFunctionComponent> asyncFunctions = moduleHolder.getDefinition().getAsyncFunctions();
            ArrayList arrayList = new ArrayList(asyncFunctions.size());
            for (Map.Entry<String, BaseAsyncFunctionComponent> entry : asyncFunctions.entrySet()) {
                arrayList.add(MapsKt.mapOf(TuplesKt.m176to("name", entry.getKey()), TuplesKt.m176to("argumentsCount", Integer.valueOf(entry.getValue().getArgsCount$expo_modules_core_release()))));
            }
            ArrayList arrayList2 = arrayList;
            exportKey.invoke(moduleHolder.getName(), arrayList2);
            Tuples m176to = TuplesKt.m176to(moduleHolder.getName(), arrayList2);
            linkedHashMap.put(m176to.getFirst(), m176to.getSecond());
        }
        return linkedHashMap;
    }

    public final List<com.facebook.react.uimanager.ViewManager<?, ?>> exportViewManagers() {
        com.facebook.react.uimanager.ViewManager simpleViewManagerWrapper;
        ArrayList arrayList = new ArrayList();
        Iterator<ModuleHolder> it = getRegistry().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ModuleHolder next = it.next();
            if (next.getDefinition().getViewManagerDefinition() != null) {
                arrayList.add(next);
            }
        }
        ArrayList<ModuleHolder> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        for (ModuleHolder moduleHolder : arrayList2) {
            ViewManagerWrapperDelegate viewManagerWrapperDelegate = new ViewManagerWrapperDelegate(moduleHolder);
            ViewManagerDefinition viewManagerDefinition = moduleHolder.getDefinition().getViewManagerDefinition();
            Intrinsics.checkNotNull(viewManagerDefinition);
            int r2 = WhenMappings.$EnumSwitchMapping$0[viewManagerDefinition.getViewManagerType().ordinal()];
            if (r2 == 1) {
                simpleViewManagerWrapper = new SimpleViewManagerWrapper(viewManagerWrapperDelegate);
            } else if (r2 != 2) {
                throw new NoWhenBranchMatchedException();
            } else {
                simpleViewManagerWrapper = new GroupViewManagerWrapper(viewManagerWrapperDelegate);
            }
            arrayList3.add(simpleViewManagerWrapper);
        }
        return arrayList3;
    }

    public final Map<String, Map<String, Object>> viewManagersMetadata() {
        ArrayList arrayList = new ArrayList();
        for (ModuleHolder moduleHolder : getRegistry()) {
            if (moduleHolder.getDefinition().getViewManagerDefinition() != null) {
                arrayList.add(moduleHolder);
            }
        }
        ArrayList<ModuleHolder> arrayList2 = arrayList;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList2, 10)), 16));
        for (ModuleHolder moduleHolder2 : arrayList2) {
            String name = moduleHolder2.getName();
            ViewManagerDefinition viewManagerDefinition = moduleHolder2.getDefinition().getViewManagerDefinition();
            List<String> propsNames = viewManagerDefinition == null ? null : viewManagerDefinition.getPropsNames();
            if (propsNames == null) {
                propsNames = CollectionsKt.emptyList();
            }
            Tuples m176to = TuplesKt.m176to(name, MapsKt.mapOf(TuplesKt.m176to("propsNames", propsNames)));
            linkedHashMap.put(m176to.getFirst(), m176to.getSecond());
        }
        return linkedHashMap;
    }

    public final List<ViewWrapperDelegateHolder> extractViewManagersDelegateHolders(List<? extends com.facebook.react.uimanager.ViewManager<?, ?>> viewManagers) {
        Intrinsics.checkNotNullParameter(viewManagers, "viewManagers");
        ArrayList arrayList = new ArrayList();
        for (Object obj : viewManagers) {
            if (obj instanceof ViewWrapperDelegateHolder) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final void updateModuleHoldersInViewManagers(List<? extends ViewWrapperDelegateHolder> viewWrapperHolders) {
        Intrinsics.checkNotNullParameter(viewWrapperHolders, "viewWrapperHolders");
        List<? extends ViewWrapperDelegateHolder> list = viewWrapperHolders;
        ArrayList<ViewManagerWrapperDelegate> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (ViewWrapperDelegateHolder viewWrapperDelegateHolder : list) {
            arrayList.add(viewWrapperDelegateHolder.getViewWrapperDelegate());
        }
        for (ViewManagerWrapperDelegate viewManagerWrapperDelegate : arrayList) {
            ModuleHolder moduleHolder = getRegistry().getModuleHolder(viewManagerWrapperDelegate.getModuleHolder$expo_modules_core_release().getName());
            if (moduleHolder == null) {
                String name = viewManagerWrapperDelegate.getModuleHolder$expo_modules_core_release().getName();
                throw new IllegalArgumentException(("Cannot update the module holder for " + name + ".").toString());
            }
            viewManagerWrapperDelegate.setModuleHolder$expo_modules_core_release(moduleHolder);
        }
    }

    public final void onDestroy() {
        this.appContext.onDestroy$expo_modules_core_release();
        CoreLogger.getLogger().info("✅ KotlinInteropModuleRegistry was destroyed");
    }

    public final void installJSIInterop() {
        this.appContext.installJSIInterop();
    }

    public final void setLegacyModulesProxy(NativeModulesProxy proxyModule) {
        Intrinsics.checkNotNullParameter(proxyModule, "proxyModule");
        this.appContext.setLegacyModulesProxyHolder$expo_modules_core_release(new WeakReference<>(proxyModule));
    }
}
