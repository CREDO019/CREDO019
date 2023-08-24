package com.google.android.gms.internal.ads;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
final class zzebu implements zzfyk {
    final /* synthetic */ zzebv zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzebu(zzebv zzebvVar) {
        this.zza = zzebvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final void zza(Throwable th) {
        Pattern pattern;
        zzeez zzeezVar;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfj)).booleanValue()) {
            pattern = zzebv.zza;
            Matcher matcher = pattern.matcher(th.getMessage());
            if (matcher.matches()) {
                String group = matcher.group(1);
                zzeezVar = this.zza.zzf;
                zzeezVar.zzi(Integer.parseInt(group));
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzfyk
    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzeez zzeezVar;
        zzeez zzeezVar2;
        zzfde zzfdeVar = (zzfde) obj;
        if (((Boolean) com.google.android.gms.ads.internal.client.zzay.zzc().zzb(zzbiy.zzfj)).booleanValue()) {
            zzeezVar = this.zza.zzf;
            zzeezVar.zzi(zzfdeVar.zzb.zzb.zze);
            zzeezVar2 = this.zza.zzf;
            zzeezVar2.zzj(zzfdeVar.zzb.zzb.zzf);
        }
    }
}
