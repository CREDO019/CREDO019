package com.google.android.gms.internal.vision;

import java.util.List;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzdw extends zzdv {
    private final zzdu zzmh = new zzdu();

    @Override // com.google.android.gms.internal.vision.zzdv
    public final void zza(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        }
        Objects.requireNonNull(th2, "The suppressed exception cannot be null.");
        this.zzmh.zza(th, true).add(th2);
    }

    @Override // com.google.android.gms.internal.vision.zzdv
    public final void zza(Throwable th) {
        th.printStackTrace();
        List<Throwable> zza = this.zzmh.zza(th, false);
        if (zza == null) {
            return;
        }
        synchronized (zza) {
            for (Throwable th2 : zza) {
                System.err.print("Suppressed: ");
                th2.printStackTrace();
            }
        }
    }
}
