package com.bumptech.glide.util;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Looper;
import com.bumptech.glide.load.model.Model;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

/* loaded from: classes.dex */
public final class Util {
    private static final int HASH_ACCUMULATOR = 17;
    private static final int HASH_MULTIPLIER = 31;
    private static final char[] HEX_CHAR_ARRAY = "0123456789abcdef".toCharArray();
    private static final char[] SHA_256_CHARS = new char[64];

    public static int hashCode(int r0, int r1) {
        return (r1 * 31) + r0;
    }

    private static boolean isValidDimension(int r1) {
        return r1 > 0 || r1 == Integer.MIN_VALUE;
    }

    private Util() {
    }

    public static String sha256BytesToHex(byte[] bArr) {
        String bytesToHex;
        char[] cArr = SHA_256_CHARS;
        synchronized (cArr) {
            bytesToHex = bytesToHex(bArr, cArr);
        }
        return bytesToHex;
    }

    private static String bytesToHex(byte[] bArr, char[] cArr) {
        for (int r0 = 0; r0 < bArr.length; r0++) {
            int r1 = bArr[r0] & 255;
            int r2 = r0 * 2;
            char[] cArr2 = HEX_CHAR_ARRAY;
            cArr[r2] = cArr2[r1 >>> 4];
            cArr[r2 + 1] = cArr2[r1 & 15];
        }
        return new String(cArr);
    }

    @Deprecated
    public static int getSize(Bitmap bitmap) {
        return getBitmapByteSize(bitmap);
    }

    public static int getBitmapByteSize(Bitmap bitmap) {
        if (bitmap.isRecycled()) {
            throw new IllegalStateException("Cannot obtain size for recycled Bitmap: " + bitmap + "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig());
        }
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                return bitmap.getAllocationByteCount();
            } catch (NullPointerException unused) {
            }
        }
        return bitmap.getHeight() * bitmap.getRowBytes();
    }

    public static int getBitmapByteSize(int r0, int r1, Bitmap.Config config) {
        return r0 * r1 * getBytesPerPixel(config);
    }

    private static int getBytesPerPixel(Bitmap.Config config) {
        if (config == null) {
            config = Bitmap.Config.ARGB_8888;
        }
        int r3 = C11441.$SwitchMap$android$graphics$Bitmap$Config[config.ordinal()];
        if (r3 != 1) {
            if (r3 == 2 || r3 == 3) {
                return 2;
            }
            return r3 != 4 ? 4 : 8;
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bumptech.glide.util.Util$1 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C11441 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config;

        static {
            int[] r0 = new int[Bitmap.Config.values().length];
            $SwitchMap$android$graphics$Bitmap$Config = r0;
            try {
                r0[Bitmap.Config.ALPHA_8.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGBA_F16.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_8888.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static boolean isValidDimensions(int r0, int r1) {
        return isValidDimension(r0) && isValidDimension(r1);
    }

    public static void assertMainThread() {
        if (!isOnMainThread()) {
            throw new IllegalArgumentException("You must call this method on the main thread");
        }
    }

    public static void assertBackgroundThread() {
        if (!isOnBackgroundThread()) {
            throw new IllegalArgumentException("You must call this method on a background thread");
        }
    }

    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean isOnBackgroundThread() {
        return !isOnMainThread();
    }

    public static <T> Queue<T> createQueue(int r1) {
        return new ArrayDeque(r1);
    }

    public static <T> List<T> getSnapshot(Collection<T> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (T t : collection) {
            if (t != null) {
                arrayList.add(t);
            }
        }
        return arrayList;
    }

    public static boolean bothNullOrEqual(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static boolean bothModelsNullEquivalentOrEquals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else if (obj instanceof Model) {
            return ((Model) obj).isEquivalentTo(obj2);
        } else {
            return obj.equals(obj2);
        }
    }

    public static int hashCode(int r1) {
        return hashCode(r1, 17);
    }

    public static int hashCode(float f) {
        return hashCode(f, 17);
    }

    public static int hashCode(float f, int r1) {
        return hashCode(Float.floatToIntBits(f), r1);
    }

    public static int hashCode(Object obj, int r1) {
        return hashCode(obj == null ? 0 : obj.hashCode(), r1);
    }

    public static int hashCode(boolean z, int r1) {
        return hashCode(z ? 1 : 0, r1);
    }

    public static int hashCode(boolean z) {
        return hashCode(z, 17);
    }
}
