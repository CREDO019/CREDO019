package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzgrg extends RuntimeException {
    public zzgrg(zzgpx zzgpxVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzgoz zza() {
        return new zzgoz(getMessage());
    }
}
