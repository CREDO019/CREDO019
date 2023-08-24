package androidx.recyclerview.widget;

import androidx.recyclerview.widget.TileList;

/* loaded from: classes.dex */
interface ThreadUtil<T> {

    /* loaded from: classes.dex */
    public interface BackgroundCallback<T> {
        void loadTile(int r1, int r2);

        void recycleTile(TileList.Tile<T> tile);

        void refresh(int r1);

        void updateRange(int r1, int r2, int r3, int r4, int r5);
    }

    /* loaded from: classes.dex */
    public interface MainThreadCallback<T> {
        void addTile(int r1, TileList.Tile<T> tile);

        void removeTile(int r1, int r2);

        void updateItemCount(int r1, int r2);
    }

    BackgroundCallback<T> getBackgroundProxy(BackgroundCallback<T> backgroundCallback);

    MainThreadCallback<T> getMainThreadProxy(MainThreadCallback<T> mainThreadCallback);
}
