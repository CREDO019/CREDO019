package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* compiled from: WithLifecycleState.kt */
@Metadata(m185bv = {1, 0, 3}, m184d1 = {"\u0000,\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u001aA\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u000b\u001a+\u0010\f\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001a+\u0010\f\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u000e2\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a+\u0010\u0010\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001a+\u0010\u0010\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u000e2\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a+\u0010\u0011\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\r\u001a+\u0010\u0011\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u000e2\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a3\u0010\u0012\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a3\u0010\u0012\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a3\u0010\u0015\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u000e\b\u0004\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00010\nH\u0081Hø\u0001\u0000¢\u0006\u0002\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, m183d2 = {"suspendWithStateAtLeastUnchecked", "R", "Landroidx/lifecycle/Lifecycle;", "state", "Landroidx/lifecycle/Lifecycle$State;", "dispatchNeeded", "", "lifecycleDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "block", "Lkotlin/Function0;", "(Landroidx/lifecycle/Lifecycle;Landroidx/lifecycle/Lifecycle$State;ZLkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withCreated", "(Landroidx/lifecycle/Lifecycle;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withResumed", "withStarted", "withStateAtLeast", "(Landroidx/lifecycle/Lifecycle;Landroidx/lifecycle/Lifecycle$State;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Lifecycle$State;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withStateAtLeastUnchecked", "lifecycle-runtime-ktx_release"}, m182k = 2, m181mv = {1, 4, 1})
/* loaded from: classes.dex */
public final class WithLifecycleStateKt {
    public static final <R> Object withStateAtLeast(Lifecycle lifecycle, Lifecycle.State state, Functions<? extends R> functions, Continuation<? super R> continuation) {
        if (!(state.compareTo(Lifecycle.State.CREATED) >= 0)) {
            throw new IllegalArgumentException(("target state must be CREATED or greater, found " + state).toString());
        }
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
    }

    private static final Object withStateAtLeast$$forInline(Lifecycle lifecycle, Lifecycle.State state, Functions functions, Continuation continuation) {
        if (!(state.compareTo(Lifecycle.State.CREATED) >= 0)) {
            throw new IllegalArgumentException(("target state must be CREATED or greater, found " + state).toString());
        }
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        InlineMarker.mark(3);
        Continuation continuation2 = null;
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation2.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        InlineMarker.mark(0);
        Object suspendWithStateAtLeastUnchecked = suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
        InlineMarker.mark(1);
        return suspendWithStateAtLeastUnchecked;
    }

    public static final <R> Object withCreated(Lifecycle lifecycle, Functions<? extends R> functions, Continuation<? super R> continuation) {
        Lifecycle.State state = Lifecycle.State.CREATED;
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
    }

    private static final Object withCreated$$forInline(Lifecycle lifecycle, Functions functions, Continuation continuation) {
        Lifecycle.State state = Lifecycle.State.CREATED;
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        InlineMarker.mark(3);
        Continuation continuation2 = null;
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation2.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        InlineMarker.mark(0);
        Object suspendWithStateAtLeastUnchecked = suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
        InlineMarker.mark(1);
        return suspendWithStateAtLeastUnchecked;
    }

    public static final <R> Object withStarted(Lifecycle lifecycle, Functions<? extends R> functions, Continuation<? super R> continuation) {
        Lifecycle.State state = Lifecycle.State.STARTED;
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
    }

    private static final Object withStarted$$forInline(Lifecycle lifecycle, Functions functions, Continuation continuation) {
        Lifecycle.State state = Lifecycle.State.STARTED;
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        InlineMarker.mark(3);
        Continuation continuation2 = null;
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation2.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        InlineMarker.mark(0);
        Object suspendWithStateAtLeastUnchecked = suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
        InlineMarker.mark(1);
        return suspendWithStateAtLeastUnchecked;
    }

