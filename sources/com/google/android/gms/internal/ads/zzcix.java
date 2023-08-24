package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.google.android.gms.internal.ads.zzfph;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcix {
    private final Context zza;
    private final String zzb;
    private final zzcgt zzc;
    private final zzbjk zzd;
    private final zzbjn zze;
    private final com.google.android.gms.ads.internal.util.zzbf zzf;
    private final long[] zzg;
    private final String[] zzh;
    private boolean zzi;
    private boolean zzj;
    private boolean zzk;
    private boolean zzl;
    private boolean zzm;
    private zzcic zzn;
    private boolean zzo;
    private boolean zzp;
    private long zzq;

    public zzcix(Context context, zzcgt zzcgtVar, String str, zzbjn zzbjnVar, zzbjk zzbjkVar) {
        com.google.android.gms.ads.internal.util.zzbd zzbdVar = new com.google.android.gms.ads.internal.util.zzbd();
        zzbdVar.zza("min_1", Double.MIN_VALUE, 1.0d);
        zzbdVar.zza("1_5", 1.0d, 5.0d);
        zzbdVar.zza("5_10", 5.0d, 10.0d);
        zzbdVar.zza("10_20", 10.0d, 20.0d);
        zzbdVar.zza("20_30", 20.0d, 30.0d);
        zzbdVar.zza("30_max", 30.0d, Double.MAX_VALUE);
        this.zzf = zzbdVar.zzb();
        this.zzi = false;
        this.zzj = false;
        this.zzk = false;
        this.zzl = false;
        this.zzq = -1L;
        this.zza = context;
        this.zzc = zzcgtVar;
        this.zzb = str;
        this.zze = zzbjnVar;
        this.zzd = zzbjkVar;
        String str2 = (String) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzy);
        if (str2 == null) {
            this.zzh = new String[0];
            this.zzg = new long[0];
            return;
        }
        String[] split = TextUtils.split(str2, ",");
        int length = split.length;
        this.zzh = new String[length];
        this.zzg = new long[length];
        for (int r0 = 0; r0 < split.length; r0++) {
            try {
                this.zzg[r0] = Long.parseLong(split[r0]);
            } catch (NumberFormatException e) {
                com.google.android.gms.ads.internal.util.zze.zzk("Unable to parse frame hash target time number.", e);
                this.zzg[r0] = -1;
            }
        }
    }

    public final void zza(zzcic zzcicVar) {
        zzbjf.zza(this.zze, this.zzd, "vpc2");
        this.zzi = true;
        this.zze.zzd("vpn", zzcicVar.zzj());
        this.zzn = zzcicVar;
    }

    public final void zzb() {
        if (!this.zzi || this.zzj) {
            return;
        }
        zzbjf.zza(this.zze, this.zzd, "vfr2");
        this.zzj = true;
    }

    public final void zzc() {
        this.zzm = true;
        if (!this.zzj || this.zzk) {
            return;
        }
        zzbjf.zza(this.zze, this.zzd, "vfp2");
        this.zzk = true;
    }

    public final void zzd() {
        if (!((Boolean) zzblc.zza.zze()).booleanValue() || this.zzo) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString(SessionDescription.ATTR_TYPE, "native-player-metrics");
        bundle.putString("request", this.zzb);
        bundle.putString("player", this.zzn.zzj());
        for (com.google.android.gms.ads.internal.util.zzbc zzbcVar : this.zzf.zza()) {
            bundle.putString("fps_c_".concat(String.valueOf(zzbcVar.zza)), Integer.toString(zzbcVar.zze));
            bundle.putString("fps_p_".concat(String.valueOf(zzbcVar.zza)), Double.toString(zzbcVar.zzd));
        }
        int r0 = 0;
        while (true) {
            long[] jArr = this.zzg;
            if (r0 < jArr.length) {
                String str = this.zzh[r0];
                if (str != null) {
                    Long valueOf = Long.valueOf(jArr[r0]);
                    Objects.toString(valueOf);
                    bundle.putString("fh_".concat(valueOf.toString()), str);
                }
                r0++;
            } else {
                com.google.android.gms.ads.internal.zzt.zzq();
                final Context context = this.zza;
                final String str2 = this.zzc.zza;
                com.google.android.gms.ads.internal.zzt.zzq();
                bundle.putString("device", com.google.android.gms.ads.internal.util.zzs.zzq());
                bundle.putString("eids", TextUtils.join(",", zzbiy.zza()));
                com.google.android.gms.ads.internal.client.zzaw.zzb();
                zzcgg.zzv(context, str2, "gmob-apps", bundle, true, new zzcgf() { // from class: com.google.android.gms.ads.internal.util.zzk
                    @Override // com.google.android.gms.internal.ads.zzcgf
                    public final boolean zza(String str3) {
                        Context context2 = context;
                        String str4 = str2;
                        zzfph zzfphVar = zzs.zza;
                        com.google.android.gms.ads.internal.zzt.zzq();
                        zzs.zzH(context2, str4, str3);
                        return true;
                    }
                });
                this.zzo = true;
                return;
            }
        }
    }

    public final void zze() {
        this.zzm = false;
    }

    public final void zzf(zzcic zzcicVar) {
        if (this.zzk && !this.zzl) {
            if (com.google.android.gms.ads.internal.util.zze.zzc() && !this.zzl) {
                com.google.android.gms.ads.internal.util.zze.zza("VideoMetricsMixin first frame");
            }
            zzbjf.zza(this.zze, this.zzd, "vff2");
            this.zzl = true;
        }
        long nanoTime = com.google.android.gms.ads.internal.zzt.zzB().nanoTime();
        if (this.zzm && this.zzp && this.zzq != -1) {
            this.zzf.zzb(TimeUnit.SECONDS.toNanos(1L) / (nanoTime - this.zzq));
        }
        this.zzp = this.zzm;
        this.zzq = nanoTime;
        long longValue = ((Long) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzz)).longValue();
        long zza = zzcicVar.zza();
        int r11 = 0;
        while (true) {
            String[] strArr = this.zzh;
            if (r11 >= strArr.length) {
                return;
            }
            if (strArr[r11] == null && longValue > Math.abs(zza - this.zzg[r11])) {
                String[] strArr2 = this.zzh;
                int r4 = 8;
                Bitmap bitmap = zzcicVar.getBitmap(8, 8);
                long j = 63;
                long j2 = 0;
                int r10 = 0;
                while (r10 < r4) {
                    int r5 = 0;
                    while (r5 < r4) {
                        int pixel = bitmap.getPixel(r5, r10);
                        j2 |= ((Color.blue(pixel) + Color.red(pixel)) + Color.green(pixel) > 128 ? 1L : 0L) << ((int) j);
                        r5++;
                        j--;
                        r4 = 8;
                    }
                    r10++;
                    r4 = 8;
                }
                strArr2[r11] = String.format("%016X", Long.valueOf(j2));
                return;
            }
            r11++;
        }
    }
}
