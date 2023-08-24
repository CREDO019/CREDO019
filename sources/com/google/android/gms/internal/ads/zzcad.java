package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import androidx.core.p005os.EnvironmentCompat;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.ParametersAreNonnullByDefault;

/* compiled from: com.google.android.gms:play-services-ads-lite@@21.2.0 */
@ParametersAreNonnullByDefault
/* loaded from: classes2.dex */
public final class zzcad implements zzcaf {
    static zzcaf zza;
    static zzcaf zzb;
    private static final Object zzc = new Object();
    private final Context zze;
    private final ExecutorService zzg;
    private final zzcgt zzh;
    private final Object zzd = new Object();
    private final WeakHashMap zzf = new WeakHashMap();

    protected zzcad(Context context, zzcgt zzcgtVar) {
        zzfpg.zza();
        this.zzg = Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());
        this.zze = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzh = zzcgtVar;
    }

    public static zzcaf zza(Context context) {
        synchronized (zzc) {
            if (zza == null) {
                if (((Boolean) zzbkw.zze.zze()).booleanValue()) {
                    if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgD)).booleanValue()) {
                        zza = new zzcad(context, zzcgt.zza());
                    }
                }
                zza = new zzcae();
            }
        }
        return zza;
    }

    public static zzcaf zzb(Context context, zzcgt zzcgtVar) {
        synchronized (zzc) {
            if (zzb == null) {
                if (((Boolean) zzbkw.zze.zze()).booleanValue()) {
                    if (!((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzgD)).booleanValue()) {
                        zzcad zzcadVar = new zzcad(context, zzcgtVar);
                        Thread thread = Looper.getMainLooper().getThread();
                        if (thread != null) {
                            synchronized (zzcadVar.zzd) {
                                zzcadVar.zzf.put(thread, true);
                            }
                            thread.setUncaughtExceptionHandler(new zzcac(zzcadVar, thread.getUncaughtExceptionHandler()));
                        }
                        Thread.setDefaultUncaughtExceptionHandler(new zzcab(zzcadVar, Thread.getDefaultUncaughtExceptionHandler()));
                        zzb = zzcadVar;
                    }
                }
                zzb = new zzcae();
            }
        }
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzc(Thread thread, Throwable th) {
        StackTraceElement[] stackTrace;
        if (th != null) {
            boolean z = false;
            boolean z2 = false;
            for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
                for (StackTraceElement stackTraceElement : th2.getStackTrace()) {
                    z |= zzcgg.zzn(stackTraceElement.getClassName());
                    z2 |= getClass().getName().equals(stackTraceElement.getClassName());
                }
            }
            if (!z || z2) {
                return;
            }
            zze(th, "", 1.0f);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcaf
    public final void zzd(Throwable th, String str) {
        zze(th, str, 1.0f);
    }

    @Override // com.google.android.gms.internal.ads.zzcaf
    public final void zze(Throwable th, String str, float f) {
        boolean z;
        String str2;
        if (zzcgg.zzf(th) == null) {
            return;
        }
        String name = th.getClass().getName();
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        double d = f;
        double random = Math.random();
        int r11 = f > 0.0f ? (int) (1.0f / f) : 1;
        if (random < d) {
            ArrayList<String> arrayList = new ArrayList();
            try {
                z = Wrappers.packageManager(this.zze).isCallerInstantApp();
            } catch (Throwable th2) {
                zzcgn.zzh("Error fetching instant app info", th2);
                z = false;
            }
            try {
                str2 = this.zze.getPackageName();
            } catch (Throwable unused) {
                zzcgn.zzj("Cannot obtain package name, proceeding.");
                str2 = EnvironmentCompat.MEDIA_UNKNOWN;
            }
            Uri.Builder appendQueryParameter = new Uri.Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("is_aia", Boolean.toString(z)).appendQueryParameter("id", "gmob-apps-report-exception").appendQueryParameter("os", Build.VERSION.RELEASE).appendQueryParameter("api", String.valueOf(Build.VERSION.SDK_INT));
            String str3 = Build.MANUFACTURER;
            String str4 = Build.MODEL;
            if (!str4.startsWith(str3)) {
                str4 = str3 + " " + str4;
            }
            arrayList.add(appendQueryParameter.appendQueryParameter("device", str4).appendQueryParameter("js", this.zzh.zza).appendQueryParameter("appid", str2).appendQueryParameter("exceptiontype", name).appendQueryParameter("stacktrace", stringWriter2).appendQueryParameter("eids", TextUtils.join(",", zzbiy.zza())).appendQueryParameter("exceptionkey", str).appendQueryParameter("cl", "470884269").appendQueryParameter("rc", "dev").appendQueryParameter("sampling_rate", Integer.toString(r11)).appendQueryParameter("pb_tm", String.valueOf(zzbkw.zzc.zze())).appendQueryParameter("gmscv", String.valueOf(GoogleApiAvailabilityLight.getInstance().getApkVersion(this.zze))).appendQueryParameter("lite", true != this.zzh.zze ? SessionDescription.SUPPORTED_SDP_VERSION : IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).toString());
            for (final String str5 : arrayList) {
                final zzcgs zzcgsVar = new zzcgs(null);
                this.zzg.execute(new Runnable() { // from class: com.google.android.gms.internal.ads.zzcaa
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzcgs.this.zza(str5);
                    }
                });
            }
        }
    }
}
