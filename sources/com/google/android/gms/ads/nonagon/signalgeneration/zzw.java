package com.google.android.gms.ads.nonagon.signalgeneration;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzbkh;
import com.google.android.gms.internal.ads.zzcfb;
import com.google.android.gms.internal.ads.zzcfi;
import com.google.android.gms.internal.ads.zzcgn;
import com.google.android.gms.internal.ads.zzcgt;
import com.google.android.gms.internal.ads.zzdxj;
import com.google.android.gms.internal.ads.zzdxt;
import com.google.android.gms.internal.ads.zzfir;
import com.google.android.gms.internal.ads.zzfjc;
import com.google.android.gms.internal.ads.zzfyk;
import com.google.android.gms.internal.ads.zzfyx;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzw implements zzfyk {
    final /* synthetic */ zzfyx zza;
    final /* synthetic */ zzcfi zzb;
    final /* synthetic */ zzcfb zzc;
    final /* synthetic */ zzfir zzd;
    final /* synthetic */ long zze;
    final /* synthetic */ zzaa zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(zzaa zzaaVar, zzfyx zzfyxVar, zzcfi zzcfiVar, zzcfb zzcfbVar, zzfir zzfirVar, long j) {
        this.zzf = zzaaVar;
        this.zza = zzfyxVar;
        this.zzb = zzcfiVar;
        this.zzc = zzcfbVar;
        this.zzd = zzfirVar;
        this.zze = j;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        zzdxt zzdxtVar;
        zzdxj zzdxjVar;
        long currentTimeMillis = com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis();
        long j = this.zze;
        String message = th.getMessage();
        com.google.android.gms.ads.internal.zzt.zzp().zzt(th, "SignalGeneratorImpl.generateSignals");
        zzaa zzaaVar = this.zzf;
        zzdxtVar = zzaaVar.zzr;
        zzdxjVar = zzaaVar.zzj;
        zzf.zzc(zzdxtVar, zzdxjVar, "sgf", new Pair("sgf_reason", message), new Pair("tqgt", String.valueOf(currentTimeMillis - j)));
        zzfjc zzr = zzaa.zzr(this.zza, this.zzb);
        if (((Boolean) zzbkh.zze.zze()).booleanValue() && zzr != null) {
            zzfir zzfirVar = this.zzd;
            zzfirVar.zze(false);
            zzr.zza(zzfirVar);
            zzr.zzg();
        }
        try {
            zzcfb zzcfbVar = this.zzc;
            zzcfbVar.zzb("Internal error. " + message);
        } catch (RemoteException e) {
            zzcgn.zzh("", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzdxt zzdxtVar;
        zzdxj zzdxjVar;
        zzdxj zzdxjVar2;
        boolean z;
        boolean z2;
        zzdxt zzdxtVar2;
        zzdxj zzdxjVar3;
        String str;
        String str2;
        String str3;
        String str4;
        Context context;
        zzcgt zzcgtVar;
        String str5;
        String str6;
        AtomicInteger atomicInteger;
        zzdxt zzdxtVar3;
        zzdxj zzdxjVar4;
        zzdxt zzdxtVar4;
        zzdxj zzdxjVar5;
        zzam zzamVar = (zzam) obj;
        zzfjc zzr = zzaa.zzr(this.zza, this.zzb);
        if (!((Boolean) zzay.zzc().zzb(zzbiy.zzgB)).booleanValue()) {
            try {
                this.zzc.zzb("QueryInfo generation has been disabled.");
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.zze.zzg("QueryInfo generation has been disabled.".concat(e.toString()));
            }
            if (!((Boolean) zzbkh.zze.zze()).booleanValue() || zzr == null) {
                return;
            }
            zzfir zzfirVar = this.zzd;
            zzfirVar.zze(false);
            zzr.zza(zzfirVar);
            zzr.zzg();
            return;
        }
        long currentTimeMillis = com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis() - this.zze;
        try {
            try {
                if (zzamVar == null) {
                    this.zzc.zzc(null, null, null);
                    zzaa zzaaVar = this.zzf;
                    zzdxtVar4 = zzaaVar.zzr;
                    zzdxjVar5 = zzaaVar.zzj;
                    zzf.zzc(zzdxtVar4, zzdxjVar5, "sgs", new Pair("rid", "-1"));
                    this.zzd.zze(true);
                    if (!((Boolean) zzbkh.zze.zze()).booleanValue() || zzr == null) {
                        return;
                    }
                    zzr.zza(this.zzd);
                    zzr.zzg();
                    return;
                }
                try {
                    String optString = new JSONObject(zzamVar.zzb).optString("request_id", "");
                    if (TextUtils.isEmpty(optString)) {
                        com.google.android.gms.ads.internal.util.zze.zzj("The request ID is empty in request JSON.");
                        this.zzc.zzb("Internal error: request ID is empty in request JSON.");
                        zzaa zzaaVar2 = this.zzf;
                        zzdxtVar3 = zzaaVar2.zzr;
                        zzdxjVar4 = zzaaVar2.zzj;
                        zzf.zzc(zzdxtVar3, zzdxjVar4, "sgf", new Pair("sgf_reason", "rid_missing"));
                        this.zzd.zze(false);
                        if (!((Boolean) zzbkh.zze.zze()).booleanValue() || zzr == null) {
                            return;
                        }
                        zzr.zza(this.zzd);
                        zzr.zzg();
                        return;
                    }
                    zzaa zzaaVar3 = this.zzf;
                    String str7 = zzamVar.zzb;
                    zzdxjVar2 = zzaaVar3.zzj;
                    zzaa.zzG(zzaaVar3, optString, str7, zzdxjVar2);
                    Bundle bundle = zzamVar.zzc;
                    zzaa zzaaVar4 = this.zzf;
                    z = zzaaVar4.zzw;
                    if (z && bundle != null) {
                        str5 = zzaaVar4.zzy;
                        if (bundle.getInt(str5, -1) == -1) {
                            zzaa zzaaVar5 = this.zzf;
                            str6 = zzaaVar5.zzy;
                            atomicInteger = zzaaVar5.zzz;
                            bundle.putInt(str6, atomicInteger.get());
                        }
                    }
                    zzaa zzaaVar6 = this.zzf;
                    z2 = zzaaVar6.zzv;
                    if (z2 && bundle != null) {
                        str = zzaaVar6.zzx;
                        if (TextUtils.isEmpty(bundle.getString(str))) {
                            str2 = this.zzf.zzB;
                            if (TextUtils.isEmpty(str2)) {
                                zzaa zzaaVar7 = this.zzf;
                                com.google.android.gms.ads.internal.util.zzs zzq = com.google.android.gms.ads.internal.zzt.zzq();
                                zzaa zzaaVar8 = this.zzf;
                                context = zzaaVar8.zzg;
                                zzcgtVar = zzaaVar8.zzA;
                                zzaaVar7.zzB = zzq.zzc(context, zzcgtVar.zza);
                            }
                            zzaa zzaaVar9 = this.zzf;
                            str3 = zzaaVar9.zzx;
                            str4 = zzaaVar9.zzB;
                            bundle.putString(str3, str4);
                        }
                    }
                    this.zzc.zzc(zzamVar.zza, zzamVar.zzb, bundle);
                    zzaa zzaaVar10 = this.zzf;
                    zzdxtVar2 = zzaaVar10.zzr;
                    zzdxjVar3 = zzaaVar10.zzj;
                    zzf.zzc(zzdxtVar2, zzdxjVar3, "sgs", new Pair("tqgt", String.valueOf(currentTimeMillis)));
                    this.zzd.zze(true);
                    if (!((Boolean) zzbkh.zze.zze()).booleanValue() || zzr == null) {
                        return;
                    }
                    zzr.zza(this.zzd);
                    zzr.zzg();
                } catch (JSONException e2) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Failed to create JSON object from the request string.");
                    zzcfb zzcfbVar = this.zzc;
                    String obj2 = e2.toString();
                    zzcfbVar.zzb("Internal error for request JSON: " + obj2);
                    zzaa zzaaVar11 = this.zzf;
                    zzdxtVar = zzaaVar11.zzr;
                    zzdxjVar = zzaaVar11.zzj;
                    zzf.zzc(zzdxtVar, zzdxjVar, "sgf", new Pair("sgf_reason", "request_invalid"));
                    this.zzd.zze(false);
                    if (!((Boolean) zzbkh.zze.zze()).booleanValue() || zzr == null) {
                        return;
                    }
                    zzr.zza(this.zzd);
                    zzr.zzg();
                }
            } catch (RemoteException e3) {
                this.zzd.zze(false);
                zzcgn.zzh("", e3);
                if (!((Boolean) zzbkh.zze.zze()).booleanValue() || zzr == null) {
                    return;
                }
                zzr.zza(this.zzd);
                zzr.zzg();
            }
        } catch (Throwable th) {
            if (((Boolean) zzbkh.zze.zze()).booleanValue() && zzr != null) {
                zzr.zza(this.zzd);
                zzr.zzg();
            }
            throw th;
        }
    }
}
