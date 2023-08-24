package androidx.recyclerview.widget;

import androidx.core.util.Pools;
import androidx.recyclerview.widget.OpReorderer;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class AdapterHelper implements OpReorderer.Callback {
    private static final boolean DEBUG = false;
    static final int POSITION_TYPE_INVISIBLE = 0;
    static final int POSITION_TYPE_NEW_OR_LAID_OUT = 1;
    private static final String TAG = "AHT";
    final Callback mCallback;
    final boolean mDisableRecycler;
    private int mExistingUpdateTypes;
    Runnable mOnItemProcessedCallback;
    final OpReorderer mOpReorderer;
    final ArrayList<UpdateOp> mPendingUpdates;
    final ArrayList<UpdateOp> mPostponedList;
    private Pools.Pool<UpdateOp> mUpdateOpPool;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Callback {
        RecyclerView.ViewHolder findViewHolder(int r1);

        void markViewHoldersUpdated(int r1, int r2, Object obj);

        void offsetPositionsForAdd(int r1, int r2);

        void offsetPositionsForMove(int r1, int r2);

        void offsetPositionsForRemovingInvisible(int r1, int r2);

        void offsetPositionsForRemovingLaidOutOrNewView(int r1, int r2);

        void onDispatchFirstPass(UpdateOp updateOp);

        void onDispatchSecondPass(UpdateOp updateOp);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterHelper(Callback callback) {
        this(callback, false);
    }

    AdapterHelper(Callback callback, boolean z) {
        this.mUpdateOpPool = new Pools.SimplePool(30);
        this.mPendingUpdates = new ArrayList<>();
        this.mPostponedList = new ArrayList<>();
        this.mExistingUpdateTypes = 0;
        this.mCallback = callback;
        this.mDisableRecycler = z;
        this.mOpReorderer = new OpReorderer(this);
    }

    AdapterHelper addUpdateOp(UpdateOp... updateOpArr) {
        Collections.addAll(this.mPendingUpdates, updateOpArr);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reset() {
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void preProcess() {
        this.mOpReorderer.reorderOps(this.mPendingUpdates);
        int size = this.mPendingUpdates.size();
        for (int r1 = 0; r1 < size; r1++) {
            UpdateOp updateOp = this.mPendingUpdates.get(r1);
            int r3 = updateOp.cmd;
            if (r3 == 1) {
                applyAdd(updateOp);
            } else if (r3 == 2) {
                applyRemove(updateOp);
            } else if (r3 == 4) {
                applyUpdate(updateOp);
            } else if (r3 == 8) {
                applyMove(updateOp);
            }
            Runnable runnable = this.mOnItemProcessedCallback;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.mPendingUpdates.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void consumePostponedUpdates() {
        int size = this.mPostponedList.size();
        for (int r2 = 0; r2 < size; r2++) {
            this.mCallback.onDispatchSecondPass(this.mPostponedList.get(r2));
        }
        recycleUpdateOpsAndClearList(this.mPostponedList);
        this.mExistingUpdateTypes = 0;
    }

    private void applyMove(UpdateOp updateOp) {
        postponeAndUpdateViewHolders(updateOp);
    }

    private void applyRemove(UpdateOp updateOp) {
        boolean z;
        char c;
        int r0 = updateOp.positionStart;
        int r1 = updateOp.positionStart + updateOp.itemCount;
        int r2 = updateOp.positionStart;
        char c2 = 65535;
        int r5 = 0;
        while (r2 < r1) {
            if (this.mCallback.findViewHolder(r2) != null || canFindInPreLayout(r2)) {
                if (c2 == 0) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(2, r0, r5, null));
                    z = true;
                } else {
                    z = false;
                }
                c = 1;
            } else {
                if (c2 == 1) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(2, r0, r5, null));
                    z = true;
                } else {
                    z = false;
                }
                c = 0;
            }
            if (z) {
                r2 -= r5;
                r1 -= r5;
                r5 = 1;
            } else {
                r5++;
            }
            r2++;
            c2 = c;
        }
        if (r5 != updateOp.itemCount) {
            recycleUpdateOp(updateOp);
            updateOp = obtainUpdateOp(2, r0, r5, null);
        }
        if (c2 == 0) {
            dispatchAndUpdateViewHolders(updateOp);
        } else {
            postponeAndUpdateViewHolders(updateOp);
        }
    }

    private void applyUpdate(UpdateOp updateOp) {
        int r0 = updateOp.positionStart;
        int r1 = updateOp.positionStart + updateOp.itemCount;
        char c = 65535;
        int r5 = 0;
        for (int r2 = updateOp.positionStart; r2 < r1; r2++) {
            if (this.mCallback.findViewHolder(r2) != null || canFindInPreLayout(r2)) {
                if (c == 0) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(4, r0, r5, updateOp.payload));
                    r0 = r2;
                    r5 = 0;
                }
                c = 1;
            } else {
                if (c == 1) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(4, r0, r5, updateOp.payload));
                    r0 = r2;
                    r5 = 0;
                }
                c = 0;
            }
            r5++;
        }
        if (r5 != updateOp.itemCount) {
            Object obj = updateOp.payload;
            recycleUpdateOp(updateOp);
            updateOp = obtainUpdateOp(4, r0, r5, obj);
        }
        if (c == 0) {
            dispatchAndUpdateViewHolders(updateOp);
        } else {
            postponeAndUpdateViewHolders(updateOp);
        }
    }

    private void dispatchAndUpdateViewHolders(UpdateOp updateOp) {
        int r3;
        if (updateOp.cmd == 1 || updateOp.cmd == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int updatePositionWithPostponed = updatePositionWithPostponed(updateOp.positionStart, updateOp.cmd);
        int r2 = updateOp.positionStart;
        int r32 = updateOp.cmd;
        if (r32 == 2) {
            r3 = 0;
        } else if (r32 != 4) {
            throw new IllegalArgumentException("op should be remove or update." + updateOp);
        } else {
            r3 = 1;
        }
        int r8 = 1;
        for (int r7 = 1; r7 < updateOp.itemCount; r7++) {
            int updatePositionWithPostponed2 = updatePositionWithPostponed(updateOp.positionStart + (r3 * r7), updateOp.cmd);
            int r10 = updateOp.cmd;
            if (r10 == 2 ? updatePositionWithPostponed2 == updatePositionWithPostponed : r10 == 4 && updatePositionWithPostponed2 == updatePositionWithPostponed + 1) {
                r8++;
            } else {
                UpdateOp obtainUpdateOp = obtainUpdateOp(updateOp.cmd, updatePositionWithPostponed, r8, updateOp.payload);
                dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp, r2);
                recycleUpdateOp(obtainUpdateOp);
                if (updateOp.cmd == 4) {
                    r2 += r8;
                }
                updatePositionWithPostponed = updatePositionWithPostponed2;
                r8 = 1;
            }
        }
        Object obj = updateOp.payload;
        recycleUpdateOp(updateOp);
        if (r8 > 0) {
            UpdateOp obtainUpdateOp2 = obtainUpdateOp(updateOp.cmd, updatePositionWithPostponed, r8, obj);
            dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp2, r2);
            recycleUpdateOp(obtainUpdateOp2);
        }
    }

    void dispatchFirstPassAndUpdateViewHolders(UpdateOp updateOp, int r4) {
        this.mCallback.onDispatchFirstPass(updateOp);
        int r0 = updateOp.cmd;
        if (r0 == 2) {
            this.mCallback.offsetPositionsForRemovingInvisible(r4, updateOp.itemCount);
        } else if (r0 == 4) {
            this.mCallback.markViewHoldersUpdated(r4, updateOp.itemCount, updateOp.payload);
        } else {
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    private int updatePositionWithPostponed(int r7, int r8) {
        int r2;
        int r4;
        for (int size = this.mPostponedList.size() - 1; size >= 0; size--) {
            UpdateOp updateOp = this.mPostponedList.get(size);
            if (updateOp.cmd == 8) {
                if (updateOp.positionStart < updateOp.itemCount) {
                    r2 = updateOp.positionStart;
                    r4 = updateOp.itemCount;
                } else {
                    r2 = updateOp.itemCount;
                    r4 = updateOp.positionStart;
                }
                if (r7 >= r2 && r7 <= r4) {
                    if (r2 == updateOp.positionStart) {
                        if (r8 == 1) {
                            updateOp.itemCount++;
                        } else if (r8 == 2) {
                            updateOp.itemCount--;
                        }
                        r7++;
                    } else {
                        if (r8 == 1) {
                            updateOp.positionStart++;
                        } else if (r8 == 2) {
                            updateOp.positionStart--;
                        }
                        r7--;
                    }
                } else if (r7 < updateOp.positionStart) {
                    if (r8 == 1) {
                        updateOp.positionStart++;
                        updateOp.itemCount++;
                    } else if (r8 == 2) {
                        updateOp.positionStart--;
                        updateOp.itemCount--;
                    }
                }
            } else if (updateOp.positionStart <= r7) {
                if (updateOp.cmd == 1) {
                    r7 -= updateOp.itemCount;
                } else if (updateOp.cmd == 2) {
                    r7 += updateOp.itemCount;
                }
            } else if (r8 == 1) {
                updateOp.positionStart++;
            } else if (r8 == 2) {
                updateOp.positionStart--;
            }
        }
        for (int size2 = this.mPostponedList.size() - 1; size2 >= 0; size2--) {
            UpdateOp updateOp2 = this.mPostponedList.get(size2);
            if (updateOp2.cmd == 8) {
                if (updateOp2.itemCount == updateOp2.positionStart || updateOp2.itemCount < 0) {
                    this.mPostponedList.remove(size2);
                    recycleUpdateOp(updateOp2);
                }
            } else if (updateOp2.itemCount <= 0) {
                this.mPostponedList.remove(size2);
                recycleUpdateOp(updateOp2);
            }
        }
        return r7;
    }

    private boolean canFindInPreLayout(int r8) {
        int size = this.mPostponedList.size();
        for (int r2 = 0; r2 < size; r2++) {
            UpdateOp updateOp = this.mPostponedList.get(r2);
            if (updateOp.cmd == 8) {
                if (findPositionOffset(updateOp.itemCount, r2 + 1) == r8) {
                    return true;
                }
            } else if (updateOp.cmd == 1) {
                int r4 = updateOp.positionStart + updateOp.itemCount;
                for (int r3 = updateOp.positionStart; r3 < r4; r3++) {
                    if (findPositionOffset(r3, r2 + 1) == r8) {
                        return true;
                    }
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    private void applyAdd(UpdateOp updateOp) {
        postponeAndUpdateViewHolders(updateOp);
    }

    private void postponeAndUpdateViewHolders(UpdateOp updateOp) {
        this.mPostponedList.add(updateOp);
        int r0 = updateOp.cmd;
        if (r0 == 1) {
            this.mCallback.offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
        } else if (r0 == 2) {
            this.mCallback.offsetPositionsForRemovingLaidOutOrNewView(updateOp.positionStart, updateOp.itemCount);
        } else if (r0 == 4) {
            this.mCallback.markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
        } else if (r0 == 8) {
            this.mCallback.offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + updateOp);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasPendingUpdates() {
        return this.mPendingUpdates.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasAnyUpdateTypes(int r2) {
        return (r2 & this.mExistingUpdateTypes) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int findPositionOffset(int r2) {
        return findPositionOffset(r2, 0);
    }

    int findPositionOffset(int r5, int r6) {
        int size = this.mPostponedList.size();
        while (r6 < size) {
            UpdateOp updateOp = this.mPostponedList.get(r6);
            if (updateOp.cmd == 8) {
                if (updateOp.positionStart == r5) {
                    r5 = updateOp.itemCount;
                } else {
                    if (updateOp.positionStart < r5) {
                        r5--;
                    }
                    if (updateOp.itemCount <= r5) {
                        r5++;
                    }
                }
            } else if (updateOp.positionStart > r5) {
                continue;
            } else if (updateOp.cmd == 2) {
                if (r5 < updateOp.positionStart + updateOp.itemCount) {
                    return -1;
                }
                r5 -= updateOp.itemCount;
            } else if (updateOp.cmd == 1) {
                r5 += updateOp.itemCount;
            }
            r6++;
        }
        return r5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onItemRangeChanged(int r5, int r6, Object obj) {
        if (r6 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(4, r5, r6, obj));
        this.mExistingUpdateTypes |= 4;
        return this.mPendingUpdates.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onItemRangeInserted(int r5, int r6) {
        if (r6 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(1, r5, r6, null));
        this.mExistingUpdateTypes |= 1;
        return this.mPendingUpdates.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onItemRangeRemoved(int r6, int r7) {
        if (r7 < 1) {
            return false;
        }
        this.mPendingUpdates.add(obtainUpdateOp(2, r6, r7, null));
        this.mExistingUpdateTypes |= 2;
        return this.mPendingUpdates.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean onItemRangeMoved(int r5, int r6, int r7) {
        if (r5 == r6) {
            return false;
        }
        if (r7 != 1) {
            throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
        }
        this.mPendingUpdates.add(obtainUpdateOp(8, r5, r6, null));
        this.mExistingUpdateTypes |= 8;
        return this.mPendingUpdates.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void consumeUpdatesInOnePass() {
        consumePostponedUpdates();
        int size = this.mPendingUpdates.size();
        for (int r2 = 0; r2 < size; r2++) {
            UpdateOp updateOp = this.mPendingUpdates.get(r2);
            int r4 = updateOp.cmd;
            if (r4 == 1) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.offsetPositionsForAdd(updateOp.positionStart, updateOp.itemCount);
            } else if (r4 == 2) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.offsetPositionsForRemovingInvisible(updateOp.positionStart, updateOp.itemCount);
            } else if (r4 == 4) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.markViewHoldersUpdated(updateOp.positionStart, updateOp.itemCount, updateOp.payload);
            } else if (r4 == 8) {
                this.mCallback.onDispatchSecondPass(updateOp);
                this.mCallback.offsetPositionsForMove(updateOp.positionStart, updateOp.itemCount);
            }
            Runnable runnable = this.mOnItemProcessedCallback;
            if (runnable != null) {
                runnable.run();
            }
        }
        recycleUpdateOpsAndClearList(this.mPendingUpdates);
        this.mExistingUpdateTypes = 0;
    }

    public int applyPendingUpdatesToPosition(int r6) {
        int size = this.mPendingUpdates.size();
        for (int r1 = 0; r1 < size; r1++) {
            UpdateOp updateOp = this.mPendingUpdates.get(r1);
            int r3 = updateOp.cmd;
            if (r3 != 1) {
                if (r3 != 2) {
                    if (r3 == 8) {
                        if (updateOp.positionStart == r6) {
                            r6 = updateOp.itemCount;
                        } else {
                            if (updateOp.positionStart < r6) {
                                r6--;
                            }
                            if (updateOp.itemCount <= r6) {
                                r6++;
                            }
                        }
                    }
                } else if (updateOp.positionStart > r6) {
                    continue;
                } else if (updateOp.positionStart + updateOp.itemCount > r6) {
                    return -1;
                } else {
                    r6 -= updateOp.itemCount;
                }
            } else if (updateOp.positionStart <= r6) {
                r6 += updateOp.itemCount;
            }
        }
        return r6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasUpdates() {
        return (this.mPostponedList.isEmpty() || this.mPendingUpdates.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class UpdateOp {
        static final int ADD = 1;
        static final int MOVE = 8;
        static final int POOL_SIZE = 30;
        static final int REMOVE = 2;
        static final int UPDATE = 4;
        int cmd;
        int itemCount;
        Object payload;
        int positionStart;

        UpdateOp(int r1, int r2, int r3, Object obj) {
            this.cmd = r1;
            this.positionStart = r2;
            this.itemCount = r3;
            this.payload = obj;
        }

        String cmdToString() {
            int r0 = this.cmd;
            return r0 != 1 ? r0 != 2 ? r0 != 4 ? r0 != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + cmdToString() + ",s:" + this.positionStart + "c:" + this.itemCount + ",p:" + this.payload + "]";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof UpdateOp) {
                UpdateOp updateOp = (UpdateOp) obj;
                int r1 = this.cmd;
                if (r1 != updateOp.cmd) {
                    return false;
                }
                if (r1 == 8 && Math.abs(this.itemCount - this.positionStart) == 1 && this.itemCount == updateOp.positionStart && this.positionStart == updateOp.itemCount) {
                    return true;
                }
                if (this.itemCount == updateOp.itemCount && this.positionStart == updateOp.positionStart) {
                    Object obj2 = this.payload;
                    if (obj2 != null) {
                        if (!obj2.equals(updateOp.payload)) {
                            return false;
                        }
                    } else if (updateOp.payload != null) {
                        return false;
                    }
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return (((this.cmd * 31) + this.positionStart) * 31) + this.itemCount;
        }
    }

    @Override // androidx.recyclerview.widget.OpReorderer.Callback
    public UpdateOp obtainUpdateOp(int r2, int r3, int r4, Object obj) {
        UpdateOp acquire = this.mUpdateOpPool.acquire();
        if (acquire == null) {
            return new UpdateOp(r2, r3, r4, obj);
        }
        acquire.cmd = r2;
        acquire.positionStart = r3;
        acquire.itemCount = r4;
        acquire.payload = obj;
        return acquire;
    }

    @Override // androidx.recyclerview.widget.OpReorderer.Callback
    public void recycleUpdateOp(UpdateOp updateOp) {
        if (this.mDisableRecycler) {
            return;
        }
        updateOp.payload = null;
        this.mUpdateOpPool.release(updateOp);
    }

    void recycleUpdateOpsAndClearList(List<UpdateOp> list) {
        int size = list.size();
        for (int r1 = 0; r1 < size; r1++) {
            recycleUpdateOp(list.get(r1));
        }
        list.clear();
    }
}
