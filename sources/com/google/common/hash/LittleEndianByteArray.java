package com.google.common.hash;

import com.google.common.primitives.Longs;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
final class LittleEndianByteArray {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final LittleEndianBytes byteArray;

    /* loaded from: classes3.dex */
    private enum JavaLittleEndianBytes implements LittleEndianBytes {
        INSTANCE { // from class: com.google.common.hash.LittleEndianByteArray.JavaLittleEndianBytes.1
            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public long getLongLittleEndian(byte[] bArr, int r11) {
                return Longs.fromBytes(bArr[r11 + 7], bArr[r11 + 6], bArr[r11 + 5], bArr[r11 + 4], bArr[r11 + 3], bArr[r11 + 2], bArr[r11 + 1], bArr[r11]);
            }

            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public void putLongLittleEndian(byte[] bArr, int r10, long j) {
                long j2 = 255;
                for (int r2 = 0; r2 < 8; r2++) {
                    bArr[r10 + r2] = (byte) ((j & j2) >> (r2 * 8));
                    j2 <<= 8;
                }
            }
        }
    }

    /* loaded from: classes3.dex */
    private interface LittleEndianBytes {
        long getLongLittleEndian(byte[] bArr, int r2);

        void putLongLittleEndian(byte[] bArr, int r2, long j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long load64(byte[] bArr, int r2) {
        return byteArray.getLongLittleEndian(bArr, r2);
    }

    static long load64Safely(byte[] bArr, int r8, int r9) {
        int min = Math.min(r9, 8);
        long j = 0;
        for (int r2 = 0; r2 < min; r2++) {
            j |= (bArr[r8 + r2] & 255) << (r2 * 8);
        }
        return j;
    }

    static void store64(byte[] bArr, int r2, long j) {
        byteArray.putLongLittleEndian(bArr, r2, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int load32(byte[] bArr, int r3) {
        return ((bArr[r3 + 3] & 255) << 24) | (bArr[r3] & 255) | ((bArr[r3 + 1] & 255) << 8) | ((bArr[r3 + 2] & 255) << 16);
    }

    static boolean usingUnsafe() {
        return byteArray instanceof UnsafeByteArray;
    }

    /* loaded from: classes3.dex */
    private enum UnsafeByteArray implements LittleEndianBytes {
        UNSAFE_LITTLE_ENDIAN { // from class: com.google.common.hash.LittleEndianByteArray.UnsafeByteArray.1
            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public long getLongLittleEndian(byte[] bArr, int r7) {
                return UnsafeByteArray.theUnsafe.getLong(bArr, r7 + UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET);
            }

            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public void putLongLittleEndian(byte[] bArr, int r8, long j) {
                UnsafeByteArray.theUnsafe.putLong(bArr, r8 + UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET, j);
            }
        },
        UNSAFE_BIG_ENDIAN { // from class: com.google.common.hash.LittleEndianByteArray.UnsafeByteArray.2
            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public long getLongLittleEndian(byte[] bArr, int r7) {
                return Long.reverseBytes(UnsafeByteArray.theUnsafe.getLong(bArr, r7 + UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET));
            }

            @Override // com.google.common.hash.LittleEndianByteArray.LittleEndianBytes
            public void putLongLittleEndian(byte[] bArr, int r8, long j) {
                UnsafeByteArray.theUnsafe.putLong(bArr, r8 + UnsafeByteArray.BYTE_ARRAY_BASE_OFFSET, Long.reverseBytes(j));
            }
        };
        
        private static final int BYTE_ARRAY_BASE_OFFSET;
        private static final Unsafe theUnsafe;

        static {
            Unsafe unsafe = getUnsafe();
            theUnsafe = unsafe;
            BYTE_ARRAY_BASE_OFFSET = unsafe.arrayBaseOffset(byte[].class);
            if (unsafe.arrayIndexScale(byte[].class) != 1) {
                throw new AssertionError();
            }
        }

        private static Unsafe getUnsafe() {
            try {
                try {
                    return Unsafe.getUnsafe();
                } catch (SecurityException unused) {
                    return (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() { // from class: com.google.common.hash.LittleEndianByteArray.UnsafeByteArray.3
                        @Override // java.security.PrivilegedExceptionAction
                        public Unsafe run() throws Exception {
                            Field[] declaredFields;
                            for (Field field : Unsafe.class.getDeclaredFields()) {
                                field.setAccessible(true);
                                Object obj = field.get(null);
                                if (Unsafe.class.isInstance(obj)) {
                                    return (Unsafe) Unsafe.class.cast(obj);
                                }
                            }
                            throw new NoSuchFieldError("the Unsafe");
                        }
                    });
                }
            } catch (PrivilegedActionException e) {
                throw new RuntimeException("Could not initialize intrinsics", e.getCause());
            }
        }
    }

    static {
        LittleEndianBytes littleEndianBytes = JavaLittleEndianBytes.INSTANCE;
        try {
            if ("amd64".equals(System.getProperty("os.arch"))) {
                if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
                    littleEndianBytes = UnsafeByteArray.UNSAFE_LITTLE_ENDIAN;
                } else {
                    littleEndianBytes = UnsafeByteArray.UNSAFE_BIG_ENDIAN;
                }
            }
        } catch (Throwable unused) {
        }
        byteArray = littleEndianBytes;
    }

    private LittleEndianByteArray() {
    }
}
