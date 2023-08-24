package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaqb {
    private static final String zzd = "zzaqb";
    protected final Context zza;
    private ExecutorService zze;
    private DexClassLoader zzf;
    private zzaph zzg;
    private byte[] zzh;
    private final boolean zzl;
    private zzaow zzo;
    private final Map zzp;
    private zzapu zzr;
    private volatile AdvertisingIdClient zzi = null;
    private volatile boolean zzj = false;
    private Future zzk = null;
    private volatile zzamx zzm = null;
    private Future zzn = null;
    protected boolean zzb = false;
    protected boolean zzc = false;
    private boolean zzq = false;

    private zzaqb(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.zzl = applicationContext != null;
        context = applicationContext != null ? applicationContext : context;
        this.zza = context;
        this.zzp = new HashMap();
        if (this.zzr != null) {
            return;
        }
        this.zzr = new zzapu(context);
    }

    public static zzaqb zzg(Context context, String str, String str2, boolean z) {
        zzaph zzaphVar;
        byte[] zzb;
        zzaqb zzaqbVar = new zzaqb(context);
        try {
            zzaqbVar.zze = Executors.newCachedThreadPool(new zzapx());
            zzaqbVar.zzj = z;
            if (z) {
                zzaqbVar.zzk = zzaqbVar.zze.submit(new zzapy(zzaqbVar));
            }
            zzaqbVar.zze.execute(new zzaqa(zzaqbVar));
            try {
                GoogleApiAvailabilityLight googleApiAvailabilityLight = GoogleApiAvailabilityLight.getInstance();
                zzaqbVar.zzb = googleApiAvailabilityLight.getApkVersion(zzaqbVar.zza) > 0;
                zzaqbVar.zzc = googleApiAvailabilityLight.isGooglePlayServicesAvailable(zzaqbVar.zza) == 0;
            } catch (Throwable unused) {
            }
            zzaqbVar.zzo(0, true);
            if (zzaqe.zzf() && ((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcv)).booleanValue()) {
                throw new IllegalStateException("Task Context initialization must not be called from the UI thread.");
            }
            zzaphVar = new zzaph(null);
            zzaqbVar.zzg = zzaphVar;
            try {
                try {
                    zzb = zzanm.zzb("3ti3ezo40ryM1w4gfDBvjiqMuHkyKLnrJqm+zFKeNdY=", false);
                } catch (IllegalArgumentException e) {
                    throw new zzapg(zzaphVar, e);
                }
            } catch (zzapg e2) {
                throw new zzaps(e2);
            }
        } catch (zzaps unused2) {
        }
        if (zzb.length != 32) {
            throw new zzapg(zzaphVar);
        }
        byte[] bArr = new byte[16];
        ByteBuffer.wrap(zzb, 4, 16).get(bArr);
        for (int r3 = 0; r3 < 16; r3++) {
            bArr[r3] = (byte) (bArr[r3] ^ 68);
        }
        zzaqbVar.zzh = bArr;
        try {
            try {
                try {
                    File cacheDir = zzaqbVar.zza.getCacheDir();
                    if (cacheDir == null && (cacheDir = zzaqbVar.zza.getDir("dex", 0)) == null) {
                        throw new zzaps();
                    }
                    File file = new File(String.format("%s/%s.jar", cacheDir, "1655145693758"));
                    if (!file.exists()) {
                        byte[] zzb2 = zzaqbVar.zzg.zzb(zzaqbVar.zzh, "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");
                        file.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        fileOutputStream.write(zzb2, 0, zzb2.length);
                        fileOutputStream.close();
                    }
                    zzaqbVar.zzx(cacheDir, "1655145693758");
                    try {
                        zzaqbVar.zzf = new DexClassLoader(file.getAbsolutePath(), cacheDir.getAbsolutePath(), null, zzaqbVar.zza.getClassLoader());
                        zzy(file);
                        zzaqbVar.zzw(cacheDir, "1655145693758");
                        zzz(String.format("%s/%s.dex", cacheDir, "1655145693758"));
                        zzaqbVar.zzo = new zzaow(zzaqbVar);
                        zzaqbVar.zzq = true;
                        return zzaqbVar;
                    } catch (Throwable th) {
                        zzy(file);
                        zzaqbVar.zzw(cacheDir, "1655145693758");
                        zzz(String.format("%s/%s.dex", cacheDir, "1655145693758"));
                        throw th;
                    }
                } catch (IOException e3) {
                    throw new zzaps(e3);
                } catch (NullPointerException e4) {
                    throw new zzaps(e4);
                }
            } catch (zzapg e5) {
                throw new zzaps(e5);
            }
        } catch (FileNotFoundException e6) {
            throw new zzaps(e6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzv() {
        try {
            if (this.zzi == null && this.zzl) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zza);
                advertisingIdClient.start();
                this.zzi = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException unused) {
            this.zzi = null;
        }
    }

    private final void zzw(File file, String str) {
        Throwable th;
        FileOutputStream fileOutputStream;
        File file2 = new File(String.format("%s/%s.tmp", file, "1655145693758"));
        if (file2.exists()) {
            return;
        }
        File file3 = new File(String.format("%s/%s.dex", file, "1655145693758"));
        if (!file3.exists()) {
            return;
        }
        long length = file3.length();
        if (length <= 0) {
            return;
        }
        byte[] bArr = new byte[(int) length];
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file3);
            try {
                if (fileInputStream2.read(bArr) <= 0) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException unused) {
                    }
                    zzy(file3);
                    return;
                }
                System.out.print("test");
                System.out.print("test");
                System.out.print("test");
                zzana zza = zzanb.zza();
                zza.zzc(zzgnf.zzv(Build.VERSION.SDK.getBytes()));
                zza.zzd(zzgnf.zzv("1655145693758".getBytes()));
                byte[] bytes = this.zzg.zza(this.zzh, bArr).getBytes();
                zza.zza(zzgnf.zzv(bytes));
                zza.zzb(zzgnf.zzv(zzanq.zze(bytes)));
                file2.createNewFile();
                fileOutputStream = new FileOutputStream(file2);
                try {
                    byte[] zzaw = ((zzanb) zza.zzal()).zzaw();
                    fileOutputStream.write(zzaw, 0, zzaw.length);
                    fileOutputStream.close();
                    try {
                        fileInputStream2.close();
                    } catch (IOException unused2) {
                    }
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused3) {
                    }
                    zzy(file3);
                } catch (zzapg | IOException | NoSuchAlgorithmException unused4) {
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused5) {
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused6) {
                        }
                    }
                    zzy(file3);
                } catch (Throwable th2) {
                    th = th2;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused7) {
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException unused8) {
                        }
                    }
                    zzy(file3);
                    throw th;
                }
            } catch (zzapg | IOException | NoSuchAlgorithmException unused9) {
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
            }
        } catch (zzapg | IOException | NoSuchAlgorithmException unused10) {
            fileOutputStream = null;
        } catch (Throwable th4) {
            th = th4;
            fileOutputStream = null;
        }
    }

    private final boolean zzx(File file, String str) {
        FileOutputStream fileOutputStream;
        File file2 = new File(String.format("%s/%s.tmp", file, "1655145693758"));
        if (file2.exists()) {
            File file3 = new File(String.format("%s/%s.dex", file, "1655145693758"));
            if (!file3.exists()) {
                FileInputStream fileInputStream = null;
                try {
                    long length = file2.length();
                    if (length <= 0) {
                        zzy(file2);
                        return false;
                    }
                    byte[] bArr = new byte[(int) length];
                    FileInputStream fileInputStream2 = new FileInputStream(file2);
                    try {
                        try {
                            if (fileInputStream2.read(bArr) <= 0) {
                                Log.d(zzd, "Cannot read the cache data.");
                                zzy(file2);
                                try {
                                    fileInputStream2.close();
                                } catch (IOException unused) {
                                }
                                return false;
                            }
                            try {
                                try {
                                    zzanb zzd2 = zzanb.zzd(bArr, zzgnz.zza());
                                    if ("1655145693758".equals(new String(zzd2.zzh().zzE())) && Arrays.equals(zzd2.zzf().zzE(), zzanq.zze(zzd2.zze().zzE())) && Arrays.equals(zzd2.zzg().zzE(), Build.VERSION.SDK.getBytes())) {
                                        byte[] zzb = this.zzg.zzb(this.zzh, new String(zzd2.zze().zzE()));
                                        file3.createNewFile();
                                        fileOutputStream = new FileOutputStream(file3);
                                        try {
                                            fileOutputStream.write(zzb, 0, zzb.length);
                                            try {
                                                fileInputStream2.close();
                                            } catch (IOException unused2) {
                                            }
                                            try {
                                                fileOutputStream.close();
                                            } catch (IOException unused3) {
                                            }
                                            return true;
                                        } catch (zzapg | IOException | NoSuchAlgorithmException unused4) {
                                            fileInputStream = fileInputStream2;
                                            if (fileInputStream != null) {
                                                try {
                                                    fileInputStream.close();
                                                } catch (IOException unused5) {
                                                }
                                            }
                                            if (fileOutputStream != null) {
                                                try {
                                                    fileOutputStream.close();
                                                } catch (IOException unused6) {
                                                }
                                            }
                                            return false;
                                        } catch (Throwable th) {
                                            th = th;
                                            fileInputStream = fileInputStream2;
                                            if (fileInputStream != null) {
                                                try {
                                                    fileInputStream.close();
                                                } catch (IOException unused7) {
                                                }
                                            }
                                            if (fileOutputStream != null) {
                                                try {
                                                    fileOutputStream.close();
                                                } catch (IOException unused8) {
                                                }
                                            }
                                            throw th;
                                        }
                                    }
                                    zzy(file2);
                                    try {
                                        fileInputStream2.close();
                                    } catch (IOException unused9) {
                                    }
                                    return false;
                                } catch (IOException unused10) {
                                    return false;
                                }
                            } catch (NullPointerException unused11) {
                                fileInputStream2.close();
                                return false;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream = null;
                        }
                    } catch (zzapg | IOException | NoSuchAlgorithmException unused12) {
                        fileOutputStream = null;
                    }
                } catch (zzapg | IOException | NoSuchAlgorithmException unused13) {
                    fileOutputStream = null;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                }
            }
            return false;
        }
        return false;
    }

    private static final void zzy(File file) {
        if (file.exists()) {
            file.delete();
        } else {
            Log.d(zzd, String.format("File %s not found. No need for deletion", file.getAbsolutePath()));
        }
    }

    private static final void zzz(String str) {
        zzy(new File(str));
    }

    public final int zza() {
        if (this.zzo != null) {
            return zzaow.zzd();
        }
        return Integer.MIN_VALUE;
    }

    public final Context zzb() {
        return this.zza;
    }

    public final zzamx zzc() {
        return this.zzm;
    }

    public final zzaow zzd() {
        return this.zzo;
    }

    public final zzaph zze() {
        return this.zzg;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzapu zzf() {
        return this.zzr;
    }

    public final AdvertisingIdClient zzh() {
        if (this.zzj) {
            if (this.zzi != null) {
                return this.zzi;
            }
            Future future = this.zzk;
            if (future != null) {
                try {
                    future.get(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, TimeUnit.MILLISECONDS);
                    this.zzk = null;
                } catch (InterruptedException | ExecutionException unused) {
                } catch (TimeoutException unused2) {
                    this.zzk.cancel(true);
                }
            }
            return this.zzi;
        }
        return null;
    }

    public final DexClassLoader zzi() {
        return this.zzf;
    }

    public final Method zzj(String str, String str2) {
        zzarl zzarlVar = (zzarl) this.zzp.get(new Pair(str, str2));
        if (zzarlVar == null) {
            return null;
        }
        return zzarlVar.zza();
    }

    public final ExecutorService zzk() {
        return this.zze;
    }

    public final Future zzl() {
        return this.zzn;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzo(int r3, boolean z) {
        if (this.zzc) {
            Future<?> submit = this.zze.submit(new zzapz(this, r3, true));
            if (r3 == 0) {
                this.zzn = submit;
            }
        }
    }

    public final boolean zzp() {
        return this.zzc;
    }

    public final boolean zzq() {
        return this.zzb;
    }

    public final boolean zzr() {
        return this.zzq;
    }

    public final boolean zzs() {
        return this.zzr.zza();
    }

    public final boolean zzt(String str, String str2, Class... clsArr) {
        Pair pair = new Pair(str, str2);
        if (this.zzp.containsKey(pair)) {
            return false;
        }
        this.zzp.put(pair, new zzarl(this, str, str2, clsArr));
        return true;
    }

    public final byte[] zzu() {
        return this.zzh;
    }
}
