package kotlin.time;

import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;

/* compiled from: TimeSources.kt */
@Metadata(m184d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001b\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\nJ\b\u0010\r\u001a\u00020\u0004H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, m183d2 = {"Lkotlin/time/TestTimeSource;", "Lkotlin/time/AbstractLongTimeSource;", "()V", "reading", "", ViewProps.OVERFLOW, "", "duration", "Lkotlin/time/Duration;", "overflow-LRDsOJo", "(J)V", "plusAssign", "plusAssign-LRDsOJo", "read", "kotlin-stdlib"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes5.dex */
public final class TestTimeSource extends AbstractLongTimeSource {
    private long reading;

    public TestTimeSource() {
        super(DurationUnitJvm.NANOSECONDS);
    }

    @Override // kotlin.time.AbstractLongTimeSource
    protected long read() {
        return this.reading;
    }

    /* renamed from: plusAssign-LRDsOJo  reason: not valid java name */
    public final void m3183plusAssignLRDsOJo(long j) {
        long j2;
        long m3101toLongimpl = Duration.m3101toLongimpl(j, getUnit());
        if (m3101toLongimpl != Long.MIN_VALUE && m3101toLongimpl != Long.MAX_VALUE) {
            long j3 = this.reading;
            j2 = j3 + m3101toLongimpl;
            if ((m3101toLongimpl ^ j3) >= 0 && (j3 ^ j2) < 0) {
                m3182overflowLRDsOJo(j);
            }
        } else {
            double m3098toDoubleimpl = this.reading + Duration.m3098toDoubleimpl(j, getUnit());
            if (m3098toDoubleimpl > 9.223372036854776E18d || m3098toDoubleimpl < -9.223372036854776E18d) {
                m3182overflowLRDsOJo(j);
            }
            j2 = (long) m3098toDoubleimpl;
        }
        this.reading = j2;
    }

    /* renamed from: overflow-LRDsOJo  reason: not valid java name */
    private final void m3182overflowLRDsOJo(long j) {
        throw new IllegalStateException("TestTimeSource will overflow if its reading " + this.reading + "ns is advanced by " + ((Object) Duration.m3104toStringimpl(j)) + '.');
    }
}
