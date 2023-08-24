package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdsg {
    private final zzfdn zza;
    private final Executor zzb;
    private final zzduw zzc;
    private final zzdtr zzd;
    private final Context zze;
    private final zzdxo zzf;
    private final zzfhz zzg;
    private final zzfju zzh;
    private final zzefz zzi;

    public zzdsg(zzfdn zzfdnVar, Executor executor, zzduw zzduwVar, Context context, zzdxo zzdxoVar, zzfhz zzfhzVar, zzfju zzfjuVar, zzefz zzefzVar, zzdtr zzdtrVar) {
        this.zza = zzfdnVar;
        this.zzb = executor;
        this.zzc = zzduwVar;
        this.zze = context;
        this.zzf = zzdxoVar;
        this.zzg = zzfhzVar;
        this.zzh = zzfjuVar;
        this.zzi = zzefzVar;
        this.zzd = zzdtrVar;
    }

    private final void zzh(zzcmn zzcmnVar) {
        zzi(zzcmnVar);
        zzcmnVar.zzaf("/video", zzbpp.zzl);
        zzcmnVar.zzaf("/videoMeta", zzbpp.zzm);
        zzcmnVar.zzaf("/precache", new zzcla());
        zzcmnVar.zzaf("/delayPageLoaded", zzbpp.zzp);
        zzcmnVar.zzaf("/instrument", zzbpp.zzn);
        zzcmnVar.zzaf("/log", zzbpp.zzg);
        zzcmnVar.zzaf("/click", zzbpp.zza(null));
        if (this.zza.zzb != null) {
            zzcmnVar.zzP().zzC(true);
            zzcmnVar.zzaf("/open", new zzbqb(null, null, null, null, null));
        } else {
            zzcmnVar.zzP().zzC(false);
        }
        if (com.google.android.gms.ads.internal.zzt.zzo().zzu(zzcmnVar.getContext())) {
            zzcmnVar.zzaf("/logScionEvent", new zzbpw(zzcmnVar.getContext()));
        }
    }

    private static final void zzi(zzcmn zzcmnVar) {
        zzcmnVar.zzaf("/videoClicked", zzbpp.zzh);
        zzcmnVar.zzP().zzE(true);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcQ)).booleanValue()) {
            zzcmnVar.zzaf("/getNativeAdViewSignals", zzbpp.zzs);
        }
        zzcmnVar.zzaf("/getNativeClickMeta", zzbpp.zzt);
    }

    public final zzfyx zza(final JSONObject jSONObject) {
        return zzfyo.zzn(zzfyo.zzn(zzfyo.zzi(null), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzdrw
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzdsg.this.zze(obj);
            }
        }, this.zzb), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzdrx
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzdsg.this.zzc(jSONObject, (zzcmn) obj);
            }
        }, this.zzb);
    }

    public final zzfyx zzb(final String str, final String str2, final zzfcs zzfcsVar, final zzfcv zzfcvVar, final com.google.android.gms.ads.internal.client.zzq zzqVar) {
        return zzfyo.zzn(zzfyo.zzi(null), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzdrz
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzdsg.this.zzd(zzqVar, zzfcsVar, zzfcvVar, str, str2, obj);
            }
        }, this.zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzc(JSONObject jSONObject, final zzcmn zzcmnVar) throws Exception {
        final zzche zza = zzche.zza(zzcmnVar);
        if (this.zza.zzb != null) {
            zzcmnVar.zzai(zzcoc.zzd());
        } else {
            zzcmnVar.zzai(zzcoc.zze());
        }
        zzcmnVar.zzP().zzz(new zzcny() { // from class: com.google.android.gms.internal.ads.zzdrv
            @Override // com.google.android.gms.internal.ads.zzcny
            public final void zza(boolean z) {
                zzdsg.this.zzf(zzcmnVar, zza, z);
            }
        });
        zzcmnVar.zzl("google.afma.nativeAds.renderVideo", jSONObject);
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzd(com.google.android.gms.ads.internal.client.zzq zzqVar, zzfcs zzfcsVar, zzfcv zzfcvVar, String str, String str2, Object obj) throws Exception {
        final zzcmn zza = this.zzc.zza(zzqVar, zzfcsVar, zzfcvVar);
        final zzche zza2 = zzche.zza(zza);
        if (this.zza.zzb != null) {
            zzh(zza);
            zza.zzai(zzcoc.zzd());
        } else {
            zzdto zzb = this.zzd.zzb();
            zza.zzP().zzL(zzb, zzb, zzb, zzb, zzb, false, null, new com.google.android.gms.ads.internal.zzb(this.zze, null, null), null, null, this.zzi, this.zzh, this.zzf, this.zzg, null, zzb, null);
            zzi(zza);
        }
        zza.zzP().zzz(new zzcny() { // from class: com.google.android.gms.internal.ads.zzdsa
            @Override // com.google.android.gms.internal.ads.zzcny
            public final void zza(boolean z) {
                zzdsg.this.zzg(zza, zza2, z);
            }
        });
        zza.zzad(str, str2, null);
        return zza2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zze(Object obj) throws Exception {
        zzcmn zza = this.zzc.zza(com.google.android.gms.ads.internal.client.zzq.zzc(), null, null);
        final zzche zza2 = zzche.zza(zza);
        zzh(zza);
        zza.zzP().zzF(new zzcnz() { // from class: com.google.android.gms.internal.ads.zzdry
            @Override // com.google.android.gms.internal.ads.zzcnz
            public final void zza() {
                zzche.this.zzb();
            }
        });
        zza.loadUrl((String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcP));
        return zza2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(zzcmn zzcmnVar, zzche zzcheVar, boolean z) {
        if (this.zza.zza != null && zzcmnVar.zzs() != null) {
            zzcmnVar.zzs().zzs(this.zza.zza);
        }
        zzcheVar.zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(zzcmn zzcmnVar, zzche zzcheVar, boolean z) {
        if (z) {
            if (this.zza.zza != null && zzcmnVar.zzs() != null) {
                zzcmnVar.zzs().zzs(this.zza.zza);
            }
            zzcheVar.zzb();
            return;
        }
        zzcheVar.zze(new zzeka(1, "Html video Web View failed to load."));
    }
}
