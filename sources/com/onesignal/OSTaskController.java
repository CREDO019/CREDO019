package com.onesignal;

import com.onesignal.OneSignal;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSTaskController {
    static final String OS_PENDING_EXECUTOR = "OS_PENDING_EXECUTOR_";
    protected final OSLogger logger;
    private ExecutorService pendingTaskExecutor;
    private final ConcurrentLinkedQueue<Runnable> taskQueueWaitingForInit = new ConcurrentLinkedQueue<>();
    private final AtomicLong lastTaskId = new AtomicLong();

    /* JADX INFO: Access modifiers changed from: package-private */
    public OSTaskController(OSLogger oSLogger) {
        this.logger = oSLogger;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldRunTaskThroughQueue() {
        if (Thread.currentThread().getName().contains(OS_PENDING_EXECUTOR)) {
            return false;
        }
        if (OneSignal.isInitDone() && this.pendingTaskExecutor == null) {
            return false;
        }
        if (OneSignal.isInitDone() || this.pendingTaskExecutor != null) {
            return !this.pendingTaskExecutor.isShutdown();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addTaskToQueue(Runnable runnable) {
        addTaskToQueue(new PendingTaskRunnable(this, runnable));
    }

    private void addTaskToQueue(PendingTaskRunnable pendingTaskRunnable) {
        pendingTaskRunnable.taskId = this.lastTaskId.incrementAndGet();
        ExecutorService executorService = this.pendingTaskExecutor;
        if (executorService == null) {
            OSLogger oSLogger = this.logger;
            oSLogger.debug("Adding a task to the pending queue with ID: " + pendingTaskRunnable.taskId);
            this.taskQueueWaitingForInit.add(pendingTaskRunnable);
        } else if (executorService.isShutdown()) {
        } else {
            OSLogger oSLogger2 = this.logger;
            oSLogger2.debug("Executor is still running, add to the executor with ID: " + pendingTaskRunnable.taskId);
            try {
                this.pendingTaskExecutor.submit(pendingTaskRunnable);
            } catch (RejectedExecutionException e) {
                OSLogger oSLogger3 = this.logger;
                oSLogger3.info("Executor is shutdown, running task manually with ID: " + pendingTaskRunnable.taskId);
                pendingTaskRunnable.run();
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startPendingTasks() {
        OneSignal.LOG_LEVEL log_level = OneSignal.LOG_LEVEL.DEBUG;
        OneSignal.Log(log_level, "startPendingTasks with task queue quantity: " + this.taskQueueWaitingForInit.size());
        if (this.taskQueueWaitingForInit.isEmpty()) {
            return;
        }
        this.pendingTaskExecutor = Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.onesignal.OSTaskController.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName(OSTaskController.OS_PENDING_EXECUTOR + thread.getId());
                return thread;
            }
        });
        while (!this.taskQueueWaitingForInit.isEmpty()) {
            this.pendingTaskExecutor.submit(this.taskQueueWaitingForInit.poll());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTaskRan(long j) {
        if (this.lastTaskId.get() == j) {
            OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "Last Pending Task has ran, shutting down");
            this.pendingTaskExecutor.shutdown();
        }
    }

    ConcurrentLinkedQueue<Runnable> getTaskQueueWaitingForInit() {
        return this.taskQueueWaitingForInit;
    }

    void shutdownNow() {
        ExecutorService executorService = this.pendingTaskExecutor;
        if (executorService != null) {
            executorService.shutdownNow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class PendingTaskRunnable implements Runnable {
        private OSTaskController controller;
        private Runnable innerTask;
        private long taskId;

        PendingTaskRunnable(OSTaskController oSTaskController, Runnable runnable) {
            this.controller = oSTaskController;
            this.innerTask = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.innerTask.run();
            this.controller.onTaskRan(this.taskId);
        }

        public String toString() {
            return "PendingTaskRunnable{innerTask=" + this.innerTask + ", taskId=" + this.taskId + '}';
        }
    }
}
