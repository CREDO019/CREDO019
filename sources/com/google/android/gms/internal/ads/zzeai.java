package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build;
import android.os.RemoteException;
import android.text.TextUtils;
import com.amplitude.api.Constants;
import expo.modules.updates.UpdatesConfiguration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzeai implements zzeaq, zzdzt {
    private final zzeap zza;
    private final zzear zzb;
    private final zzdzu zzc;
    private final zzead zzd;
    private final zzdzs zze;
    private final String zzf;
    private boolean zzm;
    private int zzn;
    private boolean zzo;
    private String zzi = "{}";
    private String zzj = "";
    private long zzk = Long.MAX_VALUE;
    private zzeae zzl = zzeae.NONE;
    private zzeah zzp = zzeah.UNKNOWN;
    private final Map zzh = new HashMap();
    private final String zzg = "afma-sdk-a-v21.2.0";

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeai(zzeap zzeapVar, zzear zzearVar, zzdzu zzdzuVar, Context context, zzcgt zzcgtVar, zzead zzeadVar) {
        this.zza = zzeapVar;
        this.zzb = zzearVar;
        this.zzc = zzdzuVar;
        this.zze = new zzdzs(context);
        this.zzf = zzcgtVar.zza;
        this.zzd = zzeadVar;
        com.google.android.gms.ads.internal.zzt.zzt().zzg(this);
    }

    private final synchronized JSONObject zzo() throws JSONException {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        for (Map.Entry entry : this.zzh.entrySet()) {
            JSONArray jSONArray = new JSONArray();
            for (zzdzw zzdzwVar : (List) entry.getValue()) {
                if (zzdzwVar.zzd()) {
                    jSONArray.put(zzdzwVar.zzc());
                }
            }
            if (jSONArray.length() > 0) {
                jSONObject.put((String) entry.getKey(), jSONArray);
            }
        }
        return jSONObject;
    }

    private final void zzp() {
        this.zzo = true;
        this.zzd.zzc();
        this.zza.zzg(this);
        this.zzb.zzc(this);
        this.zzc.zzc(this);
        zzv(com.google.android.gms.ads.internal.zzt.zzp().zzh().zzo());
    }

    private final void zzq() {
        com.google.android.gms.ads.internal.zzt.zzp().zzh().zzG(zzc());
    }

    private final synchronized void zzr(zzeae zzeaeVar, boolean z) {
        if (this.zzl == zzeaeVar) {
            return;
        }
        if (zzm()) {
            zzt();
        }
        this.zzl = zzeaeVar;
        if (zzm()) {
            zzu();
        }
        if (z) {
            zzq();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0036 A[Catch: all -> 0x003d, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:7:0x0007, B:9:0x000b, B:11:0x001d, B:13:0x0027, B:18:0x0036, B:14:0x002b, B:16:0x0031), top: B:26:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003b A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final synchronized void zzs(boolean r2, boolean r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.zzm     // Catch: java.lang.Throwable -> L3d
            if (r0 != r2) goto L7
            monitor-exit(r1)
            return
        L7:
            r1.zzm = r2     // Catch: java.lang.Throwable -> L3d
            if (r2 == 0) goto L2b
            com.google.android.gms.internal.ads.zzbiq r2 = com.google.android.gms.internal.ads.zzbiy.zzhO     // Catch: java.lang.Throwable -> L3d
            com.google.android.gms.internal.ads.zzbiw r0 = com.google.android.gms.ads.internal.client.zzay.zzc()     // Catch: java.lang.Throwable -> L3d
            java.lang.Object r2 = r0.zzb(r2)     // Catch: java.lang.Throwable -> L3d
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch: java.lang.Throwable -> L3d
            boolean r2 = r2.booleanValue()     // Catch: java.lang.Throwable -> L3d
            if (r2 == 0) goto L27
            com.google.android.gms.ads.internal.util.zzaw r2 = com.google.android.gms.ads.internal.zzt.zzt()     // Catch: java.lang.Throwable -> L3d
            boolean r2 = r2.zzl()     // Catch: java.lang.Throwable -> L3d
            if (r2 != 0) goto L2b
        L27:
            r1.zzu()     // Catch: java.lang.Throwable -> L3d
            goto L34
        L2b:
            boolean r2 = r1.zzm()     // Catch: java.lang.Throwable -> L3d
            if (r2 != 0) goto L34
            r1.zzt()     // Catch: java.lang.Throwable -> L3d
        L34:
            if (r3 == 0) goto L3b
            r1.zzq()     // Catch: java.lang.Throwable -> L3d
            monitor-exit(r1)
            return
        L3b:
            monitor-exit(r1)
            return
        L3d:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzeai.zzs(boolean, boolean):void");
    }

    private final synchronized void zzt() {
        zzeae zzeaeVar = zzeae.NONE;
        int ordinal = this.zzl.ordinal();
        if (ordinal == 1) {
            this.zzb.zza();
        } else if (ordinal != 2) {
        } else {
            this.zzc.zza();
        }
    }

    private final synchronized void zzu() {
        zzeae zzeaeVar = zzeae.NONE;
        int ordinal = this.zzl.ordinal();
        if (ordinal == 1) {
            this.zzb.zzb();
        } else if (ordinal != 2) {
        } else {
            this.zzc.zzb();
        }
    }

    private final synchronized void zzv(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            zzs(jSONObject.optBoolean("isTestMode", false), false);
            zzr(zzeae.zza(jSONObject.optString("gesture", "NONE")), false);
            this.zzi = jSONObject.optString("networkExtras", "{}");
            this.zzk = jSONObject.optLong("networkExtrasExpirationSecs", Long.MAX_VALUE);
        } catch (JSONException unused) {
        }
    }

    public final zzeae zza() {
        return this.zzl;
    }

    public final synchronized String zzb() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhz)).booleanValue() && zzm()) {
            if (this.zzk < com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis() / 1000) {
                this.zzi = "{}";
                this.zzk = Long.MAX_VALUE;
                return "";
            } else if (this.zzi.equals("{}")) {
                return "";
            } else {
                return this.zzi;
            }
        }
        return "";
    }

    public final synchronized String zzc() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        try {
            jSONObject.put("isTestMode", this.zzm);
            jSONObject.put("gesture", this.zzl);
            if (this.zzk > com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis() / 1000) {
                jSONObject.put("networkExtras", this.zzi);
                jSONObject.put("networkExtrasExpirationSecs", this.zzk);
            }
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public final synchronized JSONObject zzd() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        try {
            jSONObject.put(Constants.AMP_TRACKING_OPTION_PLATFORM, "ANDROID");
            jSONObject.put(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY, this.zzg);
            jSONObject.put("internalSdkVersion", this.zzf);
            jSONObject.put("osVersion", Build.VERSION.RELEASE);
            jSONObject.put("adapters", this.zzd.zza());
            if (this.zzk < com.google.android.gms.ads.internal.zzt.zzB().currentTimeMillis() / 1000) {
                this.zzi = "{}";
            }
            jSONObject.put("networkExtras", this.zzi);
            jSONObject.put("adSlots", zzo());
            jSONObject.put("appInfo", this.zze.zza());
            String zzc = com.google.android.gms.ads.internal.zzt.zzp().zzh().zzh().zzc();
            if (!TextUtils.isEmpty(zzc)) {
                jSONObject.put("cld", new JSONObject(zzc));
            }
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhP)).booleanValue() && !TextUtils.isEmpty(this.zzj)) {
                String str = this.zzj;
                com.google.android.gms.ads.internal.util.zze.zze("Policy violation data: " + str);
                jSONObject.put("policyViolations", new JSONObject(this.zzj));
            }
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhO)).booleanValue()) {
                jSONObject.put("openAction", this.zzp);
                jSONObject.put("gesture", this.zzl);
            }
        } catch (JSONException e) {
            com.google.android.gms.ads.internal.zzt.zzp().zzs(e, "Inspector.toJson");
            com.google.android.gms.ads.internal.util.zze.zzk("Ad inspector encountered an error", e);
        }
        return jSONObject;
    }

    public final synchronized void zze(String str, zzdzw zzdzwVar) {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhz)).booleanValue() && zzm()) {
            if (this.zzn >= ((Integer) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhB)).intValue()) {
                com.google.android.gms.ads.internal.util.zze.zzj("Maximum number of ad requests stored reached. Dropping the current request.");
                return;
            }
            if (!this.zzh.containsKey(str)) {
                this.zzh.put(str, new ArrayList());
            }
            this.zzn++;
            ((List) this.zzh.get(str)).add(zzdzwVar);
        }
    }

    public final void zzf() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhz)).booleanValue()) {
            if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhO)).booleanValue() || !com.google.android.gms.ads.internal.zzt.zzp().zzh().zzO()) {
                String zzo = com.google.android.gms.ads.internal.zzt.zzp().zzh().zzo();
                if (TextUtils.isEmpty(zzo)) {
                    return;
                }
                try {
                    if (new JSONObject(zzo).optBoolean("isTestMode", false)) {
                        zzp();
                        return;
                    }
                    return;
                } catch (JSONException unused) {
                    return;
                }
            }
            zzp();
        }
    }

    public final synchronized void zzg(com.google.android.gms.ads.internal.client.zzcy zzcyVar, zzeah zzeahVar) {
        if (!zzm()) {
            try {
                zzcyVar.zze(zzfem.zzd(18, null, null));
                return;
            } catch (RemoteException unused) {
                com.google.android.gms.ads.internal.util.zze.zzj("Ad inspector cannot be opened because the device is not in test mode. See https://developers.google.com/admob/android/test-ads#enable_test_devices for more information.");
                return;
            }
        }
        if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhz)).booleanValue()) {
            try {
                zzcyVar.zze(zzfem.zzd(1, null, null));
                return;
            } catch (RemoteException unused2) {
                com.google.android.gms.ads.internal.util.zze.zzj("Ad inspector had an internal error.");
                return;
            }
        }
        this.zzp = zzeahVar;
        this.zza.zzi(zzcyVar, new zzbpr(this));
        return;
    }

    public final synchronized void zzh(String str, long j) {
        this.zzi = str;
        this.zzk = j;
        zzq();
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000a, code lost:
        if (r2 != false) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzi(boolean r2) {
        /*
            r1 = this;
            boolean r0 = r1.zzo
            if (r0 != 0) goto La
            if (r2 == 0) goto L15
            r1.zzp()
            goto Lc
        La:
            if (r2 == 0) goto L15
        Lc:
            boolean r2 = r1.zzm
            if (r2 == 0) goto L11
            goto L15
        L11:
            r1.zzu()
            return
        L15:
            boolean r2 = r1.zzm()
            if (r2 != 0) goto L1e
            r1.zzt()
        L1e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzeai.zzi(boolean):void");
    }

    public final void zzj(zzeae zzeaeVar) {
        zzr(zzeaeVar, true);
    }

    public final synchronized void zzk(String str) {
        this.zzj = str;
    }

    public final void zzl(boolean z) {
        if (!this.zzo && z) {
            zzp();
        }
        zzs(z, true);
    }

    public final synchronized boolean zzm() {
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhO)).booleanValue()) {
            return this.zzm || com.google.android.gms.ads.internal.zzt.zzt().zzl();
        }
        return this.zzm;
    }

    public final synchronized boolean zzn() {
        return this.zzm;
    }
}
