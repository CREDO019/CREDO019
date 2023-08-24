package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.WindowManager;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfle {
    private static WindowManager zzb;
    private static final String[] zzc = {"x", "y", "width", "height"};
    static float zza = Resources.getSystem().getDisplayMetrics().density;

    public static JSONObject zza(int r4, int r5, int r6, int r7) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("x", r4 / zza);
            jSONObject.put("y", r5 / zza);
            jSONObject.put("width", r6 / zza);
            jSONObject.put("height", r7 / zza);
        } catch (JSONException e) {
            zzflf.zza("Error with creating viewStateObject", e);
        }
        return jSONObject;
    }

    public static void zzb(JSONObject jSONObject, String str) {
        try {
            jSONObject.put("adSessionId", str);
        } catch (JSONException e) {
            zzflf.zza("Error with setting ad session id", e);
        }
    }

    public static void zzc(JSONObject jSONObject, JSONObject jSONObject2) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("childViews");
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
                jSONObject.put("childViews", optJSONArray);
            }
            optJSONArray.put(jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void zzd(JSONObject jSONObject, zzfli zzfliVar) {
        zzfkq zza2 = zzfliVar.zza();
        JSONArray jSONArray = new JSONArray();
        ArrayList zzb2 = zzfliVar.zzb();
        int size = zzb2.size();
        for (int r3 = 0; r3 < size; r3++) {
            jSONArray.put((String) zzb2.get(r3));
        }
        try {
            jSONObject.put("isFriendlyObstructionFor", jSONArray);
            jSONObject.put("friendlyObstructionClass", zza2.zzd());
            jSONObject.put("friendlyObstructionPurpose", zza2.zza());
            jSONObject.put("friendlyObstructionReason", zza2.zzc());
        } catch (JSONException e) {
            zzflf.zza("Error with setting friendly obstruction", e);
        }
    }

    public static void zze(JSONObject jSONObject, Boolean bool) {
        try {
            jSONObject.put("hasWindowFocus", bool);
        } catch (JSONException e) {
            zzflf.zza("Error with setting not visible reason", e);
        }
    }

    public static void zzf(JSONObject jSONObject, String str) {
        try {
            jSONObject.put("notVisibleReason", str);
        } catch (JSONException e) {
            zzflf.zza("Error with setting not visible reason", e);
        }
    }

    public static void zzg(Context context) {
        if (context != null) {
            zza = context.getResources().getDisplayMetrics().density;
            zzb = (WindowManager) context.getSystemService("window");
        }
    }

    public static void zzh(JSONObject jSONObject, String str, Object obj) {
        try {
            jSONObject.put(str, obj);
        } catch (NullPointerException | JSONException e) {
            zzflf.zza("JSONException during JSONObject.put for name [" + str + "]", e);
        }
    }

    public static void zzi(JSONObject jSONObject) {
        float f;
        float f2 = 0.0f;
        if (zzb != null) {
            Point point = new Point(0, 0);
            zzb.getDefaultDisplay().getRealSize(point);
            f2 = point.x / zza;
            f = point.y / zza;
        } else {
            f = 0.0f;
        }
        try {
            jSONObject.put("width", f2);
            jSONObject.put("height", f);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static boolean zzj(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null && jSONObject2 == null) {
            return true;
        }
        if (jSONObject != null && jSONObject2 != null) {
            String[] strArr = zzc;
            int r3 = 0;
            while (true) {
                if (r3 < 4) {
                    String str = strArr[r3];
                    if (jSONObject.optDouble(str) != jSONObject2.optDouble(str)) {
                        break;
                    }
                    r3++;
                } else if (jSONObject.optString("adSessionId", "").equals(jSONObject2.optString("adSessionId", "")) && Boolean.valueOf(jSONObject.optBoolean("hasWindowFocus")).equals(Boolean.valueOf(jSONObject2.optBoolean("hasWindowFocus")))) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("isFriendlyObstructionFor");
                    JSONArray optJSONArray2 = jSONObject2.optJSONArray("isFriendlyObstructionFor");
                    if (optJSONArray != null || optJSONArray2 != null) {
                        if (zzk(optJSONArray, optJSONArray2)) {
                            for (int r5 = 0; r5 < optJSONArray.length(); r5++) {
                                if (!optJSONArray.optString(r5, "").equals(optJSONArray2.optString(r5, ""))) {
                                    break;
                                }
                            }
                        }
                    }
                    JSONArray optJSONArray3 = jSONObject.optJSONArray("childViews");
                    JSONArray optJSONArray4 = jSONObject2.optJSONArray("childViews");
                    if (optJSONArray3 != null || optJSONArray4 != null) {
                        if (zzk(optJSONArray3, optJSONArray4)) {
                            for (int r2 = 0; r2 < optJSONArray3.length(); r2++) {
                                if (zzj(optJSONArray3.optJSONObject(r2), optJSONArray4.optJSONObject(r2))) {
                                }
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean zzk(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        return (jSONArray == null || jSONArray2 == null || jSONArray.length() != jSONArray2.length()) ? false : true;
    }
}
