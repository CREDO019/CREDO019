package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public class NonHierarchicalDistanceBasedAlgorithm<T extends ClusterItem> implements Algorithm<T> {
    public static final int MAX_DISTANCE_AT_ZOOM = 100;
    private static final SphericalMercatorProjection PROJECTION = new SphericalMercatorProjection(1.0d);
    private final Collection<QuadItem<T>> mItems = new ArrayList();
    private final PointQuadTree<QuadItem<T>> mQuadTree = new PointQuadTree<>(0.0d, 1.0d, 0.0d, 1.0d);

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void addItem(T t) {
        QuadItem<T> quadItem = new QuadItem<>(t);
        synchronized (this.mQuadTree) {
            this.mItems.add(quadItem);
            this.mQuadTree.add(quadItem);
        }
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void addItems(Collection<T> collection) {
        for (T t : collection) {
            addItem(t);
        }
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void clearItems() {
        synchronized (this.mQuadTree) {
            this.mItems.clear();
            this.mQuadTree.clear();
        }
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void removeItem(T t) {
        QuadItem<T> quadItem = new QuadItem<>(t);
        synchronized (this.mQuadTree) {
            this.mItems.remove(quadItem);
            this.mQuadTree.remove(quadItem);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Set<? extends Cluster<T>> getClusters(double d) {
        double pow = (100.0d / Math.pow(2.0d, (int) d)) / 256.0d;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        synchronized (this.mQuadTree) {
            for (QuadItem<T> quadItem : this.mItems) {
                if (!hashSet.contains(quadItem)) {
                    Collection<QuadItem<T>> search = this.mQuadTree.search(createBoundsFromSpan(quadItem.getPoint(), pow));
                    if (search.size() == 1) {
                        hashSet2.add(quadItem);
                        hashSet.add(quadItem);
                        hashMap.put(quadItem, Double.valueOf(0.0d));
                    } else {
                        StaticCluster staticCluster = new StaticCluster(((QuadItem) quadItem).mClusterItem.getPosition());
                        hashSet2.add(staticCluster);
                        for (QuadItem<T> quadItem2 : search) {
                            Double d2 = (Double) hashMap.get(quadItem2);
                            double d3 = pow;
                            double distanceSquared = distanceSquared(quadItem2.getPoint(), quadItem.getPoint());
                            if (d2 != null) {
                                if (d2.doubleValue() < distanceSquared) {
                                    pow = d3;
                                } else {
                                    ((StaticCluster) hashMap2.get(quadItem2)).remove(((QuadItem) quadItem2).mClusterItem);
                                }
                            }
                            hashMap.put(quadItem2, Double.valueOf(distanceSquared));
                            staticCluster.add(((QuadItem) quadItem2).mClusterItem);
                            hashMap2.put(quadItem2, staticCluster);
                            pow = d3;
                        }
                        hashSet.addAll(search);
                        pow = pow;
                    }
                }
            }
        }
        return hashSet2;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Collection<T> getItems() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mQuadTree) {
            for (QuadItem<T> quadItem : this.mItems) {
                arrayList.add(((QuadItem) quadItem).mClusterItem);
            }
        }
        return arrayList;
    }

    private double distanceSquared(Point point, Point point2) {
        return ((point.f1212x - point2.f1212x) * (point.f1212x - point2.f1212x)) + ((point.f1213y - point2.f1213y) * (point.f1213y - point2.f1213y));
    }

    private Bounds createBoundsFromSpan(Point point, double d) {
        double d2 = d / 2.0d;
        return new Bounds(point.f1212x - d2, point.f1212x + d2, point.f1213y - d2, point.f1213y + d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class QuadItem<T extends ClusterItem> implements PointQuadTree.Item, Cluster<T> {
        private final T mClusterItem;
        private final Point mPoint;
        private final LatLng mPosition;
        private Set<T> singletonSet;

        @Override // com.google.maps.android.clustering.Cluster
        public int getSize() {
            return 1;
        }

        private QuadItem(T t) {
            this.mClusterItem = t;
            LatLng position = t.getPosition();
            this.mPosition = position;
            this.mPoint = NonHierarchicalDistanceBasedAlgorithm.PROJECTION.toPoint(position);
            this.singletonSet = Collections.singleton(t);
        }

        @Override // com.google.maps.android.quadtree.PointQuadTree.Item
        public Point getPoint() {
            return this.mPoint;
        }

        @Override // com.google.maps.android.clustering.Cluster
        public LatLng getPosition() {
            return this.mPosition;
        }

        @Override // com.google.maps.android.clustering.Cluster
        public Set<T> getItems() {
            return this.singletonSet;
        }

        public int hashCode() {
            return this.mClusterItem.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof QuadItem) {
                return ((QuadItem) obj).mClusterItem.equals(this.mClusterItem);
            }
            return false;
        }
    }
}
