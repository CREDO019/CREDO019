package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgqs {
    private static final Class zza;
    private static final zzgrh zzb;
    private static final zzgrh zzc;
    private static final zzgrh zzd;

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
        zzd = new zzgrj();
    }

    public static zzgrh zzA() {
        return zzc;
    }

    public static zzgrh zzB() {
        return zzd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzC(int r5, List list, zzgor zzgorVar, Object obj, zzgrh zzgrhVar) {
        if (zzgorVar == null) {
            return obj;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int r2 = 0;
            for (int r1 = 0; r1 < size; r1++) {
                int intValue = ((Integer) list.get(r1)).intValue();
                if (zzgorVar.zza(intValue)) {
                    if (r1 != r2) {
                        list.set(r2, Integer.valueOf(intValue));
                    }
                    r2++;
                } else {
                    obj = zzD(r5, intValue, obj, zzgrhVar);
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
                if (!zzgorVar.zza(intValue2)) {
                    obj = zzD(r5, intValue2, obj, zzgrhVar);
                    it.remove();
                }
            }
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzD(int r2, int r3, Object obj, zzgrh zzgrhVar) {
        if (obj == null) {
            obj = zzgrhVar.zzf();
        }
        zzgrhVar.zzl(obj, r2, r3);
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzE(zzgoa zzgoaVar, Object obj, Object obj2) {
        zzgoaVar.zza(obj2);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzF(zzgrh zzgrhVar, Object obj, Object obj2) {
        zzgrhVar.zzo(obj, zzgrhVar.zze(zzgrhVar.zzd(obj), zzgrhVar.zzd(obj2)));
    }

    public static void zzG(Class cls) {
        Class cls2;
        if (!zzgon.class.isAssignableFrom(cls) && (cls2 = zza) != null && !cls2.isAssignableFrom(cls)) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzH(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzI(zzgps zzgpsVar, Object obj, Object obj2, long j) {
        zzgrr.zzv(obj, j, zzgps.zzc(zzgrr.zzh(obj, j), zzgrr.zzh(obj2, j)));
    }

    public static void zzJ(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzc(r1, list, z);
    }

    public static void zzK(int r1, List list, zzgnv zzgnvVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zze(r1, list);
    }

    public static void zzL(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzg(r1, list, z);
    }

    public static void zzM(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzj(r1, list, z);
    }

    public static void zzN(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzl(r1, list, z);
    }

    public static void zzO(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzn(r1, list, z);
    }

    public static void zzP(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzp(r1, list, z);
    }

    public static void zzQ(int r2, List list, zzgnv zzgnvVar, zzgqq zzgqqVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int r0 = 0; r0 < list.size(); r0++) {
            zzgnvVar.zzq(r2, list.get(r0), zzgqqVar);
        }
    }

    public static void zzR(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzs(r1, list, z);
    }

    public static void zzS(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzu(r1, list, z);
    }

    public static void zzT(int r2, List list, zzgnv zzgnvVar, zzgqq zzgqqVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int r0 = 0; r0 < list.size(); r0++) {
            zzgnvVar.zzv(r2, list.get(r0), zzgqqVar);
        }
    }

    public static void zzU(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzx(r1, list, z);
    }

    public static void zzV(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzz(r1, list, z);
    }

    public static void zzW(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzB(r1, list, z);
    }

    public static void zzX(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzD(r1, list, z);
    }

    public static void zzY(int r1, List list, zzgnv zzgnvVar) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzG(r1, list);
    }

    public static void zzZ(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzI(r1, list, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zza(int r0, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * (zzgnu.zzE(r0 << 3) + 1);
    }

    public static void zzaa(int r1, List list, zzgnv zzgnvVar, boolean z) throws IOException {
        if (list == null || list.isEmpty()) {
            return;
        }
        zzgnvVar.zzK(r1, list, z);
    }

    private static zzgrh zzab(boolean z) {
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
            return (zzgrh) cls.getConstructor(Boolean.TYPE).newInstance(Boolean.valueOf(z));
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
        int zzD = size * zzgnu.zzD(r2);
        for (int r1 = 0; r1 < list.size(); r1++) {
            zzD += zzgnu.zzw((zzgnf) list.get(r1));
        }
        return zzD;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(int r0, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zze(list) + (size * zzgnu.zzD(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgoo) {
            zzgoo zzgooVar = (zzgoo) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzgnu.zzy(zzgooVar.zze(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzgnu.zzy(((Integer) list.get(r1)).intValue());
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
        return size * (zzgnu.zzE(r0 << 3) + 4);
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
        return size * (zzgnu.zzE(r0 << 3) + 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzi(List list) {
        return list.size() * 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(int r4, List list, zzgqq zzgqqVar) {
        int size = list.size();
        if (size != 0) {
            int r2 = 0;
            for (int r1 = 0; r1 < size; r1++) {
                r2 += zzgnu.zzx(r4, (zzgpx) list.get(r1), zzgqqVar);
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
        return zzl(list) + (size * zzgnu.zzD(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzl(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgoo) {
            zzgoo zzgooVar = (zzgoo) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzgnu.zzy(zzgooVar.zze(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzgnu.zzy(((Integer) list.get(r1)).intValue());
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
        return zzn(list) + (list.size() * zzgnu.zzD(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzn(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgpm) {
            zzgpm zzgpmVar = (zzgpm) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzgnu.zzF(zzgpmVar.zze(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzgnu.zzF(((Long) list.get(r1)).longValue());
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzo(int r1, Object obj, zzgqq zzgqqVar) {
        if (obj instanceof zzgpd) {
            int zzE = zzgnu.zzE(r1 << 3);
            int zza2 = ((zzgpd) obj).zza();
            return zzE + zzgnu.zzE(zza2) + zza2;
        }
        return zzgnu.zzE(r1 << 3) + zzgnu.zzA((zzgpx) obj, zzgqqVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzp(int r4, List list, zzgqq zzgqqVar) {
        int zzA;
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        int zzD = zzgnu.zzD(r4) * size;
        for (int r1 = 0; r1 < size; r1++) {
            Object obj = list.get(r1);
            if (obj instanceof zzgpd) {
                zzA = zzgnu.zzz((zzgpd) obj);
            } else {
                zzA = zzgnu.zzA((zzgpx) obj, zzgqqVar);
            }
            zzD += zzA;
        }
        return zzD;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzq(int r0, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzr(list) + (size * zzgnu.zzD(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzr(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgoo) {
            zzgoo zzgooVar = (zzgoo) list;
            r2 = 0;
            while (r1 < size) {
                int zze = zzgooVar.zze(r1);
                r2 += zzgnu.zzE((zze >> 31) ^ (zze + zze));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                int intValue = ((Integer) list.get(r1)).intValue();
                r2 += zzgnu.zzE((intValue >> 31) ^ (intValue + intValue));
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
        return zzt(list) + (size * zzgnu.zzD(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzt(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgpm) {
            zzgpm zzgpmVar = (zzgpm) list;
            r2 = 0;
            while (r1 < size) {
                long zze = zzgpmVar.zze(r1);
                r2 += zzgnu.zzF((zze >> 63) ^ (zze + zze));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                long longValue = ((Long) list.get(r1)).longValue();
                r2 += zzgnu.zzF((longValue >> 63) ^ (longValue + longValue));
                r1++;
            }
        }
        return r2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzu(int r4, List list) {
        int zzC;
        int zzC2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        int zzD = zzgnu.zzD(r4) * size;
        if (list instanceof zzgpf) {
            zzgpf zzgpfVar = (zzgpf) list;
            while (r1 < size) {
                Object zzf = zzgpfVar.zzf(r1);
                if (zzf instanceof zzgnf) {
                    zzC2 = zzgnu.zzw((zzgnf) zzf);
                } else {
                    zzC2 = zzgnu.zzC((String) zzf);
                }
                zzD += zzC2;
                r1++;
            }
        } else {
            while (r1 < size) {
                Object obj = list.get(r1);
                if (obj instanceof zzgnf) {
                    zzC = zzgnu.zzw((zzgnf) obj);
                } else {
                    zzC = zzgnu.zzC((String) obj);
                }
                zzD += zzC;
                r1++;
            }
        }
        return zzD;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(int r0, List list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return zzw(list) + (size * zzgnu.zzD(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzw(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgoo) {
            zzgoo zzgooVar = (zzgoo) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzgnu.zzE(zzgooVar.zze(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzgnu.zzE(((Integer) list.get(r1)).intValue());
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
        return zzy(list) + (size * zzgnu.zzD(r0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzy(List list) {
        int r2;
        int size = list.size();
        int r1 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof zzgpm) {
            zzgpm zzgpmVar = (zzgpm) list;
            r2 = 0;
            while (r1 < size) {
                r2 += zzgnu.zzF(zzgpmVar.zze(r1));
                r1++;
            }
        } else {
            r2 = 0;
            while (r1 < size) {
                r2 += zzgnu.zzF(((Long) list.get(r1)).longValue());
                r1++;
            }
        }
        return r2;
    }

    public static zzgrh zzz() {
        return zzb;
    }
}
