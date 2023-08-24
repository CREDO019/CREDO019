package com.google.android.gms.internal.ads;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgrr {
    static final long zza;
    static final boolean zzb;
    private static final Unsafe zzc;
    private static final Class zzd;
    private static final boolean zze;
    private static final zzgrq zzf;
    private static final boolean zzg;
    private static final boolean zzh;
    private static final long zzi;

    /* JADX WARN: Removed duplicated region for block: B:22:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0158  */
    static {
        /*
            Method dump skipped, instructions count: 348
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgrr.<clinit>():void");
    }

    private zzgrr() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzA() {
        return zzh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzB() {
        return zzg;
    }

    private static int zzC(Class cls) {
        if (zzh) {
            return zzf.zzj(cls);
        }
        return -1;
    }

    private static int zzD(Class cls) {
        if (zzh) {
            return zzf.zzk(cls);
        }
        return -1;
    }

    private static Field zzE() {
        int r0 = zzgmq.zza;
        Field zzF = zzF(Buffer.class, "effectiveDirectAddress");
        if (zzF == null) {
            Field zzF2 = zzF(Buffer.class, "address");
            if (zzF2 == null || zzF2.getType() != Long.TYPE) {
                return null;
            }
            return zzF2;
        }
        return zzF;
    }

    private static Field zzF(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzG(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        zzgrq zzgrqVar = zzf;
        int zzl = zzgrqVar.zzl(obj, j2);
        int r6 = ((~((int) j)) & 3) << 3;
        zzgrqVar.zzp(obj, j2, ((255 & b) << r6) | (zzl & (~(255 << r6))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzH(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        zzgrq zzgrqVar = zzf;
        int r6 = (((int) j) & 3) << 3;
        zzgrqVar.zzp(obj, j2, ((255 & b) << r6) | (zzgrqVar.zzl(obj, j2) & (~(255 << r6))));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte zza(long j) {
        return zzf.zza(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double zzb(Object obj, long j) {
        return zzf.zzb(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float zzc(Object obj, long j) {
        return zzf.zzc(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(Object obj, long j) {
        return zzf.zzl(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zze(ByteBuffer byteBuffer) {
        return zzf.zzm(byteBuffer, zzi);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzf(Object obj, long j) {
        return zzf.zzm(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzg(Class cls) {
        try {
            return zzc.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzh(Object obj, long j) {
        return zzf.zzo(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Unsafe zzi() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzgrn());
        } catch (Throwable unused) {
            return null;
        }
    }

    static /* bridge */ /* synthetic */ void zzj(Throwable th) {
        Logger.getLogger(zzgrr.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzo(long j, byte[] bArr, long j2, long j3) {
        zzf.zzd(j, bArr, j2, j3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzp(Object obj, long j, boolean z) {
        zzf.zze(obj, j, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzq(byte[] bArr, long j, byte b) {
        zzf.zzf(bArr, zza + j, b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzr(Object obj, long j, double d) {
        zzf.zzg(obj, j, d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzs(Object obj, long j, float f) {
        zzf.zzh(obj, j, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzt(Object obj, long j, int r4) {
        zzf.zzp(obj, j, r4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzu(Object obj, long j, long j2) {
        zzf.zzq(obj, j, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzv(Object obj, long j, Object obj2) {
        zzf.zzr(obj, j, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean zzw(Object obj, long j) {
        return ((byte) ((zzf.zzl(obj, (-4) & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean zzx(Object obj, long j) {
        return ((byte) ((zzf.zzl(obj, (-4) & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static boolean zzy(Class cls) {
        int r1 = zzgmq.zza;
        try {
            Class cls2 = zzd;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzz(Object obj, long j) {
        return zzf.zzi(obj, j);
    }
}
