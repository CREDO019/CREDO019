package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.core.p005os.EnvironmentCompat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.io.StringReader;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeca {
    private final zzcok zza;
    private final Context zzb;
    private final zzcgt zzc;
    private final zzfdn zzd;
    private final Executor zze;
    private final String zzf;
    private final zzfjc zzg;
    private final zzfdz zzh;
    private final zzdxj zzi;

    public zzeca(zzcok zzcokVar, Context context, zzcgt zzcgtVar, zzfdn zzfdnVar, Executor executor, String str, zzfjc zzfjcVar, zzdxj zzdxjVar) {
        this.zza = zzcokVar;
        this.zzb = context;
        this.zzc = zzcgtVar;
        this.zzd = zzfdnVar;
        this.zze = executor;
        this.zzf = str;
        this.zzg = zzfjcVar;
        this.zzh = zzcokVar.zzv();
        this.zzi = zzdxjVar;
    }

    private final zzfyx zzc(final String str, final String str2) {
        zzfir zza = zzfiq.zza(this.zzb, 11);
        zza.zzf();
        final zzbtv zza2 = com.google.android.gms.ads.internal.zzt.zzf().zza(this.zzb, this.zzc, this.zza.zzy()).zza("google.afma.response.normalize", zzbuc.zza, zzbuc.zza);
        zzfyx zzn = zzfyo.zzn(zzfyo.zzn(zzfyo.zzn(zzfyo.zzi(""), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzebx
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                String str3 = str;
                String str4 = str2;
                String str5 = (String) obj;
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = new JSONObject();
                try {
                    jSONObject3.put("headers", new JSONObject());
                    jSONObject3.put(TtmlNode.TAG_BODY, str3);
                    jSONObject2.put("base_url", "");
                    jSONObject2.put("signals", new JSONObject(str4));
                    jSONObject.put("request", jSONObject2);
                    jSONObject.put("response", jSONObject3);
                    jSONObject.put("flags", new JSONObject());
                    return zzfyo.zzi(jSONObject);
                } catch (JSONException e) {
                    throw new JSONException("Preloaded loader: ".concat(String.valueOf(e.getMessage())));
                }
            }
        }, this.zze), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzeby
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzbtv.this.zzb((JSONObject) obj);
            }
        }, this.zze), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzebz
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                return zzeca.this.zzb((JSONObject) obj);
            }
        }, this.zze);
        zzfjb.zza(zzn, this.zzg, zza);
        return zzn;
    }

    private final String zzd(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONArray jSONArray = jSONObject.getJSONArray("ad_types");
            if (jSONArray != null && EnvironmentCompat.MEDIA_UNKNOWN.equals(jSONArray.getString(0))) {
                jSONObject.put("ad_types", new JSONArray().put(this.zzf));
            }
            return jSONObject.toString();
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzj("Failed to update the ad types for rendering. ".concat(e.toString()));
            return str;
        }
    }

    private static final String zze(String str) {
        try {
            return new JSONObject(str).optString("request_id", "");
        } catch (JSONException unused) {
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:66:0x00aa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.internal.ads.zzfyx zza() {
        /*
            Method dump skipped, instructions count: 385
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzeca.zza():com.google.android.gms.internal.ads.zzfyx");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzb(JSONObject jSONObject) throws Exception {
        return zzfyo.zzi(new zzfde(new zzfdb(this.zzd), zzfdd.zza(new StringReader(jSONObject.toString()))));
    }
}
