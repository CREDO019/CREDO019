package com.google.zxing.aztec.decoder;

import androidx.exifinterface.media.ExifInterface;
import androidx.webkit.ProxyConfig;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.zxing.FormatException;
import com.google.zxing.aztec.AztecDetectorResult;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.onesignal.NotificationBundleProcessor;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.p028io.IOUtils;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* loaded from: classes3.dex */
public final class Decoder {
    private AztecDetectorResult ddata;
    private static final String[] UPPER_TABLE = {"CTRL_PS", " ", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C", "D", ExifInterface.LONGITUDE_EAST, "F", RequestConfiguration.MAX_AD_CONTENT_RATING_G, "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", ExifInterface.LATITUDE_SOUTH, "T", "U", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] LOWER_TABLE = {"CTRL_PS", " ", NotificationBundleProcessor.PUSH_ADDITIONAL_DATA_KEY, "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", NotificationBundleProcessor.PUSH_MINIFIED_BUTTON_TEXT, NotificationBundleProcessor.PUSH_MINIFIED_BUTTONS_LIST, "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] MIXED_TABLE = {"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", StringUtils.f1569CR, "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] PUNCT_TABLE = {"", StringUtils.f1569CR, IOUtils.LINE_SEPARATOR_WINDOWS, ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", ProxyConfig.MATCH_ALL_SCHEMES, "+", ",", "-", ".", "/", ParameterizedMessage.ERROR_MSG_SEPARATOR, ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] DIGIT_TABLE = {"CTRL_PS", " ", SessionDescription.SUPPORTED_SDP_VERSION, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "2", ExifInterface.GPS_MEASUREMENT_3D, "4", "5", "6", "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public enum Table {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    private static int totalBitsInLayer(int r1, boolean z) {
        return ((z ? 88 : 112) + (r1 << 4)) * r1;
    }

    public DecoderResult decode(AztecDetectorResult aztecDetectorResult) throws FormatException {
        this.ddata = aztecDetectorResult;
        boolean[] correctBits = correctBits(extractBits(aztecDetectorResult.getBits()));
        DecoderResult decoderResult = new DecoderResult(convertBoolArrayToByteArray(correctBits), getEncodedData(correctBits), null, null);
        decoderResult.setNumBits(correctBits.length);
        return decoderResult;
    }

    public static String highLevelDecode(boolean[] zArr) {
        return getEncodedData(zArr);
    }

    private static String getEncodedData(boolean[] zArr) {
        int length = zArr.length;
        Table table = Table.UPPER;
        Table table2 = Table.UPPER;
        StringBuilder sb = new StringBuilder(20);
        int r5 = 0;
        while (r5 < length) {
            if (table2 != Table.BINARY) {
                int r6 = table2 == Table.DIGIT ? 4 : 5;
                if (length - r5 < r6) {
                    break;
                }
                int readCode = readCode(zArr, r5, r6);
                r5 += r6;
                String character = getCharacter(table2, readCode);
                if (character.startsWith("CTRL_")) {
                    table = getTable(character.charAt(5));
                    if (character.charAt(6) != 'L') {
                        Table table3 = table2;
                        table2 = table;
                        table = table3;
                    }
                } else {
                    sb.append(character);
                }
                table2 = table;
            } else if (length - r5 < 5) {
                break;
            } else {
                int readCode2 = readCode(zArr, r5, 5);
                r5 += 5;
                if (readCode2 == 0) {
                    if (length - r5 < 11) {
                        break;
                    }
                    readCode2 = readCode(zArr, r5, 11) + 31;
                    r5 += 11;
                }
                int r62 = 0;
                while (true) {
                    if (r62 >= readCode2) {
                        break;
                    } else if (length - r5 < 8) {
                        r5 = length;
                        break;
                    } else {
                        sb.append((char) readCode(zArr, r5, 8));
                        r5 += 8;
                        r62++;
                    }
                }
                table2 = table;
            }
        }
        return sb.toString();
    }

