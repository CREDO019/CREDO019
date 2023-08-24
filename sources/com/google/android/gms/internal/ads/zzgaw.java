package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgaw implements Comparable {
    private final byte[] zza;

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        zzgaw zzgawVar = (zzgaw) obj;
        int length = this.zza.length;
        int length2 = zzgawVar.zza.length;
        if (length != length2) {
            return length - length2;
        }
        int r0 = 0;
        while (true) {
            byte[] bArr = this.zza;
            if (r0 >= bArr.length) {
                return 0;
            }
            byte b = bArr[r0];
            byte b2 = zzgawVar.zza[r0];
            if (b != b2) {
                return b - b2;
            }
            r0++;
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzgaw) {
            return Arrays.equals(this.zza, ((zzgaw) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zza);
    }

    public final String toString() {
        return zzglz.zza(this.zza);
    }
}
