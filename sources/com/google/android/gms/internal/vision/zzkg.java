package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
interface zzkg {
    void zza(int r1, double d) throws IOException;

    void zza(int r1, float f) throws IOException;

    void zza(int r1, long j) throws IOException;

    void zza(int r1, zzfh zzfhVar) throws IOException;

    <K, V> void zza(int r1, zzht<K, V> zzhtVar, Map<K, V> map) throws IOException;

    void zza(int r1, Object obj) throws IOException;

    void zza(int r1, Object obj, zzir zzirVar) throws IOException;

    void zza(int r1, String str) throws IOException;

    void zza(int r1, List<String> list) throws IOException;

    void zza(int r1, List<?> list, zzir zzirVar) throws IOException;

    void zza(int r1, List<Integer> list, boolean z) throws IOException;

    void zza(int r1, boolean z) throws IOException;

    void zzb(int r1, long j) throws IOException;

    @Deprecated
    void zzb(int r1, Object obj, zzir zzirVar) throws IOException;

    void zzb(int r1, List<zzfh> list) throws IOException;

    @Deprecated
    void zzb(int r1, List<?> list, zzir zzirVar) throws IOException;

    void zzb(int r1, List<Integer> list, boolean z) throws IOException;

    @Deprecated
    void zzbj(int r1) throws IOException;

    @Deprecated
    void zzbk(int r1) throws IOException;

    void zzc(int r1, long j) throws IOException;

    void zzc(int r1, List<Long> list, boolean z) throws IOException;

    void zzd(int r1, List<Long> list, boolean z) throws IOException;

    void zze(int r1, List<Long> list, boolean z) throws IOException;

    void zzf(int r1, List<Float> list, boolean z) throws IOException;

    int zzfj();

    void zzg(int r1, List<Double> list, boolean z) throws IOException;

    void zzh(int r1, int r2) throws IOException;

    void zzh(int r1, List<Integer> list, boolean z) throws IOException;

    void zzi(int r1, int r2) throws IOException;

    void zzi(int r1, long j) throws IOException;

    void zzi(int r1, List<Boolean> list, boolean z) throws IOException;

    void zzj(int r1, int r2) throws IOException;

    void zzj(int r1, long j) throws IOException;

    void zzj(int r1, List<Integer> list, boolean z) throws IOException;

    void zzk(int r1, int r2) throws IOException;

    void zzk(int r1, List<Integer> list, boolean z) throws IOException;

    void zzl(int r1, List<Long> list, boolean z) throws IOException;

    void zzm(int r1, List<Integer> list, boolean z) throws IOException;

    void zzn(int r1, List<Long> list, boolean z) throws IOException;

    void zzr(int r1, int r2) throws IOException;

    void zzs(int r1, int r2) throws IOException;
}
