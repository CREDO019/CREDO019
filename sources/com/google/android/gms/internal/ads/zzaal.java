package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaal {
    public final int zza;
    public final byte[] zzb;
    public final int zzc;
    public final int zzd;

    public zzaal(int r1, byte[] bArr, int r3, int r4) {
        this.zza = r1;
        this.zzb = bArr;
        this.zzc = r3;
        this.zzd = r4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzaal zzaalVar = (zzaal) obj;
            if (this.zza == zzaalVar.zza && this.zzc == zzaalVar.zzc && this.zzd == zzaalVar.zzd && Arrays.equals(this.zzb, zzaalVar.zzb)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (((((this.zza * 31) + Arrays.hashCode(this.zzb)) * 31) + this.zzc) * 31) + this.zzd;
    }
}
