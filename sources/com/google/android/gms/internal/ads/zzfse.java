package com.google.android.gms.internal.ads;

import java.util.logging.Logger;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfse {
    private static final Logger zza = Logger.getLogger(zzfse.class.getName());
    private static final zzfsd zzb = new zzfsd(null);

    private zzfse() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zza(@CheckForNull String str) {
        return str == null ? "" : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzb(@CheckForNull String str) {
        return str == null || str.isEmpty();
    }
}
