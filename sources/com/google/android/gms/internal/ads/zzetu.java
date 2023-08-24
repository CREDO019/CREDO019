package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.AdFormat;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzetu implements zzeun {
    private final String zza;
    private final zzfyy zzb;
    private final ScheduledExecutorService zzc;
    private final Context zzd;
    private final zzfdn zze;
    private final zzcok zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzetu(zzfyy zzfyyVar, ScheduledExecutorService scheduledExecutorService, String str, Context context, zzfdn zzfdnVar, zzcok zzcokVar) {
        this.zzb = zzfyyVar;
        this.zzc = scheduledExecutorService;
        this.zza = str;
        this.zzd = context;
        this.zze = zzfdnVar;
        this.zzf = zzcokVar;
    }

    public static /* synthetic */ zzfyx zzc(zzetu zzetuVar) {
        String str = zzetuVar.zza;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgg)).booleanValue()) {
            str = AdFormat.UNKNOWN.name();
        }
        com.google.android.gms.ads.nonagon.signalgeneration.zzg zzn = zzetuVar.zzf.zzn();
        zzdci zzdciVar = new zzdci();
        zzdciVar.zzc(zzetuVar.zzd);
        zzfdl zzfdlVar = new zzfdl();
        zzfdlVar.zzs("adUnitId");
        zzfdlVar.zzE(zzetuVar.zze.zzd);
        zzfdlVar.zzr(new com.google.android.gms.ads.internal.client.zzq());
        zzdciVar.zzf(zzfdlVar.zzG());
        zzn.zza(zzdciVar.zzg());
        com.google.android.gms.ads.nonagon.signalgeneration.zzac zzacVar = new com.google.android.gms.ads.nonagon.signalgeneration.zzac();
        zzacVar.zza(str);
        zzn.zzb(zzacVar.zzb());
        new zzdii();
        return zzfyo.zzf(zzfyo.zzm((zzfyf) zzfyo.zzo(zzfyf.zzv(zzn.zzc().zzc()), ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgh)).longValue(), TimeUnit.MILLISECONDS, zzetuVar.zzc), new zzfru() { // from class: com.google.android.gms.internal.ads.zzets
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                com.google.android.gms.ads.nonagon.signalgeneration.zzam zzamVar = (com.google.android.gms.ads.nonagon.signalgeneration.zzam) obj;
                return zzamVar != null ? new zzetv(zzamVar.zza) : new zzetv(null);
            }
        }, zzetuVar.zzb), Exception.class, new zzfru() { // from class: com.google.android.gms.internal.ads.zzett
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                zzcgn.zzh("", (Exception) obj);
                return new zzetv(null);
            }
        }, zzetuVar.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 33;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgf)).booleanValue() || "adUnitId".equals(this.zze.zzf)) {
            return this.zzb.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzetq
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return new zzetv(null);
                }
            });
        }
        return zzfyo.zzl(new zzfxu() { // from class: com.google.android.gms.internal.ads.zzetr
            @Override // com.google.android.gms.internal.ads.zzfxu
            public final zzfyx zza() {
                return zzetu.zzc(zzetu.this);
            }
        }, this.zzb);
    }
}
