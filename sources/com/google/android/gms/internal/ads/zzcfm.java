package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcfm implements Runnable {
    final /* synthetic */ Context zza;
    final /* synthetic */ zzchf zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcfm(zzcfn zzcfnVar, Context context, zzchf zzchfVar) {
        this.zza = context;
        this.zzb = zzchfVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.zzb.zzd(AdvertisingIdClient.getAdvertisingIdInfo(this.zza));
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e) {
            this.zzb.zze(e);
            zzcgn.zzh("Exception while getting advertising Id info", e);
        }
    }
}
