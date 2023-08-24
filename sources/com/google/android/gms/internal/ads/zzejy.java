package com.google.android.gms.internal.ads;

import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzejy {
    private final zzfec zza;
    private final zzdvg zzb;
    private final zzdxo zzc;
    private final zzfhz zzd;

    public zzejy(zzfec zzfecVar, zzdvg zzdvgVar, zzdxo zzdxoVar, zzfhz zzfhzVar) {
        this.zza = zzfecVar;
        this.zzb = zzdvgVar;
        this.zzc = zzdxoVar;
        this.zzd = zzfhzVar;
    }

    public final void zza(zzfcv zzfcvVar, zzfcs zzfcsVar, int r12, @Nullable zzego zzegoVar, long j) {
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhn)).booleanValue()) {
            zzdxn zza = this.zzc.zza();
            zza.zze(zzfcvVar);
            zza.zzd(zzfcsVar);
            zza.zzb("action", "adapter_status");
            zza.zzb("adapter_l", String.valueOf(j));
            zza.zzb("sc", Integer.toString(r12));
            if (zzegoVar != null) {
                zza.zzb("arec", Integer.toString(zzegoVar.zzb().zza));
                String zza2 = this.zza.zza(zzegoVar.getMessage());
                if (zza2 != null) {
                    zza.zzb("areec", zza2);
                }
            }
            zzdvf zzb = this.zzb.zzb(zzfcsVar.zzu);
            if (zzb != null) {
                zza.zzb("ancn", zzb.zza);
                zzbxl zzbxlVar = zzb.zzb;
                if (zzbxlVar != null) {
                    zza.zzb("adapter_v", zzbxlVar.toString());
                }
                zzbxl zzbxlVar2 = zzb.zzc;
                if (zzbxlVar2 != null) {
                    zza.zzb("adapter_sv", zzbxlVar2.toString());
                }
            }
            zza.zzg();
            return;
        }
        zzfhy zzb2 = zzfhy.zzb("adapter_status");
        zzb2.zzg(zzfcvVar);
        zzb2.zzf(zzfcsVar);
        zzb2.zza("adapter_l", String.valueOf(j));
        zzb2.zza("sc", Integer.toString(r12));
        if (zzegoVar != null) {
            zzb2.zza("arec", Integer.toString(zzegoVar.zzb().zza));
            String zza3 = this.zza.zza(zzegoVar.getMessage());
            if (zza3 != null) {
                zzb2.zza("areec", zza3);
            }
        }
        zzdvf zzb3 = this.zzb.zzb(zzfcsVar.zzu);
        if (zzb3 != null) {
            zzb2.zza("ancn", zzb3.zza);
            zzbxl zzbxlVar3 = zzb3.zzb;
            if (zzbxlVar3 != null) {
                zzb2.zza("adapter_v", zzbxlVar3.toString());
            }
            zzbxl zzbxlVar4 = zzb3.zzc;
            if (zzbxlVar4 != null) {
                zzb2.zza("adapter_sv", zzbxlVar4.toString());
            }
        }
        this.zzd.zzb(zzb2);
    }
}
