package com.google.android.gms.internal.vision;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.0.0 */
/* loaded from: classes3.dex */
public abstract class zzbe<T> {
    private static Context zze = null;
    private static boolean zzfz = false;
    private static zzcu<zzcn<zzba>> zzga;
    private final String name;
    private final zzbk zzgb;
    private final T zzgc;
    private volatile int zzge;
    private volatile T zzgf;
    private static final Object zzfy = new Object();
    private static final AtomicInteger zzgd = new AtomicInteger();

    public static void init(Context context) {
        synchronized (zzfy) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            if (zze != context) {
                zzaq.zzx();
                zzbj.zzx();
                zzav.zzaa();
                zzgd.incrementAndGet();
                zze = context;
                zzga = zzcx.zza(zzbd.zzfx);
            }
        }
    }

    abstract T zza(Object obj);

    public static void maybeInit(Context context) {
        synchronized (zzfy) {
            if (zze == null) {
                init(context);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzab() {
        zzgd.incrementAndGet();
    }

    private zzbe(zzbk zzbkVar, String str, T t) {
        this.zzge = -1;
        if (zzbkVar.zzgk == null && zzbkVar.zzgl == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        if (zzbkVar.zzgk != null && zzbkVar.zzgl != null) {
            throw new IllegalArgumentException("Must pass one of SharedPreferences file name or ContentProvider URI");
        }
        this.zzgb = zzbkVar;
        this.name = str;
        this.zzgc = t;
    }

    private final String zze(String str) {
        if (str == null || !str.isEmpty()) {
            String valueOf = String.valueOf(str);
            String valueOf2 = String.valueOf(this.name);
            return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        return this.name;
    }

    public final String zzac() {
        return zze(this.zzgb.zzgn);
    }

    public final T get() {
        T zzad;
        int r0 = zzgd.get();
        if (this.zzge < r0) {
            synchronized (this) {
                if (this.zzge < r0) {
                    if (zze == null) {
                        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
                    }
                    zzcn<zzba> zzcnVar = zzga.get();
                    if (zzcnVar.isPresent()) {
                        String zza = zzcnVar.get().zza(this.zzgb.zzgl, this.zzgb.zzgk, this.zzgb.zzgn, this.name);
                        if (zza != null) {
                            zzad = zza(zza);
                            this.zzgf = zzad;
                            this.zzge = r0;
                        }
                        zzad = this.zzgc;
                        this.zzgf = zzad;
                        this.zzge = r0;
                    } else if (this.zzgb.zzgp) {
                        zzad = this.zzgc;
                        this.zzgf = zzad;
                        this.zzge = r0;
                    } else {
                        zzad = this.zzgc;
                        this.zzgf = zzad;
                        this.zzge = r0;
                    }
                }
            }
        }
        return this.zzgf;
    }

    @Nullable
    private final T zzad() {
        zzau zzb;
        Object zzb2;
        boolean z = false;
        if (!this.zzgb.zzgq) {
            String str = (String) zzav.zze(zze).zzb("gms:phenotype:phenotype_flag:debug_bypass_phenotype");
            if (str != null && zzal.zzeu.matcher(str).matches()) {
                z = true;
            }
        }
        if (!z) {
            if (this.zzgb.zzgl != null) {
                if (!zzbc.zza(zze, this.zzgb.zzgl)) {
                    zzb = null;
                } else if (this.zzgb.zzgr) {
                    ContentResolver contentResolver = zze.getContentResolver();
                    String lastPathSegment = this.zzgb.zzgl.getLastPathSegment();
                    String packageName = zze.getPackageName();
                    StringBuilder sb = new StringBuilder(String.valueOf(lastPathSegment).length() + 1 + String.valueOf(packageName).length());
                    sb.append(lastPathSegment);
                    sb.append("#");
                    sb.append(packageName);
                    zzb = zzaq.zza(contentResolver, zzbb.getContentProviderUri(sb.toString()));
                } else {
                    zzb = zzaq.zza(zze.getContentResolver(), this.zzgb.zzgl);
                }
            } else {
                zzb = zzbj.zzb(zze, this.zzgb.zzgk);
            }
            if (zzb != null && (zzb2 = zzb.zzb(zzac())) != null) {
                return zza(zzb2);
            }
        } else if (Log.isLoggable("PhenotypeFlag", 3)) {
            String valueOf = String.valueOf(zzac());
            Log.d("PhenotypeFlag", valueOf.length() != 0 ? "Bypass reading Phenotype values for flag: ".concat(valueOf) : new String("Bypass reading Phenotype values for flag: "));
        }
        return null;
    }

    @Nullable
    private final T zzae() {
        if (!this.zzgb.zzgo && (this.zzgb.zzgs == null || this.zzgb.zzgs.apply(zze).booleanValue())) {
            Object zzb = zzav.zze(zze).zzb(this.zzgb.zzgo ? null : zze(this.zzgb.zzgm));
            if (zzb != null) {
                return zza(zzb);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzbe<Boolean> zza(zzbk zzbkVar, String str, boolean z) {
        return new zzbf(zzbkVar, str, Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> zzbe<T> zza(zzbk zzbkVar, String str, T t, zzbh<T> zzbhVar) {
        return new zzbi(zzbkVar, str, t, zzbhVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ zzcn zzaf() {
        new zzaz();
        return zzaz.zzf(zze);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbe(zzbk zzbkVar, String str, Object obj, zzbg zzbgVar) {
        this(zzbkVar, str, obj);
    }
}
