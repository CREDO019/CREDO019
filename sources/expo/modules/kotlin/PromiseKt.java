package expo.modules.kotlin;

import com.facebook.react.bridge.WritableMap;
import expo.modules.kotlin.jni.PromiseImpl;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Promise.kt */
@Metadata(m184d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m183d2 = {"unknownCode", "", "toBridgePromise", "Lcom/facebook/react/bridge/Promise;", "Lexpo/modules/kotlin/Promise;", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class PromiseKt {
    private static final String unknownCode = "UnknownCode";

    public static final com.facebook.react.bridge.Promise toBridgePromise(final Promise promise) {
        final PromiseKt$toBridgePromise$resolveMethod$2 promiseKt$toBridgePromise$resolveMethod$2;
        Intrinsics.checkNotNullParameter(promise, "<this>");
        if (promise instanceof PromiseImpl) {
            promiseKt$toBridgePromise$resolveMethod$2 = new PromiseKt$toBridgePromise$resolveMethod$1(((PromiseImpl) promise).getResolveBlock$expo_modules_core_release());
        } else {
            promiseKt$toBridgePromise$resolveMethod$2 = new PromiseKt$toBridgePromise$resolveMethod$2(promise);
        }
        return new com.facebook.react.bridge.Promise() { // from class: expo.modules.kotlin.PromiseKt$toBridgePromise$1
            @Override // com.facebook.react.bridge.Promise
            public void resolve(Object obj) {
                ((Function1) promiseKt$toBridgePromise$resolveMethod$2).invoke(obj);
            }

            @Override // com.facebook.react.bridge.Promise
            public void reject(String str, String str2) {
                Promise promise2 = promise;
                if (str == null) {
                    str = "UnknownCode";
                }
                promise2.reject(str, str2, null);
            }

            @Override // com.facebook.react.bridge.Promise
            public void reject(String str, Throwable th) {
                Promise promise2 = promise;
                if (str == null) {
                    str = "UnknownCode";
                }
                promise2.reject(str, null, th);
            }

            @Override // com.facebook.react.bridge.Promise
            public void reject(String str, String str2, Throwable th) {
                Promise promise2 = promise;
                if (str == null) {
                    str = "UnknownCode";
                }
                promise2.reject(str, str2, th);
            }

            @Override // com.facebook.react.bridge.Promise
            public void reject(Throwable th) {
                promise.reject("UnknownCode", null, th);
            }

            @Override // com.facebook.react.bridge.Promise
            public void reject(Throwable th, WritableMap writableMap) {
                promise.reject("UnknownCode", null, th);
            }

            @Override // com.facebook.react.bridge.Promise
            public void reject(String str, WritableMap userInfo) {
                Intrinsics.checkNotNullParameter(userInfo, "userInfo");
                Promise promise2 = promise;
                if (str == null) {
                    str = "UnknownCode";
                }
                promise2.reject(str, null, null);
            }

            @Override // com.facebook.react.bridge.Promise
            public void reject(String str, Throwable th, WritableMap writableMap) {
                Promise promise2 = promise;
                if (str == null) {
                    str = "UnknownCode";
                }
                promise2.reject(str, null, th);
            }

            @Override // com.facebook.react.bridge.Promise
            public void reject(String str, String str2, WritableMap userInfo) {
                Intrinsics.checkNotNullParameter(userInfo, "userInfo");
                Promise promise2 = promise;
                if (str == null) {
                    str = "UnknownCode";
                }
                promise2.reject(str, str2, null);
            }

            @Override // com.facebook.react.bridge.Promise
            public void reject(String str, String str2, Throwable th, WritableMap writableMap) {
                Promise promise2 = promise;
                if (str == null) {
                    str = "UnknownCode";
                }
                promise2.reject(str, str2, th);
            }

            @Override // com.facebook.react.bridge.Promise
            public void reject(String str) {
                promise.reject("UnknownCode", str, null);
            }
        };
    }
}
