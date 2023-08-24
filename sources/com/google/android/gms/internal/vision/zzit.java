package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzit {
    private static final Class<?> zzzr = zzhv();
    private static final zzjj<?, ?> zzzs = zzn(false);
    private static final zzjj<?, ?> zzzt = zzn(true);
    private static final zzjj<?, ?> zzzu = new zzjl();

    public static void zzg(Class<?> cls) {
        Class<?> cls2;
        if (!zzgs.class.isAssignableFrom(cls) && (cls2 = zzzr) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    public static void zza(int r1, List<Double> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzg(r1, list, z);
    }

    public static void zzb(int r1, List<Float> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzf(r1, list, z);
    }

    public static void zzc(int r1, List<Long> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzc(r1, list, z);
    }

    public static void zzd(int r1, List<Long> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzd(r1, list, z);
    }

    public static void zze(int r1, List<Long> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzn(r1, list, z);
    }

    public static void zzf(int r1, List<Long> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zze(r1, list, z);
    }

    public static void zzg(int r1, List<Long> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzl(r1, list, z);
    }

    public static void zzh(int r1, List<Integer> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zza(r1, list, z);
    }

    public static void zzi(int r1, List<Integer> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzj(r1, list, z);
    }

    public static void zzj(int r1, List<Integer> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzm(r1, list, z);
    }

    public static void zzk(int r1, List<Integer> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzb(r1, list, z);
    }

    public static void zzl(int r1, List<Integer> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzk(r1, list, z);
    }

    public static void zzm(int r1, List<Integer> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzh(r1, list, z);
    }

    public static void zzn(int r1, List<Boolean> list, zzkg zzkgVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzi(r1, list, z);
    }

    public static void zza(int r1, List<String> list, zzkg zzkgVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zza(r1, list);
    }

    public static void zzb(int r1, List<zzfh> list, zzkg zzkgVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzb(r1, list);
    }

    public static void zza(int r1, List<?> list, zzkg zzkgVar, zzir zzirVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zza(r1, list, zzirVar);
    }

    public static void zzb(int r1, List<?> list, zzkg zzkgVar, zzir zzirVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzkgVar.zzb(r1, list, zzirVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(List<Long> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhq) {
            zzhq zzhqVar = (zzhq) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzv(zzhqVar.getLong(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzv(list.get(r1).longValue());
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzo(int r0, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zzq(list) + (list.size() * zzga.zzba(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzr(List<Long> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhq) {
            zzhq zzhqVar = (zzhq) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzw(zzhqVar.getLong(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzw(list.get(r1).longValue());
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzp(int r0, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzga.zzba(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzs(List<Long> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzhq) {
            zzhq zzhqVar = (zzhq) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzx(zzhqVar.getLong(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzx(list.get(r1).longValue());
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(int r0, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzs(list) + (size * zzga.zzba(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzt(List<Integer> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzbg(zzguVar.getInt(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzbg(list.get(r1).intValue());
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzr(int r0, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzt(list) + (size * zzga.zzba(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzu(List<Integer> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzbb(zzguVar.getInt(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzbb(list.get(r1).intValue());
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzs(int r0, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzu(list) + (size * zzga.zzba(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(List<Integer> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzbc(zzguVar.getInt(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzbc(list.get(r1).intValue());
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzt(int r0, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzv(list) + (size * zzga.zzba(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(List<Integer> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgu) {
            zzgu zzguVar = (zzgu) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzbd(zzguVar.getInt(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzga.zzbd(list.get(r1).intValue());
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzu(int r0, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzga.zzba(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(List<?> list) {
        return list.size() << 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(int r0, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzga.zzo(r0, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzy(List<?> list) {
        return list.size() << 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(int r2, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzga.zzg(r2, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzz(List<?> list) {
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(int r0, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzga.zzb(r0, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int r4, List<?> list) {
        int zzy;
        int zzy2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        int zzba = zzga.zzba(r4) * size;
        if (list instanceof zzhj) {
            zzhj zzhjVar = (zzhj) list;
            while (r1 < size) {
                Object raw = zzhjVar.getRaw(r1);
                if (raw instanceof zzfh) {
                    zzy2 = zzga.zzb((zzfh) raw);
                } else {
                    zzy2 = zzga.zzy((String) raw);
                }
                zzba += zzy2;
                r1++;
            }
        } else {
            while (r1 < size) {
                Object obj = list.get(r1);
                if (obj instanceof zzfh) {
                    zzy = zzga.zzb((zzfh) obj);
                } else {
                    zzy = zzga.zzy((String) obj);
                }
                zzba += zzy;
                r1++;
            }
        }
        return zzba;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int r1, Object obj, zzir zzirVar) {
        if (obj instanceof zzhh) {
            return zzga.zza(r1, (zzhh) obj);
        }
        return zzga.zzb(r1, (zzic) obj, zzirVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int r4, List<?> list, zzir zzirVar) {
        int zza;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzba = zzga.zzba(r4) * size;
        for (int r1 = 0; r1 < size; r1++) {
            Object obj = list.get(r1);
            if (obj instanceof zzhh) {
                zza = zzga.zza((zzhh) obj);
            } else {
                zza = zzga.zza((zzic) obj, zzirVar);
            }
            zzba += zza;
        }
        return zzba;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int r2, List<zzfh> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzba = size * zzga.zzba(r2);
        for (int r1 = 0; r1 < list.size(); r1++) {
            zzba += zzga.zzb(list.get(r1));
        }
        return zzba;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int r4, List<zzic> list, zzir zzirVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int r2 = 0;
        for (int r1 = 0; r1 < size; r1++) {
            r2 += zzga.zzc(r4, list.get(r1), zzirVar);
        }
        return r2;
    }

    public static zzjj<?, ?> zzhs() {
        return zzzs;
    }

    public static zzjj<?, ?> zzht() {
        return zzzt;
    }

    public static zzjj<?, ?> zzhu() {
        return zzzu;
    }

    private static zzjj<?, ?> zzn(boolean z) {
        try {
            Class<?> zzhw = zzhw();
            if (zzhw == null) {
                return null;
            }
            return (zzjj) zzhw.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzhv() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzhw() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zze(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void zza(zzhv zzhvVar, T t, T t2, long j) {
        zzjp.zza(t, j, zzhvVar.zzc(zzjp.zzp(t, j), zzjp.zzp(t2, j)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, FT extends zzgk<FT>> void zza(zzgf<FT> zzgfVar, T t, T t2) {
        zzgi<FT> zze = zzgfVar.zze(t2);
        if (zze.zztb.isEmpty()) {
            return;
        }
        zzgfVar.zzf(t).zza(zze);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, UT, UB> void zza(zzjj<UT, UB> zzjjVar, T t, T t2) {
        zzjjVar.zzf(t, zzjjVar.zzh(zzjjVar.zzv(t), zzjjVar.zzv(t2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB zza(int r5, List<Integer> list, zzgv<?> zzgvVar, UB ub, zzjj<UT, UB> zzjjVar) {
        if (zzgvVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int r2 = 0;
            for (int r1 = 0; r1 < size; r1++) {
                int intValue = list.get(r1).intValue();
                if (zzgvVar.zzg(intValue) != null) {
                    if (r1 != r2) {
                        list.set(r2, Integer.valueOf(intValue));
                    }
                    r2++;
                } else {
                    ub = (UB) zza(r5, intValue, ub, zzjjVar);
                }
            }
            if (r2 != size) {
                list.subList(r2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (zzgvVar.zzg(intValue2) == null) {
                    ub = (UB) zza(r5, intValue2, ub, zzjjVar);
                    it.remove();
                }
            }
        }
        return ub;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB zza(int r5, List<Integer> list, zzgy zzgyVar, UB ub, zzjj<UT, UB> zzjjVar) {
        if (zzgyVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int r2 = 0;
            for (int r1 = 0; r1 < size; r1++) {
                int intValue = list.get(r1).intValue();
                if (zzgyVar.zzf(intValue)) {
                    if (r1 != r2) {
                        list.set(r2, Integer.valueOf(intValue));
                    }
                    r2++;
                } else {
                    ub = (UB) zza(r5, intValue, ub, zzjjVar);
                }
            }
            if (r2 != size) {
                list.subList(r2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (!zzgyVar.zzf(intValue2)) {
                    ub = (UB) zza(r5, intValue2, ub, zzjjVar);
                    it.remove();
                }
            }
        }
        return ub;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB zza(int r2, int r3, UB ub, zzjj<UT, UB> zzjjVar) {
        if (ub == null) {
            ub = zzjjVar.zzif();
        }
        zzjjVar.zza((zzjj<UT, UB>) ub, r2, r3);
        return ub;
    }
}
