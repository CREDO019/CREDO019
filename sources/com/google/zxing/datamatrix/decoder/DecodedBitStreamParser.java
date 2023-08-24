package com.google.zxing.datamatrix.decoder;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import com.google.common.base.Ascii;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.DecoderResult;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.text.Typography;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.p028io.IOUtils;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes3.dex */
final class DecodedBitStreamParser {
    private static final char[] C40_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', Matrix.MATRIX_TYPE_ZERO};
    private static final char[] C40_SHIFT2_SET_CHARS;
    private static final char[] TEXT_BASIC_SET_CHARS;
    private static final char[] TEXT_SHIFT2_SET_CHARS;
    private static final char[] TEXT_SHIFT3_SET_CHARS;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum Mode {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE
    }

    static {
        char[] cArr = {'!', Typography.quote, '#', '$', '%', Typography.amp, '\'', '(', ')', '*', '+', ',', '-', '.', IOUtils.DIR_SEPARATOR_UNIX, ':', ';', Typography.less, '=', Typography.greater, '?', '@', '[', IOUtils.DIR_SEPARATOR_WINDOWS, ']', '^', '_'};
        C40_SHIFT2_SET_CHARS = cArr;
        TEXT_BASIC_SET_CHARS = new char[]{'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        TEXT_SHIFT2_SET_CHARS = cArr;
        TEXT_SHIFT3_SET_CHARS = new char[]{'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', Matrix.MATRIX_TYPE_ZERO, '{', '|', '}', '~', Ascii.MAX};
    }

    private DecodedBitStreamParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DecoderResult decode(byte[] bArr) throws FormatException {
        BitSource bitSource = new BitSource(bArr);
        StringBuilder sb = new StringBuilder(100);
        StringBuilder sb2 = new StringBuilder(0);
        ArrayList arrayList = new ArrayList(1);
        Mode mode = Mode.ASCII_ENCODE;
        do {
            if (mode == Mode.ASCII_ENCODE) {
                mode = decodeAsciiSegment(bitSource, sb, sb2);
            } else {
                int r5 = C33681.f1218xb73eb560[mode.ordinal()];
                if (r5 == 1) {
                    decodeC40Segment(bitSource, sb);
                } else if (r5 == 2) {
                    decodeTextSegment(bitSource, sb);
                } else if (r5 == 3) {
                    decodeAnsiX12Segment(bitSource, sb);
                } else if (r5 == 4) {
                    decodeEdifactSegment(bitSource, sb);
                } else if (r5 == 5) {
                    decodeBase256Segment(bitSource, sb, arrayList);
                } else {
                    throw FormatException.getFormatInstance();
                }
                mode = Mode.ASCII_ENCODE;
            }
            if (mode == Mode.PAD_ENCODE) {
                break;
            }
        } while (bitSource.available() > 0);
        if (sb2.length() > 0) {
            sb.append((CharSequence) sb2);
        }
        String sb3 = sb.toString();
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        return new DecoderResult(bArr, sb3, arrayList, null);
    }

    /* renamed from: com.google.zxing.datamatrix.decoder.DecodedBitStreamParser$1 */
    /* loaded from: classes3.dex */
    static /* synthetic */ class C33681 {

        /* renamed from: $SwitchMap$com$google$zxing$datamatrix$decoder$DecodedBitStreamParser$Mode */
        static final /* synthetic */ int[] f1218xb73eb560;

        static {
            int[] r0 = new int[Mode.values().length];
            f1218xb73eb560 = r0;
            try {
                r0[Mode.C40_ENCODE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1218xb73eb560[Mode.TEXT_ENCODE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1218xb73eb560[Mode.ANSIX12_ENCODE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1218xb73eb560[Mode.EDIFACT_ENCODE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1218xb73eb560[Mode.BASE256_ENCODE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static Mode decodeAsciiSegment(BitSource bitSource, StringBuilder sb, StringBuilder sb2) throws FormatException {
        boolean z = false;
        do {
            int readBits = bitSource.readBits(8);
            if (readBits == 0) {
                throw FormatException.getFormatInstance();
            }
            if (readBits > 128) {
                if (readBits != 129) {
                    if (readBits <= 229) {
                        int r2 = readBits - 130;
                        if (r2 < 10) {
                            sb.append('0');
                        }
                        sb.append(r2);
                    } else {
                        switch (readBits) {
                            case 230:
                                return Mode.C40_ENCODE;
                            case 231:
                                return Mode.BASE256_ENCODE;
                            case 232:
                                sb.append((char) 29);
                                break;
                            case 233:
                            case 234:
                            case 241:
                                break;
                            case 235:
                                z = true;
                                break;
                            case 236:
                                sb.append("[)>\u001e05\u001d");
                                sb2.insert(0, "\u001e\u0004");
                                break;
                            case 237:
                                sb.append("[)>\u001e06\u001d");
                                sb2.insert(0, "\u001e\u0004");
                                break;
                            case 238:
                                return Mode.ANSIX12_ENCODE;
                            case 239:
                                return Mode.TEXT_ENCODE;
                            case PsExtractor.VIDEO_STREAM_MASK /* 240 */:
                                return Mode.EDIFACT_ENCODE;
                            default:
                                if (readBits != 254 || bitSource.available() != 0) {
                                    throw FormatException.getFormatInstance();
                                }
                                break;
                        }
                    }
                } else {
                    return Mode.PAD_ENCODE;
                }
            } else {
                if (z) {
                    readBits += 128;
                }
                sb.append((char) (readBits - 1));
                return Mode.ASCII_ENCODE;
            }
        } while (bitSource.available() > 0);
        return Mode.ASCII_ENCODE;
    }

    private static void decodeC40Segment(BitSource bitSource, StringBuilder sb) throws FormatException {
        int readBits;
        int[] r1 = new int[3];
        boolean z = false;
        int r4 = 0;
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), r1);
            for (int r5 = 0; r5 < 3; r5++) {
                int r6 = r1[r5];
                if (r4 != 0) {
                    if (r4 != 1) {
                        if (r4 == 2) {
                            char[] cArr = C40_SHIFT2_SET_CHARS;
                            if (r6 < cArr.length) {
                                char c = cArr[r6];
                                if (z) {
                                    sb.append((char) (c + 128));
                                    z = false;
                                } else {
                                    sb.append(c);
                                }
                            } else if (r6 == 27) {
                                sb.append((char) 29);
                            } else if (r6 != 30) {
                                throw FormatException.getFormatInstance();
                            } else {
                                z = true;
                            }
                            r4 = 0;
                        } else if (r4 != 3) {
                            throw FormatException.getFormatInstance();
                        } else {
                            if (z) {
                                sb.append((char) (r6 + 224));
                                z = false;
                                r4 = 0;
                            } else {
                                sb.append((char) (r6 + 96));
                                r4 = 0;
                            }
                        }
                    } else if (z) {
                        sb.append((char) (r6 + 128));
                        z = false;
                        r4 = 0;
                    } else {
                        sb.append((char) r6);
                        r4 = 0;
                    }
                } else if (r6 < 3) {
                    r4 = r6 + 1;
                } else {
                    char[] cArr2 = C40_BASIC_SET_CHARS;
                    if (r6 < cArr2.length) {
                        char c2 = cArr2[r6];
                        if (z) {
                            sb.append((char) (c2 + 128));
                            z = false;
                        } else {
                            sb.append(c2);
                        }
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeTextSegment(BitSource bitSource, StringBuilder sb) throws FormatException {
        int readBits;
        int[] r1 = new int[3];
        boolean z = false;
        int r4 = 0;
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), r1);
            for (int r5 = 0; r5 < 3; r5++) {
                int r6 = r1[r5];
                if (r4 != 0) {
                    if (r4 != 1) {
                        if (r4 == 2) {
                            char[] cArr = TEXT_SHIFT2_SET_CHARS;
                            if (r6 < cArr.length) {
                                char c = cArr[r6];
                                if (z) {
                                    sb.append((char) (c + 128));
                                    z = false;
                                } else {
                                    sb.append(c);
                                }
                            } else if (r6 == 27) {
                                sb.append((char) 29);
                            } else if (r6 != 30) {
                                throw FormatException.getFormatInstance();
                            } else {
                                z = true;
                            }
                            r4 = 0;
                        } else if (r4 == 3) {
                            char[] cArr2 = TEXT_SHIFT3_SET_CHARS;
                            if (r6 < cArr2.length) {
                                char c2 = cArr2[r6];
                                if (z) {
                                    sb.append((char) (c2 + 128));
                                    z = false;
                                    r4 = 0;
                                } else {
                                    sb.append(c2);
                                    r4 = 0;
                                }
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    } else if (z) {
                        sb.append((char) (r6 + 128));
                        z = false;
                        r4 = 0;
                    } else {
                        sb.append((char) r6);
                        r4 = 0;
                    }
                } else if (r6 < 3) {
                    r4 = r6 + 1;
                } else {
                    char[] cArr3 = TEXT_BASIC_SET_CHARS;
                    if (r6 < cArr3.length) {
                        char c3 = cArr3[r6];
                        if (z) {
                            sb.append((char) (c3 + 128));
                            z = false;
                        } else {
                            sb.append(c3);
                        }
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeAnsiX12Segment(BitSource bitSource, StringBuilder sb) throws FormatException {
        int readBits;
        int[] r1 = new int[3];
        while (bitSource.available() != 8 && (readBits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(readBits, bitSource.readBits(8), r1);
            for (int r2 = 0; r2 < 3; r2++) {
                int r3 = r1[r2];
                if (r3 == 0) {
                    sb.append(CharUtils.f1567CR);
                } else if (r3 == 1) {
                    sb.append('*');
                } else if (r3 == 2) {
                    sb.append(Typography.greater);
                } else if (r3 == 3) {
                    sb.append(' ');
                } else if (r3 < 14) {
                    sb.append((char) (r3 + 44));
                } else if (r3 < 40) {
                    sb.append((char) (r3 + 51));
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void parseTwoBytes(int r2, int r3, int[] r4) {
        int r22 = ((r2 << 8) + r3) - 1;
        int r0 = r22 / 1600;
        r4[0] = r0;
        int r23 = r22 - (r0 * 1600);
        int r02 = r23 / 40;
        r4[1] = r02;
        r4[2] = r23 - (r02 * 40);
    }

    private static void decodeEdifactSegment(BitSource bitSource, StringBuilder sb) {
        while (bitSource.available() > 16) {
            for (int r0 = 0; r0 < 4; r0++) {
                int readBits = bitSource.readBits(6);
                if (readBits == 31) {
                    int bitOffset = 8 - bitSource.getBitOffset();
                    if (bitOffset != 8) {
                        bitSource.readBits(bitOffset);
                        return;
                    }
                    return;
                }
                if ((readBits & 32) == 0) {
                    readBits |= 64;
                }
                sb.append((char) readBits);
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeBase256Segment(BitSource bitSource, StringBuilder sb, Collection<byte[]> collection) throws FormatException {
        int byteOffset = bitSource.getByteOffset() + 1;
        int r3 = byteOffset + 1;
        int unrandomize255State = unrandomize255State(bitSource.readBits(8), byteOffset);
        if (unrandomize255State == 0) {
            unrandomize255State = bitSource.available() / 8;
        } else if (unrandomize255State >= 250) {
            unrandomize255State = ((unrandomize255State - 249) * ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + unrandomize255State(bitSource.readBits(8), r3);
            r3++;
        }
        if (unrandomize255State < 0) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[unrandomize255State];
        int r4 = 0;
        while (r4 < unrandomize255State) {
            if (bitSource.available() < 8) {
                throw FormatException.getFormatInstance();
            }
            bArr[r4] = (byte) unrandomize255State(bitSource.readBits(8), r3);
            r4++;
            r3++;
        }
        collection.add(bArr);
        try {
            sb.append(new String(bArr, "ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Platform does not support required encoding: ".concat(String.valueOf(e)));
        }
    }

    private static int unrandomize255State(int r0, int r1) {
        int r02 = r0 - (((r1 * 149) % 255) + 1);
        return r02 >= 0 ? r02 : r02 + 256;
    }
}
