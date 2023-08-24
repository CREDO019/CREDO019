package androidx.recyclerview.widget;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.recyclerview.widget.ThreadUtil;
import androidx.recyclerview.widget.TileList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
class MessageThreadUtil<T> implements ThreadUtil<T> {

    /* renamed from: androidx.recyclerview.widget.MessageThreadUtil$1 */
    /* loaded from: classes.dex */
    class C05681 implements ThreadUtil.MainThreadCallback<T> {
        static final int ADD_TILE = 2;
        static final int REMOVE_TILE = 3;
        static final int UPDATE_ITEM_COUNT = 1;
        final /* synthetic */ ThreadUtil.MainThreadCallback val$callback;
        final MessageQueue mQueue = new MessageQueue();
        private final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
        private Runnable mMainThreadRunnable = new Runnable() { // from class: androidx.recyclerview.widget.MessageThreadUtil.1.1
            @Override // java.lang.Runnable
            public void run() {
                SyncQueueItem next = C05681.this.mQueue.next();
                while (next != null) {
                    int r1 = next.what;
                    if (r1 == 1) {
                        C05681.this.val$callback.updateItemCount(next.arg1, next.arg2);
                    } else if (r1 == 2) {
                        C05681.this.val$callback.addTile(next.arg1, (TileList.Tile) next.data);
                    } else if (r1 == 3) {
                        C05681.this.val$callback.removeTile(next.arg1, next.arg2);
                    } else {
                        Log.e("ThreadUtil", "Unsupported message, what=" + next.what);
                    }
                    next = C05681.this.mQueue.next();
                }
            }
        };

