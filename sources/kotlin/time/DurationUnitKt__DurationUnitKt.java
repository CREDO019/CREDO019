package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DurationUnit.kt */
@Metadata(m184d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0001\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0001H\u0001¨\u0006\t"}, m183d2 = {"durationUnitByIsoChar", "Lkotlin/time/DurationUnit;", "isoChar", "", "isTimeComponent", "", "durationUnitByShortName", "shortName", "", "kotlin-stdlib"}, m182k = 5, m181mv = {1, 6, 0}, m179xi = 49, m178xs = "kotlin/time/DurationUnitKt")
/* loaded from: classes5.dex */
class DurationUnitKt__DurationUnitKt extends DurationUnitKt__DurationUnitJvmKt {

    /* compiled from: DurationUnit.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes5.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[DurationUnitJvm.values().length];
            r0[DurationUnitJvm.NANOSECONDS.ordinal()] = 1;
            r0[DurationUnitJvm.MICROSECONDS.ordinal()] = 2;
            r0[DurationUnitJvm.MILLISECONDS.ordinal()] = 3;
            r0[DurationUnitJvm.SECONDS.ordinal()] = 4;
            r0[DurationUnitJvm.MINUTES.ordinal()] = 5;
            r0[DurationUnitJvm.HOURS.ordinal()] = 6;
            r0[DurationUnitJvm.DAYS.ordinal()] = 7;
            $EnumSwitchMapping$0 = r0;
        }
    }

    public static final String shortName(DurationUnitJvm durationUnitJvm) {
        Intrinsics.checkNotNullParameter(durationUnitJvm, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[durationUnitJvm.ordinal()]) {
            case 1:
                return "ns";
            case 2:
                return "us";
            case 3:
                return "ms";
            case 4:
                return "s";
            case 5:
                return "m";
            case 6:
                return "h";
            case 7:
                return "d";
            default:
                throw new IllegalStateException(("Unknown unit: " + durationUnitJvm).toString());
        }
    }

    public static final DurationUnitJvm durationUnitByShortName(String shortName) {
        Intrinsics.checkNotNullParameter(shortName, "shortName");
        int hashCode = shortName.hashCode();
        if (hashCode != 100) {
            if (hashCode != 104) {
                if (hashCode != 109) {
                    if (hashCode != 115) {
                        if (hashCode != 3494) {
                            if (hashCode != 3525) {
                                if (hashCode == 3742 && shortName.equals("us")) {
                                    return DurationUnitJvm.MICROSECONDS;
                                }
                            } else if (shortName.equals("ns")) {
                                return DurationUnitJvm.NANOSECONDS;
                            }
                        } else if (shortName.equals("ms")) {
                            return DurationUnitJvm.MILLISECONDS;
                        }
                    } else if (shortName.equals("s")) {
                        return DurationUnitJvm.SECONDS;
                    }
                } else if (shortName.equals("m")) {
                    return DurationUnitJvm.MINUTES;
                }
            } else if (shortName.equals("h")) {
                return DurationUnitJvm.HOURS;
            }
        } else if (shortName.equals("d")) {
            return DurationUnitJvm.DAYS;
        }
        throw new IllegalArgumentException("Unknown duration unit short name: " + shortName);
    }

    public static final DurationUnitJvm durationUnitByIsoChar(char c, boolean z) {
        if (!z) {
            if (c == 'D') {
                return DurationUnitJvm.DAYS;
            }
            throw new IllegalArgumentException("Invalid or unsupported duration ISO non-time unit: " + c);
        } else if (c == 'H') {
            return DurationUnitJvm.HOURS;
        } else {
            if (c == 'M') {
                return DurationUnitJvm.MINUTES;
            }
            if (c == 'S') {
                return DurationUnitJvm.SECONDS;
            }
            throw new IllegalArgumentException("Invalid duration ISO time unit: " + c);
        }
    }
}
