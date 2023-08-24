package org.bouncycastle.crypto.generators;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.io.ByteArrayOutputStream;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* loaded from: classes5.dex */
public class OpenBSDBCrypt {
    private static final Set<String> allowedVersions;
    private static final String defaultVersion = "2y";
    private static final byte[] encodingTable = {46, 47, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57};
    private static final byte[] decodingTable = new byte[128];

    static {
        HashSet hashSet = new HashSet();
        allowedVersions = hashSet;
        hashSet.add("2");
        hashSet.add("2x");
        hashSet.add("2a");
        hashSet.add(defaultVersion);
        hashSet.add("2b");
        int r0 = 0;
        int r1 = 0;
        while (true) {
            byte[] bArr = decodingTable;
            if (r1 >= bArr.length) {
                break;
            }
            bArr[r1] = -1;
            r1++;
        }
        while (true) {
            byte[] bArr2 = encodingTable;
            if (r0 >= bArr2.length) {
                return;
            }
            decodingTable[bArr2[r0]] = (byte) r0;
            r0++;
        }
    }

    private OpenBSDBCrypt() {
    }

    public static boolean checkPassword(String str, byte[] bArr) {
        if (bArr != null) {
            return doCheckPassword(str, Arrays.clone(bArr));
        }
        throw new IllegalArgumentException("Missing password.");
    }

    public static boolean checkPassword(String str, char[] cArr) {
        if (cArr != null) {
            return doCheckPassword(str, Strings.toUTF8ByteArray(cArr));
        }
        throw new IllegalArgumentException("Missing password.");
    }

    private static String createBcryptString(String str, byte[] bArr, byte[] bArr2, int r6) {
        String num;
        if (!allowedVersions.contains(str)) {
            throw new IllegalArgumentException("Version " + str + " is not accepted by this implementation.");
        }
        StringBuilder sb = new StringBuilder(60);
        sb.append('$');
        sb.append(str);
        sb.append('$');
        if (r6 < 10) {
            num = SessionDescription.SUPPORTED_SDP_VERSION + r6;
        } else {
            num = Integer.toString(r6);
        }
        sb.append(num);
        sb.append('$');
        encodeData(sb, bArr2);
        encodeData(sb, BCrypt.generate(bArr, bArr2, r6));
        return sb.toString();
    }

    private static byte[] decodeSaltString(String str) {
        char[] charArray = str.toCharArray();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16);
        if (charArray.length != 22) {
            throw new DataLengthException("Invalid base64 salt length: " + charArray.length + " , 22 required.");
        }
        for (char c : charArray) {
            if (c > 'z' || c < '.' || (c > '9' && c < 'A')) {
                throw new IllegalArgumentException("Salt string contains invalid character: " + ((int) c));
            }
        }
        char[] cArr = new char[24];
        System.arraycopy(charArray, 0, cArr, 0, charArray.length);
        for (int r10 = 0; r10 < 24; r10 += 4) {
            byte[] bArr = decodingTable;
            byte b = bArr[cArr[r10]];
            byte b2 = bArr[cArr[r10 + 1]];
            byte b3 = bArr[cArr[r10 + 2]];
            byte b4 = bArr[cArr[r10 + 3]];
            byteArrayOutputStream.write((b << 2) | (b2 >> 4));
            byteArrayOutputStream.write((b2 << 4) | (b3 >> 2));
            byteArrayOutputStream.write(b4 | (b3 << 6));
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byte[] bArr2 = new byte[16];
        System.arraycopy(byteArray, 0, bArr2, 0, 16);
        return bArr2;
    }

