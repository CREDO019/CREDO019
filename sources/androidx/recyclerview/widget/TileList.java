package androidx.recyclerview.widget;

import android.util.SparseArray;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
class TileList<T> {
    Tile<T> mLastAccessedTile;
    final int mTileSize;
    private final SparseArray<Tile<T>> mTiles = new SparseArray<>(10);

    public TileList(int r3) {
        this.mTileSize = r3;
    }

    public T getItemAt(int r3) {
        Tile<T> tile = this.mLastAccessedTile;
        if (tile == null || !tile.containsPosition(r3)) {
            int indexOfKey = this.mTiles.indexOfKey(r3 - (r3 % this.mTileSize));
            if (indexOfKey < 0) {
                return null;
            }
            this.mLastAccessedTile = this.mTiles.valueAt(indexOfKey);
        }
        return this.mLastAccessedTile.getByPosition(r3);
    }

    public int size() {
        return this.mTiles.size();
    }

    public void clear() {
        this.mTiles.clear();
    }

    public Tile<T> getAtIndex(int r2) {
        if (r2 < 0 || r2 >= this.mTiles.size()) {
            return null;
        }
        return this.mTiles.valueAt(r2);
    }

    public Tile<T> addOrReplace(Tile<T> tile) {
        int indexOfKey = this.mTiles.indexOfKey(tile.mStartPosition);
        if (indexOfKey < 0) {
            this.mTiles.put(tile.mStartPosition, tile);
            return null;
        }
        Tile<T> valueAt = this.mTiles.valueAt(indexOfKey);
        this.mTiles.setValueAt(indexOfKey, tile);
        if (this.mLastAccessedTile == valueAt) {
            this.mLastAccessedTile = tile;
        }
        return valueAt;
    }

    public Tile<T> removeAtPos(int r3) {
        Tile<T> tile = this.mTiles.get(r3);
        if (this.mLastAccessedTile == tile) {
            this.mLastAccessedTile = null;
        }
        this.mTiles.delete(r3);
        return tile;
    }

    /* loaded from: classes.dex */
    public static class Tile<T> {
        public int mItemCount;
        public final T[] mItems;
        Tile<T> mNext;
        public int mStartPosition;

        public Tile(Class<T> cls, int r2) {
            this.mItems = (T[]) ((Object[]) Array.newInstance((Class<?>) cls, r2));
        }

        boolean containsPosition(int r3) {
            int r0 = this.mStartPosition;
            return r0 <= r3 && r3 < r0 + this.mItemCount;
        }

        T getByPosition(int r3) {
            return this.mItems[r3 - this.mStartPosition];
        }
    }
}
