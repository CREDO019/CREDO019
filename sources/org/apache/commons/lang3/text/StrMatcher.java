package org.apache.commons.lang3.text;

import java.util.Arrays;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;

@Deprecated
/* loaded from: classes5.dex */
public abstract class StrMatcher {
    private static final StrMatcher COMMA_MATCHER = new CharMatcher(',');
    private static final StrMatcher TAB_MATCHER = new CharMatcher('\t');
    private static final StrMatcher SPACE_MATCHER = new CharMatcher(' ');
    private static final StrMatcher SPLIT_MATCHER = new CharSetMatcher(" \t\n\r\f".toCharArray());
    private static final StrMatcher TRIM_MATCHER = new TrimMatcher();
    private static final StrMatcher SINGLE_QUOTE_MATCHER = new CharMatcher('\'');
    private static final StrMatcher DOUBLE_QUOTE_MATCHER = new CharMatcher(Typography.quote);
    private static final StrMatcher QUOTE_MATCHER = new CharSetMatcher("'\"".toCharArray());
    private static final StrMatcher NONE_MATCHER = new NoMatcher();

    public abstract int isMatch(char[] cArr, int r2, int r3, int r4);

    public static StrMatcher commaMatcher() {
        return COMMA_MATCHER;
    }

    public static StrMatcher tabMatcher() {
        return TAB_MATCHER;
    }

    public static StrMatcher spaceMatcher() {
        return SPACE_MATCHER;
    }

    public static StrMatcher splitMatcher() {
        return SPLIT_MATCHER;
    }

    public static StrMatcher trimMatcher() {
        return TRIM_MATCHER;
    }

    public static StrMatcher singleQuoteMatcher() {
        return SINGLE_QUOTE_MATCHER;
    }

    public static StrMatcher doubleQuoteMatcher() {
        return DOUBLE_QUOTE_MATCHER;
    }

    public static StrMatcher quoteMatcher() {
        return QUOTE_MATCHER;
    }

    public static StrMatcher noneMatcher() {
        return NONE_MATCHER;
    }

    public static StrMatcher charMatcher(char c) {
        return new CharMatcher(c);
    }

    public static StrMatcher charSetMatcher(char... cArr) {
        if (cArr == null || cArr.length == 0) {
            return NONE_MATCHER;
        }
        if (cArr.length == 1) {
            return new CharMatcher(cArr[0]);
        }
        return new CharSetMatcher(cArr);
    }

    public static StrMatcher charSetMatcher(String str) {
        if (StringUtils.isEmpty(str)) {
            return NONE_MATCHER;
        }
        if (str.length() == 1) {
            return new CharMatcher(str.charAt(0));
        }
        return new CharSetMatcher(str.toCharArray());
    }

    public static StrMatcher stringMatcher(String str) {
        if (StringUtils.isEmpty(str)) {
            return NONE_MATCHER;
        }
        return new StringMatcher(str);
    }

    protected StrMatcher() {
    }

    public int isMatch(char[] cArr, int r4) {
        return isMatch(cArr, r4, 0, cArr.length);
    }

    /* loaded from: classes5.dex */
    static final class CharSetMatcher extends StrMatcher {
        private final char[] chars;

        CharSetMatcher(char[] cArr) {
            char[] cArr2 = (char[]) cArr.clone();
            this.chars = cArr2;
            Arrays.sort(cArr2);
        }

        @Override // org.apache.commons.lang3.text.StrMatcher
        public int isMatch(char[] cArr, int r2, int r3, int r4) {
            return Arrays.binarySearch(this.chars, cArr[r2]) >= 0 ? 1 : 0;
        }
    }

    /* loaded from: classes5.dex */
    static final class CharMatcher extends StrMatcher {

        /* renamed from: ch */
        private final char f1573ch;

        CharMatcher(char c) {
            this.f1573ch = c;
        }

        @Override // org.apache.commons.lang3.text.StrMatcher
        public int isMatch(char[] cArr, int r2, int r3, int r4) {
            return this.f1573ch == cArr[r2] ? 1 : 0;
        }
    }

    /* loaded from: classes5.dex */
    static final class StringMatcher extends StrMatcher {
        private final char[] chars;

        StringMatcher(String str) {
            this.chars = str.toCharArray();
        }

        @Override // org.apache.commons.lang3.text.StrMatcher
        public int isMatch(char[] cArr, int r5, int r6, int r7) {
            int length = this.chars.length;
            if (r5 + length > r7) {
                return 0;
            }
            int r72 = 0;
            while (true) {
                char[] cArr2 = this.chars;
                if (r72 >= cArr2.length) {
                    return length;
                }
                if (cArr2[r72] != cArr[r5]) {
                    return 0;
                }
                r72++;
                r5++;
            }
        }

        public String toString() {
            return super.toString() + ' ' + Arrays.toString(this.chars);
        }
    }

    /* loaded from: classes5.dex */
    static final class NoMatcher extends StrMatcher {
        @Override // org.apache.commons.lang3.text.StrMatcher
        public int isMatch(char[] cArr, int r2, int r3, int r4) {
            return 0;
        }

        NoMatcher() {
        }
    }

    /* loaded from: classes5.dex */
    static final class TrimMatcher extends StrMatcher {
        TrimMatcher() {
        }

        @Override // org.apache.commons.lang3.text.StrMatcher
        public int isMatch(char[] cArr, int r2, int r3, int r4) {
            return cArr[r2] <= ' ' ? 1 : 0;
        }
    }
}
