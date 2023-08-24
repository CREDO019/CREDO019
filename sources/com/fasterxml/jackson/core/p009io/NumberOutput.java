package com.fasterxml.jackson.core.p009io;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import org.apache.commons.fileupload.MultipartStream;

/* renamed from: com.fasterxml.jackson.core.io.NumberOutput */
/* loaded from: classes.dex */
public final class NumberOutput {
    private static int BILLION = 1000000000;
    private static long BILLION_L = 1000000000;
    private static long MAX_INT_AS_LONG = 2147483647L;
    private static int MILLION = 1000000;
    private static long MIN_INT_AS_LONG = -2147483648L;
    static final String SMALLEST_INT = String.valueOf(Integer.MIN_VALUE);
    static final String SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
    private static final int[] TRIPLET_TO_CHARS = new int[1000];
    private static final String[] sSmallIntStrs;
    private static final String[] sSmallIntStrs2;

    static {
        int r2 = 0;
        for (int r1 = 0; r1 < 10; r1++) {
            for (int r4 = 0; r4 < 10; r4++) {
                int r5 = 0;
                while (r5 < 10) {
                    TRIPLET_TO_CHARS[r2] = ((r1 + 48) << 16) | ((r4 + 48) << 8) | (r5 + 48);
                    r5++;
                    r2++;
                }
            }
        }
        sSmallIntStrs = new String[]{SessionDescription.SUPPORTED_SDP_VERSION, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "2", ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9", "10"};
        sSmallIntStrs2 = new String[]{"-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};
    }

    public static int outputInt(int r3, char[] cArr, int r5) {
        int r0;
        if (r3 < 0) {
            if (r3 == Integer.MIN_VALUE) {
                return _outputSmallestI(cArr, r5);
            }
            cArr[r5] = '-';
            r3 = -r3;
            r5++;
        }
        if (r3 < MILLION) {
            if (r3 >= 1000) {
                int r02 = r3 / 1000;
                return _full3(r3 - (r02 * 1000), cArr, _leading3(r02, cArr, r5));
            } else if (r3 < 10) {
                cArr[r5] = (char) (r3 + 48);
                return r5 + 1;
            } else {
                return _leading3(r3, cArr, r5);
            }
        }
        int r03 = BILLION;
        if (r3 >= r03) {
            int r32 = r3 - r03;
            if (r32 >= r03) {
                r32 -= r03;
                r0 = r5 + 1;
                cArr[r5] = '2';
            } else {
                r0 = r5 + 1;
                cArr[r5] = '1';
            }
            return _outputFullBillion(r32, cArr, r0);
        }
        int r04 = r3 / 1000;
        int r1 = r04 / 1000;
        return _full3(r3 - (r04 * 1000), cArr, _full3(r04 - (r1 * 1000), cArr, _leading3(r1, cArr, r5)));
    }

    public static int outputInt(int r3, byte[] bArr, int r5) {
        int r0;
        if (r3 < 0) {
            if (r3 == Integer.MIN_VALUE) {
                return _outputSmallestI(bArr, r5);
            }
            bArr[r5] = MultipartStream.DASH;
            r3 = -r3;
            r5++;
        }
        if (r3 < MILLION) {
            if (r3 >= 1000) {
                int r02 = r3 / 1000;
                return _full3(r3 - (r02 * 1000), bArr, _leading3(r02, bArr, r5));
            } else if (r3 < 10) {
                int r03 = r5 + 1;
                bArr[r5] = (byte) (r3 + 48);
                return r03;
            } else {
                return _leading3(r3, bArr, r5);
            }
        }
        int r04 = BILLION;
        if (r3 >= r04) {
            int r32 = r3 - r04;
            if (r32 >= r04) {
                r32 -= r04;
                r0 = r5 + 1;
                bArr[r5] = 50;
            } else {
                r0 = r5 + 1;
                bArr[r5] = 49;
            }
            return _outputFullBillion(r32, bArr, r0);
        }
        int r05 = r3 / 1000;
        int r1 = r05 / 1000;
        return _full3(r3 - (r05 * 1000), bArr, _full3(r05 - (r1 * 1000), bArr, _leading3(r1, bArr, r5)));
    }

    public static int outputLong(long j, char[] cArr, int r9) {
        int _outputFullBillion;
        if (j < 0) {
            if (j > MIN_INT_AS_LONG) {
                return outputInt((int) j, cArr, r9);
            }
            if (j == Long.MIN_VALUE) {
                return _outputSmallestL(cArr, r9);
            }
            cArr[r9] = '-';
            j = -j;
            r9++;
        } else if (j <= MAX_INT_AS_LONG) {
            return outputInt((int) j, cArr, r9);
        }
        long j2 = BILLION_L;
        long j3 = j / j2;
        long j4 = j - (j3 * j2);
        if (j3 < j2) {
            _outputFullBillion = _outputUptoBillion((int) j3, cArr, r9);
        } else {
            long j5 = j3 / j2;
            int _leading3 = _leading3((int) j5, cArr, r9);
            _outputFullBillion = _outputFullBillion((int) (j3 - (j2 * j5)), cArr, _leading3);
        }
        return _outputFullBillion((int) j4, cArr, _outputFullBillion);
    }

    public static int outputLong(long j, byte[] bArr, int r9) {
        int _outputFullBillion;
        if (j < 0) {
            if (j > MIN_INT_AS_LONG) {
                return outputInt((int) j, bArr, r9);
            }
            if (j == Long.MIN_VALUE) {
                return _outputSmallestL(bArr, r9);
            }
            bArr[r9] = MultipartStream.DASH;
            j = -j;
            r9++;
        } else if (j <= MAX_INT_AS_LONG) {
            return outputInt((int) j, bArr, r9);
        }
        long j2 = BILLION_L;
        long j3 = j / j2;
        long j4 = j - (j3 * j2);
        if (j3 < j2) {
            _outputFullBillion = _outputUptoBillion((int) j3, bArr, r9);
        } else {
            long j5 = j3 / j2;
            int _leading3 = _leading3((int) j5, bArr, r9);
            _outputFullBillion = _outputFullBillion((int) (j3 - (j2 * j5)), bArr, _leading3);
        }
        return _outputFullBillion((int) j4, bArr, _outputFullBillion);
    }

    public static String toString(int r3) {
        String[] strArr = sSmallIntStrs;
        if (r3 < strArr.length) {
            if (r3 >= 0) {
                return strArr[r3];
            }
            int r0 = (-r3) - 1;
            String[] strArr2 = sSmallIntStrs2;
            if (r0 < strArr2.length) {
                return strArr2[r0];
            }
        }
        return Integer.toString(r3);
    }

    public static String toString(long j) {
        if (j <= 2147483647L && j >= -2147483648L) {
            return toString((int) j);
        }
        return Long.toString(j);
    }

    public static String toString(double d) {
        return Double.toString(d);
    }

    public static String toString(float f) {
        return Float.toString(f);
    }

    private static int _outputUptoBillion(int r4, char[] cArr, int r6) {
        if (r4 < MILLION) {
            if (r4 < 1000) {
                return _leading3(r4, cArr, r6);
            }
            int r0 = r4 / 1000;
            return _outputUptoMillion(cArr, r6, r0, r4 - (r0 * 1000));
        }
        int r02 = r4 / 1000;
        int r42 = r4 - (r02 * 1000);
        int r1 = r02 / 1000;
        int _leading3 = _leading3(r1, cArr, r6);
        int[] r12 = TRIPLET_TO_CHARS;
        int r03 = r12[r02 - (r1 * 1000)];
        int r2 = _leading3 + 1;
        cArr[_leading3] = (char) (r03 >> 16);
        int r62 = r2 + 1;
        cArr[r2] = (char) ((r03 >> 8) & 127);
        int r22 = r62 + 1;
        cArr[r62] = (char) (r03 & 127);
        int r43 = r12[r42];
        int r63 = r22 + 1;
        cArr[r22] = (char) (r43 >> 16);
        int r04 = r63 + 1;
        cArr[r63] = (char) ((r43 >> 8) & 127);
        int r64 = r04 + 1;
        cArr[r04] = (char) (r43 & 127);
        return r64;
    }

    private static int _outputFullBillion(int r6, char[] cArr, int r8) {
        int r0 = r6 / 1000;
        int r62 = r6 - (r0 * 1000);
        int r1 = r0 / 1000;
        int[] r2 = TRIPLET_TO_CHARS;
        int r3 = r2[r1];
        int r4 = r8 + 1;
        cArr[r8] = (char) (r3 >> 16);
        int r82 = r4 + 1;
        cArr[r4] = (char) ((r3 >> 8) & 127);
        int r42 = r82 + 1;
        cArr[r82] = (char) (r3 & 127);
        int r83 = r2[r0 - (r1 * 1000)];
        int r02 = r42 + 1;
        cArr[r42] = (char) (r83 >> 16);
        int r12 = r02 + 1;
        cArr[r02] = (char) ((r83 >> 8) & 127);
        int r03 = r12 + 1;
        cArr[r12] = (char) (r83 & 127);
        int r63 = r2[r62];
        int r84 = r03 + 1;
        cArr[r03] = (char) (r63 >> 16);
        int r04 = r84 + 1;
        cArr[r84] = (char) ((r63 >> 8) & 127);
        int r85 = r04 + 1;
        cArr[r04] = (char) (r63 & 127);
        return r85;
    }

    private static int _outputUptoBillion(int r4, byte[] bArr, int r6) {
        if (r4 < MILLION) {
            if (r4 < 1000) {
                return _leading3(r4, bArr, r6);
            }
            int r0 = r4 / 1000;
            return _outputUptoMillion(bArr, r6, r0, r4 - (r0 * 1000));
        }
        int r02 = r4 / 1000;
        int r42 = r4 - (r02 * 1000);
        int r1 = r02 / 1000;
        int _leading3 = _leading3(r1, bArr, r6);
        int[] r12 = TRIPLET_TO_CHARS;
        int r03 = r12[r02 - (r1 * 1000)];
        int r2 = _leading3 + 1;
        bArr[_leading3] = (byte) (r03 >> 16);
        int r62 = r2 + 1;
        bArr[r2] = (byte) (r03 >> 8);
        int r22 = r62 + 1;
        bArr[r62] = (byte) r03;
        int r43 = r12[r42];
        int r63 = r22 + 1;
        bArr[r22] = (byte) (r43 >> 16);
        int r04 = r63 + 1;
        bArr[r63] = (byte) (r43 >> 8);
        int r64 = r04 + 1;
        bArr[r04] = (byte) r43;
        return r64;
    }

    private static int _outputFullBillion(int r5, byte[] bArr, int r7) {
        int r0 = r5 / 1000;
        int r52 = r5 - (r0 * 1000);
        int r1 = r0 / 1000;
        int r02 = r0 - (r1 * 1000);
        int[] r2 = TRIPLET_TO_CHARS;
        int r12 = r2[r1];
        int r3 = r7 + 1;
        bArr[r7] = (byte) (r12 >> 16);
        int r72 = r3 + 1;
        bArr[r3] = (byte) (r12 >> 8);
        int r32 = r72 + 1;
        bArr[r72] = (byte) r12;
        int r73 = r2[r02];
        int r03 = r32 + 1;
        bArr[r32] = (byte) (r73 >> 16);
        int r13 = r03 + 1;
        bArr[r03] = (byte) (r73 >> 8);
        int r04 = r13 + 1;
        bArr[r13] = (byte) r73;
        int r53 = r2[r52];
        int r74 = r04 + 1;
        bArr[r04] = (byte) (r53 >> 16);
        int r05 = r74 + 1;
        bArr[r74] = (byte) (r53 >> 8);
        int r75 = r05 + 1;
        bArr[r05] = (byte) r53;
        return r75;
    }

    private static int _outputUptoMillion(char[] cArr, int r4, int r5, int r6) {
        int[] r0 = TRIPLET_TO_CHARS;
        int r1 = r0[r5];
        if (r5 > 9) {
            if (r5 > 99) {
                cArr[r4] = (char) (r1 >> 16);
                r4++;
            }
            cArr[r4] = (char) ((r1 >> 8) & 127);
            r4++;
        }
        int r52 = r4 + 1;
        cArr[r4] = (char) (r1 & 127);
        int r42 = r0[r6];
        int r62 = r52 + 1;
        cArr[r52] = (char) (r42 >> 16);
        int r53 = r62 + 1;
        cArr[r62] = (char) ((r42 >> 8) & 127);
        int r63 = r53 + 1;
        cArr[r53] = (char) (r42 & 127);
        return r63;
    }

    private static int _outputUptoMillion(byte[] bArr, int r4, int r5, int r6) {
        int[] r0 = TRIPLET_TO_CHARS;
        int r1 = r0[r5];
        if (r5 > 9) {
            if (r5 > 99) {
                bArr[r4] = (byte) (r1 >> 16);
                r4++;
            }
            bArr[r4] = (byte) (r1 >> 8);
            r4++;
        }
        int r52 = r4 + 1;
        bArr[r4] = (byte) r1;
        int r42 = r0[r6];
        int r62 = r52 + 1;
        bArr[r52] = (byte) (r42 >> 16);
        int r53 = r62 + 1;
        bArr[r62] = (byte) (r42 >> 8);
        int r63 = r53 + 1;
        bArr[r53] = (byte) r42;
        return r63;
    }

    private static int _leading3(int r2, char[] cArr, int r4) {
        int r0 = TRIPLET_TO_CHARS[r2];
        if (r2 > 9) {
            if (r2 > 99) {
                cArr[r4] = (char) (r0 >> 16);
                r4++;
            }
            cArr[r4] = (char) ((r0 >> 8) & 127);
            r4++;
        }
        int r22 = r4 + 1;
        cArr[r4] = (char) (r0 & 127);
        return r22;
    }

    private static int _leading3(int r2, byte[] bArr, int r4) {
        int r0 = TRIPLET_TO_CHARS[r2];
        if (r2 > 9) {
            if (r2 > 99) {
                bArr[r4] = (byte) (r0 >> 16);
                r4++;
            }
            bArr[r4] = (byte) (r0 >> 8);
            r4++;
        }
        int r22 = r4 + 1;
        bArr[r4] = (byte) r0;
        return r22;
    }

    private static int _full3(int r2, char[] cArr, int r4) {
        int r22 = TRIPLET_TO_CHARS[r2];
        int r0 = r4 + 1;
        cArr[r4] = (char) (r22 >> 16);
        int r42 = r0 + 1;
        cArr[r0] = (char) ((r22 >> 8) & 127);
        int r02 = r42 + 1;
        cArr[r42] = (char) (r22 & 127);
        return r02;
    }

    private static int _full3(int r2, byte[] bArr, int r4) {
        int r22 = TRIPLET_TO_CHARS[r2];
        int r0 = r4 + 1;
        bArr[r4] = (byte) (r22 >> 16);
        int r42 = r0 + 1;
        bArr[r0] = (byte) (r22 >> 8);
        int r02 = r42 + 1;
        bArr[r42] = (byte) r22;
        return r02;
    }

    private static int _outputSmallestL(char[] cArr, int r4) {
        String str = SMALLEST_LONG;
        int length = str.length();
        str.getChars(0, length, cArr, r4);
        return r4 + length;
    }

    private static int _outputSmallestL(byte[] bArr, int r5) {
        int length = SMALLEST_LONG.length();
        int r1 = 0;
        while (r1 < length) {
            bArr[r5] = (byte) SMALLEST_LONG.charAt(r1);
            r1++;
            r5++;
        }
        return r5;
    }

    private static int _outputSmallestI(char[] cArr, int r4) {
        String str = SMALLEST_INT;
        int length = str.length();
        str.getChars(0, length, cArr, r4);
        return r4 + length;
    }

    private static int _outputSmallestI(byte[] bArr, int r5) {
        int length = SMALLEST_INT.length();
        int r1 = 0;
        while (r1 < length) {
            bArr[r5] = (byte) SMALLEST_INT.charAt(r1);
            r1++;
            r5++;
        }
        return r5;
    }
}
