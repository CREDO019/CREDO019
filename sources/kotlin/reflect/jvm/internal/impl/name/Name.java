package kotlin.reflect.jvm.internal.impl.name;

/* loaded from: classes5.dex */
public final class Name implements Comparable<Name> {
    private final String name;
    private final boolean special;

    private static /* synthetic */ void $$$reportNull$$$0(int r9) {
        String str = (r9 == 1 || r9 == 2 || r9 == 3 || r9 == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(r9 == 1 || r9 == 2 || r9 == 3 || r9 == 4) ? 2 : 3];
        if (r9 == 1 || r9 == 2 || r9 == 3 || r9 == 4) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/name/Name";
        } else {
            objArr[0] = "name";
        }
        if (r9 == 1) {
            objArr[1] = "asString";
        } else if (r9 == 2) {
            objArr[1] = "getIdentifier";
        } else if (r9 == 3 || r9 == 4) {
            objArr[1] = "asStringStripSpecialMarkers";
        } else {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/name/Name";
        }
        switch (r9) {
            case 1:
            case 2:
            case 3:
            case 4:
                break;
            case 5:
                objArr[2] = "identifier";
                break;
            case 6:
                objArr[2] = "isValidIdentifier";
                break;
            case 7:
                objArr[2] = "special";
                break;
            case 8:
                objArr[2] = "guessByFirstCharacter";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String format = String.format(str, objArr);
        if (r9 != 1 && r9 != 2 && r9 != 3 && r9 != 4) {
            throw new IllegalArgumentException(format);
        }
        throw new IllegalStateException(format);
    }

    private Name(String str, boolean z) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        this.name = str;
        this.special = z;
    }

    public String asString() {
        String str = this.name;
        if (str == null) {
            $$$reportNull$$$0(1);
        }
        return str;
    }

    public String getIdentifier() {
        if (this.special) {
            throw new IllegalStateException("not identifier: " + this);
        }
        String asString = asString();
        if (asString == null) {
            $$$reportNull$$$0(2);
        }
        return asString;
    }

    public boolean isSpecial() {
        return this.special;
    }

    @Override // java.lang.Comparable
    public int compareTo(Name name) {
        return this.name.compareTo(name.name);
    }

    public static Name identifier(String str) {
        if (str == null) {
            $$$reportNull$$$0(5);
        }
        return new Name(str, false);
    }

    public static boolean isValidIdentifier(String str) {
        if (str == null) {
            $$$reportNull$$$0(6);
        }
        if (str.isEmpty() || str.startsWith("<")) {
            return false;
        }
        for (int r0 = 0; r0 < str.length(); r0++) {
            char charAt = str.charAt(r0);
            if (charAt == '.' || charAt == '/' || charAt == '\\') {
                return false;
            }
        }
        return true;
    }

    public static Name special(String str) {
        if (str == null) {
            $$$reportNull$$$0(7);
        }
        if (!str.startsWith("<")) {
            throw new IllegalArgumentException("special name must start with '<': " + str);
        }
        return new Name(str, true);
    }

    public static Name guessByFirstCharacter(String str) {
        if (str == null) {
            $$$reportNull$$$0(8);
        }
        if (str.startsWith("<")) {
            return special(str);
        }
        return identifier(str);
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Name) {
            Name name = (Name) obj;
            return this.special == name.special && this.name.equals(name.name);
        }
        return false;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + (this.special ? 1 : 0);
    }
}
