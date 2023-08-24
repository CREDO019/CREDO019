package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.p028io.IOUtils;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;

/* loaded from: classes5.dex */
public class ColognePhonetic implements StringEncoder {
    private static final char[] AEIJOUY = {'A', 'E', 'I', 'J', 'O', Matrix.MATRIX_TYPE_RANDOM_UT, 'Y'};
    private static final char[] SCZ = {'S', 'C', Matrix.MATRIX_TYPE_ZERO};
    private static final char[] WFPV = {'W', 'F', 'P', 'V'};
    private static final char[] GKQ = {'G', 'K', 'Q'};
    private static final char[] CKQ = {'C', 'K', 'Q'};
    private static final char[] AHKLOQRUX = {'A', 'H', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'O', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, Matrix.MATRIX_TYPE_RANDOM_UT, 'X'};

    /* renamed from: SZ */
    private static final char[] f1553SZ = {'S', Matrix.MATRIX_TYPE_ZERO};
    private static final char[] AHOUKQX = {'A', 'H', 'O', Matrix.MATRIX_TYPE_RANDOM_UT, 'K', 'Q', 'X'};
    private static final char[] TDX = {'T', 'D', 'X'};
    private static final char[][] PREPROCESS_MAP = {new char[]{196, 'A'}, new char[]{220, Matrix.MATRIX_TYPE_RANDOM_UT}, new char[]{214, 'O'}, new char[]{223, 'S'}};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public abstract class CologneBuffer {
        protected final char[] data;
        protected int length;

        protected abstract char[] copyData(int r1, int r2);

        public CologneBuffer(char[] cArr) {
            this.length = 0;
            this.data = cArr;
            this.length = cArr.length;
        }

        public CologneBuffer(int r2) {
            this.length = 0;
            this.data = new char[r2];
            this.length = 0;
        }

        public int length() {
            return this.length;
        }

        public String toString() {
            return new String(copyData(0, this.length));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class CologneOutputBuffer extends CologneBuffer {
        public CologneOutputBuffer(int r2) {
            super(r2);
        }

        public void addRight(char c) {
            this.data[this.length] = c;
            this.length++;
        }

        @Override // org.apache.commons.codec.language.ColognePhonetic.CologneBuffer
        protected char[] copyData(int r4, int r5) {
            char[] cArr = new char[r5];
            System.arraycopy(this.data, r4, cArr, 0, r5);
            return cArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes5.dex */
    public class CologneInputBuffer extends CologneBuffer {
        public CologneInputBuffer(char[] cArr) {
            super(cArr);
        }

        public void addLeft(char c) {
            this.length++;
            this.data[getNextPos()] = c;
        }

        @Override // org.apache.commons.codec.language.ColognePhonetic.CologneBuffer
        protected char[] copyData(int r5, int r6) {
            char[] cArr = new char[r6];
            System.arraycopy(this.data, (this.data.length - this.length) + r5, cArr, 0, r6);
            return cArr;
        }

        public char getNextChar() {
            return this.data[getNextPos()];
        }

        protected int getNextPos() {
            return this.data.length - this.length;
        }

        public char removeNext() {
            this.length--;
            return getNextChar();
        }
    }

    private static boolean arrayContains(char[] cArr, char c) {
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    public String colognePhonetic(String str) {
        if (str == null) {
            return null;
        }
        String preprocess = preprocess(str);
        CologneOutputBuffer cologneOutputBuffer = new CologneOutputBuffer(preprocess.length() * 2);
        CologneInputBuffer cologneInputBuffer = new CologneInputBuffer(preprocess.toCharArray());
        int length = cologneInputBuffer.length();
        char c = IOUtils.DIR_SEPARATOR_UNIX;
        char c2 = '-';
        while (length > 0) {
            char removeNext = cologneInputBuffer.removeNext();
            int length2 = cologneInputBuffer.length();
            char nextChar = length2 > 0 ? cologneInputBuffer.getNextChar() : '-';
            char c3 = '4';
            if (arrayContains(AEIJOUY, removeNext)) {
                c3 = '0';
            } else if (removeNext == 'H' || removeNext < 'A' || removeNext > 'Z') {
                if (c == '/') {
                    length = length2;
                } else {
                    c3 = '-';
                }
            } else if (removeNext == 'B' || (removeNext == 'P' && nextChar != 'H')) {
                c3 = '1';
            } else if ((removeNext == 'D' || removeNext == 'T') && !arrayContains(SCZ, nextChar)) {
                c3 = '2';
            } else if (arrayContains(WFPV, removeNext)) {
                c3 = '3';
            } else if (!arrayContains(GKQ, removeNext)) {
                if (removeNext != 'X' || arrayContains(CKQ, c2)) {
                    if (removeNext != 'S' && removeNext != 'Z') {
                        if (removeNext == 'C') {
                            if (c == '/') {
                            }
                        } else if (!arrayContains(TDX, removeNext)) {
                            c3 = removeNext == 'R' ? '7' : removeNext == 'L' ? '5' : (removeNext == 'M' || removeNext == 'N') ? '6' : removeNext;
                        }
                    }
                    c3 = '8';
                } else {
                    cologneInputBuffer.addLeft('S');
                    length2++;
                }
            }
            if (c3 != '-' && ((c != c3 && (c3 != '0' || c == '/')) || c3 < '0' || c3 > '8')) {
                cologneOutputBuffer.addRight(c3);
            }
            c2 = removeNext;
            length = length2;
            c = c3;
        }
        return cologneOutputBuffer.toString();
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("This method's parameter was expected to be of the type " + String.class.getName() + ". But actually it was of the type " + obj.getClass().getName() + ".");
        }
        return encode((String) obj);
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return colognePhonetic(str);
    }

    public boolean isEncodeEqual(String str, String str2) {
        return colognePhonetic(str).equals(colognePhonetic(str2));
    }

    private String preprocess(String str) {
        char[] charArray = str.toUpperCase(Locale.GERMAN).toCharArray();
        for (int r1 = 0; r1 < charArray.length; r1++) {
            if (charArray[r1] > 'Z') {
                char[][] cArr = PREPROCESS_MAP;
                int length = cArr.length;
                int r4 = 0;
                while (true) {
                    if (r4 < length) {
                        char[] cArr2 = cArr[r4];
                        if (charArray[r1] == cArr2[0]) {
                            charArray[r1] = cArr2[1];
                            break;
                        }
                        r4++;
                    }
                }
            }
        }
        return new String(charArray);
    }
}
