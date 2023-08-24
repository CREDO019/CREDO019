package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzckm extends zzalj {
    static final zzckm zzb = new zzckm();

    zzckm() {
    }

    @Override // com.google.android.gms.internal.ads.zzalj
    public final zzaln zza(String str, byte[] bArr, String str2) {
        if ("moov".equals(str)) {
            return new zzalp();
        }
        if ("mvhd".equals(str)) {
            return new zzalq();
        }
        return new zzalr(str);
    }
}
