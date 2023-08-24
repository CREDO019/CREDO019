package com.google.android.gms.internal.ads;

import android.media.MediaCodec;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaul {
    public byte[] zza;
    public byte[] zzb;
    public int zzc;
    public int[] zzd;
    public int[] zze;
    public int zzf;
    private final MediaCodec.CryptoInfo zzg;
    private final zzauk zzh;

    public zzaul() {
        MediaCodec.CryptoInfo cryptoInfo = zzban.zza >= 16 ? new MediaCodec.CryptoInfo() : null;
        this.zzg = cryptoInfo;
        this.zzh = zzban.zza >= 24 ? new zzauk(cryptoInfo, null) : null;
    }

    public final MediaCodec.CryptoInfo zza() {
        return this.zzg;
    }

    public final void zzb(int r1, int[] r2, int[] r3, byte[] bArr, byte[] bArr2, int r6) {
        this.zzf = r1;
        this.zzd = r2;
        this.zze = r3;
        this.zzb = bArr;
        this.zza = bArr2;
        this.zzc = 1;
        if (zzban.zza >= 16) {
            this.zzg.numSubSamples = this.zzf;
            this.zzg.numBytesOfClearData = this.zzd;
            this.zzg.numBytesOfEncryptedData = this.zze;
            this.zzg.key = this.zzb;
            this.zzg.iv = this.zza;
            this.zzg.mode = this.zzc;
            if (zzban.zza >= 24) {
                zzauk.zza(this.zzh, 0, 0);
            }
        }
    }
}
