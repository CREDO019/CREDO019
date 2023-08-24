package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;

/* loaded from: classes5.dex */
public class Metaphone implements StringEncoder {
    private static final String FRONTV = "EIY";
    private static final String VARSON = "CSPTG";
    private static final String VOWELS = "AEIOU";
    private int maxCodeLen = 4;

    private boolean isLastChar(int r2, int r3) {
        return r3 + 1 == r2;
    }

    public String metaphone(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return "";
        }
        if (length == 1) {
            return str.toUpperCase(Locale.ENGLISH);
        }
        char[] charArray = str.toUpperCase(Locale.ENGLISH).toCharArray();
        StringBuilder sb = new StringBuilder(40);
        StringBuilder sb2 = new StringBuilder(10);
        int r5 = 0;
        char c = charArray[0];
        if (c != 'A') {
            if (c == 'G' || c == 'K' || c == 'P') {
                if (charArray[1] == 'N') {
                    sb.append(charArray, 1, charArray.length - 1);
                } else {
                    sb.append(charArray);
                }
            } else if (c != 'W') {
                if (c == 'X') {
                    charArray[0] = 'S';
                    sb.append(charArray);
                } else {
                    sb.append(charArray);
                }
            } else if (charArray[1] == 'R') {
                sb.append(charArray, 1, charArray.length - 1);
            } else if (charArray[1] == 'H') {
                sb.append(charArray, 1, charArray.length - 1);
                sb.setCharAt(0, 'W');
            } else {
                sb.append(charArray);
            }
        } else if (charArray[1] == 'E') {
            sb.append(charArray, 1, charArray.length - 1);
        } else {
            sb.append(charArray);
        }
        int length2 = sb.length();
        while (sb2.length() < getMaxCodeLen() && r5 < length2) {
            char charAt = sb.charAt(r5);
            if (charAt == 'C' || !isPreviousChar(sb, r5, charAt)) {
                switch (charAt) {
                    case 'A':
                    case 'E':
                    case 'I':
                    case 'O':
                    case 'U':
                        if (r5 == 0) {
                            sb2.append(charAt);
                            break;
                        }
                        break;
                    case 'B':
                        if (!isPreviousChar(sb, r5, 'M') || !isLastChar(length2, r5)) {
                            sb2.append(charAt);
                            break;
                        }
                        break;
                    case 'C':
                        if (!isPreviousChar(sb, r5, 'S') || isLastChar(length2, r5) || FRONTV.indexOf(sb.charAt(r5 + 1)) < 0) {
                            if (regionMatch(sb, r5, "CIA")) {
                                sb2.append('X');
                                break;
                            } else if (!isLastChar(length2, r5) && FRONTV.indexOf(sb.charAt(r5 + 1)) >= 0) {
                                sb2.append('S');
                                break;
                            } else if (isPreviousChar(sb, r5, 'S') && isNextChar(sb, r5, 'H')) {
                                sb2.append('K');
                                break;
                            } else if (isNextChar(sb, r5, 'H')) {
                                if (r5 == 0 && length2 >= 3 && isVowel(sb, 2)) {
                                    sb2.append('K');
                                    break;
                                } else {
                                    sb2.append('X');
                                    break;
                                }
                            } else {
                                sb2.append('K');
                                break;
                            }
                        }
                        break;
                    case 'D':
                        if (!isLastChar(length2, r5 + 1) && isNextChar(sb, r5, 'G')) {
                            int r6 = r5 + 2;
                            if (FRONTV.indexOf(sb.charAt(r6)) >= 0) {
                                sb2.append('J');
                                r5 = r6;
                                break;
                            }
                        }
                        sb2.append('T');
                        break;
                    case 'F':
                    case 'J':
                    case 'L':
                    case 'M':
                    case 'N':
                    case 'R':
                        sb2.append(charAt);
                        break;
                    case 'G':
                        int r62 = r5 + 1;
                        if ((!isLastChar(length2, r62) || !isNextChar(sb, r5, 'H')) && ((isLastChar(length2, r62) || !isNextChar(sb, r5, 'H') || isVowel(sb, r5 + 2)) && (r5 <= 0 || (!regionMatch(sb, r5, "GN") && !regionMatch(sb, r5, "GNED"))))) {
                            boolean isPreviousChar = isPreviousChar(sb, r5, 'G');
                            if (!isLastChar(length2, r5) && FRONTV.indexOf(sb.charAt(r62)) >= 0 && !isPreviousChar) {
                                sb2.append('J');
                                break;
                            } else {
                                sb2.append('K');
                                break;
                            }
                        }
                        break;
                    case 'H':
                        if (!isLastChar(length2, r5) && ((r5 <= 0 || VARSON.indexOf(sb.charAt(r5 - 1)) < 0) && isVowel(sb, r5 + 1))) {
                            sb2.append('H');
                            break;
                        }
                        break;
                    case 'K':
                        if (r5 > 0) {
                            if (!isPreviousChar(sb, r5, 'C')) {
                                sb2.append(charAt);
                                break;
                            }
                        } else {
                            sb2.append(charAt);
                            break;
                        }
                        break;
                    case 'P':
                        if (isNextChar(sb, r5, 'H')) {
                            sb2.append('F');
                            break;
                        } else {
                            sb2.append(charAt);
                            break;
                        }
                    case 'Q':
                        sb2.append('K');
                        break;
                    case 'S':
                        if (regionMatch(sb, r5, "SH") || regionMatch(sb, r5, "SIO") || regionMatch(sb, r5, "SIA")) {
                            sb2.append('X');
                            break;
                        } else {
                            sb2.append('S');
                            break;
                        }
                        break;
                    case 'T':
                        if (regionMatch(sb, r5, "TIA") || regionMatch(sb, r5, "TIO")) {
                            sb2.append('X');
                            break;
                        } else if (!regionMatch(sb, r5, "TCH")) {
                            if (regionMatch(sb, r5, "TH")) {
                                sb2.append('0');
                                break;
                            } else {
                                sb2.append('T');
                                break;
                            }
                        }
                        break;
                    case 'V':
                        sb2.append('F');
                        break;
                    case 'W':
                    case 'Y':
                        if (!isLastChar(length2, r5) && isVowel(sb, r5 + 1)) {
                            sb2.append(charAt);
                            break;
                        }
                        break;
                    case 'X':
                        sb2.append('K');
                        sb2.append('S');
                        break;
                    case 'Z':
                        sb2.append('S');
                        break;
                }
                r5++;
            } else {
                r5++;
            }
            if (sb2.length() > getMaxCodeLen()) {
                sb2.setLength(getMaxCodeLen());
            }
        }
        return sb2.toString();
    }

    private boolean isVowel(StringBuilder sb, int r2) {
        return VOWELS.indexOf(sb.charAt(r2)) >= 0;
    }

    private boolean isPreviousChar(StringBuilder sb, int r4, char c) {
        return r4 > 0 && r4 < sb.length() && sb.charAt(r4 - 1) == c;
    }

    private boolean isNextChar(StringBuilder sb, int r5, char c) {
        return r5 >= 0 && r5 < sb.length() - 1 && sb.charAt(r5 + 1) == c;
    }

    private boolean regionMatch(StringBuilder sb, int r4, String str) {
        if (r4 < 0 || (str.length() + r4) - 1 >= sb.length()) {
            return false;
        }
        return sb.substring(r4, str.length() + r4).equals(str);
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("Parameter supplied to Metaphone encode is not of type java.lang.String");
        }
        return metaphone((String) obj);
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return metaphone(str);
    }

    public boolean isMetaphoneEqual(String str, String str2) {
        return metaphone(str).equals(metaphone(str2));
    }

    public int getMaxCodeLen() {
        return this.maxCodeLen;
    }

    public void setMaxCodeLen(int r1) {
        this.maxCodeLen = r1;
    }
}
