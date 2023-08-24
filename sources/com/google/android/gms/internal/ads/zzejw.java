package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.common.util.Clock;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeoutException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzejw implements zzfyk {
    final /* synthetic */ long zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzfcs zzc;
    final /* synthetic */ zzfcv zzd;
    final /* synthetic */ zzfjq zze;
    final /* synthetic */ zzfde zzf;
    final /* synthetic */ zzejx zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzejw(zzejx zzejxVar, long j, String str, zzfcs zzfcsVar, zzfcv zzfcvVar, zzfjq zzfjqVar, zzfde zzfdeVar) {
        this.zzg = zzejxVar;
        this.zza = j;
        this.zzb = str;
        this.zzc = zzfcsVar;
        this.zzd = zzfcvVar;
        this.zze = zzfjqVar;
        this.zzf = zzfdeVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        Clock clock;
        int r2;
        boolean z;
        com.google.android.gms.ads.internal.client.zze zzeVar;
        zzegp zzegpVar;
        zzfju zzfjuVar;
        zzejy zzejyVar;
        clock = this.zzg.zza;
        long elapsedRealtime = clock.elapsedRealtime() - this.zza;
        if (th instanceof TimeoutException) {
            r2 = 2;
        } else if (th instanceof zzejm) {
            r2 = 3;
        } else if (th instanceof CancellationException) {
            r2 = 4;
        } else if (th instanceof zzfds) {
            r2 = 5;
        } else {
            r2 = ((th instanceof zzeas) && zzfem.zza(th).zza == 3) ? 1 : 6;
        }
        zzejx.zzg(this.zzg, this.zzb, r2, elapsedRealtime, this.zzc.zzah);
        zzejx zzejxVar = this.zzg;
        z = zzejxVar.zze;
        if (z) {
            zzejyVar = zzejxVar.zzb;
            zzejyVar.zza(this.zzd, this.zzc, r2, th instanceof zzego ? (zzego) th : null, elapsedRealtime);
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhd)).booleanValue()) {
            zzfjuVar = this.zzg.zzc;
            zzfjq zzfjqVar = this.zze;
            zzfde zzfdeVar = this.zzf;
            zzfcs zzfcsVar = this.zzc;
            zzfjuVar.zzd(zzfjqVar.zzc(zzfdeVar, zzfcsVar, zzfcsVar.zzo));
        }
        com.google.android.gms.ads.internal.client.zze zza = zzfem.zza(th);
        int r22 = zza.zza;
        if ((r22 == 3 || r22 == 0) && (zzeVar = zza.zzd) != null && !zzeVar.zzc.equals(MobileAds.ERROR_DOMAIN)) {
            zza = zzfem.zza(new zzego(13, zza.zzd));
        }
        zzegpVar = this.zzg.zzf;
        zzegpVar.zze(this.zzc, elapsedRealtime, zza);
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zzb(Object obj) {
        Clock clock;
        boolean z;
        zzegp zzegpVar;
        zzejy zzejyVar;
        clock = this.zzg.zza;
        long elapsedRealtime = clock.elapsedRealtime() - this.zza;
        zzejx.zzg(this.zzg, this.zzb, 0, elapsedRealtime, this.zzc.zzah);
        zzejx zzejxVar = this.zzg;
        z = zzejxVar.zze;
        if (z) {
            zzejyVar = zzejxVar.zzb;
            zzejyVar.zza(this.zzd, this.zzc, 0, null, elapsedRealtime);
        }
        zzegpVar = this.zzg.zzf;
        zzegpVar.zzf(this.zzc, elapsedRealtime, null);
    }
}
