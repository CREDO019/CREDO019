package com.google.android.gms.internal.ads;

import java.security.SecureRandom;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgmg {
    private static final ThreadLocal zza = new zzgmf();

    public static byte[] zza(int r1) {
        byte[] bArr = new byte[r1];
        ((SecureRandom) zza.get()).nextBytes(bArr);
        return bArr;
    }
}