        C05681(ThreadUtil.MainThreadCallback mainThreadCallback) {
            this.val$callback = mainThreadCallback;
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void updateItemCount(int r2, int r3) {
            sendMessage(SyncQueueItem.obtainMessage(1, r2, r3));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void addTile(int r2, TileList.Tile<T> tile) {
            sendMessage(SyncQueueItem.obtainMessage(2, r2, tile));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
        public void removeTile(int r2, int r3) {
            sendMessage(SyncQueueItem.obtainMessage(3, r2, r3));
        }

        private void sendMessage(SyncQueueItem syncQueueItem) {
            this.mQueue.sendMessage(syncQueueItem);
            this.mMainThreadHandler.post(this.mMainThreadRunnable);
        }
    }

    @Override // androidx.recyclerview.widget.ThreadUtil
    public ThreadUtil.MainThreadCallback<T> getMainThreadProxy(ThreadUtil.MainThreadCallback<T> mainThreadCallback) {
        return new C05681(mainThreadCallback);
    }

    /* renamed from: androidx.recyclerview.widget.MessageThreadUtil$2 */
    /* loaded from: classes.dex */
    class C05702 implements ThreadUtil.BackgroundCallback<T> {
        static final int LOAD_TILE = 3;
        static final int RECYCLE_TILE = 4;
        static final int REFRESH = 1;
        static final int UPDATE_RANGE = 2;
        final /* synthetic */ ThreadUtil.BackgroundCallback val$callback;
        final MessageQueue mQueue = new MessageQueue();
        private final Executor mExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
        AtomicBoolean mBackgroundRunning = new AtomicBoolean(false);
        private Runnable mBackgroundRunnable = new Runnable() { // from class: androidx.recyclerview.widget.MessageThreadUtil.2.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    SyncQueueItem next = C05702.this.mQueue.next();
                    if (next != null) {
                        int r1 = next.what;
                        if (r1 == 1) {
                            C05702.this.mQueue.removeMessages(1);
                            C05702.this.val$callback.refresh(next.arg1);
                        } else if (r1 == 2) {
                            C05702.this.mQueue.removeMessages(2);
                            C05702.this.mQueue.removeMessages(3);
                            C05702.this.val$callback.updateRange(next.arg1, next.arg2, next.arg3, next.arg4, next.arg5);
                        } else if (r1 == 3) {
                            C05702.this.val$callback.loadTile(next.arg1, next.arg2);
                        } else if (r1 == 4) {
                            C05702.this.val$callback.recycleTile((TileList.Tile) next.data);
                        } else {
                            Log.e("ThreadUtil", "Unsupported message, what=" + next.what);
                        }
                    } else {
                        C05702.this.mBackgroundRunning.set(false);
                        return;
                    }
                }
            }
        };

        C05702(ThreadUtil.BackgroundCallback backgroundCallback) {
            this.val$callback = backgroundCallback;
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void refresh(int r3) {
            sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(1, r3, (Object) null));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void updateRange(int r8, int r9, int r10, int r11, int r12) {
            sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(2, r8, r9, r10, r11, r12, null));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void loadTile(int r2, int r3) {
            sendMessage(SyncQueueItem.obtainMessage(3, r2, r3));
        }

        @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
        public void recycleTile(TileList.Tile<T> tile) {
            sendMessage(SyncQueueItem.obtainMessage(4, 0, tile));
        }

        private void sendMessage(SyncQueueItem syncQueueItem) {
            this.mQueue.sendMessage(syncQueueItem);
            maybeExecuteBackgroundRunnable();
        }

        private void sendMessageAtFrontOfQueue(SyncQueueItem syncQueueItem) {
            this.mQueue.sendMessageAtFrontOfQueue(syncQueueItem);
            maybeExecuteBackgroundRunnable();
        }

        private void maybeExecuteBackgroundRunnable() {
            if (this.mBackgroundRunning.compareAndSet(false, true)) {
                this.mExecutor.execute(this.mBackgroundRunnable);
            }
        }
    }

    @Override // androidx.recyclerview.widget.ThreadUtil
    public ThreadUtil.BackgroundCallback<T> getBackgroundProxy(ThreadUtil.BackgroundCallback<T> backgroundCallback) {
        return new C05702(backgroundCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SyncQueueItem {
        private static SyncQueueItem sPool;
        private static final Object sPoolLock = new Object();
        public int arg1;
        public int arg2;
        public int arg3;
        public int arg4;
        public int arg5;
        public Object data;
        SyncQueueItem next;
        public int what;

        SyncQueueItem() {
        }

        void recycle() {
            this.next = null;
            this.arg5 = 0;
            this.arg4 = 0;
            this.arg3 = 0;
            this.arg2 = 0;
            this.arg1 = 0;
            this.what = 0;
            this.data = null;
            synchronized (sPoolLock) {
                SyncQueueItem syncQueueItem = sPool;
                if (syncQueueItem != null) {
                    this.next = syncQueueItem;
                }
                sPool = this;
            }
        }

        static SyncQueueItem obtainMessage(int r3, int r4, int r5, int r6, int r7, int r8, Object obj) {
            SyncQueueItem syncQueueItem;
            synchronized (sPoolLock) {
                syncQueueItem = sPool;
                if (syncQueueItem == null) {
                    syncQueueItem = new SyncQueueItem();
                } else {
                    sPool = syncQueueItem.next;
                    syncQueueItem.next = null;
                }
                syncQueueItem.what = r3;
                syncQueueItem.arg1 = r4;
                syncQueueItem.arg2 = r5;
                syncQueueItem.arg3 = r6;
                syncQueueItem.arg4 = r7;
                syncQueueItem.arg5 = r8;
                syncQueueItem.data = obj;
            }
            return syncQueueItem;
        }

        static SyncQueueItem obtainMessage(int r7, int r8, int r9) {
            return obtainMessage(r7, r8, r9, 0, 0, 0, null);
        }

        static SyncQueueItem obtainMessage(int r7, int r8, Object obj) {
            return obtainMessage(r7, r8, 0, 0, 0, 0, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class MessageQueue {
        private SyncQueueItem mRoot;

        MessageQueue() {
        }

        synchronized SyncQueueItem next() {
            SyncQueueItem syncQueueItem = this.mRoot;
            if (syncQueueItem == null) {
                return null;
            }
            this.mRoot = syncQueueItem.next;
            return syncQueueItem;
        }

        synchronized void sendMessageAtFrontOfQueue(SyncQueueItem syncQueueItem) {
            syncQueueItem.next = this.mRoot;
            this.mRoot = syncQueueItem;
        }

        synchronized void sendMessage(SyncQueueItem syncQueueItem) {
            SyncQueueItem syncQueueItem2 = this.mRoot;
            if (syncQueueItem2 == null) {
                this.mRoot = syncQueueItem;
                return;
            }
            while (syncQueueItem2.next != null) {
                syncQueueItem2 = syncQueueItem2.next;
            }
            syncQueueItem2.next = syncQueueItem;
        }

        synchronized void removeMessages(int r5) {
            while (true) {
                SyncQueueItem syncQueueItem = this.mRoot;
                if (syncQueueItem == null || syncQueueItem.what != r5) {
                    break;
                }
                SyncQueueItem syncQueueItem2 = this.mRoot;
                this.mRoot = syncQueueItem2.next;
                syncQueueItem2.recycle();
            }
            SyncQueueItem syncQueueItem3 = this.mRoot;
            if (syncQueueItem3 != null) {
                SyncQueueItem syncQueueItem4 = syncQueueItem3.next;
                while (syncQueueItem4 != null) {
                    SyncQueueItem syncQueueItem5 = syncQueueItem4.next;
                    if (syncQueueItem4.what == r5) {
                        syncQueueItem3.next = syncQueueItem5;
                        syncQueueItem4.recycle();
                    } else {
                        syncQueueItem3 = syncQueueItem4;
                    }
                    syncQueueItem4 = syncQueueItem5;
                }
            }
        }
    }
}
