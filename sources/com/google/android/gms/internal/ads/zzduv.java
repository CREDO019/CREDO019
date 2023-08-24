package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.view.View;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzduv {
    private final zzdcw zza;
    private final zzdkj zzb;
    private final zzdef zzc;
    private final zzdes zzd;
    private final zzdfe zze;
    private final zzdhr zzf;
    private final Executor zzg;
    private final zzdkg zzh;
    private final zzcvi zzi;
    private final com.google.android.gms.ads.internal.zzb zzj;
    private final zzcdo zzk;
    private final zzapb zzl;
    private final zzdhi zzm;
    private final zzefz zzn;
    private final zzfju zzo;
    private final zzdxo zzp;
    private final zzfhz zzq;

    public zzduv(zzdcw zzdcwVar, zzdef zzdefVar, zzdes zzdesVar, zzdfe zzdfeVar, zzdhr zzdhrVar, Executor executor, zzdkg zzdkgVar, zzcvi zzcviVar, com.google.android.gms.ads.internal.zzb zzbVar, zzcdo zzcdoVar, zzapb zzapbVar, zzdhi zzdhiVar, zzefz zzefzVar, zzfju zzfjuVar, zzdxo zzdxoVar, zzfhz zzfhzVar, zzdkj zzdkjVar) {
        this.zza = zzdcwVar;
        this.zzc = zzdefVar;
        this.zzd = zzdesVar;
        this.zze = zzdfeVar;
        this.zzf = zzdhrVar;
        this.zzg = executor;
        this.zzh = zzdkgVar;
        this.zzi = zzcviVar;
        this.zzj = zzbVar;
        this.zzk = zzcdoVar;
        this.zzl = zzapbVar;
        this.zzm = zzdhiVar;
        this.zzn = zzefzVar;
        this.zzo = zzfjuVar;
        this.zzp = zzdxoVar;
        this.zzq = zzfhzVar;
        this.zzb = zzdkjVar;
    }

    public static final zzfyx zzj(zzcmn zzcmnVar, String str, String str2) {
        final zzchf zzchfVar = new zzchf();
        zzcmnVar.zzP().zzz(new zzcny() { // from class: com.google.android.gms.internal.ads.zzdut
            @Override // com.google.android.gms.internal.ads.zzcny
            public final void zza(boolean z) {
                zzchf zzchfVar2 = zzchf.this;
                if (z) {
                    zzchfVar2.zzd(null);
                } else {
                    zzchfVar2.zze(new Exception("Ad Web View failed to load."));
                }
            }
        });
        zzcmnVar.zzad(str, str2, null);
        return zzchfVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzc() {
        this.zza.onAdClicked();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzd(String str, String str2) {
        this.zzf.zzbD(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zze() {
        this.zzc.zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(View view) {
        this.zzj.zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(zzcmn zzcmnVar, zzcmn zzcmnVar2, Map map) {
        this.zzi.zzh(zzcmnVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ boolean zzh(View view, MotionEvent motionEvent) {
        this.zzj.zza();
        if (view != null) {
            view.performClick();
            return false;
        }
        return false;
    }

    public final void zzi(final zzcmn zzcmnVar, boolean z, zzbpt zzbptVar) {
        zzaox zzc;
        zzcmnVar.zzP().zzL(new com.google.android.gms.ads.internal.client.zza() { // from class: com.google.android.gms.internal.ads.zzdum
            @Override // com.google.android.gms.ads.internal.client.zza
            public final void onAdClicked() {
                zzduv.this.zzc();
            }
        }, this.zzd, this.zze, new zzbon() { // from class: com.google.android.gms.internal.ads.zzdun
            @Override // com.google.android.gms.internal.ads.zzbon
            public final void zzbD(String str, String str2) {
                zzduv.this.zzd(str, str2);
            }
        }, new com.google.android.gms.ads.internal.overlay.zzz() { // from class: com.google.android.gms.internal.ads.zzduo
            @Override // com.google.android.gms.ads.internal.overlay.zzz
            public final void zzg() {
                zzduv.this.zze();
            }
        }, z, zzbptVar, this.zzj, new zzduu(this), this.zzk, this.zzn, this.zzo, this.zzp, this.zzq, null, this.zzb, null);
        zzcmnVar.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.gms.internal.ads.zzdup
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                zzduv.this.zzh(view, motionEvent);
                return false;
            }
        });
        zzcmnVar.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.gms.internal.ads.zzduq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                zzduv.this.zzf(view);
            }
        });
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcf)).booleanValue() && (zzc = this.zzl.zzc()) != null) {
            zzc.zzn((View) zzcmnVar);
        }
        this.zzh.zzj(zzcmnVar, this.zzg);
        this.zzh.zzj(new zzbbm() { // from class: com.google.android.gms.internal.ads.zzdur
            @Override // com.google.android.gms.internal.ads.zzbbm
            public final void zzc(zzbbl zzbblVar) {
                zzcmn.this.zzP().zzo(zzbblVar.zzd.left, zzbblVar.zzd.top, false);
            }
        }, this.zzg);
        this.zzh.zza((View) zzcmnVar);
        zzcmnVar.zzaf("/trackActiveViewUnit", new zzbpq() { // from class: com.google.android.gms.internal.ads.zzdus
            @Override // com.google.android.gms.internal.ads.zzbpq
            public final void zza(Object obj, Map map) {
                zzduv.this.zzg(zzcmnVar, (zzcmn) obj, map);
            }
        });
        this.zzi.zzi(zzcmnVar);
    }
}
