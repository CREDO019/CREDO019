package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class zzfiq {
    public static zzfir zza(Context context, int r3) {
        boolean booleanValue;
        if (zzfje.zza()) {
            int r0 = r3 - 2;
            if (r0 == 20 || r0 == 21) {
                booleanValue = ((Boolean) zzbkh.zze.zze()).booleanValue();
            } else {
                switch (r0) {
                    case 2:
                    case 3:
                    case 6:
                    case 7:
                    case 8:
                        booleanValue = ((Boolean) zzbkh.zzc.zze()).booleanValue();
                        break;
                    case 4:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                        booleanValue = ((Boolean) zzbkh.zzd.zze()).booleanValue();
                        break;
                    case 5:
                        booleanValue = ((Boolean) zzbkh.zzb.zze()).booleanValue();
                        break;
                }
            }
            if (booleanValue) {
                return new zzfit(context, r3);
            }
        }
        return new zzfjn();
    }

    public static zzfir zzb(Context context, int r1, int r2, com.google.android.gms.ads.internal.client.zzl zzlVar) {
        zzfir zza = zza(context, r1);
        if (zza instanceof zzfit) {
            zza.zzf();
            zza.zzk(r2);
            if (zzfjb.zze(zzlVar.zzp)) {
                zza.zzd(zzlVar.zzp);
            }
            return zza;
        }
        return zza;
    }
}
