package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
abstract class zzjt {
    abstract int zzb(int r1, byte[] bArr, int r3, int r4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzb(CharSequence charSequence, byte[] bArr, int r3, int r4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String zzh(byte[] bArr, int r2, int r3) throws zzhc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzf(byte[] bArr, int r3, int r4) {
        return zzb(0, bArr, r3, r4) == 0;
    }
}