    public static final <R> Object withResumed(Lifecycle lifecycle, Functions<? extends R> functions, Continuation<? super R> continuation) {
        Lifecycle.State state = Lifecycle.State.RESUMED;
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
    }

    private static final Object withResumed$$forInline(Lifecycle lifecycle, Functions functions, Continuation continuation) {
        Lifecycle.State state = Lifecycle.State.RESUMED;
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        InlineMarker.mark(3);
        Continuation continuation2 = null;
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation2.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        InlineMarker.mark(0);
        Object suspendWithStateAtLeastUnchecked = suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
        InlineMarker.mark(1);
        return suspendWithStateAtLeastUnchecked;
    }

    public static final <R> Object withStateAtLeast(LifecycleOwner lifecycleOwner, Lifecycle.State state, Functions<? extends R> functions, Continuation<? super R> continuation) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
        if (!(state.compareTo(Lifecycle.State.CREATED) >= 0)) {
            throw new IllegalArgumentException(("target state must be CREATED or greater, found " + state).toString());
        }
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
    }

    private static final Object withStateAtLeast$$forInline(LifecycleOwner lifecycleOwner, Lifecycle.State state, Functions functions, Continuation continuation) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
        if (!(state.compareTo(Lifecycle.State.CREATED) >= 0)) {
            throw new IllegalArgumentException(("target state must be CREATED or greater, found " + state).toString());
        }
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        InlineMarker.mark(3);
        Continuation continuation2 = null;
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation2.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        InlineMarker.mark(0);
        Object suspendWithStateAtLeastUnchecked = suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
        InlineMarker.mark(1);
        return suspendWithStateAtLeastUnchecked;
    }

    public static final <R> Object withCreated(LifecycleOwner lifecycleOwner, Functions<? extends R> functions, Continuation<? super R> continuation) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
        Lifecycle.State state = Lifecycle.State.CREATED;
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
    }

    private static final Object withCreated$$forInline(LifecycleOwner lifecycleOwner, Functions functions, Continuation continuation) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
        Lifecycle.State state = Lifecycle.State.CREATED;
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        InlineMarker.mark(3);
        Continuation continuation2 = null;
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation2.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        InlineMarker.mark(0);
        Object suspendWithStateAtLeastUnchecked = suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
        InlineMarker.mark(1);
        return suspendWithStateAtLeastUnchecked;
    }

    public static final <R> Object withStarted(LifecycleOwner lifecycleOwner, Functions<? extends R> functions, Continuation<? super R> continuation) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
        Lifecycle.State state = Lifecycle.State.STARTED;
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
    }

    private static final Object withStarted$$forInline(LifecycleOwner lifecycleOwner, Functions functions, Continuation continuation) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
        Lifecycle.State state = Lifecycle.State.STARTED;
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        InlineMarker.mark(3);
        Continuation continuation2 = null;
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation2.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        InlineMarker.mark(0);
        Object suspendWithStateAtLeastUnchecked = suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
        InlineMarker.mark(1);
        return suspendWithStateAtLeastUnchecked;
    }

    public static final <R> Object withResumed(LifecycleOwner lifecycleOwner, Functions<? extends R> functions, Continuation<? super R> continuation) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
        Lifecycle.State state = Lifecycle.State.RESUMED;
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
    }

    private static final Object withResumed$$forInline(LifecycleOwner lifecycleOwner, Functions functions, Continuation continuation) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycle, "lifecycle");
        Lifecycle.State state = Lifecycle.State.RESUMED;
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        InlineMarker.mark(3);
        Continuation continuation2 = null;
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation2.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        InlineMarker.mark(0);
        Object suspendWithStateAtLeastUnchecked = suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
        InlineMarker.mark(1);
        return suspendWithStateAtLeastUnchecked;
    }

    public static final <R> Object withStateAtLeastUnchecked(Lifecycle lifecycle, Lifecycle.State state, Functions<? extends R> functions, Continuation<? super R> continuation) {
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        return suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
    }

    private static final Object withStateAtLeastUnchecked$$forInline(Lifecycle lifecycle, Lifecycle.State state, Functions functions, Continuation continuation) {
        MainCoroutineDispatcher immediate = Dispatchers.getMain().getImmediate();
        InlineMarker.mark(3);
        Continuation continuation2 = null;
        boolean isDispatchNeeded = immediate.isDispatchNeeded(continuation2.getContext());
        if (!isDispatchNeeded) {
            if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
                throw new WithLifecycleState();
            }
            if (lifecycle.getCurrentState().compareTo(state) >= 0) {
                return functions.invoke();
            }
        }
        InlineMarker.mark(0);
        Object suspendWithStateAtLeastUnchecked = suspendWithStateAtLeastUnchecked(lifecycle, state, isDispatchNeeded, immediate, new WithLifecycleStateKt$withStateAtLeastUnchecked$2(functions), continuation);
        InlineMarker.mark(1);
        return suspendWithStateAtLeastUnchecked;
    }

    /* JADX WARN: Type inference failed for: r10v0, types: [androidx.lifecycle.WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$$inlined$suspendCancellableCoroutine$lambda$1] */
    public static final <R> Object suspendWithStateAtLeastUnchecked(final Lifecycle lifecycle, final Lifecycle.State state, final boolean z, final CoroutineDispatcher coroutineDispatcher, final Functions<? extends R> functions, Continuation<? super R> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        final CancellableContinuationImpl cancellableContinuationImpl2 = cancellableContinuationImpl;
        final ?? r10 = new LifecycleEventObserver() { // from class: androidx.lifecycle.WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$$inlined$suspendCancellableCoroutine$lambda$1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                Object m1749constructorimpl;
                Intrinsics.checkNotNullParameter(source, "source");
                Intrinsics.checkNotNullParameter(event, "event");
                if (event == Lifecycle.Event.upTo(state)) {
                    lifecycle.removeObserver(this);
                    CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                    Functions functions2 = functions;
                    try {
                        Result.Companion companion = Result.Companion;
                        m1749constructorimpl = Result.m1749constructorimpl(functions2.invoke());
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.Companion;
                        m1749constructorimpl = Result.m1749constructorimpl(ResultKt.createFailure(th));
                    }
                    cancellableContinuation.resumeWith(m1749constructorimpl);
                } else if (event == Lifecycle.Event.ON_DESTROY) {
                    lifecycle.removeObserver(this);
                    Result.Companion companion3 = Result.Companion;
                    CancellableContinuation.this.resumeWith(Result.m1749constructorimpl(ResultKt.createFailure(new WithLifecycleState())));
                }
            }
        };
        if (z) {
            coroutineDispatcher.mo3255dispatch(EmptyCoroutineContext.INSTANCE, new Runnable() { // from class: androidx.lifecycle.WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$$inlined$suspendCancellableCoroutine$lambda$2
                @Override // java.lang.Runnable
                public final void run() {
                    lifecycle.addObserver(C0472x3029b39e.this);
                }
            });
        } else {
            lifecycle.addObserver((LifecycleObserver) r10);
        }
        cancellableContinuationImpl2.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: androidx.lifecycle.WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$$inlined$suspendCancellableCoroutine$lambda$3
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                if (coroutineDispatcher.isDispatchNeeded(EmptyCoroutineContext.INSTANCE)) {
                    coroutineDispatcher.mo3255dispatch(EmptyCoroutineContext.INSTANCE, new Runnable() { // from class: androidx.lifecycle.WithLifecycleStateKt$suspendWithStateAtLeastUnchecked$$inlined$suspendCancellableCoroutine$lambda$3.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            lifecycle.removeObserver(C0472x3029b39e.this);
                        }
                    });
                } else {
                    lifecycle.removeObserver(C0472x3029b39e.this);
                }
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
