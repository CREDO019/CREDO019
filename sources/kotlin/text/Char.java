package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\f\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\f\u0010\u0004\u001a\u00020\u0002*\u00020\u0001H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0007\u001a\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0006\u001a\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0002\u0010\u0007\u001a\u001c\u0010\b\u001a\u00020\t*\u00020\u00012\u0006\u0010\n\u001a\u00020\u00012\b\b\u0002\u0010\u000b\u001a\u00020\t\u001a\n\u0010\f\u001a\u00020\t*\u00020\u0001\u001a\u0015\u0010\r\u001a\u00020\u000e*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000eH\u0087\n\u001a\f\u0010\u000f\u001a\u00020\u000e*\u00020\u0001H\u0007¨\u0006\u0010"}, m183d2 = {"digitToChar", "", "", "radix", "digitToInt", "digitToIntOrNull", "(C)Ljava/lang/Integer;", "(CI)Ljava/lang/Integer;", "equals", "", "other", "ignoreCase", "isSurrogate", "plus", "", "titlecase", "kotlin-stdlib"}, m182k = 5, m181mv = {1, 6, 0}, m179xi = 49, m178xs = "kotlin/text/CharsKt")
/* renamed from: kotlin.text.CharsKt__CharKt */
/* loaded from: classes5.dex */
class Char extends CharJVM {
    public static final boolean isSurrogate(char c) {
        return 55296 <= c && c < 57344;
    }

    public static final int digitToInt(char c) {
        int digitOf = CharsKt.digitOf(c, 10);
        if (digitOf >= 0) {
            return digitOf;
        }
        throw new IllegalArgumentException("Char " + c + " is not a decimal digit");
    }

    public static final int digitToInt(char c, int r4) {
        Integer digitToIntOrNull = CharsKt.digitToIntOrNull(c, r4);
        if (digitToIntOrNull != null) {
            return digitToIntOrNull.intValue();
        }
        throw new IllegalArgumentException("Char " + c + " is not a digit in the given radix=" + r4);
    }

    public static final Integer digitToIntOrNull(char c) {
        Integer valueOf = Integer.valueOf(CharsKt.digitOf(c, 10));
        if (valueOf.intValue() >= 0) {
            return valueOf;
        }
        return null;
    }

    public static final Integer digitToIntOrNull(char c, int r1) {
        CharsKt.checkRadix(r1);
        Integer valueOf = Integer.valueOf(CharsKt.digitOf(c, r1));
        if (valueOf.intValue() >= 0) {
            return valueOf;
        }
        return null;
    }

    public static final char digitToChar(int r3) {
        boolean z = false;
        if (r3 >= 0 && r3 < 10) {
            z = true;
        }
        if (z) {
            return (char) (r3 + 48);
        }
        throw new IllegalArgumentException("Int " + r3 + " is not a decimal digit");
    }

    public static final char digitToChar(int r3, int r4) {
        boolean z = false;
        if (2 <= r4 && r4 < 37) {
            z = true;
        }
        if (!z) {
            throw new IllegalArgumentException("Invalid radix: " + r4 + ". Valid radix values are in range 2..36");
        } else if (r3 >= 0 && r3 < r4) {
            return (char) (r3 < 10 ? r3 + 48 : ((char) (r3 + 65)) - '\n');
        } else {
            throw new IllegalArgumentException("Digit " + r3 + " does not represent a valid digit in radix " + r4);
        }
    }

    public static final String titlecase(char c) {
        return _OneToManyTitlecaseMappings.titlecaseImpl(c);
    }

    private static final String plus(char c, String other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return c + other;
    }

    public static /* synthetic */ boolean equals$default(char c, char c2, boolean z, int r3, Object obj) {
        if ((r3 & 2) != 0) {
            z = false;
        }
        return CharsKt.equals(c, c2, z);
    }

    public static final boolean equals(char c, char c2, boolean z) {
        if (c == c2) {
            return true;
        }
        if (z) {
            char upperCase = Character.toUpperCase(c);
            char upperCase2 = Character.toUpperCase(c2);
            return upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2);
        }
        return false;
    }
}
