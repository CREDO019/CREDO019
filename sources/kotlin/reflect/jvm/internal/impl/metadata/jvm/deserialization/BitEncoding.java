package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

/* loaded from: classes5.dex */
public class BitEncoding {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean FORCE_8TO7_ENCODING;

    private static /* synthetic */ void $$$reportNull$$$0(int r19) {
        String str = (r19 == 1 || r19 == 3 || r19 == 6 || r19 == 8 || r19 == 10 || r19 == 12 || r19 == 14) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(r19 == 1 || r19 == 3 || r19 == 6 || r19 == 8 || r19 == 10 || r19 == 12 || r19 == 14) ? 2 : 3];
        if (r19 == 1 || r19 == 3 || r19 == 6 || r19 == 8 || r19 == 10 || r19 == 12 || r19 == 14) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/BitEncoding";
        } else {
            objArr[0] = "data";
        }
        if (r19 == 1) {
            objArr[1] = "encodeBytes";
        } else if (r19 == 3) {
            objArr[1] = "encode8to7";
        } else if (r19 == 6) {
            objArr[1] = "splitBytesToStringArray";
        } else if (r19 == 8) {
            objArr[1] = "decodeBytes";
        } else if (r19 == 10) {
            objArr[1] = "dropMarker";
        } else if (r19 == 12) {
            objArr[1] = "combineStringArrayIntoBytes";
        } else if (r19 != 14) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/BitEncoding";
        } else {
            objArr[1] = "decode7to8";
        }
        switch (r19) {
            case 1:
            case 3:
            case 6:
            case 8:
            case 10:
            case 12:
            case 14:
                break;
            case 2:
                objArr[2] = "encode8to7";
                break;
            case 4:
                objArr[2] = "addModuloByte";
                break;
            case 5:
                objArr[2] = "splitBytesToStringArray";
                break;
            case 7:
                objArr[2] = "decodeBytes";
                break;
            case 9:
                objArr[2] = "dropMarker";
                break;
            case 11:
                objArr[2] = "combineStringArrayIntoBytes";
                break;
            case 13:
                objArr[2] = "decode7to8";
                break;
            default:
                objArr[2] = "encodeBytes";
                break;
        }
        String format = String.format(str, objArr);
        if (r19 != 1 && r19 != 3 && r19 != 6 && r19 != 8 && r19 != 10 && r19 != 12 && r19 != 14) {
            throw new IllegalArgumentException(format);
        }
        throw new IllegalStateException(format);
    }

    static {
        String str;
        try {
            str = System.getProperty("kotlin.jvm.serialization.use8to7");
        } catch (SecurityException unused) {
            str = null;
        }
        FORCE_8TO7_ENCODING = "true".equals(str);
    }

    private BitEncoding() {
    }

    private static void addModuloByte(byte[] bArr, int r4) {
        if (bArr == null) {
            $$$reportNull$$$0(4);
        }
        int length = bArr.length;
        for (int r0 = 0; r0 < length; r0++) {
            bArr[r0] = (byte) ((bArr[r0] + r4) & 127);
        }
    }

    public static byte[] decodeBytes(String[] strArr) {
        if (strArr == null) {
            $$$reportNull$$$0(7);
        }
        if (strArr.length > 0 && !strArr[0].isEmpty()) {
            char charAt = strArr[0].charAt(0);
            if (charAt == 0) {
                byte[] stringsToBytes = utfEncoding.stringsToBytes(dropMarker(strArr));
                if (stringsToBytes == null) {
                    $$$reportNull$$$0(8);
                }
                return stringsToBytes;
            } else if (charAt == 65535) {
                strArr = dropMarker(strArr);
            }
        }
        byte[] combineStringArrayIntoBytes = combineStringArrayIntoBytes(strArr);
        addModuloByte(combineStringArrayIntoBytes, 127);
        return decode7to8(combineStringArrayIntoBytes);
    }

    private static String[] dropMarker(String[] strArr) {
        if (strArr == null) {
            $$$reportNull$$$0(9);
        }
        String[] strArr2 = (String[]) strArr.clone();
        strArr2[0] = strArr2[0].substring(1);
        if (strArr2 == null) {
            $$$reportNull$$$0(10);
        }
        return strArr2;
    }

    private static byte[] combineStringArrayIntoBytes(String[] strArr) {
        if (strArr == null) {
            $$$reportNull$$$0(11);
        }
        int r3 = 0;
        for (String str : strArr) {
            r3 += str.length();
        }
        byte[] bArr = new byte[r3];
        int r4 = 0;
        for (String str2 : strArr) {
            int length = str2.length();
            int r7 = 0;
            while (r7 < length) {
                bArr[r4] = (byte) str2.charAt(r7);
                r7++;
                r4++;
            }
        }
        return bArr;
    }

    private static byte[] decode7to8(byte[] bArr) {
        if (bArr == null) {
            $$$reportNull$$$0(13);
        }
        int length = (bArr.length * 7) / 8;
        byte[] bArr2 = new byte[length];
        int r4 = 0;
        int r5 = 0;
        for (int r3 = 0; r3 < length; r3++) {
            r4++;
            int r9 = r5 + 1;
            bArr2[r3] = (byte) (((bArr[r4] & 255) >>> r5) + ((bArr[r4] & ((1 << r9) - 1)) << (7 - r5)));
            if (r5 == 6) {
                r4++;
                r5 = 0;
            } else {
                r5 = r9;
            }
        }
        return bArr2;
    }
}
