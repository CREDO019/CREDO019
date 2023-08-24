package com.google.android.gms.internal.ads;

import android.view.View;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdoi implements zzbbm {
    final /* synthetic */ String zza;
    final /* synthetic */ zzdoj zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdoi(zzdoj zzdojVar, String str) {
        this.zzb = zzdojVar;
        this.zza = str;
    }

    @Override // com.google.android.gms.internal.ads.zzbbm
    public final void zzc(zzbbl zzbblVar) {
        Map map;
        zzdqj zzdqjVar;
        zzdqj zzdqjVar2;
        zzdqj zzdqjVar3;
        zzdqj zzdqjVar4;
        Map map2;
        zzdqj zzdqjVar5;
        zzdqj zzdqjVar6;
        zzdqj zzdqjVar7;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzbv)).booleanValue()) {
            synchronized (this) {
                if (zzbblVar.zzj) {
                    zzdoj zzdojVar = this.zzb;
                    zzdqjVar4 = zzdojVar.zzo;
                    if (zzdqjVar4 == null) {
                        return;
                    }
                    map2 = zzdojVar.zzy;
                    map2.put(this.zza, true);
                    zzdoj zzdojVar2 = this.zzb;
                    zzdqjVar5 = zzdojVar2.zzo;
                    View zzf = zzdqjVar5.zzf();
                    zzdqjVar6 = this.zzb.zzo;
                    Map zzl = zzdqjVar6.zzl();
                    zzdqjVar7 = this.zzb.zzo;
                    zzdojVar2.zzv(zzf, zzl, zzdqjVar7.zzm(), true);
                }
            }
        } else if (zzbblVar.zzj) {
            map = this.zzb.zzy;
            map.put(this.zza, true);
            zzdoj zzdojVar3 = this.zzb;
            zzdqjVar = zzdojVar3.zzo;
            View zzf2 = zzdqjVar.zzf();
            zzdqjVar2 = this.zzb.zzo;
            Map zzl2 = zzdqjVar2.zzl();
            zzdqjVar3 = this.zzb.zzo;
            zzdojVar3.zzv(zzf2, zzl2, zzdqjVar3.zzm(), true);
        }
    }
}
