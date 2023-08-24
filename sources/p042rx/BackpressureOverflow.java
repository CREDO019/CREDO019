package p042rx;

import p042rx.exceptions.MissingBackpressureException;

/* renamed from: rx.BackpressureOverflow */
/* loaded from: classes4.dex */
public final class BackpressureOverflow {
    public static final Strategy ON_OVERFLOW_DEFAULT;
    public static final Strategy ON_OVERFLOW_DROP_LATEST;
    public static final Strategy ON_OVERFLOW_DROP_OLDEST;
    public static final Strategy ON_OVERFLOW_ERROR;

    /* renamed from: rx.BackpressureOverflow$Strategy */
    /* loaded from: classes4.dex */
    public interface Strategy {
        boolean mayAttemptDrop() throws MissingBackpressureException;
    }

    private BackpressureOverflow() {
        throw new IllegalStateException("No instances!");
    }

    static {
        Error error = Error.INSTANCE;
        ON_OVERFLOW_ERROR = error;
        ON_OVERFLOW_DEFAULT = error;
        ON_OVERFLOW_DROP_OLDEST = DropOldest.INSTANCE;
        ON_OVERFLOW_DROP_LATEST = DropLatest.INSTANCE;
    }

    /* renamed from: rx.BackpressureOverflow$DropOldest */
    /* loaded from: classes4.dex */
    static final class DropOldest implements Strategy {
        static final DropOldest INSTANCE = new DropOldest();

        @Override // p042rx.BackpressureOverflow.Strategy
        public boolean mayAttemptDrop() {
            return true;
        }

        private DropOldest() {
        }
    }

    /* renamed from: rx.BackpressureOverflow$DropLatest */
    /* loaded from: classes4.dex */
    static final class DropLatest implements Strategy {
        static final DropLatest INSTANCE = new DropLatest();

        @Override // p042rx.BackpressureOverflow.Strategy
        public boolean mayAttemptDrop() {
            return false;
        }

        private DropLatest() {
        }
    }

    /* renamed from: rx.BackpressureOverflow$Error */
    /* loaded from: classes4.dex */
    static final class Error implements Strategy {
        static final Error INSTANCE = new Error();

        private Error() {
        }

        @Override // p042rx.BackpressureOverflow.Strategy
        public boolean mayAttemptDrop() throws MissingBackpressureException {
            throw new MissingBackpressureException("Overflowed buffer");
        }
    }
}
