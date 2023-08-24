package org.bouncycastle.util;

import com.google.android.exoplayer2.extractor.p011ts.PsExtractor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Vector;
import org.bouncycastle.util.encoders.UTF8;

/* loaded from: classes4.dex */
public final class Strings {
    private static String LINE_SEPARATOR;

    /* loaded from: classes4.dex */
    private static class StringListImpl extends ArrayList<String> implements StringList {
        private StringListImpl() {
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public void add(int r1, String str) {
            super.add(r1, (int) str);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean add(String str) {
            return super.add((StringListImpl) str);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List, org.bouncycastle.util.StringList
        public /* bridge */ /* synthetic */ String get(int r1) {
            return (String) super.get(r1);
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public String set(int r1, String str) {
            return (String) super.set(r1, (int) str);
        }

        @Override // org.bouncycastle.util.StringList
        public String[] toStringArray() {
            int size = size();
            String[] strArr = new String[size];
            for (int r2 = 0; r2 != size; r2++) {
                strArr[r2] = (String) get(r2);
            }
            return strArr;
        }

        @Override // org.bouncycastle.util.StringList
        public String[] toStringArray(int r5, int r6) {
            String[] strArr = new String[r6 - r5];
            for (int r1 = r5; r1 != size() && r1 != r6; r1++) {
                strArr[r1 - r5] = (String) get(r1);
            }
            return strArr;
        }
    }

    static {
        try {
            try {
                LINE_SEPARATOR = (String) AccessController.doPrivileged(new PrivilegedAction<String>() { // from class: org.bouncycastle.util.Strings.1
                    @Override // java.security.PrivilegedAction
                    public String run() {
                        return System.getProperty("line.separator");
                    }
                });
            } catch (Exception unused) {
                LINE_SEPARATOR = "\n";
            }
        } catch (Exception unused2) {
            LINE_SEPARATOR = String.format("%n", new Object[0]);
        }
    }

    public static char[] asCharArray(byte[] bArr) {
        int length = bArr.length;
        char[] cArr = new char[length];
        for (int r2 = 0; r2 != length; r2++) {
            cArr[r2] = (char) (bArr[r2] & 255);
        }
        return cArr;
    }

    public static boolean constantTimeAreEqual(String str, String str2) {
        boolean z = str.length() == str2.length();
        int length = str.length();
        for (int r4 = 0; r4 != length; r4++) {
            z &= str.charAt(r4) == str2.charAt(r4);
        }
        return z;
    }

    public static String fromByteArray(byte[] bArr) {
        return new String(asCharArray(bArr));
    }

    public static String fromUTF8ByteArray(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        int transcodeToUTF16 = UTF8.transcodeToUTF16(bArr, cArr);
        if (transcodeToUTF16 >= 0) {
            return new String(cArr, 0, transcodeToUTF16);
        }
        throw new IllegalArgumentException("Invalid UTF-8 input");
    }

    public static String lineSeparator() {
        return LINE_SEPARATOR;
    }

    public static StringList newList() {
        return new StringListImpl();
    }

    public static String[] split(String str, char c) {
        int r2;
        Vector vector = new Vector();
        boolean z = true;
        while (true) {
            if (!z) {
                break;
            }
            int indexOf = str.indexOf(c);
            if (indexOf > 0) {
                vector.addElement(str.substring(0, indexOf));
                str = str.substring(indexOf + 1);
            } else {
                vector.addElement(str);
                z = false;
            }
        }
        int size = vector.size();
        String[] strArr = new String[size];
        for (r2 = 0; r2 != size; r2++) {
            strArr[r2] = (String) vector.elementAt(r2);
        }
        return strArr;
    }

    public static int toByteArray(String str, byte[] bArr, int r6) {
        int length = str.length();
        for (int r1 = 0; r1 < length; r1++) {
            bArr[r6 + r1] = (byte) str.charAt(r1);
        }
        return length;
    }

    public static byte[] toByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int r2 = 0; r2 != length; r2++) {
            bArr[r2] = (byte) str.charAt(r2);
        }
        return bArr;
    }

    public static byte[] toByteArray(char[] cArr) {
        int length = cArr.length;
        byte[] bArr = new byte[length];
        for (int r2 = 0; r2 != length; r2++) {
            bArr[r2] = (byte) cArr[r2];
        }
        return bArr;
    }

    public static String toLowerCase(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int r1 = 0; r1 != charArray.length; r1++) {
            char c = charArray[r1];
            if ('A' <= c && 'Z' >= c) {
                charArray[r1] = (char) ((c - 'A') + 97);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }

    public static void toUTF8ByteArray(char[] cArr, OutputStream outputStream) throws IOException {
        int r3;
        char c;
        int r0 = 0;
        while (r0 < cArr.length) {
            char c2 = cArr[r0];
            char c3 = c2;
            if (c2 >= 128) {
                if (c2 < 2048) {
                    r3 = (c2 >> 6) | 192;
                } else if (c2 < 55296 || c2 > 57343) {
                    outputStream.write((c2 >> '\f') | 224);
                    r3 = ((c2 >> 6) & 63) | 128;
                } else {
                    r0++;
                    if (r0 >= cArr.length) {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                    char c4 = cArr[r0];
                    if (c2 > 56319) {
                        throw new IllegalStateException("invalid UTF-16 codepoint");
                    }
                    int r1 = (((c2 & 1023) << 10) | (c4 & 1023)) + 65536;
                    outputStream.write((r1 >> 18) | PsExtractor.VIDEO_STREAM_MASK);
                    outputStream.write(((r1 >> 12) & 63) | 128);
                    outputStream.write(((r1 >> 6) & 63) | 128);
                    c = r1;
                    c3 = (c & 63) | 128;
                }
                outputStream.write(r3);
                c = c2;
                c3 = (c & 63) | 128;
            }
            outputStream.write(c3);
            r0++;
        }
    }

    public static byte[] toUTF8ByteArray(String str) {
        return toUTF8ByteArray(str.toCharArray());
    }

    public static byte[] toUTF8ByteArray(char[] cArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            toUTF8ByteArray(cArr, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new IllegalStateException("cannot encode string to byte array!");
        }
    }

    public static String toUpperCase(String str) {
        char[] charArray = str.toCharArray();
        boolean z = false;
        for (int r1 = 0; r1 != charArray.length; r1++) {
            char c = charArray[r1];
            if ('a' <= c && 'z' >= c) {
                charArray[r1] = (char) ((c - 'a') + 65);
                z = true;
            }
        }
        return z ? new String(charArray) : str;
    }
}
