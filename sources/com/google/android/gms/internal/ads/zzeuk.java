package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeuk implements zzeun {
    private final zzfyy zza;
    private final Context zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeuk(zzfyy zzfyyVar, Context context) {
        this.zza = zzfyyVar;
        this.zzb = context;
    }

    public static Bundle zzc(Context context, JSONArray jSONArray) {
        SharedPreferences sharedPreferences;
        String str;
        Bundle bundle = new Bundle();
        for (int r2 = 0; r2 < jSONArray.length(); r2++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(r2);
            String optString = optJSONObject.optString("bk");
            String optString2 = optJSONObject.optString("sk");
            int optInt = optJSONObject.optInt(SessionDescription.ATTR_TYPE, -1);
            int r3 = optInt != 0 ? optInt != 1 ? optInt != 2 ? 0 : 3 : 2 : 1;
            if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString2) && r3 != 0) {
                String[] split = optString2.split("/");
                int length = split.length;
                Object obj = null;
                if (length <= 2 && length != 0) {
                    if (length == 1) {
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                        str = split[0];
                    } else {
                        sharedPreferences = context.getSharedPreferences(split[0], 0);
                        str = split[1];
                    }
                    obj = sharedPreferences.getAll().get(str);
                }
                if (obj != null) {
                    int r32 = r3 - 1;
                    if (r32 != 0) {
                        if (r32 != 1) {
                            if (obj instanceof Boolean) {
                                bundle.putBoolean(optString, ((Boolean) obj).booleanValue());
                            }
                        } else if (obj instanceof Integer) {
                            bundle.putInt(optString, ((Integer) obj).intValue());
                        } else if (obj instanceof Long) {
                            bundle.putLong(optString, ((Long) obj).longValue());
                        } else if (obj instanceof Float) {
                            bundle.putFloat(optString, ((Float) obj).floatValue());
                        }
                    } else if (obj instanceof String) {
                        bundle.putString(optString, (String) obj);
                    }
                }
            }
        }
        return bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 37;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return this.zza.zzb(new Callable() { // from class: com.google.android.gms.internal.ads.zzeui
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzeuk.this.zzd();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzeum zzd() throws Exception {
        String str = (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzff);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            final Bundle zzc = zzc(this.zzb, new JSONArray(str));
            return new zzeum() { // from class: com.google.android.gms.internal.ads.zzeuj
                @Override // com.google.android.gms.internal.ads.zzeum
                public final void zzf(Object obj) {
                    ((Bundle) obj).putBundle("shared_pref", zzc);
                }
            };
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.util.zze.zzf("JSON parsing error", e);
            return null;
        }
    }
}
