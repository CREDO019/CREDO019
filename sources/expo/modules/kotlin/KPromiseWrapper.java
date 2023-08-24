package expo.modules.kotlin;

import com.onesignal.OneSignalDbContract;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.types.JSTypeConverter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KPromiseWrapper.kt */
@Metadata(m184d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0012\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, m183d2 = {"Lexpo/modules/kotlin/KPromiseWrapper;", "Lexpo/modules/kotlin/Promise;", "bridgePromise", "Lcom/facebook/react/bridge/Promise;", "(Lcom/facebook/react/bridge/Promise;)V", "getBridgePromise$expo_modules_core_release", "()Lcom/facebook/react/bridge/Promise;", "reject", "", "code", "", OneSignalDbContract.NotificationTable.COLUMN_NAME_MESSAGE, "cause", "", "resolve", "value", "", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class KPromiseWrapper implements Promise {
    private final com.facebook.react.bridge.Promise bridgePromise;

    public KPromiseWrapper(com.facebook.react.bridge.Promise bridgePromise) {
        Intrinsics.checkNotNullParameter(bridgePromise, "bridgePromise");
        this.bridgePromise = bridgePromise;
    }

    @Override // expo.modules.kotlin.Promise
    public void reject(CodedException codedException) {
        Promise.DefaultImpls.reject(this, codedException);
    }

    public final com.facebook.react.bridge.Promise getBridgePromise$expo_modules_core_release() {
        return this.bridgePromise;
    }

    @Override // expo.modules.kotlin.Promise
    public void resolve(Object obj) {
        this.bridgePromise.resolve(JSTypeConverter.convertToJSValue$default(JSTypeConverter.INSTANCE, obj, null, 2, null));
    }

    @Override // expo.modules.kotlin.Promise
    public void reject(String code, String str, Throwable th) {
        Intrinsics.checkNotNullParameter(code, "code");
        this.bridgePromise.reject(code, str, th);
    }
}
