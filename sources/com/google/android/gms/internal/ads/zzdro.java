package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.RNFetchBlob.RNFetchBlobConst;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.ImagesContract;
import com.onesignal.OSInAppMessageContentKt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzdro {
    private final Context zza;
    private final zzdqx zzb;
    private final zzapb zzc;
    private final zzcgt zzd;
    private final com.google.android.gms.ads.internal.zza zze;
    private final zzbel zzf;
    private final Executor zzg;
    private final zzblo zzh;
    private final zzdsg zzi;
    private final zzduw zzj;
    private final ScheduledExecutorService zzk;
    private final zzdtr zzl;
    private final zzdxo zzm;
    private final zzfhz zzn;
    private final zzfju zzo;
    private final zzefz zzp;

    public zzdro(Context context, zzdqx zzdqxVar, zzapb zzapbVar, zzcgt zzcgtVar, com.google.android.gms.ads.internal.zza zzaVar, zzbel zzbelVar, Executor executor, zzfdn zzfdnVar, zzdsg zzdsgVar, zzduw zzduwVar, ScheduledExecutorService scheduledExecutorService, zzdxo zzdxoVar, zzfhz zzfhzVar, zzfju zzfjuVar, zzefz zzefzVar, zzdtr zzdtrVar) {
        this.zza = context;
        this.zzb = zzdqxVar;
        this.zzc = zzapbVar;
        this.zzd = zzcgtVar;
        this.zze = zzaVar;
        this.zzf = zzbelVar;
        this.zzg = executor;
        this.zzh = zzfdnVar.zzi;
        this.zzi = zzdsgVar;
        this.zzj = zzduwVar;
        this.zzk = scheduledExecutorService;
        this.zzm = zzdxoVar;
        this.zzn = zzfhzVar;
        this.zzo = zzfjuVar;
        this.zzp = zzefzVar;
        this.zzl = zzdtrVar;
    }

    public static final com.google.android.gms.ads.internal.client.zzef zzi(JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2 = jSONObject.optJSONObject("mute");
        if (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject("default_reason")) == null) {
            return null;
        }
        return zzr(optJSONObject);
    }

    public static final List zzj(JSONObject jSONObject) {
        JSONObject optJSONObject = jSONObject.optJSONObject("mute");
        if (optJSONObject == null) {
            return zzfuv.zzo();
        }
        JSONArray optJSONArray = optJSONObject.optJSONArray("reasons");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return zzfuv.zzo();
        }
        ArrayList arrayList = new ArrayList();
        for (int r1 = 0; r1 < optJSONArray.length(); r1++) {
            com.google.android.gms.ads.internal.client.zzef zzr = zzr(optJSONArray.optJSONObject(r1));
            if (zzr != null) {
                arrayList.add(zzr);
            }
        }
        return zzfuv.zzm(arrayList);
    }

    private static zzfyx zzl(zzfyx zzfyxVar, Object obj) {
        return zzfyo.zzg(zzfyxVar, Exception.class, new zzfxv(null) { // from class: com.google.android.gms.internal.ads.zzdrl
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj2) {
                com.google.android.gms.ads.internal.util.zze.zzb("Error during loading assets.", (Exception) obj2);
                return zzfyo.zzi(null);
            }
        }, zzcha.zzf);
    }

    private static zzfyx zzm(boolean z, final zzfyx zzfyxVar, Object obj) {
        if (z) {
            return zzfyo.zzn(zzfyxVar, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzdrj
                @Override // com.google.android.gms.internal.ads.zzfxv
                public final zzfyx zza(Object obj2) {
                    return obj2 != null ? zzfyx.this : zzfyo.zzh(new zzeka(1, "Retrieve required value in native ad response failed."));
                }
            }, zzcha.zzf);
        }
        return zzl(zzfyxVar, null);
    }

    private final zzfyx zzn(JSONObject jSONObject, boolean z) {
        if (jSONObject == null) {
            return zzfyo.zzi(null);
        }
        final String optString = jSONObject.optString(ImagesContract.URL);
        if (TextUtils.isEmpty(optString)) {
            return zzfyo.zzi(null);
        }
        final double optDouble = jSONObject.optDouble("scale", 1.0d);
        boolean optBoolean = jSONObject.optBoolean("is_transparent", true);
        final int optInt = jSONObject.optInt("width", -1);
        final int optInt2 = jSONObject.optInt("height", -1);
        if (z) {
            return zzfyo.zzi(new zzblm(null, Uri.parse(optString), optDouble, optInt, optInt2));
        }
        return zzm(jSONObject.optBoolean("require"), zzfyo.zzm(this.zzb.zzb(optString, optDouble, optBoolean), new zzfru() { // from class: com.google.android.gms.internal.ads.zzdrm
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                String str = optString;
                return new zzblm(new BitmapDrawable(Resources.getSystem(), (Bitmap) obj), Uri.parse(str), optDouble, optInt, optInt2);
            }
        }, this.zzg), null);
    }

    private final zzfyx zzo(JSONArray jSONArray, boolean z, boolean z2) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return zzfyo.zzi(Collections.emptyList());
        }
        ArrayList arrayList = new ArrayList();
        int length = z2 ? jSONArray.length() : 1;
        for (int r1 = 0; r1 < length; r1++) {
            arrayList.add(zzn(jSONArray.optJSONObject(r1), z));
        }
        return zzfyo.zzm(zzfyo.zze(arrayList), new zzfru() { // from class: com.google.android.gms.internal.ads.zzdrk
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                ArrayList arrayList2 = new ArrayList();
                for (zzblm zzblmVar : (List) obj) {
                    if (zzblmVar != null) {
                        arrayList2.add(zzblmVar);
                    }
                }
                return arrayList2;
            }
        }, this.zzg);
    }

    private final zzfyx zzp(JSONObject jSONObject, zzfcs zzfcsVar, zzfcv zzfcvVar) {
        final zzfyx zzb = this.zzi.zzb(jSONObject.optString("base_url"), jSONObject.optString(OSInAppMessageContentKt.HTML), zzfcsVar, zzfcvVar, zzk(jSONObject.optInt("width", 0), jSONObject.optInt("height", 0)));
        return zzfyo.zzn(zzb, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzdrn
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj) {
                zzfyx zzfyxVar = zzfyx.this;
                zzcmn zzcmnVar = (zzcmn) obj;
                if (zzcmnVar == null || zzcmnVar.zzs() == null) {
                    throw new zzeka(1, "Retrieve video view in html5 ad response failed.");
                }
                return zzfyxVar;
            }
        }, zzcha.zzf);
    }

    private static Integer zzq(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException unused) {
            return null;
        }
    }

    private static final com.google.android.gms.ads.internal.client.zzef zzr(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("reason");
        String optString2 = jSONObject.optString("ping_url");
        if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
            return null;
        }
        return new com.google.android.gms.ads.internal.client.zzef(optString, optString2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzblj zza(JSONObject jSONObject, List list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        String optString = jSONObject.optString("text");
        Integer zzq = zzq(jSONObject, "bg_color");
        Integer zzq2 = zzq(jSONObject, "text_color");
        int optInt = jSONObject.optInt("text_size", -1);
        boolean optBoolean = jSONObject.optBoolean("allow_pub_rendering");
        int optInt2 = jSONObject.optInt("animation_ms", 1000);
        return new zzblj(optString, list, zzq, zzq2, optInt > 0 ? Integer.valueOf(optInt) : null, jSONObject.optInt("presentation_ms", 4000) + optInt2, this.zzh.zze, optBoolean);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzb(com.google.android.gms.ads.internal.client.zzq zzqVar, zzfcs zzfcsVar, zzfcv zzfcvVar, String str, String str2, Object obj) throws Exception {
        zzcmn zza = this.zzj.zza(zzqVar, zzfcsVar, zzfcvVar);
        final zzche zza2 = zzche.zza(zza);
        zzdto zzb = this.zzl.zzb();
        zza.zzP().zzL(zzb, zzb, zzb, zzb, zzb, false, null, new com.google.android.gms.ads.internal.zzb(this.zza, null, null), null, null, this.zzp, this.zzo, this.zzm, this.zzn, null, zzb, null);
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcQ)).booleanValue()) {
            zza.zzaf("/getNativeAdViewSignals", zzbpp.zzs);
        }
        zza.zzaf("/getNativeClickMeta", zzbpp.zzt);
        zza.zzP().zzz(new zzcny() { // from class: com.google.android.gms.internal.ads.zzdri
            @Override // com.google.android.gms.internal.ads.zzcny
            public final void zza(boolean z) {
                zzche zzcheVar = zzche.this;
                if (z) {
                    zzcheVar.zzb();
                } else {
                    zzcheVar.zze(new zzeka(1, "Image Web View failed to load."));
                }
            }
        });
        zza.zzad(str, str2, null);
        return zza2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzc(String str, Object obj) throws Exception {
        com.google.android.gms.ads.internal.zzt.zzA();
        zzcmn zza = zzcmz.zza(this.zza, zzcoc.zza(), "native-omid", false, false, this.zzc, null, this.zzd, null, null, this.zze, this.zzf, null, null);
        final zzche zza2 = zzche.zza(zza);
        zza.zzP().zzz(new zzcny() { // from class: com.google.android.gms.internal.ads.zzdre
            @Override // com.google.android.gms.internal.ads.zzcny
            public final void zza(boolean z) {
                zzche.this.zzb();
            }
        });
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzeg)).booleanValue()) {
            zza.loadData(Base64.encodeToString(str.getBytes(), 1), "text/html", RNFetchBlobConst.RNFB_RESPONSE_BASE64);
        } else {
            zza.loadData(str, "text/html", "UTF-8");
        }
        return zza2;
    }

    public final zzfyx zzd(JSONObject jSONObject, String str) {
        final JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return zzfyo.zzi(null);
        }
        JSONArray optJSONArray = optJSONObject.optJSONArray("images");
        JSONObject optJSONObject2 = optJSONObject.optJSONObject("image");
        if (optJSONArray == null && optJSONObject2 != null) {
            optJSONArray = new JSONArray();
            optJSONArray.put(optJSONObject2);
        }
        return zzm(optJSONObject.optBoolean("require"), zzfyo.zzm(zzo(optJSONArray, false, true), new zzfru() { // from class: com.google.android.gms.internal.ads.zzdrf
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                return zzdro.this.zza(optJSONObject, (List) obj);
            }
        }, this.zzg), null);
    }

    public final zzfyx zze(JSONObject jSONObject, String str) {
        return zzn(jSONObject.optJSONObject(str), this.zzh.zzb);
    }

    public final zzfyx zzf(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray("images");
        zzblo zzbloVar = this.zzh;
        return zzo(optJSONArray, zzbloVar.zzb, zzbloVar.zzd);
    }

    public final zzfyx zzg(JSONObject jSONObject, String str, final zzfcs zzfcsVar, final zzfcv zzfcvVar) {
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzie)).booleanValue()) {
            return zzfyo.zzi(null);
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("images");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return zzfyo.zzi(null);
        }
        JSONObject optJSONObject = optJSONArray.optJSONObject(0);
        if (optJSONObject == null) {
            return zzfyo.zzi(null);
        }
        final String optString = optJSONObject.optString("base_url");
        final String optString2 = optJSONObject.optString(OSInAppMessageContentKt.HTML);
        final com.google.android.gms.ads.internal.client.zzq zzk = zzk(optJSONObject.optInt("width", 0), optJSONObject.optInt("height", 0));
        if (!TextUtils.isEmpty(optString2)) {
            final zzfyx zzn = zzfyo.zzn(zzfyo.zzi(null), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzdrg
                @Override // com.google.android.gms.internal.ads.zzfxv
                public final zzfyx zza(Object obj) {
                    return zzdro.this.zzb(zzk, zzfcsVar, zzfcvVar, optString, optString2, obj);
                }
            }, zzcha.zze);
            return zzfyo.zzn(zzn, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzdrh
                @Override // com.google.android.gms.internal.ads.zzfxv
                public final zzfyx zza(Object obj) {
                    zzfyx zzfyxVar = zzfyx.this;
                    if (((zzcmn) obj) != null) {
                        return zzfyxVar;
                    }
                    throw new zzeka(1, "Retrieve Web View from image ad response failed.");
                }
            }, zzcha.zzf);
        }
        return zzfyo.zzi(null);
    }

    public final zzfyx zzh(JSONObject jSONObject, zzfcs zzfcsVar, zzfcv zzfcvVar) {
        zzfyx zza;
        JSONObject zzg = com.google.android.gms.ads.internal.util.zzbu.zzg(jSONObject, "html_containers", "instream");
        if (zzg == null) {
            JSONObject optJSONObject = jSONObject.optJSONObject("video");
            if (optJSONObject == null) {
                return zzfyo.zzi(null);
            }
            String optString = optJSONObject.optString("vast_xml");
            boolean z = false;
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzid)).booleanValue() && optJSONObject.has(OSInAppMessageContentKt.HTML)) {
                z = true;
            }
            if (TextUtils.isEmpty(optString)) {
                if (!z) {
                    com.google.android.gms.ads.internal.util.zze.zzj("Required field 'vast_xml' or 'html' is missing");
                    return zzfyo.zzi(null);
                }
            } else if (!z) {
                zza = this.zzi.zza(optJSONObject);
                return zzl(zzfyo.zzo(zza, ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcR)).intValue(), TimeUnit.SECONDS, this.zzk), null);
            }
            zza = zzp(optJSONObject, zzfcsVar, zzfcvVar);
            return zzl(zzfyo.zzo(zza, ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcR)).intValue(), TimeUnit.SECONDS, this.zzk), null);
        }
        return zzp(zzg, zzfcsVar, zzfcvVar);
    }

    private final com.google.android.gms.ads.internal.client.zzq zzk(int r4, int r5) {
        if (r4 == 0) {
            if (r5 == 0) {
                return com.google.android.gms.ads.internal.client.zzq.zzc();
            }
            r4 = 0;
        }
        return new com.google.android.gms.ads.internal.client.zzq(this.zza, new AdSize(r4, r5));
    }
}
