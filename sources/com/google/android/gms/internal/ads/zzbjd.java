package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import expo.modules.updates.UpdatesConfiguration;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
@ParametersAreNonnullByDefault
@Deprecated
/* loaded from: classes2.dex */
public final class zzbjd {
    String zzd;
    Context zze;
    String zzf;
    private AtomicBoolean zzh;
    private File zzi;
    final BlockingQueue zza = new ArrayBlockingQueue(100);
    final LinkedHashMap zzb = new LinkedHashMap();
    final Map zzc = new HashMap();
    private final HashSet zzg = new HashSet(Arrays.asList("noop", "activeViewPingSent", "viewabilityChanged", "visibilityChanged"));

    public static /* synthetic */ void zzc(zzbjd zzbjdVar) {
        while (true) {
            try {
                zzbjn zzbjnVar = (zzbjn) zzbjdVar.zza.take();
                zzbjm zza = zzbjnVar.zza();
                if (!TextUtils.isEmpty(zza.zzb())) {
                    zzbjdVar.zzg(zzbjdVar.zzb(zzbjdVar.zzb, zzbjnVar.zzb()), zza);
                }
            } catch (InterruptedException e) {
                com.google.android.gms.ads.internal.util.zze.zzk("CsiReporter:reporter interrupted", e);
                return;
            }
        }
    }

    private final void zzg(Map map, zzbjm zzbjmVar) {
        Uri.Builder buildUpon = Uri.parse(this.zzd).buildUpon();
        for (Map.Entry entry : map.entrySet()) {
            buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        String uri = buildUpon.build().toString();
        if (zzbjmVar != null) {
            StringBuilder sb = new StringBuilder(uri);
            if (!TextUtils.isEmpty(zzbjmVar.zzb())) {
                sb.append("&it=");
                sb.append(zzbjmVar.zzb());
            }
            if (!TextUtils.isEmpty(zzbjmVar.zza())) {
                sb.append("&blat=");
                sb.append(zzbjmVar.zza());
            }
            uri = sb.toString();
        }
        if (!this.zzh.get()) {
            com.google.android.gms.ads.internal.zzt.zzq();
            com.google.android.gms.ads.internal.util.zzs.zzH(this.zze, this.zzf, uri);
            return;
        }
        File file = this.zzi;
        if (file != null) {
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file, true);
                    try {
                        fileOutputStream2.write(uri.getBytes());
                        fileOutputStream2.write(10);
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e) {
                            com.google.android.gms.ads.internal.util.zze.zzk("CsiReporter: Cannot close file: sdk_csi_data.txt.", e);
                        }
                    } catch (IOException e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        com.google.android.gms.ads.internal.util.zze.zzk("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                                com.google.android.gms.ads.internal.util.zze.zzk("CsiReporter: Cannot close file: sdk_csi_data.txt.", e3);
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e4) {
                                com.google.android.gms.ads.internal.util.zze.zzk("CsiReporter: Cannot close file: sdk_csi_data.txt.", e4);
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException e5) {
                e = e5;
            }
        } else {
            com.google.android.gms.ads.internal.util.zze.zzj("CsiReporter: File doesn't exists. Cannot write CSI data to file.");
        }
    }

    public final zzbjj zza(String str) {
        zzbjj zzbjjVar = (zzbjj) this.zzc.get(str);
        return zzbjjVar != null ? zzbjjVar : zzbjj.zza;
    }

    final Map zzb(Map map, Map map2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        for (Map.Entry entry : map2.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) linkedHashMap.get(str);
            linkedHashMap.put(str, zza(str).zza(str2, (String) entry.getValue()));
        }
        return linkedHashMap;
    }

    public final void zzd(Context context, String str, String str2, Map map) {
        File externalStorageDirectory;
        this.zze = context;
        this.zzf = str;
        this.zzd = str2;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.zzh = atomicBoolean;
        atomicBoolean.set(((Boolean) zzbkj.zzc.zze()).booleanValue());
        if (this.zzh.get() && (externalStorageDirectory = Environment.getExternalStorageDirectory()) != null) {
            this.zzi = new File(externalStorageDirectory, "sdk_csi_data.txt");
        }
        for (Map.Entry entry : map.entrySet()) {
            this.zzb.put((String) entry.getKey(), (String) entry.getValue());
        }
        zzcha.zza.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzbjc
            @Override // java.lang.Runnable
            public final void run() {
                zzbjd.zzc(zzbjd.this);
            }
        });
        this.zzc.put("action", zzbjj.zzb);
        this.zzc.put("ad_format", zzbjj.zzb);
        this.zzc.put("e", zzbjj.zzc);
    }

    public final void zze(String str) {
        if (this.zzg.contains(str)) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(UpdatesConfiguration.UPDATES_CONFIGURATION_SDK_VERSION_KEY, this.zzf);
        linkedHashMap.put("ue", str);
        zzg(zzb(this.zzb, linkedHashMap), null);
    }

    public final boolean zzf(zzbjn zzbjnVar) {
        return this.zza.offer(zzbjnVar);
    }
}
