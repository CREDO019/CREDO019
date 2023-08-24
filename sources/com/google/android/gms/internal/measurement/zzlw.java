package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
public final class zzlw {
    private static final Class zza;
    private static final zzml zzb;
    private static final zzml zzc;
    private static final zzml zzd;

    static {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        zza = cls;
        zzb = zzab(false);
        zzc = zzab(true);
        zzd = new zzmn();
    }

    public static zzml zzA() {
        return zzc;
    }

    public static zzml zzB() {
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzC(int r5, List list, zzkg zzkgVar, Object obj, zzml zzmlVar) {
        if (zzkgVar == null) {
            return obj;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int r2 = 0;
            for (int r1 = 0; r1 < size; r1++) {
                int intValue = ((Integer) list.get(r1)).intValue();
                if (zzkgVar.zza(intValue)) {
                    if (r1 != r2) {
                        list.set(r2, Integer.valueOf(intValue));
                    }
                    r2++;
                } else {
                    obj = zzD(r5, intValue, obj, zzmlVar);
                }
            }
            if (r2 != size) {
                list.subList(r2, size).clear();
                return obj;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!zzkgVar.zza(intValue2)) {
                    obj = zzD(r5, intValue2, obj, zzmlVar);
                    it.remove();
                }
            }
        }
        return obj;
    }

