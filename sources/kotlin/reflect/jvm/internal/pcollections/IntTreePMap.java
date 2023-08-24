package kotlin.reflect.jvm.internal.pcollections;

/* loaded from: classes5.dex */
final class IntTreePMap<V> {
    private static final IntTreePMap<Object> EMPTY = new IntTreePMap<>(IntTree.EMPTYNODE);
    private final IntTree<V> root;

    public static <V> IntTreePMap<V> empty() {
        return (IntTreePMap<V>) EMPTY;
    }

    private IntTreePMap(IntTree<V> intTree) {
        this.root = intTree;
    }

    private IntTreePMap<V> withRoot(IntTree<V> intTree) {
        return intTree == this.root ? this : new IntTreePMap<>(intTree);
    }

    public V get(int r4) {
        return this.root.get(r4);
    }

    public IntTreePMap<V> plus(int r4, V v) {
        return withRoot(this.root.plus(r4, v));
    }

    public IntTreePMap<V> minus(int r4) {
        return withRoot(this.root.minus(r4));
    }
}
