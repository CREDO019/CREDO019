package androidx.recyclerview.widget;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.recyclerview.widget.ThreadUtil;
import androidx.recyclerview.widget.TileList;

/* loaded from: classes.dex */
public class AsyncListUtil<T> {
    static final boolean DEBUG = false;
    static final String TAG = "AsyncListUtil";
    boolean mAllowScrollHints;
    private final ThreadUtil.BackgroundCallback<T> mBackgroundCallback;
    final ThreadUtil.BackgroundCallback<T> mBackgroundProxy;
    final DataCallback<T> mDataCallback;
    private final ThreadUtil.MainThreadCallback<T> mMainThreadCallback;
    final ThreadUtil.MainThreadCallback<T> mMainThreadProxy;
    final Class<T> mTClass;
    final TileList<T> mTileList;
    final int mTileSize;
    final ViewCallback mViewCallback;
    final int[] mTmpRange = new int[2];
    final int[] mPrevRange = new int[2];
    final int[] mTmpRangeExtended = new int[2];
    private int mScrollHint = 0;
    int mItemCount = 0;
    int mDisplayedGeneration = 0;
    int mRequestedGeneration = 0;
    final SparseIntArray mMissingPositions = new SparseIntArray();

    /* loaded from: classes.dex */
    public static abstract class DataCallback<T> {
        public abstract void fillData(T[] tArr, int r2, int r3);

        public int getMaxCachedTiles() {
            return 10;
        }

        public void recycleData(T[] tArr, int r2) {
        }

        public abstract int refreshData();
    }

    void log(String str, Object... objArr) {
        Log.d(TAG, "[MAIN] " + String.format(str, objArr));
    }

