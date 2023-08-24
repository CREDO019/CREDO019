package com.google.android.gms.internal.vision;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public final class zzhu<K, V> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> void zza(zzga zzgaVar, zzht<K, V> zzhtVar, K k, V v) throws IOException {
        zzgi.zza(zzgaVar, zzhtVar.zzym, 1, k);
        zzgi.zza(zzgaVar, zzhtVar.zzyo, 2, v);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K, V> int zza(zzht<K, V> zzhtVar, K k, V v) {
        return zzgi.zza(zzhtVar.zzym, 1, k) + zzgi.zza(zzhtVar.zzyo, 2, v);
    }
}
