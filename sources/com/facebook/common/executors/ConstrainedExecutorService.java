package com.facebook.common.executors;

import com.facebook.common.logging.FLog;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class ConstrainedExecutorService extends AbstractExecutorService {
    private static final Class<?> TAG = ConstrainedExecutorService.class;
    private final Executor mExecutor;
    private volatile int mMaxConcurrency;
    private final AtomicInteger mMaxQueueSize;
    private final String mName;
    private final AtomicInteger mPendingWorkers;
    private final Worker mTaskRunner;
    private final BlockingQueue<Runnable> mWorkQueue;

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return false;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return false;
    }

    public ConstrainedExecutorService(String name, int maxConcurrency, Executor executor, BlockingQueue<Runnable> workQueue) {
        if (maxConcurrency <= 0) {
            throw new IllegalArgumentException("max concurrency must be > 0");
        }
        this.mName = name;
        this.mExecutor = executor;
        this.mMaxConcurrency = maxConcurrency;
        this.mWorkQueue = workQueue;
        this.mTaskRunner = new Worker();
        this.mPendingWorkers = new AtomicInteger(0);
        this.mMaxQueueSize = new AtomicInteger(0);
    }

    public static ConstrainedExecutorService newConstrainedExecutor(String name, int maxConcurrency, int queueSize, Executor executor) {
        return new ConstrainedExecutorService(name, maxConcurrency, executor, new LinkedBlockingQueue(queueSize));
    }

    public boolean isIdle() {
        return this.mWorkQueue.isEmpty() && this.mPendingWorkers.get() == 0;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        Objects.requireNonNull(runnable, "runnable parameter is null");
        if (!this.mWorkQueue.offer(runnable)) {
            throw new RejectedExecutionException(this.mName + " queue is full, size=" + this.mWorkQueue.size());
        }
        int size = this.mWorkQueue.size();
        int r0 = this.mMaxQueueSize.get();
        if (size > r0 && this.mMaxQueueSize.compareAndSet(r0, size)) {
            FLog.m1306v(TAG, "%s: max pending work in queue = %d", this.mName, Integer.valueOf(size));
        }
        startWorkerIfNeeded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startWorkerIfNeeded() {
        int r0 = this.mPendingWorkers.get();
        while (r0 < this.mMaxConcurrency) {
            int r1 = r0 + 1;
            if (this.mPendingWorkers.compareAndSet(r0, r1)) {
                FLog.m1305v(TAG, "%s: starting worker %d of %d", this.mName, Integer.valueOf(r1), Integer.valueOf(this.mMaxConcurrency));
                this.mExecutor.execute(this.mTaskRunner);
                return;
            }
            FLog.m1307v(TAG, "%s: race in startWorkerIfNeeded; retrying", this.mName);
            r0 = this.mPendingWorkers.get();
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class Worker implements Runnable {
        private Worker() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Runnable runnable = (Runnable) ConstrainedExecutorService.this.mWorkQueue.poll();
                if (runnable == null) {
                    FLog.m1307v(ConstrainedExecutorService.TAG, "%s: Worker has nothing to run", ConstrainedExecutorService.this.mName);
                } else {
                    runnable.run();
                }
                int decrementAndGet = ConstrainedExecutorService.this.mPendingWorkers.decrementAndGet();
                if (!ConstrainedExecutorService.this.mWorkQueue.isEmpty()) {
                    ConstrainedExecutorService.this.startWorkerIfNeeded();
                } else {
                    FLog.m1306v(ConstrainedExecutorService.TAG, "%s: worker finished; %d workers left", ConstrainedExecutorService.this.mName, Integer.valueOf(decrementAndGet));
                }
            } catch (Throwable th) {
                int decrementAndGet2 = ConstrainedExecutorService.this.mPendingWorkers.decrementAndGet();
                if (!ConstrainedExecutorService.this.mWorkQueue.isEmpty()) {
                    ConstrainedExecutorService.this.startWorkerIfNeeded();
                } else {
                    FLog.m1306v(ConstrainedExecutorService.TAG, "%s: worker finished; %d workers left", ConstrainedExecutorService.this.mName, Integer.valueOf(decrementAndGet2));
                }
                throw th;
            }
        }
    }
}
