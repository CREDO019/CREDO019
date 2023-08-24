package com.google.maps.android.quadtree;

import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.quadtree.PointQuadTree.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* loaded from: classes3.dex */
public class PointQuadTree<T extends Item> {
    private static final int MAX_DEPTH = 40;
    private static final int MAX_ELEMENTS = 50;
    private final Bounds mBounds;
    private List<PointQuadTree<T>> mChildren;
    private final int mDepth;
    private List<T> mItems;

    /* loaded from: classes3.dex */
    public interface Item {
        Point getPoint();
    }

    public PointQuadTree(double d, double d2, double d3, double d4) {
        this(new Bounds(d, d2, d3, d4));
    }

    public PointQuadTree(Bounds bounds) {
        this(bounds, 0);
    }

    private PointQuadTree(double d, double d2, double d3, double d4, int r19) {
        this(new Bounds(d, d2, d3, d4), r19);
    }

    private PointQuadTree(Bounds bounds, int r3) {
        this.mChildren = null;
        this.mBounds = bounds;
        this.mDepth = r3;
    }

    public void add(T t) {
        Point point = t.getPoint();
        if (this.mBounds.contains(point.f1212x, point.f1213y)) {
            insert(point.f1212x, point.f1213y, t);
        }
    }

    private void insert(double d, double d2, T t) {
        if (this.mChildren != null) {
            if (d2 < this.mBounds.midY) {
                if (d < this.mBounds.midX) {
                    this.mChildren.get(0).insert(d, d2, t);
                    return;
                } else {
                    this.mChildren.get(1).insert(d, d2, t);
                    return;
                }
            } else if (d < this.mBounds.midX) {
                this.mChildren.get(2).insert(d, d2, t);
                return;
            } else {
                this.mChildren.get(3).insert(d, d2, t);
                return;
            }
        }
        if (this.mItems == null) {
            this.mItems = new ArrayList();
        }
        this.mItems.add(t);
        if (this.mItems.size() <= 50 || this.mDepth >= 40) {
            return;
        }
        split();
    }

    private void split() {
        ArrayList arrayList = new ArrayList(4);
        this.mChildren = arrayList;
        arrayList.add(new PointQuadTree(this.mBounds.minX, this.mBounds.midX, this.mBounds.minY, this.mBounds.midY, this.mDepth + 1));
        this.mChildren.add(new PointQuadTree<>(this.mBounds.midX, this.mBounds.maxX, this.mBounds.minY, this.mBounds.midY, this.mDepth + 1));
        this.mChildren.add(new PointQuadTree<>(this.mBounds.minX, this.mBounds.midX, this.mBounds.midY, this.mBounds.maxY, this.mDepth + 1));
        this.mChildren.add(new PointQuadTree<>(this.mBounds.midX, this.mBounds.maxX, this.mBounds.midY, this.mBounds.maxY, this.mDepth + 1));
        List<T> list = this.mItems;
        this.mItems = null;
        for (T t : list) {
            insert(t.getPoint().f1212x, t.getPoint().f1213y, t);
        }
    }

    public boolean remove(T t) {
        Point point = t.getPoint();
        if (this.mBounds.contains(point.f1212x, point.f1213y)) {
            return remove(point.f1212x, point.f1213y, t);
        }
        return false;
    }

    private boolean remove(double d, double d2, T t) {
        if (this.mChildren != null) {
            if (d2 < this.mBounds.midY) {
                if (d < this.mBounds.midX) {
                    return this.mChildren.get(0).remove(d, d2, t);
                }
                return this.mChildren.get(1).remove(d, d2, t);
            } else if (d < this.mBounds.midX) {
                return this.mChildren.get(2).remove(d, d2, t);
            } else {
                return this.mChildren.get(3).remove(d, d2, t);
            }
        }
        List<T> list = this.mItems;
        if (list == null) {
            return false;
        }
        return list.remove(t);
    }

    public void clear() {
        this.mChildren = null;
        List<T> list = this.mItems;
        if (list != null) {
            list.clear();
        }
    }

    public Collection<T> search(Bounds bounds) {
        ArrayList arrayList = new ArrayList();
        search(bounds, arrayList);
        return arrayList;
    }

    private void search(Bounds bounds, Collection<T> collection) {
        if (this.mBounds.intersects(bounds)) {
            List<PointQuadTree<T>> list = this.mChildren;
            if (list != null) {
                for (PointQuadTree<T> pointQuadTree : list) {
                    pointQuadTree.search(bounds, collection);
                }
            } else if (this.mItems != null) {
                if (bounds.contains(this.mBounds)) {
                    collection.addAll(this.mItems);
                    return;
                }
                for (T t : this.mItems) {
                    if (bounds.contains(t.getPoint())) {
                        collection.add(t);
                    }
                }
            }
        }
    }
}
