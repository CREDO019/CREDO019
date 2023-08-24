package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* loaded from: classes2.dex */
final class zzeh {
    private static final Class<?> zzoh = zzdp();
    private static final zzex<?, ?> zzoi = zzd(false);
    private static final zzex<?, ?> zzoj = zzd(true);
    private static final zzex<?, ?> zzok = new zzez();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(List<Long> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdc) {
            zzdc zzdcVar = (zzdc) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zze(zzdcVar.getLong(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zze(list.get(r1).longValue());
                r1++;
            }
        }
        return r2;
    }

    private static <UT, UB> UB zza(int r2, int r3, UB ub, zzex<UT, UB> zzexVar) {
        if (ub == null) {
            ub = zzexVar.zzdz();
        }
        zzexVar.zza((zzex<UT, UB>) ub, r2, r3);
        return ub;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <UT, UB> UB zza(int r5, List<Integer> list, zzck<?> zzckVar, UB ub, zzex<UT, UB> zzexVar) {
        if (zzckVar == null) {
            return ub;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int r2 = 0;
            for (int r1 = 0; r1 < size; r1++) {
                int intValue = list.get(r1).intValue();
                if (zzckVar.zzb(intValue) != null) {
                    if (r1 != r2) {
                        list.set(r2, Integer.valueOf(intValue));
                    }
                    r2++;
                } else {
                    ub = (UB) zza(r5, intValue, ub, zzexVar);
                }
            }
            if (r2 != size) {
                list.subList(r2, size).clear();
            }
        } else {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = it.next().intValue();
                if (zzckVar.zzb(intValue2) == null) {
                    ub = (UB) zza(r5, intValue2, ub, zzexVar);
                    it.remove();
                }
            }
        }
        return ub;
    }

    public static void zza(int r1, List<String> list, zzfr zzfrVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zza(r1, list);
    }

    public static void zza(int r1, List<?> list, zzfr zzfrVar, zzef zzefVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zza(r1, list, zzefVar);
    }

