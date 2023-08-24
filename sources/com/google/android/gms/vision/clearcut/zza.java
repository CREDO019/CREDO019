package com.google.android.gms.vision.clearcut;

import com.google.android.gms.internal.vision.zzea;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
final class zza implements Runnable {
    private final /* synthetic */ int zzbq;
    private final /* synthetic */ zzea.zzo zzbr;
    private final /* synthetic */ DynamiteClearcutLogger zzbs;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(DynamiteClearcutLogger dynamiteClearcutLogger, int r2, zzea.zzo zzoVar) {
        this.zzbs = dynamiteClearcutLogger;
        this.zzbq = r2;
        this.zzbr = zzoVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        VisionClearcutLogger visionClearcutLogger;
        visionClearcutLogger = this.zzbs.zzbp;
        visionClearcutLogger.zzb(this.zzbq, this.zzbr);
    }
}
