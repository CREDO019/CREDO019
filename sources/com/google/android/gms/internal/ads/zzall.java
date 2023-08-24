package com.google.android.gms.internal.ads;

import java.io.Closeable;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzall extends zzguc implements Closeable {
    private static final zzguj zza = zzguj.zzb(zzall.class);

    public zzall(zzgud zzgudVar, zzalk zzalkVar) throws IOException {
        zzf(zzgudVar, zzgudVar.zzc(), zzalkVar);
    }

    @Override // com.google.android.gms.internal.ads.zzguc, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
    }

    @Override // com.google.android.gms.internal.ads.zzguc
    public final String toString() {
        String obj = this.zzd.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(obj).length() + 7);
        sb.append("model(");
        sb.append(obj);
        sb.append(")");
        return sb.toString();
    }
}
