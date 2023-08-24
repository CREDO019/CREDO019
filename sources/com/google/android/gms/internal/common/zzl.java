package com.google.android.gms.internal.common;

import org.apache.commons.p028io.IOUtils;

/* compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* loaded from: classes.dex */
final class zzl extends zzk {
    private final char zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(char c) {
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

    @Override // com.google.android.gms.internal.common.zzo
    public final boolean zza(char c) {
        return c == this.zza;
    }
}
