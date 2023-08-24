package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzcps extends zzevf {
    private final zzewr zza;
    private final zzcpu zzb;
    private final zzcps zzc = this;
    private final zzgve zzd;
    private final zzgve zze;
    private final zzgve zzf;
    private final zzgve zzg;
    private final zzgve zzh;
    private final zzgve zzi;
    private final zzgve zzj;
    private final zzgve zzk;
    private final zzgve zzl;
    private final zzgve zzm;
    private final zzgve zzn;
    private final zzgve zzo;
    private final zzgve zzp;
    private final zzgve zzq;
    private final zzgve zzr;
    private final zzgve zzs;
    private final zzgve zzt;
    private final zzgve zzu;
    private final zzgve zzv;
    private final zzgve zzw;
    private final zzgve zzx;
    private final zzgve zzy;
    private final zzgve zzz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzcps(zzcpu zzcpuVar, zzewr zzewrVar, zzcpr zzcprVar) {
        zzgve zzgveVar;
        zzctn zzctnVar;
        zzgve zzgveVar2;
        zzgve zzgveVar3;
        zzcpe zzcpeVar;
        zzgve zzgveVar4;
        zzctn zzctnVar2;
        zzgve zzgveVar5;
        zzgve zzgveVar6;
        zzgve zzgveVar7;
        zzcth zzcthVar;
        zzgve zzgveVar8;
        zzctj zzctjVar;
        zzctl zzctlVar;
        zzgve zzgveVar9;
        zzgve zzgveVar10;
        zzgve zzgveVar11;
        zzctp zzctpVar;
        zzgve zzgveVar12;
        zzctf zzctfVar;
        zzgve zzgveVar13;
        zzgve zzgveVar14;
        zzgve zzgveVar15;
        zzgve zzgveVar16;
        this.zzb = zzcpuVar;
        this.zza = zzewrVar;
        zzgveVar = zzcpuVar.zzF;
        this.zzd = zzguq.zzc(new zzfjd(zzgveVar));
        zzewt zzewtVar = new zzewt(zzewrVar);
        this.zze = zzewtVar;
        zzctnVar = zzctm.zza;
        zzgveVar2 = zzcpuVar.zzh;
        zzgveVar3 = zzcpuVar.zzn;
        this.zzf = new zzeve(zzctnVar, zzgveVar2, zzgveVar3, zzfgk.zza(), zzewtVar);
        zzews zzewsVar = new zzews(zzewrVar);
        this.zzg = zzewsVar;
        zzcpeVar = zzcpd.zza;
        zzgveVar4 = zzcpuVar.zzh;
        this.zzh = new zzevn(zzcpeVar, zzgveVar4, zzewsVar, zzfgk.zza());
        zzctnVar2 = zzctm.zza;
        zzgveVar5 = zzcpuVar.zzh;
        zzgveVar6 = zzcpuVar.zzac;
        zzgveVar7 = zzcpuVar.zzn;
        this.zzi = new zzevy(zzctnVar2, zzewtVar, zzgveVar5, zzgveVar6, zzgveVar7, zzfgk.zza(), zzewsVar);
        zzcthVar = zzctg.zza;
        zzfgk zza = zzfgk.zza();
        zzgveVar8 = zzcpuVar.zzh;
        this.zzj = new zzewc(zzcthVar, zza, zzgveVar8);
        zzctjVar = zzcti.zza;
        this.zzk = new zzewj(zzctjVar, zzfgk.zza(), zzewsVar);
        zzctlVar = zzctk.zza;
        zzgveVar9 = zzcpuVar.zzn;
        zzgveVar10 = zzcpuVar.zzh;
        this.zzl = new zzewq(zzctlVar, zzgveVar9, zzgveVar10);
        this.zzm = new zzexh(zzfgk.zza());
        zzewu zzewuVar = new zzewu(zzewrVar);
        this.zzn = zzewuVar;
        zzgveVar11 = zzcpuVar.zzac;
        zzctpVar = zzcto.zza;
        zzfgk zza2 = zzfgk.zza();
        zzgveVar12 = zzcpuVar.zzn;
        this.zzo = new zzexd(zzgveVar11, zzewuVar, zzctpVar, zza2, zzewsVar, zzgveVar12);
        zzctfVar = zzcte.zza;
        zzgveVar13 = zzcpuVar.zzac;
        zzgveVar14 = zzcpuVar.zzn;
        this.zzp = new zzevs(zzewsVar, zzctfVar, zzgveVar13, zzgveVar14, zzfgk.zza());
        zzewv zzewvVar = new zzewv(zzewrVar);
        this.zzq = zzewvVar;
        zzgve zzc = zzguq.zzc(zzdwn.zza());
        this.zzr = zzc;
        zzgve zzc2 = zzguq.zzc(zzdwl.zza());
        this.zzs = zzc2;
        zzgve zzc3 = zzguq.zzc(zzdwp.zza());
        this.zzt = zzc3;
        zzgve zzc4 = zzguq.zzc(zzdwr.zza());
        this.zzu = zzc4;
        zzguu zzc5 = zzguv.zzc(4);
        zzc5.zzb(zzfhj.GMS_SIGNALS, zzc);
        zzc5.zzb(zzfhj.BUILD_URL, zzc2);
        zzc5.zzb(zzfhj.HTTP, zzc3);
        zzc5.zzb(zzfhj.PRE_PROCESS, zzc4);
        zzguv zzc6 = zzc5.zzc();
        this.zzv = zzc6;
        zzgveVar15 = zzcpuVar.zzh;
        zzgve zzc7 = zzguq.zzc(new zzdws(zzewvVar, zzgveVar15, zzfgk.zza(), zzc6));
        this.zzw = zzc7;
        zzgvb zza3 = zzgvc.zza(0, 1);
        zza3.zza(zzc7);
        zzgvc zzc8 = zza3.zzc();
        this.zzx = zzc8;
        zzfhs zzfhsVar = new zzfhs(zzc8);
        this.zzy = zzfhsVar;
        zzfgk zza4 = zzfgk.zza();
        zzgveVar16 = zzcpuVar.zzn;
        this.zzz = zzguq.zzc(new zzfhr(zza4, zzgveVar16, zzfhsVar));
    }

    private final zzevi zze() {
        zzcfn zzcfnVar = new zzcfn();
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        String zzd = this.zza.zzd();
        zzguz.zzb(zzd);
        return new zzevi(zzcfnVar, zzfyyVar, zzd, this.zza.zzb(), this.zza.zza(), null);
    }

    private final zzewl zzf() {
        zzbij zzbijVar = new zzbij();
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        List zzf = this.zza.zzf();
        zzguz.zzb(zzf);
        return new zzewl(zzbijVar, zzfyyVar, zzf, null);
    }

    @Override // com.google.android.gms.internal.ads.zzevf
    public final zzeuq zza() {
        zzcon zzconVar;
        zzgve zzgveVar;
        zzgve zzgveVar2;
        zzconVar = this.zzb.zza;
        Context zza = zzconVar.zza();
        zzguz.zzb(zza);
        zzcfk zzcfkVar = new zzcfk();
        zzcfl zzcflVar = new zzcfl();
        zzgveVar = this.zzb.zzaC;
        Object zzb = zzgveVar.zzb();
        zzevi zze = zze();
        zzewl zzf = zzf();
        zzgul zza2 = zzguq.zza(this.zzf);
        zzgul zza3 = zzguq.zza(this.zzh);
        zzgul zza4 = zzguq.zza(this.zzi);
        zzgul zza5 = zzguq.zza(this.zzj);
        zzgul zza6 = zzguq.zza(this.zzk);
        zzgul zza7 = zzguq.zza(this.zzl);
        zzgul zza8 = zzguq.zza(this.zzm);
        zzgul zza9 = zzguq.zza(this.zzo);
        zzgul zza10 = zzguq.zza(this.zzp);
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        zzfjc zzfjcVar = (zzfjc) this.zzd.zzb();
        zzgveVar2 = this.zzb.zzV;
        return zzewy.zza(zza, zzcfkVar, zzcflVar, zzb, zze, zzf, zza2, zza3, zza4, zza5, zza6, zza7, zza8, zza9, zza10, zzfyyVar, zzfjcVar, (zzdxo) zzgveVar2.zzb());
    }

    @Override // com.google.android.gms.internal.ads.zzevf
    public final zzeuq zzb() {
        zzcon zzconVar;
        zzgve zzgveVar;
        zzgve zzgveVar2;
        zzcon zzconVar2;
        zzgve zzgveVar3;
        zzcon zzconVar3;
        zzgve zzgveVar4;
        zzgve zzgveVar5;
        zzgve zzgveVar6;
        zzcon zzconVar4;
        zzcon zzconVar5;
        zzcon zzconVar6;
        zzgve zzgveVar7;
        zzgve zzgveVar8;
        zzgve zzgveVar9;
        zzgve zzgveVar10;
        zzgve zzgveVar11;
        zzgve zzgveVar12;
        zzconVar = this.zzb.zza;
        Context zza = zzconVar.zza();
        zzguz.zzb(zza);
        zzfyy zzfyyVar = zzcha.zza;
        zzguz.zzb(zzfyyVar);
        zzcfk zzcfkVar = new zzcfk();
        zzfyy zzfyyVar2 = zzcha.zza;
        zzguz.zzb(zzfyyVar2);
        String zzc = this.zza.zzc();
        zzguz.zzb(zzc);
        zzewh zzewhVar = new zzewh(zzcfkVar, zzfyyVar2, zzc, null);
        zzgveVar = this.zzb.zzn;
        zzesy zzesyVar = new zzesy(zzewhVar, 0L, (ScheduledExecutorService) zzgveVar.zzb());
        zzbze zzbzeVar = new zzbze();
        zzgveVar2 = this.zzb.zzn;
        zzconVar2 = this.zzb.zza;
        Context zza2 = zzconVar2.zza();
        zzguz.zzb(zza2);
        zzewo zzewoVar = new zzewo(zzbzeVar, (ScheduledExecutorService) zzgveVar2.zzb(), zza2, null);
        zzgveVar3 = this.zzb.zzn;
        zzesy zzesyVar2 = new zzesy(zzewoVar, ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdg)).longValue(), (ScheduledExecutorService) zzgveVar3.zzb());
        zzcfn zzcfnVar = new zzcfn();
        zzconVar3 = this.zzb.zza;
        Context zza3 = zzconVar3.zza();
        zzguz.zzb(zza3);
        zzgveVar4 = this.zzb.zzn;
        zzfyy zzfyyVar3 = zzcha.zza;
        zzguz.zzb(zzfyyVar3);
        zzevc zzevcVar = new zzevc(zzcfnVar, zza3, (ScheduledExecutorService) zzgveVar4.zzb(), zzfyyVar3, this.zza.zza(), null);
        zzgveVar5 = this.zzb.zzn;
        zzesy zzesyVar3 = new zzesy(zzevcVar, 0L, (ScheduledExecutorService) zzgveVar5.zzb());
        zzfyy zzfyyVar4 = zzcha.zza;
        zzguz.zzb(zzfyyVar4);
        zzexf zzexfVar = new zzexf(zzfyyVar4);
        zzgveVar6 = this.zzb.zzn;
        zzesy zzesyVar4 = new zzesy(zzexfVar, 0L, (ScheduledExecutorService) zzgveVar6.zzb());
        zzeww zzewwVar = new zzeun() { // from class: com.google.android.gms.internal.ads.zzeww
            @Override // com.google.android.gms.internal.ads.zzeun
            public final /* synthetic */ int zza() {
                return 0;
            }

            @Override // com.google.android.gms.internal.ads.zzeun
            public final zzfyx zzb() {
                return zzfyo.zzi(new zzeum() { // from class: com.google.android.gms.internal.ads.zzewx
                    @Override // com.google.android.gms.internal.ads.zzeum
                    public final void zzf(Object obj) {
                        try {
                            ((JSONObject) obj).getJSONObject("sdk_env").put("container_version", GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
                        } catch (JSONException unused) {
                        }
                    }
                });
            }
        };
        zzconVar4 = this.zzb.zza;
        Context zza4 = zzconVar4.zza();
        zzguz.zzb(zza4);
        String zzc2 = this.zza.zzc();
        zzguz.zzb(zzc2);
        zzfyy zzfyyVar5 = zzcha.zza;
        zzguz.zzb(zzfyyVar5);
        zzevl zzevlVar = new zzevl(null, zza4, zzc2, zzfyyVar5);
        zzbea zzbeaVar = new zzbea();
        zzfyy zzfyyVar6 = zzcha.zza;
        zzguz.zzb(zzfyyVar6);
        zzconVar5 = this.zzb.zza;
        Context zza5 = zzconVar5.zza();
        zzguz.zzb(zza5);
        zzcfn zzcfnVar2 = new zzcfn();
        int zza6 = this.zza.zza();
        zzconVar6 = this.zzb.zza;
        Context zza7 = zzconVar6.zza();
        zzguz.zzb(zza7);
        zzgveVar7 = this.zzb.zzac;
        zzcfw zzcfwVar = (zzcfw) zzgveVar7.zzb();
        zzgveVar8 = this.zzb.zzn;
        ScheduledExecutorService scheduledExecutorService = (ScheduledExecutorService) zzgveVar8.zzb();
        zzfyy zzfyyVar7 = zzcha.zza;
        zzguz.zzb(zzfyyVar7);
        String zzc3 = this.zza.zzc();
        zzguz.zzb(zzc3);
        zzgveVar9 = this.zzb.zzaC;
        String zzc4 = this.zza.zzc();
        zzguz.zzb(zzc4);
        zzbdo zzbdoVar = new zzbdo();
        zzgveVar10 = this.zzb.zzac;
        zzgveVar11 = this.zzb.zzn;
        zzfyy zzfyyVar8 = zzcha.zza;
        zzguz.zzb(zzfyyVar8);
        zzfva zzm = zzfva.zzm(zzesyVar, zzesyVar2, zzesyVar3, zzesyVar4, zzewwVar, zzevlVar, new zzewa(zzbeaVar, zzfyyVar6, zza5, null), zzf(), zze(), new zzevw(zzcfnVar2, zza6, zza7, zzcfwVar, scheduledExecutorService, zzfyyVar7, zzc3, null), (zzeun) zzgveVar9.zzb(), zzevs.zza(zzc4, zzbdoVar, (zzcfw) zzgveVar10.zzb(), (ScheduledExecutorService) zzgveVar11.zzb(), zzfyyVar8));
        zzfjc zzfjcVar = (zzfjc) this.zzd.zzb();
        zzgveVar12 = this.zzb.zzV;
        return new zzeuq(zza, zzfyyVar, zzm, zzfjcVar, (zzdxo) zzgveVar12.zzb());
    }

    @Override // com.google.android.gms.internal.ads.zzevf
    public final zzfhp zzc() {
        return (zzfhp) this.zzz.zzb();
    }

    @Override // com.google.android.gms.internal.ads.zzevf
    public final zzfjc zzd() {
        return (zzfjc) this.zzd.zzb();
    }
}
