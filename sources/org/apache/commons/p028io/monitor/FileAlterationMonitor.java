package org.apache.commons.p028io.monitor;

import androidx.work.WorkRequest;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadFactory;

/* renamed from: org.apache.commons.io.monitor.FileAlterationMonitor */
/* loaded from: classes5.dex */
public final class FileAlterationMonitor implements Runnable {
    private final long interval;
    private final List<FileAlterationObserver> observers;
    private volatile boolean running;
    private Thread thread;
    private ThreadFactory threadFactory;

    public FileAlterationMonitor() {
        this(WorkRequest.MIN_BACKOFF_MILLIS);
    }

    public FileAlterationMonitor(long j) {
        this.observers = new CopyOnWriteArrayList();
        this.thread = null;
        this.running = false;
        this.interval = j;
    }

    public FileAlterationMonitor(long j, FileAlterationObserver... fileAlterationObserverArr) {
        this(j);
        if (fileAlterationObserverArr != null) {
            for (FileAlterationObserver fileAlterationObserver : fileAlterationObserverArr) {
                addObserver(fileAlterationObserver);
            }
        }
    }

    public long getInterval() {
        return this.interval;
    }

    public synchronized void setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }

    public void addObserver(FileAlterationObserver fileAlterationObserver) {
        if (fileAlterationObserver != null) {
            this.observers.add(fileAlterationObserver);
        }
    }

    public void removeObserver(FileAlterationObserver fileAlterationObserver) {
        if (fileAlterationObserver != null) {
            do {
            } while (this.observers.remove(fileAlterationObserver));
        }
    }

    public Iterable<FileAlterationObserver> getObservers() {
        return this.observers;
    }

    public synchronized void start() throws Exception {
        if (this.running) {
            throw new IllegalStateException("Monitor is already running");
        }
        for (FileAlterationObserver fileAlterationObserver : this.observers) {
            fileAlterationObserver.initialize();
        }
        this.running = true;
        ThreadFactory threadFactory = this.threadFactory;
        if (threadFactory != null) {
            this.thread = threadFactory.newThread(this);
        } else {
            this.thread = new Thread(this);
        }
        this.thread.start();
    }

    public synchronized void stop() throws Exception {
        stop(this.interval);
    }

    public synchronized void stop(long j) throws Exception {
        if (!this.running) {
            throw new IllegalStateException("Monitor is not running");
        }
        this.running = false;
        try {
            this.thread.join(j);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
        for (FileAlterationObserver fileAlterationObserver : this.observers) {
            fileAlterationObserver.destroy();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.running) {
            for (FileAlterationObserver fileAlterationObserver : this.observers) {
                fileAlterationObserver.checkAndNotify();
            }
            if (!this.running) {
                return;
            }
            try {
                Thread.sleep(this.interval);
            } catch (InterruptedException unused) {
            }
        }
    }
}
