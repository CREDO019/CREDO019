package p042rx.exceptions;

import java.util.HashSet;
import java.util.List;
import p042rx.Observer;
import p042rx.SingleSubscriber;

/* renamed from: rx.exceptions.Exceptions */
/* loaded from: classes4.dex */
public final class Exceptions {
    private static final int MAX_DEPTH = 25;

    private Exceptions() {
        throw new IllegalStateException("No instances!");
    }

    public static RuntimeException propagate(Throwable th) {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        throw new RuntimeException(th);
    }

    public static void throwIfFatal(Throwable th) {
        if (th instanceof OnErrorNotImplementedException) {
            throw ((OnErrorNotImplementedException) th);
        }
        if (th instanceof OnErrorFailedException) {
            throw ((OnErrorFailedException) th);
        }
        if (th instanceof OnCompletedFailedException) {
            throw ((OnCompletedFailedException) th);
        }
        if (th instanceof VirtualMachineError) {
            throw ((VirtualMachineError) th);
        }
        if (th instanceof ThreadDeath) {
            throw ((ThreadDeath) th);
        }
        if (th instanceof LinkageError) {
            throw ((LinkageError) th);
        }
    }

    public static void addCause(Throwable th, Throwable th2) {
        HashSet hashSet = new HashSet();
        int r1 = 0;
        while (th.getCause() != null) {
            int r2 = r1 + 1;
            if (r1 >= 25) {
                return;
            }
            th = th.getCause();
            if (!hashSet.contains(th.getCause())) {
                hashSet.add(th.getCause());
                r1 = r2;
            }
        }
        try {
            th.initCause(th2);
        } catch (Throwable unused) {
        }
    }

    public static Throwable getFinalCause(Throwable th) {
        int r0 = 0;
        while (th.getCause() != null) {
            int r1 = r0 + 1;
            if (r0 >= 25) {
                return new RuntimeException("Stack too deep to get final cause");
            }
            th = th.getCause();
            r0 = r1;
        }
        return th;
    }

    public static void throwIfAny(List<? extends Throwable> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (list.size() == 1) {
            Throwable th = list.get(0);
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            if (th instanceof Error) {
                throw ((Error) th);
            }
            throw new RuntimeException(th);
        }
        throw new CompositeException(list);
    }

    public static void throwOrReport(Throwable th, Observer<?> observer, Object obj) {
        throwIfFatal(th);
        observer.onError(OnErrorThrowable.addValueAsLastCause(th, obj));
    }

    public static void throwOrReport(Throwable th, SingleSubscriber<?> singleSubscriber, Object obj) {
        throwIfFatal(th);
        singleSubscriber.onError(OnErrorThrowable.addValueAsLastCause(th, obj));
    }

    public static void throwOrReport(Throwable th, Observer<?> observer) {
        throwIfFatal(th);
        observer.onError(th);
    }

    public static void throwOrReport(Throwable th, SingleSubscriber<?> singleSubscriber) {
        throwIfFatal(th);
        singleSubscriber.onError(th);
    }
}
