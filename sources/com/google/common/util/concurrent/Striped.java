package com.google.common.util.concurrent;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.math.IntMath;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public abstract class Striped<L> {
    private static final int ALL_SET = -1;
    private static final int LARGE_LAZY_CUTOFF = 1024;
    private static final Supplier<ReadWriteLock> READ_WRITE_LOCK_SUPPLIER = new Supplier<ReadWriteLock>() { // from class: com.google.common.util.concurrent.Striped.5
        @Override // com.google.common.base.Supplier
        public ReadWriteLock get() {
            return new ReentrantReadWriteLock();
        }
    };
    private static final Supplier<ReadWriteLock> WEAK_SAFE_READ_WRITE_LOCK_SUPPLIER = new Supplier<ReadWriteLock>() { // from class: com.google.common.util.concurrent.Striped.6
        @Override // com.google.common.base.Supplier
        public ReadWriteLock get() {
            return new WeakSafeReadWriteLock();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static int smear(int r2) {
        int r22 = r2 ^ ((r2 >>> 20) ^ (r2 >>> 12));
        return (r22 >>> 4) ^ ((r22 >>> 7) ^ r22);
    }

    public abstract L get(Object obj);

    public abstract L getAt(int r1);

    abstract int indexFor(Object obj);

    public abstract int size();

    private Striped() {
    }

    public Iterable<L> bulkGet(Iterable<? extends Object> iterable) {
        ArrayList newArrayList = Lists.newArrayList(iterable);
        if (newArrayList.isEmpty()) {
            return ImmutableList.m409of();
        }
        int[] r0 = new int[newArrayList.size()];
        for (int r2 = 0; r2 < newArrayList.size(); r2++) {
            r0[r2] = indexFor(newArrayList.get(r2));
        }
        Arrays.sort(r0);
        int r22 = r0[0];
        newArrayList.set(0, getAt(r22));
        for (int r1 = 1; r1 < newArrayList.size(); r1++) {
            int r3 = r0[r1];
            if (r3 == r22) {
                newArrayList.set(r1, newArrayList.get(r1 - 1));
            } else {
                newArrayList.set(r1, getAt(r3));
                r22 = r3;
            }
        }
        return Collections.unmodifiableList(newArrayList);
    }

    static <L> Striped<L> custom(int r2, Supplier<L> supplier) {
        return new CompactStriped(r2, supplier);
    }

    public static Striped<Lock> lock(int r1) {
        return custom(r1, new Supplier<Lock>() { // from class: com.google.common.util.concurrent.Striped.1
            @Override // com.google.common.base.Supplier
            public Lock get() {
                return new PaddedLock();
            }
        });
    }

    public static Striped<Lock> lazyWeakLock(int r1) {
        return lazy(r1, new Supplier<Lock>() { // from class: com.google.common.util.concurrent.Striped.2
            @Override // com.google.common.base.Supplier
            public Lock get() {
                return new ReentrantLock(false);
            }
        });
    }

    private static <L> Striped<L> lazy(int r1, Supplier<L> supplier) {
        if (r1 < 1024) {
            return new SmallLazyStriped(r1, supplier);
        }
        return new LargeLazyStriped(r1, supplier);
    }

    public static Striped<Semaphore> semaphore(int r1, final int r2) {
        return custom(r1, new Supplier<Semaphore>() { // from class: com.google.common.util.concurrent.Striped.3
            @Override // com.google.common.base.Supplier
            public Semaphore get() {
                return new PaddedSemaphore(r2);
            }
        });
    }

    public static Striped<Semaphore> lazyWeakSemaphore(int r1, final int r2) {
        return lazy(r1, new Supplier<Semaphore>() { // from class: com.google.common.util.concurrent.Striped.4
            @Override // com.google.common.base.Supplier
            public Semaphore get() {
                return new Semaphore(r2, false);
            }
        });
    }

    public static Striped<ReadWriteLock> readWriteLock(int r1) {
        return custom(r1, READ_WRITE_LOCK_SUPPLIER);
    }

    public static Striped<ReadWriteLock> lazyWeakReadWriteLock(int r1) {
        return lazy(r1, WEAK_SAFE_READ_WRITE_LOCK_SUPPLIER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class WeakSafeReadWriteLock implements ReadWriteLock {
        private final ReadWriteLock delegate = new ReentrantReadWriteLock();

        WeakSafeReadWriteLock() {
        }

        @Override // java.util.concurrent.locks.ReadWriteLock
        public Lock readLock() {
            return new WeakSafeLock(this.delegate.readLock(), this);
        }

        @Override // java.util.concurrent.locks.ReadWriteLock
        public Lock writeLock() {
            return new WeakSafeLock(this.delegate.writeLock(), this);
        }
    }

    /* loaded from: classes3.dex */
    private static final class WeakSafeLock extends ForwardingLock {
        private final Lock delegate;
        private final WeakSafeReadWriteLock strongReference;

        WeakSafeLock(Lock lock, WeakSafeReadWriteLock weakSafeReadWriteLock) {
            this.delegate = lock;
            this.strongReference = weakSafeReadWriteLock;
        }

        @Override // com.google.common.util.concurrent.ForwardingLock
        Lock delegate() {
            return this.delegate;
        }

        @Override // com.google.common.util.concurrent.ForwardingLock, java.util.concurrent.locks.Lock
        public Condition newCondition() {
            return new WeakSafeCondition(this.delegate.newCondition(), this.strongReference);
        }
    }

    /* loaded from: classes3.dex */
    private static final class WeakSafeCondition extends ForwardingCondition {
        private final Condition delegate;
        private final WeakSafeReadWriteLock strongReference;

        WeakSafeCondition(Condition condition, WeakSafeReadWriteLock weakSafeReadWriteLock) {
            this.delegate = condition;
            this.strongReference = weakSafeReadWriteLock;
        }

        @Override // com.google.common.util.concurrent.ForwardingCondition
        Condition delegate() {
            return this.delegate;
        }
    }

    /* loaded from: classes3.dex */
    private static abstract class PowerOfTwoStriped<L> extends Striped<L> {
        final int mask;

        PowerOfTwoStriped(int r4) {
            super();
            Preconditions.checkArgument(r4 > 0, "Stripes must be positive");
            this.mask = r4 > 1073741824 ? -1 : Striped.ceilToPowerOfTwo(r4) - 1;
        }

        @Override // com.google.common.util.concurrent.Striped
        final int indexFor(Object obj) {
            return Striped.smear(obj.hashCode()) & this.mask;
        }

        @Override // com.google.common.util.concurrent.Striped
        public final L get(Object obj) {
            return getAt(indexFor(obj));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class CompactStriped<L> extends PowerOfTwoStriped<L> {
        private final Object[] array;

        private CompactStriped(int r4, Supplier<L> supplier) {
            super(r4);
            int r0 = 0;
            Preconditions.checkArgument(r4 <= 1073741824, "Stripes must be <= 2^30)");
            this.array = new Object[this.mask + 1];
            while (true) {
                Object[] objArr = this.array;
                if (r0 >= objArr.length) {
                    return;
                }
                objArr[r0] = supplier.get();
                r0++;
            }
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int r2) {
            return (L) this.array[r2];
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.array.length;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class SmallLazyStriped<L> extends PowerOfTwoStriped<L> {
        final AtomicReferenceArray<ArrayReference<? extends L>> locks;
        final ReferenceQueue<L> queue;
        final int size;
        final Supplier<L> supplier;

        SmallLazyStriped(int r2, Supplier<L> supplier) {
            super(r2);
            this.queue = new ReferenceQueue<>();
            int r22 = this.mask == -1 ? Integer.MAX_VALUE : this.mask + 1;
            this.size = r22;
            this.locks = new AtomicReferenceArray<>(r22);
            this.supplier = supplier;
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int r6) {
            L l;
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(r6, size());
            }
            ArrayReference<? extends L> arrayReference = this.locks.get(r6);
            L l2 = arrayReference == null ? null : (L) arrayReference.get();
            if (l2 != null) {
                return l2;
            }
            L l3 = this.supplier.get();
            ArrayReference<? extends L> arrayReference2 = new ArrayReference<>(l3, r6, this.queue);
            while (!this.locks.compareAndSet(r6, arrayReference, arrayReference2)) {
                arrayReference = this.locks.get(r6);
                if (arrayReference == null) {
                    l = null;
                    continue;
                } else {
                    l = (L) arrayReference.get();
                    continue;
                }
                if (l != null) {
                    return l;
                }
            }
            drainQueue();
            return l3;
        }

        private void drainQueue() {
            while (true) {
                Reference<? extends L> poll = this.queue.poll();
                if (poll == null) {
                    return;
                }
                ArrayReference<? extends L> arrayReference = (ArrayReference) poll;
                this.locks.compareAndSet(arrayReference.index, arrayReference, null);
            }
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.size;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes3.dex */
        public static final class ArrayReference<L> extends WeakReference<L> {
            final int index;

            ArrayReference(L l, int r2, ReferenceQueue<L> referenceQueue) {
                super(l, referenceQueue);
                this.index = r2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class LargeLazyStriped<L> extends PowerOfTwoStriped<L> {
        final ConcurrentMap<Integer, L> locks;
        final int size;
        final Supplier<L> supplier;

        LargeLazyStriped(int r2, Supplier<L> supplier) {
            super(r2);
            this.size = this.mask == -1 ? Integer.MAX_VALUE : this.mask + 1;
            this.supplier = supplier;
            this.locks = new MapMaker().weakValues().makeMap();
        }

        @Override // com.google.common.util.concurrent.Striped
        public L getAt(int r3) {
            if (this.size != Integer.MAX_VALUE) {
                Preconditions.checkElementIndex(r3, size());
            }
            L l = this.locks.get(Integer.valueOf(r3));
            if (l != null) {
                return l;
            }
            L l2 = this.supplier.get();
            return (L) MoreObjects.firstNonNull(this.locks.putIfAbsent(Integer.valueOf(r3), l2), l2);
        }

        @Override // com.google.common.util.concurrent.Striped
        public int size() {
            return this.size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int ceilToPowerOfTwo(int r1) {
        return 1 << IntMath.log2(r1, RoundingMode.CEILING);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class PaddedLock extends ReentrantLock {
        long unused1;
        long unused2;
        long unused3;

        PaddedLock() {
            super(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class PaddedSemaphore extends Semaphore {
        long unused1;
        long unused2;
        long unused3;

        PaddedSemaphore(int r2) {
            super(r2, false);
        }
    }
}
