package org.apache.commons.lang3;

import java.util.Random;

/* loaded from: classes5.dex */
public class RandomStringUtils {
    private static final Random RANDOM = new Random();

    public static String random(int r1) {
        return random(r1, false, false);
    }

    public static String randomAscii(int r3) {
        return random(r3, 32, 127, false, false);
    }

    public static String randomAscii(int r0, int r1) {
        return randomAscii(RandomUtils.nextInt(r0, r1));
    }

    public static String randomAlphabetic(int r2) {
        return random(r2, true, false);
    }

    public static String randomAlphabetic(int r0, int r1) {
        return randomAlphabetic(RandomUtils.nextInt(r0, r1));
    }

    public static String randomAlphanumeric(int r1) {
        return random(r1, true, true);
    }

    public static String randomAlphanumeric(int r0, int r1) {
        return randomAlphanumeric(RandomUtils.nextInt(r0, r1));
    }

    public static String randomGraph(int r3) {
        return random(r3, 33, 126, false, false);
    }

    public static String randomGraph(int r0, int r1) {
        return randomGraph(RandomUtils.nextInt(r0, r1));
    }

    public static String randomNumeric(int r2) {
        return random(r2, false, true);
    }

    public static String randomNumeric(int r0, int r1) {
        return randomNumeric(RandomUtils.nextInt(r0, r1));
    }

    public static String randomPrint(int r3) {
        return random(r3, 32, 126, false, false);
    }

    public static String randomPrint(int r0, int r1) {
        return randomPrint(RandomUtils.nextInt(r0, r1));
    }

    public static String random(int r1, boolean z, boolean z2) {
        return random(r1, 0, 0, z, z2);
    }

    public static String random(int r7, int r8, int r9, boolean z, boolean z2) {
        return random(r7, r8, r9, z, z2, null, RANDOM);
    }

    public static String random(int r7, int r8, int r9, boolean z, boolean z2, char... cArr) {
        return random(r7, r8, r9, z, z2, cArr, RANDOM);
    }

    public static String random(int r4, int r5, int r6, boolean z, boolean z2, char[] cArr, Random random) {
        char c;
        if (r4 == 0) {
            return "";
        }
        if (r4 < 0) {
            throw new IllegalArgumentException("Requested random string length " + r4 + " is less than 0.");
        } else if (cArr != null && cArr.length == 0) {
            throw new IllegalArgumentException("The chars array must not be empty");
        } else {
            if (r5 == 0 && r6 == 0) {
                if (cArr != null) {
                    r6 = cArr.length;
                } else if (z || z2) {
                    r6 = 123;
                    r5 = 32;
                } else {
                    r6 = 1114111;
                }
            } else if (r6 <= r5) {
                throw new IllegalArgumentException("Parameter end (" + r6 + ") must be greater than start (" + r5 + ")");
            }
            if (cArr == null && ((z2 && r6 <= 48) || (z && r6 <= 65))) {
                throw new IllegalArgumentException("Parameter end (" + r6 + ") must be greater then (48) for generating digits or greater then (65) for generating letters.");
            }
            StringBuilder sb = new StringBuilder(r4);
            int r62 = r6 - r5;
            while (true) {
                int r1 = r4 - 1;
                if (r4 != 0) {
                    if (cArr == null) {
                        int nextInt = random.nextInt(r62) + r5;
                        int type = Character.getType(nextInt);
                        if (type != 0 && type != 18) {
                            c = nextInt;
                            if (type == 19) {
                            }
                        }
                        r4 = r1 + 1;
                    } else {
                        c = cArr[random.nextInt(r62) + r5];
                    }
                    int charCount = Character.charCount(c);
                    if (r1 != 0 || charCount <= 1) {
                        if (!(z && Character.isLetter(c)) && (!(z2 && Character.isDigit(c)) && (z || z2))) {
                            r1++;
                        } else {
                            sb.appendCodePoint(c);
                            if (charCount == 2) {
                                r1--;
                            }
                        }
                        r4 = r1;
                    } else {
                        r4 = r1 + 1;
                    }
                } else {
                    return sb.toString();
                }
            }
        }
    }

    public static String random(int r7, String str) {
        if (str == null) {
            return random(r7, 0, 0, false, false, null, RANDOM);
        }
        return random(r7, str.toCharArray());
    }

    public static String random(int r7, char... cArr) {
        if (cArr == null) {
            return random(r7, 0, 0, false, false, null, RANDOM);
        }
        return random(r7, 0, cArr.length, false, false, cArr, RANDOM);
    }
}
