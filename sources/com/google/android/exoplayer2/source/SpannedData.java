package com.google.android.exoplayer2.source;

import android.util.SparseArray;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Consumer;

/* loaded from: classes2.dex */
final class SpannedData<V> {
    private int memoizedReadIndex;
    private final Consumer<V> removeCallback;
    private final SparseArray<V> spans;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$new$0(Object obj) {
    }

    public SpannedData() {
        this(new Consumer() { // from class: com.google.android.exoplayer2.source.SpannedData$$ExternalSyntheticLambda0
            @Override // com.google.android.exoplayer2.util.Consumer
            public final void accept(Object obj) {
                SpannedData.lambda$new$0(obj);
            }
        });
    }

    public SpannedData(Consumer<V> consumer) {
        this.spans = new SparseArray<>();
        this.removeCallback = consumer;
        this.memoizedReadIndex = -1;
    }

    public V get(int r3) {
        if (this.memoizedReadIndex == -1) {
            this.memoizedReadIndex = 0;
        }
        while (true) {
            int r0 = this.memoizedReadIndex;
            if (r0 <= 0 || r3 >= this.spans.keyAt(r0)) {
                break;
            }
            this.memoizedReadIndex--;
        }
        while (this.memoizedReadIndex < this.spans.size() - 1 && r3 >= this.spans.keyAt(this.memoizedReadIndex + 1)) {
            this.memoizedReadIndex++;
        }
        return this.spans.valueAt(this.memoizedReadIndex);
    }

    public void appendSpan(int r5, V v) {
        if (this.memoizedReadIndex == -1) {
            Assertions.checkState(this.spans.size() == 0);
            this.memoizedReadIndex = 0;
        }
        if (this.spans.size() > 0) {
            SparseArray<V> sparseArray = this.spans;
            int keyAt = sparseArray.keyAt(sparseArray.size() - 1);
            Assertions.checkArgument(r5 >= keyAt);
            if (keyAt == r5) {
                SparseArray<V> sparseArray2 = this.spans;
                this.removeCallback.accept(sparseArray2.valueAt(sparseArray2.size() - 1));
            }
        }
        this.spans.append(r5, v);
    }

    public V getEndValue() {
        SparseArray<V> sparseArray = this.spans;
        return sparseArray.valueAt(sparseArray.size() - 1);
    }

    public void discardTo(int r5) {
        int r0 = 0;
        while (r0 < this.spans.size() - 1) {
            int r2 = r0 + 1;
            if (r5 < this.spans.keyAt(r2)) {
                return;
            }
            this.removeCallback.accept(this.spans.valueAt(r0));
            this.spans.removeAt(r0);
            int r02 = this.memoizedReadIndex;
            if (r02 > 0) {
                this.memoizedReadIndex = r02 - 1;
            }
            r0 = r2;
        }
    }

    public void discardFrom(int r4) {
        for (int size = this.spans.size() - 1; size >= 0 && r4 < this.spans.keyAt(size); size--) {
            this.removeCallback.accept(this.spans.valueAt(size));
            this.spans.removeAt(size);
        }
        this.memoizedReadIndex = this.spans.size() > 0 ? Math.min(this.memoizedReadIndex, this.spans.size() - 1) : -1;
    }

    public void clear() {
        for (int r0 = 0; r0 < this.spans.size(); r0++) {
            this.removeCallback.accept(this.spans.valueAt(r0));
        }
        this.memoizedReadIndex = -1;
        this.spans.clear();
    }

    public boolean isEmpty() {
        return this.spans.size() == 0;
    }
}
