package com.google.android.gms.internal.ads;

import java.util.UUID;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@21.2.0 */
/* loaded from: classes2.dex */
public final class zzbur implements zzfxv {
    private final zzbtx zza;
    private final zzbty zzb;
    private final String zzc = "google.afma.activeView.handleUpdate";
    private final zzfyx zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbur(zzfyx zzfyxVar, String str, zzbty zzbtyVar, zzbtx zzbtxVar) {
        this.zzd = zzfyxVar;
        this.zzb = zzbtyVar;
        this.zza = zzbtxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfxv
    public final zzfyx zza(Object obj) throws Exception {
        return zzb(obj);
    }

    public final zzfyx zzb(final Object obj) {
        return zzfyo.zzn(this.zzd, new zzfxv() { // from class: com.google.android.gms.internal.ads.zzbup
            @Override // com.google.android.gms.internal.ads.zzfxv
            public final zzfyx zza(Object obj2) {
                return zzbur.this.zzc(obj, (zzbts) obj2);
            }
        }, zzcha.zzf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ zzfyx zzc(Object obj, zzbts zzbtsVar) throws Exception {
        zzchf zzchfVar = new zzchf();
        com.google.android.gms.ads.internal.zzt.zzq();
        String str = UUID.randomUUID().toString();
        zzbpp.zzo.zzc(str, new zzbuq(this, zzchfVar));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", str);
        jSONObject.put("args", (JSONObject) obj);
        zzbtsVar.zzl(this.zzc, jSONObject);
        return zzchfVar;
    }
}
