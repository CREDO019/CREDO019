package org.apache.commons.codec.digest;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.Charsets;

/* loaded from: classes5.dex */
public class Sha2Crypt {
    private static final int ROUNDS_DEFAULT = 5000;
    private static final int ROUNDS_MAX = 999999999;
    private static final int ROUNDS_MIN = 1000;
    private static final String ROUNDS_PREFIX = "rounds=";
    private static final Pattern SALT_PATTERN = Pattern.compile("^\\$([56])\\$(rounds=(\\d+)\\$)?([\\.\\/a-zA-Z0-9]{1,16}).*");
    private static final int SHA256_BLOCKSIZE = 32;
    static final String SHA256_PREFIX = "$5$";
    private static final int SHA512_BLOCKSIZE = 64;
    static final String SHA512_PREFIX = "$6$";

    public static String sha256Crypt(byte[] bArr) {
        return sha256Crypt(bArr, null);
    }

    public static String sha256Crypt(byte[] bArr, String str) {
        if (str == null) {
            str = SHA256_PREFIX + B64.getRandomSalt(8);
        }
        return sha2Crypt(bArr, str, SHA256_PREFIX, 32, "SHA-256");
    }

    private static String sha2Crypt(byte[] bArr, String str, String str2, int r31, String str3) {
        int r5;
        boolean z;
        byte b;
        byte[] bArr2;
        int r10;
        int length = bArr.length;
        if (str == null) {
            throw new IllegalArgumentException("Salt must not be null");
        }
        Matcher matcher = SALT_PATTERN.matcher(str);
        if (matcher == null || !matcher.find()) {
            throw new IllegalArgumentException("Invalid salt value: " + str);
        }
        if (matcher.group(3) != null) {
            r5 = Math.max(1000, Math.min((int) ROUNDS_MAX, Integer.parseInt(matcher.group(3))));
            z = true;
        } else {
            r5 = 5000;
            z = false;
        }
        String group = matcher.group(4);
        byte[] bytes = group.getBytes(Charsets.UTF_8);
        int length2 = bytes.length;
        MessageDigest digest = DigestUtils.getDigest(str3);
        digest.update(bArr);
        digest.update(bytes);
        MessageDigest digest2 = DigestUtils.getDigest(str3);
        digest2.update(bArr);
        digest2.update(bytes);
        digest2.update(bArr);
        byte[] digest3 = digest2.digest();
        int length3 = bArr.length;
        while (length3 > r31) {
            digest.update(digest3, 0, r31);
            length3 -= r31;
        }
        digest.update(digest3, 0, length3);
        for (int length4 = bArr.length; length4 > 0; length4 >>= 1) {
            if ((length4 & 1) != 0) {
                digest.update(digest3, 0, r31);
            } else {
                digest.update(bArr);
            }
        }
        byte[] digest4 = digest.digest();
        MessageDigest digest5 = DigestUtils.getDigest(str3);
        for (int r15 = 1; r15 <= length; r15++) {
            digest5.update(bArr);
        }
        byte[] digest6 = digest5.digest();
        byte[] bArr3 = new byte[length];
        int r1 = 0;
        while (r1 < length - r31) {
            System.arraycopy(digest6, 0, bArr3, r1, r31);
            r1 += r31;
        }
        System.arraycopy(digest6, 0, bArr3, r1, length - r1);
        MessageDigest digest7 = DigestUtils.getDigest(str3);
        for (int r6 = 1; r6 <= (digest4[0] & 255) + 16; r6++) {
            digest7.update(bytes);
        }
        byte[] digest8 = digest7.digest();
        byte[] bArr4 = new byte[length2];
        int r9 = 0;
        while (r9 < length2 - r31) {
            System.arraycopy(digest8, 0, bArr4, r9, r31);
            r9 += r31;
        }
        System.arraycopy(digest8, 0, bArr4, r9, length2 - r9);
        MessageDigest messageDigest = digest;
        int r92 = 0;
        while (r92 <= r5 - 1) {
            messageDigest = DigestUtils.getDigest(str3);
            int r7 = r92 & 1;
            if (r7 != 0) {
                bArr2 = bytes;
                r10 = 0;
                messageDigest.update(bArr3, 0, length);
            } else {
                bArr2 = bytes;
                r10 = 0;
                messageDigest.update(digest4, 0, r31);
            }
            if (r92 % 3 != 0) {
                messageDigest.update(bArr4, r10, length2);
            }
            if (r92 % 7 != 0) {
                messageDigest.update(bArr3, r10, length);
            }
            if (r7 != 0) {
                messageDigest.update(digest4, r10, r31);
            } else {
                messageDigest.update(bArr3, r10, length);
            }
            digest4 = messageDigest.digest();
            r92++;
            bytes = bArr2;
        }
        byte[] bArr5 = bytes;
        StringBuilder sb = new StringBuilder(str2);
        if (z) {
            sb.append(ROUNDS_PREFIX);
            sb.append(r5);
            sb.append("$");
        }
        sb.append(group);
        sb.append("$");
        if (r31 == 32) {
            B64.b64from24bit(digest4[0], digest4[10], digest4[20], 4, sb);
            B64.b64from24bit(digest4[21], digest4[1], digest4[11], 4, sb);
            B64.b64from24bit(digest4[12], digest4[22], digest4[2], 4, sb);
            B64.b64from24bit(digest4[3], digest4[13], digest4[23], 4, sb);
            B64.b64from24bit(digest4[24], digest4[4], digest4[14], 4, sb);
            B64.b64from24bit(digest4[15], digest4[25], digest4[5], 4, sb);
            B64.b64from24bit(digest4[6], digest4[16], digest4[26], 4, sb);
            B64.b64from24bit(digest4[27], digest4[7], digest4[17], 4, sb);
            B64.b64from24bit(digest4[18], digest4[28], digest4[8], 4, sb);
            B64.b64from24bit(digest4[9], digest4[19], digest4[29], 4, sb);
            B64.b64from24bit((byte) 0, digest4[31], digest4[30], 3, sb);
            b = 0;
        } else {
            B64.b64from24bit(digest4[0], digest4[21], digest4[42], 4, sb);
            B64.b64from24bit(digest4[22], digest4[43], digest4[1], 4, sb);
            B64.b64from24bit(digest4[44], digest4[2], digest4[23], 4, sb);
            B64.b64from24bit(digest4[3], digest4[24], digest4[45], 4, sb);
            B64.b64from24bit(digest4[25], digest4[46], digest4[4], 4, sb);
            B64.b64from24bit(digest4[47], digest4[5], digest4[26], 4, sb);
            B64.b64from24bit(digest4[6], digest4[27], digest4[48], 4, sb);
            B64.b64from24bit(digest4[28], digest4[49], digest4[7], 4, sb);
            B64.b64from24bit(digest4[50], digest4[8], digest4[29], 4, sb);
            B64.b64from24bit(digest4[9], digest4[30], digest4[51], 4, sb);
            B64.b64from24bit(digest4[31], digest4[52], digest4[10], 4, sb);
            B64.b64from24bit(digest4[53], digest4[11], digest4[32], 4, sb);
            B64.b64from24bit(digest4[12], digest4[33], digest4[54], 4, sb);
            B64.b64from24bit(digest4[34], digest4[55], digest4[13], 4, sb);
            B64.b64from24bit(digest4[56], digest4[14], digest4[35], 4, sb);
            B64.b64from24bit(digest4[15], digest4[36], digest4[57], 4, sb);
            B64.b64from24bit(digest4[37], digest4[58], digest4[16], 4, sb);
            B64.b64from24bit(digest4[59], digest4[17], digest4[38], 4, sb);
            B64.b64from24bit(digest4[18], digest4[39], digest4[60], 4, sb);
            B64.b64from24bit(digest4[40], digest4[61], digest4[19], 4, sb);
            B64.b64from24bit(digest4[62], digest4[20], digest4[41], 4, sb);
            b = 0;
            B64.b64from24bit((byte) 0, (byte) 0, digest4[63], 2, sb);
        }
        Arrays.fill(digest8, b);
        Arrays.fill(bArr3, b);
        Arrays.fill(bArr4, b);
        messageDigest.reset();
        digest7.reset();
        Arrays.fill(bArr, b);
        Arrays.fill(bArr5, b);
        return sb.toString();
    }

    public static String sha512Crypt(byte[] bArr) {
        return sha512Crypt(bArr, null);
    }

    public static String sha512Crypt(byte[] bArr, String str) {
        if (str == null) {
            str = SHA512_PREFIX + B64.getRandomSalt(8);
        }
        return sha2Crypt(bArr, str, SHA512_PREFIX, 64, "SHA-512");
    }
}
