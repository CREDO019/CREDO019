package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzgru extends zzgrt {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r13[r14] <= (-65)) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0047, code lost:
        if (r13[r14] <= (-65)) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0083, code lost:
        if (r13[r14] <= (-65)) goto L11;
     */
    @Override // com.google.android.gms.internal.ads.zzgrt
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zza(int r12, byte[] r13, int r14, int r15) {
        /*
            Method dump skipped, instructions count: 245
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgru.zza(int, byte[], int, int):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.ads.zzgrt
    public final String zzb(byte[] bArr, int r12, int r13) throws zzgoz {
        int length = bArr.length;
        if ((r12 | r13 | ((length - r12) - r13)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(r12), Integer.valueOf(r13)));
        }
        int r0 = r12 + r13;
        char[] cArr = new char[r13];
        int r1 = 0;
        while (r12 < r0) {
            byte b = bArr[r12];
            if (!zzgrs.zzd(b)) {
                break;
            }
            r12++;
            cArr[r1] = (char) b;
            r1++;
        }
        while (r12 < r0) {
            int r3 = r12 + 1;
            byte b2 = bArr[r12];
            if (zzgrs.zzd(b2)) {
                int r4 = r1 + 1;
                cArr[r1] = (char) b2;
                r12 = r3;
                while (true) {
                    r1 = r4;
                    if (r12 < r0) {
                        byte b3 = bArr[r12];
                        if (!zzgrs.zzd(b3)) {
                            break;
                        }
                        r12++;
                        r4 = r1 + 1;
                        cArr[r1] = (char) b3;
                    }
                }
            } else if (zzgrs.zzf(b2)) {
                if (r3 < r0) {
                    zzgrs.zzc(b2, bArr[r3], cArr, r1);
                    r12 = r3 + 1;
                    r1++;
                } else {
                    throw zzgoz.zzd();
                }
            } else if (zzgrs.zze(b2)) {
                if (r3 < r0 - 1) {
                    int r42 = r3 + 1;
                    zzgrs.zzb(b2, bArr[r3], bArr[r42], cArr, r1);
                    r12 = r42 + 1;
                    r1++;
                } else {
                    throw zzgoz.zzd();
                }
            } else if (r3 < r0 - 2) {
                int r43 = r3 + 1;
                int r5 = r43 + 1;
                zzgrs.zza(b2, bArr[r3], bArr[r43], bArr[r5], cArr, r1);
                r1 += 2;
                r12 = r5 + 1;
            } else {
                throw zzgoz.zzd();
            }
        }
        return new String(cArr, 0, r1);
    }
}
