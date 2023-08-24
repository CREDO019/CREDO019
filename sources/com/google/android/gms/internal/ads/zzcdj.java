package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzcdj implements zzcdo {
    public static final /* synthetic */ int zzb = 0;
    private static final List zzc = Collections.synchronizedList(new ArrayList());
    boolean zza;
    private final zzgsi zzd;
    private final LinkedHashMap zze;
    private final Context zzh;
    private final zzcdl zzi;
    private final zzcdk zzn;
    private final List zzf = new ArrayList();
    private final List zzg = new ArrayList();
    private final Object zzj = new Object();
    private HashSet zzk = new HashSet();
    private boolean zzl = false;
    private boolean zzm = false;

    public zzcdj(Context context, zzcgt zzcgtVar, zzcdl zzcdlVar, String str, zzcdk zzcdkVar, byte[] bArr) {
        Preconditions.checkNotNull(zzcdlVar, "SafeBrowsing config is not present.");
        this.zzh = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zze = new LinkedHashMap();
        this.zzn = zzcdkVar;
        this.zzi = zzcdlVar;
        for (String str2 : zzcdlVar.zze) {
            this.zzk.add(str2.toLowerCase(Locale.ENGLISH));
        }
        this.zzk.remove("cookie".toLowerCase(Locale.ENGLISH));
        zzgsi zza = zzgtn.zza();
        zza.zzn(9);
        zza.zzj(str);
        zza.zzh(str);
        zzgsj zza2 = zzgsk.zza();
        String str3 = this.zzi.zza;
        if (str3 != null) {
            zza2.zza(str3);
        }
        zza.zzg((zzgsk) zza2.zzal());
        zzgti zza3 = zzgtj.zza();
        zza3.zzc(Wrappers.packageManager(this.zzh).isCallerInstantApp());
        String str4 = zzcgtVar.zza;
        if (str4 != null) {
            zza3.zza(str4);
        }
        long apkVersion = GoogleApiAvailabilityLight.getInstance().getApkVersion(this.zzh);
        if (apkVersion > 0) {
            zza3.zzb(apkVersion);
        }
        zza.zzf((zzgtj) zza3.zzal());
        this.zzd = zza;
    }

    @Override // com.google.android.gms.internal.ads.zzcdo
    public final zzcdl zza() {
        return this.zzi;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzb(Map map) throws Exception {
        zzgtg zzgtgVar;
        zzfyx zzm;
        if (map != null) {
            try {
                for (String str : map.keySet()) {
                    JSONArray optJSONArray = new JSONObject((String) map.get(str)).optJSONArray("matches");
                    if (optJSONArray != null) {
                        synchronized (this.zzj) {
                            int length = optJSONArray.length();
                            synchronized (this.zzj) {
                                zzgtgVar = (zzgtg) this.zze.get(str);
                            }
                            if (zzgtgVar == null) {
                                zzcdn.zza("Cannot find the corresponding resource object for " + str);
                            } else {
                                for (int r6 = 0; r6 < length; r6++) {
                                    zzgtgVar.zza(optJSONArray.getJSONObject(r6).getString("threat_type"));
                                }
                                this.zza = (length > 0) | this.zza;
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                if (((Boolean) zzbkv.zzb.zze()).booleanValue()) {
                    com.google.android.gms.ads.internal.util.zze.zzf("Failed to get SafeBrowsing metadata", e);
                }
                return zzfyo.zzh(new Exception("Safebrowsing report transmission failed."));
            }
        }
        if (this.zza) {
            synchronized (this.zzj) {
                this.zzd.zzn(10);
            }
        }
        boolean z = this.zza;
        if ((!z || !this.zzi.zzg) && ((!this.zzm || !this.zzi.zzf) && (z || !this.zzi.zzd))) {
            return zzfyo.zzi(null);
        }
        synchronized (this.zzj) {
            for (zzgtg zzgtgVar2 : this.zze.values()) {
                this.zzd.zzc((zzgth) zzgtgVar2.zzal());
            }
            this.zzd.zza(this.zzf);
            this.zzd.zzb(this.zzg);
            if (zzcdn.zzb()) {
                String zzl = this.zzd.zzl();
                String zzk = this.zzd.zzk();
                StringBuilder sb = new StringBuilder("Sending SB report\n  url: " + zzl + "\n  clickUrl: " + zzk + "\n  resources: \n");
                for (zzgth zzgthVar : this.zzd.zzm()) {
                    sb.append("    [");
                    sb.append(zzgthVar.zza());
                    sb.append("] ");
                    sb.append(zzgthVar.zze());
                }
                zzcdn.zza(sb.toString());
            }
            zzfyx zzb2 = new com.google.android.gms.ads.internal.util.zzbo(this.zzh).zzb(1, this.zzi.zzb, null, ((zzgtn) this.zzd.zzal()).zzaw());
            if (zzcdn.zzb()) {
                zzb2.zzc(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcde
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzcdn.zza("Pinged SB successfully.");
                    }
                }, zzcha.zza);
            }
            zzm = zzfyo.zzm(zzb2, new zzfru() { // from class: com.google.android.gms.internal.ads.zzcdf
                @Override // com.google.android.gms.internal.ads.zzfru
                public final Object apply(Object obj) {
                    String str2 = (String) obj;
                    int r1 = zzcdj.zzb;
                    return null;
                }
            }, zzcha.zzf);
        }
        return zzm;
    }

    @Override // com.google.android.gms.internal.ads.zzcdo
    public final void zzd(String str, Map map, int r9) {
        synchronized (this.zzj) {
            if (r9 == 3) {
                this.zzm = true;
            }
            if (this.zze.containsKey(str)) {
                if (r9 == 3) {
                    ((zzgtg) this.zze.get(str)).zze(zzgtf.zza(3));
                }
                return;
            }
            zzgtg zzc2 = zzgth.zzc();
            int zza = zzgtf.zza(r9);
            if (zza != 0) {
                zzc2.zze(zza);
            }
            zzc2.zzb(this.zze.size());
            zzc2.zzd(str);
            zzgsr zza2 = zzgsu.zza();
            if (!this.zzk.isEmpty() && map != null) {
                for (Map.Entry entry : map.entrySet()) {
                    String str2 = entry.getKey() != null ? (String) entry.getKey() : "";
                    String str3 = entry.getValue() != null ? (String) entry.getValue() : "";
                    if (this.zzk.contains(str2.toLowerCase(Locale.ENGLISH))) {
                        zzgsp zza3 = zzgsq.zza();
                        zza3.zza(zzgnf.zzx(str2));
                        zza3.zzb(zzgnf.zzx(str3));
                        zza2.zza((zzgsq) zza3.zzal());
                    }
                }
            }
            zzc2.zzc((zzgsu) zza2.zzal());
            this.zze.put(str, zzc2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcdo
    public final void zze() {
        synchronized (this.zzj) {
            this.zze.keySet();
            zzfyx zzn = zzfyo.zzn(zzfyo.zzi(Collections.emptyMap()), new zzfxv() { // from class: com.google.android.gms.internal.ads.zzcdg
                @Override // com.google.android.gms.internal.ads.zzfxv
                public final zzfyx zza(Object obj) {
                    return zzcdj.this.zzb((Map) obj);
                }
            }, zzcha.zzf);
            zzfyx zzo = zzfyo.zzo(zzn, 10L, TimeUnit.SECONDS, zzcha.zzd);
            zzfyo.zzr(zzn, new zzcdi(this, zzo), zzcha.zzf);
            zzc.add(zzo);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(Bitmap bitmap) {
        zzgnc zzt = zzgnf.zzt();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, zzt);
        synchronized (this.zzj) {
            zzgsi zzgsiVar = this.zzd;
            zzgsz zza = zzgtb.zza();
            zza.zza(zzt.zzb());
            zza.zzb("image/png");
            zza.zzc(2);
            zzgsiVar.zzi((zzgtb) zza.zzal());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0036 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // com.google.android.gms.internal.ads.zzcdo
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzg(android.view.View r8) {
        /*
            r7 = this;
            com.google.android.gms.internal.ads.zzcdl r0 = r7.zzi
            boolean r0 = r0.zzc
            if (r0 != 0) goto L7
            return
        L7:
            boolean r0 = r7.zzl
            if (r0 == 0) goto Lc
            return
        Lc:
            com.google.android.gms.ads.internal.zzt.zzq()
            r0 = 1
            r1 = 0
            if (r8 != 0) goto L14
            goto L6d
        L14:
            boolean r2 = r8.isDrawingCacheEnabled()     // Catch: java.lang.RuntimeException -> L2d
            r8.setDrawingCacheEnabled(r0)     // Catch: java.lang.RuntimeException -> L2d
            android.graphics.Bitmap r3 = r8.getDrawingCache()     // Catch: java.lang.RuntimeException -> L2d
            if (r3 == 0) goto L26
            android.graphics.Bitmap r3 = android.graphics.Bitmap.createBitmap(r3)     // Catch: java.lang.RuntimeException -> L2d
            goto L27
        L26:
            r3 = r1
        L27:
            r8.setDrawingCacheEnabled(r2)     // Catch: java.lang.RuntimeException -> L2b
            goto L34
        L2b:
            r2 = move-exception
            goto L2f
        L2d:
            r2 = move-exception
            r3 = r1
        L2f:
            java.lang.String r4 = "Fail to capture the web view"
            com.google.android.gms.ads.internal.util.zze.zzh(r4, r2)
        L34:
            if (r3 != 0) goto L6c
            int r2 = r8.getWidth()     // Catch: java.lang.RuntimeException -> L65
            int r3 = r8.getHeight()     // Catch: java.lang.RuntimeException -> L65
            if (r2 == 0) goto L5f
            if (r3 != 0) goto L43
            goto L5f
        L43:
            int r4 = r8.getWidth()     // Catch: java.lang.RuntimeException -> L65
            int r5 = r8.getHeight()     // Catch: java.lang.RuntimeException -> L65
            android.graphics.Bitmap$Config r6 = android.graphics.Bitmap.Config.RGB_565     // Catch: java.lang.RuntimeException -> L65
            android.graphics.Bitmap r4 = android.graphics.Bitmap.createBitmap(r4, r5, r6)     // Catch: java.lang.RuntimeException -> L65
            android.graphics.Canvas r5 = new android.graphics.Canvas     // Catch: java.lang.RuntimeException -> L65
            r5.<init>(r4)     // Catch: java.lang.RuntimeException -> L65
            r6 = 0
            r8.layout(r6, r6, r2, r3)     // Catch: java.lang.RuntimeException -> L65
            r8.draw(r5)     // Catch: java.lang.RuntimeException -> L65
            r1 = r4
            goto L6d
        L5f:
            java.lang.String r8 = "Width or height of view is zero"
            com.google.android.gms.ads.internal.util.zze.zzj(r8)     // Catch: java.lang.RuntimeException -> L65
            goto L6d
        L65:
            r8 = move-exception
            java.lang.String r2 = "Fail to capture the webview"
            com.google.android.gms.ads.internal.util.zze.zzh(r2, r8)
            goto L6d
        L6c:
            r1 = r3
        L6d:
            if (r1 != 0) goto L75
            java.lang.String r8 = "Failed to capture the webview bitmap."
            com.google.android.gms.internal.ads.zzcdn.zza(r8)
            return
        L75:
            r7.zzl = r0
            com.google.android.gms.internal.ads.zzcdh r8 = new com.google.android.gms.internal.ads.zzcdh
            r8.<init>()
            com.google.android.gms.ads.internal.util.zzs.zzf(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcdj.zzg(android.view.View):void");
    }

    @Override // com.google.android.gms.internal.ads.zzcdo
    public final void zzh(String str) {
        synchronized (this.zzj) {
            if (str == null) {
                this.zzd.zzd();
            } else {
                this.zzd.zze(str);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcdo
    public final boolean zzi() {
        return PlatformVersion.isAtLeastKitKat() && this.zzi.zzc && !this.zzl;
    }
}
