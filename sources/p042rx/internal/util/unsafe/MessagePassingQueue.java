package p042rx.internal.util.unsafe;

/* renamed from: rx.internal.util.unsafe.MessagePassingQueue */
/* loaded from: classes6.dex */
public interface MessagePassingQueue<M> {
    boolean isEmpty();

    boolean offer(M m);

    M peek();

    M poll();

    int size();
}
