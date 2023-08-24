package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public final class zzlm<T> implements zzlu<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzmv.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzlj zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int[] zzj;
    private final int zzk;
    private final int zzl;
    private final zzkx zzm;
    private final zzml zzn;
    private final zzjp zzo;
    private final zzlo zzp;
    private final zzle zzq;

    private zzlm(int[] r6, Object[] objArr, int r8, int r9, zzlj zzljVar, boolean z, boolean z2, int[] r13, int r14, int r15, zzlo zzloVar, zzkx zzkxVar, zzml zzmlVar, zzjp zzjpVar, zzle zzleVar, byte[] bArr) {
        this.zzc = r6;
        this.zzd = objArr;
        this.zze = r8;
        this.zzf = r9;
        this.zzi = z;
        boolean z3 = false;
        if (zzjpVar != null && zzjpVar.zzc(zzljVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzj = r13;
        this.zzk = r14;
        this.zzl = r15;
        this.zzp = zzloVar;
        this.zzm = zzkxVar;
        this.zzn = zzmlVar;
        this.zzo = zzjpVar;
        this.zzg = zzljVar;
        this.zzq = zzleVar;
    }

    private static int zzA(int r0) {
        return (r0 >>> 20) & 255;
    }

    private final int zzB(int r2) {
        return this.zzc[r2 + 1];
    }

    private static long zzC(Object obj, long j) {
        return ((Long) zzmv.zzf(obj, j)).longValue();
    }

    private final zzkg zzD(int r2) {
        int r22 = r2 / 3;
        return (zzkg) this.zzd[r22 + r22 + 1];
    }

    private final zzlu zzE(int r4) {
        int r42 = r4 / 3;
        int r43 = r42 + r42;
        zzlu zzluVar = (zzlu) this.zzd[r43];
        if (zzluVar != null) {
            return zzluVar;
        }
        zzlu zzb2 = zzlr.zza().zzb((Class) this.zzd[r43 + 1]);
        this.zzd[r43] = zzb2;
        return zzb2;
    }

    private final Object zzF(int r2) {
        int r22 = r2 / 3;
        return this.zzd[r22 + r22];
    }

    private static Field zzG(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private final void zzH(Object obj, Object obj2, int r6) {
        long zzB = zzB(r6) & 1048575;
        if (zzO(obj2, r6)) {
            Object zzf = zzmv.zzf(obj, zzB);
            Object zzf2 = zzmv.zzf(obj2, zzB);
            if (zzf != null && zzf2 != null) {
                zzmv.zzs(obj, zzB, zzkk.zzg(zzf, zzf2));
                zzJ(obj, r6);
            } else if (zzf2 != null) {
                zzmv.zzs(obj, zzB, zzf2);
                zzJ(obj, r6);
            }
        }
    }

    private final void zzI(Object obj, Object obj2, int r7) {
        int zzB = zzB(r7);
        int r1 = this.zzc[r7];
        long j = zzB & 1048575;
        if (zzR(obj2, r1, r7)) {
            Object zzf = zzR(obj, r1, r7) ? zzmv.zzf(obj, j) : null;
            Object zzf2 = zzmv.zzf(obj2, j);
            if (zzf != null && zzf2 != null) {
                zzmv.zzs(obj, j, zzkk.zzg(zzf, zzf2));
                zzK(obj, r1, r7);
            } else if (zzf2 != null) {
                zzmv.zzs(obj, j, zzf2);
                zzK(obj, r1, r7);
            }
        }
    }

    private final void zzJ(Object obj, int r7) {
        int zzy = zzy(r7);
        long j = 1048575 & zzy;
        if (j == 1048575) {
            return;
        }
        zzmv.zzq(obj, j, (1 << (zzy >>> 20)) | zzmv.zzc(obj, j));
    }

    private final void zzK(Object obj, int r4, int r5) {
        zzmv.zzq(obj, zzy(r5) & 1048575, r4);
    }

    private final void zzL(Object obj, zznd zzndVar) throws IOException {
        int r11;
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int r5 = 1048575;
        int r7 = 0;
        int r8 = 0;
        int r9 = 1048575;
        while (r7 < length) {
            int zzB = zzB(r7);
            int[] r112 = this.zzc;
            int r12 = r112[r7];
            int zzA = zzA(zzB);
            if (zzA <= 17) {
                int r113 = r112[r7 + 2];
                int r14 = r113 & r5;
                if (r14 != r9) {
                    r8 = unsafe.getInt(obj, r14);
                    r9 = r14;
                }
                r11 = 1 << (r113 >>> 20);
            } else {
                r11 = 0;
            }
            long j = zzB & r5;
            switch (zzA) {
                case 0:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzf(r12, zzmv.zza(obj, j));
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 1:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzo(r12, zzmv.zzb(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 2:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzt(r12, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 3:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzJ(r12, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 4:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzr(r12, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 5:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzm(r12, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 6:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzk(r12, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 7:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzb(r12, zzmv.zzw(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 8:
                    if ((r8 & r11) != 0) {
                        zzT(r12, unsafe.getObject(obj, j), zzndVar);
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 9:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzv(r12, unsafe.getObject(obj, j), zzE(r7));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 10:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzd(r12, (zzjb) unsafe.getObject(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 11:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzH(r12, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 12:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzi(r12, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 13:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzw(r12, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 14:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzy(r12, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 15:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzA(r12, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 16:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzC(r12, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 17:
                    if ((r8 & r11) != 0) {
                        zzndVar.zzq(r12, unsafe.getObject(obj, j), zzE(r7));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 18:
                    zzlw.zzJ(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 19:
                    zzlw.zzN(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 20:
                    zzlw.zzQ(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 21:
                    zzlw.zzY(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 22:
                    zzlw.zzP(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 23:
                    zzlw.zzM(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 24:
                    zzlw.zzL(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 25:
                    zzlw.zzH(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 26:
                    zzlw.zzW(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar);
                    break;
                case 27:
                    zzlw.zzR(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, zzE(r7));
                    break;
                case 28:
                    zzlw.zzI(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar);
                    break;
                case 29:
                    zzlw.zzX(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    break;
                case 30:
                    zzlw.zzK(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    break;
                case 31:
                    zzlw.zzS(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    break;
                case 32:
                    zzlw.zzT(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    break;
                case 33:
                    zzlw.zzU(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    break;
                case 34:
                    zzlw.zzV(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, false);
                    break;
                case 35:
                    zzlw.zzJ(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 36:
                    zzlw.zzN(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 37:
                    zzlw.zzQ(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 38:
                    zzlw.zzY(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 39:
                    zzlw.zzP(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 40:
                    zzlw.zzM(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 41:
                    zzlw.zzL(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 42:
                    zzlw.zzH(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 43:
                    zzlw.zzX(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 44:
                    zzlw.zzK(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 45:
                    zzlw.zzS(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 46:
                    zzlw.zzT(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 47:
                    zzlw.zzU(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 48:
                    zzlw.zzV(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, true);
                    break;
                case 49:
                    zzlw.zzO(this.zzc[r7], (List) unsafe.getObject(obj, j), zzndVar, zzE(r7));
                    break;
                case 50:
                    zzM(zzndVar, r12, unsafe.getObject(obj, j), r7);
                    break;
                case 51:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzf(r12, zzn(obj, j));
                        break;
                    }
                    break;
                case 52:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzo(r12, zzo(obj, j));
                        break;
                    }
                    break;
                case 53:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzt(r12, zzC(obj, j));
                        break;
                    }
                    break;
                case 54:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzJ(r12, zzC(obj, j));
                        break;
                    }
                    break;
                case 55:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzr(r12, zzr(obj, j));
                        break;
                    }
                    break;
                case 56:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzm(r12, zzC(obj, j));
                        break;
                    }
                    break;
                case 57:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzk(r12, zzr(obj, j));
                        break;
                    }
                    break;
                case 58:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzb(r12, zzS(obj, j));
                        break;
                    }
                    break;
                case 59:
                    if (zzR(obj, r12, r7)) {
                        zzT(r12, unsafe.getObject(obj, j), zzndVar);
                        break;
                    }
                    break;
                case 60:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzv(r12, unsafe.getObject(obj, j), zzE(r7));
                        break;
                    }
                    break;
                case 61:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzd(r12, (zzjb) unsafe.getObject(obj, j));
                        break;
                    }
                    break;
                case 62:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzH(r12, zzr(obj, j));
                        break;
                    }
                    break;
                case 63:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzi(r12, zzr(obj, j));
                        break;
                    }
                    break;
                case 64:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzw(r12, zzr(obj, j));
                        break;
                    }
                    break;
                case 65:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzy(r12, zzC(obj, j));
                        break;
                    }
                    break;
                case 66:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzA(r12, zzr(obj, j));
                        break;
                    }
                    break;
                case 67:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzC(r12, zzC(obj, j));
                        break;
                    }
                    break;
                case 68:
                    if (zzR(obj, r12, r7)) {
                        zzndVar.zzq(r12, unsafe.getObject(obj, j), zzE(r7));
                        break;
                    }
                    break;
            }
            r7 += 3;
            r5 = 1048575;
        }
        zzml zzmlVar = this.zzn;
        zzmlVar.zzi(zzmlVar.zzc(obj), zzndVar);
    }

    private final void zzM(zznd zzndVar, int r2, Object obj, int r4) throws IOException {
        if (obj == null) {
            return;
        }
        zzlc zzlcVar = (zzlc) zzF(r4);
        throw null;
    }

    private final boolean zzN(Object obj, Object obj2, int r3) {
        return zzO(obj, r3) == zzO(obj2, r3);
    }

    private final boolean zzO(Object obj, int r11) {
        int zzy = zzy(r11);
        long j = zzy & 1048575;
        if (j != 1048575) {
            return (zzmv.zzc(obj, j) & (1 << (zzy >>> 20))) != 0;
        }
        int zzB = zzB(r11);
        long j2 = zzB & 1048575;
        switch (zzA(zzB)) {
            case 0:
                return Double.doubleToRawLongBits(zzmv.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzmv.zzb(obj, j2)) != 0;
            case 2:
                return zzmv.zzd(obj, j2) != 0;
            case 3:
                return zzmv.zzd(obj, j2) != 0;
            case 4:
                return zzmv.zzc(obj, j2) != 0;
            case 5:
                return zzmv.zzd(obj, j2) != 0;
            case 6:
                return zzmv.zzc(obj, j2) != 0;
            case 7:
                return zzmv.zzw(obj, j2);
            case 8:
                Object zzf = zzmv.zzf(obj, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                } else if (zzf instanceof zzjb) {
                    return !zzjb.zzb.equals(zzf);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return zzmv.zzf(obj, j2) != null;
            case 10:
                return !zzjb.zzb.equals(zzmv.zzf(obj, j2));
            case 11:
                return zzmv.zzc(obj, j2) != 0;
            case 12:
                return zzmv.zzc(obj, j2) != 0;
            case 13:
                return zzmv.zzc(obj, j2) != 0;
            case 14:
                return zzmv.zzd(obj, j2) != 0;
            case 15:
                return zzmv.zzc(obj, j2) != 0;
            case 16:
                return zzmv.zzd(obj, j2) != 0;
            case 17:
                return zzmv.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzP(Object obj, int r3, int r4, int r5, int r6) {
        if (r4 == 1048575) {
            return zzO(obj, r3);
        }
        return (r5 & r6) != 0;
    }

    private static boolean zzQ(Object obj, int r3, zzlu zzluVar) {
        return zzluVar.zzk(zzmv.zzf(obj, r3 & 1048575));
    }

    private final boolean zzR(Object obj, int r4, int r5) {
        return zzmv.zzc(obj, (long) (zzy(r5) & 1048575)) == r4;
    }

    private static boolean zzS(Object obj, long j) {
        return ((Boolean) zzmv.zzf(obj, j)).booleanValue();
    }

    private static final void zzT(int r1, Object obj, zznd zzndVar) throws IOException {
        if (obj instanceof String) {
            zzndVar.zzF(r1, (String) obj);
        } else {
            zzndVar.zzd(r1, (zzjb) obj);
        }
    }

    static zzmm zzd(Object obj) {
        zzkc zzkcVar = (zzkc) obj;
        zzmm zzmmVar = zzkcVar.zzc;
        if (zzmmVar == zzmm.zzc()) {
            zzmm zze = zzmm.zze();
            zzkcVar.zzc = zze;
            return zze;
        }
        return zzmmVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzlm zzl(Class cls, zzlg zzlgVar, zzlo zzloVar, zzkx zzkxVar, zzml zzmlVar, zzjp zzjpVar, zzle zzleVar) {
        if (zzlgVar instanceof zzlt) {
            return zzm((zzlt) zzlgVar, zzloVar, zzkxVar, zzmlVar, zzjpVar, zzleVar);
        }
        zzmi zzmiVar = (zzmi) zzlgVar;
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:123:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0385  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.google.android.gms.internal.measurement.zzlm zzm(com.google.android.gms.internal.measurement.zzlt r34, com.google.android.gms.internal.measurement.zzlo r35, com.google.android.gms.internal.measurement.zzkx r36, com.google.android.gms.internal.measurement.zzml r37, com.google.android.gms.internal.measurement.zzjp r38, com.google.android.gms.internal.measurement.zzle r39) {
        /*
            Method dump skipped, instructions count: 1016
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlm.zzm(com.google.android.gms.internal.measurement.zzlt, com.google.android.gms.internal.measurement.zzlo, com.google.android.gms.internal.measurement.zzkx, com.google.android.gms.internal.measurement.zzml, com.google.android.gms.internal.measurement.zzjp, com.google.android.gms.internal.measurement.zzle):com.google.android.gms.internal.measurement.zzlm");
    }

    private static double zzn(Object obj, long j) {
        return ((Double) zzmv.zzf(obj, j)).doubleValue();
    }

    private static float zzo(Object obj, long j) {
        return ((Float) zzmv.zzf(obj, j)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final int zzp(Object obj) {
        int r10;
        int zzA;
        int zzA2;
        int zzA3;
        int zzB;
        int zzA4;
        int zzv;
        int zzA5;
        int zzA6;
        int zzd;
        int zzA7;
        int r4;
        int zzu;
        int zzi;
        int zzz;
        int zzA8;
        int r42;
        int zzA9;
        int zzA10;
        int zzA11;
        int zzB2;
        int zzA12;
        int zzd2;
        int zzA13;
        int r43;
        Unsafe unsafe = zzb;
        int r3 = 1048575;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 1048575;
        while (r5 < this.zzc.length) {
            int zzB3 = zzB(r5);
            int[] r102 = this.zzc;
            int r11 = r102[r5];
            int zzA14 = zzA(zzB3);
            if (zzA14 <= 17) {
                int r103 = r102[r5 + 2];
                int r13 = r103 & r3;
                r10 = 1 << (r103 >>> 20);
                if (r13 != r8) {
                    r7 = unsafe.getInt(obj, r13);
                    r8 = r13;
                }
            } else {
                r10 = 0;
            }
            long j = zzB3 & r3;
            switch (zzA14) {
                case 0:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzA = zzjj.zzA(r11 << 3);
                        zzA5 = zzA + 8;
                        r6 += zzA5;
                        break;
                    }
                case 1:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzA2 = zzjj.zzA(r11 << 3);
                        zzA5 = zzA2 + 4;
                        r6 += zzA5;
                        break;
                    }
                case 2:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(obj, j);
                        zzA3 = zzjj.zzA(r11 << 3);
                        zzB = zzjj.zzB(j2);
                        r6 += zzA3 + zzB;
                        break;
                    }
                case 3:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        long j3 = unsafe.getLong(obj, j);
                        zzA3 = zzjj.zzA(r11 << 3);
                        zzB = zzjj.zzB(j3);
                        r6 += zzA3 + zzB;
                        break;
                    }
                case 4:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        int r32 = unsafe.getInt(obj, j);
                        zzA4 = zzjj.zzA(r11 << 3);
                        zzv = zzjj.zzv(r32);
                        r4 = zzA4 + zzv;
                        r6 += r4;
                        break;
                    }
                case 5:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzA = zzjj.zzA(r11 << 3);
                        zzA5 = zzA + 8;
                        r6 += zzA5;
                        break;
                    }
                case 6:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzA2 = zzjj.zzA(r11 << 3);
                        zzA5 = zzA2 + 4;
                        r6 += zzA5;
                        break;
                    }
                case 7:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzA5 = zzjj.zzA(r11 << 3) + 1;
                        r6 += zzA5;
                        break;
                    }
                case 8:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzjb) {
                            zzA6 = zzjj.zzA(r11 << 3);
                            zzd = ((zzjb) object).zzd();
                            zzA7 = zzjj.zzA(zzd);
                            r4 = zzA6 + zzA7 + zzd;
                            r6 += r4;
                            break;
                        } else {
                            zzA4 = zzjj.zzA(r11 << 3);
                            zzv = zzjj.zzy((String) object);
                            r4 = zzA4 + zzv;
                            r6 += r4;
                        }
                    }
                case 9:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzA5 = zzlw.zzo(r11, unsafe.getObject(obj, j), zzE(r5));
                        r6 += zzA5;
                        break;
                    }
                case 10:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzA6 = zzjj.zzA(r11 << 3);
                        zzd = ((zzjb) unsafe.getObject(obj, j)).zzd();
                        zzA7 = zzjj.zzA(zzd);
                        r4 = zzA6 + zzA7 + zzd;
                        r6 += r4;
                        break;
                    }
                case 11:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        int r33 = unsafe.getInt(obj, j);
                        zzA4 = zzjj.zzA(r11 << 3);
                        zzv = zzjj.zzA(r33);
                        r4 = zzA4 + zzv;
                        r6 += r4;
                        break;
                    }
                case 12:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        int r34 = unsafe.getInt(obj, j);
                        zzA4 = zzjj.zzA(r11 << 3);
                        zzv = zzjj.zzv(r34);
                        r4 = zzA4 + zzv;
                        r6 += r4;
                        break;
                    }
                case 13:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzA2 = zzjj.zzA(r11 << 3);
                        zzA5 = zzA2 + 4;
                        r6 += zzA5;
                        break;
                    }
                case 14:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzA = zzjj.zzA(r11 << 3);
                        zzA5 = zzA + 8;
                        r6 += zzA5;
                        break;
                    }
                case 15:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        int r35 = unsafe.getInt(obj, j);
                        zzA4 = zzjj.zzA(r11 << 3);
                        zzv = zzjj.zzA((r35 >> 31) ^ (r35 + r35));
                        r4 = zzA4 + zzv;
                        r6 += r4;
                        break;
                    }
                case 16:
                    if ((r10 & r7) == 0) {
                        break;
                    } else {
                        long j4 = unsafe.getLong(obj, j);
                        r6 += zzjj.zzA(r11 << 3) + zzjj.zzB((j4 >> 63) ^ (j4 + j4));
                        break;
                    }
                case 17:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzA5 = zzjj.zzu(r11, (zzlj) unsafe.getObject(obj, j), zzE(r5));
                        r6 += zzA5;
                        break;
                    }
                case 18:
                    zzA5 = zzlw.zzh(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzA5;
                    break;
                case 19:
                    zzA5 = zzlw.zzf(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzA5;
                    break;
                case 20:
                    zzA5 = zzlw.zzm(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzA5;
                    break;
                case 21:
                    zzA5 = zzlw.zzx(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzA5;
                    break;
                case 22:
                    zzA5 = zzlw.zzk(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzA5;
                    break;
                case 23:
                    zzA5 = zzlw.zzh(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzA5;
                    break;
                case 24:
                    zzA5 = zzlw.zzf(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzA5;
                    break;
                case 25:
                    zzA5 = zzlw.zza(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzA5;
                    break;
                case 26:
                    zzu = zzlw.zzu(r11, (List) unsafe.getObject(obj, j));
                    r6 += zzu;
                    break;
                case 27:
                    zzu = zzlw.zzp(r11, (List) unsafe.getObject(obj, j), zzE(r5));
                    r6 += zzu;
                    break;
                case 28:
                    zzu = zzlw.zzc(r11, (List) unsafe.getObject(obj, j));
                    r6 += zzu;
                    break;
                case 29:
                    zzu = zzlw.zzv(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzu;
                    break;
                case 30:
                    zzu = zzlw.zzd(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzu;
                    break;
                case 31:
                    zzu = zzlw.zzf(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzu;
                    break;
                case 32:
                    zzu = zzlw.zzh(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzu;
                    break;
                case 33:
                    zzu = zzlw.zzq(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzu;
                    break;
                case 34:
                    zzu = zzlw.zzs(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzu;
                    break;
                case 35:
                    zzi = zzlw.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 36:
                    zzi = zzlw.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 37:
                    zzi = zzlw.zzn((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 38:
                    zzi = zzlw.zzy((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 39:
                    zzi = zzlw.zzl((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 40:
                    zzi = zzlw.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 41:
                    zzi = zzlw.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 42:
                    zzi = zzlw.zzb((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 43:
                    zzi = zzlw.zzw((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 44:
                    zzi = zzlw.zze((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 45:
                    zzi = zzlw.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 46:
                    zzi = zzlw.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 47:
                    zzi = zzlw.zzr((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 48:
                    zzi = zzlw.zzt((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzz = zzjj.zzz(r11);
                        zzA8 = zzjj.zzA(zzi);
                        r42 = zzz + zzA8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 49:
                    zzu = zzlw.zzj(r11, (List) unsafe.getObject(obj, j), zzE(r5));
                    r6 += zzu;
                    break;
                case 50:
                    zzle.zza(r11, unsafe.getObject(obj, j), zzF(r5));
                    break;
                case 51:
                    if (zzR(obj, r11, r5)) {
                        zzA9 = zzjj.zzA(r11 << 3);
                        zzu = zzA9 + 8;
                        r6 += zzu;
                    }
                    break;
                case 52:
                    if (zzR(obj, r11, r5)) {
                        zzA10 = zzjj.zzA(r11 << 3);
                        zzu = zzA10 + 4;
                        r6 += zzu;
                    }
                    break;
                case 53:
                    if (zzR(obj, r11, r5)) {
                        long zzC = zzC(obj, j);
                        zzA11 = zzjj.zzA(r11 << 3);
                        zzB2 = zzjj.zzB(zzC);
                        r6 += zzA11 + zzB2;
                    }
                    break;
                case 54:
                    if (zzR(obj, r11, r5)) {
                        long zzC2 = zzC(obj, j);
                        zzA11 = zzjj.zzA(r11 << 3);
                        zzB2 = zzjj.zzB(zzC2);
                        r6 += zzA11 + zzB2;
                    }
                    break;
                case 55:
                    if (zzR(obj, r11, r5)) {
                        int zzr = zzr(obj, j);
                        r42 = zzjj.zzA(r11 << 3);
                        zzi = zzjj.zzv(zzr);
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 56:
                    if (zzR(obj, r11, r5)) {
                        zzA9 = zzjj.zzA(r11 << 3);
                        zzu = zzA9 + 8;
                        r6 += zzu;
                    }
                    break;
                case 57:
                    if (zzR(obj, r11, r5)) {
                        zzA10 = zzjj.zzA(r11 << 3);
                        zzu = zzA10 + 4;
                        r6 += zzu;
                    }
                    break;
                case 58:
                    if (zzR(obj, r11, r5)) {
                        zzu = zzjj.zzA(r11 << 3) + 1;
                        r6 += zzu;
                    }
                    break;
                case 59:
                    if (zzR(obj, r11, r5)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzjb) {
                            zzA12 = zzjj.zzA(r11 << 3);
                            zzd2 = ((zzjb) object2).zzd();
                            zzA13 = zzjj.zzA(zzd2);
                            r43 = zzA12 + zzA13 + zzd2;
                            r6 += r43;
                        } else {
                            r42 = zzjj.zzA(r11 << 3);
                            zzi = zzjj.zzy((String) object2);
                            r43 = r42 + zzi;
                            r6 += r43;
                        }
                    }
                    break;
                case 60:
                    if (zzR(obj, r11, r5)) {
                        zzu = zzlw.zzo(r11, unsafe.getObject(obj, j), zzE(r5));
                        r6 += zzu;
                    }
                    break;
                case 61:
                    if (zzR(obj, r11, r5)) {
                        zzA12 = zzjj.zzA(r11 << 3);
                        zzd2 = ((zzjb) unsafe.getObject(obj, j)).zzd();
                        zzA13 = zzjj.zzA(zzd2);
                        r43 = zzA12 + zzA13 + zzd2;
                        r6 += r43;
                    }
                    break;
                case 62:
                    if (zzR(obj, r11, r5)) {
                        int zzr2 = zzr(obj, j);
                        r42 = zzjj.zzA(r11 << 3);
                        zzi = zzjj.zzA(zzr2);
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 63:
                    if (zzR(obj, r11, r5)) {
                        int zzr3 = zzr(obj, j);
                        r42 = zzjj.zzA(r11 << 3);
                        zzi = zzjj.zzv(zzr3);
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 64:
                    if (zzR(obj, r11, r5)) {
                        zzA10 = zzjj.zzA(r11 << 3);
                        zzu = zzA10 + 4;
                        r6 += zzu;
                    }
                    break;
                case 65:
                    if (zzR(obj, r11, r5)) {
                        zzA9 = zzjj.zzA(r11 << 3);
                        zzu = zzA9 + 8;
                        r6 += zzu;
                    }
                    break;
                case 66:
                    if (zzR(obj, r11, r5)) {
                        int zzr4 = zzr(obj, j);
                        r42 = zzjj.zzA(r11 << 3);
                        zzi = zzjj.zzA((zzr4 >> 31) ^ (zzr4 + zzr4));
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 67:
                    if (zzR(obj, r11, r5)) {
                        long zzC3 = zzC(obj, j);
                        r6 += zzjj.zzA(r11 << 3) + zzjj.zzB((zzC3 >> 63) ^ (zzC3 + zzC3));
                    }
                    break;
                case 68:
                    if (zzR(obj, r11, r5)) {
                        zzu = zzjj.zzu(r11, (zzlj) unsafe.getObject(obj, j), zzE(r5));
                        r6 += zzu;
                    }
                    break;
            }
            r5 += 3;
            r3 = 1048575;
        }
        zzml zzmlVar = this.zzn;
        int zza2 = r6 + zzmlVar.zza(zzmlVar.zzc(obj));
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        return zza2;
    }

    private final int zzq(Object obj) {
        int zzA;
        int zzA2;
        int zzA3;
        int zzB;
        int zzA4;
        int zzv;
        int zzA5;
        int zzA6;
        int zzd;
        int zzA7;
        int zzo;
        int zzz;
        int zzA8;
        int r5;
        Unsafe unsafe = zzb;
        int r3 = 0;
        for (int r2 = 0; r2 < this.zzc.length; r2 += 3) {
            int zzB2 = zzB(r2);
            int zzA9 = zzA(zzB2);
            int r6 = this.zzc[r2];
            long j = zzB2 & 1048575;
            if (zzA9 >= zzju.DOUBLE_LIST_PACKED.zza() && zzA9 <= zzju.SINT64_LIST_PACKED.zza()) {
                int r4 = this.zzc[r2 + 2];
            }
            switch (zzA9) {
                case 0:
                    if (zzO(obj, r2)) {
                        zzA = zzjj.zzA(r6 << 3);
                        zzo = zzA + 8;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzO(obj, r2)) {
                        zzA2 = zzjj.zzA(r6 << 3);
                        zzo = zzA2 + 4;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzO(obj, r2)) {
                        long zzd2 = zzmv.zzd(obj, j);
                        zzA3 = zzjj.zzA(r6 << 3);
                        zzB = zzjj.zzB(zzd2);
                        r3 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzO(obj, r2)) {
                        long zzd3 = zzmv.zzd(obj, j);
                        zzA3 = zzjj.zzA(r6 << 3);
                        zzB = zzjj.zzB(zzd3);
                        r3 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzO(obj, r2)) {
                        int zzc = zzmv.zzc(obj, j);
                        zzA4 = zzjj.zzA(r6 << 3);
                        zzv = zzjj.zzv(zzc);
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzO(obj, r2)) {
                        zzA = zzjj.zzA(r6 << 3);
                        zzo = zzA + 8;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzO(obj, r2)) {
                        zzA2 = zzjj.zzA(r6 << 3);
                        zzo = zzA2 + 4;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzO(obj, r2)) {
                        zzA5 = zzjj.zzA(r6 << 3);
                        zzo = zzA5 + 1;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zzO(obj, r2)) {
                        break;
                    } else {
                        Object zzf = zzmv.zzf(obj, j);
                        if (zzf instanceof zzjb) {
                            zzA6 = zzjj.zzA(r6 << 3);
                            zzd = ((zzjb) zzf).zzd();
                            zzA7 = zzjj.zzA(zzd);
                            r5 = zzA6 + zzA7 + zzd;
                            r3 += r5;
                            break;
                        } else {
                            zzA4 = zzjj.zzA(r6 << 3);
                            zzv = zzjj.zzy((String) zzf);
                            r5 = zzA4 + zzv;
                            r3 += r5;
                        }
                    }
                case 9:
                    if (zzO(obj, r2)) {
                        zzo = zzlw.zzo(r6, zzmv.zzf(obj, j), zzE(r2));
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzO(obj, r2)) {
                        zzA6 = zzjj.zzA(r6 << 3);
                        zzd = ((zzjb) zzmv.zzf(obj, j)).zzd();
                        zzA7 = zzjj.zzA(zzd);
                        r5 = zzA6 + zzA7 + zzd;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzO(obj, r2)) {
                        int zzc2 = zzmv.zzc(obj, j);
                        zzA4 = zzjj.zzA(r6 << 3);
                        zzv = zzjj.zzA(zzc2);
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzO(obj, r2)) {
                        int zzc3 = zzmv.zzc(obj, j);
                        zzA4 = zzjj.zzA(r6 << 3);
                        zzv = zzjj.zzv(zzc3);
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzO(obj, r2)) {
                        zzA2 = zzjj.zzA(r6 << 3);
                        zzo = zzA2 + 4;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzO(obj, r2)) {
                        zzA = zzjj.zzA(r6 << 3);
                        zzo = zzA + 8;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzO(obj, r2)) {
                        int zzc4 = zzmv.zzc(obj, j);
                        zzA4 = zzjj.zzA(r6 << 3);
                        zzv = zzjj.zzA((zzc4 >> 31) ^ (zzc4 + zzc4));
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzO(obj, r2)) {
                        long zzd4 = zzmv.zzd(obj, j);
                        zzA4 = zzjj.zzA(r6 << 3);
                        zzv = zzjj.zzB((zzd4 >> 63) ^ (zzd4 + zzd4));
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzO(obj, r2)) {
                        zzo = zzjj.zzu(r6, (zzlj) zzmv.zzf(obj, j), zzE(r2));
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzo = zzlw.zzh(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 19:
                    zzo = zzlw.zzf(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 20:
                    zzo = zzlw.zzm(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 21:
                    zzo = zzlw.zzx(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 22:
                    zzo = zzlw.zzk(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 23:
                    zzo = zzlw.zzh(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 24:
                    zzo = zzlw.zzf(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 25:
                    zzo = zzlw.zza(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 26:
                    zzo = zzlw.zzu(r6, (List) zzmv.zzf(obj, j));
                    r3 += zzo;
                    break;
                case 27:
                    zzo = zzlw.zzp(r6, (List) zzmv.zzf(obj, j), zzE(r2));
                    r3 += zzo;
                    break;
                case 28:
                    zzo = zzlw.zzc(r6, (List) zzmv.zzf(obj, j));
                    r3 += zzo;
                    break;
                case 29:
                    zzo = zzlw.zzv(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 30:
                    zzo = zzlw.zzd(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 31:
                    zzo = zzlw.zzf(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 32:
                    zzo = zzlw.zzh(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 33:
                    zzo = zzlw.zzq(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 34:
                    zzo = zzlw.zzs(r6, (List) zzmv.zzf(obj, j), false);
                    r3 += zzo;
                    break;
                case 35:
                    zzv = zzlw.zzi((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    zzv = zzlw.zzg((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    zzv = zzlw.zzn((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    zzv = zzlw.zzy((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    zzv = zzlw.zzl((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    zzv = zzlw.zzi((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    zzv = zzlw.zzg((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    zzv = zzlw.zzb((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    zzv = zzlw.zzw((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    zzv = zzlw.zze((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    zzv = zzlw.zzg((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    zzv = zzlw.zzi((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    zzv = zzlw.zzr((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    zzv = zzlw.zzt((List) unsafe.getObject(obj, j));
                    if (zzv > 0) {
                        zzz = zzjj.zzz(r6);
                        zzA8 = zzjj.zzA(zzv);
                        zzA4 = zzz + zzA8;
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    zzo = zzlw.zzj(r6, (List) zzmv.zzf(obj, j), zzE(r2));
                    r3 += zzo;
                    break;
                case 50:
                    zzle.zza(r6, zzmv.zzf(obj, j), zzF(r2));
                    break;
                case 51:
                    if (zzR(obj, r6, r2)) {
                        zzA = zzjj.zzA(r6 << 3);
                        zzo = zzA + 8;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzR(obj, r6, r2)) {
                        zzA2 = zzjj.zzA(r6 << 3);
                        zzo = zzA2 + 4;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzR(obj, r6, r2)) {
                        long zzC = zzC(obj, j);
                        zzA3 = zzjj.zzA(r6 << 3);
                        zzB = zzjj.zzB(zzC);
                        r3 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzR(obj, r6, r2)) {
                        long zzC2 = zzC(obj, j);
                        zzA3 = zzjj.zzA(r6 << 3);
                        zzB = zzjj.zzB(zzC2);
                        r3 += zzA3 + zzB;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzR(obj, r6, r2)) {
                        int zzr = zzr(obj, j);
                        zzA4 = zzjj.zzA(r6 << 3);
                        zzv = zzjj.zzv(zzr);
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzR(obj, r6, r2)) {
                        zzA = zzjj.zzA(r6 << 3);
                        zzo = zzA + 8;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzR(obj, r6, r2)) {
                        zzA2 = zzjj.zzA(r6 << 3);
                        zzo = zzA2 + 4;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzR(obj, r6, r2)) {
                        zzA5 = zzjj.zzA(r6 << 3);
                        zzo = zzA5 + 1;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zzR(obj, r6, r2)) {
                        break;
                    } else {
                        Object zzf2 = zzmv.zzf(obj, j);
                        if (zzf2 instanceof zzjb) {
                            zzA6 = zzjj.zzA(r6 << 3);
                            zzd = ((zzjb) zzf2).zzd();
                            zzA7 = zzjj.zzA(zzd);
                            r5 = zzA6 + zzA7 + zzd;
                            r3 += r5;
                            break;
                        } else {
                            zzA4 = zzjj.zzA(r6 << 3);
                            zzv = zzjj.zzy((String) zzf2);
                            r5 = zzA4 + zzv;
                            r3 += r5;
                        }
                    }
                case 60:
                    if (zzR(obj, r6, r2)) {
                        zzo = zzlw.zzo(r6, zzmv.zzf(obj, j), zzE(r2));
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzR(obj, r6, r2)) {
                        zzA6 = zzjj.zzA(r6 << 3);
                        zzd = ((zzjb) zzmv.zzf(obj, j)).zzd();
                        zzA7 = zzjj.zzA(zzd);
                        r5 = zzA6 + zzA7 + zzd;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzR(obj, r6, r2)) {
                        int zzr2 = zzr(obj, j);
                        zzA4 = zzjj.zzA(r6 << 3);
                        zzv = zzjj.zzA(zzr2);
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzR(obj, r6, r2)) {
                        int zzr3 = zzr(obj, j);
                        zzA4 = zzjj.zzA(r6 << 3);
                        zzv = zzjj.zzv(zzr3);
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzR(obj, r6, r2)) {
                        zzA2 = zzjj.zzA(r6 << 3);
                        zzo = zzA2 + 4;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzR(obj, r6, r2)) {
                        zzA = zzjj.zzA(r6 << 3);
                        zzo = zzA + 8;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzR(obj, r6, r2)) {
                        int zzr4 = zzr(obj, j);
                        zzA4 = zzjj.zzA(r6 << 3);
                        zzv = zzjj.zzA((zzr4 >> 31) ^ (zzr4 + zzr4));
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzR(obj, r6, r2)) {
                        long zzC3 = zzC(obj, j);
                        zzA4 = zzjj.zzA(r6 << 3);
                        zzv = zzjj.zzB((zzC3 >> 63) ^ (zzC3 + zzC3));
                        r5 = zzA4 + zzv;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzR(obj, r6, r2)) {
                        zzo = zzjj.zzu(r6, (zzlj) zzmv.zzf(obj, j), zzE(r2));
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
            }
        }
        zzml zzmlVar = this.zzn;
        return r3 + zzmlVar.zza(zzmlVar.zzc(obj));
    }

    private static int zzr(Object obj, long j) {
        return ((Integer) zzmv.zzf(obj, j)).intValue();
    }

    private final int zzs(Object obj, byte[] bArr, int r3, int r4, int r5, long j, zzio zzioVar) throws IOException {
        Unsafe unsafe = zzb;
        Object zzF = zzF(r5);
        Object object = unsafe.getObject(obj, j);
        if (!((zzld) object).zze()) {
            zzld zzb2 = zzld.zza().zzb();
            zzle.zzb(zzb2, object);
            unsafe.putObject(obj, j, zzb2);
        }
        zzlc zzlcVar = (zzlc) zzF;
        throw null;
    }

    private final int zzt(Object obj, byte[] bArr, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long j, int r28, zzio zzioVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[r28 + 2] & 1048575;
        switch (r25) {
            case 51:
                if (r23 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzip.zzn(bArr, r19))));
                    unsafe.putInt(obj, j2, r22);
                    return r19 + 8;
                }
                break;
            case 52:
                if (r23 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzip.zzb(bArr, r19))));
                    unsafe.putInt(obj, j2, r22);
                    return r19 + 4;
                }
                break;
            case 53:
            case 54:
                if (r23 == 0) {
                    int zzm = zzip.zzm(bArr, r19, zzioVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzioVar.zzb));
                    unsafe.putInt(obj, j2, r22);
                    return zzm;
                }
                break;
            case 55:
            case 62:
                if (r23 == 0) {
                    int zzj = zzip.zzj(bArr, r19, zzioVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzioVar.zza));
                    unsafe.putInt(obj, j2, r22);
                    return zzj;
                }
                break;
            case 56:
            case 65:
                if (r23 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(zzip.zzn(bArr, r19)));
                    unsafe.putInt(obj, j2, r22);
                    return r19 + 8;
                }
                break;
            case 57:
            case 64:
                if (r23 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(zzip.zzb(bArr, r19)));
                    unsafe.putInt(obj, j2, r22);
                    return r19 + 4;
                }
                break;
            case 58:
                if (r23 == 0) {
                    int zzm2 = zzip.zzm(bArr, r19, zzioVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(zzioVar.zzb != 0));
                    unsafe.putInt(obj, j2, r22);
                    return zzm2;
                }
                break;
            case 59:
                if (r23 == 2) {
                    int zzj2 = zzip.zzj(bArr, r19, zzioVar);
                    int r4 = zzioVar.zza;
                    if (r4 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else if ((r24 & 536870912) == 0 || zzna.zzf(bArr, zzj2, zzj2 + r4)) {
                        unsafe.putObject(obj, j, new String(bArr, zzj2, r4, zzkk.zzb));
                        zzj2 += r4;
                    } else {
                        throw zzkm.zzc();
                    }
                    unsafe.putInt(obj, j2, r22);
                    return zzj2;
                }
                break;
            case 60:
                if (r23 == 2) {
                    int zzd = zzip.zzd(zzE(r28), bArr, r19, r20, zzioVar);
                    Object object = unsafe.getInt(obj, j2) == r22 ? unsafe.getObject(obj, j) : null;
                    if (object == null) {
                        unsafe.putObject(obj, j, zzioVar.zzc);
                    } else {
                        unsafe.putObject(obj, j, zzkk.zzg(object, zzioVar.zzc));
                    }
                    unsafe.putInt(obj, j2, r22);
                    return zzd;
                }
                break;
            case 61:
                if (r23 == 2) {
                    int zza2 = zzip.zza(bArr, r19, zzioVar);
                    unsafe.putObject(obj, j, zzioVar.zzc);
                    unsafe.putInt(obj, j2, r22);
                    return zza2;
                }
                break;
            case 63:
                if (r23 == 0) {
                    int zzj3 = zzip.zzj(bArr, r19, zzioVar);
                    int r42 = zzioVar.zza;
                    zzkg zzD = zzD(r28);
                    if (zzD == null || zzD.zza(r42)) {
                        unsafe.putObject(obj, j, Integer.valueOf(r42));
                        unsafe.putInt(obj, j2, r22);
                    } else {
                        zzd(obj).zzh(r21, Long.valueOf(r42));
                    }
                    return zzj3;
                }
                break;
            case 66:
                if (r23 == 0) {
                    int zzj4 = zzip.zzj(bArr, r19, zzioVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzjf.zzb(zzioVar.zza)));
                    unsafe.putInt(obj, j2, r22);
                    return zzj4;
                }
                break;
            case 67:
                if (r23 == 0) {
                    int zzm3 = zzip.zzm(bArr, r19, zzioVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzjf.zzc(zzioVar.zzb)));
                    unsafe.putInt(obj, j2, r22);
                    return zzm3;
                }
                break;
            case 68:
                if (r23 == 3) {
                    int zzc = zzip.zzc(zzE(r28), bArr, r19, r20, (r21 & (-8)) | 4, zzioVar);
                    Object object2 = unsafe.getInt(obj, j2) == r22 ? unsafe.getObject(obj, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(obj, j, zzioVar.zzc);
                    } else {
                        unsafe.putObject(obj, j, zzkk.zzg(object2, zzioVar.zzc));
                    }
                    unsafe.putInt(obj, j2, r22);
                    return zzc;
                }
                break;
        }
        return r19;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x02e7, code lost:
        if (r0 != r5) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:101:0x02e9, code lost:
        r15 = r30;
        r14 = r31;
        r12 = r32;
        r13 = r34;
        r11 = r35;
        r1 = r20;
        r2 = r23;
        r6 = r26;
        r7 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x02fd, code lost:
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x032e, code lost:
        if (r0 != r15) goto L106;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x0351, code lost:
        if (r0 != r15) goto L106;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v9, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int zzu(java.lang.Object r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.measurement.zzio r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 958
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlm.zzu(java.lang.Object, byte[], int, int, com.google.android.gms.internal.measurement.zzio):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x021d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0152  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:119:0x021a -> B:120:0x021b). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:66:0x014f -> B:67:0x0150). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:99:0x01cc -> B:100:0x01cd). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int zzv(java.lang.Object r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.measurement.zzio r29) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1172
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzlm.zzv(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.measurement.zzio):int");
    }

    private final int zzw(int r2) {
        if (r2 < this.zze || r2 > this.zzf) {
            return -1;
        }
        return zzz(r2, 0);
    }

    private final int zzx(int r2, int r3) {
        if (r2 < this.zze || r2 > this.zzf) {
            return -1;
        }
        return zzz(r2, r3);
    }

    private final int zzy(int r2) {
        return this.zzc[r2 + 2];
    }

    private final int zzz(int r6, int r7) {
        int length = (this.zzc.length / 3) - 1;
        while (r7 <= length) {
            int r2 = (length + r7) >>> 1;
            int r3 = r2 * 3;
            int r4 = this.zzc[r3];
            if (r6 == r4) {
                return r3;
            }
            if (r6 < r4) {
                length = r2 - 1;
            } else {
                r7 = r2 + 1;
            }
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final int zza(Object obj) {
        return this.zzi ? zzq(obj) : zzp(obj);
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final int zzb(Object obj) {
        int r2;
        int zzc;
        int length = this.zzc.length;
        int r22 = 0;
        for (int r1 = 0; r1 < length; r1 += 3) {
            int zzB = zzB(r1);
            int r4 = this.zzc[r1];
            long j = 1048575 & zzB;
            int r7 = 37;
            switch (zzA(zzB)) {
                case 0:
                    r2 = r22 * 53;
                    zzc = zzkk.zzc(Double.doubleToLongBits(zzmv.zza(obj, j)));
                    r22 = r2 + zzc;
                    break;
                case 1:
                    r2 = r22 * 53;
                    zzc = Float.floatToIntBits(zzmv.zzb(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 2:
                    r2 = r22 * 53;
                    zzc = zzkk.zzc(zzmv.zzd(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 3:
                    r2 = r22 * 53;
                    zzc = zzkk.zzc(zzmv.zzd(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 4:
                    r2 = r22 * 53;
                    zzc = zzmv.zzc(obj, j);
                    r22 = r2 + zzc;
                    break;
                case 5:
                    r2 = r22 * 53;
                    zzc = zzkk.zzc(zzmv.zzd(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 6:
                    r2 = r22 * 53;
                    zzc = zzmv.zzc(obj, j);
                    r22 = r2 + zzc;
                    break;
                case 7:
                    r2 = r22 * 53;
                    zzc = zzkk.zza(zzmv.zzw(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 8:
                    r2 = r22 * 53;
                    zzc = ((String) zzmv.zzf(obj, j)).hashCode();
                    r22 = r2 + zzc;
                    break;
                case 9:
                    Object zzf = zzmv.zzf(obj, j);
                    if (zzf != null) {
                        r7 = zzf.hashCode();
                    }
                    r22 = (r22 * 53) + r7;
                    break;
                case 10:
                    r2 = r22 * 53;
                    zzc = zzmv.zzf(obj, j).hashCode();
                    r22 = r2 + zzc;
                    break;
                case 11:
                    r2 = r22 * 53;
                    zzc = zzmv.zzc(obj, j);
                    r22 = r2 + zzc;
                    break;
                case 12:
                    r2 = r22 * 53;
                    zzc = zzmv.zzc(obj, j);
                    r22 = r2 + zzc;
                    break;
                case 13:
                    r2 = r22 * 53;
                    zzc = zzmv.zzc(obj, j);
                    r22 = r2 + zzc;
                    break;
                case 14:
                    r2 = r22 * 53;
                    zzc = zzkk.zzc(zzmv.zzd(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 15:
                    r2 = r22 * 53;
                    zzc = zzmv.zzc(obj, j);
                    r22 = r2 + zzc;
                    break;
                case 16:
                    r2 = r22 * 53;
                    zzc = zzkk.zzc(zzmv.zzd(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 17:
                    Object zzf2 = zzmv.zzf(obj, j);
                    if (zzf2 != null) {
                        r7 = zzf2.hashCode();
                    }
                    r22 = (r22 * 53) + r7;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    r2 = r22 * 53;
                    zzc = zzmv.zzf(obj, j).hashCode();
                    r22 = r2 + zzc;
                    break;
                case 50:
                    r2 = r22 * 53;
                    zzc = zzmv.zzf(obj, j).hashCode();
                    r22 = r2 + zzc;
                    break;
                case 51:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzkk.zzc(Double.doubleToLongBits(zzn(obj, j)));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = Float.floatToIntBits(zzo(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzkk.zzc(zzC(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzkk.zzc(zzC(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzr(obj, j);
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzkk.zzc(zzC(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzr(obj, j);
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzkk.zza(zzS(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = ((String) zzmv.zzf(obj, j)).hashCode();
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzmv.zzf(obj, j).hashCode();
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzmv.zzf(obj, j).hashCode();
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzr(obj, j);
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzr(obj, j);
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzr(obj, j);
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzkk.zzc(zzC(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzr(obj, j);
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzkk.zzc(zzC(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzR(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzmv.zzf(obj, j).hashCode();
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (r22 * 53) + this.zzn.zzc(obj).hashCode();
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        return hashCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzc(Object obj, byte[] bArr, int r32, int r33, int r34, zzio zzioVar) throws IOException {
        Unsafe unsafe;
        int r7;
        Object obj2;
        zzlm<T> zzlmVar;
        byte b;
        int zzw;
        int r322;
        int r2;
        int r8;
        int r22;
        int r21;
        Object obj3;
        int r11;
        zzio zzioVar2;
        int r18;
        int r27;
        int r13;
        int r112;
        int r113;
        int r3;
        int r82;
        int r35;
        int r272;
        int r15;
        zzlm<T> zzlmVar2 = this;
        Object obj4 = obj;
        byte[] bArr2 = bArr;
        int r132 = r33;
        int r114 = r34;
        zzio zzioVar3 = zzioVar;
        Unsafe unsafe2 = zzb;
        int r0 = r32;
        int r1 = 0;
        int r23 = -1;
        int r36 = 0;
        int r5 = 0;
        int r6 = 1048575;
        while (true) {
            if (r0 < r132) {
                int r12 = r0 + 1;
                byte b2 = bArr2[r0];
                if (b2 < 0) {
                    int zzk = zzip.zzk(b2, bArr2, r12, zzioVar3);
                    b = zzioVar3.zza;
                    r12 = zzk;
                } else {
                    b = b2;
                }
                int r02 = b >>> 3;
                int r72 = b & 7;
                if (r02 > r23) {
                    zzw = zzlmVar2.zzx(r02, r36 / 3);
                } else {
                    zzw = zzlmVar2.zzw(r02);
                }
                if (zzw == -1) {
                    r322 = r02;
                    r2 = r12;
                    r8 = b;
                    r22 = r5;
                    unsafe = unsafe2;
                    r7 = r114;
                    r21 = 0;
                } else {
                    int[] r37 = zzlmVar2.zzc;
                    int r83 = r37[zzw + 1];
                    int zzA = zzA(r83);
                    int r212 = r12;
                    long j = r83 & 1048575;
                    if (zzA <= 17) {
                        int r03 = r37[zzw + 2];
                        int r25 = 1 << (r03 >>> 20);
                        int r04 = r03 & 1048575;
                        if (r04 != r6) {
                            r18 = b;
                            if (r6 != 1048575) {
                                unsafe2.putInt(obj4, r6, r5);
                            }
                            r5 = unsafe2.getInt(obj4, r04);
                            r27 = r04;
                        } else {
                            r18 = b;
                            r27 = r6;
                        }
                        int r62 = r5;
                        switch (zzA) {
                            case 0:
                                r13 = zzw;
                                r322 = r02;
                                r112 = r212;
                                if (r72 == 1) {
                                    zzmv.zzo(obj4, j, Double.longBitsToDouble(zzip.zzn(bArr2, r112)));
                                    r0 = r112 + 8;
                                    r5 = r62 | r25;
                                    r23 = r322;
                                    r114 = r34;
                                    r36 = r13;
                                    r1 = r18;
                                    r6 = r27;
                                    r132 = r33;
                                    break;
                                } else {
                                    r212 = r112;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                }
                            case 1:
                                r13 = zzw;
                                r322 = r02;
                                r112 = r212;
                                if (r72 == 5) {
                                    zzmv.zzp(obj4, j, Float.intBitsToFloat(zzip.zzb(bArr2, r112)));
                                    r0 = r112 + 4;
                                    r5 = r62 | r25;
                                    r23 = r322;
                                    r114 = r34;
                                    r36 = r13;
                                    r1 = r18;
                                    r6 = r27;
                                    r132 = r33;
                                    break;
                                } else {
                                    r212 = r112;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                }
                            case 2:
                            case 3:
                                r13 = zzw;
                                r322 = r02;
                                r112 = r212;
                                if (r72 == 0) {
                                    int zzm = zzip.zzm(bArr2, r112, zzioVar3);
                                    unsafe2.putLong(obj, j, zzioVar3.zzb);
                                    r5 = r62 | r25;
                                    r23 = r322;
                                    r114 = r34;
                                    r0 = zzm;
                                    r36 = r13;
                                    r1 = r18;
                                    r6 = r27;
                                    r132 = r33;
                                    break;
                                } else {
                                    r212 = r112;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                }
                            case 4:
                            case 11:
                                r13 = zzw;
                                r322 = r02;
                                r112 = r212;
                                if (r72 == 0) {
                                    r0 = zzip.zzj(bArr2, r112, zzioVar3);
                                    unsafe2.putInt(obj4, j, zzioVar3.zza);
                                    r5 = r62 | r25;
                                    r23 = r322;
                                    r114 = r34;
                                    r36 = r13;
                                    r1 = r18;
                                    r6 = r27;
                                    r132 = r33;
                                    break;
                                } else {
                                    r212 = r112;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                }
                            case 5:
                            case 14:
                                int r115 = r18;
                                r322 = r02;
                                if (r72 == 1) {
                                    r13 = zzw;
                                    r18 = r115;
                                    r112 = r212;
                                    unsafe2.putLong(obj, j, zzip.zzn(bArr2, r212));
                                    r0 = r112 + 8;
                                    r5 = r62 | r25;
                                    r23 = r322;
                                    r114 = r34;
                                    r36 = r13;
                                    r1 = r18;
                                    r6 = r27;
                                    r132 = r33;
                                    break;
                                } else {
                                    r13 = zzw;
                                    r18 = r115;
                                    r212 = r212;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                }
                            case 6:
                            case 13:
                                r113 = r18;
                                r322 = r02;
                                r3 = r212;
                                if (r72 == 5) {
                                    unsafe2.putInt(obj4, j, zzip.zzb(bArr2, r3));
                                    r0 = r3 + 4;
                                    r5 = r62 | r25;
                                    r36 = zzw;
                                    r1 = r113;
                                    r6 = r27;
                                    r23 = r322;
                                    r114 = r34;
                                    break;
                                } else {
                                    r13 = zzw;
                                    r212 = r3;
                                    r18 = r113;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                }
                            case 7:
                                r113 = r18;
                                r322 = r02;
                                r3 = r212;
                                if (r72 == 0) {
                                    r0 = zzip.zzm(bArr2, r3, zzioVar3);
                                    zzmv.zzm(obj4, j, zzioVar3.zzb != 0);
                                    r5 = r62 | r25;
                                    r36 = zzw;
                                    r1 = r113;
                                    r6 = r27;
                                    r23 = r322;
                                    r114 = r34;
                                    break;
                                } else {
                                    r13 = zzw;
                                    r212 = r3;
                                    r18 = r113;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                }
                            case 8:
                                r113 = r18;
                                r322 = r02;
                                r3 = r212;
                                if (r72 == 2) {
                                    if ((536870912 & r83) == 0) {
                                        r0 = zzip.zzg(bArr2, r3, zzioVar3);
                                    } else {
                                        r0 = zzip.zzh(bArr2, r3, zzioVar3);
                                    }
                                    unsafe2.putObject(obj4, j, zzioVar3.zzc);
                                    r5 = r62 | r25;
                                    r36 = zzw;
                                    r1 = r113;
                                    r6 = r27;
                                    r23 = r322;
                                    r114 = r34;
                                    break;
                                } else {
                                    r13 = zzw;
                                    r212 = r3;
                                    r18 = r113;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                }
                            case 9:
                                r82 = zzw;
                                r113 = r18;
                                r322 = r02;
                                r35 = r212;
                                if (r72 == 2) {
                                    r0 = zzip.zzd(zzlmVar2.zzE(r82), bArr2, r35, r132, zzioVar3);
                                    if ((r62 & r25) == 0) {
                                        unsafe2.putObject(obj4, j, zzioVar3.zzc);
                                    } else {
                                        unsafe2.putObject(obj4, j, zzkk.zzg(unsafe2.getObject(obj4, j), zzioVar3.zzc));
                                    }
                                    r5 = r62 | r25;
                                    r23 = r322;
                                    r36 = r82;
                                    r1 = r113;
                                    r6 = r27;
                                    r114 = r34;
                                    break;
                                } else {
                                    r212 = r35;
                                    r13 = r82;
                                    r18 = r113;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                }
                            case 10:
                                r82 = zzw;
                                r113 = r18;
                                r322 = r02;
                                r35 = r212;
                                if (r72 == 2) {
                                    r0 = zzip.zza(bArr2, r35, zzioVar3);
                                    unsafe2.putObject(obj4, j, zzioVar3.zzc);
                                    r5 = r62 | r25;
                                    r23 = r322;
                                    r36 = r82;
                                    r1 = r113;
                                    r6 = r27;
                                    r114 = r34;
                                    break;
                                } else {
                                    r212 = r35;
                                    r13 = r82;
                                    r18 = r113;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                }
                            case 12:
                                r82 = zzw;
                                r113 = r18;
                                r322 = r02;
                                r35 = r212;
                                if (r72 != 0) {
                                    r212 = r35;
                                    r13 = r82;
                                    r18 = r113;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                } else {
                                    r0 = zzip.zzj(bArr2, r35, zzioVar3);
                                    int r14 = zzioVar3.zza;
                                    zzkg zzD = zzlmVar2.zzD(r82);
                                    if (zzD == null || zzD.zza(r14)) {
                                        unsafe2.putInt(obj4, j, r14);
                                        r5 = r62 | r25;
                                        r23 = r322;
                                        r36 = r82;
                                        r1 = r113;
                                        r6 = r27;
                                        r114 = r34;
                                        break;
                                    } else {
                                        zzd(obj).zzh(r113, Long.valueOf(r14));
                                        r23 = r322;
                                        r5 = r62;
                                        r36 = r82;
                                        r1 = r113;
                                        r6 = r27;
                                        r114 = r34;
                                    }
                                }
                                break;
                            case 15:
                                r82 = zzw;
                                r113 = r18;
                                r322 = r02;
                                r35 = r212;
                                if (r72 == 0) {
                                    r0 = zzip.zzj(bArr2, r35, zzioVar3);
                                    unsafe2.putInt(obj4, j, zzjf.zzb(zzioVar3.zza));
                                    r5 = r62 | r25;
                                    r23 = r322;
                                    r36 = r82;
                                    r1 = r113;
                                    r6 = r27;
                                    r114 = r34;
                                    break;
                                } else {
                                    r212 = r35;
                                    r13 = r82;
                                    r18 = r113;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                }
                            case 16:
                                if (r72 == 0) {
                                    int zzm2 = zzip.zzm(bArr2, r212, zzioVar3);
                                    r82 = zzw;
                                    r113 = r18;
                                    unsafe2.putLong(obj, j, zzjf.zzc(zzioVar3.zzb));
                                    r5 = r62 | r25;
                                    r23 = r02;
                                    r0 = zzm2;
                                    r36 = r82;
                                    r1 = r113;
                                    r6 = r27;
                                    r114 = r34;
                                    break;
                                } else {
                                    r322 = r02;
                                    r13 = zzw;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                }
                            default:
                                r13 = zzw;
                                r322 = r02;
                                r112 = r212;
                                if (r72 == 3) {
                                    r0 = zzip.zzc(zzlmVar2.zzE(r13), bArr, r112, r33, (r322 << 3) | 4, zzioVar);
                                    if ((r62 & r25) == 0) {
                                        unsafe2.putObject(obj4, j, zzioVar3.zzc);
                                    } else {
                                        unsafe2.putObject(obj4, j, zzkk.zzg(unsafe2.getObject(obj4, j), zzioVar3.zzc));
                                    }
                                    r5 = r62 | r25;
                                    bArr2 = bArr;
                                    r23 = r322;
                                    r114 = r34;
                                    r36 = r13;
                                    r1 = r18;
                                    r6 = r27;
                                    r132 = r33;
                                    break;
                                } else {
                                    r212 = r112;
                                    r7 = r34;
                                    r22 = r62;
                                    unsafe = unsafe2;
                                    r8 = r18;
                                    r2 = r212;
                                    r6 = r27;
                                    r21 = r13;
                                    break;
                                }
                        }
                    } else {
                        int r133 = zzw;
                        int r182 = b;
                        if (zzA != 27) {
                            r22 = r5;
                            r272 = r6;
                            if (zzA <= 49) {
                                r322 = r02;
                                unsafe = unsafe2;
                                r21 = r133;
                                r0 = zzv(obj, bArr, r212, r33, r182, r322, r72, r133, r83, zzA, j, zzioVar);
                                if (r0 != r212) {
                                    zzlmVar2 = this;
                                    obj4 = obj;
                                    bArr2 = bArr;
                                    r23 = r322;
                                    r132 = r33;
                                    r114 = r34;
                                    zzioVar3 = zzioVar;
                                    r1 = r182;
                                    r36 = r21;
                                    r5 = r22;
                                    r6 = r272;
                                    unsafe2 = unsafe;
                                } else {
                                    r7 = r34;
                                    r2 = r0;
                                    r8 = r182;
                                    r6 = r272;
                                }
                            } else {
                                r322 = r02;
                                unsafe = unsafe2;
                                r15 = r212;
                                r21 = r133;
                                if (zzA != 50) {
                                    r0 = zzt(obj, bArr, r15, r33, r182, r322, r72, r83, zzA, j, r21, zzioVar);
                                    if (r0 != r15) {
                                        zzlmVar2 = this;
                                        obj4 = obj;
                                        bArr2 = bArr;
                                        r23 = r322;
                                        r132 = r33;
                                        r114 = r34;
                                        zzioVar3 = zzioVar;
                                        r1 = r182;
                                        r36 = r21;
                                        r5 = r22;
                                        r6 = r272;
                                        unsafe2 = unsafe;
                                    } else {
                                        r7 = r34;
                                        r2 = r0;
                                        r8 = r182;
                                        r6 = r272;
                                    }
                                } else if (r72 == 2) {
                                    r0 = zzs(obj, bArr, r15, r33, r21, j, zzioVar);
                                    if (r0 != r15) {
                                        zzlmVar2 = this;
                                        obj4 = obj;
                                        bArr2 = bArr;
                                        r23 = r322;
                                        r132 = r33;
                                        r114 = r34;
                                        zzioVar3 = zzioVar;
                                        r1 = r182;
                                        r36 = r21;
                                        r5 = r22;
                                        r6 = r272;
                                        unsafe2 = unsafe;
                                    } else {
                                        r7 = r34;
                                        r2 = r0;
                                        r8 = r182;
                                        r6 = r272;
                                    }
                                }
                            }
                        } else if (r72 == 2) {
                            zzkj zzkjVar = (zzkj) unsafe2.getObject(obj4, j);
                            if (!zzkjVar.zzc()) {
                                int size = zzkjVar.size();
                                zzkjVar = zzkjVar.zzd(size == 0 ? 10 : size + size);
                                unsafe2.putObject(obj4, j, zzkjVar);
                            }
                            r1 = r182;
                            r27 = r6;
                            r0 = zzip.zze(zzlmVar2.zzE(r133), r1, bArr, r212, r33, zzkjVar, zzioVar);
                            bArr2 = bArr;
                            r114 = r34;
                            r23 = r02;
                            r36 = r133;
                            r5 = r5;
                            r6 = r27;
                            r132 = r33;
                        } else {
                            r22 = r5;
                            r272 = r6;
                            r322 = r02;
                            unsafe = unsafe2;
                            r15 = r212;
                            r21 = r133;
                        }
                        r7 = r34;
                        r2 = r15;
                        r8 = r182;
                        r6 = r272;
                    }
                }
                if (r8 != r7 || r7 == 0) {
                    if (this.zzh) {
                        zzioVar2 = zzioVar;
                        if (zzioVar2.zzd != zzjo.zza()) {
                            r11 = r322;
                            if (zzioVar2.zzd.zzc(this.zzg, r11) == null) {
                                r0 = zzip.zzi(r8, bArr, r2, r33, zzd(obj), zzioVar);
                                obj3 = obj;
                                r132 = r33;
                                r1 = r8;
                                zzlmVar2 = this;
                                zzioVar3 = zzioVar2;
                                r23 = r11;
                                obj4 = obj3;
                                r36 = r21;
                                r5 = r22;
                                unsafe2 = unsafe;
                                bArr2 = bArr;
                                r114 = r7;
                            } else {
                                zzjz zzjzVar = (zzjz) obj;
                                throw null;
                            }
                        } else {
                            obj3 = obj;
                            r11 = r322;
                        }
                    } else {
                        obj3 = obj;
                        r11 = r322;
                        zzioVar2 = zzioVar;
                    }
                    r0 = zzip.zzi(r8, bArr, r2, r33, zzd(obj), zzioVar);
                    r132 = r33;
                    r1 = r8;
                    zzlmVar2 = this;
                    zzioVar3 = zzioVar2;
                    r23 = r11;
                    obj4 = obj3;
                    r36 = r21;
                    r5 = r22;
                    unsafe2 = unsafe;
                    bArr2 = bArr;
                    r114 = r7;
                } else {
                    zzlmVar = this;
                    obj2 = obj;
                    r0 = r2;
                    r1 = r8;
                    r5 = r22;
                }
            } else {
                unsafe = unsafe2;
                r7 = r114;
                obj2 = obj4;
                zzlmVar = zzlmVar2;
            }
        }
        if (r6 != 1048575) {
            unsafe.putInt(obj2, r6, r5);
        }
        for (int r38 = zzlmVar.zzk; r38 < zzlmVar.zzl; r38++) {
            int r4 = zzlmVar.zzj[r38];
            int r52 = zzlmVar.zzc[r4];
            Object zzf = zzmv.zzf(obj2, zzlmVar.zzB(r4) & 1048575);
            if (zzf != null && zzlmVar.zzD(r4) != null) {
                zzld zzldVar = (zzld) zzf;
                zzlc zzlcVar = (zzlc) zzlmVar.zzF(r4);
                throw null;
            }
        }
        if (r7 == 0) {
            if (r0 != r33) {
                throw zzkm.zze();
            }
        } else if (r0 > r33 || r1 != r7) {
            throw zzkm.zze();
        }
        return r0;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final Object zze() {
        return ((zzkc) this.zzg).zzl(4, null, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzf(Object obj) {
        int r1;
        int r0 = this.zzk;
        while (true) {
            r1 = this.zzl;
            if (r0 >= r1) {
                break;
            }
            long zzB = zzB(this.zzj[r0]) & 1048575;
            Object zzf = zzmv.zzf(obj, zzB);
            if (zzf != null) {
                ((zzld) zzf).zzc();
                zzmv.zzs(obj, zzB, zzf);
            }
            r0++;
        }
        int length = this.zzj.length;
        while (r1 < length) {
            this.zzm.zza(obj, this.zzj[r1]);
            r1++;
        }
        this.zzn.zzg(obj);
        if (this.zzh) {
            this.zzo.zzb(obj);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzh(Object obj, byte[] bArr, int r11, int r12, zzio zzioVar) throws IOException {
        if (this.zzi) {
            zzu(obj, bArr, r11, r12, zzioVar);
        } else {
            zzc(obj, bArr, r11, r12, 0, zzioVar);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzi(Object obj, zznd zzndVar) throws IOException {
        if (!this.zzi) {
            zzL(obj, zzndVar);
        } else if (!this.zzh) {
            int length = this.zzc.length;
            for (int r2 = 0; r2 < length; r2 += 3) {
                int zzB = zzB(r2);
                int r4 = this.zzc[r2];
                switch (zzA(zzB)) {
                    case 0:
                        if (zzO(obj, r2)) {
                            zzndVar.zzf(r4, zzmv.zza(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzO(obj, r2)) {
                            zzndVar.zzo(r4, zzmv.zzb(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzO(obj, r2)) {
                            zzndVar.zzt(r4, zzmv.zzd(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzO(obj, r2)) {
                            zzndVar.zzJ(r4, zzmv.zzd(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzO(obj, r2)) {
                            zzndVar.zzr(r4, zzmv.zzc(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzO(obj, r2)) {
                            zzndVar.zzm(r4, zzmv.zzd(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzO(obj, r2)) {
                            zzndVar.zzk(r4, zzmv.zzc(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzO(obj, r2)) {
                            zzndVar.zzb(r4, zzmv.zzw(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzO(obj, r2)) {
                            zzT(r4, zzmv.zzf(obj, zzB & 1048575), zzndVar);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zzO(obj, r2)) {
                            zzndVar.zzv(r4, zzmv.zzf(obj, zzB & 1048575), zzE(r2));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzO(obj, r2)) {
                            zzndVar.zzd(r4, (zzjb) zzmv.zzf(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzO(obj, r2)) {
                            zzndVar.zzH(r4, zzmv.zzc(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzO(obj, r2)) {
                            zzndVar.zzi(r4, zzmv.zzc(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzO(obj, r2)) {
                            zzndVar.zzw(r4, zzmv.zzc(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzO(obj, r2)) {
                            zzndVar.zzy(r4, zzmv.zzd(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzO(obj, r2)) {
                            zzndVar.zzA(r4, zzmv.zzc(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzO(obj, r2)) {
                            zzndVar.zzC(r4, zzmv.zzd(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzO(obj, r2)) {
                            zzndVar.zzq(r4, zzmv.zzf(obj, zzB & 1048575), zzE(r2));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzlw.zzJ(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 19:
                        zzlw.zzN(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 20:
                        zzlw.zzQ(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 21:
                        zzlw.zzY(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 22:
                        zzlw.zzP(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 23:
                        zzlw.zzM(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 24:
                        zzlw.zzL(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 25:
                        zzlw.zzH(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 26:
                        zzlw.zzW(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar);
                        break;
                    case 27:
                        zzlw.zzR(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, zzE(r2));
                        break;
                    case 28:
                        zzlw.zzI(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar);
                        break;
                    case 29:
                        zzlw.zzX(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 30:
                        zzlw.zzK(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 31:
                        zzlw.zzS(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 32:
                        zzlw.zzT(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 33:
                        zzlw.zzU(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 34:
                        zzlw.zzV(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, false);
                        break;
                    case 35:
                        zzlw.zzJ(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 36:
                        zzlw.zzN(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 37:
                        zzlw.zzQ(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 38:
                        zzlw.zzY(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 39:
                        zzlw.zzP(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 40:
                        zzlw.zzM(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 41:
                        zzlw.zzL(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 42:
                        zzlw.zzH(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 43:
                        zzlw.zzX(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 44:
                        zzlw.zzK(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 45:
                        zzlw.zzS(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 46:
                        zzlw.zzT(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 47:
                        zzlw.zzU(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 48:
                        zzlw.zzV(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, true);
                        break;
                    case 49:
                        zzlw.zzO(r4, (List) zzmv.zzf(obj, zzB & 1048575), zzndVar, zzE(r2));
                        break;
                    case 50:
                        zzM(zzndVar, r4, zzmv.zzf(obj, zzB & 1048575), r2);
                        break;
                    case 51:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzf(r4, zzn(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzo(r4, zzo(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzt(r4, zzC(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzJ(r4, zzC(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzr(r4, zzr(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzm(r4, zzC(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzk(r4, zzr(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzb(r4, zzS(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zzR(obj, r4, r2)) {
                            zzT(r4, zzmv.zzf(obj, zzB & 1048575), zzndVar);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzv(r4, zzmv.zzf(obj, zzB & 1048575), zzE(r2));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzd(r4, (zzjb) zzmv.zzf(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzH(r4, zzr(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzi(r4, zzr(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzw(r4, zzr(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzy(r4, zzC(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzA(r4, zzr(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzC(r4, zzC(obj, zzB & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zzR(obj, r4, r2)) {
                            zzndVar.zzq(r4, zzmv.zzf(obj, zzB & 1048575), zzE(r2));
                            break;
                        } else {
                            break;
                        }
                }
            }
            zzml zzmlVar = this.zzn;
            zzmlVar.zzi(zzmlVar.zzc(obj), zzndVar);
        } else {
            this.zzo.zza(obj);
            throw null;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final boolean zzj(Object obj, Object obj2) {
        boolean zzZ;
        int length = this.zzc.length;
        for (int r2 = 0; r2 < length; r2 += 3) {
            int zzB = zzB(r2);
            long j = zzB & 1048575;
            switch (zzA(zzB)) {
                case 0:
                    if (zzN(obj, obj2, r2) && Double.doubleToLongBits(zzmv.zza(obj, j)) == Double.doubleToLongBits(zzmv.zza(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (zzN(obj, obj2, r2) && Float.floatToIntBits(zzmv.zzb(obj, j)) == Float.floatToIntBits(zzmv.zzb(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (zzN(obj, obj2, r2) && zzmv.zzd(obj, j) == zzmv.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (zzN(obj, obj2, r2) && zzmv.zzd(obj, j) == zzmv.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (zzN(obj, obj2, r2) && zzmv.zzc(obj, j) == zzmv.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (zzN(obj, obj2, r2) && zzmv.zzd(obj, j) == zzmv.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (zzN(obj, obj2, r2) && zzmv.zzc(obj, j) == zzmv.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (zzN(obj, obj2, r2) && zzmv.zzw(obj, j) == zzmv.zzw(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (zzN(obj, obj2, r2) && zzlw.zzZ(zzmv.zzf(obj, j), zzmv.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (zzN(obj, obj2, r2) && zzlw.zzZ(zzmv.zzf(obj, j), zzmv.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (zzN(obj, obj2, r2) && zzlw.zzZ(zzmv.zzf(obj, j), zzmv.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (zzN(obj, obj2, r2) && zzmv.zzc(obj, j) == zzmv.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (zzN(obj, obj2, r2) && zzmv.zzc(obj, j) == zzmv.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (zzN(obj, obj2, r2) && zzmv.zzc(obj, j) == zzmv.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (zzN(obj, obj2, r2) && zzmv.zzd(obj, j) == zzmv.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (zzN(obj, obj2, r2) && zzmv.zzc(obj, j) == zzmv.zzc(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (zzN(obj, obj2, r2) && zzmv.zzd(obj, j) == zzmv.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (zzN(obj, obj2, r2) && zzlw.zzZ(zzmv.zzf(obj, j), zzmv.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    zzZ = zzlw.zzZ(zzmv.zzf(obj, j), zzmv.zzf(obj2, j));
                    break;
                case 50:
                    zzZ = zzlw.zzZ(zzmv.zzf(obj, j), zzmv.zzf(obj2, j));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                    long zzy = zzy(r2) & 1048575;
                    if (zzmv.zzc(obj, zzy) == zzmv.zzc(obj2, zzy) && zzlw.zzZ(zzmv.zzf(obj, j), zzmv.zzf(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!zzZ) {
                return false;
            }
        }
        if (this.zzn.zzc(obj).equals(this.zzn.zzc(obj2))) {
            if (this.zzh) {
                this.zzo.zza(obj);
                this.zzo.zza(obj2);
                throw null;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final boolean zzk(Object obj) {
        int r16;
        int r17;
        int r0 = 1048575;
        int r1 = 0;
        int r10 = 0;
        while (r10 < this.zzk) {
            int r12 = this.zzj[r10];
            int r13 = this.zzc[r12];
            int zzB = zzB(r12);
            int r2 = this.zzc[r12 + 2];
            int r4 = r2 & 1048575;
            int r15 = 1 << (r2 >>> 20);
            if (r4 != r0) {
                if (r4 != 1048575) {
                    r1 = zzb.getInt(obj, r4);
                }
                r17 = r1;
                r16 = r4;
            } else {
                r16 = r0;
                r17 = r1;
            }
            if ((268435456 & zzB) != 0 && !zzP(obj, r12, r16, r17, r15)) {
                return false;
            }
            int zzA = zzA(zzB);
            if (zzA != 9 && zzA != 17) {
                if (zzA != 27) {
                    if (zzA == 60 || zzA == 68) {
                        if (zzR(obj, r13, r12) && !zzQ(obj, zzB, zzE(r12))) {
                            return false;
                        }
                    } else if (zzA != 49) {
                        if (zzA == 50 && !((zzld) zzmv.zzf(obj, zzB & 1048575)).isEmpty()) {
                            zzlc zzlcVar = (zzlc) zzF(r12);
                            throw null;
                        }
                    }
                }
                List list = (List) zzmv.zzf(obj, zzB & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzlu zzE = zzE(r12);
                    for (int r22 = 0; r22 < list.size(); r22++) {
                        if (!zzE.zzk(list.get(r22))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (zzP(obj, r12, r16, r17, r15) && !zzQ(obj, zzB, zzE(r12))) {
                return false;
            }
            r10++;
            r0 = r16;
            r1 = r17;
        }
        if (this.zzh) {
            this.zzo.zza(obj);
            throw null;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzlu
    public final void zzg(Object obj, Object obj2) {
        Objects.requireNonNull(obj2);
        for (int r0 = 0; r0 < this.zzc.length; r0 += 3) {
            int zzB = zzB(r0);
            long j = 1048575 & zzB;
            int r4 = this.zzc[r0];
            switch (zzA(zzB)) {
                case 0:
                    if (zzO(obj2, r0)) {
                        zzmv.zzo(obj, j, zzmv.zza(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzO(obj2, r0)) {
                        zzmv.zzp(obj, j, zzmv.zzb(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzO(obj2, r0)) {
                        zzmv.zzr(obj, j, zzmv.zzd(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzO(obj2, r0)) {
                        zzmv.zzr(obj, j, zzmv.zzd(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzO(obj2, r0)) {
                        zzmv.zzq(obj, j, zzmv.zzc(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzO(obj2, r0)) {
                        zzmv.zzr(obj, j, zzmv.zzd(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzO(obj2, r0)) {
                        zzmv.zzq(obj, j, zzmv.zzc(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzO(obj2, r0)) {
                        zzmv.zzm(obj, j, zzmv.zzw(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzO(obj2, r0)) {
                        zzmv.zzs(obj, j, zzmv.zzf(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzH(obj, obj2, r0);
                    break;
                case 10:
                    if (zzO(obj2, r0)) {
                        zzmv.zzs(obj, j, zzmv.zzf(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzO(obj2, r0)) {
                        zzmv.zzq(obj, j, zzmv.zzc(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzO(obj2, r0)) {
                        zzmv.zzq(obj, j, zzmv.zzc(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzO(obj2, r0)) {
                        zzmv.zzq(obj, j, zzmv.zzc(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzO(obj2, r0)) {
                        zzmv.zzr(obj, j, zzmv.zzd(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzO(obj2, r0)) {
                        zzmv.zzq(obj, j, zzmv.zzc(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzO(obj2, r0)) {
                        zzmv.zzr(obj, j, zzmv.zzd(obj2, j));
                        zzJ(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzH(obj, obj2, r0);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzm.zzb(obj, obj2, j);
                    break;
                case 50:
                    zzlw.zzaa(this.zzq, obj, obj2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (zzR(obj2, r4, r0)) {
                        zzmv.zzs(obj, j, zzmv.zzf(obj2, j));
                        zzK(obj, r4, r0);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzI(obj, obj2, r0);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzR(obj2, r4, r0)) {
                        zzmv.zzs(obj, j, zzmv.zzf(obj2, j));
                        zzK(obj, r4, r0);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzI(obj, obj2, r0);
                    break;
            }
        }
        zzlw.zzF(this.zzn, obj, obj2);
        if (this.zzh) {
            zzlw.zzE(this.zzo, obj, obj2);
        }
    }
}
