package com.google.android.gms.internal.ads;

import android.os.Build;
import android.os.ConditionVariable;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzaow {
    protected volatile Boolean zzb;
    private final zzaqb zze;
    private static final ConditionVariable zzc = new ConditionVariable();
    protected static volatile zzfoh zza = null;
    private static volatile Random zzd = null;

    public zzaow(zzaqb zzaqbVar) {
        this.zze = zzaqbVar;
        zzaqbVar.zzk().execute(new zzaov(this));
    }

    public static final int zzd() {
        try {
            return Build.VERSION.SDK_INT >= 21 ? ThreadLocalRandom.current().nextInt() : zze().nextInt();
        } catch (RuntimeException unused) {
            return zze().nextInt();
        }
    }

    private static Random zze() {
        if (zzd == null) {
            synchronized (zzaow.class) {
                if (zzd == null) {
                    zzd = new Random();
                }
            }
        }
        return zzd;
    }

    public final void zzc(int r3, int r4, long j, String str, Exception exc) {
        try {
            zzc.block();
            if (!this.zzb.booleanValue() || zza == null) {
                return;
            }
            zzalt zza2 = zzalx.zza();
            zza2.zza(this.zze.zza.getPackageName());
            zza2.zze(j);
            if (str != null) {
                zza2.zzb(str);
            }
            if (exc != null) {
                StringWriter stringWriter = new StringWriter();
                exc.printStackTrace(new PrintWriter(stringWriter));
                zza2.zzf(stringWriter.toString());
                zza2.zzd(exc.getClass().getName());
            }
            zzfog zza3 = zza.zza(((zzalx) zza2.zzal()).zzaw());
            zza3.zza(r3);
            if (r4 != -1) {
                zza3.zzb(r4);
            }
            zza3.zzc();
        } catch (Exception unused) {
        }
    }
}
