package com.google.android.gms.internal.ads;

import org.apache.commons.p028io.IOUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfro extends zzfrn {
    private final char zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfro(char c) {
        this.zza = c;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CharMatcher.is('");
        int r1 = this.zza;
        char[] cArr = {IOUtils.DIR_SEPARATOR_WINDOWS, 'u', 0, 0, 0, 0};
        for (int r3 = 0; r3 < 4; r3++) {
            cArr[5 - r3] = "0123456789ABCDEF".charAt(r1 & 15);
            r1 >>= 4;
        }
        sb.append(String.copyValueOf(cArr));
        sb.append("')");
        return sb.toString();
    }

    @Override // com.google.android.gms.internal.ads.zzfrr
    public final boolean zzb(char c) {
        return c == this.zza;
    }
}
