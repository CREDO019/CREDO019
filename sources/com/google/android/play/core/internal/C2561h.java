package com.google.android.play.core.internal;

import android.util.Pair;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* renamed from: com.google.android.play.core.internal.h */
/* loaded from: classes3.dex */
public final class C2561h {
    /* renamed from: a */
    private static int m704a(int r3) {
        if (r3 != 513) {
            if (r3 != 514) {
                if (r3 != 769) {
                    switch (r3) {
                        case 257:
                        case 259:
                            return 1;
                        case 258:
                        case 260:
                            return 2;
                        default:
                            String valueOf = String.valueOf(Long.toHexString(r3));
                            throw new IllegalArgumentException(valueOf.length() != 0 ? "Unknown signature algorithm: 0x".concat(valueOf) : new String("Unknown signature algorithm: 0x"));
                    }
                }
                return 1;
            }
            return 2;
        }
        return 1;
    }

    /* renamed from: a */
    public static long m697a(ByteBuffer byteBuffer) {
        m686c(byteBuffer);
        return m696a(byteBuffer, byteBuffer.position() + 16);
    }

    /* renamed from: a */
    private static long m696a(ByteBuffer byteBuffer, int r3) {
        return byteBuffer.getInt(r3) & BodyPartID.bodyIdMax;
    }

    /* renamed from: a */
    static Pair<ByteBuffer, Long> m702a(RandomAccessFile randomAccessFile) throws IOException {
        if (randomAccessFile.length() < 22) {
            return null;
        }
        Pair<ByteBuffer, Long> m701a = m701a(randomAccessFile, 0);
        return m701a != null ? m701a : m701a(randomAccessFile, 65535);
    }

    /* renamed from: a */
    private static Pair<ByteBuffer, Long> m701a(RandomAccessFile randomAccessFile, int r10) throws IOException {
        int r6;
        long length = randomAccessFile.length();
        if (length < 22) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(((int) Math.min(r10, (-22) + length)) + 22);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        long capacity = length - allocate.capacity();
        randomAccessFile.seek(capacity);
        randomAccessFile.readFully(allocate.array(), allocate.arrayOffset(), allocate.capacity());
        m686c(allocate);
        int capacity2 = allocate.capacity();
        if (capacity2 >= 22) {
            int r9 = capacity2 - 22;
            int min = Math.min(r9, 65535);
            for (int r5 = 0; r5 < min; r5++) {
                r6 = r9 - r5;
                if (allocate.getInt(r6) == 101010256 && ((char) allocate.getShort(r6 + 20)) == r5) {
                    break;
                }
            }
        }
        r6 = -1;
        if (r6 == -1) {
            return null;
        }
        allocate.position(r6);
        ByteBuffer slice = allocate.slice();
        slice.order(ByteOrder.LITTLE_ENDIAN);
        return Pair.create(slice, Long.valueOf(capacity + r6));
    }

    /* renamed from: a */
    public static String m699a(String str, String str2) {
        StringBuilder sb = new StringBuilder(str.length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(ParameterizedMessage.ERROR_MSG_SEPARATOR);
        sb.append(str2);
        return sb.toString();
    }

    /* renamed from: a */
    public static String m698a(String str, String str2, String str3) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 2 + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append(str);
        sb.append(ParameterizedMessage.ERROR_MSG_SEPARATOR);
        sb.append(str2);
        sb.append(ParameterizedMessage.ERROR_MSG_SEPARATOR);
        sb.append(str3);
        return sb.toString();
    }

    /* renamed from: a */
    private static void m703a(int r2, byte[] bArr) {
        bArr[1] = (byte) (r2 & 255);
        bArr[2] = (byte) ((r2 >>> 8) & 255);
        bArr[3] = (byte) ((r2 >>> 16) & 255);
        bArr[4] = (byte) (r2 >> 24);
    }

    /* renamed from: a */
    public static void m695a(ByteBuffer byteBuffer, long j) {
        m686c(byteBuffer);
        int position = byteBuffer.position() + 16;
        if (j >= 0 && j <= BodyPartID.bodyIdMax) {
            byteBuffer.putInt(byteBuffer.position() + position, (int) j);
            return;
        }
        StringBuilder sb = new StringBuilder(47);
        sb.append("uint32 value of out range: ");
        sb.append(j);
        throw new IllegalArgumentException(sb.toString());
    }

