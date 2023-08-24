package kotlin.internal;

import com.onesignal.NotificationBundleProcessor;
import kotlin.Metadata;

@Metadata(m184d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a \u0010\u0000\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a \u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u0001H\u0001\u001a \u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0001\u001a\u0018\u0010\n\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\n\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005H\u0002¨\u0006\u000b"}, m183d2 = {"differenceModulo", "", NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY, "b", "c", "", "getProgressionLastElement", "start", "end", "step", "mod", "kotlin-stdlib"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: kotlin.internal.ProgressionUtilKt */
/* loaded from: classes5.dex */
public final class progressionUtil {
    private static final int mod(int r0, int r1) {
        int r02 = r0 % r1;
        return r02 >= 0 ? r02 : r02 + r1;
    }

    private static final long mod(long j, long j2) {
        long j3 = j % j2;
        return j3 >= 0 ? j3 : j3 + j2;
    }

    private static final int differenceModulo(int r0, int r1, int r2) {
        return mod(mod(r0, r2) - mod(r1, r2), r2);
    }

    private static final long differenceModulo(long j, long j2, long j3) {
        return mod(mod(j, j3) - mod(j2, j3), j3);
    }

    public static final int getProgressionLastElement(int r0, int r1, int r2) {
        if (r2 > 0) {
            return r0 >= r1 ? r1 : r1 - differenceModulo(r1, r0, r2);
        } else if (r2 < 0) {
            return r0 <= r1 ? r1 : r1 + differenceModulo(r0, r1, -r2);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }

    public static final long getProgressionLastElement(long j, long j2, long j3) {
        int r2 = (j3 > 0L ? 1 : (j3 == 0L ? 0 : -1));
        if (r2 > 0) {
            return j >= j2 ? j2 : j2 - differenceModulo(j2, j, j3);
        } else if (r2 < 0) {
            return j <= j2 ? j2 : j2 + differenceModulo(j, j2, -j3);
        } else {
            throw new IllegalArgumentException("Step is zero.");
        }
    }
}
