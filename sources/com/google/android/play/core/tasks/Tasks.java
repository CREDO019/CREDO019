package com.google.android.play.core.tasks;

import com.google.android.play.core.internal.C2510av;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes3.dex */
public final class Tasks {
    private Tasks() {
    }

    /* renamed from: a */
    public static <ResultT> Task<ResultT> m469a(Exception exc) {
        C2686m c2686m = new C2686m();
        c2686m.m452a(exc);
        return c2686m;
    }

    /* renamed from: a */
    public static <ResultT> Task<ResultT> m468a(ResultT resultt) {
        C2686m c2686m = new C2686m();
        c2686m.m451a((C2686m) resultt);
        return c2686m;
    }

    /* renamed from: a */
    private static <ResultT> ResultT m471a(Task<ResultT> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }

    /* renamed from: a */
    private static void m470a(Task<?> task, C2687n c2687n) {
        task.addOnSuccessListener(TaskExecutors.f1091a, c2687n);
        task.addOnFailureListener(TaskExecutors.f1091a, c2687n);
    }

    public static <ResultT> ResultT await(Task<ResultT> task) throws ExecutionException, InterruptedException {
        C2510av.m774a(task, "Task must not be null");
        if (task.isComplete()) {
            return (ResultT) m471a((Task<Object>) task);
        }
        C2687n c2687n = new C2687n(null);
        m470a(task, c2687n);
        c2687n.m446a();
        return (ResultT) m471a((Task<Object>) task);
    }

    public static <ResultT> ResultT await(Task<ResultT> task, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        C2510av.m774a(task, "Task must not be null");
        C2510av.m774a(timeUnit, "TimeUnit must not be null");
        if (task.isComplete()) {
            return (ResultT) m471a((Task<Object>) task);
        }
        C2687n c2687n = new C2687n(null);
        m470a(task, c2687n);
        if (c2687n.m445a(j, timeUnit)) {
            return (ResultT) m471a((Task<Object>) task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }
}
