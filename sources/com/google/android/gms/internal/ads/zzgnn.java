package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzgnn {
    public static final /* synthetic */ int zzd = 0;
    private static volatile int zze = 100;
    int zza;
    final int zzb = zze;
    zzgno zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgnn(zzgnm zzgnmVar) {
    }

    public static int zzF(int r1) {
        return (-(r1 & 1)) ^ (r1 >>> 1);
    }

    public static long zzG(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static zzgnn zzH(InputStream inputStream, int r3) {
        return new zzgnl(inputStream, 4096, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgnn zzI(byte[] bArr, int r8, int r9, boolean z) {
        zzgnh zzgnhVar = new zzgnh(bArr, r8, r9, z, null);
        try {
            zzgnhVar.zze(r9);
            return zzgnhVar;
        } catch (zzgoz e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract void zzA(int r1);

    public abstract boolean zzC() throws IOException;

    public abstract boolean zzD() throws IOException;

    public abstract boolean zzE(int r1) throws IOException;

    public abstract double zzb() throws IOException;

    public abstract float zzc() throws IOException;

    public abstract int zzd();

    public abstract int zze(int r1) throws zzgoz;

    public abstract int zzf() throws IOException;

    public abstract int zzg() throws IOException;

    public abstract int zzh() throws IOException;

    public abstract int zzk() throws IOException;

    public abstract int zzl() throws IOException;

    public abstract int zzm() throws IOException;

    public abstract int zzn() throws IOException;

    public abstract long zzo() throws IOException;

    public abstract long zzp() throws IOException;

    public abstract long zzt() throws IOException;

    public abstract long zzu() throws IOException;

    public abstract long zzv() throws IOException;

    public abstract zzgnf zzw() throws IOException;

    public abstract String zzx() throws IOException;

    public abstract String zzy() throws IOException;

    public abstract void zzz(int r1) throws zzgoz;
}
