package expo.modules.core.logging;

import java.util.BitSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoggerOptions.kt */
@Metadata(m184d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \b2\u00020\u0001:\u0001\bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, m183d2 = {"Lexpo/modules/core/logging/LoggerOptions;", "", "bitSet", "Ljava/util/BitSet;", "(Ljava/util/BitSet;)V", "contains", "", "other", "Companion", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class LoggerOptions {
    public static final Companion Companion = new Companion(null);
    private static final LoggerOptions logToFile;
    private static final LoggerOptions logToOS;
    private final BitSet bitSet;

    public LoggerOptions(BitSet bitSet) {
        Intrinsics.checkNotNullParameter(bitSet, "bitSet");
        this.bitSet = bitSet;
    }

    public final boolean contains(LoggerOptions other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return this.bitSet.intersects(other.bitSet);
    }

    /* compiled from: LoggerOptions.kt */
    @Metadata(m184d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\t\u001a\u00020\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\f"}, m183d2 = {"Lexpo/modules/core/logging/LoggerOptions$Companion;", "", "()V", "logToFile", "Lexpo/modules/core/logging/LoggerOptions;", "getLogToFile", "()Lexpo/modules/core/logging/LoggerOptions;", "logToOS", "getLogToOS", "union", "loggerOptions", "", "expo-modules-core_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LoggerOptions getLogToOS() {
            return LoggerOptions.logToOS;
        }

        public final LoggerOptions getLogToFile() {
            return LoggerOptions.logToFile;
        }

        public final LoggerOptions union(List<LoggerOptions> loggerOptions) {
            Intrinsics.checkNotNullParameter(loggerOptions, "loggerOptions");
            BitSet result = BitSet.valueOf(new long[]{0});
            for (LoggerOptions loggerOptions2 : loggerOptions) {
                result.or(loggerOptions2.bitSet);
            }
            Intrinsics.checkNotNullExpressionValue(result, "result");
            return new LoggerOptions(result);
        }
    }

    static {
        BitSet valueOf = BitSet.valueOf(new long[]{1});
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(longArrayOf(1L))");
        logToOS = new LoggerOptions(valueOf);
        BitSet valueOf2 = BitSet.valueOf(new long[]{2});
        Intrinsics.checkNotNullExpressionValue(valueOf2, "valueOf(longArrayOf(2L))");
        logToFile = new LoggerOptions(valueOf2);
    }
}
