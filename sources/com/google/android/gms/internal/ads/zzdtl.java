package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.Executor;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdtl {
    private final com.google.android.gms.ads.internal.zza zzb;
    private final zzcmz zzc;
    private final Context zzd;
    private final zzdxo zze;
    private final zzfhz zzf;
    private final Executor zzg;
    private final zzapb zzh;
    private final zzcgt zzi;
    private final zzefz zzk;
    private final zzfju zzl;
    private zzfyx zzm;
    private final zzdtf zza = new zzdtf(null);
    private final zzbqe zzj = new zzbqe();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdtl(zzdti zzdtiVar) {
        this.zzd = zzdti.zza(zzdtiVar);
        this.zzg = zzdti.zzj(zzdtiVar);
        this.zzh = zzdti.zzb(zzdtiVar);
        this.zzi = zzdti.zzd(zzdtiVar);
        this.zzb = zzdti.zzc(zzdtiVar);
        this.zzc = zzdti.zze(zzdtiVar);
        this.zzk = zzdti.zzg(zzdtiVar);
        this.zzl = zzdti.zzi(zzdtiVar);
        this.zze = zzdti.zzf(zzdtiVar);
        this.zzf = zzdti.zzh(zzdtiVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzcmn zza(zzcmn zzcmnVar) {
        zzcmnVar.zzaf("/result", this.zzj);
        zzcoa zzP = zzcmnVar.zzP();
        zzdtf zzdtfVar = this.zza;
        zzP.zzL(null, zzdtfVar, zzdtfVar, zzdtfVar, zzdtfVar, false, null, new com.google.android.gms.ads.internal.zzb(this.zzd, null, null), null, null, this.zzk, this.zzl, this.zze, this.zzf, null, null, null);
        return zzcmnVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzc(String str, JSONObject jSONObject, zzcmn zzcmnVar) throws Exception {
        return this.zzj.zzb(zzcmnVar, str, jSONObject);
    }

    public final synchronized zzfyx zzd(final String str, final JSONObject jSONObject) {
        zzfyx zzfyxVar = this.zzm;
        if (zzfyxVar == null) {
            return zzfyo.zzi(null);
        }
        return zzfyo.zzn(zzfyxVar, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzdsx
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzdtl.this.zzc(str, jSONObject, (zzcmn) obj);
            }
        }, this.zzg);
    }

    public final synchronized void zze(zzfcs zzfcsVar, zzfcv zzfcvVar) {
        zzfyx zzfyxVar = this.zzm;
        if (zzfyxVar == null) {
            return;
        }
        zzfyo.zzr(zzfyxVar, new zzdtd(this, zzfcsVar, zzfcvVar), this.zzg);
    }

    public final synchronized void zzf() {
        zzfyx zzfyxVar = this.zzm;
        if (zzfyxVar == null) {
            return;
        }
        zzfyo.zzr(zzfyxVar, new zzdsz(this), this.zzg);
        this.zzm = null;
    }

    public final synchronized void zzg(String str, Map map) {
        zzfyx zzfyxVar = this.zzm;
        if (zzfyxVar == null) {
            return;
        }
        zzfyo.zzr(zzfyxVar, new zzdtc(this, "sendMessageToNativeJs", map), this.zzg);
    }

    public final synchronized void zzh() {
        final Context context = this.zzd;
        final zzcgt zzcgtVar = this.zzi;
        final String str = (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcO);
        final zzapb zzapbVar = this.zzh;
        final com.google.android.gms.ads.internal.zza zzaVar = this.zzb;
        zzfyx zzm = zzfyo.zzm(zzfyo.zzl(new zzfxu() { // from class: com.google.android.gms.internal.ads.zzcmw
            @Override // com.google.android.gms.internal.ads.zzfxu
            public final zzfyx zza() {
                Context context2 = context;
                zzapb zzapbVar2 = zzapbVar;
                zzcgt zzcgtVar2 = zzcgtVar;
                com.google.android.gms.ads.internal.zza zzaVar2 = zzaVar;
                String str2 = str;
                com.google.android.gms.ads.internal.zzt.zzA();
                zzcmn zza = zzcmz.zza(context2, zzcoc.zza(), "", false, false, zzapbVar2, null, zzcgtVar2, null, null, zzaVar2, zzbel.zza(), null, null);
                final zzche zza2 = zzche.zza(zza);
                zza.zzP().zzz(new zzcny() { // from class: com.google.android.gms.internal.ads.zzcmx
                    @Override // com.google.android.gms.internal.ads.zzcny
                    public final void zza(boolean z) {
                        zzche.this.zzb();
                    }
                });
                zza.loadUrl(str2);
                return zza2;
            }
        }, zzcha.zze), new zzfru() { // from class: com.google.android.gms.internal.ads.zzdsy
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                zzcmn zzcmnVar = (zzcmn) obj;
                zzdtl.this.zza(zzcmnVar);
                return zzcmnVar;
            }
        }, this.zzg);
        this.zzm = zzm;
        zzchd.zza(zzm, "NativeJavascriptExecutor.initializeEngine");
    }

    public final synchronized void zzi(String str, zzbpq zzbpqVar) {
        zzfyx zzfyxVar = this.zzm;
        if (zzfyxVar == null) {
            return;
        }
        zzfyo.zzr(zzfyxVar, new zzdta(this, str, zzbpqVar), this.zzg);
    }

    public final void zzj(WeakReference weakReference, String str, zzbpq zzbpqVar) {
        zzi(str, new zzdtk(this, weakReference, str, zzbpqVar, null));
    }

    public final synchronized void zzk(String str, zzbpq zzbpqVar) {
        zzfyx zzfyxVar = this.zzm;
        if (zzfyxVar == null) {
            return;
        }
        zzfyo.zzr(zzfyxVar, new zzdtb(this, str, zzbpqVar), this.zzg);
    }
}
