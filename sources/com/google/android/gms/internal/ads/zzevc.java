package com.google.android.gms.internal.ads;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzevc implements zzeun {
    private final Context zza;
    private final ScheduledExecutorService zzb;
    private final Executor zzc;
    private final int zzd;
    private final zzcfn zze;

    public zzevc(zzcfn zzcfnVar, Context context, ScheduledExecutorService scheduledExecutorService, Executor executor, int r5, byte[] bArr) {
        this.zze = zzcfnVar;
        this.zza = context;
        this.zzb = scheduledExecutorService;
        this.zzc = executor;
        this.zzd = r5;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 40;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaO)).booleanValue()) {
            return zzfyo.zzh(new Exception("Did not ad Ad ID into query param."));
        }
        return zzfyo.zzf((zzfyf) zzfyo.zzo(zzfyo.zzm(zzfyf.zzv(this.zze.zza(this.zza, this.zzd)), new zzfru() { // from class: com.google.android.gms.internal.ads.zzeva
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                AdvertisingIdClient.Info info = (AdvertisingIdClient.Info) obj;
                info.getClass();
                return new zzevd(info, null);
            }
        }, this.zzc), ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaP)).longValue(), TimeUnit.MILLISECONDS, this.zzb), Throwable.class, new zzfru() { // from class: com.google.android.gms.internal.ads.zzevb
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                return zzevc.this.zzc((Throwable) obj);
            }
        }, this.zzc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzevd zzc(Throwable th) {
        com.google.android.gms.ads.internal.client.zzaw.zzb();
        ContentResolver contentResolver = this.zza.getContentResolver();
        return new zzevd(null, contentResolver == null ? null : Settings.Secure.getString(contentResolver, "android_id"));
    }
}
