package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdrb {
    private final zzfyy zza;
    private final zzdro zzb;
    private final zzdrt zzc;

    public zzdrb(zzfyy zzfyyVar, zzdro zzdroVar, zzdrt zzdrtVar) {
        this.zza = zzfyyVar;
        this.zzb = zzdroVar;
        this.zzc = zzdrtVar;
    }

    public final zzfyx zza(final zzfde zzfdeVar, final zzfcs zzfcsVar, final JSONObject jSONObject) {
        zzfyx zzn;
        final zzfyx zzb = this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzdqz
            @Override // java.util.concurrent.Callable
            public final Object call() {
                zzfde zzfdeVar2 = zzfdeVar;
                zzfcs zzfcsVar2 = zzfcsVar;
                JSONObject jSONObject2 = jSONObject;
                zzdoo zzdooVar = new zzdoo();
                zzdooVar.zzV(jSONObject2.optInt("template_id", -1));
                zzdooVar.zzI(jSONObject2.optString("custom_template_id"));
                JSONObject optJSONObject = jSONObject2.optJSONObject("omid_settings");
                zzdooVar.zzS(optJSONObject != null ? optJSONObject.optString("omid_partner_name") : null);
                zzfdn zzfdnVar = zzfdeVar2.zza.zza;
                if (zzfdnVar.zzg.contains(Integer.toString(zzdooVar.zzc()))) {
                    if (zzdooVar.zzc() == 3) {
                        if (zzdooVar.zzy() == null) {
                            throw new zzeka(1, "No custom template id for custom template ad response.");
                        }
                        if (!zzfdnVar.zzh.contains(zzdooVar.zzy())) {
                            throw new zzeka(1, "Unexpected custom template id in the response.");
                        }
                    }
                    zzdooVar.zzT(jSONObject2.optDouble("rating", -1.0d));
                    String optString = jSONObject2.optString("headline", null);
                    if (zzfcsVar2.zzN) {
                        com.google.android.gms.ads.internal.zzt.zzq();
                        optString = com.google.android.gms.ads.internal.util.zzs.zzv() + " : " + optString;
                    }
                    zzdooVar.zzU("headline", optString);
                    zzdooVar.zzU(TtmlNode.TAG_BODY, jSONObject2.optString(TtmlNode.TAG_BODY, null));
                    zzdooVar.zzU("call_to_action", jSONObject2.optString("call_to_action", null));
                    zzdooVar.zzU("store", jSONObject2.optString("store", null));
                    zzdooVar.zzU("price", jSONObject2.optString("price", null));
                    zzdooVar.zzU("advertiser", jSONObject2.optString("advertiser", null));
                    return zzdooVar;
                }
                throw new zzeka(1, "Invalid template ID: " + zzdooVar.zzc());
            }
        });
        final zzfyx zzf = this.zzb.zzf(jSONObject, "images");
        final zzfyx zzg = this.zzb.zzg(jSONObject, "images", zzfcsVar, zzfdeVar.zzb.zzb);
        final zzfyx zze = this.zzb.zze(jSONObject, "secondary_image");
        final zzfyx zze2 = this.zzb.zze(jSONObject, "app_icon");
        final zzfyx zzd = this.zzb.zzd(jSONObject, "attribution");
        final zzfyx zzh = this.zzb.zzh(jSONObject, zzfcsVar, zzfdeVar.zzb.zzb);
        final zzdro zzdroVar = this.zzb;
        if (!jSONObject.optBoolean("enable_omid")) {
            zzn = zzfyo.zzi(null);
        } else {
            JSONObject optJSONObject = jSONObject.optJSONObject("omid_settings");
            if (optJSONObject == null) {
                zzn = zzfyo.zzi(null);
            } else {
                final String optString = optJSONObject.optString("omid_html");
                if (TextUtils.isEmpty(optString)) {
                    zzn = zzfyo.zzi(null);
                } else {
                    zzn = zzfyo.zzn(zzfyo.zzi(null), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzdrd
                        @Override // com.google.android.gms.internal.ads.zzfxv
                        public final zzfyx zza(Object obj) {
                            return zzdro.this.zzc(optString, obj);
                        }
                    }, zzcha.zze);
                }
            }
        }
        final zzfyx zzfyxVar = zzn;
        final zzfyx zza = this.zzc.zza(jSONObject, "custom_assets");
        return zzfyo.zzb(zzb, zzf, zzg, zze, zze2, zzd, zzh, zzfyxVar, zza).zza(new Callable() { // from class: com.google.android.gms.internal.ads.zzdra
            @Override // java.util.concurrent.Callable
            public final Object call() {
                zzfyx zzfyxVar2 = zzb;
                zzfyx zzfyxVar3 = zzf;
                zzfyx zzfyxVar4 = zze2;
                zzfyx zzfyxVar5 = zze;
                zzfyx zzfyxVar6 = zzd;
                JSONObject jSONObject2 = jSONObject;
                zzfyx zzfyxVar7 = zzh;
                zzfyx zzfyxVar8 = zzg;
                zzfyx zzfyxVar9 = zzfyxVar;
                zzfyx zzfyxVar10 = zza;
                zzdoo zzdooVar = (zzdoo) zzfyxVar2.get();
                zzdooVar.zzN((List) zzfyxVar3.get());
                zzdooVar.zzK((zzbma) zzfyxVar4.get());
                zzdooVar.zzO((zzbma) zzfyxVar5.get());
                zzdooVar.zzH((zzbls) zzfyxVar6.get());
                zzdooVar.zzQ(zzdro.zzj(jSONObject2));
                zzdooVar.zzJ(zzdro.zzi(jSONObject2));
                zzcmn zzcmnVar = (zzcmn) zzfyxVar7.get();
                if (zzcmnVar != null) {
                    zzdooVar.zzY(zzcmnVar);
                    zzdooVar.zzX(zzcmnVar.zzH());
                    zzdooVar.zzW(zzcmnVar.zzs());
                }
                zzcmn zzcmnVar2 = (zzcmn) zzfyxVar8.get();
                if (zzcmnVar2 != null) {
                    zzdooVar.zzM(zzcmnVar2);
                    zzdooVar.zzZ(zzcmnVar2.zzH());
                }
                zzcmn zzcmnVar3 = (zzcmn) zzfyxVar9.get();
                if (zzcmnVar3 != null) {
                    zzdooVar.zzR(zzcmnVar3);
                }
                for (zzdrs zzdrsVar : (List) zzfyxVar10.get()) {
                    if (zzdrsVar.zza != 1) {
                        zzdooVar.zzL(zzdrsVar.zzb, zzdrsVar.zzd);
                    } else {
                        zzdooVar.zzU(zzdrsVar.zzb, zzdrsVar.zzc);
                    }
                }
                return zzdooVar;
            }
        }, this.zza);
    }
}
