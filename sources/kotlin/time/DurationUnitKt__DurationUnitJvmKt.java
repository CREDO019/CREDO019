package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DurationUnitJvm.kt */
@Metadata(m184d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001\u001a \u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001\u001a \u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001\u001a\f\u0010\b\u001a\u00020\u0004*\u00020\tH\u0007\u001a\f\u0010\n\u001a\u00020\t*\u00020\u0004H\u0007¨\u0006\u000b"}, m183d2 = {"convertDurationUnit", "", "value", "sourceUnit", "Lkotlin/time/DurationUnit;", "targetUnit", "", "convertDurationUnitOverflow", "toDurationUnit", "Ljava/util/concurrent/TimeUnit;", "toTimeUnit", "kotlin-stdlib"}, m182k = 5, m181mv = {1, 6, 0}, m179xi = 49, m178xs = "kotlin/time/DurationUnitKt")
/* loaded from: classes5.dex */
class DurationUnitKt__DurationUnitJvmKt {

    /* compiled from: DurationUnitJvm.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[TimeUnit.values().length];
            r0[TimeUnit.NANOSECONDS.ordinal()] = 1;
            r0[TimeUnit.MICROSECONDS.ordinal()] = 2;
            r0[TimeUnit.MILLISECONDS.ordinal()] = 3;
            r0[TimeUnit.SECONDS.ordinal()] = 4;
            r0[TimeUnit.MINUTES.ordinal()] = 5;
            r0[TimeUnit.HOURS.ordinal()] = 6;
            r0[TimeUnit.DAYS.ordinal()] = 7;
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static final TimeUnit toTimeUnit(DurationUnitJvm durationUnitJvm) {
        Intrinsics.checkNotNullParameter(durationUnitJvm, "<this>");
        return durationUnitJvm.getTimeUnit$kotlin_stdlib();
    }

    public static final DurationUnitJvm toDurationUnit(TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[timeUnit.ordinal()]) {
            case 1:
                return DurationUnitJvm.NANOSECONDS;
            case 2:
                return DurationUnitJvm.MICROSECONDS;
            case 3:
                return DurationUnitJvm.MILLISECONDS;
            case 4:
                return DurationUnitJvm.SECONDS;
            case 5:
                return DurationUnitJvm.MINUTES;
            case 6:
                return DurationUnitJvm.HOURS;
            case 7:
                return DurationUnitJvm.DAYS;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final double convertDurationUnit(double d, DurationUnitJvm sourceUnit, DurationUnitJvm targetUnit) {
        Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
        Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
        long convert = targetUnit.getTimeUnit$kotlin_stdlib().convert(1L, sourceUnit.getTimeUnit$kotlin_stdlib());
        return convert > 0 ? d * convert : d / sourceUnit.getTimeUnit$kotlin_stdlib().convert(1L, targetUnit.getTimeUnit$kotlin_stdlib());
    }

    public static final long convertDurationUnitOverflow(long j, DurationUnitJvm sourceUnit, DurationUnitJvm targetUnit) {
        Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
        Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
        return targetUnit.getTimeUnit$kotlin_stdlib().convert(j, sourceUnit.getTimeUnit$kotlin_stdlib());
    }

    public static final long convertDurationUnit(long j, DurationUnitJvm sourceUnit, DurationUnitJvm targetUnit) {
        Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
        Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
        return targetUnit.getTimeUnit$kotlin_stdlib().convert(j, sourceUnit.getTimeUnit$kotlin_stdlib());
    }
}
