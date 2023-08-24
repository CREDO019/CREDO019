package com.google.android.gms.internal.ads;

import android.view.View;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzarj extends zzarm {
    private final View zzi;

    public zzarj(zzaqb zzaqbVar, String str, String str2, zzamh zzamhVar, int r12, int r13, View view) {
        super(zzaqbVar, "I7Z8iinoDf65D6f8x6SJHqGD1Z2cIloE56napHJ3hKPe1zHuuQTwZLhUlKl9SuDr", "CMP58KUFBRi55MrO79QJf+iIcc+kMldspC1nSaWllCQ=", zzamhVar, r12, 57);
        this.zzi = view;
    }

    @Override // com.google.android.gms.internal.ads.zzarm
    protected final void zza() throws IllegalAccessException, InvocationTargetException {
        if (this.zzi != null) {
            Boolean bool = (Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzcB);
            Boolean bool2 = (Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zziw);
            zzaqf zzaqfVar = new zzaqf((String) this.zzf.invoke(null, this.zzi, this.zzb.zzb().getResources().getDisplayMetrics(), bool, bool2));
            zzamv zza = zzamw.zza();
            zza.zzb(zzaqfVar.zza.longValue());
            zza.zzd(zzaqfVar.zzb.longValue());
            zza.zze(zzaqfVar.zzc.longValue());
            if (bool2.booleanValue()) {
                zza.zzc(zzaqfVar.zze.longValue());
            }
            if (bool.booleanValue()) {
                zza.zza(zzaqfVar.zzd.longValue());
            }
            this.zze.zzX((zzamw) zza.zzal());
        }
    }
}
