package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public abstract class zzcok implements zzctr {
    @Nullable
    private static zzcok zza;

    private static synchronized zzcok zzC(Context context, @Nullable zzbvf zzbvfVar, int r11, boolean z, int r13, zzcpm zzcpmVar) {
        synchronized (zzcok.class) {
            zzcok zzcokVar = zza;
            if (zzcokVar != null) {
                return zzcokVar;
            }
            zzbiy.zzc(context);
            zzfeg zzd = zzfeg.zzd(context);
            zzcgt zzc = zzd.zzc(ModuleDescriptor.MODULE_VERSION, false, r13);
            zzd.zze(zzbvfVar);
            zzcqw zzcqwVar = new zzcqw(null);
            zzcol zzcolVar = new zzcol();
            zzcolVar.zzd(zzc);
            zzcolVar.zzc(context);
            zzcqwVar.zzb(new zzcon(zzcolVar, null));
            zzcqwVar.zzc(new zzcsj(zzcpmVar, null));
            zzcok zza2 = zzcqwVar.zza();
            com.google.android.gms.ads.internal.zzt.zzp().zzr(context, zzc);
            com.google.android.gms.ads.internal.zzt.zzc().zzi(context);
            com.google.android.gms.ads.internal.zzt.zzq().zzj(context);
            com.google.android.gms.ads.internal.zzt.zzq().zzi(context);
            com.google.android.gms.ads.internal.util.zzd.zza(context);
            com.google.android.gms.ads.internal.zzt.zzb().zzd(context);
            com.google.android.gms.ads.internal.zzt.zzw().zzb(context);
            zzcet.zzd(context);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfj)).booleanValue()) {
                if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzas)).booleanValue()) {
                    zzbel zzbelVar = new zzbel(new zzber(context));
                    zzeev zzeevVar = new zzeev(new zzeer(context), zza2.zzz());
                    com.google.android.gms.ads.internal.zzt.zzq();
                    new zzefr(context, zzc, zzbelVar, zzeevVar, UUID.randomUUID().toString(), zza2.zzx()).zzb(com.google.android.gms.ads.internal.zzt.zzp().zzh().zzP());
                }
            }
            zza = zza2;
            return zza2;
        }
    }

    public static zzcok zza(Context context, @Nullable zzbvf zzbvfVar, int r8) {
        return zzC(context, zzbvfVar, ModuleDescriptor.MODULE_VERSION, false, r8, new zzcpm());
    }

    public abstract Executor zzA();

    public abstract ScheduledExecutorService zzB();

    public abstract zzcsu zzb();

    public abstract zzcwc zzc();

    public abstract zzcwn zzd();

    public abstract zzcxw zze();

    public abstract zzdfn zzf();

    public abstract zzdme zzg();

    public abstract zzdna zzh();

    public abstract zzdue zzi();

    public abstract zzdyw zzj();

    public abstract zzeai zzk();

    public abstract zzegh zzl();

    public abstract com.google.android.gms.ads.nonagon.signalgeneration.zzc zzm();

    public abstract com.google.android.gms.ads.nonagon.signalgeneration.zzg zzn();

    public abstract com.google.android.gms.ads.nonagon.signalgeneration.zzaa zzo();

    @Override // com.google.android.gms.internal.ads.zzctr
    public final zzevf zzp(zzcba zzcbaVar, int r3) {
        return zzq(new zzewr(zzcbaVar, r3));
    }

    protected abstract zzevf zzq(zzewr zzewrVar);

    public abstract zzexq zzr();

    public abstract zzeze zzs();

    public abstract zzfax zzt();

    public abstract zzfcl zzu();

    public abstract zzfdz zzv();

    public abstract zzfej zzw();

    public abstract zzfhz zzx();

    public abstract zzfje zzy();

    public abstract zzfyy zzz();
}
