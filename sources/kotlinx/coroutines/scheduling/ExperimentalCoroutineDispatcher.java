package kotlinx.coroutines.scheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DefaultExecutor;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

/* compiled from: Deprecated.kt */
@Metadata(m184d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0011\u0018\u00002\u00020\u0001B%\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001b\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\bB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0003J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\rH\u0002J\u001c\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u00060\u001cj\u0002`\u001dH\u0016J)\u0010\u001e\u001a\u00020\u00162\n\u0010\u001b\u001a\u00060\u001cj\u0002`\u001d2\u0006\u0010\u0019\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b\"J\u001c\u0010#\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u00060\u001cj\u0002`\u001dH\u0016J\u000e\u0010$\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0003J\b\u0010%\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, m183d2 = {"Lkotlinx/coroutines/scheduling/ExperimentalCoroutineDispatcher;", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "corePoolSize", "", "maxPoolSize", "schedulerName", "", "(IILjava/lang/String;)V", "(II)V", "idleWorkerKeepAliveNs", "", "(IIJLjava/lang/String;)V", "coroutineScheduler", "Lkotlinx/coroutines/scheduling/CoroutineScheduler;", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "blocking", "Lkotlinx/coroutines/CoroutineDispatcher;", "parallelism", "close", "", "createScheduler", "dispatch", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "dispatchWithContext", "Lkotlinx/coroutines/scheduling/TaskContext;", "tailDispatch", "", "dispatchWithContext$kotlinx_coroutines_core", "dispatchYield", "limited", "toString", "kotlinx-coroutines-core"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public class ExperimentalCoroutineDispatcher extends ExecutorCoroutineDispatcher {
    private final int corePoolSize;
    private CoroutineScheduler coroutineScheduler;
    private final long idleWorkerKeepAliveNs;
    private final int maxPoolSize;
    private final String schedulerName;

    public /* synthetic */ ExperimentalCoroutineDispatcher(int r7, int r8, long j, String str, int r12, DefaultConstructorMarker defaultConstructorMarker) {
        this(r7, r8, j, (r12 & 8) != 0 ? "CoroutineScheduler" : str);
    }

    public ExperimentalCoroutineDispatcher(int r1, int r2, long j, String str) {
        this.corePoolSize = r1;
        this.maxPoolSize = r2;
        this.idleWorkerKeepAliveNs = j;
        this.schedulerName = str;
        this.coroutineScheduler = createScheduler();
    }

    public /* synthetic */ ExperimentalCoroutineDispatcher(int r1, int r2, String str, int r4, DefaultConstructorMarker defaultConstructorMarker) {
        this((r4 & 1) != 0 ? TasksKt.CORE_POOL_SIZE : r1, (r4 & 2) != 0 ? TasksKt.MAX_POOL_SIZE : r2, (r4 & 4) != 0 ? TasksKt.DEFAULT_SCHEDULER_NAME : str);
    }

    public ExperimentalCoroutineDispatcher(int r7, int r8, String str) {
        this(r7, r8, TasksKt.IDLE_WORKER_KEEP_ALIVE_NS, str);
    }

    public /* synthetic */ ExperimentalCoroutineDispatcher(int r1, int r2, int r3, DefaultConstructorMarker defaultConstructorMarker) {
        this((r3 & 1) != 0 ? TasksKt.CORE_POOL_SIZE : r1, (r3 & 2) != 0 ? TasksKt.MAX_POOL_SIZE : r2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility for Ktor 1.0-beta")
    public /* synthetic */ ExperimentalCoroutineDispatcher(int r9, int r10) {
        this(r9, r10, TasksKt.IDLE_WORKER_KEEP_ALIVE_NS, null, 8, null);
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher
    public Executor getExecutor() {
        return this.coroutineScheduler;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    /* renamed from: dispatch */
    public void mo3255dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        try {
            CoroutineScheduler.dispatch$default(this.coroutineScheduler, runnable, null, false, 6, null);
        } catch (RejectedExecutionException unused) {
            DefaultExecutor.INSTANCE.mo3255dispatch(coroutineContext, runnable);
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatchYield(CoroutineContext coroutineContext, Runnable runnable) {
        try {
            CoroutineScheduler.dispatch$default(this.coroutineScheduler, runnable, null, true, 2, null);
        } catch (RejectedExecutionException unused) {
            DefaultExecutor.INSTANCE.dispatchYield(coroutineContext, runnable);
        }
    }

    @Override // kotlinx.coroutines.ExecutorCoroutineDispatcher, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.coroutineScheduler.close();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return super.toString() + "[scheduler = " + this.coroutineScheduler + ']';
    }

    public static /* synthetic */ CoroutineDispatcher blocking$default(ExperimentalCoroutineDispatcher experimentalCoroutineDispatcher, int r1, int r2, Object obj) {
        if (obj == null) {
            if ((r2 & 1) != 0) {
                r1 = 16;
            }
            return experimentalCoroutineDispatcher.blocking(r1);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: blocking");
    }

    public final CoroutineDispatcher blocking(int r4) {
        if (!(r4 > 0)) {
            throw new IllegalArgumentException(("Expected positive parallelism level, but have " + r4).toString());
        }
        return new LimitingDispatcher(this, r4, null, 1);
    }

    public final CoroutineDispatcher limited(int r4) {
        if (!(r4 > 0)) {
            throw new IllegalArgumentException(("Expected positive parallelism level, but have " + r4).toString());
        }
        if (!(r4 <= this.corePoolSize)) {
            throw new IllegalArgumentException(("Expected parallelism level lesser than core pool size (" + this.corePoolSize + "), but have " + r4).toString());
        }
        return new LimitingDispatcher(this, r4, null, 0);
    }

    public final void dispatchWithContext$kotlinx_coroutines_core(Runnable runnable, TaskContext taskContext, boolean z) {
        try {
            this.coroutineScheduler.dispatch(runnable, taskContext, z);
        } catch (RejectedExecutionException unused) {
            DefaultExecutor.INSTANCE.enqueue(this.coroutineScheduler.createTask(runnable, taskContext));
        }
    }

    private final CoroutineScheduler createScheduler() {
        return new CoroutineScheduler(this.corePoolSize, this.maxPoolSize, this.idleWorkerKeepAliveNs, this.schedulerName);
    }
}