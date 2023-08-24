package com.google.android.gms.internal.vision;

import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public interface zzir<T> {
    boolean equals(T t, T t2);

    int hashCode(T t);

    T newInstance();

    void zza(T t, zzis zzisVar, zzgd zzgdVar) throws IOException;

    void zza(T t, zzkg zzkgVar) throws IOException;

    void zza(T t, byte[] bArr, int r3, int r4, zzfb zzfbVar) throws IOException;

    void zzd(T t, T t2);

    void zzg(T t);

    int zzr(T t);

    boolean zzt(T t);
}
