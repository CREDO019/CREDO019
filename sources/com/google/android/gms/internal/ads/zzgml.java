package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgml {
    private final byte[] zza;

    private zzgml(byte[] bArr, int r3, int r4) {
        byte[] bArr2 = new byte[r4];
        this.zza = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, r4);
    }

    public static zzgml zza(byte[] bArr, int r2, int r3) {
        return new zzgml(bArr, 0, r3);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzgml) {
            return Arrays.equals(((zzgml) obj).zza, this.zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final String toString() {
        String zza = zzglz.zza(this.zza);
        return "Bytes(" + zza + ")";
    }

    public final byte[] zzb() {
        byte[] bArr = this.zza;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }
}
