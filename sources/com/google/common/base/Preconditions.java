package com.google.common.base;

import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean z, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, objArr));
        }
    }

    public static void checkArgument(boolean z, String str, char c) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c)));
        }
    }

    public static void checkArgument(boolean z, String str, int r4) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(r4)));
        }
    }

    public static void checkArgument(boolean z, String str, long j) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j)));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj));
        }
    }

    public static void checkArgument(boolean z, String str, char c, char c2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c), Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, String str, char c, int r5) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c), Integer.valueOf(r5)));
        }
    }

    public static void checkArgument(boolean z, String str, char c, long j) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c), Long.valueOf(j)));
        }
    }

    public static void checkArgument(boolean z, String str, char c, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c), obj));
        }
    }

    public static void checkArgument(boolean z, String str, int r4, char c) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(r4), Character.valueOf(c)));
        }
    }

    public static void checkArgument(boolean z, String str, int r4, int r5) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(r4), Integer.valueOf(r5)));
        }
    }

    public static void checkArgument(boolean z, String str, int r4, long j) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(r4), Long.valueOf(j)));
        }
    }

    public static void checkArgument(boolean z, String str, int r4, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(r4), obj));
        }
    }

    public static void checkArgument(boolean z, String str, long j, char c) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j), Character.valueOf(c)));
        }
    }

    public static void checkArgument(boolean z, String str, long j, int r6) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j), Integer.valueOf(r6)));
        }
    }

    public static void checkArgument(boolean z, String str, long j, long j2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j), Long.valueOf(j2)));
        }
    }

    public static void checkArgument(boolean z, String str, long j, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j), obj));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj, char c) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, Character.valueOf(c)));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj, int r5) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, Integer.valueOf(r5)));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj, long j) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, Long.valueOf(j)));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, obj2));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, obj2, obj3));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3, @CheckForNull Object obj4) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
        }
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean z, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void checkState(boolean z, @CheckForNull String str, @CheckForNull Object... objArr) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, objArr));
        }
    }

    public static void checkState(boolean z, String str, char c) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c)));
        }
    }

    public static void checkState(boolean z, String str, int r4) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(r4)));
        }
    }

    public static void checkState(boolean z, String str, long j) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j)));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj));
        }
    }

    public static void checkState(boolean z, String str, char c, char c2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c), Character.valueOf(c2)));
        }
    }

    public static void checkState(boolean z, String str, char c, int r5) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c), Integer.valueOf(r5)));
        }
    }

    public static void checkState(boolean z, String str, char c, long j) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c), Long.valueOf(j)));
        }
    }

    public static void checkState(boolean z, String str, char c, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c), obj));
        }
    }

    public static void checkState(boolean z, String str, int r4, char c) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(r4), Character.valueOf(c)));
        }
    }

    public static void checkState(boolean z, String str, int r4, int r5) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(r4), Integer.valueOf(r5)));
        }
    }

    public static void checkState(boolean z, String str, int r4, long j) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(r4), Long.valueOf(j)));
        }
    }

    public static void checkState(boolean z, String str, int r4, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(r4), obj));
        }
    }

    public static void checkState(boolean z, String str, long j, char c) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j), Character.valueOf(c)));
        }
    }

    public static void checkState(boolean z, String str, long j, int r6) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j), Integer.valueOf(r6)));
        }
    }

    public static void checkState(boolean z, String str, long j, long j2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j), Long.valueOf(j2)));
        }
    }

    public static void checkState(boolean z, String str, long j, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j), obj));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj, char c) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, Character.valueOf(c)));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj, int r5) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, Integer.valueOf(r5)));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj, long j) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, Long.valueOf(j)));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, obj2));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, obj2, obj3));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3, @CheckForNull Object obj4) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
        }
    }

    public static <T> T checkNotNull(@CheckForNull T t) {
        java.util.Objects.requireNonNull(t);
        return t;
    }

    public static <T> T checkNotNull(@CheckForNull T t, @CheckForNull Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, objArr));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, int r4) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(r4)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, char c, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c), Character.valueOf(c2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, char c, int r5) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c), Integer.valueOf(r5)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, char c, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c), Long.valueOf(j)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, char c, @CheckForNull Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c), obj));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, int r4, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(r4), Character.valueOf(c)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, int r4, int r5) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(r4), Integer.valueOf(r5)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, int r4, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(r4), Long.valueOf(j)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, int r4, @CheckForNull Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(r4), obj));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, long j, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j), Character.valueOf(c)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, long j, int r6) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j), Integer.valueOf(r6)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, long j, long j2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j), Long.valueOf(j2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, long j, @CheckForNull Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j), obj));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj, char c) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, Character.valueOf(c)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj, int r5) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, Integer.valueOf(r5)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj, long j) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, Long.valueOf(j)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj, @CheckForNull Object obj2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, obj2));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, obj2, obj3));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3, @CheckForNull Object obj4) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
    }

    public static int checkElementIndex(int r1, int r2) {
        return checkElementIndex(r1, r2, "index");
    }

    public static int checkElementIndex(int r1, int r2, String str) {
        if (r1 < 0 || r1 >= r2) {
            throw new IndexOutOfBoundsException(badElementIndex(r1, r2, str));
        }
        return r1;
    }

    private static String badElementIndex(int r4, int r5, String str) {
        if (r4 < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", str, Integer.valueOf(r4));
        }
        if (r5 >= 0) {
            return Strings.lenientFormat("%s (%s) must be less than size (%s)", str, Integer.valueOf(r4), Integer.valueOf(r5));
        }
        StringBuilder sb = new StringBuilder(26);
        sb.append("negative size: ");
        sb.append(r5);
        throw new IllegalArgumentException(sb.toString());
    }

    public static int checkPositionIndex(int r1, int r2) {
        return checkPositionIndex(r1, r2, "index");
    }

    public static int checkPositionIndex(int r1, int r2, String str) {
        if (r1 < 0 || r1 > r2) {
            throw new IndexOutOfBoundsException(badPositionIndex(r1, r2, str));
        }
        return r1;
    }

    private static String badPositionIndex(int r4, int r5, String str) {
        if (r4 < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", str, Integer.valueOf(r4));
        }
        if (r5 >= 0) {
            return Strings.lenientFormat("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(r4), Integer.valueOf(r5));
        }
        StringBuilder sb = new StringBuilder(26);
        sb.append("negative size: ");
        sb.append(r5);
        throw new IllegalArgumentException(sb.toString());
    }

    public static void checkPositionIndexes(int r1, int r2, int r3) {
        if (r1 < 0 || r2 < r1 || r2 > r3) {
            throw new IndexOutOfBoundsException(badPositionIndexes(r1, r2, r3));
        }
    }

    private static String badPositionIndexes(int r1, int r2, int r3) {
        if (r1 < 0 || r1 > r3) {
            return badPositionIndex(r1, r3, "start index");
        }
        return (r2 < 0 || r2 > r3) ? badPositionIndex(r2, r3, "end index") : Strings.lenientFormat("end index (%s) must not be less than start index (%s)", Integer.valueOf(r2), Integer.valueOf(r1));
    }
}
