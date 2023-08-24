package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.2 */
/* loaded from: classes3.dex */
final class zzjk implements zznd {
    private final zzjj zza;

    private zzjk(zzjj zzjjVar) {
        zzkk.zzf(zzjjVar, "output");
        this.zza = zzjjVar;
        zzjjVar.zza = this;
    }

    public static zzjk zza(zzjj zzjjVar) {
        zzjk zzjkVar = zzjjVar.zza;
        return zzjkVar != null ? zzjkVar : new zzjk(zzjjVar);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzA(int r3, int r4) throws IOException {
        this.zza.zzp(r3, (r4 >> 31) ^ (r4 + r4));
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzC(int r5, long j) throws IOException {
        this.zza.zzr(r5, (j >> 63) ^ (j + j));
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    @Deprecated
    public final void zzE(int r3) throws IOException {
        this.zza.zzo(r3, 3);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzF(int r2, String str) throws IOException {
        this.zza.zzm(r2, str);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzG(int r5, List list) throws IOException {
        int r1 = 0;
        if (!(list instanceof zzkr)) {
            while (r1 < list.size()) {
                this.zza.zzm(r5, (String) list.get(r1));
                r1++;
            }
            return;
        }
        zzkr zzkrVar = (zzkr) list;
        while (r1 < list.size()) {
            Object zzf = zzkrVar.zzf(r1);
            if (zzf instanceof String) {
                this.zza.zzm(r5, (String) zzf);
            } else {
                this.zza.zze(r5, (zzjb) zzf);
            }
            r1++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzH(int r2, int r3) throws IOException {
        this.zza.zzp(r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzJ(int r2, long j) throws IOException {
        this.zza.zzr(r2, j);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzb(int r2, boolean z) throws IOException {
        this.zza.zzd(r2, z);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzd(int r2, zzjb zzjbVar) throws IOException {
        this.zza.zze(r2, zzjbVar);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zze(int r4, List list) throws IOException {
        for (int r0 = 0; r0 < list.size(); r0++) {
            this.zza.zze(r4, (zzjb) list.get(r0));
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzf(int r2, double d) throws IOException {
        this.zza.zzh(r2, Double.doubleToRawLongBits(d));
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    @Deprecated
    public final void zzh(int r3) throws IOException {
        this.zza.zzo(r3, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzi(int r2, int r3) throws IOException {
        this.zza.zzj(r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzk(int r2, int r3) throws IOException {
        this.zza.zzf(r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzm(int r2, long j) throws IOException {
        this.zza.zzh(r2, j);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzo(int r2, float f) throws IOException {
        this.zza.zzf(r2, Float.floatToRawIntBits(f));
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzq(int r3, Object obj, zzlu zzluVar) throws IOException {
        zzjj zzjjVar = this.zza;
        zzjjVar.zzo(r3, 3);
        zzluVar.zzi((zzlj) obj, zzjjVar.zza);
        zzjjVar.zzo(r3, 4);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzr(int r2, int r3) throws IOException {
        this.zza.zzj(r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzt(int r2, long j) throws IOException {
        this.zza.zzr(r2, j);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzv(int r4, Object obj, zzlu zzluVar) throws IOException {
        Object obj2 = (zzlj) obj;
        zzjg zzjgVar = (zzjg) this.zza;
        zzjgVar.zzq((r4 << 3) | 2);
        zzil zzilVar = (zzil) obj2;
        int zzbu = zzilVar.zzbu();
        if (zzbu == -1) {
            zzbu = zzluVar.zza(zzilVar);
            zzilVar.zzbx(zzbu);
        }
        zzjgVar.zzq(zzbu);
        zzluVar.zzi(obj2, zzjgVar.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzw(int r2, int r3) throws IOException {
        this.zza.zzf(r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzy(int r2, long j) throws IOException {
        this.zza.zzh(r2, j);
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzB(int r4, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                zzjj zzjjVar = this.zza;
                int intValue = ((Integer) list.get(r0)).intValue();
                zzjjVar.zzp(r4, (intValue >> 31) ^ (intValue + intValue));
                r0++;
            }
            return;
        }
        this.zza.zzo(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            int intValue2 = ((Integer) list.get(r42)).intValue();
            r6 += zzjj.zzA((intValue2 >> 31) ^ (intValue2 + intValue2));
        }
        this.zza.zzq(r6);
        while (r0 < list.size()) {
            zzjj zzjjVar2 = this.zza;
            int intValue3 = ((Integer) list.get(r0)).intValue();
            zzjjVar2.zzq((intValue3 >> 31) ^ (intValue3 + intValue3));
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzD(int r7, List list, boolean z) throws IOException {
        int r1 = 0;
        if (!z) {
            while (r1 < list.size()) {
                zzjj zzjjVar = this.zza;
                long longValue = ((Long) list.get(r1)).longValue();
                zzjjVar.zzr(r7, (longValue >> 63) ^ (longValue + longValue));
                r1++;
            }
            return;
        }
        this.zza.zzo(r7, 2);
        int r9 = 0;
        for (int r72 = 0; r72 < list.size(); r72++) {
            long longValue2 = ((Long) list.get(r72)).longValue();
            r9 += zzjj.zzB((longValue2 >> 63) ^ (longValue2 + longValue2));
        }
        this.zza.zzq(r9);
        while (r1 < list.size()) {
            zzjj zzjjVar2 = this.zza;
            long longValue3 = ((Long) list.get(r1)).longValue();
            zzjjVar2.zzs((longValue3 >> 63) ^ (longValue3 + longValue3));
            r1++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzI(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzp(r3, ((Integer) list.get(r0)).intValue());
                r0++;
            }
            return;
        }
        this.zza.zzo(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzjj.zzA(((Integer) list.get(r32)).intValue());
        }
        this.zza.zzq(r5);
        while (r0 < list.size()) {
            this.zza.zzq(((Integer) list.get(r0)).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzK(int r4, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzr(r4, ((Long) list.get(r0)).longValue());
                r0++;
            }
            return;
        }
        this.zza.zzo(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            r6 += zzjj.zzB(((Long) list.get(r42)).longValue());
        }
        this.zza.zzq(r6);
        while (r0 < list.size()) {
            this.zza.zzs(((Long) list.get(r0)).longValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzc(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzd(r3, ((Boolean) list.get(r0)).booleanValue());
                r0++;
            }
            return;
        }
        this.zza.zzo(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            ((Boolean) list.get(r32)).booleanValue();
            r5++;
        }
        this.zza.zzq(r5);
        while (r0 < list.size()) {
            this.zza.zzb(((Boolean) list.get(r0)).booleanValue() ? (byte) 1 : (byte) 0);
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzj(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzj(r3, ((Integer) list.get(r0)).intValue());
                r0++;
            }
            return;
        }
        this.zza.zzo(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzjj.zzv(((Integer) list.get(r32)).intValue());
        }
        this.zza.zzq(r5);
        while (r0 < list.size()) {
            this.zza.zzk(((Integer) list.get(r0)).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzl(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzf(r3, ((Integer) list.get(r0)).intValue());
                r0++;
            }
            return;
        }
        this.zza.zzo(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            ((Integer) list.get(r32)).intValue();
            r5 += 4;
        }
        this.zza.zzq(r5);
        while (r0 < list.size()) {
            this.zza.zzg(((Integer) list.get(r0)).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzn(int r4, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzh(r4, ((Long) list.get(r0)).longValue());
                r0++;
            }
            return;
        }
        this.zza.zzo(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            ((Long) list.get(r42)).longValue();
            r6 += 8;
        }
        this.zza.zzq(r6);
        while (r0 < list.size()) {
            this.zza.zzi(((Long) list.get(r0)).longValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzs(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzj(r3, ((Integer) list.get(r0)).intValue());
                r0++;
            }
            return;
        }
        this.zza.zzo(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzjj.zzv(((Integer) list.get(r32)).intValue());
        }
        this.zza.zzq(r5);
        while (r0 < list.size()) {
            this.zza.zzk(((Integer) list.get(r0)).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzu(int r4, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzr(r4, ((Long) list.get(r0)).longValue());
                r0++;
            }
            return;
        }
        this.zza.zzo(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            r6 += zzjj.zzB(((Long) list.get(r42)).longValue());
        }
        this.zza.zzq(r6);
        while (r0 < list.size()) {
            this.zza.zzs(((Long) list.get(r0)).longValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzx(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzf(r3, ((Integer) list.get(r0)).intValue());
                r0++;
            }
            return;
        }
        this.zza.zzo(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            ((Integer) list.get(r32)).intValue();
            r5 += 4;
        }
        this.zza.zzq(r5);
        while (r0 < list.size()) {
            this.zza.zzg(((Integer) list.get(r0)).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzz(int r4, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzh(r4, ((Long) list.get(r0)).longValue());
                r0++;
            }
            return;
        }
        this.zza.zzo(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            ((Long) list.get(r42)).longValue();
            r6 += 8;
        }
        this.zza.zzq(r6);
        while (r0 < list.size()) {
            this.zza.zzi(((Long) list.get(r0)).longValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzg(int r4, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzh(r4, Double.doubleToRawLongBits(((Double) list.get(r0)).doubleValue()));
                r0++;
            }
            return;
        }
        this.zza.zzo(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            ((Double) list.get(r42)).doubleValue();
            r6 += 8;
        }
        this.zza.zzq(r6);
        while (r0 < list.size()) {
            this.zza.zzi(Double.doubleToRawLongBits(((Double) list.get(r0)).doubleValue()));
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zznd
    public final void zzp(int r3, List list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zza.zzf(r3, Float.floatToRawIntBits(((Float) list.get(r0)).floatValue()));
                r0++;
            }
            return;
        }
        this.zza.zzo(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            ((Float) list.get(r32)).floatValue();
            r5 += 4;
        }
        this.zza.zzq(r5);
        while (r0 < list.size()) {
            this.zza.zzg(Float.floatToRawIntBits(((Float) list.get(r0)).floatValue()));
            r0++;
        }
    }
}
