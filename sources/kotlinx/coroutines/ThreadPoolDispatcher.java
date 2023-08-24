package kotlinx.coroutines;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;

@Metadata(m184d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0007"}, m183d2 = {"newFixedThreadPoolContext", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "nThreads", "", "name", "", "newSingleThreadContext", "kotlinx-coroutines-core"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: kotlinx.coroutines.ThreadPoolDispatcherKt */
/* loaded from: classes5.dex */
public final class ThreadPoolDispatcher {
    public static final ExecutorCoroutineDispatcher newSingleThreadContext(String str) {
        return newFixedThreadPoolContext(1, str);
    }

    public static final ExecutorCoroutineDispatcher newFixedThreadPoolContext(final int r2, final String str) {
        if (!(r2 >= 1)) {
            throw new IllegalArgumentException(("Expected at least one thread, but " + r2 + " specified").toString());
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        return ExecutorsKt.from((ExecutorService) Executors.newScheduledThreadPool(r2, new ThreadFactory() { // from class: kotlinx.coroutines.ThreadPoolDispatcherKt$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread m3200newFixedThreadPoolContext$lambda1;
                m3200newFixedThreadPoolContext$lambda1 = ThreadPoolDispatcher.m3200newFixedThreadPoolContext$lambda1(r2, str, atomicInteger, runnable);
                return m3200newFixedThreadPoolContext$lambda1;
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: newFixedThreadPoolContext$lambda-1  reason: not valid java name */
    public static final Thread m3200newFixedThreadPoolContext$lambda1(int r2, String str, AtomicInteger atomicInteger, Runnable runnable) {
        if (r2 != 1) {
            str = str + '-' + atomicInteger.incrementAndGet();
        }
        Thread thread = new Thread(runnable, str);
        thread.setDaemon(true);
        return thread;
    }
}
