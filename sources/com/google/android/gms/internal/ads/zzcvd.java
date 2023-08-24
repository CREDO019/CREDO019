package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzcvd {
    private final String zza;
    private final zzbuo zzb;
    private final Executor zzc;
    private zzcvi zzd;
    private final zzbpq zze = new zzcva(this);
    private final zzbpq zzf = new zzcvc(this);

    public zzcvd(String str, zzbuo zzbuoVar, Executor executor) {
        this.zza = str;
        this.zzb = zzbuoVar;
        this.zzc = executor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ boolean zzg(zzcvd zzcvdVar, Map map) {
        if (map == null) {
            return false;
        }
        String str = (String) map.get("hashCode");
        return !TextUtils.isEmpty(str) && str.equals(zzcvdVar.zza);
    }

    public final void zzc(zzcvi zzcviVar) {
        this.zzb.zzb("/updateActiveView", this.zze);
        this.zzb.zzb("/untrackActiveViewUnit", this.zzf);
        this.zzd = zzcviVar;
    }

    public final void zzd(zzcmn zzcmnVar) {
        zzcmnVar.zzaf("/updateActiveView", this.zze);
        zzcmnVar.zzaf("/untrackActiveViewUnit", this.zzf);
    }

    public final void zze() {
        this.zzb.zzc("/updateActiveView", this.zze);
        this.zzb.zzc("/untrackActiveViewUnit", this.zzf);
    }

    public final void zzf(zzcmn zzcmnVar) {
        zzcmnVar.zzaw("/updateActiveView", this.zze);
        zzcmnVar.zzaw("/untrackActiveViewUnit", this.zzf);
    }
}
