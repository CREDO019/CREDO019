package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzatf {
    public static final zzatf zza = new zzatf(new int[]{2}, 2);
    private final int[] zzb;
    private final int zzc;

    zzatf(int[] r1, int r2) {
        int[] copyOf = Arrays.copyOf(r1, 1);
        this.zzb = copyOf;
        Arrays.sort(copyOf);
        this.zzc = 2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzatf) {
            zzatf zzatfVar = (zzatf) obj;
            if (Arrays.equals(this.zzb, zzatfVar.zzb)) {
                int r5 = zzatfVar.zzc;
                return true;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return (Arrays.hashCode(this.zzb) * 31) + 2;
    }

    public final String toString() {
        String arrays = Arrays.toString(this.zzb);
        return "AudioCapabilities[maxChannelCount=2, supportedEncodings=" + arrays + "]";
    }
}
