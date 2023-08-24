package com.google.android.gms.internal.vision;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zzdy extends zzdv {
    @Override // com.google.android.gms.internal.vision.zzdv
    public final void zza(Throwable th, Throwable th2) {
        th.addSuppressed(th2);
    }

    @Override // com.google.android.gms.internal.vision.zzdv
    public final void zza(Throwable th) {
        th.printStackTrace();
    }
}
