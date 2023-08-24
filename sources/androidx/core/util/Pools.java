package androidx.core.util;

/* loaded from: classes.dex */
public final class Pools {

    /* loaded from: classes.dex */
    public interface Pool<T> {
        T acquire();

        boolean release(T t);
    }

    private Pools() {
    }

    /* loaded from: classes.dex */
    public static class SimplePool<T> implements Pool<T> {
        private final Object[] mPool;
        private int mPoolSize;

        public SimplePool(int r2) {
            if (r2 <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.mPool = new Object[r2];
        }

        @Override // androidx.core.util.Pools.Pool
        public T acquire() {
            int r0 = this.mPoolSize;
            if (r0 > 0) {
                int r2 = r0 - 1;
                Object[] objArr = this.mPool;
                T t = (T) objArr[r2];
                objArr[r2] = null;
                this.mPoolSize = r0 - 1;
                return t;
            }
            return null;
        }

        @Override // androidx.core.util.Pools.Pool
        public boolean release(T t) {
            if (isInPool(t)) {
                throw new IllegalStateException("Already in the pool!");
            }
            int r0 = this.mPoolSize;
            Object[] objArr = this.mPool;
            if (r0 < objArr.length) {
                objArr[r0] = t;
                this.mPoolSize = r0 + 1;
                return true;
            }
            return false;
        }

        private boolean isInPool(T t) {
            for (int r1 = 0; r1 < this.mPoolSize; r1++) {
                if (this.mPool[r1] == t) {
                    return true;
                }
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class SynchronizedPool<T> extends SimplePool<T> {
        private final Object mLock;

        public SynchronizedPool(int r1) {
            super(r1);
            this.mLock = new Object();
        }

        @Override // androidx.core.util.Pools.SimplePool, androidx.core.util.Pools.Pool
        public T acquire() {
            T t;
            synchronized (this.mLock) {
                t = (T) super.acquire();
            }
            return t;
        }

        @Override // androidx.core.util.Pools.SimplePool, androidx.core.util.Pools.Pool
        public boolean release(T t) {
            boolean release;
            synchronized (this.mLock) {
                release = super.release(t);
            }
            return release;
        }
    }
}
