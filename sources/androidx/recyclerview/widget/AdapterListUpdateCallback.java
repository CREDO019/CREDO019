package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes.dex */
public final class AdapterListUpdateCallback implements ListUpdateCallback {
    private final RecyclerView.Adapter mAdapter;

    public AdapterListUpdateCallback(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onInserted(int r2, int r3) {
        this.mAdapter.notifyItemRangeInserted(r2, r3);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onRemoved(int r2, int r3) {
        this.mAdapter.notifyItemRangeRemoved(r2, r3);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onMoved(int r2, int r3) {
        this.mAdapter.notifyItemMoved(r2, r3);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onChanged(int r2, int r3, Object obj) {
        this.mAdapter.notifyItemRangeChanged(r2, r3, obj);
    }
}