    public AsyncListUtil(Class<T> cls, int r4, DataCallback<T> dataCallback, ViewCallback viewCallback) {
        ThreadUtil.MainThreadCallback<T> mainThreadCallback = new ThreadUtil.MainThreadCallback<T>() { // from class: androidx.recyclerview.widget.AsyncListUtil.1
            @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
            public void updateItemCount(int r1, int r2) {
                if (isRequestedGeneration(r1)) {
                    AsyncListUtil.this.mItemCount = r2;
                    AsyncListUtil.this.mViewCallback.onDataRefresh();
                    AsyncListUtil asyncListUtil = AsyncListUtil.this;
                    asyncListUtil.mDisplayedGeneration = asyncListUtil.mRequestedGeneration;
                    recycleAllTiles();
                    AsyncListUtil.this.mAllowScrollHints = false;
                    AsyncListUtil.this.updateRange();
                }
            }

            @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
            public void addTile(int r42, TileList.Tile<T> tile) {
                if (!isRequestedGeneration(r42)) {
                    AsyncListUtil.this.mBackgroundProxy.recycleTile(tile);
                    return;
                }
                TileList.Tile<T> addOrReplace = AsyncListUtil.this.mTileList.addOrReplace(tile);
                if (addOrReplace != null) {
                    Log.e(AsyncListUtil.TAG, "duplicate tile @" + addOrReplace.mStartPosition);
                    AsyncListUtil.this.mBackgroundProxy.recycleTile(addOrReplace);
                }
                int r43 = tile.mStartPosition + tile.mItemCount;
                int r0 = 0;
                while (r0 < AsyncListUtil.this.mMissingPositions.size()) {
                    int keyAt = AsyncListUtil.this.mMissingPositions.keyAt(r0);
                    if (tile.mStartPosition > keyAt || keyAt >= r43) {
                        r0++;
                    } else {
                        AsyncListUtil.this.mMissingPositions.removeAt(r0);
                        AsyncListUtil.this.mViewCallback.onItemLoaded(keyAt);
                    }
                }
            }

            @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
            public void removeTile(int r2, int r3) {
                if (isRequestedGeneration(r2)) {
                    TileList.Tile<T> removeAtPos = AsyncListUtil.this.mTileList.removeAtPos(r3);
                    if (removeAtPos == null) {
                        Log.e(AsyncListUtil.TAG, "tile not found @" + r3);
                        return;
                    }
                    AsyncListUtil.this.mBackgroundProxy.recycleTile(removeAtPos);
                }
            }

            private void recycleAllTiles() {
                for (int r0 = 0; r0 < AsyncListUtil.this.mTileList.size(); r0++) {
                    AsyncListUtil.this.mBackgroundProxy.recycleTile(AsyncListUtil.this.mTileList.getAtIndex(r0));
                }
                AsyncListUtil.this.mTileList.clear();
            }

            private boolean isRequestedGeneration(int r2) {
                return r2 == AsyncListUtil.this.mRequestedGeneration;
            }
        };
        this.mMainThreadCallback = mainThreadCallback;
        ThreadUtil.BackgroundCallback<T> backgroundCallback = new ThreadUtil.BackgroundCallback<T>() { // from class: androidx.recyclerview.widget.AsyncListUtil.2
            private int mFirstRequiredTileStart;
            private int mGeneration;
            private int mItemCount;
            private int mLastRequiredTileStart;
            final SparseBooleanArray mLoadedTiles = new SparseBooleanArray();
            private TileList.Tile<T> mRecycledRoot;

            @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
            public void refresh(int r3) {
                this.mGeneration = r3;
                this.mLoadedTiles.clear();
                this.mItemCount = AsyncListUtil.this.mDataCallback.refreshData();
                AsyncListUtil.this.mMainThreadProxy.updateItemCount(this.mGeneration, this.mItemCount);
            }

            @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
            public void updateRange(int r2, int r3, int r42, int r5, int r6) {
                if (r2 > r3) {
                    return;
                }
                int tileStart = getTileStart(r2);
                int tileStart2 = getTileStart(r3);
                this.mFirstRequiredTileStart = getTileStart(r42);
                int tileStart3 = getTileStart(r5);
                this.mLastRequiredTileStart = tileStart3;
                if (r6 == 1) {
                    requestTiles(this.mFirstRequiredTileStart, tileStart2, r6, true);
                    requestTiles(tileStart2 + AsyncListUtil.this.mTileSize, this.mLastRequiredTileStart, r6, false);
                    return;
                }
                requestTiles(tileStart, tileStart3, r6, false);
                requestTiles(this.mFirstRequiredTileStart, tileStart - AsyncListUtil.this.mTileSize, r6, true);
            }

            private int getTileStart(int r2) {
                return r2 - (r2 % AsyncListUtil.this.mTileSize);
            }

            private void requestTiles(int r42, int r5, int r6, boolean z) {
                int r0 = r42;
                while (r0 <= r5) {
                    AsyncListUtil.this.mBackgroundProxy.loadTile(z ? (r5 + r42) - r0 : r0, r6);
                    r0 += AsyncListUtil.this.mTileSize;
                }
            }

            @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
            public void loadTile(int r5, int r6) {
                if (isTileLoaded(r5)) {
                    return;
                }
                TileList.Tile<T> acquireTile = acquireTile();
                acquireTile.mStartPosition = r5;
                acquireTile.mItemCount = Math.min(AsyncListUtil.this.mTileSize, this.mItemCount - acquireTile.mStartPosition);
                AsyncListUtil.this.mDataCallback.fillData(acquireTile.mItems, acquireTile.mStartPosition, acquireTile.mItemCount);
                flushTileCache(r6);
                addTile(acquireTile);
            }

            @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
            public void recycleTile(TileList.Tile<T> tile) {
                AsyncListUtil.this.mDataCallback.recycleData(tile.mItems, tile.mItemCount);
                tile.mNext = this.mRecycledRoot;
                this.mRecycledRoot = tile;
            }

            private TileList.Tile<T> acquireTile() {
                TileList.Tile<T> tile = this.mRecycledRoot;
                if (tile != null) {
                    this.mRecycledRoot = tile.mNext;
                    return tile;
                }
                return new TileList.Tile<>(AsyncListUtil.this.mTClass, AsyncListUtil.this.mTileSize);
            }

            private boolean isTileLoaded(int r2) {
                return this.mLoadedTiles.get(r2);
            }

            private void addTile(TileList.Tile<T> tile) {
                this.mLoadedTiles.put(tile.mStartPosition, true);
                AsyncListUtil.this.mMainThreadProxy.addTile(this.mGeneration, tile);
            }

            private void removeTile(int r3) {
                this.mLoadedTiles.delete(r3);
                AsyncListUtil.this.mMainThreadProxy.removeTile(this.mGeneration, r3);
            }

            private void flushTileCache(int r8) {
                int maxCachedTiles = AsyncListUtil.this.mDataCallback.getMaxCachedTiles();
                while (this.mLoadedTiles.size() >= maxCachedTiles) {
                    int keyAt = this.mLoadedTiles.keyAt(0);
                    SparseBooleanArray sparseBooleanArray = this.mLoadedTiles;
                    int keyAt2 = sparseBooleanArray.keyAt(sparseBooleanArray.size() - 1);
                    int r3 = this.mFirstRequiredTileStart - keyAt;
                    int r5 = keyAt2 - this.mLastRequiredTileStart;
                    if (r3 > 0 && (r3 >= r5 || r8 == 2)) {
                        removeTile(keyAt);
                    } else if (r5 <= 0) {
                        return;
                    } else {
                        if (r3 >= r5 && r8 != 1) {
                            return;
                        }
                        removeTile(keyAt2);
                    }
                }
            }

            private void log(String str, Object... objArr) {
                Log.d(AsyncListUtil.TAG, "[BKGR] " + String.format(str, objArr));
            }
        };
        this.mBackgroundCallback = backgroundCallback;
        this.mTClass = cls;
        this.mTileSize = r4;
        this.mDataCallback = dataCallback;
        this.mViewCallback = viewCallback;
        this.mTileList = new TileList<>(r4);
        MessageThreadUtil messageThreadUtil = new MessageThreadUtil();
        this.mMainThreadProxy = messageThreadUtil.getMainThreadProxy(mainThreadCallback);
        this.mBackgroundProxy = messageThreadUtil.getBackgroundProxy(backgroundCallback);
        refresh();
    }

