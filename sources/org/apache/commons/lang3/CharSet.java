package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class CharSet implements Serializable {
    public static final CharSet ASCII_ALPHA;
    public static final CharSet ASCII_ALPHA_LOWER;
    public static final CharSet ASCII_ALPHA_UPPER;
    public static final CharSet ASCII_NUMERIC;
    protected static final Map<String, CharSet> COMMON;
    public static final CharSet EMPTY;
    private static final long serialVersionUID = 5947847346149275958L;
    private final Set<CharRange> set = Collections.synchronizedSet(new HashSet());

    static {
        CharSet charSet = new CharSet(null);
        EMPTY = charSet;
        CharSet charSet2 = new CharSet("a-zA-Z");
        ASCII_ALPHA = charSet2;
        CharSet charSet3 = new CharSet("a-z");
        ASCII_ALPHA_LOWER = charSet3;
        CharSet charSet4 = new CharSet("A-Z");
        ASCII_ALPHA_UPPER = charSet4;
        CharSet charSet5 = new CharSet("0-9");
        ASCII_NUMERIC = charSet5;
        Map<String, CharSet> synchronizedMap = Collections.synchronizedMap(new HashMap());
        COMMON = synchronizedMap;
        synchronizedMap.put(null, charSet);
        synchronizedMap.put("", charSet);
        synchronizedMap.put("a-zA-Z", charSet2);
        synchronizedMap.put("A-Za-z", charSet2);
        synchronizedMap.put("a-z", charSet3);
        synchronizedMap.put("A-Z", charSet4);
        synchronizedMap.put("0-9", charSet5);
    }

    public static CharSet getInstance(String... strArr) {
        CharSet charSet;
        if (strArr == null) {
            return null;
        }
        return (strArr.length != 1 || (charSet = COMMON.get(strArr[0])) == null) ? new CharSet(strArr) : charSet;
    }

    protected CharSet(String... strArr) {
        for (String str : strArr) {
            add(str);
        }
    }

    protected void add(String str) {
        if (str == null) {
            return;
        }
        int length = str.length();
        int r1 = 0;
        while (r1 < length) {
            int r2 = length - r1;
            if (r2 >= 4 && str.charAt(r1) == '^' && str.charAt(r1 + 2) == '-') {
                this.set.add(CharRange.isNotIn(str.charAt(r1 + 1), str.charAt(r1 + 3)));
                r1 += 4;
            } else if (r2 >= 3 && str.charAt(r1 + 1) == '-') {
                this.set.add(CharRange.isIn(str.charAt(r1), str.charAt(r1 + 2)));
                r1 += 3;
            } else if (r2 >= 2 && str.charAt(r1) == '^') {
                this.set.add(CharRange.isNot(str.charAt(r1 + 1)));
                r1 += 2;
            } else {
                this.set.add(CharRange.m140is(str.charAt(r1)));
                r1++;
            }
        }
    }

    CharRange[] getCharRanges() {
        Set<CharRange> set = this.set;
        return (CharRange[]) set.toArray(new CharRange[set.size()]);
    }

    public boolean contains(char c) {
        for (CharRange charRange : this.set) {
            if (charRange.contains(c)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CharSet) {
            return this.set.equals(((CharSet) obj).set);
        }
        return false;
    }

    public int hashCode() {
        return this.set.hashCode() + 89;
    }

    public String toString() {
        return this.set.toString();
    }
}
