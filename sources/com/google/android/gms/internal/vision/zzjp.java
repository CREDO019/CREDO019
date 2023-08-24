package com.google.android.gms.internal.vision;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzjp {
    private static final Logger logger = Logger.getLogger(zzjp.class.getName());
    private static final boolean zzaap;
    private static final boolean zzaaq;
    private static final zzd zzaar;
    private static final boolean zzaas;
    private static final long zzaat;
    private static final long zzaau;
    private static final long zzaav;
    private static final long zzaaw;
    private static final long zzaax;
    private static final long zzaay;
    private static final long zzaaz;
    private static final long zzaba;
    private static final long zzabb;
    private static final long zzabc;
    private static final long zzabd;
    private static final long zzabe;
    private static final long zzabf;
    private static final long zzabg;
    private static final int zzabh;
    static final boolean zzabi;
    private static final Class<?> zzrm;
    private static final boolean zzsr;
    private static final Unsafe zzyt;

    private zzjp() {
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final byte zzy(Object obj, long j) {
            if (zzjp.zzabi) {
                return zzjp.zzq(obj, j);
            }
            return zzjp.zzr(obj, j);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final void zze(Object obj, long j, byte b) {
            if (zzjp.zzabi) {
                zzjp.zza(obj, j, b);
            } else {
                zzjp.zzb(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final boolean zzm(Object obj, long j) {
            if (zzjp.zzabi) {
                return zzjp.zzs(obj, j);
            }
            return zzjp.zzt(obj, j);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final void zza(Object obj, long j, boolean z) {
            if (zzjp.zzabi) {
                zzjp.zzb(obj, j, z);
            } else {
                zzjp.zzc(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final byte zzy(Object obj, long j) {
            return this.zzabl.getByte(obj, j);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final void zze(Object obj, long j, byte b) {
            this.zzabl.putByte(obj, j, b);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final boolean zzm(Object obj, long j) {
            return this.zzabl.getBoolean(obj, j);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final void zza(Object obj, long j, boolean z) {
            this.zzabl.putBoolean(obj, j, z);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final float zzn(Object obj, long j) {
            return this.zzabl.getFloat(obj, j);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final void zza(Object obj, long j, float f) {
            this.zzabl.putFloat(obj, j, f);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final double zzo(Object obj, long j) {
            return this.zzabl.getDouble(obj, j);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final void zza(Object obj, long j, double d) {
            this.zzabl.putDouble(obj, j, d);
        }
    }

    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final byte zzy(Object obj, long j) {
            if (zzjp.zzabi) {
                return zzjp.zzq(obj, j);
            }
            return zzjp.zzr(obj, j);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final void zze(Object obj, long j, byte b) {
            if (zzjp.zzabi) {
                zzjp.zza(obj, j, b);
            } else {
                zzjp.zzb(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final boolean zzm(Object obj, long j) {
            if (zzjp.zzabi) {
                return zzjp.zzs(obj, j);
            }
            return zzjp.zzt(obj, j);
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final void zza(Object obj, long j, boolean z) {
            if (zzjp.zzabi) {
                zzjp.zzb(obj, j, z);
            } else {
                zzjp.zzc(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        @Override // com.google.android.gms.internal.vision.zzjp.zzd
        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzij() {
        return zzsr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
    /* loaded from: classes3.dex */
    public static abstract class zzd {
        Unsafe zzabl;

        zzd(Unsafe unsafe) {
            this.zzabl = unsafe;
        }

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);

        public final int zzk(Object obj, long j) {
            return this.zzabl.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int r5) {
            this.zzabl.putInt(obj, j, r5);
        }

        public final long zzl(Object obj, long j) {
            return this.zzabl.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzabl.putLong(obj, j, j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzik() {
        return zzaas;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T zzh(Class<T> cls) {
        try {
            return (T) zzyt.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzi(Class<?> cls) {
        if (zzsr) {
            return zzaar.zzabl.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzj(Class<?> cls) {
        if (zzsr) {
            return zzaar.zzabl.arrayIndexScale(cls);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(Object obj, long j) {
        return zzaar.zzk(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(Object obj, long j, int r4) {
        zzaar.zzb(obj, j, r4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzl(Object obj, long j) {
        return zzaar.zzl(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j, long j2) {
        zzaar.zza(obj, j, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzm(Object obj, long j) {
        return zzaar.zzm(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j, boolean z) {
        zzaar.zza(obj, j, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float zzn(Object obj, long j) {
        return zzaar.zzn(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j, float f) {
        zzaar.zza(obj, j, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double zzo(Object obj, long j) {
        return zzaar.zzo(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j, double d) {
        zzaar.zza(obj, j, d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzp(Object obj, long j) {
        return zzaar.zzabl.getObject(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Object obj, long j, Object obj2) {
        zzaar.zzabl.putObject(obj, j, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte zza(byte[] bArr, long j) {
        return zzaar.zzy(bArr, zzaat + j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(byte[] bArr, long j, byte b) {
        zzaar.zze(bArr, zzaat + j, b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Unsafe zzil() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzjr());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzim() {
        Unsafe unsafe = zzyt;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("arrayBaseOffset", Class.class);
            cls.getMethod("arrayIndexScale", Class.class);
            cls.getMethod("getInt", Object.class, Long.TYPE);
            cls.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            cls.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
            cls.getMethod("getObject", Object.class, Long.TYPE);
            cls.getMethod("putObject", Object.class, Long.TYPE, Object.class);
            if (zzfa.zzdr()) {
                return true;
            }
            cls.getMethod("getByte", Object.class, Long.TYPE);
            cls.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
            cls.getMethod("getBoolean", Object.class, Long.TYPE);
            cls.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
            cls.getMethod("getFloat", Object.class, Long.TYPE);
            cls.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
            cls.getMethod("getDouble", Object.class, Long.TYPE);
            cls.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzin() {
        Unsafe unsafe = zzyt;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            if (zzio() == null) {
                return false;
            }
            if (zzfa.zzdr()) {
                return true;
            }
            cls.getMethod("getByte", Long.TYPE);
            cls.getMethod("putByte", Long.TYPE, Byte.TYPE);
            cls.getMethod("getInt", Long.TYPE);
            cls.getMethod("putInt", Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Long.TYPE);
            cls.getMethod("putLong", Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzk(Class<?> cls) {
        if (zzfa.zzdr()) {
            try {
                Class<?> cls2 = zzrm;
                cls2.getMethod("peekLong", cls, Boolean.TYPE);
                cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
                cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
                cls2.getMethod("peekInt", cls, Boolean.TYPE);
                cls2.getMethod("pokeByte", cls, Byte.TYPE);
                cls2.getMethod("peekByte", cls);
                cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
                cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }
        return false;
    }

    private static Field zzio() {
        Field zzb2;
        if (!zzfa.zzdr() || (zzb2 = zzb(Buffer.class, "effectiveDirectAddress")) == null) {
            Field zzb3 = zzb(Buffer.class, "address");
            if (zzb3 == null || zzb3.getType() != Long.TYPE) {
                return null;
            }
            return zzb3;
        }
        return zzb2;
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (zzk(obj, (-4) & j) >>> ((int) (((~j) & 3) << 3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzr(Object obj, long j) {
        return (byte) (zzk(obj, (-4) & j) >>> ((int) ((j & 3) << 3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        int zzk = zzk(obj, j2);
        int r5 = ((~((int) j)) & 3) << 3;
        zzb(obj, j2, ((255 & b) << r5) | (zzk & (~(255 << r5))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        int r5 = (((int) j) & 3) << 3;
        zzb(obj, j2, ((255 & b) << r5) | (zzk(obj, j2) & (~(255 << r5))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, z ? (byte) 1 : (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, z ? (byte) 1 : (byte) 0);
    }

    static {
        Unsafe zzil = zzil();
        zzyt = zzil;
        zzrm = zzfa.zzds();
        boolean zzk = zzk(Long.TYPE);
        zzaap = zzk;
        boolean zzk2 = zzk(Integer.TYPE);
        zzaaq = zzk2;
        zzd zzdVar = null;
        if (zzil != null) {
            if (!zzfa.zzdr()) {
                zzdVar = new zzb(zzil);
            } else if (zzk) {
                zzdVar = new zzc(zzil);
            } else if (zzk2) {
                zzdVar = new zza(zzil);
            }
        }
        zzaar = zzdVar;
        zzaas = zzin();
        zzsr = zzim();
        long zzi = zzi(byte[].class);
        zzaat = zzi;
        zzaau = zzi(boolean[].class);
        zzaav = zzj(boolean[].class);
        zzaaw = zzi(int[].class);
        zzaax = zzj(int[].class);
        zzaay = zzi(long[].class);
        zzaaz = zzj(long[].class);
        zzaba = zzi(float[].class);
        zzabb = zzj(float[].class);
        zzabc = zzi(double[].class);
        zzabd = zzj(double[].class);
        zzabe = zzi(Object[].class);
        zzabf = zzj(Object[].class);
        Field zzio = zzio();
        zzabg = (zzio == null || zzdVar == null) ? -1L : zzdVar.zzabl.objectFieldOffset(zzio);
        zzabh = (int) (7 & zzi);
        zzabi = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }
}
