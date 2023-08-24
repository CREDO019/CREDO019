package com.facebook.soloader;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public final class NativeDeps {
    private static final float HASHMAP_LOAD_FACTOR = 1.0f;
    private static final int INITIAL_HASH = 5381;
    private static final int LIB_PREFIX_LEN = 3;
    private static final int LIB_SUFFIX_LEN = 3;
    private static final String LOG_TAG = "NativeDeps";
    private static final int WAITING_THREADS_WARNING_THRESHOLD = 3;
    @Nullable
    private static byte[] sEncodedDeps = null;
    private static volatile boolean sInitialized = false;
    private static Map<Integer, List<Integer>> sPrecomputedDeps = null;
    private static List<Integer> sPrecomputedLibs = null;
    private static volatile boolean sUseDepsFileAsync = false;
    private static final int LIB_PREFIX_SUFFIX_LEN = 3 + 3;
    private static final ReentrantReadWriteLock sWaitForDepsFileLock = new ReentrantReadWriteLock();

    public static String[] getDependencies(String str, File file) throws IOException {
        String[] awaitGetDepsFromPrecomputedDeps = awaitGetDepsFromPrecomputedDeps(str);
        return awaitGetDepsFromPrecomputedDeps != null ? awaitGetDepsFromPrecomputedDeps : MinElf.extract_DT_NEEDED(file);
    }

    public static String[] getDependencies(String str, ElfByteChannel elfByteChannel) throws IOException {
        String[] awaitGetDepsFromPrecomputedDeps = awaitGetDepsFromPrecomputedDeps(str);
        return awaitGetDepsFromPrecomputedDeps != null ? awaitGetDepsFromPrecomputedDeps : MinElf.extract_DT_NEEDED(elfByteChannel);
    }

    @Nullable
    private static String[] awaitGetDepsFromPrecomputedDeps(String str) {
        if (sInitialized) {
            return tryGetDepsFromPrecomputedDeps(str);
        }
        if (sUseDepsFileAsync) {
            ReentrantReadWriteLock reentrantReadWriteLock = sWaitForDepsFileLock;
            reentrantReadWriteLock.readLock().lock();
            try {
                String[] tryGetDepsFromPrecomputedDeps = tryGetDepsFromPrecomputedDeps(str);
                reentrantReadWriteLock.readLock().unlock();
                return tryGetDepsFromPrecomputedDeps;
            } catch (Throwable th) {
                sWaitForDepsFileLock.readLock().unlock();
                throw th;
            }
        }
        return null;
    }

    public static boolean useDepsFile(final Context context, boolean z, final boolean z2) {
        if (!z) {
            return useDepsFileFromApkSync(context, z2);
        }
        new Thread(new Runnable() { // from class: com.facebook.soloader.NativeDeps.1
            @Override // java.lang.Runnable
            public void run() {
                NativeDeps.sWaitForDepsFileLock.writeLock().lock();
                boolean unused = NativeDeps.sUseDepsFileAsync = true;
                try {
                    NativeDeps.useDepsFileFromApkSync(context, z2);
                } finally {
                    int readLockCount = NativeDeps.sWaitForDepsFileLock.getReadLockCount();
                    if (readLockCount >= 3) {
                        Log.w(NativeDeps.LOG_TAG, "NativeDeps initialization finished with " + Integer.toString(readLockCount) + " threads waiting.");
                    }
                    NativeDeps.sWaitForDepsFileLock.writeLock().unlock();
                    boolean unused2 = NativeDeps.sUseDepsFileAsync = false;
                }
            }
        }, "soloader-nativedeps-init").start();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean useDepsFileFromApkSync(Context context, boolean z) {
        boolean z2;
        try {
            z2 = initDeps(context, z);
        } catch (IOException unused) {
            z2 = false;
        }
        if (!z2 && z) {
            try {
                NativeDepsUnpacker.ensureNativeDepsAvailable(context);
                z2 = initDeps(context, z);
            } catch (IOException unused2) {
            }
        }
        if (!z2) {
            Log.w(LOG_TAG, "Failed to extract native deps from APK, falling back to using MinElf to get library dependencies.");
        }
        return z2;
    }

    private static boolean initDeps(Context context, boolean z) throws IOException {
        byte[] readNativeDepsFromApk;
        byte[] bArr;
        verifyUninitialized();
        if (z) {
            bArr = SysUtil.makeApkDepBlock(new File(context.getApplicationInfo().sourceDir), context);
            readNativeDepsFromApk = NativeDepsUnpacker.readNativeDepsFromDisk(context);
        } else {
            readNativeDepsFromApk = NativeDepsUnpacker.readNativeDepsFromApk(context);
            bArr = null;
        }
        return processDepsBytes(bArr, readNativeDepsFromApk);
    }

    private static void indexLib(int r2, int r3) {
        sPrecomputedLibs.add(Integer.valueOf(r3));
        List<Integer> list = sPrecomputedDeps.get(Integer.valueOf(r2));
        if (list == null) {
            list = new ArrayList<>();
            sPrecomputedDeps.put(Integer.valueOf(r2), list);
        }
        list.add(Integer.valueOf(r3));
    }

    private static void indexDepsBytes(byte[] bArr, int r9) {
        int r4;
        byte b;
        boolean z = true;
        int r3 = 0;
        int r42 = 0;
        while (true) {
            if (z) {
                r3 = INITIAL_HASH;
                r4 = r9;
                while (true) {
                    try {
                        b = bArr[r4];
                        if (b <= 32) {
                            break;
                        }
                        r3 = (r3 << 5) + r3 + b;
                        r4++;
                    } catch (IndexOutOfBoundsException unused) {
                        if (z || r9 == bArr.length) {
                            return;
                        }
                        indexLib(r3, r9);
                        return;
                    }
                }
                indexLib(r3, r9);
                z = b != 32;
            } else {
                while (bArr[r9] != 10) {
                    try {
                        r9++;
                    } catch (IndexOutOfBoundsException unused2) {
                        r9 = r42;
                        if (z) {
                            return;
                        }
                        return;
                    }
                }
                z = true;
                int r7 = r42;
                r4 = r9;
                r9 = r7;
            }
            int r43 = r4 + 1;
            r42 = r9;
            r9 = r43;
        }
    }

    private static int verifyBytesAndGetOffset(@Nullable byte[] bArr, @Nullable byte[] bArr2) {
        if (bArr == null || bArr.length == 0 || bArr2.length < bArr.length + 4) {
            return -1;
        }
        if (bArr2.length != bArr.length + 4 + ByteBuffer.wrap(bArr2, bArr.length, 4).getInt()) {
            return -1;
        }
        for (int r1 = 0; r1 < bArr.length; r1++) {
            if (bArr[r1] != bArr2[r1]) {
                return -1;
            }
        }
        return bArr.length + 4;
    }

    private static int findNextLine(byte[] bArr, int r3) {
        while (r3 < bArr.length && bArr[r3] != 10) {
            r3++;
        }
        return r3 < bArr.length ? r3 + 1 : r3;
    }

    private static int parseLibCount(byte[] bArr, int r2, int r3) {
        try {
            return Integer.parseInt(new String(bArr, r2, r3));
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    static boolean processDepsBytes(byte[] bArr, byte[] bArr2) throws IOException {
        int r6;
        int parseLibCount;
        if (bArr != null) {
            r6 = verifyBytesAndGetOffset(bArr, bArr2);
            if (r6 == -1) {
                return false;
            }
        } else {
            r6 = 0;
        }
        int findNextLine = findNextLine(bArr2, r6);
        if (findNextLine < bArr2.length && (parseLibCount = parseLibCount(bArr2, r6, (findNextLine - r6) - 1)) > 0) {
            sPrecomputedDeps = new HashMap(((int) (parseLibCount / 1.0f)) + 1, 1.0f);
            sPrecomputedLibs = new ArrayList(parseLibCount);
            indexDepsBytes(bArr2, findNextLine);
            if (sPrecomputedLibs.size() != parseLibCount) {
                return false;
            }
            sEncodedDeps = bArr2;
            sInitialized = true;
            return true;
        }
        return false;
    }

    private static boolean libIsAtOffset(String str, int r5) {
        int r2;
        int r0 = LIB_PREFIX_LEN;
        while (true) {
            int length = str.length();
            r2 = LIB_SUFFIX_LEN;
            if (r0 >= length - r2 || r5 >= sEncodedDeps.length || (str.codePointAt(r0) & 255) != sEncodedDeps[r5]) {
                break;
            }
            r0++;
            r5++;
        }
        return r0 == str.length() - r2;
    }

    private static int hashLib(String str) {
        int r1 = INITIAL_HASH;
        for (int r0 = LIB_PREFIX_LEN; r0 < str.length() - LIB_SUFFIX_LEN; r0++) {
            r1 = str.codePointAt(r0) + (r1 << 5) + r1;
        }
        return r1;
    }

    private static int getOffsetForLib(String str) {
        List<Integer> list = sPrecomputedDeps.get(Integer.valueOf(hashLib(str)));
        if (list == null) {
            return -1;
        }
        for (Integer num : list) {
            int intValue = num.intValue();
            if (libIsAtOffset(str, intValue)) {
                return intValue;
            }
        }
        return -1;
    }

    @Nullable
    private static String getLibString(int r7) {
        if (r7 >= sPrecomputedLibs.size()) {
            return null;
        }
        int intValue = sPrecomputedLibs.get(r7).intValue();
        int r0 = intValue;
        while (true) {
            byte[] bArr = sEncodedDeps;
            if (r0 >= bArr.length || bArr[r0] <= 32) {
                break;
            }
            r0++;
        }
        int r02 = (r0 - intValue) + LIB_PREFIX_SUFFIX_LEN;
        char[] cArr = new char[r02];
        cArr[0] = 'l';
        cArr[1] = 'i';
        cArr[2] = 'b';
        for (int r3 = 0; r3 < r02 - LIB_PREFIX_SUFFIX_LEN; r3++) {
            cArr[LIB_PREFIX_LEN + r3] = (char) sEncodedDeps[intValue + r3];
        }
        cArr[r02 - 3] = '.';
        cArr[r02 - 2] = 's';
        cArr[r02 - 1] = 'o';
        return new String(cArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x003d, code lost:
        return null;
     */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String[] getDepsForLibAtOffset(int r6, int r7) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r6 = r6 + r7
            int r7 = com.facebook.soloader.NativeDeps.LIB_PREFIX_SUFFIX_LEN
            int r6 = r6 - r7
            r7 = 0
            r1 = 0
            r2 = 0
        Lc:
            byte[] r3 = com.facebook.soloader.NativeDeps.sEncodedDeps
            int r4 = r3.length
            r5 = 0
            if (r6 >= r4) goto L3e
            r3 = r3[r6]
            r4 = 10
            if (r3 == r4) goto L3e
            r4 = 32
            if (r3 != r4) goto L2b
            if (r2 == 0) goto L3a
            java.lang.String r1 = getLibString(r1)
            if (r1 != 0) goto L25
            return r5
        L25:
            r0.add(r1)
            r1 = 0
            r2 = 0
            goto L3a
        L2b:
            r2 = 48
            if (r3 < r2) goto L3d
            r2 = 57
            if (r3 <= r2) goto L34
            goto L3d
        L34:
            int r1 = r1 * 10
            int r3 = r3 + (-48)
            int r1 = r1 + r3
            r2 = 1
        L3a:
            int r6 = r6 + 1
            goto Lc
        L3d:
            return r5
        L3e:
            if (r2 == 0) goto L4a
            java.lang.String r6 = getLibString(r1)
            if (r6 != 0) goto L47
            return r5
        L47:
            r0.add(r6)
        L4a:
            int r6 = r0.size()
            if (r6 != 0) goto L51
            return r5
        L51:
            int r6 = r0.size()
            java.lang.String[] r6 = new java.lang.String[r6]
            java.lang.Object[] r6 = r0.toArray(r6)
            java.lang.String[] r6 = (java.lang.String[]) r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.NativeDeps.getDepsForLibAtOffset(int, int):java.lang.String[]");
    }

    @Nullable
    static String[] tryGetDepsFromPrecomputedDeps(String str) {
        int offsetForLib;
        if (sInitialized && str.length() > LIB_PREFIX_SUFFIX_LEN && (offsetForLib = getOffsetForLib(str)) != -1) {
            return getDepsForLibAtOffset(offsetForLib, str.length());
        }
        return null;
    }

    private static void verifyUninitialized() {
        if (sInitialized) {
            synchronized (NativeDeps.class) {
                if (sInitialized) {
                    throw new IllegalStateException("Trying to initialize NativeDeps but it was already initialized");
                }
            }
        }
    }
}
