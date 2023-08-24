package p042rx.schedulers;

/* renamed from: rx.schedulers.TimeInterval */
/* loaded from: classes6.dex */
public class TimeInterval<T> {
    private final long intervalInMilliseconds;
    private final T value;

    public TimeInterval(long j, T t) {
        this.value = t;
        this.intervalInMilliseconds = j;
    }

    public long getIntervalInMilliseconds() {
        return this.intervalInMilliseconds;
    }

    public T getValue() {
        return this.value;
    }

    public int hashCode() {
        long j = this.intervalInMilliseconds;
        int r1 = (((int) (j ^ (j >>> 32))) + 31) * 31;
        T t = this.value;
        return r1 + (t == null ? 0 : t.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            TimeInterval timeInterval = (TimeInterval) obj;
            if (this.intervalInMilliseconds != timeInterval.intervalInMilliseconds) {
                return false;
            }
            T t = this.value;
            if (t == null) {
                if (timeInterval.value != null) {
                    return false;
                }
            } else if (!t.equals(timeInterval.value)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String toString() {
        return "TimeInterval [intervalInMilliseconds=" + this.intervalInMilliseconds + ", value=" + this.value + "]";
    }
}
