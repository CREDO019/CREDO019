package org.apache.logging.log4j;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.codec.language.p027bm.Rule;
import org.apache.logging.log4j.spi.StandardLevel;

/* loaded from: classes5.dex */
public final class Level implements Comparable<Level>, Serializable {
    public static final String CATEGORY = "Level";
    private static final long serialVersionUID = 1581082;
    private final int intLevel;
    private final String name;
    private final StandardLevel standardLevel;
    private static final ConcurrentMap<String, Level> levels = new ConcurrentHashMap();
    public static final Level OFF = new Level("OFF", StandardLevel.OFF.intLevel());
    public static final Level FATAL = new Level("FATAL", StandardLevel.FATAL.intLevel());
    public static final Level ERROR = new Level("ERROR", StandardLevel.ERROR.intLevel());
    public static final Level WARN = new Level("WARN", StandardLevel.WARN.intLevel());
    public static final Level INFO = new Level("INFO", StandardLevel.INFO.intLevel());
    public static final Level DEBUG = new Level("DEBUG", StandardLevel.DEBUG.intLevel());
    public static final Level TRACE = new Level("TRACE", StandardLevel.TRACE.intLevel());
    public static final Level ALL = new Level(Rule.ALL, StandardLevel.ALL.intLevel());

    private Level(String str, int r4) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Illegal null Level constant");
        }
        if (r4 < 0) {
            throw new IllegalArgumentException("Illegal Level int less than zero.");
        }
        this.name = str;
        this.intLevel = r4;
        this.standardLevel = StandardLevel.getStandardLevel(r4);
        if (levels.putIfAbsent(str, this) == null) {
            return;
        }
        throw new IllegalStateException("Level " + str + " has already been defined.");
    }

    public int intLevel() {
        return this.intLevel;
    }

    public StandardLevel getStandardLevel() {
        return this.standardLevel;
    }

    public boolean isLessSpecificThan(Level level) {
        return this.intLevel >= level.intLevel;
    }

    public boolean isMoreSpecificThan(Level level) {
        return this.intLevel <= level.intLevel;
    }

    public Level clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    @Override // java.lang.Comparable
    public int compareTo(Level level) {
        int r0 = this.intLevel;
        int r2 = level.intLevel;
        if (r0 < r2) {
            return -1;
        }
        return r0 > r2 ? 1 : 0;
    }

    public boolean equals(Object obj) {
        return (obj instanceof Level) && obj == this;
    }

    public Class<Level> getDeclaringClass() {
        return Level.class;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String name() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public static Level forName(String str, int r2) {
        Level level = levels.get(str);
        if (level != null) {
            return level;
        }
        try {
            return new Level(str, r2);
        } catch (IllegalStateException unused) {
            return levels.get(str);
        }
    }

    public static Level getLevel(String str) {
        return levels.get(str);
    }

    public static Level toLevel(String str) {
        return toLevel(str, DEBUG);
    }

    public static Level toLevel(String str, Level level) {
        Level level2;
        return (str == null || (level2 = levels.get(str.toUpperCase(Locale.ENGLISH))) == null) ? level : level2;
    }

    public static Level[] values() {
        Collection<Level> values = levels.values();
        return (Level[]) values.toArray(new Level[values.size()]);
    }

    public static Level valueOf(String str) {
        Objects.requireNonNull(str, "No level name given.");
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        ConcurrentMap<String, Level> concurrentMap = levels;
        if (concurrentMap.containsKey(upperCase)) {
            return concurrentMap.get(upperCase);
        }
        throw new IllegalArgumentException("Unknown level constant [" + upperCase + "].");
    }

    public static <T extends Enum<T>> T valueOf(Class<T> cls, String str) {
        return (T) Enum.valueOf(cls, str);
    }

    protected Object readResolve() {
        return valueOf(this.name);
    }
}
