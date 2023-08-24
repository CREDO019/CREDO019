package expo.modules.adapters.react;

import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import expo.modules.core.Promise;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes4.dex */
class PromiseWrapper implements Promise {
    private com.facebook.react.bridge.Promise mPromise;

    @Override // expo.modules.core.Promise
    public /* synthetic */ void reject(String str, String str2) {
        reject(str, str2, null);
    }

    @Override // expo.modules.core.Promise
    public /* synthetic */ void reject(String str, Throwable th) {
        reject(str, th.getMessage(), th);
    }

    @Override // expo.modules.core.Promise
    public /* synthetic */ void reject(Throwable th) {
        Promise.CC.$default$reject(this, th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PromiseWrapper(com.facebook.react.bridge.Promise promise) {
        this.mPromise = promise;
    }

    @Override // expo.modules.core.Promise
    public void resolve(@Nullable Object obj) {
        if (obj instanceof Bundle) {
            this.mPromise.resolve(Arguments.fromBundle((Bundle) obj));
        } else if (obj instanceof List) {
            this.mPromise.resolve(Arguments.fromList((List) obj));
        } else {
            this.mPromise.resolve(obj);
        }
    }

    @Override // expo.modules.core.Promise
    public void reject(String str, String str2, Throwable th) {
        this.mPromise.reject(str, str2, th);
    }
}
