package com.google.android.gms.internal.ads;

import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzbtr {
    private final Context zzb;
    private final String zzc;
    private final zzcgt zzd;
    private final zzfje zze;
    private final com.google.android.gms.ads.internal.util.zzbb zzf;
    private final com.google.android.gms.ads.internal.util.zzbb zzg;
    private zzbtq zzh;
    private final Object zza = new Object();
    private int zzi = 1;

    public zzbtr(Context context, zzcgt zzcgtVar, String str, com.google.android.gms.ads.internal.util.zzbb zzbbVar, com.google.android.gms.ads.internal.util.zzbb zzbbVar2, zzfje zzfjeVar) {
        this.zzc = str;
        this.zzb = context.getApplicationContext();
        this.zzd = zzcgtVar;
        this.zze = zzfjeVar;
        this.zzf = zzbbVar;
        this.zzg = zzbbVar2;
    }

    public final zzbtl zzb(zzapb zzapbVar) {
        synchronized (this.zza) {
            synchronized (this.zza) {
                zzbtq zzbtqVar = this.zzh;
                if (zzbtqVar != null && this.zzi == 0) {
                    zzbtqVar.zzi(new zzchj() { // from class: com.google.android.gms.internal.ads.zzbsw
                        @Override // com.google.android.gms.internal.ads.zzchj
                        public final void zza(Object obj) {
                            zzbtr.this.zzk((zzbsm) obj);
                        }
                    }, new zzchh() { // from class: com.google.android.gms.internal.ads.zzbsx
                        @Override // com.google.android.gms.internal.ads.zzchh
                        public final void zza() {
                        }
                    });
                }
            }
            zzbtq zzbtqVar2 = this.zzh;
            if (zzbtqVar2 != null && zzbtqVar2.zze() != -1) {
                int r0 = this.zzi;
                if (r0 == 0) {
                    return this.zzh.zza();
                } else if (r0 != 1) {
                    return this.zzh.zza();
                } else {
                    this.zzi = 2;
                    zzd(null);
                    return this.zzh.zza();
                }
            }
            this.zzi = 2;
            zzbtq zzd = zzd(null);
            this.zzh = zzd;
            return zzd.zza();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzbtq zzd(zzapb zzapbVar) {
        zzfir zza = zzfiq.zza(this.zzb, 6);
        zza.zzf();
        final zzbtq zzbtqVar = new zzbtq(this.zzg);
        zzcha.zze.execute(new Runnable(null, zzbtqVar) { // from class: com.google.android.gms.internal.ads.zzbsy
            public final /* synthetic */ zzbtq zzb;

            {
                this.zzb = zzbtqVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzbtr.this.zzj(null, this.zzb);
            }
        });
        zzbtqVar.zzi(new zzbtg(this, zzbtqVar, zza), new zzbth(this, zzbtqVar, zza));
        return zzbtqVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzi(zzbtq zzbtqVar, final zzbsm zzbsmVar) {
        synchronized (this.zza) {
            if (zzbtqVar.zze() != -1 && zzbtqVar.zze() != 1) {
                zzbtqVar.zzg();
                zzcha.zze.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbtb
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzbsm.this.zzc();
                    }
                });
                com.google.android.gms.ads.internal.util.zze.zza("Could not receive loaded message in a timely manner. Rejecting.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzj(zzapb zzapbVar, zzbtq zzbtqVar) {
        try {
            zzbsu zzbsuVar = new zzbsu(this.zzb, this.zzd, null, null);
            zzbsuVar.zzk(new zzbta(this, zzbtqVar, zzbsuVar));
            zzbsuVar.zzq("/jsLoaded", new zzbtc(this, zzbtqVar, zzbsuVar));
            com.google.android.gms.ads.internal.util.zzca zzcaVar = new com.google.android.gms.ads.internal.util.zzca();
            zzbtd zzbtdVar = new zzbtd(this, null, zzbsuVar, zzcaVar);
            zzcaVar.zzb(zzbtdVar);
            zzbsuVar.zzq("/requestReload", zzbtdVar);
            if (this.zzc.endsWith(".js")) {
                zzbsuVar.zzh(this.zzc);
            } else if (this.zzc.startsWith("<html>")) {
                zzbsuVar.zzf(this.zzc);
            } else {
                zzbsuVar.zzg(this.zzc);
            }
            com.google.android.gms.ads.internal.util.zzs.zza.postDelayed(new zzbtf(this, zzbtqVar, zzbsuVar), 60000L);
        } catch (Throwable th) {
            com.google.android.gms.ads.internal.util.zze.zzh("Error creating webview.", th);
            com.google.android.gms.ads.internal.zzt.zzp().zzt(th, "SdkJavascriptFactory.loadJavascriptEngine");
            zzbtqVar.zzg();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzk(zzbsm zzbsmVar) {
        if (zzbsmVar.zzi()) {
            this.zzi = 1;
        }
    }
}
