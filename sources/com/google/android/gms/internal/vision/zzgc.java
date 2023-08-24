package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzgs;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzgc implements zzkg {
    private final zzga zzsd;

    public static zzgc zza(zzga zzgaVar) {
        if (zzgaVar.zzss != null) {
            return zzgaVar.zzss;
        }
        return new zzgc(zzgaVar);
    }

    private zzgc(zzga zzgaVar) {
        zzga zzgaVar2 = (zzga) zzgt.zza(zzgaVar, "output");
        this.zzsd = zzgaVar2;
        zzgaVar2.zzss = this;
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final int zzfj() {
        return zzgs.zzf.zzww;
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzr(int r2, int r3) throws IOException {
        this.zzsd.zzk(r2, r3);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzi(int r2, long j) throws IOException {
        this.zzsd.zza(r2, j);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzj(int r2, long j) throws IOException {
        this.zzsd.zzc(r2, j);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zza(int r2, float f) throws IOException {
        this.zzsd.zza(r2, f);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zza(int r2, double d) throws IOException {
        this.zzsd.zza(r2, d);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzs(int r2, int r3) throws IOException {
        this.zzsd.zzh(r2, r3);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zza(int r2, long j) throws IOException {
        this.zzsd.zza(r2, j);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzh(int r2, int r3) throws IOException {
        this.zzsd.zzh(r2, r3);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzc(int r2, long j) throws IOException {
        this.zzsd.zzc(r2, j);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzk(int r2, int r3) throws IOException {
        this.zzsd.zzk(r2, r3);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zza(int r2, boolean z) throws IOException {
        this.zzsd.zza(r2, z);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zza(int r2, String str) throws IOException {
        this.zzsd.zza(r2, str);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zza(int r2, zzfh zzfhVar) throws IOException {
        this.zzsd.zza(r2, zzfhVar);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzi(int r2, int r3) throws IOException {
        this.zzsd.zzi(r2, r3);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzj(int r2, int r3) throws IOException {
        this.zzsd.zzj(r2, r3);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzb(int r2, long j) throws IOException {
        this.zzsd.zzb(r2, j);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zza(int r2, Object obj, zzir zzirVar) throws IOException {
        this.zzsd.zza(r2, (zzic) obj, zzirVar);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzb(int r3, Object obj, zzir zzirVar) throws IOException {
        zzga zzgaVar = this.zzsd;
        zzgaVar.zzg(r3, 3);
        zzirVar.zza((zzic) obj, zzgaVar.zzss);
        zzgaVar.zzg(r3, 4);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzbj(int r3) throws IOException {
        this.zzsd.zzg(r3, 3);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzbk(int r3) throws IOException {
        this.zzsd.zzg(r3, 4);
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zza(int r2, Object obj) throws IOException {
        if (obj instanceof zzfh) {
            this.zzsd.zzb(r2, (zzfh) obj);
        } else {
            this.zzsd.zza(r2, (zzic) obj);
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zza(int r3, List<Integer> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r3, 2);
            int r5 = 0;
            for (int r32 = 0; r32 < list.size(); r32++) {
                r5 += zzga.zzbb(list.get(r32).intValue());
            }
            this.zzsd.zzax(r5);
            while (r0 < list.size()) {
                this.zzsd.zzaw(list.get(r0).intValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zzh(r3, list.get(r0).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzb(int r3, List<Integer> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r3, 2);
            int r5 = 0;
            for (int r32 = 0; r32 < list.size(); r32++) {
                r5 += zzga.zzbe(list.get(r32).intValue());
            }
            this.zzsd.zzax(r5);
            while (r0 < list.size()) {
                this.zzsd.zzaz(list.get(r0).intValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zzk(r3, list.get(r0).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzc(int r4, List<Long> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r4, 2);
            int r6 = 0;
            for (int r42 = 0; r42 < list.size(); r42++) {
                r6 += zzga.zzv(list.get(r42).longValue());
            }
            this.zzsd.zzax(r6);
            while (r0 < list.size()) {
                this.zzsd.zzs(list.get(r0).longValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zza(r4, list.get(r0).longValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzd(int r4, List<Long> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r4, 2);
            int r6 = 0;
            for (int r42 = 0; r42 < list.size(); r42++) {
                r6 += zzga.zzw(list.get(r42).longValue());
            }
            this.zzsd.zzax(r6);
            while (r0 < list.size()) {
                this.zzsd.zzs(list.get(r0).longValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zza(r4, list.get(r0).longValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zze(int r4, List<Long> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r4, 2);
            int r6 = 0;
            for (int r42 = 0; r42 < list.size(); r42++) {
                r6 += zzga.zzy(list.get(r42).longValue());
            }
            this.zzsd.zzax(r6);
            while (r0 < list.size()) {
                this.zzsd.zzu(list.get(r0).longValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zzc(r4, list.get(r0).longValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzf(int r3, List<Float> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r3, 2);
            int r5 = 0;
            for (int r32 = 0; r32 < list.size(); r32++) {
                r5 += zzga.zzt(list.get(r32).floatValue());
            }
            this.zzsd.zzax(r5);
            while (r0 < list.size()) {
                this.zzsd.zzs(list.get(r0).floatValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zza(r3, list.get(r0).floatValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzg(int r4, List<Double> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r4, 2);
            int r6 = 0;
            for (int r42 = 0; r42 < list.size(); r42++) {
                r6 += zzga.zzb(list.get(r42).doubleValue());
            }
            this.zzsd.zzax(r6);
            while (r0 < list.size()) {
                this.zzsd.zza(list.get(r0).doubleValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zza(r4, list.get(r0).doubleValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzh(int r3, List<Integer> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r3, 2);
            int r5 = 0;
            for (int r32 = 0; r32 < list.size(); r32++) {
                r5 += zzga.zzbg(list.get(r32).intValue());
            }
            this.zzsd.zzax(r5);
            while (r0 < list.size()) {
                this.zzsd.zzaw(list.get(r0).intValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zzh(r3, list.get(r0).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzi(int r3, List<Boolean> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r3, 2);
            int r5 = 0;
            for (int r32 = 0; r32 < list.size(); r32++) {
                r5 += zzga.zzl(list.get(r32).booleanValue());
            }
            this.zzsd.zzax(r5);
            while (r0 < list.size()) {
                this.zzsd.zzk(list.get(r0).booleanValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zza(r3, list.get(r0).booleanValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zza(int r5, List<String> list) throws IOException {
        int r1 = 0;
        if (list instanceof zzhj) {
            zzhj zzhjVar = (zzhj) list;
            while (r1 < list.size()) {
                Object raw = zzhjVar.getRaw(r1);
                if (raw instanceof String) {
                    this.zzsd.zza(r5, (String) raw);
                } else {
                    this.zzsd.zza(r5, (zzfh) raw);
                }
                r1++;
            }
            return;
        }
        while (r1 < list.size()) {
            this.zzsd.zza(r5, list.get(r1));
            r1++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzb(int r4, List<zzfh> list) throws IOException {
        for (int r0 = 0; r0 < list.size(); r0++) {
            this.zzsd.zza(r4, list.get(r0));
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzj(int r3, List<Integer> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r3, 2);
            int r5 = 0;
            for (int r32 = 0; r32 < list.size(); r32++) {
                r5 += zzga.zzbc(list.get(r32).intValue());
            }
            this.zzsd.zzax(r5);
            while (r0 < list.size()) {
                this.zzsd.zzax(list.get(r0).intValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zzi(r3, list.get(r0).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzk(int r3, List<Integer> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r3, 2);
            int r5 = 0;
            for (int r32 = 0; r32 < list.size(); r32++) {
                r5 += zzga.zzbf(list.get(r32).intValue());
            }
            this.zzsd.zzax(r5);
            while (r0 < list.size()) {
                this.zzsd.zzaz(list.get(r0).intValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zzk(r3, list.get(r0).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzl(int r4, List<Long> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r4, 2);
            int r6 = 0;
            for (int r42 = 0; r42 < list.size(); r42++) {
                r6 += zzga.zzz(list.get(r42).longValue());
            }
            this.zzsd.zzax(r6);
            while (r0 < list.size()) {
                this.zzsd.zzu(list.get(r0).longValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zzc(r4, list.get(r0).longValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzm(int r3, List<Integer> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r3, 2);
            int r5 = 0;
            for (int r32 = 0; r32 < list.size(); r32++) {
                r5 += zzga.zzbd(list.get(r32).intValue());
            }
            this.zzsd.zzax(r5);
            while (r0 < list.size()) {
                this.zzsd.zzay(list.get(r0).intValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zzj(r3, list.get(r0).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzn(int r4, List<Long> list, boolean z) throws IOException {
        int r0 = 0;
        if (z) {
            this.zzsd.zzg(r4, 2);
            int r6 = 0;
            for (int r42 = 0; r42 < list.size(); r42++) {
                r6 += zzga.zzx(list.get(r42).longValue());
            }
            this.zzsd.zzax(r6);
            while (r0 < list.size()) {
                this.zzsd.zzt(list.get(r0).longValue());
                r0++;
            }
            return;
        }
        while (r0 < list.size()) {
            this.zzsd.zzb(r4, list.get(r0).longValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zza(int r3, List<?> list, zzir zzirVar) throws IOException {
        for (int r0 = 0; r0 < list.size(); r0++) {
            zza(r3, list.get(r0), zzirVar);
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final void zzb(int r3, List<?> list, zzir zzirVar) throws IOException {
        for (int r0 = 0; r0 < list.size(); r0++) {
            zzb(r3, list.get(r0), zzirVar);
        }
    }

    @Override // com.google.android.gms.internal.vision.zzkg
    public final <K, V> void zza(int r5, zzht<K, V> zzhtVar, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zzsd.zzg(r5, 2);
            this.zzsd.zzax(zzhu.zza(zzhtVar, entry.getKey(), entry.getValue()));
            zzhu.zza(this.zzsd, zzhtVar, entry.getKey(), entry.getValue());
        }
    }
}