    static Object zzD(int r2, int r3, Object obj, zzml zzmlVar) {
        if (obj == null) {
            obj = zzmlVar.zze();
        }
        zzmlVar.zzf(obj, r2, r3);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzE(zzjp zzjpVar, Object obj, Object obj2) {
        zzjpVar.zza(obj2);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzF(zzml zzmlVar, Object obj, Object obj2) {
        zzmlVar.zzh(obj, zzmlVar.zzd(zzmlVar.zzc(obj), zzmlVar.zzc(obj2)));
    }

    public static void zzG(Class cls) {
        Class cls2;
        if (!zzkc.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zzH(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzc(r1, list, z);
    }

    public static void zzI(int r1, List list, zznd zzndVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zze(r1, list);
    }

    public static void zzJ(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzg(r1, list, z);
    }

    public static void zzK(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzj(r1, list, z);
    }

    public static void zzL(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzl(r1, list, z);
    }

    public static void zzM(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzn(r1, list, z);
    }

    public static void zzN(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzp(r1, list, z);
    }

    public static void zzO(int r3, List list, zznd zzndVar, zzlu zzluVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int r0 = 0; r0 < list.size(); r0++) {
            ((zzjk) zzndVar).zzq(r3, list.get(r0), zzluVar);
        }
    }

    public static void zzP(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzs(r1, list, z);
    }

    public static void zzQ(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzu(r1, list, z);
    }

    public static void zzR(int r3, List list, zznd zzndVar, zzlu zzluVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int r0 = 0; r0 < list.size(); r0++) {
            ((zzjk) zzndVar).zzv(r3, list.get(r0), zzluVar);
        }
    }

    public static void zzS(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzx(r1, list, z);
    }

    public static void zzT(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzz(r1, list, z);
    }

    public static void zzU(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzB(r1, list, z);
    }

    public static void zzV(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzD(r1, list, z);
    }

    public static void zzW(int r1, List list, zznd zzndVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzG(r1, list);
    }

    public static void zzX(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzI(r1, list, z);
    }

    public static void zzY(int r1, List list, zznd zzndVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzndVar.zzK(r1, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzZ(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int r0, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjj.zzA(r0 << 3) + 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzaa(zzle zzleVar, Object obj, Object obj2, long j) {
        zzmv.zzs(obj, j, zzle.zzb(zzmv.zzf(obj, j), zzmv.zzf(obj2, j)));
    }

    private static zzml zzab(boolean z) {
        Class<?> cls;
        try {
            cls = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            cls = null;
        }
        if (cls == null) {
            return null;
        }
        try {
            return (zzml) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused2) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(List list) {
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int r2, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = size * zzjj.zzz(r2);
        for (int r1 = 0; r1 < list.size(); r1++) {
            zzz += zzjj.zzt((zzjb) list.get(r1));
        }
        return zzz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int r0, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzjj.zzz(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkd) {
            zzkd zzkdVar = (zzkd) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzjj.zzv(zzkdVar.zze(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzjj.zzv(((Integer) list.get(r1)).intValue());
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(int r0, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjj.zzA(r0 << 3) + 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(List list) {
        return list.size() * 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(int r0, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzjj.zzA(r0 << 3) + 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(List list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(int r4, List list, zzlu zzluVar) {
        int size = list.size();
        if (size != 0) {
            int r2 = 0;
            for (int r1 = 0; r1 < size; r1++) {
                r2 += zzjj.zzu(r4, (zzlj) list.get(r1), zzluVar);
            }
            return r2;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(int r0, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzl(list) + (size * zzjj.zzz(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkd) {
            zzkd zzkdVar = (zzkd) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzjj.zzv(zzkdVar.zze(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzjj.zzv(((Integer) list.get(r1)).intValue());
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzm(int r0, List list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzn(list) + (list.size() * zzjj.zzz(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzn(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzky) {
            zzky zzkyVar = (zzky) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzjj.zzB(zzkyVar.zza(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzjj.zzB(((Long) list.get(r1)).longValue());
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzo(int r1, Object obj, zzlu zzluVar) {
        if (obj instanceof zzkp) {
            int zzA = zzjj.zzA(r1 << 3);
            int zza2 = ((zzkp) obj).zza();
            return zzA + zzjj.zzA(zza2) + zza2;
        }
        return zzjj.zzA(r1 << 3) + zzjj.zzx((zzlj) obj, zzluVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzp(int r4, List list, zzlu zzluVar) {
        int zzx;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzz = zzjj.zzz(r4) * size;
        for (int r1 = 0; r1 < size; r1++) {
            Object obj = list.get(r1);
            if (obj instanceof zzkp) {
                zzx = zzjj.zzw((zzkp) obj);
            } else {
                zzx = zzjj.zzx((zzlj) obj, zzluVar);
            }
            zzz += zzx;
        }
        return zzz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(int r0, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzjj.zzz(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzr(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkd) {
            zzkd zzkdVar = (zzkd) list;
            r2 = 0;
            while (r1 < size) {
                int zze = zzkdVar.zze(r1);
                r2 += zzjj.zzA((zze >> 31) ^ (zze + zze));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                int intValue = ((Integer) list.get(r1)).intValue();
                r2 += zzjj.zzA((intValue >> 31) ^ (intValue + intValue));
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzs(int r0, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzjj.zzz(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzt(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzky) {
            zzky zzkyVar = (zzky) list;
            r2 = 0;
            while (r1 < size) {
                long zza2 = zzkyVar.zza(r1);
                r2 += zzjj.zzB((zza2 >> 63) ^ (zza2 + zza2));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                long longValue = ((Long) list.get(r1)).longValue();
                r2 += zzjj.zzB((longValue >> 63) ^ (longValue + longValue));
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzu(int r4, List list) {
        int zzy;
        int zzy2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        int zzz = zzjj.zzz(r4) * size;
        if (list instanceof zzkr) {
            zzkr zzkrVar = (zzkr) list;
            while (r1 < size) {
                Object zzf = zzkrVar.zzf(r1);
                if (zzf instanceof zzjb) {
                    zzy2 = zzjj.zzt((zzjb) zzf);
                } else {
                    zzy2 = zzjj.zzy((String) zzf);
                }
                zzz += zzy2;
                r1++;
            }
        } else {
            while (r1 < size) {
                Object obj = list.get(r1);
                if (obj instanceof zzjb) {
                    zzy = zzjj.zzt((zzjb) obj);
                } else {
                    zzy = zzjj.zzy((String) obj);
                }
                zzz += zzy;
                r1++;
            }
        }
        return zzz;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(int r0, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzjj.zzz(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzkd) {
            zzkd zzkdVar = (zzkd) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzjj.zzA(zzkdVar.zze(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzjj.zzA(((Integer) list.get(r1)).intValue());
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(int r0, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzy(list) + (size * zzjj.zzz(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzy(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzky) {
            zzky zzkyVar = (zzky) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzjj.zzB(zzkyVar.zza(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzjj.zzB(((Long) list.get(r1)).longValue());
                r1++;
            }
        }
        return r2;
    }

    public static zzml zzz() {
        return zzb;
    }
}
