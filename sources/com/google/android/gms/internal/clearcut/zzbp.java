package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzcg;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
final class zzbp implements zzfr {
    private final zzbn zzfo;

    private zzbp(zzbn zzbnVar) {
        zzbn zzbnVar2 = (zzbn) zzci.zza(zzbnVar, "output");
        this.zzfo = zzbnVar2;
        zzbnVar2.zzfz = this;
    }

    public static zzbp zza(zzbn zzbnVar) {
        return zzbnVar.zzfz != null ? zzbnVar.zzfz : new zzbp(zzbnVar);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r2, double d) throws IOException {
        this.zzfo.zza(r2, d);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r2, float f) throws IOException {
        this.zzfo.zza(r2, f);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r2, long j) throws IOException {
        this.zzfo.zza(r2, j);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r2, zzbb zzbbVar) throws IOException {
        this.zzfo.zza(r2, zzbbVar);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final <K, V> void zza(int r5, zzdh<K, V> zzdhVar, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zzfo.zzb(r5, 2);
            this.zzfo.zzo(zzdg.zza(zzdhVar, entry.getKey(), entry.getValue()));
            zzdg.zza(this.zzfo, zzdhVar, entry.getKey(), entry.getValue());
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r2, Object obj) throws IOException {
        if (obj instanceof zzbb) {
            this.zzfo.zzb(r2, (zzbb) obj);
        } else {
            this.zzfo.zzb(r2, (zzdo) obj);
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r2, Object obj, zzef zzefVar) throws IOException {
        this.zzfo.zza(r2, (zzdo) obj, zzefVar);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r2, String str) throws IOException {
        this.zzfo.zza(r2, str);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r5, List<String> list) throws IOException {
        int r1 = 0;
        if (!(list instanceof zzcx)) {
            while (r1 < list.size()) {
                this.zzfo.zza(r5, list.get(r1));
                r1++;
            }
            return;
        }
        zzcx zzcxVar = (zzcx) list;
        while (r1 < list.size()) {
            Object raw = zzcxVar.getRaw(r1);
            if (raw instanceof String) {
                this.zzfo.zza(r5, (String) raw);
            } else {
                this.zzfo.zza(r5, (zzbb) raw);
            }
            r1++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r3, List<?> list, zzef zzefVar) throws IOException {
        for (int r0 = 0; r0 < list.size(); r0++) {
            zza(r3, list.get(r0), zzefVar);
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zza(int r3, List<Integer> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zzc(r3, list.get(r0).intValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzbn.zzs(list.get(r32).intValue());
        }
        this.zzfo.zzo(r5);
        while (r0 < list.size()) {
            this.zzfo.zzn(list.get(r0).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzaa(int r3) throws IOException {
        this.zzfo.zzb(r3, 3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzab(int r3) throws IOException {
        this.zzfo.zzb(r3, 4);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final int zzaj() {
        return zzcg.zzg.zzko;
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzb(int r2, long j) throws IOException {
        this.zzfo.zzb(r2, j);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzb(int r3, Object obj, zzef zzefVar) throws IOException {
        zzbn zzbnVar = this.zzfo;
        zzbnVar.zzb(r3, 3);
        zzefVar.zza((zzdo) obj, zzbnVar.zzfz);
        zzbnVar.zzb(r3, 4);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzb(int r4, List<zzbb> list) throws IOException {
        for (int r0 = 0; r0 < list.size(); r0++) {
            this.zzfo.zza(r4, list.get(r0));
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzb(int r3, List<?> list, zzef zzefVar) throws IOException {
        for (int r0 = 0; r0 < list.size(); r0++) {
            zzb(r3, list.get(r0), zzefVar);
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzb(int r3, List<Integer> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zzf(r3, list.get(r0).intValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzbn.zzv(list.get(r32).intValue());
        }
        this.zzfo.zzo(r5);
        while (r0 < list.size()) {
            this.zzfo.zzq(list.get(r0).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzb(int r2, boolean z) throws IOException {
        this.zzfo.zzb(r2, z);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzc(int r2, int r3) throws IOException {
        this.zzfo.zzc(r2, r3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzc(int r2, long j) throws IOException {
        this.zzfo.zzc(r2, j);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzc(int r4, List<Long> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zza(r4, list.get(r0).longValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            r6 += zzbn.zze(list.get(r42).longValue());
        }
        this.zzfo.zzo(r6);
        while (r0 < list.size()) {
            this.zzfo.zzb(list.get(r0).longValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzd(int r2, int r3) throws IOException {
        this.zzfo.zzd(r2, r3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzd(int r4, List<Long> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zza(r4, list.get(r0).longValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            r6 += zzbn.zzf(list.get(r42).longValue());
        }
        this.zzfo.zzo(r6);
        while (r0 < list.size()) {
            this.zzfo.zzb(list.get(r0).longValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zze(int r2, int r3) throws IOException {
        this.zzfo.zze(r2, r3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zze(int r4, List<Long> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zzc(r4, list.get(r0).longValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            r6 += zzbn.zzh(list.get(r42).longValue());
        }
        this.zzfo.zzo(r6);
        while (r0 < list.size()) {
            this.zzfo.zzd(list.get(r0).longValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzf(int r2, int r3) throws IOException {
        this.zzfo.zzf(r2, r3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzf(int r3, List<Float> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zza(r3, list.get(r0).floatValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzbn.zzb(list.get(r32).floatValue());
        }
        this.zzfo.zzo(r5);
        while (r0 < list.size()) {
            this.zzfo.zza(list.get(r0).floatValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzg(int r4, List<Double> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zza(r4, list.get(r0).doubleValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            r6 += zzbn.zzb(list.get(r42).doubleValue());
        }
        this.zzfo.zzo(r6);
        while (r0 < list.size()) {
            this.zzfo.zza(list.get(r0).doubleValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzh(int r3, List<Integer> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zzc(r3, list.get(r0).intValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzbn.zzx(list.get(r32).intValue());
        }
        this.zzfo.zzo(r5);
        while (r0 < list.size()) {
            this.zzfo.zzn(list.get(r0).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzi(int r2, long j) throws IOException {
        this.zzfo.zza(r2, j);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzi(int r3, List<Boolean> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zzb(r3, list.get(r0).booleanValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzbn.zzb(list.get(r32).booleanValue());
        }
        this.zzfo.zzo(r5);
        while (r0 < list.size()) {
            this.zzfo.zza(list.get(r0).booleanValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzj(int r2, long j) throws IOException {
        this.zzfo.zzc(r2, j);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzj(int r3, List<Integer> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zzd(r3, list.get(r0).intValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzbn.zzt(list.get(r32).intValue());
        }
        this.zzfo.zzo(r5);
        while (r0 < list.size()) {
            this.zzfo.zzo(list.get(r0).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzk(int r3, List<Integer> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zzf(r3, list.get(r0).intValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzbn.zzw(list.get(r32).intValue());
        }
        this.zzfo.zzo(r5);
        while (r0 < list.size()) {
            this.zzfo.zzq(list.get(r0).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzl(int r4, List<Long> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zzc(r4, list.get(r0).longValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            r6 += zzbn.zzi(list.get(r42).longValue());
        }
        this.zzfo.zzo(r6);
        while (r0 < list.size()) {
            this.zzfo.zzd(list.get(r0).longValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzm(int r2, int r3) throws IOException {
        this.zzfo.zzf(r2, r3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzm(int r3, List<Integer> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zze(r3, list.get(r0).intValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r3, 2);
        int r5 = 0;
        for (int r32 = 0; r32 < list.size(); r32++) {
            r5 += zzbn.zzu(list.get(r32).intValue());
        }
        this.zzfo.zzo(r5);
        while (r0 < list.size()) {
            this.zzfo.zzp(list.get(r0).intValue());
            r0++;
        }
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzn(int r2, int r3) throws IOException {
        this.zzfo.zzc(r2, r3);
    }

    @Override // com.google.android.gms.internal.clearcut.zzfr
    public final void zzn(int r4, List<Long> list, boolean z) throws IOException {
        int r0 = 0;
        if (!z) {
            while (r0 < list.size()) {
                this.zzfo.zzb(r4, list.get(r0).longValue());
                r0++;
            }
            return;
        }
        this.zzfo.zzb(r4, 2);
        int r6 = 0;
        for (int r42 = 0; r42 < list.size(); r42++) {
            r6 += zzbn.zzg(list.get(r42).longValue());
        }
        this.zzfo.zzo(r6);
        while (r0 < list.size()) {
            this.zzfo.zzc(list.get(r0).longValue());
            r0++;
        }
    }
}