    public static void zza(int r1, List<Double> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzg(r1, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, FT extends zzca<FT>> void zza(zzbu<FT> zzbuVar, T t, T t2) {
        zzby<FT> zza = zzbuVar.zza(t2);
        if (zza.isEmpty()) {
            return;
        }
        zzbuVar.zzb(t).zza(zza);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void zza(zzdj zzdjVar, T t, T t2, long j) {
        zzfd.zza(t, j, zzdjVar.zzb(zzfd.zzo(t, j), zzfd.zzo(t2, j)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, UT, UB> void zza(zzex<UT, UB> zzexVar, T t, T t2) {
        zzexVar.zze(t, zzexVar.zzg(zzexVar.zzq(t), zzexVar.zzq(t2)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(List<Long> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdc) {
            zzdc zzdcVar = (zzdc) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zzf(zzdcVar.getLong(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zzf(list.get(r1).longValue());
                r1++;
            }
        }
        return r2;
    }

    public static void zzb(int r1, List<zzbb> list, zzfr zzfrVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzb(r1, list);
    }

    public static void zzb(int r1, List<?> list, zzfr zzfrVar, zzef zzefVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzb(r1, list, zzefVar);
    }

    public static void zzb(int r1, List<Float> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzf(r1, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int r1, Object obj, zzef zzefVar) {
        return obj instanceof zzcv ? zzbn.zza(r1, (zzcv) obj) : zzbn.zzb(r1, (zzdo) obj, zzefVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int r4, List<?> list) {
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        int zzr = zzbn.zzr(r4) * size;
        if (list instanceof zzcx) {
            zzcx zzcxVar = (zzcx) list;
            while (r1 < size) {
                Object raw = zzcxVar.getRaw(r1);
                zzr += raw instanceof zzbb ? zzbn.zzb((zzbb) raw) : zzbn.zzh((String) raw);
                r1++;
            }
        } else {
            while (r1 < size) {
                Object obj = list.get(r1);
                zzr += obj instanceof zzbb ? zzbn.zzb((zzbb) obj) : zzbn.zzh((String) obj);
                r1++;
            }
        }
        return zzr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(int r4, List<?> list, zzef zzefVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = zzbn.zzr(r4) * size;
        for (int r1 = 0; r1 < size; r1++) {
            Object obj = list.get(r1);
            zzr += obj instanceof zzcv ? zzbn.zza((zzcv) obj) : zzbn.zzb((zzdo) obj, zzefVar);
        }
        return zzr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(List<Long> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzdc) {
            zzdc zzdcVar = (zzdc) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zzg(zzdcVar.getLong(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zzg(list.get(r1).longValue());
                r1++;
            }
        }
        return r2;
    }

    public static void zzc(int r1, List<Long> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzc(r1, list, z);
    }

    public static boolean zzc(int r9, int r10, int r11) {
        if (r10 < 40) {
            return true;
        }
        long j = r11;
        return ((((long) r10) - ((long) r9)) + 1) + 9 <= ((2 * j) + 3) + ((j + 3) * 3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int r2, List<zzbb> list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzr = size * zzbn.zzr(r2);
        for (int r1 = 0; r1 < list.size(); r1++) {
            zzr += zzbn.zzb(list.get(r1));
        }
        return zzr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int r4, List<zzdo> list, zzef zzefVar) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int r2 = 0;
        for (int r1 = 0; r1 < size; r1++) {
            r2 += zzbn.zzc(r4, list.get(r1), zzefVar);
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(List<Integer> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzch) {
            zzch zzchVar = (zzch) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zzx(zzchVar.getInt(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zzx(list.get(r1).intValue());
                r1++;
            }
        }
        return r2;
    }

    private static zzex<?, ?> zzd(boolean z) {
        try {
            Class<?> zzdq = zzdq();
            if (zzdq == null) {
                return null;
            }
            return (zzex) zzdq.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void zzd(int r1, List<Long> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzd(r1, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzd(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static zzex<?, ?> zzdm() {
        return zzoi;
    }

    public static zzex<?, ?> zzdn() {
        return zzoj;
    }

    public static zzex<?, ?> zzdo() {
        return zzok;
    }

    private static Class<?> zzdp() {
        try {
            return Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> zzdq() {
        try {
            return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(List<Integer> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzch) {
            zzch zzchVar = (zzch) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zzs(zzchVar.getInt(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zzs(list.get(r1).intValue());
                r1++;
            }
        }
        return r2;
    }

    public static void zze(int r1, List<Long> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzn(r1, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(List<Integer> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzch) {
            zzch zzchVar = (zzch) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zzt(zzchVar.getInt(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zzt(list.get(r1).intValue());
                r1++;
            }
        }
        return r2;
    }

    public static void zzf(int r1, List<Long> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zze(r1, list, z);
    }

    public static void zzf(Class<?> cls) {
        Class<?> cls2;
        if (!zzcg.class.isAssignableFrom(cls) && (cls2 = zzoh) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzg(List<Integer> list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzch) {
            zzch zzchVar = (zzch) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zzu(zzchVar.getInt(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzbn.zzu(list.get(r1).intValue());
                r1++;
            }
        }
        return r2;
    }

    public static void zzg(int r1, List<Long> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzl(r1, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzh(List<?> list) {
        return list.size() << 2;
    }

    public static void zzh(int r1, List<Integer> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zza(r1, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(List<?> list) {
        return list.size() << 3;
    }

    public static void zzi(int r1, List<Integer> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzj(r1, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(List<?> list) {
        return list.size();
    }

    public static void zzj(int r1, List<Integer> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzm(r1, list, z);
    }

    public static void zzk(int r1, List<Integer> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzb(r1, list, z);
    }

    public static void zzl(int r1, List<Integer> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzk(r1, list, z);
    }

    public static void zzm(int r1, List<Integer> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzh(r1, list, z);
    }

    public static void zzn(int r1, List<Boolean> list, zzfr zzfrVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzfrVar.zzi(r1, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzo(int r0, List<Long> list, boolean z) {
        if (list.size() == 0) {
            return 0;
        }
        return zza(list) + (list.size() * zzbn.zzr(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzp(int r0, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzb(list) + (size * zzbn.zzr(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(int r0, List<Long> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzc(list) + (size * zzbn.zzr(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzr(int r0, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzd(list) + (size * zzbn.zzr(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzs(int r0, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzbn.zzr(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzt(int r0, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzf(list) + (size * zzbn.zzr(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzu(int r0, List<Integer> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzg(list) + (size * zzbn.zzr(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(int r0, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbn.zzj(r0, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(int r2, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbn.zzg(r2, 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzx(int r0, List<?> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * zzbn.zzc(r0, true);
    }
}
