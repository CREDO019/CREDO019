package androidx.recyclerview.widget;

/* loaded from: classes.dex */
public class BatchingListUpdateCallback implements ListUpdateCallback {
    private static final int TYPE_ADD = 1;
    private static final int TYPE_CHANGE = 3;
    private static final int TYPE_NONE = 0;
    private static final int TYPE_REMOVE = 2;
    final ListUpdateCallback mWrapped;
    int mLastEventType = 0;
    int mLastEventPosition = -1;
    int mLastEventCount = -1;
    Object mLastEventPayload = null;

    public BatchingListUpdateCallback(ListUpdateCallback listUpdateCallback) {
        this.mWrapped = listUpdateCallback;
    }

    public void dispatchLastEvent() {
        int r0 = this.mLastEventType;
        if (r0 == 0) {
            return;
        }
        if (r0 == 1) {
            this.mWrapped.onInserted(this.mLastEventPosition, this.mLastEventCount);
        } else if (r0 == 2) {
            this.mWrapped.onRemoved(this.mLastEventPosition, this.mLastEventCount);
        } else if (r0 == 3) {
            this.mWrapped.onChanged(this.mLastEventPosition, this.mLastEventCount, this.mLastEventPayload);
        }
        this.mLastEventPayload = null;
        this.mLastEventType = 0;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onInserted(int r5, int r6) {
        int r0;
        if (this.mLastEventType == 1 && r5 >= (r0 = this.mLastEventPosition)) {
            int r2 = this.mLastEventCount;
            if (r5 <= r0 + r2) {
                this.mLastEventCount = r2 + r6;
                this.mLastEventPosition = Math.min(r5, r0);
                return;
            }
        }
        dispatchLastEvent();
        this.mLastEventPosition = r5;
        this.mLastEventCount = r6;
        this.mLastEventType = 1;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onRemoved(int r4, int r5) {
        int r0;
        if (this.mLastEventType == 2 && (r0 = this.mLastEventPosition) >= r4 && r0 <= r4 + r5) {
            this.mLastEventCount += r5;
            this.mLastEventPosition = r4;
            return;
        }
        dispatchLastEvent();
        this.mLastEventPosition = r4;
        this.mLastEventCount = r5;
        this.mLastEventType = 2;
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onMoved(int r2, int r3) {
        dispatchLastEvent();
        this.mWrapped.onMoved(r2, r3);
    }

    @Override // androidx.recyclerview.widget.ListUpdateCallback
    public void onChanged(int r6, int r7, Object obj) {
        int r3;
        if (this.mLastEventType == 3) {
            int r0 = this.mLastEventPosition;
            int r2 = this.mLastEventCount;
            if (r6 <= r0 + r2 && (r3 = r6 + r7) >= r0 && this.mLastEventPayload == obj) {
                this.mLastEventPosition = Math.min(r6, r0);
                this.mLastEventCount = Math.max(r2 + r0, r3) - this.mLastEventPosition;
                return;
            }
        }
        dispatchLastEvent();
        this.mLastEventPosition = r6;
        this.mLastEventCount = r7;
        this.mLastEventPayload = obj;
        this.mLastEventType = 3;
    }
}
