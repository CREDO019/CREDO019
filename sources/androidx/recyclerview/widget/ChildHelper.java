package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ChildHelper {
    private static final boolean DEBUG = false;
    private static final String TAG = "ChildrenHelper";
    final Callback mCallback;
    final Bucket mBucket = new Bucket();
    final List<View> mHiddenViews = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Callback {
        void addView(View view, int r2);

        void attachViewToParent(View view, int r2, ViewGroup.LayoutParams layoutParams);

        void detachViewFromParent(int r1);

        View getChildAt(int r1);

        int getChildCount();

        RecyclerView.ViewHolder getChildViewHolder(View view);

        int indexOfChild(View view);

        void onEnteredHiddenState(View view);

        void onLeftHiddenState(View view);

        void removeAllViews();

        void removeViewAt(int r1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChildHelper(Callback callback) {
        this.mCallback = callback;
    }

    private void hideViewInternal(View view) {
        this.mHiddenViews.add(view);
        this.mCallback.onEnteredHiddenState(view);
    }

    private boolean unhideViewInternal(View view) {
        if (this.mHiddenViews.remove(view)) {
            this.mCallback.onLeftHiddenState(view);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addView(View view, boolean z) {
        addView(view, -1, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addView(View view, int r3, boolean z) {
        int offset;
        if (r3 < 0) {
            offset = this.mCallback.getChildCount();
        } else {
            offset = getOffset(r3);
        }
        this.mBucket.insert(offset, z);
        if (z) {
            hideViewInternal(view);
        }
        this.mCallback.addView(view, offset);
    }

    private int getOffset(int r5) {
        if (r5 < 0) {
            return -1;
        }
        int childCount = this.mCallback.getChildCount();
        int r2 = r5;
        while (r2 < childCount) {
            int countOnesBefore = r5 - (r2 - this.mBucket.countOnesBefore(r2));
            if (countOnesBefore == 0) {
                while (this.mBucket.get(r2)) {
                    r2++;
                }
                return r2;
            }
            r2 += countOnesBefore;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeView(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild < 0) {
            return;
        }
        if (this.mBucket.remove(indexOfChild)) {
            unhideViewInternal(view);
        }
        this.mCallback.removeViewAt(indexOfChild);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeViewAt(int r3) {
        int offset = getOffset(r3);
        View childAt = this.mCallback.getChildAt(offset);
        if (childAt == null) {
            return;
        }
        if (this.mBucket.remove(offset)) {
            unhideViewInternal(childAt);
        }
        this.mCallback.removeViewAt(offset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View getChildAt(int r2) {
        return this.mCallback.getChildAt(getOffset(r2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeAllViewsUnfiltered() {
        this.mBucket.reset();
        for (int size = this.mHiddenViews.size() - 1; size >= 0; size--) {
            this.mCallback.onLeftHiddenState(this.mHiddenViews.get(size));
            this.mHiddenViews.remove(size);
        }
        this.mCallback.removeAllViews();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View findHiddenNonRemovedView(int r6) {
        int size = this.mHiddenViews.size();
        for (int r1 = 0; r1 < size; r1++) {
            View view = this.mHiddenViews.get(r1);
            RecyclerView.ViewHolder childViewHolder = this.mCallback.getChildViewHolder(view);
            if (childViewHolder.getLayoutPosition() == r6 && !childViewHolder.isInvalid() && !childViewHolder.isRemoved()) {
                return view;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void attachViewToParent(View view, int r3, ViewGroup.LayoutParams layoutParams, boolean z) {
        int offset;
        if (r3 < 0) {
            offset = this.mCallback.getChildCount();
        } else {
            offset = getOffset(r3);
        }
        this.mBucket.insert(offset, z);
        if (z) {
            hideViewInternal(view);
        }
        this.mCallback.attachViewToParent(view, offset, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getChildCount() {
        return this.mCallback.getChildCount() - this.mHiddenViews.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getUnfilteredChildCount() {
        return this.mCallback.getChildCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View getUnfilteredChildAt(int r2) {
        return this.mCallback.getChildAt(r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void detachViewFromParent(int r2) {
        int offset = getOffset(r2);
        this.mBucket.remove(offset);
        this.mCallback.detachViewFromParent(offset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int indexOfChild(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild == -1 || this.mBucket.get(indexOfChild)) {
            return -1;
        }
        return indexOfChild - this.mBucket.countOnesBefore(indexOfChild);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isHidden(View view) {
        return this.mHiddenViews.contains(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void hide(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
        this.mBucket.set(indexOfChild);
        hideViewInternal(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void unhide(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        } else if (!this.mBucket.get(indexOfChild)) {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        } else {
            this.mBucket.clear(indexOfChild);
            unhideViewInternal(view);
        }
    }

    public String toString() {
        return this.mBucket.toString() + ", hidden list:" + this.mHiddenViews.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean removeViewIfHidden(View view) {
        int indexOfChild = this.mCallback.indexOfChild(view);
        if (indexOfChild == -1) {
            unhideViewInternal(view);
            return true;
        } else if (this.mBucket.get(indexOfChild)) {
            this.mBucket.remove(indexOfChild);
            unhideViewInternal(view);
            this.mCallback.removeViewAt(indexOfChild);
            return true;
        } else {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Bucket {
        static final int BITS_PER_WORD = 64;
        static final long LAST_BIT = Long.MIN_VALUE;
        long mData = 0;
        Bucket mNext;

        Bucket() {
        }

        void set(int r5) {
            if (r5 >= 64) {
                ensureNext();
                this.mNext.set(r5 - 64);
                return;
            }
            this.mData |= 1 << r5;
        }

        private void ensureNext() {
            if (this.mNext == null) {
                this.mNext = new Bucket();
            }
        }

        void clear(int r5) {
            if (r5 >= 64) {
                Bucket bucket = this.mNext;
                if (bucket != null) {
                    bucket.clear(r5 - 64);
                    return;
                }
                return;
            }
            this.mData &= ~(1 << r5);
        }

        boolean get(int r5) {
            if (r5 < 64) {
                return (this.mData & (1 << r5)) != 0;
            }
            ensureNext();
            return this.mNext.get(r5 - 64);
        }

        void reset() {
            this.mData = 0L;
            Bucket bucket = this.mNext;
            if (bucket != null) {
                bucket.reset();
            }
        }

        void insert(int r11, boolean z) {
            if (r11 >= 64) {
                ensureNext();
                this.mNext.insert(r11 - 64, z);
                return;
            }
            long j = this.mData;
            boolean z2 = (Long.MIN_VALUE & j) != 0;
            long j2 = (1 << r11) - 1;
            this.mData = ((j & (~j2)) << 1) | (j & j2);
            if (z) {
                set(r11);
            } else {
                clear(r11);
            }
            if (z2 || this.mNext != null) {
                ensureNext();
                this.mNext.insert(0, z2);
            }
        }

        boolean remove(int r13) {
            if (r13 >= 64) {
                ensureNext();
                return this.mNext.remove(r13 - 64);
            }
            long j = 1 << r13;
            long j2 = this.mData;
            boolean z = (j2 & j) != 0;
            long j3 = j2 & (~j);
            this.mData = j3;
            long j4 = j - 1;
            this.mData = (j3 & j4) | Long.rotateRight((~j4) & j3, 1);
            Bucket bucket = this.mNext;
            if (bucket != null) {
                if (bucket.get(0)) {
                    set(63);
                }
                this.mNext.remove(0);
            }
            return z;
        }

        int countOnesBefore(int r7) {
            Bucket bucket = this.mNext;
            if (bucket == null) {
                if (r7 >= 64) {
                    return Long.bitCount(this.mData);
                }
                return Long.bitCount(this.mData & ((1 << r7) - 1));
            } else if (r7 < 64) {
                return Long.bitCount(this.mData & ((1 << r7) - 1));
            } else {
                return bucket.countOnesBefore(r7 - 64) + Long.bitCount(this.mData);
            }
        }

        public String toString() {
            if (this.mNext == null) {
                return Long.toBinaryString(this.mData);
            }
            return this.mNext.toString() + "xx" + Long.toBinaryString(this.mData);
        }
    }
}
