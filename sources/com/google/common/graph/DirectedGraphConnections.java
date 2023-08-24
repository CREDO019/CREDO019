package com.google.common.graph;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.graph.ElementOrder;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class DirectedGraphConnections<N, V> implements GraphConnections<N, V> {
    private static final Object PRED = new Object();
    private final Map<N, Object> adjacentNodeValues;
    @CheckForNull
    private final List<NodeConnection<N>> orderedNodeConnections;
    private int predecessorCount;
    private int successorCount;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class PredAndSucc {
        private final Object successorValue;

        PredAndSucc(Object obj) {
            this.successorValue = obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static abstract class NodeConnection<N> {
        final N node;

        NodeConnection(N n) {
            this.node = (N) Preconditions.checkNotNull(n);
        }

        /* loaded from: classes3.dex */
        static final class Pred<N> extends NodeConnection<N> {
            Pred(N n) {
                super(n);
            }

            public boolean equals(@CheckForNull Object obj) {
                if (obj instanceof Pred) {
                    return this.node.equals(((Pred) obj).node);
                }
                return false;
            }

            public int hashCode() {
                return Pred.class.hashCode() + this.node.hashCode();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public static final class Succ<N> extends NodeConnection<N> {
            Succ(N n) {
                super(n);
            }

            public boolean equals(@CheckForNull Object obj) {
                if (obj instanceof Succ) {
                    return this.node.equals(((Succ) obj).node);
                }
                return false;
            }

            public int hashCode() {
                return Succ.class.hashCode() + this.node.hashCode();
            }
        }
    }

    private DirectedGraphConnections(Map<N, Object> map, @CheckForNull List<NodeConnection<N>> list, int r4, int r5) {
        this.adjacentNodeValues = (Map) Preconditions.checkNotNull(map);
        this.orderedNodeConnections = list;
        this.predecessorCount = Graphs.checkNonNegative(r4);
        this.successorCount = Graphs.checkNonNegative(r5);
        Preconditions.checkState(r4 <= map.size() && r5 <= map.size());
    }

    /* renamed from: com.google.common.graph.DirectedGraphConnections$8 */
    /* loaded from: classes3.dex */
    static /* synthetic */ class C30198 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$graph$ElementOrder$Type;

        static {
            int[] r0 = new int[ElementOrder.Type.values().length];
            $SwitchMap$com$google$common$graph$ElementOrder$Type = r0;
            try {
                r0[ElementOrder.Type.UNORDERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$graph$ElementOrder$Type[ElementOrder.Type.STABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: of */
    public static <N, V> DirectedGraphConnections<N, V> m299of(ElementOrder<N> elementOrder) {
        ArrayList arrayList;
        int r0 = C30198.$SwitchMap$com$google$common$graph$ElementOrder$Type[elementOrder.type().ordinal()];
        if (r0 == 1) {
            arrayList = null;
        } else if (r0 == 2) {
            arrayList = new ArrayList();
        } else {
            throw new AssertionError(elementOrder.type());
        }
        return new DirectedGraphConnections<>(new HashMap(4, 1.0f), arrayList, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <N, V> DirectedGraphConnections<N, V> ofImmutable(N n, Iterable<EndpointPair<N>> iterable, Function<N, V> function) {
        Preconditions.checkNotNull(n);
        Preconditions.checkNotNull(function);
        HashMap hashMap = new HashMap();
        ImmutableList.Builder builder = ImmutableList.builder();
        int r3 = 0;
        int r4 = 0;
        for (EndpointPair<N> endpointPair : iterable) {
            if (endpointPair.nodeU().equals(n) && endpointPair.nodeV().equals(n)) {
                hashMap.put(n, new PredAndSucc(function.apply(n)));
                builder.add((ImmutableList.Builder) new NodeConnection.Pred(n));
                builder.add((ImmutableList.Builder) new NodeConnection.Succ(n));
                r3++;
            } else if (endpointPair.nodeV().equals(n)) {
                N nodeU = endpointPair.nodeU();
                Object put = hashMap.put(nodeU, PRED);
                if (put != null) {
                    hashMap.put(nodeU, new PredAndSucc(put));
                }
                builder.add((ImmutableList.Builder) new NodeConnection.Pred(nodeU));
                r3++;
            } else {
                Preconditions.checkArgument(endpointPair.nodeU().equals(n));
                N nodeV = endpointPair.nodeV();
                V apply = function.apply(nodeV);
                Object put2 = hashMap.put(nodeV, apply);
                if (put2 != null) {
                    Preconditions.checkArgument(put2 == PRED);
                    hashMap.put(nodeV, new PredAndSucc(apply));
                }
                builder.add((ImmutableList.Builder) new NodeConnection.Succ(nodeV));
            }
            r4++;
        }
        return new DirectedGraphConnections<>(hashMap, builder.build(), r3, r4);
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> adjacentNodes() {
        if (this.orderedNodeConnections == null) {
            return Collections.unmodifiableSet(this.adjacentNodeValues.keySet());
        }
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                final Iterator it = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                final HashSet hashSet = new HashSet();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.1.1
                    @Override // com.google.common.collect.AbstractIterator
                    @CheckForNull
                    protected N computeNext() {
                        while (it.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) it.next();
                            if (hashSet.add(nodeConnection.node)) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.adjacentNodeValues.size();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@CheckForNull Object obj) {
                return DirectedGraphConnections.this.adjacentNodeValues.containsKey(obj);
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> predecessors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.2
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    final Iterator it = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                    return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.1
                        @Override // com.google.common.collect.AbstractIterator
                        @CheckForNull
                        protected N computeNext() {
                            while (it.hasNext()) {
                                Map.Entry entry = (Map.Entry) it.next();
                                if (DirectedGraphConnections.isPredecessor(entry.getValue())) {
                                    return (N) entry.getKey();
                                }
                            }
                            return endOfData();
                        }
                    };
                }
                final Iterator it2 = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.2.2
                    @Override // com.google.common.collect.AbstractIterator
                    @CheckForNull
                    protected N computeNext() {
                        while (it2.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) it2.next();
                            if (nodeConnection instanceof NodeConnection.Pred) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.predecessorCount;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@CheckForNull Object obj) {
                return DirectedGraphConnections.isPredecessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public Set<N> successors() {
        return new AbstractSet<N>() { // from class: com.google.common.graph.DirectedGraphConnections.3
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public UnmodifiableIterator<N> iterator() {
                if (DirectedGraphConnections.this.orderedNodeConnections == null) {
                    final Iterator it = DirectedGraphConnections.this.adjacentNodeValues.entrySet().iterator();
                    return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.1
                        @Override // com.google.common.collect.AbstractIterator
                        @CheckForNull
                        protected N computeNext() {
                            while (it.hasNext()) {
                                Map.Entry entry = (Map.Entry) it.next();
                                if (DirectedGraphConnections.isSuccessor(entry.getValue())) {
                                    return (N) entry.getKey();
                                }
                            }
                            return endOfData();
                        }
                    };
                }
                final Iterator it2 = DirectedGraphConnections.this.orderedNodeConnections.iterator();
                return new AbstractIterator<N>(this) { // from class: com.google.common.graph.DirectedGraphConnections.3.2
                    @Override // com.google.common.collect.AbstractIterator
                    @CheckForNull
                    protected N computeNext() {
                        while (it2.hasNext()) {
                            NodeConnection nodeConnection = (NodeConnection) it2.next();
                            if (nodeConnection instanceof NodeConnection.Succ) {
                                return nodeConnection.node;
                            }
                        }
                        return endOfData();
                    }
                };
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DirectedGraphConnections.this.successorCount;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@CheckForNull Object obj) {
                return DirectedGraphConnections.isSuccessor(DirectedGraphConnections.this.adjacentNodeValues.get(obj));
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    public Iterator<EndpointPair<N>> incidentEdgeIterator(final N n) {
        final Iterator transform;
        Preconditions.checkNotNull(n);
        List<NodeConnection<N>> list = this.orderedNodeConnections;
        if (list == null) {
            transform = Iterators.concat(Iterators.transform(predecessors().iterator(), new Function<N, EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.4
                @Override // com.google.common.base.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    return apply((C30154) obj);
                }

                @Override // com.google.common.base.Function
                public EndpointPair<N> apply(N n2) {
                    return EndpointPair.ordered(n2, n);
                }
            }), Iterators.transform(successors().iterator(), new Function<N, EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.5
                @Override // com.google.common.base.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    return apply((C30165) obj);
                }

                @Override // com.google.common.base.Function
                public EndpointPair<N> apply(N n2) {
                    return EndpointPair.ordered(n, n2);
                }
            }));
        } else {
            transform = Iterators.transform(list.iterator(), new Function<NodeConnection<N>, EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.6
                @Override // com.google.common.base.Function
                public /* bridge */ /* synthetic */ Object apply(Object obj) {
                    return apply((NodeConnection) ((NodeConnection) obj));
                }

                public EndpointPair<N> apply(NodeConnection<N> nodeConnection) {
                    if (nodeConnection instanceof NodeConnection.Succ) {
                        return EndpointPair.ordered(n, nodeConnection.node);
                    }
                    return EndpointPair.ordered(nodeConnection.node, n);
                }
            });
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        return new AbstractIterator<EndpointPair<N>>(this) { // from class: com.google.common.graph.DirectedGraphConnections.7
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Removed duplicated region for block: B:4:0x0008  */
            @Override // com.google.common.collect.AbstractIterator
            @javax.annotation.CheckForNull
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public com.google.common.graph.EndpointPair<N> computeNext() {
                /*
                    r3 = this;
                L0:
                    java.util.Iterator r0 = r2
                    boolean r0 = r0.hasNext()
                    if (r0 == 0) goto L28
                    java.util.Iterator r0 = r2
                    java.lang.Object r0 = r0.next()
                    com.google.common.graph.EndpointPair r0 = (com.google.common.graph.EndpointPair) r0
                    java.lang.Object r1 = r0.nodeU()
                    java.lang.Object r2 = r0.nodeV()
                    boolean r1 = r1.equals(r2)
                    if (r1 == 0) goto L27
                    java.util.concurrent.atomic.AtomicBoolean r1 = r3
                    r2 = 1
                    boolean r1 = r1.getAndSet(r2)
                    if (r1 != 0) goto L0
                L27:
                    return r0
                L28:
                    java.lang.Object r0 = r3.endOfData()
                    com.google.common.graph.EndpointPair r0 = (com.google.common.graph.EndpointPair) r0
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.DirectedGraphConnections.C30187.computeNext():com.google.common.graph.EndpointPair");
            }
        };
    }

    @Override // com.google.common.graph.GraphConnections
    @CheckForNull
    public V value(N n) {
        Preconditions.checkNotNull(n);
        V v = (V) this.adjacentNodeValues.get(n);
        if (v == PRED) {
            return null;
        }
        return v instanceof PredAndSucc ? (V) ((PredAndSucc) v).successorValue : v;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // com.google.common.graph.GraphConnections
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void removePredecessor(N r4) {
        /*
            r3 = this;
            com.google.common.base.Preconditions.checkNotNull(r4)
            java.util.Map<N, java.lang.Object> r0 = r3.adjacentNodeValues
            java.lang.Object r0 = r0.get(r4)
            java.lang.Object r1 = com.google.common.graph.DirectedGraphConnections.PRED
            r2 = 1
            if (r0 != r1) goto L15
            java.util.Map<N, java.lang.Object> r0 = r3.adjacentNodeValues
            r0.remove(r4)
        L13:
            r0 = 1
            goto L26
        L15:
            boolean r1 = r0 instanceof com.google.common.graph.DirectedGraphConnections.PredAndSucc
            if (r1 == 0) goto L25
            java.util.Map<N, java.lang.Object> r1 = r3.adjacentNodeValues
            com.google.common.graph.DirectedGraphConnections$PredAndSucc r0 = (com.google.common.graph.DirectedGraphConnections.PredAndSucc) r0
            java.lang.Object r0 = com.google.common.graph.DirectedGraphConnections.PredAndSucc.access$600(r0)
            r1.put(r4, r0)
            goto L13
        L25:
            r0 = 0
        L26:
            if (r0 == 0) goto L3c
            int r0 = r3.predecessorCount
            int r0 = r0 - r2
            r3.predecessorCount = r0
            com.google.common.graph.Graphs.checkNonNegative(r0)
            java.util.List<com.google.common.graph.DirectedGraphConnections$NodeConnection<N>> r0 = r3.orderedNodeConnections
            if (r0 == 0) goto L3c
            com.google.common.graph.DirectedGraphConnections$NodeConnection$Pred r1 = new com.google.common.graph.DirectedGraphConnections$NodeConnection$Pred
            r1.<init>(r4)
            r0.remove(r1)
        L3c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.DirectedGraphConnections.removePredecessor(java.lang.Object):void");
    }

    @Override // com.google.common.graph.GraphConnections
    @CheckForNull
    public V removeSuccessor(Object obj) {
        Object obj2;
        Preconditions.checkNotNull(obj);
        Object obj3 = this.adjacentNodeValues.get(obj);
        if (obj3 == null || obj3 == (obj2 = PRED)) {
            obj3 = null;
        } else if (obj3 instanceof PredAndSucc) {
            this.adjacentNodeValues.put(obj, obj2);
            obj3 = ((PredAndSucc) obj3).successorValue;
        } else {
            this.adjacentNodeValues.remove(obj);
        }
        if (obj3 != null) {
            int r2 = this.successorCount - 1;
            this.successorCount = r2;
            Graphs.checkNonNegative(r2);
            List<NodeConnection<N>> list = this.orderedNodeConnections;
            if (list != null) {
                list.remove(new NodeConnection.Succ(obj));
            }
        }
        if (obj3 == null) {
            return null;
        }
        return (V) obj3;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    @Override // com.google.common.graph.GraphConnections
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addPredecessor(N r5, V r6) {
        /*
            r4 = this;
            java.util.Map<N, java.lang.Object> r6 = r4.adjacentNodeValues
            java.lang.Object r0 = com.google.common.graph.DirectedGraphConnections.PRED
            java.lang.Object r6 = r6.put(r5, r0)
            r1 = 0
            r2 = 1
            if (r6 != 0) goto Le
        Lc:
            r1 = 1
            goto L25
        Le:
            boolean r3 = r6 instanceof com.google.common.graph.DirectedGraphConnections.PredAndSucc
            if (r3 == 0) goto L18
            java.util.Map<N, java.lang.Object> r0 = r4.adjacentNodeValues
            r0.put(r5, r6)
            goto L25
        L18:
            if (r6 == r0) goto L25
            java.util.Map<N, java.lang.Object> r0 = r4.adjacentNodeValues
            com.google.common.graph.DirectedGraphConnections$PredAndSucc r1 = new com.google.common.graph.DirectedGraphConnections$PredAndSucc
            r1.<init>(r6)
            r0.put(r5, r1)
            goto Lc
        L25:
            if (r1 == 0) goto L3b
            int r6 = r4.predecessorCount
            int r6 = r6 + r2
            r4.predecessorCount = r6
            com.google.common.graph.Graphs.checkPositive(r6)
            java.util.List<com.google.common.graph.DirectedGraphConnections$NodeConnection<N>> r6 = r4.orderedNodeConnections
            if (r6 == 0) goto L3b
            com.google.common.graph.DirectedGraphConnections$NodeConnection$Pred r0 = new com.google.common.graph.DirectedGraphConnections$NodeConnection$Pred
            r0.<init>(r5)
            r6.add(r0)
        L3b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.DirectedGraphConnections.addPredecessor(java.lang.Object, java.lang.Object):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    @Override // com.google.common.graph.GraphConnections
    @javax.annotation.CheckForNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public V addSuccessor(N r5, V r6) {
        /*
            r4 = this;
            java.util.Map<N, java.lang.Object> r0 = r4.adjacentNodeValues
            java.lang.Object r0 = r0.put(r5, r6)
            r1 = 0
            if (r0 != 0) goto Lb
        L9:
            r0 = r1
            goto L2f
        Lb:
            boolean r2 = r0 instanceof com.google.common.graph.DirectedGraphConnections.PredAndSucc
            if (r2 == 0) goto L20
            java.util.Map<N, java.lang.Object> r2 = r4.adjacentNodeValues
            com.google.common.graph.DirectedGraphConnections$PredAndSucc r3 = new com.google.common.graph.DirectedGraphConnections$PredAndSucc
            r3.<init>(r6)
            r2.put(r5, r3)
            com.google.common.graph.DirectedGraphConnections$PredAndSucc r0 = (com.google.common.graph.DirectedGraphConnections.PredAndSucc) r0
            java.lang.Object r0 = com.google.common.graph.DirectedGraphConnections.PredAndSucc.access$600(r0)
            goto L2f
        L20:
            java.lang.Object r2 = com.google.common.graph.DirectedGraphConnections.PRED
            if (r0 != r2) goto L2f
            java.util.Map<N, java.lang.Object> r0 = r4.adjacentNodeValues
            com.google.common.graph.DirectedGraphConnections$PredAndSucc r2 = new com.google.common.graph.DirectedGraphConnections$PredAndSucc
            r2.<init>(r6)
            r0.put(r5, r2)
            goto L9
        L2f:
            if (r0 != 0) goto L46
            int r6 = r4.successorCount
            int r6 = r6 + 1
            r4.successorCount = r6
            com.google.common.graph.Graphs.checkPositive(r6)
            java.util.List<com.google.common.graph.DirectedGraphConnections$NodeConnection<N>> r6 = r4.orderedNodeConnections
            if (r6 == 0) goto L46
            com.google.common.graph.DirectedGraphConnections$NodeConnection$Succ r2 = new com.google.common.graph.DirectedGraphConnections$NodeConnection$Succ
            r2.<init>(r5)
            r6.add(r2)
        L46:
            if (r0 != 0) goto L49
            goto L4a
        L49:
            r1 = r0
        L4a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.graph.DirectedGraphConnections.addSuccessor(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPredecessor(@CheckForNull Object obj) {
        return obj == PRED || (obj instanceof PredAndSucc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isSuccessor(@CheckForNull Object obj) {
        return (obj == PRED || obj == null) ? false : true;
    }
}
