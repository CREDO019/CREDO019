package com.google.android.gms.ads.internal.util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.internal.ads.zzbiy;
import com.google.android.gms.internal.ads.zzeah;
import com.google.android.gms.internal.ads.zzeai;
import com.google.android.gms.internal.ads.zzfyx;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaw {
    private zzeai zzg;
    private final Object zzb = new Object();
    private String zzc = "";
    private String zzd = "";
    private boolean zze = false;
    private boolean zzf = false;
    protected String zza = "";

    protected static final String zzo(Context context, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", com.google.android.gms.ads.internal.zzt.zzq().zzc(context, str2));
        zzfyx zzb = new zzbo(context).zzb(0, str, hashMap, null);
        try {
            return (String) zzb.get(((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdQ)).intValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            zze.zzh("Interrupted while retrieving a response from: ".concat(String.valueOf(str)), e);
            zzb.cancel(true);
            return null;
        } catch (TimeoutException e2) {
            zze.zzh("Timeout while retrieving a response from: ".concat(String.valueOf(str)), e2);
            zzb.cancel(true);
            return null;
        } catch (Exception e3) {
            zze.zzh("Error retrieving a response from: ".concat(String.valueOf(str)), e3);
            return null;
        }
    }

    private final Uri zzp(Context context, String str, String str2, String str3) {
        String str4;
        String str5;
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        synchronized (this.zzb) {
            if (TextUtils.isEmpty(this.zzc)) {
                com.google.android.gms.ads.internal.zzt.zzq();
                try {
                    str5 = new String(IOUtils.readInputStreamFully(context.openFileInput("debug_signals_id.txt"), true), "UTF-8");
                } catch (IOException unused) {
                    zze.zze("Error reading from internal storage.");
                    str5 = "";
                }
                this.zzc = str5;
                if (TextUtils.isEmpty(str5)) {
                    com.google.android.gms.ads.internal.zzt.zzq();
                    this.zzc = UUID.randomUUID().toString();
                    com.google.android.gms.ads.internal.zzt.zzq();
                    String str6 = this.zzc;
                    try {
                        FileOutputStream openFileOutput = context.openFileOutput("debug_signals_id.txt", 0);
                        openFileOutput.write(str6.getBytes("UTF-8"));
                        openFileOutput.close();
                    } catch (Exception e) {
                        zze.zzh("Error writing to file in internal storage.", e);
                    }
                }
            }
            str4 = this.zzc;
        }
        buildUpon.appendQueryParameter("linkedDeviceId", str4);
        buildUpon.appendQueryParameter("adSlotPath", str2);
        buildUpon.appendQueryParameter("afmaVersion", str3);
        return buildUpon.build();
    }

    public final zzeai zza() {
        return this.zzg;
    }

    public final String zzb() {
        String str;
        synchronized (this.zzb) {
            str = this.zzd;
        }
        return str;
    }

    public final void zzc(Context context) {
        zzeai zzeaiVar;
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhO)).booleanValue() || (zzeaiVar = this.zzg) == null) {
            return;
        }
        zzeaiVar.zzg(new zzat(this, context), zzeah.DEBUG_MENU);
    }

    public final void zzd(Context context, String str, String str2) {
        com.google.android.gms.ads.internal.zzt.zzq();
        zzs.zzQ(context, zzp(context, (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdM), str, str2));
    }

    public final void zze(Context context, String str, String str2, String str3) {
        Uri.Builder buildUpon = zzp(context, (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdP), str3, str).buildUpon();
        buildUpon.appendQueryParameter("debugData", str2);
        com.google.android.gms.ads.internal.zzt.zzq();
        zzs.zzH(context, str, buildUpon.build().toString());
    }

    public final void zzf(boolean z) {
        synchronized (this.zzb) {
            this.zzf = z;
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhO)).booleanValue()) {
                com.google.android.gms.ads.internal.zzt.zzp().zzh().zzB(z);
                zzeai zzeaiVar = this.zzg;
                if (zzeaiVar != null) {
                    zzeaiVar.zzi(z);
                }
            }
        }
    }

    public final void zzg(zzeai zzeaiVar) {
        this.zzg = zzeaiVar;
    }

    public final void zzh(boolean z) {
        synchronized (this.zzb) {
            this.zze = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzi(Context context, String str, boolean z, boolean z2) {
        if (!(context instanceof Activity)) {
            zze.zzi("Can not create dialog without Activity Context");
        } else {
            zzs.zza.post(new zzav(this, context, str, z, z2));
        }
    }

    public final boolean zzj(Context context, String str, String str2) {
        String zzo = zzo(context, zzp(context, (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzdO), str, str2).toString(), str2);
        if (TextUtils.isEmpty(zzo)) {
            zze.zze("Not linked for debug signals.");
            return false;
        }
        try {
            boolean equals = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(new JSONObject(zzo.trim()).optString("debug_mode"));
            zzf(equals);
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhO)).booleanValue()) {
                zzg zzh = com.google.android.gms.ads.internal.zzt.zzp().zzh();
                if (true != equals) {
                    str = "";
                }
                zzh.zzA(str);
            }
            return equals;
        } catch (JSONException e) {
            zze.zzk("Fail to get debug mode response json.", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzk(android.content.Context r4, java.lang.String r5, java.lang.String r6) {
        /*
            r3 = this;
            com.google.android.gms.internal.ads.zzbiq r0 = com.google.android.gms.internal.ads.zzbiy.zzdN
            com.google.android.gms.internal.ads.zzbiw r1 = com.google.android.gms.ads.internal.client.zzay.zzc()
            java.lang.Object r0 = r1.zzb(r0)
            java.lang.String r0 = (java.lang.String) r0
            android.net.Uri r0 = r3.zzp(r4, r0, r5, r6)
            java.lang.String r0 = r0.toString()
            java.lang.String r4 = zzo(r4, r0, r6)
            boolean r6 = android.text.TextUtils.isEmpty(r4)
            r0 = 0
            if (r6 == 0) goto L25
            java.lang.String r4 = "Not linked for in app preview."
            com.google.android.gms.ads.internal.util.zze.zze(r4)
            return r0
        L25:
            java.lang.String r4 = r4.trim()
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: org.json.JSONException -> L83
            r6.<init>(r4)     // Catch: org.json.JSONException -> L83
            java.lang.String r4 = "gct"
            java.lang.String r4 = r6.optString(r4)     // Catch: org.json.JSONException -> L83
            java.lang.String r1 = "status"
            java.lang.String r6 = r6.optString(r1)     // Catch: org.json.JSONException -> L83
            r3.zza = r6     // Catch: org.json.JSONException -> L83
            com.google.android.gms.internal.ads.zzbiq r6 = com.google.android.gms.internal.ads.zzbiy.zzhO     // Catch: org.json.JSONException -> L83
            com.google.android.gms.internal.ads.zzbiw r1 = com.google.android.gms.ads.internal.client.zzay.zzc()     // Catch: org.json.JSONException -> L83
            java.lang.Object r6 = r1.zzb(r6)     // Catch: org.json.JSONException -> L83
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch: org.json.JSONException -> L83
            boolean r6 = r6.booleanValue()     // Catch: org.json.JSONException -> L83
            r1 = 1
            if (r6 == 0) goto L79
            java.lang.String r6 = "0"
            java.lang.String r2 = r3.zza     // Catch: org.json.JSONException -> L83
            boolean r6 = r6.equals(r2)     // Catch: org.json.JSONException -> L83
            if (r6 != 0) goto L66
            java.lang.String r6 = "2"
            java.lang.String r2 = r3.zza     // Catch: org.json.JSONException -> L83
            boolean r6 = r6.equals(r2)     // Catch: org.json.JSONException -> L83
            if (r6 == 0) goto L64
            goto L66
        L64:
            r6 = 0
            goto L67
        L66:
            r6 = 1
        L67:
            r3.zzf(r6)     // Catch: org.json.JSONException -> L83
            com.google.android.gms.internal.ads.zzcfw r2 = com.google.android.gms.ads.internal.zzt.zzp()     // Catch: org.json.JSONException -> L83
            com.google.android.gms.ads.internal.util.zzg r2 = r2.zzh()     // Catch: org.json.JSONException -> L83
            if (r1 == r6) goto L76
            java.lang.String r5 = ""
        L76:
            r2.zzA(r5)     // Catch: org.json.JSONException -> L83
        L79:
            java.lang.Object r5 = r3.zzb
            monitor-enter(r5)
            r3.zzd = r4     // Catch: java.lang.Throwable -> L80
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L80
            return r1
        L80:
            r4 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L80
            throw r4
        L83:
            r4 = move-exception
            java.lang.String r5 = "Fail to get in app preview response json."
            com.google.android.gms.ads.internal.util.zze.zzk(r5, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.util.zzaw.zzk(android.content.Context, java.lang.String, java.lang.String):boolean");
    }

    public final boolean zzl() {
        boolean z;
        synchronized (this.zzb) {
            z = this.zzf;
        }
        return z;
    }

    public final boolean zzm() {
        boolean z;
        synchronized (this.zzb) {
            z = this.zze;
        }
        return z;
    }

    public final boolean zzn(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || !zzm()) {
            return false;
        }
        zze.zze("Sending troubleshooting signals to the server.");
        zze(context, str, str2, str3);
        return true;
    }
}
