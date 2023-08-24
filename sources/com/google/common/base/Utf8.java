package com.google.common.base;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public final class Utf8 {
    public static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int r1 = 0;
        while (r1 < length && charSequence.charAt(r1) < 128) {
            r1++;
        }
        int r2 = length;
        while (true) {
            if (r1 < length) {
                char charAt = charSequence.charAt(r1);
                if (charAt >= 2048) {
                    r2 += encodedLengthGeneral(charSequence, r1);
                    break;
                }
                r2 += (127 - charAt) >>> 31;
                r1++;
            } else {
                break;
            }
        }
        if (r2 >= length) {
            return r2;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(r2 + 4294967296L);
        throw new IllegalArgumentException(sb.toString());
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int r5) {
        int length = charSequence.length();
        int r1 = 0;
        while (r5 < length) {
            char charAt = charSequence.charAt(r5);
            if (charAt < 2048) {
                r1 += (127 - charAt) >>> 31;
            } else {
                r1 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, r5) == charAt) {
                        throw new IllegalArgumentException(unpairedSurrogateMsg(r5));
                    }
                    r5++;
                }
            }
            r5++;
        }
        return r1;
    }

    public static boolean isWellFormed(byte[] bArr) {
        return isWellFormed(bArr, 0, bArr.length);
    }

    public static boolean isWellFormed(byte[] bArr, int r2, int r3) {
        int r32 = r3 + r2;
        Preconditions.checkPositionIndexes(r2, r32, bArr.length);
        while (r2 < r32) {
            if (bArr[r2] < 0) {
                return isWellFormedSlowPath(bArr, r2, r32);
            }
            r2++;
        }
        return true;
    }

    private static boolean isWellFormedSlowPath(byte[] bArr, int r7, int r8) {
        byte b;
        while (r7 < r8) {
            int r0 = r7 + 1;
            byte b2 = bArr[r7];
            if (b2 < 0) {
                if (b2 < -32) {
                    if (r0 != r8 && b2 >= -62) {
                        r7 = r0 + 1;
                        if (bArr[r0] > -65) {
                        }
                    }
                    return false;
                } else if (b2 < -16) {
                    int r4 = r0 + 1;
                    if (r4 < r8 && (b = bArr[r0]) <= -65 && ((b2 != -32 || b >= -96) && (b2 != -19 || -96 > b))) {
                        r7 = r4 + 1;
                        if (bArr[r4] > -65) {
                        }
                    }
                    return false;
                } else if (r0 + 2 >= r8) {
                    return false;
                } else {
                    int r1 = r0 + 1;
                    byte b3 = bArr[r0];
                    if (b3 <= -65 && (((b2 << Ascii.f1122FS) + (b3 + 112)) >> 30) == 0) {
                        int r72 = r1 + 1;
                        if (bArr[r1] <= -65) {
                            r0 = r72 + 1;
                            if (bArr[r72] > -65) {
                            }
                        }
                    }
                    return false;
                }
            }
            r7 = r0;
        }
        return true;
    }

    private static String unpairedSurrogateMsg(int r2) {
        StringBuilder sb = new StringBuilder(39);
        sb.append("Unpaired surrogate at index ");
        sb.append(r2);
        return sb.toString();
    }

    private Utf8() {
    }
}
