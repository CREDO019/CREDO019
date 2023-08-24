package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzekc implements zzfxv {
    private final zzfhp zza;
    private final zzdda zzb;
    private final zzfjq zzc;
    private final zzfju zzd;
    private final Executor zze;
    private final ScheduledExecutorService zzf;
    private final zzczf zzg;
    private final zzejx zzh;
    private final zzegp zzi;
    private final Context zzj;
    private final zzfjc zzk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzekc(Context context, zzfhp zzfhpVar, zzejx zzejxVar, zzdda zzddaVar, zzfjq zzfjqVar, zzfju zzfjuVar, zzczf zzczfVar, Executor executor, ScheduledExecutorService scheduledExecutorService, zzegp zzegpVar, zzfjc zzfjcVar) {
        this.zzj = context;
        this.zza = zzfhpVar;
        this.zzh = zzejxVar;
        this.zzb = zzddaVar;
        this.zzc = zzfjqVar;
        this.zzd = zzfjuVar;
        this.zzg = zzczfVar;
        this.zze = executor;
        this.zzf = scheduledExecutorService;
        this.zzi = zzegpVar;
        this.zzk = zzfjcVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fd  */
    @Override // com.google.android.gms.internal.ads.zzfxv
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* synthetic */ com.google.android.gms.internal.ads.zzfyx zza(java.lang.Object r9) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzekc.zza(java.lang.Object):com.google.android.gms.internal.ads.zzfyx");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzb(zzfcs zzfcsVar, zzfde zzfdeVar, zzegk zzegkVar, Throwable th) throws Exception {
        zzfir zza = zzfiq.zza(this.zzj, 12);
        zza.zzc(zzfcsVar.zzF);
        zza.zzf();
        zzejx zzejxVar = this.zzh;
        zzfyx zzo = zzfyo.zzo(zzegkVar.zza(zzfdeVar, zzfcsVar), zzfcsVar.zzS, TimeUnit.MILLISECONDS, this.zzf);
        zzejxVar.zze(zzfdeVar, zzfcsVar, zzo, this.zzc);
        zzfjb.zza(zzo, this.zzk, zza);
        return zzo;
    }
}