    private static Table getTable(char c) {
        if (c != 'B') {
            if (c != 'D') {
                if (c != 'P') {
                    if (c != 'L') {
                        if (c == 'M') {
                            return Table.MIXED;
                        }
                        return Table.UPPER;
                    }
                    return Table.LOWER;
                }
                return Table.PUNCT;
            }
            return Table.DIGIT;
        }
        return Table.BINARY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.zxing.aztec.decoder.Decoder$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C33661 {
        static final /* synthetic */ int[] $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table;

        static {
            int[] r0 = new int[Table.values().length];
            $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table = r0;
            try {
                r0[Table.UPPER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[Table.DIGIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static String getCharacter(Table table, int r2) {
        int r1 = C33661.$SwitchMap$com$google$zxing$aztec$decoder$Decoder$Table[table.ordinal()];
        if (r1 != 1) {
            if (r1 != 2) {
                if (r1 != 3) {
                    if (r1 != 4) {
                        if (r1 == 5) {
                            return DIGIT_TABLE[r2];
                        }
                        throw new IllegalStateException("Bad table");
                    }
                    return PUNCT_TABLE[r2];
                }
                return MIXED_TABLE[r2];
            }
            return LOWER_TABLE[r2];
        }
        return UPPER_TABLE[r2];
    }

    private boolean[] correctBits(boolean[] zArr) throws FormatException {
        GenericGF genericGF;
        int r1 = 8;
        if (this.ddata.getNbLayers() <= 2) {
            r1 = 6;
            genericGF = GenericGF.AZTEC_DATA_6;
        } else if (this.ddata.getNbLayers() <= 8) {
            genericGF = GenericGF.AZTEC_DATA_8;
        } else if (this.ddata.getNbLayers() <= 22) {
            r1 = 10;
            genericGF = GenericGF.AZTEC_DATA_10;
        } else {
            r1 = 12;
            genericGF = GenericGF.AZTEC_DATA_12;
        }
        int nbDatablocks = this.ddata.getNbDatablocks();
        int length = zArr.length / r1;
        if (length < nbDatablocks) {
            throw FormatException.getFormatInstance();
        }
        int length2 = zArr.length % r1;
        int[] r5 = new int[length];
        int r7 = 0;
        while (r7 < length) {
            r5[r7] = readCode(zArr, length2, r1);
            r7++;
            length2 += r1;
        }
        try {
            new ReedSolomonDecoder(genericGF).decode(r5, length - nbDatablocks);
            int r0 = (1 << r1) - 1;
            int r4 = 0;
            for (int r3 = 0; r3 < nbDatablocks; r3++) {
                int r72 = r5[r3];
                if (r72 == 0 || r72 == r0) {
                    throw FormatException.getFormatInstance();
                }
                if (r72 == 1 || r72 == r0 - 1) {
                    r4++;
                }
            }
            boolean[] zArr2 = new boolean[(nbDatablocks * r1) - r4];
            int r73 = 0;
            for (int r42 = 0; r42 < nbDatablocks; r42++) {
                int r8 = r5[r42];
                if (r8 == 1 || r8 == r0 - 1) {
                    Arrays.fill(zArr2, r73, (r73 + r1) - 1, r8 > 1);
                    r73 += r1 - 1;
                } else {
                    int r9 = r1 - 1;
                    while (r9 >= 0) {
                        int r10 = r73 + 1;
                        zArr2[r73] = ((1 << r9) & r8) != 0;
                        r9--;
                        r73 = r10;
                    }
                }
            }
            return zArr2;
        } catch (ReedSolomonException e) {
            throw FormatException.getFormatInstance(e);
        }
    }

    private boolean[] extractBits(BitMatrix bitMatrix) {
        boolean isCompact = this.ddata.isCompact();
        int nbLayers = this.ddata.getNbLayers();
        int r4 = (isCompact ? 11 : 14) + (nbLayers << 2);
        int[] r5 = new int[r4];
        boolean[] zArr = new boolean[totalBitsInLayer(nbLayers, isCompact)];
        int r8 = 2;
        if (isCompact) {
            for (int r9 = 0; r9 < r4; r9++) {
                r5[r9] = r9;
            }
        } else {
            int r10 = r4 / 2;
            int r92 = ((r4 + 1) + (((r10 - 1) / 15) * 2)) / 2;
            for (int r11 = 0; r11 < r10; r11++) {
                int r12 = (r11 / 15) + r11;
                r5[(r10 - r11) - 1] = (r92 - r12) - 1;
                r5[r10 + r11] = r12 + r92 + 1;
            }
        }
        int r93 = 0;
        int r102 = 0;
        while (r93 < nbLayers) {
            int r112 = ((nbLayers - r93) << r8) + (isCompact ? 9 : 12);
            int r122 = r93 << 1;
            int r13 = (r4 - 1) - r122;
            int r14 = 0;
            while (r14 < r112) {
                int r15 = r14 << 1;
                int r7 = 0;
                while (r7 < r8) {
                    int r17 = r122 + r7;
                    int r18 = r122 + r14;
                    zArr[r102 + r15 + r7] = bitMatrix.get(r5[r17], r5[r18]);
                    int r82 = r5[r18];
                    int r16 = r13 - r7;
                    boolean z = isCompact;
                    zArr[(r112 * 2) + r102 + r15 + r7] = bitMatrix.get(r82, r5[r16]);
                    int r83 = r13 - r14;
                    zArr[(r112 * 4) + r102 + r15 + r7] = bitMatrix.get(r5[r16], r5[r83]);
                    zArr[(r112 * 6) + r102 + r15 + r7] = bitMatrix.get(r5[r83], r5[r17]);
                    r7++;
                    nbLayers = nbLayers;
                    isCompact = z;
                    r8 = 2;
                }
                r14++;
                r8 = 2;
            }
            r102 += r112 << 3;
            r93++;
            r8 = 2;
        }
        return zArr;
    }

    private static int readCode(boolean[] zArr, int r4, int r5) {
        int r0 = 0;
        for (int r1 = r4; r1 < r4 + r5; r1++) {
            r0 <<= 1;
            if (zArr[r1]) {
                r0 |= 1;
            }
        }
        return r0;
    }

    private static byte readByte(boolean[] zArr, int r3) {
        int readCode;
        int length = zArr.length - r3;
        if (length >= 8) {
            readCode = readCode(zArr, r3, 8);
        } else {
            readCode = readCode(zArr, r3, length) << (8 - length);
        }
        return (byte) readCode;
    }

    static byte[] convertBoolArrayToByteArray(boolean[] zArr) {
        int length = (zArr.length + 7) / 8;
        byte[] bArr = new byte[length];
        for (int r2 = 0; r2 < length; r2++) {
            bArr[r2] = readByte(zArr, r2 << 3);
        }
        return bArr;
    }
}
