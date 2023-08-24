package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zznb {
    public static final zznb zza = new zznb(new int[]{2}, 8);
    private static final zznb zzb = new zznb(new int[]{2, 5, 6}, 8);
    private static final zzfuy zzc;
    private final int[] zzd;
    private final int zze;

    static {
        zzfux zzfuxVar = new zzfux();
        zzfuxVar.zza(5, 6);
        zzfuxVar.zza(17, 6);
        zzfuxVar.zza(7, 6);
        zzfuxVar.zza(18, 6);
        zzfuxVar.zza(6, 8);
        zzfuxVar.zza(8, 8);
        zzfuxVar.zza(14, 8);
        zzc = zzfuxVar.zzc();
    }

    public zznb(int[] r1, int r2) {
        int[] copyOf = Arrays.copyOf(r1, r1.length);
        this.zzd = copyOf;
        Arrays.sort(copyOf);
        this.zze = 8;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zznb) {
            zznb zznbVar = (zznb) obj;
            if (Arrays.equals(this.zzd, zznbVar.zzd)) {
                int r5 = zznbVar.zze;
                return true;
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        return (Arrays.hashCode(this.zzd) * 31) + 8;
    }

    public final String toString() {
        String arrays = Arrays.toString(this.zzd);
        return "AudioCapabilities[maxChannelCount=8, supportedEncodings=" + arrays + "]";
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0080, code lost:
        if (r7 != 5) goto L40;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x009e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.util.Pair zza(com.google.android.gms.internal.ads.zzaf r10) {
        /*
            r9 = this;
            java.lang.String r0 = r10.zzm
            java.util.Objects.requireNonNull(r0)
            java.lang.String r1 = r10.zzj
            int r0 = com.google.android.gms.internal.ads.zzbt.zza(r0, r1)
            com.google.android.gms.internal.ads.zzfuy r1 = com.google.android.gms.internal.ads.zznb.zzc
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            boolean r2 = r1.containsKey(r2)
            r3 = 0
            if (r2 != 0) goto L19
            return r3
        L19:
            r2 = 7
            r4 = 6
            r5 = 8
            r6 = 18
            if (r0 != r6) goto L2b
            boolean r0 = r9.zzc(r6)
            if (r0 != 0) goto L29
            r0 = 6
            goto L34
        L29:
            r0 = 18
        L2b:
            if (r0 != r5) goto L34
            boolean r7 = r9.zzc(r5)
            if (r7 != 0) goto L34
            r0 = 7
        L34:
            boolean r7 = r9.zzc(r0)
            if (r7 != 0) goto L3b
            return r3
        L3b:
            int r7 = r10.zzz
            r8 = -1
            if (r7 == r8) goto L46
            if (r0 != r6) goto L43
            goto L46
        L43:
            if (r7 <= r5) goto L6e
            return r3
        L46:
            int r10 = r10.zzA
            if (r10 != r8) goto L4d
            r10 = 48000(0xbb80, float:6.7262E-41)
        L4d:
            int r6 = com.google.android.gms.internal.ads.zzel.zza
            r7 = 29
            if (r6 < r7) goto L58
            int r7 = com.google.android.gms.internal.ads.zzna.zza(r0, r10)
            goto L6e
        L58:
            java.lang.Integer r10 = java.lang.Integer.valueOf(r0)
            r6 = 0
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r10 = r1.getOrDefault(r10, r6)
            java.lang.Integer r10 = (java.lang.Integer) r10
            java.util.Objects.requireNonNull(r10)
            int r7 = r10.intValue()
        L6e:
            int r10 = com.google.android.gms.internal.ads.zzel.zza
            r1 = 28
            if (r10 > r1) goto L83
            if (r7 != r2) goto L79
            r4 = 8
            goto L84
        L79:
            r10 = 3
            if (r7 == r10) goto L84
            r10 = 4
            if (r7 == r10) goto L84
            r10 = 5
            if (r7 != r10) goto L83
            goto L84
        L83:
            r4 = r7
        L84:
            int r10 = com.google.android.gms.internal.ads.zzel.zza
            r1 = 26
            if (r10 > r1) goto L98
            java.lang.String r10 = com.google.android.gms.internal.ads.zzel.zzb
            java.lang.String r1 = "fugu"
            boolean r10 = r1.equals(r10)
            if (r10 == 0) goto L98
            r10 = 1
            if (r4 != r10) goto L98
            r4 = 2
        L98:
            int r10 = com.google.android.gms.internal.ads.zzel.zzj(r4)
            if (r10 != 0) goto L9f
            return r3
        L9f:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            android.util.Pair r10 = android.util.Pair.create(r0, r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zznb.zza(com.google.android.gms.internal.ads.zzaf):android.util.Pair");
    }

    public final boolean zzc(int r2) {
        return Arrays.binarySearch(this.zzd, r2) >= 0;
    }
}
