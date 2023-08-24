package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeqj implements zzeun {
    private static final Object zza = new Object();
    private final String zzb;
    private final String zzc;
    private final zzdap zzd;
    private final zzfes zze;
    private final zzfdn zzf;
    private final com.google.android.gms.ads.internal.util.zzg zzg = com.google.android.gms.ads.internal.zzt.zzp().zzh();
    private final zzdxj zzh;

    public zzeqj(String str, String str2, zzdap zzdapVar, zzfes zzfesVar, zzfdn zzfdnVar, zzdxj zzdxjVar) {
        this.zzb = str;
        this.zzc = str2;
        this.zzd = zzdapVar;
        this.zze = zzfesVar;
        this.zzf = zzfdnVar;
        this.zzh = zzdxjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 12;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        final Bundle bundle = new Bundle();
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgz)).booleanValue()) {
            this.zzh.zza().put("seq_num", this.zzb);
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeE)).booleanValue()) {
            this.zzd.zzg(this.zzf.zzd);
            bundle.putAll(this.zze.zzb());
        }
        return zzfyo.zzi(new zzeum() { // from class: com.google.android.gms.internal.ads.zzeqi
            @Override // com.google.android.gms.internal.ads.zzeum
            public final void zzf(Object obj) {
                zzeqj.this.zzc(bundle, (Bundle) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc(Bundle bundle, Bundle bundle2) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeE)).booleanValue()) {
            bundle2.putBundle("quality_signals", bundle);
        } else {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeD)).booleanValue()) {
                synchronized (zza) {
                    this.zzd.zzg(this.zzf.zzd);
                    bundle2.putBundle("quality_signals", this.zze.zzb());
                }
            } else {
                this.zzd.zzg(this.zzf.zzd);
                bundle2.putBundle("quality_signals", this.zze.zzb());
            }
        }
        bundle2.putString("seq_num", this.zzb);
        if (this.zzg.zzP()) {
            return;
        }
        bundle2.putString("session_id", this.zzc);
    }
}
