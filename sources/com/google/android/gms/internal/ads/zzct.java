package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Looper;
import android.view.accessibility.CaptioningManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public class zzct {
    private final int zza;
    private final int zzb;
    private final int zzc;
    private final int zzd;
    private int zze;
    private int zzf;
    private boolean zzg;
    private final zzfuv zzh;
    private final zzfuv zzi;
    private final int zzj;
    private final int zzk;
    private final zzfuv zzl;
    private zzfuv zzm;
    private int zzn;
    private final HashMap zzo;
    private final HashSet zzp;

    @Deprecated
    public zzct() {
        this.zza = Integer.MAX_VALUE;
        this.zzb = Integer.MAX_VALUE;
        this.zzc = Integer.MAX_VALUE;
        this.zzd = Integer.MAX_VALUE;
        this.zze = Integer.MAX_VALUE;
        this.zzf = Integer.MAX_VALUE;
        this.zzg = true;
        this.zzh = zzfuv.zzo();
        this.zzi = zzfuv.zzo();
        this.zzj = Integer.MAX_VALUE;
        this.zzk = Integer.MAX_VALUE;
        this.zzl = zzfuv.zzo();
        this.zzm = zzfuv.zzo();
        this.zzn = 0;
        this.zzo = new HashMap();
        this.zzp = new HashSet();
    }

    public final zzct zzd(Context context) {
        CaptioningManager captioningManager;
        if ((zzel.zza >= 23 || Looper.myLooper() != null) && (captioningManager = (CaptioningManager) context.getSystemService("captioning")) != null && captioningManager.isEnabled()) {
            this.zzn = 1088;
            Locale locale = captioningManager.getLocale();
            if (locale != null) {
                this.zzm = zzfuv.zzp(zzel.zzN(locale));
            }
        }
        return this;
    }

    public zzct zze(int r1, int r2, boolean z) {
        this.zze = r1;
        this.zzf = r2;
        this.zzg = true;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public zzct(zzcu zzcuVar) {
        this.zza = Integer.MAX_VALUE;
        this.zzb = Integer.MAX_VALUE;
        this.zzc = Integer.MAX_VALUE;
        this.zzd = Integer.MAX_VALUE;
        this.zze = zzcuVar.zzl;
        this.zzf = zzcuVar.zzm;
        this.zzg = zzcuVar.zzn;
        this.zzh = zzcuVar.zzo;
        this.zzi = zzcuVar.zzq;
        this.zzj = Integer.MAX_VALUE;
        this.zzk = Integer.MAX_VALUE;
        this.zzl = zzcuVar.zzu;
        this.zzm = zzcuVar.zzv;
        this.zzn = zzcuVar.zzw;
        this.zzp = new HashSet(zzcuVar.zzC);
        this.zzo = new HashMap(zzcuVar.zzB);
    }
}
