package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeje implements zzeiy {
    private final zzdnb zza;
    private final zzfyy zzb;
    private final zzdrb zzc;
    private final zzfej zzd;
    private final zzdtr zze;

    public zzeje(zzdnb zzdnbVar, zzfyy zzfyyVar, zzdrb zzdrbVar, zzfej zzfejVar, zzdtr zzdtrVar) {
        this.zza = zzdnbVar;
        this.zzb = zzfyyVar;
        this.zzc = zzdrbVar;
        this.zzd = zzfejVar;
        this.zze = zzdtrVar;
    }

    private final zzfyx zzg(final zzfde zzfdeVar, final zzfcs zzfcsVar, final JSONObject jSONObject) {
        final zzfyx zza = this.zzd.zza();
        final zzfyx zza2 = this.zzc.zza(zzfdeVar, zzfcsVar, jSONObject);
        return zzfyo.zzd(zza, zza2).zza(new Callable() { // from class: com.google.android.gms.internal.ads.zzeiz
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzeje.this.zzc(zza2, zza, zzfdeVar, zzfcsVar, jSONObject);
            }
        }, this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final zzfyx zza(final zzfde zzfdeVar, final zzfcs zzfcsVar) {
        return zzfyo.zzn(zzfyo.zzn(this.zzd.zza(), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzejb
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzeje.this.zze(zzfcsVar, (zzdtl) obj);
            }
        }, this.zzb), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzejc
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzeje.this.zzf(zzfdeVar, zzfcsVar, (JSONArray) obj);
            }
        }, this.zzb);
    }

    @Override // com.google.android.gms.internal.ads.zzegk
    public final boolean zzb(zzfde zzfdeVar, zzfcs zzfcsVar) {
        zzfcx zzfcxVar = zzfcsVar.zzt;
        return (zzfcxVar == null || zzfcxVar.zzc == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzdoj zzc(zzfyx zzfyxVar, zzfyx zzfyxVar2, zzfde zzfdeVar, zzfcs zzfcsVar, JSONObject jSONObject) throws Exception {
        zzdoo zzdooVar = (zzdoo) zzfyxVar.get();
        zzdtl zzdtlVar = (zzdtl) zzfyxVar2.get();
        zzdop zzd = this.zza.zzd(new zzczr(zzfdeVar, zzfcsVar, null), new zzdpa(zzdooVar), new zzdnq(jSONObject, zzdtlVar));
        zzd.zzj().zzb();
        zzd.zzk().zza(zzdtlVar);
        zzd.zzg().zza(zzdooVar.zzr());
        zzd.zzl().zza(this.zze);
        return zzd.zza();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzd(zzdtl zzdtlVar, JSONObject jSONObject) throws Exception {
        this.zzd.zzb(zzfyo.zzi(zzdtlVar));
        if (!jSONObject.optBoolean("success")) {
            throw new zzbtu("process json failed");
        }
        return zzfyo.zzi(jSONObject.getJSONObject("json").getJSONArray("ads"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zze(zzfcs zzfcsVar, final zzdtl zzdtlVar) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("isNonagon", true);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhk)).booleanValue() && PlatformVersion.isAtLeastR()) {
            jSONObject.put("skipDeepLinkValidation", true);
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("response", zzfcsVar.zzt.zzc);
        jSONObject2.put("sdk_params", jSONObject);
        return zzfyo.zzn(zzdtlVar.zzd("google.afma.nativeAds.preProcessJson", jSONObject2), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzeja
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzeje.this.zzd(zzdtlVar, (JSONObject) obj);
            }
        }, this.zzb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzf(zzfde zzfdeVar, zzfcs zzfcsVar, JSONArray jSONArray) throws Exception {
        if (jSONArray.length() == 0) {
            return zzfyo.zzh(new zzeas(3));
        }
        if (zzfdeVar.zza.zza.zzk > 1) {
            int length = jSONArray.length();
            this.zzd.zzc(Math.min(length, zzfdeVar.zza.zza.zzk));
            ArrayList arrayList = new ArrayList(zzfdeVar.zza.zza.zzk);
            for (int r2 = 0; r2 < zzfdeVar.zza.zza.zzk; r2++) {
                if (r2 < length) {
                    arrayList.add(zzg(zzfdeVar, zzfcsVar, jSONArray.getJSONObject(r2)));
                } else {
                    arrayList.add(zzfyo.zzh(new zzeas(3)));
                }
            }
            return zzfyo.zzi(arrayList);
        }
        return zzfyo.zzm(zzg(zzfdeVar, zzfcsVar, jSONArray.getJSONObject(0)), new zzfru() { // from class: com.google.android.gms.internal.ads.zzejd
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                return Collections.singletonList(zzfyo.zzi((zzdoj) obj));
            }
        }, this.zzb);
    }
}
