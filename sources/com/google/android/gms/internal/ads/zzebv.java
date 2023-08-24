package com.google.android.gms.internal.ads;

import android.content.Context;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzebv implements zzect {
    private static final Pattern zza = Pattern.compile("Received error HTTP response code: (.*)");
    private final zzeaw zzb;
    private final zzfyy zzc;
    private final zzfdn zzd;
    private final ScheduledExecutorService zze;
    private final zzeez zzf;
    private final zzfjc zzg;
    private final Context zzh;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzebv(Context context, zzfdn zzfdnVar, zzeaw zzeawVar, zzfyy zzfyyVar, ScheduledExecutorService scheduledExecutorService, zzeez zzeezVar, zzfjc zzfjcVar) {
        this.zzh = context;
        this.zzd = zzfdnVar;
        this.zzb = zzeawVar;
        this.zzc = zzfyyVar;
        this.zze = scheduledExecutorService;
        this.zzf = zzeezVar;
        this.zzg = zzfjcVar;
    }

    @Override // com.google.android.gms.internal.ads.zzect
    public final zzfyx zzb(zzcba zzcbaVar) {
        zzfyx zzb = this.zzb.zzb(zzcbaVar);
        zzfir zza2 = zzfiq.zza(this.zzh, 11);
        zzfjb.zzd(zzb, zza2);
        zzfyx zzn = zzfyo.zzn(zzb, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzebs
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzebv.this.zzc((InputStream) obj);
            }
        }, this.zzc);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeA)).booleanValue()) {
            zzn = zzfyo.zzg(zzfyo.zzo(zzn, ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeB)).intValue(), TimeUnit.SECONDS, this.zze), TimeoutException.class, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzebt
                @Override // com.google.android.gms.internal.ads.zzfxv
                public final zzfyx zza(Object obj) {
                    TimeoutException timeoutException = (TimeoutException) obj;
                    return zzfyo.zzh(new zzeas(5));
                }
            }, zzcha.zzf);
        }
        zzfjb.zza(zzn, this.zzg, zza2);
        zzfyo.zzr(zzn, new zzebu(this), zzcha.zzf);
        return zzn;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzc(InputStream inputStream) throws Exception {
        return zzfyo.zzi(new zzfde(new zzfdb(this.zzd), zzfdd.zza(new InputStreamReader(inputStream))));
    }
}
