package androidx.recyclerview.widget;

import android.view.ViewGroup;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StableIdStorage;
import androidx.recyclerview.widget.ViewTypeStorage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class NestedAdapterWrapper {
    public final RecyclerView.Adapter<RecyclerView.ViewHolder> adapter;
    private RecyclerView.AdapterDataObserver mAdapterObserver = new RecyclerView.AdapterDataObserver() { // from class: androidx.recyclerview.widget.NestedAdapterWrapper.1
        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            NestedAdapterWrapper nestedAdapterWrapper = NestedAdapterWrapper.this;
            nestedAdapterWrapper.mCachedItemCount = nestedAdapterWrapper.adapter.getItemCount();
            NestedAdapterWrapper.this.mCallback.onChanged(NestedAdapterWrapper.this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int r4, int r5) {
            NestedAdapterWrapper.this.mCallback.onItemRangeChanged(NestedAdapterWrapper.this, r4, r5, null);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int r3, int r4, Object obj) {
            NestedAdapterWrapper.this.mCallback.onItemRangeChanged(NestedAdapterWrapper.this, r3, r4, obj);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int r3, int r4) {
            NestedAdapterWrapper.this.mCachedItemCount += r4;
            NestedAdapterWrapper.this.mCallback.onItemRangeInserted(NestedAdapterWrapper.this, r3, r4);
            if (NestedAdapterWrapper.this.mCachedItemCount <= 0 || NestedAdapterWrapper.this.adapter.getStateRestorationPolicy() != RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY) {
                return;
            }
            NestedAdapterWrapper.this.mCallback.onStateRestorationPolicyChanged(NestedAdapterWrapper.this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int r3, int r4) {
            NestedAdapterWrapper.this.mCachedItemCount -= r4;
            NestedAdapterWrapper.this.mCallback.onItemRangeRemoved(NestedAdapterWrapper.this, r3, r4);
            if (NestedAdapterWrapper.this.mCachedItemCount >= 1 || NestedAdapterWrapper.this.adapter.getStateRestorationPolicy() != RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY) {
                return;
            }
            NestedAdapterWrapper.this.mCallback.onStateRestorationPolicyChanged(NestedAdapterWrapper.this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int r2, int r3, int r4) {
            Preconditions.checkArgument(r4 == 1, "moving more than 1 item is not supported in RecyclerView");
            NestedAdapterWrapper.this.mCallback.onItemRangeMoved(NestedAdapterWrapper.this, r2, r3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onStateRestorationPolicyChanged() {
            NestedAdapterWrapper.this.mCallback.onStateRestorationPolicyChanged(NestedAdapterWrapper.this);
        }
    };
    int mCachedItemCount;
    final Callback mCallback;
    private final StableIdStorage.StableIdLookup mStableIdLookup;
    private final ViewTypeStorage.ViewTypeLookup mViewTypeLookup;

    /* loaded from: classes.dex */
    interface Callback {
        void onChanged(NestedAdapterWrapper nestedAdapterWrapper);

        void onItemRangeChanged(NestedAdapterWrapper nestedAdapterWrapper, int r2, int r3);

        void onItemRangeChanged(NestedAdapterWrapper nestedAdapterWrapper, int r2, int r3, Object obj);

        void onItemRangeInserted(NestedAdapterWrapper nestedAdapterWrapper, int r2, int r3);

        void onItemRangeMoved(NestedAdapterWrapper nestedAdapterWrapper, int r2, int r3);

        void onItemRangeRemoved(NestedAdapterWrapper nestedAdapterWrapper, int r2, int r3);

        void onStateRestorationPolicyChanged(NestedAdapterWrapper nestedAdapterWrapper);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NestedAdapterWrapper(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, Callback callback, ViewTypeStorage viewTypeStorage, StableIdStorage.StableIdLookup stableIdLookup) {
        this.adapter = adapter;
        this.mCallback = callback;
        this.mViewTypeLookup = viewTypeStorage.createViewTypeWrapper(this);
        this.mStableIdLookup = stableIdLookup;
        this.mCachedItemCount = adapter.getItemCount();
        adapter.registerAdapterDataObserver(this.mAdapterObserver);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispose() {
        this.adapter.unregisterAdapterDataObserver(this.mAdapterObserver);
        this.mViewTypeLookup.dispose();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCachedItemCount() {
        return this.mCachedItemCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getItemViewType(int r3) {
        return this.mViewTypeLookup.localToGlobal(this.adapter.getItemViewType(r3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int r3) {
        return this.adapter.onCreateViewHolder(viewGroup, this.mViewTypeLookup.globalToLocal(r3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int r3) {
        this.adapter.bindViewHolder(viewHolder, r3);
    }

    public long getItemId(int r3) {
        return this.mStableIdLookup.localToGlobal(this.adapter.getItemId(r3));
    }
}
