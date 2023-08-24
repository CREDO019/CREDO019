package com.google.android.exoplayer2.offline;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer2.database.DatabaseProvider;
import com.google.android.exoplayer2.offline.Downloader;
import com.google.android.exoplayer2.scheduler.Requirements;
import com.google.android.exoplayer2.scheduler.RequirementsWatcher;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

/* loaded from: classes2.dex */
public final class DownloadManager {
    public static final int DEFAULT_MAX_PARALLEL_DOWNLOADS = 3;
    public static final int DEFAULT_MIN_RETRY_COUNT = 5;
    public static final Requirements DEFAULT_REQUIREMENTS = new Requirements(1);
    private static final int MSG_ADD_DOWNLOAD = 6;
    private static final int MSG_CONTENT_LENGTH_CHANGED = 10;
    private static final int MSG_DOWNLOAD_UPDATE = 2;
    private static final int MSG_INITIALIZE = 0;
    private static final int MSG_INITIALIZED = 0;
    private static final int MSG_PROCESSED = 1;
    private static final int MSG_RELEASE = 12;
    private static final int MSG_REMOVE_ALL_DOWNLOADS = 8;
    private static final int MSG_REMOVE_DOWNLOAD = 7;
    private static final int MSG_SET_DOWNLOADS_PAUSED = 1;
    private static final int MSG_SET_MAX_PARALLEL_DOWNLOADS = 4;
    private static final int MSG_SET_MIN_RETRY_COUNT = 5;
    private static final int MSG_SET_NOT_MET_REQUIREMENTS = 2;
    private static final int MSG_SET_STOP_REASON = 3;
    private static final int MSG_TASK_STOPPED = 9;
    private static final int MSG_UPDATE_PROGRESS = 11;
    private static final String TAG = "DownloadManager";
    private int activeTaskCount;
    private final Handler applicationHandler;
    private final Context context;
    private final WritableDownloadIndex downloadIndex;
    private List<Download> downloads;
    private boolean downloadsPaused;
    private boolean initialized;
    private final InternalHandler internalHandler;
    private final CopyOnWriteArraySet<Listener> listeners;
    private int maxParallelDownloads;
    private int minRetryCount;
    private int notMetRequirements;
    private int pendingMessages;
    private final RequirementsWatcher.Listener requirementsListener;
    private RequirementsWatcher requirementsWatcher;
    private boolean waitingForRequirements;

    /* loaded from: classes2.dex */
    public interface Listener {

        /* renamed from: com.google.android.exoplayer2.offline.DownloadManager$Listener$-CC  reason: invalid class name */
        /* loaded from: classes2.dex */
        public final /* synthetic */ class CC {
            public static void $default$onDownloadChanged(Listener _this, DownloadManager downloadManager, Download download, Exception exc) {
            }

            public static void $default$onDownloadRemoved(Listener _this, DownloadManager downloadManager, Download download) {
            }

            public static void $default$onDownloadsPausedChanged(Listener _this, DownloadManager downloadManager, boolean z) {
            }

            public static void $default$onIdle(Listener _this, DownloadManager downloadManager) {
            }

            public static void $default$onInitialized(Listener _this, DownloadManager downloadManager) {
            }

            public static void $default$onRequirementsStateChanged(Listener _this, DownloadManager downloadManager, Requirements requirements, int r3) {
            }

            public static void $default$onWaitingForRequirementsChanged(Listener _this, DownloadManager downloadManager, boolean z) {
            }
        }

        void onDownloadChanged(DownloadManager downloadManager, Download download, Exception exc);

        void onDownloadRemoved(DownloadManager downloadManager, Download download);

        void onDownloadsPausedChanged(DownloadManager downloadManager, boolean z);

        void onIdle(DownloadManager downloadManager);

        void onInitialized(DownloadManager downloadManager);

        void onRequirementsStateChanged(DownloadManager downloadManager, Requirements requirements, int r3);

        void onWaitingForRequirementsChanged(DownloadManager downloadManager, boolean z);
    }

