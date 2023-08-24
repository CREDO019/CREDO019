package expo.modules.kotlin.defaultmodules;

import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.adapters.react.NativeModulesProxy;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.PromiseKt;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.AnyTypeKt;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: NativeModulesProxyModule.kt */
@Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m183d2 = {"Lexpo/modules/kotlin/defaultmodules/NativeModulesProxyModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class NativeModulesProxyModule extends Module {
    @Override // expo.modules.kotlin.modules.Module
    public ModuleDefinitionData definition() {
        AsyncFunctionComponent asyncFunctionComponent;
        ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(this);
        moduleDefinitionBuilder.Name(NativeModulesProxyModuleKt.NativeModulesProxyModuleName);
        moduleDefinitionBuilder.Constants(new Functions<Map<String, ? extends Object>>() { // from class: expo.modules.kotlin.defaultmodules.NativeModulesProxyModule$definition$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Functions
            public final Map<String, ? extends Object> invoke() {
                BaseJavaModule baseJavaModule;
                WeakReference<NativeModulesProxy> legacyModulesProxyHolder$expo_modules_core_release = NativeModulesProxyModule.this.getAppContext().getLegacyModulesProxyHolder$expo_modules_core_release();
                Map<String, Object> map = null;
                if (legacyModulesProxyHolder$expo_modules_core_release != null && (baseJavaModule = (NativeModulesProxy) legacyModulesProxyHolder$expo_modules_core_release.get()) != null) {
                    map = baseJavaModule.getConstants();
                }
                return map == null ? MapsKt.emptyMap() : map;
            }
        });
        ModuleDefinitionBuilder moduleDefinitionBuilder2 = moduleDefinitionBuilder;
        if (Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(Promise.class), Reflection.getOrCreateKotlinClass(Promise.class))) {
            asyncFunctionComponent = new AsyncFunctionWithPromiseComponent("callMethod", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(String.class)), AnyTypeKt.toAnyType(Reflection.typeOf(String.class)), AnyTypeKt.toAnyType(Reflection.typeOf(ReadableArray.class))}, new Function2<Object[], Promise, Unit>() { // from class: expo.modules.kotlin.defaultmodules.NativeModulesProxyModule$definition$lambda-1$$inlined$AsyncFunction$1
                {
                    super(2);
                }

                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                public final void invoke2(Object[] args, Promise promise) {
                    Intrinsics.checkNotNullParameter(args, "args");
                    Intrinsics.checkNotNullParameter(promise, "promise");
                    Object obj = args[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                    String str = (String) obj;
                    Object obj2 = args[1];
                    Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.String");
                    String str2 = (String) obj2;
                    Object obj3 = args[2];
                    Objects.requireNonNull(obj3, "null cannot be cast to non-null type com.facebook.react.bridge.ReadableArray");
                    ReadableArray readableArray = (ReadableArray) obj3;
                    com.facebook.react.bridge.Promise bridgePromise = PromiseKt.toBridgePromise(promise);
                    WeakReference<NativeModulesProxy> legacyModulesProxyHolder$expo_modules_core_release = NativeModulesProxyModule.this.getAppContext().getLegacyModulesProxyHolder$expo_modules_core_release();
                    NativeModulesProxy nativeModulesProxy = legacyModulesProxyHolder$expo_modules_core_release == null ? null : legacyModulesProxyHolder$expo_modules_core_release.get();
                    if (nativeModulesProxy == null) {
                        throw new UnexpectedException("The legacy modules proxy holder has been lost");
                    }
                    nativeModulesProxy.callMethod(str, str2, readableArray, bridgePromise);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Object[] objArr, Promise promise) {
                    invoke2(objArr, promise);
                    return Unit.INSTANCE;
                }
            });
        } else {
            asyncFunctionComponent = new AsyncFunctionComponent("callMethod", new AnyType[]{AnyTypeKt.toAnyType(Reflection.typeOf(String.class)), AnyTypeKt.toAnyType(Reflection.typeOf(String.class)), AnyTypeKt.toAnyType(Reflection.typeOf(ReadableArray.class)), AnyTypeKt.toAnyType(Reflection.typeOf(Promise.class))}, new Function1<Object[], Object>() { // from class: expo.modules.kotlin.defaultmodules.NativeModulesProxyModule$definition$lambda-1$$inlined$AsyncFunction$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object[] it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    Object obj = it[0];
                    Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.String");
                    String str = (String) obj;
                    Object obj2 = it[1];
                    Objects.requireNonNull(obj2, "null cannot be cast to non-null type kotlin.String");
                    String str2 = (String) obj2;
                    Object obj3 = it[2];
                    Objects.requireNonNull(obj3, "null cannot be cast to non-null type com.facebook.react.bridge.ReadableArray");
                    ReadableArray readableArray = (ReadableArray) obj3;
                    Object obj4 = it[3];
                    Objects.requireNonNull(obj4, "null cannot be cast to non-null type expo.modules.kotlin.Promise");
                    com.facebook.react.bridge.Promise bridgePromise = PromiseKt.toBridgePromise((Promise) obj4);
                    WeakReference<NativeModulesProxy> legacyModulesProxyHolder$expo_modules_core_release = NativeModulesProxyModule.this.getAppContext().getLegacyModulesProxyHolder$expo_modules_core_release();
                    NativeModulesProxy nativeModulesProxy = legacyModulesProxyHolder$expo_modules_core_release == null ? null : legacyModulesProxyHolder$expo_modules_core_release.get();
                    if (nativeModulesProxy == null) {
                        throw new UnexpectedException("The legacy modules proxy holder has been lost");
                    }
                    nativeModulesProxy.callMethod(str, str2, readableArray, bridgePromise);
                    return Unit.INSTANCE;
                }
            });
        }
        moduleDefinitionBuilder2.getAsyncFunctions().put("callMethod", asyncFunctionComponent);
        return moduleDefinitionBuilder.buildModule();
    }
}
