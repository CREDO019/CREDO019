package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.util.Base64;
import com.google.android.play.core.internal.C2510av;
import com.google.android.play.core.internal.C2551ci;
import com.google.android.play.core.splitcompat.C2608p;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipException;
import org.bouncycastle.asn1.cmc.BodyPartID;

/* renamed from: com.google.android.play.core.assetpacks.db */
/* loaded from: classes3.dex */
public final class C2436db {

    /* renamed from: a */
    private static InterfaceC2353a f668a;

    /* renamed from: a */
    static int m903a(byte[] bArr, int r3) {
        return (bArr[r3 + 3] & 255) | ((bArr[r3] & 255) << 24) | ((bArr[r3 + 1] & 255) << 16) | ((bArr[r3 + 2] & 255) << 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static AssetLocation m906a(String str, String str2) throws IOException {
        Long l;
        int m899c;
        C2510av.m773a(str != null, "Attempted to get file location from a null apk path.");
        C2510av.m773a(str2 != null, String.format("Attempted to get file location in apk %s with a null file path.", str));
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
        byte[] bArr = new byte[22];
        randomAccessFile.seek(randomAccessFile.length() - 22);
        randomAccessFile.readFully(bArr);
        C2390bj m904a = m903a(bArr, 0) == 1347093766 ? m904a(bArr) : null;
        byte b = 5;
        if (m904a == null) {
            long length = randomAccessFile.length() - 22;
            long j = (-65536) + length;
            if (j < 0) {
                j = 0;
            }
            int min = (int) Math.min(1024L, randomAccessFile.length());
            byte[] bArr2 = new byte[min];
            byte[] bArr3 = new byte[22];
            loop0: while (true) {
                long max = Math.max(3 + (length - min), j);
                randomAccessFile.seek(max);
                randomAccessFile.readFully(bArr2);
                for (int r15 = min - 4; r15 >= 0; r15 -= 4) {
                    byte b2 = bArr2[r15];
                    int r6 = b2 != b ? b2 != 6 ? b2 != 75 ? b2 != 80 ? -1 : 0 : 1 : 3 : 2;
                    if (r6 >= 0 && r15 >= r6 && m903a(bArr2, r15 - r6) == 1347093766) {
                        randomAccessFile.seek((max + r15) - r6);
                        randomAccessFile.readFully(bArr3);
                        m904a = m904a(bArr3);
                        break loop0;
                    }
                    b = 5;
                }
                if (max == j) {
                    throw new ZipException(String.format("End Of Central Directory signature not found in APK %s", str));
                }
                length = max;
            }
        }
        long j2 = m904a.f497a;
        byte[] bytes = str2.getBytes("UTF-8");
        byte[] bArr4 = new byte[46];
        byte[] bArr5 = new byte[str2.length()];
        int r9 = 0;
        while (true) {
            if (r9 >= m904a.f498b) {
                l = null;
                break;
            }
            randomAccessFile.seek(j2);
            randomAccessFile.readFully(bArr4);
            int m903a = m903a(bArr4, 0);
            if (m903a != 1347092738) {
                throw new ZipException(String.format("Missing central directory file header signature when looking for file %s in APK %s. Read %d entries out of %d. Found %d instead of the header signature %d.", str2, str, Integer.valueOf(r9), Integer.valueOf(m904a.f498b), Integer.valueOf(m903a), 1347092738));
            }
            randomAccessFile.seek(j2 + 28);
            if (m899c(bArr4, 28) == str2.length()) {
                randomAccessFile.seek(46 + j2);
                randomAccessFile.read(bArr5);
                if (Arrays.equals(bArr5, bytes)) {
                    l = Long.valueOf(m901b(bArr4, 42));
                    break;
                }
            }
            j2 += m899c + 46 + m899c(bArr4, 30) + m899c(bArr4, 32);
            r9++;
        }
        if (l == null) {
            return null;
        }
        long longValue = l.longValue();
        byte[] bArr6 = new byte[8];
        randomAccessFile.seek(22 + longValue);
        randomAccessFile.readFully(bArr6);
        return AssetLocation.m1044a(str, longValue + 30 + m899c(bArr6, 4) + m899c(bArr6, 6), m901b(bArr6, 0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static synchronized InterfaceC2353a m907a(Context context) {
        InterfaceC2353a interfaceC2353a;
        synchronized (C2436db.class) {
            if (f668a == null) {
                C2397bq c2397bq = new C2397bq(null);
                c2397bq.m957a(new C2469n(C2608p.m576a(context)));
                f668a = c2397bq.m958a();
            }
            interfaceC2353a = f668a;
        }
        return interfaceC2353a;
    }

    /* renamed from: a */
    private static C2390bj m904a(byte[] bArr) {
        int m899c = m899c(bArr, 10);
        m901b(bArr, 12);
        return new C2390bj(m901b(bArr, 16), m899c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m905a(List<File> list) throws NoSuchAlgorithmException, IOException {
        int read;
        MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
        byte[] bArr = new byte[8192];
        for (File file : list) {
            FileInputStream fileInputStream = new FileInputStream(file);
            do {
                try {
                    read = fileInputStream.read(bArr);
                    if (read > 0) {
                        messageDigest.update(bArr, 0, read);
                    }
                } catch (Throwable th) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        C2551ci.m714a(th, th2);
                    }
                    throw th;
                }
            } while (read != -1);
            fileInputStream.close();
        }
        return Base64.encodeToString(messageDigest.digest(), 11);
    }

    /* renamed from: a */
    public static boolean m909a(int r2) {
        return r2 == 1 || r2 == 7 || r2 == 2 || r2 == 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m908a(int r4, int r5) {
        if (r4 == 5) {
            if (r5 != 5) {
                return true;
            }
            r4 = 5;
        }
        if (r4 != 6 || r5 == 6 || r5 == 5) {
            if (r4 != 4 || r5 == 4) {
                if (r4 == 3 && (r5 == 2 || r5 == 7 || r5 == 1 || r5 == 8)) {
                    return true;
                }
                if (r4 == 2) {
                    return r5 == 1 || r5 == 8;
                }
                return false;
            }
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static long m901b(byte[] bArr, int r3) {
        return ((m899c(bArr, r3 + 2) << 16) | m899c(bArr, r3)) & BodyPartID.bodyIdMax;
    }

    /* renamed from: b */
    public static boolean m902b(int r1) {
        return r1 == 5 || r1 == 6 || r1 == 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static int m899c(byte[] bArr, int r2) {
        return ((bArr[r2 + 1] & 255) << 8) | (bArr[r2] & 255);
    }

    /* renamed from: c */
    public static boolean m900c(int r1) {
        return r1 == 2 || r1 == 7 || r1 == 3;
    }
}
