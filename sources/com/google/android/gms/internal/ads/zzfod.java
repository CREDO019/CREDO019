package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.exoplayer2.PlaybackException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.security.GeneralSecurityException;
import java.util.HashMap;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzfod {
    private static final HashMap zza = new HashMap();
    private final Context zzb;
    private final zzfoe zzc;
    private final zzfmf zzd;
    private final zzfma zze;
    private zzfns zzf;
    private final Object zzg = new Object();

    public zzfod(Context context, zzfoe zzfoeVar, zzfmf zzfmfVar, zzfma zzfmaVar) {
        this.zzb = context;
        this.zzc = zzfoeVar;
        this.zzd = zzfmfVar;
        this.zze = zzfmaVar;
    }

    private final synchronized Class zzd(zzfnt zzfntVar) throws zzfoc {
        String zzk = zzfntVar.zza().zzk();
        HashMap hashMap = zza;
        Class cls = (Class) hashMap.get(zzk);
        if (cls != null) {
            return cls;
        }
        try {
            if (this.zze.zza(zzfntVar.zzc())) {
                try {
                    File zzb = zzfntVar.zzb();
                    if (!zzb.exists()) {
                        zzb.mkdirs();
                    }
                    Class loadClass = new DexClassLoader(zzfntVar.zzc().getAbsolutePath(), zzb.getAbsolutePath(), null, this.zzb.getClassLoader()).loadClass("com.google.ccc.abuse.droidguard.DroidGuard");
                    hashMap.put(zzk, loadClass);
                    return loadClass;
                } catch (ClassNotFoundException | IllegalArgumentException | SecurityException e) {
                    throw new zzfoc(2008, e);
                }
            }
            throw new zzfoc(2026, "VM did not pass signature verification");
        } catch (GeneralSecurityException e2) {
            throw new zzfoc(2026, e2);
        }
    }

    public final zzfmi zza() {
        zzfns zzfnsVar;
        synchronized (this.zzg) {
            zzfnsVar = this.zzf;
        }
        return zzfnsVar;
    }

    public final zzfnt zzb() {
        synchronized (this.zzg) {
            zzfns zzfnsVar = this.zzf;
            if (zzfnsVar != null) {
                return zzfnsVar.zzf();
            }
            return null;
        }
    }

    public final boolean zzc(zzfnt zzfntVar) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            try {
                zzfns zzfnsVar = new zzfns(zzd(zzfntVar).getDeclaredConstructor(Context.class, String.class, byte[].class, Object.class, Bundle.class, Integer.TYPE).newInstance(this.zzb, "msa-r", zzfntVar.zze(), null, new Bundle(), 2), zzfntVar, this.zzc, this.zzd);
                if (!zzfnsVar.zzh()) {
                    throw new zzfoc(4000, "init failed");
                }
                int zze = zzfnsVar.zze();
                if (zze == 0) {
                    synchronized (this.zzg) {
                        zzfns zzfnsVar2 = this.zzf;
                        if (zzfnsVar2 != null) {
                            try {
                                zzfnsVar2.zzg();
                            } catch (zzfoc e) {
                                this.zzd.zzc(e.zza(), -1L, e);
                            }
                        }
                        this.zzf = zzfnsVar;
                    }
                    this.zzd.zzd(3000, System.currentTimeMillis() - currentTimeMillis);
                    return true;
                }
                throw new zzfoc((int) PlaybackException.ERROR_CODE_DECODER_INIT_FAILED, "ci: " + zze);
            } catch (Exception e2) {
                throw new zzfoc((int) PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS, e2);
            }
        } catch (zzfoc e3) {
            this.zzd.zzc(e3.zza(), System.currentTimeMillis() - currentTimeMillis, e3);
            return false;
        } catch (Exception e4) {
            this.zzd.zzc(4010, System.currentTimeMillis() - currentTimeMillis, e4);
            return false;
        }
    }
}
