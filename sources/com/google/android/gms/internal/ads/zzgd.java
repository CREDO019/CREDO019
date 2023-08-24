package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgd {
    public byte[] zza;
    public byte[] zzb;
    public int zzc;
    public int[] zzd;
    public int[] zze;
    public int zzf;
    public int zzg;
    public int zzh;
    private final MediaCodec.CryptoInfo zzi;
    private final zzgc zzj;

    public zzgd() {
        MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
        this.zzi = cryptoInfo;
        this.zzj = zzel.zza >= 24 ? new zzgc(cryptoInfo, null) : null;
    }

    public final MediaCodec.CryptoInfo zza() {
        return this.zzi;
    }

    public final void zzb(int r4) {
        if (r4 == 0) {
            return;
        }
        if (this.zzd == null) {
            int[] r0 = new int[1];
            this.zzd = r0;
            this.zzi.numBytesOfClearData = r0;
        }
        int[] r02 = this.zzd;
        r02[0] = r02[0] + r4;
    }

    public final void zzc(int r2, int[] r3, int[] r4, byte[] bArr, byte[] bArr2, int r7, int r8, int r9) {
        this.zzf = r2;
        this.zzd = r3;
        this.zze = r4;
        this.zzb = bArr;
        this.zza = bArr2;
        this.zzc = r7;
        this.zzg = r8;
        this.zzh = r9;
        this.zzi.numSubSamples = r2;
        this.zzi.numBytesOfClearData = r3;
        this.zzi.numBytesOfEncryptedData = r4;
        this.zzi.key = bArr;
        this.zzi.iv = bArr2;
        this.zzi.mode = r7;
        if (zzel.zza >= 24) {
            zzgc zzgcVar = this.zzj;
            Objects.requireNonNull(zzgcVar);
            zzgc.zza(zzgcVar, r8, r9);
        }
    }
}
