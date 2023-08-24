package org.apache.commons.codec.language;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.ads.RequestConfiguration;
import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.binary.StringUtils;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes5.dex */
public class DoubleMetaphone implements StringEncoder {
    private static final String VOWELS = "AEIOUY";
    private int maxCodeLen = 4;
    private static final String[] SILENT_START = {"GN", "KN", "PN", "WR", "PS"};
    private static final String[] L_R_N_M_B_H_F_V_W_SPACE = {"L", "R", "N", "M", "B", "H", "F", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, " "};
    private static final String[] ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER = {"ES", "EP", "EB", "EL", "EY", "IB", "IL", "IN", "IE", "EI", "ER"};
    private static final String[] L_T_K_S_N_M_B_Z = {"L", "T", "K", ExifInterface.LATITUDE_SOUTH, "N", "M", "B", "Z"};

    public String doubleMetaphone(String str) {
        return doubleMetaphone(str, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    /* JADX WARN: Type inference failed for: r1v11, types: [int] */
    /* JADX WARN: Type inference failed for: r1v12, types: [int] */
    /* JADX WARN: Type inference failed for: r1v13, types: [int] */
    /* JADX WARN: Type inference failed for: r1v14, types: [int] */
    /* JADX WARN: Type inference failed for: r1v15, types: [int] */
    /* JADX WARN: Type inference failed for: r1v16, types: [int] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18, types: [int] */
    /* JADX WARN: Type inference failed for: r1v19, types: [int] */
    /* JADX WARN: Type inference failed for: r1v2, types: [int] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [int] */
    /* JADX WARN: Type inference failed for: r1v5, types: [int] */
    /* JADX WARN: Type inference failed for: r1v6, types: [int] */
    /* JADX WARN: Type inference failed for: r1v7, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8, types: [int] */
    /* JADX WARN: Type inference failed for: r1v9, types: [int] */
    /* JADX WARN: Type inference failed for: r7v0, types: [org.apache.commons.codec.language.DoubleMetaphone] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.String] */
    public String doubleMetaphone(String str, boolean z) {
        int r3;
        ?? cleanInput = cleanInput(str);
        if (cleanInput == 0) {
            return null;
        }
        boolean isSlavoGermanic = isSlavoGermanic(cleanInput);
        ?? isSilentStart = isSilentStart(cleanInput);
        DoubleMetaphoneResult doubleMetaphoneResult = new DoubleMetaphoneResult(getMaxCodeLen());
        while (!doubleMetaphoneResult.isComplete() && isSilentStart <= cleanInput.length() - 1) {
            char charAt = cleanInput.charAt(isSilentStart);
            if (charAt == 199) {
                doubleMetaphoneResult.append('S');
            } else if (charAt != 209) {
                switch (charAt) {
                    case 'A':
                    case 'E':
                    case 'I':
                    case 'O':
                    case 'U':
                    case 'Y':
                        isSilentStart = handleAEIOUY(doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'B':
                        doubleMetaphoneResult.append('P');
                        r3 = isSilentStart + 1;
                        if (charAt(cleanInput, r3) != 'B') {
                            isSilentStart = r3;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'C':
                        isSilentStart = handleC(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'D':
                        isSilentStart = handleD(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'F':
                        doubleMetaphoneResult.append('F');
                        r3 = isSilentStart + 1;
                        if (charAt(cleanInput, r3) != 'F') {
                            isSilentStart = r3;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'G':
                        isSilentStart = handleG(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                    case 'H':
                        isSilentStart = handleH(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'J':
                        isSilentStart = handleJ(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                    case 'K':
                        doubleMetaphoneResult.append('K');
                        r3 = isSilentStart + 1;
                        if (charAt(cleanInput, r3) != 'K') {
                            isSilentStart = r3;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'L':
                        isSilentStart = handleL(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'M':
                        doubleMetaphoneResult.append('M');
                        if (!conditionM0(cleanInput, isSilentStart)) {
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'N':
                        doubleMetaphoneResult.append('N');
                        r3 = isSilentStart + 1;
                        if (charAt(cleanInput, r3) != 'N') {
                            isSilentStart = r3;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'P':
                        isSilentStart = handleP(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'Q':
                        doubleMetaphoneResult.append('K');
                        r3 = isSilentStart + 1;
                        if (charAt(cleanInput, r3) != 'Q') {
                            isSilentStart = r3;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'R':
                        isSilentStart = handleR(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                    case 'S':
                        isSilentStart = handleS(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                    case 'T':
                        isSilentStart = handleT(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'V':
                        doubleMetaphoneResult.append('F');
                        r3 = isSilentStart + 1;
                        if (charAt(cleanInput, r3) != 'V') {
                            isSilentStart = r3;
                            break;
                        } else {
                            isSilentStart += 2;
                            break;
                        }
                    case 'W':
                        isSilentStart = handleW(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'X':
                        isSilentStart = handleX(cleanInput, doubleMetaphoneResult, isSilentStart);
                        break;
                    case 'Z':
                        isSilentStart = handleZ(cleanInput, doubleMetaphoneResult, isSilentStart, isSlavoGermanic);
                        break;
                }
            } else {
                doubleMetaphoneResult.append('N');
            }
            isSilentStart++;
        }
        return z ? doubleMetaphoneResult.getAlternate() : doubleMetaphoneResult.getPrimary();
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("DoubleMetaphone encode parameter is not of type String");
        }
        return doubleMetaphone((String) obj);
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return doubleMetaphone(str);
    }

    public boolean isDoubleMetaphoneEqual(String str, String str2) {
        return isDoubleMetaphoneEqual(str, str2, false);
    }

    public boolean isDoubleMetaphoneEqual(String str, String str2, boolean z) {
        return StringUtils.equals(doubleMetaphone(str, z), doubleMetaphone(str2, z));
    }

    public int getMaxCodeLen() {
        return this.maxCodeLen;
    }

    public void setMaxCodeLen(int r1) {
        this.maxCodeLen = r1;
    }

    private int handleAEIOUY(DoubleMetaphoneResult doubleMetaphoneResult, int r3) {
        if (r3 == 0) {
            doubleMetaphoneResult.append('A');
        }
        return r3 + 1;
    }

    private int handleC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r14) {
        if (conditionC0(str, r14)) {
            doubleMetaphoneResult.append('K');
        } else if (r14 == 0 && contains(str, r14, 6, "CAESAR")) {
            doubleMetaphoneResult.append('S');
        } else if (contains(str, r14, 2, "CH")) {
            return handleCH(str, doubleMetaphoneResult, r14);
        } else {
            if (contains(str, r14, 2, "CZ") && !contains(str, r14 - 2, 4, "WICZ")) {
                doubleMetaphoneResult.append('S', 'X');
            } else {
                int r3 = r14 + 1;
                if (contains(str, r3, 3, "CIA")) {
                    doubleMetaphoneResult.append('X');
                } else if (contains(str, r14, 2, "CC") && (r14 != 1 || charAt(str, 0) != 'M')) {
                    return handleCC(str, doubleMetaphoneResult, r14);
                } else {
                    if (contains(str, r14, 2, "CK", "CG", "CQ")) {
                        doubleMetaphoneResult.append('K');
                    } else if (contains(str, r14, 2, "CI", "CE", "CY")) {
                        if (contains(str, r14, 3, "CIO", "CIE", "CIA")) {
                            doubleMetaphoneResult.append('S', 'X');
                        } else {
                            doubleMetaphoneResult.append('S');
                        }
                    } else {
                        doubleMetaphoneResult.append('K');
                        if (!contains(str, r3, 2, " C", " Q", " G")) {
                            if (!contains(str, r3, 1, "C", "K", "Q") || contains(str, r3, 2, "CE", "CI")) {
                                return r3;
                            }
                        }
                    }
                }
                return r14 + 3;
            }
        }
        return r14 + 2;
    }

    private int handleCC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r7) {
        int r0 = r7 + 2;
        if (contains(str, r0, 1, "I", ExifInterface.LONGITUDE_EAST, "H") && !contains(str, r0, 2, "HU")) {
            if ((r7 == 1 && charAt(str, r7 - 1) == 'A') || contains(str, r7 - 1, 5, "UCCEE", "UCCES")) {
                doubleMetaphoneResult.append("KS");
            } else {
                doubleMetaphoneResult.append('X');
            }
            return r7 + 3;
        }
        doubleMetaphoneResult.append('K');
        return r0;
    }

    private int handleCH(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r8) {
        if (r8 > 0 && contains(str, r8, 4, "CHAE")) {
            doubleMetaphoneResult.append('K', 'X');
        } else if (conditionCH0(str, r8)) {
            doubleMetaphoneResult.append('K');
        } else if (!conditionCH1(str, r8)) {
            if (r8 > 0) {
                if (contains(str, 0, 2, "MC")) {
                    doubleMetaphoneResult.append('K');
                } else {
                    doubleMetaphoneResult.append('X', 'K');
                }
            } else {
                doubleMetaphoneResult.append('X');
            }
            return r8 + 2;
        } else {
            doubleMetaphoneResult.append('K');
        }
        return r8 + 2;
    }

    private int handleD(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r8) {
        if (contains(str, r8, 2, "DG")) {
            int r0 = r8 + 2;
            if (contains(str, r0, 1, "I", ExifInterface.LONGITUDE_EAST, "Y")) {
                doubleMetaphoneResult.append('J');
                return r8 + 3;
            }
            doubleMetaphoneResult.append("TK");
            return r0;
        } else if (contains(str, r8, 2, "DT", "DD")) {
            doubleMetaphoneResult.append('T');
            return r8 + 2;
        } else {
            doubleMetaphoneResult.append('T');
            return r8 + 1;
        }
    }

    private int handleG(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r19, boolean z) {
        int r4 = r19 + 1;
        if (charAt(str, r4) == 'H') {
            return handleGH(str, doubleMetaphoneResult, r19);
        }
        if (charAt(str, r4) == 'N') {
            if (r19 == 1 && isVowel(charAt(str, 0)) && !z) {
                doubleMetaphoneResult.append("KN", "N");
            } else if (!contains(str, r19 + 2, 2, "EY") && charAt(str, r4) != 'Y' && !z) {
                doubleMetaphoneResult.append("N", "KN");
            } else {
                doubleMetaphoneResult.append("KN");
            }
        } else if (contains(str, r4, 2, "LI") && !z) {
            doubleMetaphoneResult.append("KL", "L");
        } else if (r19 == 0 && (charAt(str, r4) == 'Y' || contains(str, r4, 2, ES_EP_EB_EL_EY_IB_IL_IN_IE_EI_ER))) {
            doubleMetaphoneResult.append('K', 'J');
        } else {
            if ((contains(str, r4, 2, "ER") || charAt(str, r4) == 'Y') && !contains(str, 0, 6, "DANGER", "RANGER", "MANGER")) {
                int r7 = r19 - 1;
                if (!contains(str, r7, 1, ExifInterface.LONGITUDE_EAST, "I") && !contains(str, r7, 3, "RGY", "OGY")) {
                    doubleMetaphoneResult.append('K', 'J');
                    return r19 + 2;
                }
            }
            if (contains(str, r4, 1, ExifInterface.LONGITUDE_EAST, "I", "Y") || contains(str, r19 - 1, 4, "AGGI", "OGGI")) {
                if (contains(str, 0, 4, "VAN ", "VON ") || contains(str, 0, 3, "SCH") || contains(str, r4, 2, "ET")) {
                    doubleMetaphoneResult.append('K');
                } else if (contains(str, r4, 3, "IER")) {
                    doubleMetaphoneResult.append('J');
                } else {
                    doubleMetaphoneResult.append('J', 'K');
                }
                return r19 + 2;
            } else if (charAt(str, r4) == 'G') {
                int r42 = r19 + 2;
                doubleMetaphoneResult.append('K');
                return r42;
            } else {
                doubleMetaphoneResult.append('K');
                return r4;
            }
        }
        return r19 + 2;
    }

    private int handleGH(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r13) {
        if (r13 > 0 && !isVowel(charAt(str, r13 - 1))) {
            doubleMetaphoneResult.append('K');
        } else if (r13 == 0) {
            int r132 = r13 + 2;
            if (charAt(str, r132) == 'I') {
                doubleMetaphoneResult.append('J');
                return r132;
            }
            doubleMetaphoneResult.append('K');
            return r132;
        } else if ((r13 <= 1 || !contains(str, r13 - 2, 1, "B", "H", "D")) && ((r13 <= 2 || !contains(str, r13 - 3, 1, "B", "H", "D")) && (r13 <= 3 || !contains(str, r13 - 4, 1, "B", "H")))) {
            if (r13 > 2 && charAt(str, r13 - 1) == 'U' && contains(str, r13 - 3, 1, "C", RequestConfiguration.MAX_AD_CONTENT_RATING_G, "L", "R", "T")) {
                doubleMetaphoneResult.append('F');
            } else if (r13 > 0 && charAt(str, r13 - 1) != 'I') {
                doubleMetaphoneResult.append('K');
            }
        }
        return r13 + 2;
    }

    private int handleH(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r4) {
        if ((r4 == 0 || isVowel(charAt(str, r4 - 1))) && isVowel(charAt(str, r4 + 1))) {
            doubleMetaphoneResult.append('H');
            return r4 + 2;
        }
        return r4 + 1;
    }

    private int handleJ(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r12, boolean z) {
        if (contains(str, r12, 4, "JOSE") || contains(str, 0, 4, "SAN ")) {
            if ((r12 == 0 && charAt(str, r12 + 4) == ' ') || str.length() == 4 || contains(str, 0, 4, "SAN ")) {
                doubleMetaphoneResult.append('H');
            } else {
                doubleMetaphoneResult.append('J', 'H');
            }
            return r12 + 1;
        }
        if (r12 == 0 && !contains(str, r12, 4, "JOSE")) {
            doubleMetaphoneResult.append('J', 'A');
        } else {
            int r0 = r12 - 1;
            if (isVowel(charAt(str, r0)) && !z) {
                int r13 = r12 + 1;
                if (charAt(str, r13) == 'A' || charAt(str, r13) == 'O') {
                    doubleMetaphoneResult.append('J', 'H');
                }
            }
            if (r12 == str.length() - 1) {
                doubleMetaphoneResult.append('J', ' ');
            } else if (!contains(str, r12 + 1, 1, L_T_K_S_N_M_B_Z) && !contains(str, r0, 1, ExifInterface.LATITUDE_SOUTH, "K", "L")) {
                doubleMetaphoneResult.append('J');
            }
        }
        int r11 = r12 + 1;
        return charAt(str, r11) == 'J' ? r12 + 2 : r11;
    }

    private int handleL(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r6) {
        int r0 = r6 + 1;
        if (charAt(str, r0) == 'L') {
            if (conditionL0(str, r6)) {
                doubleMetaphoneResult.appendPrimary(Matrix.MATRIX_TYPE_RANDOM_LT);
            } else {
                doubleMetaphoneResult.append(Matrix.MATRIX_TYPE_RANDOM_LT);
            }
            return r6 + 2;
        }
        doubleMetaphoneResult.append(Matrix.MATRIX_TYPE_RANDOM_LT);
        return r0;
    }

    private int handleP(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r6) {
        int r0 = r6 + 1;
        if (charAt(str, r0) == 'H') {
            doubleMetaphoneResult.append('F');
            return r6 + 2;
        }
        doubleMetaphoneResult.append('P');
        if (contains(str, r0, 1, "P", "B")) {
            r0 = r6 + 2;
        }
        return r0;
    }

    private int handleR(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r7, boolean z) {
        if (r7 == str.length() - 1 && !z && contains(str, r7 - 2, 2, "IE") && !contains(str, r7 - 4, 2, "ME", RequestConfiguration.MAX_AD_CONTENT_RATING_MA)) {
            doubleMetaphoneResult.appendAlternate(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        } else {
            doubleMetaphoneResult.append(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        }
        int r6 = r7 + 1;
        return charAt(str, r6) == 'R' ? r7 + 2 : r6;
    }

    private int handleS(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r12, boolean z) {
        if (!contains(str, r12 - 1, 3, "ISL", "YSL")) {
            if (r12 == 0 && contains(str, r12, 5, "SUGAR")) {
                doubleMetaphoneResult.append('X', 'S');
            } else {
                if (contains(str, r12, 2, "SH")) {
                    if (contains(str, r12 + 1, 4, "HEIM", "HOEK", "HOLM", "HOLZ")) {
                        doubleMetaphoneResult.append('S');
                    } else {
                        doubleMetaphoneResult.append('X');
                    }
                } else if (contains(str, r12, 3, "SIO", "SIA") || contains(str, r12, 4, "SIAN")) {
                    if (z) {
                        doubleMetaphoneResult.append('S');
                    } else {
                        doubleMetaphoneResult.append('S', 'X');
                    }
                    return r12 + 3;
                } else {
                    if (r12 != 0 || !contains(str, r12 + 1, 1, "M", "N", "L", ExifInterface.LONGITUDE_WEST)) {
                        int r2 = r12 + 1;
                        if (!contains(str, r2, 1, "Z")) {
                            if (contains(str, r12, 2, "SC")) {
                                return handleSC(str, doubleMetaphoneResult, r12);
                            }
                            if (r12 == str.length() - 1 && contains(str, r12 - 2, 2, "AI", "OI")) {
                                doubleMetaphoneResult.appendAlternate('S');
                            } else {
                                doubleMetaphoneResult.append('S');
                            }
                            if (!contains(str, r2, 1, ExifInterface.LATITUDE_SOUTH, "Z")) {
                                return r2;
                            }
                        }
                    }
                    doubleMetaphoneResult.append('S', 'X');
                    int r11 = r12 + 1;
                    if (!contains(str, r11, 1, "Z")) {
                        return r11;
                    }
                }
                return r12 + 2;
            }
        }
        return r12 + 1;
    }

    private int handleSC(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r14) {
        int r0 = r14 + 2;
        if (charAt(str, r0) == 'H') {
            int r02 = r14 + 3;
            if (contains(str, r02, 2, "OO", "ER", "EN", "UY", "ED", "EM")) {
                if (contains(str, r02, 2, "ER", "EN")) {
                    doubleMetaphoneResult.append("X", "SK");
                } else {
                    doubleMetaphoneResult.append("SK");
                }
            } else if (r14 == 0 && !isVowel(charAt(str, 3)) && charAt(str, 3) != 'W') {
                doubleMetaphoneResult.append('X', 'S');
            } else {
                doubleMetaphoneResult.append('X');
            }
        } else if (contains(str, r0, 1, "I", ExifInterface.LONGITUDE_EAST, "Y")) {
            doubleMetaphoneResult.append('S');
        } else {
            doubleMetaphoneResult.append("SK");
        }
        return r14 + 3;
    }

    private int handleT(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r9) {
        if (contains(str, r9, 4, "TION")) {
            doubleMetaphoneResult.append('X');
        } else if (contains(str, r9, 3, "TIA", "TCH")) {
            doubleMetaphoneResult.append('X');
        } else if (contains(str, r9, 2, "TH") || contains(str, r9, 3, "TTH")) {
            int r92 = r9 + 2;
            if (contains(str, r92, 2, "OM", "AM") || contains(str, 0, 4, "VAN ", "VON ") || contains(str, 0, 3, "SCH")) {
                doubleMetaphoneResult.append('T');
                return r92;
            }
            doubleMetaphoneResult.append('0', 'T');
            return r92;
        } else {
            doubleMetaphoneResult.append('T');
            int r8 = r9 + 1;
            return contains(str, r8, 1, "T", "D") ? r9 + 2 : r8;
        }
        return r9 + 3;
    }

    private int handleW(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r10) {
        int r1 = 2;
        if (contains(str, r10, 2, "WR")) {
            doubleMetaphoneResult.append(Matrix.MATRIX_TYPE_RANDOM_REGULAR);
        } else {
            if (r10 == 0) {
                int r2 = r10 + 1;
                if (isVowel(charAt(str, r2)) || contains(str, r10, 2, "WH")) {
                    if (isVowel(charAt(str, r2))) {
                        doubleMetaphoneResult.append('A', 'F');
                    } else {
                        doubleMetaphoneResult.append('A');
                    }
                    return r2;
                }
            }
            if ((r10 == str.length() - 1 && isVowel(charAt(str, r10 - 1))) || contains(str, r10 - 1, 5, "EWSKI", "EWSKY", "OWSKI", "OWSKY") || contains(str, 0, 3, "SCH")) {
                doubleMetaphoneResult.appendAlternate('F');
            } else {
                r1 = 4;
                if (contains(str, r10, 4, "WICZ", "WITZ")) {
                    doubleMetaphoneResult.append("TS", "FX");
                }
            }
            return r10 + 1;
        }
        return r10 + r1;
    }

    private int handleX(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r8) {
        if (r8 == 0) {
            doubleMetaphoneResult.append('S');
            return r8 + 1;
        }
        if (r8 != str.length() - 1 || (!contains(str, r8 - 3, 3, "IAU", "EAU") && !contains(str, r8 - 2, 2, "AU", "OU"))) {
            doubleMetaphoneResult.append("KS");
        }
        int r7 = r8 + 1;
        return contains(str, r7, 1, "C", "X") ? r8 + 2 : r7;
    }

    private int handleZ(String str, DoubleMetaphoneResult doubleMetaphoneResult, int r8, boolean z) {
        int r0 = r8 + 1;
        if (charAt(str, r0) == 'H') {
            doubleMetaphoneResult.append('J');
            return r8 + 2;
        }
        if (contains(str, r0, 2, "ZO", "ZI", "ZA") || (z && r8 > 0 && charAt(str, r8 - 1) != 'T')) {
            doubleMetaphoneResult.append(ExifInterface.LATITUDE_SOUTH, "TS");
        } else {
            doubleMetaphoneResult.append('S');
        }
        if (charAt(str, r0) == 'Z') {
            r0 = r8 + 2;
        }
        return r0;
    }

    private boolean conditionC0(String str, int r8) {
        if (contains(str, r8, 4, "CHIA")) {
            return true;
        }
        if (r8 <= 1) {
            return false;
        }
        int r2 = r8 - 2;
        if (!isVowel(charAt(str, r2)) && contains(str, r8 - 1, 3, "ACH")) {
            char charAt = charAt(str, r8 + 2);
            return !(charAt == 'I' || charAt == 'E') || contains(str, r2, 6, "BACHER", "MACHER");
        }
        return false;
    }

    private boolean conditionCH0(String str, int r10) {
        if (r10 != 0) {
            return false;
        }
        int r102 = r10 + 1;
        return (contains(str, r102, 5, "HARAC", "HARIS") || contains(str, r102, 3, "HOR", "HYM", "HIA", "HEM")) && !contains(str, 0, 5, "CHORE");
    }

    private boolean conditionCH1(String str, int r10) {
        if (!contains(str, 0, 4, "VAN ", "VON ") && !contains(str, 0, 3, "SCH") && !contains(str, r10 - 2, 6, "ORCHES", "ARCHIT", "ORCHID")) {
            int r0 = r10 + 2;
            if (!contains(str, r0, 1, "T", ExifInterface.LATITUDE_SOUTH)) {
                if (!contains(str, r10 - 1, 1, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "O", "U", ExifInterface.LONGITUDE_EAST) && r10 != 0) {
                    return false;
                }
                if (!contains(str, r0, 1, L_R_N_M_B_H_F_V_W_SPACE) && r10 + 1 != str.length() - 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean conditionL0(String str, int r9) {
        if (r9 == str.length() - 3 && contains(str, r9 - 1, 4, "ILLO", "ILLA", "ALLE")) {
            return true;
        }
        return (contains(str, str.length() - 2, 2, "AS", "OS") || contains(str, str.length() - 1, 1, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "O")) && contains(str, r9 - 1, 4, "ALLE");
    }

    private boolean conditionM0(String str, int r7) {
        int r0 = r7 + 1;
        if (charAt(str, r0) == 'M') {
            return true;
        }
        return contains(str, r7 + (-1), 3, "UMB") && (r0 == str.length() - 1 || contains(str, r7 + 2, 2, "ER"));
    }

    private boolean isSlavoGermanic(String str) {
        return str.indexOf(87) > -1 || str.indexOf(75) > -1 || str.indexOf("CZ") > -1 || str.indexOf("WITZ") > -1;
    }

    private boolean isVowel(char c) {
        return VOWELS.indexOf(c) != -1;
    }

    private boolean isSilentStart(String str) {
        for (String str2 : SILENT_START) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    private String cleanInput(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return null;
        }
        return trim.toUpperCase(Locale.ENGLISH);
    }

    protected char charAt(String str, int r3) {
        if (r3 < 0 || r3 >= str.length()) {
            return (char) 0;
        }
        return str.charAt(r3);
    }

    protected static boolean contains(String str, int r3, int r4, String... strArr) {
        int r42;
        if (r3 < 0 || (r42 = r4 + r3) > str.length()) {
            return false;
        }
        String substring = str.substring(r3, r42);
        for (String str2 : strArr) {
            if (substring.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    /* loaded from: classes5.dex */
    public class DoubleMetaphoneResult {
        private final StringBuilder alternate;
        private final int maxLength;
        private final StringBuilder primary;

        public DoubleMetaphoneResult(int r4) {
            this.primary = new StringBuilder(DoubleMetaphone.this.getMaxCodeLen());
            this.alternate = new StringBuilder(DoubleMetaphone.this.getMaxCodeLen());
            this.maxLength = r4;
        }

        public void append(char c) {
            appendPrimary(c);
            appendAlternate(c);
        }

        public void append(char c, char c2) {
            appendPrimary(c);
            appendAlternate(c2);
        }

        public void appendPrimary(char c) {
            if (this.primary.length() < this.maxLength) {
                this.primary.append(c);
            }
        }

        public void appendAlternate(char c) {
            if (this.alternate.length() < this.maxLength) {
                this.alternate.append(c);
            }
        }

        public void append(String str) {
            appendPrimary(str);
            appendAlternate(str);
        }

        public void append(String str, String str2) {
            appendPrimary(str);
            appendAlternate(str2);
        }

        public void appendPrimary(String str) {
            int length = this.maxLength - this.primary.length();
            if (str.length() <= length) {
                this.primary.append(str);
            } else {
                this.primary.append(str.substring(0, length));
            }
        }

        public void appendAlternate(String str) {
            int length = this.maxLength - this.alternate.length();
            if (str.length() <= length) {
                this.alternate.append(str);
            } else {
                this.alternate.append(str.substring(0, length));
            }
        }

        public String getPrimary() {
            return this.primary.toString();
        }

        public String getAlternate() {
            return this.alternate.toString();
        }

        public boolean isComplete() {
            return this.primary.length() >= this.maxLength && this.alternate.length() >= this.maxLength;
        }
    }
}
