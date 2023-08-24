package org.apache.commons.p028io;

import java.io.Serializable;

/* renamed from: org.apache.commons.io.IOCase */
/* loaded from: classes5.dex */
public enum IOCase implements Serializable {
    SENSITIVE("Sensitive", true),
    INSENSITIVE("Insensitive", false),
    SYSTEM("System", !FilenameUtils.isSystemWindows());
    
    private static final long serialVersionUID = -6343169151696340687L;
    private final String name;
    private final transient boolean sensitive;

    public static IOCase forName(String str) {
        IOCase[] values;
        for (IOCase iOCase : values()) {
            if (iOCase.getName().equals(str)) {
                return iOCase;
            }
        }
        throw new IllegalArgumentException("Invalid IOCase name: " + str);
    }

    IOCase(String str, boolean z) {
        this.name = str;
        this.sensitive = z;
    }

    private Object readResolve() {
        return forName(this.name);
    }

    public String getName() {
        return this.name;
    }

    public boolean isCaseSensitive() {
        return this.sensitive;
    }

    public int checkCompareTo(String str, String str2) {
        if (str == null || str2 == null) {
            throw new NullPointerException("The strings must not be null");
        }
        return this.sensitive ? str.compareTo(str2) : str.compareToIgnoreCase(str2);
    }

    public boolean checkEquals(String str, String str2) {
        if (str == null || str2 == null) {
            throw new NullPointerException("The strings must not be null");
        }
        return this.sensitive ? str.equals(str2) : str.equalsIgnoreCase(str2);
    }

    public boolean checkStartsWith(String str, String str2) {
        return str.regionMatches(!this.sensitive, 0, str2, 0, str2.length());
    }

    public boolean checkEndsWith(String str, String str2) {
        int length = str2.length();
        return str.regionMatches(!this.sensitive, str.length() - length, str2, 0, length);
    }

    public int checkIndexOf(String str, int r4, String str2) {
        int length = str.length() - str2.length();
        if (length >= r4) {
            while (r4 <= length) {
                if (checkRegionMatches(str, r4, str2)) {
                    return r4;
                }
                r4++;
            }
            return -1;
        }
        return -1;
    }

    public boolean checkRegionMatches(String str, int r9, String str2) {
        return str.regionMatches(!this.sensitive, r9, str2, 0, str2.length());
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}