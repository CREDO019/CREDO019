package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzaht extends zzyr {
    public zzaht(zzej zzejVar, long j, long j2) {
        super(new zzym(), new zzahs(zzejVar, null), j, 0L, j + 1, 0L, j2, 188L, 1000);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ int zzh(byte[] bArr, int r3) {
        return (bArr[r3 + 3] & 255) | ((bArr[r3] & 255) << 24) | ((bArr[r3 + 1] & 255) << 16) | ((bArr[r3 + 2] & 255) << 8);
    }
}
