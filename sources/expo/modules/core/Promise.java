package expo.modules.core;

import expo.modules.core.interfaces.CodedThrowable;

/* loaded from: classes4.dex */
public interface Promise {
    public static final String UNKNOWN_ERROR = "E_UNKNOWN_ERROR";

    void reject(String str, String str2);

    void reject(String str, String str2, Throwable th);

    void reject(String str, Throwable th);

    void reject(Throwable th);

    void resolve(Object obj);

    /* renamed from: expo.modules.core.Promise$-CC  reason: invalid class name */
    /* loaded from: classes4.dex */
    public final /* synthetic */ class CC {
        public static void $default$reject(Promise _this, Throwable th) {
            if (th instanceof CodedThrowable) {
                CodedThrowable codedThrowable = (CodedThrowable) th;
                _this.reject(codedThrowable.getCode(), codedThrowable.getMessage(), th);
                return;
            }
            _this.reject(Promise.UNKNOWN_ERROR, th);
        }
    }
}