    /* renamed from: a */
    private static void m692a(Map<Integer, byte[]> map, FileChannel fileChannel, long j, long j2, long j3, ByteBuffer byteBuffer) throws SecurityException {
        if (map.isEmpty()) {
            throw new SecurityException("No digests provided");
        }
        C2542c c2542c = new C2542c(fileChannel, 0L, j);
        C2542c c2542c2 = new C2542c(fileChannel, j2, j3 - j2);
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.order(ByteOrder.LITTLE_ENDIAN);
        m695a(duplicate, j);
        C2488a c2488a = new C2488a(duplicate);
        int size = map.size();
        int[] r4 = new int[size];
        int r7 = 0;
        for (Integer num : map.keySet()) {
            r4[r7] = num.intValue();
            r7++;
        }
        try {
            byte[][] m691a = m691a(r4, new InterfaceC2515b[]{c2542c, c2542c2, c2488a});
            for (int r6 = 0; r6 < size; r6++) {
                int r1 = r4[r6];
                if (!MessageDigest.isEqual(map.get(Integer.valueOf(r1)), m691a[r6])) {
                    throw new SecurityException(m690b(r1).concat(" digest of contents did not verify"));
                }
            }
        } catch (DigestException e) {
            throw new SecurityException("Failed to compute digest(s) of contents", e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0048, code lost:
        r11 = m704a(r5);
        r12 = m704a(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0050, code lost:
        if (r11 == 1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0052, code lost:
        if (r12 == 1) goto L19;
     */
    /* JADX WARN: Removed duplicated region for block: B:119:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x014b A[Catch: SignatureException -> 0x026f, InvalidAlgorithmParameterException -> 0x0271, InvalidKeyException -> 0x0273, InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException -> 0x0275, NoSuchAlgorithmException -> 0x0277, TryCatch #5 {InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException -> 0x0275, blocks: (B:68:0x0135, B:70:0x014b, B:71:0x014e), top: B:138:0x0135 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0157  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.security.cert.X509Certificate[] m694a(java.nio.ByteBuffer r22, java.util.Map<java.lang.Integer, byte[]> r23, java.security.cert.CertificateFactory r24) throws java.lang.SecurityException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 706
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.internal.C2561h.m694a(java.nio.ByteBuffer, java.util.Map, java.security.cert.CertificateFactory):java.security.cert.X509Certificate[]");
    }

    /* renamed from: a */
    private static byte[][] m691a(int[] r26, InterfaceC2515b[] interfaceC2515bArr) throws DigestException {
        long j;
        int r9;
        int length;
        long j2 = 0;
        long j3 = 0;
        int r4 = 0;
        while (true) {
            j = 1048576;
            if (r4 >= 3) {
                break;
            }
            j3 += (interfaceC2515bArr[r4].mo722a() + 1048575) / 1048576;
            r4++;
        }
        if (j3 >= 2097151) {
            StringBuilder sb = new StringBuilder(37);
            sb.append("Too many chunks: ");
            sb.append(j3);
            throw new DigestException(sb.toString());
        }
        int r42 = (int) j3;
        byte[][] bArr = new byte[r26.length];
        int r6 = 0;
        while (true) {
            length = r26.length;
            if (r6 >= length) {
                break;
            }
            byte[] bArr2 = new byte[(m687c(r26[r6]) * r42) + 5];
            bArr2[0] = 90;
            m703a(r42, bArr2);
            bArr[r6] = bArr2;
            r6++;
        }
        byte[] bArr3 = new byte[5];
        bArr3[0] = -91;
        MessageDigest[] messageDigestArr = new MessageDigest[length];
        for (int r12 = 0; r12 < r26.length; r12++) {
            String m690b = m690b(r26[r12]);
            try {
                messageDigestArr[r12] = MessageDigest.getInstance(m690b);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(m690b.concat(" digest not supported"), e);
            }
        }
        int r122 = 0;
        int r13 = 0;
        int r15 = 0;
        for (r9 = 3; r122 < r9; r9 = 3) {
            InterfaceC2515b interfaceC2515b = interfaceC2515bArr[r122];
            long j4 = j2;
            int r17 = r122;
            long mo722a = interfaceC2515b.mo722a();
            while (mo722a > j2) {
                int min = (int) Math.min(mo722a, j);
                m703a(min, bArr3);
                for (int r1 = 0; r1 < length; r1++) {
                    messageDigestArr[r1].update(bArr3);
                }
                long j5 = j4;
                try {
                    interfaceC2515b.mo721a(messageDigestArr, j5, min);
                    int r14 = 0;
                    while (r14 < r26.length) {
                        int r92 = r26[r14];
                        InterfaceC2515b interfaceC2515b2 = interfaceC2515b;
                        byte[] bArr4 = bArr[r14];
                        int m687c = m687c(r92);
                        byte[] bArr5 = bArr3;
                        MessageDigest messageDigest = messageDigestArr[r14];
                        MessageDigest[] messageDigestArr2 = messageDigestArr;
                        int digest = messageDigest.digest(bArr4, (r13 * m687c) + 5, m687c);
                        if (digest != m687c) {
                            String algorithm = messageDigest.getAlgorithm();
                            StringBuilder sb2 = new StringBuilder(String.valueOf(algorithm).length() + 46);
                            sb2.append("Unexpected output size of ");
                            sb2.append(algorithm);
                            sb2.append(" digest: ");
                            sb2.append(digest);
                            throw new RuntimeException(sb2.toString());
                        }
                        r14++;
                        interfaceC2515b = interfaceC2515b2;
                        bArr3 = bArr5;
                        messageDigestArr = messageDigestArr2;
                    }
                    InterfaceC2515b interfaceC2515b3 = interfaceC2515b;
                    long j6 = min;
                    long j7 = j5 + j6;
                    mo722a -= j6;
                    r13++;
                    j2 = 0;
                    j = 1048576;
                    interfaceC2515b = interfaceC2515b3;
                    j4 = j7;
                    bArr3 = bArr3;
                } catch (IOException e2) {
                    StringBuilder sb3 = new StringBuilder(59);
                    sb3.append("Failed to digest chunk #");
                    sb3.append(r13);
                    sb3.append(" of section #");
                    sb3.append(r15);
                    throw new DigestException(sb3.toString(), e2);
                }
            }
            r15++;
            r122 = r17 + 1;
            j2 = 0;
            j = 1048576;
        }
        byte[][] bArr6 = new byte[r26.length];
        for (int r3 = 0; r3 < r26.length; r3++) {
            int r2 = r26[r3];
            byte[] bArr7 = bArr[r3];
            String m690b2 = m690b(r2);
            try {
                bArr6[r3] = MessageDigest.getInstance(m690b2).digest(bArr7);
            } catch (NoSuchAlgorithmException e3) {
                throw new RuntimeException(m690b2.concat(" digest not supported"), e3);
            }
        }
        return bArr6;
    }

    /* renamed from: a */
    public static X509Certificate[][] m700a(String str) throws C2558e, SecurityException, IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
        try {
            Pair<ByteBuffer, Long> m702a = m702a(randomAccessFile);
            if (m702a == null) {
                long length = randomAccessFile.length();
                StringBuilder sb = new StringBuilder(102);
                sb.append("Not an APK file: ZIP End of Central Directory record not found in file with ");
                sb.append(length);
                sb.append(" bytes");
                throw new C2558e(sb.toString());
            }
            ByteBuffer byteBuffer = (ByteBuffer) m702a.first;
            long longValue = ((Long) m702a.second).longValue();
            long j = (-20) + longValue;
            if (j >= 0) {
                randomAccessFile.seek(j);
                if (randomAccessFile.readInt() == 1347094023) {
                    throw new C2558e("ZIP64 APK not supported");
                }
            }
            long m697a = m697a(byteBuffer);
            if (m697a >= longValue) {
                StringBuilder sb2 = new StringBuilder(122);
                sb2.append("ZIP Central Directory offset out of range: ");
                sb2.append(m697a);
                sb2.append(". ZIP End of Central Directory offset: ");
                sb2.append(longValue);
                throw new C2558e(sb2.toString());
            } else if (m689b(byteBuffer) + m697a == longValue) {
                if (m697a < 32) {
                    StringBuilder sb3 = new StringBuilder(87);
                    sb3.append("APK too small for APK Signing Block. ZIP Central Directory offset: ");
                    sb3.append(m697a);
                    throw new C2558e(sb3.toString());
                }
                ByteBuffer allocate = ByteBuffer.allocate(24);
                allocate.order(ByteOrder.LITTLE_ENDIAN);
                randomAccessFile.seek(m697a - allocate.capacity());
                randomAccessFile.readFully(allocate.array(), allocate.arrayOffset(), allocate.capacity());
                int r2 = 8;
                if (allocate.getLong(8) == 2334950737559900225L && allocate.getLong(16) == 3617552046287187010L) {
                    int r3 = 0;
                    long j2 = allocate.getLong(0);
                    if (j2 < allocate.capacity() || j2 > 2147483639) {
                        StringBuilder sb4 = new StringBuilder(57);
                        sb4.append("APK Signing Block size out of range: ");
                        sb4.append(j2);
                        throw new C2558e(sb4.toString());
                    }
                    int r0 = (int) (8 + j2);
                    long j3 = m697a - r0;
                    if (j3 < 0) {
                        StringBuilder sb5 = new StringBuilder(59);
                        sb5.append("APK Signing Block offset out of range: ");
                        sb5.append(j3);
                        throw new C2558e(sb5.toString());
                    }
                    ByteBuffer allocate2 = ByteBuffer.allocate(r0);
                    allocate2.order(ByteOrder.LITTLE_ENDIAN);
                    randomAccessFile.seek(j3);
                    randomAccessFile.readFully(allocate2.array(), allocate2.arrayOffset(), allocate2.capacity());
                    long j4 = allocate2.getLong(0);
                    if (j4 != j2) {
                        StringBuilder sb6 = new StringBuilder(103);
                        sb6.append("APK Signing Block sizes in header and footer do not match: ");
                        sb6.append(j4);
                        sb6.append(" vs ");
                        sb6.append(j2);
                        throw new C2558e(sb6.toString());
                    }
                    Pair create = Pair.create(allocate2, Long.valueOf(j3));
                    ByteBuffer byteBuffer2 = (ByteBuffer) create.first;
                    long longValue2 = ((Long) create.second).longValue();
                    if (byteBuffer2.order() == ByteOrder.LITTLE_ENDIAN) {
                        int capacity = byteBuffer2.capacity() - 24;
                        if (capacity < 8) {
                            StringBuilder sb7 = new StringBuilder(38);
                            sb7.append("end < start: ");
                            sb7.append(capacity);
                            sb7.append(" < ");
                            sb7.append(8);
                            throw new IllegalArgumentException(sb7.toString());
                        }
                        int capacity2 = byteBuffer2.capacity();
                        if (capacity > byteBuffer2.capacity()) {
                            StringBuilder sb8 = new StringBuilder(41);
                            sb8.append("end > capacity: ");
                            sb8.append(capacity);
                            sb8.append(" > ");
                            sb8.append(capacity2);
                            throw new IllegalArgumentException(sb8.toString());
                        }
                        int limit = byteBuffer2.limit();
                        int position = byteBuffer2.position();
                        byteBuffer2.position(0);
                        byteBuffer2.limit(capacity);
                        byteBuffer2.position(8);
                        ByteBuffer slice = byteBuffer2.slice();
                        slice.order(byteBuffer2.order());
                        byteBuffer2.position(0);
                        byteBuffer2.limit(limit);
                        byteBuffer2.position(position);
                        while (slice.hasRemaining()) {
                            r3++;
                            if (slice.remaining() < r2) {
                                StringBuilder sb9 = new StringBuilder(70);
                                sb9.append("Insufficient data to read size of APK Signing Block entry #");
                                sb9.append(r3);
                                throw new C2558e(sb9.toString());
                            }
                            long j5 = slice.getLong();
                            if (j5 < 4 || j5 > 2147483647L) {
                                StringBuilder sb10 = new StringBuilder(76);
                                sb10.append("APK Signing Block entry #");
                                sb10.append(r3);
                                sb10.append(" size out of range: ");
                                sb10.append(j5);
                                throw new C2558e(sb10.toString());
                            }
                            int r13 = (int) j5;
                            int position2 = slice.position() + r13;
                            if (r13 > slice.remaining()) {
                                int remaining = slice.remaining();
                                StringBuilder sb11 = new StringBuilder(91);
                                sb11.append("APK Signing Block entry #");
                                sb11.append(r3);
                                sb11.append(" size out of range: ");
                                sb11.append(r13);
                                sb11.append(", available: ");
                                sb11.append(remaining);
                                throw new C2558e(sb11.toString());
                            } else if (slice.getInt() == 1896449818) {
                                X509Certificate[][] m693a = m693a(randomAccessFile.getChannel(), new C2557d(m688b(slice, r13 - 4), longValue2, m697a, longValue, byteBuffer));
                                randomAccessFile.close();
                                return m693a;
                            } else {
                                slice.position(position2);
                                r2 = 8;
                            }
                        }
                        throw new C2558e("No APK Signature Scheme v2 block in APK Signing Block");
                    }
                    throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
                }
                throw new C2558e("No APK Signing Block before ZIP Central Directory");
            } else {
                throw new C2558e("ZIP Central Directory is not immediately followed by End of Central Directory");
            }
        } finally {
            try {
                randomAccessFile.close();
            } catch (IOException unused) {
            }
        }
    }

    /* renamed from: a */
    private static X509Certificate[][] m693a(FileChannel fileChannel, C2557d c2557d) throws SecurityException {
        ByteBuffer byteBuffer;
        long j;
        long j2;
        long j3;
        ByteBuffer byteBuffer2;
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            try {
                byteBuffer = c2557d.f858a;
                ByteBuffer m685d = m685d(byteBuffer);
                int r3 = 0;
                while (m685d.hasRemaining()) {
                    r3++;
                    try {
                        arrayList.add(m694a(m685d(m685d), hashMap, certificateFactory));
                    } catch (IOException | SecurityException | BufferUnderflowException e) {
                        StringBuilder sb = new StringBuilder(48);
                        sb.append("Failed to parse/verify signer #");
                        sb.append(r3);
                        sb.append(" block");
                        throw new SecurityException(sb.toString(), e);
                    }
                }
                if (r3 > 0) {
                    if (hashMap.isEmpty()) {
                        throw new SecurityException("No content digests found");
                    }
                    j = c2557d.f859b;
                    j2 = c2557d.f860c;
                    j3 = c2557d.f861d;
                    byteBuffer2 = c2557d.f862e;
                    m692a(hashMap, fileChannel, j, j2, j3, byteBuffer2);
                    return (X509Certificate[][]) arrayList.toArray(new X509Certificate[arrayList.size()]);
                }
                throw new SecurityException("No signers found");
            } catch (IOException e2) {
                throw new SecurityException("Failed to read list of signers", e2);
            }
        } catch (CertificateException e3) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e3);
        }
    }

    /* renamed from: b */
    public static long m689b(ByteBuffer byteBuffer) {
        m686c(byteBuffer);
        return m696a(byteBuffer, byteBuffer.position() + 12);
    }

    /* renamed from: b */
    private static String m690b(int r3) {
        if (r3 != 1) {
            if (r3 == 2) {
                return "SHA-512";
            }
            StringBuilder sb = new StringBuilder(44);
            sb.append("Unknown content digest algorthm: ");
            sb.append(r3);
            throw new IllegalArgumentException(sb.toString());
        }
        return "SHA-256";
    }

    /* renamed from: b */
    private static ByteBuffer m688b(ByteBuffer byteBuffer, int r4) throws BufferUnderflowException {
        if (r4 < 0) {
            StringBuilder sb = new StringBuilder(17);
            sb.append("size: ");
            sb.append(r4);
            throw new IllegalArgumentException(sb.toString());
        }
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        int r42 = r4 + position;
        if (r42 < position || r42 > limit) {
            throw new BufferUnderflowException();
        }
        byteBuffer.limit(r42);
        try {
            ByteBuffer slice = byteBuffer.slice();
            slice.order(byteBuffer.order());
            byteBuffer.position(r42);
            return slice;
        } finally {
            byteBuffer.limit(limit);
        }
    }

    /* renamed from: c */
    private static int m687c(int r3) {
        if (r3 != 1) {
            if (r3 == 2) {
                return 64;
            }
            StringBuilder sb = new StringBuilder(44);
            sb.append("Unknown content digest algorthm: ");
            sb.append(r3);
            throw new IllegalArgumentException(sb.toString());
        }
        return 32;
    }

    /* renamed from: c */
    private static void m686c(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    /* renamed from: d */
    private static ByteBuffer m685d(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() < 4) {
            int remaining = byteBuffer.remaining();
            StringBuilder sb = new StringBuilder(93);
            sb.append("Remaining buffer too short to contain length of length-prefixed field. Remaining: ");
            sb.append(remaining);
            throw new IOException(sb.toString());
        }
        int r0 = byteBuffer.getInt();
        if (r0 >= 0) {
            if (r0 <= byteBuffer.remaining()) {
                return m688b(byteBuffer, r0);
            }
            int remaining2 = byteBuffer.remaining();
            StringBuilder sb2 = new StringBuilder(101);
            sb2.append("Length-prefixed field longer than remaining buffer. Field length: ");
            sb2.append(r0);
            sb2.append(", remaining: ");
            sb2.append(remaining2);
            throw new IOException(sb2.toString());
        }
        throw new IllegalArgumentException("Negative length");
    }

    /* renamed from: e */
    private static byte[] m684e(ByteBuffer byteBuffer) throws IOException {
        int r0 = byteBuffer.getInt();
        if (r0 >= 0) {
            if (r0 <= byteBuffer.remaining()) {
                byte[] bArr = new byte[r0];
                byteBuffer.get(bArr);
                return bArr;
            }
            int remaining = byteBuffer.remaining();
            StringBuilder sb = new StringBuilder(90);
            sb.append("Underflow while reading length-prefixed value. Length: ");
            sb.append(r0);
            sb.append(", available: ");
            sb.append(remaining);
            throw new IOException(sb.toString());
        }
        throw new IOException("Negative length");
    }
}
