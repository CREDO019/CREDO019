package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DiffUtil {
    private static final Comparator<Diagonal> DIAGONAL_COMPARATOR = new Comparator<Diagonal>() { // from class: androidx.recyclerview.widget.DiffUtil.1
        @Override // java.util.Comparator
        public int compare(Diagonal diagonal, Diagonal diagonal2) {
            return diagonal.f47x - diagonal2.f47x;
        }
    };

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract boolean areContentsTheSame(int r1, int r2);

        public abstract boolean areItemsTheSame(int r1, int r2);

        public Object getChangePayload(int r1, int r2) {
            return null;
        }

        public abstract int getNewListSize();

        public abstract int getOldListSize();
    }

    /* loaded from: classes.dex */
    public static abstract class ItemCallback<T> {
        public abstract boolean areContentsTheSame(T t, T t2);

        public abstract boolean areItemsTheSame(T t, T t2);

        public Object getChangePayload(T t, T t2) {
            return null;
        }
    }

    private DiffUtil() {
    }

    public static DiffResult calculateDiff(Callback callback) {
        return calculateDiff(callback, true);
    }

    public static DiffResult calculateDiff(Callback callback, boolean z) {
        int oldListSize = callback.getOldListSize();
        int newListSize = callback.getNewListSize();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new Range(0, oldListSize, 0, newListSize));
        int r0 = ((((oldListSize + newListSize) + 1) / 2) * 2) + 1;
        CenteredArray centeredArray = new CenteredArray(r0);
        CenteredArray centeredArray2 = new CenteredArray(r0);
        ArrayList arrayList3 = new ArrayList();
        while (!arrayList2.isEmpty()) {
            Range range = (Range) arrayList2.remove(arrayList2.size() - 1);
            Snake midPoint = midPoint(range, callback, centeredArray, centeredArray2);
            if (midPoint != null) {
                if (midPoint.diagonalSize() > 0) {
                    arrayList.add(midPoint.toDiagonal());
                }
                Range range2 = arrayList3.isEmpty() ? new Range() : (Range) arrayList3.remove(arrayList3.size() - 1);
                range2.oldListStart = range.oldListStart;
                range2.newListStart = range.newListStart;
                range2.oldListEnd = midPoint.startX;
                range2.newListEnd = midPoint.startY;
                arrayList2.add(range2);
                range.oldListEnd = range.oldListEnd;
                range.newListEnd = range.newListEnd;
                range.oldListStart = midPoint.endX;
                range.newListStart = midPoint.endY;
                arrayList2.add(range);
            } else {
                arrayList3.add(range);
            }
        }
        Collections.sort(arrayList, DIAGONAL_COMPARATOR);
        return new DiffResult(callback, arrayList, centeredArray.backingData(), centeredArray2.backingData(), z);
    }

    private static Snake midPoint(Range range, Callback callback, CenteredArray centeredArray, CenteredArray centeredArray2) {
        if (range.oldSize() >= 1 && range.newSize() >= 1) {
            int oldSize = ((range.oldSize() + range.newSize()) + 1) / 2;
            centeredArray.set(1, range.oldListStart);
            centeredArray2.set(1, range.oldListEnd);
            for (int r2 = 0; r2 < oldSize; r2++) {
                Snake forward = forward(range, callback, centeredArray, centeredArray2, r2);
                if (forward != null) {
                    return forward;
                }
                Snake backward = backward(range, callback, centeredArray, centeredArray2, r2);
                if (backward != null) {
                    return backward;
                }
            }
        }
        return null;
    }

    private static Snake forward(Range range, Callback callback, CenteredArray centeredArray, CenteredArray centeredArray2, int r15) {
        int r5;
        int r6;
        int r9;
        boolean z = Math.abs(range.oldSize() - range.newSize()) % 2 == 1;
        int oldSize = range.oldSize() - range.newSize();
        int r3 = -r15;
        for (int r4 = r3; r4 <= r15; r4 += 2) {
            if (r4 == r3 || (r4 != r15 && centeredArray.get(r4 + 1) > centeredArray.get(r4 - 1))) {
                r5 = centeredArray.get(r4 + 1);
                r6 = r5;
            } else {
                r5 = centeredArray.get(r4 - 1);
                r6 = r5 + 1;
            }
            int r7 = (range.newListStart + (r6 - range.oldListStart)) - r4;
            int r8 = (r15 == 0 || r6 != r5) ? r7 : r7 - 1;
            while (r6 < range.oldListEnd && r7 < range.newListEnd && callback.areItemsTheSame(r6, r7)) {
                r6++;
                r7++;
            }
            centeredArray.set(r4, r6);
            if (z && (r9 = oldSize - r4) >= r3 + 1 && r9 <= r15 - 1 && centeredArray2.get(r9) <= r6) {
                Snake snake = new Snake();
                snake.startX = r5;
                snake.startY = r8;
                snake.endX = r6;
                snake.endY = r7;
                snake.reverse = false;
                return snake;
            }
        }
        return null;
    }

    private static Snake backward(Range range, Callback callback, CenteredArray centeredArray, CenteredArray centeredArray2, int r15) {
        int r5;
        int r6;
        int r9;
        boolean z = (range.oldSize() - range.newSize()) % 2 == 0;
        int oldSize = range.oldSize() - range.newSize();
        int r3 = -r15;
        for (int r4 = r3; r4 <= r15; r4 += 2) {
            if (r4 == r3 || (r4 != r15 && centeredArray2.get(r4 + 1) < centeredArray2.get(r4 - 1))) {
                r5 = centeredArray2.get(r4 + 1);
                r6 = r5;
            } else {
                r5 = centeredArray2.get(r4 - 1);
                r6 = r5 - 1;
            }
            int r7 = range.newListEnd - ((range.oldListEnd - r6) - r4);
            int r8 = (r15 == 0 || r6 != r5) ? r7 : r7 + 1;
            while (r6 > range.oldListStart && r7 > range.newListStart && callback.areItemsTheSame(r6 - 1, r7 - 1)) {
                r6--;
                r7--;
            }
            centeredArray2.set(r4, r6);
            if (z && (r9 = oldSize - r4) >= r3 && r9 <= r15 && centeredArray.get(r9) >= r6) {
                Snake snake = new Snake();
                snake.startX = r6;
                snake.startY = r7;
                snake.endX = r5;
                snake.endY = r8;
                snake.reverse = true;
                return snake;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Diagonal {
        public final int size;

        /* renamed from: x */
        public final int f47x;

        /* renamed from: y */
        public final int f48y;

        Diagonal(int r1, int r2, int r3) {
            this.f47x = r1;
            this.f48y = r2;
            this.size = r3;
        }

        int endX() {
            return this.f47x + this.size;
        }

        int endY() {
            return this.f48y + this.size;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Snake {
        public int endX;
        public int endY;
        public boolean reverse;
        public int startX;
        public int startY;

        Snake() {
        }

        boolean hasAdditionOrRemoval() {
            return this.endY - this.startY != this.endX - this.startX;
        }

        boolean isAddition() {
            return this.endY - this.startY > this.endX - this.startX;
        }

        int diagonalSize() {
            return Math.min(this.endX - this.startX, this.endY - this.startY);
        }

        Diagonal toDiagonal() {
            if (hasAdditionOrRemoval()) {
                if (this.reverse) {
                    return new Diagonal(this.startX, this.startY, diagonalSize());
                }
                if (isAddition()) {
                    return new Diagonal(this.startX, this.startY + 1, diagonalSize());
                }
                return new Diagonal(this.startX + 1, this.startY, diagonalSize());
            }
            int r1 = this.startX;
            return new Diagonal(r1, this.startY, this.endX - r1);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Range {
        int newListEnd;
        int newListStart;
        int oldListEnd;
        int oldListStart;

        public Range() {
        }

        public Range(int r1, int r2, int r3, int r4) {
            this.oldListStart = r1;
            this.oldListEnd = r2;
            this.newListStart = r3;
            this.newListEnd = r4;
        }

        int oldSize() {
            return this.oldListEnd - this.oldListStart;
        }

        int newSize() {
            return this.newListEnd - this.newListStart;
        }
    }

    /* loaded from: classes.dex */
    public static class DiffResult {
        private static final int FLAG_CHANGED = 2;
        private static final int FLAG_MASK = 15;
        private static final int FLAG_MOVED = 12;
        private static final int FLAG_MOVED_CHANGED = 4;
        private static final int FLAG_MOVED_NOT_CHANGED = 8;
        private static final int FLAG_NOT_CHANGED = 1;
        private static final int FLAG_OFFSET = 4;
        public static final int NO_POSITION = -1;
        private final Callback mCallback;
        private final boolean mDetectMoves;
        private final List<Diagonal> mDiagonals;
        private final int[] mNewItemStatuses;
        private final int mNewListSize;
        private final int[] mOldItemStatuses;
        private final int mOldListSize;

        DiffResult(Callback callback, List<Diagonal> list, int[] r3, int[] r4, boolean z) {
            this.mDiagonals = list;
            this.mOldItemStatuses = r3;
            this.mNewItemStatuses = r4;
            Arrays.fill(r3, 0);
            Arrays.fill(r4, 0);
            this.mCallback = callback;
            this.mOldListSize = callback.getOldListSize();
            this.mNewListSize = callback.getNewListSize();
            this.mDetectMoves = z;
            addEdgeDiagonals();
            findMatchingItems();
        }

        private void addEdgeDiagonals() {
            Diagonal diagonal = this.mDiagonals.isEmpty() ? null : this.mDiagonals.get(0);
            if (diagonal == null || diagonal.f47x != 0 || diagonal.f48y != 0) {
                this.mDiagonals.add(0, new Diagonal(0, 0, 0));
            }
            this.mDiagonals.add(new Diagonal(this.mOldListSize, this.mNewListSize, 0));
        }

        private void findMatchingItems() {
            for (Diagonal diagonal : this.mDiagonals) {
                for (int r2 = 0; r2 < diagonal.size; r2++) {
                    int r3 = diagonal.f47x + r2;
                    int r4 = diagonal.f48y + r2;
                    int r5 = this.mCallback.areContentsTheSame(r3, r4) ? 1 : 2;
                    this.mOldItemStatuses[r3] = (r4 << 4) | r5;
                    this.mNewItemStatuses[r4] = (r3 << 4) | r5;
                }
            }
            if (this.mDetectMoves) {
                findMoveMatches();
            }
        }

        private void findMoveMatches() {
            int r1 = 0;
            for (Diagonal diagonal : this.mDiagonals) {
                while (r1 < diagonal.f47x) {
                    if (this.mOldItemStatuses[r1] == 0) {
                        findMatchingAddition(r1);
                    }
                    r1++;
                }
                r1 = diagonal.endX();
            }
        }

        private void findMatchingAddition(int r6) {
            int size = this.mDiagonals.size();
            int r2 = 0;
            for (int r1 = 0; r1 < size; r1++) {
                Diagonal diagonal = this.mDiagonals.get(r1);
                while (r2 < diagonal.f48y) {
                    if (this.mNewItemStatuses[r2] == 0 && this.mCallback.areItemsTheSame(r6, r2)) {
                        int r0 = this.mCallback.areContentsTheSame(r6, r2) ? 8 : 4;
                        this.mOldItemStatuses[r6] = (r2 << 4) | r0;
                        this.mNewItemStatuses[r2] = (r6 << 4) | r0;
                        return;
                    }
                    r2++;
                }
                r2 = diagonal.endY();
            }
        }

        public int convertOldPositionToNew(int r4) {
            if (r4 < 0 || r4 >= this.mOldListSize) {
                throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + r4 + ", old list size = " + this.mOldListSize);
            }
            int r42 = this.mOldItemStatuses[r4];
            if ((r42 & 15) == 0) {
                return -1;
            }
            return r42 >> 4;
        }

        public int convertNewPositionToOld(int r4) {
            if (r4 < 0 || r4 >= this.mNewListSize) {
                throw new IndexOutOfBoundsException("Index out of bounds - passed position = " + r4 + ", new list size = " + this.mNewListSize);
            }
            int r42 = this.mNewItemStatuses[r4];
            if ((r42 & 15) == 0) {
                return -1;
            }
            return r42 >> 4;
        }

        public void dispatchUpdatesTo(RecyclerView.Adapter adapter) {
            dispatchUpdatesTo(new AdapterListUpdateCallback(adapter));
        }

        public void dispatchUpdatesTo(ListUpdateCallback listUpdateCallback) {
            BatchingListUpdateCallback batchingListUpdateCallback;
            int r9;
            if (listUpdateCallback instanceof BatchingListUpdateCallback) {
                batchingListUpdateCallback = (BatchingListUpdateCallback) listUpdateCallback;
            } else {
                batchingListUpdateCallback = new BatchingListUpdateCallback(listUpdateCallback);
            }
            int r0 = this.mOldListSize;
            ArrayDeque arrayDeque = new ArrayDeque();
            int r2 = this.mOldListSize;
            int r3 = this.mNewListSize;
            for (int size = this.mDiagonals.size() - 1; size >= 0; size--) {
                Diagonal diagonal = this.mDiagonals.get(size);
                int endX = diagonal.endX();
                int endY = diagonal.endY();
                while (true) {
                    if (r2 <= endX) {
                        break;
                    }
                    r2--;
                    int r10 = this.mOldItemStatuses[r2];
                    if ((r10 & 12) != 0) {
                        int r11 = r10 >> 4;
                        PostponedUpdate postponedUpdate = getPostponedUpdate(arrayDeque, r11, false);
                        if (postponedUpdate != null) {
                            int r92 = (r0 - postponedUpdate.currentPos) - 1;
                            batchingListUpdateCallback.onMoved(r2, r92);
                            if ((r10 & 4) != 0) {
                                batchingListUpdateCallback.onChanged(r92, 1, this.mCallback.getChangePayload(r2, r11));
                            }
                        } else {
                            arrayDeque.add(new PostponedUpdate(r2, (r0 - r2) - 1, true));
                        }
                    } else {
                        batchingListUpdateCallback.onRemoved(r2, 1);
                        r0--;
                    }
                }
                while (r3 > endY) {
                    r3--;
                    int r7 = this.mNewItemStatuses[r3];
                    if ((r7 & 12) != 0) {
                        int r102 = r7 >> 4;
                        PostponedUpdate postponedUpdate2 = getPostponedUpdate(arrayDeque, r102, true);
                        if (postponedUpdate2 == null) {
                            arrayDeque.add(new PostponedUpdate(r3, r0 - r2, false));
                        } else {
                            batchingListUpdateCallback.onMoved((r0 - postponedUpdate2.currentPos) - 1, r2);
                            if ((r7 & 4) != 0) {
                                batchingListUpdateCallback.onChanged(r2, 1, this.mCallback.getChangePayload(r102, r3));
                            }
                        }
                    } else {
                        batchingListUpdateCallback.onInserted(r2, 1);
                        r0++;
                    }
                }
                int r22 = diagonal.f47x;
                int r32 = diagonal.f48y;
                for (r9 = 0; r9 < diagonal.size; r9++) {
                    if ((this.mOldItemStatuses[r22] & 15) == 2) {
                        batchingListUpdateCallback.onChanged(r22, 1, this.mCallback.getChangePayload(r22, r32));
                    }
                    r22++;
                    r32++;
                }
                r2 = diagonal.f47x;
                r3 = diagonal.f48y;
            }
            batchingListUpdateCallback.dispatchLastEvent();
        }

        private static PostponedUpdate getPostponedUpdate(Collection<PostponedUpdate> collection, int r3, boolean z) {
            PostponedUpdate postponedUpdate;
            Iterator<PostponedUpdate> it = collection.iterator();
            while (true) {
                if (!it.hasNext()) {
                    postponedUpdate = null;
                    break;
                }
                postponedUpdate = it.next();
                if (postponedUpdate.posInOwnerList == r3 && postponedUpdate.removal == z) {
                    it.remove();
                    break;
                }
            }
            while (it.hasNext()) {
                PostponedUpdate next = it.next();
                if (z) {
                    next.currentPos--;
                } else {
                    next.currentPos++;
                }
            }
            return postponedUpdate;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class PostponedUpdate {
        int currentPos;
        int posInOwnerList;
        boolean removal;

        PostponedUpdate(int r1, int r2, boolean z) {
            this.posInOwnerList = r1;
            this.currentPos = r2;
            this.removal = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class CenteredArray {
        private final int[] mData;
        private final int mMid;

        CenteredArray(int r1) {
            int[] r12 = new int[r1];
            this.mData = r12;
            this.mMid = r12.length / 2;
        }

        int get(int r3) {
            return this.mData[r3 + this.mMid];
        }

        int[] backingData() {
            return this.mData;
        }

        void set(int r3, int r4) {
            this.mData[r3 + this.mMid] = r4;
        }

        public void fill(int r2) {
            Arrays.fill(this.mData, r2);
        }
    }
}
