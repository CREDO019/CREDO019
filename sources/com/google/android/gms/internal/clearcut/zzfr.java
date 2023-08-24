package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
interface zzfr {
    void zza(int r1, double d) throws IOException;

    void zza(int r1, float f) throws IOException;

    void zza(int r1, long j) throws IOException;

    void zza(int r1, zzbb zzbbVar) throws IOException;

    <K, V> void zza(int r1, zzdh<K, V> zzdhVar, Map<K, V> map) throws IOException;

    void zza(int r1, Object obj) throws IOException;

    void zza(int r1, Object obj, zzef zzefVar) throws IOException;

    void zza(int r1, String str) throws IOException;

    void zza(int r1, List<String> list) throws IOException;

    void zza(int r1, List<?> list, zzef zzefVar) throws IOException;

    void zza(int r1, List<Integer> list, boolean z) throws IOException;

    @Deprecated
    void zzaa(int r1) throws IOException;

    @Deprecated
    void zzab(int r1) throws IOException;

    int zzaj();

    void zzb(int r1, long j) throws IOException;

    @Deprecated
    void zzb(int r1, Object obj, zzef zzefVar) throws IOException;

    void zzb(int r1, List<zzbb> list) throws IOException;

    @Deprecated
    void zzb(int r1, List<?> list, zzef zzefVar) throws IOException;

    void zzb(int r1, List<Integer> list, boolean z) throws IOException;

    void zzb(int r1, boolean z) throws IOException;

    void zzc(int r1, int r2) throws IOException;

    void zzc(int r1, long j) throws IOException;

    void zzc(int r1, List<Long> list, boolean z) throws IOException;

    void zzd(int r1, int r2) throws IOException;

    void zzd(int r1, List<Long> list, boolean z) throws IOException;

    void zze(int r1, int r2) throws IOException;

    void zze(int r1, List<Long> list, boolean z) throws IOException;

    void zzf(int r1, int r2) throws IOException;

    void zzf(int r1, List<Float> list, boolean z) throws IOException;

    void zzg(int r1, List<Double> list, boolean z) throws IOException;

    void zzh(int r1, List<Integer> list, boolean z) throws IOException;

    void zzi(int r1, long j) throws IOException;

    void zzi(int r1, List<Boolean> list, boolean z) throws IOException;

    void zzj(int r1, long j) throws IOException;

    void zzj(int r1, List<Integer> list, boolean z) throws IOException;

    void zzk(int r1, List<Integer> list, boolean z) throws IOException;

    void zzl(int r1, List<Long> list, boolean z) throws IOException;

    void zzm(int r1, int r2) throws IOException;

    void zzm(int r1, List<Integer> list, boolean z) throws IOException;

    void zzn(int r1, int r2) throws IOException;

    void zzn(int r1, List<Long> list, boolean z) throws IOException;
}