    private static boolean doCheckPassword(String str, byte[] bArr) {
        String substring;
        if (str != null) {
            if (str.charAt(1) == '2') {
                int length = str.length();
                if (length != 60 && (length != 59 || str.charAt(2) != '$')) {
                    throw new DataLengthException("Bcrypt String length: " + length + ", 60 required.");
                }
                int r8 = 3;
                if (str.charAt(2) == '$') {
                    if (str.charAt(0) != '$' || str.charAt(5) != '$') {
                        throw new IllegalArgumentException("Invalid Bcrypt String format.");
                    }
                } else if (str.charAt(0) != '$' || str.charAt(3) != '$' || str.charAt(6) != '$') {
                    throw new IllegalArgumentException("Invalid Bcrypt String format.");
                }
                if (str.charAt(2) == '$') {
                    substring = str.substring(1, 2);
                } else {
                    substring = str.substring(1, 3);
                    r8 = 4;
                }
                if (!allowedVersions.contains(substring)) {
                    throw new IllegalArgumentException("Bcrypt version '" + substring + "' is not supported by this implementation");
                }
                String substring2 = str.substring(r8, r8 + 2);
                try {
                    int parseInt = Integer.parseInt(substring2);
                    if (parseInt < 4 || parseInt > 31) {
                        throw new IllegalArgumentException("Invalid cost factor: " + parseInt + ", 4 < cost < 31 expected.");
                    }
                    return Strings.constantTimeAreEqual(str, doGenerate(substring, bArr, decodeSaltString(str.substring(str.lastIndexOf(36) + 1, length - 31)), parseInt));
                } catch (NumberFormatException unused) {
                    throw new IllegalArgumentException("Invalid cost factor: " + substring2);
                }
            }
            throw new IllegalArgumentException("not a Bcrypt string");
        }
        throw new IllegalArgumentException("Missing bcryptString.");
    }

    private static String doGenerate(String str, byte[] bArr, byte[] bArr2, int r7) {
        if (!allowedVersions.contains(str)) {
            throw new IllegalArgumentException("Version " + str + " is not accepted by this implementation.");
        } else if (bArr2 != null) {
            if (bArr2.length != 16) {
                throw new DataLengthException("16 byte salt required: " + bArr2.length);
            } else if (r7 < 4 || r7 > 31) {
                throw new IllegalArgumentException("Invalid cost factor.");
            } else {
                int length = bArr.length < 72 ? bArr.length + 1 : 72;
                byte[] bArr3 = new byte[length];
                if (length > bArr.length) {
                    length = bArr.length;
                }
                System.arraycopy(bArr, 0, bArr3, 0, length);
                Arrays.fill(bArr, (byte) 0);
                String createBcryptString = createBcryptString(str, bArr3, bArr2, r7);
                Arrays.fill(bArr3, (byte) 0);
                return createBcryptString;
            }
        } else {
            throw new IllegalArgumentException("Salt required.");
        }
    }

    private static void encodeData(StringBuilder sb, byte[] bArr) {
        boolean z;
        if (bArr.length != 24 && bArr.length != 16) {
            throw new DataLengthException("Invalid length: " + bArr.length + ", 24 for key or 16 for salt expected");
        }
        if (bArr.length == 16) {
            byte[] bArr2 = new byte[18];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            bArr = bArr2;
            z = true;
        } else {
            bArr[bArr.length - 1] = 0;
            z = false;
        }
        int length = bArr.length;
        for (int r3 = 0; r3 < length; r3 += 3) {
            int r4 = bArr[r3] & 255;
            int r5 = bArr[r3 + 1] & 255;
            int r6 = bArr[r3 + 2] & 255;
            byte[] bArr3 = encodingTable;
            sb.append((char) bArr3[(r4 >>> 2) & 63]);
            sb.append((char) bArr3[((r4 << 4) | (r5 >>> 4)) & 63]);
            sb.append((char) bArr3[((r5 << 2) | (r6 >>> 6)) & 63]);
            sb.append((char) bArr3[r6 & 63]);
        }
        int length2 = sb.length();
        sb.setLength(z ? length2 - 2 : length2 - 1);
    }

    public static String generate(String str, byte[] bArr, byte[] bArr2, int r3) {
        if (bArr != null) {
            return doGenerate(str, Arrays.clone(bArr), bArr2, r3);
        }
        throw new IllegalArgumentException("Password required.");
    }

    public static String generate(String str, char[] cArr, byte[] bArr, int r3) {
        if (cArr != null) {
            return doGenerate(str, Strings.toUTF8ByteArray(cArr), bArr, r3);
        }
        throw new IllegalArgumentException("Password required.");
    }

    public static String generate(byte[] bArr, byte[] bArr2, int r3) {
        return generate(defaultVersion, bArr, bArr2, r3);
    }

    public static String generate(char[] cArr, byte[] bArr, int r3) {
        return generate(defaultVersion, cArr, bArr, r3);
    }
}
