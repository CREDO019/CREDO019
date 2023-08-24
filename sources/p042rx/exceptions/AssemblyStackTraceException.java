package p042rx.exceptions;

import java.util.HashSet;
import p042rx.plugins.RxJavaHooks;

/* renamed from: rx.exceptions.AssemblyStackTraceException */
/* loaded from: classes4.dex */
public final class AssemblyStackTraceException extends RuntimeException {
    private static final long serialVersionUID = 2038859767182585852L;

    public AssemblyStackTraceException(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    public void attachTo(Throwable th) {
        HashSet hashSet = new HashSet();
        while (th.getCause() != null) {
            th = th.getCause();
            if (!hashSet.add(th)) {
                RxJavaHooks.onError(this);
                return;
            }
        }
        try {
            th.initCause(this);
        } catch (IllegalStateException unused) {
            RxJavaHooks.onError(new RuntimeException("Received an exception with a cause set to null, instead of being unset. To fix this, look down the chain of causes. The last exception had a cause explicitly set to null. It should be unset instead.", th));
        }
    }

    public static AssemblyStackTraceException find(Throwable th) {
        HashSet hashSet = new HashSet();
        while (!(th instanceof AssemblyStackTraceException)) {
            if (th == null || th.getCause() == null) {
                return null;
            }
            th = th.getCause();
            if (!hashSet.add(th)) {
                return null;
            }
        }
        return (AssemblyStackTraceException) th;
    }
}
