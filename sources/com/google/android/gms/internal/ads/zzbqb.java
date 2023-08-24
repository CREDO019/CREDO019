package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.SystemClock;
import android.view.View;
import androidx.core.app.NotificationManagerCompat;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.onesignal.NotificationBundleProcessor;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbqb implements zzbpq {
    private final com.google.android.gms.ads.internal.zzb zza;
    private final zzdxo zzb;
    private final zzfhz zzc;
    private final zzbxu zze;
    private final zzefz zzf;
    private com.google.android.gms.ads.internal.overlay.zzx zzg = null;
    private final zzcgs zzd = new zzcgs(null);

    public zzbqb(com.google.android.gms.ads.internal.zzb zzbVar, zzbxu zzbxuVar, zzefz zzefzVar, zzdxo zzdxoVar, zzfhz zzfhzVar) {
        this.zza = zzbVar;
        this.zze = zzbxuVar;
        this.zzf = zzefzVar;
        this.zzb = zzdxoVar;
        this.zzc = zzfhzVar;
    }

    public static int zzb(Map map) {
        String str = (String) map.get(NotificationBundleProcessor.PUSH_MINIFIED_BUTTONS_LIST);
        if (str != null) {
            if ("p".equalsIgnoreCase(str)) {
                return 7;
            }
            if ("l".equalsIgnoreCase(str)) {
                return 6;
            }
            return "c".equalsIgnoreCase(str) ? 14 : -1;
        }
        return -1;
    }

    static Uri zzc(Context context, zzapb zzapbVar, Uri uri, View view, Activity activity) {
        if (zzapbVar == null) {
            return uri;
        }
        try {
            return zzapbVar.zze(uri) ? zzapbVar.zza(uri, context, view, activity) : uri;
        } catch (zzapc unused) {
            return uri;
        } catch (Exception e) {
            com.google.android.gms.ads.internal.zzt.zzp().zzt(e, "OpenGmsgHandler.maybeAddClickSignalsToUri");
            return uri;
        }
    }

    static Uri zzd(Uri uri) {
        try {
            if (uri.getQueryParameter("aclk_ms") != null) {
                return uri.buildUpon().appendQueryParameter("aclk_upms", String.valueOf(SystemClock.uptimeMillis())).build();
            }
        } catch (UnsupportedOperationException e) {
            com.google.android.gms.ads.internal.util.zze.zzh("Error adding click uptime parameter to url: ".concat(String.valueOf(uri.toString())), e);
        }
        return uri;
    }

    public static boolean zzf(Map map) {
        return IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(map.get("custom_close"));
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x00df, code lost:
        if (com.google.android.gms.internal.ads.zzbqa.zzc(r11, r5, r6, r7) == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0122, code lost:
        r11 = r15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzh(com.google.android.gms.ads.internal.client.zza r18, java.util.Map r19, boolean r20, java.lang.String r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbqb.zzh(com.google.android.gms.ads.internal.client.zza, java.util.Map, boolean, java.lang.String, boolean):void");
    }

    private final void zzi(boolean z) {
        zzbxu zzbxuVar = this.zze;
        if (zzbxuVar != null) {
            zzbxuVar.zza(z);
        }
    }

    private final boolean zzj(com.google.android.gms.ads.internal.client.zza zzaVar, Context context, String str, String str2) {
        boolean zzv = com.google.android.gms.ads.internal.zzt.zzp().zzv(context);
        com.google.android.gms.ads.internal.zzt.zzq();
        com.google.android.gms.ads.internal.util.zzbr zzw = com.google.android.gms.ads.internal.util.zzs.zzw(context);
        zzdxo zzdxoVar = this.zzb;
        if (zzdxoVar != null) {
            zzegh.zzc(context, zzdxoVar, this.zzc, this.zzf, str2, "offline_open");
        }
        zzcmn zzcmnVar = (zzcmn) zzaVar;
        boolean z = zzcmnVar.zzQ().zzi() && zzcmnVar.zzk() == null;
        if (zzv) {
            this.zzf.zzh(this.zzd, str2);
            return false;
        }
        com.google.android.gms.ads.internal.zzt.zzq();
        if (NotificationManagerCompat.from(context).areNotificationsEnabled() && zzw != null && !z) {
            if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhf)).booleanValue()) {
                if (zzcmnVar.zzQ().zzi()) {
                    zzegh.zzh(zzcmnVar.zzk(), null, zzw, this.zzf, this.zzb, this.zzc, str2, str);
                } else {
                    ((zzcns) zzaVar).zzaG(zzw, this.zzf, this.zzb, this.zzc, str2, str, 14);
                }
                zzdxo zzdxoVar2 = this.zzb;
                if (zzdxoVar2 != null) {
                    zzegh.zzc(context, zzdxoVar2, this.zzc, this.zzf, str2, "dialog_impression");
                }
                zzaVar.onAdClicked();
                return true;
            }
        }
        this.zzf.zzc(str2);
        if (this.zzb != null) {
            HashMap hashMap = new HashMap();
            com.google.android.gms.ads.internal.zzt.zzq();
            if (!NotificationManagerCompat.from(context).areNotificationsEnabled()) {
                hashMap.put("dialog_not_shown_reason", "notifications_disabled");
            } else if (zzw == null) {
                hashMap.put("dialog_not_shown_reason", "work_manager_unavailable");
            } else {
                if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhf)).booleanValue()) {
                    hashMap.put("dialog_not_shown_reason", "notification_flow_disabled");
                } else if (z) {
                    hashMap.put("dialog_not_shown_reason", "fullscreen_no_activity");
                }
            }
            zzegh.zzd(context, this.zzb, this.zzc, this.zzf, str2, "dialog_not_shown", hashMap);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzk(int r5) {
        if (this.zzb == null) {
            return;
        }
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzhn)).booleanValue()) {
            zzfhz zzfhzVar = this.zzc;
            zzfhy zzb = zzfhy.zzb("cct_action");
            zzb.zza("cct_open_status", zzbjv.zza(r5));
            zzfhzVar.zzb(zzb);
            return;
        }
        zzdxn zza = this.zzb.zza();
        zza.zzb("action", "cct_action");
        zza.zzb("cct_open_status", zzbjv.zza(r5));
        zza.zzg();
    }

    /* JADX WARN: Removed duplicated region for block: B:134:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x039f  */
    @Override // com.google.android.gms.internal.ads.zzbpq
    /* renamed from: zze */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza(com.google.android.gms.ads.internal.client.zza r33, java.util.Map r34) {
        /*
            Method dump skipped, instructions count: 1084
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbqb.zza(com.google.android.gms.ads.internal.client.zza, java.util.Map):void");
    }
}
