package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeec implements zzfgs {
    private static final Pattern zza = Pattern.compile("([^;]+=[^;]+)(;\\s|$)", 2);
    private final String zzb;
    private final zzfir zzc;
    private final zzfjc zzd;

    public zzeec(String str, zzfjc zzfjcVar, zzfir zzfirVar) {
        this.zzb = str;
        this.zzd = zzfjcVar;
        this.zzc = zzfirVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfgs
    public final /* bridge */ /* synthetic */ Object zza(Object obj) throws Exception {
        JSONObject jSONObject;
        zzcbd zzcbdVar;
        zzcbd zzcbdVar2;
        zzcbd zzcbdVar3;
        zzcbd zzcbdVar4;
        zzcbd zzcbdVar5;
        zzcbd zzcbdVar6;
        zzcbd zzcbdVar7;
        JSONObject jSONObject2;
        String str;
        zzeeb zzeebVar = (zzeeb) obj;
        jSONObject = zzeebVar.zza;
        int optInt = jSONObject.optInt("http_timeout_millis", 60000);
        zzcbdVar = zzeebVar.zzb;
        String str2 = "";
        if (zzcbdVar.zza() == -2) {
            HashMap hashMap = new HashMap();
            zzcbdVar2 = zzeebVar.zzb;
            if (zzcbdVar2.zzh() && !TextUtils.isEmpty(this.zzb)) {
                if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzaI)).booleanValue()) {
                    String str3 = this.zzb;
                    if (TextUtils.isEmpty(str3)) {
                        str = "";
                    } else {
                        Matcher matcher = zza.matcher(str3);
                        str = "";
                        while (matcher.find()) {
                            String group = matcher.group(1);
                            if (group != null && (group.toLowerCase(Locale.ROOT).startsWith("id=") || group.toLowerCase(Locale.ROOT).startsWith("ide="))) {
                                if (!TextUtils.isEmpty(str)) {
                                    str = str.concat("; ");
                                }
                                str = str.concat(group);
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(str)) {
                        hashMap.put(HttpHeaders.COOKIE, str);
                    }
                } else {
                    hashMap.put(HttpHeaders.COOKIE, this.zzb);
                }
            }
            zzcbdVar3 = zzeebVar.zzb;
            if (zzcbdVar3.zzi()) {
                jSONObject2 = zzeebVar.zza;
                JSONObject optJSONObject = jSONObject2.optJSONObject("pii");
                if (optJSONObject != null) {
                    if (!TextUtils.isEmpty(optJSONObject.optString("doritos", ""))) {
                        hashMap.put("x-afma-drt-cookie", optJSONObject.optString("doritos", ""));
                    }
                    if (!TextUtils.isEmpty(optJSONObject.optString("doritos_v2", ""))) {
                        hashMap.put("x-afma-drt-v2-cookie", optJSONObject.optString("doritos_v2", ""));
                    }
                } else {
                    com.google.android.gms.ads.internal.util.zze.zza("DSID signal does not exist.");
                }
            }
            zzcbdVar4 = zzeebVar.zzb;
            if (zzcbdVar4 != null) {
                zzcbdVar6 = zzeebVar.zzb;
                if (!TextUtils.isEmpty(zzcbdVar6.zzd())) {
                    zzcbdVar7 = zzeebVar.zzb;
                    str2 = zzcbdVar7.zzd();
                }
            }
            zzfjc zzfjcVar = this.zzd;
            zzfir zzfirVar = this.zzc;
            zzfirVar.zze(true);
            zzfjcVar.zza(zzfirVar);
            zzcbdVar5 = zzeebVar.zzb;
            return new zzedx(zzcbdVar5.zze(), optInt, hashMap, str2.getBytes(zzfrs.zzc), "");
        }
        zzfjc zzfjcVar2 = this.zzd;
        zzfir zzfirVar2 = this.zzc;
        zzfirVar2.zze(false);
        zzfjcVar2.zza(zzfirVar2);
        if (zzcbdVar.zza() == 1) {
            if (zzcbdVar.zzf() != null) {
                str2 = TextUtils.join(", ", zzcbdVar.zzf());
                com.google.android.gms.ads.internal.util.zze.zzg(str2);
            }
            throw new zzeas(2, "Error building request URL: ".concat(String.valueOf(str2)));
        }
        throw new zzeas(1);
    }
}
