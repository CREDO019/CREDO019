package p042rx.plugins;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import p042rx.Scheduler;
import p042rx.functions.Action0;
import p042rx.internal.schedulers.CachedThreadScheduler;
import p042rx.internal.schedulers.EventLoopsScheduler;
import p042rx.internal.schedulers.NewThreadScheduler;
import p042rx.internal.util.RxThreadFactory;

/* renamed from: rx.plugins.RxJavaSchedulersHook */
/* loaded from: classes6.dex */
public class RxJavaSchedulersHook {
    private static final RxJavaSchedulersHook DEFAULT_INSTANCE = new RxJavaSchedulersHook();

    public Scheduler getComputationScheduler() {
        return null;
    }

    public Scheduler getIOScheduler() {
        return null;
    }

    public Scheduler getNewThreadScheduler() {
        return null;
    }

    @Deprecated
    public Action0 onSchedule(Action0 action0) {
        return action0;
    }

    public static Scheduler createComputationScheduler() {
        return createComputationScheduler(new RxThreadFactory("RxComputationScheduler-"));
    }

    public static Scheduler createComputationScheduler(ThreadFactory threadFactory) {
        Objects.requireNonNull(threadFactory, "threadFactory == null");
        return new EventLoopsScheduler(threadFactory);
    }

    public static Scheduler createIoScheduler() {
        return createIoScheduler(new RxThreadFactory("RxIoScheduler-"));
    }

    public static Scheduler createIoScheduler(ThreadFactory threadFactory) {
        Objects.requireNonNull(threadFactory, "threadFactory == null");
        return new CachedThreadScheduler(threadFactory);
    }

    public static Scheduler createNewThreadScheduler() {
        return createNewThreadScheduler(new RxThreadFactory("RxNewThreadScheduler-"));
    }

    public static Scheduler createNewThreadScheduler(ThreadFactory threadFactory) {
        Objects.requireNonNull(threadFactory, "threadFactory == null");
        return new NewThreadScheduler(threadFactory);
    }

    public static RxJavaSchedulersHook getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }
}
