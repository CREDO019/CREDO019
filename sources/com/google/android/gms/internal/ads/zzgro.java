package com.google.android.gms.internal.ads;

import libcore.io.Memory;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgro extends zzgrq {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgro(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.gms.internal.ads.zzgrq
    public final byte zza(long j) {
        return Memory.peekByte((int) j);
    }

    @Override // com.google.android.gms.internal.ads.zzgrq
    public final double zzb(Object obj, long j) {
        return Double.longBitsToDouble(zzm(obj, j));
    }

    @Override // com.google.android.gms.internal.ads.zzgrq
    public final float zzc(Object obj, long j) {
        return Float.intBitsToFloat(zzl(obj, j));
    }

    @Override // com.google.android.gms.internal.ads.zzgrq
    public final void zzd(long j, byte[] bArr, long j2, long j3) {
        Memory.peekByteArray((int) j, bArr, (int) j2, (int) j3);
    }

    @Override // com.google.android.gms.internal.ads.zzgrq
    public final void zze(Object obj, long j, boolean z) {
        if (zzgrr.zzb) {
            zzgrr.zzG(obj, j, r3 ? (byte) 1 : (byte) 0);
        } else {
            zzgrr.zzH(obj, j, r3 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgrq
    public final void zzf(Object obj, long j, byte b) {
        if (zzgrr.zzb) {
            zzgrr.zzG(obj, j, b);
        } else {
            zzgrr.zzH(obj, j, b);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgrq
    public final void zzg(Object obj, long j, double d) {
        zzq(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.gms.internal.ads.zzgrq
    public final void zzh(Object obj, long j, float f) {
        zzp(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.gms.internal.ads.zzgrq
    public final boolean zzi(Object obj, long j) {
        if (zzgrr.zzb) {
            return zzgrr.zzw(obj, j);
        }
        return zzgrr.zzx(obj, j);
    }
}
