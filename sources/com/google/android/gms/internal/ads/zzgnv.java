package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgnv {
    private final zzgnu zza;

    private zzgnv(zzgnu zzgnuVar) {
        zzgox.zzf(zzgnuVar, "output");
        this.zza = zzgnuVar;
        zzgnuVar.zze = this;
    }

    public static zzgnv zza(zzgnu zzgnuVar) {
        zzgnv zzgnvVar = zzgnuVar.zze;
        return zzgnvVar != null ? zzgnvVar : new zzgnv(zzgnuVar);
    }

    public final void zzA(int r3, int r4) throws IOException {
        this.zza.zzr(r3, (r4 >> 31) ^ (r4 + r4));
    }

    public final void zzC(int r5, long j) throws IOException {
        this.zza.zzt(r5, (j >> 63) ^ (j + j));
    }

    @Deprecated
    public final void zzE(int r3) throws IOException {
        this.zza.zzq(r3, 3);
    }

    public final void zzF(int r2, String str) throws IOException {
        this.zza.zzo(r2, str);
    }

    public final void zzG(int r5, List list) throws IOException {
        int r1 = 0;
        if (!(list instanceof zzgpf)) {
            while (r1 < list.size()) {
                this.zza.zzo(r5, (String) list.get(r1));
                r1++;
            }
            return;
        }
        zzgpf zzgpfVar = (zzgpf) list;
        while (r1 < list.size()) {
            Object zzf = zzgpfVar.zzf(r1);
            if (zzf instanceof String) {
                this.zza.zzo(r5, (String) zzf);
            } else {
                this.zza.zzQ(r5, (zzgnf) zzf);
            }
            r1++;
        }
    }

    public final void zzH(int r2, int r3) throws IOException {
        this.zza.zzr(r2, r3);
    }

    public final void zzJ(int r2, long j) throws IOException {
        this.zza.zzt(r2, j);
    }

    public final void zzb(int r2, boolean z) throws IOException {
        this.zza.zzP(r2, z);
    }

    public final void zzd(int r2, zzgnf zzgnfVar) throws IOException {
        this.zza.zzQ(r2, zzgnfVar);
    }

    public final void zze(int r4, List list) throws IOException {
        for (int r0 = 0; r0 < list.size(); r0++) {
            this.zza.zzQ(r4, (zzgnf) list.get(r0));
        }
    }

    public final void zzf(int r2, double d) throws IOException {
        this.zza.zzj(r2, Double.doubleToRawLongBits(d));
    }

    @Deprecated
    public final void zzh(int r3) throws IOException {
        this.zza.zzq(r3, 4);
    }

    public final void zzi(int r2, int r3) throws IOException {
        this.zza.zzl(r2, r3);
    }

    public final void zzk(int r2, int r3) throws IOException {
        this.zza.zzh(r2, r3);
    }

    public final void zzm(int r2, long j) throws IOException {
        this.zza.zzj(r2, j);
    }

    public final void zzo(int r2, float f) throws IOException {
        this.zza.zzh(r2, Float.floatToRawIntBits(f));
    }

    public final void zzq(int r3, Object obj, zzgqq zzgqqVar) throws IOException {
        zzgnu zzgnuVar = this.zza;
        zzgnuVar.zzq(r3, 3);
        zzgqqVar.zzn((zzgpx) obj, zzgnuVar.zze);
        zzgnuVar.zzq(r3, 4);
    }

    public final void zzr(int r2, int r3) throws IOException {
        this.zza.zzl(r2, r3);
    }

    public final void zzt(int r2, long j) throws IOException {
        this.zza.zzt(r2, j);
    }

    public final void zzv(int r2, Object obj, zzgqq zzgqqVar) throws IOException {
        this.zza.zzn(r2, (zzgpx) obj, zzgqqVar);
    }

    public final void zzw(int r2, int r3) throws IOException {
        this.zza.zzh(r2, r3);
    }

    public final void zzy(int r2, long j) throws IOException {
        this.zza.zzj(r2, j);
    }

    public final void zzB(int r4, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                zzgnu zzgnuVar = this.zza;
                int intValue = ((Integer) list.get(r0)).intValue();
                zzgnuVar.zzr(r4, (intValue >> 31) ^ (intValue + intValue));
                r0++;
            }
            return;
        }
        this.zza.zzq(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            int intValue2 = ((Integer) list.get(r42)).intValue();
            r6 += zzgnu.zzE((intValue2 >> 31) ^ (intValue2 + intValue2));
        }
        this.zza.zzs(r6);
        while (r0 < list.size()) {
            zzgnu zzgnuVar2 = this.zza;
            int intValue3 = ((Integer) list.get(r0)).intValue();
            zzgnuVar2.zzs((intValue3 >> 31) ^ (intValue3 + intValue3));
            r0++;
        }
    }

    public final void zzD(int r7, List list, boolean z) throws IOException {
        int r1 = 0;
        if (!z) {
            while (r1 < list.size()) {
                zzgnu zzgnuVar = this.zza;
                long longValue = ((Long) list.get(r1)).longValue();
                zzgnuVar.zzt(r7, (longValue >> 63) ^ (longValue + longValue));
                r1++;
            }
            return;
        }
        this.zza.zzq(r7, 2);
        int r9 = 0;
        for (int r72 = 0; r72 < list.size(); r72++) {
            long longValue2 = ((Long) list.get(r72)).longValue();
            r9 += zzgnu.zzF((longValue2 >> 63) ^ (longValue2 + longValue2));
        }
        this.zza.zzs(r9);
        while (r1 < list.size()) {
            zzgnu zzgnuVar2 = this.zza;
            long longValue3 = ((Long) list.get(r1)).longValue();
            zzgnuVar2.zzu((longValue3 >> 63) ^ (longValue3 + longValue3));
            r1++;
        }
    }

    public final void zzI(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzr(r3, ((Integer) list.get(r0)).intValue());
                r0++;
            }
            return;
        }
        this.zza.zzq(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzgnu.zzE(((Integer) list.get(r32)).intValue());
        }
        this.zza.zzs(r5);
        while (r0 < list.size()) {
            this.zza.zzs(((Integer) list.get(r0)).intValue());
            r0++;
        }
    }

    public final void zzK(int r4, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzt(r4, ((Long) list.get(r0)).longValue());
                r0++;
            }
            return;
        }
        this.zza.zzq(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            r6 += zzgnu.zzF(((Long) list.get(r42)).longValue());
        }
        this.zza.zzs(r6);
        while (r0 < list.size()) {
            this.zza.zzu(((Long) list.get(r0)).longValue());
            r0++;
        }
    }

    public final void zzc(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzP(r3, ((Boolean) list.get(r0)).booleanValue());
                r0++;
            }
            return;
        }
        this.zza.zzq(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            ((Boolean) list.get(r32)).booleanValue();
            r5++;
        }
        this.zza.zzs(r5);
        while (r0 < list.size()) {
            this.zza.zzO(((Boolean) list.get(r0)).booleanValue() ? (byte) 1 : (byte) 0);
            r0++;
        }
    }

    public final void zzj(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzl(r3, ((Integer) list.get(r0)).intValue());
                r0++;
            }
            return;
        }
        this.zza.zzq(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzgnu.zzy(((Integer) list.get(r32)).intValue());
        }
        this.zza.zzs(r5);
        while (r0 < list.size()) {
            this.zza.zzm(((Integer) list.get(r0)).intValue());
            r0++;
        }
    }

    public final void zzl(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzh(r3, ((Integer) list.get(r0)).intValue());
                r0++;
            }
            return;
        }
        this.zza.zzq(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            ((Integer) list.get(r32)).intValue();
            r5 += 4;
        }
        this.zza.zzs(r5);
        while (r0 < list.size()) {
            this.zza.zzi(((Integer) list.get(r0)).intValue());
            r0++;
        }
    }

    public final void zzn(int r4, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzj(r4, ((Long) list.get(r0)).longValue());
                r0++;
            }
            return;
        }
        this.zza.zzq(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            ((Long) list.get(r42)).longValue();
            r6 += 8;
        }
        this.zza.zzs(r6);
        while (r0 < list.size()) {
            this.zza.zzk(((Long) list.get(r0)).longValue());
            r0++;
        }
    }

    public final void zzs(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzl(r3, ((Integer) list.get(r0)).intValue());
                r0++;
            }
            return;
        }
        this.zza.zzq(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzgnu.zzy(((Integer) list.get(r32)).intValue());
        }
        this.zza.zzs(r5);
        while (r0 < list.size()) {
            this.zza.zzm(((Integer) list.get(r0)).intValue());
            r0++;
        }
    }

    public final void zzu(int r4, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzt(r4, ((Long) list.get(r0)).longValue());
                r0++;
            }
            return;
        }
        this.zza.zzq(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            r6 += zzgnu.zzF(((Long) list.get(r42)).longValue());
        }
        this.zza.zzs(r6);
        while (r0 < list.size()) {
            this.zza.zzu(((Long) list.get(r0)).longValue());
            r0++;
        }
    }

    public final void zzx(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzh(r3, ((Integer) list.get(r0)).intValue());
                r0++;
            }
            return;
        }
        this.zza.zzq(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            ((Integer) list.get(r32)).intValue();
            r5 += 4;
        }
        this.zza.zzs(r5);
        while (r0 < list.size()) {
            this.zza.zzi(((Integer) list.get(r0)).intValue());
            r0++;
        }
    }

    public final void zzz(int r4, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzj(r4, ((Long) list.get(r0)).longValue());
                r0++;
            }
            return;
        }
        this.zza.zzq(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            ((Long) list.get(r42)).longValue();
            r6 += 8;
        }
        this.zza.zzs(r6);
        while (r0 < list.size()) {
            this.zza.zzk(((Long) list.get(r0)).longValue());
            r0++;
        }
    }

    public final void zzg(int r4, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzj(r4, Double.doubleToRawLongBits(((Double) list.get(r0)).doubleValue()));
                r0++;
            }
            return;
        }
        this.zza.zzq(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            ((Double) list.get(r42)).doubleValue();
            r6 += 8;
        }
        this.zza.zzs(r6);
        while (r0 < list.size()) {
            this.zza.zzk(Double.doubleToRawLongBits(((Double) list.get(r0)).doubleValue()));
            r0++;
        }
    }

    public final void zzp(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzh(r3, Float.floatToRawIntBits(((Float) list.get(r0)).floatValue()));
                r0++;
            }
            return;
        }
        this.zza.zzq(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            ((Float) list.get(r32)).floatValue();
            r5 += 4;
        }
        this.zza.zzs(r5);
        while (r0 < list.size()) {
            this.zza.zzi(Float.floatToRawIntBits(((Float) list.get(r0)).floatValue()));
            r0++;
        }
    }
}
