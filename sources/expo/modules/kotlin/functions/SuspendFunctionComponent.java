package expo.modules.kotlin.functions;

import androidx.core.app.NotificationCompat;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.ModuleHolder;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JNIAsyncFunctionBody;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.jni.PromiseImpl;
import expo.modules.kotlin.types.AnyType;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SuspendFunctionComponent.kt */
@Metadata(m184d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001Bh\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012H\u0010\u0007\u001aD\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u001d\u0012\u001b\u0012\b\b\u0001\u0012\u0004\u0018\u00010\n0\u0005¢\u0006\f\b\u000b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\r\u0012\u0006\u0012\u0004\u0018\u00010\n0\b¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u001d\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0010¢\u0006\u0002\b\u0017J \u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016RU\u0010\u0007\u001aD\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u001d\u0012\u001b\u0012\b\b\u0001\u0012\u0004\u0018\u00010\n0\u0005¢\u0006\f\b\u000b\u0012\b\b\u0002\u0012\u0004\b\b(\f\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\r\u0012\u0006\u0012\u0004\u0018\u00010\n0\b¢\u0006\u0002\b\u000eX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, m183d2 = {"Lexpo/modules/kotlin/functions/SuspendFunctionComponent;", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", TtmlNode.TAG_BODY, "Lkotlin/Function3;", "Lkotlinx/coroutines/CoroutineScope;", "", "Lkotlin/ParameterName;", "args", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;Lkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function3;", "attachToJSObject", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "attachToJSObject$expo_modules_core_release", NotificationCompat.CATEGORY_CALL, "holder", "Lexpo/modules/kotlin/ModuleHolder;", "Lcom/facebook/react/bridge/ReadableArray;", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/kotlin/Promise;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SuspendFunctionComponent extends BaseAsyncFunctionComponent {
    private final Function3<CoroutineScope, Object[], Continuation<Object>, Object> body;

    /* compiled from: SuspendFunctionComponent.kt */
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SuspendFunctionComponent(String name, AnyType[] desiredArgsTypes, Function3<? super CoroutineScope, ? super Object[], ? super Continuation<Object>, ? extends Object> body) {
        super(name, desiredArgsTypes);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(desiredArgsTypes, "desiredArgsTypes");
        Intrinsics.checkNotNullParameter(body, "body");
        this.body = body;
    }

    @Override // expo.modules.kotlin.functions.BaseAsyncFunctionComponent
    public void call(ModuleHolder holder, ReadableArray args, Promise promise) {
        CoroutineScope mainQueue;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(promise, "promise");
        AppContext appContext = holder.getModule().getAppContext();
        int r1 = WhenMappings.$EnumSwitchMapping$0[getQueue().ordinal()];
        if (r1 == 1) {
            mainQueue = appContext.getMainQueue();
        } else if (r1 != 2) {
            throw new NoWhenBranchMatchedException();
        } else {
            mainQueue = appContext.getModulesQueue();
        }
        BuildersKt__Builders_commonKt.launch$default(mainQueue, null, null, new SuspendFunctionComponent$call$1(promise, this, holder, args, null), 3, null);
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
        jsObject.registerAsyncFunction(name, argsCount$expo_modules_core_release, (ExpectedType[]) array, new JNIAsyncFunctionBody() { // from class: expo.modules.kotlin.functions.SuspendFunctionComponent$$ExternalSyntheticLambda0
            @Override // expo.modules.kotlin.jni.JNIAsyncFunctionBody
            public final void invoke(Object[] objArr, PromiseImpl promiseImpl) {
                SuspendFunctionComponent.m1673attachToJSObject$lambda1(SuspendFunctionComponent.this, appContext, jsObject, objArr, promiseImpl);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: attachToJSObject$lambda-1  reason: not valid java name */
    public static final void m1673attachToJSObject$lambda1(SuspendFunctionComponent this$0, AppContext appContext, JavaScriptModuleObject jsObject, Object[] args, PromiseImpl bridgePromise) {
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
        BuildersKt__Builders_commonKt.launch$default(mainQueue, null, null, new SuspendFunctionComponent$attachToJSObject$2$1(bridgePromise, this$0, jsObject, args, null), 3, null);
    }
}
