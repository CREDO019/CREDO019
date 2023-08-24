package com.google.android.play.core.internal;

import android.content.ComponentName;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/* renamed from: com.google.android.play.core.internal.bq */
/* loaded from: classes3.dex */
public final class C2532bq {
    /* renamed from: a */
    public static <T> C2531bp<T> m742a(Object obj, String str, Class<T> cls) {
        return new C2531bp<>(obj, m743a(obj, str), cls);
    }

    /* renamed from: a */
    public static <R, P0> R m748a(Class cls, Class<R> cls2, Class<P0> cls3, P0 p0) {
        try {
            return cls2.cast(m746a((Class<?>) cls, "isDexOptNeeded", (Class<?>[]) new Class[]{cls3}).invoke(null, p0));
        } catch (Exception e) {
            throw new C2533br(String.format("Failed to invoke static method %s on type %s", "isDexOptNeeded", cls), e);
        }
    }

    /* renamed from: a */
    public static <R, P0, P1> R m747a(Class cls, Class<R> cls2, Class<P0> cls3, P0 p0, Class<P1> cls4, P1 p1) {
        try {
            return cls2.cast(m746a((Class<?>) cls, "optimizedPathFor", (Class<?>[]) new Class[]{cls3, cls4}).invoke(null, p0, p1));
        } catch (Exception e) {
            throw new C2533br(String.format("Failed to invoke static method %s on type %s", "optimizedPathFor", cls), e);
        }
    }

    /* renamed from: a */
    public static <R, P0> R m741a(Object obj, String str, Class<R> cls, Class<P0> cls2, P0 p0) {
        try {
            return cls.cast(m739a(obj, str, cls2).invoke(obj, p0));
        } catch (Exception e) {
            throw new C2533br(String.format("Failed to invoke method %s on an object of type %s", str, obj.getClass()), e);
        }
    }

    /* renamed from: a */
    public static <R, P0, P1, P2> R m740a(Object obj, String str, Class<R> cls, Class<P0> cls2, P0 p0, Class<P1> cls3, P1 p1, Class<P2> cls4, P2 p2) {
        try {
            return cls.cast(m739a(obj, str, cls2, cls3, cls4).invoke(obj, p0, p1, p2));
        } catch (Exception e) {
            throw new C2533br(String.format("Failed to invoke method %s on an object of type %s", str, obj.getClass()), e);
        }
    }

    /* renamed from: a */
    private static Field m743a(Object obj, String str) {
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        throw new C2533br(String.format("Failed to find a field named %s on an object of instance %s", str, obj.getClass().getName()));
    }

