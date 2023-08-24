package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzarl {
    protected static final String zza = "zzarl";
    private final zzaqb zzb;
    private final String zzc;
    private final String zzd;
    private final Class[] zzf;
    private volatile Method zze = null;
    private final CountDownLatch zzg = new CountDownLatch(1);

    public zzarl(zzaqb zzaqbVar, String str, String str2, Class... clsArr) {
        this.zzb = zzaqbVar;
        this.zzc = str;
        this.zzd = str2;
        this.zzf = clsArr;
        zzaqbVar.zzk().submit(new zzark(this));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void zzb(zzarl zzarlVar) {
        CountDownLatch countDownLatch;
        Class loadClass;
        try {
            zzaqb zzaqbVar = zzarlVar.zzb;
            loadClass = zzaqbVar.zzi().loadClass(zzarlVar.zzc(zzaqbVar.zzu(), zzarlVar.zzc));
        } catch (zzapg | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException unused) {
        } catch (NullPointerException unused2) {
            countDownLatch = zzarlVar.zzg;
        } catch (Throwable th) {
            zzarlVar.zzg.countDown();
            throw th;
        }
        if (loadClass == null) {
            countDownLatch = zzarlVar.zzg;
        } else {
            zzarlVar.zze = loadClass.getMethod(zzarlVar.zzc(zzarlVar.zzb.zzu(), zzarlVar.zzd), zzarlVar.zzf);
            if (zzarlVar.zze == null) {
                countDownLatch = zzarlVar.zzg;
            }
            countDownLatch = zzarlVar.zzg;
        }
        countDownLatch.countDown();
    }

    private final String zzc(byte[] bArr, String str) throws zzapg, UnsupportedEncodingException {
        return new String(this.zzb.zze().zzb(bArr, str), "UTF-8");
    }

    public final Method zza() {
        if (this.zze != null) {
            return this.zze;
        }
        try {
            if (this.zzg.await(2L, TimeUnit.SECONDS)) {
                return this.zze;
            }
            return null;
        } catch (InterruptedException unused) {
            return null;
        }
    }
}
