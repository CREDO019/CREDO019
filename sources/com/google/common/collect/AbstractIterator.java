package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public abstract class AbstractIterator<T> extends UnmodifiableIterator<T> {
    @CheckForNull
    private T next;
    private State state = State.NOT_READY;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum State {
        READY,
        NOT_READY,
        DONE,
        FAILED
    }

    @CheckForNull
    protected abstract T computeNext();

    /* JADX INFO: Access modifiers changed from: protected */
    @CheckForNull
    public final T endOfData() {
        this.state = State.DONE;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.AbstractIterator$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C27621 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$collect$AbstractIterator$State;

        static {
            int[] r0 = new int[State.values().length];
            $SwitchMap$com$google$common$collect$AbstractIterator$State = r0;
            try {
                r0[State.DONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$collect$AbstractIterator$State[State.READY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        Preconditions.checkState(this.state != State.FAILED);
        int r0 = C27621.$SwitchMap$com$google$common$collect$AbstractIterator$State[this.state.ordinal()];
        if (r0 != 1) {
            if (r0 != 2) {
                return tryToComputeNext();
            }
            return true;
        }
        return false;
    }

    private boolean tryToComputeNext() {
        this.state = State.FAILED;
        this.next = computeNext();
        if (this.state != State.DONE) {
            this.state = State.READY;
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    @ParametricNullness
    public final T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.state = State.NOT_READY;
        T t = (T) NullnessCasts.uncheckedCastNullableTToT(this.next);
        this.next = null;
        return t;
    }

    @ParametricNullness
    public final T peek() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return (T) NullnessCasts.uncheckedCastNullableTToT(this.next);
    }
}
