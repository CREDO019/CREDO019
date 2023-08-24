package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import com.facebook.device.yearclass.YearClass;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzfmo implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    protected final zzfnm zza;
    private final String zzb;
    private final String zzc;
    private final LinkedBlockingQueue zzd;
    private final HandlerThread zze;
    private final zzfmf zzf;
    private final long zzg;
    private final int zzh;

    public zzfmo(Context context, int r8, int r9, String str, String str2, String str3, zzfmf zzfmfVar) {
        this.zzb = str;
        this.zzh = r9;
        this.zzc = str2;
        this.zzf = zzfmfVar;
        HandlerThread handlerThread = new HandlerThread("GassDGClient");
        this.zze = handlerThread;
        handlerThread.start();
        this.zzg = System.currentTimeMillis();
        zzfnm zzfnmVar = new zzfnm(context, handlerThread.getLooper(), this, this, 19621000);
        this.zza = zzfnmVar;
        this.zzd = new LinkedBlockingQueue();
        zzfnmVar.checkAvailabilityAndConnect();
    }

    static zzfny zza() {
        return new zzfny(null, 1);
    }

    private final void zze(int r4, long j, Exception exc) {
        this.zzf.zzc(r4, System.currentTimeMillis() - j, exc);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        zzfnr zzd = zzd();
        if (zzd != null) {
            try {
                zzfny zzf = zzd.zzf(new zzfnw(1, this.zzh, this.zzb, this.zzc));
                zze(5011, this.zzg, null);
                this.zzd.put(zzf);
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        try {
            zze(4012, this.zzg, null);
            this.zzd.put(zza());
        } catch (InterruptedException unused) {
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int r4) {
        try {
            zze(4011, this.zzg, null);
            this.zzd.put(zza());
        } catch (InterruptedException unused) {
        }
    }

    public final zzfny zzb(int r5) {
        zzfny zzfnyVar;
        try {
            zzfnyVar = (zzfny) this.zzd.poll(50000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            zze(YearClass.CLASS_2009, this.zzg, e);
            zzfnyVar = null;
        }
        zze(3004, this.zzg, null);
        if (zzfnyVar != null) {
            if (zzfnyVar.zzc == 7) {
                zzfmf.zzg(3);
            } else {
                zzfmf.zzg(2);
            }
        }
        return zzfnyVar == null ? zza() : zzfnyVar;
    }

    public final void zzc() {
        zzfnm zzfnmVar = this.zza;
        if (zzfnmVar != null) {
            if (zzfnmVar.isConnected() || this.zza.isConnecting()) {
                this.zza.disconnect();
            }
        }
    }

    protected final zzfnr zzd() {
        try {
            return this.zza.zzp();
        } catch (DeadObjectException | IllegalStateException unused) {
            return null;
        }
    }
}