    private boolean isRefreshPending() {
        return this.mRequestedGeneration != this.mDisplayedGeneration;
    }

    public void onRangeChanged() {
        if (isRefreshPending()) {
            return;
        }
        updateRange();
        this.mAllowScrollHints = true;
    }

    public void refresh() {
        this.mMissingPositions.clear();
        ThreadUtil.BackgroundCallback<T> backgroundCallback = this.mBackgroundProxy;
        int r1 = this.mRequestedGeneration + 1;
        this.mRequestedGeneration = r1;
        backgroundCallback.refresh(r1);
    }

    public T getItem(int r4) {
        if (r4 < 0 || r4 >= this.mItemCount) {
            throw new IndexOutOfBoundsException(r4 + " is not within 0 and " + this.mItemCount);
        }
        T itemAt = this.mTileList.getItemAt(r4);
        if (itemAt == null && !isRefreshPending()) {
            this.mMissingPositions.put(r4, 0);
        }
        return itemAt;
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    void updateRange() {
        this.mViewCallback.getItemRangeInto(this.mTmpRange);
        int[] r0 = this.mTmpRange;
        if (r0[0] > r0[1] || r0[0] < 0 || r0[1] >= this.mItemCount) {
            return;
        }
        if (!this.mAllowScrollHints) {
            this.mScrollHint = 0;
        } else {
            int r2 = r0[0];
            int[] r4 = this.mPrevRange;
            if (r2 > r4[1] || r4[0] > r0[1]) {
                this.mScrollHint = 0;
            } else if (r0[0] < r4[0]) {
                this.mScrollHint = 1;
            } else if (r0[0] > r4[0]) {
                this.mScrollHint = 2;
            }
        }
        int[] r22 = this.mPrevRange;
        r22[0] = r0[0];
        r22[1] = r0[1];
        this.mViewCallback.extendRangeInto(r0, this.mTmpRangeExtended, this.mScrollHint);
        int[] r02 = this.mTmpRangeExtended;
        r02[0] = Math.min(this.mTmpRange[0], Math.max(r02[0], 0));
        int[] r03 = this.mTmpRangeExtended;
        r03[1] = Math.max(this.mTmpRange[1], Math.min(r03[1], this.mItemCount - 1));
        ThreadUtil.BackgroundCallback<T> backgroundCallback = this.mBackgroundProxy;
        int[] r04 = this.mTmpRange;
        int r5 = r04[0];
        int r6 = r04[1];
        int[] r05 = this.mTmpRangeExtended;
        backgroundCallback.updateRange(r5, r6, r05[0], r05[1], this.mScrollHint);
    }

    /* loaded from: classes.dex */
    public static abstract class ViewCallback {
        public static final int HINT_SCROLL_ASC = 2;
        public static final int HINT_SCROLL_DESC = 1;
        public static final int HINT_SCROLL_NONE = 0;

        public abstract void getItemRangeInto(int[] r1);

        public abstract void onDataRefresh();

        public abstract void onItemLoaded(int r1);

        public void extendRangeInto(int[] r7, int[] r8, int r9) {
            int r1 = (r7[1] - r7[0]) + 1;
            int r3 = r1 / 2;
            r8[0] = r7[0] - (r9 == 1 ? r1 : r3);
            int r72 = r7[1];
            if (r9 != 2) {
                r1 = r3;
            }
            r8[1] = r72 + r1;
        }
    }
}
