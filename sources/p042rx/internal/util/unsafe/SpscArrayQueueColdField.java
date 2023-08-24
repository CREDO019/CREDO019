package p042rx.internal.util.unsafe;

/* compiled from: SpscArrayQueue.java */
/* renamed from: rx.internal.util.unsafe.SpscArrayQueueColdField */
/* loaded from: classes6.dex */
abstract class SpscArrayQueueColdField<E> extends ConcurrentCircularArrayQueue<E> {
    private static final Integer MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    protected final int lookAheadStep;

    public SpscArrayQueueColdField(int r2) {
        super(r2);
        this.lookAheadStep = Math.min(r2 / 4, MAX_LOOK_AHEAD_STEP.intValue());
    }
}