    @Deprecated
    public DownloadManager(Context context, DatabaseProvider databaseProvider, Cache cache, DataSource.Factory factory) {
        this(context, databaseProvider, cache, factory, DefaultDownloaderFactory$$ExternalSyntheticLambda0.INSTANCE);
    }

    public DownloadManager(Context context, DatabaseProvider databaseProvider, Cache cache, DataSource.Factory factory, Executor executor) {
        this(context, new DefaultDownloadIndex(databaseProvider), new DefaultDownloaderFactory(new CacheDataSource.Factory().setCache(cache).setUpstreamDataSourceFactory(factory), executor));
    }

    public DownloadManager(Context context, WritableDownloadIndex writableDownloadIndex, DownloaderFactory downloaderFactory) {
        this.context = context.getApplicationContext();
        this.downloadIndex = writableDownloadIndex;
        this.maxParallelDownloads = 3;
        this.minRetryCount = 5;
        this.downloadsPaused = true;
        this.downloads = Collections.emptyList();
        this.listeners = new CopyOnWriteArraySet<>();
        Handler createHandlerForCurrentOrMainLooper = Util.createHandlerForCurrentOrMainLooper(new Handler.Callback() { // from class: com.google.android.exoplayer2.offline.DownloadManager$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                boolean handleMainMessage;
                handleMainMessage = DownloadManager.this.handleMainMessage(message);
                return handleMainMessage;
            }
        });
        this.applicationHandler = createHandlerForCurrentOrMainLooper;
        HandlerThread handlerThread = new HandlerThread("ExoPlayer:DownloadManager");
        handlerThread.start();
        InternalHandler internalHandler = new InternalHandler(handlerThread, writableDownloadIndex, downloaderFactory, createHandlerForCurrentOrMainLooper, this.maxParallelDownloads, this.minRetryCount, this.downloadsPaused);
        this.internalHandler = internalHandler;
        RequirementsWatcher.Listener listener = new RequirementsWatcher.Listener() { // from class: com.google.android.exoplayer2.offline.DownloadManager$$ExternalSyntheticLambda1
            @Override // com.google.android.exoplayer2.scheduler.RequirementsWatcher.Listener
            public final void onRequirementsStateChanged(RequirementsWatcher requirementsWatcher, int r3) {
                DownloadManager.this.onRequirementsStateChanged(requirementsWatcher, r3);
            }
        };
        this.requirementsListener = listener;
        RequirementsWatcher requirementsWatcher = new RequirementsWatcher(context, listener, DEFAULT_REQUIREMENTS);
        this.requirementsWatcher = requirementsWatcher;
        int start = requirementsWatcher.start();
        this.notMetRequirements = start;
        this.pendingMessages = 1;
        internalHandler.obtainMessage(0, start, 0).sendToTarget();
    }

    public Looper getApplicationLooper() {
        return this.applicationHandler.getLooper();
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public boolean isIdle() {
        return this.activeTaskCount == 0 && this.pendingMessages == 0;
    }

    public boolean isWaitingForRequirements() {
        return this.waitingForRequirements;
    }

    public void addListener(Listener listener) {
        Assertions.checkNotNull(listener);
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public Requirements getRequirements() {
        return this.requirementsWatcher.getRequirements();
    }

    public int getNotMetRequirements() {
        return this.notMetRequirements;
    }

    public void setRequirements(Requirements requirements) {
        if (requirements.equals(this.requirementsWatcher.getRequirements())) {
            return;
        }
        this.requirementsWatcher.stop();
        RequirementsWatcher requirementsWatcher = new RequirementsWatcher(this.context, this.requirementsListener, requirements);
        this.requirementsWatcher = requirementsWatcher;
        onRequirementsStateChanged(this.requirementsWatcher, requirementsWatcher.start());
    }

    public int getMaxParallelDownloads() {
        return this.maxParallelDownloads;
    }

    public void setMaxParallelDownloads(int r4) {
        Assertions.checkArgument(r4 > 0);
        if (this.maxParallelDownloads == r4) {
            return;
        }
        this.maxParallelDownloads = r4;
        this.pendingMessages++;
        this.internalHandler.obtainMessage(4, r4, 0).sendToTarget();
    }

    public int getMinRetryCount() {
        return this.minRetryCount;
    }

    public void setMinRetryCount(int r4) {
        Assertions.checkArgument(r4 >= 0);
        if (this.minRetryCount == r4) {
            return;
        }
        this.minRetryCount = r4;
        this.pendingMessages++;
        this.internalHandler.obtainMessage(5, r4, 0).sendToTarget();
    }

    public DownloadIndex getDownloadIndex() {
        return this.downloadIndex;
    }

    public List<Download> getCurrentDownloads() {
        return this.downloads;
    }

    public boolean getDownloadsPaused() {
        return this.downloadsPaused;
    }

    public void resumeDownloads() {
        setDownloadsPaused(false);
    }

    public void pauseDownloads() {
        setDownloadsPaused(true);
    }

    public void setStopReason(String str, int r5) {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(3, r5, 0, str).sendToTarget();
    }

    public void addDownload(DownloadRequest downloadRequest) {
        addDownload(downloadRequest, 0);
    }

    public void addDownload(DownloadRequest downloadRequest, int r5) {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(6, r5, 0, downloadRequest).sendToTarget();
    }

    public void removeDownload(String str) {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(7, str).sendToTarget();
    }

    public void removeAllDownloads() {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(8).sendToTarget();
    }

    public void release() {
        synchronized (this.internalHandler) {
            if (this.internalHandler.released) {
                return;
            }
            this.internalHandler.sendEmptyMessage(12);
            boolean z = false;
            while (!this.internalHandler.released) {
                try {
                    this.internalHandler.wait();
                } catch (InterruptedException unused) {
                    z = true;
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
            this.applicationHandler.removeCallbacksAndMessages(null);
            this.downloads = Collections.emptyList();
            this.pendingMessages = 0;
            this.activeTaskCount = 0;
            this.initialized = false;
            this.notMetRequirements = 0;
            this.waitingForRequirements = false;
        }
    }

    private void setDownloadsPaused(boolean z) {
        if (this.downloadsPaused == z) {
            return;
        }
        this.downloadsPaused = z;
        this.pendingMessages++;
        this.internalHandler.obtainMessage(1, z ? 1 : 0, 0).sendToTarget();
        boolean updateWaitingForRequirements = updateWaitingForRequirements();
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onDownloadsPausedChanged(this, z);
        }
        if (updateWaitingForRequirements) {
            notifyWaitingForRequirementsChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRequirementsStateChanged(RequirementsWatcher requirementsWatcher, int r5) {
        Requirements requirements = requirementsWatcher.getRequirements();
        if (this.notMetRequirements != r5) {
            this.notMetRequirements = r5;
            this.pendingMessages++;
            this.internalHandler.obtainMessage(2, r5, 0).sendToTarget();
        }
        boolean updateWaitingForRequirements = updateWaitingForRequirements();
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onRequirementsStateChanged(this, requirements, r5);
        }
        if (updateWaitingForRequirements) {
            notifyWaitingForRequirementsChanged();
        }
    }

    private boolean updateWaitingForRequirements() {
        boolean z;
        if (!this.downloadsPaused && this.notMetRequirements != 0) {
            for (int r0 = 0; r0 < this.downloads.size(); r0++) {
                if (this.downloads.get(r0).state == 0) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        boolean z2 = this.waitingForRequirements != z;
        this.waitingForRequirements = z;
        return z2;
    }

    private void notifyWaitingForRequirementsChanged() {
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onWaitingForRequirementsChanged(this, this.waitingForRequirements);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleMainMessage(Message message) {
        int r0 = message.what;
        if (r0 == 0) {
            onInitialized((List) message.obj);
        } else if (r0 == 1) {
            onMessageProcessed(message.arg1, message.arg2);
        } else if (r0 == 2) {
            onDownloadUpdate((DownloadUpdate) message.obj);
        } else {
            throw new IllegalStateException();
        }
        return true;
    }

    private void onInitialized(List<Download> list) {
        this.initialized = true;
        this.downloads = Collections.unmodifiableList(list);
        boolean updateWaitingForRequirements = updateWaitingForRequirements();
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onInitialized(this);
        }
        if (updateWaitingForRequirements) {
            notifyWaitingForRequirementsChanged();
        }
    }

    private void onDownloadUpdate(DownloadUpdate downloadUpdate) {
        this.downloads = Collections.unmodifiableList(downloadUpdate.downloads);
        Download download = downloadUpdate.download;
        boolean updateWaitingForRequirements = updateWaitingForRequirements();
        if (downloadUpdate.isRemove) {
            Iterator<Listener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onDownloadRemoved(this, download);
            }
        } else {
            Iterator<Listener> it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                it2.next().onDownloadChanged(this, download, downloadUpdate.finalException);
            }
        }
        if (updateWaitingForRequirements) {
            notifyWaitingForRequirementsChanged();
        }
    }

    private void onMessageProcessed(int r2, int r3) {
        this.pendingMessages -= r2;
        this.activeTaskCount = r3;
        if (isIdle()) {
            Iterator<Listener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onIdle(this);
            }
        }
    }

    static Download mergeRequest(Download download, DownloadRequest downloadRequest, int r18, long j) {
        int r1 = download.state;
        return new Download(download.request.copyWithMergedRequest(downloadRequest), (r1 == 5 || r1 == 7) ? 7 : r18 != 0 ? 1 : 0, (r1 == 5 || download.isTerminalState()) ? j : download.startTimeMs, j, -1L, r18, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class InternalHandler extends Handler {
        private static final int UPDATE_PROGRESS_INTERVAL_MS = 5000;
        private int activeDownloadTaskCount;
        private final HashMap<String, Task> activeTasks;
        private final WritableDownloadIndex downloadIndex;
        private final DownloaderFactory downloaderFactory;
        private final ArrayList<Download> downloads;
        private boolean downloadsPaused;
        private final Handler mainHandler;
        private int maxParallelDownloads;
        private int minRetryCount;
        private int notMetRequirements;
        public boolean released;
        private final HandlerThread thread;

        public InternalHandler(HandlerThread handlerThread, WritableDownloadIndex writableDownloadIndex, DownloaderFactory downloaderFactory, Handler handler, int r6, int r7, boolean z) {
            super(handlerThread.getLooper());
            this.thread = handlerThread;
            this.downloadIndex = writableDownloadIndex;
            this.downloaderFactory = downloaderFactory;
            this.mainHandler = handler;
            this.maxParallelDownloads = r6;
            this.minRetryCount = r7;
            this.downloadsPaused = z;
            this.downloads = new ArrayList<>();
            this.activeTasks = new HashMap<>();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int r1 = 0;
            switch (message.what) {
                case 0:
                    initialize(message.arg1);
                    r1 = 1;
                    break;
                case 1:
                    setDownloadsPaused(message.arg1 != 0);
                    r1 = 1;
                    break;
                case 2:
                    setNotMetRequirements(message.arg1);
                    r1 = 1;
                    break;
                case 3:
                    setStopReason((String) message.obj, message.arg1);
                    r1 = 1;
                    break;
                case 4:
                    setMaxParallelDownloads(message.arg1);
                    r1 = 1;
                    break;
                case 5:
                    setMinRetryCount(message.arg1);
                    r1 = 1;
                    break;
                case 6:
                    addDownload((DownloadRequest) message.obj, message.arg1);
                    r1 = 1;
                    break;
                case 7:
                    removeDownload((String) message.obj);
                    r1 = 1;
                    break;
                case 8:
                    removeAllDownloads();
                    r1 = 1;
                    break;
                case 9:
                    onTaskStopped((Task) message.obj);
                    break;
                case 10:
                    onContentLengthChanged((Task) message.obj, Util.toLong(message.arg1, message.arg2));
                    return;
                case 11:
                    updateProgress();
                    return;
                case 12:
                    release();
                    return;
                default:
                    throw new IllegalStateException();
            }
            this.mainHandler.obtainMessage(1, r1, this.activeTasks.size()).sendToTarget();
        }

        private void initialize(int r6) {
            this.notMetRequirements = r6;
            DownloadCursor downloadCursor = null;
            try {
                try {
                    this.downloadIndex.setDownloadingStatesToQueued();
                    downloadCursor = this.downloadIndex.getDownloads(0, 1, 2, 5, 7);
                    while (downloadCursor.moveToNext()) {
                        this.downloads.add(downloadCursor.getDownload());
                    }
                } catch (IOException e) {
                    Log.m1135e(DownloadManager.TAG, "Failed to load index.", e);
                    this.downloads.clear();
                }
                Util.closeQuietly(downloadCursor);
                this.mainHandler.obtainMessage(0, new ArrayList(this.downloads)).sendToTarget();
                syncTasks();
            } catch (Throwable th) {
                Util.closeQuietly(downloadCursor);
                throw th;
            }
        }

        private void setDownloadsPaused(boolean z) {
            this.downloadsPaused = z;
            syncTasks();
        }

        private void setNotMetRequirements(int r1) {
            this.notMetRequirements = r1;
            syncTasks();
        }

        private void setStopReason(String str, int r5) {
            if (str == null) {
                for (int r1 = 0; r1 < this.downloads.size(); r1++) {
                    setStopReason(this.downloads.get(r1), r5);
                }
                try {
                    this.downloadIndex.setStopReason(r5);
                } catch (IOException e) {
                    Log.m1135e(DownloadManager.TAG, "Failed to set manual stop reason", e);
                }
            } else {
                Download download = getDownload(str, false);
                if (download != null) {
                    setStopReason(download, r5);
                } else {
                    try {
                        this.downloadIndex.setStopReason(str, r5);
                    } catch (IOException e2) {
                        Log.m1135e(DownloadManager.TAG, "Failed to set manual stop reason: " + str, e2);
                    }
                }
            }
            syncTasks();
        }

        private void setStopReason(Download download, int r16) {
            if (r16 == 0) {
                if (download.state == 1) {
                    putDownloadWithState(download, 0, 0);
                }
            } else if (r16 != download.stopReason) {
                int r3 = download.state;
                putDownload(new Download(download.request, (r3 == 0 || r3 == 2) ? 1 : 1, download.startTimeMs, System.currentTimeMillis(), download.contentLength, r16, 0, download.progress));
            }
        }

        private void setMaxParallelDownloads(int r1) {
            this.maxParallelDownloads = r1;
            syncTasks();
        }

        private void setMinRetryCount(int r1) {
            this.minRetryCount = r1;
        }

        private void addDownload(DownloadRequest downloadRequest, int r15) {
            Download download = getDownload(downloadRequest.f233id, true);
            long currentTimeMillis = System.currentTimeMillis();
            if (download != null) {
                putDownload(DownloadManager.mergeRequest(download, downloadRequest, r15, currentTimeMillis));
            } else {
                putDownload(new Download(downloadRequest, r15 != 0 ? 1 : 0, currentTimeMillis, currentTimeMillis, -1L, r15, 0));
            }
            syncTasks();
        }

        private void removeDownload(String str) {
            Download download = getDownload(str, true);
            if (download == null) {
                Log.m1136e(DownloadManager.TAG, "Failed to remove nonexistent download: " + str);
                return;
            }
            putDownloadWithState(download, 5, 0);
            syncTasks();
        }

        private void removeAllDownloads() {
            ArrayList arrayList = new ArrayList();
            try {
                DownloadCursor downloads = this.downloadIndex.getDownloads(3, 4);
                while (downloads.moveToNext()) {
                    arrayList.add(downloads.getDownload());
                }
                if (downloads != null) {
                    downloads.close();
                }
            } catch (IOException unused) {
                Log.m1136e(DownloadManager.TAG, "Failed to load downloads.");
            }
            for (int r4 = 0; r4 < this.downloads.size(); r4++) {
                ArrayList<Download> arrayList2 = this.downloads;
                arrayList2.set(r4, copyDownloadWithState(arrayList2.get(r4), 5, 0));
            }
            for (int r42 = 0; r42 < arrayList.size(); r42++) {
                this.downloads.add(copyDownloadWithState((Download) arrayList.get(r42), 5, 0));
            }
            Collections.sort(this.downloads, DownloadManager$InternalHandler$$ExternalSyntheticLambda0.INSTANCE);
            try {
                this.downloadIndex.setStatesToRemoving();
            } catch (IOException e) {
                Log.m1135e(DownloadManager.TAG, "Failed to update index.", e);
            }
            ArrayList arrayList3 = new ArrayList(this.downloads);
            for (int r1 = 0; r1 < this.downloads.size(); r1++) {
                this.mainHandler.obtainMessage(2, new DownloadUpdate(this.downloads.get(r1), false, arrayList3, null)).sendToTarget();
            }
            syncTasks();
        }

        private void release() {
            for (Task task : this.activeTasks.values()) {
                task.cancel(true);
            }
            try {
                this.downloadIndex.setDownloadingStatesToQueued();
            } catch (IOException e) {
                Log.m1135e(DownloadManager.TAG, "Failed to update index.", e);
            }
            this.downloads.clear();
            this.thread.quit();
            synchronized (this) {
                this.released = true;
                notifyAll();
            }
        }

        private void syncTasks() {
            int r1 = 0;
            for (int r0 = 0; r0 < this.downloads.size(); r0++) {
                Download download = this.downloads.get(r0);
                Task task = this.activeTasks.get(download.request.f233id);
                int r4 = download.state;
                if (r4 == 0) {
                    task = syncQueuedDownload(task, download);
                } else if (r4 == 1) {
                    syncStoppedDownload(task);
                } else if (r4 == 2) {
                    Assertions.checkNotNull(task);
                    syncDownloadingDownload(task, download, r1);
                } else if (r4 == 5 || r4 == 7) {
                    syncRemovingDownload(task, download);
                } else {
                    throw new IllegalStateException();
                }
                if (task != null && !task.isRemove) {
                    r1++;
                }
            }
        }

        private void syncStoppedDownload(Task task) {
            if (task != null) {
                Assertions.checkState(!task.isRemove);
                task.cancel(false);
            }
        }

        private Task syncQueuedDownload(Task task, Download download) {
            if (task == null) {
                if (!canDownloadsRun() || this.activeDownloadTaskCount >= this.maxParallelDownloads) {
                    return null;
                }
                Download putDownloadWithState = putDownloadWithState(download, 2, 0);
                Task task2 = new Task(putDownloadWithState.request, this.downloaderFactory.createDownloader(putDownloadWithState.request), putDownloadWithState.progress, false, this.minRetryCount, this);
                this.activeTasks.put(putDownloadWithState.request.f233id, task2);
                int r10 = this.activeDownloadTaskCount;
                this.activeDownloadTaskCount = r10 + 1;
                if (r10 == 0) {
                    sendEmptyMessageDelayed(11, 5000L);
                }
                task2.start();
                return task2;
            }
            Assertions.checkState(!task.isRemove);
            task.cancel(false);
            return task;
        }

        private void syncDownloadingDownload(Task task, Download download, int r4) {
            Assertions.checkState(!task.isRemove);
            if (!canDownloadsRun() || r4 >= this.maxParallelDownloads) {
                putDownloadWithState(download, 0, 0);
                task.cancel(false);
            }
        }

        private void syncRemovingDownload(Task task, Download download) {
            if (task == null) {
                Task task2 = new Task(download.request, this.downloaderFactory.createDownloader(download.request), download.progress, true, this.minRetryCount, this);
                this.activeTasks.put(download.request.f233id, task2);
                task2.start();
            } else if (task.isRemove) {
            } else {
                task.cancel(false);
            }
        }

        private void onContentLengthChanged(Task task, long j) {
            Download download = (Download) Assertions.checkNotNull(getDownload(task.request.f233id, false));
            if (j == download.contentLength || j == -1) {
                return;
            }
            putDownload(new Download(download.request, download.state, download.startTimeMs, System.currentTimeMillis(), j, download.stopReason, download.failureReason, download.progress));
        }

        private void onTaskStopped(Task task) {
            String str = task.request.f233id;
            this.activeTasks.remove(str);
            boolean z = task.isRemove;
            if (!z) {
                int r2 = this.activeDownloadTaskCount - 1;
                this.activeDownloadTaskCount = r2;
                if (r2 == 0) {
                    removeMessages(11);
                }
            }
            if (task.isCanceled) {
                syncTasks();
                return;
            }
            Exception exc = task.finalException;
            if (exc != null) {
                Log.m1135e(DownloadManager.TAG, "Task failed: " + task.request + ", " + z, exc);
            }
            Download download = (Download) Assertions.checkNotNull(getDownload(str, false));
            int r0 = download.state;
            if (r0 == 2) {
                Assertions.checkState(!z);
                onDownloadTaskStopped(download, exc);
            } else if (r0 == 5 || r0 == 7) {
                Assertions.checkState(z);
                onRemoveTaskStopped(download);
            } else {
                throw new IllegalStateException();
            }
            syncTasks();
        }

        private void onDownloadTaskStopped(Download download, Exception exc) {
            Download download2 = new Download(download.request, exc == null ? 3 : 4, download.startTimeMs, System.currentTimeMillis(), download.contentLength, download.stopReason, exc == null ? 0 : 1, download.progress);
            this.downloads.remove(getDownloadIndex(download2.request.f233id));
            try {
                this.downloadIndex.putDownload(download2);
            } catch (IOException e) {
                Log.m1135e(DownloadManager.TAG, "Failed to update index.", e);
            }
            this.mainHandler.obtainMessage(2, new DownloadUpdate(download2, false, new ArrayList(this.downloads), exc)).sendToTarget();
        }

        private void onRemoveTaskStopped(Download download) {
            if (download.state == 7) {
                putDownloadWithState(download, download.stopReason == 0 ? 0 : 1, download.stopReason);
                syncTasks();
                return;
            }
            this.downloads.remove(getDownloadIndex(download.request.f233id));
            try {
                this.downloadIndex.removeDownload(download.request.f233id);
            } catch (IOException unused) {
                Log.m1136e(DownloadManager.TAG, "Failed to remove from database");
            }
            this.mainHandler.obtainMessage(2, new DownloadUpdate(download, true, new ArrayList(this.downloads), null)).sendToTarget();
        }

        private void updateProgress() {
            for (int r0 = 0; r0 < this.downloads.size(); r0++) {
                Download download = this.downloads.get(r0);
                if (download.state == 2) {
                    try {
                        this.downloadIndex.putDownload(download);
                    } catch (IOException e) {
                        Log.m1135e(DownloadManager.TAG, "Failed to update index.", e);
                    }
                }
            }
            sendEmptyMessageDelayed(11, 5000L);
        }

        private boolean canDownloadsRun() {
            return !this.downloadsPaused && this.notMetRequirements == 0;
        }

        private Download putDownloadWithState(Download download, int r3, int r4) {
            Assertions.checkState((r3 == 3 || r3 == 4) ? false : true);
            return putDownload(copyDownloadWithState(download, r3, r4));
        }

        private Download putDownload(Download download) {
            Assertions.checkState((download.state == 3 || download.state == 4) ? false : true);
            int downloadIndex = getDownloadIndex(download.request.f233id);
            if (downloadIndex == -1) {
                this.downloads.add(download);
                Collections.sort(this.downloads, DownloadManager$InternalHandler$$ExternalSyntheticLambda0.INSTANCE);
            } else {
                boolean z = download.startTimeMs != this.downloads.get(downloadIndex).startTimeMs;
                this.downloads.set(downloadIndex, download);
                if (z) {
                    Collections.sort(this.downloads, DownloadManager$InternalHandler$$ExternalSyntheticLambda0.INSTANCE);
                }
            }
            try {
                this.downloadIndex.putDownload(download);
            } catch (IOException e) {
                Log.m1135e(DownloadManager.TAG, "Failed to update index.", e);
            }
            this.mainHandler.obtainMessage(2, new DownloadUpdate(download, false, new ArrayList(this.downloads), null)).sendToTarget();
            return download;
        }

        private Download getDownload(String str, boolean z) {
            int downloadIndex = getDownloadIndex(str);
            if (downloadIndex != -1) {
                return this.downloads.get(downloadIndex);
            }
            if (z) {
                try {
                    return this.downloadIndex.getDownload(str);
                } catch (IOException e) {
                    Log.m1135e(DownloadManager.TAG, "Failed to load download: " + str, e);
                    return null;
                }
            }
            return null;
        }

        private int getDownloadIndex(String str) {
            for (int r0 = 0; r0 < this.downloads.size(); r0++) {
                if (this.downloads.get(r0).request.f233id.equals(str)) {
                    return r0;
                }
            }
            return -1;
        }

        private static Download copyDownloadWithState(Download download, int r14, int r15) {
            return new Download(download.request, r14, download.startTimeMs, System.currentTimeMillis(), download.contentLength, r15, 0, download.progress);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int compareStartTimes(Download download, Download download2) {
            return Util.compareLong(download.startTimeMs, download2.startTimeMs);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class Task extends Thread implements Downloader.ProgressListener {
        private long contentLength;
        private final DownloadProgress downloadProgress;
        private final Downloader downloader;
        private Exception finalException;
        private volatile InternalHandler internalHandler;
        private volatile boolean isCanceled;
        private final boolean isRemove;
        private final int minRetryCount;
        private final DownloadRequest request;

        private Task(DownloadRequest downloadRequest, Downloader downloader, DownloadProgress downloadProgress, boolean z, int r5, InternalHandler internalHandler) {
            this.request = downloadRequest;
            this.downloader = downloader;
            this.downloadProgress = downloadProgress;
            this.isRemove = z;
            this.minRetryCount = r5;
            this.internalHandler = internalHandler;
            this.contentLength = -1L;
        }

        public void cancel(boolean z) {
            if (z) {
                this.internalHandler = null;
            }
            if (this.isCanceled) {
                return;
            }
            this.isCanceled = true;
            this.downloader.cancel();
            interrupt();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                if (this.isRemove) {
                    this.downloader.remove();
                } else {
                    long j = -1;
                    int r3 = 0;
                    while (!this.isCanceled) {
                        try {
                            this.downloader.download(this);
                            break;
                        } catch (IOException e) {
                            if (!this.isCanceled) {
                                long j2 = this.downloadProgress.bytesDownloaded;
                                if (j2 != j) {
                                    j = j2;
                                    r3 = 0;
                                }
                                r3++;
                                if (r3 > this.minRetryCount) {
                                    throw e;
                                }
                                Thread.sleep(getRetryDelayMillis(r3));
                            }
                        }
                    }
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            } catch (Exception e2) {
                this.finalException = e2;
            }
            InternalHandler internalHandler = this.internalHandler;
            if (internalHandler != null) {
                internalHandler.obtainMessage(9, this).sendToTarget();
            }
        }

        @Override // com.google.android.exoplayer2.offline.Downloader.ProgressListener
        public void onProgress(long j, long j2, float f) {
            this.downloadProgress.bytesDownloaded = j2;
            this.downloadProgress.percentDownloaded = f;
            if (j != this.contentLength) {
                this.contentLength = j;
                InternalHandler internalHandler = this.internalHandler;
                if (internalHandler != null) {
                    internalHandler.obtainMessage(10, (int) (j >> 32), (int) j, this).sendToTarget();
                }
            }
        }

        private static int getRetryDelayMillis(int r1) {
            return Math.min((r1 - 1) * 1000, 5000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class DownloadUpdate {
        public final Download download;
        public final List<Download> downloads;
        public final Exception finalException;
        public final boolean isRemove;

        public DownloadUpdate(Download download, boolean z, List<Download> list, Exception exc) {
            this.download = download;
            this.isRemove = z;
            this.downloads = list;
            this.finalException = exc;
        }
    }
}
