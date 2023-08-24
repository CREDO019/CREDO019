package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPublicKey;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzglj implements zzgaa {
    private static final byte[] zza = new byte[0];
    private final zzglm zzb;
    private final String zzc;
    private final byte[] zzd;
    private final zzglh zze;
    private final int zzf;

    public zzglj(ECPublicKey eCPublicKey, byte[] bArr, String str, int r6, zzglh zzglhVar) throws GeneralSecurityException {
        zzgln.zzc(eCPublicKey.getW(), eCPublicKey.getParams().getCurve());
        this.zzb = new zzglm(eCPublicKey);
        this.zzd = bArr;
        this.zzc = str;
        this.zzf = r6;
        this.zze = zzglhVar;
    }

    @Override // com.google.android.gms.internal.ads.zzgaa
    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        zzgll zza2 = this.zzb.zza(this.zzc, this.zzd, bArr2, this.zze.zza(), this.zzf);
        byte[] zza3 = this.zze.zzb(zza2.zzb()).zza(bArr, zza);
        byte[] zza4 = zza2.zza();
        return ByteBuffer.allocate(zza4.length + zza3.length).put(zza4).put(zza3).array();
    }
}
