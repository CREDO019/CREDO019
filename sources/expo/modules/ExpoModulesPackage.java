package expo.modules;

import android.util.Log;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import expo.modules.adapters.react.ModuleRegistryAdapter;
import expo.modules.core.ModulePriorities;
import expo.modules.core.interfaces.Package;
import java.util.Comparator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: ExpoModulesPackage.kt */
@Metadata(m184d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001e\u0010\f\u001a\u0010\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\r0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, m183d2 = {"Lexpo/modules/ExpoModulesPackage;", "Lcom/facebook/react/ReactPackage;", "()V", "moduleRegistryAdapter", "Lexpo/modules/adapters/react/ModuleRegistryAdapter;", "getModuleRegistryAdapter", "()Lexpo/modules/adapters/react/ModuleRegistryAdapter;", "createNativeModules", "", "Lcom/facebook/react/bridge/NativeModule;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "createViewManagers", "Lcom/facebook/react/uimanager/ViewManager;", "Companion", "expo_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ExpoModulesPackage implements ReactPackage {
    public static final Companion Companion = new Companion(null);
    private static final Lazy<List<Package>> packageList$delegate = LazyKt.lazy(new Functions<List<? extends Package>>() { // from class: expo.modules.ExpoModulesPackage$Companion$packageList$2
        @Override // kotlin.jvm.functions.Functions
        public final List<? extends Package> invoke() {
            try {
                Object invoke = Class.forName("expo.modules.ExpoModulesPackageList").getMethod("getPackageList", new Class[0]).invoke(null, new Object[0]);
                if (invoke == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<expo.modules.core.interfaces.Package>");
                }
                return CollectionsKt.sortedWith((List) invoke, new Comparator() { // from class: expo.modules.ExpoModulesPackage$Companion$packageList$2$invoke$$inlined$sortedByDescending$1
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt.compareValues(Integer.valueOf(ModulePriorities.INSTANCE.get(Reflection.getOrCreateKotlinClass(((Package) t2).getClass()).getQualifiedName())), Integer.valueOf(ModulePriorities.INSTANCE.get(Reflection.getOrCreateKotlinClass(((Package) t).getClass()).getQualifiedName())));
                    }
                });
            } catch (Exception e) {
                Log.e("ExpoModulesPackage", "Couldn't get expo package list.", e);
                return CollectionsKt.emptyList();
            }
        }
    });
    private final ModuleRegistryAdapter moduleRegistryAdapter = new ModuleRegistryAdapter(Companion.getPackageList());

    public final ModuleRegistryAdapter getModuleRegistryAdapter() {
        return this.moduleRegistryAdapter;
    }

    /* compiled from: ExpoModulesPackage.kt */
    @Metadata(m184d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R'\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048FX\u0086\u0084\u0002¢\u0006\u0012\n\u0004\b\t\u0010\n\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, m183d2 = {"Lexpo/modules/ExpoModulesPackage$Companion;", "", "()V", "packageList", "", "Lexpo/modules/core/interfaces/Package;", "getPackageList$annotations", "getPackageList", "()Ljava/util/List;", "packageList$delegate", "Lkotlin/Lazy;", "expo_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getPackageList$annotations() {
        }

        private Companion() {
        }

        public final List<Package> getPackageList() {
            return (List) ExpoModulesPackage.packageList$delegate.getValue();
        }
    }

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        List<NativeModule> createNativeModules = this.moduleRegistryAdapter.createNativeModules(reactContext);
        Intrinsics.checkNotNullExpressionValue(createNativeModules, "moduleRegistryAdapter.cr…tiveModules(reactContext)");
        return createNativeModules;
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager<?, ?>> createViewManagers(ReactApplicationContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        List<ViewManager<?, ?>> createViewManagers = this.moduleRegistryAdapter.createViewManagers(reactContext);
        Intrinsics.checkNotNullExpressionValue(createViewManagers, "moduleRegistryAdapter.cr…iewManagers(reactContext)");
        return createViewManagers;
    }
}