    /* renamed from: a */
    private static Method m746a(Class<?> cls, String str, Class<?>... clsArr) {
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            try {
                Method declaredMethod = cls2.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
            }
        }
        throw new C2533br(String.format("Could not find a method named %s with parameters %s in type %s", str, Arrays.asList(clsArr), cls));
    }

    /* renamed from: a */
    private static Method m739a(Object obj, String str, Class<?>... clsArr) {
        return m746a(obj.getClass(), str, clsArr);
    }

    /* renamed from: a */
    public static void m750a(PackageManager packageManager, ComponentName componentName, int r12) {
        ComponentInfo componentInfo;
        int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
        if (componentEnabledSetting != 1) {
            if (componentEnabledSetting != 2) {
                String packageName = componentName.getPackageName();
                String className = componentName.getClassName();
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(packageName, r12 | 512);
                    ComponentInfo[][] componentInfoArr = {packageInfo.activities, packageInfo.services, packageInfo.providers};
                    int r122 = 0;
                    loop0: while (true) {
                        if (r122 >= 3) {
                            componentInfo = null;
                            break;
                        }
                        ComponentInfo[] componentInfoArr2 = componentInfoArr[r122];
                        if (componentInfoArr2 != null) {
                            int length = componentInfoArr2.length;
                            for (int r7 = 0; r7 < length; r7++) {
                                componentInfo = componentInfoArr2[r7];
                                if (componentInfo.name.equals(className)) {
                                    break loop0;
                                }
                            }
                            continue;
                        }
                        r122++;
                    }
                    if (componentInfo != null) {
                        if (componentInfo.isEnabled()) {
                            return;
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
        }
    }

    /* renamed from: a */
    public static void m749a(AbstractC2543ca abstractC2543ca, InputStream inputStream, OutputStream outputStream, long j) throws IOException {
        byte[] bArr = new byte[16384];
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream, 4096));
        int readInt = dataInputStream.readInt();
        if (readInt != -771763713) {
            String valueOf = String.valueOf(String.format("%x", Integer.valueOf(readInt)));
            throw new C2541bz(valueOf.length() != 0 ? "Unexpected magic=".concat(valueOf) : new String("Unexpected magic="));
        }
        int read = dataInputStream.read();
        if (read != 4) {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Unexpected version=");
            sb.append(read);
            throw new C2541bz(sb.toString());
        }
        long j2 = 0;
        while (true) {
            long j3 = j - j2;
            try {
                int read2 = dataInputStream.read();
                if (read2 == -1) {
                    throw new IOException("Patch file overrun");
                }
                if (read2 == 0) {
                    return;
                }
                switch (read2) {
                    case 247:
                        read2 = dataInputStream.readUnsignedShort();
                        m737a(bArr, dataInputStream, outputStream, read2, j3);
                        break;
                    case 248:
                        read2 = dataInputStream.readInt();
                        m737a(bArr, dataInputStream, outputStream, read2, j3);
                        break;
                    case 249:
                        long readUnsignedShort = dataInputStream.readUnsignedShort();
                        read2 = dataInputStream.read();
                        if (read2 == -1) {
                            throw new IOException("Unexpected end of patch");
                        }
                        m738a(bArr, abstractC2543ca, outputStream, readUnsignedShort, read2, j3);
                        break;
                    case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION /* 250 */:
                        read2 = dataInputStream.readUnsignedShort();
                        m738a(bArr, abstractC2543ca, outputStream, dataInputStream.readUnsignedShort(), read2, j3);
                        break;
                    case 251:
                        read2 = dataInputStream.readInt();
                        m738a(bArr, abstractC2543ca, outputStream, dataInputStream.readUnsignedShort(), read2, j3);
                        break;
                    case 252:
                        long readInt2 = dataInputStream.readInt();
                        read2 = dataInputStream.read();
                        if (read2 == -1) {
                            throw new IOException("Unexpected end of patch");
                        }
                        m738a(bArr, abstractC2543ca, outputStream, readInt2, read2, j3);
                        break;
                    case 253:
                        read2 = dataInputStream.readUnsignedShort();
                        m738a(bArr, abstractC2543ca, outputStream, dataInputStream.readInt(), read2, j3);
                        break;
                    case 254:
                        read2 = dataInputStream.readInt();
                        m738a(bArr, abstractC2543ca, outputStream, dataInputStream.readInt(), read2, j3);
                        break;
                    case 255:
                        long readLong = dataInputStream.readLong();
                        read2 = dataInputStream.readInt();
                        m738a(bArr, abstractC2543ca, outputStream, readLong, read2, j3);
                        break;
                    default:
                        m737a(bArr, dataInputStream, outputStream, read2, j3);
                        break;
                }
                j2 += read2;
            } finally {
                outputStream.flush();
            }
        }
    }

    /* renamed from: a */
    public static <T> void m745a(T t) {
        Objects.requireNonNull(t);
    }

    /* renamed from: a */
    public static <T> void m744a(T t, Class<T> cls) {
        if (t == null) {
            throw new IllegalStateException(String.valueOf(cls.getCanonicalName()).concat(" must be set"));
        }
    }

    /* renamed from: a */
    private static void m738a(byte[] bArr, AbstractC2543ca abstractC2543ca, OutputStream outputStream, long j, int r15, long j2) throws IOException {
        int r1 = r15;
        if (r1 < 0) {
            throw new IOException("copyLength negative");
        }
        if (j < 0) {
            throw new IOException("inputOffset negative");
        }
        long j3 = r1;
        if (j3 > j2) {
            throw new IOException("Output length overrun");
        }
        try {
            InputStream m720b = new C2544cb(abstractC2543ca, j, j3).m720b();
            while (r1 > 0) {
                int min = Math.min(r1, 16384);
                int r5 = 0;
                while (r5 < min) {
                    int read = m720b.read(bArr, r5, min - r5);
                    if (read == -1) {
                        throw new IOException("truncated input stream");
                    }
                    r5 += read;
                }
                outputStream.write(bArr, 0, min);
                r1 -= min;
            }
            if (m720b != null) {
                m720b.close();
            }
        } catch (EOFException e) {
            throw new IOException("patch underrun", e);
        }
    }

    /* renamed from: a */
    private static void m737a(byte[] bArr, DataInputStream dataInputStream, OutputStream outputStream, int r6, long j) throws IOException {
        if (r6 < 0) {
            throw new IOException("copyLength negative");
        }
        if (r6 > j) {
            throw new IOException("Output length overrun");
        }
        while (r6 > 0) {
            try {
                int min = Math.min(r6, 16384);
                dataInputStream.readFully(bArr, 0, min);
                outputStream.write(bArr, 0, min);
                r6 -= min;
            } catch (EOFException unused) {
                throw new IOException("patch underrun");
            }
        }
    }

    /* renamed from: b */
    public static <T> C2531bp m735b(Object obj, String str, Class<T> cls) {
        return new C2531bp(obj, m743a(obj, str), cls, null);
    }

    /* renamed from: b */
    public static <T> void m736b(T t) {
        Objects.requireNonNull(t, "Cannot return null from a non-@Nullable @Provides method");
    }
}
