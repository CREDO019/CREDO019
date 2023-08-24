package com.google.android.gms.internal.ads;

import java.util.PriorityQueue;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzbcv {
    static long zza(long j, int r8) {
        if (r8 == 1) {
            return j;
        }
        return ((r8 & 1) == 0 ? zza((j * j) % 1073807359, r8 >> 1) : j * (zza((j * j) % 1073807359, r8 >> 1) % 1073807359)) % 1073807359;
    }

    static String zzb(String[] strArr, int r3, int r4) {
        int r42 = r4 + r3;
        if (strArr.length < r42) {
            com.google.android.gms.ads.internal.util.zze.zzg("Unable to construct shingle");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int r1 = r42 - 1;
            if (r3 < r1) {
                sb.append(strArr[r3]);
                sb.append(' ');
                r3++;
            } else {
                sb.append(strArr[r1]);
                return sb.toString();
            }
        }
    }

    public static void zzc(String[] strArr, int r17, int r18, PriorityQueue priorityQueue) {
        int length = strArr.length;
        if (length < 6) {
            zzd(r17, zze(strArr, 0, length), zzb(strArr, 0, length), length, priorityQueue);
            return;
        }
        long zze = zze(strArr, 0, 6);
        zzd(r17, zze, zzb(strArr, 0, 6), 6, priorityQueue);
        long zza = zza(16785407L, 5);
        int r1 = 1;
        while (true) {
            int length2 = strArr.length;
            if (r1 >= length2 - 5) {
                return;
            }
            zze = ((((((zze + 1073807359) - ((((zzbcr.zza(strArr[r1 - 1]) + 2147483647L) % 1073807359) * zza) % 1073807359)) % 1073807359) * 16785407) % 1073807359) + ((zzbcr.zza(strArr[r1 + 5]) + 2147483647L) % 1073807359)) % 1073807359;
            zzd(r17, zze, zzb(strArr, r1, 6), length2, priorityQueue);
            r1++;
        }
    }

    static void zzd(int r2, long j, String str, int r6, PriorityQueue priorityQueue) {
        zzbcu zzbcuVar = new zzbcu(j, str, r6);
        if ((priorityQueue.size() != r2 || (((zzbcu) priorityQueue.peek()).zzc <= zzbcuVar.zzc && ((zzbcu) priorityQueue.peek()).zza <= zzbcuVar.zza)) && !priorityQueue.contains(zzbcuVar)) {
            priorityQueue.add(zzbcuVar);
            if (priorityQueue.size() > r2) {
                priorityQueue.poll();
            }
        }
    }

    private static long zze(String[] strArr, int r9, int r10) {
        long zza = (zzbcr.zza(strArr[0]) + 2147483647L) % 1073807359;
        for (int r92 = 1; r92 < r10; r92++) {
            zza = (((zza * 16785407) % 1073807359) + ((zzbcr.zza(strArr[r92]) + 2147483647L) % 1073807359)) % 1073807359;
        }
        return zza;
    }
}
