package org.apache.logging.log4j.util;

import java.util.Locale;

/* loaded from: classes5.dex */
public final class EnglishEnums {
    private EnglishEnums() {
    }

    public static <T extends Enum<T>> T valueOf(Class<T> cls, String str) {
        return (T) valueOf(cls, str, null);
    }

    public static <T extends Enum<T>> T valueOf(Class<T> cls, String str, T t) {
        return str == null ? t : (T) Enum.valueOf(cls, str.toUpperCase(Locale.ENGLISH));
    }
}
