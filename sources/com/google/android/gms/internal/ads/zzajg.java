package com.google.android.gms.internal.ads;

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

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzajg {
    public static X509Certificate[][] zza(String str) throws zzajd, SecurityException, IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
        try {
            Pair zzc = zzajh.zzc(randomAccessFile);
            if (zzc != null) {
                ByteBuffer byteBuffer = (ByteBuffer) zzc.first;
                long longValue = ((Long) zzc.second).longValue();
                long j = (-20) + longValue;
                if (j >= 0) {
                    randomAccessFile.seek(j);
                    if (randomAccessFile.readInt() == 1347094023) {
                        throw new zzajd("ZIP64 APK not supported");
                    }
                }
                long zza = zzajh.zza(byteBuffer);
                if (zza >= longValue) {
                    throw new zzajd("ZIP Central Directory offset out of range: " + zza + ". ZIP End of Central Directory offset: " + longValue);
                } else if (zzajh.zzb(byteBuffer) + zza == longValue) {
                    if (zza < 32) {
                        throw new zzajd("APK too small for APK Signing Block. ZIP Central Directory offset: " + zza);
                    }
                    ByteBuffer allocate = ByteBuffer.allocate(24);
                    allocate.order(ByteOrder.LITTLE_ENDIAN);
                    randomAccessFile.seek(zza - allocate.capacity());
                    randomAccessFile.readFully(allocate.array(), allocate.arrayOffset(), allocate.capacity());
                    int r2 = 8;
                    if (allocate.getLong(8) != 2334950737559900225L || allocate.getLong(16) != 3617552046287187010L) {
                        throw new zzajd("No APK Signing Block before ZIP Central Directory");
                    }
                    int r3 = 0;
                    long j2 = allocate.getLong(0);
                    if (j2 < allocate.capacity() || j2 > 2147483639) {
                        throw new zzajd("APK Signing Block size out of range: " + j2);
                    }
                    int r0 = (int) (8 + j2);
                    long j3 = zza - r0;
                    if (j3 < 0) {
                        throw new zzajd("APK Signing Block offset out of range: " + j3);
                    }
                    ByteBuffer allocate2 = ByteBuffer.allocate(r0);
                    allocate2.order(ByteOrder.LITTLE_ENDIAN);
                    randomAccessFile.seek(j3);
                    randomAccessFile.readFully(allocate2.array(), allocate2.arrayOffset(), allocate2.capacity());
                    long j4 = allocate2.getLong(0);
                    if (j4 != j2) {
                        throw new zzajd("APK Signing Block sizes in header and footer do not match: " + j4 + " vs " + j2);
                    }
                    Pair create = Pair.create(allocate2, Long.valueOf(j3));
                    ByteBuffer byteBuffer2 = (ByteBuffer) create.first;
                    long longValue2 = ((Long) create.second).longValue();
                    if (byteBuffer2.order() == ByteOrder.LITTLE_ENDIAN) {
                        int capacity = byteBuffer2.capacity() - 24;
                        if (capacity < 8) {
                            throw new IllegalArgumentException("end < start: " + capacity + " < 8");
                        }
                        int capacity2 = byteBuffer2.capacity();
                        if (capacity > byteBuffer2.capacity()) {
                            throw new IllegalArgumentException("end > capacity: " + capacity + " > " + capacity2);
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
                            if (slice.remaining() >= r2) {
                                long j5 = slice.getLong();
                                if (j5 >= 4 && j5 <= 2147483647L) {
                                    int r13 = (int) j5;
                                    int position2 = slice.position() + r13;
                                    if (r13 <= slice.remaining()) {
                                        if (slice.getInt() != 1896449818) {
                                            slice.position(position2);
                                            r2 = 8;
                                        } else {
                                            X509Certificate[][] zzl = zzl(randomAccessFile.getChannel(), new zzajc(zze(slice, r13 - 4), longValue2, zza, longValue, byteBuffer, null));
                                            randomAccessFile.close();
                                            return zzl;
                                        }
                                    } else {
                                        throw new zzajd("APK Signing Block entry #" + r3 + " size out of range: " + r13 + ", available: " + slice.remaining());
                                    }
                                } else {
                                    throw new zzajd("APK Signing Block entry #" + r3 + " size out of range: " + j5);
                                }
                            } else {
                                throw new zzajd("Insufficient data to read size of APK Signing Block entry #" + r3);
                            }
                        }
                        throw new zzajd("No APK Signature Scheme v2 block in APK Signing Block");
                    }
                    throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
                } else {
                    throw new zzajd("ZIP Central Directory is not immediately followed by End of Central Directory");
                }
            }
            throw new zzajd("Not an APK file: ZIP End of Central Directory record not found in file with " + randomAccessFile.length() + " bytes");
        } finally {
            try {
                randomAccessFile.close();
            } catch (IOException unused) {
            }
        }
    }

    private static int zzb(int r3) {
        if (r3 != 1) {
            if (r3 == 2) {
                return 64;
            }
            throw new IllegalArgumentException("Unknown content digest algorthm: " + r3);
        }
        return 32;
    }

    private static int zzc(int r3) {
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
                            throw new IllegalArgumentException("Unknown signature algorithm: 0x".concat(String.valueOf(Long.toHexString(r3))));
                    }
                }
                return 1;
            }
            return 2;
        }
        return 1;
    }

    private static String zzd(int r3) {
        if (r3 != 1) {
            if (r3 == 2) {
                return "SHA-512";
            }
            throw new IllegalArgumentException("Unknown content digest algorthm: " + r3);
        }
        return "SHA-256";
    }

    private static ByteBuffer zze(ByteBuffer byteBuffer, int r4) throws BufferUnderflowException {
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

    private static ByteBuffer zzf(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() < 4) {
            throw new IOException("Remaining buffer too short to contain length of length-prefixed field. Remaining: " + byteBuffer.remaining());
        }
        int r0 = byteBuffer.getInt();
        if (r0 < 0) {
            throw new IllegalArgumentException("Negative length");
        }
        if (r0 > byteBuffer.remaining()) {
            throw new IOException("Length-prefixed field longer than remaining buffer. Field length: " + r0 + ", remaining: " + byteBuffer.remaining());
        }
        return zze(byteBuffer, r0);
    }

    private static void zzg(int r1, byte[] bArr, int r3) {
        bArr[1] = (byte) (r1 & 255);
        bArr[2] = (byte) ((r1 >>> 8) & 255);
        bArr[3] = (byte) ((r1 >>> 16) & 255);
        bArr[4] = (byte) (r1 >> 24);
    }

    private static void zzh(Map map, FileChannel fileChannel, long j, long j2, long j3, ByteBuffer byteBuffer) throws SecurityException {
        if (!map.isEmpty()) {
            zzajb zzajbVar = new zzajb(fileChannel, 0L, j);
            zzajb zzajbVar2 = new zzajb(fileChannel, j2, j3 - j2);
            ByteBuffer duplicate = byteBuffer.duplicate();
            duplicate.order(ByteOrder.LITTLE_ENDIAN);
            zzajh.zzd(duplicate, j);
            zzaiz zzaizVar = new zzaiz(duplicate);
            int size = map.size();
            int[] r4 = new int[size];
            int r7 = 0;
            for (Integer num : map.keySet()) {
                r4[r7] = num.intValue();
                r7++;
            }
            try {
                byte[][] zzk = zzk(r4, new zzaja[]{zzajbVar, zzajbVar2, zzaizVar});
                for (int r6 = 0; r6 < size; r6++) {
                    int r1 = r4[r6];
                    if (!MessageDigest.isEqual((byte[]) map.get(Integer.valueOf(r1)), zzk[r6])) {
                        throw new SecurityException(zzd(r1).concat(" digest of contents did not verify"));
                    }
                }
                return;
            } catch (DigestException e) {
                throw new SecurityException("Failed to compute digest(s) of contents", e);
            }
        }
        throw new SecurityException("No digests provided");
    }

    private static byte[] zzi(ByteBuffer byteBuffer) throws IOException {
        int r0 = byteBuffer.getInt();
        if (r0 < 0) {
            throw new IOException("Negative length");
        }
        if (r0 > byteBuffer.remaining()) {
            throw new IOException("Underflow while reading length-prefixed value. Length: " + r0 + ", available: " + byteBuffer.remaining());
        }
        byte[] bArr = new byte[r0];
        byteBuffer.get(bArr);
        return bArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0048, code lost:
        r11 = zzc(r5);
        r12 = zzc(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0050, code lost:
        if (r11 == 1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0052, code lost:
        if (r12 == 1) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.security.cert.X509Certificate[] zzj(java.nio.ByteBuffer r22, java.util.Map r23, java.security.cert.CertificateFactory r24) throws java.lang.SecurityException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 686
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzajg.zzj(java.nio.ByteBuffer, java.util.Map, java.security.cert.CertificateFactory):java.security.cert.X509Certificate[]");
    }

    private static byte[][] zzk(int[] r25, zzaja[] zzajaVarArr) throws DigestException {
        long j;
        int r9;
        int length;
        MessageDigest messageDigest;
        long j2 = 0;
        int r3 = 0;
        long j3 = 0;
        int r4 = 0;
        while (true) {
            j = 1048576;
            if (r4 >= 3) {
                break;
            }
            j3 += (zzajaVarArr[r4].zza() + 1048575) / 1048576;
            r4++;
        }
        if (j3 >= 2097151) {
            throw new DigestException("Too many chunks: " + j3);
        }
        int r42 = (int) j3;
        byte[][] bArr = new byte[r25.length];
        int r6 = 0;
        while (true) {
            length = r25.length;
            if (r6 >= length) {
                break;
            }
            byte[] bArr2 = new byte[(zzb(r25[r6]) * r42) + 5];
            bArr2[0] = 90;
            zzg(r42, bArr2, 1);
            bArr[r6] = bArr2;
            r6++;
        }
        byte[] bArr3 = new byte[5];
        bArr3[0] = -91;
        MessageDigest[] messageDigestArr = new MessageDigest[length];
        for (int r13 = 0; r13 < r25.length; r13++) {
            String zzd = zzd(r25[r13]);
            try {
                messageDigestArr[r13] = MessageDigest.getInstance(zzd);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(zzd.concat(" digest not supported"), e);
            }
        }
        int r132 = 0;
        int r14 = 0;
        for (r9 = 3; r132 < r9; r9 = 3) {
            zzaja zzajaVar = zzajaVarArr[r132];
            long j4 = j2;
            long zza = zzajaVar.zza();
            while (zza > j2) {
                int min = (int) Math.min(zza, j);
                zzg(min, bArr3, 1);
                for (int r1 = 0; r1 < length; r1++) {
                    messageDigestArr[r1].update(bArr3);
                }
                long j5 = j4;
                try {
                    zzajaVar.zzb(messageDigestArr, j5, min);
                    byte[] bArr4 = bArr3;
                    int r12 = 0;
                    while (r12 < r25.length) {
                        int r43 = r25[r12];
                        zzaja zzajaVar2 = zzajaVar;
                        byte[] bArr5 = bArr[r12];
                        int zzb = zzb(r43);
                        int r22 = length;
                        MessageDigest[] messageDigestArr2 = messageDigestArr;
                        int digest = messageDigestArr[r12].digest(bArr5, (r14 * zzb) + 5, zzb);
                        if (digest != zzb) {
                            throw new RuntimeException("Unexpected output size of " + messageDigest.getAlgorithm() + " digest: " + digest);
                        }
                        r12++;
                        zzajaVar = zzajaVar2;
                        length = r22;
                        messageDigestArr = messageDigestArr2;
                    }
                    MessageDigest[] messageDigestArr3 = messageDigestArr;
                    long j6 = min;
                    long j7 = j5 + j6;
                    zza -= j6;
                    r14++;
                    bArr3 = bArr4;
                    j2 = 0;
                    j4 = j7;
                    messageDigestArr = messageDigestArr3;
                    j = 1048576;
                } catch (IOException e2) {
                    throw new DigestException("Failed to digest chunk #" + r14 + " of section #" + r3, e2);
                }
            }
            r3++;
            r132++;
            j2 = 0;
            j = 1048576;
        }
        byte[][] bArr6 = new byte[r25.length];
        for (int r32 = 0; r32 < r25.length; r32++) {
            int r2 = r25[r32];
            byte[] bArr7 = bArr[r32];
            String zzd2 = zzd(r2);
            try {
                bArr6[r32] = MessageDigest.getInstance(zzd2).digest(bArr7);
            } catch (NoSuchAlgorithmException e3) {
                throw new RuntimeException(zzd2.concat(" digest not supported"), e3);
            }
        }
        return bArr6;
    }

    private static X509Certificate[][] zzl(FileChannel fileChannel, zzajc zzajcVar) throws SecurityException {
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
                byteBuffer = zzajcVar.zza;
                ByteBuffer zzf = zzf(byteBuffer);
                int r3 = 0;
                while (zzf.hasRemaining()) {
                    r3++;
                    try {
                        arrayList.add(zzj(zzf(zzf), hashMap, certificateFactory));
                    } catch (IOException | SecurityException | BufferUnderflowException e) {
                        throw new SecurityException("Failed to parse/verify signer #" + r3 + " block", e);
                    }
                }
                if (r3 <= 0) {
                    throw new SecurityException("No signers found");
                }
                if (!hashMap.isEmpty()) {
                    j = zzajcVar.zzb;
                    j2 = zzajcVar.zzc;
                    j3 = zzajcVar.zzd;
                    byteBuffer2 = zzajcVar.zze;
                    zzh(hashMap, fileChannel, j, j2, j3, byteBuffer2);
                    return (X509Certificate[][]) arrayList.toArray(new X509Certificate[arrayList.size()]);
                }
                throw new SecurityException("No content digests found");
            } catch (IOException e2) {
                throw new SecurityException("Failed to read list of signers", e2);
            }
        } catch (CertificateException e3) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e3);
        }
    }
}
