package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzech extends zzecf {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzech(Context context) {
        this.zzf = new zzcal(context, com.google.android.gms.ads.internal.zzt.zzu().zzb(), this, this);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        synchronized (this.zzb) {
            if (!this.zzd) {
                this.zzd = true;
                try {
                    this.zzf.zzp().zzf(this.zze, new zzece(this));
                } catch (RemoteException | IllegalArgumentException unused) {
                    this.zza.zze(new zzecu(1));
                } catch (Throwable th) {
                    com.google.android.gms.ads.internal.zzt.zzp().zzt(th, "RemoteSignalsClientTask.onConnected");
                    this.zza.zze(new zzecu(1));
                }
            }
        }
    }
}
