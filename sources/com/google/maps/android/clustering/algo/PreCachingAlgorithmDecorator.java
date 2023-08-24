package com.google.maps.android.clustering.algo;

import androidx.collection.LruCache;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes3.dex */
public class PreCachingAlgorithmDecorator<T extends ClusterItem> implements Algorithm<T> {
    private final Algorithm<T> mAlgorithm;
    private final LruCache<Integer, Set<? extends Cluster<T>>> mCache = new LruCache<>(5);
    private final ReadWriteLock mCacheLock = new ReentrantReadWriteLock();

    public PreCachingAlgorithmDecorator(Algorithm<T> algorithm) {
        this.mAlgorithm = algorithm;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void addItem(T t) {
        this.mAlgorithm.addItem(t);
        clearCache();
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void addItems(Collection<T> collection) {
        this.mAlgorithm.addItems(collection);
        clearCache();
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void clearItems() {
        this.mAlgorithm.clearItems();
        clearCache();
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void removeItem(T t) {
        this.mAlgorithm.removeItem(t);
        clearCache();
    }

    private void clearCache() {
        this.mCache.evictAll();
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Set<? extends Cluster<T>> getClusters(double d) {
        int r4 = (int) d;
        Set<? extends Cluster<T>> clustersInternal = getClustersInternal(r4);
        int r1 = r4 + 1;
        if (this.mCache.get(Integer.valueOf(r1)) == null) {
            new Thread(new PrecacheRunnable(r1)).start();
        }
        int r42 = r4 - 1;
        if (this.mCache.get(Integer.valueOf(r42)) == null) {
            new Thread(new PrecacheRunnable(r42)).start();
        }
        return clustersInternal;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Collection<T> getItems() {
        return this.mAlgorithm.getItems();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Set<? extends Cluster<T>> getClustersInternal(int r4) {
        this.mCacheLock.readLock().lock();
        Set<? extends Cluster<T>> set = this.mCache.get(Integer.valueOf(r4));
        this.mCacheLock.readLock().unlock();
        if (set == null) {
            this.mCacheLock.writeLock().lock();
            set = this.mCache.get(Integer.valueOf(r4));
            if (set == null) {
                set = this.mAlgorithm.getClusters(r4);
                this.mCache.put(Integer.valueOf(r4), set);
            }
            this.mCacheLock.writeLock().unlock();
        }
        return set;
    }

    /* loaded from: classes3.dex */
    private class PrecacheRunnable implements Runnable {
        private final int mZoom;

        public PrecacheRunnable(int r2) {
            this.mZoom = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep((long) ((Math.random() * 500.0d) + 500.0d));
            } catch (InterruptedException unused) {
            }
            PreCachingAlgorithmDecorator.this.getClustersInternal(this.mZoom);
        }
    }
}
