package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import java.util.concurrent.Future;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbeh {
    private zzbdw zza;
    private boolean zzb;
    private final Context zzc;
    private final Object zzd = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbeh(Context context) {
        this.zzc = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Future zzc(zzbdx zzbdxVar) {
        zzbeb zzbebVar = new zzbeb(this);
        zzbef zzbefVar = new zzbef(this, zzbdxVar, zzbebVar);
        zzbeg zzbegVar = new zzbeg(this, zzbebVar);
        synchronized (this.zzd) {
            zzbdw zzbdwVar = new zzbdw(this.zzc, com.google.android.gms.ads.internal.zzt.zzu().zzb(), zzbefVar, zzbegVar);
            this.zza = zzbdwVar;
            zzbdwVar.checkAvailabilityAndConnect();
        }
        return zzbebVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zze(zzbeh zzbehVar) {
        synchronized (zzbehVar.zzd) {
            zzbdw zzbdwVar = zzbehVar.zza;
            if (zzbdwVar == null) {
                return;
            }
            zzbdwVar.disconnect();
            zzbehVar.zza = null;
            Binder.flushPendingCommands();
        }
    }
}
