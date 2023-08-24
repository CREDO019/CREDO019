package expo.modules.kotlin;

import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.modules.Module;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMarkers;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: ModuleRegistry.kt */
@Metadata(m184d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0018\u0010\u0010\u001a\u0004\u0018\u0001H\u0011\"\u0006\b\u0000\u0010\u0011\u0018\u0001H\u0086\b¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\tJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0016\u001a\u00020\u0013J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0014\u001a\u00020\tJ\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\tJ\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001aH\u0096\u0002J\u000e\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u001dJ!\u0010\u001b\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u001e2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u0002H\u001e¢\u0006\u0002\u0010 J/\u0010\u001b\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u001e\"\u0004\b\u0001\u0010!2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u0002H\u001e2\u0006\u0010\"\u001a\u0002H!¢\u0006\u0002\u0010#J\u000e\u0010$\u001a\u00020\u00002\u0006\u0010%\u001a\u00020&J\u000e\u0010$\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0013R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00020\b8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006'"}, m183d2 = {"Lexpo/modules/kotlin/ModuleRegistry;", "", "Lexpo/modules/kotlin/ModuleHolder;", "appContext", "Ljava/lang/ref/WeakReference;", "Lexpo/modules/kotlin/AppContext;", "(Ljava/lang/ref/WeakReference;)V", "registry", "", "", "getRegistry$annotations", "()V", "getRegistry", "()Ljava/util/Map;", "cleanUp", "", "getModule", "T", "()Ljava/lang/Object;", "Lexpo/modules/kotlin/modules/Module;", "name", "getModuleHolder", "module", "hasModule", "", "iterator", "", "post", "eventName", "Lexpo/modules/kotlin/events/EventName;", "Sender", "sender", "(Lexpo/modules/kotlin/events/EventName;Ljava/lang/Object;)V", "Payload", "payload", "(Lexpo/modules/kotlin/events/EventName;Ljava/lang/Object;Ljava/lang/Object;)V", "register", "provider", "Lexpo/modules/kotlin/ModulesProvider;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ModuleRegistry implements Iterable<ModuleHolder>, KMarkers {
    private final WeakReference<AppContext> appContext;
    private final Map<String, ModuleHolder> registry;

    public static /* synthetic */ void getRegistry$annotations() {
    }

    public ModuleRegistry(WeakReference<AppContext> appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.appContext = appContext;
        this.registry = new LinkedHashMap();
    }

    public final Map<String, ModuleHolder> getRegistry() {
        return this.registry;
    }

    public final void register(Module module) {
        Intrinsics.checkNotNullParameter(module, "module");
        final ModuleHolder moduleHolder = new ModuleHolder(module);
        AppContext appContext = this.appContext.get();
        if (appContext == null) {
            throw new IllegalArgumentException("Cannot create a module for invalid app context.".toString());
        }
        module.set_appContext$expo_modules_core_release(appContext);
        module.setCoroutineScopeDelegate(LazyKt.lazy(new Functions<CoroutineScope>() { // from class: expo.modules.kotlin.ModuleRegistry$register$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final CoroutineScope invoke() {
                return CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)).plus(new CoroutineName(ModuleHolder.this.getDefinition().getName())));
            }
        }));
        moduleHolder.post(EventName.MODULE_CREATE);
        moduleHolder.registerContracts();
        this.registry.put(moduleHolder.getName(), moduleHolder);
    }

    public final ModuleRegistry register(ModulesProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        Iterator<T> it = provider.getModulesList().iterator();
        while (it.hasNext()) {
            Module module = (Module) ((Class) it.next()).newInstance();
            Intrinsics.checkNotNullExpressionValue(module, "module");
            register(module);
        }
        return this;
    }

    public final boolean hasModule(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.registry.containsKey(name);
    }

    public final Module getModule(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ModuleHolder moduleHolder = this.registry.get(name);
        if (moduleHolder == null) {
            return null;
        }
        return moduleHolder.getModule();
    }

    public final /* synthetic */ <T> T getModule() {
        T t;
        Iterator<T> it = getRegistry().values().iterator();
        while (true) {
            if (!it.hasNext()) {
                t = null;
                break;
            }
            t = it.next();
            Module module = ((ModuleHolder) t).getModule();
            Intrinsics.reifiedOperationMarker(3, "T");
            if (module instanceof Object) {
                break;
            }
        }
        ModuleHolder moduleHolder = (ModuleHolder) t;
        Module module2 = moduleHolder != null ? moduleHolder.getModule() : null;
        Intrinsics.reifiedOperationMarker(2, "T");
        return (T) module2;
    }

    public final ModuleHolder getModuleHolder(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.registry.get(name);
    }

    public final ModuleHolder getModuleHolder(Module module) {
        Object obj;
        boolean z;
        Intrinsics.checkNotNullParameter(module, "module");
        Iterator<T> it = this.registry.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((ModuleHolder) obj).getModule() == module) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (z) {
                break;
            }
        }
        return (ModuleHolder) obj;
    }

    public final void post(EventName eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Iterator<ModuleHolder> it = iterator();
        while (it.hasNext()) {
            it.next().post(eventName);
        }
    }

    public final <Sender> void post(EventName eventName, Sender sender) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Iterator<ModuleHolder> it = iterator();
        while (it.hasNext()) {
            it.next().post(eventName, sender);
        }
    }

    public final <Sender, Payload> void post(EventName eventName, Sender sender, Payload payload) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        Iterator<ModuleHolder> it = iterator();
        while (it.hasNext()) {
            it.next().post(eventName, sender, payload);
        }
    }

    @Override // java.lang.Iterable
    public Iterator<ModuleHolder> iterator() {
        return this.registry.values().iterator();
    }

    public final void cleanUp() {
        Iterator<ModuleHolder> it = iterator();
        while (it.hasNext()) {
            it.next().cleanUp();
        }
        this.registry.clear();
        CoreLogger.getLogger().info("✅ ModuleRegistry was destroyed");
    }
}
