package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.io.IOException;
import java.util.concurrent.Future;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbef implements BaseGmsClient.BaseConnectionCallbacks {
    public static final /* synthetic */ int zzd = 0;
    final /* synthetic */ zzbdx zza;
    final /* synthetic */ zzchf zzb;
    final /* synthetic */ zzbeh zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbef(zzbeh zzbehVar, zzbdx zzbdxVar, zzchf zzchfVar) {
        this.zzc = zzbehVar;
        this.zza = zzbdxVar;
        this.zzb = zzchfVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        Object obj;
        boolean z;
        final zzbdw zzbdwVar;
        obj = this.zzc.zzd;
        synchronized (obj) {
            zzbeh zzbehVar = this.zzc;
            z = zzbehVar.zzb;
            if (z) {
                return;
            }
            zzbehVar.zzb = true;
            zzbdwVar = this.zzc.zza;
            if (zzbdwVar == null) {
                return;
            }
            zzfyy zzfyyVar = zzcha.zza;
            final zzbdx zzbdxVar = this.zza;
            final zzchf zzchfVar = this.zzb;
            final zzfyx zza = zzfyyVar.zza(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbec
                @Override // java.lang.Runnable
                public final void run() {
                    zzbdu zzf;
                    zzbef zzbefVar = zzbef.this;
                    zzbdw zzbdwVar2 = zzbdwVar;
                    zzbdx zzbdxVar2 = zzbdxVar;
                    zzchf zzchfVar2 = zzchfVar;
                    try {
                        zzbdz zzq = zzbdwVar2.zzq();
                        if (zzbdwVar2.zzp()) {
                            zzf = zzq.zzg(zzbdxVar2);
                        } else {
                            zzf = zzq.zzf(zzbdxVar2);
                        }
                        if (!zzf.zze()) {
                            zzchfVar2.zze(new RuntimeException("No entry contents."));
                            zzbeh.zze(zzbefVar.zzc);
                            return;
                        }
                        zzbee zzbeeVar = new zzbee(zzbefVar, zzf.zzc(), 1);
                        int read = zzbeeVar.read();
                        if (read == -1) {
                            throw new IOException("Unable to read from cache.");
                        }
                        zzbeeVar.unread(read);
                        zzchfVar2.zzd(zzbej.zzb(zzbeeVar, zzf.zzd(), zzf.zzg(), zzf.zza(), zzf.zzf()));
                    } catch (RemoteException | IOException e) {
                        com.google.android.gms.ads.internal.util.zze.zzh("Unable to obtain a cache service instance.", e);
                        zzchfVar2.zze(e);
                        zzbeh.zze(zzbefVar.zzc);
                    }
                }
            });
            final zzchf zzchfVar2 = this.zzb;
            zzchfVar2.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbed
                @Override // java.lang.Runnable
                public final void run() {
                    zzchf zzchfVar3 = zzchf.this;
                    Future future = zza;
                    int r2 = zzbef.zzd;
                    if (zzchfVar3.isCancelled()) {
                        future.cancel(true);
                    }
                }
            }, zzcha.zzf);
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int r1) {
    }
}
