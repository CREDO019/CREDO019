package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

/* loaded from: classes.dex */
public abstract class SortedListAdapterCallback<T2> extends SortedList.Callback<T2> {
    final RecyclerView.Adapter mAdapter;

    public SortedListAdapterCallback(RecyclerView.Adapter adapter) {
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

    @Override // androidx.recyclerview.widget.SortedList.Callback
    public void onChanged(int r2, int r3) {
        this.mAdapter.notifyItemRangeChanged(r2, r3);
    }

    @Override // androidx.recyclerview.widget.SortedList.Callback, androidx.recyclerview.widget.ListUpdateCallback
    public void onChanged(int r2, int r3, Object obj) {
        this.mAdapter.notifyItemRangeChanged(r2, r3, obj);
    }
}
