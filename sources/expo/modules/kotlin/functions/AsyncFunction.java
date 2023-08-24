package expo.modules.kotlin.functions;

import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JNIAsyncFunctionBody;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.jni.PromiseImpl;
import expo.modules.kotlin.types.AnyType;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AsyncFunction.kt */
@Metadata(m184d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u001d\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0010¢\u0006\u0002\b\u000eJ \u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001d\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H ¢\u0006\u0002\b\u0017J'\u0010\u0016\u001a\u00020\t2\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u00052\u0006\u0010\u0014\u001a\u00020\u0015H ¢\u0006\u0004\b\u0017\u0010\u0019¨\u0006\u001a"}, m183d2 = {"Lexpo/modules/kotlin/functions/AsyncFunction;", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;)V", "attachToJSObject", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "attachToJSObject$expo_modules_core_release", NotificationCompat.CATEGORY_CALL, "holder", "Lexpo/modules/kotlin/ModuleHolder;", "args", "Lcom/facebook/react/bridge/ReadableArray;", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "callUserImplementation", "callUserImplementation$expo_modules_core_release", "", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public abstract class AsyncFunction extends BaseAsyncFunctionComponent {

    /* compiled from: AsyncFunction.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[Queues.values().length];
            r0[Queues.MAIN.ordinal()] = 1;
            r0[Queues.DEFAULT.ordinal()] = 2;
            $EnumSwitchMapping$0 = r0;
        }
    }

    public abstract void callUserImplementation$expo_modules_core_release(ReadableArray readableArray, Promise promise) throws CodedException;

    public abstract void callUserImplementation$expo_modules_core_release(Object[] objArr, Promise promise);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsyncFunction(String name, AnyType[] desiredArgsTypes) {
        super(name, desiredArgsTypes);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desiredArgsTypes, "desiredArgsTypes");
    }

    @Override // expo.modules.kotlin.functions.BaseAsyncFunctionComponent
    public void call(ModuleHolder holder, ReadableArray args, Promise promise) {
        CoroutineScope mainQueue;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(promise, "promise");
        int r0 = WhenMappings.$EnumSwitchMapping$0[getQueue().ordinal()];
        if (r0 == 1) {
            mainQueue = holder.getModule().getAppContext().getMainQueue();
        } else if (r0 != 2) {
            throw new NoWhenBranchMatchedException();
        } else {
            mainQueue = null;
        }
        CoroutineScope coroutineScope = mainQueue;
        if (coroutineScope != null) {
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AsyncFunction$call$1(promise, this, holder, args, null), 3, null);
        } else {
            callUserImplementation$expo_modules_core_release(args, promise);
        }
    }

    @Override // expo.modules.kotlin.functions.AnyFunction
    public void attachToJSObject$expo_modules_core_release(final AppContext appContext, final JavaScriptModuleObject jsObject) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(jsObject, "jsObject");
        String name = getName();
        int argsCount$expo_modules_core_release = getArgsCount$expo_modules_core_release();
        AnyType[] desiredArgsTypes = getDesiredArgsTypes();
        ArrayList arrayList = new ArrayList(desiredArgsTypes.length);
        int length = desiredArgsTypes.length;
        int r6 = 0;
        while (r6 < length) {
            AnyType anyType = desiredArgsTypes[r6];
            r6++;
            arrayList.add(anyType.getCppRequiredTypes());
        }
        Object[] array = arrayList.toArray(new ExpectedType[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        jsObject.registerAsyncFunction(name, argsCount$expo_modules_core_release, (ExpectedType[]) array, new JNIAsyncFunctionBody() { // from class: expo.modules.kotlin.functions.AsyncFunction$$ExternalSyntheticLambda0
            @Override // expo.modules.kotlin.jni.JNIAsyncFunctionBody
            public final void invoke(Object[] objArr, PromiseImpl promiseImpl) {
                AsyncFunction.m1672attachToJSObject$lambda1(AsyncFunction.this, appContext, jsObject, objArr, promiseImpl);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: attachToJSObject$lambda-1  reason: not valid java name */
    public static final void m1672attachToJSObject$lambda1(AsyncFunction this$0, AppContext appContext, JavaScriptModuleObject jsObject, Object[] args, PromiseImpl bridgePromise) {
        CoroutineScope mainQueue;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(appContext, "$appContext");
        Intrinsics.checkNotNullParameter(jsObject, "$jsObject");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(bridgePromise, "bridgePromise");
        int r0 = WhenMappings.$EnumSwitchMapping$0[this$0.getQueue().ordinal()];
        if (r0 == 1) {
            mainQueue = appContext.getMainQueue();
        } else if (r0 != 2) {
            throw new NoWhenBranchMatchedException();
        } else {
            mainQueue = appContext.getModulesQueue();
        }
        BuildersKt__Builders_commonKt.launch$default(mainQueue, null, null, new AsyncFunction$attachToJSObject$2$1(bridgePromise, this$0, jsObject, args, null), 3, null);
    }
}
