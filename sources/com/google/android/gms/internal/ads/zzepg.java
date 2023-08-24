package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzepg implements zzeun {
    private final zzeun zza;
    private final zzfdn zzb;
    private final Context zzc;
    private final zzcfw zzd;

    public zzepg(zzeqx zzeqxVar, zzfdn zzfdnVar, Context context, zzcfw zzcfwVar) {
        this.zza = zzeqxVar;
        this.zzb = zzfdnVar;
        this.zzc = context;
        this.zzd = zzcfwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final int zza() {
        return 7;
    }

    @Override // com.google.android.gms.internal.ads.zzeun
    public final zzfyx zzb() {
        return zzfyo.zzm(this.zza.zzb(), new zzfru() { // from class: com.google.android.gms.internal.ads.zzepf
            @Override // com.google.android.gms.internal.ads.zzfru
            public final Object apply(Object obj) {
                return zzepg.this.zzc((zzeus) obj);
            }
        }, zzcha.zzf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzeph zzc(zzeus zzeusVar) {
        String str;
        boolean z;
        String str2;
        float f;
        int r8;
        int r9;
        int r5;
        DisplayMetrics displayMetrics;
        com.google.android.gms.ads.internal.client.zzq zzqVar = this.zzb.zze;
        com.google.android.gms.ads.internal.client.zzq[] zzqVarArr = zzqVar.zzg;
        if (zzqVarArr == null) {
            str = zzqVar.zza;
            z = zzqVar.zzi;
        } else {
            str = null;
            boolean z2 = false;
            boolean z3 = false;
            z = false;
            for (com.google.android.gms.ads.internal.client.zzq zzqVar2 : zzqVarArr) {
                boolean z4 = zzqVar2.zzi;
                if (!z4 && !z2) {
                    str = zzqVar2.zza;
                    z2 = true;
                }
                if (z4) {
                    if (z3) {
                        z3 = true;
                    } else {
                        z3 = true;
                        z = true;
                    }
                }
                if (z2 && z3) {
                    break;
                }
            }
        }
        Resources resources = this.zzc.getResources();
        if (resources == null || (displayMetrics = resources.getDisplayMetrics()) == null) {
            str2 = null;
            f = 0.0f;
            r8 = 0;
            r9 = 0;
        } else {
            float f2 = displayMetrics.density;
            int r7 = displayMetrics.widthPixels;
            r9 = displayMetrics.heightPixels;
            str2 = this.zzd.zzh().zzm();
            r8 = r7;
            f = f2;
        }
        StringBuilder sb = new StringBuilder();
        com.google.android.gms.ads.internal.client.zzq[] zzqVarArr2 = zzqVar.zzg;
        if (zzqVarArr2 != null) {
            boolean z5 = false;
            for (com.google.android.gms.ads.internal.client.zzq zzqVar3 : zzqVarArr2) {
                if (zzqVar3.zzi) {
                    z5 = true;
                } else {
                    if (sb.length() != 0) {
                        sb.append("|");
                    }
                    int r4 = zzqVar3.zze;
                    if (r4 == -1) {
                        r4 = f != 0.0f ? (int) (zzqVar3.zzf / f) : -1;
                    }
                    sb.append(r4);
                    sb.append("x");
                    int r42 = zzqVar3.zzb;
                    if (r42 == -2) {
                        r42 = f != 0.0f ? (int) (zzqVar3.zzc / f) : -2;
                    }
                    sb.append(r42);
                }
            }
            if (z5) {
                if (sb.length() != 0) {
                    r5 = 0;
                    sb.insert(0, "|");
                } else {
                    r5 = 0;
                }
                sb.insert(r5, "320x50");
            }
        }
        return new zzeph(zzqVar, str, z, sb.toString(), f, r8, r9, str2, this.zzb.zzp);
    }
}
