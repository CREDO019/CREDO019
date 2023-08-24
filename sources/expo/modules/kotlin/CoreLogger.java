package expo.modules.kotlin;

import expo.modules.core.logging.Logger;
import expo.modules.core.logging.LoggerOptions;
import kotlin.Metadata;

@Metadata(m184d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, m183d2 = {"logger", "Lexpo/modules/core/logging/Logger;", "getLogger", "()Lexpo/modules/core/logging/Logger;", "expo-modules-core_release"}, m182k = 2, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.kotlin.CoreLoggerKt */
/* loaded from: classes4.dex */
public final class CoreLogger {
    private static final Logger logger = new Logger("ExpoModulesCore", null, LoggerOptions.Companion.getLogToOS());

    public static final Logger getLogger() {
        return logger;
    }
}
