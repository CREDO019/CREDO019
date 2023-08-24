package org.bouncycastle.pqc.crypto.xmss;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

/* loaded from: classes3.dex */
public class XMSSUtil {

    /* loaded from: classes3.dex */
    private static class CheckingStream extends ObjectInputStream {
        private static final Set components;
        private boolean found;
        private final Class mainClass;

        static {
            HashSet hashSet = new HashSet();
            components = hashSet;
            hashSet.add("java.util.TreeMap");
            hashSet.add("java.lang.Integer");
            hashSet.add("java.lang.Number");
            hashSet.add("org.bouncycastle.pqc.crypto.xmss.BDS");
            hashSet.add("java.util.ArrayList");
            hashSet.add("org.bouncycastle.pqc.crypto.xmss.XMSSNode");
            hashSet.add("[B");
            hashSet.add("java.util.LinkedList");
            hashSet.add("java.util.Stack");
            hashSet.add("java.util.Vector");
            hashSet.add("[Ljava.lang.Object;");
            hashSet.add("org.bouncycastle.pqc.crypto.xmss.BDSTreeHash");
        }

        CheckingStream(Class cls, InputStream inputStream) throws IOException {
            super(inputStream);
            this.found = false;
            this.mainClass = cls;
        }

        @Override // java.io.ObjectInputStream
        protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
            if (this.found) {
                if (!components.contains(objectStreamClass.getName())) {
                    throw new InvalidClassException("unexpected class: ", objectStreamClass.getName());
                }
            } else if (!objectStreamClass.getName().equals(this.mainClass.getName())) {
                throw new InvalidClassException("unexpected class: ", objectStreamClass.getName());
            } else {
                this.found = true;
            }
            return super.resolveClass(objectStreamClass);
        }
    }

    public static boolean areEqual(byte[][] bArr, byte[][] bArr2) {
        if (hasNullPointer(bArr) || hasNullPointer(bArr2)) {
            throw new NullPointerException("a or b == null");
        }
        for (int r1 = 0; r1 < bArr.length; r1++) {
            if (!Arrays.areEqual(bArr[r1], bArr2[r1])) {
                return false;
            }
        }
        return true;
    }

    public static long bytesToXBigEndian(byte[] bArr, int r6, int r7) {
        Objects.requireNonNull(bArr, "in == null");
        long j = 0;
        for (int r2 = r6; r2 < r6 + r7; r2++) {
            j = (j << 8) | (bArr[r2] & 255);
        }
        return j;
    }

    public static int calculateTau(int r3, int r4) {
        for (int r1 = 0; r1 < r4; r1++) {
            if (((r3 >> r1) & 1) == 0) {
                return r1;
            }
        }
        return 0;
    }

    public static byte[] cloneArray(byte[] bArr) {
        Objects.requireNonNull(bArr, "in == null");
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public static byte[][] cloneArray(byte[][] bArr) {
        if (hasNullPointer(bArr)) {
            throw new NullPointerException("in has null pointers");
        }
        byte[][] bArr2 = new byte[bArr.length];
        for (int r2 = 0; r2 < bArr.length; r2++) {
            bArr2[r2] = new byte[bArr[r2].length];
            System.arraycopy(bArr[r2], 0, bArr2[r2], 0, bArr[r2].length);
        }
        return bArr2;
    }

    public static void copyBytesAtOffset(byte[] bArr, byte[] bArr2, int r5) {
        Objects.requireNonNull(bArr, "dst == null");
        Objects.requireNonNull(bArr2, "src == null");
        if (r5 < 0) {
            throw new IllegalArgumentException("offset hast to be >= 0");
        }
        if (bArr2.length + r5 > bArr.length) {
            throw new IllegalArgumentException("src length + offset must not be greater than size of destination");
        }
        for (int r0 = 0; r0 < bArr2.length; r0++) {
            bArr[r5 + r0] = bArr2[r0];
        }
    }

    public static Object deserialize(byte[] bArr, Class cls) throws IOException, ClassNotFoundException {
        CheckingStream checkingStream = new CheckingStream(cls, new ByteArrayInputStream(bArr));
        Object readObject = checkingStream.readObject();
        if (checkingStream.available() == 0) {
            if (cls.isInstance(readObject)) {
                return readObject;
            }
            throw new IOException("unexpected class found in ObjectInputStream");
        }
        throw new IOException("unexpected data found at end of ObjectInputStream");
    }

    public static void dumpByteArray(byte[][] bArr) {
        if (hasNullPointer(bArr)) {
            throw new NullPointerException("x has null pointers");
        }
        for (byte[] bArr2 : bArr) {
            System.out.println(Hex.toHexString(bArr2));
        }
    }

    public static byte[] extractBytesAtOffset(byte[] bArr, int r4, int r5) {
        Objects.requireNonNull(bArr, "src == null");
        if (r4 >= 0) {
            if (r5 >= 0) {
                if (r4 + r5 <= bArr.length) {
                    byte[] bArr2 = new byte[r5];
                    for (int r1 = 0; r1 < r5; r1++) {
                        bArr2[r1] = bArr[r4 + r1];
                    }
                    return bArr2;
                }
                throw new IllegalArgumentException("offset + length must not be greater then size of source array");
            }
            throw new IllegalArgumentException("length hast to be >= 0");
        }
        throw new IllegalArgumentException("offset hast to be >= 0");
    }

    public static int getDigestSize(Digest digest) {
        Objects.requireNonNull(digest, "digest == null");
        String algorithmName = digest.getAlgorithmName();
        if (algorithmName.equals("SHAKE128")) {
            return 32;
        }
        if (algorithmName.equals("SHAKE256")) {
            return 64;
        }
        return digest.getDigestSize();
    }

    public static int getLeafIndex(long j, int r6) {
        return (int) (j & ((1 << r6) - 1));
    }

    public static long getTreeIndex(long j, int r2) {
        return j >> r2;
    }

    public static boolean hasNullPointer(byte[][] bArr) {
        if (bArr == null) {
            return true;
        }
        for (byte[] bArr2 : bArr) {
            if (bArr2 == null) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIndexValid(int r3, long j) {
        if (j >= 0) {
            return j < (1 << r3);
        }
        throw new IllegalStateException("index must not be negative");
    }

    public static boolean isNewAuthenticationPathNeeded(long j, int r8, int r9) {
        return j != 0 && (j + 1) % ((long) Math.pow((double) (1 << r8), (double) r9)) == 0;
    }

    public static boolean isNewBDSInitNeeded(long j, int r8, int r9) {
        return j != 0 && j % ((long) Math.pow((double) (1 << r8), (double) (r9 + 1))) == 0;
    }

    public static int log2(int r1) {
        int r0 = 0;
        while (true) {
            r1 >>= 1;
            if (r1 == 0) {
                return r0;
            }
            r0++;
        }
    }

    public static void longToBigEndian(long j, byte[] bArr, int r9) {
        Objects.requireNonNull(bArr, "in == null");
        if (bArr.length - r9 < 8) {
            throw new IllegalArgumentException("not enough space in array");
        }
        bArr[r9] = (byte) ((j >> 56) & 255);
        bArr[r9 + 1] = (byte) ((j >> 48) & 255);
        bArr[r9 + 2] = (byte) ((j >> 40) & 255);
        bArr[r9 + 3] = (byte) ((j >> 32) & 255);
        bArr[r9 + 4] = (byte) ((j >> 24) & 255);
        bArr[r9 + 5] = (byte) ((j >> 16) & 255);
        bArr[r9 + 6] = (byte) ((j >> 8) & 255);
        bArr[r9 + 7] = (byte) (j & 255);
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] toBytesBigEndian(long j, int r4) {
        byte[] bArr = new byte[r4];
        for (int r42 = r4 - 1; r42 >= 0; r42--) {
            bArr[r42] = (byte) j;
            j >>>= 8;
        }
        return bArr;
    }
}
