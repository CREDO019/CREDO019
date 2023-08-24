package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgqa<T> implements zzgqq<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzgrr.zzi();
    private final int[] zzc;
    private final Object[] zzd;
    private final int zze;
    private final int zzf;
    private final zzgpx zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final boolean zzj;
    private final int[] zzk;
    private final int zzl;
    private final int zzm;
    private final zzgpl zzn;
    private final zzgrh zzo;
    private final zzgoa zzp;
    private final zzgqc zzq;
    private final zzgps zzr;

    private zzgqa(int[] r6, Object[] objArr, int r8, int r9, zzgpx zzgpxVar, boolean z, boolean z2, int[] r13, int r14, int r15, zzgqc zzgqcVar, zzgpl zzgplVar, zzgrh zzgrhVar, zzgoa zzgoaVar, zzgps zzgpsVar, byte[] bArr) {
        this.zzc = r6;
        this.zzd = objArr;
        this.zze = r8;
        this.zzf = r9;
        this.zzi = zzgpxVar instanceof zzgon;
        this.zzj = z;
        boolean z3 = false;
        if (zzgoaVar != null && zzgoaVar.zzh(zzgpxVar)) {
            z3 = true;
        }
        this.zzh = z3;
        this.zzk = r13;
        this.zzl = r14;
        this.zzm = r15;
        this.zzq = zzgqcVar;
        this.zzn = zzgplVar;
        this.zzo = zzgrhVar;
        this.zzp = zzgoaVar;
        this.zzg = zzgpxVar;
        this.zzr = zzgpsVar;
    }

    private final int zzA(int r6, int r7) {
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

    private static int zzB(int r0) {
        return (r0 >>> 20) & 255;
    }

    private final int zzC(int r2) {
        return this.zzc[r2 + 1];
    }

    private static long zzD(Object obj, long j) {
        return ((Long) zzgrr.zzh(obj, j)).longValue();
    }

    private final zzgor zzE(int r2) {
        int r22 = r2 / 3;
        return (zzgor) this.zzd[r22 + r22 + 1];
    }

    private final zzgqq zzF(int r4) {
        int r42 = r4 / 3;
        int r43 = r42 + r42;
        zzgqq zzgqqVar = (zzgqq) this.zzd[r43];
        if (zzgqqVar != null) {
            return zzgqqVar;
        }
        zzgqq zzb2 = zzgqf.zza().zzb((Class) this.zzd[r43 + 1]);
        this.zzd[r43] = zzb2;
        return zzb2;
    }

    private final Object zzG(Object obj, int r4, Object obj2, zzgrh zzgrhVar) {
        int r6 = this.zzc[r4];
        Object zzh = zzgrr.zzh(obj, zzC(r4) & 1048575);
        if (zzh == null || zzE(r4) == null) {
            return obj2;
        }
        zzgpr zzgprVar = (zzgpr) zzh;
        zzgpq zzgpqVar = (zzgpq) zzH(r4);
        throw null;
    }

    private final Object zzH(int r2) {
        int r22 = r2 / 3;
        return this.zzd[r22 + r22];
    }

    private static Field zzI(Class cls, String str) {
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

    private final void zzJ(Object obj, Object obj2, int r6) {
        long zzC = zzC(r6) & 1048575;
        if (zzQ(obj2, r6)) {
            Object zzh = zzgrr.zzh(obj, zzC);
            Object zzh2 = zzgrr.zzh(obj2, zzC);
            if (zzh != null && zzh2 != null) {
                zzgrr.zzv(obj, zzC, zzgox.zzg(zzh, zzh2));
                zzM(obj, r6);
            } else if (zzh2 != null) {
                zzgrr.zzv(obj, zzC, zzh2);
                zzM(obj, r6);
            }
        }
    }

    private final void zzK(Object obj, Object obj2, int r7) {
        int zzC = zzC(r7);
        int r1 = this.zzc[r7];
        long j = zzC & 1048575;
        if (zzT(obj2, r1, r7)) {
            Object zzh = zzT(obj, r1, r7) ? zzgrr.zzh(obj, j) : null;
            Object zzh2 = zzgrr.zzh(obj2, j);
            if (zzh != null && zzh2 != null) {
                zzgrr.zzv(obj, j, zzgox.zzg(zzh, zzh2));
                zzN(obj, r1, r7);
            } else if (zzh2 != null) {
                zzgrr.zzv(obj, j, zzh2);
                zzN(obj, r1, r7);
            }
        }
    }

    private final void zzL(Object obj, int r4, zzgqi zzgqiVar) throws IOException {
        if (zzP(r4)) {
            zzgrr.zzv(obj, r4 & 1048575, zzgqiVar.zzu());
        } else if (!this.zzi) {
            zzgrr.zzv(obj, r4 & 1048575, zzgqiVar.zzp());
        } else {
            zzgrr.zzv(obj, r4 & 1048575, zzgqiVar.zzt());
        }
    }

    private final void zzM(Object obj, int r7) {
        int zzz = zzz(r7);
        long j = 1048575 & zzz;
        if (j == 1048575) {
            return;
        }
        zzgrr.zzt(obj, j, (1 << (zzz >>> 20)) | zzgrr.zzd(obj, j));
    }

    private final void zzN(Object obj, int r4, int r5) {
        zzgrr.zzt(obj, zzz(r5) & 1048575, r4);
    }

    private final boolean zzO(Object obj, Object obj2, int r3) {
        return zzQ(obj, r3) == zzQ(obj2, r3);
    }

    private static boolean zzP(int r1) {
        return (r1 & 536870912) != 0;
    }

    private final boolean zzQ(Object obj, int r11) {
        int zzz = zzz(r11);
        long j = zzz & 1048575;
        if (j != 1048575) {
            return (zzgrr.zzd(obj, j) & (1 << (zzz >>> 20))) != 0;
        }
        int zzC = zzC(r11);
        long j2 = zzC & 1048575;
        switch (zzB(zzC)) {
            case 0:
                return Double.doubleToRawLongBits(zzgrr.zzb(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzgrr.zzc(obj, j2)) != 0;
            case 2:
                return zzgrr.zzf(obj, j2) != 0;
            case 3:
                return zzgrr.zzf(obj, j2) != 0;
            case 4:
                return zzgrr.zzd(obj, j2) != 0;
            case 5:
                return zzgrr.zzf(obj, j2) != 0;
            case 6:
                return zzgrr.zzd(obj, j2) != 0;
            case 7:
                return zzgrr.zzz(obj, j2);
            case 8:
                Object zzh = zzgrr.zzh(obj, j2);
                if (zzh instanceof String) {
                    return !((String) zzh).isEmpty();
                } else if (zzh instanceof zzgnf) {
                    return !zzgnf.zzb.equals(zzh);
                } else {
                    throw new IllegalArgumentException();
                }
            case 9:
                return zzgrr.zzh(obj, j2) != null;
            case 10:
                return !zzgnf.zzb.equals(zzgrr.zzh(obj, j2));
            case 11:
                return zzgrr.zzd(obj, j2) != 0;
            case 12:
                return zzgrr.zzd(obj, j2) != 0;
            case 13:
                return zzgrr.zzd(obj, j2) != 0;
            case 14:
                return zzgrr.zzf(obj, j2) != 0;
            case 15:
                return zzgrr.zzd(obj, j2) != 0;
            case 16:
                return zzgrr.zzf(obj, j2) != 0;
            case 17:
                return zzgrr.zzh(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzR(Object obj, int r3, int r4, int r5, int r6) {
        if (r4 == 1048575) {
            return zzQ(obj, r3);
        }
        return (r5 & r6) != 0;
    }

    private static boolean zzS(Object obj, int r3, zzgqq zzgqqVar) {
        return zzgqqVar.zzk(zzgrr.zzh(obj, r3 & 1048575));
    }

    private final boolean zzT(Object obj, int r4, int r5) {
        return zzgrr.zzd(obj, (long) (zzz(r5) & 1048575)) == r4;
    }

    private static boolean zzU(Object obj, long j) {
        return ((Boolean) zzgrr.zzh(obj, j)).booleanValue();
    }

    private final void zzV(Object obj, zzgnv zzgnvVar) throws IOException {
        int r11;
        if (this.zzh) {
            this.zzp.zza(obj);
            throw null;
        }
        int length = this.zzc.length;
        Unsafe unsafe = zzb;
        int r5 = 1048575;
        int r7 = 0;
        int r8 = 0;
        int r9 = 1048575;
        while (r7 < length) {
            int zzC = zzC(r7);
            int[] r112 = this.zzc;
            int r12 = r112[r7];
            int zzB = zzB(zzC);
            if (zzB <= 17) {
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
            long j = zzC & r5;
            switch (zzB) {
                case 0:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzf(r12, zzgrr.zzb(obj, j));
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 1:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzo(r12, zzgrr.zzc(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 2:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzt(r12, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 3:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzJ(r12, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 4:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzr(r12, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 5:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzm(r12, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 6:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzk(r12, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 7:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzb(r12, zzgrr.zzz(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 8:
                    if ((r8 & r11) != 0) {
                        zzX(r12, unsafe.getObject(obj, j), zzgnvVar);
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 9:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzv(r12, unsafe.getObject(obj, j), zzF(r7));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 10:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzd(r12, (zzgnf) unsafe.getObject(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 11:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzH(r12, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 12:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzi(r12, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 13:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzw(r12, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 14:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzy(r12, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 15:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzA(r12, unsafe.getInt(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 16:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzC(r12, unsafe.getLong(obj, j));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 17:
                    if ((r8 & r11) != 0) {
                        zzgnvVar.zzq(r12, unsafe.getObject(obj, j), zzF(r7));
                    } else {
                        continue;
                    }
                    r7 += 3;
                    r5 = 1048575;
                case 18:
                    zzgqs.zzL(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 19:
                    zzgqs.zzP(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 20:
                    zzgqs.zzS(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 21:
                    zzgqs.zzaa(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 22:
                    zzgqs.zzR(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 23:
                    zzgqs.zzO(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 24:
                    zzgqs.zzN(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 25:
                    zzgqs.zzJ(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    continue;
                    r7 += 3;
                    r5 = 1048575;
                case 26:
                    zzgqs.zzY(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar);
                    break;
                case 27:
                    zzgqs.zzT(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, zzF(r7));
                    break;
                case 28:
                    zzgqs.zzK(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar);
                    break;
                case 29:
                    zzgqs.zzZ(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    break;
                case 30:
                    zzgqs.zzM(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    break;
                case 31:
                    zzgqs.zzU(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    break;
                case 32:
                    zzgqs.zzV(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    break;
                case 33:
                    zzgqs.zzW(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    break;
                case 34:
                    zzgqs.zzX(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, false);
                    break;
                case 35:
                    zzgqs.zzL(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 36:
                    zzgqs.zzP(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 37:
                    zzgqs.zzS(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 38:
                    zzgqs.zzaa(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 39:
                    zzgqs.zzR(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 40:
                    zzgqs.zzO(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 41:
                    zzgqs.zzN(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 42:
                    zzgqs.zzJ(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 43:
                    zzgqs.zzZ(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 44:
                    zzgqs.zzM(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 45:
                    zzgqs.zzU(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 46:
                    zzgqs.zzV(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 47:
                    zzgqs.zzW(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 48:
                    zzgqs.zzX(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, true);
                    break;
                case 49:
                    zzgqs.zzQ(this.zzc[r7], (List) unsafe.getObject(obj, j), zzgnvVar, zzF(r7));
                    break;
                case 50:
                    zzW(zzgnvVar, r12, unsafe.getObject(obj, j), r7);
                    break;
                case 51:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzf(r12, zzo(obj, j));
                        break;
                    }
                    break;
                case 52:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzo(r12, zzp(obj, j));
                        break;
                    }
                    break;
                case 53:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzt(r12, zzD(obj, j));
                        break;
                    }
                    break;
                case 54:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzJ(r12, zzD(obj, j));
                        break;
                    }
                    break;
                case 55:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzr(r12, zzs(obj, j));
                        break;
                    }
                    break;
                case 56:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzm(r12, zzD(obj, j));
                        break;
                    }
                    break;
                case 57:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzk(r12, zzs(obj, j));
                        break;
                    }
                    break;
                case 58:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzb(r12, zzU(obj, j));
                        break;
                    }
                    break;
                case 59:
                    if (zzT(obj, r12, r7)) {
                        zzX(r12, unsafe.getObject(obj, j), zzgnvVar);
                        break;
                    }
                    break;
                case 60:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzv(r12, unsafe.getObject(obj, j), zzF(r7));
                        break;
                    }
                    break;
                case 61:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzd(r12, (zzgnf) unsafe.getObject(obj, j));
                        break;
                    }
                    break;
                case 62:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzH(r12, zzs(obj, j));
                        break;
                    }
                    break;
                case 63:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzi(r12, zzs(obj, j));
                        break;
                    }
                    break;
                case 64:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzw(r12, zzs(obj, j));
                        break;
                    }
                    break;
                case 65:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzy(r12, zzD(obj, j));
                        break;
                    }
                    break;
                case 66:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzA(r12, zzs(obj, j));
                        break;
                    }
                    break;
                case 67:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzC(r12, zzD(obj, j));
                        break;
                    }
                    break;
                case 68:
                    if (zzT(obj, r12, r7)) {
                        zzgnvVar.zzq(r12, unsafe.getObject(obj, j), zzF(r7));
                        break;
                    }
                    break;
            }
            r7 += 3;
            r5 = 1048575;
        }
        zzgrh zzgrhVar = this.zzo;
        zzgrhVar.zzr(zzgrhVar.zzd(obj), zzgnvVar);
    }

    private final void zzW(zzgnv zzgnvVar, int r2, Object obj, int r4) throws IOException {
        if (obj == null) {
            return;
        }
        zzgpq zzgpqVar = (zzgpq) zzH(r4);
        throw null;
    }

    private static final void zzX(int r1, Object obj, zzgnv zzgnvVar) throws IOException {
        if (obj instanceof String) {
            zzgnvVar.zzF(r1, (String) obj);
        } else {
            zzgnvVar.zzd(r1, (zzgnf) obj);
        }
    }

    static zzgri zzd(Object obj) {
        zzgon zzgonVar = (zzgon) obj;
        zzgri zzgriVar = zzgonVar.zzc;
        if (zzgriVar == zzgri.zzc()) {
            zzgri zze = zzgri.zze();
            zzgonVar.zzc = zze;
            return zze;
        }
        return zzgriVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgqa zzl(Class cls, zzgpu zzgpuVar, zzgqc zzgqcVar, zzgpl zzgplVar, zzgrh zzgrhVar, zzgoa zzgoaVar, zzgps zzgpsVar) {
        if (zzgpuVar instanceof zzgqh) {
            return zzm((zzgqh) zzgpuVar, zzgqcVar, zzgplVar, zzgrhVar, zzgoaVar, zzgpsVar);
        }
        zzgre zzgreVar = (zzgre) zzgpuVar;
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
    static com.google.android.gms.internal.ads.zzgqa zzm(com.google.android.gms.internal.ads.zzgqh r34, com.google.android.gms.internal.ads.zzgqc r35, com.google.android.gms.internal.ads.zzgpl r36, com.google.android.gms.internal.ads.zzgrh r37, com.google.android.gms.internal.ads.zzgoa r38, com.google.android.gms.internal.ads.zzgps r39) {
        /*
            Method dump skipped, instructions count: 1016
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgqa.zzm(com.google.android.gms.internal.ads.zzgqh, com.google.android.gms.internal.ads.zzgqc, com.google.android.gms.internal.ads.zzgpl, com.google.android.gms.internal.ads.zzgrh, com.google.android.gms.internal.ads.zzgoa, com.google.android.gms.internal.ads.zzgps):com.google.android.gms.internal.ads.zzgqa");
    }

    private static double zzo(Object obj, long j) {
        return ((Double) zzgrr.zzh(obj, j)).doubleValue();
    }

    private static float zzp(Object obj, long j) {
        return ((Float) zzgrr.zzh(obj, j)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final int zzq(Object obj) {
        int r10;
        int zzE;
        int zzE2;
        int zzE3;
        int zzF;
        int zzE4;
        int zzy;
        int zzE5;
        int zzE6;
        int zzd;
        int zzE7;
        int r4;
        int zzu;
        int zzi;
        int zzD;
        int zzE8;
        int r42;
        int zzE9;
        int zzE10;
        int zzE11;
        int zzF2;
        int zzE12;
        int zzd2;
        int zzE13;
        int r43;
        Unsafe unsafe = zzb;
        int r3 = 1048575;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 1048575;
        while (r5 < this.zzc.length) {
            int zzC = zzC(r5);
            int[] r102 = this.zzc;
            int r11 = r102[r5];
            int zzB = zzB(zzC);
            if (zzB <= 17) {
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
            long j = zzC & r3;
            switch (zzB) {
                case 0:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzE = zzgnu.zzE(r11 << 3);
                        zzE5 = zzE + 8;
                        r6 += zzE5;
                        break;
                    }
                case 1:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzE2 = zzgnu.zzE(r11 << 3);
                        zzE5 = zzE2 + 4;
                        r6 += zzE5;
                        break;
                    }
                case 2:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(obj, j);
                        zzE3 = zzgnu.zzE(r11 << 3);
                        zzF = zzgnu.zzF(j2);
                        r6 += zzE3 + zzF;
                        break;
                    }
                case 3:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        long j3 = unsafe.getLong(obj, j);
                        zzE3 = zzgnu.zzE(r11 << 3);
                        zzF = zzgnu.zzF(j3);
                        r6 += zzE3 + zzF;
                        break;
                    }
                case 4:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        int r32 = unsafe.getInt(obj, j);
                        zzE4 = zzgnu.zzE(r11 << 3);
                        zzy = zzgnu.zzy(r32);
                        r4 = zzE4 + zzy;
                        r6 += r4;
                        break;
                    }
                case 5:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzE = zzgnu.zzE(r11 << 3);
                        zzE5 = zzE + 8;
                        r6 += zzE5;
                        break;
                    }
                case 6:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzE2 = zzgnu.zzE(r11 << 3);
                        zzE5 = zzE2 + 4;
                        r6 += zzE5;
                        break;
                    }
                case 7:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzE5 = zzgnu.zzE(r11 << 3) + 1;
                        r6 += zzE5;
                        break;
                    }
                case 8:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzgnf) {
                            zzE6 = zzgnu.zzE(r11 << 3);
                            zzd = ((zzgnf) object).zzd();
                            zzE7 = zzgnu.zzE(zzd);
                            r4 = zzE6 + zzE7 + zzd;
                            r6 += r4;
                            break;
                        } else {
                            zzE4 = zzgnu.zzE(r11 << 3);
                            zzy = zzgnu.zzC((String) object);
                            r4 = zzE4 + zzy;
                            r6 += r4;
                        }
                    }
                case 9:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzE5 = zzgqs.zzo(r11, unsafe.getObject(obj, j), zzF(r5));
                        r6 += zzE5;
                        break;
                    }
                case 10:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzE6 = zzgnu.zzE(r11 << 3);
                        zzd = ((zzgnf) unsafe.getObject(obj, j)).zzd();
                        zzE7 = zzgnu.zzE(zzd);
                        r4 = zzE6 + zzE7 + zzd;
                        r6 += r4;
                        break;
                    }
                case 11:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        int r33 = unsafe.getInt(obj, j);
                        zzE4 = zzgnu.zzE(r11 << 3);
                        zzy = zzgnu.zzE(r33);
                        r4 = zzE4 + zzy;
                        r6 += r4;
                        break;
                    }
                case 12:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        int r34 = unsafe.getInt(obj, j);
                        zzE4 = zzgnu.zzE(r11 << 3);
                        zzy = zzgnu.zzy(r34);
                        r4 = zzE4 + zzy;
                        r6 += r4;
                        break;
                    }
                case 13:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzE2 = zzgnu.zzE(r11 << 3);
                        zzE5 = zzE2 + 4;
                        r6 += zzE5;
                        break;
                    }
                case 14:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzE = zzgnu.zzE(r11 << 3);
                        zzE5 = zzE + 8;
                        r6 += zzE5;
                        break;
                    }
                case 15:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        int r35 = unsafe.getInt(obj, j);
                        zzE4 = zzgnu.zzE(r11 << 3);
                        zzy = zzgnu.zzE((r35 >> 31) ^ (r35 + r35));
                        r4 = zzE4 + zzy;
                        r6 += r4;
                        break;
                    }
                case 16:
                    if ((r10 & r7) == 0) {
                        break;
                    } else {
                        long j4 = unsafe.getLong(obj, j);
                        r6 += zzgnu.zzE(r11 << 3) + zzgnu.zzF((j4 >> 63) ^ (j4 + j4));
                        break;
                    }
                case 17:
                    if ((r7 & r10) == 0) {
                        break;
                    } else {
                        zzE5 = zzgnu.zzx(r11, (zzgpx) unsafe.getObject(obj, j), zzF(r5));
                        r6 += zzE5;
                        break;
                    }
                case 18:
                    zzE5 = zzgqs.zzh(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzE5;
                    break;
                case 19:
                    zzE5 = zzgqs.zzf(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzE5;
                    break;
                case 20:
                    zzE5 = zzgqs.zzm(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzE5;
                    break;
                case 21:
                    zzE5 = zzgqs.zzx(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzE5;
                    break;
                case 22:
                    zzE5 = zzgqs.zzk(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzE5;
                    break;
                case 23:
                    zzE5 = zzgqs.zzh(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzE5;
                    break;
                case 24:
                    zzE5 = zzgqs.zzf(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzE5;
                    break;
                case 25:
                    zzE5 = zzgqs.zza(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzE5;
                    break;
                case 26:
                    zzu = zzgqs.zzu(r11, (List) unsafe.getObject(obj, j));
                    r6 += zzu;
                    break;
                case 27:
                    zzu = zzgqs.zzp(r11, (List) unsafe.getObject(obj, j), zzF(r5));
                    r6 += zzu;
                    break;
                case 28:
                    zzu = zzgqs.zzc(r11, (List) unsafe.getObject(obj, j));
                    r6 += zzu;
                    break;
                case 29:
                    zzu = zzgqs.zzv(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzu;
                    break;
                case 30:
                    zzu = zzgqs.zzd(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzu;
                    break;
                case 31:
                    zzu = zzgqs.zzf(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzu;
                    break;
                case 32:
                    zzu = zzgqs.zzh(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzu;
                    break;
                case 33:
                    zzu = zzgqs.zzq(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzu;
                    break;
                case 34:
                    zzu = zzgqs.zzs(r11, (List) unsafe.getObject(obj, j), false);
                    r6 += zzu;
                    break;
                case 35:
                    zzi = zzgqs.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 36:
                    zzi = zzgqs.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 37:
                    zzi = zzgqs.zzn((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 38:
                    zzi = zzgqs.zzy((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 39:
                    zzi = zzgqs.zzl((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 40:
                    zzi = zzgqs.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 41:
                    zzi = zzgqs.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 42:
                    zzi = zzgqs.zzb((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 43:
                    zzi = zzgqs.zzw((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 44:
                    zzi = zzgqs.zze((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 45:
                    zzi = zzgqs.zzg((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 46:
                    zzi = zzgqs.zzi((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 47:
                    zzi = zzgqs.zzr((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 48:
                    zzi = zzgqs.zzt((List) unsafe.getObject(obj, j));
                    if (zzi > 0) {
                        zzD = zzgnu.zzD(r11);
                        zzE8 = zzgnu.zzE(zzi);
                        r42 = zzD + zzE8;
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 49:
                    zzu = zzgqs.zzj(r11, (List) unsafe.getObject(obj, j), zzF(r5));
                    r6 += zzu;
                    break;
                case 50:
                    zzgps.zza(r11, unsafe.getObject(obj, j), zzH(r5));
                    break;
                case 51:
                    if (zzT(obj, r11, r5)) {
                        zzE9 = zzgnu.zzE(r11 << 3);
                        zzu = zzE9 + 8;
                        r6 += zzu;
                    }
                    break;
                case 52:
                    if (zzT(obj, r11, r5)) {
                        zzE10 = zzgnu.zzE(r11 << 3);
                        zzu = zzE10 + 4;
                        r6 += zzu;
                    }
                    break;
                case 53:
                    if (zzT(obj, r11, r5)) {
                        long zzD2 = zzD(obj, j);
                        zzE11 = zzgnu.zzE(r11 << 3);
                        zzF2 = zzgnu.zzF(zzD2);
                        r6 += zzE11 + zzF2;
                    }
                    break;
                case 54:
                    if (zzT(obj, r11, r5)) {
                        long zzD3 = zzD(obj, j);
                        zzE11 = zzgnu.zzE(r11 << 3);
                        zzF2 = zzgnu.zzF(zzD3);
                        r6 += zzE11 + zzF2;
                    }
                    break;
                case 55:
                    if (zzT(obj, r11, r5)) {
                        int zzs = zzs(obj, j);
                        r42 = zzgnu.zzE(r11 << 3);
                        zzi = zzgnu.zzy(zzs);
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 56:
                    if (zzT(obj, r11, r5)) {
                        zzE9 = zzgnu.zzE(r11 << 3);
                        zzu = zzE9 + 8;
                        r6 += zzu;
                    }
                    break;
                case 57:
                    if (zzT(obj, r11, r5)) {
                        zzE10 = zzgnu.zzE(r11 << 3);
                        zzu = zzE10 + 4;
                        r6 += zzu;
                    }
                    break;
                case 58:
                    if (zzT(obj, r11, r5)) {
                        zzu = zzgnu.zzE(r11 << 3) + 1;
                        r6 += zzu;
                    }
                    break;
                case 59:
                    if (zzT(obj, r11, r5)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzgnf) {
                            zzE12 = zzgnu.zzE(r11 << 3);
                            zzd2 = ((zzgnf) object2).zzd();
                            zzE13 = zzgnu.zzE(zzd2);
                            r43 = zzE12 + zzE13 + zzd2;
                            r6 += r43;
                        } else {
                            r42 = zzgnu.zzE(r11 << 3);
                            zzi = zzgnu.zzC((String) object2);
                            r43 = r42 + zzi;
                            r6 += r43;
                        }
                    }
                    break;
                case 60:
                    if (zzT(obj, r11, r5)) {
                        zzu = zzgqs.zzo(r11, unsafe.getObject(obj, j), zzF(r5));
                        r6 += zzu;
                    }
                    break;
                case 61:
                    if (zzT(obj, r11, r5)) {
                        zzE12 = zzgnu.zzE(r11 << 3);
                        zzd2 = ((zzgnf) unsafe.getObject(obj, j)).zzd();
                        zzE13 = zzgnu.zzE(zzd2);
                        r43 = zzE12 + zzE13 + zzd2;
                        r6 += r43;
                    }
                    break;
                case 62:
                    if (zzT(obj, r11, r5)) {
                        int zzs2 = zzs(obj, j);
                        r42 = zzgnu.zzE(r11 << 3);
                        zzi = zzgnu.zzE(zzs2);
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 63:
                    if (zzT(obj, r11, r5)) {
                        int zzs3 = zzs(obj, j);
                        r42 = zzgnu.zzE(r11 << 3);
                        zzi = zzgnu.zzy(zzs3);
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 64:
                    if (zzT(obj, r11, r5)) {
                        zzE10 = zzgnu.zzE(r11 << 3);
                        zzu = zzE10 + 4;
                        r6 += zzu;
                    }
                    break;
                case 65:
                    if (zzT(obj, r11, r5)) {
                        zzE9 = zzgnu.zzE(r11 << 3);
                        zzu = zzE9 + 8;
                        r6 += zzu;
                    }
                    break;
                case 66:
                    if (zzT(obj, r11, r5)) {
                        int zzs4 = zzs(obj, j);
                        r42 = zzgnu.zzE(r11 << 3);
                        zzi = zzgnu.zzE((zzs4 >> 31) ^ (zzs4 + zzs4));
                        r43 = r42 + zzi;
                        r6 += r43;
                    }
                    break;
                case 67:
                    if (zzT(obj, r11, r5)) {
                        long zzD4 = zzD(obj, j);
                        r6 += zzgnu.zzE(r11 << 3) + zzgnu.zzF((zzD4 >> 63) ^ (zzD4 + zzD4));
                    }
                    break;
                case 68:
                    if (zzT(obj, r11, r5)) {
                        zzu = zzgnu.zzx(r11, (zzgpx) unsafe.getObject(obj, j), zzF(r5));
                        r6 += zzu;
                    }
                    break;
            }
            r5 += 3;
            r3 = 1048575;
        }
        zzgrh zzgrhVar = this.zzo;
        int zza2 = r6 + zzgrhVar.zza(zzgrhVar.zzd(obj));
        if (this.zzh) {
            this.zzp.zza(obj);
            throw null;
        }
        return zza2;
    }

    private final int zzr(Object obj) {
        int zzE;
        int zzE2;
        int zzE3;
        int zzF;
        int zzE4;
        int zzy;
        int zzE5;
        int zzE6;
        int zzd;
        int zzE7;
        int zzo;
        int zzD;
        int zzE8;
        int r5;
        Unsafe unsafe = zzb;
        int r3 = 0;
        for (int r2 = 0; r2 < this.zzc.length; r2 += 3) {
            int zzC = zzC(r2);
            int zzB = zzB(zzC);
            int r6 = this.zzc[r2];
            long j = zzC & 1048575;
            if (zzB >= zzgof.DOUBLE_LIST_PACKED.zza() && zzB <= zzgof.SINT64_LIST_PACKED.zza()) {
                int r4 = this.zzc[r2 + 2];
            }
            switch (zzB) {
                case 0:
                    if (zzQ(obj, r2)) {
                        zzE = zzgnu.zzE(r6 << 3);
                        zzo = zzE + 8;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzQ(obj, r2)) {
                        zzE2 = zzgnu.zzE(r6 << 3);
                        zzo = zzE2 + 4;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzQ(obj, r2)) {
                        long zzf = zzgrr.zzf(obj, j);
                        zzE3 = zzgnu.zzE(r6 << 3);
                        zzF = zzgnu.zzF(zzf);
                        r3 += zzE3 + zzF;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzQ(obj, r2)) {
                        long zzf2 = zzgrr.zzf(obj, j);
                        zzE3 = zzgnu.zzE(r6 << 3);
                        zzF = zzgnu.zzF(zzf2);
                        r3 += zzE3 + zzF;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzQ(obj, r2)) {
                        int zzd2 = zzgrr.zzd(obj, j);
                        zzE4 = zzgnu.zzE(r6 << 3);
                        zzy = zzgnu.zzy(zzd2);
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzQ(obj, r2)) {
                        zzE = zzgnu.zzE(r6 << 3);
                        zzo = zzE + 8;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzQ(obj, r2)) {
                        zzE2 = zzgnu.zzE(r6 << 3);
                        zzo = zzE2 + 4;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzQ(obj, r2)) {
                        zzE5 = zzgnu.zzE(r6 << 3);
                        zzo = zzE5 + 1;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (!zzQ(obj, r2)) {
                        break;
                    } else {
                        Object zzh = zzgrr.zzh(obj, j);
                        if (zzh instanceof zzgnf) {
                            zzE6 = zzgnu.zzE(r6 << 3);
                            zzd = ((zzgnf) zzh).zzd();
                            zzE7 = zzgnu.zzE(zzd);
                            r5 = zzE6 + zzE7 + zzd;
                            r3 += r5;
                            break;
                        } else {
                            zzE4 = zzgnu.zzE(r6 << 3);
                            zzy = zzgnu.zzC((String) zzh);
                            r5 = zzE4 + zzy;
                            r3 += r5;
                        }
                    }
                case 9:
                    if (zzQ(obj, r2)) {
                        zzo = zzgqs.zzo(r6, zzgrr.zzh(obj, j), zzF(r2));
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (zzQ(obj, r2)) {
                        zzE6 = zzgnu.zzE(r6 << 3);
                        zzd = ((zzgnf) zzgrr.zzh(obj, j)).zzd();
                        zzE7 = zzgnu.zzE(zzd);
                        r5 = zzE6 + zzE7 + zzd;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzQ(obj, r2)) {
                        int zzd3 = zzgrr.zzd(obj, j);
                        zzE4 = zzgnu.zzE(r6 << 3);
                        zzy = zzgnu.zzE(zzd3);
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzQ(obj, r2)) {
                        int zzd4 = zzgrr.zzd(obj, j);
                        zzE4 = zzgnu.zzE(r6 << 3);
                        zzy = zzgnu.zzy(zzd4);
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzQ(obj, r2)) {
                        zzE2 = zzgnu.zzE(r6 << 3);
                        zzo = zzE2 + 4;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzQ(obj, r2)) {
                        zzE = zzgnu.zzE(r6 << 3);
                        zzo = zzE + 8;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzQ(obj, r2)) {
                        int zzd5 = zzgrr.zzd(obj, j);
                        zzE4 = zzgnu.zzE(r6 << 3);
                        zzy = zzgnu.zzE((zzd5 >> 31) ^ (zzd5 + zzd5));
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzQ(obj, r2)) {
                        long zzf3 = zzgrr.zzf(obj, j);
                        zzE4 = zzgnu.zzE(r6 << 3);
                        zzy = zzgnu.zzF((zzf3 >> 63) ^ (zzf3 + zzf3));
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (zzQ(obj, r2)) {
                        zzo = zzgnu.zzx(r6, (zzgpx) zzgrr.zzh(obj, j), zzF(r2));
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    zzo = zzgqs.zzh(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 19:
                    zzo = zzgqs.zzf(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 20:
                    zzo = zzgqs.zzm(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 21:
                    zzo = zzgqs.zzx(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 22:
                    zzo = zzgqs.zzk(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 23:
                    zzo = zzgqs.zzh(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 24:
                    zzo = zzgqs.zzf(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 25:
                    zzo = zzgqs.zza(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 26:
                    zzo = zzgqs.zzu(r6, (List) zzgrr.zzh(obj, j));
                    r3 += zzo;
                    break;
                case 27:
                    zzo = zzgqs.zzp(r6, (List) zzgrr.zzh(obj, j), zzF(r2));
                    r3 += zzo;
                    break;
                case 28:
                    zzo = zzgqs.zzc(r6, (List) zzgrr.zzh(obj, j));
                    r3 += zzo;
                    break;
                case 29:
                    zzo = zzgqs.zzv(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 30:
                    zzo = zzgqs.zzd(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 31:
                    zzo = zzgqs.zzf(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 32:
                    zzo = zzgqs.zzh(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 33:
                    zzo = zzgqs.zzq(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 34:
                    zzo = zzgqs.zzs(r6, (List) zzgrr.zzh(obj, j), false);
                    r3 += zzo;
                    break;
                case 35:
                    zzy = zzgqs.zzi((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    zzy = zzgqs.zzg((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    zzy = zzgqs.zzn((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    zzy = zzgqs.zzy((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    zzy = zzgqs.zzl((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    zzy = zzgqs.zzi((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    zzy = zzgqs.zzg((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    zzy = zzgqs.zzb((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    zzy = zzgqs.zzw((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    zzy = zzgqs.zze((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    zzy = zzgqs.zzg((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    zzy = zzgqs.zzi((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    zzy = zzgqs.zzr((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    zzy = zzgqs.zzt((List) unsafe.getObject(obj, j));
                    if (zzy > 0) {
                        zzD = zzgnu.zzD(r6);
                        zzE8 = zzgnu.zzE(zzy);
                        zzE4 = zzD + zzE8;
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    zzo = zzgqs.zzj(r6, (List) zzgrr.zzh(obj, j), zzF(r2));
                    r3 += zzo;
                    break;
                case 50:
                    zzgps.zza(r6, zzgrr.zzh(obj, j), zzH(r2));
                    break;
                case 51:
                    if (zzT(obj, r6, r2)) {
                        zzE = zzgnu.zzE(r6 << 3);
                        zzo = zzE + 8;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzT(obj, r6, r2)) {
                        zzE2 = zzgnu.zzE(r6 << 3);
                        zzo = zzE2 + 4;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzT(obj, r6, r2)) {
                        long zzD2 = zzD(obj, j);
                        zzE3 = zzgnu.zzE(r6 << 3);
                        zzF = zzgnu.zzF(zzD2);
                        r3 += zzE3 + zzF;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzT(obj, r6, r2)) {
                        long zzD3 = zzD(obj, j);
                        zzE3 = zzgnu.zzE(r6 << 3);
                        zzF = zzgnu.zzF(zzD3);
                        r3 += zzE3 + zzF;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzT(obj, r6, r2)) {
                        int zzs = zzs(obj, j);
                        zzE4 = zzgnu.zzE(r6 << 3);
                        zzy = zzgnu.zzy(zzs);
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzT(obj, r6, r2)) {
                        zzE = zzgnu.zzE(r6 << 3);
                        zzo = zzE + 8;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzT(obj, r6, r2)) {
                        zzE2 = zzgnu.zzE(r6 << 3);
                        zzo = zzE2 + 4;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzT(obj, r6, r2)) {
                        zzE5 = zzgnu.zzE(r6 << 3);
                        zzo = zzE5 + 1;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (!zzT(obj, r6, r2)) {
                        break;
                    } else {
                        Object zzh2 = zzgrr.zzh(obj, j);
                        if (zzh2 instanceof zzgnf) {
                            zzE6 = zzgnu.zzE(r6 << 3);
                            zzd = ((zzgnf) zzh2).zzd();
                            zzE7 = zzgnu.zzE(zzd);
                            r5 = zzE6 + zzE7 + zzd;
                            r3 += r5;
                            break;
                        } else {
                            zzE4 = zzgnu.zzE(r6 << 3);
                            zzy = zzgnu.zzC((String) zzh2);
                            r5 = zzE4 + zzy;
                            r3 += r5;
                        }
                    }
                case 60:
                    if (zzT(obj, r6, r2)) {
                        zzo = zzgqs.zzo(r6, zzgrr.zzh(obj, j), zzF(r2));
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzT(obj, r6, r2)) {
                        zzE6 = zzgnu.zzE(r6 << 3);
                        zzd = ((zzgnf) zzgrr.zzh(obj, j)).zzd();
                        zzE7 = zzgnu.zzE(zzd);
                        r5 = zzE6 + zzE7 + zzd;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzT(obj, r6, r2)) {
                        int zzs2 = zzs(obj, j);
                        zzE4 = zzgnu.zzE(r6 << 3);
                        zzy = zzgnu.zzE(zzs2);
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzT(obj, r6, r2)) {
                        int zzs3 = zzs(obj, j);
                        zzE4 = zzgnu.zzE(r6 << 3);
                        zzy = zzgnu.zzy(zzs3);
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzT(obj, r6, r2)) {
                        zzE2 = zzgnu.zzE(r6 << 3);
                        zzo = zzE2 + 4;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzT(obj, r6, r2)) {
                        zzE = zzgnu.zzE(r6 << 3);
                        zzo = zzE + 8;
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzT(obj, r6, r2)) {
                        int zzs4 = zzs(obj, j);
                        zzE4 = zzgnu.zzE(r6 << 3);
                        zzy = zzgnu.zzE((zzs4 >> 31) ^ (zzs4 + zzs4));
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzT(obj, r6, r2)) {
                        long zzD4 = zzD(obj, j);
                        zzE4 = zzgnu.zzE(r6 << 3);
                        zzy = zzgnu.zzF((zzD4 >> 63) ^ (zzD4 + zzD4));
                        r5 = zzE4 + zzy;
                        r3 += r5;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzT(obj, r6, r2)) {
                        zzo = zzgnu.zzx(r6, (zzgpx) zzgrr.zzh(obj, j), zzF(r2));
                        r3 += zzo;
                        break;
                    } else {
                        break;
                    }
            }
        }
        zzgrh zzgrhVar = this.zzo;
        return r3 + zzgrhVar.zza(zzgrhVar.zzd(obj));
    }

    private static int zzs(Object obj, long j) {
        return ((Integer) zzgrr.zzh(obj, j)).intValue();
    }

    private final int zzt(Object obj, byte[] bArr, int r3, int r4, int r5, long j, zzgmr zzgmrVar) throws IOException {
        Unsafe unsafe = zzb;
        Object zzH = zzH(r5);
        Object object = unsafe.getObject(obj, j);
        if (zzgps.zzb(object)) {
            zzgpr zzb2 = zzgpr.zza().zzb();
            zzgps.zzc(zzb2, object);
            unsafe.putObject(obj, j, zzb2);
        }
        zzgpq zzgpqVar = (zzgpq) zzH;
        throw null;
    }

    private final int zzu(Object obj, byte[] bArr, int r19, int r20, int r21, int r22, int r23, int r24, int r25, long j, int r28, zzgmr zzgmrVar) throws IOException {
        Unsafe unsafe = zzb;
        long j2 = this.zzc[r28 + 2] & 1048575;
        switch (r25) {
            case 51:
                if (r23 == 1) {
                    unsafe.putObject(obj, j, Double.valueOf(Double.longBitsToDouble(zzgms.zzn(bArr, r19))));
                    unsafe.putInt(obj, j2, r22);
                    return r19 + 8;
                }
                break;
            case 52:
                if (r23 == 5) {
                    unsafe.putObject(obj, j, Float.valueOf(Float.intBitsToFloat(zzgms.zzb(bArr, r19))));
                    unsafe.putInt(obj, j2, r22);
                    return r19 + 4;
                }
                break;
            case 53:
            case 54:
                if (r23 == 0) {
                    int zzm = zzgms.zzm(bArr, r19, zzgmrVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzgmrVar.zzb));
                    unsafe.putInt(obj, j2, r22);
                    return zzm;
                }
                break;
            case 55:
            case 62:
                if (r23 == 0) {
                    int zzj = zzgms.zzj(bArr, r19, zzgmrVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzgmrVar.zza));
                    unsafe.putInt(obj, j2, r22);
                    return zzj;
                }
                break;
            case 56:
            case 65:
                if (r23 == 1) {
                    unsafe.putObject(obj, j, Long.valueOf(zzgms.zzn(bArr, r19)));
                    unsafe.putInt(obj, j2, r22);
                    return r19 + 8;
                }
                break;
            case 57:
            case 64:
                if (r23 == 5) {
                    unsafe.putObject(obj, j, Integer.valueOf(zzgms.zzb(bArr, r19)));
                    unsafe.putInt(obj, j2, r22);
                    return r19 + 4;
                }
                break;
            case 58:
                if (r23 == 0) {
                    int zzm2 = zzgms.zzm(bArr, r19, zzgmrVar);
                    unsafe.putObject(obj, j, Boolean.valueOf(zzgmrVar.zzb != 0));
                    unsafe.putInt(obj, j2, r22);
                    return zzm2;
                }
                break;
            case 59:
                if (r23 == 2) {
                    int zzj2 = zzgms.zzj(bArr, r19, zzgmrVar);
                    int r4 = zzgmrVar.zza;
                    if (r4 == 0) {
                        unsafe.putObject(obj, j, "");
                    } else if ((r24 & 536870912) == 0 || zzgrw.zzj(bArr, zzj2, zzj2 + r4)) {
                        unsafe.putObject(obj, j, new String(bArr, zzj2, r4, zzgox.zzb));
                        zzj2 += r4;
                    } else {
                        throw zzgoz.zzd();
                    }
                    unsafe.putInt(obj, j2, r22);
                    return zzj2;
                }
                break;
            case 60:
                if (r23 == 2) {
                    int zzd = zzgms.zzd(zzF(r28), bArr, r19, r20, zzgmrVar);
                    Object object = unsafe.getInt(obj, j2) == r22 ? unsafe.getObject(obj, j) : null;
                    if (object == null) {
                        unsafe.putObject(obj, j, zzgmrVar.zzc);
                    } else {
                        unsafe.putObject(obj, j, zzgox.zzg(object, zzgmrVar.zzc));
                    }
                    unsafe.putInt(obj, j2, r22);
                    return zzd;
                }
                break;
            case 61:
                if (r23 == 2) {
                    int zza2 = zzgms.zza(bArr, r19, zzgmrVar);
                    unsafe.putObject(obj, j, zzgmrVar.zzc);
                    unsafe.putInt(obj, j2, r22);
                    return zza2;
                }
                break;
            case 63:
                if (r23 == 0) {
                    int zzj3 = zzgms.zzj(bArr, r19, zzgmrVar);
                    int r42 = zzgmrVar.zza;
                    zzgor zzE = zzE(r28);
                    if (zzE == null || zzE.zza(r42)) {
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
                    int zzj4 = zzgms.zzj(bArr, r19, zzgmrVar);
                    unsafe.putObject(obj, j, Integer.valueOf(zzgnn.zzF(zzgmrVar.zza)));
                    unsafe.putInt(obj, j2, r22);
                    return zzj4;
                }
                break;
            case 67:
                if (r23 == 0) {
                    int zzm3 = zzgms.zzm(bArr, r19, zzgmrVar);
                    unsafe.putObject(obj, j, Long.valueOf(zzgnn.zzG(zzgmrVar.zzb)));
                    unsafe.putInt(obj, j2, r22);
                    return zzm3;
                }
                break;
            case 68:
                if (r23 == 3) {
                    int zzc = zzgms.zzc(zzF(r28), bArr, r19, r20, (r21 & (-8)) | 4, zzgmrVar);
                    Object object2 = unsafe.getInt(obj, j2) == r22 ? unsafe.getObject(obj, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(obj, j, zzgmrVar.zzc);
                    } else {
                        unsafe.putObject(obj, j, zzgox.zzg(object2, zzgmrVar.zzc));
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
    private final int zzv(java.lang.Object r31, byte[] r32, int r33, int r34, com.google.android.gms.internal.ads.zzgmr r35) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 958
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgqa.zzv(java.lang.Object, byte[], int, int, com.google.android.gms.internal.ads.zzgmr):int");
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
    private final int zzw(java.lang.Object r16, byte[] r17, int r18, int r19, int r20, int r21, int r22, int r23, long r24, int r26, long r27, com.google.android.gms.internal.ads.zzgmr r29) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1172
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgqa.zzw(java.lang.Object, byte[], int, int, int, int, int, int, long, int, long, com.google.android.gms.internal.ads.zzgmr):int");
    }

    private final int zzx(int r2) {
        if (r2 < this.zze || r2 > this.zzf) {
            return -1;
        }
        return zzA(r2, 0);
    }

    private final int zzy(int r2, int r3) {
        if (r2 < this.zze || r2 > this.zzf) {
            return -1;
        }
        return zzA(r2, r3);
    }

    private final int zzz(int r2) {
        return this.zzc[r2 + 2];
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final int zza(Object obj) {
        return this.zzj ? zzr(obj) : zzq(obj);
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final int zzb(Object obj) {
        int r2;
        int zzc;
        int length = this.zzc.length;
        int r22 = 0;
        for (int r1 = 0; r1 < length; r1 += 3) {
            int zzC = zzC(r1);
            int r4 = this.zzc[r1];
            long j = 1048575 & zzC;
            int r7 = 37;
            switch (zzB(zzC)) {
                case 0:
                    r2 = r22 * 53;
                    zzc = zzgox.zzc(Double.doubleToLongBits(zzgrr.zzb(obj, j)));
                    r22 = r2 + zzc;
                    break;
                case 1:
                    r2 = r22 * 53;
                    zzc = Float.floatToIntBits(zzgrr.zzc(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 2:
                    r2 = r22 * 53;
                    zzc = zzgox.zzc(zzgrr.zzf(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 3:
                    r2 = r22 * 53;
                    zzc = zzgox.zzc(zzgrr.zzf(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 4:
                    r2 = r22 * 53;
                    zzc = zzgrr.zzd(obj, j);
                    r22 = r2 + zzc;
                    break;
                case 5:
                    r2 = r22 * 53;
                    zzc = zzgox.zzc(zzgrr.zzf(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 6:
                    r2 = r22 * 53;
                    zzc = zzgrr.zzd(obj, j);
                    r22 = r2 + zzc;
                    break;
                case 7:
                    r2 = r22 * 53;
                    zzc = zzgox.zza(zzgrr.zzz(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 8:
                    r2 = r22 * 53;
                    zzc = ((String) zzgrr.zzh(obj, j)).hashCode();
                    r22 = r2 + zzc;
                    break;
                case 9:
                    Object zzh = zzgrr.zzh(obj, j);
                    if (zzh != null) {
                        r7 = zzh.hashCode();
                    }
                    r22 = (r22 * 53) + r7;
                    break;
                case 10:
                    r2 = r22 * 53;
                    zzc = zzgrr.zzh(obj, j).hashCode();
                    r22 = r2 + zzc;
                    break;
                case 11:
                    r2 = r22 * 53;
                    zzc = zzgrr.zzd(obj, j);
                    r22 = r2 + zzc;
                    break;
                case 12:
                    r2 = r22 * 53;
                    zzc = zzgrr.zzd(obj, j);
                    r22 = r2 + zzc;
                    break;
                case 13:
                    r2 = r22 * 53;
                    zzc = zzgrr.zzd(obj, j);
                    r22 = r2 + zzc;
                    break;
                case 14:
                    r2 = r22 * 53;
                    zzc = zzgox.zzc(zzgrr.zzf(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 15:
                    r2 = r22 * 53;
                    zzc = zzgrr.zzd(obj, j);
                    r22 = r2 + zzc;
                    break;
                case 16:
                    r2 = r22 * 53;
                    zzc = zzgox.zzc(zzgrr.zzf(obj, j));
                    r22 = r2 + zzc;
                    break;
                case 17:
                    Object zzh2 = zzgrr.zzh(obj, j);
                    if (zzh2 != null) {
                        r7 = zzh2.hashCode();
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
                    zzc = zzgrr.zzh(obj, j).hashCode();
                    r22 = r2 + zzc;
                    break;
                case 50:
                    r2 = r22 * 53;
                    zzc = zzgrr.zzh(obj, j).hashCode();
                    r22 = r2 + zzc;
                    break;
                case 51:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzgox.zzc(Double.doubleToLongBits(zzo(obj, j)));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = Float.floatToIntBits(zzp(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzgox.zzc(zzD(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzgox.zzc(zzD(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzs(obj, j);
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzgox.zzc(zzD(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzs(obj, j);
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzgox.zza(zzU(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = ((String) zzgrr.zzh(obj, j)).hashCode();
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzgrr.zzh(obj, j).hashCode();
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzgrr.zzh(obj, j).hashCode();
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzs(obj, j);
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzs(obj, j);
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzs(obj, j);
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzgox.zzc(zzD(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzs(obj, j);
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzgox.zzc(zzD(obj, j));
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzT(obj, r4, r1)) {
                        r2 = r22 * 53;
                        zzc = zzgrr.zzh(obj, j).hashCode();
                        r22 = r2 + zzc;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (r22 * 53) + this.zzo.zzd(obj).hashCode();
        if (this.zzh) {
            this.zzp.zza(obj);
            throw null;
        }
        return hashCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zzc(Object obj, byte[] bArr, int r31, int r32, int r33, zzgmr zzgmrVar) throws IOException {
        Unsafe unsafe;
        int r7;
        Object obj2;
        zzgqa<T> zzgqaVar;
        Object obj3;
        int r3;
        int r1;
        int r0;
        int zzx;
        int r2;
        int r8;
        int r20;
        int r23;
        int r18;
        Object obj4;
        zzgmr zzgmrVar2;
        int r11;
        int r17;
        int r25;
        int r112;
        int r4;
        int r19;
        int r72;
        int zzm;
        int r12;
        int r252;
        int r15;
        int r312;
        zzgqa<T> zzgqaVar2 = this;
        Object obj5 = obj;
        byte[] bArr2 = bArr;
        int r13 = r32;
        int r113 = r33;
        zzgmr zzgmrVar3 = zzgmrVar;
        Unsafe unsafe2 = zzb;
        int r02 = r31;
        int r14 = 0;
        int r22 = -1;
        int r34 = 0;
        int r5 = 0;
        int r6 = 1048575;
        while (true) {
            if (r02 < r13) {
                int r16 = r02 + 1;
                byte b = bArr2[r02];
                if (b < 0) {
                    r0 = zzgms.zzk(b, bArr2, r16, zzgmrVar3);
                    r1 = zzgmrVar3.zza;
                } else {
                    r1 = b;
                    r0 = r16;
                }
                int r73 = r1 >>> 3;
                int r82 = r1 & 7;
                if (r73 > r22) {
                    zzx = zzgqaVar2.zzy(r73, r34 / 3);
                } else {
                    zzx = zzgqaVar2.zzx(r73);
                }
                if (zzx == -1) {
                    r2 = r0;
                    r8 = r1;
                    r20 = r5;
                    r23 = r73;
                    unsafe = unsafe2;
                    r7 = r113;
                    r18 = 0;
                } else {
                    int[] r35 = zzgqaVar2.zzc;
                    int r42 = r35[zzx + 1];
                    int zzB = zzB(r42);
                    int r192 = r1;
                    int r21 = r0;
                    long j = r42 & 1048575;
                    if (zzB <= 17) {
                        int r03 = r35[zzx + 2];
                        int r24 = 1 << (r03 >>> 20);
                        int r04 = r03 & 1048575;
                        if (r04 != r6) {
                            r17 = zzx;
                            if (r6 != 1048575) {
                                unsafe2.putInt(obj5, r6, r5);
                            }
                            r5 = unsafe2.getInt(obj5, r04);
                            r25 = r04;
                        } else {
                            r17 = zzx;
                            r25 = r6;
                        }
                        int r62 = r5;
                        switch (zzB) {
                            case 0:
                                r112 = r192;
                                r4 = r21;
                                r19 = r73;
                                r72 = r17;
                                if (r82 != 1) {
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    zzgrr.zzr(obj5, j, Double.longBitsToDouble(zzgms.zzn(bArr2, r4)));
                                    r02 = r4 + 8;
                                    r5 = r62 | r24;
                                    r34 = r72;
                                    r14 = r112;
                                    r22 = r19;
                                    r6 = r25;
                                    r113 = r33;
                                }
                            case 1:
                                r112 = r192;
                                r4 = r21;
                                r19 = r73;
                                r72 = r17;
                                if (r82 != 5) {
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    zzgrr.zzs(obj5, j, Float.intBitsToFloat(zzgms.zzb(bArr2, r4)));
                                    r02 = r4 + 4;
                                    r5 = r62 | r24;
                                    r34 = r72;
                                    r14 = r112;
                                    r22 = r19;
                                    r6 = r25;
                                    r113 = r33;
                                }
                            case 2:
                            case 3:
                                r112 = r192;
                                r4 = r21;
                                r19 = r73;
                                r72 = r17;
                                if (r82 != 0) {
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    zzm = zzgms.zzm(bArr2, r4, zzgmrVar3);
                                    unsafe2.putLong(obj, j, zzgmrVar3.zzb);
                                    r5 = r62 | r24;
                                    r34 = r72;
                                    r02 = zzm;
                                    r14 = r112;
                                    r22 = r19;
                                    r6 = r25;
                                    r113 = r33;
                                }
                            case 4:
                            case 11:
                                r112 = r192;
                                r4 = r21;
                                r19 = r73;
                                r72 = r17;
                                if (r82 != 0) {
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    r02 = zzgms.zzj(bArr2, r4, zzgmrVar3);
                                    unsafe2.putInt(obj5, j, zzgmrVar3.zza);
                                    r5 = r62 | r24;
                                    r34 = r72;
                                    r14 = r112;
                                    r22 = r19;
                                    r6 = r25;
                                    r113 = r33;
                                }
                            case 5:
                            case 14:
                                r112 = r192;
                                r12 = r21;
                                r19 = r73;
                                r72 = r17;
                                if (r82 != 1) {
                                    r4 = r12;
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    unsafe2.putLong(obj, j, zzgms.zzn(bArr2, r12));
                                    r02 = r12 + 8;
                                    r5 = r62 | r24;
                                    r34 = r72;
                                    r14 = r112;
                                    r22 = r19;
                                    r6 = r25;
                                    r113 = r33;
                                }
                            case 6:
                            case 13:
                                r112 = r192;
                                r12 = r21;
                                r19 = r73;
                                r72 = r17;
                                if (r82 != 5) {
                                    r4 = r12;
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    unsafe2.putInt(obj5, j, zzgms.zzb(bArr2, r12));
                                    r02 = r12 + 4;
                                    r5 = r62 | r24;
                                    r34 = r72;
                                    r14 = r112;
                                    r22 = r19;
                                    r6 = r25;
                                    r113 = r33;
                                }
                            case 7:
                                r112 = r192;
                                r12 = r21;
                                r19 = r73;
                                r72 = r17;
                                if (r82 != 0) {
                                    r4 = r12;
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    r02 = zzgms.zzm(bArr2, r12, zzgmrVar3);
                                    zzgrr.zzp(obj5, j, zzgmrVar3.zzb != 0);
                                    r5 = r62 | r24;
                                    r34 = r72;
                                    r14 = r112;
                                    r22 = r19;
                                    r6 = r25;
                                    r113 = r33;
                                }
                            case 8:
                                r112 = r192;
                                r12 = r21;
                                r19 = r73;
                                r72 = r17;
                                if (r82 != 2) {
                                    r4 = r12;
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    if ((536870912 & r42) == 0) {
                                        r02 = zzgms.zzg(bArr2, r12, zzgmrVar3);
                                    } else {
                                        r02 = zzgms.zzh(bArr2, r12, zzgmrVar3);
                                    }
                                    unsafe2.putObject(obj5, j, zzgmrVar3.zzc);
                                    r5 = r62 | r24;
                                    r34 = r72;
                                    r14 = r112;
                                    r22 = r19;
                                    r6 = r25;
                                    r113 = r33;
                                }
                            case 9:
                                r112 = r192;
                                r12 = r21;
                                r19 = r73;
                                r72 = r17;
                                if (r82 != 2) {
                                    r4 = r12;
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    r02 = zzgms.zzd(zzgqaVar2.zzF(r72), bArr2, r12, r13, zzgmrVar3);
                                    if ((r62 & r24) == 0) {
                                        unsafe2.putObject(obj5, j, zzgmrVar3.zzc);
                                    } else {
                                        unsafe2.putObject(obj5, j, zzgox.zzg(unsafe2.getObject(obj5, j), zzgmrVar3.zzc));
                                    }
                                    r5 = r62 | r24;
                                    r34 = r72;
                                    r14 = r112;
                                    r22 = r19;
                                    r6 = r25;
                                    r113 = r33;
                                }
                            case 10:
                                r112 = r192;
                                r12 = r21;
                                r19 = r73;
                                r72 = r17;
                                if (r82 != 2) {
                                    r4 = r12;
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    r02 = zzgms.zza(bArr2, r12, zzgmrVar3);
                                    unsafe2.putObject(obj5, j, zzgmrVar3.zzc);
                                    r5 = r62 | r24;
                                    r34 = r72;
                                    r14 = r112;
                                    r22 = r19;
                                    r6 = r25;
                                    r113 = r33;
                                }
                            case 12:
                                r112 = r192;
                                r12 = r21;
                                r19 = r73;
                                r72 = r17;
                                if (r82 != 0) {
                                    r4 = r12;
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    r02 = zzgms.zzj(bArr2, r12, zzgmrVar3);
                                    int r110 = zzgmrVar3.zza;
                                    zzgor zzE = zzgqaVar2.zzE(r72);
                                    if (zzE == null || zzE.zza(r110)) {
                                        unsafe2.putInt(obj5, j, r110);
                                        r5 = r62 | r24;
                                        r34 = r72;
                                        r14 = r112;
                                        r22 = r19;
                                        r6 = r25;
                                        r113 = r33;
                                    } else {
                                        zzd(obj).zzh(r112, Long.valueOf(r110));
                                        r5 = r62;
                                        r34 = r72;
                                        r14 = r112;
                                        r22 = r19;
                                        r6 = r25;
                                        r113 = r33;
                                    }
                                }
                                break;
                            case 15:
                                r112 = r192;
                                r12 = r21;
                                r19 = r73;
                                r72 = r17;
                                if (r82 != 0) {
                                    r4 = r12;
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    r02 = zzgms.zzj(bArr2, r12, zzgmrVar3);
                                    unsafe2.putInt(obj5, j, zzgnn.zzF(zzgmrVar3.zza));
                                    r5 = r62 | r24;
                                    r34 = r72;
                                    r14 = r112;
                                    r22 = r19;
                                    r6 = r25;
                                    r113 = r33;
                                }
                            case 16:
                                if (r82 != 0) {
                                    r112 = r192;
                                    r19 = r73;
                                    r72 = r17;
                                    r4 = r21;
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    zzm = zzgms.zzm(bArr2, r21, zzgmrVar3);
                                    r112 = r192;
                                    r19 = r73;
                                    r72 = r17;
                                    unsafe2.putLong(obj, j, zzgnn.zzG(zzgmrVar3.zzb));
                                    r5 = r62 | r24;
                                    r34 = r72;
                                    r02 = zzm;
                                    r14 = r112;
                                    r22 = r19;
                                    r6 = r25;
                                    r113 = r33;
                                }
                            default:
                                r112 = r192;
                                r4 = r21;
                                r19 = r73;
                                r72 = r17;
                                if (r82 != 3) {
                                    r2 = r4;
                                    r20 = r62;
                                    r18 = r72;
                                    unsafe = unsafe2;
                                    r8 = r112;
                                    r23 = r19;
                                    r6 = r25;
                                    r7 = r33;
                                    break;
                                } else {
                                    r02 = zzgms.zzc(zzgqaVar2.zzF(r72), bArr, r4, r32, (r19 << 3) | 4, zzgmrVar);
                                    if ((r62 & r24) == 0) {
                                        unsafe2.putObject(obj5, j, zzgmrVar3.zzc);
                                    } else {
                                        unsafe2.putObject(obj5, j, zzgox.zzg(unsafe2.getObject(obj5, j), zzgmrVar3.zzc));
                                    }
                                    r5 = r62 | r24;
                                    bArr2 = bArr;
                                    r13 = r32;
                                    r34 = r72;
                                    r14 = r112;
                                    r22 = r19;
                                    r6 = r25;
                                    r113 = r33;
                                }
                        }
                    } else {
                        int r74 = zzx;
                        if (zzB != 27) {
                            r20 = r5;
                            r252 = r6;
                            if (zzB <= 49) {
                                r312 = r192;
                                r18 = r74;
                                r23 = r73;
                                unsafe = unsafe2;
                                r02 = zzw(obj, bArr, r21, r32, r192, r73, r82, r18, r42, zzB, j, zzgmrVar);
                                if (r02 != r21) {
                                    zzgqaVar2 = this;
                                    obj5 = obj;
                                    bArr2 = bArr;
                                    r14 = r312;
                                    r13 = r32;
                                    r113 = r33;
                                    zzgmrVar3 = zzgmrVar;
                                    r34 = r18;
                                    r5 = r20;
                                    r22 = r23;
                                    r6 = r252;
                                    unsafe2 = unsafe;
                                } else {
                                    r8 = r312;
                                    r7 = r33;
                                    r2 = r02;
                                    r6 = r252;
                                }
                            } else {
                                r15 = r21;
                                r312 = r192;
                                r18 = r74;
                                unsafe = unsafe2;
                                r23 = r73;
                                if (zzB != 50) {
                                    r02 = zzu(obj, bArr, r15, r32, r312, r23, r82, r42, zzB, j, r18, zzgmrVar);
                                    if (r02 != r15) {
                                        zzgqaVar2 = this;
                                        obj5 = obj;
                                        bArr2 = bArr;
                                        r14 = r312;
                                        r13 = r32;
                                        r113 = r33;
                                        zzgmrVar3 = zzgmrVar;
                                        r34 = r18;
                                        r5 = r20;
                                        r22 = r23;
                                        r6 = r252;
                                        unsafe2 = unsafe;
                                    } else {
                                        r8 = r312;
                                        r7 = r33;
                                        r2 = r02;
                                        r6 = r252;
                                    }
                                } else if (r82 == 2) {
                                    r02 = zzt(obj, bArr, r15, r32, r18, j, zzgmrVar);
                                    if (r02 != r15) {
                                        zzgqaVar2 = this;
                                        obj5 = obj;
                                        bArr2 = bArr;
                                        r14 = r312;
                                        r13 = r32;
                                        r113 = r33;
                                        zzgmrVar3 = zzgmrVar;
                                        r34 = r18;
                                        r5 = r20;
                                        r22 = r23;
                                        r6 = r252;
                                        unsafe2 = unsafe;
                                    } else {
                                        r8 = r312;
                                        r7 = r33;
                                        r2 = r02;
                                        r6 = r252;
                                    }
                                }
                            }
                        } else if (r82 == 2) {
                            zzgow zzgowVar = (zzgow) unsafe2.getObject(obj5, j);
                            if (!zzgowVar.zzc()) {
                                int size = zzgowVar.size();
                                zzgowVar = zzgowVar.zzd(size == 0 ? 10 : size + size);
                                unsafe2.putObject(obj5, j, zzgowVar);
                            }
                            r25 = r6;
                            r02 = zzgms.zze(zzgqaVar2.zzF(r74), r192, bArr, r21, r32, zzgowVar, zzgmrVar);
                            bArr2 = bArr;
                            r13 = r32;
                            r34 = r74;
                            r14 = r192;
                            r22 = r73;
                            r5 = r5;
                            r6 = r25;
                            r113 = r33;
                        } else {
                            r20 = r5;
                            r252 = r6;
                            r15 = r21;
                            r312 = r192;
                            r18 = r74;
                            unsafe = unsafe2;
                            r23 = r73;
                        }
                        r8 = r312;
                        r7 = r33;
                        r2 = r15;
                        r6 = r252;
                    }
                }
                if (r8 != r7 || r7 == 0) {
                    if (this.zzh) {
                        zzgmrVar2 = zzgmrVar;
                        if (zzgmrVar2.zzd != zzgnz.zza()) {
                            r11 = r23;
                            if (zzgmrVar2.zzd.zzc(this.zzg, r11) == null) {
                                r02 = zzgms.zzi(r8, bArr, r2, r32, zzd(obj), zzgmrVar);
                                obj4 = obj;
                                r13 = r32;
                                r14 = r8;
                                zzgqaVar2 = this;
                                zzgmrVar3 = zzgmrVar2;
                                r22 = r11;
                                obj5 = obj4;
                                r34 = r18;
                                r5 = r20;
                                unsafe2 = unsafe;
                                bArr2 = bArr;
                                r113 = r7;
                            } else {
                                zzgok zzgokVar = (zzgok) obj;
                                throw null;
                            }
                        } else {
                            obj4 = obj;
                        }
                    } else {
                        obj4 = obj;
                        zzgmrVar2 = zzgmrVar;
                    }
                    r11 = r23;
                    r02 = zzgms.zzi(r8, bArr, r2, r32, zzd(obj), zzgmrVar);
                    r13 = r32;
                    r14 = r8;
                    zzgqaVar2 = this;
                    zzgmrVar3 = zzgmrVar2;
                    r22 = r11;
                    obj5 = obj4;
                    r34 = r18;
                    r5 = r20;
                    unsafe2 = unsafe;
                    bArr2 = bArr;
                    r113 = r7;
                } else {
                    r3 = 1048575;
                    zzgqaVar = this;
                    obj2 = obj;
                    r02 = r2;
                    r14 = r8;
                    r5 = r20;
                    obj3 = null;
                }
            } else {
                unsafe = unsafe2;
                r7 = r113;
                obj2 = obj5;
                zzgqaVar = zzgqaVar2;
                obj3 = null;
                r3 = 1048575;
            }
        }
        if (r6 != r3) {
            unsafe.putInt(obj2, r6, r5);
        }
        for (int r36 = zzgqaVar.zzl; r36 < zzgqaVar.zzm; r36++) {
            zzgqaVar.zzG(obj2, zzgqaVar.zzk[r36], obj3, zzgqaVar.zzo);
        }
        if (r7 == 0) {
            if (r02 != r32) {
                throw zzgoz.zzg();
            }
        } else if (r02 > r32 || r14 != r7) {
            throw zzgoz.zzg();
        }
        return r02;
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final Object zze() {
        return ((zzgon) this.zzg).zzb(4, null, null);
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final void zzf(Object obj) {
        int r1;
        int r0 = this.zzl;
        while (true) {
            r1 = this.zzm;
            if (r0 >= r1) {
                break;
            }
            long zzC = zzC(this.zzk[r0]) & 1048575;
            Object zzh = zzgrr.zzh(obj, zzC);
            if (zzh != null) {
                ((zzgpr) zzh).zzc();
                zzgrr.zzv(obj, zzC, zzh);
            }
            r0++;
        }
        int length = this.zzk.length;
        while (r1 < length) {
            this.zzn.zzb(obj, this.zzk[r1]);
            r1++;
        }
        this.zzo.zzm(obj);
        if (this.zzh) {
            this.zzp.zze(obj);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final void zzh(Object obj, zzgqi zzgqiVar, zzgnz zzgnzVar) throws IOException {
        Objects.requireNonNull(zzgnzVar);
        zzgrh zzgrhVar = this.zzo;
        zzgoa zzgoaVar = this.zzp;
        zzgoe zzgoeVar = null;
        Object obj2 = null;
        while (true) {
            try {
                int zzc = zzgqiVar.zzc();
                int zzx = zzx(zzc);
                if (zzx >= 0) {
                    int zzC = zzC(zzx);
                    try {
                        switch (zzB(zzC)) {
                            case 0:
                                zzgrr.zzr(obj, zzC & 1048575, zzgqiVar.zza());
                                zzM(obj, zzx);
                                break;
                            case 1:
                                zzgrr.zzs(obj, zzC & 1048575, zzgqiVar.zzb());
                                zzM(obj, zzx);
                                break;
                            case 2:
                                zzgrr.zzu(obj, zzC & 1048575, zzgqiVar.zzl());
                                zzM(obj, zzx);
                                break;
                            case 3:
                                zzgrr.zzu(obj, zzC & 1048575, zzgqiVar.zzo());
                                zzM(obj, zzx);
                                break;
                            case 4:
                                zzgrr.zzt(obj, zzC & 1048575, zzgqiVar.zzg());
                                zzM(obj, zzx);
                                break;
                            case 5:
                                zzgrr.zzu(obj, zzC & 1048575, zzgqiVar.zzk());
                                zzM(obj, zzx);
                                break;
                            case 6:
                                zzgrr.zzt(obj, zzC & 1048575, zzgqiVar.zzf());
                                zzM(obj, zzx);
                                break;
                            case 7:
                                zzgrr.zzp(obj, zzC & 1048575, zzgqiVar.zzN());
                                zzM(obj, zzx);
                                break;
                            case 8:
                                zzL(obj, zzC, zzgqiVar);
                                zzM(obj, zzx);
                                break;
                            case 9:
                                if (zzQ(obj, zzx)) {
                                    long j = zzC & 1048575;
                                    zzgrr.zzv(obj, j, zzgox.zzg(zzgrr.zzh(obj, j), zzgqiVar.zzs(zzF(zzx), zzgnzVar)));
                                    break;
                                } else {
                                    zzgrr.zzv(obj, zzC & 1048575, zzgqiVar.zzs(zzF(zzx), zzgnzVar));
                                    zzM(obj, zzx);
                                    break;
                                }
                            case 10:
                                zzgrr.zzv(obj, zzC & 1048575, zzgqiVar.zzp());
                                zzM(obj, zzx);
                                break;
                            case 11:
                                zzgrr.zzt(obj, zzC & 1048575, zzgqiVar.zzj());
                                zzM(obj, zzx);
                                break;
                            case 12:
                                int zze = zzgqiVar.zze();
                                zzgor zzE = zzE(zzx);
                                if (zzE != null && !zzE.zza(zze)) {
                                    obj2 = zzgqs.zzD(zzc, zze, obj2, zzgrhVar);
                                    break;
                                }
                                zzgrr.zzt(obj, zzC & 1048575, zze);
                                zzM(obj, zzx);
                                break;
                            case 13:
                                zzgrr.zzt(obj, zzC & 1048575, zzgqiVar.zzh());
                                zzM(obj, zzx);
                                break;
                            case 14:
                                zzgrr.zzu(obj, zzC & 1048575, zzgqiVar.zzm());
                                zzM(obj, zzx);
                                break;
                            case 15:
                                zzgrr.zzt(obj, zzC & 1048575, zzgqiVar.zzi());
                                zzM(obj, zzx);
                                break;
                            case 16:
                                zzgrr.zzu(obj, zzC & 1048575, zzgqiVar.zzn());
                                zzM(obj, zzx);
                                break;
                            case 17:
                                if (zzQ(obj, zzx)) {
                                    long j2 = zzC & 1048575;
                                    zzgrr.zzv(obj, j2, zzgox.zzg(zzgrr.zzh(obj, j2), zzgqiVar.zzr(zzF(zzx), zzgnzVar)));
                                    break;
                                } else {
                                    zzgrr.zzv(obj, zzC & 1048575, zzgqiVar.zzr(zzF(zzx), zzgnzVar));
                                    zzM(obj, zzx);
                                    break;
                                }
                            case 18:
                                zzgqiVar.zzx(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 19:
                                zzgqiVar.zzB(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 20:
                                zzgqiVar.zzE(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 21:
                                zzgqiVar.zzM(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 22:
                                zzgqiVar.zzD(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 23:
                                zzgqiVar.zzA(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 24:
                                zzgqiVar.zzz(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 25:
                                zzgqiVar.zzv(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 26:
                                if (zzP(zzC)) {
                                    ((zzgno) zzgqiVar).zzK(this.zzn.zza(obj, zzC & 1048575), true);
                                    break;
                                } else {
                                    ((zzgno) zzgqiVar).zzK(this.zzn.zza(obj, zzC & 1048575), false);
                                    break;
                                }
                            case 27:
                                zzgqiVar.zzF(this.zzn.zza(obj, zzC & 1048575), zzF(zzx), zzgnzVar);
                                break;
                            case 28:
                                zzgqiVar.zzw(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 29:
                                zzgqiVar.zzL(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 30:
                                List zza2 = this.zzn.zza(obj, zzC & 1048575);
                                zzgqiVar.zzy(zza2);
                                obj2 = zzgqs.zzC(zzc, zza2, zzE(zzx), obj2, zzgrhVar);
                                break;
                            case 31:
                                zzgqiVar.zzG(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 32:
                                zzgqiVar.zzH(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 33:
                                zzgqiVar.zzI(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 34:
                                zzgqiVar.zzJ(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 35:
                                zzgqiVar.zzx(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 36:
                                zzgqiVar.zzB(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 37:
                                zzgqiVar.zzE(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 38:
                                zzgqiVar.zzM(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 39:
                                zzgqiVar.zzD(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 40:
                                zzgqiVar.zzA(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 41:
                                zzgqiVar.zzz(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 42:
                                zzgqiVar.zzv(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 43:
                                zzgqiVar.zzL(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 44:
                                List zza3 = this.zzn.zza(obj, zzC & 1048575);
                                zzgqiVar.zzy(zza3);
                                obj2 = zzgqs.zzC(zzc, zza3, zzE(zzx), obj2, zzgrhVar);
                                break;
                            case 45:
                                zzgqiVar.zzG(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 46:
                                zzgqiVar.zzH(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 47:
                                zzgqiVar.zzI(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 48:
                                zzgqiVar.zzJ(this.zzn.zza(obj, zzC & 1048575));
                                break;
                            case 49:
                                zzgqiVar.zzC(this.zzn.zza(obj, zzC & 1048575), zzF(zzx), zzgnzVar);
                                break;
                            case 50:
                                Object zzH = zzH(zzx);
                                long zzC2 = zzC(zzx) & 1048575;
                                Object zzh = zzgrr.zzh(obj, zzC2);
                                if (zzh == null) {
                                    zzh = zzgpr.zza().zzb();
                                    zzgrr.zzv(obj, zzC2, zzh);
                                } else if (zzgps.zzb(zzh)) {
                                    Object zzb2 = zzgpr.zza().zzb();
                                    zzgps.zzc(zzb2, zzh);
                                    zzgrr.zzv(obj, zzC2, zzb2);
                                    zzh = zzb2;
                                }
                                zzgpr zzgprVar = (zzgpr) zzh;
                                zzgpq zzgpqVar = (zzgpq) zzH;
                                throw null;
                                break;
                            case 51:
                                zzgrr.zzv(obj, zzC & 1048575, Double.valueOf(zzgqiVar.zza()));
                                zzN(obj, zzc, zzx);
                                break;
                            case 52:
                                zzgrr.zzv(obj, zzC & 1048575, Float.valueOf(zzgqiVar.zzb()));
                                zzN(obj, zzc, zzx);
                                break;
                            case 53:
                                zzgrr.zzv(obj, zzC & 1048575, Long.valueOf(zzgqiVar.zzl()));
                                zzN(obj, zzc, zzx);
                                break;
                            case 54:
                                zzgrr.zzv(obj, zzC & 1048575, Long.valueOf(zzgqiVar.zzo()));
                                zzN(obj, zzc, zzx);
                                break;
                            case 55:
                                zzgrr.zzv(obj, zzC & 1048575, Integer.valueOf(zzgqiVar.zzg()));
                                zzN(obj, zzc, zzx);
                                break;
                            case 56:
                                zzgrr.zzv(obj, zzC & 1048575, Long.valueOf(zzgqiVar.zzk()));
                                zzN(obj, zzc, zzx);
                                break;
                            case 57:
                                zzgrr.zzv(obj, zzC & 1048575, Integer.valueOf(zzgqiVar.zzf()));
                                zzN(obj, zzc, zzx);
                                break;
                            case 58:
                                zzgrr.zzv(obj, zzC & 1048575, Boolean.valueOf(zzgqiVar.zzN()));
                                zzN(obj, zzc, zzx);
                                break;
                            case 59:
                                zzL(obj, zzC, zzgqiVar);
                                zzN(obj, zzc, zzx);
                                break;
                            case 60:
                                if (zzT(obj, zzc, zzx)) {
                                    long j3 = zzC & 1048575;
                                    zzgrr.zzv(obj, j3, zzgox.zzg(zzgrr.zzh(obj, j3), zzgqiVar.zzs(zzF(zzx), zzgnzVar)));
                                } else {
                                    zzgrr.zzv(obj, zzC & 1048575, zzgqiVar.zzs(zzF(zzx), zzgnzVar));
                                    zzM(obj, zzx);
                                }
                                zzN(obj, zzc, zzx);
                                break;
                            case 61:
                                zzgrr.zzv(obj, zzC & 1048575, zzgqiVar.zzp());
                                zzN(obj, zzc, zzx);
                                break;
                            case 62:
                                zzgrr.zzv(obj, zzC & 1048575, Integer.valueOf(zzgqiVar.zzj()));
                                zzN(obj, zzc, zzx);
                                break;
                            case 63:
                                int zze2 = zzgqiVar.zze();
                                zzgor zzE2 = zzE(zzx);
                                if (zzE2 != null && !zzE2.zza(zze2)) {
                                    obj2 = zzgqs.zzD(zzc, zze2, obj2, zzgrhVar);
                                    break;
                                }
                                zzgrr.zzv(obj, zzC & 1048575, Integer.valueOf(zze2));
                                zzN(obj, zzc, zzx);
                                break;
                            case 64:
                                zzgrr.zzv(obj, zzC & 1048575, Integer.valueOf(zzgqiVar.zzh()));
                                zzN(obj, zzc, zzx);
                                break;
                            case 65:
                                zzgrr.zzv(obj, zzC & 1048575, Long.valueOf(zzgqiVar.zzm()));
                                zzN(obj, zzc, zzx);
                                break;
                            case 66:
                                zzgrr.zzv(obj, zzC & 1048575, Integer.valueOf(zzgqiVar.zzi()));
                                zzN(obj, zzc, zzx);
                                break;
                            case 67:
                                zzgrr.zzv(obj, zzC & 1048575, Long.valueOf(zzgqiVar.zzn()));
                                zzN(obj, zzc, zzx);
                                break;
                            case 68:
                                zzgrr.zzv(obj, zzC & 1048575, zzgqiVar.zzr(zzF(zzx), zzgnzVar));
                                zzN(obj, zzc, zzx);
                                break;
                            default:
                                if (obj2 == null) {
                                    obj2 = zzgrhVar.zzf();
                                }
                                if (!zzgrhVar.zzp(obj2, zzgqiVar)) {
                                    for (int r14 = this.zzl; r14 < this.zzm; r14++) {
                                        obj2 = zzG(obj, this.zzk[r14], obj2, zzgrhVar);
                                    }
                                    if (obj2 != null) {
                                        zzgrhVar.zzn(obj, obj2);
                                        return;
                                    }
                                    return;
                                }
                                break;
                        }
                    } catch (zzgoy unused) {
                        zzgrhVar.zzq(zzgqiVar);
                        if (obj2 == null) {
                            obj2 = zzgrhVar.zzc(obj);
                        }
                        if (!zzgrhVar.zzp(obj2, zzgqiVar)) {
                            for (int r142 = this.zzl; r142 < this.zzm; r142++) {
                                obj2 = zzG(obj, this.zzk[r142], obj2, zzgrhVar);
                            }
                            if (obj2 != null) {
                                zzgrhVar.zzn(obj, obj2);
                                return;
                            }
                            return;
                        }
                    }
                } else if (zzc == Integer.MAX_VALUE) {
                    for (int r143 = this.zzl; r143 < this.zzm; r143++) {
                        obj2 = zzG(obj, this.zzk[r143], obj2, zzgrhVar);
                    }
                    if (obj2 != null) {
                        zzgrhVar.zzn(obj, obj2);
                        return;
                    }
                    return;
                } else {
                    Object zzc2 = !this.zzh ? null : zzgoaVar.zzc(zzgnzVar, this.zzg, zzc);
                    if (zzc2 != null) {
                        if (zzgoeVar == null) {
                            zzgoeVar = zzgoaVar.zzb(obj);
                        }
                        zzgoe zzgoeVar2 = zzgoeVar;
                        obj2 = zzgoaVar.zzd(zzgqiVar, zzc2, zzgnzVar, zzgoeVar2, obj2, zzgrhVar);
                        zzgoeVar = zzgoeVar2;
                    } else {
                        zzgrhVar.zzq(zzgqiVar);
                        if (obj2 == null) {
                            obj2 = zzgrhVar.zzc(obj);
                        }
                        if (!zzgrhVar.zzp(obj2, zzgqiVar)) {
                            for (int r144 = this.zzl; r144 < this.zzm; r144++) {
                                obj2 = zzG(obj, this.zzk[r144], obj2, zzgrhVar);
                            }
                            if (obj2 != null) {
                                zzgrhVar.zzn(obj, obj2);
                                return;
                            }
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
                for (int r15 = this.zzl; r15 < this.zzm; r15++) {
                    obj2 = zzG(obj, this.zzk[r15], obj2, zzgrhVar);
                }
                if (obj2 != null) {
                    zzgrhVar.zzn(obj, obj2);
                }
                throw th;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final void zzi(Object obj, byte[] bArr, int r11, int r12, zzgmr zzgmrVar) throws IOException {
        if (this.zzj) {
            zzv(obj, bArr, r11, r12, zzgmrVar);
        } else {
            zzc(obj, bArr, r11, r12, 0, zzgmrVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final boolean zzj(Object obj, Object obj2) {
        boolean zzH;
        int length = this.zzc.length;
        for (int r2 = 0; r2 < length; r2 += 3) {
            int zzC = zzC(r2);
            long j = zzC & 1048575;
            switch (zzB(zzC)) {
                case 0:
                    if (zzO(obj, obj2, r2) && Double.doubleToLongBits(zzgrr.zzb(obj, j)) == Double.doubleToLongBits(zzgrr.zzb(obj2, j))) {
                        continue;
                    }
                    return false;
                case 1:
                    if (zzO(obj, obj2, r2) && Float.floatToIntBits(zzgrr.zzc(obj, j)) == Float.floatToIntBits(zzgrr.zzc(obj2, j))) {
                        continue;
                    }
                    return false;
                case 2:
                    if (zzO(obj, obj2, r2) && zzgrr.zzf(obj, j) == zzgrr.zzf(obj2, j)) {
                        continue;
                    }
                    return false;
                case 3:
                    if (zzO(obj, obj2, r2) && zzgrr.zzf(obj, j) == zzgrr.zzf(obj2, j)) {
                        continue;
                    }
                    return false;
                case 4:
                    if (zzO(obj, obj2, r2) && zzgrr.zzd(obj, j) == zzgrr.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 5:
                    if (zzO(obj, obj2, r2) && zzgrr.zzf(obj, j) == zzgrr.zzf(obj2, j)) {
                        continue;
                    }
                    return false;
                case 6:
                    if (zzO(obj, obj2, r2) && zzgrr.zzd(obj, j) == zzgrr.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 7:
                    if (zzO(obj, obj2, r2) && zzgrr.zzz(obj, j) == zzgrr.zzz(obj2, j)) {
                        continue;
                    }
                    return false;
                case 8:
                    if (zzO(obj, obj2, r2) && zzgqs.zzH(zzgrr.zzh(obj, j), zzgrr.zzh(obj2, j))) {
                        continue;
                    }
                    return false;
                case 9:
                    if (zzO(obj, obj2, r2) && zzgqs.zzH(zzgrr.zzh(obj, j), zzgrr.zzh(obj2, j))) {
                        continue;
                    }
                    return false;
                case 10:
                    if (zzO(obj, obj2, r2) && zzgqs.zzH(zzgrr.zzh(obj, j), zzgrr.zzh(obj2, j))) {
                        continue;
                    }
                    return false;
                case 11:
                    if (zzO(obj, obj2, r2) && zzgrr.zzd(obj, j) == zzgrr.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 12:
                    if (zzO(obj, obj2, r2) && zzgrr.zzd(obj, j) == zzgrr.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 13:
                    if (zzO(obj, obj2, r2) && zzgrr.zzd(obj, j) == zzgrr.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 14:
                    if (zzO(obj, obj2, r2) && zzgrr.zzf(obj, j) == zzgrr.zzf(obj2, j)) {
                        continue;
                    }
                    return false;
                case 15:
                    if (zzO(obj, obj2, r2) && zzgrr.zzd(obj, j) == zzgrr.zzd(obj2, j)) {
                        continue;
                    }
                    return false;
                case 16:
                    if (zzO(obj, obj2, r2) && zzgrr.zzf(obj, j) == zzgrr.zzf(obj2, j)) {
                        continue;
                    }
                    return false;
                case 17:
                    if (zzO(obj, obj2, r2) && zzgqs.zzH(zzgrr.zzh(obj, j), zzgrr.zzh(obj2, j))) {
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
                    zzH = zzgqs.zzH(zzgrr.zzh(obj, j), zzgrr.zzh(obj2, j));
                    break;
                case 50:
                    zzH = zzgqs.zzH(zzgrr.zzh(obj, j), zzgrr.zzh(obj2, j));
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
                    long zzz = zzz(r2) & 1048575;
                    if (zzgrr.zzd(obj, zzz) == zzgrr.zzd(obj2, zzz) && zzgqs.zzH(zzgrr.zzh(obj, j), zzgrr.zzh(obj2, j))) {
                        continue;
                    }
                    return false;
                default:
            }
            if (!zzH) {
                return false;
            }
        }
        if (this.zzo.zzd(obj).equals(this.zzo.zzd(obj2))) {
            if (this.zzh) {
                this.zzp.zza(obj);
                this.zzp.zza(obj2);
                throw null;
            }
            return true;
        }
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final boolean zzk(Object obj) {
        int r16;
        int r17;
        int r0 = 1048575;
        int r1 = 0;
        int r10 = 0;
        while (r10 < this.zzl) {
            int r12 = this.zzk[r10];
            int r13 = this.zzc[r12];
            int zzC = zzC(r12);
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
            if ((268435456 & zzC) != 0 && !zzR(obj, r12, r16, r17, r15)) {
                return false;
            }
            int zzB = zzB(zzC);
            if (zzB != 9 && zzB != 17) {
                if (zzB != 27) {
                    if (zzB == 60 || zzB == 68) {
                        if (zzT(obj, r13, r12) && !zzS(obj, zzC, zzF(r12))) {
                            return false;
                        }
                    } else if (zzB != 49) {
                        if (zzB == 50 && !((zzgpr) zzgrr.zzh(obj, zzC & 1048575)).isEmpty()) {
                            zzgpq zzgpqVar = (zzgpq) zzH(r12);
                            throw null;
                        }
                    }
                }
                List list = (List) zzgrr.zzh(obj, zzC & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzgqq zzF = zzF(r12);
                    for (int r22 = 0; r22 < list.size(); r22++) {
                        if (!zzF.zzk(list.get(r22))) {
                            return false;
                        }
                    }
                    continue;
                }
            } else if (zzR(obj, r12, r16, r17, r15) && !zzS(obj, zzC, zzF(r12))) {
                return false;
            }
            r10++;
            r0 = r16;
            r1 = r17;
        }
        if (this.zzh) {
            this.zzp.zza(obj);
            throw null;
        }
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final void zzn(Object obj, zzgnv zzgnvVar) throws IOException {
        if (!this.zzj) {
            zzV(obj, zzgnvVar);
        } else if (!this.zzh) {
            int length = this.zzc.length;
            for (int r2 = 0; r2 < length; r2 += 3) {
                int zzC = zzC(r2);
                int r4 = this.zzc[r2];
                switch (zzB(zzC)) {
                    case 0:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzf(r4, zzgrr.zzb(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzo(r4, zzgrr.zzc(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzt(r4, zzgrr.zzf(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzJ(r4, zzgrr.zzf(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzr(r4, zzgrr.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzm(r4, zzgrr.zzf(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzk(r4, zzgrr.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzb(r4, zzgrr.zzz(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzQ(obj, r2)) {
                            zzX(r4, zzgrr.zzh(obj, zzC & 1048575), zzgnvVar);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzv(r4, zzgrr.zzh(obj, zzC & 1048575), zzF(r2));
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzd(r4, (zzgnf) zzgrr.zzh(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzH(r4, zzgrr.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzi(r4, zzgrr.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzw(r4, zzgrr.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzy(r4, zzgrr.zzf(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzA(r4, zzgrr.zzd(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzC(r4, zzgrr.zzf(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzQ(obj, r2)) {
                            zzgnvVar.zzq(r4, zzgrr.zzh(obj, zzC & 1048575), zzF(r2));
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzgqs.zzL(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 19:
                        zzgqs.zzP(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 20:
                        zzgqs.zzS(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 21:
                        zzgqs.zzaa(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 22:
                        zzgqs.zzR(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 23:
                        zzgqs.zzO(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 24:
                        zzgqs.zzN(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 25:
                        zzgqs.zzJ(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 26:
                        zzgqs.zzY(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar);
                        break;
                    case 27:
                        zzgqs.zzT(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, zzF(r2));
                        break;
                    case 28:
                        zzgqs.zzK(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar);
                        break;
                    case 29:
                        zzgqs.zzZ(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 30:
                        zzgqs.zzM(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 31:
                        zzgqs.zzU(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 32:
                        zzgqs.zzV(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 33:
                        zzgqs.zzW(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 34:
                        zzgqs.zzX(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, false);
                        break;
                    case 35:
                        zzgqs.zzL(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 36:
                        zzgqs.zzP(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 37:
                        zzgqs.zzS(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 38:
                        zzgqs.zzaa(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 39:
                        zzgqs.zzR(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 40:
                        zzgqs.zzO(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 41:
                        zzgqs.zzN(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 42:
                        zzgqs.zzJ(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 43:
                        zzgqs.zzZ(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 44:
                        zzgqs.zzM(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 45:
                        zzgqs.zzU(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 46:
                        zzgqs.zzV(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 47:
                        zzgqs.zzW(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 48:
                        zzgqs.zzX(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, true);
                        break;
                    case 49:
                        zzgqs.zzQ(r4, (List) zzgrr.zzh(obj, zzC & 1048575), zzgnvVar, zzF(r2));
                        break;
                    case 50:
                        zzW(zzgnvVar, r4, zzgrr.zzh(obj, zzC & 1048575), r2);
                        break;
                    case 51:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzf(r4, zzo(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzo(r4, zzp(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzt(r4, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzJ(r4, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzr(r4, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzm(r4, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzk(r4, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzb(r4, zzU(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zzT(obj, r4, r2)) {
                            zzX(r4, zzgrr.zzh(obj, zzC & 1048575), zzgnvVar);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzv(r4, zzgrr.zzh(obj, zzC & 1048575), zzF(r2));
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzd(r4, (zzgnf) zzgrr.zzh(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzH(r4, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzi(r4, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzw(r4, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzy(r4, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzA(r4, zzs(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzC(r4, zzD(obj, zzC & 1048575));
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zzT(obj, r4, r2)) {
                            zzgnvVar.zzq(r4, zzgrr.zzh(obj, zzC & 1048575), zzF(r2));
                            break;
                        } else {
                            break;
                        }
                }
            }
            zzgrh zzgrhVar = this.zzo;
            zzgrhVar.zzr(zzgrhVar.zzd(obj), zzgnvVar);
        } else {
            this.zzp.zza(obj);
            throw null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgqq
    public final void zzg(Object obj, Object obj2) {
        Objects.requireNonNull(obj2);
        for (int r0 = 0; r0 < this.zzc.length; r0 += 3) {
            int zzC = zzC(r0);
            long j = 1048575 & zzC;
            int r4 = this.zzc[r0];
            switch (zzB(zzC)) {
                case 0:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzr(obj, j, zzgrr.zzb(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzs(obj, j, zzgrr.zzc(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzu(obj, j, zzgrr.zzf(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzu(obj, j, zzgrr.zzf(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzt(obj, j, zzgrr.zzd(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzu(obj, j, zzgrr.zzf(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzt(obj, j, zzgrr.zzd(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzp(obj, j, zzgrr.zzz(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzv(obj, j, zzgrr.zzh(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzJ(obj, obj2, r0);
                    break;
                case 10:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzv(obj, j, zzgrr.zzh(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzt(obj, j, zzgrr.zzd(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzt(obj, j, zzgrr.zzd(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzt(obj, j, zzgrr.zzd(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzu(obj, j, zzgrr.zzf(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzt(obj, j, zzgrr.zzd(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzQ(obj2, r0)) {
                        zzgrr.zzu(obj, j, zzgrr.zzf(obj2, j));
                        zzM(obj, r0);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzJ(obj, obj2, r0);
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
                    this.zzn.zzc(obj, obj2, j);
                    break;
                case 50:
                    zzgqs.zzI(this.zzr, obj, obj2, j);
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
                    if (zzT(obj2, r4, r0)) {
                        zzgrr.zzv(obj, j, zzgrr.zzh(obj2, j));
                        zzN(obj, r4, r0);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzK(obj, obj2, r0);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzT(obj2, r4, r0)) {
                        zzgrr.zzv(obj, j, zzgrr.zzh(obj2, j));
                        zzN(obj, r4, r0);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzK(obj, obj2, r0);
                    break;
            }
        }
        zzgqs.zzF(this.zzo, obj, obj2);
        if (this.zzh) {
            zzgqs.zzE(this.zzp, obj, obj2);
        }
    }
}
