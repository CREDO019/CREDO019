package expo.modules.kotlin;

import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.util.JSStackTrace;
import expo.modules.core.errors.CodedException;
import expo.modules.kotlin.activityresult.AppContextActivityResultCaller;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventListener;
import expo.modules.kotlin.events.EventListenerWithPayload;
import expo.modules.kotlin.events.EventListenerWithSenderAndPayload;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.exception.FunctionCallException;
import expo.modules.kotlin.exception.MethodNotFoundException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.functions.AnyFunction;
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.PropertyComponent;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* compiled from: ModuleHolder.kt */
@Metadata(m184d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001d\u001a\u00020\u0016J\u000e\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 J!\u0010\u001e\u001a\u00020\u0016\"\u0004\b\u0000\u0010!2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\"\u001a\u0002H!¢\u0006\u0002\u0010#J/\u0010\u001e\u001a\u00020\u0016\"\u0004\b\u0000\u0010$\"\u0004\b\u0001\u0010!2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010%\u001a\u0002H$2\u0006\u0010\"\u001a\u0002H!¢\u0006\u0002\u0010&J\u0006\u0010'\u001a\u00020\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\t\u001a\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006("}, m183d2 = {"Lexpo/modules/kotlin/ModuleHolder;", "", "module", "Lexpo/modules/kotlin/modules/Module;", "(Lexpo/modules/kotlin/modules/Module;)V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "getDefinition", "()Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "getJsObject", "()Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "jsObject$delegate", "Lkotlin/Lazy;", "getModule", "()Lexpo/modules/kotlin/modules/Module;", "name", "", "getName", "()Ljava/lang/String;", NotificationCompat.CATEGORY_CALL, "", JSStackTrace.METHOD_NAME_KEY, "args", "Lcom/facebook/react/bridge/ReadableArray;", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "callSync", "cleanUp", "post", "eventName", "Lexpo/modules/kotlin/events/EventName;", "Payload", "payload", "(Lexpo/modules/kotlin/events/EventName;Ljava/lang/Object;)V", "Sender", "sender", "(Lexpo/modules/kotlin/events/EventName;Ljava/lang/Object;Ljava/lang/Object;)V", "registerContracts", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class ModuleHolder {
    private final ModuleDefinitionData definition;
    private final Lazy jsObject$delegate;
    private final Module module;

    public ModuleHolder(Module module) {
        Intrinsics.checkNotNullParameter(module, "module");
        this.module = module;
        this.definition = module.definition();
        this.jsObject$delegate = LazyKt.lazy(new Functions<JavaScriptModuleObject>() { // from class: expo.modules.kotlin.ModuleHolder$jsObject$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Functions
            public final JavaScriptModuleObject invoke() {
                JavaScriptModuleObject javaScriptModuleObject = new JavaScriptModuleObject(ModuleHolder.this.getName());
                ModuleHolder moduleHolder = ModuleHolder.this;
                WritableNativeMap convertedConstants = Arguments.makeNativeMap(moduleHolder.getDefinition().getConstantsProvider().invoke());
                Intrinsics.checkNotNullExpressionValue(convertedConstants, "convertedConstants");
                javaScriptModuleObject.exportConstants(convertedConstants);
                ConcatIterator<AnyFunction> functions = moduleHolder.getDefinition().getFunctions();
                while (functions.hasNext()) {
                    functions.next().attachToJSObject$expo_modules_core_release(moduleHolder.getModule().getAppContext(), javaScriptModuleObject);
                }
                for (Map.Entry<String, PropertyComponent> entry : moduleHolder.getDefinition().getProperties().entrySet()) {
                    entry.getValue().attachToJSObject(javaScriptModuleObject);
                }
                return javaScriptModuleObject;
            }
        });
    }

    public final Module getModule() {
        return this.module;
    }

    public final ModuleDefinitionData getDefinition() {
        return this.definition;
    }

    public final String getName() {
        return this.definition.getName();
    }

    public final JavaScriptModuleObject getJsObject() {
        return (JavaScriptModuleObject) this.jsObject$delegate.getValue();
    }

    public final void call(String methodName, ReadableArray args, Promise promise) {
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(promise, "promise");
        try {
            BaseAsyncFunctionComponent baseAsyncFunctionComponent = getDefinition().getAsyncFunctions().get(methodName);
            if (baseAsyncFunctionComponent == null) {
                throw new MethodNotFoundException();
            }
            baseAsyncFunctionComponent.call(this, args, promise);
            Unit unit = Unit.INSTANCE;
        } catch (CodedException e) {
            String code = e.getCode();
            Intrinsics.checkNotNullExpressionValue(code, "e.code");
            throw new FunctionCallException(methodName, getDefinition().getName(), new expo.modules.kotlin.exception.CodedException(code, e.getMessage(), e.getCause()));
        } catch (expo.modules.kotlin.exception.CodedException e2) {
            throw new FunctionCallException(methodName, getDefinition().getName(), e2);
        } catch (Throwable th) {
            throw new FunctionCallException(methodName, getDefinition().getName(), new UnexpectedException(th));
        }
    }

    public final Object callSync(String methodName, ReadableArray args) {
        Intrinsics.checkNotNullParameter(methodName, "methodName");
        Intrinsics.checkNotNullParameter(args, "args");
        SyncFunctionComponent syncFunctionComponent = this.definition.getSyncFunctions().get(methodName);
        if (syncFunctionComponent == null) {
            throw new MethodNotFoundException();
        }
        return syncFunctionComponent.call(args);
    }

    public final void post(EventName eventName) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        EventListener eventListener = this.definition.getEventListeners().get(eventName);
        if (eventListener == null) {
            return;
        }
        BasicEventListener basicEventListener = eventListener instanceof BasicEventListener ? (BasicEventListener) eventListener : null;
        if (basicEventListener == null) {
            return;
        }
        basicEventListener.call();
    }

    public final <Payload> void post(EventName eventName, Payload payload) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        EventListener eventListener = this.definition.getEventListeners().get(eventName);
        if (eventListener == null) {
            return;
        }
        EventListenerWithPayload eventListenerWithPayload = eventListener instanceof EventListenerWithPayload ? (EventListenerWithPayload) eventListener : null;
        if (eventListenerWithPayload == null) {
            return;
        }
        eventListenerWithPayload.call(payload);
    }

    public final <Sender, Payload> void post(EventName eventName, Sender sender, Payload payload) {
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        EventListener eventListener = this.definition.getEventListeners().get(eventName);
        if (eventListener == null) {
            return;
        }
        EventListenerWithSenderAndPayload eventListenerWithSenderAndPayload = eventListener instanceof EventListenerWithSenderAndPayload ? (EventListenerWithSenderAndPayload) eventListener : null;
        if (eventListenerWithSenderAndPayload == null) {
            return;
        }
        eventListenerWithSenderAndPayload.call(sender, payload);
    }

    public final void registerContracts() {
        Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> registerContracts = this.definition.getRegisterContracts();
        if (registerContracts == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(getModule().getAppContext().getMainQueue(), null, null, new ModuleHolder$registerContracts$1$1(registerContracts, this, null), 3, null);
    }

    public final void cleanUp() {
        this.module.cleanUp$expo_modules_core_release();
    }
}
